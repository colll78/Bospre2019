import java.util.*;

public class SpaceTravel {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Graph g = new Graph();
        HashMap<Integer, HashSet<Node>> sameStationNodes;
        ArrayList<Node> sameShipNodes;
        while (true) {
            String testCaseName = scan.nextLine();
            int numStarSystems = scan.nextInt();
            int numShips = scan.nextInt();
            sameStationNodes = new HashMap<>();
            for (int i = 0; i < numShips; i++) {
                int nStops = scan.nextInt();
                sameShipNodes = new ArrayList<>();
                for (int y = 0; y < nStops; y++) {

                    int stop = scan.nextInt();
                    int time = scan.nextInt();
                    Node newN = new Node(stop, time);
                    sameShipNodes.add(newN);
                    if (sameStationNodes.containsKey(stop)) {
                        sameStationNodes.get(stop).add(newN);
                    } else {
                        sameStationNodes.put(stop, new HashSet<>());
                        sameStationNodes.get(stop).add(newN);
                    }
                    g.addVertex(newN);
                }
                for (int k = 1; k < sameShipNodes.size(); k++) {
                    sameShipNodes.get(k).addEdge(sameShipNodes.get(k - 1));
                }
            }
            for (int i = numStarSystems; i > 0; i--) {


            }
        }

    }

    static class Node {
        private int stationNumber;
        private int time;
        private ArrayList<Node> neighbors;

        public Node(int stationNumber, int time) {
            this.stationNumber = stationNumber;
            this.time = time;
        }

        public int getTime() {
            return this.time;
        }

        public int getStationNumber() {
            return this.stationNumber;
        }

        public ArrayList<Node> getNeighbors() {
            return this.neighbors;
        }

        public void addEdge(Node to) {
            neighbors.add(to);
            to.getNeighbors().add(this);
        }

        public int hashCode() {
            return Objects.hash(this.stationNumber, this.time);
        }

        public boolean equals(Object o) {
            if (o instanceof Node) {
                Node n = (Node) o;
                return n.stationNumber == this.stationNumber && n.time == this.time;
            } else {
                return false;
            }
        }
    }

    static class Graph {
        private int V;
        // private int E;
        private HashSet<Node> vertices;

        public Graph() {
            this.V = 0;
            // this.E = 0;
            this.vertices = new HashSet<>();
        }

        public void addVertex(Node n) {
            vertices.add(n);
            this.V++;
        }

        public int V() {
            return this.V;
        }
        // public int E() { return this.E; }

        public HashSet<Node> getNodes() {
            return this.vertices;
        }
    }
}


