package net.koreate.common.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CheckTokenInterceptor 
					extends HandlerInterceptorAdapter {

	// session token , request token 을 비교 처리
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("CheckTokenInterceptor START");
		if(request.getMethod().equalsIgnoreCase("POST")) {
			HttpSession session = request.getSession();
			String sessionToken 
				= (String)session.getAttribute("csrf_token");
			System.out.println("session : " + sessionToken);
			String requestToken = request.getParameter("csrf_token");
			System.out.println("request : " + requestToken);
			if(requestToken == null
					|| 
				requestToken.trim().equals("")
					||
			   !requestToken.equals(sessionToken)) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 접근 입니다. 이자식아.');");
				out.println("history.back();");
				out.println("</script>");
				return false;
			}
			
		}
		return true;
	}
	
}








