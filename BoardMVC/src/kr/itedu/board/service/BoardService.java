package kr.itedu.board.service;

import java.util.ArrayList;

import kr.itedu.board.BoardVO;
import kr.itedu.board.common.BoardDAO;

public class BoardService {
	public ArrayList<BoardVO> getBoardList(int btype) {
		ArrayList<BoardVO> data = null;
		BoardDAO dao = BoardDAO.getInstance();
		data = dao.getAllBoardList(btype);
		return data;
	}
	public BoardVO getBoardDetail(int bid, int btype) {
		BoardVO data= null;
		BoardDAO dao = BoardDAO.getInstance();
		data = dao.getBoardList(bid, btype);
		return data;
	}

}

