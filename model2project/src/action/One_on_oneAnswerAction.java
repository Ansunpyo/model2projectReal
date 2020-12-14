package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.OneOnOneAnswerService;
import vo.ActionForward;
import vo.Member;
import vo.One_On_One;

public class One_on_oneAnswerAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int result = 0;
		
		if(loginMember != null) {
			forward = new ActionForward();
			One_On_One one = new One_On_One();
			
			one.setAnswer(request.getParameter("answer"));
			one.setOne_on_one_num(Integer.parseInt(request.getParameter("one_on_one_num")));
			
			OneOnOneAnswerService oneOnOneAnswerService = new OneOnOneAnswerService();
			result = oneOnOneAnswerService.oneOnOneAnswer(one);
			
			if (result > 0) {
				forward.setRedirect(true);
				forward.setPath("one_on_onead.do?page=1");
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
