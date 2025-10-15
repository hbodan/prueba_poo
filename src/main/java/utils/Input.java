package utils;

import java.util.Scanner;

public class Input {
    public static int getInt(Scanner sc, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Por favor ingrese un número entero.");
            }
        }
    }

    public static long  getLong(Scanner sc, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Long.parseLong(sc.nextLine());
            }  catch (Exception e) {
                System.out.println("Por favor ingrese un numero entero.");
            }
        }
    }

    public static double  getDouble(Scanner sc, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(sc.nextLine());
            }  catch (Exception e) {
                System.out.println("Por favor ingrese un número decimal.");
            }
        }
    }

    public static String getString(Scanner sc, String prompt, int limit) {
        while(true) {
            System.out.print(prompt);
            String input = sc.nextLine();

            if(input.length() > limit) {
                System.out.println("La longitud debe ser menor o igual a " + limit);
            } else if(input.isEmpty()) {
                System.out.println("El input no puede estar vacío.");
            } else
                return input;
        }
    }

    public static String getFixedString(Scanner sc, String prompt, int size) {
        while(true) {
            System.out.print(prompt);
            String input = sc.nextLine();

            if(input.length() != size) {
                System.out.println("La longitud debe ser igual a " + size);
            }  else if(input.isEmpty()) {
                System.out.println("El input no puede estar vacío.");
            }else return input;
        }
    }
}
