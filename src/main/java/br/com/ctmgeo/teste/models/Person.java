package br.com.ctmgeo.teste.models;

import java.io.Serializable;
import java.util.Date;

import br.com.ctmgeo.teste.interfaces.CreatePersonRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="persons")
public class Person implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotBlank(message = "Por favor, preencha o campo genero") 
	private String genero;

	@Column(nullable = false)
	@NotBlank(message = "Por favor, preencha o campo titulo") 
	private String titulo;

	@Column(nullable = false)
	@NotBlank(message = "Por favor, preencha o campo nome") 
	private String nome;

	@Column(nullable = false, name="inicial_do_meio")
	@NotBlank(message = "Por favor, preencha o campo inicialDoMeio") 
	private String inicialDoMeio;

	@Column(nullable = false)
	@NotBlank(message = "Por favor, preencha o campo sobrenome") 
	private String sobrenome;

	@Column(nullable = false)
	@NotBlank(message = "Por favor, preencha o campo estado") 
	private String estado;

	@Column(nullable = false)
	@NotBlank(message = "Por favor, preencha o campo email") 
	private String email;

	@Column(nullable = false, name="data_de_nascimento")
	@NotNull(message = "Por favor, preencha o campo dataDeNascimento") 
	private Date dataDeNascimento;

	@Column(nullable = false)
	@NotNull(message = "Por favor, preencha o campo latitude") 
	private double latitude;

	@Column(nullable = false)
	@NotNull(message = "Por favor, preencha o campo longitude") 
	private double longitude;

	public Person() {
		// Empty Constructor
	}

	public Person(CreatePersonRequest createPersonRequest) {
		this.genero = createPersonRequest.genero();
		this.titulo = createPersonRequest.titulo();
		this.nome = createPersonRequest.nome();
		this.inicialDoMeio = createPersonRequest.inicial_do_meio();
		this.sobrenome = createPersonRequest.sobrenome();
		this.estado = createPersonRequest.estado();
		this.email = createPersonRequest.email();
		this.dataDeNascimento = createPersonRequest.data_de_nascimento();
		this.latitude = createPersonRequest.latitude();
		this.longitude = createPersonRequest.longitude();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getInicialDoMeio() {
		return inicialDoMeio;
	}

	public void setInicialDoMeio(String inicialDoMeio) {
		this.inicialDoMeio = inicialDoMeio;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((genero == null) ? 0 : genero.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((inicialDoMeio == null) ? 0 : inicialDoMeio.hashCode());
		result = prime * result + ((sobrenome == null) ? 0 : sobrenome.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((dataDeNascimento == null) ? 0 : dataDeNascimento.hashCode());
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (genero == null) {
			if (other.genero != null)
				return false;
		} else if (!genero.equals(other.genero))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (inicialDoMeio == null) {
			if (other.inicialDoMeio != null)
				return false;
		} else if (!inicialDoMeio.equals(other.inicialDoMeio))
			return false;
		if (sobrenome == null) {
			if (other.sobrenome != null)
				return false;
		} else if (!sobrenome.equals(other.sobrenome))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (dataDeNascimento == null) {
				return false;
		} else if (!dataDeNascimento.equals(other.dataDeNascimento))
			return false;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		return Double.doubleToLongBits(longitude) == Double.doubleToLongBits(other.longitude);
	}
}