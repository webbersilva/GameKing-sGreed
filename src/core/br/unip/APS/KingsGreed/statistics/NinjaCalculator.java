package br.unip.APS.KingsGreed.statistics;

public class NinjaCalculator extends PlayerCalculator {

	public int hp(int level) {
		return 40 + 74 * level;
	}

	public int mp(int level) {
		return 50 * level;
	}

	public int attack(int str) {
		return (int) (str * 7.5);
	}

	public int spell(int intel) {
		return (int) (intel * 2.5);
	}

}
