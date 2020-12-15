package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.IntroDetailService;
import vo.ActionForward;
import vo.Intro;
import vo.Member;

public class IntroDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int intro_num = Integer.parseInt(request.getParameter("intro_num"));
		String page = request.getParameter("page");
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		IntroDetailService introDetailService = new IntroDetailService();
		ArrayList[] articleList = introDetailService.getArticle(intro_num);
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
		session.setAttribute("articleList", articleList);
		forward.setPath("/intro/intro_view.jsp");
		return forward;
	}

}
