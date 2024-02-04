package org.iesalandalus.programacion.reservashotel.modelo;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Reservas;
import org.iesalandalus.programacion.reservashotel.vista.Consola;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Modelo {

    // Aquí hemos copiado y pegado
    public Habitaciones habitaciones;
    public Reservas reservas;
    public Huespedes huespedes;
    public static final int CAPACIDAD=5;


    public Modelo(){

    }

    public void comenzar(){
        // Aquí hemos copiado y pegado lo que teníamos en el main del mainApp anteriormente.
        habitaciones = new Habitaciones(CAPACIDAD);
        huespedes = new Huespedes(CAPACIDAD);
        reservas = new Reservas(CAPACIDAD);
    }

    public void terminar(){
        System.out.println("Fin del programa.");
    }

    public void insertar(Huesped huesped) throws OperationNotSupportedException{
        // Aqui directamente hacemos la llamada al método que insertará el huesped en el array, y como no lo vamos a tratar aqui
        // sino que lo vamos a tratar la excepcion en la vista, pues hacemos un throws.
        huespedes.insertar(huesped);
    }

    public Huesped buscar(Huesped huesped){
        return huespedes.buscar(huesped);
    }

    public void borrar(Huesped huesped) throws OperationNotSupportedException{
        huespedes.borrar(huesped);
    }

    public Huesped[] getHuespedes(){
        return huespedes.get();
        /*Huesped[] huespedesADevolver = new Huesped[huespedes.get().length];

        // Aquí hacemos esto para asegurarnos de que es el mismo huesped con un if.
        for (int i=0; i<huespedes.get().length;i++) {
            huespedesADevolver[i] = new Huesped(huespedesADevolver[i].getNombre(),huespedesADevolver[i].getDni(), huespedesADevolver[i].getCorreo(), huespedesADevolver[i].getTelefono(), huespedesADevolver[i].getFechaNacimiento());
        }
        return huespedesADevolver;*/
    }

    public void insertar(Habitacion habitacion)throws OperationNotSupportedException{
        habitaciones.insertar(habitacion);
    }

    public Habitacion buscar(Habitacion habitacion){
        return habitaciones.buscar(habitacion);
    }

    public void borrar(Habitacion habitacion)throws OperationNotSupportedException{
        habitaciones.borrar(habitacion);
    }

    public Habitacion[] getHabitaciones(){
        return habitaciones.get();
        /*Habitacion[] habitacionesADevolver = new Habitacion[habitaciones.get().length];

        // Aquí hacemos esto para asegurarnos de que es el mismo huesped con un if.
        for (int i=0; i<habitaciones.get().length;i++) {
                habitacionesADevolver[i] = new Habitacion(habitacionesADevolver[i].getPlanta(), habitacionesADevolver[i].getPuerta(), habitacionesADevolver[i].getPrecio());
        }
        return habitacionesADevolver;*/
    }

    public Habitacion[] getHabitaciones(TipoHabitacion tipoHabitacion){
        return habitaciones.get(tipoHabitacion);
        /*Habitacion[] habitacionesADevolver = new Habitacion[habitaciones.get(tipoHabitacion).length];

        // Aquí hacemos esto para asegurarnos de que es el mismo huesped con un if.
        for (int i=0; i<habitaciones.get(tipoHabitacion).length;i++) {
            if (habitacionesADevolver[i].getTipoHabitacion().equals(tipoHabitacion)){
                habitacionesADevolver[i] = new Habitacion(habitacionesADevolver[i].getPlanta(), habitacionesADevolver[i].getPuerta(), habitacionesADevolver[i].getPrecio());
            }
        }
        return habitacionesADevolver;*/
    }

    public void insertar(Reserva reserva)throws OperationNotSupportedException{
        reservas.insertar(reserva);
    }

    public void borrar(Reserva reserva)throws OperationNotSupportedException{
        reservas.borrar(reserva);
    }

    public Reserva buscar(Reserva reserva){
        return reservas.buscar(reserva);
    }

    public Reserva[] getReservas(){
        return reservas.get();
        // He hecho esto porque en el enunciado pone que que devolvemos una lista de objetos nuevos de los geters, y no una copia.
        // LUEGO pregunte a mis compañeros y me dijeron que solo hacia falta el reservas.get();
        /*Reserva[] reservasADevolver = new Reserva[reservas.get().length];

        for (int i=0; i<reservas.get().length;i++){
            reservasADevolver[i]= new Reserva(reservasADevolver[i].getHuesped(), reservasADevolver[i].getHabitacion(), reservasADevolver[i].getRegimen(),reservasADevolver[i].getFechaInicioReserva(), reservasADevolver[i].getFechaFinReserva(),reservasADevolver[i].getNumeroPersonas());
        }
        return reservasADevolver;*/
    }

    public Reserva[] getReserva(Huesped huesped){
        return reservas.getReservas(huesped);
        /*Reserva[] reservasADevolver = new Reserva[reservas.getReservas(huesped).length];

        // Aquí hacemos esto para asegurarnos de que es el mismo huesped con un if.
        for (int i=0; i<reservas.getReservas(huesped).length;i++) {
            if (reservasADevolver[i].getHuesped().equals(huesped)){
                reservasADevolver[i] = new Reserva(reservasADevolver[i].getHuesped(), reservasADevolver[i].getHabitacion(), reservasADevolver[i].getRegimen(), reservasADevolver[i].getFechaInicioReserva(), reservasADevolver[i].getFechaFinReserva(), reservasADevolver[i].getNumeroPersonas());
            }
        }
        return reservasADevolver;*/
    }

    public Reserva[] getReserva(TipoHabitacion tipoHabitacion){
        return reservas.getReservas(tipoHabitacion);
        /*Reserva[] reservasADevolver = new Reserva[reservas.getReservas(tipoHabitacion).length];

        // Aquí hacemos esto para asegurarnos de que es el mismo huesped con un if.
        for (int i=0; i<reservas.getReservas(tipoHabitacion).length;i++) {
            if (reservasADevolver[i].getHabitacion().getTipoHabitacion().equals(tipoHabitacion)){
                reservasADevolver[i] = new Reserva(reservasADevolver[i].getHuesped(), reservasADevolver[i].getHabitacion(), reservasADevolver[i].getRegimen(), reservasADevolver[i].getFechaInicioReserva(), reservasADevolver[i].getFechaFinReserva(), reservasADevolver[i].getNumeroPersonas());
            }
        }
        return reservasADevolver;*/
    }

    public Reserva[] getReserva(Habitacion habitacion){
        /*if (habitacion == null)
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un tipo de habitación nula.");
        //return Arrays.stream(copiaProfundaReservas()).filter(reserva -> reserva.getHabitacion().getTipoHabitacion() == tipoHabitacion).toArray(Reserva[]::new);
        int contador = 0;
        for (Reserva contadorHabitacion : reservas.get()) {
            if (contadorHabitacion.getHabitacion().equals(habitacion)) {
                contador++;
            }
        }
        Reserva[] reservasHabitacion = new Reserva[contador];
        if (contador == 0) {
            System.out.println("La habitación no tiene ningúna reservada creada.");
        } else {

            contador = 0;
            for (Reserva reservaHabitacion : reservas.get()) {
                if (reservaHabitacion.getHabitacion().equals(habitacion)) {
                    reservasHabitacion[contador++] = reservaHabitacion;
                }
            }

        }return reservasHabitacion;*/

        // No había caído en que tenemos getReservasFuturas para poder pasar como parámetro habitacion, y habia creado mi propio getReservas de habitacion...
        return reservas.getReservasFuturas(habitacion);
        /*Reserva[] reservasADevolver = new Reserva[reservas.getReservasFuturas(habitacion).length];

        // Aquí hacemos esto para asegurarnos de que es el mismo huesped con un if.
        for (int i=0; i<reservas.getReservasFuturas(habitacion).length;i++) {
            if (reservasADevolver[i].getHabitacion().equals(habitacion)){
                reservasADevolver[i] = new Reserva(reservasADevolver[i].getHuesped(), reservasADevolver[i].getHabitacion(), reservasADevolver[i].getRegimen(), reservasADevolver[i].getFechaInicioReserva(), reservasADevolver[i].getFechaFinReserva(), reservasADevolver[i].getNumeroPersonas());
            }
        }
        return reservasADevolver;*/
    }

    public void realizarCheckin(Reserva reserva, LocalDateTime fecha){
        reservas.realizarCheckin(reserva,fecha);
    }

    public void realizarCheckout(Reserva reserva, LocalDateTime fecha){
        reservas.realizarCheckout(reserva,fecha);
    }

}
