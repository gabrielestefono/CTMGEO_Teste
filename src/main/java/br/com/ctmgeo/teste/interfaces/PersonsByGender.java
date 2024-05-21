package br.com.ctmgeo.teste.interfaces;

import jakarta.persistence.Tuple;

public class PersonsByGender {
	private String estado;

	private Integer quantidade;

	private String genero;

	public PersonsByGender(Tuple val) {
    this.estado = val.get("ESTADO").toString();
    this.quantidade = Integer.valueOf(val.get("TOTAL").toString());
		this.genero = val.get("GENERO").toString();
  }

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
}
