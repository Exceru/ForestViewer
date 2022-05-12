package wald;

import java.awt.*;
import java.awt.geom.Point2D;

public class Main {

    private static int convert(int zahl, int length) {
        return Math.abs(zahl-length);
    }

    public static boolean nearlyEqual(double a, double b, double eps){
        return Math.abs(a-b) < eps;
    }

    private static int[][] lineareInterpolation(int[][] array, int y0, int x0, int y1, int x1){
        int[][] grid = array;

        double posX = x0;
        double posY = y0;

        int dx = x1 - x0;
        int dy = y1 - y0;

        double lx = 0.0;
        double ly = 0.0;

        double diagonal = Math.max(Math.abs(dx), Math.abs(dy));

        int marked = 0;


        for (int i = 1; i <= diagonal; i++) {

            double t = diagonal == 0 ? 0.0 : (double) i / (double) diagonal;
            lx = lerp(x0, x1, t);
            ly = lerp(y0, y1, t);

            posX = Math.round(lx);
            posY = Math.round(ly);

            if (nearlyEqual(lx, posX, 0.03) && nearlyEqual(ly, posY, 0.03)) {
                if(marked == 0) {
                    marked = 1;
                } else {
                    marked += 1;
                    grid[(int)posY][(int)posX] = 1;
                }
            }


        }




        return grid;
    }

    private static double lerp(double start, double end, double t) {
        return start + t * (end - start);
    }

    private static int[][] bresenham(int[][] array, int y0, int x0, int y1, int x1){
        int [][] grid = array;
        int t, incx, incy, pdx, pdy, ddx, ddy, deltaslowdirection, deltafastdirection, err;

        int dx = x1 - x0;
        int dy = y1 - y0;

        // Current position
        int y = y0;
        int x = x0;


        /* Entfernung in beiden Dimensionen berechnen */
        dx = x1 - x0;
        dy = y1 - y0;

        /* Vorzeichen des Inkrements bestimmen */
        incx = (int) Math.signum(dx);
        incy = (int) Math.signum(dy);
        if (dx < 0) dx = -dx;
        if (dy < 0) dy = -dy;

        int placedPixels = 0;

        /* feststellen, welche Entfernung größer ist */
        if (dx > dy)
        {
            /* x ist schnelle Richtung */
            pdx = incx; pdy = 0;    /* pd. ist Parallelschritt */
            ddx = incx; ddy = incy; /* dd. ist Diagonalschritt */
            deltaslowdirection = dy;   deltafastdirection = dx;   /* Delta in langsamer Richtung, Delta in schneller Richtung */
        }
        else
        {
            /* y ist schnelle Richtung */
            pdx = 0;    pdy = incy; /* pd. ist Parallelschritt */
            ddx = incx; ddy = incy; /* dd. ist Diagonalschritt */
            deltaslowdirection = dx;   deltafastdirection = dy;   /* Delta in langsamer Richtung, Delta in schneller Richtung */
        }

        /* Initialisierungen vor Schleifenbeginn */
        x = x0;
        y = y0;
        err = deltafastdirection / 2;
        //grid[y][x] = 1;

        /* Pixel berechnen */
        for(t = 0; t < deltafastdirection; ++t) /* t zaehlt die Pixel, deltafastdirection ist Anzahl der Schritte */
        {
            /* Aktualisierung Fehlerterm */
            err -= deltaslowdirection;
            if(err < 0)
            {
                /* Fehlerterm wieder positiv (>=0) machen */
                err += deltafastdirection;
                /* Schritt in langsame Richtung, Diagonalschritt */
                x += ddx;
                y += ddy;
            }
            else
            {
                /* Schritt in schnelle Richtung, Parallelschritt */
                x += pdx;
                y += pdy;
            }

            if(placedPixels == 0) {
                grid[y][x] = 0;
                placedPixels += 1;
            } else {
                grid[y][x] = 1;
                placedPixels += 1;
            }
        }

        return grid;
    }

    public static void main(String[] args) {

        System.out.println("Hello world!");

        Display main = new Display();

        int[][] grid = new int[10][10];

        // init all white
        for(int row = 0; row < grid[0].length; row++){
            for (int column = 0; column < grid.length; column++) {
                grid[row][column] = 0;
            }
        }

        int start = grid.length/2;


        int startX = 2;
        int startY = 2;

        int zielX = 9;
        int zielY = 9;

        grid[startX][startY] = 2;
        //grid[zielY][zielX] = 1;

        //grid = bresenham(grid,10/2,10/2, zielY, zielX);
        grid = lineareInterpolation(grid, startX,startY, zielY, zielX);


        // Fuer jedes Feld die Sichtbarkeit berechnen
        for(int row = 0; row < grid[0].length; row++){
            for (int column = 0; column < grid.length; column++) {
                //if(((column == 0 || column == 9) || (row == 0 || row == 9))) {
                    grid = lineareInterpolation(grid, startX, startY, row, column);
                //}

            }
        }


        main.setGrid(grid);
        main.run();



    }
}