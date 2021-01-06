package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ReviewCommentService;
import vo.ActionForward;
import vo.Member;

public class ReviewCommentDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		if(loginMember != null) {
			forward = new ActionForward();
			int review_num = Integer.parseInt(request.getParameter("review_num"));
			int page = Integer.parseInt(request.getParameter("page"));
			int comment_num = Integer.parseInt(request.getParameter("comment_num"));
			
			ReviewCommentService reviewCommentService = new ReviewCommentService();
			int result = reviewCommentService.reviewCommentDelete(review_num, comment_num);
			if (result > 0) {
				forward.setRedirect(true);
				forward.setPath("reviewView.do?page=" + page + "&review_num=" + review_num);
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
