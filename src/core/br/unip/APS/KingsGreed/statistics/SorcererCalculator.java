package br.unip.APS.KingsGreed.statistics;


public class SorcererCalculator extends PlayerCalculator {

	public int hp(int level) {
		return 80 + 48 * level;
	}

	public int mp(int level) {
		return 150 * level;
	}

	public int attack(int str) {
		return (int)(str * 2.5);
	}

	public int spell(int intel) {
		return (int)(intel * 7.5);
	}
}
