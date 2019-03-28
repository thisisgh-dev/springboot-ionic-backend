package com.guilherme.springbootionicbackend.domain.enums;

public enum StatusPayment {

	PENDING(1, "Pendente"), SETTLED(2, "Quitado"), CANCELED(3, "Cancelado");
	private int cod;
	private String description;

	private StatusPayment(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}

	public static StatusPayment toEnum(Integer id) {
		if (id == null) {
			return null;
		}

		for (StatusPayment x : StatusPayment.values()) {
			if (id.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("ID invalid " + id);

	}
}
