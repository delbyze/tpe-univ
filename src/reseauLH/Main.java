package reseauLH;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.GraphParseException;
import org.graphstream.stream.file.FileSourceGPX;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        System.out.println("\n*** TPE : Modélisation de la vie urbaine ***\n");

        // Test de DGS
        //ReseauLH reseau = new ReseauLH("Reseau Le Havre", "lh.dgs");

        // Test de GPX
        ReseauLH reseau = new ReseauLH("Reseau Le Havre", "test.gpx");
        ReseauLH reseau2 = new ReseauLH("Reseau Le Havre", "rct.gpx");

        reseau.showGraph();
        reseau2.showGraph();


        // Test : read a GPX file
        //reseauWithGPX("Reseau Le Havre", "rce.gpx");

        /* --- Partie calcul --- */

        // Some basic measures
        int i;
        System.out.println("- Number of nodes : " + reseau.getNbNodes());
        System.out.println("\n- Number of edges : " + reseau.getNbEdges());
        System.out.println("\n- Average degree : " + reseau.getAverageDegree());
        int[] tab_dist = reseau.getDegreeDistribution();
        System.out.print("\n- Degrees distribution : [");
        for (i = 0; i < tab_dist.length - 1; i++)
            System.out.print(i + " : " + tab_dist[i] + ", ");
        System.out.print(i + " : " + tab_dist[i] + "]");
        System.out.println("\n\n- Clustering coefficient : " + reseau.getClusteringCoefficient());

        // Diameter
        // System.out.print( "\n- Diameter : ");
        // System.out.println(reseau.getDiameter());

        // Average lenght
        System.out.println("\n- Average lenght : " + reseau.getAverageLenght());

        // Eccentricity
        System.out.println("\n- Eccentricity : " + reseau.getEccentricityMin());

        // Betweenness centrality
        System.out.println("\n- Betweenness centrality : " + reseau.getBetweennessCentrality());


    }


    /* --- Deprecated --- */
    /*
    public static void reseauWithGPX(String id, String file) {
        String filePath = "data/" + file;
        Graph g = new SingleGraph(id);
        FileSourceGPX fs = new FileSourceGPX();
        fs.addSink(g);

        try {
            fs.readAll(filePath);
        } catch( IOException e) {

        } finally {
            fs.removeSink(g);
        }

        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        g.addAttribute("ui.quality"); // better quality for pixel node
        g.addAttribute("ui.antialias"); // better quality for pixel edge
        g.addAttribute("ui.stylesheet", "url('data/style.css')"); // set a stylesheet

        g.display(false);

        System.out.println(g.getEdgeCount());
        System.out.println(g.getNodeCount());
    }
    */
}