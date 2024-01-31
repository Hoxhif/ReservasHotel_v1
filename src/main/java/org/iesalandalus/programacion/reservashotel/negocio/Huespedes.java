package org.iesalandalus.programacion.reservashotel.negocio;

import org.iesalandalus.programacion.reservashotel.dominio.Huesped;

import javax.naming.OperationNotSupportedException;


import java.time.LocalDate;
import java.util.Arrays;

public class Huespedes {
    private Huesped coleccionHuespedes[];
    private int capacidad;
    private int tamano;

    public Huespedes(int capacidad){
        if (capacidad<=0)
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");

        this.capacidad=capacidad;
        this.coleccionHuespedes = new Huesped[getCapacidad()];
        this.tamano=0;
    }


    public Huesped[] get() {
        return copiaProfundaHuespedes();
    }

    private Huesped[] copiaProfundaHuespedes() {
        // No he llegado a entender muy bien esta parte despues de mucho analizar... Me estaba dando un error todo el rato en esta parte sobre el index en el test de insertar huesped validos si estan llenos, y he tenido que hacer ese condicional y me ha pasado los test de esa manera.. Entiendo que habría que hacer condicional de otra manera?... (El error era Index 2 out of bounds for length 2 para pasar ese test.)
           /* coleccionHuespedes[0] = new Huesped("José Ramón Jiménez Reyes", "11223344B", "joseramon.jimenez@iesalandalus.org", "950112233", LocalDate.of(2002, 9, 15));
            coleccionHuespedes[1] = new Huesped("Andrés Rubio Del Río", "22334455Y", "andres.rubio@iesalandalus.org", "666223344", LocalDate.of(2002, 8, 15));
            if (getCapacidad()>=3)
                coleccionHuespedes[2] = new Huesped("Bob Esponja", "33445566R", "bog.esponja@iesalandalus.org", "600334455", LocalDate.of(1996, 10, 30));
            */
        /*coleccionHuespedes[3] = new Huesped(this.coleccionHuespedes[2].getNombre(),this.coleccionHuespedes[2].getDni(),this.coleccionHuespedes[2].getCorreo(),this.coleccionHuespedes[2].getTelefono(),this.coleccionHuespedes[2].getFechaNacimiento());*/

// Para realizar este método use ChatGPT para ver que interpretaba el que tenía que hacer para este método.
        //return Arrays.copyOf(coleccionHuespedes, tamano);

        int j=0;
        Huesped[] copiaHuespedes = new Huesped[capacidad];
        for (int i = 0; i < capacidad; i++) {
            if (coleccionHuespedes[i] != null) {
                copiaHuespedes[j++] = new Huesped(coleccionHuespedes[i]);
            }

        }return Arrays.copyOf(copiaHuespedes, j);

        // Esto lo obtuve de la clase del jueves 11/01/2024 donde se explico que se tenía que hacer esto, sin embargo cuando lo implemente me da error el test de nulos.

        /*Huesped copiaHuespedes [] = new Huesped[capacidad];
        for (int i = 0; !tamanoSuperado(i); i++) {
            if (coleccionHuespedes[i] != null)
                copiaHuespedes[i] = new Huesped(coleccionHuespedes[i]);
        }

        return copiaHuespedes;*/

    }


    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void insertar(Huesped huesped) throws OperationNotSupportedException{
        if (huesped == null)
            throw new NullPointerException("ERROR: No se puede insertar un huésped nulo.");
        int indice = buscarIndice(huesped);
        if (capacidadSuperada(indice))
            throw new OperationNotSupportedException("ERROR: No se aceptan más huéspedes.");
        if (tamanoSuperado(indice))
            throw new OperationNotSupportedException("ERROR: El huesped ya existe.");
        /*for (int i=0; i< tamano; i++){
        if (huesped.getDni().equals(coleccionHuespedes[i].getDni()))
            throw new OperationNotSupportedException("ERROR: Ya existe un huésped con ese dni.");
        }*/

        coleccionHuespedes[tamano]=huesped;
        tamano++;

    }

    private int buscarIndice(Huesped huesped){
        if (huesped == null)
            throw new NullPointerException("ERROR: El huesped indicado es nulo.");
        for (int i=0; i<tamano; i++){
            if (huesped.equals(coleccionHuespedes[i])){
                return i;
            }
        }
        return -1;
    }

    private boolean tamanoSuperado(int indice){
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

    public Huesped buscar (Huesped huesped){
        if (huesped == null)
            throw new NullPointerException("ERROR: el huesped es nulo.");
        int indice = buscarIndice(huesped);
        if (indice != -1)
            return new Huesped(coleccionHuespedes[indice]);
        else return null;
    }

    public void borrar (Huesped huesped) throws OperationNotSupportedException{
        if (huesped == null)
            throw new NullPointerException("ERROR: No se puede borrar un huésped nulo.");
        if (buscarIndice(huesped)<0)
            throw new OperationNotSupportedException("ERROR: No existe ningún huésped como el indicado.");
        int indice = buscarIndice(huesped);
        desplazarUnaPosicionHaciaIzquierda(indice);
        tamano--;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice){
        for (int i = indice; i<tamano-1;i++){
            coleccionHuespedes[i] = coleccionHuespedes[i+1];
        }
        coleccionHuespedes[tamano-1] = null;

    }

}