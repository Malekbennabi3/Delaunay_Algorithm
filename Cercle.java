package com.company;

import java.util.Scanner;

public class Cercle {

    Point centre;
    int rayon;

    Cercle(Point centre, int rayon){

        this.centre = centre;
        this.rayon = rayon;

    }

    public double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    static Cercle trouverCercle(Point p1, Point p2, Point p3){
        int x12 = p1.x - p2.x;
        int x13 = p1.x - p3.x;

        int y12 = p1.y - p2.y;
        int y13 = p1.y - p3.y;

        int y31 = p3.y - p1.y;
        int y21 = p2.y - p1.y;

        int x31 = p3.x - p1.x;
        int x21 = p2.x - p1.x;

        // x1^2 - x3^2
        int sx13 = (int)(Math.pow(p1.x, 2) -
                Math.pow(p3.x, 2));

        // y1^2 - y3^2
        int sy13 = (int)(Math.pow(p1.y, 2) - Math.pow(p3.y, 2));

        int sx21 = (int)(Math.pow(p2.x, 2) - Math.pow(p1.x, 2));

        int sy21 = (int)(Math.pow(p2.y, 2) - Math.pow(p1.y, 2));

        int f = ((sx13) * (x12) + (sy13) * (x12) + (sx21) * (x13) + (sy21) * (x13)) / (2 * ((y31) * (x12) - (y21) * (x13)));
        int g = ((sx13) * (y12) + (sy13) * (y12) + (sx21) * (y13) + (sy21) * (y13)) / (2 * ((x31) * (y12) - (x21) * (y13)));

        int c = -(int)Math.pow(p1.x, 2) - (int)Math.pow(p1.y, 2) - 2 * g * p1.x - 2 * f * p1.y;

        int h = -g;
        int k = -f;
        int sqr_of_r = h * h + k * k - c;

        double r = Math.sqrt(sqr_of_r);

        System.out.println("Le Centre du cercle est: ("+h+", "+k+")");
        System.out.println("Le Rayon du cercle est: "+r);


        return new Cercle(new Point(h, k), (int)r);

    }


    Boolean isInCircle( Point p){

        boolean isInside = false;

        if(distance(this.centre.x, this.centre.y, p.x, p.y) < this.rayon)
            isInside = true;

        return isInside;

    }

}

 class Main {

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        EnsemblePoints ep = new EnsemblePoints(5);
        ep.setADelaunay(255);

        System.out.print("Nombre de points: ");
        int np=sc.nextInt();

        ep.randomPoints(np);

        Utility util = new Utility(ep);
        util.delaunay();

        Window wind = new Window(ep);

    }
}

