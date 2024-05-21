package br.com.ctmgeo.teste.interfaces;

import java.util.ArrayList;
import java.util.List;

public class MinMaxDto {
	private List<GenderStateCountDto> min = new ArrayList<>();

	private List<GenderStateCountDto> max = new ArrayList<>();

	public MinMaxDto(List<GenderStateCountDto> genderStateCountDtos) {
		for (GenderStateCountDto genderStateCountDto : genderStateCountDtos) {
			if("Menor".equals(genderStateCountDto.getTipo())){
				this.min.add(genderStateCountDto);
			}else{
				this.max.add(genderStateCountDto);
			}
		}
	}

	public List<GenderStateCountDto> getMin() {
		return min;
	}

	public void setMin(List<GenderStateCountDto> min) {
		this.min = min;
	}

	public List<GenderStateCountDto> getMax() {
		return max;
	}

	public void setMax(List<GenderStateCountDto> max) {
		this.max = max;
	}
}
