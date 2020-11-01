package com.example.ion;

public class MovieItem {
    private String ImagePosterIUrl;
    private String mMovie;
    private double mRating;
    private String mOverview;
    public MovieItem(String imagePosterIUrl, String mMovie, double mRating, String mOverview) {
        ImagePosterIUrl = imagePosterIUrl;
        this.mMovie = mMovie;
        this.mRating = mRating;
        this.mOverview = mOverview;
    }

    public String getImagePosterIUrl() {
        return ImagePosterIUrl;
    }

    public String getmMovie() {
        return mMovie;
    }

    public String getmOverview() {
        return mOverview;
    }

    public double getmRating() {
        return mRating;
    }
}
