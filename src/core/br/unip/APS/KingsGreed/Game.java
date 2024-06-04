package br.unip.APS.KingsGreed;

public class Game {

	public static void main(String[] args) {
		// areas do jogo
		Sala inicio = new Sala("Você acorda com uma dor de cabeça enorme..."
				+ "Olhando ao redor você vê um senhor com uma aura místeriosa vindo em sua direção."
				+ "Ao chegar no seu campo de ação, você se prepara para lutar mas ele responde: \n"
				+ " VELHO MISTERIOSO:	\n"
				+ "Calma meu jovem, te bateram na cabeça, lembra do seu nome?, e como uma prova de que estou do seu lado escolha uma arma para se defender:\n"
				+ " Longe ao sul tem uma fumaça negra subindo ao ceús, poderia é ver para mim o que é?\n"
				+ "DICA: as direções que podem ser escolhidas são norte, sul, leste e oeste.\n", 1);

		Sala cabana_incendiada = new Sala("Cabana Incendiada.\n"
				+ "Adentrando a estranha floresta, uma sensação familiar ao reconhecer a paisagem e uma angústia de que algo está errado te atinge.\n"
				+ "Ao longe é possível vem uma fumaça negra subindo aos céus, como se algo estivesse pegando fogo. Chegando mais perto, \n"
				+ "a dor da perda lhe atinge. Um aviso pregado nas árvores chama sua atenção. \n"
				+ "\t|   -------------------------------------------------- |\n"
				+ "\t|                                                      |\n"
				+ "\t|                                                      |\n"
				+ "\t|                                                      |\n"
				+ "\t|                                                      |\n"
				+ "\t|                     Ordens do rei    				|\n"
				+ "\t|            A casa a frente foi incendiada por        |\n"
				+ "\t|         desobedecer a uma ordem direta do rei.       |\n"
				+ "\t|             A propriedade foi confiscada.            |\n"
				+ "\t|            A próxima é a cabana ao leste.            |\n"
				+ "\t|                                                      |\n"
				+ "\t|                                                      |\n"
				+ "\t|_____________________________________________________ |\n" + "\n"
				+ "	O melhor a se fazer é ir avisar a cabana ao norte. \n"
				+ "Dica: Lute contra o adversário com o comando (lute) ou (enfrente)”\n", 2);

		Sala cabana_velha = new Sala("Cabana velha.\n" + "O misterioso velho abre a porta novamente e pergunta\n"
				+ "VELHO:\n" + "'Descobriu o que aconteceu?'"
				+ "Você conta que era a sua casa ao sul que estava pegando fogo e que os guardas estão a caminho."
				+ "O som de pessoas marchando se aproxima.", 3);

		Sala castelo_rei = new Sala(
				"Depois de se aventurar e ver a destruição que a ganância do rei proporcionou ao meio ambiente,\n"
						+ " rios poluídos, pradarias destruidas pelo lixo, animais mutantes por causa da poluição, \n"
						+ "você decide que é hora de isso tudo acabar.\n" + "REI:\n"
						+ "Então você conseguiu sobreviver a minha ordem de execução...\n"
						+ "É como diz o ditado, se quer algo bem feito, faça você mesmo!",
				0);

		Sala castelo_inicio = new Sala("Castelo do rei.\n" + "Guardas:\n" + "Como ele ainda está vivo?"
				+ "Os guardas vem ao seu encontro furiosamente!!!", 6);

		Sala entrada_caverna = new Sala("Seguindo o caminho da caverna você encontra um baú.\n"
				+ "\t|   -------------------------------------------------- |\n"
				+ "\t|                                                      |\n"
				+ "\t|                                                      |\n"
				+ "\t|                                                      |\n"
				+ "\t|                                                      |\n"
				+ "\t|                     Ordens do rei    				|\n"
				+ "\t|                 Não siga em frente!!         	    |\n"
				+ "\t|         		     Perigo iminente!	   		        |\n"
				+ "\t|            								            |\n"
				+ "\t|            								            |\n"
				+ "\t|                                                      |\n"
				+ "\t|                                                      |\n"
				+ "\t|_____________________________________________________ |\n" + "\n"
				+ "Voce chegou ao fundo da caverna , prossiga o caminho da sua aventura.", 8);

		Sala fundo_caverna = new Sala("Voce encontrou um lobo ancestral." + "Batalhe com ele para sair da caverna.\n",
				9);

		Sala rio_poluido = new Sala("Apos uma longa caminhada , voce chegou a um lugar fedido ,com um rio verde."
				+ "WOWW! Voce encontrou um Peixe radioativo, CUIDADO , ele parece ser hostil.\n"
				+ "Voce deve enfrenta-lo para passar do rio e chegar ao Rei.\n", 10);

		Sala pradaria = new Sala(
				"Seguindo a oeste voce esta numa planice de vegetação baixa , seguindo a diante voce avista um Galpão velho."
						+ "Apos voce entrar no local , consegue vizualizar um bau.\n"
						+ "Depois de coletar os recursos, voce continua explorando o local.\n"
						+ "WOW! Voce da de cara com um Bufalo Selvagem, ele parece habitar no local, lute com ele  para sair da Galpão!",
				11);
		Sala Sala_trono = new Sala(
				"Com a queda do rei, o país finalmente respira aliviado. O nobre guerreiro, com sua bravura e determinação, conseguiu limpar as ruas dos inimigos e restaurar a paz. \n"
						+ "Os cidadãos saem às ruas para comemorar e agradecer ao herói que lutou por eles. O país finalente tem suas ruas limpas novamente, e conseguem ver uma paisangem limpa e sem poluição.\n"
						+ "Isso graças ao nobre guerreiro!!",
				12);

		Inimigos criaturas = new Inimigos();
		Usaveis utilizaveis = new Usaveis();
		// Caminhos
		inicio.setAdjacente(cabana_incendiada, Sala.SUL);
		inicio.setAdjacente(castelo_inicio, Sala.NORTE); // castelo inicio

		cabana_velha.setAdjacente(cabana_incendiada, Sala.SUL);
		cabana_velha.setAdjacente(castelo_inicio, Sala.NORTE);

		castelo_inicio.setAdjacente(castelo_rei, Sala.NORTE);

		cabana_incendiada.setAdjacente(cabana_velha, Sala.NORTE);
		cabana_incendiada.setAdjacente(rio_poluido, Sala.LESTE);
		cabana_incendiada.setAdjacente(pradaria, Sala.OESTE);

		entrada_caverna.setAdjacente(fundo_caverna, Sala.SUL);
		entrada_caverna.setAdjacente(rio_poluido, Sala.NORTE);
		fundo_caverna.setAdjacente(entrada_caverna, Sala.NORTE);

		rio_poluido.setAdjacente(entrada_caverna, Sala.SUL);
		rio_poluido.setAdjacente(cabana_velha, Sala.NORTE);
		rio_poluido.setAdjacente(cabana_incendiada, Sala.OESTE);

		pradaria.setAdjacente(cabana_incendiada, Sala.LESTE);

		castelo_rei.setAdjacente(Sala_trono, Sala.NORTE);

		// Inimigos
		cabana_incendiada.setOcupante(criaturas.javali);
		cabana_velha.setOcupante(criaturas.Guardas_tochas);
		castelo_inicio.setOcupante(criaturas.Guarda_castelo_inicio);
		fundo_caverna.setOcupante(criaturas.loboancestral);
		rio_poluido.setOcupante(criaturas.peixeradioativo);
		pradaria.setOcupante(criaturas.bufaloselvagem);
		castelo_rei.setOcupante(criaturas.Rei);

		cabana_incendiada.loot().add(utilizaveis.healthPotion);
		cabana_incendiada.loot().add(utilizaveis.manaPotion);

		cabana_velha.loot().add(utilizaveis.healthPotion);
		cabana_velha.loot().add(utilizaveis.manaPotion);
		cabana_velha.loot().add(utilizaveis.armor);

		entrada_caverna.loot().add(utilizaveis.healthPotion);
		entrada_caverna.loot().add(utilizaveis.armor);

		fundo_caverna.loot().add(utilizaveis.healthPotion);
		fundo_caverna.loot().add(utilizaveis.manaPotion);
		fundo_caverna.loot().add(utilizaveis.shadowkatana);
		fundo_caverna.loot().add(utilizaveis.grimorio);
		fundo_caverna.loot().add(utilizaveis.thorngloves);
		fundo_caverna.loot().add(utilizaveis.armor);

		rio_poluido.loot().add(utilizaveis.healthPotion);
		rio_poluido.loot().add(utilizaveis.manaPotion);
		rio_poluido.loot().add(utilizaveis.armor);

		pradaria.loot().add(utilizaveis.healthPotion);
		pradaria.loot().add(utilizaveis.manaPotion);
		pradaria.loot().add(utilizaveis.armor);

		castelo_inicio.loot().add(utilizaveis.healthPotion);

		// instanciando objetos de controle do jogo
		Comandos_game cli = new Comandos_game();
		Corpogame world = new Corpogame(inicio, Sala_trono);
		Engine_game game = new Engine_game(world, cli);
		// roda o jogo
		game.comecar();

		// fim do programa
		System.exit(0);
	}

}
