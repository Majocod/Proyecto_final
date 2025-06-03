import java.util.Scanner;

public class RegistroTemperaturas {
    private EstacionMeteorologica[] estaciones;
    private Scanner scanner = new Scanner(System.in);
    private final String[] MESES = {
        "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
        "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
    };

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            int opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1 -> registrarEstaciones();
                case 2 -> cargarTemperaturas();
                case 3 -> verAnalisisPorEstacion();
                case 4 -> verAnalisisMensual();
                case 5 -> generarReporte();
                case 6 -> verHistorial();
                case 7 -> {
                    salir = true;
                    System.out.println("Saliendo...");
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\n--- Menú ---");
        System.out.println("1. Registrar estaciones");
        System.out.println("2. Cargar temperaturas");
        System.out.println("3. Ver análisis por estación");
        System.out.println("4. Ver análisis mensual");
        System.out.println("5. Generar reporte");
        System.out.println("6. Ver historial de una estación");
        System.out.println("7. Salir");
    }

    private void registrarEstaciones() {
        int cantidad = leerEntero("¿Cuántas estaciones desea registrar? ");
        estaciones = new EstacionMeteorologica[cantidad];
        for (int i = 0; i < cantidad; i++) {
            String nombre;
            do {
                System.out.print("Nombre de la estación " + (i + 1) + ": ");
                nombre = scanner.nextLine().trim();
                if (nombre.isEmpty()) {
                    System.out.println("El nombre no puede estar vacío.");
                }
            } while (nombre.isEmpty());
            estaciones[i] = new EstacionMeteorologica(nombre);
        }
    }

    private void cargarTemperaturas() {
        if (estaciones == null) {
            System.out.println("Primero registre las estaciones.");
            return;
        }
        for (EstacionMeteorologica est : estaciones) {
            System.out.println("Temperaturas para la estación: " + est.getNombre());
            for (int mes = 0; mes < 12; mes++) {
                double temp = leerDouble("Ingrese temperatura para " + est.getNombre() + " - " + MESES[mes] + ": ");
                est.setTemperaturaMes(mes, temp);
            }
        }
    }

    private void verAnalisisPorEstacion() {
        if (estaciones == null) return;
        for (EstacionMeteorologica est : estaciones) {
            System.out.println("\nEstación: " + est.getNombre());
            System.out.printf("Promedio anual: %.2f°C\n", est.calcularPromedioAnual());
            System.out.printf("Máxima: %.2f°C\n", est.getTemperaturaMaxima());
            System.out.printf("Mínima: %.2f°C\n", est.getTemperaturaMinima());
        }
    }

    private void verAnalisisMensual() {
        if (estaciones == null) return;
        for (int mes = 0; mes < 12; mes++) {
            double suma = 0, max = -Double.MAX_VALUE, min = Double.MAX_VALUE;
            for (EstacionMeteorologica est : estaciones) {
                double t = est.getTemperaturaMes(mes);
                suma += t;
                if (t > max) max = t;
                if (t < min) min = t;
            }
            double promedio = suma / estaciones.length;
            System.out.printf("%s - Promedio: %.2f°C, Máx: %.2f°C, Mín: %.2f°C\n",
                    MESES[mes], promedio, max, min);
        }
    }

    private void generarReporte() {
        if (estaciones == null) return;
        EstacionMeteorologica mayor = estaciones[0];
        EstacionMeteorologica menor = estaciones[0];
        for (EstacionMeteorologica est : estaciones) {
            if (est.calcularPromedioAnual() > mayor.calcularPromedioAnual()) mayor = est;
            if (est.calcularPromedioAnual() < menor.calcularPromedioAnual()) menor = est;
        }
        for (EstacionMeteorologica est : estaciones) {
            int mesMin = est.getMesMinimo();
            int mesMax = est.getMesMaximo();
            System.out.println("\nEstación: " + est.getNombre());
            System.out.printf("Temperatura media anual: %.1f°C\n", est.calcularPromedioAnual());
            System.out.printf("Temperatura mínima: %.1f°C en %s\n", est.getTemperaturaMinima(), MESES[mesMin]);
            System.out.printf("Temperatura máxima: %.1f°C en %s\n", est.getTemperaturaMaxima(), MESES[mesMax]);
        }
        System.out.println("\nEstación con mayor temperatura anual: " + mayor.getNombre());
        System.out.printf("Promedio: %.1f°C\n", mayor.calcularPromedioAnual());
        System.out.println("Estación con menor temperatura anual: " + menor.getNombre());
        System.out.printf("Promedio: %.1f°C\n", menor.calcularPromedioAnual());
    }

    private void verHistorial() {
        if (estaciones == null) return;
        System.out.print("Ingrese el nombre de la estación: ");
        String nombre = scanner.nextLine().trim();
        boolean encontrada = false;
        for (EstacionMeteorologica est : estaciones) {
            if (est.getNombre().equalsIgnoreCase(nombre)) {
                est.mostrarHistorial(MESES);
                encontrada = true;
                break;
            }
        }
        if (!encontrada) {
            System.out.println("Estación no encontrada.");
        }
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: ingrese un número entero válido.");
            }
        }
    }

    private double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: ingrese un número decimal válido.");
            }
        }
    }
}
