package com.example.trakker.SearchPagePackage;

//FOR SEARCH PAGE RECYCLERVIEW

public class CourseModel {
    // variables for our course
    // name and description.
    private String courseName;
    private String courseDescription;
    private String Poster;
    private String Backdrop;
    private String courseID;
    private String courseType;
    private String releaseDate;

    // creating constructor for our variables.
    public CourseModel(String courseName, String courseDescription, String poster, String backdrop, String courseID, String courseType, String releaseDate) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.Poster = "https://image.tmdb.org/t/p/w500/" + poster;
        this.Backdrop = "https://image.tmdb.org/t/p/w500/" + backdrop;
        this.courseID = courseID;
        this.courseType = courseType;
        this.releaseDate = releaseDate;
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

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String ID) {
        this.Poster = ID;
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

    public String getCourseType() { return courseType; }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getReleaseDate() { return releaseDate; }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


}