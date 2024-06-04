package br.unip.APS.KingsGreed.entities;

import java.lang.Math;
import java.util.function.Function;

import br.unip.APS.KingsGreed.statistics.*;

public class Personagem {

	public static final String STR = "STR";
	public static final String DEX = "DEX";
	public static final String INT = "INT";

	private String nome;
	private int level = 0;

	private String classe = "None";
	private StatCalculator calculator = new NullStatCalculator();

	private int healthPoints = 1;
	private int magicPoints = 0;

	private int strength = 0;
	private int dexterity = 0;
	private int intelligence = 0;

	private int baseStrength = 0;
	private int baseDexterity = 0;
	private int baseIntelligence = 0;

	private int armourModifier = 0;
	private int negationModifier = 0;

	private Equip[] equipment = new Equip[Item.EQUIP_SUBTYPES];

	public Personagem(final String nome, int level, final String classe) {
		setNome(nome);
		this.level = level;
		setClasse(classe);
	}

	public Personagem(final String nome, int level, final String classe, final StatCalculator calculator, int str,
			int dex, int intel) {
		this(nome, level, classe);

		this.calculator = calculator;

		healthPoints = calculator.hp(level);
		magicPoints = calculator.mp(level);

		baseStrength = str;
		baseDexterity = dex;
		baseIntelligence = intel;

		strength = baseStrength;
		dexterity = baseDexterity;
		intelligence = baseIntelligence;
	}

	public Personagem(final Personagem other) {
		this(other.nome, other.level, other.classe, other.calculator, other.baseStrength, other.baseDexterity,
				other.baseIntelligence);

		healthPoints = other.healthPoints;
		magicPoints = other.magicPoints;

		strength = other.strength;
		dexterity = other.dexterity;
		intelligence = other.intelligence;

		armourModifier = other.armourModifier;
		negationModifier = other.negationModifier;

		equipment = other.equipment.clone();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		if (nome == null) {
			throw new NullPointerException("Personagem name can't be null.");
		}
		this.nome = nome;
	}

	public int level() {
		return level;
	}

