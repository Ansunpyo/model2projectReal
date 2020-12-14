package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.FreeBoardWriteService;
import vo.ActionForward;
import vo.Free;
import vo.Member;

public class FreeBoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int result = 0;
		
		if(loginMember != null) {
			forward = new ActionForward();
			String id = loginMember.getId();
			Free fr = new Free();
			
			fr.setTitle(request.getParameter("title"));
			fr.setContents(request.getParameter("contents"));
			
			FreeBoardWriteService freeBoardWriteService = new FreeBoardWriteService();
			result = freeBoardWriteService.writeFree(id, fr);
			
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
