package br.com.ctmgeo.teste.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ctmgeo.teste.models.Person;
import jakarta.persistence.Tuple;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

	@Query(value = "WITH GENEROS AS (SELECT DISTINCT GENERO FROM PERSONS), MAX_MIN_COUNTS AS ( SELECT GENERO, ESTADO, COUNT(*) AS Count, ROW_NUMBER() OVER(PARTITION BY GENERO ORDER BY COUNT(*) DESC) AS rn_desc, ROW_NUMBER() OVER(PARTITION BY GENERO ORDER BY COUNT(*) ASC) AS rn_asc FROM PERSONS WHERE GENERO IN (SELECT GENERO FROM GENEROS) GROUP BY GENERO, ESTADO) SELECT GENERO, ESTADO, Count, CASE WHEN rn_desc = 1 THEN 'Maior' WHEN rn_asc = 1 THEN 'Menor' END AS Tipo FROM MAX_MIN_COUNTS WHERE rn_desc = 1 OR rn_asc = 1 ORDER BY GENERO, rn_desc;", nativeQuery = true)
  List<Tuple> findEstadoWithMaxMaleCount();

  @Query(value = "SELECT ESTADO, COUNT(*) AS COUNT, 20 AS TIPO FROM PERSONS WHERE DATEADD('YEAR', 20, DATA_DE_NASCIMENTO) > CURRENT_DATE() GROUP BY ESTADO HAVING COUNT(*) = ( SELECT MAX(COUNT) FROM ( SELECT COUNT(*) AS COUNT FROM PERSONS WHERE DATEADD('YEAR', 20, DATA_DE_NASCIMENTO) > CURRENT_DATE() GROUP BY ESTADO ) ) UNION ALL SELECT ESTADO, COUNT(*) AS COUNT, 50 AS TIPO FROM PERSONS WHERE DATEADD('YEAR', 50, DATA_DE_NASCIMENTO) <= CURRENT_DATE() GROUP BY ESTADO HAVING COUNT(*) = ( SELECT MAX(COUNT) FROM ( SELECT COUNT(*) AS COUNT FROM PERSONS WHERE DATEADD('YEAR', 50, DATA_DE_NASCIMENTO) <= CURRENT_DATE() GROUP BY ESTADO ) );", nativeQuery = true)
  List<Tuple> getAllUnder20AndOver50();

  @Query("SELECT DISTINCT p.genero FROM Person p")
  List<String> findGenders();

  @Query(value = "SELECT ESTADO, GENERO, COALESCE(COUNT(*), 0) as TOTAL FROM PERSONS WHERE GENERO = :gender GROUP BY ESTADO;", nativeQuery = true)
  List<Tuple> findByStateAndGender(@Param("gender") String gender);
}
