package com.alura.desafio.principal;

import com.alura.desafio.service.*;
;

import java.util.InputMismatchException;
import java.util.Scanner;

public class principal {


        int menu=0, opciones;

        Scanner lectura = new Scanner(System.in);

        convierteDatos coversorDatos = new convierteDatos();
        consumoAPI API = new consumoAPI();
        private final String API_URL = "gutendex.com/books";


        public void menu(){

            while(menu == 0){
                System.out.println("----SCREENMATCH LIBROS MENU----\n");
                System.out.println("1 - Buscar libro por nombre");
                System.out.println("2 - Ver libros de un autor");
                System.out.println("3 - Ver lista de libros de un género");
                System.out.println("2 - Ver libros de un autor");
                System.out.println("5 - CERRAR DE SCREENMATCH LIBROS");

                try {
                    opciones = lectura.nextInt();
                    while (opciones < 1 || opciones > 5) {
                        System.out.println("ERROR, ingresar opciones validas (1-5) ");
                        opciones = lectura.nextInt();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("ERROR, ingresar opciones validas (1-5)");
                    lectura.nextLine();
                }


                switch (opciones){

                    case 1:

                        System.out.println("BUSQUEDA DE LIBRO POR NOMBRE");
                        System.out.println("Ingresar nombre del libro a buscar: ");
                        String nombre = lectura.nextLine();

                }





            }

        }
}
