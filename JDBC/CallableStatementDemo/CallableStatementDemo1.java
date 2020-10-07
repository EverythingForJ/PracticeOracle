import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class CallableStatementDemo1 {
	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
		System.out.print("사원 번호 : "); 
		int empno = scan.nextInt(); // 7788
		System.out.print("봉급 : ");
		double sal = scan.nextDouble(); // 8000
		
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2,3
		String sql = "{ call EMP_SAL_UPDATE(?,?) }"; // 불완전 sql
		CallableStatement cstmt = conn.prepareCall(sql); // 4
		cstmt.setInt(1, empno);
		cstmt.setDouble(2, sal); // 비로소 완전한 SQL문장
		int row = cstmt.executeUpdate(); // 매우 주의하자. sql을 파라미터로 넣지 말자. 5
		if(row == 1)System.out.println("수정 성공");
		else System.out.println("수정 실패");
		if(cstmt != null) cstmt.close();
		DBClose.close(conn); // 6
	}
}
