package ir.maktabsharif.quiz24.controller;

import ir.maktabsharif.quiz24.controllers.AdminController;
import ir.maktabsharif.quiz24.entities.mongodb.Analytic;
import ir.maktabsharif.quiz24.entities.mysql.User;
import ir.maktabsharif.quiz24.repositories.AnalyticRepository;
import ir.maktabsharif.quiz24.repositories.StudentRepository;
import ir.maktabsharif.quiz24.repositories.TeacherRepository;
import ir.maktabsharif.quiz24.services.AnalyticService;
import ir.maktabsharif.quiz24.services.StudentService;
import ir.maktabsharif.quiz24.services.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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

    @Test
    void mockMvc() throws Exception {
        List<Analytic> analytics = new ArrayList<>();
        analytics.add(new Analytic());
        Mockito.when(analyticRepository.findAll()).thenReturn(analytics);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
        mockMvc.perform(get("/admin/")).andExpect(status().isOk()).andExpect(view().name("admin"));
    }
}