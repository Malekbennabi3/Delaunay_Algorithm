package com.company;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;


public class InterfaceGraph extends JPanel{

    EnsemblePoints ep;

    public InterfaceGraph(EnsemblePoints ep) {
        this.ep = ep;
    }

    void update(){

        this.repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        BufferedImage canvasImg = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D canvasPen = canvasImg.createGraphics();

        Utility util = new Utility(this.ep);

        g.setColor(new Color(0, 0, 0, 255));

        if(ep.points.size() > 0)
            for(Point p : ep.points)
                g.fillRect(p.x, p.y, ep.pointSize, ep.pointSize);

        g.setColor(new Color(0, 0, 255, ep.aDelaunay));

        if(ep.triangles.size() > 0)
            for(Triangle t : ep.triangles){

                g.drawLine(t.p1.x, t.p1.y, t.p2.x, t.p2.y);
                g.drawLine(t.p2.x, t.p2.y, t.p3.x, t.p3.y);
                g.drawLine(t.p3.x, t.p3.y, t.p1.x, t.p1.y);

                Cercle c = Cercle.trouverCercle(t.p1, t.p2, t.p3);

            }

        g.drawImage(canvasImg, 0, 0, this);

        g.dispose();

    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.

    }

}
