import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import javax.swing.JPanel;

public class Jugador {

    private final int TOTAL_CARTAS = 10;
    private final int MARGEN = 10;
    private final int DISTANCIA = 50;

    private Carta[] cartas = new Carta[TOTAL_CARTAS];
    private Random r = new Random();

    public void repartir() {
        int i = 0;
        for (Carta c : cartas) {
            cartas[i++] = new Carta(r);  // Simula la creación de cartas aleatorias
        }
    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        int i = 0;
        for (Carta c : cartas) {
            c.mostrar(pnl, MARGEN + i++ * DISTANCIA, MARGEN); // Muestra las cartas gráficamente
        }
        pnl.repaint();
    }

    public String getGrupos() {
        String mensaje = "No se encontraron grupos";
        int[] contadores = new int[NombreCarta.values().length];

        for (Carta c : cartas) {
            contadores[c.getNombre().ordinal()]++;
        }

        boolean hayGrupos = false;
        for (int i = 0; i < contadores.length; i++) {
            if (contadores[i] >= 2) {
                if (!hayGrupos) {
                    hayGrupos = true;
                    mensaje = "Los grupos que se encontraron fueron:\n";
                }
                mensaje += Grupo.values()[contadores[i]] + " de " + NombreCarta.values()[i] + "\n";
            }
        }

        return mensaje;
    }

    // Método para ordenar las cartas por pinta y valor
    public void ordenarCartasPorPintaYValor() {
        Arrays.sort(cartas, new Comparator<Carta>() {
            @Override
            public int compare(Carta c1, Carta c2) {
                int valor1 = obtenerValorGlobal(c1);
                int valor2 = obtenerValorGlobal(c2);
                return Integer.compare(valor1, valor2);
            }
        });
    }

    public void remover(JPanel pnl) {
        pnl.removeAll();

    }

    // Utilizamos pintas para identificar las cartas
    private int obtenerValorGlobal(Carta c) {
        int valor = c.getNombre().ordinal() + 1; 
        Pinta pinta = c.getPinta();
        switch (pinta) {
            case TREBOL: return valor; 
            case PICA: return valor + 13;
            case CORAZON: return valor + 26;
            case DIAMANTE: return valor + 39;
            default: return valor;
        }
    }

    // Método para detectar escaleras de 3 o más cartas
    public String detectarEscaleras() {
        ordenarCartasPorPintaYValor(); // Primero, ordenamos las cartas

        String mensaje = "No se encontraron escaleras.";
        int consecutivos = 1; // Contador de cartas consecutivas
        Pinta pintaActual = cartas[0].getPinta(); // Iniciamos con la pinta de la primera carta
        StringBuilder escaleras = new StringBuilder(); // Para almacenar las escaleras

        for (int i = 1; i < cartas.length; i++) {
            NombreCarta actual = cartas[i].getNombre();
            NombreCarta anterior = cartas[i - 1].getNombre();

            // Verificamos si las cartas son consecutivas en valor y tienen la misma pinta
            if (actual.ordinal() == anterior.ordinal() + 1 && cartas[i].getPinta() == pintaActual) {
                consecutivos++;
            } else {
                if (consecutivos >= 3) {
                    escaleras.append("Escalera de ").append(consecutivos).append(" cartas: ");
                    for (int j = i - consecutivos; j < i; j++) {
                        escaleras.append(cartas[j].getNombre()).append(" ");
                    }
                    escaleras.append("\n");
                }
                consecutivos = 1; // Reiniciamos el contador
                pintaActual = cartas[i].getPinta(); // Cambiamos la pinta actual
            }
        }

        // Verificar la última secuencia
        if (consecutivos >= 3) {
            escaleras.append("Escalera de ").append(consecutivos).append(" cartas: ");
            for (int j = cartas.length - consecutivos; j < cartas.length; j++) {
                escaleras.append(cartas[j].getNombre()).append(" ");
            }
            escaleras.append("\n");
        }

        if (escaleras.length() > 0) {
            mensaje = "Se encontraron las siguientes escaleras:\n" + escaleras.toString();
        }

        return mensaje;
    }
}
