import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class CallableStatementDemo {
	public static void main(String[] args) throws SQLException {
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2,3
		String sql = "{ call sp_emp_clone_delete(?) }"; // �ҿ��� sql
		CallableStatement cstmt = conn.prepareCall(sql); // 4
		cstmt.setInt(1, 7788); // ��μ� ������ SQL����
		int row = cstmt.executeUpdate(); // �ſ� ��������. sql�� �Ķ���ͷ� ���� ����. 5
		if(row == 1)System.out.println("���� ����");
		else System.out.println("���� ����");
		DBClose.close(conn); // 6
	}
}
