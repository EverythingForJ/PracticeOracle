import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

// �̸�,����,�Ŵ���,�޿��� �Է¹޾� ����ϱ�

public class CallableStatementDemo3 {
	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
		System.out.print("�̸� : "); 
		String ename = scan.next(); 
		System.out.print("���� : "); 
		String job = scan.next();
		System.out.print("�Ŵ��� : "); 
		int mgr = scan.nextInt();
		System.out.print("�޿� : "); 
		double sal = scan.nextDouble();
		
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2,3
		String sql = "{ call EMP_INPUT(?,?,?,?) }";
		CallableStatement cstmt = conn.prepareCall(sql);
		cstmt.setString(1, ename);
		cstmt.setString(2, job);
		cstmt.setInt(3, mgr);
		cstmt.setDouble(4, sal); // 4. sql ���� �ϼ�
		int row = cstmt.executeUpdate();
		if(row == 1)System.out.println("�Է� ����");
		else System.out.println("�Է� ����");
		if(cstmt != null) cstmt.close();
		DBClose.close(conn); // 6
		
	}
}