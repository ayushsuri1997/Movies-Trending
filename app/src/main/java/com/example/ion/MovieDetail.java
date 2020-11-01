package com.example.ion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.ion.MainActivity.EXTRA_Name;
import static com.example.ion.MainActivity.EXTRA_Overview;
import static com.example.ion.MainActivity.EXTRA_Rating;
import static com.example.ion.MainActivity.EXTRA_URL;

public class MovieDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String name = intent.getStringExtra(EXTRA_Name);
        double rating = intent.getDoubleExtra(EXTRA_Rating,0);
        String overview = intent.getStringExtra(EXTRA_Overview);

        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textViewName = findViewById(R.id.movieDetail);
        TextView textViewRating = findViewById(R.id.movieRating);
        TextView textViewOverview = findViewById(R.id.movieOverview);

        Picasso.with(this).load("https://image.tmdb.org/t/p/original"+imageUrl).fit().centerInside().into(imageView);
        textViewName.setText(name);
        textViewRating.setText("Rating: "+rating);
        textViewOverview.setText("Overview: "+ overview);

    }
}