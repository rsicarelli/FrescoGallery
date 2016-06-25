package br.com.rsicarelli.frescogallery.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class GalleryRecyclerView extends RecyclerView {
    private int columnWidth = -1;

    public GalleryRecyclerView(Context context) {
        this(context, null, 0);
    }

    public GalleryRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GalleryRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            int[] attrsArray = {
                    android.R.attr.columnWidth
            };
            TypedArray array = context.obtainStyledAttributes(attrs, attrsArray);
            columnWidth = array.getDimensionPixelSize(0, -1);
            array.recycle();
        }

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        setLayoutManager(layoutManager);
    }

    public int getColumnWidth() {
        return columnWidth;
    }
}