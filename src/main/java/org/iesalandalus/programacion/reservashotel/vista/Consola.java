package org.iesalandalus.programacion.reservashotel.vista;




import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.iesalandalus.programacion.reservashotel.MainApp.*;


public class Consola {
    private Consola(){

    }

    public static void mostrarMenu(){
        System.out.println("Bienvenido al programa de gestión de reservas de un Hotel, creado por José Antonio Guirado González");
        System.out.println("Opciones: ");

        Opcion menuOpciones[]= Opcion.values();

        for (Opcion opcion: menuOpciones){
            if (opcion.ordinal()<menuOpciones.length-1) //De esta manera quito la opcion de consultar disponibilidad del menu.
                System.out.println(opcion);
        }


        /*System.out.println(Opcion.SALIR);
        System.out.println(Opcion.INSERTAR_HUESPED);
        System.out.println(Opcion.BUSCAR_HUESPED);
        System.out.println(Opcion.BORRAR_HUESPED);
        System.out.println(Opcion.MOSTRAR_HUESPEDES);
        System.out.println(Opcion.INSERTAR_HABITACION);
        System.out.println(Opcion.BUSCAR_HABITACION);
        System.out.println(Opcion.BORRAR_HABITACION);
        System.out.println(Opcion.MOSTRAR_HABITACIONES);
        System.out.println(Opcion.INSERTAR_RESERVA);
        System.out.println(Opcion.ANULAR_RESERVA);
        System.out.println(Opcion.MOSTRAR_RESERVAS);
        System.out.println(Opcion.CONSULTAR_DISPONIBILIDAD);*/
    }


    public static Opcion elegirOpcion(){
        int opcion;
        do {
            System.out.println("Elija una opción: ");
            opcion = Entrada.entero();
            if (opcion<=0 || opcion>Opcion.values().length+1)
                System.out.println("Opción no válida.");
        }while (opcion<=0 || opcion>Opcion.values().length+1); //De esta manera si ingresa un valor mayor a el ordinal de un enum se repite el bucle.
        switch (opcion){
            case 1: return Opcion.SALIR;
            case 2: return Opcion.INSERTAR_HUESPED;
            case 3: return Opcion.BUSCAR_HUESPED;
            case 4: return Opcion.BORRAR_HUESPED;
            case 5: return Opcion.MOSTRAR_HUESPEDES;
            case 6: return Opcion.INSERTAR_HABITACION;
            case 7: return Opcion.BUSCAR_HABITACION;
            case 8: return Opcion.BORRAR_HABITACION;
            case 9: return Opcion.MOSTRAR_HABITACIONES;
            case 10: return Opcion.INSERTAR_RESERVA;
            case 11: return Opcion.ANULAR_RESERVA;
            case 12: return Opcion.MOSTRAR_RESERVAS;
            case 13: return(Opcion.CONSULTAR_DISPONIBILIDAD);
            default:return Opcion.SALIR;
        }
    }

