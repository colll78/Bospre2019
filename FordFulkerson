import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class FordFulkerson {
    public static final double FLOATING_POINT_EPSILON = 1E-11;

    private final int V;
    private boolean[] marked; //marked[v] = true iff s->v path in residual graph
    private FlowEdge[] edgeTo; //edgeTo[v] = last edge on shortest residual s->v path
    private double value; //current value of max flow

    public FordFulkerson(FlowNetwork G, int s, int t){
        V = G.V();
        validate(s);
        validate(t);
        if(s == t) throw new IllegalArgumentException("Source equals sink");
        if(!isFeasible(G, s, t)) throw new IllegalArgumentException("Initial flow is infeasible");

        value = excess(G, t);

        while(hasAugmentingPath(G, s, t)){
            //bottleneck capacity
            double bottle = Double.POSITIVE_INFINITY;
            for (int v = t; v != s; v = edgeTo[v].other(v)){
                bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
            }

            //augment flow
            for (int v = t; v != s; v = edgeTo[v].other(v)){
                edgeTo[v].addResidualFlowTo(v, bottle);
            }

            value += bottle;
        }
        assert check(G, s, t);
    }

    public double value(){
        return value;
    }

    private void validate(int v)  {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public boolean inCut(int v){
        validate(v);
        return marked[v];
    }

    private boolean hasAugmentingPath(FlowNetwork G, int s, int t){
        edgeTo = new FlowEdge[G.V()];
        marked = new boolean[G.V()];

        //breadth-first-search
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        marked[s] = true;

        while(!queue.isEmpty() && !marked[t]){
            int v = queue.remove();
            for (FlowEdge e : G.adj(v)){
                int w = e.other(v);

                //if residual capacity from v to w
                if(e.residualCapacityTo(w) > 0){
                    if(!marked[w]){
                        edgeTo[w] = e;
                        marked[w] = true;
                        queue.add(w);
                    }
                }

            }
        }
        return marked[t];
    }

    private double excess(FlowNetwork G, int v){
        double excess = 0.0;
        for (FlowEdge e : G.adj(v)){
            if (v == e.from())      excess -= e.flow();
            else                    excess =+ e.flow();
        }
        return excess;
    }

    private boolean isFeasible(FlowNetwork G, int s, int t){
        for(int v = 0; v < G.V(); v++){
            for(FlowEdge e : G.adj(v)){
                if(e.flow() < -FLOATING_POINT_EPSILON || e.flow() > e.capacity() + FLOATING_POINT_EPSILON){
                    System.err.println("Edge does not satisfy capacity constraints: " + e );
                    return false;
                }
            }
        }
        if(Math.abs(value - excess(G, t)) > FLOATING_POINT_EPSILON) {
            System.err.println("Excess at source    = " + excess(G, s));
            System.err.println("Max flow            = " + value);
            return false;
        }
        if(Math.abs(value - excess(G, t)) > FLOATING_POINT_EPSILON){
            System.err.println("Excess at sink      = " + excess(G, t));
            System.err.println("Max flow            = " + value);
            return false;
        }
        for(int v = 0; v < G.V(); v++){
            if(v == s || v == t) continue;
            else if(Math.abs(excess(G, v)) > FLOATING_POINT_EPSILON){
                System.err.println("Net flow out of " + v + " doesn't equal zero");
                return false;
            }
        }
        return true;
    }

    private boolean check(FlowNetwork G, int s, int t){
        if(!isFeasible(G, s, t)){
            System.out.println("Flow is infeasible");
            return false;
        }
        if(!inCut(s)){
            System.err.println("source " + s + " is not on source side of min cut");
            return false;
        }
        if(inCut(t)){
            System.err.println("sink " + t + " is on source side of min cut");
            return false;
        }

        double minCutValue = 0.0;
        for(int v = 0; v < G.V(); v++){
            for(FlowEdge e : G.adj(v)){
                if((v == e.from()) && inCut(e.from()) && !inCut(e.to())){
                    minCutValue += e.capacity();
                }
            }
        }

        if(Math.abs(minCutValue - value) > FLOATING_POINT_EPSILON){
            System.err.println("Max flow value = " + value + ", min cut value = " + minCutValue);
            return false;
        }

        return true;
    }

    static class Bag<Item> implements Iterable<Item> {
        private Node first;
        private int n;

        private class Node{
            Item item;
            Node next;
        }

        public boolean isEmpty()    { return this.first == null; }
        public int size()           { return this.n; }

        public void add(Item item){
            Node oldfirst = this.first;
            this.first = new Node ();
            this.first.item = item;
            this.first.next = oldfirst;
            this.n++;
        }

        public
        Iterator <Item> iterator(){
            return new ListIterator ();
        }

        private class ListIterator implements Iterator<Item>{
            private Node current = first;

            public boolean hasNext(){
                return current != null;
            }

            public void remove(){

            }

            public Item next(){
                Item item = current.item;
                this.current = current.next;
                return item;
            }
        }

    }

    static class FlowEdge {
        public final int v;
        public final int w;
        public final double capacity;
        public double flow;

        public FlowEdge(int v, int w, double capacity){
            this.v = v;
            this.w = w;
            this.capacity = capacity;
            this.flow = 0.0;
        }

        public FlowEdge(int v, int w, double capacity, double flow) {
            if (v < 0) throw new IllegalArgumentException("vertex index must be a non-negative integer");
            if (w < 0) throw new IllegalArgumentException("vertex index must be a non-negative integer");
            if (!(capacity >= 0.0))  throw new IllegalArgumentException("edge capacity must be non-negative");
            if (!(flow <= capacity)) throw new IllegalArgumentException("flow exceeds capacity");
            if (!(flow >= 0.0))      throw new IllegalArgumentException("flow must be non-negative");
            this.v         = v;
            this.w         = w;
            this.capacity  = capacity;
            this.flow      = flow;
        }

        public int from(){ return this.v; }
        public int to(){ return this.w; }
        public double capacity(){ return this.capacity; }
        public double flow() { return flow; }

        public int other(int vertex){
            if (vertex == v) return w;
            else if (vertex == w) return v;
            else throw new IllegalArgumentException();
        }

        public double residualCapacityTo(int vertex){
            if (vertex == v) return flow;
            else if (vertex == w) return capacity - flow;
            else throw new IllegalArgumentException();
        }

        public void addResidualFlowTo(int vertex, double delta){
            if (vertex == v) flow -= delta;
            if (vertex == w) flow += delta;
            else throw new IllegalArgumentException();
        }

        public String toString() {
            return v + "->" + w + " " + flow + "/" + capacity;
        }
    }

    static class FlowNetwork {
        private int V;
        private int E;
        private Bag<FlowEdge>[] adj;

        public FlowNetwork(int V){
            this.V = V;
            this.E = 0;
            adj = (Bag<FlowEdge>[]) new Bag[V];
            for (int v = 0; v < V; v++){
                adj[v] = new Bag<FlowEdge>();
            }
        }

        public FlowNetwork(int V, int E) {
            this(V);
            Random r = new Random();
            if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
            for (int i = 0; i < E; i++) {
                int v = r.ints(0, V).findFirst().getAsInt();
                int w = r.ints(0, V).findFirst().getAsInt();
                double capacity = r.doubles(0, 100).findFirst().getAsDouble();
                addEdge(new FlowEdge(v, w, capacity));
            }
        }

        int V(){ return this.V; }
        int E(){ return this.E; }

        public void addEdge(FlowEdge e) {
            int v = e.from();
            int w = e.to();
            validateVertex(v);
            validateVertex(w);
            adj[v].add(e);
            adj[w].add(e);
            E++;
        }

        public Iterable<FlowEdge> adj(int v) {
            validateVertex(v);
            return adj[v];
        }

        private void validateVertex(int v) {
            if (v < 0 || v >= V)
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }

        public Iterable<FlowEdge> edges(){
            Bag<FlowEdge> bag = new Bag<FlowEdge>();
            for(int v = 0; v < V; v++){
                for(FlowEdge e : adj[v]){
                    if(e.other(v) > v){
                        bag.add(e);
                    }
                }
            }
            return bag;
        }

        public String toString(){
            StringBuilder s = new StringBuilder();
            s.append(V + " " + E + "\n");
            for (int v = 0; v < V; v++) {
                s.append(v + ":  ");
                for (FlowEdge e : adj[v]) {
                    if (e.to() != v) s.append(e + "  ");
                }
                s.append("\n");
            }
            return s.toString();
        }

        public static void main(String[] args){

        }
    }


}
