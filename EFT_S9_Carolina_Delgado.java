package EFT_S9_Carolina_Delgado;

// Importar clase Scanner para leer entrada de datos y clases asociadas a Listas para almacenar dato de venta.
import java.util.Scanner;
import java.util.ArrayList;


// Clase que representa una entrada comprada por un cliente.
class Entrada {
    int asiento;            // Número del asiento comprado (1 a 50).
    double precioBase;      // Precio original del asiento antes de aplicar descuentos.
    double precioFinal;     // Precio que el cliente pagará después de aplicar su descuento.
    double descuento;       // Porcentaje de descuento aplicado (ej: 0.25 para 25%).
    String nombreCliente;   // Nombre de la persona que compró la entrada.

    // Constructor que inicializa todos los atributos y calcula el precio final
    // Punto de depuración: Para verificar si el constructor contiene todos los datos necesarios para poder almacenar los datos.
    public Entrada(int asiento, double precioBase, double descuento, String nombreCliente) {
        this.asiento = asiento;
        this.precioBase = precioBase;
        this.descuento = descuento;
        this.nombreCliente = nombreCliente;
        this.precioFinal = precioBase * (1 - descuento); 
    }
}

public class EFT_S9_Carolina_Delgado {

    // Definir constantes de descuentos según tipo de cliente.
    public static final double DESCUENTO_NINOS = 0.1;         // 10% para menores de 12 años
    public static final double DESCUENTO_MUJERES = 0.2;       // 20% para mujeres
    public static final double DESCUENTO_ESTUDIANTES = 0.15;  // 15% para estudiantes
    public static final double DESCUENTO_TERCERA_EDAD = 0.25; // 25% para mayores de 60 años

    // Definir constantes de precios por ubicación. Los asientos están divididos en bloques de 10.
    private static final int CANTIDAD_ASIENTOS = 50;
    private static final double PRECIO_GALERIA = 20000;
    private static final double PRECIO_PLATEA_ALTA = 50000;
    private static final double PRECIO_PLATEA_BAJA = 30000;
    private static final double PRECIO_PALCO = 60000;
    private static final double PRECIO_VIP = 70000;

    // Arreglo para registrar si un asiento está ocupado (1) o disponible (0)
    private static int[] asientos = new int[CANTIDAD_ASIENTOS];

    // Arreglo que almacena objetos Entrada vendidas
    private static ArrayList<Entrada> entradasVendidas = new ArrayList();

    // Contador que indica cuántas entradas se han vendido
    private static int contadorVendidas = 0;

    // Scanner para entrada de datos del usuario por consola
    private static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        boolean comprando = true; // Controla el ciclo principal del menú
        int opcion;

