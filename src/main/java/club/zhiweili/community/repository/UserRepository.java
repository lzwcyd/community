package club.zhiweili.community.repository;

import club.zhiweili.community.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
  User findByToken(String token);
}
