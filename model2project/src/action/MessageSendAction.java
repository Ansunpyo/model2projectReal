package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MessageSendService;
import vo.ActionForward;
import vo.Member;
import vo.Message;

public class MessageSendAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int result = 0;
		
		if(loginMember != null) {
			forward = new ActionForward();
			String id = loginMember.getId();
			Member mem = new Member();
			Message mes = new Message();
			
			mem.setName(request.getParameter("receiver"));
			mes.setTitle(request.getParameter("title"));
			mes.setContents(request.getParameter("contents"));
			
			MessageSendService messageSendService = new MessageSendService();
			result = messageSendService.sendMessage(id, mem, mes);
			
			if (result > 0) {
				forward.setRedirect(true);
				forward.setPath("messenger.do?page=1");
				return forward;
			} else {
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
