import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class CallableStatementDemo1 {
	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
		System.out.print("��� ��ȣ : "); 
		int empno = scan.nextInt(); // 7788
		System.out.print("���� : ");
		double sal = scan.nextDouble(); // 8000
		
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2,3
		String sql = "{ call EMP_SAL_UPDATE(?,?) }"; // �ҿ��� sql
		CallableStatement cstmt = conn.prepareCall(sql); // 4
		cstmt.setInt(1, empno);
		cstmt.setDouble(2, sal); // ��μ� ������ SQL����
		int row = cstmt.executeUpdate(); // �ſ� ��������. sql�� �Ķ���ͷ� ���� ����. 5
		if(row == 1)System.out.println("���� ����");
		else System.out.println("���� ����");
		if(cstmt != null) cstmt.close();
		DBClose.close(conn); // 6
	}
}
