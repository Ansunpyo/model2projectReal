package action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.ActionForward;

public class LogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.invalidate();
		Cookie idCookie = new Cookie("id", null);
		idCookie.setMaxAge(0);
		response.addCookie(idCookie);
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("index.do");
		return forward;
	}

}
