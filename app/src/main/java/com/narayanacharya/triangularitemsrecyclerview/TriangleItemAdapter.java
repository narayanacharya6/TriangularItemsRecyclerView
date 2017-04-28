package com.narayanacharya.triangularitemsrecyclerview;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

class TriangleItemAdapter extends RecyclerView.Adapter<TriangleItemAdapter.TriangleViewHolder> {

    TriangleItemAdapter() {
        super();
    }

    @Override
    public TriangleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TriangleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_triangle_item, parent, false));
    }

    @Override
    public void onBindViewHolder(TriangleViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 50;
    }

    class TriangleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TriangleFrameLayout rootTriangle;
        private final TextView itemText;

        @SuppressLint("RtlHardcoded")
        void bind(int position) {
            // determine whether it's a left or a right triangle
            TriangleFrameLayout.Align align =
                    (position & 1) == 0 ? TriangleFrameLayout.Align.LEFT : TriangleFrameLayout.Align.RIGHT;

            // setup the triangle
            rootTriangle.setTriangleAlignment(align);
            rootTriangle.setBackgroundColor(Color.argb(255, 0, (int) (Math.random() * 256), (int) (Math.random() * 256)));

            itemText.setText(String.valueOf(position));
            itemText.setGravity((position & 1) == 0 ? Gravity.LEFT : Gravity.RIGHT);
        }

        TriangleViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            rootTriangle = (TriangleFrameLayout) itemView.findViewById(R.id.root_triangle);
            itemText = (TextView) itemView.findViewById(R.id.item_text);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "#" + getAdapterPosition(), Toast.LENGTH_SHORT).show();
        }
    }
}
