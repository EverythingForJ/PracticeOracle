import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

// 이름,업무,매니저,급여를 입력받아 등록하기

public class CallableStatementDemo3 {
	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
		System.out.print("이름 : "); 
		String ename = scan.next(); 
		System.out.print("업무 : "); 
		String job = scan.next();
		System.out.print("매니저 : "); 
		int mgr = scan.nextInt();
		System.out.print("급여 : "); 
		double sal = scan.nextDouble();
		
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2,3
		String sql = "{ call EMP_INPUT(?,?,?,?) }";
		CallableStatement cstmt = conn.prepareCall(sql);
		cstmt.setString(1, ename);
		cstmt.setString(2, job);
		cstmt.setInt(3, mgr);
		cstmt.setDouble(4, sal); // 4. sql 문장 완성
		int row = cstmt.executeUpdate();
		if(row == 1)System.out.println("입력 성공");
		else System.out.println("입력 실패");
		if(cstmt != null) cstmt.close();
		DBClose.close(conn); // 6
		
	}
}