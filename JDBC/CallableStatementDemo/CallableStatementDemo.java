import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class CallableStatementDemo {
	public static void main(String[] args) throws SQLException {
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2,3
		String sql = "{ call sp_emp_clone_delete(?) }"; // 불완전 sql
		CallableStatement cstmt = conn.prepareCall(sql); // 4
		cstmt.setInt(1, 7788); // 비로소 완전한 SQL문장
		int row = cstmt.executeUpdate(); // 매우 주의하자. sql을 파라미터로 넣지 말자. 5
		if(row == 1)System.out.println("삭제 성공");
		else System.out.println("삭제 실패");
		DBClose.close(conn); // 6
	}
}
