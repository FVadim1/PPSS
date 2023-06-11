package ppss.ejercicio1;

import java.util.Calendar;

public class GestorLlamadasTestable extends GestorLlamadas {

    private int h=0;

    @Override
    public int getHoraActual() { return h; }

    public void setHoraActual(int hora) { h = hora;}
}
