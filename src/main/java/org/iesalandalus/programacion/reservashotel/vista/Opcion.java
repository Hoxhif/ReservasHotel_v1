package org.iesalandalus.programacion.reservashotel.vista;

public enum Opcion {
    SALIR("Salir"),
    INSERTAR_HUESPED("Insertar Huesped"),
    BUSCAR_HUESPED("Buscar Huesped"),
    BORRAR_HUESPED("Borrar Huesped"),
    MOSTRAR_HUESPEDES("Mostrar Huespedes"),
    INSERTAR_HABITACION("Insertar Habitación"),
    BUSCAR_HABITACION("Buscar Habitación"),
    BORRAR_HABITACION("Borrar Habitación"),
    MOSTRAR_HABITACIONES("Mostrar Habitaciones"),
    INSERTAR_RESERVA("Insertar Reserva"),
    ANULAR_RESERVA("Anular Reserva"),
    MOSTRAR_RESERVAS("Mostrar Reservas"),
    CONSULTAR_DISPONIBILIDAD("Consultar Disponibilidad");


    private String mensajeAMostrar;

    private Opcion(String mensajeAMostrar){
        this.mensajeAMostrar=mensajeAMostrar;
    }

    @Override
    public String toString() {
        return ordinal()+1+".-"+" "+mensajeAMostrar;
    }
}

