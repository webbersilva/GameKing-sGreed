package br.unip.APS.KingsGreed.statistics;

public class NullStatCalculator implements StatCalculator {


	public long exp(int level) { return 1; }
	public int hp(int level) { return 1; }
	public int mp(int level) { return 0; }
	public int attack(int str) { return 0; }
	public int spell(int intel) { return 0; }

}
