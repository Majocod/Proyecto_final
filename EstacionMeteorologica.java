public class EstacionMeteorologica {

    private String nombre;
    private double[] temperaturas;

     // Recibe el nombre de la estación y crea el arreglo 'temperaturas'
    // con 12 posiciones

    public EstacionMeteorologica(String nombre) {
        this.nombre = nombre;
        this.temperaturas = new double[12];
    }

    // Devuelve el nombre de la estación
    public String getNombre() {
        return nombre;
    }

    // Asigna una temperatura a un mes específico.
    public void setTemperaturaMes(int mes, double temperatura) {
        if (mes >= 0 && mes < 12) {
            temperaturas[mes] = temperatura;
        }
    }

    // Devuelve la temperatura registrada para un mes
    public double getTemperaturaMes(int mes) {
        if (mes >= 0 && mes < 12) {
            return temperaturas[mes];
        }
        return -1; // Valor inválido si el mes está fuera de rango
    }

    // Calcula el promedio anual de temperaturas
    public double calcularPromedioAnual() {
        double suma = 0;
        for (double t : temperaturas) {
            suma += t;
        }
        return suma / 12;
    }

    // Devuelve la temperatura más alta registrada
    public double getTemperaturaMaxima() {
        double max = temperaturas[0];
        for (double t : temperaturas) {
            if (t > max) max = t;
        }
        return max;
    }

    // Devuelve la temperatura más baja registrada
    public double getTemperaturaMinima() {
        double min = temperaturas[0];
        for (double t : temperaturas) {
            if (t < min) min = t;
        }
        return min;
    }

    // Devuelve el índice del mes con la temperatura más alta
    public int getMesMaximo() {
        double max = temperaturas[0];
        int indice = 0;
        for (int i = 1; i < temperaturas.length; i++) {
            if (temperaturas[i] > max) {
                max = temperaturas[i];
                indice = i;
            }
        }
        return indice;
    }

    // Devuelve el índice del mes con la temperatura más baja
    public int getMesMinimo() {
        double min = temperaturas[0];
        int indice = 0;
        for (int i = 1; i < temperaturas.length; i++) {
            if (temperaturas[i] < min) {
                min = temperaturas[i];
                indice = i;
            }
        }
        return indice;
    }

    // Muestra el historial de temperaturas por mes con sus nombres
    public void mostrarHistorial(String[] meses) {
        System.out.println("\nHistorial de temperaturas para " + nombre + ":");
        for (int i = 0; i < 12; i++) {
            System.out.printf("%s: %.1f°C\n", meses[i], temperaturas[i]);
        }
    }
}
