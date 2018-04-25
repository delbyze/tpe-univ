package reseauLH;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.GraphParseException;
import org.graphstream.stream.file.FileSourceGPX;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        System.out.println("\n*** TPE : Modélisation de la vie urbaine ***\n");

        /* --- Initialisation des réseaux --- */
        ReseauLH reseauLH = new ReseauLH("Reseau Le Havre", "lh.dgs");
        ReseauLH reseauCyclabe = new ReseauLH("Reseau Le Havre", "rct.gpx");
        ReseauLH reseauTransportCommun = new ReseauLH("Reseau Le Havre", "rtt.gpx");
        ReseauLH reseauTest = new ReseauLH("Reseau Le Havre", "testfusion.gpx");


        reseauLH.setGraphType(0);
        reseauLH.showGraph();

        reseauCyclabe.setGraphType(1);
        reseauCyclabe.showGraph();

        reseauTransportCommun.setGraphType(2);
        reseauTransportCommun.showGraph();

        reseauTest.setGraphType(0);
        reseauTest.showGraph();

        /* --- Partie calcul --- */

        // Réseau LH
        System.out.println("*** Reseau LH ***" );
        System.out.println("- Number of nodes : " + reseauLH.getNbNodes());
        System.out.println("- Number of edges : " + reseauLH.getNbEdges());
        System.out.println("- Average degree : " + reseauLH.getAverageDegree());
        System.out.println("- Clustering coefficient : " + reseauLH.getClusteringCoefficient());
        System.out.println("- Eccentricity : " + reseauLH.getEccentricityMin());
        // System.out.println("- Average lenght : " + reseauLH.getAverageLenght());
        // System.out.println("- Betweenness centrality : " + reseauLH.getBetweennessCentrality());

        // Réseau cyclable
        System.out.println("\n*** Reseau cyclable ***" );
        System.out.println("- Number of nodes : " + reseauCyclabe.getNbNodes());
        System.out.println("- Number of edges : " + reseauCyclabe.getNbEdges());
        System.out.println("- Average degree : " + reseauCyclabe.getAverageDegree());
        // System.out.println("- Clustering coefficient : " + reseauCyclabe.getClusteringCoefficient());
        // System.out.println("- Eccentricity : " + reseauCyclabe.getEccentricityMin());
        // System.out.println("- Average lenght : " + reseauCyclabe.getAverageLenght());
        // System.out.println("- Betweenness centrality : " + reseauCyclabe.getBetweennessCentrality());

        // Réseau transport en commun
        System.out.println("\n*** Reseau transport en commun ***" );
        System.out.println("- Number of nodes : " + reseauTransportCommun.getNbNodes());
        System.out.println("- Number of edges : " + reseauTransportCommun.getNbEdges());
        System.out.println("- Average degree : " + reseauTransportCommun.getAverageDegree());
        // System.out.println("- Clustering coefficient : " + reseauTransportCommun.getClusteringCoefficient());
        // System.out.println("- Eccentricity : " + reseauTransportCommun.getEccentricityMin());
        // System.out.println("- Average lenght : " + reseauTransportCommun.getAverageLenght());
        // System.out.println("- Betweenness centrality : " + reseauTransportCommun.getBetweennessCentrality());

        // Réseau test
        System.out.println("\n*** Reseau test ***" );
        System.out.println("- Number of nodes : " + reseauTest.getNbNodes());
        System.out.println("- Number of edges : " + reseauTest.getNbEdges());
        System.out.println("- Average degree : " + reseauTest.getAverageDegree());
        // System.out.println("- Clustering coefficient : " + reseauTest.getClusteringCoefficient());
        // System.out.println("- Eccentricity : " + reseauTest.getEccentricityMin());
        // System.out.println("- Average lenght : " + reseauTest.getAverageLenght());
        // System.out.println("- Betweenness centrality : " + reseauTest.getBetweennessCentrality());


       /*
       // Some basic measures
        int i;
        System.out.print("\n- Degrees distribution : [");
        for (i = 0; i < tab_dist.length - 1; i++)
            System.out.print(i + " : " + tab_dist[i] + ", ");
        System.out.print(i + " : " + tab_dist[i] + "]");

        // Diameter
        // System.out.print( "\n- Diameter : ");
        // System.out.println(reseau.getDiameter());
       */
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