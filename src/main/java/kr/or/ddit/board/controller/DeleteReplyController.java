package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IboardService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/deleteReply")
public class DeleteReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory
			.getLogger(DeleteReplyController.class);
    
	private IboardService boardService;
    
    @Override
    public void init() throws ServletException {
    	boardService = new BoardService();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("DeleteReplyController doGet()");
		
		String replyId = request.getParameter("replyId");
		logger.debug("replyId : {}", replyId);
		int articleNumber = Integer.parseInt(request.getParameter("aNumber"));
		logger.debug("anumber : {}", articleNumber);
		
		int deleteCnt = boardService.deleteReply(replyId);
		
		if(deleteCnt == 1){
			logger.debug("댓글삭제완료");
			response.sendRedirect(request.getContextPath()+"/showArticle?aNumber="+articleNumber);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("DeleteReplyController doPost()");
	}

}
