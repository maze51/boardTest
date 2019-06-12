package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board.model.ReplyVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IboardService;
import kr.or.ddit.user.model.UserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/writeReply")
public class WriteReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory
			.getLogger(WriteReplyController.class);
	
	private IboardService boardService;
    
    @Override
    public void init() throws ServletException {
    	boardService = new BoardService();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//logger.debug("WriteReplyController doPost()");
		
		String replyContent = request.getParameter("reply");
		int articleNumber = Integer.parseInt(request.getParameter("aNum"));
		
		HttpSession session = request.getSession();
		UserVO userVo = (UserVO) session.getAttribute("USER_INFO");
		String userId = userVo.getUserId();
		
		ReplyVO replyVo = new ReplyVO();
		replyVo.setReply_user(userId);
		replyVo.setReply_article(articleNumber);
		replyVo.setReply_content(replyContent);
		
		int insertCnt = boardService.writeReply(replyVo);
		
		if(insertCnt==1)
			response.sendRedirect(request.getContextPath()+"/showArticle?aNumber="+articleNumber);
	}

}
