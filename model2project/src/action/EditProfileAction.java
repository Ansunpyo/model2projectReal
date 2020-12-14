package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.EditProfileService;
import vo.ActionForward;

public class EditProfileAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String major = request.getParameter("major");
		String education = request.getParameter("education");
		
		EditProfileService editProfileService = new EditProfileService();
		int result = editProfileService.doEdit(id, pw, name, email, gender, major, education);
		
		if(result > 0) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("index.do");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('실패');history.back();</script>");
		}
		return forward;
	}

}
