package ir.maktabsharif.quiz24.services.studentservice;

import ir.maktabsharif.quiz24.entities.mysql.Student;
import ir.maktabsharif.quiz24.entities.mysql.UserStatus;
import ir.maktabsharif.quiz24.repositories.StudentRepository;
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

    public Student verifyStudent(Long id) {
        Student student = studentRepository.findById(id).isPresent()?studentRepository.findById(id).get():new Student();
        student.setStatus(UserStatus.APPROVED);
        studentRepository.save(student);
        return student;
    }
}
