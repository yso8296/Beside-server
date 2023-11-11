package com.hackathon.beside.news.summary;

import com.hackathon.beside.common.entity.Summary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SummaryRepository extends JpaRepository<Summary, Long> {
}
