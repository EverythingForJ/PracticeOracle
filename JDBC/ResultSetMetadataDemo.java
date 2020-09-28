import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class ResultSetMetadataDemo {
	public static void main(String[] args) throws SQLException {
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2,3
		
		Statement stmt = conn.createStatement(); // 4
		ResultSet rs = stmt.executeQuery("SELECT * FROM EMP ");
		ResultSetMetaData rsmd = rs.getMetaData(); // �̾ƿ� ������ ���� �� ��
		int count = rsmd.getColumnCount(); // �÷��� ���� 
		// System.out.println(count);
		
		for(int i=1; i<=count; i++) { // db index 1���� ����
			// System.out.println(rsmd.getColumnLabel(i)); // �÷� ��Ī ���
			// System.out.println(rsmd.getColumnName(i)); // �÷� �̸� ��� (���� ���̷� ��� ���� ���� �� �ִ�.)
			// System.out.println("==========================");
			
			System.out.print(rsmd.getColumnName(i) + ":");
//			switch(rsmd.getColumnType(i)) {
//			case Types.NUMERIC : 
//			case Types.DATE : 
//			case Types.CHAR : 
//			default :  // varchar
//			}
			System.out.print(rsmd.getColumnTypeName(i)); // ������ ������ ���
			System.out.print(" : "+rsmd.getPrecision(i)); // ������
			System.out.println(" : "+rsmd.getScale(i)); // �Ҽ���
		}
		
		DBClose.close(conn);
				
	}
}
