package com.narayanacharya.triangularitemsrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvTriangles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvTriangles = (RecyclerView) findViewById(R.id.rvTriangles);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvTriangles.setLayoutManager(layoutManager);
        rvTriangles.setAdapter(new TriangleItemAdapter());
    }
}
