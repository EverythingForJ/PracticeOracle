import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.example.libs.DBClose;
import com.example.libs.DBConnection;

public class InsertDemo1 {
	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
		System.out.print(" ��� ��ȣ : ");	int empno = scan.nextInt();
		System.out.print(" ����� �̸� : ");	String ename = scan.next();
		System.out.print(" ���� : ");	String job = scan.next();
		
		DBConnection dbconn = new DBConnection();
		Connection conn= dbconn.getConnection(); // 2, 3
		// select�� 7�ܰ�, �������� ��� 6�ܰ�

		String sql = " INSERT INTO EMP_COPY(EMPNO,ENAME, JOB, HIREDATE ) " + 
					" VALUES (?, ?, ?, SYSDATE) "; // �ҿ��� sql����
		
		PreparedStatement pstmt = conn.prepareStatement(sql); // 4. �����˻�, ��ü�˻�
		pstmt.setInt(1, empno);
		pstmt.setString(2, ename);
		pstmt.setString(3, job); // ������ sql���� �ϼ�
		
		int count = pstmt.executeUpdate(); // 5. �̹� ������ sql�� ��������
		if(count==1) System.out.println("Insert Success");
		else System.out.println("Insert Failure");
		
		DBClose.close(conn, pstmt); // 6
	}
}
