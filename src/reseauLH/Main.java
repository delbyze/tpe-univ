package reseauLH;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.Graphs;

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

        /* ---  Réseau LH -- */
        System.out.println("*** Reseau LH ***" );
        System.out.println("- Number of nodes : " + reseauLH.getNbNodes());
        System.out.println("- Number of edges : " + reseauLH.getNbEdges());
        System.out.println("- Average degree : " + reseauLH.getAverageDegree());
        System.out.println("- Clustering coefficient : " + reseauLH.getClusteringCoefficient());
        System.out.println("- Eccentricity : " + reseauLH.getEccentricityMin());
        // System.out.println("- Average lenght : " + reseauLH.getAverageLenght());
        // System.out.println("- Betweenness centrality : " + reseauLH.getBetweennessCentrality());

        /* ---  Réseau cyclable --- */
        System.out.println("\n*** Reseau cyclable ***" );
        System.out.println("- Number of nodes : " + reseauCyclabe.getNbNodes());
        System.out.println("- Number of edges : " + reseauCyclabe.getNbEdges());
        System.out.println("- Average degree : " + reseauCyclabe.getAverageDegree());
        // System.out.println("- Clustering coefficient : " + reseauCyclabe.getClusteringCoefficient());
        // System.out.println("- Eccentricity : " + reseauCyclabe.getEccentricityMin());
        // System.out.println("- Average lenght : " + reseauCyclabe.getAverageLenght());
        // System.out.println("- Betweenness centrality : " + reseauCyclabe.getBetweennessCentrality());

        /* ---  Réseau transport en commun --- */
        System.out.println("\n*** Reseau transport en commun ***" );
        System.out.println("- Number of nodes : " + reseauTransportCommun.getNbNodes());
        System.out.println("- Number of edges : " + reseauTransportCommun.getNbEdges());
        System.out.println("- Average degree : " + reseauTransportCommun.getAverageDegree());
        // System.out.println("- Clustering coefficient : " + reseauTransportCommun.getClusteringCoefficient());
        // System.out.println("- Eccentricity : " + reseauTransportCommun.getEccentricityMin());
        // System.out.println("- Average lenght : " + reseauTransportCommun.getAverageLenght());
        // System.out.println("- Betweenness centrality : " + reseauTransportCommun.getBetweennessCentrality());

        /* ---  Réseau test --- */
        System.out.println("\n*** Reseau test ***" );
        System.out.println("- Number of nodes : " + reseauTest.getNbNodes());
        System.out.println("- Number of edges : " + reseauTest.getNbEdges());
        System.out.println("- Average degree : " + reseauTest.getAverageDegree());
        // System.out.println("- Clustering coefficient : " + reseauTest.getClusteringCoefficient());
        // System.out.println("- Eccentricity : " + reseauTest.getEccentricityMin());
        // System.out.println("- Average lenght : " + reseauTest.getAverageLenght());
        // System.out.println("- Betweenness centrality : " + reseauTest.getBetweennessCentrality());

        /* ---  Test with merge graph function --- */
        Graph ng = Graphs.merge(reseauCyclabe.getGraph(),reseauTransportCommun.getGraph());
        ng.display(false);
        System.out.println("\n*** Reseau test merge ***" );
        System.out.println("- Number of nodes : " + ng.getNodeCount());
        System.out.println("- Number of edges : " + ng.getEdgeCount());
    }
}