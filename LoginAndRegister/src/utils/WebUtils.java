package utils;

import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

public class WebUtils {
	
	@Deprecated
	public static <T> T copyToBean_old(HttpServletRequest request, Class<T> clazz) {
		try {
			// 创建对象
			T t = clazz.newInstance();
			
			// 获取所有的表单元素的名称
			Enumeration<String> enums = request.getParameterNames();
			// 遍历
			while (enums.hasMoreElements()) {
				// 获取表单元素的名称:<input type="password" name="pwd"/>
				String name = enums.nextElement();  // pwd
				// 获取名称对应的值
				String value = request.getParameter(name);
				// 把指定属性名称对应的值进行拷贝
				BeanUtils.copyProperty(t, name, value);
			}
			
			return t;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 处理请求数据的封装
	 */
	public static <T> T copyToBean(HttpServletRequest request, Class<T> clazz) {
		try {
			// 创建对象
			T t = clazz.newInstance();
			BeanUtils.populate(t, request.getParameterMap());
			return t;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static <T> T copyTimeToBean(HttpServletRequest request, Class<T> clazz) {
		try {
			// （注册日期类型转换器）
			String pattern = "yyyy-MM-dd";
		    //返回默认区域设置的Java虚拟机实例
		    Locale locale = Locale.getDefault();

		    DateLocaleConverter converter = new DateLocaleConverter(locale,pattern);
		    converter.setLenient(true);
		    ConvertUtils.register(converter, Date.class);
			// 创建对象
			T t = clazz.newInstance();
			BeanUtils.populate(t, request.getParameterMap());
			return t;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
