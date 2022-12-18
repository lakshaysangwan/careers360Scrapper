package com.lakshay.careers360Scrapper.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class College {
    @Id
    @SequenceGenerator(name = "college_sequence", sequenceName = "college_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "college_sequence")
    private Long id;
    private String collegeName;
    private String instituteType;
    private String approval;
    private String acceptance;
    private String establishedYear;
    private String totalStudents;
    private String url;
    private String courseUrl;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;
    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Course> courses = new ArrayList<Course>();

    public College() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getInstituteType() {
        return instituteType;
    }

    public void setInstituteType(String instituteType) {
        this.instituteType = instituteType;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(String acceptance) {
        this.acceptance = acceptance;
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

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "College{" +
                "id=" + id +
                ", collegeName='" + collegeName + '\'' +
                ", instituteType='" + instituteType + '\'' +
                ", approval='" + approval + '\'' +
                ", acceptance='" + acceptance + '\'' +
                ", establishedYear='" + establishedYear + '\'' +
                ", totalStudents='" + totalStudents + '\'' +
                ", url='" + url + '\'' +
                ", courseUrl='" + courseUrl + '\'' +
                ", university=" + university +
                ", courses=" + courses +
                '}';
    }

}
