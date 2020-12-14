package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ReviewDeleteService;
import svc.ReviewLastPageService;
import vo.ActionForward;
import vo.Member;

public class ReviewDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int review_num = (int) session.getAttribute("review_num");
		int result = 0;
		
		if(loginMember != null) {
			forward = new ActionForward();
			ReviewDeleteService reviewDeleteService = new ReviewDeleteService();
			result = reviewDeleteService.deleteReview(review_num);
			
			if (result > 0) {
				ReviewLastPageService reviewLastPageService = new ReviewLastPageService();
				int lastPage = reviewLastPageService.getReviewLastPage();
				session.setAttribute("lastPage", lastPage);
				forward.setRedirect(true);
				forward.setPath("review.do?page=1");
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
