package com.roshan.traversity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class LocationActivity extends AppCompatActivity {
    ImageView image;
    TextView title;
    TextView descripton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        image =  findViewById(R.id.locationImage);
        title = findViewById(R.id.locationTitle);
        descripton = findViewById(R.id.locationDescription);
        setViews();
    }

    public void setViews(){
        Glide.with(this).load(getIntent().getStringExtra("thumbnail")).into(image);

        title.setText(getIntent().getStringExtra("title"));
        descripton.setText(getIntent().getStringExtra("description"));
    }
}
