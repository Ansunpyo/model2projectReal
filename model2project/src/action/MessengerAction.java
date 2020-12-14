package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MessengerLastPageService;
import svc.MessengerService;
import vo.ActionForward;
import vo.Member;

public class MessengerAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		int nowPage = 1;
		if(request.getParameter("page") != null) {
			nowPage = Integer.parseInt(request.getParameter("page"));
		}
		Member loginMember = (Member) session.getAttribute("loginMember");
		if(loginMember != null) {
			String id = loginMember.getId();
			forward = new ActionForward();
			MessengerService messengerService = new MessengerService();
			LinkedList[] messageList = messengerService.getMessageList(id, nowPage);
			MessengerLastPageService messengerLastPageService = new MessengerLastPageService();
			int lastPage = messengerLastPageService.getMessengerLastPage(id);
			session.setAttribute("messageLastPage", lastPage);
			session.setAttribute("messageList", messageList);
			forward.setPath("messenger.jsp?page=" + nowPage);
			return forward;
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
