package ir.maktabsharif.quiz24.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "COURSE")
public class Course implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "COURSE_ID", nullable = false, unique = true, updatable = false)
    private Long id;
    @Column(name = "TITLE", nullable = false)
    private String title;
    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE", nullable = false, updatable = false)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "FINISH_DATE", nullable = false, updatable = false)
    private Date finishDate;
    @ManyToOne
    @JoinColumn(name = "FK_TEACHER")
    private Teacher teacher;
    @ManyToMany
    @JoinTable(
            name = "COURSE_STUDENT",
            joinColumns = {
                    @JoinColumn(name = "FK_COURSE")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "FK_TEACHER")
            }
    )
    private List<Student> students;
    @OneToMany(mappedBy = "course")
    private List<Quiz> quizzes;

    public Quiz addExam(Quiz quiz) {
        quizzes.add(quiz);
        quiz.setCourse(this);
        return quiz;
    }
}
