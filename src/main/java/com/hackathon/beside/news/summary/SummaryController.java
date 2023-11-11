package com.hackathon.beside.news.summary;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
