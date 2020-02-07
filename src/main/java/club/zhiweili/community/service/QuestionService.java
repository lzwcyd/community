package club.zhiweili.community.service;

import club.zhiweili.community.dto.QuestionDto;
import club.zhiweili.community.entity.Question;
import club.zhiweili.community.entity.User;
import club.zhiweili.community.repository.QuestionRepository;
import club.zhiweili.community.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Created by lzw on 2020/2/1 11:25 上午 */
@Service
public class QuestionService {
  @Autowired QuestionRepository questionRepository;
  @Autowired UserRepository userRepository;

  public List<QuestionDto> list(Integer page, Integer size) {
    Integer offset = size*(page-1);
    Page<Question> questions = questionRepository.findAll(PageRequest.of(offset,size));
    List<QuestionDto> questionDtoList = new ArrayList<>();
    for (Question question : questions) {
      Optional<User> user = userRepository.findById(question.getCreator());
      QuestionDto questionDto = new QuestionDto();
      BeanUtils.copyProperties(question, questionDto);
      questionDto.setUser(user.get());
      questionDtoList.add(questionDto);
    }
    return questionDtoList;
  }
}
