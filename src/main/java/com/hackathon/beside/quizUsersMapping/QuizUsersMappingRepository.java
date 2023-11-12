package com.hackathon.beside.quizUsersMapping;

import com.hackathon.beside.common.entity.NewsUsersMapping;
import com.hackathon.beside.common.entity.QuizUsersMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuizUsersMappingRepository extends JpaRepository<QuizUsersMapping, Long> {

    @Query("select num from QuizUsersMapping num where num.user.id =:userId")
    Page<QuizUsersMapping> findAllById(@Param("userId") Long userId, PageRequest pageRequest);

}
