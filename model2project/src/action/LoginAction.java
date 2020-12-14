package action;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.LoginService;
import vo.ActionForward;
import vo.Member;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String re = request.getParameter("rememberID");
		LoginService loginService = new LoginService();
		Member loginMember = loginService.getLoginMember(id, pw);
		
		if (re != null && re.equals("remember")) {
			Cookie idCookie = new Cookie("id", id);
			idCookie.setMaxAge(60 * 60 * 24 * 365);
			System.out.println(idCookie.getValue());
			response.addCookie(idCookie);
			response.addHeader("samesite", "none");
		}
		
		if(loginMember != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", loginMember);
			
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("index.do");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 실패');history.back();</script>");
		}
		return forward;
	}

}
