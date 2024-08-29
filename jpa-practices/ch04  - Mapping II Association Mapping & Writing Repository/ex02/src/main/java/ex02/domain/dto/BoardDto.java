package ex02.domain.dto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor // dto projection할때 많이 쓴다! jpql, querydsl 차이
@AllArgsConstructor
public class BoardDto {
    private Integer id;
    private Integer hit;
    private String title;
    private String content;
    private Date regDate;
    private String userName;
}
