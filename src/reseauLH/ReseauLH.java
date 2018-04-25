package reseauLH;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.GraphParseException;
import org.graphstream.stream.file.FileSourceGPX;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.graphstream.algorithm.Toolkit.*;

/**
 * Classe de création et calcul d'un réseau
 * @author Weber Delia
 * @version 1.1
 */
public class ReseauLH {

    /* ------------------------- */
    /* --- Variables Globaux --- */
    /* ------------------------- */
    private Graph graph;


    /* -------------------- */
    /* --- Contructeurs --- */
    /* -------------------- */

    /**
     * Contructeur
     * @author Weber Delia
     * @param id   Identifieur du graphe
     * @param file Nom du fichier source que le graphe doit représenter
     * @version 1.1
     */
    public ReseauLH(String id, String file) {

        String filePath = "data/" + file;

        this.graph = new SingleGraph(id);

        if(file.endsWith(".gpx"))
        {
            FileSourceGPX fs = new FileSourceGPX();
            fs.addSink(this.graph);

            try {
                fs.readAll(filePath);
            } catch( IOException e) {

            } finally {
                fs.removeSink(this.graph);
            }
        }
        else
        {
            try {
                this.graph.read(filePath);
            } catch (IOException | GraphParseException e) {
                e.printStackTrace();
            }
        }

        this.initialiseStyle();
    }

    /* ---------------- */
    /* --- Méthodes --- */
    /* ---------------- */

    /**
     * Méthode d'initiallisation de style
     * @author Weber Delia
     * @version 1.0
     */
    private void initialiseStyle() {
        System.setProperty("org.graphstream.ui.renderer","org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        this.graph.addAttribute("ui.quality"); // better quality for pixel node
        this.graph.addAttribute("ui.antialias"); // better quality for pixel edge
        this.graph.addAttribute("ui.stylesheet","url('data/style.css')"); // set a stylesheet
    }

    /**
     * Getter du nombre de noeuds
     * @author Weber Delia
     * @return Le nombre de noeud du graphe
     * @version 1.0
     */
    public int getNbNodes() {
        return this.graph.getNodeCount();
    }

    /**
     * Getter du nombre d'aretes
     * @author Weber Delia
     * @return Le nombre d'aretes du graphe
     * @version 1.0
     */
    public int getNbEdges() {
        return this.graph.getEdgeCount();
    }

    /**
     * Getter du degré moyen du graphe
     * @author Weber Delia
     * @return Le degré du graphe
     * @version 1.0
     */
    public double getAverageDegree() {
        return averageDegree(this.graph);
    }

    /**
     * Getter des degrés de distribution du graphe
     * @author Weber Delia
     * @return Les degrés de distribution du graphe
     * @version 1.0
     */
    public int[] getDegreeDistribution() {
        return degreeDistribution(this.graph);
    }

    /**
     * Getter du coefficient de clustering du graphe
     * @author Weber Delia
     * @return Le coefficient de clustering du graphe
     * @version 1.0
     */
    public double getClusteringCoefficient() {
        return averageClusteringCoefficient(this.graph);
    }

    /**
     * Getter du Diametre du graphe
     * @author Weber Delia
     * @return Le diametre du graphe
     * @version 1.0
     */
    public double getDiameter() {
        return diameter(this.graph,"length",false);
    }

    /**
     * Donne la longueur moyen du graphe en fonction de ses aretes
     * @author Weber Delia
     * @return La longueur moyenne du graphe
     * @version 1.0
     */
    public double getAverageLenght() {
        double average_length = 0.0;
        double cpt = 0.0;

        Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "length");
        dijkstra.init(this.graph);

        for(Node n1 : this.graph.getEachNode()) {
            dijkstra.setSource(n1);
            dijkstra.compute();

            for(Node n2 : this.graph.getEachNode()) {
                double tmp_length = 0.0;
                Node tmp_node = null;

                for(Node n_path : dijkstra.getPathNodes(n2)) {
                    if(tmp_node != null)
                        tmp_length += (double) n_path.getEdgeFrom(tmp_node).getAttribute("length");
                    tmp_node = n_path;
                }
                cpt++;
                average_length += tmp_length;
            }
        }
        return average_length/cpt;
    }

