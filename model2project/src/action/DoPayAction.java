package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.DoPayService;
import vo.ActionForward;
import vo.Member;

public class DoPayAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		if(loginMember != null) {
			forward = new ActionForward();
			String id = loginMember.getId();
			int lecture_num = Integer.parseInt(request.getParameter("lecture_num"));
			String type = request.getParameter("type");
			String pay_code = request.getParameter("pay_code");
			
			DoPayService doPayService = new DoPayService();
			int result = doPayService.doPay(id, lecture_num, type, pay_code);
			if(result > 0) {
				forward.setPath("lectureDetail.do?lecture_num=" + lecture_num);
				return forward;
			} else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('결제 실패!');history.back();</script>");
				return forward;
			}
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
