package prof2022;

import java.util.Vector;

public class Matricula {
    Vector<Asignatura> vectorAsignaturas;

    Matricula(Vector<Asignatura> vectorAsignaturas) {
        this.vectorAsignaturas = vectorAsignaturas;
    }

    public double getImporte() throws Exception {
        double importe = 0.0;

        if (vectorAsignaturas == null)
            throw new Exception();

        for (Asignatura i : vectorAsignaturas)
            importe += i.getImporte();

        return importe;
    }
}
