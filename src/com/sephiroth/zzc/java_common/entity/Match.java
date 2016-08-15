package com.sephiroth.zzc.java_common.entity;

public class Match {

	public String teamHome;
	public String teamGuest;

	public float oddWin;
	public float oddPlain;
	public float oddLose;

	public float oddMin;
	public float oddMiddle;
	public float oddMax;
	
	public int result = -1;

	public Match() {
		super();
		this.teamHome = "";
		this.teamGuest = "";
		this.oddWin = 0.0f;
		this.oddPlain = 0.0f;
		this.oddLose = 0.0f;
	}

	public Match(String teamHome, String teamGuest, float oddWin,
			float oddPlain, float oddLose) {
		super();
		this.teamHome = teamHome;
		this.teamGuest = teamGuest;
		this.oddWin = oddWin;
		this.oddPlain = oddPlain;
		this.oddLose = oddLose;
	}
	
	@Override
	public String toString() {
		return teamHome+" vs "+teamGuest+" : "+oddWin+" "+oddPlain+" "+oddLose+"   "+result;
	}
	
	public String toCsv() {
		return teamHome+","+teamGuest+","+oddWin+","+oddPlain+","+oddLose+","+result;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getTeamHome() {
		return teamHome;
	}

	public void setTeamHome(String teamHome) {
		this.teamHome = teamHome;
	}

	public String getTeamGuest() {
		return teamGuest;
	}

	public void setTeamGuest(String teamGuest) {
		this.teamGuest = teamGuest;
	}

	public float getOddWin() {
		return oddWin;
	}

	public void setOddWin(float oddWin) {
		this.oddWin = oddWin;
	}

	public float getOddPlain() {
		return oddPlain;
	}

	public void setOddPlain(float oddPlain) {
		this.oddPlain = oddPlain;
	}

	public float getOddLose() {
		return oddLose;
	}

	public void setOddLose(float oddLose) {
		this.oddLose = oddLose;
	}
}
