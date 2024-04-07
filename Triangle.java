package com.company;

public class Triangle {

    int id = 0;

    Point p1;
    Point p2;
    Point p3;

    Triangle(Point p1, Point p2, Point p3){

        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;

    }

}

class Arete {
    public Arete(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    //
    @Override
    public boolean equals(Object e){
        return (p1.equals(((Arete)e).p1) && p2.equals(((Arete)e).p2)) || (p1.equals(((Arete)e).p2) && p2.equals(((Arete)e).p1));
    }

    public Integer hash(){
        return (int)(p1.x+p1.y+p2.x+p2.y);
    }

    Point p1;
    Point p2;
}
