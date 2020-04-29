package fantasyBaseball;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main
{
	public static void main (String[] args) throws FileNotFoundException
	{	
		Draft draft = new Draft(4);
		LinkedList pitch = draft.refDataBase();
		System.out.println("******** Available Players to Draft *********");
		System.out.println();
		pitch.printList();
		boolean run = true;  // SHOULD USE ENUM INSTEAD PERHAPS ???????
		Scanner scan = new Scanner(System.in);
		while (run)
		{
			System.out.println();
			System.out.println("Available Commands: (Case Sensitive!!)");
			System.out.println("--------------------------------------");
			System.out.println("ODRAFT \"player name\" leagueMember --- ex: ODRAFT \"Iglesias, J\" B");
			System.out.println("IDRAFT \"player name\" ex: --- IDRAFT \"Bryant\"");
			System.out.println("OVERALL position --- ex: OVERALL SS");
			System.out.println("POVERALL");
			System.out.println("TEAM leagueMember");
			System.out.println("STARS leagueMember");
			System.out.println("SAVE filename");
			System.out.println("RESTORE filename");
			System.out.println("EVALFUN expression");
			System.out.println("PEVALFUN expression");
			System.out.println("QUIT");
			System.out.println("--->");
			System.out.printf("Enter command: ");
			
			String command = scan.nextLine();
			String[] commandARR = command.split(" ");
			int len = commandARR.length;
			String cmd = commandARR[0].toUpperCase();
			
			if (cmd.equals("ODRAFT")) { 
				String[] odCMD = command.split("\"");
				len = odCMD.length;
				if(len != 3)
					System.out.println("Invalid ODRAFT command");
				else
				{
					
					draft.oDraft(odCMD[1], odCMD[2].replaceAll(" ", ""));
				}
			}
			
			if (cmd.equals("IDRAFT")) {
				
				String[] odCMD = command.split("\"");
				len = odCMD.length;
				if(len != 2)
					System.out.println("Invalid IDRAFT command");
				else
				{
					draft.iDraft(odCMD[1]);
				}
			}
			
			if (cmd.equals("OVERALL")) {
				if(len == 1)
				{
					String overall = draft.overall("");
					System.out.println(overall);
				}
				else
				{
					String overall = draft.overall(commandARR[1]);
					System.out.println(overall);
				}
			}
			
			if (cmd.equals("POVERALL")) {
				if(len != 1)
					System.out.println("Invalid POVERALL command");
				else
				{
					String pOverall = draft.pOverall();
					System.out.println(pOverall);
				}
			}
				
			if (cmd.equals("TEAM")) {
				if(len != 2)
					System.out.println("Invalid TEAM command");
				else
				{
					String team = draft.team(commandARR[1]);
					System.out.println(team);
				}
			}
				
			if (cmd.equals("STARS")) {
				if(len != 2)
					System.out.println("Invalid STARS command");
				else
				{
					draft.stars(commandARR[1]);
				}
			}
				
			if (cmd.equals("SAVE")) {
				if(len != 2)
					System.out.println("Invalid SAVE command");
				else
				{
					try {
						draft.save(commandARR[1]);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
				
			if (cmd.equals("RESTORE")) {
				if(len != 2)
					System.out.println("Invalid RESTORE command");
				else
				{
					draft.restore(commandARR[1]);
				}
			}
				
			if (cmd.equals("EVALFUN")) {
				if(len != 2)
					System.out.println("Invalid EVALFUN command");
				else
				{
					
				}
			}
				
			if (cmd.equals("PEVALFUN")) {
				if(len != 2)
					System.out.println("Invalid PEVALFUN command");
				else
				{
					
				}
			}
				
			if (cmd.equals("QUIT")) {
				if(len != 1)
					System.out.println("Invalid QUIT command");
				else
				{
					System.out.println("Would you like to save the current draft? y/n");
					if(!scan.nextLine().equals("y") || !scan.nextLine().equals("Y")) {
						scan.close();
						run = false;
					}else {
						System.out.println("Enter file name: ");
						String saveStr = scan.nextLine();
						try {
							draft.save(saveStr);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					run = false;
				}
			}
		}
		scan.close();
		System.out.println("Goodbye!");
		
	}
}