package br.com.ctmgeo.teste;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.ctmgeo.teste.repositories.PersonRepository;

@SpringBootTest
class TestCSVLoaderIntegrationTest {

	@Autowired
	private PersonRepository personRepository;

	@Test
	void testCSVDataLoaded(){
		int expectations = 10;

		Awaitility.await().atMost(60, TimeUnit.SECONDS).until(()-> personRepository.count() >= expectations);

		assertThat(personRepository.count()).isGreaterThanOrEqualTo(expectations);
	}
}
