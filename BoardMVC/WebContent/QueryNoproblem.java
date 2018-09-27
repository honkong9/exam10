package kr.hkit.exam09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryNoproblem {
	//Connection 객체를 얻어오는 메소드
	public static Connection getConn() throws SQLException, ClassNotFoundException {
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "hr";
		String pw = "hkitedu";

		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url, id, pw);
		return conn;		
	}	
	
	//리소스를 해제하는 메소드
	public static void close(Connection c, PreparedStatement p, ResultSet r) {
		if(r != null) {
			try { r.close(); } catch(Exception e) {}
		}
		
		if(p != null) {
			try { p.close(); } catch(Exception e) {}
		}
		
		if(c != null) {
			try { c.close(); } catch(Exception e) {}
		}
	}
	
	//tb_board 테이블을 생성하는 메소드
	public static int createTable() {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = getConn();
			String query = " create table tb_board( "
					+ " bid number,"
					+ " btitle nvarchar2(50),"
					+ " bcontent nvarchar2(200),"
					+ " bregdate date default sysdate,"
					+ " primary key(bid) "
					+ " ) ";
			ps = conn.prepareStatement(query);
			result = ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			close(conn, ps, null);
		}
		return result;
	}
	
	//tb_board 테이블을 제거하는 메소드
	public static int dropTable() {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = getConn();
			String query = " drop table tb_board ";					
			ps = conn.prepareStatement(query);
			result = ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			close(conn, ps, null);
		}
		return result;
	}
	
	//데이터 1행을 삭제하거나 전부 삭제하는 메소드
	public static int boardDelete(int bid) {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConn();
			String query = " delete from tb_board ";
			
			if(bid > 0) {
				query += " where bid = " + bid;
			}
			
			ps = conn.prepareStatement(query);			
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			close(conn, ps, rs);	
		}
		return result;
	}
	
	//tb_board 테이블의 모든 행 정보 가져오는 메소드
	public static ArrayList<BoardVO> getAllBoardList() {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConn();
			String query = " select " + 
					" bid, btitle, " + 
					" to_char(bregdate, 'YYYY-MM-DD hh24:mi') as bregdate " + 
					" from tb_board ORDER BY bid desc ";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				int bid = rs.getInt("bid");
				String btitle = rs.getString("btitle");
				String bregdate = rs.getString("bregdate");
				BoardVO bv = new BoardVO();
				bv.setBid(bid);
				bv.setBtitle(btitle);
				bv.setBregdate(bregdate);				
				list.add(bv);
			}			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);	
		}
		return list;
	}
	
	//tb_board 테이블의 1행 정보를 가져오는 메소드
	public static BoardVO getBoardDetail(int bid) {
		BoardVO result = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConn();
			String query = " select " + 
					" bid, btitle, bcontent, " + 
					" to_char(bregdate, 'YYYY-MM-DD hh24:mi') as bregdate " + 
					" from tb_board where bid = ? ";
			ps = conn.prepareStatement(query);
			ps.setInt(1, bid);
			rs = ps.executeQuery();
			if(rs.next()) {				
				result = new BoardVO();
				String btitle = rs.getString("btitle");
				String bregdate = rs.getString("bregdate");
				String bcontent = rs.getString("bcontent");
				
				result.setBid(bid);
				result.setBtitle(btitle);
				result.setBcontent(bcontent);
				result.setBregdate(bregdate);
			}			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);	
		}
		return result;
	}
	
	//tb_board 테이블의 1행 정보를 수정하는 메소드
	public static int boardUpdate(BoardVO bv) {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConn();
			String query = " update tb_board " + 
					" set btitle = ?, " + 
					" bcontent = ? " +
					" where bid = ? ";
			ps = conn.prepareStatement(query);
			ps.setString(1, bv.getBtitle());
			ps.setString(2, bv.getBcontent());
			ps.setInt(3, bv.getBid());
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			close(conn, ps, rs);	
		}
		return result;
	}
	
	//tb_board 테이블에 1행 정보를 삽입하는 메소드
	public static int boardInsert(BoardVO bv) {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConn();
			String query = " insert into tb_board " + 
					" (bid, btitle, bcontent) " + 
					" values " +
					" ((select nvl(max(bid), 0) + 1 from tb_board), ?, ?) ";
			ps = conn.prepareStatement(query);
			ps.setString(1, bv.getBtitle());
			ps.setString(2, bv.getBcontent());
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			close(conn, ps, rs);	
		}
		return result;
	}
	
}
