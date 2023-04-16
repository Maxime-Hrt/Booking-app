package model.hotels;

import org.bson.Document;


public class Rating {
    private int rating;
    private String comment;
    private String author;
    //private Members member;

    public Rating() {
    }


    public Rating(int rating, String comment, String author){ //Members member) {
        try{
            if (rating < 0 || rating > 5) {
                throw new Exception("Rating must be between 0 and 5");
            }
            this.rating = rating;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.rating = 0;
        }
        this.comment = comment;
        //this.member = member;
        this.author = author;
    }

    public Rating(Document rating){
        try {
            int ratingValue = rating.getInteger("rating");
            if (ratingValue < 0 || ratingValue > 5) {
                throw new Exception("Rating must be between 0 and 5");
            }
            this.rating = ratingValue;
        } catch (Exception e){
            System.out.println(e.getMessage());
            this.rating = 0;
        }
        this.comment = rating.getString("comment");
        this.author = rating.getString("author");
    }

    public Document toDocument() {
        return new Document("rating", this.rating)
                .append("comment", this.comment)
                .append("author", this.author);
    }



    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void printRating() {
        System.out.println("\tRating: " + this.rating);
        System.out.println("\tComment: " + this.comment);
        System.out.println("\tAuthor: " + this.author + "\n");
    }
}
