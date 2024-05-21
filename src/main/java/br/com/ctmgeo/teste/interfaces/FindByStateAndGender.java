package br.com.ctmgeo.teste.interfaces;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

public class FindByStateAndGender {
	private String state;
	private final Map<String, Integer> genderCounts;

	public FindByStateAndGender(String state, Map<String, Integer> genderCounts) {
		this.state = state;
		this.genderCounts = genderCounts;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@JsonAnyGetter
	public Map<String, Integer> getGenderCounts() {
		return genderCounts;
	}
}