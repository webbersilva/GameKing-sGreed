package br.unip.APS.KingsGreed;

import br.unip.APS.KingsGreed.entities.Personagem;
import br.unip.APS.KingsGreed.entities.Inventario;

public class Sala {

	private static final int DIRECOES = 4;
	public static final int NORTE = 0;
	public static final int LESTE = 1;
	public static final int SUL = 2;
	public static final int OESTE = 3;

	private String descricao;
	private Sala[] adjacente;
	private Inventario loot = new Inventario(20);
	private Personagem ocupante;
	public int salaAtual;

	public Sala(final String descricao, final Sala[] adjacente, final Personagem ocupante, final int salaAtual) {
		setDescricao(descricao);
		this.adjacente = adjacente;
		this.ocupante = ocupante;
		this.salaAtual = salaAtual;
	}

	public Sala(final String description, final Sala[] adjacent, final int LocalRoom) {
		this(description, adjacent, null, LocalRoom);
	}

	public Sala(final String description, final int LocalRoom) {
		this(description, new Sala[DIRECOES], LocalRoom);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(final String descricao) {
		this.descricao = (descricao == null) ? "" : descricao;
	}

	public Sala getAdjacente(int direcao) {
		return adjacente[direcao];
	}

	public void setAdjacente(final Sala sala, int direcao) {
		adjacente[direcao] = sala;
	}

	public Inventario loot() {
		return loot;
	}

	public Personagem getOcupante() {
		return ocupante;
	}

	public void setOcupante(final Personagem ocupant) {
		this.ocupante = ocupant;
	}

	public int getSalaAtual() {
		return salaAtual;
	}

	public void setSalaAtual(int SalaAtual) {
		salaAtual = SalaAtual;
	}

}