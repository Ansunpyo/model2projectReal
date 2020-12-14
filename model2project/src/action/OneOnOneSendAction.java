package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.OneOnOneSendService;
import vo.ActionForward;
import vo.Member;
import vo.One_On_One;

public class OneOnOneSendAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int result = 0;
		
		if(loginMember != null) {
			forward = new ActionForward();
			String id = loginMember.getId();
			One_On_One one = new One_On_One();
			
			one.setTitle(request.getParameter("title"));
			one.setContents(request.getParameter("contents"));
			
			OneOnOneSendService oneOnOneSendService = new OneOnOneSendService();
			result = oneOnOneSendService.sendOneOnOne(id, one);
			
			if (result > 0) {
				forward.setRedirect(true);
				forward.setPath("one_on_one.do");
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
