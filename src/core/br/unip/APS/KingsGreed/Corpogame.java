package br.unip.APS.KingsGreed;


import br.unip.APS.KingsGreed.entities.Jogador;


/**
 * Contains the current state of the game.
 */
public class Corpogame {

	// ATTRIBUTES
	private Sala SalaAtual;
	private Jogador jogador;
	private Sala SalaFinal;


	// CONSTRUCTORS
	public Corpogame(
		final Sala start,
		final Sala end,
		final Jogador jogador
	) {
		this.SalaAtual = start;
		this.SalaFinal = end;
		this.jogador = jogador;
	}

	public Corpogame(final Sala start, final Sala end) {
		this(start, end, null);
	}


	// METHODS
	public Sala getSalaAtual() {
		return SalaAtual;
	}

	public void setSalaAtual(final Sala currentRoom) {
		this.SalaAtual = currentRoom;
	}

	/**
	 * @param direction - use Sala.DIRECTION as direction.
	 * @return true if the jogador could move to that direction.
	 */
	public boolean goToAdjacent(int direction) {
		try {
			if (SalaAtual.getAdjacente(direction) == null) {
				return false;
			} else {
				SalaAtual = SalaAtual.getAdjacente(direction);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		return true;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(final Jogador jogador) {
		this.jogador = jogador;
	}

	public boolean perdeu() {
		return jogador.isDead();
	}

	public boolean ganhou() {
		return SalaAtual == SalaFinal;
	}

	public Sala getSalaFinal() {
		return SalaFinal;
	}

	public void setSalaFinal(final Sala SalaFinal) {
		this.SalaFinal = SalaFinal;
	}

}
