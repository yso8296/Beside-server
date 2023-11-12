package com.hackathon.beside.news.quiz;

import com.hackathon.beside.common.entity.Question;
import com.hackathon.beside.common.entity.Quiz;
import com.hackathon.beside.common.entity.QuizOption;
import com.hackathon.beside.common.entity.QuizOptionUserMapping;
import com.hackathon.beside.common.entity.QuizUsersMapping;
import com.hackathon.beside.common.entity.User;
import com.hackathon.beside.common.exception.ResourceNotFoundException;
import com.hackathon.beside.news.cardnews.presentation.response.QuestionDto;
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
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizOptionUserRepository quizOptionUserRepository;
    private final QuizOptionRepository quizOptionRepository;
    private final UserRepository userRepository;
    private final QuizUsersMappingRepository quizUsersMappingRepository;

    //todo 풀었는 문제인 경우 해설과, 선택한 보기 표시할 수 있도록 구현.
    public QuizDto getTodayQuiz() {
        Quiz quiz = quizRepository.getTodayQuiz()
                .orElseThrow(() -> new ResourceNotFoundException("존재 하지 않는 퀴즈 입니다."));

        return QuizDto.toTodayQuiz(quiz);
    }

    public QuizDto getQuizById(long quizId, Long userId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("존재 하지 않는 퀴즈 입니다."));
        List<Long> questionIds = quiz.getQuestions().stream()
                .map(Question::getId)
                .toList();

        List<QuizOptionUserMapping> quizOptionUserMappings = quizOptionUserRepository.findByUserChoices(userId, questionIds);
        List<QuizOptionUserMapping> wrongAnswerQuestions = quizOptionUserMappings.stream()
                .filter(quizOptionUserMapping -> !quizOptionUserMapping.getQuizOption().isAnswer())
                .toList();

        List<Long> wrongAnswerQuestionIds = wrongAnswerQuestions.stream()
                .map(QuizOptionUserMapping::getId)
                .toList();

        Map<Long,Question> wrongQuestionMap = quiz.getQuestions()
                .stream()
                .filter(question -> wrongAnswerQuestionIds.contains(question.getId()))
                .collect(Collectors.toMap(
                        Question::getId,
                        question -> question
                ));

        Map<Long, QuizOptionUserMapping> quizOptionUserMap = wrongAnswerQuestionIds.stream()
                .collect(Collectors.toMap(
                        questionId -> questionId,
                        questionId -> quizOptionUserRepository.findByUserIdAndQuestionId(userId, questionId))
                );

        Map<Long, QuizOption> answerOptionMap = wrongAnswerQuestionIds.stream()
                .collect(Collectors.toMap(
                        questionId -> questionId,
                        quizOptionRepository::findAnswerOptionByQuestionId
                ));

        List<QuestionDto> questionDtos = new ArrayList<>();
        wrongAnswerQuestionIds.forEach(
                questionId -> questionDtos.add(
                        new QuestionDto(
                                wrongQuestionMap.get(questionId),
                                quizOptionUserMap.get(questionId),
                                answerOptionMap.get(questionId)
                        )));

        return new QuizDto(quiz, questionDtos);
    }

    public QuizRecordHasNextDto quizRecord(Long userId, Pageable pageable) {
        int page = pageable.getPageNumber(); // page 위치에 있는 값은 0부터 시작한다.
        int pageLimit = 20; // 한페이지에 보여줄 글 개수

        User user = userRepository.findById(userId).orElseThrow();

        PageRequest pageRequest = PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id"));
        Page<QuizUsersMapping> quizUsersMappings = quizUsersMappingRepository.findAllById(userId, pageRequest);

        boolean hasNext = quizUsersMappings.hasNext();

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
