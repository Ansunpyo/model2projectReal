package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.FreeDeleteService;
import vo.ActionForward;
import vo.Member;

public class FreeBoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int free_num = (int) session.getAttribute("free_num");
		int result = 0;
		
		if(loginMember != null) {
			forward = new ActionForward();
			FreeDeleteService freeDeleteService = new FreeDeleteService();
			result = freeDeleteService.deleteFree(free_num);
			
			if (result > 0) {
				forward.setRedirect(true);
				forward.setPath("freeBoard.do?page=1");
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
