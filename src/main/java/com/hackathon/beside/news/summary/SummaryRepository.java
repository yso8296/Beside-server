package com.hackathon.beside.news.summary;

import com.hackathon.beside.common.entity.Quiz;
import com.hackathon.beside.common.entity.Summary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SummaryRepository extends JpaRepository<Summary, Long> {

    @Query("select summary from Summary summary order by summary.id desc limit 1")
    Optional<Summary> getTodaySummary();
}
