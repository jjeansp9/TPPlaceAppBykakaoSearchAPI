package com.jspstudio.tpplaceappbykakaosearchapi.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.tabs.TabLayout
import com.jspstudio.tpplaceappbykakaosearchapi.R
import com.jspstudio.tpplaceappbykakaosearchapi.databinding.ActivityMainBinding
import com.jspstudio.tpplaceappbykakaosearchapi.fragments.SearchListFragment
import com.jspstudio.tpplaceappbykakaosearchapi.fragments.SearchMapFragment

//import com.jspstudio.tpplaceappbykakaosearchapi.R

class MainActivity : AppCompatActivity() {

    val binding:ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        // 툴바를 제목줄로 대체하여 설정
        setSupportActionBar(binding.toolbar)

        // 첫 실행될 프래그먼트를 동적 추가
        supportFragmentManager.beginTransaction().add(R.id.container_fragment, SearchListFragment()).commit()

        // 탭레이아웃의 탭버튼 클릭시 보여줄 프래그먼트를 변경
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.text == "LIST"){
                    supportFragmentManager.beginTransaction().replace(R.id.container_fragment, SearchListFragment()).commit()
                }else if(tab?.text == "MAP"){
                    supportFragmentManager.beginTransaction().replace(R.id.container_fragment, SearchMapFragment()).commit()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_help-> Toast.makeText(this, "도움말", Toast.LENGTH_SHORT).show()
            R.id.menu_logout -> Toast.makeText(this, "로그아웃", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}