    /**
     * Donne l'excentricité minimal du graphe
     * @author Weber Delia
     * @return l'excentricité miniaml du graphe
     * @version 1.0
     */
    public String getEccentricityMin() {
        String ret = "";
        ArrayList<Node> ecc_min_nodes = new ArrayList<>();
        double ecc_min = -1;

        Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "length");
        dijkstra.init(this.graph);

        for(Node n1 : this.graph.getEachNode()) {
            dijkstra.setSource(n1);
            dijkstra.compute();

            double ecc = 0.0;
            for(Node n2 : this.graph.getEachNode()) {
                double tmp_length = 0.0;
                Node tmp_node = null;

                for(Node n_path : dijkstra.getPathNodes(n2)) {
                    if(tmp_node != null)
                        tmp_length += (double) n_path.getEdgeFrom(tmp_node).getAttribute("length");
                    tmp_node = n_path;
                }
                if(tmp_length > ecc)
                    ecc = tmp_length;
            }
            if(ecc == ecc_min)
                ecc_min_nodes.add(n1);

            if(ecc < ecc_min || ecc_min == -1) {
                ecc_min_nodes.clear();
                ecc_min_nodes.add(n1);
                ecc_min = ecc;
            }
        }

        for(Node n : ecc_min_nodes) {
            n.addAttribute("ui.class", "center_nodes");
            ret += n.getId()+", ";
        }
        return ret+"\n  Valeur : "+ecc_min;
    }

    /**
     * Donne la centralité intermédiaire du graphe
     * @author Weber Delia
     * @return la centralité intermédiaire du graphe
     * @version 1.0
     */
    public String getBetweennessCentrality() {
        String ret = "";
        int cpt_max = 0;

        HashMap<String, Integer> edges = new HashMap<>();
        for(Edge e : this.graph.getEachEdge())
            edges.put(e.getId(),0);

        Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "length");
        dijkstra.init(this.graph);

        for(Node n1 : this.graph.getEachNode()) {
            dijkstra.setSource(n1);
            dijkstra.compute();

            for(Node n2 : this.graph.getEachNode()) {
                Node tmp_node = null;

                for(Node n_path : dijkstra.getPathNodes(n2)) {
                    if(tmp_node != null) {
                        Edge tmp_edge = n_path.getEdgeFrom(tmp_node);
                        edges.put(tmp_edge.getId(), edges.get(tmp_edge.getId()) + 1);
                        if(edges.get(tmp_edge.getId()) > cpt_max)
                            cpt_max = edges.get(tmp_edge.getId());
                    }
                    tmp_node = n_path;
                }
            }
        }

        for(String s : edges.keySet()) {
            Color color;
            Edge tmp_edge = this.graph.getEdge(s);
            double percentage = ((double)  edges.get(s) / (double) cpt_max) * 100;
            if(percentage < 50.0) {
                int red = (int) (255 * (percentage / 50));
                color = new Color(red, 255, 0);
            } else {
                int green = 255 - (int) (255 * ((percentage / 2) / 100));
                color = new Color(255, green, 0);
            }
            tmp_edge.addAttribute("ui.style", "fill-color: " + "rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ");");
        }

        for(String s : edges.keySet())
            ret += s + " : " + edges.get(s) + ", ";
        return ret;
    }

    /**
     * Donne la centralité intermédiaire du graphe
     * @author Weber Delia
     * @version 1.0
     */
    public void showGraph() {
        this.graph.display(false);
    }

    /**
     * Modifie le type du graphe
     * @author Weber Delia
     * @param index Index du type
     * @version 1.0
     */
    public void setGraphType(int index) {
        switch (index) {
            case 1 :
                for(Edge e : this.graph.getEachEdge())
                    e.setAttribute("ui.class","cyclable");
                break;
            case 2 :
                for(Edge e : this.graph.getEachEdge())
                    e.setAttribute("ui.class","transport_commun");
                break;
            default:
                for(Edge e : this.graph.getEachEdge())
                    e.setAttribute("ui.class","route");
                break;
        }
    }

    /**
     * Retourne le graphe courant
     * @author Weber Delia
     * @version 1.0
     */
    public Graph getGraph() {
        return this.graph;
    }


}