package archivo.estudiantes;

import archivo.estudiantes.model.Estudiante;
import archivo.estudiantes.service.EstudianteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import java.util.List;

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
        var salir = false;
        var consola = new Scanner(System.in);
        while (!salir) {
            mostrarMenu();
            salir = ejecutarOpciones(consola);
            logger.info(nl);
        } // fin while
    }

    private void mostrarMenu() {
        //logger.info(nl);
        logger.info("""
                * Sistema Estudiantil *
                1. Listar Estudiantes
                2. Buscar Estudiante
                3. Agregar Estudiante
                4. Modificar Estudiante
                5. Eliminar Estudiante
                6. Salir
                Elige una opción:""");
    }

    private boolean ejecutarOpciones(Scanner consola) {
        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;
        switch (opcion) {
            case 1 -> {
                logger.info(nl + "Listado de Estudiantes: " + nl);
                List<Estudiante> estudiantes = estudianteService.listarEstudiantes();
                estudiantes.forEach((estudiante -> logger.info(estudiante.toString() + nl)));
            }
            case 2 -> {
                logger.info("Introduce el ID del estudiante: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                Estudiante estudiante = estudianteService.findEstudianteById(idEstudiante);
                if (estudiante != null)
                    logger.info("Estudiante encontrado: " + estudiante + nl);
                else
                    logger.info("No se encontró estudiante con el ID: " + idEstudiante + nl);
            }
            case 3 -> {
                logger.info("Agregar Estudiante: " +nl);
                logger.info("Nombre: ");
                var nombre = consola.nextLine();
                logger.info("Apellido: ");
                var apellido = consola.nextLine();
                logger.info("Telefono: ");
                var telefono = consola.nextLine();
                logger.info("Email: ");
                var email = consola.nextLine();
                // Crear objeto (sin id)
                var estudiante = new Estudiante();
                estudiante.setNombre(nombre);
                estudiante.setApellido(apellido);
                estudiante.setTelefono(telefono);
                estudiante.setEmail(email);
                estudianteService.guardarEstudiante(estudiante);
                logger.info("Estudiante agregado: " + estudiante + nl);
            }
            case 4 -> {
                logger.info("Modificar Estudiante: " +nl);
                logger.info("Introduce el ID del Estudiante que deseas modificar: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                Estudiante estudiante = estudianteService.findEstudianteById(idEstudiante);
                if (estudiante != null){
                    logger.info("Nombre: ");
                    var nombre = consola.nextLine();
                    logger.info("Apellido: ");
                    var apellido = consola.nextLine();
                    logger.info("Telefono: ");
                    var telefono = consola.nextLine();
                    logger.info("Email: ");
                    var email = consola.nextLine();
                    estudiante.setNombre(nombre);
                    estudiante.setApellido(apellido);
                    estudiante.setTelefono(telefono);
                    estudiante.setEmail(email);
                    estudianteService.guardarEstudiante(estudiante);
                    logger.info("Estudiante modificado: " + estudiante + nl);
                }
                else
                    logger.info("No se encontró estudiante con el ID: " + idEstudiante + nl);
            }
            case 5 -> {
                logger.info("Eliminar Estudiante: " +nl);
                logger.info("Introduce el ID del Estudiante que deseas eliminar: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = estudianteService.findEstudianteById(idEstudiante);
                if (estudiante != null) {
                    estudianteService.eliminarEstudiante(estudiante);
                    logger.info("Estudiante eliminado: " + estudiante + nl);
                }
                else
                    logger.info("No se encontró estudiante con el ID: " + idEstudiante + nl);
            }
            case 6 -> {
                logger.info("¡Hasta pronto!" +nl + nl);
                salir = true;
            }
            default -> logger.info("Opción NO reconocida: " + opcion + nl);
        } // fin switch
        return salir;
    }
}