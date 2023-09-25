package com.yipeng.bottomnavigationsample

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.yipeng.bottomnavigationsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){}

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 加载布局并获取Binding对象
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
//        val navView = findViewById<BottomNavigationView>(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        // 创建底部菜单项和Fragment之间的对应关系
        // 这样点击底部菜单可以正确地切换对应的Fragment。
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_files, R.id.navigation_profile
            )
        )

//         navView.setOnItemSelectedListener{ item ->
//            when (item.itemId) {
//                R.id.navigation_files -> {
//                    if (Environment.isExternalStorageEmulated ()){
//                        // 没有权限,申请
//                        val intent = Intent()
//                        intent.action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
//                        intent.data = Uri.parse("package:$packageName")
//                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//
//                        launcher.launch(intent)
//                    }
//                }
//            }
//            true
//        }

        // 关联ActionBar、NavController和BottomNavigationView
        // 让系统可以自动处理它们之间的交互和同步。
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}