import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class MetadataDemo1 {
	public static void main(String[] args) throws SQLException{
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection();
		DatabaseMetaData dbmd = conn.getMetaData();
		System.out.println(dbmd.getDatabaseMajorVersion() + "."+dbmd.getDatabaseMinorVersion());
		System.out.println(dbmd.getDatabaseProductName());
		System.out.println(dbmd.getDatabaseProductVersion());
		System.out.println("--------------------------------------");
		System.out.println(dbmd.getDriverMajorVersion());
		System.out.println(dbmd.getDriverMinorVersion());
		System.out.println(dbmd.getDriverName());
		System.out.println(dbmd.getDriverVersion());
		DBClose.close(conn);
	}
}
