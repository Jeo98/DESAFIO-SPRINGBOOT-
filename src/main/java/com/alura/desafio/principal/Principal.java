package com.alura.desafio.principal;
import com.alura.desafio.modelos.*;
import com.alura.desafio.service.consumoAPI;
import com.alura.desafio.service.convierteDatos;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.exit;

public class Principal {


    int menu=0, opciones;

    Scanner lectura = new Scanner(System.in);

    convierteDatos conversor = new convierteDatos();
    consumoAPI API = new consumoAPI();
    private final String API_URL = "https://gutendex.com/books/";

    public void menu(){

        String JsonAPI =API.obtenerDatosAPI(API_URL);
        //System.out.println(JsonAPI);
        var datos= conversor.obtenerDatos(JsonAPI, Datos.class);
        //System.out.println(datos);


        while(menu == 0){
            System.out.println("----SCREENMATCH LIBROS MENU----\n");
            System.out.println("1 - Buscar libro por nombre");
            System.out.println("2 - Ver libros de un autor");
            System.out.println("3 - TOP 10 mejores");
            System.out.println("4 - Ver estadisticas de Biblioteca");

            //-------------------------------------------------------
            System.out.println("0 - CERRAR DE SCREENMATCH LIBROS");
            //-------------------------------------------------------
            System.out.println("-> ");
            try {
                opciones = lectura.nextInt();
                while (opciones < 0 || opciones > 5) {
                    System.out.println("ERROR, ingresar opciones validas (0-5) ");
                    opciones = lectura.nextInt();
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR, ingresar opciones validas (0-5)");
                lectura.nextLine();
            }


            switch (opciones) {

                case 1:
                    lectura.nextLine();
                    System.out.println("\n-> BUSQUEDA DE LIBRO POR NOMBRE < -\n");
                    System.out.println("Ingresar nombre del libro a buscar: ");
                    String nombre = lectura.nextLine();

                    Optional<DatosLibro> nombreLibroBuscado = datos.resultadoslibros().stream()
                            .filter(e -> e.titulo().toUpperCase().contains(nombre.toUpperCase()))
                            .findFirst();

                    if (nombreLibroBuscado.isPresent()) {
                        System.out.println("Libro encontrado: ");
                        System.out.println(nombreLibroBuscado.get() + "\n");
                    } else
                        System.out.println("libro no encontrado...\n");


                    break;
                case 2:
                    lectura.nextLine();
                    System.out.println("\n-> LIBROS DE AUTOR < -\n");
                    System.out.println("Ingrese el nombre del autor: ");
                    nombre = lectura.nextLine();

                    List<DatosLibro> librosAutorBuscado = datos.resultadoslibros().stream()
                            .filter(a -> a.autor().stream()
                            .anyMatch(b -> b.nombreAutor().replace(",","").equalsIgnoreCase(nombre)))
                            .toList();

                    if (!librosAutorBuscado.isEmpty()){
                        System.out.println("Libros del autor " + nombre + " encontrados: ");
                        librosAutorBuscado.forEach(l -> System.out.println(" - "+ l.titulo()));
                        System.out.println("\n");
                    }
                    else
                        System.out.println("Autor no encontrado...\n");
                break;


                case 3:
                    System.out.println("\n-> TOP 10 LIBROS < -\n");
                    datos.resultadoslibros().stream()
                            .sorted(Comparator.comparing(DatosLibro::cantidadDescargas).reversed())
                            .limit(10).forEach(e -> System.out.println("Titulo: "+e.titulo() + "| cantidad de descargas: "+e.cantidadDescargas()));
                break;

                case 4:
                    lectura.nextLine();
                    System.out.println("\n-> ESTADISTICAS DE BIBLIOTECA < -\n");


                    IntSummaryStatistics estadisticas = datos.resultadoslibros().stream()
                                .filter(d -> d.cantidadDescargas()>0)
                                .collect(Collectors.summarizingInt(DatosLibro::cantidadDescargas));
                        System.out.println();
                        System.out.println("Cantidad media de  en biblioteca: " + estadisticas.getAverage());
                        System.out.println("Cantidad maxima de descargas en biblioteca: " + estadisticas.getMax());
                        System.out.println("Cantidad minima de descargas en biblioteca: "+ estadisticas.getMin());
                        System.out.println("cantidad de datos consultados en biblioteca para realizar estadisticas: " + estadisticas.getCount());


                break;

                case 0:
                    System.out.println("\n-> CIERRE | GRACIAS POR UTILIZAR LA BIBLIOTECA < -\n");
                    menu=1; //cierre de sistema

                break;
             }
        }
    }
}
