package ex02.domain;

import lombok.*;

import javax.persistence.*;

// lombok
@Setter
@Getter
@ToString
@RequiredArgsConstructor // Notnull이 붙어있는 것들을 자동으로 생성자 생성
@NoArgsConstructor
@Entity
public class Emaillist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull // lombok
    @Column(nullable = false)
    private String firstName;

    @NonNull
    @Column(nullable = false)
    private String lastName;

    @NonNull
    @Column(nullable = false)
    private String email;
}
