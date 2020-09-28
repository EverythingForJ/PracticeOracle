import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchDemo {
	public static void main(String[] args) throws SQLException {
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2,3
		
		String sql = "INSERT INTO EMP_TEST(empno, ename, job) VALUES (?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		int [] empnoArray = {1111, 2222,3333, 4444};
		String [] enameArray = {"������", "������", "������", "ȫ����"};
		String [] jobArray = {"Developer","Designer","Marktter", "Salesman"};
		
		for(int i =0; i<empnoArray.length; i++) {
			pstmt.setInt(1, empnoArray[i]);
			pstmt.setString(2, enameArray[i]);
			pstmt.setString(3, jobArray[i]); // ��μ� ������ sql����
			pstmt.addBatch(); // batch�� ��´�.
		}
		
		int [] rows = pstmt.executeBatch();
		if(rows.length == 4) {
			System.out.println(rows.length+"���� �Է� ����");
			for(int i : rows) {System.out.println(i);}
		}else {
			System.out.println("�Է� ����");
		}
		
		System.out.println(rows);
		DBClose.close(conn); // 6
		
	}
}
