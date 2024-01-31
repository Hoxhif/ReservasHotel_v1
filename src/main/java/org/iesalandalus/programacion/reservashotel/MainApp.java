package org.iesalandalus.programacion.reservashotel;


import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.negocio.Reservas;
import org.iesalandalus.programacion.reservashotel.vista.Consola;
import org.iesalandalus.programacion.reservashotel.vista.Opcion;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

import static org.iesalandalus.programacion.reservashotel.vista.Opcion.*;

public class MainApp {

    // Estos atributos de clase los he puesto publicos porque si no me dan errores que no se como solventar de otra manera... Por ahora estan así.
    public static Habitaciones  habitaciones;
    public static Reservas  reservas;
    public static Huespedes  huespedes;
    public static final int CAPACIDAD=5;


    private static void ejecutarOpcion(Opcion opcion){
        switch (opcion) {
            case SALIR:
                break;
            case INSERTAR_HUESPED:
                insertarHuesped();
            case BUSCAR_HUESPED:
                buscarHuesped();
            case BORRAR_HUESPED:
                borrarHuesped();
            case MOSTRAR_HUESPEDES:
                mostrarHuespedes();
            case INSERTAR_HABITACION:
                insertarHabitacion();
            case BUSCAR_HABITACION:
                buscarHabitacion();
            case BORRAR_HABITACION:
                borrarHabitacion();
            case MOSTRAR_HABITACIONES:
                mostrarHabitaciones();
            case INSERTAR_RESERVA:
                insertarReserva();
            case ANULAR_RESERVA:
                anularReserva();
            case MOSTRAR_RESERVAS:
                mostrarReservas();
                //case 13:
                //consultarDisponibilidad();
            default:
                break;
        }
    }

    private static void insertarHuesped(){
        // En esta parte lo que hago es crear un nuevo objeto de tipo huesped y le doy como valor los datos que devuelven leerHuesped al llamar al metodo y despues llamo al metodo de la clase huespedes para insertarlo en el array pasando por parametro al propiop huesped creado.
        // se podría realmente haber omitido la linea de crear un nuevo objeto nuevoHuesped y directamente haber puesto en huespedes.insertar(Consola.leerHuesped());.
        try{
            Huesped nuevoHuesped = Consola.leerHuesped();
            huespedes.insertar(nuevoHuesped);
        }catch (OperationNotSupportedException e){
            System.out.println(e.getMessage());
        }
    }

    private static void buscarHuesped(){
        System.out.println(Consola.getClientePorDni());
    }

    private static void borrarHuesped(){
        Huesped huespedABorrar = Consola.getClientePorDni();
        try {
            huespedes.borrar(huespedABorrar);
        }catch (OperationNotSupportedException e){
            System.out.println(e.getMessage());
        }

    }

    private static void mostrarHuespedes(){
        if (huespedes.getTamano()==0){
            System.out.println("No hay huespedes a mostrar. ");
        }else if (huespedes.getTamano()>0){
            huespedes.get();
        }

    }

    private static void insertarHabitacion(){
        try{
            Habitacion nuevaHabitacion = Consola.leerHabitacion();
            habitaciones.insertar(nuevaHabitacion);
        }catch (OperationNotSupportedException e){
            System.out.println(e.getMessage());
        }
    }

    private static void buscarHabitacion(){
        System.out.println(Consola.leerHabitacionPorIdentificador());
    }

