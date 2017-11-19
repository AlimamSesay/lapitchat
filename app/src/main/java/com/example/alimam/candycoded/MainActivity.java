package com.example.alimam.candycoded;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.*;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private CandyCursorAdapter adapter;
    private Candy[] candies;
    private CandyDbHelper candyDbHelper = new CandyDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final ArrayList<String> candy_list = new ArrayList<>();

//        candy_list.add("Alimam");

//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//            this,
//            R.layout.list_item_candy,
//            R.id.text_view_candy,
//            candy_list);

        SQLiteDatabase db = candyDbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM candy", null);

        adapter = new CandyCursorAdapter(this, cursor);
        ListView listView = (ListView)this.findViewById(R.id.list_view_candy);

        listView.setAdapter(adapter);



        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void  onItemClick(
                    AdapterView<?> adapterView, View view, int i, long l){
                Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
//                detailIntent.putExtra("candy_name", candies[i].name);
//                detailIntent.putExtra("candy_image", candies[i].image);
//                detailIntent.putExtra("candy_price", candies[i].price);
//                detailIntent.putExtra("candy_desc", candies[i].description);
                detailIntent.putExtra("position", i);
                startActivity(detailIntent);
            }
        });
//https://vast-brushlands-23089.herokuapp.com/main/api
        AsyncHttpClient client = new AsyncHttpClient();

        client.get("http://192.168.0.103:3000/candy",
                new TextHttpResponseHandler(){

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String response) {
                        Log.d("AsyncHttpClient", "Success = " + statusCode);

                        Gson gson = new GsonBuilder().create();
                        Candy[] candies = gson.fromJson(response, Candy[].class);
//                        adapter.clear();
//                        for(Candy candy : candies){
//                            adapter.add(candy.name);
//                        }

                        addCandiesToDatabase(candies);

                        SQLiteDatabase db = candyDbHelper.getWritableDatabase();
                        Cursor cursor = db.rawQuery("SELECT * FROM candy", null);
                        adapter.changeCursor(cursor);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable) {
                        Log.e("AsyncHttpClient", "Failure code = " + statusCode);
                    }



                } );
    }

    public void addCandiesToDatabase(Candy[] candies){
        SQLiteDatabase db = candyDbHelper.getWritableDatabase();

        for(Candy candy : candies){
            ContentValues values = new ContentValues();
            values.put(CandyContract.CandyEntry.COLUMN_NAME_NAME, candy.name);
            values.put(CandyContract.CandyEntry.COLUMN_NAME_PRICE, candy.price);
            values.put(CandyContract.CandyEntry.COLUMN_NAME_DESC, candy.description);
            values.put(CandyContract.CandyEntry.COLUMN_NAME_IMAGE, candy.image);

            db.insert(CandyContract.CandyEntry.TABLE_NAME, null, values);
        }
    }
}
