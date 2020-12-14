package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MyMessageLastPageService;
import svc.MyMessageService;
import vo.ActionForward;
import vo.Member;

public class MyMessageAction implements Action {

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
			MyMessageService myMessageService = new MyMessageService();
			LinkedList[] messageList = myMessageService.getMyMessageList(id, nowPage);
			MyMessageLastPageService myMessageLastPageService = new MyMessageLastPageService();
			int lastPage = myMessageLastPageService.getMessengerLastPage(id);
			session.setAttribute("messageLastPage", lastPage);
			session.setAttribute("messageList", messageList);
			forward.setPath("myMessage.jsp?page=" + nowPage);
			return forward;
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
