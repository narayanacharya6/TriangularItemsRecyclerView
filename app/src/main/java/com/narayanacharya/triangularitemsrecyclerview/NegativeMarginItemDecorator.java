package com.narayanacharya.triangularitemsrecyclerview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

class NegativeMarginItemDecorator extends RecyclerView.ItemDecoration {

    private final int marginTop;

    NegativeMarginItemDecorator(int marginTop) {
        this.marginTop = marginTop;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if(position != 0) {
            outRect.top = marginTop;
        }
    }
}
