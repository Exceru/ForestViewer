package wald;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Wald {
    private final List<List<Feld>> felder;
    private int sizeX;
    private int sizeY;

    public Wald(int sizeX, int sizeY) {
        this.felder = new ArrayList<>();

        this.sizeX = sizeX;
        this.sizeY = sizeY;

        for(int y = 0; y < sizeY; y++){
            ArrayList<Feld> horizontaleListe = new ArrayList<>();
            for (int x = 0; x < sizeX; x++) {
                horizontaleListe.add(new Feld(Feldtyp.BAUM));
            }
            this.felder.add(horizontaleListe);
        }

    }

    public Feld getFeld(int x, int y) {
        return this.felder.get(y).get(x);
    }

    public void setFeld(int x, int y, Feld feld) {
        List<Feld> yteZeile = this.felder.get(y);
        yteZeile.set(x, feld);

        this.felder.set(y, yteZeile);
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }
}
