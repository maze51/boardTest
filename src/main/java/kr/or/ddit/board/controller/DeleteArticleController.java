package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IboardService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/deleteArticle")
public class DeleteArticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory
			.getLogger(DeleteArticleController.class);
       
	private IboardService boardService;
    
    @Override
    public void init() throws ServletException {
    	boardService = new BoardService();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("DeleteArticleController doGet()");
		
		int articleNumber = Integer.parseInt(request.getParameter("articleNum"));
		
		int deleteCnt = boardService.deleteArticle(articleNumber);
		
		if(deleteCnt == 1){
			logger.debug("삭제완료");
			HttpSession session = request.getSession();
			request.setAttribute("boardId", session.getAttribute("boardId"));
			
			request.getRequestDispatcher("/showBoard").forward(request,response);
			//response.sendRedirect(request.getContextPath()+"/showBoard");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
