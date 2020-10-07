import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

// 사번을 받아 삭제하는 프로시저

public class CallableStatementDemo2 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("삭제하려는 사원의 번호를 입력해주세요 : "); 
		int empno = scan.nextInt(); 
		
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2,3

		CallableStatement cstmt = null;
		
		
		try {
			conn.setAutoCommit(false);
			String sql = "{ call SP_EMP_DELETE(?) }"; // 프로시저 표기법에 주의하자
			cstmt = conn.prepareCall(sql); // 4
			cstmt.setInt(1, empno); // 비로소 완전한 SQL문장
			int row = cstmt.executeUpdate(); // 매우 주의하자. sql을 파라미터로 넣지 말자. 5
			if(row == 1) {
				System.out.println("삭제 성공");
				conn.commit();
			}
			else {
				throw new SQLException("삭제 실패");
			}
		}catch(SQLException e) {
			System.out.println(e);
			try {
				conn.rollback();
			}catch(SQLException ex) {
				System.out.println(ex);
			}
		}finally {
				try {
					if(cstmt != null) cstmt.close();
					DBClose.close(conn); // 6
				} catch (SQLException e) {
					System.out.println(e);
				}
		}
		
	}
}