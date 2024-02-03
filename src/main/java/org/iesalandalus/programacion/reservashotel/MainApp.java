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
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;



public class MainApp {

    // Estos atributos de clase los he puesto publicos porque si no me dan errores que no se como solventar de otra manera... Por ahora estan así.
    public static Habitaciones habitaciones;
    public static Reservas reservas;
    public static Huespedes huespedes;
    public static final int CAPACIDAD=5;


    private static void ejecutarOpcion(Opcion opcion){
        switch (opcion) {
            case SALIR:
                break;
            case INSERTAR_HUESPED:
                insertarHuesped();
                break;
            case BUSCAR_HUESPED:
                buscarHuesped();
                break;
            case BORRAR_HUESPED:
                borrarHuesped();
                break;
            case MOSTRAR_HUESPEDES:
                mostrarHuespedes();
                break;
            case INSERTAR_HABITACION:
                insertarHabitacion();
                break;
            case BUSCAR_HABITACION:
                buscarHabitacion();
                break;
            case BORRAR_HABITACION:
                borrarHabitacion();
                break;
            case MOSTRAR_HABITACIONES:
                mostrarHabitaciones();
                break;
            case INSERTAR_RESERVA:
                insertarReserva();
                break;
            case ANULAR_RESERVA:
                anularReserva();
                break;
            case MOSTRAR_RESERVAS:
                mostrarReservas();
                break;
            //case CONSULTAR_DISPONIBILIDAD:
            //  consultarDisponibilidad(Consola.leerTipoHabitacion(), Consola.leerFecha("Inserte la fecha de posible reserva: "), Consola.leerFecha("Inserte la fecha de posible fin reserva: "));
            //break;
            default:
                break;
        }
    }

    private static void insertarHuesped(){
        // En esta parte lo que hago es crear un nuevo objeto de tipo huesped y le doy como valor los datos que devuelven leerHuesped al llamar al metodo y despues llamo al metodo de la clase huespedes para insertarlo en el array pasando por parametro al propiop huesped creado.
        // se podría realmente haber omitido la linea de crear un nuevo objeto nuevoHuesped y directamente haber puesto en huespedes.insertar(Consola.leerHuesped());
        try {
            Huesped nuevoHuesped = Consola.leerHuesped();
            huespedes.insertar(nuevoHuesped);
            System.out.println("Huesped creado satisfactoriamente");
        } catch (OperationNotSupportedException | NullPointerException e) {
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
            System.out.println("Huesped borrado satisfactoriamente");
        }catch (OperationNotSupportedException e){
            System.out.println(e.getMessage());
        }

    }

    private static void mostrarHuespedes(){
        if (huespedes.getTamano()==0){
            System.out.println("No hay huespedes a mostrar. ");
        }else{
            System.out.println("Listado de huespedes: ");
            for (Huesped huesped: huespedes.get()){
                System.out.println(huesped.toString());
            }
        }

    }

