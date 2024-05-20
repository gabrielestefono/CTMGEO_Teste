package br.com.ctmgeo.teste;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class TestH2Integration {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void whenConnected_thenReturnsTrue() {
		Integer isConnected = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
		assertThat(isConnected).isEqualTo(1);
	}
}
