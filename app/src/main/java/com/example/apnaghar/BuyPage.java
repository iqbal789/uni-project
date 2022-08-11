package com.example.apnaghar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BuyPage extends AppCompatActivity {

    //Widgets
    RecyclerView recyclerViewBuy;

    //Firebase:
    private DatabaseReference myRef;

    //Variables:
    private ArrayList<Data> DataList;
    private RecyclerAdapter recyclerAdapterBuy;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_page);

        Button backbtn = findViewById(R.id.buy_backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(BuyPage.this, HomePage.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                finish();
            }
        });

        recyclerViewBuy = (RecyclerView) findViewById(R.id.recyclerViewBuy);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewBuy.setLayoutManager(layoutManager);
        recyclerViewBuy.setHasFixedSize(true);

        //Divider
        DividerItemDecoration divider =
                new DividerItemDecoration(recyclerViewBuy.getContext(),
                        DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(),
                R.drawable.line_divider));
        recyclerViewBuy.addItemDecoration(divider);

        //Firebase
        myRef = FirebaseDatabase.getInstance("https://apnaghar-f69d9-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();

        //ArrayList:
        DataList = new ArrayList<>();

        //Clear ArrayList
        clearAll();

        //GetDataMethod
        GetDataFromFirebase();
    }
    private void GetDataFromFirebase() {

        Query query = myRef.child("buy");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clearAll();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Data data = new Data();

                    data.setImageUrl(snapshot.child("image").getValue().toString());
                    data.setArea(snapshot.child("area").getValue().toString());
                    data.setLocation(snapshot.child("location").getValue().toString());
                    data.setPkr(snapshot.child("pkr").getValue().toString());

                    DataList.add(data);
                }
                recyclerAdapterBuy = new RecyclerAdapter(getApplicationContext(), DataList);
                recyclerViewBuy.setAdapter(recyclerAdapterBuy);
                recyclerAdapterBuy.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void clearAll(){
        if(DataList != null){
            DataList.clear();

            if(recyclerAdapterBuy != null){
                recyclerAdapterBuy.notifyDataSetChanged();
            }
        }
        DataList = new ArrayList<>();
    }
}
