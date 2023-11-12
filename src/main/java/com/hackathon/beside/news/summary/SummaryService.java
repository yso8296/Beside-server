package com.hackathon.beside.news.summary;

import com.hackathon.beside.common.entity.*;
import com.hackathon.beside.common.exception.ResourceNotFoundException;
import com.hackathon.beside.news.cardnews.CardNewsRecordDto;
import com.hackathon.beside.summaryUsersMapping.SummaryUsersMappingRepository;
import com.hackathon.beside.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SummaryService {

    private final SummaryRepository summaryRepository;
    private final UserRepository userRepository;
    private final SummaryUsersMappingRepository summaryUsersMappingRepository;

    public String getSummaryById(long summaryId) {
        Summary summary = summaryRepository.findById(summaryId)
                .orElseThrow(() -> new ResourceNotFoundException("요청하신 필기 노트를 찾을 수 없습니다."));

        return summary.getContent();
    }

    public SummaryRecordHasNextDto getSummaryRecord(Long userId, Pageable pageable) {
        int page = pageable.getPageNumber(); // page 위치에 있는 값은 0부터 시작한다.
        int pageLimit = 20; // 한페이지에 보여줄 글 개수

        User user = userRepository.findById(userId).orElseThrow();

        PageRequest pageRequest = PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id"));
        Page<SummaryUsersMapping> summaryUsersMappings = summaryUsersMappingRepository.findAllById(userId, pageRequest);
        boolean hasNext = summaryUsersMappings.hasNext();

        List<SummaryRecordDto> summaryRecordDtos = new ArrayList<>();

        for (SummaryUsersMapping summaryUsersMapping : summaryUsersMappings) {
            if (isEqualUser(user, summaryUsersMapping)) {
                Summary summary = summaryUsersMapping.getSummary();
                summaryRecordDtos.add(SummaryRecordDto.toSummaryRecordDto(summary));
            }
        }

        return SummaryRecordHasNextDto.toSummaryRecordHasNextDto(hasNext, summaryRecordDtos);
    }

    private static boolean isEqualUser(User user, SummaryUsersMapping summaryUsersMapping) {
        return summaryUsersMapping.getUser().getId() == user.getId();
    }

    public String getTodaySummary(Long userId) {
        Optional<Summary> todaySummary = summaryRepository.getTodaySummary();
        return todaySummary.get().getContent();
    }
}
