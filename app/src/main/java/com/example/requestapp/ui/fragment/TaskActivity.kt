package com.example.requestapp.ui.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.requestapp.R
import com.example.requestapp.ui.fragment.addTaskFragment.AddTaskFragment
import com.example.requestapp.ui.fragment.availableTasksFragment.AvailableTasksFragment
import com.example.requestapp.ui.fragment.completedTaksksFragment.CompletedTasksFragment
import com.example.requestapp.ui.fragment.profileFragment.ProfileFragment
import com.example.requestapp.utils.Config
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TaskActivity() : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        val bottomNavigationView: BottomNavigationView = findViewById<BottomNavigationView>(R.id.bottonNavigation)
        bottomNavigationView.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ProfileFragment()).commit()
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
        val actionButton: FloatingActionButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        actionButton.setOnClickListener { openDialog() }
    }

    fun openDialog() {
        val addTaskFragment = AddTaskFragment()
        addTaskFragment.show(supportFragmentManager, Config.CREATE_DIALOG)
    }

    var navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        val selectedFragment: Fragment = when (menuItem.itemId) {
            R.id.nav_add -> ProfileFragment()
            R.id.nav_piority_b -> AvailableTasksFragment()
            else -> CompletedTasksFragment()
        }
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit()
        true
    }
}
