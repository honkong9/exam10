package kr.itedu.board.common;

import static kr.itedu.board.common.DBConnecter.getConn;
import static kr.itedu.board.common.DBConnecter.close;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kr.itedu.board.BoardVO;



public class BoardDAO {
	
	private static BoardDAO dao;
	
	private BoardDAO() {}
	
	public static BoardDAO getInstance() {
		if(dao==null) {
			dao = new BoardDAO();
		}
		return dao;
	}
	
	public ArrayList<BoardVO> getAllBoardList(int btype) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			conn=getConn();
			String query = " select " + 
					" bid, btitle, bcontent, " +
					" to_char(bregdate, 'YYYY-MM-DD hh24:mi') as bregdate "+
					" from t_board" +btype+
					" ORDER BY bid desc ";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				BoardVO vo=new BoardVO();
				vo.setBid(rs.getInt("bid"));
				vo.setBtitle(rs.getString("btitle"));
				vo.setBcontent(rs.getString("bcontent"));
				vo.setBregdate(rs.getString("bregdate"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return list;
	}
	public BoardVO getBoardList(int bid, int btype) {
		BoardVO vo = new BoardVO();
		try {
			Connection conn = DBConnecter.getConn();
			String query = " select " + 
					" btitle, bcontent ,bregdate, editdate, " +
					" to_char(bregdate, 'YYYY-MM-DD hh24:mi') as bregdate "+
					" from t_board"+btype+
					" where bid = " +bid;
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {	
				vo.setBcontent(rs.getString("bcontent"));
				vo.setBtitle(rs.getString("btitle"));
				vo.setBregdate(rs.getString("bregdate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
}
