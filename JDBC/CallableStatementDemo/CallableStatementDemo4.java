import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class CallableStatementDemo4 {
	public static void main(String[] args) throws SQLException {
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2,3
		String sql = "{ call SP_EMP_SELECT(?,?,?) }"; // �ҿ��� sql����
		CallableStatement cstmt = conn.prepareCall(sql);
		// ������, IN mode�� setXXX()�޼ҵ带 ����� ��
		cstmt.setInt(1, 7788);
		cstmt.registerOutParameter(2, Types.VARCHAR); // �̸�
		cstmt.registerOutParameter(3, Types.NUMERIC); // ����
		cstmt.execute(); // ��������, executeUpdate()�� executeQuery()�� �ƴϴ�.

		String ename = cstmt.getString(2);
		double sal = cstmt.getDouble(3);
		
		System.out.println("��� ��ȣ : 7788");
		System.out.println("����� : "+ename);
		System.out.println("����  : "+sal);
		
		if(cstmt!=null)cstmt.close();
		DBClose.close(conn); // 7
	}
}
