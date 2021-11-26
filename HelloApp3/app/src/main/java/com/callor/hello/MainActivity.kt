package com.callor.hello

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.callor.hello.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // lateinit var : 지금변수를 선언만하고
    // 생성은 잠시후에 실행하겠다
    private lateinit var mainBiding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 선언된 mainBinding 을 activity_main.xml 파일을 열어서
        // inflate(확장) 하여 mainBinding 객체로(변수로) 초기화
        mainBiding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBiding.root)

        val navController = findNavController(R.id.fragmentContainer)
        val appBarConfig = AppBarConfiguration(
            setOf(
                R.id.bottom_nav_home,R.id.bottom_nav_book
            )
        )
        setupActionBarWithNavController(navController,appBarConfig)
        mainBiding.bottomNav.setupWithNavController(navController)

    }
}