    private static void borrarHabitacion(){
        Habitacion habitacionABorrar = Consola.leerHabitacionPorIdentificador();
        try{
            habitaciones.borrar(habitacionABorrar);
        }catch (OperationNotSupportedException e){
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarHabitaciones(){
        if (habitaciones.getTamano()==0){
            System.out.println("No hay habitaciones a mostrar. ");
        }else if (habitaciones.getTamano()>0){
            habitaciones.get();
        }

    }

    private static void insertarReserva(){
        try{
            Reserva nuevaReserva = Consola.leerReserva();
            reservas.insertar(nuevaReserva);
        }catch (OperationNotSupportedException e){
            System.out.println(e.getMessage());
        }
    }


    private static void listarReservas(Huesped huesped){


    }

    private static void listarReservas (TipoHabitacion tipoHabitacion){

    }

    /* private static Reserva[] getReservasAnulables (Reserva[] reservasAAnular){

     }
 */
    private static void anularReserva(){

    }

    private static void mostrarReservas(){
        if (reservas.getTamano()==0){
            System.out.println("No hay reservas a mostrar. ");
        }else if (reservas.getTamano()>0){
            reservas.get();
        }
    }

    // Aquí no supe que tenía que hacer por que no se que tipo de método es, en tanto que no se que tipo de dato devuelve.
   /* private static Reservas getNumElementosNoNulos(){

    }
*/
/*

    private static Habitacion consultarDisponibilidad(TipoHabitacion tipoHabitacion, LocalDate fechaInicioReserva, LocalDate fechaFinReserva){
        /* Este algorítmo es sacado de la carpeta de drive donde se nos ha proporcionado el mismo.  */
        /* boolean tipoHabitacionEncontrada=false;
        Habitacion habitacionDisponible=null;
        int numElementos=0;

        Habitacion[] habitacionesTipoSolicitado= habitaciones.get(tipoHabitacion);

        if (habitacionesTipoSolicitado==null)
            return habitacionDisponible;

        for (int i=0; i<habitacionesTipoSolicitado.length && !tipoHabitacionEncontrada; i++)
        {

            if (habitacionesTipoSolicitado[i]!=null)
            {
                Reserva[] reservasFuturas = reservas.getReservasFuturas(habitacionesTipoSolicitado[i]);
                numElementos=getNumElementosNoNulos(reservasFuturas);

                if (numElementos == 0)
                {
                    //Si la primera de las habitaciones encontradas del tipo solicitado no tiene reservas en el futuro,
                    // quiere decir que está disponible.
                    habitacionDisponible=new Habitacion(habitacionesTipoSolicitado[i]);
                    tipoHabitacionEncontrada=true;
                }
                else {

                    //Ordenamos de mayor a menor las reservas futuras encontradas por fecha de fin de la reserva.
                    // Si la fecha de inicio de la reserva es posterior a la mayor de las fechas de fin de las reservas
                    // (la reserva de la posición 0), quiere decir que la habitación está disponible en las fechas indicadas.

                    Arrays.sort(reservasFuturas, 0, numElementos, Comparator.comparing(Reserva::getFechaFinReserva).reversed());

                    /*System.out.println("\n\nMostramos las reservas ordenadas por fecha de inicio de menor a mayor (numelementos="+numElementos+")");
                    mostrar(reservasFuturas);*/
        /*

                    if (fechaInicioReserva.isAfter(reservasFuturas[0].getFechaFinReserva())) {
                        habitacionDisponible = new Habitacion(habitacionesTipoSolicitado[i]);
                        tipoHabitacionEncontrada = true;
                    }

                    if (!tipoHabitacionEncontrada)
                    {
                        //Ordenamos de menor a mayor las reservas futuras encontradas por fecha de inicio de la reserva.
                        // Si la fecha de fin de la reserva es anterior a la menor de las fechas de inicio de las reservas
                        // (la reserva de la posición 0), quiere decir que la habitación está disponible en las fechas indicadas.

                        Arrays.sort(reservasFuturas, 0, numElementos, Comparator.comparing(Reserva::getFechaInicioReserva));

                        /*System.out.println("\n\nMostramos las reservas ordenadas por fecha de inicio de menor a mayor (numelementos="+numElementos+")");
                        mostrar(reservasFuturas);*/
        /*

                        if (fechaFinReserva.isBefore(reservasFuturas[0].getFechaInicioReserva())) {
                            habitacionDisponible = new Habitacion(habitacionesTipoSolicitado[i]);
                            tipoHabitacionEncontrada = true;
                        }
                    }

                    //Recorremos el array de reservas futuras para ver si las fechas solicitadas están algún hueco existente entre las fechas reservadas
                    if (!tipoHabitacionEncontrada)
                    {
                        for(int j=1;j<reservasFuturas.length && !tipoHabitacionEncontrada;j++)
                        {
                            if (reservasFuturas[j]!=null && reservasFuturas[j-1]!=null)
                            {
                                if(fechaInicioReserva.isAfter(reservasFuturas[j-1].getFechaFinReserva()) &&
                                        fechaFinReserva.isBefore(reservasFuturas[j].getFechaInicioReserva())) {

                                    habitacionDisponible = new Habitacion(habitacionesTipoSolicitado[i]);
                                    tipoHabitacionEncontrada = true;
                                }
                            }
                        }
                    }


                }
            }
        }

        return habitacionDisponible;
        */
/*
    }

*/




    public static void main(String[] args) {
        habitaciones = new Habitaciones(CAPACIDAD);
        huespedes = new Huespedes(CAPACIDAD);
        reservas = new Reservas(CAPACIDAD);


    }



}
