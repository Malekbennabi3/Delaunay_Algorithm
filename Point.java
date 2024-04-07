package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Point {

    int x, y;

    Point(int x, int y){

        this.x = x;
        this.y = y;

    }

}

class EnsemblePoints {

    ArrayList<Point> points;
    ArrayList<Triangle> triangles;

    public static int aDelaunay;

    int pointSize = 5;

    int width;
    int height;

    int border = 300;


    EnsemblePoints(int nb){

        points = new ArrayList<>();
        triangles = new ArrayList<>();

        this.pointSize=nb;
        this.width = 800;
        this.height = 800;

    }

    //Selection aleatoire de points
    void randomPoints(int num){

        Random rand = new Random(System.currentTimeMillis());

        int x, y;

        for(int i = 0; i < num; i++){

            x = border + rand.nextInt(this.width-2*border);
            y = border + rand.nextInt(this.height-2*border);

            System.out.println("x: " + x +" " + y);

            this.points.add(new Point(x, y));

        }
    }


    public void setADelaunay(int alphaDelaunay) {
        this.aDelaunay = alphaDelaunay;
    }
}

