package reseauLH;

public class Main {

    public static void main(String[] args) {
        System.out.println("\n*** TPE : Modélisation de la vie urbaine ***\n");

        /* --- Initialisation des réseaux --- */
        ReseauLH reseauLH = new ReseauLH("Reseau LH", "lh.dgs");
        ReseauLH reseauCyclabe = new ReseauLH("Reseau cyclable", "rct.gpx");
        ReseauLH reseauTransportCommun = new ReseauLH("Reseau transports en commun", "rtt.gpx");
        ReseauLH reseauVoiture = new ReseauLH("Reseau voiture", "rvt.gpx");

        reseauLH.setGraphType(0);
        reseauLH.showGraph();

        reseauCyclabe.setGraphType(1);
        reseauCyclabe.showGraph();

        reseauTransportCommun.setGraphType(2);
        reseauTransportCommun.showGraph();

        reseauVoiture.setGraphType(3);
        reseauVoiture.showGraph();

        /* ---  Réseau LH -- */
        System.out.println("*** Reseau LH ***" );
        System.out.println("- Number of nodes : " + reseauLH.getNbNodes());
        System.out.println("- Number of edges : " + reseauLH.getNbEdges());
        System.out.println("- Average degree : " + reseauLH.getAverageDegree());
        System.out.println("- Clustering coefficient : " + reseauLH.getClusteringCoefficient());
        System.out.println("- Average length : " + reseauLH.getAverageLenght());
        System.out.println("- Eccentricity min : " + reseauLH.getEccentricityMin());

        /* ---  Réseau cyclable --- */
        System.out.println("\n*** Reseau cyclable ***" );
        System.out.println("- Number of nodes : " + reseauCyclabe.getNbNodes());
        System.out.println("- Number of edges : " + reseauCyclabe.getNbEdges());
        System.out.println("- Average degree : " + reseauCyclabe.getAverageDegree());
        // System.out.println("- Clustering coefficient : " + reseauCyclabe.getClusteringCoefficient());

        /* ---  Réseau transport en commun --- */
        System.out.println("\n*** Reseau transport en commun ***" );
        System.out.println("- Number of nodes : " + reseauTransportCommun.getNbNodes());
        System.out.println("- Number of edges : " + reseauTransportCommun.getNbEdges());
        System.out.println("- Average degree : " + reseauTransportCommun.getAverageDegree());
        // System.out.println("- Clustering coefficient : " + reseauTransportCommun.getClusteringCoefficient());

        /* ---  Réseau voiture --- */
        System.out.println("\n*** Reseau voiture ***" );
        System.out.println("- Number of nodes : " + reseauVoiture.getNbNodes());
        System.out.println("- Number of edges : " + reseauVoiture.getNbEdges());
        System.out.println("- Average degree : " + reseauVoiture.getAverageDegree());
        // System.out.println("- Clustering coefficient : " + reseauVoiture.getClusteringCoefficient());

        /* ---  Test with merge graph function --- */
        /*
        ReseauLH reseauTest = new ReseauLH("Reseau Le Havre", "testfusion.gpx");
        reseauTest.setGraphType(0);
        reseauTest.showGraph();
        System.out.println("\n*** Reseau test ***" );
        System.out.println("- Number of nodes : " + reseauTest.getNbNodes());
        System.out.println("- Number of edges : " + reseauTest.getNbEdges());
        System.out.println("- Average degree : " + reseauTest.getAverageDegree());

        Graph ng = Graphs.merge(reseauCyclabe.getGraph(),reseauTransportCommun.getGraph());
        ng.display(false);
        System.out.println("\n*** Reseau test merge ***" );
        System.out.println("- Number of nodes : " + ng.getNodeCount());
        System.out.println("- Number of edges : " + ng.getEdgeCount());
        */
    }
}