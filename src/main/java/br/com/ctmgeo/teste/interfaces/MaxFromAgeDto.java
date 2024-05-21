package br.com.ctmgeo.teste.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Tuple;

public class MaxFromAgeDto {

	private String state;

	private Integer total;

	@JsonIgnore
	private String type;

	public MaxFromAgeDto(Tuple tuple) {
		this.state = tuple.get("ESTADO").toString();
		this.total = Integer.valueOf(tuple.get("COUNT").toString());
		this.type = tuple.get("TIPO").toString();
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
