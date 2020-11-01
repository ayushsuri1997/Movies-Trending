package com.example.ion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieAdapter.OnItemClickListener {

    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_Name = "movieName";
    public static final String EXTRA_Rating = "Rating";
    public static final String EXTRA_Overview = "Overview";

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private ArrayList<MovieItem> movieItemsList;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        
        movieItemsList = new ArrayList<>();
        
        requestQueue = Volley.newRequestQueue(this);
        
        parseJSON();
    }

    private void parseJSON() {
        String url = "https://api.themoviedb.org/3/trending/movie/week?api_key=495e9d39bf1368e74739efaed06a0e8b";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject movie = jsonArray.getJSONObject(i);
                                String movieName = movie.getString("title");
                                String imageURL = movie.getString("poster_path");
                                double rating = movie.getDouble("vote_average");
                                String overview = movie.getString("overview");
                                movieItemsList.add(new MovieItem(imageURL,movieName,rating,overview));
                            }
                            movieAdapter = new MovieAdapter(MainActivity.this,movieItemsList);
                            recyclerView.setAdapter(movieAdapter);
                            movieAdapter.setOnItemClickListener(MainActivity.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, MovieDetail.class);
        MovieItem clickedItem = movieItemsList.get(position);
        detailIntent.putExtra(EXTRA_URL, clickedItem.getImagePosterIUrl());
        detailIntent.putExtra(EXTRA_Name, clickedItem.getmMovie());
        detailIntent.putExtra(EXTRA_Rating, clickedItem.getmRating());
        detailIntent.putExtra(EXTRA_Overview,clickedItem.getmOverview());
        startActivity(detailIntent);
    }
}