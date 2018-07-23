package service;

import entity.Teacher;

public interface TeacherService {
	void save(Teacher teacher);
	boolean findTeacher(String name);
	Teacher findByNameAndPassword(Teacher teacher);
}
