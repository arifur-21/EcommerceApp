package com.example.foodproject.model;

public class RatingModel {
    String rating,ratingId,productId;

    public RatingModel() {
    }

    public RatingModel(String rating, String ratingId, String productId) {
        this.rating = rating;
        this.ratingId = ratingId;
        this.productId = productId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRatingId() {
        return ratingId;
    }

    public void setRatingId(String ratingId) {
        this.ratingId = ratingId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
