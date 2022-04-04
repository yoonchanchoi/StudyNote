package com.example.view.studynote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController=navHostFragment.navController

        setupActionBarWithNavController(navController)
    }


    // 기본 작업 모음에 탐색 지원을 추가하려면 아래와 같이 기본 활동의 onCreate() 메서드에서 setupActionBarWithNavController()를 호출합니다. AppBarConfiguration은 onSupportNavigateUp()을 재정의할 때도 사용하므로 onCreate() 외부에 선언해야 합니다.
    // 즉 add 프래그먼트로 갔을 때 상단 왼쪽에 툴바 안에 있는 뒤로 가기 버튼을 누르면 navHostFragment로 돌아옵니다.
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHostFragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}