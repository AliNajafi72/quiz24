package ir.maktabsharif.quiz24.controller;

import ir.maktabsharif.quiz24.entity.mysql.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PagesControllerTest extends User {

    PagesController pagesController;

    @BeforeEach
    void setUp() {
        this.pagesController = new PagesController();
    }

    @Test
    void indexPage() {
        String view = pagesController.indexPage();
        assertEquals("index", view);
    }
}