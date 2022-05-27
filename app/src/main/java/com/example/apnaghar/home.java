package com.example.apnaghar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button buy = findViewById(R.id.buy);
        Button rent = findViewById(R.id.rent);
        Button build = findViewById(R.id.build);

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
        build.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openbuild_page();
            }
        });
    }
    public void openbuy_page(){
        Intent myIntent = new Intent(home.this, buy_page.class);
        startActivity(myIntent);
    }
    public void openrent_page(){
        Intent myIntent = new Intent(home.this, rent_page.class);
        startActivity(myIntent);
    }
    public void openbuild_page(){
        Intent myIntent = new Intent(home.this, build_page.class);
        startActivity(myIntent);
    }

}
