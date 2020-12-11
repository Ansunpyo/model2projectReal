package action;

import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.IntroWriteProService;
import vo.ActionForward;
import vo.Intro;
import vo.Member;

public class IntroWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		Intro intro = null;
		int fileSize = 10 * 1920 * 1080;
		String realFolder = "C:/Upload";
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		intro = new Intro(); 
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		String id = null;
		if(loginMember != null) id = loginMember.getId();
		intro.setContents(multi.getParameter("contents"));
		Enumeration<?> images = multi.getFileNames();
		if(images.hasMoreElements())
		intro.setImg1(multi.getOriginalFileName((String)images.nextElement()));
		if(images.hasMoreElements())
		intro.setImg2(multi.getOriginalFileName((String)images.nextElement()));
		if(images.hasMoreElements())
		intro.setImg3(multi.getOriginalFileName((String)images.nextElement()));
		if(images.hasMoreElements())
		intro.setImg4(multi.getOriginalFileName((String)images.nextElement()));
		if(images.hasMoreElements())
		intro.setImg5(multi.getOriginalFileName((String)images.nextElement()));
		if(images.hasMoreElements())
		intro.setImg6(multi.getOriginalFileName((String)images.nextElement()));
		
		IntroWriteProService introWriteProService = new IntroWriteProService();
		boolean isWriteSuccess = introWriteProService.registArticle(id, intro);
		
		if(!isWriteSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패');");
			out.println("history.back();");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("introList.do");
		}
		
		return forward;
	}

}
