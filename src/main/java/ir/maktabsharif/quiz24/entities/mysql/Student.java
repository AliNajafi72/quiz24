package ir.maktabsharif.quiz24.entities.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "STUDENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends User implements Serializable {
    @ManyToMany(mappedBy = "students")
    private List<Course> courses = new ArrayList<>();

    @Override
    public boolean isApproved() {
        return super.isApproved();
    }
}
