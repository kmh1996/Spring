package net.koreate.common.interceptor;

import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.koreate.board.service.BoardService;
import net.koreate.board.vo.BoardVO;
import net.koreate.user.vo.UserVO;

public class AuthInterceptor
						extends HandlerInterceptorAdapter {
	@Inject
	BoardService bs;
	
	
	// 전처리
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 권한 확인
		System.out.println("AuthInterceptor STRAT");
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		HttpSession session = request.getSession();
		
		Object obj = session.getAttribute("userInfo");
		
		if(obj == null) {
			System.out.println("사용자 정보 없음");
			response.sendRedirect(contextPath+"/user/signIn");
			return false;
		}else {
			if(requestURI.equals(contextPath+"/board/register")) {
				System.out.println("새글 작성");
				return true;
			}
			
			String num = request.getParameter("bno");
			if(num != null && !num.trim().equals("")) {
				
				int bno = Integer.parseInt(num);
				
				if(requestURI.equals(contextPath+"/board/replyRegister")) {
					System.out.println("답글 작성");
					return true;
				}
				
				// 요청 드러온 게시글 정보
				BoardVO vo = bs.read(bno);
				// 로그인 된 사용자 정보 - session
				UserVO user = (UserVO)obj;
				System.out.println("수정 OR 삭제 요청 시");
				
				if(vo.getUno() == user.getUno()) {
					System.out.println("권한 있는 사용자");
					return true;
				}else {
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.print("<script>");
					out.print("alert('접근 권한이 없습니다.');");
					out.print("history.go(-1);");
					out.print("</script>");
					return false;
				}
			}
		}
		return true;
	}
	
}





