package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Utility {

    EnsemblePoints ep;

    Utility(EnsemblePoints ep){
        this.ep = ep;
    }

    void delaunay(){

        Utility util = new Utility(this.ep);

        //Choix du triangle superieur
        Point p1 = new Point(ep.width/2, 0);
        Point p2 = new Point(60, ep.height-60);
        Point p3 = new Point(ep.width-60, ep.height-60);


        Triangle superTriangle = new Triangle(p1, p2, p3);

        ep.triangles.add(superTriangle);

        for(Point point: ep.points){
            Map<Integer, Arete> listeAretes = new HashMap<>();
            List<Triangle> trianglesToRemove = new ArrayList<>();
            for(Triangle t: ep.triangles){

                //Trouver le cercle regroupant les 3 points
                Cercle c = Cercle.trouverCercle(t.p1, t.p2, t.p3);

                c.isInCircle(p1);
                c.isInCircle(p2);
                c.isInCircle(p3);

                Point p0 = c.centre;

                if(p0==null)
                    break;
                double r = sqrt(pow(t.p1.x-p0.x, 2) + pow(t.p1.y - p0.y, 2));
                //Rayon > Dist(p, centre)
                if(sqrt(pow(point.x - p0.x, 2) + pow(point.y - p0.y, 2))<r){
                    Arete a1 = new Arete(t.p1, t.p2);
                    Arete tmp = listeAretes.put(a1.hash(), a1);
                    listeAretes.remove(tmp!=null?a1.hash():null);

                    Arete a2 = new Arete(t.p2, t.p3);
                    tmp = listeAretes.put(a2.hash(), a2);
                    listeAretes.remove(tmp!=null?a2.hash():null);

                    Arete a3 = new Arete(t.p1, t.p3);
                    tmp = listeAretes.put(a3.hash(), a3);
                    listeAretes.remove(tmp!=null?a3.hash():null);

                    trianglesToRemove.add(t);

                }
            }

            ep.triangles.removeAll(trianglesToRemove);

            for(Map.Entry<Integer, Arete> arete:listeAretes.entrySet()){
                ep.triangles.add(new Triangle(arete.getValue().p1, arete.getValue().p2, point));
            }
        }

        List<Triangle> trianglesToRemove = new ArrayList<>();

        for(Triangle tr : ep.triangles){

            if(tr.p1 == superTriangle.p1 || tr.p2 == superTriangle.p1 || tr.p3 == superTriangle.p1)
                trianglesToRemove.add(tr);

            if(tr.p1 == superTriangle.p2 || tr.p2 == superTriangle.p2 || tr.p3 == superTriangle.p2)
                trianglesToRemove.add(tr);

            if(tr.p1 == superTriangle.p3 || tr.p2 == superTriangle.p3 || tr.p3 == superTriangle.p3)
                trianglesToRemove.add(tr);

        }

        ep.triangles.removeAll(trianglesToRemove);

    }

}




class Window {

    int width, height;

    EnsemblePoints dm;

    JFrame mainWindow;

    JPanel mainPan;

    JPanel midPan;
    JPanel rightPan;
    InterfaceGraph canvas;

    Window(EnsemblePoints dm){

        this.dm = dm;

        this.width = dm.width;
        this.height = dm.height;

        this.mainWindow = new JFrame();
        this.mainWindow.setSize(new Dimension(dm.width, dm.height));
        this.mainWindow.setLocationRelativeTo(null);


        this.mainPan = new JPanel();
        this.mainPan.setLayout(new BorderLayout());

        this.midPan = new JPanel();
        this.rightPan = new JPanel();
        this.canvas = new InterfaceGraph(this.dm);

        this.mainWindow.add(BorderLayout.CENTER, this.canvas);
        this.canvas.update();

        this.mainWindow.setVisible(true);
        this.mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

