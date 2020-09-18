import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.example.libs.DBClose;
import com.example.libs.DBConnection;

public class InsertDemo1 {
	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
		System.out.print(" 사원 번호 : ");	int empno = scan.nextInt();
		System.out.print(" 사원의 이름 : ");	String ename = scan.next();
		System.out.print(" 직무 : ");	String job = scan.next();
		
		DBConnection dbconn = new DBConnection();
		Connection conn= dbconn.getConnection(); // 2, 3
		// select만 7단계, 나머지는 모두 6단계

		String sql = " INSERT INTO EMP_COPY(EMPNO,ENAME, JOB, HIREDATE ) " + 
					" VALUES (?, ?, ?, SYSDATE) "; // 불완전 sql문장
		
		PreparedStatement pstmt = conn.prepareStatement(sql); // 4. 문법검사, 개체검사
		pstmt.setInt(1, empno);
		pstmt.setString(2, ename);
		pstmt.setString(3, job); // 완전한 sql문장 완성
		
		int count = pstmt.executeUpdate(); // 5. 이미 완전한 sql을 넣지말기
		if(count==1) System.out.println("Insert Success");
		else System.out.println("Insert Failure");
		
		DBClose.close(conn, pstmt); // 6
	}
}
