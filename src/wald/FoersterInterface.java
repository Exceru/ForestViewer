package wald;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class FoersterInterface {
    private Wald wald;
    private WaldDisplay waldDisplay;
    private int sizeX;
    private int sizeY;
    private boolean randomFoerster;

    public FoersterInterface(int x, int y) {
        this.wald = new Wald(x,y);

        waldDisplay = new WaldDisplay();

        this.wald = getVerdeckteBaeume(2,2, this.wald);

        waldDisplay.setWald(this.wald);
        waldDisplay.run();


    }

    private Wald getVerdeckteBaeume(int x, int y, Wald grid) {
        //Wald grid = new Wald(this.sizeX, this.sizeY);

        for (int row = 0; row < grid.getSizeY(); row++) {
            for (int column = 0; column < grid.getSizeX(); column++) {
                 lineareInterpolation(grid, y, x, row, column);
            }
        }

        return grid;
    }

    private void showMaximaleSichtbareBaeume() {

    }

    private void showBesteStandorte() {

    }

    public static boolean nearlyEqual(double a, double b, double delta){
        return Math.abs(a-b) < delta;
    }

    private double lerp(double start, double end, double t) {
        return start + t * (end - start);
    }

    private void lineareInterpolation(Wald grid, int startY, int startX, int endeY, int endeX){
        //Wald grid = wald;

        double posX;
        double posY;

        int dx = endeX - startX;
        int dy = endeY - startY;

        double lx;
        double ly;

        int diagonal = Math.max(Math.abs(dx), Math.abs(dy));

        int marked = 0;

        grid.setFeld(startX, startY, new Feld(Feldtyp.FOERSTER));


        for (int i = 1; i <= diagonal; i++) {

            double t = (double) i / (double) diagonal;
            lx = lerp(startX, endeX, t);
            ly = lerp(startY, endeY, t);

            posX = Math.round(lx);
            posY = Math.round(ly);

            if (nearlyEqual(lx, posX, 0.03) && nearlyEqual(ly, posY, 0.03)) {
                if(marked == 0) {
                    marked = 1;
                } else {
                    marked += 1;
                    grid.setFeld((int) posY, (int)posX, new Feld(Feldtyp.VERDECKTERBAUM));
                }
            }


        }

        //return grid;
    }

}
