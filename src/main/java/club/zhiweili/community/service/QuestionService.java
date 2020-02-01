package club.zhiweili.community.service;

import club.zhiweili.community.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lzw on 2020/2/1 11:25 上午
 */
@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;

//    public void create(Question question){
//        questionRepository.save(question);
//    }
}
