package org.formation.model;

public class Demande {

	private int montant;

	public Demande(int montant) {
		this.montant = montant;
	}
	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}
	@Override
	public String toString() {
		return "Demande [montant=" + montant + "]";
	}
}
