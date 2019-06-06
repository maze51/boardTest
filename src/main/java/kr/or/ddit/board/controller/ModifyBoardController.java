package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IboardService;
import kr.or.ddit.user.model.UserVO;

@WebServlet("/modifyBoard")
public class ModifyBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory
			.getLogger(ModifyBoardController.class);
	
	private IboardService boardService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String boardName = request.getParameter("boardName");
		String useSelect = request.getParameter("useSelect");
		if(useSelect != null && useSelect.equals("사용")){
			useSelect = "1";
		} else if(useSelect != null && useSelect.equals("미사용")){
			useSelect = "0";
		}
		
		HttpSession session = request.getSession();
		String boardId = request.getParameter("boardId");
		
		Map<String, Object> mMap = new HashMap<String, Object>();
		
		mMap.put("boardId", boardId);
		mMap.put("boardName", boardName);
		mMap.put("useSelect", useSelect);
		
		int modifyCnt = boardService.modifyBoard(mMap);
		
		if(modifyCnt == 1){
			response.sendRedirect(request.getContextPath()+"/createBoard");
		}
		
	}

}
