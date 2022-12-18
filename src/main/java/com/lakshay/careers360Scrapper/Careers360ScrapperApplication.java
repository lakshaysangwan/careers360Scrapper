package com.lakshay.careers360Scrapper;

import com.lakshay.careers360Scrapper.Repository.UniversityRepository;
import com.lakshay.careers360Scrapper.Service.UniversityService;
import com.lakshay.careers360Scrapper.Service.UtilitiesService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Careers360ScrapperApplication implements CommandLineRunner {
    static String url = "https://university.careers360.com/colleges/list-of-universities-in-india";
    private final Logger logger = LoggerFactory.getLogger(Careers360ScrapperApplication.class);
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    private UniversityService universityService;
    @Autowired
    private UtilitiesService utilitiesService;

    public static void main(String[] args) {
        SpringApplication.run(Careers360ScrapperApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Document doc = Jsoup.connect(url).get();
        String totalUniversitiesString = doc.getElementsByClass("showResult").get(0).text();
        String totalUniversities = utilitiesService.findUsingRegex(" (.*) ", totalUniversitiesString);
        int totalPages = utilitiesService.totalPages(totalUniversities);
        universityService.universityScrapper(totalPages);
    }
}
