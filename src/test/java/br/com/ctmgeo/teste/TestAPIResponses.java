package br.com.ctmgeo.teste;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import br.com.ctmgeo.teste.interfaces.CreatePersonRequest;
import br.com.ctmgeo.teste.models.Person;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TestAPIResponses {
	@Autowired
	private TestRestTemplate restTemplate;

	@SuppressWarnings("deprecation")
	@Test
	@org.junit.jupiter.api.Order(1)
	void ShouldReturnAllPersons() throws Exception {
  	CreatePersonRequest request1 = new CreatePersonRequest("female","Mrs.","Isabela","A","Pinto","SP","IsabelaAzevedoPinto@dayrep.com",new Date(1946, 11, 30),-23.649462,-46.704023);
		CreatePersonRequest request2 = new CreatePersonRequest("female","Ms.","Luana","F","Barros","DF","LuanaFerreiraBarros@rhyta.com",new Date(1990, 1, 2),-15.605035,-47.655667);
		CreatePersonRequest request3 = new CreatePersonRequest("male","Mr.","Matheus","R","Souza","GO","MatheusRochaSouza@jourrapide.com",new Date(1970, 10, 7),-16.68665,-49.17841);

    Person person1 = new Person(request1);
    Person person2 = new Person(request2);
		Person person3 = new Person(request3);

    ResponseEntity<List<Person>> response = restTemplate.exchange(
            "http://localhost:8080/api/person",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Person>>() {});

		List<Person> expectedPersons = new ArrayList<>(Arrays.asList(person1, person2, person3));
		@SuppressWarnings("null")
		List<Person> responseNoId = response.getBody().stream().map(p -> {p.setId(null);return p;}).collect(Collectors.toList());
		for (int i = 0; i < expectedPersons.size(); i++) {
			Person expected = expectedPersons.get(i);
			Person actual = responseNoId.get(i);
			
			assertEquals(expected.getEmail(), actual.getEmail());
			assertEquals(expected.getEstado(), actual.getEstado());
			assertEquals(expected.getGenero(), actual.getGenero());
			assertEquals(expected.getInicialDoMeio(), actual.getInicialDoMeio());
			assertEquals(expected.getLatitude(), actual.getLatitude());
			assertEquals(expected.getLongitude(), actual.getLongitude());
			assertEquals(expected.getNome(), actual.getNome());
			assertEquals(expected.getSobrenome(), actual.getSobrenome());
			assertEquals(expected.getTitulo(), actual.getTitulo());
		}
	}

	@Test
	@org.junit.jupiter.api.Order(2)
	@SuppressWarnings({ "deprecation", "null" })
	void ShouldCreateOnePerson() throws Exception {
		CreatePersonRequest request1 = new CreatePersonRequest("female","Mrs.","Isabela","A","Pinto","SP","IsabelaAzevedoPinto@dayrep.com",new Date(1946, 11, 30),-23.649462,-46.704023);

			Person expectedPerson = new Person(request1);

			HttpEntity<CreatePersonRequest> requestEntity = new HttpEntity<>(request1);
			ResponseEntity<Person> response = restTemplate.exchange(
							"http://localhost:8080/api/person",
							HttpMethod.POST,
							requestEntity,
							new ParameterizedTypeReference<Person>() {});
							Person actualPerson = response.getBody();
			assertEquals(expectedPerson.getEmail(), actualPerson.getEmail());
			assertEquals(expectedPerson.getEstado(), actualPerson.getEstado());
			assertEquals(expectedPerson.getGenero(), actualPerson.getGenero());
			assertEquals(expectedPerson.getInicialDoMeio(), actualPerson.getInicialDoMeio());
			assertEquals(expectedPerson.getLatitude(), actualPerson.getLatitude());
			assertEquals(expectedPerson.getLongitude(), actualPerson.getLongitude());
			assertEquals(expectedPerson.getNome(), actualPerson.getNome());
			assertEquals(expectedPerson.getSobrenome(), actualPerson.getSobrenome());
			assertEquals(expectedPerson.getTitulo(), actualPerson.getTitulo());
	}


	@Test
	@org.junit.jupiter.api.Order(3)
	@SuppressWarnings({ "deprecation", "null" })
	void ShouldReturnOnePerson() throws Exception {
		CreatePersonRequest request1 = new CreatePersonRequest("female","Mrs.","Isabela","A","Pinto","SP","IsabelaAzevedoPinto@dayrep.com",new Date(1946, 11, 30),-23.649462,-46.704023);

    Person person1 = new Person(request1);

    ResponseEntity<Person> response = restTemplate.exchange(
            "http://localhost:8080/api/person/1",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Person>() {});

		Person responseBody = response.getBody();
		assertEquals(person1.getEmail(), responseBody.getEmail());
		assertEquals(person1.getEstado(), responseBody.getEstado());
		assertEquals(person1.getGenero(), responseBody.getGenero());
		assertEquals(person1.getInicialDoMeio(), responseBody.getInicialDoMeio());
		assertEquals(person1.getLatitude(), responseBody.getLatitude());
		assertEquals(person1.getLongitude(), responseBody.getLongitude());
		assertEquals(person1.getNome(), responseBody.getNome());
		assertEquals(person1.getSobrenome(), responseBody.getSobrenome());
		assertEquals(person1.getTitulo(), responseBody.getTitulo());
	}

	@Test
	@org.junit.jupiter.api.Order(4)
	@SuppressWarnings({ "deprecation", "null" })
	void ShouldEditOnePerson() throws Exception {
		CreatePersonRequest request3 = new CreatePersonRequest("male","Mr.","Gabriel","B","Cardoso","GO","GabrielBarbosaCardoso@armyspy.com",new Date(1958, 5, 9),-16.868231,-49.224983);

    Person person1 = new Person(request3);

		HttpEntity<CreatePersonRequest> requestEntity = new HttpEntity<>(request3);
    ResponseEntity<Person> response = restTemplate.exchange(
            "http://localhost:8080/api/person/4",
            HttpMethod.PUT,
            requestEntity,
            new ParameterizedTypeReference<Person>() {});

		Person responseBody = response.getBody();
		assertEquals(person1.getEmail(), responseBody.getEmail());
		assertEquals(person1.getEstado(), responseBody.getEstado());
		assertEquals(person1.getGenero(), responseBody.getGenero());
		assertEquals(person1.getInicialDoMeio(), responseBody.getInicialDoMeio());
		assertEquals(person1.getLatitude(), responseBody.getLatitude());
		assertEquals(person1.getLongitude(), responseBody.getLongitude());
		assertEquals(person1.getNome(), responseBody.getNome());
		assertEquals(person1.getSobrenome(), responseBody.getSobrenome());
		assertEquals(person1.getTitulo(), responseBody.getTitulo());
	}

	@Test
	@org.junit.jupiter.api.Order(4)
	@SuppressWarnings({ "deprecation" })
	void ShouldDeleteOnePerson() throws Exception {
			ResponseEntity<Person> response = restTemplate.exchange(
							"http://localhost:8080/api/person/5",
							HttpMethod.DELETE,
							null,
							new ParameterizedTypeReference<Person>() {});

		assertTrue(response.getStatusCodeValue() == 200);

		ResponseEntity<Person> response2 = restTemplate.exchange(
							"http://localhost:8080/api/person/5",
							HttpMethod.DELETE,
							null,
							new ParameterizedTypeReference<Person>() {});

		assertTrue(response2.getStatusCodeValue() == 404);
	}
}
