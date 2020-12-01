/* 
 * Ajou University 2020-2 Data Base Team Project 
 * Jinyoun Song, Hyungeong Park, SeungHun Han
 * ������� ������ ������ ��� ���� ��å �� ä�� ��� ����� �����ϴ� ���� ���� 
 * JobForYou(�����)
 */

package JobForYou;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.Scanner;

import org.apache.*;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;



public class JobForYou {
	
	int flag = 1;
	static Connection conn;
	static Statement stm;
	static String email = null;
	
	static int areaCode = 0;
	static String interestCode = null;
    
	public static int menu() //Show Menu List
	{
		System.out.println("\nJobforYou ���񽺴� û�������å ������ ä����� ������ �����ϰ� �ֽ��ϴ�.\n"
				+ "���� ���� ���� ��û�� �����Ͻø�, ���Բ� ���� ������ ���������� ���Ϸ� ������ �帳�ϴ�.\n"
				+ "�̿��Ͻ� ���� ��ȣ�� �Է����ּ���.\n(ex. �̿��Ͻ� ���񽺰� '��å'�̸�, 1�� �Է����ּ���.)\n"
				+ "1. ��å ���� ����\n"
				+ "2. ä�� ��� ���� ����\n"
				+ "3. ���� ���� ���� ��û\n" 
				+ "4. ���� ����");
		Scanner scan = new Scanner(System.in);
		int sltNum = scan.nextInt();
		return sltNum;
	}
	
	public static int CorQ() // Continue or Quit
	{
		System.out.println("\n�ٸ� ���񽺸� �̿��Ͻðڽ��ϱ�? ���ϴ� ��ȣ�� �Է����ּ���!\n"
				+ "1. ���� ���� ������\n"
				+ "2. ���� ����");
		Scanner scan = new Scanner(System.in);
		int CorQ = scan.nextInt();
		return CorQ;
	}
	
	public static void Quit() //Quit Service Function, Print Quit message.
	{
		System.out.println("\nJobForYou�� �̿��� �ּż� �����մϴ�.\n"
				+ "���Ͻô� ������ �����̱⸦ �ٶ��ϴ�! :)\n");
		System.exit(0);
	}
	
	public static int displayAreaList() // display List of Area
	{
		System.out.println("\n���� �ڵ带 �Է����ּ���.\n(ex. ������ '����, ����'�̸�, 1�� �Է����ּ���.)\n"
				+ "1. ����, ����\n"
				+ "2. �λ�, �泲\n"
				+ "3. �뱸, ���\n"
				+ "4. ���, ��õ\n"
				+ "5. ����, ����, ����\n"
				+ "6 ����, ��û");
		Scanner scan = new Scanner(System.in);
		int areaCode = scan.nextInt();
		return areaCode;
	}
	
	public static int displayInterestList() // display List of Interest
	{
		System.out.println("\n���� �о� �ڵ带 �Է����ּ���.\n(ex. ���� �о߰� '�������'�̸�, 1�� �Է����ּ���.)\n"
				+ "1. �������\n"
				+ "2. �����Ʒá�ü�衤����\n"
				+ "3. �����о� �������\n"
				+ "4. �߼ұ�� �������\n"
				+ "5. �ؿ�����\n"
				+ "6. â��\n"
				+ "7. R&D ����\n"
				+ "8. �濵 ����\n"
				+ "9. �ں��� ����\n"
				+ "10. ��Ȱ������\n"
				+ "11. �ǰ�\n"
				+ "12. ��ȭ\n"
				+ "13. �ְš�����\n"
				+ "14. ��Ȱ�� ���� �� ���� ����\n"
				+ "15. �ְ� ����\n"
				+ "16. ���ڱ� ����");
		Scanner scan = new Scanner(System.in);
		int interestCode = scan.nextInt();
		return interestCode;
	}
	
	public static String getInfoString(String statement)
	{
		System.out.println(statement);
		Scanner scan = new Scanner(System.in);
		String answer = scan.nextLine();
		return answer;
	}
	
	public static int getInfoInt(String statement)
	{
		System.out.println(statement);
		Scanner scan = new Scanner(System.in);
		int answer = scan.nextInt();
		return answer;
	}
	