        // Bucle principal que muestra el menú hasta que el usuario decida salir
        while (comprando) {
            mostrarMenu(); // Imprime las opciones del menú principal

            System.out.print("Elija una opcion (1-6): ");
            opcion = scn.nextInt();
            scn.nextLine(); // Limpia el buffer

            // Punto de depuración: Para corroborar el comportamiento de los case y verificar si están bien definidos. 
            switch (opcion) {
                case 1:
                    // Compra de entrada
                    if (contadorVendidas >= CANTIDAD_ASIENTOS) {
                        System.out.println("No quedan asientos disponibles.");
                    } else {
                        comprarEntrada(); // Lógica para procesar una nueva venta
                    }
                    break;
                case 2:
                    // Mostrar promociones disponibles.
                    imprimirPromociones();
                    break;
                case 3:
                    // Mostrar estado actual de los asientos.
                    mostrarAsientos();
                    break;
                case 4:
                    // Cancelar una compra de entrada y liberar asiento.
                    EliminarEntrada();
                    break;
                case 5:
                    // Imprimir los detalles de la última entrada vendida.
                    imprimirBoleta();
                    break;
                case 6:
                    // Salir del sistema de gestión de ventas.
                    comprando = false;
                    scn.close();
                    System.out.println("Gracias por comprar en Teatro Moro.");
                    break;
                default:
                    // Entrada no válida
                    System.out.println("Opcion no válida.");
                    break;
            }
        }
    }

    // Función que imprime el menú principal con las opciones disponibles
    private static void mostrarMenu() {
        System.out.println("==============================================");
        System.out.println("----------Bienvenido al Teatro Moro----------");
        System.out.println("        Disponibilidad de asientos: 50       ");
        System.out.println("        Tipos de asientos y precio: ");
        System.out.println("          * VIP = $70000                     ");
        System.out.println("          * Palco = $60000                   ");
        System.out.println("          * Platea alta = $50000             ");
        System.out.println("          * Platea baja = $30000             ");
        System.out.println("          * Galeria = $20000                 ");
        System.out.println();
        System.out.println("-----------Menu del sistema de ventas---------");
        System.out.println("1) Comprar entrada");
        System.out.println("2) Mostrar promociones");
        System.out.println("3) Imprimir mapa de asientos");
        System.out.println("4) Eliminar entrada");
        System.out.println("5) Impresion de boleta");
        System.out.println("6) Salir del menu. Vuelva pronto.");
        System.out.println("==============================================");
    }

    // Muestra el estado de todos los asientos del teatro
    private static void mostrarAsientos() {
        System.out.println("\nMapa de ubicaciones del Teatro Moro:");
        char estadoAsiento;

        for (int i = 0; i < CANTIDAD_ASIENTOS; i++) {
            // Determina si el asiento está disponible (D) o vendido (V)
            if(asientos[i] == 0) {
                estadoAsiento = 'D';// Si asiento[i] == 0, estadoAsiento es igual 'D'. Si no, es igual 'V'
            } else {
                estadoAsiento = 'V';
            }
            // Agrupación visual de los bloques de asientos por tipo
              if (i == 0) {
                System.out.print("Galeria:     ");
            } else if (i == 10) {
                System.out.print("Platea Alta: ");
            } else if (i == 20) {
                System.out.print("Platea Baja: ");
            } else if (i == 30) {
                System.out.print("Palco:       ");
            } else if (i == 40) {
                System.out.print("VIP:         ");
            }
            // Imprime el número de asiento y su estado
            System.out.printf("[%d: %s] ", i + 1, estadoAsiento);

            if (i % 10 == 9) {
                System.out.println();
            } // Salto de línea cada 10 asientos
        }
        System.out.println("\nD: Disponible, V: Vendido\n");
    }

    // Crear método que imprime las promociones disponibles según el tipo de cliente
    private static void imprimirPromociones() {
        System.out.println("El Teatro Moro cuenta con las siguientes promociones:");
        System.out.println("Tercera edad: 25%");
        System.out.println("Mujer: 20%");
        System.out.println("Estudiante: 15%");
        System.out.println("Niño: 10%");
        System.out.println("* Las promociones no son acumulativas.");
    }

    // Crear método para la compra de entrada.
    private static void comprarEntrada() {
        String nombre;
        boolean esMujer = false;
        boolean esEstudiante = false;
        int edad = 0;
        double descuento = 0.0;
        double precioEntrada = 0.0;
        int asiento;

        // Captura datos del usuario para poder aplicar promociones pertinentes a cada tipo de cliente.
        //Punto de depuracion: Para verificar la validación no presenta inconsistencias y utiliza las funciones adecuadas.
        while (true) {
            System.out.print("Ingrese su nombre: ");
            nombre = scn.nextLine();

            System.out.print("Ingrese su sexo (Hombre/Mujer): ");
            String sexo = scn.nextLine().trim(); // Se utiliza trim(); para eliminar los posibles espacios en blanco que el usuario ingrese en consola.
            if (sexo.equalsIgnoreCase("Mujer")) { // Usar el equalsIgnoreCase para ignorar mayúsculas y minísculas de la entrada del usuario
                esMujer = true;
            } else if (!sexo.equalsIgnoreCase("Hombre")) {
                System.out.println("Sexo invalido. Intente de nuevo.");
                continue;
            }
                
        //Validar edad
            System.out.print("Ingrese su edad: ");
            if (!scn.hasNextInt()) { // Utilizar scn.hasNextInt() para validar que la entrada efectivamente se trate de un número entero y no de otro caracter o tipo de número.
                System.out.println("Edad invalida.");
                scn.nextLine(); // Limpia buffer
                continue;
            }
            edad = scn.nextInt();
            scn.nextLine();
            
        //Validar rango de edad para comprar entrada
            if (edad <= 0) {
                System.out.println("Edad invalida.");
                continue;
            }

            System.out.print("Es estudiante (Si/No):  ");
            String estudiante = scn.nextLine().trim();
            if (estudiante.equalsIgnoreCase("Si")) {
                esEstudiante = true;
            } else if (!estudiante.equalsIgnoreCase("No")) {
                System.out.println("Respuesta invalida.");
                continue;
            }

            break; 
        }

         
        mostrarAsientos(); // Muestra los asientos para que elija uno disponible
        System.out.print("Seleccione un asiento (1-50): ");
        asiento = scn.nextInt();
        scn.nextLine();
        
        if (asiento < 1 || asiento > CANTIDAD_ASIENTOS) {
                System.out.println("Asiento no valido.");
                return;
        }
          
        
        
        // Punto de depuración: Para verificar si la estructura condicional está bien definida y dentro del rango según la lógica de la cantidad de asientos y asientos disponibles.
        if (asiento < 1 || asiento > 50 || asientos[asiento - 1] == 1) {
            System.out.println("Asiento no disponible. Ya ha sido reservado");
            return;
        }

        // Determina el precio base del asiento según su ubicación en el mapa de asientos.
           if (asiento >= 1 && asiento <= 10) {
                precioEntrada = PRECIO_GALERIA;
            } else if (asiento <= 20) {
                precioEntrada = PRECIO_PLATEA_ALTA;
            } else if (asiento <= 30) {
                precioEntrada = PRECIO_PLATEA_BAJA;
            } else if (asiento <= 40) {
                precioEntrada = PRECIO_PALCO;
            } else {
                precioEntrada = PRECIO_VIP;
            }
        // Determinar descuento mayor aplicable según las promociones definidas anteriormente.
             if (edad > 60) {
                descuento = DESCUENTO_TERCERA_EDAD;
            } else if (esMujer) {
                descuento = DESCUENTO_MUJERES;
            } else if (esEstudiante) {
                descuento = DESCUENTO_ESTUDIANTES;
            } else if (edad < 12) {
                descuento = DESCUENTO_NINOS;
            } else {
                descuento = 0;
            }


        // Crear una nueva entrada con los datos recopilados
        Entrada nuevaEntrada = new Entrada(asiento, precioEntrada, descuento, nombre);
        entradasVendidas.add(nuevaEntrada);// Guarda la entrada mediante la función add de listas.
        asientos[asiento - 1] = 1; // Registra el asiento como ocupado
        contadorVendidas++;

        System.out.println("Entrada comprada exitosamente.");
    }

    // Método para cancelar una compra de entrada y liberar un asiento ocupado
    private static void EliminarEntrada() {
        // Método para cancelar una venta, liberando un asiento
        if (entradasVendidas.isEmpty()) { // Si en la lista de entradas vendidas no hay nada.
            System.out.println("No existen entradas disponibles para cancelar en la base de datos.");
            return;
        }

        System.out.println("Ingrese el numero de asiento asociado a la entrada a eliminar (1-50): ");
        int asientoEliminar = scn.nextInt();
        scn.nextLine();

        Entrada entradaAEliminar = null;

        // Buscar la entrada en la lista entradasVendidas
        for (Entrada entrada : entradasVendidas) {
            if (entrada.asiento == asientoEliminar) {
                entradaAEliminar = entrada;
                break;
            }
        }

        // Si se encuentra la entrada, la elimina de la lista
        if (entradaAEliminar != null) {
            entradasVendidas.remove(entradaAEliminar);  // Elimina la entrada de la lista
            asientos[asientoEliminar - 1] = 0;           // Marca el asiento como disponible

            System.out.println("Entrada eliminada correctamente.");
        } else {
            System.out.println("No se ha encontrado entrada para eliminar.");
        }
    }

    // Imprime los detalles de la última entrada vendida
    private static void imprimirBoleta() {
        
        if (entradasVendidas.isEmpty()) {
            System.out.println("No se ha vendido ninguna entrada aún.");
            return;
        }
        
        for (Entrada entrada : entradasVendidas) {
            // Punto de depuración: Para poder verificar si el for utilizado realmente recorre todas las entradas vendidas para poder imprimirlas cada una en consola.
            System.out.println("|===========================================|");
            System.out.println("|                TEATRO MORO                |");
            System.out.println("|===========================================|");
            System.out.println(" Nombre Cliente: " + entrada.nombreCliente);
            System.out.println(" Asiento: " + entrada.asiento);            
            System.out.println(String.format(" Descuento:%d%%", (int)(entrada.descuento * 100)));
            System.out.println(" Precio Base: $" + String.format("%,.0f", entrada.precioBase));
            System.out.println(" Precio Final: $" + String.format("%,.0f", entrada.precioFinal));
            System.out.println("|------------------------------------------|");
            System.out.println("|   Gracias por su visita al Teatro Moro   |");
            System.out.println("|          Disfrute la funcion             |");
            System.out.println("|==========================================|");
            System.out.println();
        }
    }
}

  
        
