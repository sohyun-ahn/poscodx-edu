package ex01.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Date;

@ToString
@AllArgsConstructor // 사용시, dto가 깔끔해짐
public class GuestbookDto {
    private Integer id;
    private String name;
    private String content;
    private Date regDate;
}
