package com.hackathon.beside.news.quiz;

import com.hackathon.beside.common.entity.Quiz;
import com.hackathon.beside.common.entity.QuizUsersMapping;
import com.hackathon.beside.common.entity.User;
import com.hackathon.beside.common.exception.ResourceNotFoundException;
import com.hackathon.beside.news.cardnews.presentation.response.QuizDto;
import com.hackathon.beside.quizUsersMapping.QuizUsersMappingRepository;
import com.hackathon.beside.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final QuizUsersMappingRepository quizUsersMappingRepository;

    //todo 풀었는 문제인 경우 해설과, 선택한 보기 표시할 수 있도록 구현.
    public QuizDto getQuizById(long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("존재 하지 않는 퀴즈 입니다."));

        return QuizDto.toQuizDto(quiz);
    }

    public QuizRecordHasNextDto quizRecord(Long userId, Pageable pageable) {
        int page = pageable.getPageNumber(); // page 위치에 있는 값은 0부터 시작한다.
        int pageLimit = 1; // 한페이지에 보여줄 글 개수

        User user = userRepository.findById(userId).orElseThrow();

        PageRequest pageRequest = PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id"));
        Page<Quiz> pageQuiz = quizRepository.findAllQuizRecord(user.getId(), pageRequest);

        boolean hasNext = pageQuiz.hasNext();

        // 한 페이지당 3개식 글을 보여주고 정렬 기준은 ID기준으로 내림차순
        List<QuizUsersMapping> quizUsersMappings = quizUsersMappingRepository.findAll();
        List<QuizRecordDto> quizRecordDtos = new ArrayList<>();

        for (QuizUsersMapping quizUsersMapping : quizUsersMappings) {
            if (isEqualUser(user, quizUsersMapping)) {
                quizRecordDtos.add(QuizRecordDto.toQuizRecordDto(quizUsersMapping.getQuiz(), quizUsersMapping));
            }
        }

        return QuizRecordHasNextDto.toQuizRecordHasNextDto(hasNext, quizRecordDtos);
    }

    private static boolean isEqualUser(User user, QuizUsersMapping quizUsersMapping) {
        return quizUsersMapping.getUser().getId() == user.getId();
    }
}
