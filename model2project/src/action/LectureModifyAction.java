package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.LectureUploadService;
import vo.ActionForward;
import vo.Lecture;
import vo.Lecture_Video;
import vo.Member;

public class LectureModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int result = 0;
		
		if(loginMember != null) {
			forward = new ActionForward();
			Lecture lec = new Lecture();
			int nowPage = Integer.parseInt(request.getParameter("page"));
			
			lec.setLecture_num(Integer.parseInt(request.getParameter("lecture_num")));
			lec.setLecture_title(request.getParameter("lecture_title"));
			lec.setPrice(Integer.parseInt(request.getParameter("price")));
			lec.setSubject_code(Integer.parseInt(request.getParameter("subject")));
			
			LectureUploadService lectureUploadService = new LectureUploadService();
			result = lectureUploadService.lectureModify(lec);
			
			if (result > 0) {
				forward.setRedirect(true);
				forward.setPath("lectureList.do?page=" + nowPage);
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
