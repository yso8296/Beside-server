package com.hackathon.beside.news.summary;

import com.hackathon.beside.common.annotation.LoggedInUserId;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RestController
public class SummaryController {

    private final SummaryService summaryService;

    @GetMapping("/summaries/{summaryId}")
    public String getSummaryById(
            @PathVariable("summaryId") @Positive() long summaryId
    ) {
        return summaryService.getSummaryById(summaryId);
    }

    @GetMapping("/record/summaries")
    public SummaryRecordHasNextDto SummaryRecord(
            @LoggedInUserId Long userId,
            @PageableDefault(page = 0) Pageable pageable
    ) {
        SummaryRecordHasNextDto summaryRecord = summaryService.getSummaryRecord(userId, pageable);
        return summaryRecord;
    }
}
