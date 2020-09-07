package com.example.nitesh_sunil.fortsinmaharashtra;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.nitesh_sunil.fortsinmaharashtra.DashBoard_Fragment.Home_Fragment;
import com.example.nitesh_sunil.fortsinmaharashtra.DashBoard_Fragment.Menu_Fragment;
import com.example.nitesh_sunil.fortsinmaharashtra.DashBoard_Fragment.Tours_Fragment;
import com.google.android.material.tabs.TabLayout;


import java.util.Objects;

public class Dashboard_main extends AppCompatActivity {
    private Toolbar dashboardToolbar;
    private ViewPager dashboardViewPager;
    private TabLayout tabLayout;
    private Tours_Fragment Tour_Frag;
    private Home_Fragment Home_Frag;
    private Menu_Fragment Menu_Frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_main);

        dashboardToolbar = findViewById(R.id.dashboardToolbar);
        setSupportActionBar(dashboardToolbar);

        dashboardViewPager = findViewById(R.id.dashboardViewpager);
        tabLayout = findViewById(R.id.tabLayout);


        Tour_Frag = new Tours_Fragment();
        Home_Frag = new Home_Fragment();
        Menu_Frag = new Menu_Fragment();


        tabLayout.setupWithViewPager(dashboardViewPager);


        DashboardViewPagerAdapter viewPagerAdapter = new DashboardViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFrag(Tour_Frag, "");
        viewPagerAdapter.addFrag(Home_Frag, "");
        viewPagerAdapter.addFrag(Menu_Frag, "");

        dashboardViewPager.setAdapter(viewPagerAdapter);

        SetTabIcon();

        //set home tab selected
        dashboardViewPager.setCurrentItem(1);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.dashboard_search) {
            Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);

    }


    private void SetTabIcon() {
        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_tours_black);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_home_black);
        Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(R.drawable.ic_menu_black);
    }
}
