package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ReviewUpdateService;
import vo.ActionForward;
import vo.Member;
import vo.Review;

public class ReviewUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		Review re = new Review();
		
		if(loginMember != null) {
			forward = new ActionForward();
			re.setReview_num(Integer.parseInt(request.getParameter("review_num")));
			re.setTitle(request.getParameter("title"));
			re.setContents(request.getParameter("contents"));
			
			ReviewUpdateService reviewUpdateService = new ReviewUpdateService();
			int result = reviewUpdateService.updateReview(re);
			if (result > 0) {
				forward.setRedirect(true);
				forward.setPath("review.do");
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
