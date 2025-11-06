package prof2022;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import java.util.Vector;

public class MatriculaTest {

    @Test
    void testGetImporte_VectorNuloLanzaExcepcion() {
        // Arrange: creamos una matrícula con vector nulo
        Vector<Asignatura> vectorNulo = null;
        Matricula matricula = new Matricula(vectorNulo);

        // Act & Assert: comprobamos que lanza una excepción
        assertThrows(Exception.class, () -> {
            matricula.getImporte();
        });
    }
    
    @Test
    void testGetImporte_SumaCorrectaConMocks() throws Exception {
    	// Creamos los mocks de asignatura 
    	Asignatura a1 = mock(Asignatura.class);
    	Asignatura a2 = mock(Asignatura.class);
    	
    	when(a1.getImporte()).thenReturn(100.0);
    	when(a2.getImporte()).thenReturn(200.0);
    	
    	// Creamos el vector y anhadimos los mocks
    	Vector<Asignatura> asignaturas = new Vector<>();
    	asignaturas.add(a2);
    	asignaturas.add(a1);
    	
    	// Instanciamos Matricula con ese vector
    	Matricula matricula = new Matricula(asignaturas);
    	
    	// Ejecutamos el método a probar
    	double resultado = matricula.getImporte();
    	
    	// Verificamos el resultado
    	assertEquals(300.0, resultado, 0.0001);
    	
    	// Verificamos que los mocks fueron llamados
    	verify(a1).getImporte();
    	verify(a2).getImporte();
    	
    	
    	
    }
    
    @Test
    void testGetImporte_RecorreTodasLasAsignaturas() throws Exception {
        // Mocks
        Asignatura a1 = mock(Asignatura.class);
        Asignatura a2 = mock(Asignatura.class);
        Asignatura a3 = mock(Asignatura.class);

        // Simulamos que cada asignatura devuelve algo, da igual el valor
        when(a1.getImporte()).thenReturn(10.0);
        when(a2.getImporte()).thenReturn(20.0);
        when(a3.getImporte()).thenReturn(30.0);

        // Vector con todos los mocks
        Vector<Asignatura> asignaturas = new Vector<>();
        asignaturas.add(a1);
        asignaturas.add(a2);
        asignaturas.add(a3);

        // Ejecutamos el método a probar
        Matricula matricula = new Matricula(asignaturas);
        matricula.getImporte();

        // Verificamos que se ha llamado a getImporte() exactamente una vez por cada asignatura
        verify(a1, times(1)).getImporte();
        verify(a2, times(1)).getImporte();
        verify(a3, times(1)).getImporte();

        // Y que no se han hecho llamadas de más
        verifyNoMoreInteractions(a1, a2, a3);
    }

}
