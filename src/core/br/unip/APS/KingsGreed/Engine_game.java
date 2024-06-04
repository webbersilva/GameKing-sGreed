package br.unip.APS.KingsGreed;

import java.lang.Math;

import br.unip.APS.KingsGreed.entities.*;
import br.unip.APS.KingsGreed.entities.Personagem;

public class Engine_game {

	private static final int INTENCAO_NAO_ENCONTRADA = -1, INTENCAO_AJUDA = 0, INTENCAO_EXAMINAR = 1,
			INTENCAO_MOVER = 2, INTENCAO_PERSONAGEM = 3, INTENCAO_INVENTARIO = 4, INTENCAO_EQUIPMENTO = 5,
			INTENCAO_ITEM_USAR = 6, INTENCAO_DESEQUIPAR = 7, INTENCAO_PEGAR_ITEM = 8, INTENCAO_LUTAR = 9,
			INTENCAO_GASTAR_AP = 10;

	private Corpogame game;
	private Comandos_game view;

	private boolean wantsToExit;

	public Engine_game(final Corpogame game, final Comandos_game view) {
		this.game = game;
		this.view = view;
	}

	public void comecar() {
		view.greet();
		view.show(game.getSalaAtual());
		game.setJogador(view.createPlayer());
		wantsToExit = false;
		while (!wantsToExit && !game.perdeu()) {
			if (game.ganhou()) {
				view.showMessage("\tFim.\n" + "\tEste é o fim.\n");
				view.getUserCommand();
				view.showMessage("Parabéns! Você zerou o jogo!\n");
				break;

			} else {
				ComandoConsole(view.getUserCommand().toLowerCase());
			}
		}

		view.bye();
	}

	public void nome() {
		if (game.getJogador() == null) {
			game.setJogador(view.createPlayer());
		}
	}

	private void ComandoConsole(final String command) {
		if (command.contains("sair")) {
			wantsToExit = true;

		} else if (command.contains("ajud") || (command.contains("Help"))) {
			executarComando(INTENCAO_AJUDA, command);

		} else if (command.contains("examin") || command.contains("olhar") || command.contains("olhe")
				|| command.contains("ambiente")) {
			executarComando(INTENCAO_EXAMINAR, command);

		} else if (command.contains("persona")) {
			executarComando(INTENCAO_PERSONAGEM, command);

		} else if (command.contains("itens") || command.contains("inventario") || command.contains("inventário")
				|| command.contains("inventorio") || command.contains("inventório")) {
			executarComando(INTENCAO_INVENTARIO, command);

		} else if (command.matches("desequipar \\d{1,}")) {
			executarComando(INTENCAO_DESEQUIPAR, Integer.parseInt(command.substring(11)));

		} else if (command.contains("equip")) {
			executarComando(INTENCAO_EQUIPMENTO, command);

		} else if (command.contains("norte")) {
			executarComando(INTENCAO_MOVER, Sala.NORTE);

		} else if (command.contains("leste")) {
			executarComando(INTENCAO_MOVER, Sala.LESTE);

		} else if (command.contains("sul")) {
			executarComando(INTENCAO_MOVER, Sala.SUL);

		} else if (command.contains("oeste")) {
			executarComando(INTENCAO_MOVER, Sala.OESTE);

		} else if (command.matches("pegar \\d{1,}")) {
			executarComando(INTENCAO_PEGAR_ITEM, Integer.parseInt(command.substring(6)));

		} else if (command.matches("usar \\d{1,}")) {
			executarComando(INTENCAO_ITEM_USAR, Integer.parseInt(command.substring(5)));

		} else if (command.contains("atacar") || command.contains("luta") || command.contains("lute")
				|| command.contains("bater")) {
			executarComando(INTENCAO_LUTAR, command);

		} else if (command.matches("aumentar [a-z]{3}")) {
			executarComando(INTENCAO_GASTAR_AP, command.substring(9, 12).toUpperCase());

		} else {
			executarComando(INTENCAO_NAO_ENCONTRADA, command);
		}
	}

