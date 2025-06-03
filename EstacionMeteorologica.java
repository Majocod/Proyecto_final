public class EstacionMeteorologica {
    private String nombre;
    private double[] temperaturas;

    public EstacionMeteorologica(String nombre) {
        this.nombre = nombre;
        this.temperaturas = new double[12];
    }

    public String getNombre() {
        return nombre;
    }

    public void setTemperaturaMes(int mes, double temperatura) {
        if (mes >= 0 && mes < 12) {
            temperaturas[mes] = temperatura;
        }
    }

    public double getTemperaturaMes(int mes) {
        if (mes >= 0 && mes < 12) {
            return temperaturas[mes];
        }
        return -1;
    }

    public double calcularPromedioAnual() {
        double suma = 0;
        for (double t : temperaturas) {
            suma += t;
        }
        return suma / 12;
    }

    public double getTemperaturaMaxima() {
        double max = temperaturas[0];
        for (double t : temperaturas) {
            if (t > max) max = t;
        }
        return max;
    }

    public double getTemperaturaMinima() {
        double min = temperaturas[0];
        for (double t : temperaturas) {
            if (t < min) min = t;
        }
        return min;
    }

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

    public void mostrarHistorial(String[] meses) {
        System.out.println("\nHistorial de temperaturas para " + nombre + ":");
        for (int i = 0; i < 12; i++) {
            System.out.printf("%s: %.1f°C\n", meses[i], temperaturas[i]);
        }
    }
}