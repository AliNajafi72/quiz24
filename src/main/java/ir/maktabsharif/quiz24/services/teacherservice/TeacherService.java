package ir.maktabsharif.quiz24.services.teacherservice;

import ir.maktabsharif.quiz24.commands.TeacherCommand;
import ir.maktabsharif.quiz24.entities.mysql.Teacher;
import ir.maktabsharif.quiz24.services.baseservice.BaseService;
import org.springframework.stereotype.Service;

public interface TeacherService extends BaseService<Teacher, TeacherCommand> {

}
