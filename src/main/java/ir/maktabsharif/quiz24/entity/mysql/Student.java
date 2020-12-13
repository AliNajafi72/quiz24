package ir.maktabsharif.quiz24.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "STUDENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends User implements Serializable {
    @ManyToMany(mappedBy = "students")
    private List<Course> courses;

    @Override
    public boolean isApproved() {
        return super.isApproved();
    }
}
