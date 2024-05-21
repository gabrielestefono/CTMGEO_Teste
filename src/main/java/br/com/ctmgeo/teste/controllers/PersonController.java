package br.com.ctmgeo.teste.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ctmgeo.teste.interfaces.CreatePersonRequest;
import br.com.ctmgeo.teste.interfaces.FindByStateAndGender;
import br.com.ctmgeo.teste.interfaces.MaxStateAge;
import br.com.ctmgeo.teste.interfaces.MinMaxDto;
import br.com.ctmgeo.teste.interfaces.UpdatePersonRequest;
import br.com.ctmgeo.teste.models.Person;
import br.com.ctmgeo.teste.services.PersonServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Controller de Pessoas", description = "APIs relacionadas a pessoas")
@RestController
@RequestMapping("api/person")
public class PersonController {

	private final PersonServices personServices;

	public PersonController(PersonServices personServices) {
		this.personServices = personServices;
	}

	@Operation(summary = "Obter todas as pessoas", description = "Retorna uma lista de todas as pessoas")
	@GetMapping(produces = "application/json")
	public List<Person> getAllPersons(){
		return this.personServices.getAllPersons();
	}

	@Operation(summary = "Criar uma nova pessoa", description = "Cria uma nova pessoa")
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Person createPerson(@RequestBody CreatePersonRequest person) {
		return this.personServices.createPerson(person);
	}

	@Operation(summary = "Retorna uma pessoa por ID", description = "Retorna uma pessoa por ID")
	@GetMapping(produces = "application/json", value = "{id}")
	public Person getOnePerson(@PathVariable Long id){
		return this.personServices.getOnePerson(id);
	}

	@Operation(summary = "Modifica uma pessoa por Id", description = "Modifica uma pessoa por Id")
	@PutMapping(consumes = "application/json", produces = "application/json", value = "{id}")
	public Person editPerson(@PathVariable Long id, @RequestBody UpdatePersonRequest person) {
		return this.personServices.editPerson(id, person);
	}

	@Operation(summary = "Deleta uma pessoa por Id", description = "Deleta uma pessoa por Id")
	@DeleteMapping("{id}")
	public void deletePerson(@PathVariable Long id) {
		this.personServices.deletePerson(id);
	}

	@Operation(summary = "Obtém os estados com maior número de pessoas de cada sexo e os estados com menor número de pessoas de cada sexo",
		description = "Obtém os estados com maior número de pessoas de cada sexo e os estados com menor número de pessoas de cada sexo")
	@GetMapping(produces = "application/json", value = "minmax-estado-e-genero")
	public MinMaxDto getMethodName() {
		return this.personServices.getMinMaxByStateAndGender();
	}


	@Operation(summary = "Obter os estados com maior número de pessoas acima dos 50 anos e os estados com maior número de pessoas abaixo dos 20 anos",
	description = "Obter os estados com maior número de pessoas acima dos 50 anos e os estados com maior número de pessoas abaixo dos 20 anos")
	@GetMapping(produces = "application/json", value = "max-20-50")
	public MaxStateAge getAllUnder20AndOver50() {
		return this.personServices.getAllUnder20AndOver50();
	}
	@Operation(summary = "Obter uma relação de estados e a quantidade de pessoas de cada sexo",
	description = "Obter uma relação de estados e a quantidade de pessoas de cada sexo")
	@GetMapping(produces = "application/json", value = "numero-por-estado-e-genero")
	public List<FindByStateAndGender> findByStateAndGender() {
		return this.personServices.findByStateAndGender();
	}
}

