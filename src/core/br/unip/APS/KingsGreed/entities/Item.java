package br.unip.APS.KingsGreed.entities;

import java.util.function.Consumer;

public class Item implements Comparable<Item>, Cloneable {

	public static final int TYPES = 3;
	public static final int ETC = 0;
	public static final int USE = 1;
	public static final int EQUIP = 2;

	public static final int EQUIP_SUBTYPES = 7;
	public static final int EQUIP_WEAPON = 0;
	public static final int EQUIP_BODY = 1;
	public static final int EQUIP_HEAD = 2;
	public static final int EQUIP_LEGS = 3;
	public static final int EQUIP_BACK = 4;
	public static final int EQUIP_HAND = 5;
	public static final int EQUIP_FEET = 6;

	private String name;
	private int id;
	private String description = "";

	private Consumer<Personagem> useFunction = u -> {
		return;
	};

	public Item(final String name, int id) {
		setName(name);
		this.id = id;
	}

	public Item(final String name, int id, final String description) {
		this(name, id);
		setDescription(description);
	}

	public Item(final String name, int id, final String description, Consumer<Personagem> useFunction) {
		this(name, id, description);
		this.useFunction = useFunction;
	}

	public Item(final Item other) {
		this(other.name, other.id, other.description, other.useFunction);
	}

	public int id() {
		return id;
	}

	public int type() {
		return id / 1000;
	}

	public int subtype() {
		return id % 1000 / 100;
	}

	public int num() {
		return id % 100;
	}

	public int maxCarry() {
		switch (type()) {
		case Item.EQUIP:
			return 1;

		case Item.USE:
			return 15;

		case Item.ETC:
		default:

			break;
		}

		return 30;
	}

	public String name() {
		return name;
	}

	public void setName(final String name) {
		if (name == null) {
			throw new NullPointerException("Item name can't be null.");
		}
		this.name = name;
	}

	public String description() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description != null ? description : "";
	}

	public Consumer<Personagem> getUseFunction() {
		return useFunction;
	}

	public void setUseFunction(Consumer<Personagem> useFunction) {
		this.useFunction = useFunction;
	}

	public String toString() {
		return String.format("[%d.%d.%d] %s\n" + "%s", type(), subtype(), num(), name, description);
	}

	public boolean equals(final Item otherItem) {
		return otherItem == null ? false : id == otherItem.id;
	}

	public int compareTo(final Item otherItem) {
		return Integer.valueOf(id).compareTo(Integer.valueOf(otherItem.id));
	}

	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
