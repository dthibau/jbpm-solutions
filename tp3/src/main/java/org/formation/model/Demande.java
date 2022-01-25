package org.formation.model;

import java.io.Serializable;

public class Demande implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
