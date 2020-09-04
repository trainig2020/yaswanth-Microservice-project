package com.springboot.microservice.interceptor;

import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ViewService_Dept_EmpInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		Calendar cal = Calendar.getInstance();
		String[] days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
		int dayOfWeek = cal.get(cal.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			response.getWriter().write("Web application not accessable on " + days[dayOfWeek - 1]);
			return false;

		}

		return true;
	}

}