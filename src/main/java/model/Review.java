package model;

public class Review {
    private String comment;
    private String destination;
    private double rating;

    public Review() {

    }
    public Review(String comment, String destination, double rating) {
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
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        try{
            if (rating < 0 || rating > 5) {
                throw new Exception("Rating must be between 0 and 5");
            }
            this.rating = rating;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
