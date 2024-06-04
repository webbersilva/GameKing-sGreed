package br.unip.APS.KingsGreed.statistics;


public class PugilistCalculator extends PlayerCalculator {

	// METHODS
	public int hp(int level) {
		return 150 * level;
	}

	public int mp(int level) {
		return 20 + 37 * level;
	}

	public int attack(int str) {
		return (int)(str * 8);
	}

	public int spell(int intel) {
		return intel * 2;
	}

	public int crit(int dex) {
		return (int)(dex * 333.333);
	}

}
