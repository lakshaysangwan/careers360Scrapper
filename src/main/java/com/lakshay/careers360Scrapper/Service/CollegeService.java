package com.lakshay.careers360Scrapper.Service;

import com.lakshay.careers360Scrapper.Model.College;
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
public class CollegeService {
    private final Logger logger = LoggerFactory.getLogger(CourseService.class);
    @Autowired
    CourseService courseService;
    @Autowired
    UtilitiesService utilitiesService;
    @Autowired
    CollegeDetailsService collegeDetailsService;

    public List<College> collegeScrapper(University university) {
        Document doc;
        while (true) {
            try {
                doc = Jsoup.connect(university.getCollegeUrl()).get();
                break;
            } catch (Exception e) {
            }
        }
        String totalCollegesString = doc.getElementsByClass("showResults").get(0).text();
        String totalColleges = utilitiesService.findUsingRegex(" (.*) ", totalCollegesString);
        int totalPages = utilitiesService.totalPages(totalColleges);
        List<College> colleges = new ArrayList<>();
        for (int page = 1; page <= totalPages; page++) {
            while (true) {
                try {
                    doc = Jsoup.connect(university.getCollegeUrl() + "?page=" + page).get();
                    break;
                } catch (Exception e) {
                }
            }
            Elements collegeList = doc.getElementsByClass("contentPart").get(0).getElementsByClass("cardBlkInn pull-left");
            for (Element college : collegeList) {
                College college1 = collegeDetailsService.collegeDetailsScrapper("https://www.careers360.com" + college.child(0).child(0).attr("href"));
                logger.info(college1.toString());
                college1.setCourses(courseService.courseScrapper(college1));
                college1.setUniversity(university);
                colleges.add(college1);
            }
        }
        return null;
    }
}
