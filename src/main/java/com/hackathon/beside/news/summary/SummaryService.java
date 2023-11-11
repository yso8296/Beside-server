package com.hackathon.beside.news.summary;

import com.hackathon.beside.common.entity.Summary;
import com.hackathon.beside.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SummaryService {

    private final SummaryRepository summaryRepository;

    public String getSummaryById(long summaryId) {
        Summary summary = summaryRepository.findById(summaryId)
                .orElseThrow(() -> new ResourceNotFoundException("요청하신 필기 노트를 찾을 수 없습니다."));

        return summary.getContent();
    }
}
