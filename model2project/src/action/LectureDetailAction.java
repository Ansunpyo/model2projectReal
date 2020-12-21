package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.LectureLastPageService;
import svc.LectureListService;
import svc.SubjectListService;
import vo.ActionForward;
import vo.Member;
import vo.Subject;

public class LectureDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		if(loginMember != null) {
			forward = new ActionForward();
			int lecture_num = Integer.parseInt(request.getParameter("lecture_num"));
			
			LectureDetailListService lectureDetailListService = new LectureDetailListService();
			LinkedList<Lecture_video> vidList = lectureDetailListService.getVid(lecture_num);
			//챕터(순서대로) -> 유튜브주소랑 챕터타이틀 담아오기
			session.setAttribute("vidList", vidList);
			forward.setPath("주소?lecture_num=" + lecture_num);
			return forward;
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
