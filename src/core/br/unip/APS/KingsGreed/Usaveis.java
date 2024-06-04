package br.unip.APS.KingsGreed;

import br.unip.APS.KingsGreed.entities.Equip;
import br.unip.APS.KingsGreed.entities.Item;

public class Usaveis {

	Item healthPotion = new Item("Poção de Vida (Pequena)", Item.USE * 1000 + 100, "\tRecupera 50 HP", user -> {
		user.heal(50);
	});
	Item manaPotion = new Item("Poção de Mana (Pequena)", Item.USE * 1000 + 200, "\tRecupera 50 MP", user -> {
		user.restore(50);
	});

	Item shadowkatana = new Equip("Shadow Katana", Item.EQUIP * 1000 + Item.EQUIP_WEAPON * 100 + 10,
			"\tApenas para Ninja Lvl 3+.\n" + "\t +30 de dano \n +8 de velocidade", 0, 8, 0, // primary stat bonuses
			+30, 0, 0, // damage bonuses
			0, 0, // reduction bonuses
			0, 0, // vitality bonuses
			3, "Ninja" // level/role requirements
	);

	Item grimorio = new Equip("Grimorio da Druida", Item.EQUIP * 1000 + Item.EQUIP_WEAPON * 100 + 20,
			"\tApenas para Feiticeiro Lvl 3+.\n" + "\t +30 de dano \n +5 de dano magico ", 0, 0, 5, // primary stat
																									// bonuses
			+30, 0, 0, // damage bonuses
			0, 0, // reduction bonuses
			0, 0, // vitality bonuses
			3, "Feitiçeiro" // level/role requirements
	);

	Item thorngloves = new Equip("Thorn Gloves", Item.EQUIP * 1000 + Item.EQUIP_WEAPON * 100 + 00,
			"\tApenas para Pugilista Lvl 4+.\n" + "\t +30 de dano \n+5 de velocidade de atk ", 0, 5, 0, // primary stat
																										// bonuses
			+30, 0, 0, // damage bonuses
			0, 0, // reduction bonuses
			0, 0, // vitality bonuses
			3, "Pugilista" // level/role requirements
	);

	Item armor = new Equip("Armadura", Item.EQUIP * 1000 + Item.EQUIP_HAND * 100 + 00,
			"\t Apenas para guerreiros.\n\tDEX 0\n\tArmadura +20\n\tHP +35", 0, -2, 0, // primary stat bonuses
			+100, 0, 0, // damage bonuses
			+20, 0, // reduction bonuses
			+35, 0, // vitality bonuses
			1, null // level/role requirements
	// 0, 0, 0 stat requirements
	);

}
