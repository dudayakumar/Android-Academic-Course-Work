package com.example.bloodbank3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.bloodbank3.R;
import com.example.bloodbank3.fragments.AboutUs;
import com.example.bloodbank3.fragments.Home;

public class DashboardActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        dl = (DrawerLayout)findViewById(R.id.activity_dashboard);
        t = new ActionBarDrawerToggle(this, dl, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //Uncheck previously selected menu item
                for(int i = 0; i < nv.getMenu().size(); i++){
                    nv.getMenu().getItem(i).setChecked(false);
                }

                //Check selected menu item
                menuItem.setChecked(true);

                int id = menuItem.getItemId();
                switch (id)
                {
                    case R.id.home:
                        Log.d("DashBoardActivity","*****Navigating to home page");
                        getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new Home()).commit();
                        break;
                    case R.id.userprofile:
                        Log.d("DashBoardActivity","*****Navigating to user profile");                        getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new AboutUs()).commit();
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        break;
                    case R.id.bookAppt:
                        Log.d("DashBoardActivity","*****Navigating to book an appointment");
                        break;
                    case R.id.nearby_hospital:
                        Log.d("DashBoardActivity","*****Navigating to nearby hospital");
                        break;
                    case R.id.aboutUs:
                        Log.d("DashBoardActivity","*****Navigating to about us");
                        getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new AboutUs()).commit();
                        break;
                    case R.id.contactUs:
                        Log.d("DashBoardActivity","*****Navigating to contact us");
                        break;
                    case R.id.logout:
                        Log.d("DashBoardActivity","*****Navigating to logout");
                        break;
                    default:
                        return true;
                }
                //Closing navigation drawer after selecting a menu item
                dl.closeDrawers();
                return true;
            }
        });

        if(savedInstanceState == null){
            //Initially loading fragment with Home page content
            getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new Home()).commit();
            nv.getMenu().getItem(0).setChecked(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
