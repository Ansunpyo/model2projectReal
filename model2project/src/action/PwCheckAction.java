package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.PwCheckService;
import vo.ActionForward;
import vo.Member;

public class PwCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		Member mem = new Member();
		
		mem.setId(request.getParameter("id"));
		mem.setEmail(request.getParameter("email"));
		
		forward = new ActionForward();
		
		PwCheckService pwCheckService = new PwCheckService();
		boolean isIdEmailRight = pwCheckService.pwCheck(mem);
		
		if (isIdEmailRight) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			String pw = "";
			pw = pwCheckService.yourPw(mem);
			PrintWriter out = response.getWriter();
			out.println("<script>alert('해당 아이디의 비밀번호는 " + pw + " 입니다.');history.back();</script>");
			return forward;
		} else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('입력하신 정보와 일치하는 회원이 없습니다.');history.back();</script>");
			return forward;
		}
			
	}

}
