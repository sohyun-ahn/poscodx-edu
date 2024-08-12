package ex02.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class BoardDto {
    private Integer id;
    private Integer hit;
    private String title;
    private String content;
    private Date regDate;
    private String userName;
}
