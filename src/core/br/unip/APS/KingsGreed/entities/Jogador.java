package br.unip.APS.KingsGreed.entities;

import java.util.regex.*;
import br.unip.APS.KingsGreed.statistics.*;

public class Jogador extends Personagem {

	private long experiencePoints = 0;
	private int attributePoints = 0;

	private Inventario inventario = new Inventario(15 * Item.TYPES); // NOTE: Default Jogador inventario size.

	public Jogador() {
		super("Hero", 1, "Adventurer", new NullStatCalculator(), 7, 6, 7); // NOTE: STATUS 5,5,5
	}

	public long getExpAtual() {
		return experiencePoints;
	}

	public long getExpProximoNivel() {
		return super.experiencia();
	}

	public void ganhoExp(long exp) {
		experiencePoints += exp;

		while (experiencePoints >= getExpProximoNivel()) {
			experiencePoints -= getExpProximoNivel();
			levelUp();
		}
	}

	public int getAttributePoints() {
		return attributePoints;
	}

	public boolean spendAttributePoint(String stat) {
		if (attributePoints < 1) {
			return false;
		}

		switch (stat) {
		case Personagem.STR:
			super.setForcaBase(super.getForcaBase() + 1);
			break;

		case Personagem.DEX:
			super.setBaseDexterity(super.getBaseDexterity() + 1);
			break;

		case Personagem.INT:
			super.setBaseIntelligence(super.getBaseIntelligence() + 1);
			break;

		default:
			throw new IllegalArgumentException("O atributo especificado não existe."); // TODO translate back
		// throw new IllegalArgumentException("Specified attribute doesn't exist.");
		}

		attributePoints--;
		return true;
	}

	public Inventario getInventory() {
		return inventario;
	}

	public void useItem(int inventoryIndex) {
		Item item = inventario.get(inventoryIndex);
		if (item == null) {
			return;
		}

		if (item instanceof Equip && canEquip((Equip) item)) {
			Equip previous = getEquipAt(item.subtype());

			unequip(previous);

			equip((Equip) item);
			inventario.remove(inventoryIndex, 1);

			inventario.add(previous);

		} else if (!(item instanceof Equip)) {
			super.use(item);
			inventario.remove(inventoryIndex, 1);
		}
	}

	public void setNome(String nome) {
		if (!Pattern.compile("^\\w{3,15}$").matcher(nome).matches()
				|| Pattern.compile(".*_{2,}.*").matcher(nome).matches()) {
			throw new IllegalArgumentException("Esse nome não é válido.");
		}
		super.setNome(nome);
	}

	public void levelUp() {
		attributePoints += 5;
		super.levelUp();
	}

	public String toString() {
		return String.format("%s\n" + "AP: %d\n" + "EXP: %d / %d", super.toString(), attributePoints, experiencePoints,
				getExpProximoNivel());
	}
}
