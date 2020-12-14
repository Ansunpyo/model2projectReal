package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ReviewUpdatePageService;
import vo.ActionForward;
import vo.Member;
import vo.Review;

public class ReviewUpdatePageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		Review re = null;
		
		if(loginMember != null) {
			forward = new ActionForward();
			int review_num = (int) session.getAttribute("review_num");
			
			ReviewUpdatePageService reviewUpdatePageService = new ReviewUpdatePageService();
			re = reviewUpdatePageService.updateReview(review_num);
			session.setAttribute("re", re);
			if (re != null) {
				forward.setPath("reviewUpdate.jsp");
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
