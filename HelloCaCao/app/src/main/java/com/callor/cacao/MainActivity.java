package com.callor.cacao;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.callor.cacao.adpter.ChattAdapter;
import com.callor.cacao.model.Chatt;
import com.callor.cacao.service.FirebaseServiceImplV1;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // chatt 메시지를 전달하는 view 들
    private EditText txt_msg;
    private AppCompatButton btn_send;

    // chatt 메시지를 표현할 view 들
    private RecyclerView chat_list_view;
    private ChattAdapter chattAdapter ;
    private List<Chatt> chattList;

    // firebase와 연결하는 Connection을 위한 객체 선언하기
    private DatabaseReference dbRef;
    private String nickname = "익명";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * setContentView(R.layout.activity_main);
         * *layout.xml 파일을 읽어서 화면을 만드는 method
         * setContentView는 한개의 파일을 읽어서
         *      한개의 전체 화면을 만드는 것
         */
        setContentView(R.layout.activity_main);

        SharedPreferences preferences
                = PreferenceManager.getDefaultSharedPreferences(this);
        nickname = preferences.getString("nick_name",
                "익명");
        String alarm = preferences.getString("alarm","ON");
        Log.d("닉네임",nickname);
        Log.d("알람",alarm);

        /**
         * custom 된 toolbar를 ActionBar로 설정하기 위한 코드
         */
        Toolbar main_toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(main_toolbar);

        /**
         * 새로운 Activity가 열렸을때
         * 이전 Activity(page)로 돌아가기 아이콘을 표시하기
         * MainActivity에서는 의미가 없기 때문에 사용하지 않는다
         */
        // ActionBar actionBar = getSupportActionBar();
        // actionBar.setDisplayHomeAsUpEnabled(true);

        txt_msg = findViewById(R.id.txt_msg);
        btn_send = findViewById(R.id.btn_send);
        chat_list_view = findViewById(R.id.chatt_list_view);

        // 0 보여줄 데이터 객체 생성
        chattList = new ArrayList<Chatt>();

        // 1. Adpter 객체 생성
        // Adapter 객체를 생성할때 보여줄 데이터 객체를
        // 생성자 매개변수로 주입해 주어야 한다.
        // chattAdapter = new ChattAdapter(chattList);

        // 1-1 App에 등록된 nickname을 Apdapter에 데이터와 함께 전달하기
        chattAdapter = new ChattAdapter(chattList,nickname);

        // 2. RecyclerView.Adpter와 RecyclerView 를 서로 연결
        chat_list_view.setAdapter(chattAdapter);

        // 3. RecyclerView의 데이터를 표현한데 사용할
        // Layout 메니저를 설정하기
        RecyclerView.LayoutManager layoutManager
                = new LinearLayoutManager(this);

        FirebaseDatabase dbConn = FirebaseDatabase.getInstance();

        // 사용할 table(path)
        // realtimeDataabase에서는 table을 path라는 개념으로 부른다
        dbRef = dbConn.getReference("chatting");

        // firebase로 부터 데이터 변화 이벤트가 전달되면
        // 이벤트를 수신하여 할일을 지정하기위한 핸들러 선언
        ChildEventListener childEventListener
                = new FirebaseServiceImplV1(chattAdapter);
        
        // 이벤트 핸들러 연결
        dbRef.addChildEventListener(childEventListener);


        


        // 테스트를 위한 가상의 데이터 생성
        /*
        Chatt chatt = new Chatt();
        chatt.setName("홍길동");
        chatt.setMsg("반갑습니다");
        chattList.add(chatt);

        chatt = new Chatt();
        chatt.setName("성춘향");
        chatt.setMsg("안녕하세요");
        chattList.add(chatt);

        chatt = new Chatt();
        chatt.setName("이몽룡");
        chatt.setMsg("오늘은 좋은날");
        chattList.add(chatt);
         */

 ;
        chat_list_view.setLayoutManager(layoutManager);

        /**
         * EditBox에 메시지를 입력하고 Send 버튼을 클릭했을때 할일 지정하기
         * 
         * EditBox에 메시지를 입력하고 Send를 하면
         * Firebase의 Realtime DataBase에 데이터를 insert 하기
         *
         */

        
        btn_send.setOnClickListener(view->{

            // EditBox에 입력된 문자열을 추출하여 문자열 변수에 담기
            String msg = txt_msg.getText().toString();
            if(msg != null && !msg.isEmpty()) {

                String toastMsg = String.format("메시지 : %s",msg);
                Toast.makeText(this,
                        toastMsg,
                        Toast.LENGTH_SHORT)
                        .show();

                Chatt chatVO = new Chatt();
                chatVO.setMsg(msg);
                chatVO.setName(nickname);
                Log.d("클릭",chatVO.toString());
                // chattList.add(chatVO);
                // firebase의 realtime DB의 table에 데이터를 inert하라
                // push 하라
                dbRef.push().setValue(chatVO);
                txt_msg.setText("");
            }
        });
    }// end onCreate()

    /**
     * Custom 한 Toobar가 (Main)Activity에 적용될때
     * setSupportActionBar() method가 실행될때
     * event가 발생하고 자동으로 호출되는 method
     * 
     * Toolbar를 사용하여 ActionBar를 Custom하는 이유중에 하나가
     * onCreateOptionsMenu() method를 Override하여 
     * 더욱 세밀한 Customizing을 하기 위해서 이다
     * 
     * ToolBar에 사용자 정의형 menu를 설정하여
     * 다른 기능을 수행하도록 하는 UI를 구현할수 있다
     * 
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_tool_menu,menu);
        return true;
    }

    /**
     * ActionBar에 설정된 Option Menu의 특정한 항목(item)을
     * 클릭하면 호출되는 method
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int menu_item = item.getItemId();
        if(menu_item == R.id.app_bar_settings) {
            Toast.makeText(this,
                    "설정메뉴 클릭됨",
                    Toast.LENGTH_SHORT).show();

            Intent setting_intent
                    = new Intent(MainActivity.this,
                    SettingsActivity.class);
            startActivity(setting_intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}