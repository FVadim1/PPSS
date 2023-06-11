package ppss;

public class Cine {

    public boolean[] asientos;

    public boolean reservaButacasV1(boolean[] asientos, int solicitados) {

        boolean reserva = false;

        if(solicitados > 0) {
            int j = 0;
            int sitiosLibres = 0;
            int primerLibre;

            while ((j < asientos.length) && (sitiosLibres < solicitados)) {
                if (!asientos[j]) {
                    sitiosLibres++;
                } else {
                    sitiosLibres = 0;
                }
                j++;
            }
            if (sitiosLibres == solicitados) {
                primerLibre = (j - solicitados);
                reserva = true;
                for (int k = primerLibre; k < (primerLibre + solicitados); k++) {
                    asientos[k] = true;
                }
            }
        }

        this.asientos = asientos;

        return reserva;
    }
}
