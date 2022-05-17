package wald;

public class Feld {
    private Feldtyp typ;

    public Feld(Feldtyp typ) {
        this.typ = typ;
    }

    public Feldtyp getTyp() {
        return typ;
    }

    public void setTyp(Feldtyp typ) {
        this.typ = typ;
    }
}
