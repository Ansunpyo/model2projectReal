package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MembersLastPageService;
import svc.MembersListService;
import vo.ActionForward;
import vo.Member;

public class MembersListAction implements Action {

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
			MembersListService membersListService = new MembersListService();
			LinkedList<Member> memList = membersListService.getMembersList(nowPage);
			MembersLastPageService membersLastPageService = new MembersLastPageService();
			int lastPage = membersLastPageService.getMembersLastPage();
			session.setAttribute("lastPage", lastPage);
			session.setAttribute("memList", memList);
			forward.setPath("membersad.jsp?page="+nowPage);
			return forward;
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
