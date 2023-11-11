package com.hackathon.beside.news.cardnews;

import com.hackathon.beside.common.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardNewsRepository extends JpaRepository<News, Long> {
}
