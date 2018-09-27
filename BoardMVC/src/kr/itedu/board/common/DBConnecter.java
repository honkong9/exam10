package kr.itedu.board.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnecter {

	
		public static Connection getConn() throws SQLException {
			Connection conn = null;
			try {
				Context init = new InitialContext();
				DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
				conn = ds.getConnection();
			
			}catch (NamingException e) {
				e.printStackTrace();
			}
			return conn;
		}
		
		public static void close ( Connection conn, PreparedStatement ps, ResultSet rs) {
			if(conn!=null) {
				try { conn.close(); } catch (Exception e) {}
			}
			if(ps!=null) {
				try { ps.close(); } catch (Exception e) {}
			}
			if(rs!=null) {
				try { rs.close(); } catch (Exception e) {}
			}
		}
		public static void close( Connection conn, PreparedStatement ps) {
			close(conn, ps, null);
		}
}
