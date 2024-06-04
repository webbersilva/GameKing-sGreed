package br.unip.APS.KingsGreed.entities;

import java.lang.Math;

import br.unip.APS.array.Arrays;

public class Inventario {

	private Espaços_inventario[] content;

	public Inventario(int size) {
		content = new Espaços_inventario[size];

		for (int i = 0; i < content.length; ++i) {
			content[i] = new Espaços_inventario();
		}
	}

	public Inventario() {
		this(Item.TYPES);
	}

	public int size() {
		return content.length;
	}

	public void setSize(int newSize) {
		sort();

		Espaços_inventario[] newContent = new Espaços_inventario[newSize];

		for (int i = 0; i < newContent.length; ++i) {
			if (i < content.length) {
				newContent[i] = content[i];
			} else {
				newContent[i] = new Espaços_inventario();
			}
		}

		content = newContent;
	}

	public Item get(int index) {
		return content[index].item();
	}

	public int getStack(int index) {
		return content[index].stack();
	}

	public void swap(int firstIndex, int secondIndex) {
		Espaços_inventario temp = content[firstIndex];
		content[firstIndex] = content[secondIndex];
		content[secondIndex] = temp;
	}

	public boolean add(final Item item, int number) {
		if (number <= 0 || item == null) {
			return false;
		}

		int count = number;

		for (int s = 0; s < content.length && count > 0; ++s) {
			if ((!content[s].isEmpty() && !content[s].contains(item)) || content[s].isFull()) {
				continue;
			}

			int change = 0;

			if (content[s].isEmpty()) {
				change = Math.min(count, item.maxCarry());
			} else if (content[s].contains(item)) {
				change = Math.min(count, item.maxCarry() - content[s].stack());
			}

			content[s].add(item, change);
			count -= change;
		}

		if (count < number) {
			return true;
		} else {
			return false;
		}
	}

	public boolean add(final Item item) {
		return add(item, 1);
	}

	public void remove(int index) {
		content[index].clear();
	}

	public void remove(int index, int number) {
		content[index].remove(number);
	}

	public boolean remove(final Item item) {
		int index = indexOf(item);

		if (index < 0) {
			return false;
		} else {
			remove(index, 1);
			return true;
		}
	}

	public void remove(final Item item, int number) {
		if (number <= 0) {
			return;
		}

		int removed = 0;
		while (removed < number) {
			if (!remove(item)) {
				break;
			}
			++removed;
		}
	}

	public void sort() {
		Arrays.bubbleSort(content);
		Arrays.reverse(content);
	}

	public boolean contains(int itemID) {
		for (Espaços_inventario slot : content) {
			if (slot.contains(itemID)) {
				return true;
			}
		}
		return false;
	}

	public boolean contains(final Item item) {
		return (item != null) ? contains(item.id()) : contains(item, 1);
	}

	public boolean contains(final Item item, int number) {
		return count(item) >= number;
	}

	public int indexOf(final Item item) {
		for (int i = 0; i < content.length; ++i) {
			if (content[i].contains(item)) {
				return i;
			}
		}
		return -1;
	}

	public int indexOf(int itemID) {
		for (int i = 0; i < content.length; ++i) {
			if (content[i].contains(itemID)) {
				return i;
			}
		}
		return -1;
	}

	public boolean isEmpty() {
		for (Espaços_inventario slot : content) {
			if (!slot.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public String toString() {
		String list = "";

		for (int s = 0; s < content.length; ++s) {
			if (!content[s].isEmpty()) {
				list += String.format("[%d] %s\n%s\n", s, content[s].toString(), content[s].item().description());
			}
		}

		return list;
	}

	private int count(final Item item) {
		int n = 0;
		for (Espaços_inventario slot : content) {
			if (slot.contains(item)) {
				n += slot.stack();
			}
		}
		return n;
	}

}
