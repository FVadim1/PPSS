package ppss.ejercicio2;

public class GestorLlamadasTestable extends GestorLlamadas {

    Calendario c;

    @Override
    public Calendario getCalendario() {
        return c;
    }

    public void setCalendario(Calendario ca) {
        c = ca;
    }
}