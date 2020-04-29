package fantasyBaseball;

public class Player extends BaseBaller
{
	private int rank, g, ab, r, h, base2, base3, hr, rbi, bb, so, sb, cs;
	private double avg, obp, slg, ops;
	
	public Player (String data)
	{
		String[] dataArr = data.split("\t");
		rank = Integer.parseInt(dataArr[0]);
		playerName = dataArr[1];
		team = dataArr[2];
		position = dataArr[3];
		g = Integer.parseInt(dataArr[4]);
		ab = Integer.parseInt(dataArr[5]);
		r = Integer.parseInt(dataArr[6]);
		h = Integer.parseInt(dataArr[7]);
		base2 = Integer.parseInt(dataArr[8]);
		base3 = Integer.parseInt(dataArr[9]);
		hr = Integer.parseInt(dataArr[10]);
		rbi = Integer.parseInt(dataArr[11]);
		bb = Integer.parseInt(dataArr[12]);
		so = Integer.parseInt(dataArr[13]);
		sb = Integer.parseInt(dataArr[14]);
		cs = Integer.parseInt(dataArr[15]);
		avg = Double.parseDouble(dataArr[16]);
		obp = Double.parseDouble(dataArr[17]);
		slg = Double.parseDouble(dataArr[18]);
		ops = Double.parseDouble(dataArr[19]);
	}
	
	public String toString()
	{
		String strComb = String.format("%s. %s.", playerName, team);
		String strTab = strComb.length() < 15 ? "\t\t" : "\t";
		return String.format("%d:\t %s%s %s\t%1.3f", rank, strComb, strTab , position, avg);
	}
	
	public int getPlayerRank() {
		return this.rank;
	}
	
	public int getPlayerGamesPlayed() {
		return this.g;
	}
	
	public int getPlayerAtBats() {
		return this.ab;
	}
	
	public int getPlayerRuns() {
		return this.r;
	}
	
	public int getPlayerHits() {
		return this.h;
	}
	
	public int getPlayerDoubles() {
		return this.base2;
	}
	
	public int getPlayerTriples() {
		return this.base3;
	}
	
	public int getPlayerHomeruns() {
		return this.hr;
	}
	
	public int getPlayerRBI() {
		return this.rbi;
	}
	
	public int getPlayerBaseOnBalls() {
		return this.bb;
	}
	
	public int getPlayerStrikeOuts() {
		return this.so;
	}
	
	public int getPlayerStolenBases() {
		return this.sb;
	}
	
	public int getPlayerCaughtStealing() {
		return this.cs;
	}
	public double getPlayerAvg() {
		return this.avg;
	}
	
	public double getPlayeObp() {
		return this.obp;
	}
	
	public double getPlayerSlugging() {
		return this.slg;
	}
	
	public double getPlayerOps() {
		return this.ops;
	}
}