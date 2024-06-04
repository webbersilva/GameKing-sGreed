package br.unip.APS.KingsGreed.statistics;

public class MonsterCalculator implements StatCalculator {

	public long exp(int level) {
		return (level > 2) ? (level - 1) * 15 - 10 : 10;
	};

	public int hp(int level) {
		return level * 100;
	};

	public int mp(int level) {
		return level * 50;
	};

	public int attack(int str) {
		return str * 10;
	};

	public int spell(int intel) {
		return intel * 10;
	}

}
