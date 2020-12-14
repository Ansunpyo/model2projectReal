package action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.LoginService;
import vo.ActionForward;
import vo.Member;

public class LoginPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		Cookie[] cookieArray = request.getCookies();
		String id = "";
		Member loginMember = null;
		
		if (cookieArray != null) {
			for (int i = 0; i < cookieArray.length; i++) {
				if(cookieArray[i].getName().equals("id")) {
					id = cookieArray[i].getValue();
				}
			}
			LoginService loginService = new LoginService();
			loginMember = loginService.getAutoLogin(id);
		}
		if(loginMember != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", loginMember);
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("index.do");
		} else {
			forward = new ActionForward();
			forward.setPath("login.jsp");
		}
		
		return forward;
	}

}
