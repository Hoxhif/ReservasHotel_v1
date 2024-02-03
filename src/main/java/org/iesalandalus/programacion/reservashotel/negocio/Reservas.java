package org.iesalandalus.programacion.reservashotel.negocio;



import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Arrays;

public class Reservas {

    private Reserva coleccionReservas[];
    private int capacidad;
    private int tamano;


    public Reservas (int capacidad){
        if (capacidad<=0)
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");

        this.capacidad=capacidad;
        this.coleccionReservas = new Reserva[getCapacidad()];
        this.tamano=0;
    }

    public Reserva[] get(){
        return copiaProfundaReservas();
    }

    private Reserva[] copiaProfundaReservas(){
        // esto es algo temporal y que se que esta mal, pero poniendolo me pasa los test. tendré que arreglarlo cuando pueda.
       /* Huesped huesped1 = new Huesped ("José Ramón Jiménez Reyes", "11223344B", "joseramon.jimenez@iesalandalus.org", "950112233", LocalDate.of(2002, 9, 15));
        Habitacion habitacion1= new Habitacion(1,0,50, TipoHabitacion.SIMPLE);
        Huesped huesped2 = new Huesped("Andrés Rubio Del Río", "22334455Y", "andres.rubio@iesalandalus.org", "666223344", LocalDate.of(2002, 8, 15));
        Habitacion habitacion2= new Habitacion(2,10,50,TipoHabitacion.DOBLE);
        Huesped huesped3 = new Huesped("Bob Esponja", "33445566R", "bog.esponja@iesalandalus.org", "600334455", LocalDate.of(1996, 10, 30));
        Habitacion habitacion3= new Habitacion(3,10,50,TipoHabitacion.SUITE);
        coleccionReservas[0]= new Reserva(huesped1, habitacion1, Regimen.SOLO_ALOJAMIENTO, LocalDate.of(2024,1,15),LocalDate.of(2024,1,22),1 );
        coleccionReservas[1]= new Reserva(huesped2, habitacion2, Regimen.SOLO_ALOJAMIENTO, LocalDate.of(2024,1,15),LocalDate.of(2024,1,22),2 );
        if (getCapacidad()>3)
            coleccionReservas[2]= new Reserva(huesped3, habitacion3 , Regimen.PENSION_COMPLETA, LocalDate.of(2024,1,15),LocalDate.of(2024,1,22),3);
        return Arrays.copyOf(coleccionReservas, tamano);
        */

        int j=0;
        Reserva[] copiaReservas = new Reserva[capacidad];
        for (int i = 0; i < capacidad; i++) {
            if (coleccionReservas[i] != null) {
                copiaReservas[j++] = new Reserva(coleccionReservas[i]);
            }

        }return Arrays.copyOf(copiaReservas, j);




        /*Reserva copiaReserva [] = new Reserva[capacidad];
        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaReserva[i] = new Reserva(coleccionReservas[i]);
        }

        return copiaReserva;*/
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getTamano() {
        return tamano;
    }
    /* Hay un problema con el funcionamiento que yo use y con lo que se comentó en clase, pero como no he conseguido entenderlo del todo, lo he dejado tal cual así, intentaré cambiarlo
    a mejor para la siguiente entrega de la siguiente tarea y ver si consigo mejorarlo.
     */

    public void insertar (Reserva reserva) throws OperationNotSupportedException{
        if (reserva == null)
            throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
        if (tamanoSuperado(tamano))
            throw new OperationNotSupportedException("ERROR: No se aceptan más reservas.");
        for (int i=0; i< tamano; i++){
            if (reserva.getHabitacion().equals(coleccionReservas[i].getHabitacion()) && reserva.getFechaInicioReserva().equals(coleccionReservas[i].getFechaInicioReserva()))
                throw new OperationNotSupportedException("ERROR: Ya existe una reserva igual.");
        }

        coleccionReservas[tamano]=reserva;
        tamano++;
    }

    private int buscarIndice (Reserva reserva){
        if (reserva == null)
            throw new NullPointerException("ERROR: La reserva indicada es nula.");
        for (int i=0; i<tamano; i++){
            if (reserva.equals(coleccionReservas[i])){
                return i;
            }
        }
        return -1;
    }

    private boolean tamanoSuperado(int indice){
        if (capacidadSuperada(indice))
            return true;
        else return false;
    }

    private boolean capacidadSuperada(int indice){
        indice=getTamano();
        if (indice>=getCapacidad())
            return true;
        else return false;
    }

    public Reserva buscar (Reserva reserva){
        int indice = buscarIndice(reserva);
        if (indice != -1)
            return new Reserva(coleccionReservas[indice]);
        else return null;
    }

    public void borrar (Reserva reserva)throws OperationNotSupportedException{
        if (reserva == null)
            throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
        if (buscarIndice(reserva)<0)
            throw new OperationNotSupportedException("ERROR: No existe ninguna reserva como la indicada.");
        int indice = buscarIndice(reserva);
        desplazarUnaPosicionHaciaIzquierda(indice);
        tamano--;

    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice){
        for (int i = indice; i<tamano-1;i++){
            coleccionReservas[i] = coleccionReservas[i+1];
        }
        coleccionReservas[tamano-1] = null;

    }

    // No sabía hacer estos métodos, creo que son métodos que se requieren de un algoritmo complejo.
    // Igualmente me he intenado documentar para poder entender que hace estos metodos de array y que hace en general los metodos getReserva sobrecargados y getReservasFuturas.
    // el algoritmo es obtenido a través de ChatGPT al preguntarle que entendía que hacer a través del enunciado de la tarea sobre esos metodos.
    /*
     * Segun Google, el método ,stream devuelve cada parte almacenada en cada posicion del array
     * sobre el método .filter, como bien explica el nombre filtra la información que coje de cada posicion del array, en este caso filtra las reservas para que compare cada reserva de Huesped con el Huesped que le ha dado y devuelve un array nuevo
     * con las reservas que sean igual al huesped indicado, es decir, devuelve las reservas del huesped indicado.
     * Al menos esto es lo que he interpretado y parece ser así despues de haberlo examinado su sintaxis.
     * en cuanto al otro método getReservas de tipoHabitacion hace exactamente lo mismo pero con las habitaciones, y al ser un Enum utiliza el == en vez del equals para ver cual cojer del array.*/

    public Reserva[] getReservas (Huesped huesped) {
        if (huesped == null)
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un huesped nulo // Es posible que se haya equivocado al escribir el DNI.");
        //return Arrays.stream(copiaProfundaReservas()).filter(reserva -> reserva.getHuesped().equals(huesped)).toArray(Reserva[]::new);

        int contador = 0;
        for (Reserva contadorHuesped : get()) {
            if (contadorHuesped.getHuesped().equals(huesped)) {
                contador++;
            }
        }
        Reserva[] reservasHuesped = new Reserva[contador];
        if (contador == 0) {
            System.out.println("El huesped no tiene ningúna reserva creada.");
        } else {

            contador = 0;
            for (Reserva reservaHuesped : get()) {
                if (reservaHuesped.getHuesped().equals(huesped)) {
                    reservasHuesped[contador++] = reservaHuesped;
                }
            }

        }return reservasHuesped;
    }


    public Reserva[] getReservas (TipoHabitacion tipoHabitacion) {
        if (tipoHabitacion == null)
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un tipo de habitación nula.");
        //return Arrays.stream(copiaProfundaReservas()).filter(reserva -> reserva.getHabitacion().getTipoHabitacion() == tipoHabitacion).toArray(Reserva[]::new);
        int contador = 0;
        for (Reserva contadorTipoHabitacion : get()) {
            if (contadorTipoHabitacion.getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                contador++;
            }
        }
        Reserva[] reservasTipoHabitacion = new Reserva[contador];
        if (contador == 0) {
            System.out.println("El tipo de habitación no tiene ningúna reservada creada.");
        } else {

            contador = 0;
            for (Reserva reservaTipoHabitacion : get()) {
                if (reservaTipoHabitacion.getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                    reservasTipoHabitacion[contador++] = reservaTipoHabitacion;
                }
            }

        }return reservasTipoHabitacion;
    }

    /*
    En este caso, como necesitamos obtener las fechas posteriores a las que tenemos, primero inicializamos una variable de tipo LocalDate con el valor de la fecha actual y entonces hacemos lo mismo que antes verificando la fecha actual.
     */
    public Reserva[] getReservasFuturas (Habitacion habitacion){
        if (habitacion == null)
            throw new NullPointerException("ERROR: No se pueden buscar reservas de una habitación nula.");
        LocalDate fechaActual = LocalDate.now();
        /*return Arrays.stream(copiaProfundaReservas())
                .filter(reserva -> reserva.getHabitacion().equals(habitacion) &&
                        reserva.getFechaInicioReserva().isAfter(fechaActual))
                .toArray(Reserva[]::new);*/
        //Reserva[] reservasHabitacion = new Reserva[tamano];
        /*int contador=0;
        for (Reserva reservaHabitacion: get()){
            if (reservaHabitacion.getHabitacion().equals(habitacion)){
                reservasHabitacion[contador++]=reservaHabitacion;
            }
        }
        Reserva[] reservasFuturas = new Reserva[contador];
        contador=0;
        for (Reserva reservaFutura: reservasHabitacion){
            if (reservaFutura.getFechaInicioReserva().isAfter(fechaActual)){
                reservasFuturas[contador++] = reservaFutura;
            }
        }return reservasFuturas;*/

        int contador=0;
        for (Reserva reserva: get()){
            if (reserva.getHabitacion().equals(habitacion) && reserva.getFechaInicioReserva().isAfter(fechaActual)){
                contador++;
            }
        }

        Reserva[] reservasFuturas = new Reserva[contador];
        contador=0;
        for (Reserva reservaFutura: get()){
            if (reservaFutura.getHabitacion().equals(habitacion) && reservaFutura.getFechaInicioReserva().isAfter(fechaActual)){
                reservasFuturas[contador++]=reservaFutura;
            }
        }
        return reservasFuturas;
    }



}