package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IboardService;
import kr.or.ddit.user.model.UserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/createBoard")
public class CreateBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory
			.getLogger(CreateBoardController.class);
	
	private IboardService boardService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/board/createBoard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String boardName = request.getParameter("boardName");
		String useSelect = request.getParameter("useSelect");
		
		HttpSession session = request.getSession();
		UserVO userVo = (UserVO) session.getAttribute("USER_INFO");
		String userId = userVo.getUserId(); 
		logger.debug("select : {}", useSelect);
		
		Map<String, Object> cMap = new HashMap<String, Object>();
		
		cMap.put("userId", userId);
		cMap.put("boardName", boardName);
		cMap.put("useSelect", useSelect);
		
		int insertCnt = boardService.createBoard(cMap);
		List<BoardVO> boardList = boardService.showBoardList();
		
		request.getServletContext().setAttribute("BOARD_LIST", boardList);
		
		if(insertCnt == 1){
			response.sendRedirect(request.getContextPath()+"/createBoard");
		}

	}
}