	public static int services(int sltNum, String name, String interestCode, String email, int areaCode, int flag) throws SQLException
	{
		switch(sltNum) 
		{
			case 1: flag = policyList(name, interestCode, email); //��å ���� ����
				break;
				
			case 2: flag = recruitmentEventList(name, areaCode); //ä����� ���� ����
				break;
				
			case 3: flag = subscribe(name, email); //���� ���� ��û
				break;
				
			case 4 : flag = 2;
		}
		return flag;
	}

	public static int policyList(String name, String interestCode, String email) throws SQLException //Display PolicyList
	{
		String JobQuery = "select * from JobPolicy where JobPolicy.interestCode ='"+interestCode+"'";
		ResultSet r = getQuery(JobQuery);
		System.out.println("\n"+name+"���� ���� ��å ���� ����Ʈ�� �����帱�Կ�!\n");
		while(r.next())
		{
			System.out.println
			(
					"�����: " +
					r.getString(4)+" | "+
					"�������: " +
					r.getString(5)+" | "+
					"�������: " +
					r.getString(6)+" | "+
					"����: " +
					r.getString(9)+" | "+
					"�з�: " +
					r.getString(10)+" | "+
					"�������: " +
					r.getString(11)+" | "+
					"\n----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"
			);	
		}
		return CorQ();
	}
	
	public static int recruitmentEventList(String name, int areaCode) throws SQLException // Display RecruitmentEventList
	{
		String RecruitmentEventQuery = "select * from RecruitmentEvent where areaCode = '" + areaCode + "'";
		ResultSet r = getQuery(RecruitmentEventQuery);
		System.out.println("\n"+name+"���� ���� ä�� ��� ����Ʈ�� �����帱�Կ�!\n");
		while(r.next())
		{
			System.out.println
			(
					"ä����� ��ȣ: " +
					r.getString(1)+" | "+
					"����: " +
					r.getString(3)+" | "+
					"���Ⱓ: " +
					r.getString(4)+" | "+
					"\n----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"
			);	
		}
		int choice = getInfoInt("\n�ٸ� ���񽺸� �̿��Ͻðڽ��ϱ�? ���ϴ� ��ȣ�� �Է����ּ���!\n"
				+ "1. ä����� ������ ����\n"
				+ "2. ���� ���� ������\n"
				+ "3. ���� ����");
		if (choice == 1) {
			int eventNoValue = getInfoInt("\n�������� Ȯ���ϰ� ���� ä����� ��ȣ�� �Է����ּ���!");
			return recruitmentEventDetail(name, eventNoValue);
		} else {
			return choice == 2 ? 1 : 2;
		}

	}
	
