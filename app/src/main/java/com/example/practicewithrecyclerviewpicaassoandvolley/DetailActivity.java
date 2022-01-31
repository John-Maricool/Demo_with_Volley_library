package com.example.practicewithrecyclerviewpicaassoandvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView textViewNAme, textViewLikes;
    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textViewNAme = findViewById(R.id.detail_name);
        textViewLikes = findViewById(R.id.detail_likes);
        mImageView = findViewById(R.id.imageViewDetail);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int like = intent.getIntExtra("likes", 0);
        String ID = intent.getStringExtra("ID");

        textViewNAme.setText(name);
        textViewLikes.setText("" + like);
        mImageView.setImageResource(Integer.parseInt(ID));
    }
}