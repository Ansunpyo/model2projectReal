package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.BannerAction;
import action.BannerDeleteAction;
import action.BannerUploadAction;
import action.CategoryDeleteAction;
import action.CategoryInsertAction;
import action.CategoryListAction;
import action.DeleteLectureAction;
import action.DoPayAction;
import action.EditProfileAction;
import action.EditProfilePageAction;
import action.FaqAction;
import action.FaqDeleteAction;
import action.FaqWriteAction;
import action.FavAddAction;
import action.FavoritesAction;
import action.FavoritesDeleteAction;
import action.FreeBoardAction;
import action.FreeBoardDeleteAction;
import action.FreeBoardUpdateAction;
import action.FreeBoardUpdatePageAction;
import action.FreeBoardViewAction;
import action.FreeBoardWriteAction;
import action.FreeCommentDeleteAction;
import action.FreeCommentExtraAction;
import action.FreeCommentWriteAction;
import action.IntroDeleteProAction;
import action.IntroDetailAction;
import action.IntroListAction;
import action.IntroModifyFormAction;
import action.IntroModifyProAction;
import action.IntroWriteProAction;
import action.JoinAction;
import action.LectureDetailAction;
import action.LectureDetailDeleteAction;
import action.LectureDetailUploadAction;
import action.LectureListAction;
import action.LectureListCheckedAction;
import action.LectureMDAction;
import action.LectureModifyAction;
import action.LectureUploadAction;
import action.LectureUploadPageAction;
import action.LoginAction;
import action.LoginPageAction;
import action.LogoutAction;
import action.MainAction;
import action.MainadAction;
import action.MembersListAction;
import action.MessageDeleteAction;
import action.MessageSendAction;
import action.MessageSendPageAction;
import action.MessengerAction;
import action.MyFreeAction;
import action.MyLectureAction;
import action.MyMessageAction;
import action.MyReviewAction;
import action.NoticeAction;
import action.NoticeDeleteAction;
import action.NoticeUpdateAction;
import action.NoticeUpdatePageAction;
import action.NoticeViewAction;
import action.NoticeWriteAction;
import action.OneOnOneDeleteAction;
import action.OneOnOneListAction;
import action.OneOnOneSendAction;
import action.One_on_oneAnswerAction;
import action.One_on_oneadListAction;
import action.PayPageAction;
import action.PurchaseAllListAction;
import action.PurchaseDoRefundAction;
import action.PurchaseListAction;
import action.PurchaseRefundAction;
import action.PurchaseRefundListAction;
import action.PwCheckAction;
import action.QuitAction;
import action.QuittersListAction;
import action.ReviewAction;
import action.ReviewCommentDeleteAction;
import action.ReviewCommentExtraAction;
import action.ReviewCommentWriteAction;
import action.ReviewDeleteAction;
import action.ReviewUpdateAction;
import action.ReviewUpdatePageAction;
import action.ReviewViewAction;
import action.ReviewWriteAction;
import action.ReviewWritePageAction;
import vo.ActionForward;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;
		
		if(command.contentEquals("/index.do")) {
			forward = new ActionForward();
			forward.setPath("/index.jsp");
		} else if(command.contentEquals("/indexad.do")) {
			forward = new ActionForward();
			forward.setPath("/indexad.jsp");
		} else if(command.contentEquals("/quit.do")) {
			forward = new ActionForward();
			forward.setPath("/quit.jsp");
		} else if(command.contentEquals("/main.do")) {
			action = new MainAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/mainad.do")) {
			action = new MainadAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/login.do")) {
			action = new LoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/loginPage.do")) {
			action = new LoginPageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/join.do")) {
			action = new JoinAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/logout.do")) {
			action = new LogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/editProfilePage.do")) {
			action = new EditProfilePageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/editProfile.do")) {
			action = new EditProfileAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/favorites.do")) {
			action = new FavoritesAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/favoritesDelete.do")) {
			action = new FavoritesDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/doQuit.do")) {
			action = new QuitAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/review.do")) {
			action = new ReviewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/reviewView.do")) {
			action = new ReviewViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/reviewDelete.do")) {
			action = new ReviewDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/reviewUpdatePage.do")) {
			action = new ReviewUpdatePageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/reviewUpdate.do")) {
			action = new ReviewUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/reviewWritePage.do")) {
			action = new ReviewWritePageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/reviewWrite.do")) {
			action = new ReviewWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/myReview.do")) {
			action = new MyReviewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/messenger.do")) {
			action = new MessengerAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/messageDelete.do")) {
			action = new MessageDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/sendMessage.do")) {
			action = new MessageSendAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/myMessage.do")) {
			action = new MyMessageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/one_on_one.do")) {
			action = new OneOnOneListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/one_on_one_submit.do")) {
			action = new OneOnOneSendAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/faq.do")) {
			action = new FaqAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/faqDelete.do")) {
			action = new FaqDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/faqWrite.do")) {
			action = new FaqWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/category.do")) {
			action = new CategoryListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/deleteCategory.do")) {
			action = new CategoryDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/insertCategory.do")) {
			action = new CategoryInsertAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/members.do")) {
			action = new MembersListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/logout.do")) {
			action = new LogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/quitters.do")) {
			action = new QuittersListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/one_on_onead.do")) {
			action = new One_on_oneadListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/one_on_one_answer.do")) {
			action = new One_on_oneAnswerAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/introWriteForm.do")) {
			forward = new ActionForward();
			forward.setPath("/intro/intro_write.jsp");
		} else if (command.equals("/introWritePro.do")) {
			action = new IntroWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/introList.do")) {
			action = new IntroListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/introDetail.do")) {
			action = new IntroDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/introModifyForm.do")) {
			action = new IntroModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/introModifyPro.do")) {
			action = new IntroModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/introDeleteForm.do")) {
			String nowPage = request.getParameter("page");
			request.setAttribute("page", nowPage);
			int intro_num = Integer.parseInt(request.getParameter("intro_num"));
			request.setAttribute("intro_num", intro_num);
			forward = new ActionForward();
			forward.setPath("/intro/intro_delete.jsp");
		} else if (command.equals("/introDeletePro.do")) {
			action = new IntroDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/freeBoard.do")) {
			action = new FreeBoardAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/freeWritePage.do")) {
			forward = new ActionForward();
			forward.setPath("freeBoardWrite.jsp");
		} else if(command.contentEquals("/freeWrite.do")) {
			action = new FreeBoardWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/freeView.do")) {
			action = new FreeBoardViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/freeDelete.do")) {
			action = new FreeBoardDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/freeUpdatePage.do")) {
			action = new FreeBoardUpdatePageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/freeUpdate.do")) {
			action = new FreeBoardUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/myFree.do")) {
			action = new MyFreeAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/notice.do")) {
			action = new NoticeAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/noticeView.do")) {
			action = new NoticeViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/noticeDelete.do")) {
			action = new NoticeDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/noticeUpdatePage.do")) {
			action = new NoticeUpdatePageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/noticeUpdate.do")) {
			action = new NoticeUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/noticeWritePage.do")) {
			forward = new ActionForward();
			forward.setPath("noticeWrite.jsp");
		} else if(command.contentEquals("/noticeWrite.do")) {
			action = new NoticeWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/forgotPw.do")) {
			forward = new ActionForward();
			forward.setPath("forgotPw.jsp");
		} else if(command.contentEquals("/pwCheck.do")) {
			action = new PwCheckAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/banner.do")) {
			action = new BannerAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/deleteBanner.do")) {
			action = new BannerDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/bannerUpload.do")) {
			action = new BannerUploadAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/lectureList.do")) {
			action = new LectureListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/purchaseList.do")) {
			action = new PurchaseListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/purchaseRefund.do")) {
			action = new PurchaseRefundAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/purchaseAllList.do")) {
			action = new PurchaseAllListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/purchaseRefundList.do")) {
			action = new PurchaseRefundListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/purchaseDoRefund.do")) {
			action = new PurchaseDoRefundAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/lectureListChecked.do")) {
			action = new LectureListCheckedAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/myLecture.do")) {
			action = new MyLectureAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/lectureUploadPage.do")) {
			action = new LectureUploadPageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/lectureUpload.do")) {
			action = new LectureUploadAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/lectureDetail.do")) {
			action = new LectureDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/favAdd.do")) {
			action = new FavAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/lectureMD.do")) {
			action = new LectureMDAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/lectureModify.do")) {
			action = new LectureModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/deleteLecture.do")) {
			action = new DeleteLectureAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/oneDel.do")) {
			action = new OneOnOneDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/payPage.do")) {
			action = new PayPageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/doPay.do")) {
			action = new DoPayAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/lectureDetailDelete.do")) {
			action = new LectureDetailDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.contentEquals("/lectureDetailUpload.do")) {
			action = new LectureDetailUploadAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		} else if(command.contentEquals("/freeExtraComment.do")) {
			action = new FreeCommentExtraAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		} else if(command.contentEquals("/deleteFreeComment.do")) {
			action = new FreeCommentDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		} else if(command.contentEquals("/freeCommentWrite.do")) {
			action = new FreeCommentWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		} else if(command.contentEquals("/reviewExtraComment.do")) {
			action = new ReviewCommentExtraAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		} else if(command.contentEquals("/deleteReviewComment.do")) {
			action = new ReviewCommentDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		} else if(command.contentEquals("/reviewCommentWrite.do")) {
			action = new ReviewCommentWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		} else if(command.contentEquals("/messageSend.do")) {
			action = new MessageSendPageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		
		if (forward != null) {
			
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
}
