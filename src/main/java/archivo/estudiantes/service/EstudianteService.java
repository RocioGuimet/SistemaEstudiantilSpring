package archivo.estudiantes.service;

import archivo.estudiantes.model.Estudiante;
import archivo.estudiantes.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService implements IEstudianteService{

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        return estudiantes;
    }

    @Override
    public Estudiante findEstudianteById(Integer idEstudiante) {
        Estudiante estudiante = estudianteRepository.findById(idEstudiante). orElse(null);
        return estudiante;
    }

    @Override
    public void guardarEstudiante(Estudiante estudiante) {
        estudianteRepository.save(estudiante);
    }

    @Override
    public void eliminarEstudiante(Estudiante estudiante) {
        estudianteRepository.delete(estudiante);
    }
}
