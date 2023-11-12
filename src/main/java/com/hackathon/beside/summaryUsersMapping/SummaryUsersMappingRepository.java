package com.hackathon.beside.summaryUsersMapping;

import com.hackathon.beside.common.entity.SummaryUsersMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SummaryUsersMappingRepository extends JpaRepository<SummaryUsersMapping, Long> {

    @Query("select num from SummaryUsersMapping num where num.user.id =:userId")
    Page<SummaryUsersMapping> findAllById(@Param("userId") Long userId, PageRequest pageRequest);
}
