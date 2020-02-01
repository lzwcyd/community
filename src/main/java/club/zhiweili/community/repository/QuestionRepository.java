package club.zhiweili.community.repository;

import club.zhiweili.community.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lzw on 2020/2/1 11:09 上午
 */
@RestController
public interface QuestionRepository extends JpaRepository<Question,Integer> {
}
