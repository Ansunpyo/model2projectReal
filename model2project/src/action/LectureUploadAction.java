package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.LectureUploadService;
import vo.ActionForward;
import vo.Lecture;
import vo.Lecture_Video;
import vo.Member;

public class LectureUploadAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int result = 0;
		
		if(loginMember != null) {
			forward = new ActionForward();
			String id = loginMember.getId();
			Lecture lec = new Lecture();
			Lecture_Video vid = new Lecture_Video();
			
			lec.setLecture_title(request.getParameter("lecture_title"));
			lec.setPrice(Integer.parseInt(request.getParameter("price")));
			lec.setSubject_code(Integer.parseInt(request.getParameter("subject")));
			vid.setVideo(request.getParameter("video"));
			vid.setChapter_title(request.getParameter("chapter1"));
			
			LectureUploadService lectureUploadService = new LectureUploadService();
			result = lectureUploadService.lectureUpload(id, lec, vid);
			
			if (result > 0) {
				forward.setRedirect(true);
				forward.setPath("lectureList.do?page=1");
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
