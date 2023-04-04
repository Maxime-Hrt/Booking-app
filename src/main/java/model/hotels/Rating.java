package model.hotels;

import model.Members;

public class Rating {
    private int rating;
    private String comment;
    private Members member;

    public Rating() {
    }

    public Rating(int rating, String comment, Members member) {
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
        this.member = member;
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

    public Members getMember() {
        return member;
    }

    public void setMember(Members member) {
        this.member = member;
    }

    public void printRating() {
        System.out.println("Rating: " + this.rating);
        System.out.println("Comment: " + this.comment);
        System.out.println("Member: " + this.member);
    }
}
