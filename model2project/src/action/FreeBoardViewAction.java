package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.FreeViewService;
import vo.ActionForward;
import vo.Member;

public class FreeBoardViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int free_num = Integer.parseInt(request.getParameter("free_num"));
		
		if(loginMember != null) {
			forward = new ActionForward();
			FreeViewService freeViewService = new FreeViewService();
			LinkedList<Object> freeViewList = freeViewService.getFreeView(free_num);

			session.setAttribute("freeViewList", freeViewList);
			if(loginMember.getId().equals("admin")) {
				forward.setPath("freeBoardViewad.jsp?page=" + request.getParameter("page"));
				return forward;
			}
			forward.setPath("freeBoardView.jsp?page=" + request.getParameter("page"));
			return forward;
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
