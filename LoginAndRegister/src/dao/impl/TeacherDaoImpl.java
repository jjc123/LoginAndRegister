package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.Teacherdao;
import entity.Teacher;
import utils.jdbcUtil;

public class TeacherDaoImpl implements Teacherdao {

	Connection connection = null;
	PreparedStatement prepareStatement = null;
	ResultSet result = null;

	// 保存注册信息
	@Override
	public void save(Teacher teacher) {

		String sql = "INSERT INTO teacher(name,password) value(?,?)";
		try {
			connection = jdbcUtil.getConnection();
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, teacher.getName());
			prepareStatement.setString(2, teacher.getPassword());
			prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			jdbcUtil.close(connection, prepareStatement);
		}
	}

	// 根据名字查找是否存在该老师
	@Override
	public boolean findTeacher(String name) {
		String sql = "SELECT *FROM teacher where name=?";
		try {
			connection = jdbcUtil.getConnection();
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, name);
			result = prepareStatement.executeQuery();
			if (result.next()) {
				int id = result.getInt("id");
				if (id > 0) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			jdbcUtil.close(connection, prepareStatement);
		}
	}

	@Override
	public Teacher findByNameandPassword(Teacher teacher) {

		String sql = "SELECT *FROM teacher where name=? AND password=?";
		try {
			connection = jdbcUtil.getConnection();
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, teacher.getName());
			prepareStatement.setString(2, teacher.getPassword());
			result = prepareStatement.executeQuery();
			if (result.next()) {
				Teacher newTeacher = new Teacher();
				newTeacher.setId(result.getInt("id"));
				newTeacher.setName(result.getString("name"));
				newTeacher.setPassword(result.getString("password"));
				return newTeacher;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			jdbcUtil.close(connection, prepareStatement);
		}
	}

}
