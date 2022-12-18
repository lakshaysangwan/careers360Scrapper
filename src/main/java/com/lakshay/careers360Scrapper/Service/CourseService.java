package com.lakshay.careers360Scrapper.Service;

import com.lakshay.careers360Scrapper.Model.College;
import com.lakshay.careers360Scrapper.Model.Course;
import com.lakshay.careers360Scrapper.Model.University;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    private final Logger logger = LoggerFactory.getLogger(CourseService.class);
    @Autowired
    UtilitiesService utilitiesService;

    public List<Course> courseScrapper(University university) {
        Document doc;
        while (true) {
            try {
                doc = Jsoup.connect(university.getCourseUrl()).get();
                break;
            } catch (Exception e) {
            }
        }
        String totalCoursesString = doc.getElementsByClass("showResults").get(0).text();
        String totalCourses = utilitiesService.findUsingRegex(" (.*) ", totalCoursesString);
        int totalPages = utilitiesService.totalPages(totalCourses);
        List<Course> courses = new ArrayList<>();
        for (int page = 1; page <= totalPages; page++) {
            while (true) {
                try {
                    doc = Jsoup.connect(university.getCourseUrl() + "?page=" + page).get();
                    break;
                } catch (Exception e) {
                }
            }
            Elements courseList = doc.getElementsByClass("cardList").get(0).getElementsByClass("cardBlkInn pull-left");
            for (Element courseItem : courseList) {
                Course course = new Course();
                course.setUniversity(university);
                course.setCourseName(courseItem.child(0).text());
                Elements courseDetails = courseItem.getElementsByTag("li");
                for (Element courseDetail : courseDetails) {
                    if (courseDetail.text().contains("Duration:")) {
                        course.setDuration(courseDetail.child(0).ownText());
                    } else if (courseDetail.text().contains("Seats:")) {
                        course.setSeats(courseDetail.ownText());
                    }
                }
//                logger.info(course.toString());
                courses.add(course);
            }
        }
        return courses;
    }

    public List<Course> courseScrapper(College college) {
        Document doc;
        while (true) {
            try {
                doc = Jsoup.connect(college.getCourseUrl()).get();
                break;
            } catch (Exception e) {
            }
        }
        String totalCoursesString = doc.getElementsByClass("showResults").get(0).text();
        String totalCourses = utilitiesService.findUsingRegex(" (.*) ", totalCoursesString);
        int totalPages = utilitiesService.totalPages(totalCourses);
        List<Course> courses = new ArrayList<>();
        for (int page = 1; page <= totalPages; page++) {
            while (true) {
                try {
                    doc = Jsoup.connect(college.getCourseUrl() + "?page=" + page).get();
                    break;
                } catch (Exception e) {
                }
            }
            Elements courseList = doc.getElementsByClass("cardList").get(0).getElementsByClass("cardBlkInn pull-left");
            for (Element courseItem : courseList) {
                Course course = new Course();
                course.setCollege(college);
                course.setCourseName(courseItem.child(0).text());
                Elements courseDetails = courseItem.getElementsByTag("li");
                for (Element courseDetail : courseDetails) {
                    if (courseDetail.text().contains("Duration:")) {
                        course.setDuration(courseDetail.child(0).ownText());
                    } else if (courseDetail.text().contains("Seats:")) {
                        course.setSeats(courseDetail.ownText());
                    }
                }
//                logger.info(course.toString());
                courses.add(course);
            }
        }
        return courses;
    }
}
