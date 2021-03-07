package ir.maktabsharif.quiz24.repository;

import ir.maktabsharif.quiz24.entity.mysql.Course;
import ir.maktabsharif.quiz24.entity.mysql.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class CourseRepositoryIT extends User {

    @Autowired
    CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findById() {
        Optional<Course> courseOptional = courseRepository.findById(1L);
        assertEquals("خواص فیزیکی", courseOptional.get().getTitle());
    }
}