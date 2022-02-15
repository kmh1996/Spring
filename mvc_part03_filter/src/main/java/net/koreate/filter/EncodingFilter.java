package net.koreate.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodingFilter implements Filter {
	
	String filterParam;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("encodingFilter init 호출 --- ");
		filterParam = filterConfig.getInitParameter("filterParam");
		System.out.println("filterParam : " + filterParam);
		System.out.println("encodingFilter init 종료 --- ");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("encodingFilter doFilter 호출 --- ");
		HttpServletRequest req = (HttpServletRequest)request;
		String method = req.getMethod().toUpperCase();
		if(method.equals("POST")) {
			request.setCharacterEncoding(filterParam);
		}
		chain.doFilter(request, response);
		System.out.println("encodingFilter doFilter 종료 --- ");
	}

	@Override
	public void destroy() {}

}
