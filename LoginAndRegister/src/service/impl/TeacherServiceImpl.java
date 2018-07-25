package service.impl;

import dao.Teacherdao;
import dao.impl.TeacherDaoDbutilsImpl;
import dao.impl.TeacherDaoImpl;
import entity.Teacher;
import exception.TeacherException;

public class TeacherServiceImpl implements service.TeacherService {

	Teacherdao teacherdao = new TeacherDaoImpl();
	Teacherdao dbutilsteacher = new TeacherDaoDbutilsImpl();
	@Override
	public Teacher login(Teacher teacher) {
		try {
			return dbutilsteacher.findByNameandPassword(teacher);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void register(Teacher teacher) throws TeacherException {

		try {
			boolean flag = dbutilsteacher.findTeacher(teacher.getName());
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
