package ppss.ejercicio2;

public class CalendarioStub extends Calendario {
    private int HoraActual = 0;

    @Override
    public int getHoraActual() {
        return HoraActual;
    }

    public void setHoraActual(int hora) {
        this.HoraActual = hora;
    }
}
