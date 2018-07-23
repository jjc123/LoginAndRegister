package service.impl;

import dao.Teacherdao;
import dao.impl.TeacherDaoImpl;
import entity.Teacher;
import exception.TeacherException;

public class TeacherServiceImpl implements service.TeacherService {

	Teacherdao teacherdao = new TeacherDaoImpl();

	@Override
	public Teacher login(Teacher teacher) {
		try {
			return teacherdao.findByNameandPassword(teacher);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void register(Teacher teacher) throws TeacherException {

		try {
			boolean flag = teacherdao.findTeacher(teacher.getName());
			if (flag) {

				throw new TeacherException("注册失败，该账号已被注册");

			} else {
				teacherdao.save(teacher);
			}
		} catch (TeacherException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
