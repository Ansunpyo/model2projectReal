package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.OneDelService;
import vo.ActionForward;
import vo.Member;

public class OneOnOneDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int result = 0;
		
		if(loginMember != null) {
			int one_on_one_num = Integer.parseInt(request.getParameter("one_on_one_num"));
			forward = new ActionForward();
			OneDelService oneDelService = new OneDelService();
			result = oneDelService.deleteOne(one_on_one_num);
			
			if (result > 0) {
				forward.setRedirect(true);
				forward.setPath("one_on_onead.do");
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
