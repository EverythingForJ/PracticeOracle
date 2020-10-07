import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

// ����� �޾� �����ϴ� ���ν���

public class CallableStatementDemo2 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("�����Ϸ��� ����� ��ȣ�� �Է����ּ��� : "); 
		int empno = scan.nextInt(); 
		
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2,3

		CallableStatement cstmt = null;
		
		
		try {
			conn.setAutoCommit(false);
			String sql = "{ call SP_EMP_DELETE(?) }"; // ���ν��� ǥ����� ��������
			cstmt = conn.prepareCall(sql); // 4
			cstmt.setInt(1, empno); // ��μ� ������ SQL����
			int row = cstmt.executeUpdate(); // �ſ� ��������. sql�� �Ķ���ͷ� ���� ����. 5
			if(row == 1) {
				System.out.println("���� ����");
				conn.commit();
			}
			else {
				throw new SQLException("���� ����");
			}
		}catch(SQLException e) {
			System.out.println(e);
			try {
				conn.rollback();
			}catch(SQLException ex) {
				System.out.println(ex);
			}
		}finally {
				try {
					if(cstmt != null) cstmt.close();
					DBClose.close(conn); // 6
				} catch (SQLException e) {
					System.out.println(e);
				}
		}
		
	}
}