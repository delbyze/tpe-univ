package reseauLH;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.GraphParseException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.graphstream.algorithm.Toolkit.*;

public class ReseauLH {
    private Graph graph;

    public ReseauLH(String id, String file) {
        this.graph = new SingleGraph(id);
        try {
            this.graph.read("data/"+file);
        } catch (IOException | GraphParseException e) {
            e.printStackTrace();
        }
        this.initialiseStyle();
    }

    private void initialiseStyle() {
        System.setProperty("org.graphstream.ui.renderer","org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        this.graph.addAttribute("ui.quality"); // better quality for pixel node
        this.graph.addAttribute("ui.antialias"); // better quality for pixel edge
        this.graph.addAttribute("ui.stylesheet","url('data/style.css')"); // set a stylesheet
    }

    public int getNbNodes() {
        return this.graph.getNodeCount();
    }

    public int getNbEdges() {
        return this.graph.getEdgeCount();
    }

    public double getAverageDegree() {
        return averageDegree(this.graph);
    }

    public int[] getDegreeDistribution() {
        return degreeDistribution(this.graph);
    }

    public double getClusteringCoefficient() {
        return averageClusteringCoefficient(this.graph);
    }

    public double getDiameter() {
       return diameter(this.graph,"length",false);
    }

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

    public void showGraph() { this.graph.display(false); }
}