package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.OneOnOneAdListService;
import svc.OneOnOneLastPageService;
import vo.ActionForward;
import vo.Member;

public class One_on_oneadListAction implements Action {

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
			forward = new ActionForward();
			OneOnOneAdListService oneOnOneAdListService = new OneOnOneAdListService();
			LinkedList[] oneOnOneAdList = oneOnOneAdListService.getOneOnOneAdList(nowPage);
			OneOnOneLastPageService oneOnOneLastPageService = new OneOnOneLastPageService();
			int lastPage = oneOnOneLastPageService.getOneOnOneLastPage();
			session.setAttribute("lastPage", lastPage);
			session.setAttribute("oneOnOneAdList", oneOnOneAdList);
			forward.setPath("one_on_onead.jsp");
			return forward;
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
