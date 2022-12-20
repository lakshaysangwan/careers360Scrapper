package com.lakshay.careers360Scrapper.Service;

import com.lakshay.careers360Scrapper.Model.College;
import com.lakshay.careers360Scrapper.Repository.CourseRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CollegeDetailsService {
    private final Logger logger = LoggerFactory.getLogger(CollegeDetailsService.class);
    private final CourseRepository courseRepository;

    public CollegeDetailsService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public College collegeDetailsScrapper(String url) {
        College college = new College();
        Document doc;
        while (true) {
            try {
                doc = Jsoup.connect(url).get();
                break;
            } catch (Exception e) {
                if (e.getMessage().contains("404")|| e.getMessage().contains("500")) {
                    return null;
                }
            }
        }
        college.setCollegeName(doc.getElementsByTag("h1").text());
        college.setUrl(url);
        try{
            college.setCourseUrl(doc.getElementsByClass("top-menu").get(0).getElementsContainingOwnText("Courses & Fees").get(0).attr("href"));
        }catch(IndexOutOfBoundsException indexOutOfBoundsException){
            college.setCourseUrl(null);
        }
        Elements collegeData;
        try{
            collegeData = doc.getElementsByClass("cardBlkInn quickFact").get(0).getElementsByTag("div");
        }catch(IndexOutOfBoundsException indexOutOfBoundsException){
            return null;
        }
        for (Element z : collegeData) {
            if (z.text().contentEquals("Ownership")) {
                college.setInstituteType(Objects.requireNonNull(z.nextElementSibling()).text());
            } else if (z.text().contentEquals("Approval")) {
                college.setApproval(z.nextElementSibling().text());
            } else if (z.text().contentEquals("Genders Accepted")) {
                college.setAcceptance(z.nextElementSibling().text());
            }
        }
        collegeData = doc.getElementsByTag("td");
        for (Element univDatum : collegeData) {
            if (univDatum.text().contains("Estd. Year:")) {
                college.setEstablishedYear(univDatum.child(0).text());
            } else if (univDatum.text().contains("Total Student Enrollments")) {
                college.setTotalStudents(univDatum.child(0).text());
            }
        }
        return college;
    }
}
