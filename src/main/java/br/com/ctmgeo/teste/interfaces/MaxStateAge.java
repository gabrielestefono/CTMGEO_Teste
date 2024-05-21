package br.com.ctmgeo.teste.interfaces;

import java.util.ArrayList;
import java.util.List;

public class MaxStateAge {
	private List<MaxFromAgeDto> greaterEquals50 = new ArrayList<>();
	
	private List<MaxFromAgeDto> lowerEquals20 = new ArrayList<>();

	public MaxStateAge(List<MaxFromAgeDto> all) {
		for (MaxFromAgeDto maxFromAgeDto : all) {
			if("20".equals(maxFromAgeDto.getType())){
				lowerEquals20.add(maxFromAgeDto);
			}else{
				greaterEquals50.add(maxFromAgeDto);
			}
		}
	}

	public List<MaxFromAgeDto> getGreaterEquals50() {
		return greaterEquals50;
	}

	public void setGreaterEquals50(List<MaxFromAgeDto> greaterEquals50) {
		this.greaterEquals50 = greaterEquals50;
	}

	public List<MaxFromAgeDto> getLowerEquals20() {
		return lowerEquals20;
	}

	public void setLowerEquals20(List<MaxFromAgeDto> lowerEquals20) {
		this.lowerEquals20 = lowerEquals20;
	}
}
