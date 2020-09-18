import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.example.libs.DBClose;
import com.example.libs.DBConnection;

public class UpdateDemo1 {
	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
		System.out.print(" 수정할 사원의 이름 : ");
		String ename = scan.next();
		System.out.print(" 수정할 직무 : ");
		String job = scan.next();
		System.out.print(" 수정할 월급 : ");
		double sal = scan.nextDouble();
		
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2, 3
		
		String sql = " UPDATE EMP_COPY " +
					" SET job = ?, sal = ? " +
					" WHERE ename = ? ";
		
		PreparedStatement pstmt = conn.prepareStatement(sql); // 4
		
		pstmt.setString(1, job);
		pstmt.setDouble(2, sal);
		pstmt.setString(3, ename);
		
		int rows = pstmt.executeUpdate(); // 5
		if(rows == 1) System.out.println("Update Success");
		else System.out.println("Update Failure");
		DBClose.close(conn, pstmt); // 6
	}
}