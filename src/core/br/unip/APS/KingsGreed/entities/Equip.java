package br.unip.APS.KingsGreed.entities;

public class Equip extends Item implements Cloneable {

	private int strengthBonus, dexterityBonus, intelligenceBonus = 0;
	private int attackBonus, spellBonus, criticalBonus = 0;
	private int armourBonus, negationBonus = 0;
	private int healthBonus, magicBonus = 0;

	private int levelRequirement = 0;
	private String roleRequirement = "";

	public Equip(final String nome, int id, final String description) {
		super(nome, id, description);
		super.setUseFunction(user -> {
			if (user.isEquipping(this)) {
				user.unequip(this);
			} else {
				user.equip(this);
			}
		});
	}

	public Equip(final String nome, int id, final String description, int strengthBonus, int dexterityBonus,
			int intelligenceBonus, int attackBonus, int spellBonus, int criticalBonus, int armourBonus,
			int negationBonus, int healthBonus, int magicBonus, int levelRequirement, final String roleRequirement

	) {
		this(nome, id, description);
		this.strengthBonus = strengthBonus;
		this.dexterityBonus = dexterityBonus;
		this.intelligenceBonus = intelligenceBonus;
		this.attackBonus = attackBonus;
		this.spellBonus = spellBonus;
		this.criticalBonus = criticalBonus;
		this.armourBonus = armourBonus;
		this.negationBonus = negationBonus;
		this.healthBonus = healthBonus;
		this.magicBonus = magicBonus;

		this.levelRequirement = levelRequirement;
		setRoleRequirement(roleRequirement);

	}

	public Equip(final Equip other) {
		this(other.name(), other.id(), other.description(), other.strengthBonus, other.dexterityBonus,
				other.intelligenceBonus, other.attackBonus, other.spellBonus, other.criticalBonus, other.armourBonus,
				other.negationBonus, other.healthBonus, other.magicBonus, other.levelRequirement, other.roleRequirement

		);
		setUseFunction(other.getUseFunction());
	}

	public boolean canBeEquiped(final Personagem personagem) {
		return personagem.level() >= levelRequirement
				&& personagem.getClasse().toLowerCase().contains(roleRequirement.toLowerCase());
	}

	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public int strengthBonus() {
		return strengthBonus;
	}

	public void setStrengthBonus(int strengthBonus) {
		this.strengthBonus = strengthBonus;
	}

	public int dexterityBonus() {
		return dexterityBonus;
	}

	public void setDexterityBonus(int dexterityBonus) {
		this.dexterityBonus = dexterityBonus;
	}

	public int intelligenceBonus() {
		return intelligenceBonus;
	}

	public void setIntelligenceBonus(int intelligenceBonus) {
		this.intelligenceBonus = intelligenceBonus;
	}

	public int attackBonus() {
		return attackBonus;
	}

	public void setAttackBonus(int attackBonus) {
		this.attackBonus = attackBonus;
	}

	public int spellBonus() {
		return spellBonus;
	}

	public void setSpellBonus(int spellBonus) {
		this.spellBonus = spellBonus;
	}

	public int armourBonus() {
		return armourBonus;
	}

	public void setArmourBonus(int armourBonus) {
		this.armourBonus = armourBonus;
	}

	public int negationBonus() {
		return negationBonus;
	}

	public void setNegationBonus(int negationBonus) {
		this.negationBonus = negationBonus;
	}

	public int healthBonus() {
		return healthBonus;
	}

	public void setHealthBonus(int healthBonus) {
		this.healthBonus = healthBonus;
	}

	public int magicBonus() {
		return magicBonus;
	}

	public void setMagicBonus(int magicBonus) {
		this.magicBonus = magicBonus;
	}

	public int levelRequirement() {
		return levelRequirement;
	}

	public void setLevelRequirement(int levelRequirement) {
		this.levelRequirement = levelRequirement;
	}

	public String roleRequirement() {
		return roleRequirement;
	}

	public void setRoleRequirement(final String roleRequirement) {
		this.roleRequirement = roleRequirement != null ? roleRequirement : "";
	}
}
