package service;

import entity.Teacher;
import exception.TeacherException;

public interface TeacherService {
	Teacher login(Teacher teacher);
	void register(Teacher teacher) throws TeacherException;
}
