package org.iesalandalus.programacion.reservashotel.dominio;

public enum TipoHabitacion {

    SIMPLE("SIMPLE",1),
    DOBLE("DOBLE",2),
    TRIPLE("TRIPLE",3),
    SUITE("SUITE", 4);

    private String cadenaAMostrar;
    private int numeroMaximoPersonas;

    private TipoHabitacion(String cadenaAMostrar,int numeroMaximoPersonas){
        this.cadenaAMostrar=cadenaAMostrar;
        this.numeroMaximoPersonas=numeroMaximoPersonas;
    }

    public int getNumeroMaximoPersonas() {
        return numeroMaximoPersonas;
    }

    @Override
    public String toString() {
        return ordinal()+1+".- "+cadenaAMostrar;
    }
}
