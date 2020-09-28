import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class TclDemo {
	public static void main(String[] args) {
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2,3
//		System.out.println(conn);
//		System.out.println("auto commit 상태 : "+conn.getAutoCommit());
		
		Statement stmt = null;
		Savepoint sp = null;
		
		try{
			conn.setAutoCommit(false); // commit과 rollback을 명시적으로 사용하기 위해서
			stmt = conn.createStatement(); // 4
			String sql = "DELETE FROM EMP WHERE EMPNO = 7777";
			int row = stmt.executeUpdate(sql); // 5.
			if(row==1) {
				System.out.println("삭제 성공");
				// sp = conn.setSavepoint("mysavepoint");
				conn.commit(); // 데이터베이스에 영구히 반영하기
			}else {
				throw new SQLException("삭제 실패");
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			try {
				conn.rollback();
				// conn.rollback(sp);
			} catch (SQLException e1) {
				System.out.println(e1);
			}
		}
		
		DBClose.close(conn); // 6
		
	}
}
