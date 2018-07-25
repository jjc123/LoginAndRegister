package servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import entity.Teacher;
import exception.TeacherException;
import service.TeacherService;
import service.impl.TeacherServiceImpl;
import utils.WebUtils;
import utils.jdbcUtil;

@WebServlet("/TeacherServlet")
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TeacherService teacherService = new TeacherServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		register(request, response);
	};

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	private void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = jdbcUtil.getConnection();
		System.out.println(connection);
		System.out.println(request.getContextPath());
		Teacher teacher = null;
		try {
			teacher = WebUtils.copyToBean(request, Teacher.class);
			System.out.println(teacher);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// teacher = WebUtils.copyToBean(request, Teacher.class);
		try {
			teacherService.register(teacher);
			request.setAttribute("name", teacher.getName());
			// 注册成功，转发到主页
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (TeacherException e) {
			System.out.println(e.getMessage());
			request.setAttribute("context", e.getMessage());
			// 注册失败，返回注册页面
			request.getRequestDispatcher("/Register.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error/error.jsp");
		}
	}

}
