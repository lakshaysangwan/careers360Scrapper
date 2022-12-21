package com.lakshay.careers360Scrapper.Service;

import com.lakshay.careers360Scrapper.Model.University;
import com.lakshay.careers360Scrapper.Repository.UniversityRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UniversityService {
    private final Logger logger = LoggerFactory.getLogger(UniversityService.class);
    @Autowired
    CourseService courseService;
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    CollegeService collegeService;

    public void universityScrapper(int totalPages) {
        for (int page = 1; page <= totalPages; page++) {
            Document doc;
            while (true) {
                try {
                    doc = Jsoup.connect("https://university.careers360.com/colleges/list-of-universities-in-india?sort=popularity&page=" + page).get();
                    break;
                } catch (Exception e) {
                }
            }
            Elements universityListOnPage;
            universityListOnPage = doc.getElementsByClass("facetContent").get(0).getElementsByClass("blockHeading");
            for (Element university : universityListOnPage) {
                Element data = university.getElementsByTag("a").get(0);
                University univ = new University();
                univ.setUrl(data.attr("href"));
                Document univPage;
                while (true) {
                    try {
                        univPage = Jsoup.connect(data.attr("href")).get();
                        break;
                    } catch (Exception e) {
                    }
                }
                univ.setUnivName(univPage.getElementsByTag("h1").get(0).text().toUpperCase());
                if (universityRepository.findByUnivName(univ.getUnivName()) == null) {
                    Element univProperties = null;
                    try{
                        univProperties = univPage.getElementsByClass("top-menu").get(0);
                    }catch(IndexOutOfBoundsException indexOutOfBoundsException){
                        continue;
                    }
                    if (univProperties.text().contains("Affiliated Colleges")) {
                        logger.info(univ.toString());
                        String collegeUrl = univProperties.getElementsContainingOwnText("Affiliated Colleges").attr("href");
                        univ.setCollegeUrl(collegeUrl);
                        Elements univData = univPage.getElementsByClass("cardBlkInn quickFact").get(0).getElementsByTag("div");
                        for (Element z : univData) {
                            if (z.text().contentEquals("Type of Institute")) {
                                univ.setInstituteType(Objects.requireNonNull(z.nextElementSibling()).text());
                            }
                        }
                        univData = univPage.getElementsByTag("td");
                        for (Element univDatum : univData) {
                            if (univDatum.text().contains("Estd. Year:")) {
                                univ.setEstablishedYear(univDatum.child(0).text());
                            } else if (univDatum.text().contains("Total Student Enrollments")) {
                                univ.setTotalStudents(univDatum.child(0).text());
                            }
                        }
                        univ.setColleges(collegeService.collegeScrapper(univ));
                        universityRepository.save(univ);
                    } else {
                        logger.info(univ.getUnivName() + " has no college");
                        univ.setCourseUrl(univProperties.getElementsContainingOwnText("Courses & Fees").attr("href"));
                        Elements univData = univPage.getElementsByClass("cardBlkInn quickFact").get(0).getElementsByTag("div");
                        for (Element z : univData) {
                            if (z.text().contentEquals("Type of Institute")) {
                                univ.setInstituteType(Objects.requireNonNull(z.nextElementSibling()).text());
                            }
                        }
                        univData = univPage.getElementsByTag("td");
                        for (Element univDatum : univData) {
                            if (univDatum.text().contains("Estd. Year:")) {
                                univ.setEstablishedYear(univDatum.child(0).text());
                            } else if (univDatum.text().contains("Total Student Enrollments")) {
                                univ.setTotalStudents(univDatum.child(0).text());
                            }
                        }
                        univ.setCourses(courseService.courseScrapper(univ));
                        universityRepository.save(univ);
                    }
                }
            }
        }
    }
}
