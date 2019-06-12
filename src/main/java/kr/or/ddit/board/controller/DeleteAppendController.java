package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IboardService;

@WebServlet("/deleteAppend")
public class DeleteAppendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
private IboardService boardService;
    
    @Override
    public void init() throws ServletException {
    	boardService = new BoardService();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String appendId = request.getParameter("appendId");
		int aNum = Integer.parseInt(request.getParameter("aNumber"));
		
		int deleteCnt = boardService.deleteAppend(appendId);
		
		if(deleteCnt == 1)
			response.sendRedirect(request.getContextPath()+"/modifyArticle?arNum="+aNum);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
