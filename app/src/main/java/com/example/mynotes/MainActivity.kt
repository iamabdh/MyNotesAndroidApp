package com.example.mynotes

import android.annotation.SuppressLint
import android.app.FragmentTransaction
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home_page.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController : NavController
    private lateinit var drawerLayout : DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var homePageFragment: HomePageFragment



    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val floatingButton : FloatingActionButton = findViewById(R.id.floatingActionButton)
        val menuButton : ImageButton = findViewById(R.id.menuButton)

        // for navigation
        navController = findNavController(R.id.fragment)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView.setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        menuButton.setOnClickListener {
            onSupportNavigateUp()
        }

        floatingButton.setOnClickListener {
            toCreatePage(this)
        }
        homePageFragment = HomePageFragment()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, homePageFragment)
                .commit()
        val settingFragment = SettingFragment()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, settingFragment)
                .commit()
        val darkModeFragment = DarkModeFragment()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, darkModeFragment)
                .commit()
        val aboutFragment = AboutFragment()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, aboutFragment)
                .commit()
    }

    // open navigation menu
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp(appBarConfiguration)||super.onSupportNavigateUp()
    }

    // debug function
    fun getToast(context: Context){
        Toast.makeText(context, "ssss", Toast.LENGTH_LONG).show()
    }

    fun toCreatePage(context: Context){
        startActivity(Intent(context, create::class.java))
    }

    fun toReatNotePage(context : Context){
        startActivity(Intent(context, ReadNote::class.java))
    }
}