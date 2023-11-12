package com.hackathon.beside.common.newsUsersMapping;

import com.hackathon.beside.common.entity.NewsUsersMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NewsUsersMappingRepository extends JpaRepository<NewsUsersMapping, Long> {

    @Query("select num from NewsUsersMapping num where num.user.id =:userId")
    Page<NewsUsersMapping> findAllById(@Param("userId") Long userId, PageRequest pageRequest);
}
