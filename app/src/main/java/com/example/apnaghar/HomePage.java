package com.example.apnaghar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomePage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.Home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.Home:
                        return true;

                    case R.id.ContactUs:
                        startActivity(new Intent(getApplicationContext(),ContactUs.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.AboutUs:
                        startActivity(new Intent(getApplicationContext(),AboutUs.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
        Button buy = findViewById(R.id.buy);
        Button rent = findViewById(R.id.rent);
        Button invest = findViewById(R.id.invest);
        Button build = findViewById(R.id.build);
        Button logout = findViewById(R.id.home_logout);


        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openbuy_page();
            }
        });
        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openrent_page();
            }
        });
        invest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openinvest_page();
            }
        });
        build.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openbuild_page();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensignin_page();
            }
        });
    }
    public void openbuy_page(){
        Intent myIntent = new Intent(HomePage.this, BuyPage.class);
        startActivity(myIntent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        finish();
    }
    public void openrent_page(){
        Intent myIntent = new Intent(HomePage.this, RentPage.class);
        startActivity(myIntent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        finish();
    }
    public void openinvest_page(){
        Intent myIntent = new Intent(HomePage.this, InvestPage.class);
        startActivity(myIntent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        finish();
    }
    public void openbuild_page(){
        Intent myIntent = new Intent(HomePage.this, BuildPage.class);
        startActivity(myIntent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        finish();
    }
    public void opensignin_page(){
        Intent myIntent = new Intent(HomePage.this, SignIn.class);
        startActivity(myIntent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        finish();
    }
}
