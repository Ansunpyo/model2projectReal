package action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.LectureDetailDeleteService;
import vo.ActionForward;

public class LectureDetailDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		int chapter = Integer.parseInt(request.getParameter("chapter"));
		int lecture_num = Integer.parseInt(request.getParameter("lecture_num"));
		LectureDetailDeleteService lectureDetailDeleteService = new LectureDetailDeleteService();		
		boolean isDeleteSuccess = lectureDetailDeleteService.removeArticle(chapter, lecture_num);
			
			if(!isDeleteSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('삭제 실패');history.back();</script>");
				out.close();
			} else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("lectureDetail.do?lecture_num=" + lecture_num);
			}
		
		return forward;
	}

}

