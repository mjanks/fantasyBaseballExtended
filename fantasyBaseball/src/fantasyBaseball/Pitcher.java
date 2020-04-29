package fantasyBaseball;

public class Pitcher extends BaseBaller
{
	private int rank, w, l, g, gs, sv, svo, h, r, er, hr, bb, so;
	private double era, ip, avg, whip;

	public Pitcher (String data) {

		String[] dataArr = data.split("\t");
		rank = Integer.parseInt(dataArr[0]);
		playerName = dataArr[1];
		team = dataArr[2];
		position = "P";
		w = Integer.parseInt(dataArr[3]);
		l = Integer.parseInt(dataArr[4]);
		era = Double.parseDouble(dataArr[5]);
		g = Integer.parseInt(dataArr[6]);
		gs = Integer.parseInt(dataArr[7]);
		sv = Integer.parseInt(dataArr[8]);
		svo = Integer.parseInt(dataArr[9]);
		ip = Double.parseDouble(dataArr[10]);
		h = Integer.parseInt(dataArr[11]);
		r = Integer.parseInt(dataArr[12]);
		er = Integer.parseInt(dataArr[13]);
		hr = Integer.parseInt(dataArr[14]);
		bb = Integer.parseInt(dataArr[15]);
		so = Integer.parseInt(dataArr[16]);
		avg = Double.parseDouble(dataArr[17]);
		whip = Double.parseDouble(dataArr[18]);
	}
	
	public String toString()
	{
		String strComb = String.format("%s. %s.", playerName, team);
		String strTab = strComb.length() < 15 ? "\t\t" : "\t";
		return String.format("%d:\t %s%s %s\t%1.3f", rank, strComb, strTab , position, avg);
	}
	
	public int getPitcherRank() {
		return this.rank;
	}
	
	public int getPitcherWins() {
		return this.w;
	}
	
	public int getPitcherLosses() {
		return this.l;
	}
	
	public double getPitcherEra() {
		return this.era;
	}
	
	public int getPitcherGamesPlayed() {
		return this.g;
	}
	
	public int getPitcherGamesStarted() {
		return this.gs;
	}
	
	public int getPitcherSaves() {
		return this.sv;
	}
	
	public int getPitcherSvo() {
		return this.svo;
	}
	
	public double getPitcherIP() {
		return this.ip;
	}
	
	public int getPitcherHitsAllowed() {
		return this.h;
	}
	public int getPitcherRunsAllowed() {
		return this.r;
	}
	
	public int getPitcherEarnedRuns() {
		return this.er;
	}
	
	public int getPitcherHRAllowed() {
		return this.hr;
	}
	
	public int getPitcherBB() {
		return this.bb;
	}
	public int getPitcherStrikeouts() {
		return this.so;
	}
	
	public double getPitcherAvg() {
		return this.avg;
	}
	
	public double getPitcherWHIP() {
		return this.whip;
	}
}