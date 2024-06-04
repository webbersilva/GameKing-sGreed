/**
 * 
 */
package br.unip.APS.KingsGreed;

import br.unip.APS.KingsGreed.entities.Personagem;
import br.unip.APS.KingsGreed.statistics.MonsterCalculator;

/**
 * @author gustavo
 *
 */
public class Inimigos {

	Personagem javali = new Personagem("Javali corrompido", 1, "Monstro corrompido", new MonsterCalculator(), 3, 5, 0 // str,
																														// dex,
																														// int
	);

	Personagem Guardas_tochas = new Personagem("Guarda real", 2, "Humano", new MonsterCalculator(), 5, 2, 0);

	Personagem Guarda_castelo_inicio = new Personagem("Guarda do castelo", 8, "Humano", new MonsterCalculator(), 7, 10,
			6 // str, dex, int
	);
	Personagem Rei = new Personagem("REI", 12, "Pugilist", new MonsterCalculator(), 20, 5, 0);

	Personagem loboancestral = new Personagem("Lobo Ancestral", 5, "Monstro comrrompido", new MonsterCalculator(), 8,
			10, 6 // str, dex, int
	);
	Personagem peixeradioativo = new Personagem("Peixe Radioativo ", 4, "Monstro comrrompido", new MonsterCalculator(),
			4, 9, 7 // str, dex, int
	);
	Personagem bufaloselvagem = new Personagem("Bufalo Selvagem ", 3, "Monstro comrrompido", new MonsterCalculator(), 4,
			6, 2 // str, dex, int
	);
}
