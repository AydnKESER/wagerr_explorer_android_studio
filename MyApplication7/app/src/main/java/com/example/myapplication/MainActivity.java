package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity  {


    private BottomNavigationView mMainNav;
    private FrameLayout mFrame;

    private fragmentexplorer fragmentexplorer;
    private fragmentevents fragmentevents;
    private tracerfragment tracerfragment;
    private settingsfragment settingsfragment;

    private Button btnClick;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);

        fragmentexplorer= new fragmentexplorer();
        fragmentevents= new fragmentevents();
        tracerfragment= new tracerfragment();
        settingsfragment = new settingsfragment();


        getWindow().setNavigationBarColor(getResources().getColor(R.color.navigationBarColor));

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.nav_explorer : SetFragment(fragmentexplorer);
                    return true;
                    case R.id.nav_events : SetFragment(fragmentevents);
                    return true;
                    case R.id.nav_trackers : SetFragment(tracerfragment);
                        return true;
                    case R.id.nav_settings : SetFragment(settingsfragment);
                        return true;
                    default:
                        return false;
                }

            }
        });


        SetFragment(fragmentexplorer);
    }






    private void SetFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();

    }
}
