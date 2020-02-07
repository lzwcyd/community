package club.zhiweili.community.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Data
@Table(name = "user")
@Entity(name = "user")
public class User {

  @Id
  @Setter(value = AccessLevel.NONE)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String accountId;

  private String name;

  private String token;

  private Long gmtCreate;

  private Long gmtModified;

  private String avatarUrl;
}
