package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MyFreeBoardLastPageService;
import svc.MyFreeService;
import vo.ActionForward;
import vo.Member;

public class MyFreeAction implements Action {

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
			MyFreeService myFreeService = new MyFreeService();
			LinkedList[] freeList = myFreeService.getMyFreeList(id, nowPage);
			MyFreeBoardLastPageService myFreeBoardLastPageService = new MyFreeBoardLastPageService();
			int lastPage = myFreeBoardLastPageService.getMyFreeLastPage(id);
			session.setAttribute("lastPage", lastPage);
			session.setAttribute("freeList", freeList);
			if(loginMember.getId().equals("admin")) {
				forward.setPath("myFreead.jsp?page="+nowPage);
				return forward;
			}
			forward.setPath("myFree.jsp?page=" + nowPage);
			return forward;
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
