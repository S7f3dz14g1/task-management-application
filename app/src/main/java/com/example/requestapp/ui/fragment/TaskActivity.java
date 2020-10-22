package com.example.requestapp.ui.fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.requestapp.R;
import com.example.requestapp.ui.fragment.addTaskFragment.AddTaskFragment;
import com.example.requestapp.ui.fragment.availableTasksFragment.AvailableTasksFragment;
import com.example.requestapp.ui.fragment.completedTasksFragment.CompletedTasksFragment;
import com.example.requestapp.ui.fragment.profileFragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TaskActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        BottomNavigationView bottomNavigationView;
        bottomNavigationView=findViewById(R.id.bottonNavigation);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        FloatingActionButton actionButton;

        actionButton=findViewById(R.id.floatingActionButton);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    private void openDialog() {
        AddTaskFragment addTaskFragment=new AddTaskFragment();
        addTaskFragment.show(getSupportFragmentManager(),"create dialog");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.nav_add:
                            selectedFragment=new ProfileFragment();
                            break;
                        case R.id.nav_piority_b:
                            selectedFragment =new AvailableTasksFragment();
                            break;
                        default:
                            selectedFragment=new CompletedTasksFragment();
                            break;
                    }
                    TaskActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };



}
