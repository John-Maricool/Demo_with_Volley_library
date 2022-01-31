package com.example.practicewithrecyclerviewpicaassoandvolley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ArrayList<EachItemAttributes> eachItemAttributes;
    private Recycker_Row recycker_row;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        eachItemAttributes = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);

        parseJSON();

    }

    private void parseJSON() {
        String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hit");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String creatorName = object.getString("user");
                                String imageUrl = object.getString("webformatURL");
                                int likeCount = object.getInt("likes");

                                eachItemAttributes.add(new EachItemAttributes(creatorName, likeCount, imageUrl));
                            }
                            recycker_row = new Recycker_Row(MainActivity.this, eachItemAttributes);
                            recycker_row.setOnItemClickListener(new Recycker_Row.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                                    EachItemAttributes pos =  eachItemAttributes.get(position);
                                    intent.putExtra("Name", pos.getName_cat());
                                    intent.putExtra("likes", pos.getNumber_likes());
                                    intent.putExtra("ID", pos.getImage_ID());

                                }
                            });
                            recyclerView.setAdapter(recycker_row);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }

                });


        requestQueue.add(objectRequest);
    }

}