package com.lakshay.careers360Scrapper.Repository;

import com.lakshay.careers360Scrapper.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
