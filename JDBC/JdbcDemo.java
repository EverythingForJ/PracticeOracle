// JDBC�� ����� select ó�� 7 �ܰ�

// 1. import ����.
import java.sql.*;

public class JdbcDemo {
	public static void main(String[] args) {
		// 2. ����Ŭ ����̹��� �޸𸮿� �ε�����
		// �ڹٿ��� ����̺�� Ŭ������. ����Ŭ ����̹��� Ŭ������.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Ŭ������ ã�Ҵ�!");
		} catch (ClassNotFoundException e) {
			System.out.println("Ŭ���� ��ã���̤�");
			// ojdbc8.jar ������ ������� ã������. ������Ʈ ��Ŭ�� -> build path -> Add External Archives �Ŀ� ���� ���� �� ����
			// jar�� ������ Ǯ���ʰ� ����.
		}
		
		// 3. ����Ŭ ����̹��� ���ؼ� �ڹٰ� ����ŬDB�� �����ϵ�������.
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			// �ٸ�������� ����ʹٸ� localhost(or �ڱ� IP)�ڸ��� �׻���� ip�� �ֱ�, enterprize�� orcl, expression�� ex����.
			System.out.println("���� ����!");
		} catch (SQLException e) {
			System.out.println("���� ����..");
		}
		
		// 4. Statement��ü�� ��������.
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			System.out.println("statement��ü ����� ����");
			// 5. SQL �� �ۼ��ؼ� �������� ( ������ �ۼ� )
			String sql = "SELECT empno, ename, sal FROM emp";
			rs = stmt.executeQuery(sql); // select�ۼ��� �׻� excuteQuery�޼ҵ常 ����.
			// select�� �ٱ��ϰ� �ʿ��ϴ�. �����͸� �����;��ϱ⶧����, �� �ٱ��ϸ� ResultSet�̶� �Ѵ�.
			
			// 6. SELECT�� ��� ResultSet ó������. // select �ƴ� �ܿ� 6�� ���� �ʿ����.
			while(rs.next()) {
				int empNo = rs.getInt(1); // ����Ŭ�� index�� 1���� �����ϹǷ� empno�� 1column�� �ִ�.
				String eName = rs.getString("ename"); // �̸����� �о �ȴ�.
				double sal = rs.getDouble(3);
				System.out.printf("%d, %s, %7.2f%n", empNo, eName, sal);
			}
			
		} catch (SQLException e) {
			System.out.println("statement��ü ����� ���� ��");
		}

		// 7. close����. db�� �ݵ�� �ݾƾ��Ѵ�.
		// �ݴ� ������ ���� ���ʷ�
		try{
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}catch(SQLException e) {
			System.out.println("�ݴ°� ����...");
		}
		
	}
}
