package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MessageDeleteService;
import vo.ActionForward;
import vo.Member;

public class MessageDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		if(loginMember != null) {
			int message_num = Integer.parseInt(request.getParameter("message_num"));
			forward = new ActionForward();
			MessageDeleteService messageDeleteService = new MessageDeleteService();
			int result = messageDeleteService.deleteMessage(message_num);
			if(result > 0) {
				forward.setPath("messenger.do");
				return forward;
			} else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("index.do");
				return forward;
			}
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
