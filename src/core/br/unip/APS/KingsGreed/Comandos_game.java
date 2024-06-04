package br.unip.APS.KingsGreed;

import java.util.InputMismatchException;
import java.util.Scanner;

import br.unip.APS.KingsGreed.entities.Jogador;
import br.unip.APS.KingsGreed.statistics.*;

public class Comandos_game extends Game {

	private Scanner read = new Scanner(System.in);

	public void show(final Sala sala) {
		String content = "";

		if (sala.getOcupante() != null) {
			content = "\nUm " + sala.getOcupante().getNome() + " se move rapidamente na sua direção.\n";
		} else if (!sala.loot().isEmpty()) {
			if (sala.getSalaAtual() == 2) {
				content = "\nAlguns itens não viram cinzas:\n" + sala.loot().toString();
			} else if (sala.getSalaAtual() == 8) {
				content = "\nVocê abre o baú e encontra:\n" + sala.loot().toString();
			} else {

				content = "\nVocê também vê alguns itens espalhados pelo chão:\n" + sala.loot().toString();

			}
		}
		System.out.print(String.format("\n\n%s\n" + "%s", sala.getDescricao(), content));
	}

	public String getUserCommand() {
		System.out.print("\n> ");
		return read.nextLine();
	}

	public void showMessage(final String message) {
		System.out.println("\n" + message);
	}

	public void greet() {
		System.out
				.println(" d8b         d8,                                                                       d8b\n"
						+ " ?88                                                                                   88P\n"
						+ "  888  d88'  88b  88bd88b  d888b8b   .d888b,     d888b8b    88bd88b d8888b d8888b d888888\n"
						+ "  888bd8P'   88P  88P' ?8bd8P' ?88   ?8b,       d8P' ?88    88P'  `d8b_,dPd8b_,dPd8P' ?88\n"
						+ " d88888b    d88  d88   88P88b  ,88b    `?8b     88b  ,88b  d88     88b    88b    88b  ,88b\n"
						+ "d88' `?88b,d88' d88'   88b`?88P'`88b`?888P'     `?88P'`88bd88'     `?888P'`?888P'`?88P'`88b\n"
						+ "                                 )88                   )88\n"
						+ "                                ,88P                  ,88P\n"
						+ "                            `?8888P               `?8888P\n" + "");
	}

	/**
	 * @return User-created Jogador.
	 */
	public Jogador createPlayer() {
		Jogador jogador = new Jogador();

		// name

		jogador.setStatCalculator(new SorcererCalculator());
		boolean hasSetName = false;
		while (!hasSetName) {
			System.out.print("\nLembra do seu nome? (Escolha seu nome)\n> ");

			try {
				jogador.setNome(read.nextLine());
				hasSetName = true;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				continue;
			}
		}

		// role
		boolean hasChoosenRole = false;
		while (!hasChoosenRole) {
			System.out.print("\nEscolha uma arma\n" + " [1] Cajado incrustrado (Feitiçeiro)\n"
					+ " [2] Luvas dracônicas (Pugilista)\n" + " [3] Armas ancestrais (Ninja)\n" + "> ");

			int role = 0;

			try {
				role = read.nextInt();
				read.nextLine();
			} catch (InputMismatchException e) {
				read.nextLine();
				continue;
			}

			switch (role) {
			case 1:
				jogador.setClasse("Feitiçeiro"); // Mago
				jogador.setStatCalculator(new SorcererCalculator());
				break;

			case 2:
				jogador.setClasse("Pugilista");// Guerreiro
				jogador.setStatCalculator(new PugilistCalculator());
				break;

			case 3:
				jogador.setClasse("Ninja");// Ladino
				jogador.setStatCalculator(new NinjaCalculator());
				break;

			default:
				continue;
			}
			hasChoosenRole = true;
		}

		return jogador;
	}

	public void bye() {
		System.out.print("\n\nSaindo do mundo de King's Greed...\n\n" + "Pressione ENTER para terminar o programa\n> ");
		read.nextLine();
	}

}
