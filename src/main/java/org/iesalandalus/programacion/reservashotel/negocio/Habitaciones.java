package org.iesalandalus.programacion.reservashotel.negocio;


import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;


public class Habitaciones {

    private Habitacion coleccionHabitaciones[];
    private int capacidad;
    private int tamano;

    public Habitaciones (int capacidad){
        if (capacidad<=0)
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");

        this.capacidad=capacidad;
        this.coleccionHabitaciones = new Habitacion[getCapacidad()];
        this.tamano=0;

    }

    public Habitacion[] get(){
        return copiaProfundaHabitaciones();
    }

    private Habitacion[] copiaProfundaHabitaciones(){
        // Esto lo he escrito con el proposito de que me pase los test, mirando en la información que pide que pase los test he creado objetos del mismo, pero no está bien así.
    /* coleccionHabitaciones[0] = new Habitacion(1,0,50, TipoHabitacion.SIMPLE);
        coleccionHabitaciones[1] = new Habitacion(2,10,50,TipoHabitacion.DOBLE);
        if (getCapacidad()>=3)
            coleccionHabitaciones[2] = new Habitacion(3,10,50,TipoHabitacion.SUITE);

        return Arrays.copyOf(coleccionHabitaciones, tamano);*/


        // Realicé esta modificación de ultima hora ya que de la otra manera me aceptaba los test pero era para salir del paso, pero realmente no funcionan bien.
        /*Habitacion copiaHabitaciones [] = new Habitacion[capacidad];
        for (int i=0; !tamanoSuperado(i);i++){
            copiaHabitaciones[i] = new Habitacion((coleccionHabitaciones[i]));
        }


        return copiaHabitaciones;*/

        int j=0;
        Habitacion[] copiaHabitaciones = new Habitacion[capacidad];
        for (int i = 0; i < capacidad; i++) {
            if (coleccionHabitaciones[i] != null) {
                copiaHabitaciones[j++] = new Habitacion(coleccionHabitaciones[i]);
            }

        }return Arrays.copyOf(copiaHabitaciones, j);

    }

    public Habitacion[] get(TipoHabitacion tipoHabitacion){
        return null;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getTamano() {
        return tamano;
    }

    public void insertar (Habitacion habitacion) throws OperationNotSupportedException{
        if (habitacion == null)
            throw new NullPointerException("ERROR: No se puede insertar una habitación nula.");
        int indice = buscarIndice(habitacion);
        if (capacidadSuperada(indice))
            throw new OperationNotSupportedException("ERROR: No se aceptan más habitaciones.");
        if (tamanoSuperado(indice))
            throw new OperationNotSupportedException("ERROR: Ya existe esta habitación");
        /*for (int i=0; i< tamano; i++){
            if (habitacion.getIdentificador().equals(coleccionHabitaciones[i].getIdentificador()))
                throw new OperationNotSupportedException("ERROR: Ya existe una habitación con ese identificador.");
        }*/

        coleccionHabitaciones[tamano]=habitacion;
        tamano++;
    }

    private int buscarIndice (Habitacion habitacion){
        if (habitacion == null)
            throw new NullPointerException("ERROR: la habitación indicada es nula.");
        for (int i=0; i<tamano; i++){
            if (habitacion.equals(coleccionHabitaciones[i])){
                return i;
            }
        }
        return -1;
    }

    private boolean tamanoSuperado (int indice){
        if (indice>-1)
            return true;
        else return false;
    }

    private boolean capacidadSuperada(int indice){
        indice=getTamano();
        if (indice>=getCapacidad())
            return true;
        else return false;
    }

    public Habitacion buscar (Habitacion habitacion){
        int indice = buscarIndice(habitacion);
        if (indice != -1)
            return new Habitacion(coleccionHabitaciones[indice]);
        else return null;
    }

    public void borrar (Habitacion habitacion) throws OperationNotSupportedException{
        if (habitacion == null)
            throw new NullPointerException("ERROR: No se puede borrar una habitación nula.");
        if (buscarIndice(habitacion)<0)
            throw new OperationNotSupportedException("ERROR: No existe ninguna habitación como la indicada.");
        int indice = buscarIndice(habitacion);
        desplazarUnaPosicionHaciaIzquierda(indice);
        tamano--;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice){
        for (int i = indice; i<tamano-1;i++){
            coleccionHabitaciones[i] = coleccionHabitaciones[i+1];
        }
        coleccionHabitaciones[tamano-1] = null;

    }



}