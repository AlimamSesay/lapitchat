package com.example.alimam.candycoded;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = DetailActivity.this.getIntent();

        if (intent.hasExtra("position")){
            int position = intent.getIntExtra("position", 0);
            CandyDbHelper candyDbHelper = new CandyDbHelper(this);
            SQLiteDatabase db = candyDbHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM candy",  null);
            cursor.moveToPosition(position);

            String candyName = cursor.getString(cursor.getColumnIndexOrThrow(CandyContract.CandyEntry.COLUMN_NAME_NAME));
            String candyPrice = cursor.getString(cursor.getColumnIndexOrThrow(CandyContract.CandyEntry.COLUMN_NAME_PRICE));
            String candyImage = cursor.getString(cursor.getColumnIndexOrThrow(CandyContract.CandyEntry.COLUMN_NAME_IMAGE));
            String candyDesc = cursor.getString(cursor.getColumnIndexOrThrow(CandyContract.CandyEntry.COLUMN_NAME_DESC));

            ((TextView) DetailActivity.this.findViewById(R.id.text_view_name)).setText(candyName);
            ((TextView) DetailActivity.this.findViewById(R.id.text_view_price)).setText(candyName);
            ((TextView) DetailActivity.this.findViewById(R.id.text_view_desc)).setText(candyName);

            Picasso.with(DetailActivity.this).load(candyImage).into(
                    (ImageView) DetailActivity.this.findViewById(R.id.image_view_candy));
//            int columnIndex = cursor.getColumnIndexOrThrow(CandyContract.CandyEntry.COLUMN_NAME_NAME);
//            String candyName = cursor.getString(columnIndex);
        }

//        String candyName = "";
//
//        if (intent != null && intent.hasExtra("candy_name")){
//            candyName = intent.getStringExtra("candy_name");
//        }
//
//        TextView textView = (TextView)this.findViewById(R.id.text_view_name);
//
//        textView.setText(candyName);

//        String candyImage = "";
//        if (intent != null && intent.hasExtra("candyImage")){
//            candyImage = intent.getStringExtra("candyImage");
//        }

//        ImageView imageView = (ImageView)this.findViewById(R.id.image_view_candy);
//        Picasso.with(this).load(candyImage).into(imageView);
//
//        String candyPrice = "";
//        if (intent != null && intent.hasExtra("candyPrice")){
//            candyPrice = intent.getStringExtra("candyPrice");
//        }
//
//        TextView textViewPrice = (TextView)this.findViewById(R.id.text_view_price);
//
//        textViewPrice.setText(candyPrice);
//
//        String candyDesc = "";
//        if (intent != null && intent.hasExtra("candyDesc")){
//            candyDesc = intent.getStringExtra("candyDesc");;
//        }
//
//        TextView textViewDesc = (TextView)this.findViewById(R.id.text_view_description);
//
//        textViewDesc.setText(candyDesc);
    }

}
