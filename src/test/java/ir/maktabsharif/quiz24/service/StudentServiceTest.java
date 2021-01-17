package ir.maktabsharif.quiz24.service;

import ir.maktabsharif.quiz24.entity.mysql.Student;
import ir.maktabsharif.quiz24.entity.mysql.User;
import ir.maktabsharif.quiz24.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest extends User {

    StudentService studentService;

    @Mock
    StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        this.studentRepository = Mockito.mock(StudentRepository.class);
        this.studentService = new StudentService(this.studentRepository);
    }

    @Test
    void getAllStudents() {
        Student student = new Student();
        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(student);
        Mockito.when(studentService.getAllStudents()).thenReturn(mockStudents);
        List<Student> students = studentService.getAllStudents();
        assertEquals(students.size(), 1);
    }
}