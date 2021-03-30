package ir.maktabsharif.quiz24.entities.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Quiz")
public class Quiz implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "QUIZ_ID")
    private Long id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "DURATION")
    private Integer duration;
    @ManyToOne
    @JoinColumn(name = "FK_COURSE")
    private Course course;
}
