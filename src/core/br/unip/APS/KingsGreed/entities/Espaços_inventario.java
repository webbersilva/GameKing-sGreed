package br.unip.APS.KingsGreed.entities;

public class Espaços_inventario implements Comparable<Espaços_inventario> {

	private Item item = null;
	private int stack = 0;

	public Espaços_inventario() {
	}

	public Espaços_inventario(final Item item, int number) {
		this.item = item;
		stack = number;
	}

	public Espaços_inventario(final Espaços_inventario other) {
		this(other.item != null ? new Item(other.item) : null, other.stack);
	}

	public Item item() {
		return item;
	}

	public int stack() {
		return stack;
	}

	public boolean isEmpty() {
		return item == null && stack == 0;
	}

	public boolean isFull() {
		if (isEmpty()) {
			return false;
		} else {
			return (stack >= item.maxCarry()) ? true : false;
		}
	}

	public void clear() {
		item = null;
		stack = 0;
	}

	public void remove(int number) {
		if (isEmpty())
			return;

		if (number >= 0) {
			stack -= number;

			if (stack <= 0) {
				clear();
			}

		} else {
			add(-1 * number);
		}
	}

	public void add(int number) {
		if (isEmpty())
			return;

		if (number >= 0) {
			stack += number;

			if (stack > item.maxCarry()) {
				stack = item.maxCarry();
			}

		} else {
			remove(-1 * number);
		}
	}

	public void add(final Item item, int number) {
		if (item == null) {
			clear();
			return;
		}

		if (!item.equals(this.item)) {
			clear();
			this.item = item;
		}

		add(number);
	}

	public boolean contains(final Item item) {
		return item == null ? isEmpty() : item.equals(this.item);
	}

	public boolean contains(int itemID) {
		return !isEmpty() ? item.id() == itemID : false;
	}

	public String toString() {
		if (isEmpty()) {
			return "EMPY";
		} else {
			return String.format("x%d %s", stack, item.name());
		}
	}

	public boolean equals(final Espaços_inventario otherSlot) {
		return otherSlot == null ? false : contains(otherSlot.item) && stack == otherSlot.stack;
	}

	public int compareTo(final Espaços_inventario otherSlot) {
		if (isEmpty()) {
			return otherSlot.isEmpty() ? 0 : -1;

		} else if (otherSlot.isEmpty()) {
			return 1;

		} else {
			int i = item.compareTo(otherSlot.item);
			return i == 0 ? Integer.valueOf(stack).compareTo(Integer.valueOf(otherSlot.stack)) : i;
		}
	}

}
