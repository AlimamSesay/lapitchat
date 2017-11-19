package com.example.alimam.candycoded;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by alimam on 11/18/17.
 */

public class CandyCursorAdapter extends CursorAdapter {

    public CandyCursorAdapter(Context context, Cursor c) {
        super(context, c, false);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(
                R.layout.list_item_candy, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textView = (TextView) view.findViewById(R.id.text_view_candy);//Cursor cursor,
        String candyName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        textView.setText(candyName);

    }
}
