package club.zhiweili.community.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

/** Created by lzw on 2020/2/1 11:09 上午 */
@Data
@Table(name = "question")
@Entity(name = "question")
public class Question {
  @Id
  @Setter(value = AccessLevel.NONE)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private Integer creator;
  private String title;
  private String description;
  private String tag;
  private Long gmtCreate;
  private Long gmtModified;
  private Integer commentCount;
  private Integer viewCount;
  private Integer likeCount;
}