    public static Huesped leerHuesped(){
        String nombre, dni, telefono, correo;

        System.out.println("Escriba el nombre del Huesped: ");
        nombre = Entrada.cadena();
        System.out.println("Escriba el DNI del Huesped: ");
        dni = Entrada.cadena();
        System.out.println("Escriba el telefono del Huesped: ");
        telefono = Entrada.cadena();
        System.out.println("Escriba el correo del Huesped: ");
        correo = Entrada.cadena();

        try{
            return new Huesped(nombre,dni,correo,telefono,leerFecha("Escriba su fecha de nacimiento: "));
        }catch (NullPointerException | IllegalArgumentException e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    public static Huesped getClientePorDni(){
        // método sujeto a cambios ...
        // Este algoritmo ha sido creado gracias a que en la clase MainApp tengo acceso public en los atributos.
        // Si no no podría hacerlo de otra mnaera.
        try{
            String dni;
            System.out.println("Inserte el DNI del huesped: ");
            dni = Entrada.cadena();

            for (Huesped huespedConDni: huespedes.get()){
                if (huespedConDni.getDni().equals(dni)){
                    return huespedConDni;
                }
            }
        }catch (NullPointerException | IllegalArgumentException e){
            System.out.println(e.getMessage());

        }return null;


        // Aquí, leyendo el enunciado, yo entendía que, introduciendo un DNI, podamos obtener los datos de un huesped ya existente en el sistema, su nombre, correo, telefono, etc...
        // Sin embargo, no se como hacerlo, ya que no consigo entender como añadir el array dentro de esta clase para que lo recorre y hacer la comparacion del DNI de un huesped con el DNI introducido.
        // pregunté a chatGPT sobre este metodo en concreto y me dio una solución que, no se si podría funcionar porque no llegué a implementar ya que era una solucion en la que creaba el metodo con parámetros, cosa que
        // no podía hacer porque este mñetodo no se le pasan parámetros. Pregunte a compañeros de la clase que habían hecho este método sobre que es lo que hicieron mas o menos para tener una idea de que hacer y me dijeron
        // que lo que habian hecho era pedirle el DNI y crear un objeto con los parametros inventados menos el dni que sería el que nosotros insertamos...


    /*String dni;
        System.out.println("Inserte el Dni del huesped: ");
        dni = Entrada.cadena();
        try{
            return new Huesped("José Juan",dni, "josejuan@gmail.com","654432143", LocalDate.of(2001,01,01));
        }catch (NullPointerException | IllegalArgumentException e){
            System.out.println(e.getMessage());
        }return null;*/

    }

    public static LocalDate leerFecha(String mensaje){
        // En este método tampoco supe bien que habia realmente que hacer, asi que lo que hice fue pasar el tipo String de fecha que preguntamos al crear el huesped y lo convertimos en LocalDate
        // haciendo un return de el array separado por el "-" pasado a entero y luego ese valor se lo asignamos a la fecha de nacimiento del huesped.
        /*String fecha;

        do{
            System.out.println(mensaje);
            fecha = Entrada.cadena();
            if (!fecha.matches("\\d{2}/\\d{2}/\\d{4}")) { //he tenido que usar esto en vez de dd/MM/yyyy para poder usar el matches, según en un foro, no se puede usar el matches con dd/MM/yyyy porque no entiende el matches el pattern de esa manera.
                System.out.println("La fecha introducida tiene un formato incorrecto (Usar dd/MM/yyyy)");
                fecha=null;
            }
        }while (fecha==null);
        String fechaConvertida [] = fecha.split("/");

        LocalDate fechaFinal = LocalDate.of(Integer.parseInt(fechaConvertida[2]), Integer.parseInt(fechaConvertida[1]), Integer.parseInt(fechaConvertida[0]));
        if (fechaFinal.isLeapYear()){
            return fechaFinal;
        }
        return fechaFinal;*/
        // Este método lo he obtenido a través de chatGPT porque tengo un problema que no supe solucionar con el hecho de que es un año bisiesto y me da un error cuando crea una reserva.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = null;

        do {
            try {
                System.out.println(mensaje);
                String fechaString = Entrada.cadena();
                fecha = LocalDate.parse(fechaString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("La fecha introducida tiene un formato incorrecto (Usar dd/MM/yyyy)");
            }
        } while (fecha == null);

        return fecha;
    }

    public LocalDateTime leerFechaHora(String mensaje){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Reserva.FORMATO_FECHA_HORA_RESERVA);
        LocalDateTime fecha = null;

        do {
            try {
                System.out.println(mensaje);
                String fechaString = Entrada.cadena();
                fecha = LocalDateTime.parse(fechaString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("La fecha introducida tiene un formato incorrecto (Usar dd/MM/yyyy hh:mm:ss)");
            }
        } while (fecha == null);

        return fecha;
    }


    public static Habitacion leerHabitacion(){
        int numeroPlanta, numeroPuerta;
        double precio;

        System.out.println("Escriba el número de la planta: ");
        numeroPlanta = Entrada.entero();
        System.out.println("Escriba el número de la puerta: ");
        numeroPuerta = Entrada.entero();
        System.out.println("Escriba el precio de la habitación: ");
        precio = Entrada.realDoble();
        System.out.println("Indique el tipo de habitación: ");
        TipoHabitacion tipo = leerTipoHabitacion();
        try {
            return new Habitacion(numeroPlanta, numeroPuerta, precio, tipo);
        }catch (NullPointerException | IllegalArgumentException e){
            System.out.println(e.getMessage());
            return null;
        }
        /*do {
            System.out.println("¿Desea indicar el tipo de habitación?");
            System.out.println("1.- Sí");
            System.out.println("2.- No");
            opcion = Entrada.entero();
            if (opcion<1 || opcion>2) System.out.println("Debe seleccionar una opción adecuada");
        }while (opcion<1 || opcion>2);
        if (opcion == 2){
            try{
                return new Habitacion(numeroPlanta,numeroPuerta,precio);
            }catch (NullPointerException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }else if (opcion == 1){
            TipoHabitacion tipo = leerTipoHabitacion();
            try {
                return new Habitacion(numeroPlanta, numeroPuerta, precio, tipo);
            }catch (NullPointerException | IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }

*/

    }

    public static Habitacion leerHabitacionPorIdentificador(){

        try{
            int numPlanta, numPuerta;
            String combinacion;
            System.out.println("Indique el numero de la planta: ");
            numPlanta = Entrada.entero();
            System.out.println("Indique el numero de la puerta: ");
            numPuerta = Entrada.entero();
            combinacion = ""+numPlanta+numPuerta;
            for (Habitacion habitacionCorrespondiente: habitaciones.get()){
                if (habitacionCorrespondiente.getIdentificador().equals(combinacion))
                    return habitacionCorrespondiente;
            }
        }catch (NullPointerException | IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        return null;

        // En este método entendí un poco lo mísmo que en el método del Dni, por lo que hice lo mismo.
        /*int planta, puerta;
        System.out.println("Inserte la planta de la habitación: ");
        planta = Entrada.entero();
        System.out.println("Inserte la puerta de la habitación: ");
        puerta = Entrada.entero();

        // método sujeto a cambios ...

        try{
            return new Habitacion(planta,puerta,50,TipoHabitacion.SIMPLE);
        }catch (NullPointerException | IllegalArgumentException e){
            System.out.println(e.getMessage());
        }return null;*/

    }

    public static TipoHabitacion leerTipoHabitacion(){
        int opcion;
        do {
            System.out.println("Tipos de habitación: ");

            TipoHabitacion menuOpciones[]= TipoHabitacion.values();

            for (TipoHabitacion tipo: menuOpciones){
                System.out.println(tipo.toString());
            }

            System.out.println("Elije un tipo de habitación: ");
            opcion = Entrada.entero();
            if (opcion<1 || opcion>TipoHabitacion.values().length) System.out.println("Elija una opción adecuada, por favor.");
        }while (opcion<1 || opcion>TipoHabitacion.values().length);
        switch (opcion){
            case 1: return TipoHabitacion.SIMPLE;
            case 2: return TipoHabitacion.DOBLE;
            case 3: return TipoHabitacion.TRIPLE;
            case 4: return TipoHabitacion.SUITE;
            default: return TipoHabitacion.SIMPLE;
        }
    }

    public static Regimen leerRegimen(){
        int opcion;
        do {
            System.out.println("Tipos de régimen: ");
            Regimen menuOpciones[]= Regimen.values();

            for (Regimen regimen: menuOpciones){
                System.out.println(regimen.toString());
            }
            System.out.println("Elije un tipo de régimen: ");
            opcion = Entrada.entero();
            if (opcion<1 || opcion>Regimen.values().length+1) System.out.println("Elija una opción adecuada, por favor.");
        }while (opcion<1 || opcion>Regimen.values().length+1);
        switch (opcion){
            case 1: return Regimen.SOLO_ALOJAMIENTO;
            case 2: return Regimen.ALOJAMIENTO_DESAYUNO;
            case 3: return Regimen.MEDIA_PENSION;
            case 4: return Regimen.PENSION_COMPLETA;
            default: return Regimen.SOLO_ALOJAMIENTO;
        }
    }

    public static Reserva leerReserva(){

        int numPersonas;


        Huesped huesped = getClientePorDni();

        Habitacion habitacion = leerHabitacionPorIdentificador();

        System.out.println("Seleccione un tipo de régimen: ");
        Regimen regimen = leerRegimen();

        /*do {
            System.out.println("Inserte la fecha de inicio de reserva: ");
            fechaInicio = Entrada.cadena();
            if (!fechaInicio.matches("\\d{2}/\\d{2}/\\d{4}")) System.out.println("Por favor, inserte la fecha en formato correcto (dd/MM/yyy)");
        }while(!fechaInicio.matches("\\d{2}/\\d{2}/\\d{4}"));
        do {
            System.out.println("Inserte la fecha de fin de reserva: ");
            fechaFin = Entrada.cadena();
            if (!fechaFin.matches("\\d{2}/\\d{2}/\\d{4}")) System.out.println("Por favor, inserte la fecha en formato correcto (dd/MM/yyy)");
        }while(!fechaFin.matches("\\d{2}/\\d{2}/\\d{4}"));
        */
        // Esto no hace falta porque ya insertamos la fecha cuando hacemos leerFecha en el constructor.


        System.out.println("Indique el número de personas en la reserva: ");
        numPersonas = Entrada.entero();


        try {
            return new Reserva(huesped, habitacion, regimen, leerFecha("Inserte la fecha de inicio de reserva: "), leerFecha("Inserte la fecha de fin de reserva: "), numPersonas);
        }catch(NullPointerException | IllegalArgumentException e){
            System.out.println(e.getMessage());
            return null;
        }


    }

}
