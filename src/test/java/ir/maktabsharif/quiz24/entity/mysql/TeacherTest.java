package ir.maktabsharif.quiz24.entity.mysql;

import ir.maktabsharif.quiz24.entities.mysql.Teacher;
import ir.maktabsharif.quiz24.entities.mysql.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest extends User {

    Teacher teacher;

    @BeforeEach
    void setUp() {
        teacher = new Teacher();
    }

    @Test
    void testGetUsername() {
        String username = "username";
        teacher.setUsername(username);
        assertEquals(username, teacher.getUsername());
    }
}