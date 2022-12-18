package com.lakshay.careers360Scrapper.Model;

import javax.persistence.*;

@Entity
public class Course {
    @Id
    @SequenceGenerator(name = "course_sequence", sequenceName = "course_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
    private Long id;
    private String courseName;
    private String duration;
    private String seats;
    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;
    @ManyToOne
    @JoinColumn(name = "college_id")
    private College college;

    public Course() {
    }

    public Course(Long id, String courseName, String duration, String seats, University university, College college) {
        this.id = id;
        this.courseName = courseName;
        this.duration = duration;
        this.seats = seats;
        this.university = university;
        this.college = college;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", duration='" + duration + '\'' +
                ", seats='" + seats + '\'' +
                ", university=" + university +
                ", college=" + college +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }
}