    private static void insertarHabitacion(){
        try{
            Habitacion nuevaHabitacion = Consola.leerHabitacion();
            habitaciones.insertar(nuevaHabitacion);
            System.out.println("Habitación creada satisfactoriamente");
        }catch (OperationNotSupportedException | NullPointerException e){
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
            System.out.println("Habitación borrada satisfactoriamente");
        }catch (OperationNotSupportedException e){
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarHabitaciones(){
        if (habitaciones.getTamano()==0){
            System.out.println("No hay habitaciones a mostrar. ");
        }else{
            System.out.println("Listado de Habitaciones: ");
            for (Habitacion habitacion: habitaciones.get()){
                System.out.println(habitacion.toString());
            }
        }
    }

    private static void insertarReserva(){
        try{
            Reserva nuevaReserva = Consola.leerReserva();
            if (reservas.get().length>0) {
                Habitacion habitacionDisponible = consultarDisponibilidad(nuevaReserva.getHabitacion().getTipoHabitacion(), nuevaReserva.getFechaInicioReserva(), nuevaReserva.getFechaFinReserva());
                if (habitacionDisponible != null) {
                    reservas.insertar(nuevaReserva);
                    System.out.println("Reserva creada satisfactoriamente");
                } else
                    System.out.println("No se puede realizar la reserva en esas fechas. No se encuentra disponible la habitación");
            }else{
                reservas.insertar(nuevaReserva);
                System.out.println("Reserva creada satisfactoriamente");
            }
        }catch (OperationNotSupportedException | NullPointerException e){
            System.out.println(e.getMessage());
        }
    }


    private static void listarReservas(Huesped huesped){

        /*if (huesped==null)
            throw new NullPointerException("ERROR: El huesped es nulo.");
    Reserva [] reservasHuesped=new Reserva[CAPACIDAD];
    int contador = 0;
    for (Reserva reserva: reservas.get()){
        if (reserva.getHuesped().equals(huesped))
            reservasHuesped[contador++]=reserva;
    }

    if (contador==0)
        System.out.println("El huesped con DNI "+ huesped.getDni()+" no tiene reservas realizadas");
    else{
        System.out.println("Listado de reservas del Huesped "+huesped.getNombre()+ ":");
        for (int i = 0; i < contador; i++) {
            System.out.println(reservasHuesped[i].toString());
            }
        }*/

        try{
            for (Reserva reservasHuesped: reservas.getReservas(huesped)){
                System.out.println(reservasHuesped);
            }
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
    }


    private static void listarReservas (TipoHabitacion tipoHabitacion){
       /* if (tipoHabitacion==null)
            throw new NullPointerException("ERROR: El tipo de habitación es nulo.");
        Reserva [] reservasHabitacion=new Reserva[CAPACIDAD];
        int contador = 0;
        for (Reserva reserva: reservas.get()){
            if (reserva.getHabitacion().getTipoHabitacion().equals(tipoHabitacion))
                reservasHabitacion[contador++]=reserva;
        }

        if (contador==0)
            System.out.println("El tipo de habitación "+ tipoHabitacion+" no tiene reservas realizadas");
        else{
            System.out.println("Listado de reservas del tipo de habitación "+tipoHabitacion+ ":");
            for (int i = 0; i < contador; i++) {
                System.out.println(reservasHabitacion[i].toString());
            }
        }*/
        try {
            for (Reserva reservasTipoHabitacion: reservas.getReservas(tipoHabitacion)){
                System.out.println(reservasTipoHabitacion);
            }
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    private static Reserva[] getReservasAnulables (Reserva[] reservasAAnular){
        LocalDate fechaAhora= LocalDate.now();
        int capacidadReservasAnulables=0;
        for (Reserva reserva: reservasAAnular){
            if (reserva != null && reserva.getFechaInicioReserva().isAfter(fechaAhora))
                capacidadReservasAnulables++; // Aquí creamos la capacidad del array que vamos a crear y que luego vamos a devolver, contando la cantidad de reservas que se todavía no han llegado la fecha de inicio.
        }
        int i=0;
        Reserva[] reservaAnulables = new Reserva[capacidadReservasAnulables];
        for (Reserva reserva: reservaAnulables){ //ESTO HAY QUE COMPROBARLO.
            if (reserva != null && reserva.getFechaInicioReserva().isAfter(fechaAhora))
                reservaAnulables[i++]=reserva;
        }
        return reservaAnulables;
    }

    private static void anularReserva() {

        int opcion = 0;
        Huesped huesped = Consola.getClientePorDni();
        // Aqui lo que hago es copiar el codigo de leerReserva de un Huesped para poder saber cuales son de nuevo las reservas de ese huesped, ya que el método no devuelve nada.
        Reserva[] reservasHuesped = new Reserva[CAPACIDAD];
        int contador = 0;
        for (Reserva reserva : reservas.get()) {
            if (reserva.getHuesped().equals(huesped))
                reservasHuesped[contador++] = reserva;
        }

        if (getReservasAnulables(reservasHuesped).length>0) {
            if (contador == 0)
                System.out.println("No hay reservas a anuelar, el Huesped no tiene hecha ninguna reserva.");
            else {
                if (contador == 1) {
                    Reserva reservaHuespedABorrar = reservasHuesped[0];
                    do{
                        System.out.println("Desea anular la reserva que tiene?");
                        System.out.println("1.- Sí");
                        System.out.println("2.- No");
                        System.out.println("Escoja una opción: ");
                        opcion = Entrada.entero();
                        if (opcion<1 || opcion>2)
                            System.out.println("Opción inválida, por favor, ingrese una opción correcta.");
                    }while (opcion<1 || opcion>2);
                    switch (opcion){
                        case 1:
                            try {
                                reservas.borrar(reservaHuespedABorrar);
                                System.out.println("La reserva se ha borrado satisfactoriamente.");
                                break;
                            }catch (OperationNotSupportedException e){
                                System.out.println(e.getMessage());
                            }
                        case 2:
                            System.out.println("Operación abortada");
                            break;
                    }

                } else {
                    // ME FALTA POR AÑADIR LA CONFIRMACIÓN FINAL DE BORRAR LA RESERVA QUE SE SELECCIONA.
                    do {
                        int posicion=1;
                        System.out.println("Listado de reservas del Huesped " + huesped.getNombre() + ":");
                        for (int i = 0; i < contador; i++) {
                            System.out.println(posicion+".- "+reservasHuesped[i].toString());
                            posicion++;
                        }
                        System.out.println("Indique qué reserva desea anular: ");
                        opcion = Entrada.entero();
                        if (opcion<1 || opcion>reservasHuesped.length)
                            System.out.println("Opción inválida, por favor, ingrese una opción correcta,");
                    }while (opcion<1 || opcion>reservasHuesped.length);
                    try{
                        reservas.borrar(reservasHuesped[opcion-1]);
                        System.out.println("La reserva se ha borrado satisfactoriamente.");
                    }catch(OperationNotSupportedException e){
                        System.out.println(e.getMessage());
                    }
                }

            }
        }
    }

    private static void mostrarReservas() {
        int opcion;
        if (reservas.getTamano() == 0) {
            System.out.println("No hay reservas a mostrar. ");
        } else {
            do {
                System.out.println("Indique el tipo de Reservas a mostrar: ");
                System.out.println("1.- Listar todas las reservas");
                System.out.println("2.- Listar reservas por huesped");
                System.out.println("3.- Listar reservas por tipo de habitación");
                opcion = Entrada.entero();
                if (opcion < 1 || opcion > 3) System.out.println("Indique una opción válida.");
            } while (opcion < 1 || opcion > 3);
            switch (opcion) {
                case 1:
                    System.out.println("Listado de Reservas: ");
                    for (Reserva reserva : reservas.get()) {
                        System.out.println(reserva.toString());
                    }
                    break;
                case 2:
                    try{
                        listarReservas(Consola.getClientePorDni());}catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        listarReservas(Consola.leerTipoHabitacion());
                    }catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
            }
        }
    }

    // Aquí no supe que tenía que hacer por que no se que tipo de método es, en tanto que no se que tipo de dato devuelve.
    // POSIBLEMENTE ESTE METODO NO ESTE BIEN IMPLEMENTADO DEL TODO.
    private static int getNumElementosNoNulos(Reserva[] reservaNoNula){

        int contador = 0;
        for (Reserva reserva : reservaNoNula) {
            if (reserva != null) {
                contador++;
            }
        }
        return contador;
    }




    private static Habitacion consultarDisponibilidad(TipoHabitacion tipoHabitacion, LocalDate fechaInicioReserva, LocalDate fechaFinReserva){
        /* Este algorítmo es sacado de la carpeta de drive donde se nos ha proporcionado el mismo.  */
        boolean tipoHabitacionEncontrada=false;
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

    }






    public static void main(String[] args) {
        //Inicio del programa.
        habitaciones = new Habitaciones(CAPACIDAD);
        huespedes = new Huespedes(CAPACIDAD);
        reservas = new Reservas(CAPACIDAD);

        Opcion opcion=Opcion.SALIR;
        do {
            Consola.mostrarMenu();
            opcion=Consola.elegirOpcion();
            ejecutarOpcion(opcion);
        }while (opcion != Opcion.SALIR);
        System.out.println("Fin del programa.");
    }


}