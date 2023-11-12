package com.hackathon.beside.news.cardnews;

import com.hackathon.beside.common.entity.News;
import com.hackathon.beside.common.entity.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CardNewsRepository extends JpaRepository<News, Long> {

    @Query(value = "select c from News c join fetch c.newsContents")
    Optional<News> getCardNewsContents(long newsId);

    @Query("select news from News news join news.newsUsersMappings qum where qum.user.id =:id")
    Page<News> findAllNewsRecord(@Param("id") Long id, PageRequest pageRequest);
}
