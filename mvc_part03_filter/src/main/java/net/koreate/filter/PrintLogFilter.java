package net.koreate.filter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrintLogFilter implements Filter{
	
	String filePath;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("PrintLogFilter init 시작 ------");
		filePath = filterConfig.getInitParameter("filePath");
		System.out.println("filePath : " + filePath);
		System.out.println("PrintLogFilter init 종료 ------");
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("PrintLogFilter doFilter 시작 ------");
		String realPath 
			= request.getServletContext().getRealPath(filePath);
		String rootPath 
			= request.getServletContext().getRealPath("/log");
		File file = new File(realPath);
		File log = new File(rootPath);
		System.out.println(file.getAbsolutePath());
		if(!log.exists()) {
			log.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss"
			);
		String date = sdf.format(System.currentTimeMillis());
		HttpServletRequest req
							= (HttpServletRequest)request;
		// 요청 경로
		String requestURI = req.getRequestURI();
		// 요청 방식
		String method = req.getMethod();
		// 요청 ip
		String ip = req.getRemoteAddr();
		PrintWriter pw = new PrintWriter(
			new OutputStreamWriter(new FileOutputStream(file,true))	
		);
		pw.append(date+"-"+ip+"["+method+"]"+requestURI);
		pw.println("");
		pw.flush();
		pw.close();
		chain.doFilter(request,response);
		HttpServletResponse res = (HttpServletResponse)response;
		System.out.println("response 상태코드 : " + res.getStatus());
		System.out.println("PrintLogFilter doFilter 종료 ------");
	}
	
	@Override
	public void destroy() {}

}




