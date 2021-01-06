package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.LectureDetailUploadService;
import vo.ActionForward;
import vo.Lecture;
import vo.Lecture_Video;
import vo.Member;

public class LectureDetailUploadAction implements Action {

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
			
			int lecture_num = Integer.parseInt(request.getParameter("lecture_num"));

			vid.setChapter_title(request.getParameter("chapter_title"));
			vid.setVideo(request.getParameter("video"));
			lec.setLecture_num(lecture_num);
//			vid.setChapter_title(request.getParameter("chapter1"));
			
			LectureDetailUploadService lectureDetailUploadService = new LectureDetailUploadService();
			result = lectureDetailUploadService.lectureDetailUpload(id, lec, vid);
			
			if (result > 0) {
				forward.setPath("lectureDetail.do?lecture_num=" + lecture_num);
				return forward;
			} else {
				forward.setPath("lectureDetail.do?lecture_num=" + lecture_num);
				return forward;
			}
			
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
