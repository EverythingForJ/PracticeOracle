import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

public class CallableStatementDemo10 {
	public static void main(String[] args) throws SQLException{
		Scanner scan = new Scanner(System.in);
		System.out.print("찾으시는 읍/면/동 : ");
		String dongName = scan.next();
		
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2,3
		
		String sql = "{ call SP_ZIPCODE_SELECT(?,?) }";
		CallableStatement cstmt = conn.prepareCall(sql); // 4
		cstmt.setString(1, dongName); // In Mode
		cstmt.registerOutParameter(2, OracleTypes.CURSOR); // Out Mode
		cstmt.execute();
		
		Object obj = cstmt.getObject(2); // Oracle의 cursor가 자바에서는 대상이 없어서 객채로 받음
		if(obj instanceof ResultSet) {
			ResultSet rs = (ResultSet)obj;
			while(rs.next()) {
				String zipcode = rs.getString("zipcode");
				String sido = rs.getString("sido");
				String gugun = rs.getString("gugun");
				String dong = rs.getString("dong");
				String bunji = rs.getString("bunji");
				System.out.println(zipcode + "\t"+sido+ "\t"+gugun+ "\t"+dong+ "\t"+bunji);
			}
			if(rs != null) rs.close();
		}else {
			System.out.println("형변환 불가");
		}
		
		if(cstmt!=null) cstmt.close();
		DBClose.close(conn);
	}
}
