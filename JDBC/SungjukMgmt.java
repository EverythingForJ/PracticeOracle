import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.example.libs.DBClose;
import com.example.libs.DBConnection;

public class SungjukMgmt {
	private Scanner scan;
	private Connection conn;
	
	public SungjukMgmt() { // Constructor
		this.scan = new Scanner(System.in);
		DBConnection dbconn = new DBConnection();
		this.conn = dbconn.getConnection();
		System.out.println("��ü�� �����ƽ��ϴ�.");
	}
	
	@Override
	protected void finalize() {
		System.out.println("���α׷��� �̿����ּż� �����մϴ�.");
		DBClose.close(conn);
	}
	
	public static void main(String[] args) throws SQLException {
		SungjukMgmt sm = new SungjukMgmt(); // ������ ȣ��
		while(true) {
			int choice = sm.showMenu();
			if(choice == 9) break;
			if(choice >= 1 && choice <=5) sm.process(choice);
		}
		sm=null; // null �ְ�
		System.gc(); // ������ �÷��� ȣ��
	}
	
	
	private int showMenu() {
		System.out.println("\n9. �����ϱ� \t 1. ��� ���� \t 2. �˻��ϱ� \t 3. �߰��ϱ� \t 4. �����ϱ� \t 5. �����ϱ�");
		System.out.print("����>> ");
		return this.scan.nextInt();
	}

	private void process(int choice) throws SQLException { // 1���� 5������
		switch(choice) {
		case 1 : selectAll();	break;
		case 2 : break;
		case 3 : insert();	break;
		case 4 : update();	break;
		case 5 : break;
		}
	}
	
	
	
	private void update() throws SQLException {
		System.out.print("������ �л��� �й� : ");
		String hakbun = this.scan.next();
		String sql = "SELECT KOR, ENG, MAT FROM STUDENT WHERE HAKBUN = ?";
		
		PreparedStatement pstmt = this.conn.prepareStatement(sql); // 4. �������� �ҿ����� sql����
		pstmt.setString(1, hakbun);
		ResultSet rs = pstmt.executeQuery(); // 5.
		rs.next(); // 6.
		int kor = rs.getInt("kor");
		int eng = rs.getInt("eng");
		int mat = rs.getInt("mat");
		int tot = kor+eng+mat;
		double avg = tot / 3.;
		String grade = (avg >= 90) ? "A":
						(avg >= 80) ? "B":
							(avg >= 70) ? "C":
								(avg >= 60) ? "D" : "F";
		
		if(rs!=null) rs.close();
		if(pstmt!=null) pstmt.close();
		
		sql = "UPDATE STUDENT SET TOT =?, AVG = ?, GRADE = ? WHERE HAKBUN = ?"; // �ҿ����� SQL����
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, tot);
		pstmt.setDouble(2, avg);
		pstmt.setString(3, grade);
		pstmt.setString(4, hakbun);
		
		int row = pstmt.executeUpdate();
		if(row==1)System.out.println("Update Success");
		else System.out.println("Update Failure");
		
		if(pstmt!=null) pstmt.close();
				
	}
	
	
	private void insert() throws SQLException {
		System.out.print("�й� : ");	String hakbun = this.scan.next();
		System.out.print("�̸� : ");	String name = this.scan.next();
		System.out.print("���� : ");	int kor = this.scan.nextInt();
		System.out.print("���� : ");	int eng = this.scan.nextInt();
		System.out.print("���� : ");	int mat = this.scan.nextInt();
		// �ҿ����� SQL�� �����
		
		String sql = " INSERT INTO STUDENT (HAKBUN,NAME,KOR,ENG,MAT) VALUES(?,?,?,?,?) ";
		PreparedStatement pstmt = this.conn.prepareStatement(sql);
		pstmt.setString(1, hakbun);
		pstmt.setString(2, name);
		pstmt.setInt(3, kor);
		pstmt.setInt(4, eng);
		pstmt.setInt(5, mat);
		
		int rows = pstmt.executeUpdate(); // parameter�� sql ��������.
		if(rows==1) System.out.println("�߰� ����");
		else System.out.println("�߰� ����");
		
		if(pstmt!=null)pstmt.close();
	}
	
	private void selectAll() throws SQLException {
		Statement stmt = this.conn.createStatement(); // 4.
		String sql = "SELECT * FROM STUDENT ORDER BY TOT DESC ";
		ResultSet rs = stmt.executeQuery(sql); // 5.
		
		System.out.println("�й�\t�̸� \t����\t����\t����\t����\t���\t����");
		System.out.println("-----------------------------------------------------");
		
		while(rs.next()) {
			String hakbun = rs.getString(1);
			String name = rs.getString(2);
			int kor = rs.getInt(3);
			int eng = rs.getInt(4);
			int mat = rs.getInt(5);
			int tot = rs.getInt(6);
			double avg= rs.getDouble(7);
			String grade = rs.getString(8);
			System.out.println(hakbun+"\t"+name+"\t"+kor+"\t"+eng+"\t"+mat+"\t"+tot+"\t"+avg+"\t"+grade);
		}
		if(rs!=null) rs.close();
		if(stmt!=null) stmt.close();
	}

}