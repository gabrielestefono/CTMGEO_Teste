package br.com.ctmgeo.teste.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.ctmgeo.teste.exceptions.errors.BadRequestException;
import br.com.ctmgeo.teste.exceptions.errors.NotFoundException;
import br.com.ctmgeo.teste.interfaces.CreatePersonRequest;
import br.com.ctmgeo.teste.interfaces.FindByStateAndGender;
import br.com.ctmgeo.teste.interfaces.Gender;
import br.com.ctmgeo.teste.interfaces.GenderStateCountDto;
import br.com.ctmgeo.teste.interfaces.MaxFromAgeDto;
import br.com.ctmgeo.teste.interfaces.MaxStateAge;
import br.com.ctmgeo.teste.interfaces.MinMaxDto;
import br.com.ctmgeo.teste.interfaces.PersonsByGender;
import br.com.ctmgeo.teste.interfaces.UpdatePersonRequest;
import br.com.ctmgeo.teste.models.Person;
import br.com.ctmgeo.teste.repositories.PersonRepository;
import jakarta.persistence.Tuple;

@Service
public class PersonServices {
	private final PersonRepository personRepository;

	public PersonServices(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	public List<Person> getAllPersons(){
		return this.personRepository.findAll();
	}
	public Person createPerson(@RequestBody CreatePersonRequest person){
		if (person == null) {
			throw new BadRequestException("Solicitação de criação de pessoa inválida!");
		}
		Person newPerson = new Person(person);
		return this.personRepository.save(newPerson);
	}
	public Person getOnePerson(@PathVariable Long id){
		return this.personRepository.findById(id).orElseThrow(() -> new NotFoundException("Pessoa não encontrada!"));
	}
	public Person editPerson(@PathVariable Long id, @RequestBody UpdatePersonRequest person){
		if (person == null) {
			throw new BadRequestException("Solicitação de criação de pessoa inválida!");
		}
		Person personFound = this.getOnePerson(id);
		personFound.setDataDeNascimento(person.data_de_nascimento());
		personFound.setEmail(person.email());
		personFound.setEstado(person.estado());
		personFound.setGenero(person.genero());
		personFound.setInicialDoMeio(person.inicial_do_meio());
		personFound.setLatitude(person.latitude());
		personFound.setLongitude(person.longitude());
		personFound.setNome(person.nome());
		personFound.setSobrenome(person.sobrenome());
		personFound.setTitulo(person.titulo());
		return this.personRepository.save(personFound);
	}
	public void deletePerson(@PathVariable Long id){
		Person personFound = this.getOnePerson(id);
		this.personRepository.delete(personFound);
	}

	public MinMaxDto getMinMaxByStateAndGender(){
		List<Tuple> tuple = personRepository.findEstadoWithMaxMaleCount();
		List<GenderStateCountDto> genderStates = new ArrayList<>();
		for (Tuple tupleItem : tuple) {
			GenderStateCountDto genderStateCountDto = new GenderStateCountDto(tupleItem);
			genderStates.add(genderStateCountDto);
		}
		return new MinMaxDto(genderStates);
	}

	public MaxStateAge getAllUnder20AndOver50(){
		List<Tuple> tuple = personRepository.getAllUnder20AndOver50();
		List<MaxFromAgeDto> maxFromAgeDtos = new ArrayList<>();
		for (Tuple tupleItem : tuple) {
			MaxFromAgeDto maxFromAgeDto = new MaxFromAgeDto(tupleItem);
			maxFromAgeDtos.add(maxFromAgeDto);
		}
		return new MaxStateAge(maxFromAgeDtos);
	}

	public List<FindByStateAndGender> findByStateAndGender() {
    List<FindByStateAndGender> findByGender = new ArrayList<>();
    List<String> genders = this.personRepository.findGenders();

    List<PersonsByGender> persons = genders.stream()
			.map(Gender::new)
			.flatMap(gender -> this.personRepository.findByStateAndGender(gender.getGenero()).stream())
			.map(PersonsByGender::new)
			.sorted(Comparator.comparing(PersonsByGender::getGenero).reversed())
			.sorted(Comparator.comparing(PersonsByGender::getEstado))
			.collect(Collectors.toList());

    if (!persons.isEmpty()) {
			for (int i = 0; i < persons.size(); i += genders.size()) {
				Map<String, Integer> genderCounts = new HashMap<>();
				for (int j = 0; j < genders.size(); j++) {
					genderCounts.put(persons.get(i + j).getGenero(), persons.get(i + j).getQuantidade());
				}
				FindByStateAndGender findByStateAndGender = new FindByStateAndGender(persons.get(i).getEstado(), genderCounts);
				findByGender.add(findByStateAndGender);
			}
    }
    return findByGender;
	}
}
