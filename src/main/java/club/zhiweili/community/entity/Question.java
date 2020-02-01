package club.zhiweili.community.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/** Created by lzw on 2020/2/1 11:09 上午 */
@Getter
@Setter
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

  @Column(name = "gmt_create")
  private Long gmtCreate;

  @Column(name = "gmt_modified")
  private Long gmtModified;

  @Column(name = "comment_count")
  private Integer commentCount;

  @Column(name = "view_count")
  private Integer viewCount;

  @Column(name = "like_count")
  private Integer likeCount;
}
