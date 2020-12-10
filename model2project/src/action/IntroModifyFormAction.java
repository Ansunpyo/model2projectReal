package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.IntroDetailService;
import vo.ActionForward;
import vo.Intro;

public class IntroModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		int intro_num = Integer.parseInt(request.getParameter("intro_num"));
		IntroDetailService introDetailService = new IntroDetailService();
		Intro article = introDetailService.getArticleForModify(intro_num);
		HttpSession session = request.getSession();
		session.setAttribute("article", article);
		forward.setPath("/intro/intro_modify.jsp");
		
		return forward;
	}

}
