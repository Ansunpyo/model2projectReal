package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.IntroModifyProService;
import vo.ActionForward;
import vo.Intro;

public class IntroModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		boolean isModifySuccess = false;
		int intro_num = Integer.parseInt(request.getParameter("intro_num"));
		Intro article = new Intro();
		IntroModifyProService introModifyProService = new IntroModifyProService();
		boolean isRightUser = introModifyProService.isArticleWriter(intro_num, request.getParameter("password"));
		if(!isRightUser) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('수정할 권한이 없습니다.');history.back();</script>");
		} else {
			article.setIntro_num(intro_num);
			article.setContents(request.getParameter("contents"));
			article.setImg1(request.getParameter("img1"));
			article.setImg2(request.getParameter("img2"));
			article.setImg3(request.getParameter("img3"));
			article.setImg4(request.getParameter("img4"));
			article.setImg5(request.getParameter("img5"));
			article.setImg6(request.getParameter("img6"));
			isModifySuccess = introModifyProService.modifyArticle(article);
			
			if(!isModifySuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('수정 실패');history.back();</script>");
			} else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("introDetail.do?intro_num=" + article.getIntro_num() + "&page=" + request.getParameter("page"));
			}
		}
		return forward;
	}

}
