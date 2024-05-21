package br.com.ctmgeo.teste.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Tuple;

public class GenderStateCountDto {
    @JsonProperty("gender")
    private String genero;

    @JsonProperty("state")
    private String estado;

    @JsonProperty("total")
    private Integer count;

    @JsonIgnore
    private String tipo;

    public GenderStateCountDto(Tuple tuple) {
        this.genero = tuple.get("GENERO").toString();
        this.estado = tuple.get("ESTADO").toString();
        this.count = Integer.valueOf(tuple.get("COUNT").toString());
        this.tipo = tuple.get("TIPO").toString();
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
