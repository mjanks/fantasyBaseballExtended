package fantasyBaseball;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Draft {
	
	private String team;
	private String playerName;

	private LinkedList database = new LinkedList();
	private ArrayList<BaseBaller> draftedPlayers = new ArrayList<BaseBaller>();
	
	private ArrayList<BaseBaller> teamA = new ArrayList<BaseBaller>();
	private ArrayList<BaseBaller> teamB = new ArrayList<BaseBaller>();
	private ArrayList<BaseBaller> teamC = new ArrayList<BaseBaller>();
	private ArrayList<BaseBaller> teamD = new ArrayList<BaseBaller>();
	
	public Draft() {};
	
	public Draft(String team, String playerName) {
		this.team = team;
		this.playerName = playerName;
	}
	
	public Draft (int numOfTeam) throws FileNotFoundException
	{
		Scanner scanFile = new Scanner(new File("fantasyBaseball.tsv"));
		String data = scanFile.nextLine();
		while(scanFile.hasNext())
		{
			data = scanFile.nextLine();
			this.database.insert(new Node(new Player(data)));
		}
		
		scanFile = new Scanner(new File("fantasyPitchers.tsv"));
		data = scanFile.nextLine();
		while(scanFile.hasNext())
		{
			data = scanFile.nextLine();
			this.database.insert(new Node(new Pitcher(data)));
		}
	}

	public LinkedList refDataBase()
	{
		return database;
	}
	
	public double getAvg(String player) {
		if(database.get(player).position.equals("P")) {
			Pitcher p1 = (Pitcher) database.get(player);
			double pAvg1 = p1.getPitcherAvg();
			return pAvg1;
		} else {
			Player p2 = (Player) database.get(player);
			double pAvg2 = p2.getPlayerAvg();
			return pAvg2;
		}
	}
	
	public boolean isTeamValid(String team) {
		if(team.equals("A") || team.equals("B") || 
				team.equals("C") || team.equals("D")) {
			return true;
		} else
			System.out.println("Team " + team + " does not exist");
			System.out.println("No player drafted");
			System.out.println("***************************************");
			return false;
	}
	
	public boolean isPlayerValid(String player) {
		for(int i=0; i < database.size(); i++) {
			if(player.equals(database.get(i).getLastName()) || 
					player.equals(database.get(i).getPlayername())) {
				return true;
			}
		}
		System.out.println("Not a valid player name");
		System.out.println("No player drafted");
		System.out.println("***************************************");
		return false;
	}
	
	public boolean hasPlayerBeenDrafted(String player) {
		for(int i=0; i < draftedPlayers.size(); i++ ) {
			if(player.equals(draftedPlayers.get(i).getLastName())) {
				alreadyDrafted();
				return true;
			}
			if(player.equals(draftedPlayers.get(i).playerName)) {
				alreadyDrafted();
				return true;
			}
		}
		return false;
	}
	
	//*********** PRINT HELPER FOR hasPlayerBeenDrafted() *****************
	public void alreadyDrafted() {
		System.out.println("Player has already been drafted by another team");
		System.out.println("No player drafted");
		System.out.println("***************************************");
	}
	
	public boolean posAvailable(String team, String player) { 
		int count = 0;
		String position = getPlayerPosition(player);
		
		switch(team) {
			case "A" :
				for(int i=0; i < teamA.size(); i++) {
					if(position.equals(teamA.get(i).getPosition()) && (!position.equals("P"))) {
						posAvailablePrintDeny();
						return false;
					}
				}
				if(position.equals("P")) {
					for(int j=0; j < teamA.size(); j++) {
						if(teamA.get(j).getPosition().equals("P")) {
							count++;
							if(count >= 5) {
								posAvailablePrintDeny();
								return false;
							}
						}
					}
				}
				break;
			case "B" :
				for(int i=0; i < teamB.size(); i++) {
					if(position.equals(teamB.get(i).getPosition()) && (!position.equals("P"))) {
						posAvailablePrintDeny();
						return false;
					}
				}
				if(position.equals("P")) {
					for(int j=0; j < teamB.size(); j++) {
						if(teamB.get(j).getPosition().equals("P")) {
							count++;
							if(count >= 5) {
								posAvailablePrintDeny();
								return false;
							}
						}
					}
				}
				break;
			case "C" :
				for(int i=0; i < teamC.size(); i++) {
					if(position.equals(teamC.get(i).getPosition()) && (!position.equals("P"))) {
						posAvailablePrintDeny();
						return false;
					}
				}
				if(position.equals("P")) {
					for(int j=0; j < teamC.size(); j++) {
						if(teamC.get(j).getPosition().equals("P")) {
							count++;
							if(count >= 5) {
								posAvailablePrintDeny();
								return false;
							}
						}
					}
				}
				break;
			case "D" :
				for(int i=0; i < teamD.size(); i++) {
					if(position.equals(teamD.get(i).getPosition()) && (!position.equals("P"))) {
						posAvailablePrintDeny();
						return false;
					}
				}
				if(position.equals("P")) {
					for(int j=0; j < teamD.size(); j++) {
						if(teamD.get(j).getPosition().equals("P")) {
							count++;
							if(count >= 5) {
								posAvailablePrintDeny();
								return false;
							}
						}
					}
				}
				break;
		}
		return true;
	}
	
	// ******* HELPER METHOD FOR posAvailabe() ******************
	public String getPlayerPosition(String player) {
		String pos = null;
		for(int i=0; i < database.size(); i++) {
			if(player.equals(database.get(i).getLastName()) || 
					player.equals(database.get(i).playerName)) {
				pos = database.get(i).getPosition();
				i = database.size();
			}
		}
		return pos;
	}
	
	// *********** HELPER PRINT METHOD FOR posAvailable() *****************
	public void posAvailablePrintDeny() {
		System.out.println("Position has been drafted");
		System.out.println("No player drafted");
		System.out.println("***************************************");
	}

	public boolean isPlayerDuplicate(String player) {
		int count = 0;
		for(int i=0; i < database.size(); i++) {
			if(player.equals(database.get(i).playerName)) {
				return false;
			}
		}
		for(int i=0; i < database.size(); i++) {
			if(count == 2) {
				System.out.println("Duplicate player, provide first inital");
				System.out.println("No player drafted");
				System.out.println("***************************************");
				return true;
			}
			if(player.equals(database.get(i).getLastName())) {  
				count++;
			}
		}
		return false;                                     
	}
	
	public boolean oDraft (String player, String team) {
		System.out.println();
		System.out.println("***************************************");
		int index = 0;
		
		if(!isTeamValid(team) || !isPlayerValid(player) || !posAvailable(team, player) ||
				hasPlayerBeenDrafted(player) || isPlayerDuplicate(player)) {
			return false;
		}

		for(int i=0; i < database.size(); i++) {
			if(player.equals(database.get(i).getLastName())) {  
				index = addToTeam(team, i);
				draftedPlayers.add(database.get(i));
			}
		}
		
		for(int i=0; i < database.size(); i++) {
			if(player.equals(database.get(i).playerName)) {  
				index = addToTeam(team, i);
				draftedPlayers.add(database.get(i));
			}
		}
		
		System.out.println(database.get(index).playerName + ". " + getAvg(database.get(index).playerName) + " has been drafted by team " + team);
		System.out.println("***************************************");
		return true;
	}
	
	//*********** HELPER FOR ODRAFT **********************
		public int addToTeam(String team, int i) {
			int index = 0;
			switch(team) {
			case "A" :
				teamA.add(database.get(i));
				System.out.println("Drafted: " + database.get(i).playerName + ".");
				index = i;
				break;
			case "B" :
				teamB.add(database.get(i));
				System.out.println("Drafted: " + database.get(i).playerName + ".");
				index = i;
				break;
			case "C" :
				teamC.add(database.get(i));
				System.out.println("Drafted: " + database.get(i).playerName + ".");
				index = i;
				break;
			case "D" :
				teamD.add(database.get(i));
				System.out.println("Drafted: " + database.get(i).playerName + ".");
				index = i;
				break;
			} 
			return index;
		}
	
	public boolean stars(String team) {
		if(!isTeamValid(team))
			return false;
		
		switch(team) {
			case "A" :
				System.out.println("---------------------------");
				if(teamA.size() == 0) {
					System.out.println("No players on Team A");
					System.out.println();
				} else {
					System.out.println("STARS Team A");
					System.out.println("************");
					for(int j=0; j < teamA.size(); j++) {
						System.out.println(teamA.get(j).position + " " + teamA.get(j).playerName + ".");
					}
					System.out.println("---------------------------");
				}
				break;
			case "B" :
				System.out.println("---------------------------");
				if(teamB.size() == 0) {
					System.out.println("No players on Team B");
					System.out.println();
				} else {
					System.out.println("STARS Team B");
					System.out.println("************");
					for(int j=0; j < teamB.size(); j++) {
						System.out.println(teamB.get(j).position + " " + teamB.get(j).playerName + ".");
					}
					System.out.println("---------------------------");
				}
				break;
			case "C" :
				System.out.println("---------------------------");
				if(teamC.size() == 0) {
					System.out.println("No players on Team C");
					System.out.println();
				} else {
					System.out.println("STARS Team C");
					System.out.println("************");
					for(int j=0; j < teamC.size(); j++) {
						System.out.println(teamC.get(j).position + " " + teamC.get(j).playerName + ".");
					}
					System.out.println("---------------------------");
				}
				break;
			case "D" :
				System.out.println("---------------------------");
				if(teamD.size() == 0) {
					System.out.println("No players on Team D");
					System.out.println();
				} else {
					System.out.println("STARS Team D");
					System.out.println("************");
					for(int j=0; j < teamD.size(); j++) {
						System.out.println(teamD.get(j).position + " " + teamD.get(j).playerName + ".");
					}
					System.out.println("---------------------------");
				}
				break;
		}
		return true;
	}
	
	public boolean iDraft (String player)
	{
		return oDraft(player, "A");
	}
	
	public boolean save (String fName) throws IOException
	{
		String str = "";
		for(int i = 0; i < teamA.size(); i++)
			str += teamA.get(i).getPlayername() + "\t";
		str += "\n";
		for(int i = 0; i < teamB.size(); i++)
			str += teamB.get(i).getPlayername() + "\t";
		str += "\n";
		for(int i = 0; i < teamC.size(); i++)
			str += teamC.get(i).getPlayername() + "\t";
		str += "\n";
		for(int i = 0; i < teamD.size(); i++)
			str += teamD.get(i).getPlayername() + "\t";
		str += "\n";
		
		File file = new File(fName);
		if(!file.exists())
			file.createNewFile();
		FileWriter fWrite = new FileWriter(file);
		fWrite.write(str);
		fWrite.close();
		System.out.println("Created " + fName);
		return true;
	}
	
	public boolean restore (String fName)
	{
		File file = new File(fName);
		try {
			Scanner fScan = new Scanner(file);
			String fileTxt;
			String[] names;
			fileTxt = fScan.nextLine();
			names = fileTxt.split("\t");
			for (String name : names)
				oDraft(name, "A");
			
			fileTxt = fScan.nextLine();
			names = fileTxt.split("\t");
			for (String name : names)
				oDraft(name, "B");
			
			fileTxt = fScan.nextLine();
			names = fileTxt.split("\t");
			for (String name : names)
				oDraft(name, "C");
			
			fileTxt = fScan.nextLine();
			names = fileTxt.split("\t");
			for (String name : names)
				oDraft(name, "D");
			
			return true;
		} catch (FileNotFoundException e) {
			System.out.println(String.format("File %s was not found", fName));
			return false;
		}
	}
	
	public String overall(String pos)
	{
		String remainingPlayers  = "";
	
		if(!pos.isEmpty())
		{
			if(draftedPlayers.size() > 0)
			{
				for(int i = 0; i < draftedPlayers.size(); i++)
				{
					for(int j = 0; j < database.size(); j++)
					{	
						if(!database.get(j).playerName.equals(draftedPlayers.get(i).playerName))
						{
							if(database.get(i).getPosition().equals(pos))
							{
								remainingPlayers = remainingPlayers + database.get(i) + "\n";
							}
						}
					}
				}
			}
			else
			{
				for(int j = 0; j < database.size(); j++)
				{
					if(database.get(j).position.equals(pos))
					{
						remainingPlayers = remainingPlayers + database.get(j) + "\n";
					}
				}
			}
			remainingPlayers = remainingPlayers.length() == 0 ? "Position not found" : remainingPlayers;
		}
		else
		{
			for(int j = 0; j < database.size(); j++)
			{	
				remainingPlayers = remainingPlayers + database.get(j) + "\n";
			}
			remainingPlayers = remainingPlayers.length() == 0 ? "No players remaining" : remainingPlayers;
		}
		
		return remainingPlayers;
	}
	
	public String pOverall()
	{
		String remainingPlayers = "";
		
		if(draftedPlayers.size() > 0)
		{
			for(int i = 0; i < draftedPlayers.size(); i++)
			{
				for(int j = 0; j < database.size(); j++) 
				{
					if(!database.get(i).getPlayername().equals(draftedPlayers.get(i).getPlayername()))
					{
						if(database.get(i).position.equals("P"))
						{
							remainingPlayers = remainingPlayers + database.get(i) + "\n";
						}
					}
				}
			}
			remainingPlayers = remainingPlayers.length() == 0 ? "Position not found" : remainingPlayers;
		}
		else
		{
			for(int j = 0; j < database.size(); j++)
			{
				if(database.get(j).position.equals("P"))
				{
					remainingPlayers = remainingPlayers + database.get(j) + "\n";
				}
			}
			remainingPlayers = remainingPlayers.length() == 0 ? "Position not found" : remainingPlayers;
		}
		
		return remainingPlayers;
	}
	
	public String team(String leagueMember)
	{
		int pitcherPos = 0;
		String roster = "";
		if(leagueMember.equals("A"))
		{
			for(int i = 0; i < teamA.size(); i++)
			{
				if(teamA.get(i).getPosition().equals("C"))
				{
					roster = roster + teamA.get(i).getPosition() + "\t" + teamA.get(i).getPlayername() + "\n";
				}			
			}
			
			for(int i = 0; i < teamA.size(); i++)
			{
				if(teamA.get(i).getPosition().equals("1B"))
				{
					roster = roster + teamA.get(i).getPosition() + "\t" + teamA.get(i).getPlayername() + "\n";
				}
			}
			
			for(int i = 0; i < teamA.size(); i++)
			{
				if(teamA.get(i).getPosition().equals("2B"))
				{
					roster = roster + teamA.get(i).getPosition() + "\t" + teamA.get(i).getPlayername()+ "\n";
				}
			}
			
			for(int i = 0; i < teamA.size(); i++)
			{
				if(teamA.get(i).getPosition().equals("3B"))
				{
					roster = roster + teamA.get(i).getPosition() +  "\t" + teamA.get(i).getPlayername() + "\n";
				}
			}
						
			for(int i = 0; i < teamA.size(); i++)
			{
				if(teamA.get(i).getPosition().equals("SS"))
				{
					roster = roster + teamA.get(i).getPosition() +  "\t" + teamA.get(i).getPlayername() + "\n";
				}
			}
			
			for(int i = 0; i < teamA.size(); i++)
			{
				if(teamA.get(i).getPosition().equals("LF"))
				{
					roster = roster + teamA.get(i).getPosition() +  "\t" + teamA.get(i).getPlayername() + "\n";
				}
			}
			
			for(int i = 0; i < teamA.size(); i++)
			{
				if(teamA.get(i).getPosition().equals("CF"))
				{
					roster = roster + teamA.get(i).getPosition() +  "\t" + teamA.get(i).getPlayername() + "\n";
				}
			}
			
			for(int i = 0; i < teamA.size(); i++)
			{
				if(teamA.get(i).getPosition().equals("RF"))
				{
					roster = roster + teamA.get(i).getPosition() +  "\t" + teamA.get(i).getPlayername() + "\n";
				}
			}
			
			for(int i = 0; i < teamA.size(); i++)
			{
				if(teamA.get(i).getPosition().equals("P"))
				{
					
					roster = roster + teamA.get(i).getPosition() + (++pitcherPos) +  "\t" + teamA.get(i).getPlayername() + "\n";
				}
			}
			
		}
		else if(leagueMember.equals("B"))
		{
			for(int i = 0; i < teamB.size(); i++)
			{
				if(teamB.get(i).getPosition().equals("C"))
				{
					roster = roster + teamB.get(i).getPosition() + "\t" + teamB.get(i).getPlayername() + "\n";
				}			
			}
			
			for(int i = 0; i < teamB.size(); i++)
			{
				if(teamB.get(i).getPosition().equals("1B"))
				{
					roster = roster + teamB.get(i).getPosition() + "\t" + teamB.get(i).getPlayername() + "\n";
				}
			}
			
			for(int i = 0; i < teamB.size(); i++)
			{
				if(teamB.get(i).getPosition().equals("2B"))
				{
					roster = roster + teamB.get(i).getPosition() + "\t" + teamB.get(i).getPlayername()+ "\n";
				}
			}
			
			for(int i = 0; i < teamB.size(); i++)
			{
				if(teamB.get(i).getPosition().equals("3B"))
				{
					roster = roster + teamB.get(i).getPosition() +  "\t" + teamB.get(i).getPlayername() + "\n";
				}
			}
			
			for(int i = 0; i < teamB.size(); i++)
			{
				if(teamB.get(i).getPosition().equals("SS"))
				{
					roster = roster + teamB.get(i).getPosition() +  "\t" + teamB.get(i).getPlayername() + "\n";
				}
			}
			
			for(int i = 0; i < teamB.size(); i++)
			{
				if(teamB.get(i).getPosition().equals("LF"))
				{
					roster = roster + teamB.get(i).getPosition() +  "\t" + teamB.get(i).getPlayername() + "\n";
				}
			}
			
			for(int i = 0; i < teamB.size(); i++)
			{
				if(teamB.get(i).getPosition().equals("CF"))
				{
					roster = roster + teamB.get(i).getPosition() +  "\t" + teamB.get(i).getPlayername() + "\n";
				}
			}
			
			for(int i = 0; i < teamB.size(); i++)
			{
				if(teamB.get(i).getPosition().equals("RF"))
				{
					roster = roster + teamB.get(i).getPosition() +  "\t" + teamB.get(i).getPlayername() + "\n";
				}
			}
			
			for(int i = 0; i < teamB.size(); i++)
			{
				if(teamB.get(i).getPosition().equals("P"))
				{
					roster = roster + teamB.get(i).getPosition() + (++pitcherPos) +  "\t" + teamB.get(i).getPlayername() + "\n";
				}
			}	
		}
		else if(leagueMember.equals("C"))
		{
			for(int i = 0; i < teamC.size(); i++)
			{
				if(teamC.get(i).getPosition().equals("C"))
				{
					roster = roster + teamC.get(i).getPosition() + "\t" + teamC.get(i).getPlayername() + "\n";
				}			
			}
			
			for(int i = 0; i < teamC.size(); i++)
			{
				if(teamC.get(i).getPosition().equals("1B"))
				{
					roster = roster + teamC.get(i).getPosition() + "\t" + teamC.get(i).getPlayername() + "\n";
				}
			}
			
			for(int i = 0; i < teamC.size(); i++)
			{
				if(teamC.get(i).getPosition().equals("2B"))
				{
					roster = roster + teamC.get(i).getPosition() + "\t" + teamC.get(i).getPlayername()+ "\n";
				}
			}
			
			for(int i = 0; i < teamC.size(); i++)
			{
				if(teamC.get(i).getPosition().equals("3B"))
				{
					roster = roster + teamC.get(i).getPosition() +  "\t" + teamC.get(i).getPlayername() + "\n";
				}
			}
			
			for(int i = 0; i < teamC.size(); i++)
			{
				if(teamC.get(i).getPosition().equals("SS"))
				{
					roster = roster + teamC.get(i).getPosition() +  "\t" + teamC.get(i).getPlayername() + "\n";
				}
			}
			
			for(int i = 0; i < teamC.size(); i++)
			{
				if(teamC.get(i).getPosition().equals("LF"))
				{
					roster = roster + teamC.get(i).getPosition() +  "\t" + teamC.get(i).getPlayername() + "\n";
				}
			}
			
			for(int i = 0; i < teamC.size(); i++)
			{
				if(teamC.get(i).getPosition().equals("CF"))
				{
					roster = roster + teamC.get(i).getPosition() +  "\t" + teamC.get(i).getPlayername() + "\n";
				}
			}
			
			for(int i = 0; i < teamC.size(); i++)
			{
				if(teamC.get(i).getPosition().equals("RF"))
				{
					roster = roster + teamC.get(i).getPosition() +  "\t" + teamC.get(i).getPlayername() + "\n";
				}
			}
			
			for(int i = 0; i < teamC.size(); i++)
			{
				if(teamC.get(i).getPosition().equals("P"))
				{
					roster = roster + teamC.get(i).getPosition() + (++pitcherPos) +  "\t" + teamC.get(i).getPlayername() + "\n";
				}
			}
		}
		else if(leagueMember.equals("D"))
		{
			for(int i = 0; i < teamD.size(); i++)
			{
				if(teamD.get(i).getPosition().equals("C"))
				{
					roster = roster + teamD.get(i).getPosition() + "\t" + teamD.get(i).getPlayername() + "\n";
				}			
			}
			
			for(int i = 0; i < teamD.size(); i++)
			{
				if(teamD.get(i).getPosition().equals("1B"))
				{
					roster = roster + teamD.get(i).getPosition() + "\t" + teamD.get(i).getPlayername() + "\n";
				}
			}
			
			for(int i = 0; i < teamD.size(); i++)
			{
				if(teamD.get(i).getPosition().equals("2B"))
				{
					roster = roster + teamD.get(i).getPosition() + "\t" + teamD.get(i).getPlayername()+ "\n";
				}
			}
			
			for(int i = 0; i < teamD.size(); i++)
			{
				if(teamD.get(i).getPosition().equals("3B"))
				{
					roster = roster + teamD.get(i).getPosition() +  "\t" + teamD.get(i).getPlayername() + "\n";
				}
			}
			
			for(int i = 0; i < teamD.size(); i++)
			{
				if(teamD.get(i).getPosition().equals("SS"))
				{
					roster = roster + teamD.get(i).getPosition() +  "\t" + teamD.get(i).getPlayername() + "\n";
				}
			}
			
			for(int i = 0; i < teamD.size(); i++)
			{
				if(teamD.get(i).getPosition().equals("LF"))
				{
					roster = roster + teamD.get(i).getPosition() +  "\t" + teamD.get(i).getPlayername() + "\n";
				}
			}
			
			for(int i = 0; i < teamD.size(); i++)
			{
				if(teamD.get(i).getPosition().equals("CF"))
				{
					roster = roster + teamD.get(i).getPosition() +  "\t" + teamD.get(i).getPlayername() + "\n";
				}
			}
			
			for(int i = 0; i < teamD.size(); i++)
			{
				if(teamD.get(i).getPosition().equals("RF"))
				{
					roster = roster + teamD.get(i).getPosition() +  "\t" + teamD.get(i).getPlayername() + "\n";
				}
			}
			
			for(int i = 0; i < teamD.size(); i++)
			{
				if(teamD.get(i).getPosition().equals("P"))
				{
					roster = roster + teamD.get(i).getPosition() + (++pitcherPos) +  "\t" + teamD.get(i).getPlayername() + "\n";
				}
			}
		}
		
		roster = roster.length() == 0 ? "Invalid League Member" : roster;
		
		return roster;
	}
}
