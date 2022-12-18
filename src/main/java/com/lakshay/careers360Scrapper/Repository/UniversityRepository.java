package com.lakshay.careers360Scrapper.Repository;

import com.lakshay.careers360Scrapper.Model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
    public University findByUnivName(String univName);
}
