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
		System.out.print("ã������ ����� �̸� : ");
		String name = scan.next().toUpperCase();

		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection();    //2,3
		
		String sql = "  SELECT  ename, dname " + 
		                  "  FROM     emp E INNER JOIN dept D ON (E.deptno=D.deptno)  " + 
				          "  WHERE  ename = UPPER(?) ";  //�ҿ����� SQL ����.
		PreparedStatement pstmt = conn.prepareStatement(sql);  //4
		pstmt.setString(1, name); //������ SQL����
		ResultSet rs = pstmt.executeQuery();   //5. �ſ� ��������....�Ķ���ͷ� sql�� �������� ����.
		boolean flag = rs.next();   //6. ���� ���� �ʿ����. ���� ������ �̸� ���.
		// ã������ flag�� true, ��ã�Ҵٸ� false
		if(flag) { // ã�Ҵٸ�
			String ename = rs.getString("ename");
			String dname = rs.getString("dname");
			System.out.println(ename + "\t" + dname);
		} else { // ��ã�Ҵٸ�
			System.out.println("ã���ô� ����� ��ã�ҽ��ϴ�. �ٽ� ������ �ּ���.");
		}
		DBClose.close(conn, pstmt, rs);                                     //7
	}
}
