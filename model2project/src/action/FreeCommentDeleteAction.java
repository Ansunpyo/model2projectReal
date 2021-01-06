package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.FreeCommentService;
import vo.ActionForward;
import vo.Free_Comment;
import vo.Member;

public class FreeCommentDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		if(loginMember != null) {
			forward = new ActionForward();
			int free_num = Integer.parseInt(request.getParameter("free_num"));
			int page = Integer.parseInt(request.getParameter("page"));
			int comment_num = Integer.parseInt(request.getParameter("comment_num"));
			
			FreeCommentService freeCommentService = new FreeCommentService();
			int result = freeCommentService.freeCommentDelete(free_num, comment_num);
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
