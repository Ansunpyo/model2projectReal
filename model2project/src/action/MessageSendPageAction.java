package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.IsThereIdService;
import vo.ActionForward;
import vo.Member;

public class MessageSendPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		if(loginMember != null) {
			String receiver = request.getParameter("receiver");
			if(receiver == null || receiver.length() == 0) {
				forward = new ActionForward();
				forward.setPath("sendMessage.jsp");
				return forward;
			} else {
				boolean isThereId = false;
				IsThereIdService isThereIdService = new IsThereIdService();
				isThereId = isThereIdService.isThere(receiver);
				if(isThereId) {
					session.setAttribute("receiver", receiver);
					forward = new ActionForward();
					forward.setPath("sendMessage.jsp?receiver=" + receiver);
				} else {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('탈퇴한 회원입니다.');history.back();</script>");
				}
				return forward;
			}
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
