package action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.BannerDeleteService;
import svc.DeleteLectureService;
import vo.ActionForward;
import vo.Member;

public class DeleteLectureAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int result = 0;
		
		if(loginMember != null) {
			int lecture_num = Integer.parseInt(request.getParameter("lecture_num"));
			forward = new ActionForward();
			DeleteLectureService deleteLectureService = new DeleteLectureService();
			result = deleteLectureService.deleteLecture(lecture_num);
			
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
