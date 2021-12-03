package proyecto1;

import java.io.Serializable;

public class ObjetosActividades implements Serializable{
    
    int contadorProfes = 0;
    int contadorAlumnos = 0;
    int contadorCursos = 0;
    public Profesor [] profesores = new Profesor[50];
    public Curso [] cursos = new Curso[50];
    public Alumno [] alumnos = new Alumno[300];
    
    public ObjetosActividades(){
//        contadorProfes = 0;
//        contadorAlumnos = 0;
//        contadorCursos = 0;
    }
    
    public void guardarProfe(int codigo, String nombre, String apellido, String correo, String genero, String contra){
        Profesor nuevoProfe = new Profesor(codigo, nombre, apellido, correo, genero, contra);
        profesores[contadorProfes] = nuevoProfe;
        contadorProfes++;
    }
    
    public void guardarCurso(int codigo,  String nombre, int creditos, int idProf){
        Curso nuevoCurso = new Curso(codigo,  nombre, creditos, idProf);
        cursos[contadorCursos] = nuevoCurso;
        contadorCursos++;
    }
    
    public void guardarAlumno(int codigo, String nombre, String apellido, String correo, String genero){
        Alumno nuevoAlumno = new Alumno(codigo, nombre, apellido, correo, genero);
        alumnos[contadorAlumnos] = nuevoAlumno;
        contadorAlumnos++;
    }
    
    public void asignarAlumno(int codigo, int idCurso, int posicion){
       Alumno alumno_asignable = getAlumnoAs(codigo);
       Curso curso_asignable = getCursoAs(idCurso);
       curso_asignable.setAlumnos(alumno_asignable, posicion);
    }
    
    public Alumno getAlumnoAs(int id){
        for (int i = 0; i < contadorAlumnos; i++) {
            if(id == alumnos[i].getCodigo()){
                return alumnos[i];
            }
        }
        return null;
    }
    
    public Curso getCursoAs(int id){
        for (int i = 0; i < contadorCursos; i++) {
            if(id == cursos[i].getCodigo()){
                return cursos[i];
            }
        }
        return null;
    }
    
    public void asignarNotas(int id, double note, int pos){
        Alumno alumno_asignable = getAlumnoAs(id);
        alumno_asignable.setNota(note, pos);
    }
}
