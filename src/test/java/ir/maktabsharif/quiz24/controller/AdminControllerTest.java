package ir.maktabsharif.quiz24.controller;

import ir.maktabsharif.quiz24.entity.mongodb.Analytic;
import ir.maktabsharif.quiz24.entity.mysql.User;
import ir.maktabsharif.quiz24.repository.AnalyticRepository;
import ir.maktabsharif.quiz24.repository.StudentRepository;
import ir.maktabsharif.quiz24.repository.TeacherRepository;
import ir.maktabsharif.quiz24.service.AnalyticService;
import ir.maktabsharif.quiz24.service.StudentService;
import ir.maktabsharif.quiz24.service.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdminControllerTest extends User {
    // Model
    @Mock
    Model model;

    // Repositories
    @Mock
    StudentRepository studentRepository;
    @Mock
    TeacherRepository teacherRepository;
    @Mock
    AnalyticRepository analyticRepository;

    // Services
    TeacherService teacherService;
    StudentService studentService;
    AnalyticService analyticService;

    // Controllers
    AdminController adminController;

    @BeforeEach
    void setUp() {
        this.model = Mockito.mock(Model.class);

        this.studentRepository = Mockito.mock(StudentRepository.class);
        this.teacherRepository = Mockito.mock(TeacherRepository.class);
        this.analyticRepository = Mockito.mock(AnalyticRepository.class);

        this.teacherService = new TeacherService(teacherRepository);
        this.studentService = new StudentService(studentRepository);
        this.analyticService = new AnalyticService(analyticRepository);

        this.adminController = new AdminController(this.analyticService, this.studentService, this.teacherService);
    }

    @Test
    void adminPanelIndex() {
        List<Analytic> analytics = new ArrayList<>();
        analytics.add(new Analytic());
        Mockito.when(analyticRepository.findAll()).thenReturn(analytics);
        String view = this.adminController.adminPanelIndex(this.model);
        assertEquals("admin", view);
    }
}