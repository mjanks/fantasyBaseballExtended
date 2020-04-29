package fantasyBaseball;

public class BaseBaller {
	String playerName, team, position;
	
	public String getPlayername()
	{
		return this.playerName;
	}
	
	public String getLastName()
	{
		return this.playerName.split(",")[0];
	}
	
	public String getPosition()
	{
		return position;
	}
	
	public String getTeam() {
		return this.team;
	}
}
