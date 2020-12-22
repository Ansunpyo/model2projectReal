package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.FavAddService;
import vo.ActionForward;
import vo.Member;

public class FavAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int result = 0;
		
		if(loginMember != null) {
			forward = new ActionForward();
			String id = loginMember.getId();
			
			int lecture_num = Integer.parseInt(request.getParameter("lecture_num"));
			int page = Integer.parseInt(request.getParameter("page"));
			
			FavAddService favAddService = new FavAddService();
			result = favAddService.addFav(id, lecture_num);
			
			if (result > 0) {
				forward.setRedirect(true);
				forward.setPath("lectureList.do?page=" + page);
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
