package reseauLH;

public class Main {

    public static void main(String[] args) {
        System.out.println("\n*** TPE : Modélisation de la vie urbaine ***\n");

        ReseauLH reseau = new ReseauLH("Reseau Le Havre","lh.dgs");
        reseau.showGraph();

        // Some basic measures
        int i;
        System.out.println("- Number of nodes : "+reseau.getNbNodes());
        System.out.println("\n- Number of edges : "+reseau.getNbEdges());
        System.out.println("\n- Average degree : "+reseau.getAverageDegree());
        int[] tab_dist = reseau.getDegreeDistribution();
        System.out.print("\n- Degrees distribution : [");
        for(i = 0; i < tab_dist.length-1; i++)
            System.out.print(i+" : "+tab_dist[i]+", ");
        System.out.print(i+" : "+tab_dist[i]+"]");
        System.out.println("\n\n- Clustering coefficient : "+reseau.getClusteringCoefficient());

        // Diameter
        // System.out.print( "\n- Diameter : ");
        // System.out.println(reseau.getDiameter());

        // Average lenght
        System.out.println("\n- Average lenght : "+reseau.getAverageLenght());

        // Eccentricity
        System.out.println("\n- Eccentricity : "+reseau.getEccentricityMin());

        // Betweenness centrality
        System.out.println("\n- Betweenness centrality : "+reseau.getBetweennessCentrality());
    }
}