	public void levelUp() {
		++level;
		rest();
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(final String classe) {
		this.classe = classe != null ? classe : "None";
	}

	public StatCalculator calculate() {
		return calculator;
	}

	public void setStatCalculator(final StatCalculator calculator) {
		if (calculator == null) {
			this.calculator = new NullStatCalculator();
		} else {
			int hpChange = calculator.hp(level) - this.calculator.hp(level);
			int mpChange = calculator.mp(level) - this.calculator.mp(level);

			healthPoints += hpChange;
			magicPoints += mpChange;

			this.calculator = calculator;
		}
	}

	public long experiencia() {
		return calculator.exp(level);
	}

	public int ataqueFisico() {
		return calculator.attack(strength()) + attackEquipBonus();
	}

	public int danoMagico() {
		return calculator.spell(intelligence()) + spellEquipBonus();
	}

	public int vidaMaxima() {
		return calculator.hp(level) + healthEquipBonus();
	}

	public int magiaMaxima() {
		return calculator.mp(level) + magicEquipBonus();
	}

	public int vida() {
		return Math.min(healthPoints, vidaMaxima());
	}

	public void heal(int healing) {
		setHealth(vida() + healing);
	}

	public void wound(int damage) {
		setHealth(vida() - damage);
	}

	public boolean isDead() {
		return vida() <= 0;
	}

	public int magia() {
		return Math.min(magicPoints, magiaMaxima());
	}

	public void restore(int mana) {
		setMagic(magia() + mana);
	}

	public void deplete(int manaCost) {
		setMagic(magia() - manaCost);
	}

	public int getForcaBase() {
		return baseStrength;
	}

	public void setForcaBase(int str) {
		int change = str - baseStrength;
		baseStrength = str;
		strength += change;
	}

	public int getBaseDexterity() {
		return baseDexterity;
	}

	public void setBaseDexterity(int dex) {
		int change = dex - baseDexterity;
		baseDexterity = dex;
		dexterity += change;
	}

	public int getBaseIntelligence() {
		return baseIntelligence;
	}

	public void setBaseIntelligence(int intel) {
		int change = intel - baseIntelligence;
		baseIntelligence = intel;
		intelligence += change;
	}

	public int strength() {
		return strength + strengthEquipBonus();
	}

	public int dexterity() {
		return dexterity + dexterityEquipBonus();
	}

	public int intelligence() {
		return intelligence + intelligenceEquipBonus();
	}

	public void addModifier(int str, int dex, int intel, int armour, int negation) {
		strength += str;
		dexterity += dex;
		intelligence += intel;
		armourModifier += armour;
		negationModifier += negation;
	}

	public void removeModifier(int str, int dex, int intel, int armour, int negation) {
		addModifier(-1 * str, -1 * dex, -1 * intel, -1 * armour, -1 * negation);
	}

	public void rest() {
		healthPoints = vidaMaxima();
		magicPoints = magiaMaxima();

		strength = baseStrength;
		dexterity = baseDexterity;
		intelligence = baseIntelligence;
		armourModifier = 0;
		negationModifier = 0;
	}

	public void use(Item item) {
		item.getUseFunction().accept(this);
	}

	public boolean canEquip(final Equip equip) {
		return equip.canBeEquiped(this);
	}

	public int armour() {
		return equipBonus(Equip::armourBonus) + armourModifier;
	}

	public int negation() {
		return equipBonus(Equip::negationBonus) + negationModifier;
	}

	public int strengthEquipBonus() {
		return equipBonus(Equip::strengthBonus);
	}

	public int dexterityEquipBonus() {
		return equipBonus(Equip::dexterityBonus);
	}

	public int intelligenceEquipBonus() {
		return equipBonus(Equip::intelligenceBonus);
	}

	public int attackEquipBonus() {
		return equipBonus(Equip::attackBonus);
	}

	public int spellEquipBonus() {
		return equipBonus(Equip::spellBonus);
	}

	public int healthEquipBonus() {
		return equipBonus(Equip::healthBonus);
	}

	public int magicEquipBonus() {
		return equipBonus(Equip::magicBonus);
	}

	public Equip getEquipAt(int position) {
		return equipment[position];
	}

	public boolean isEquipping(final Equip item) {
		for (Equip equip : equipment) {
			if (item == null && equip == null) {
				return true;
			} else if (item != null && item.equals(equip)) {
				return true;
			}
		}
		return false;
	}

	public boolean equip(final Equip item) {
		if (!canEquip(item)) {
			return false;
		}

		try {
			if (getEquipAt(item.subtype()) != null) {
				return false;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ArrayIndexOutOfBoundsException("Item ID equip subtype indicator digit is invalid.");
		}

		equipment[item.subtype()] = item;
		return true;
	}

	public void unequip(int position) {
		equipment[position] = null;
	}

	public boolean unequip(final Equip item) {
		if (item == null || !isEquipping(item)) {
			return false;
		}

		unequip(item.subtype());
		return true;
	}

	public String toString() {
		return String.format(
				"%s" + "%s - LVL %d %s\n" + "HP: %d / %d\n" + "MP: %d / %d\n" + "STR: %d (%d) (%d)\tAD: %d\n"
						+ "DEX: %d (%d) (%d)\n" + "INT: %d (%d) (%d)\tSD: %d\n"
						+ "Armadura: %d (%d)\tNegação Mágica: %d (%d)",
				isDead() ? "[MORTO]\n" : "", nome, level, classe, vida(), vidaMaxima(), magia(), magiaMaxima(),
				strength(), strengthEquipBonus(), strength() - (baseStrength + strengthEquipBonus()), ataqueFisico(),
				dexterity(), dexterityEquipBonus(), dexterity() - (baseDexterity + dexterityEquipBonus()),
				intelligence(), intelligenceEquipBonus(),
				intelligence() - (baseIntelligence + intelligenceEquipBonus()), danoMagico(), armour(), armourModifier,
				negation(), negationModifier);
	}

	private void setHealth(int hp) {
		healthPoints = limit(hp, 0, vidaMaxima());
	}

	private void setMagic(int mp) {
		magicPoints = limit(mp, 0, magiaMaxima());
	}

	private int equipBonus(Function<Equip, Integer> bonusFunction) {
		int total = 0;

		for (Equip item : equipment) {
			if (item == null) {
				continue;
			}
			total += bonusFunction.apply(item);
		}

		return total;
	}

	private static int limit(int value, int floor, int ceil) {
		if (value <= ceil && value >= floor) {
			return value;

		} else if (value < floor) {
			return floor;
		}

		return ceil;
	}

}
