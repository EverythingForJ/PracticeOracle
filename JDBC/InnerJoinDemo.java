import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.libs.DBClose;
import com.example.libs.DBConnection;

public class InnerJoinDemo {
	public static void main(String[] args) throws SQLException{
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection();    //2,3
					
		// 3�� �̻��� ���̺� ǥ�� � ����
		String sql = " SELECT EMPLOYEE_ID, FIRST_NAME, HIRE_DATE, E.DEPARTMENT_ID, DEPARTMENT_NAME, CITY, STATE_PROVINCE, COUNTRY_NAME " +
					" FROM EMPLOYEES E INNER JOIN DEPARTMENTS D ON E.DEPARTMENT_ID=D.DEPARTMENT_ID " +
					" INNER JOIN LOCATIONS LO ON D.LOCATION_ID = LO.LOCATION_ID " +
					" INNER JOIN COUNTRIES CO ON LO.COUNTRY_ID = CO.COUNTRY_ID " +
					" WHERE E.DEPARTMENT_ID IN (?,?,?,?)"; // �ҿ����� SQL����
		
		PreparedStatement pstmt = conn.prepareStatement(sql);  //4. ������ �ҿ����� SQL������ ������ ���⼭ ���� �˻�, ��ü�˻縦 �����Ѵ�.
		pstmt.setInt(1, 10);
		pstmt.setInt(2, 20);
		pstmt.setInt(3, 30);
		pstmt.setInt(4, 40); //������ SQL����
		ResultSet rs = pstmt.executeQuery();   //5. �ſ� ��������....�Ķ���ͷ� sql�� �������� ����.
		
		int count = 0;
		while(rs.next()) { // 6
			int employees_id = rs.getInt(1);
			String department_name = rs.getString(5);
			String city = rs.getString("CITY");
			String country_name = rs.getString("COUNTRY_NAME");
			System.out.println(++count+" : "+employees_id+"\t"+department_name+"\t"+city+"\t"+country_name);
		}
		
		DBClose.close(conn, pstmt, rs); // 7
	}
}
