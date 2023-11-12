package com.hackathon.beside.news.quiz;

import com.hackathon.beside.common.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}
