package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.JoinService;
import vo.ActionForward;
import vo.Member;

public class JoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String classify = request.getParameter("classify");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String major = request.getParameter("major");
		String education = request.getParameter("education");
		Member loginMember = null;
		
		
		JoinService joinService = new JoinService();
		
		if (classify.equals("교사")) {
			loginMember = joinService.getJoinTeacherMember(id, pw, classify, name, email, gender, major, education);
		} else {
			loginMember = joinService.getJoinStudentMember(id, pw, classify, name, email, gender);			
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
			out.println("<script>alert('회원가입 실패');history.back();</script>");
		}
		return forward;
	}

}
