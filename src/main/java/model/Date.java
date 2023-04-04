package model;

public class Date {
    private int day;
    private int month;
    private int year;

    public Date() {
    }
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    //Date format: dd/mm/yyyy hh:mm:ss
    static public Date stringToDate(String date) {
        String[] dateArray = date.split("/");
        String[] timeArray = dateArray[2].split(" ");
        Date date1 = new Date(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]), Integer.parseInt(timeArray[0]));
        return date1;
    }

    static public String dateToString(Date date) {
        return date.getDay() + "/" + date.getMonth() + "/" + date.getYear();
    }
}
