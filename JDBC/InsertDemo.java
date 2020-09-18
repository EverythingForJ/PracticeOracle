import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.libs.DBClose;
import com.example.libs.DBConnection;

public class InsertDemo {
	public static void main(String[] args) throws SQLException {
		DBConnection dbconn = new DBConnection();
		Connection conn= dbconn.getConnection(); // 2, 3
		//System.out.println(conn);
		// select�� 7�ܰ�, �������� ��� 6�ܰ�
		
		Statement stmt = conn.createStatement(); // 4
//		String sql = " INSERT INTO EMP_COPY(EMPNO,ENAME, JOB, HIREDATE )" + 
//					" VALUES (1111, 'CHULSU', 'DEVELOPER', SYSDATE) ";

		int empno = 2222;
		String ename = "YOUNGHEE";
		String job = "Designer";
		String sql = " INSERT INTO EMP_COPY(EMPNO,ENAME, JOB, HIREDATE )" + 
				" VALUES ("+empno+", '"+ename+"', '"+job+"', SYSDATE)";
		
		// ResultSet rs = stmt.executeQuery(sql); // 5. ���� select������ ���
		
		// 5. select�� ������ ������ sql�� executeUpdate()�� ����Ѵ�.
		// executeUpdate()�� int ��ȯ
		int count = stmt.executeUpdate(sql);
		// �̶� ��ȯ���� count�� �����ͺ��̽����� DML�� ���� ������ ���� row�� �����̴�.
		// ���� 0�̸� INSERT�� �ȵƴٴ� ��, 1�̸� ���������� ó���ƴٴ� ��
		if(count==1) System.out.println("Insert Success");
		else System.out.println("Insert Failure");
		
		DBClose.close(conn, stmt); // 6
	}
}
