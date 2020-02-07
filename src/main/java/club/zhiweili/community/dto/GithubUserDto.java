package club.zhiweili.community.dto;

import lombok.Data;

@Data
public class GithubUserDto {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}
