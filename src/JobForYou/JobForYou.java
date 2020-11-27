/* 
 * Ajou University 2020-2 Data Base Team Project 
 * Jinyoun Song, Hyungeong Park, SeungHun Han
 * ������� ������ ������ ��� ���� ��å �� ä�� ��� ����� �����ϴ� ���� ���� 
 * JobForYou(�����)
 */

package JobForYou;

import java.util.*;

public class JobForYou {
	
	int flag = 1;
	
	public static int menu() //Show Menu List
	{
		System.out.println("\nJobforYou ���񽺴� û�������å ������ ä����� ������ �����ϰ� �ֽ��ϴ�.\n"
				+ "���� ���� ���� ��û�� �����Ͻø�, ���Բ� ���� ������ ���������� ���Ϸ� ������ �帳�ϴ�.\n"
				+ "�̿��Ͻ� ���� ��ȣ�� �Է����ּ���.\n(ex. �̿��Ͻ� ���񽺰� '��å'�̸�, 1�� �Է����ּ���.\n"
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
	
	public static int services(int sltNum,int age,int interestCode,int areaCode,int eventNo, int flag)
	{
		switch(sltNum) 
		{
			case 1: flag = policyList(interestCode, age); //��å ���� ����
				break;
				
			case 2: //ä����� ���� ����
				break;
				
			case 3: flag = subscribe();//���� ���� ��û
				break;
				
			case 4 : flag = 2;
		}
		return flag;
	}

	public static int policyList(int interestCode, int age) //Display PolicyList
	{
		/*select *
		from jobPolicy
		where interestCode = ��interestCode�� and startAge <= age and endAge >= age;*/
		return CorQ();
	}
	
	
	public static int subscribe() //Regular Mail Subscription
	{
		System.out.print("\n���� ���� ������ ��û�� �ּż� �����մϴ�.\n"
				+ "û����������� ä����� ������ ���������� �޾ƺ��� �� �ֽ��ϴ�!\n");
		Scanner scan = new Scanner(System.in);
		String email = getInfoString("�Ʒ���  ���� �����Ͻ� ���� �ּҸ� �Է����ּ���.");
		System.out.print("\n���� ���� ���� ��û�� �Ϸ�Ǿ����ϴ�.\n"
				+ "Ȯ�� ������ ����������� �������� Ȯ���� ������!\n");
		return CorQ();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("**************JobForYou**************"); //Service Introduction
		System.out.println("* ���� ���񽺴� ����� ����,���ɻ�,���� ������ ���߾� *\n"
						 + "* ��� ���� ��å ��,ä�� ��� �������� ������ �ݴϴ�. *\n"
						 + "* ä�� ����� ���,�� ��縶��  �������� �����մϴ�.*\n*"
						 + "*************************************\n");
		Scanner scan = new Scanner(System.in);
		int flag = 1;
		int choice = 0; //for Start Service
		int sltNum = 0; //for menu;
		int age = 0;
		int areaCode = 0;
		int interestCode = 0;
		int eventNo = 0;
		String email = null;
		
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
				System.out.println("�ٽ� ������ �ݰ����ϴ�"+email+"��! ���õ� ���� ���� ��� ������ :)");
				
				while(flag == 1) //if flag == 1, repeat service
				{
					sltNum = menu();
					flag = services(sltNum,age,interestCode,areaCode,eventNo, flag);
				}
				Quit(); //flag is 2, so User want to quit.
			}
			
			else if(choice == 2) //User is new Member
			{
				String name = getInfoString("\nȸ�� ������ �����ϰڽ��ϴ�.\n�̸��� �Է����ּ���");
				age = getInfoInt("\n�� ���̸� ���ڷ� �Է����ּ���.");
				email = getInfoString("\n�α����� ���� ����� �̸����� �Է����ּ���");
				areaCode = displayAreaList(); // add area Info
				interestCode = displayInterestList(); // Add Interest Info
				// create new Student
//				insert into Student values (email,interestCode,areaCode,name,age,area,interest,isHired); 
				System.out.println("JobForYou ȸ�������� �Ϸ�Ǿ����ϴ�.");

				while(flag == 1) //if flag == 1, repeat service
				{
					sltNum = menu();
					flag = services(sltNum,age,interestCode,areaCode,eventNo, flag);
				}
				Quit(); //flag is 2, so User want to quit.
			}
		}
	}
}
