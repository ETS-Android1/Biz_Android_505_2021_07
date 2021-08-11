package com.callor.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;

import com.callor.library.service.NaverBookService;
import com.callor.library.service.impl.NaverBookServiceImplV1;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.book_list_view);
        NaverBookService naverBookService
                = new NaverBookServiceImplV1(recyclerView);
        naverBookService.getBooks("자바");
    }

    /**
     * res/menu/menu.xml 파일을 읽어서 화면의
     * ActionBar에 메뉴 등을 표현할때 사용하는 method
     *
     * 이 method는 Android가 App을 화면에 띄울때 자동으로
     * 호출하여 사용하는 method
     * 
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}