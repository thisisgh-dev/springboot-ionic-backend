package com.guilherme.springbootionicbackend.domain.enums;

public enum ClientType {

	NATURALPERSON(1, "Pessoa Física"), JURIDICALPERSON(2, "Pessoa Jurídica");

	private int cod;
	private String description;

	private ClientType(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}

	public static ClientType toEnum(Integer id) {
		if (id == null) {
			return null;
		}

		for (ClientType x : ClientType.values()) {
			if (id.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido " + id);

	}
}
