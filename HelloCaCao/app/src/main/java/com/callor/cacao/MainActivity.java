package com.callor.cacao;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
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

        txt_msg = findViewById(R.id.txt_msg);
        btn_send = findViewById(R.id.btn_send);
        chat_list_view = findViewById(R.id.chatt_list_view);

        // 0 보여줄 데이터 객체 생성
        chattList = new ArrayList<Chatt>();

        // 1. Adpter 객체 생성
        // Adapter 객체를 생성할때 보여줄 데이터 객체를
        // 생성자 매개변수로 주입해 주어야 한다.
        chattAdapter = new ChattAdapter(chattList);

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
                chatVO.setName("내멋으로");

                Log.d("클릭",chatVO.toString());

                // chattList.add(chatVO);
                // firebase의 realtime DB의 table에 데이터를 inert하라
                // push 하라
                dbRef.push().setValue(chatVO);
                txt_msg.setText("");
            }
        });
    }
}