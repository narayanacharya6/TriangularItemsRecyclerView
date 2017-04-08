package com.narayanacharya.triangularitemsrecyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class TriangleFrameLayout extends FrameLayout {

    public TriangleFrameLayout(@NonNull Context context) {
        super(context);
    }

    public TriangleFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TriangleFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    enum Align {LEFT, RIGHT}

    private Align alignment = Align.LEFT;

    /**
     * Specify whether it's a left or a right triangle.
     */
    public void setTriangleAlignment(Align alignment) {
        this.alignment = alignment;
    }

    @Override
    public void draw(Canvas canvas) {
        // crop drawing to the triangle shape
        Path mask = new Path();
        Point[] trianglePoints = getTriangle();
        mask.moveTo(trianglePoints[0].x, trianglePoints[0].y);
        mask.lineTo(trianglePoints[1].x, trianglePoints[1].y);
        mask.lineTo(trianglePoints[2].x, trianglePoints[2].y);
        mask.close();

        canvas.save();

        canvas.clipPath(mask);
        super.draw(canvas);

        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // check if touch event is within the triangle shape
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            Point touch = new Point((int) event.getX(), (int) event.getY());
            Point[] trianglePoints = getTriangle();

            if (!isPointInsideTriangle(touch, trianglePoints[0], trianglePoints[1], trianglePoints[2])) {
                // ignore touch event outside triangle
                return false;
            }
        }

        return super.onTouchEvent(event);
    }

    @SuppressWarnings("RedundantIfStatement")
    private boolean isPointInsideTriangle(Point s, Point a, Point b, Point c) {
        // stolen from http://stackoverflow.com/a/9755252
        int as_x = s.x - a.x;
        int as_y = s.y - a.y;
        boolean s_ab = (b.x - a.x) * as_y - (b.y - a.y) * as_x > 0;
        if ((c.x - a.x) * as_y - (c.y - a.y) * as_x > 0 == s_ab)
            return false;
        if ((c.x - b.x) * (s.y - b.y) - (c.y - b.y) * (s.x - b.x) > 0 != s_ab)
            return false;
        return true;
    }

    private Point[] getTriangle() {
        // define the triangle shape of this View
        boolean left = alignment == Align.LEFT;
        Point a = new Point(left ? 0 : getWidth(), -1);
        Point b = new Point(left ? 0 : getWidth(), getHeight() + 1);
        Point c = new Point(left ? getWidth() : 0, getHeight() / 2);
        return new Point[]{a, b, c};
    }

}