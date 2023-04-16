package model;

import org.bson.Document;

public class Review {
    private String comment;
    private String destination;
    private int rating;

    public Review() {

    }
    public Review(String comment, String destination, int rating) {
        this.comment = comment;
        this.destination = destination;
        try{
            if (rating < 0 || rating > 5) {
                throw new Exception("Rating must be between 0 and 5");
            }
            this.rating = rating;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Review(Document review){
        try {
            int ratingValue = review.getInteger("rating");
            if (ratingValue < 0 || ratingValue > 5) {
                throw new Exception("Rating must be between 0 and 5");
            }
            this.rating = ratingValue;
        } catch (Exception e){
            System.out.println(e.getMessage());
            this.rating = 0;
        }
        this.comment = review.getString("comment");
        this.destination = review.getString("destination");
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public int getRating() {
        return rating;
    }
    public Document toDocument() {
        return new Document("comment", this.comment)
                .append("destination", this.destination)
                .append("rating", this.rating);
    }
    public String toString() {
        return this.comment + "\n" + this.destination + "  " + this.rating;
    }
}
