package com.hackathon.beside.news.cardnews;

import com.hackathon.beside.common.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CardNewsRepository extends JpaRepository<News, Long> {

    @Query(value = "select c from News c join fetch c.newsContents")
    Optional<News> getCardNewsContents(long newsId);
}
