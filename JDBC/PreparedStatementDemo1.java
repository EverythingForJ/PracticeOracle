import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.example.libs.DBClose;
import com.example.libs.DBConnection;

public class PreparedStatementDemo1 {
	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
		System.out.print("찾으려는 사원의 이름 : ");
		String name = scan.next().toUpperCase();

		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection();    //2,3
		
		String sql = "  SELECT  ename, dname " + 
		                  "  FROM     emp E INNER JOIN dept D ON (E.deptno=D.deptno)  " + 
				          "  WHERE  ename = UPPER(?) ";  //불완전한 SQL 문장.
		PreparedStatement pstmt = conn.prepareStatement(sql);  //4
		pstmt.setString(1, name); //완전한 SQL문장
		ResultSet rs = pstmt.executeQuery();   //5. 매우 주의하자....파라미터로 sql을 전달하지 말자.
		boolean flag = rs.next();   //6. 굳이 루프 필요없다. 현재 동일한 이름 없어서.
		// 찾았으면 flag는 true, 못찾았다면 false
		if(flag) { // 찾았다면
			String ename = rs.getString("ename");
			String dname = rs.getString("dname");
			System.out.println(ename + "\t" + dname);
		} else { // 못찾았다면
			System.out.println("찾으시는 사원을 못찾았습니다. 다시 실행해 주세요.");
		}
		DBClose.close(conn, pstmt, rs);                                     //7
	}
}
