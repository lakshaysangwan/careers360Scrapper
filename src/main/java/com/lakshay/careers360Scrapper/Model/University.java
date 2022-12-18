package com.lakshay.careers360Scrapper.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class University {
    @Id
    @SequenceGenerator(name = "university_sequence", sequenceName = "university_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "university_sequence")
    private Long id;
    private String univName;
    private String instituteType;
    private String establishedYear;
    private String totalStudents;
    private String url;
    private String courseUrl;
    private String collegeUrl;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<College> colleges = new ArrayList<College>();
    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Course> courses = new ArrayList<Course>();

    public University() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnivName() {
        return univName;
    }

    public void setUnivName(String univName) {
        this.univName = univName;
    }

    public String getInstituteType() {
        return instituteType;
    }

    public void setInstituteType(String instituteType) {
        this.instituteType = instituteType;
    }

    public String getEstablishedYear() {
        return establishedYear;
    }

    public void setEstablishedYear(String establishedYear) {
        this.establishedYear = establishedYear;
    }

    public String getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(String totalStudents) {
        this.totalStudents = totalStudents;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCourseUrl() {
        return courseUrl;
    }

    public void setCourseUrl(String courseUrl) {
        this.courseUrl = courseUrl;
    }

    public String getCollegeUrl() {
        return collegeUrl;
    }

    public void setCollegeUrl(String collegeUrl) {
        this.collegeUrl = collegeUrl;
    }

    public List<College> getColleges() {
        return colleges;
    }

    public void setColleges(List<College> colleges) {
        this.colleges = colleges;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", univName='" + univName + '\'' +
                ", instituteType='" + instituteType + '\'' +
                ", establishedYear='" + establishedYear + '\'' +
                ", totalStudents='" + totalStudents + '\'' +
                ", url='" + url + '\'' +
                ", courseUrl='" + courseUrl + '\'' +
                ", collegeUrl='" + collegeUrl + '\'' +
                ", colleges=" + colleges +
                ", courses=" + courses +
                '}';
    }

}
