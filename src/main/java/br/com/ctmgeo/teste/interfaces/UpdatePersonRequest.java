package br.com.ctmgeo.teste.interfaces;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdatePersonRequest(
	@JsonProperty(required=true) String genero,
	@JsonProperty(required=true) String titulo,
	@JsonProperty(required=true) String nome,
	@JsonProperty(required=true) String inicial_do_meio,
	@JsonProperty(required=true) String sobrenome,
	@JsonProperty(required=true) String estado,
	@JsonProperty(required=true) String email,
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") @JsonProperty(required=true) Date data_de_nascimento,
	@JsonProperty(required=true) double latitude,
	@JsonProperty(required=true) double longitude
){}
