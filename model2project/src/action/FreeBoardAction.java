package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.FreeBoardLastPageService;
import svc.FreeBoardService;
import vo.ActionForward;
import vo.Member;

public class FreeBoardAction implements Action {

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
			FreeBoardService freeBoardService = new FreeBoardService();
			LinkedList[] freeList = freeBoardService.getFreeList(nowPage);
			FreeBoardLastPageService freeBoardLastPageService = new FreeBoardLastPageService();
			int lastPage = freeBoardLastPageService.getFreeLastPage();
			session.setAttribute("lastPage", lastPage);
			session.setAttribute("freeList", freeList);
			if(loginMember.getId().equals("admin")) {
				forward.setPath("freeBoardad.jsp?page="+nowPage);
				return forward;
			}
			forward.setPath("freeBoard.jsp?page=" + nowPage);
			return forward;
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