	public static int recruitmentEventDetail(String name, int eventNoValue) throws SQLException // Display RecruitmentEventDetail
	{
		String RecruitmentEventDetailQuery = "select * from RecruitmentEventDetail where eventNo = '" + eventNoValue + "'";
		ResultSet r = getQuery(RecruitmentEventDetailQuery);
		System.out.println("\n"+name+"��, " + eventNoValue + "�� ä�� ����� �� ������ �����帱�Կ�!\n");
		while(r.next())
		{
			System.out.println
			(
					"����: " +
					r.getString(2)+" | "+
					"���Ⱓ: " +
					r.getString(3)+" | "+
					"������: " +
					r.getString(4)+" | "+
					"�̸���: " +
					r.getString(5)+" | "+
					"������ȭ: " +
					r.getString(6)+" | "+
					"�����: " +
					r.getString(7)+" | "+
					"���ô±�: " +
					r.getString(8) +" | "+
					"\n----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"
			);	
		}
		return CorQ();
	}
	
	
	public static int subscribe(String name, String email) throws SQLException //Regular Mail Subscription
	{
		System.out.print("\n���� ���� ������ ��û�� �ּż� �����մϴ�.\n"
				+ "û����������� ä����� ������ ���������� �޾ƺ��� �� �ֽ��ϴ�!\n");
		Scanner scan = new Scanner(System.in);
		email = getInfoString("�Ʒ���  ���� �����Ͻ� ���� �ּҸ� �Է����ּ���.");
		
		try {

			double dValue = Math.random();
		    int iValue = (int)(dValue * 10);
		    String s = "";
		    if(iValue % 2 == 0) {
		    	String JobQuery = "select * from JobPolicy where JobPolicy.interestCode ='"+ interestCode +"'";
				ResultSet r = getQuery(JobQuery);
				
				s = s + name+"���� ���� ��å ����Ʈ�� �����帱�Կ�!";
				r.next();
				
							s = s + "�����: " +
							r.getString(4)+" | "+
							"�������: " +
							r.getString(5)+" | "+
							"�������: " +
							r.getString(6)+" | "+
							"����: " +
							r.getString(9)+" | "+
							"�з�: " +
							r.getString(10)+" | "+
							"�������: " +
							r.getString(11)+" | ";
		    } else {
		    	String RecruitmentEventQuery = "select * from RecruitmentEvent where areaCode = '" + areaCode + "'";
				ResultSet r = getQuery(RecruitmentEventQuery);
				s = s + name+"���� ���� ä�� ��� ����Ʈ�� �����帱�Կ�!";
				r.next();
							s = s + "ä����� ��ȣ: " +
							r.getString(1)+" | "+
							"����: " +
							r.getString(3)+" | "+
							"���Ⱓ: " +
							r.getString(4)+" | ";
		    }
	        
	        String json = "{\"email\": \"" + email + "\", \"msg\": \"" + s + "\"}";
	        URL url = new URL("http://35.232.159.201:3001/api/mail");
	           HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	           conn.setConnectTimeout(5000);
	           conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	           conn.setDoOutput(true);
	           conn.setDoInput(true);
	           conn.setRequestMethod("POST");
	           OutputStream os = conn.getOutputStream();
	           os.write(json.getBytes("UTF-8"));
	           os.close(); 
	           InputStream in = new BufferedInputStream(conn.getInputStream());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		System.out.print("\n���� ���� ���� ��û�� �Ϸ�Ǿ����ϴ�.\n"
				+ "Ȯ�� ������ ����������� �������� Ȯ���� ������!\n");
		return CorQ();
	}
	
	public static ResultSet getQuery(String Query) throws SQLException
	{
		ResultSet r = stm.executeQuery(Query); // insert query
	    return r;
	}
	
	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		// connect Database
		        
        String host = "34.94.93.148";
        String port = "5432";
        String db_name = "postgres";
        String username = "postgres";
        String password = "uMY1vk*m";
      
        ResultSet r;
		conn = DriverManager.getConnection("jdbc:postgresql://"+host+":"+port+"/"+db_name+"",""+username+"",""+password);//Connect PostgreSQL
	    stm = conn.createStatement();
	    
		try {
			if(conn != null)
			{
				System.out.println("JobForYou ���񽺸� �����մϴ�.\n"); //Success to Connect
			}
			else System.out.println("Connection failed\n"); //Fail to Connect
		
        Scanner scan = new Scanner(System.in);
		System.out.println("**************JobForYou**************"); //Service Introduction
		System.out.println("* ���� ���񽺴� ����� ����,���ɻ�,���� ������ ���߾� *\n"
						 + "* ��� ���� ��å ��,ä�� ��� �������� ������ �ݴϴ�. *\n"
						 + "* ä�� ����� ���,�� ��縶��  �������� �����մϴ�.*\n*"
						 + "*************************************\n");
		
		int flag = 1;
		int choice = 0; //for Start Service
		int sltNum = 0; //for menu;
		String name = null;
		int age = 0;
		String eQuery = null;
		String getInfoQuery = null;
		
		System.out.println("���񽺸� �̿��Ͻǰǰ���? �˸��� ��ȣ�� �Է����ּ���!\n" //Question to User(Continue or Quit)
				+ "�̿��� ���̴�. ->���� 1�� �Է����ּ���!\n"
				+ "������ ���̴�. ->���� 2�� �Է����ּ���!");
			
		choice = scan.nextInt();
		scan.nextLine();
		
		if(choice == 2)  //When User want to stop Using Service
			Quit();
		
		else if(choice == 1) //When User want to use our Service
		{
			System.out.println("\n���� ���񽺸� �̿��غ��̳���? �˸��� ��ȣ�� �Է����ּ���!\n" //Question to User(Have been Used or New)
				+ "����غô�. ->���� 1�� �Է����ּ���!\n" 
				+ "ó�� �̿��غ���. ->���� 2�� �Է����ּ���!");
			choice = scan.nextInt();
			scan.nextLine();
			
			if(choice == 1) //User is member of our Service
			{	
				email = getInfoString("\n�α����� ���� �̸����� �Է��� �ּ���!");		
				eQuery = "select name from Student where email = '" + email + "'";
				r = getQuery(eQuery);
				
				while(r.next())
				{ System.out.println ("�ٽ� ������ �ݰ����ϴ� "+r.getString(1)+"��! ���õ� ���� ���� ��� ������ :)"); }
				
				getInfoQuery = "select * from Student where Student.email ='"+email+"'";
				r = getQuery(getInfoQuery);
				
				while(r.next()) 
				{
					email = r.getString(1);
					interestCode = r.getString(2);
					areaCode = r.getInt(3);
					name = r.getString(4);
					age = r.getInt(5);
				}
				
				while(flag == 1) //if flag == 1, repeat service
				{
					sltNum = menu();
					flag = services(sltNum,name,interestCode,email,areaCode,flag);
				}
				Quit(); //flag is 2, so User want to quit.
			}
			
			else if(choice == 2) //User is new Member
			{
				Area[] areaData = new Area[6];
				areaData[0] = new Area(51, "����, ����");
				areaData[1] = new Area(52, "�λ�, �泲");
				areaData[2] = new Area(53, "�뱸, ���");
				areaData[3] = new Area(54, "���, ��õ");
				areaData[4] = new Area(55,"����, ����, ����" );
				areaData[5] = new Area(56, "����, ��û");
				
				Interest[] interestData = new Interest[16];
				interestData[0] = new Interest("PLCYTP01", "�������");
				interestData[1] = new Interest("PLCYTP010001", "�����Ʒá�ü�衤����");
				interestData[2] = new Interest("PLCYTP010002", "�����о� �������");
				interestData[3] = new Interest("PLCYTP010003", "�߼ұ�� �������");
				interestData[4] = new Interest("PLCYTP010004", "�ؿ�����");
				interestData[5] = new Interest("PLCYTP02", "â��");
				interestData[6] = new Interest("PLCYTP020001", "R&D ����");
				interestData[7] = new Interest("PLCYTP020002", "�濵 ����");
				interestData[8] = new Interest("PLCYTP020003", "�ں��� ����");
				interestData[9] = new Interest("PLCYTP03", "��Ȱ������");
				interestData[10] = new Interest("PLCYTP030001", "�ǰ�");
				interestData[11] = new Interest("PLCYTP030002", "��ȭ");
				interestData[12] = new Interest("PLCYTP04", "�ְš�����");
				interestData[13] = new Interest("PLCYTP040001", "��Ȱ�� ���� �� ���� ����");
				interestData[14] = new Interest("PLCYTP040002", "�ְ� ����");
				interestData[15] = new Interest("PLCYTP040003", "���ڱ� ����");
				name = getInfoString("\nȸ�� ������ �����ϰڽ��ϴ�.\n�̸��� �Է����ּ���");
				age = getInfoInt("\n�� ���̸� ���ڷ� �Է����ּ���.");
				email = getInfoString("\n�α����� ���� ����� �̸����� �Է����ּ���");
				int areaChoice = displayAreaList();
				areaCode = areaData[areaChoice - 1].areaCode;
				int interestChoice = displayInterestList();
				interestCode = interestData[interestChoice - 1].interestCode;
				String SignUpQuery = "insert into Student values ('" 
						+ email 
						+ "','"
						+ interestCode
						+ "','"
						+ areaCode
						+ "','"
						+ name
						+ "','"
						+ age
						+"')";
				stm.executeUpdate(SignUpQuery); // insert query for create new Student

				// Login
	            getInfoQuery = "select * from Student where Student.email ='"+email+"'";
	            r = getQuery(getInfoQuery);

	            while(r.next()) 
	            {
	               email = r.getString(1);
	               interestCode = r.getString(2);
	               areaCode = r.getInt(3);
	               name = r.getString(4);
	               age = r.getInt(5);
	            }

				System.out.println("JobForYou ȸ�������� �Ϸ�Ǿ����ϴ�!");

				while(flag == 1) //if flag == 1, repeat service
				{
					sltNum = menu();
					flag = services(sltNum,name,interestCode,email,areaCode,flag);
				}
				Quit(); //flag is 2, so User want to quit.
			}
		}
		
	}catch (SQLException ex) 
	{ throw ex; }
}

	static class Area{
		int areaCode;
		String area;
		public Area(int areaCode, String area) {
			this.areaCode = areaCode;
			this.area = area;
		}
	}
	
	static class Interest{
		String interestCode;
		String interest;
		public Interest(String interestCode, String interest) {
			this.interestCode = interestCode;
			this.interest = interest;
		}
	}
}

