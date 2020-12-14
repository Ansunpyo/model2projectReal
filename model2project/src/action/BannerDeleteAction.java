package action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.BannerDeleteService;
import vo.ActionForward;
import vo.Member;

public class BannerDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int result = 0;
		
		if(loginMember != null) {
			String img = request.getParameter("img");
			forward = new ActionForward();
			BannerDeleteService bannerDeleteService = new BannerDeleteService();
			result = bannerDeleteService.deleteBanner(img);
			
			if (result > 0) {
				String realPath = session.getServletContext().getRealPath("./banner/");
				File f = new File(realPath + img);
				if(f.exists()) {
					f.delete();
				}
				forward.setRedirect(true);
				forward.setPath("banner.do");
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
