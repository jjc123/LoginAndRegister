package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import dao.Teacherdao;
import entity.Teacher;
import utils.jdbcUtil;

public class TeacherDaoDbutilsImpl implements Teacherdao {

	Connection connection = null;
	PreparedStatement prepareStatement = null;
	ResultSet result = null;

	@Override
	public void save(Teacher teacher) {
		String sql = "INSERT INTO teacher(name,password) values(?,?)";
		QueryRunner queryRunner = new QueryRunner();
		connection = jdbcUtil.getConnection();
		try {
			queryRunner.update(connection, sql, teacher.getName(), teacher.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(connection, null, null);
		}
	}

	@Override
	public boolean findTeacher(String name) {
		String sql = "SELECT id FROM teacher WHERE name=?";
		try {
			QueryRunner queryRunner = new QueryRunner();
			connection = jdbcUtil.getConnection();
			Integer integer = queryRunner.query(connection, sql, new ScalarHandler<Integer>(), name);
			if (integer != null) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(connection, null, null);
		}
		return false;
	}

	@Override
	public Teacher findByNameandPassword(Teacher teacher) {
		String sql = "SELECT *FROM teacher WHERE name=? ADN password=?";
		QueryRunner queryRunner = new QueryRunner();
		connection = jdbcUtil.getConnection();
		try {
			Teacher newteacher = queryRunner.query(connection, sql, new BeanHandler<Teacher>(Teacher.class), teacher.getName(),teacher.getPassword());
			if (newteacher != null) {
				return newteacher;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(connection, null, null);
		}
		return null;
	}

}
