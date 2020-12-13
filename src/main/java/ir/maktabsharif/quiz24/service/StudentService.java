package ir.maktabsharif.quiz24.service;

import ir.maktabsharif.quiz24.entity.mysql.Student;
import ir.maktabsharif.quiz24.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        studentRepository.save(student);
        return student;
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        for (Student student:studentRepository.findAll()) {
            students.add(student);
        }
        return students;
    }
}
