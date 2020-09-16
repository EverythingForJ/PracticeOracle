import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.example.libs.DBClose;
import com.example.libs.DBConnection;

public class PreparedStatementDemo {
	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
		System.out.print("부서번호 : ");
		int department_num = scan.nextInt();
		System.out.print("월급 : ");
		double salary = scan.nextDouble();
		
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection();    //2,3
		String sql = "  SELECT  ename, deptno, sal,   dname  " + 
		                  "  FROM     emp  INNER JOIN dept  USING(deptno)  " + 
				          "  WHERE  deptno = ? AND sal >= ?";  //불완전한 SQL 문장.
		PreparedStatement pstmt = conn.prepareStatement(sql);   //아직까지 불완전한 SQL 문장, 문법검사, 개체검사를 한다.  //4
		pstmt.setInt(1, department_num);
		pstmt.setDouble(2, salary);                //완전한 SQL문장
		ResultSet rs = pstmt.executeQuery();                       //5. 매우 주의하자....파라미터로 sql을 전달하지 말자.
		while(rs.next()) {    //6
			String ename = rs.getString("ename");
			int deptno = rs.getInt("deptno");
			double sal = rs.getDouble("sal");
			String dname = rs.getString("dname");
			System.out.println(ename + "\t" + deptno + "\t" + sal + "\t" + dname);
		}
		DBClose.close(conn);                                     //7
	}
}
