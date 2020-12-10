package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.FavoritesDeleteService;
import svc.FavoritesListService;
import vo.ActionForward;
import vo.Member;

public class FavoritesDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		if(loginMember != null) {
			String id = loginMember.getId();
			String lecture_num = request.getParameter("deleteThis");
			forward = new ActionForward();
			FavoritesDeleteService favoritesDeleteService = new FavoritesDeleteService();
			int result = favoritesDeleteService.deleteFavorites(id, lecture_num);
			if(result > 0) {
				forward.setPath("favorites.do");
				return forward;
			} else {
				forward = new ActionForward();
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
