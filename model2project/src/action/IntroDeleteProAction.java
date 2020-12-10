package action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.IntroDeleteProService;
import vo.ActionForward;

public class IntroDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		int intro_num = Integer.parseInt(request.getParameter("intro_num"));
		String nowPage = request.getParameter("page");
		IntroDeleteProService introDeleteProService = new IntroDeleteProService();
		boolean isArticleWriter = introDeleteProService.isArticleWriter(intro_num, request.getParameter("password"));
		if(!isArticleWriter) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('삭제할 권한이 없습니다');history.back();</script>");
			out.close();
		} else {
			boolean isDeleteSuccess = introDeleteProService.removeArticle(intro_num);
			
			if(!isDeleteSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('삭제 실패');history.back();</script>");
				out.close();
			} else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("introList.do?page=" + nowPage);
			}
		}
		return forward;
	}

}
