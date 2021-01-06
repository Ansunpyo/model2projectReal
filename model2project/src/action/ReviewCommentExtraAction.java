package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ReviewCommentService;
import vo.ActionForward;
import vo.Member;
import vo.Review_Comment;

public class ReviewCommentExtraAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		Review_Comment rc = new Review_Comment();
		
		if(loginMember != null) {
			forward = new ActionForward();
			rc.setContents(request.getParameter("contents").replace("\n", "<br/>"));
			int number = loginMember.getNumber();
			int parent = Integer.parseInt(request.getParameter("parent"));
			int review_num = Integer.parseInt(request.getParameter("review_num"));
			int page = Integer.parseInt(request.getParameter("page"));
			
			ReviewCommentService reviewCommentService = new ReviewCommentService();
			int result = reviewCommentService.reviewCommentExtra(rc, number, parent, review_num);
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
