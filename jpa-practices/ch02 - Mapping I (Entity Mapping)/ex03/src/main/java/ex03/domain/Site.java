package ex03.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "site")
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false, length = 20)
    private String title;

    @Column(name = "profile", length = 200)
    private String profile;

    @Column(name = "welcome", length = 100)
    private String welcome;

    @Type(type = "text") // @Lob에서 수정함
    @Column(name = "description")
    private String description;

}