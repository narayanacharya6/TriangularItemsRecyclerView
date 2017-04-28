package com.narayanacharya.triangularitemsrecyclerview;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvTriangles = (RecyclerView) findViewById(R.id.rvTriangles);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvTriangles.setLayoutManager(layoutManager);

        Resources r = getResources();
        int marginTop = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, -80,
                r.getDisplayMetrics());
        rvTriangles.addItemDecoration(new NegativeMarginItemDecorator(marginTop));

        rvTriangles.setAdapter(new TriangleItemAdapter());
    }
}
