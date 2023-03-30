package com.example.trakker;

//FOR SEARCH PAGE RECYCLERVIEW

public class CourseModel {
    // variables for our course
    // name and description.
    private String courseName;
    private String courseDescription;
    private String Poster;
    private String Backdrop;

    // creating constructor for our variables.
    public CourseModel(String courseName, String courseDescription, String poster, String backdrop) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.Poster = "https://image.tmdb.org/t/p/w500/" + poster;
        this.Backdrop = "https://image.tmdb.org/t/p/w500/" + backdrop;
    }

    // creating getter and setter methods.
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        this.Poster = poster;
    }

    public String getBackdrop() {
        return Backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.Backdrop = backdrop;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }


}