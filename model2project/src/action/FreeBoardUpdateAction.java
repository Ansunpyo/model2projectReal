package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.FreeUpdateService;
import vo.ActionForward;
import vo.Free;
import vo.Member;

public class FreeBoardUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		Free fr = new Free();
		
		if(loginMember != null) {
			forward = new ActionForward();
			fr.setFree_num(Integer.parseInt(request.getParameter("free_num")));
			fr.setTitle(request.getParameter("title"));
			fr.setContents(request.getParameter("contents").replace("\n", "<br/>"));
			
			FreeUpdateService freeUpdateService = new FreeUpdateService();
			int result = freeUpdateService.updateFree(fr);
			if (result > 0) {
				forward.setRedirect(true);
				forward.setPath("freeBoard.do");
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
