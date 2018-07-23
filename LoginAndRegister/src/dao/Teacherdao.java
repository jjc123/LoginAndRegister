package dao;

import entity.Teacher;

public interface Teacherdao {
	void save(Teacher teacher);
	boolean findTeacher(String name);
	Teacher findByNameandPassword(Teacher teacher);
}
