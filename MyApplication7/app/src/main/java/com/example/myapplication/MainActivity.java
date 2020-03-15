package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity  {


    private BottomNavigationView mMainNav;
    private FrameLayout mFrame;

    private fragmentexplorer fragmentexplorer;
    private fragmentevents fragmentevents;
    private tracerfragment tracerfragment;
    private settingsfragment settingsfragment;

    private Button btnClick;

    String wagerurl = "https://explorer.wagerr.com/api/coin/";
    RequestQueue queue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue= NetworkController.getInstance(this).getRequestQueue();
//        queue.add(new JsonObjectRequest(0, wagerurl,null, new listener(),new error()));



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

    private class listener implements Response.Listener<JSONObject>{
        public void onResponse(JSONObject response){
            Log.i("mesaj","Gelen"+response.toString());
        }

    }
    private class error implements Response.ErrorListener{
        public void onErrorResponse(VolleyError error){
            Toast.makeText(MainActivity.this,"text :"+error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
