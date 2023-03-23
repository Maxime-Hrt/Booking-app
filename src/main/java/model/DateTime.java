package model;

public class DateTime extends Date{
    private int hour;
    private int minute;
    private int second;

    public DateTime() {
    }
    public DateTime(int day, int month, int year, int hour, int minute, int second) {
        super(day, month, year);
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    public int getHour() {
        return hour;
    }
    public int getMinute() {
        return minute;
    }
    public int getSecond() {
        return second;
    }
    public void setHour(int hour) {
        this.hour = hour;
    }
    public void setMinute(int minute) {
        this.minute = minute;
    }
    public void setSecond(int second) {
        this.second = second;
    }
    public void printDateTime(){
        System.out.println("Date: " + this.getDay() + "/" + this.getMonth() + "/" + this.getYear());
        System.out.println("Time: " + this.getHour() + ":" + this.getMinute() + ":" + this.getSecond());
    }
    //Date format: dd/mm/yyyy hh:mm:ss
    static public DateTime stringToDateTime(String date) {
        String[] dateArray = date.split("/");
        String[] timeArray = dateArray[2].split(" ");
        String[] hourArray = timeArray[1].split(":");
        DateTime date1 = new DateTime(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]), Integer.parseInt(timeArray[0]), Integer.parseInt(hourArray[0]), Integer.parseInt(hourArray[1]), Integer.parseInt(hourArray[2]));
        return date1;
    }
}
