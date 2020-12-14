package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.FavoritesListService;
import vo.ActionForward;
import vo.Member;

public class FavoritesAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		if(loginMember != null) {
			String id = loginMember.getId();
			forward = new ActionForward();
			FavoritesListService favoritesListService = new FavoritesListService();
			LinkedList[] favorList = favoritesListService.getFavoritesList(id);
			session.setAttribute("favoritesList", favorList);
			forward.setPath("favorites.jsp");
			return forward;
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
