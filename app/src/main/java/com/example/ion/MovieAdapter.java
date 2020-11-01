package com.example.ion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private ArrayList<MovieItem> movieItemArrayList;
    private OnItemClickListener mlistener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mlistener = listener;
    }

    public MovieAdapter(Context mcontext, ArrayList<MovieItem> movieItems) {
        context = mcontext;
        movieItemArrayList = movieItems;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder((v));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieItem currentItem = movieItemArrayList.get(position);

        String imageUrl = currentItem.getImagePosterIUrl();
        String movieName = currentItem.getmMovie();
        double ratingMovie = currentItem.getmRating();

        holder.textViewName.setText(movieName);
        holder.textViewRating.setText("Rating: " + ratingMovie);
        Picasso.with(context).load("https://image.tmdb.org/t/p/original"+imageUrl).fit().centerInside().into(holder.imageViewMovie);
    }

    @Override
    public int getItemCount() {
        return movieItemArrayList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewMovie;
        public TextView textViewName, textViewRating;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewMovie = itemView.findViewById(R.id.image_view_poster);
            textViewName = itemView.findViewById(R.id.movie_name);
            textViewRating = itemView.findViewById(R.id.rating);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlistener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mlistener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
