package example;

import java.io.Serializable;

public class MonthlyActivityRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	private int month;
	private int schActivity;
	private int prjActivity;
	private double cumulPtime;
	private double cumulSchActivity;
	private double cumulPrjActivity;
	
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getSchActivity() {
		return schActivity;
	}
	public void setSchActivity(int schActivity) {
		this.schActivity = schActivity;
	}
	public int getPrjActivity() {
		return prjActivity;
	}
	public void setPrjActivity(int prjActivity) {
		this.prjActivity = prjActivity;
	}
	public double getCumulPtime() {
		return cumulPtime;
	}
	public void setCumulPtime(double cumulPtime) {
		this.cumulPtime = cumulPtime;
	}
	public double getCumulSchActivity() {
		return cumulSchActivity;
	}
	public void setCumulSchActivity(double cumulSchActivity) {
		this.cumulSchActivity = cumulSchActivity;
	}
	public double getCumulPrjActivity() {
		return cumulPrjActivity;
	}
	public void setCumulPrjActivity(double cumulPrjActivity) {
		this.cumulPrjActivity = cumulPrjActivity;
	}
	
	@Override
	public String toString() {
		return 	month + "\t" + schActivity + "\t" + prjActivity + "\t" + 
			cumulPtime + "\t" +  cumulSchActivity + "\t" + cumulPrjActivity;
	}
	
	
}//end class
