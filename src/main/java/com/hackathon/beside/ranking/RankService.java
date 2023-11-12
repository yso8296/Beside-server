package com.hackathon.beside.ranking;

import com.hackathon.beside.common.entity.QuizUsersMapping;
import com.hackathon.beside.common.entity.User;
import com.hackathon.beside.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class RankService {

    private final UserRepository userRepository;

    public TotalRankDto getUserRank(Long userId, String interest) {
        // 1. 모든 유저 정보 조회
        // 2. interestId 이용해서 관심 주제 일치하는 유저 추출
        // 3. 각 유저마다 quizUserMapping 조회해서 맞은 개수 추출
        // 4. 점수로 순위 계산, 점수 같으면 시간 짧은 순

        List<User> users = userRepository.findAll();  // 1
        List<User> interestUsers = new ArrayList<>();

        for (User user : users) { // 2
            if (user.getInterest().getName().equals(interest)) {
                interestUsers.add(user);
            }
        }

        Map<Integer, User> map = new HashMap<>();
        for (User interestUser : interestUsers) {
            List<QuizUsersMapping> quizUsersMappings = interestUser.getQuizUsersMappings();
            int score = 0;
            for (QuizUsersMapping quizUsersMapping : quizUsersMappings) {
                score += quizUsersMapping.getCorrectCount() * 10;
            }
            map.put(score, interestUser);
        }

        Map<Integer, User> keyDescendingMap = new TreeMap<>();  // 3
        keyDescendingMap.putAll(map);

        List<MyRankDto> userRanks = new ArrayList<>();
        MyRankDto myRanking = new MyRankDto();
        int cnt = 1;
        int check = 0;
        for (int key : keyDescendingMap.keySet()) {
            User user = map.get(key);
            if (user.getId() == userId) {
                myRanking = MyRankDto.toMyRankDto(user, cnt, (float) key);
                check++;
            } else if(userRanks.size() < 3){
                userRanks.add(MyRankDto.toMyRankDto(user, cnt, key));
                check++;
            }
            cnt++;

            if(check == 4) break;
        }

        return TotalRankDto.toTotalRankDto(myRanking, userRanks);
    }
}
