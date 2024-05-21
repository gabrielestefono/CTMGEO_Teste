package br.com.ctmgeo.teste.utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import br.com.ctmgeo.teste.models.Person;
import br.com.ctmgeo.teste.repositories.PersonRepository;

@Configuration
public class CSVLoader implements CommandLineRunner{

    private final PersonRepository personRepository;
    private final Environment env;

    public CSVLoader(PersonRepository personRepository, Environment env) {
        this.personRepository = personRepository;
        this.env = env;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Stream<String> stream = Files.lines(Paths.get(env.getProperty("caminho.lista"))).skip(1)) {
            stream.forEach(linha -> {
                String[] detalhes = linha.split(",");
                Person person = new Person();
                person.setGenero(detalhes[0]);
                person.setTitulo(detalhes[1]);
                person.setNome(detalhes[2]);
                person.setInicialDoMeio(detalhes[3]);
                person.setSobrenome(detalhes[4]);
                person.setEstado(detalhes[5]);
                person.setEmail(detalhes[6]);
                try {
                    person.setDataDeNascimento(new SimpleDateFormat("MM/dd/yyyy").parse(detalhes[7]));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                person.setLatitude(Double.parseDouble(detalhes[8]));
                person.setLongitude(Double.parseDouble(detalhes[9]));
                personRepository.save(person);
            });

        }
    }
}
