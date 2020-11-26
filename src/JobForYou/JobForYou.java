/* 
 * Ajou University 2020-2 Data Base Team Project 
 * Jinyoun Song, Hyungeong Park, SeungHun Han
 * ������� ������ ������ ��� ���� ��å �� ä�� ��� ����� �����ϴ� ���� ���� 
 * JobForYou(�����)
 */

package JobForYou;

import java.util.*;

public class JobForYou {
	
	public static void menu() //Show Menu List
	{
		System.out.println("\nJobforYou ���񽺴� û�������å ������ ä����� ������ �����ϰ� �ֽ��ϴ�.\n"
				+ "�̿��Ͻ� ���� ��ȣ�� �Է����ּ���.\n"
				+ "1. ��å ���� ����\n"
				+"2. ä�� ��� ���� ����\n"
				+"3. ���� ���� ��û\n" 
				+"4. ���� ����");
	}
	
	public static void CorQ() // Continue or Quit
	{
		System.out.println("\n�ٸ� ���񽺸� �̿��Ͻðڽ��ϱ�? ���ϴ� ��ȣ�� �Է����ּ���!\n"
				+ "1. ���� ���� ������\n"
				+"2. ���� ����\n");
	}
	
	public static void Quit() //Quit Service Function, Print Quit message.
	{
		System.out.println("\nJobForYou�� �̿��� �ּż� �����մϴ�.\n"
				+ "���Ͻô� ������ �����̱⸦ �ٶ��ϴ�! :)\n");
		System.exit(0);
	}
		
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		
		System.out.println("**************JobForYou**************"); //Service Introduction
		System.out.println("* ���� ���񽺴� ����� ����,���ɻ�,���� ������ ���߾� *\n"
						 + "* ��� ���� ��å ��,ä�� ��� �������� ������ �ݴϴ�. *\n"
						 + "* ä�� ����� ���,�� ��縶��  �������� �����մϴ�.*\n*"
						 + "*************************************\n");
		Scanner scan = new Scanner(System.in);
		int choice = 0; //for Start Service
		int sltNum = 0; //for Menu
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
				System.out.println("�α����� ���� �̸����� �Է��� �ּ���!\n");
				email = scan.nextLine();
				System.out.println("�ٽ� ������ �ݰ����ϴ�"+email+"��! ���õ� ���� ���� ��� ������ :)");
				
				menu();
				sltNum = scan.nextInt();
				scan.nextLine();
				
				switch(sltNum) 
				{
					case 1:
						
				}
			}
			
			else if(choice == 2) //User is new Member
			{
				
			}
		}
		
		
		

	}
}
