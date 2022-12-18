package com.lakshay.careers360Scrapper.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class UtilitiesService {
    private static final Logger logger = LoggerFactory.getLogger(UtilitiesService.class);

    public String findUsingRegex(String regex, String string) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public int totalPages(String input) {
        int totalPages = Integer.parseInt(input) / 30;
        if (Integer.parseInt(input) % 30 != 0) {
            return ++totalPages;
        }
        return totalPages;
    }
}
