package club.zhiweili.community.dto;

import club.zhiweili.community.entity.User;
import lombok.Data;

/** Created by lzw on 2020/2/3 10:42 上午 */
@Data
public class QuestionDto {
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
  private User user;
}
