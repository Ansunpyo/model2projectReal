package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.FreeUpdatePageService;
import vo.ActionForward;
import vo.Free;
import vo.Member;

public class FreeBoardUpdatePageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		Free fr = null;
		
		if(loginMember != null) {
			forward = new ActionForward();
			int free_num = (int) session.getAttribute("free_num");
			
			FreeUpdatePageService freeUpdatePageService = new FreeUpdatePageService();
			fr = freeUpdatePageService.updateFree(free_num);
			session.setAttribute("fr", fr);
			if (fr != null) {
				if(loginMember.getId().equals("admin")) {
					forward.setPath("freeUpdatead.jsp");
					return forward;
				} else {
					forward.setPath("freeUpdate.jsp");
					return forward;
				}
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
