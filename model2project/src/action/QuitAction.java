package action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.QuitService;
import vo.ActionForward;
import vo.Member;

public class QuitAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember != null) {
			String id = loginMember.getId();
			QuitService quitService = new QuitService();
			int result = quitService.deleteMember(id);
			
			if(result > 0) {
				session.invalidate();
				Cookie idCookie = new Cookie("id", null);
				idCookie.setMaxAge(0);
				response.addCookie(idCookie);
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("index.do");
				session.invalidate();
				return forward;
			} else {
				forward = new ActionForward();
				forward.setPath("loginPage.do");
				session.invalidate();
				return forward;
			}
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			session.invalidate();
			return forward;
		}
	}

}
