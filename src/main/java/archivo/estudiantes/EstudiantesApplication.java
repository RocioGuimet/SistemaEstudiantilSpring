package archivo.estudiantes;

import archivo.estudiantes.service.EstudianteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EstudiantesApplication implements CommandLineRunner { //Consola

    @Autowired
    private EstudianteService estudianteService;

    private static final Logger logger = LoggerFactory.getLogger(EstudiantesApplication.class);

    String nl = System.lineSeparator();

    public static void main(String[] args) {
        logger.info("Iniciando la aplicación...");
        //Levantar Spring
        SpringApplication.run(EstudiantesApplication.class, args);
        logger.info("¡Aplicación finalizada!");
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info(nl + "Ejecutando método run" + nl); //Probando funcionamiento

    }

}
