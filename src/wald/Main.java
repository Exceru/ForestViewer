package wald;

import wald.Display;

public class Main {

    private static int convert(int zahl, int length) {
        return Math.abs(zahl-length);
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
        grid[y][x] = 1;

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
            grid[y][x] = 1;
        }

        return grid;
    }

    public static void main(String[] args) {

        System.out.println("Hello world!");

        Display main = new Display();

        int[][] grid = new int[20][20];

        // init all white
        for(int row = 0; row < grid[0].length; row++){
            for (int column = 0; column < grid.length; column++) {
                grid[row][column] = 0;
            }
        }

        int start = 20/2;

        int zielX = 19;
        int zielY = 19;

        grid[20/2][20/2] = 2;
        grid[zielY][zielX] = 1;

        grid = bresenham(grid,20/2,20/2, zielY, zielX);





        main.setGrid(grid);
        main.run();



    }
}