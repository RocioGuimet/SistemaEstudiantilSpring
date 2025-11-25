package archivo.estudiantes.service;

import archivo.estudiantes.model.Estudiante;

import java.util.List;

public interface IEstudianteService {
    public List<Estudiante> listarEstudiantes();

    public Estudiante findEstudianteById(Integer idEstudiante);

    public void guardarEstudiante(Estudiante estudiante);

    public void eliminarEstudiante(Estudiante estudiante);
}
