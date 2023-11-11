package com.hackathon.beside.news.summary;

import com.hackathon.beside.common.entity.Summary;
import com.hackathon.beside.news.summary.infrastructure.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SummaryService {

    private final SummaryRepository summaryRepository;

    public String getSummaryById(long summaryId) {
        Summary summary = summaryRepository.findById(summaryId)
                .orElseThrow(ResourceNotFoundException::new);

        return summary.getContent();
    }
}
