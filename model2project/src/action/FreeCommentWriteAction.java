package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.FreeCommentService;
import vo.ActionForward;
import vo.Member;

public class FreeCommentWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		if(loginMember != null) {
			forward = new ActionForward();
			int free_num = Integer.parseInt(request.getParameter("free_num"));
			int page = Integer.parseInt(request.getParameter("page"));
			int number = loginMember.getNumber();
			String contents = request.getParameter("contents").replace("\n", "<br/>");
			
			FreeCommentService freeCommentService = new FreeCommentService();
			int result = freeCommentService.freeCommentWrite(free_num, number, contents);
			if (result > 0) {
				forward.setRedirect(true);
				forward.setPath("freeView.do?page=" + page + "&free_num=" + free_num);
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
