import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class CallableStatementDemo5 {
	public static void main(String[] args) throws SQLException {
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2,3
		String sql = "{ call SP_EMP_COUNT(?) }"; // �ҿ��� sql����
		CallableStatement cstmt = conn.prepareCall(sql); // 4
		// ������, IN mode�� setXXX()�޼ҵ带 ����� ��
		// OUT�� �Ķ���ʹ� registerOutParameter()�� ���
		cstmt.registerOutParameter(1, Types.NUMERIC); // ����
		cstmt.execute(); // 5. ��������, executeUpdate()�� executeQuery()�� �ƴϴ�.
		
		int count = cstmt.getInt(1);
		
		System.out.println("�� ȸ���� ��� ����   "+count+"�� �Դϴ�.");
		
		if(cstmt!=null)cstmt.close();
		DBClose.close(conn); // 7
	}
}