	private void executarComando(int intention, Object action) {
		switch (intention) {
		case INTENCAO_AJUDA:
			view.showMessage("- Se locomova pelas direções: Norte, Sul, Leste, Oeste. Ex: \"corra para o leste\".\n"
					+ "- Tente o comando 'olhar' para ver o que está acontecendo.\n" + "- Para ver os status do .\n"
					+ "- Para pegar um item do chão, digite \"pegar x\" e ele será guardado no seu \"inventário\".\n"
					+ "- Para usar um de seus \"itens\", digite \"usar x\". Usar um de seus \"equipamentos\" o equipa.\n"
					+ "- Para desequipar um item, digite \"desequipar x\". DICA: 'x' refere-se a um [índice] indicado pelo jogo.\n"
					+ "- Se quiser desistir, basta pedir para sair :)");
			break;

		case INTENCAO_EXAMINAR:
			view.show(game.getSalaAtual());
			break;

		case INTENCAO_PERSONAGEM:
			view.showMessage(game.getJogador().toString());
			break;

		case INTENCAO_INVENTARIO:
			if (game.getJogador().getInventory().isEmpty()) {
				view.showMessage("Seu inventário está vazio.");
			} else {
				view.showMessage(game.getJogador().getInventory().toString());
			}
			break;

		case INTENCAO_EQUIPMENTO:
			String equips = "";

			for (int e = 0; e < Item.EQUIP_SUBTYPES; ++e) {
				Equip equip = game.getJogador().getEquipAt(e);
				if (equip == null) {
					continue;
				} else {
					equips += String.format("[%d] %s\n%s\n", e, equip.name(), equip.description());
				}
			}

			if (equips.equals("")) {
				view.showMessage("Não há nada equipado.");
			} else {
				view.showMessage(equips);
			}
			break;

		case INTENCAO_MOVER:
			if (game.getSalaAtual().getOcupante() != null) {
				view.showMessage("Você não pode fugir sem lutar.");
			} else if (!game.goToAdjacent((int) action)) {
				view.showMessage("Você não pode ir para lá.");
			} else {
				view.show(game.getSalaAtual());
			}
			break;

		case INTENCAO_PEGAR_ITEM:
			try {
				Item item = game.getSalaAtual().loot().get((int) action);

				if (item == null || game.getSalaAtual().getOcupante() != null) {
					throw new NullPointerException();
				}

				if (game.getJogador().getInventory().add(item)) {
					view.showMessage(item.name() + " foi adicionado ao inventário.");
					game.getSalaAtual().loot().remove((int) action, 1);
				} else {
					view.showMessage("Seu inventário já está cheio!");
				}

			} catch (Exception e) {
				view.showMessage("Item não encontrado.");
			}
			break;

		case INTENCAO_ITEM_USAR:
			try {
				Item item = game.getJogador().getInventory().get((int) action);

				if (item == null) {
					throw new NullPointerException();
				}

				if (item instanceof Equip && !game.getJogador().canEquip((Equip) item)) {
					view.showMessage("Você não possui os requisitos necessários para equipar " + item.name() + ".");
				} else {
					game.getJogador().useItem((int) action);
					view.showMessage(item.name() + " usado com sucesso!");
				}

			} catch (Exception e) {
				view.showMessage("Esse item não está no seu inventário.");
			}
			break;

		case INTENCAO_DESEQUIPAR:
			try {
				Equip equip = game.getJogador().getEquipAt((int) action);

				if (game.getJogador().unequip(equip)) {
					game.getJogador().getInventory().add(equip);
					view.showMessage(equip.name() + " foi desequipado e colocado no inventário.");
				} else {
					throw new NullPointerException();
				}

			} catch (Exception e) {
				view.showMessage("Equipamento não encontrado.");
			}
			break;

		case INTENCAO_LUTAR:
			if (game.getSalaAtual().getOcupante() == null) {
				view.showMessage("Mas não há ninguém aqui...");
			} else {
				luta(game.getJogador(), game.getSalaAtual().getOcupante());
			}
			break;

		case INTENCAO_GASTAR_AP:
			try {
				if (game.getJogador().spendAttributePoint((String) action)) {
					view.showMessage("Você gastou 1 AP.");
				} else {
					view.showMessage("Você não tem mais pontos de atributo para gastar.");
				}
			} catch (Exception e) {
				view.showMessage(e.getMessage());
			}
			break;
		case INTENCAO_NAO_ENCONTRADA:
		default:
			view.showMessage("Não entendi o que você disse. Tente pedir \"ajuda\".");
			break;
		}
	}

	private void luta(Jogador jogador, Personagem enemy) {
		// First Strike is calculated based on dexterity
		if (jogador.dexterity() > enemy.dexterity()) {
			enemy.wound(calcularDano(jogador, enemy));
			if (!enemy.isDead()) {
				jogador.wound(calcularDano(enemy, jogador));
			}

		} else if (jogador.dexterity() < enemy.dexterity()) {
			jogador.wound(calcularDano(enemy, jogador));
			if (!jogador.isDead()) {
				enemy.wound(calcularDano(jogador, enemy));
			}

		} else {
			jogador.wound(calcularDano(enemy, jogador));
			enemy.wound(calcularDano(jogador, enemy));
		}

		if (game.perdeu()) {
			view.showMessage("Você morreu!");
			return;
		}

		if (enemy.isDead()) {
			view.showMessage(enemy.getNome() + " está morto!");
			game.getSalaAtual().setOcupante(null);

			int level = jogador.level();
			jogador.ganhoExp(enemy.experiencia());

			if (jogador.level() != level) {
				view.showMessage("Level Up!\nDigite \"aumentar ATT\" para gastar seus novos pontos de atributo (AP).");
			}
		}
	}

	private int calcularDano(final Personagem agressor, final Personagem alvo) {
		String descricao = agressor.getNome();

		int feitico, reducao;
		if (agressor.danoMagico() >= agressor.ataqueFisico() && agressor.magia() >= 80) {
			feitico = agressor.danoMagico();
			reducao = alvo.negation();
			alvo.deplete(80);
			descricao += " joga uma bola de fogo em ";

		} else {
			feitico = agressor.ataqueFisico();
			reducao = alvo.armour();
			descricao += " ataca violentamente ";
		}

		descricao += alvo.getNome() + ", causando ";

		int dano = Math.max(feitico - reducao, (int) (feitico * 0.2));

		descricao += dano + " de dano.";

		view.showMessage(descricao);
		return dano;
	}

}
