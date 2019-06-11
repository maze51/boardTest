package kr.or.ddit.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.AppendVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IboardService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/downloadFile")
@MultipartConfig(maxFileSize=1024*1024*3, maxRequestSize=1024*1024*15)
public class DownloadFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger logger = LoggerFactory
			.getLogger(DownloadFileController.class);
	
	private IboardService boardService;
    
    @Override
    public void init() throws ServletException {
    	boardService = new BoardService();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String appendId = request.getParameter("aId");
		//logger.debug("aid : {}", appendId);
		
		AppendVO appendVo = boardService.getDownloadFile(appendId);
		
		String savePath = appendVo.getAppend_path();
		
		String orgFileName = appendVo.getAppend_filename();
		
		InputStream in = null;
	    OutputStream os = null;
	    File file = null;
	    boolean skip = false;
	    String client = "";
		
	    try {
	    	file = new File(savePath);
	    	in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			skip = true;
		}
	    
	    client = request.getHeader("User-Agent");
	    
	    response.reset() ;
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Description", "JSP Generated Data");
        
        if(!skip){
            
        	orgFileName = new String(orgFileName.getBytes("utf-8"),"iso-8859-1");
 
            response.setHeader("Content-Disposition", "attachment; filename=\"" + orgFileName + "\"");
            response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
             
            response.setHeader ("Content-Length", ""+file.length() );
       
            os = response.getOutputStream();
            byte b[] = new byte[(int)file.length()];
            int leng = 0;
             
            while( (leng = in.read(b)) > 0 ){
                os.write(b,0,leng);
            }
 
        }else{
            response.setContentType("text/html;charset=UTF-8");
            logger.debug("<script language='javascript'>alert('파일을 찾을 수 없습니다');history.back();</script>");
 
        }
         
        in.close();
        os.close();
	}

}
