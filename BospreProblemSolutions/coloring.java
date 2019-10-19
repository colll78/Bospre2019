import java.util.*;

public class coloring {
    public static  void main(String [] args){
        Scanner scan = new Scanner(System.in);
        while(scan.hasNextInt()){
            int vertices = scan.nextInt();
            scan.nextLine();
            int max_min_degree = 0;
            StringBuilder mat = new StringBuilder();
            for(int i = 0; i < vertices; i++){
                String l = scan.nextLine();
                mat.append(l);
            }

            String regex1 = "(?<=\\G.{" + vertices + "})";
            int[][] numbers = Arrays.stream(mat.toString().split(regex1))
                                .map(s -> (Arrays.stream(s.split("(?<=\\G.{1})"))
                                        .mapToInt(Integer::parseInt).toArray()))
                                .toArray(int[][]::new);

            Graph g = new Graph(numbers);
            max_min_degree =  g.graphPrune();
            System.out.println(max_min_degree+1);
        }
    }

    static void print2DArray(int[][] array) {
        for (int[] nums : array) {
            for (int i : nums) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

    static class Graph{
        int V;
        int E;
        ArrayList<ArrayList<Integer>> adjList;
        int[][] mat;

        public Graph(int[][] matrix){
            this.mat = matrix;
            this.V = matrix.length;
            this.E = 0;
            adjList = new ArrayList<>();
            for(int i = 0; i < matrix.length; i++){
                adjList.add(new ArrayList<>());
            }

            for(int i = 0; i < matrix.length; i++){
                for(int j = 0; j < matrix.length; j++){
                    if(matrix[i][j] == 1){
                        adjList.get(i).add(j);
                    }
                }
            }
        }

        public Graph(int V) {
            if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
            this.V = V;
            this.E = 0;
            adjList = new ArrayList<>();
            for (int v = 0; v < V; v++) {
                adjList.add(new ArrayList<>());
            }
        }

        public void removeNode(int vertex){
            for(ArrayList<Integer> a : adjList) {
                a.remove(Integer.valueOf(vertex));
            }
            for(int i = 0; i < adjList.size(); i++){
                this.adjList.get(vertex).remove(Integer.valueOf(i));
            }
        }

        public void addEdge(int from, int to){
            adjList.get(from).add(to);
            adjList.get(to).add(from);              //Remove for directed graph
            E++;
        }

        public ArrayList<Integer> getAdjEdges(int vertex){
            return adjList.get(vertex);
        }

        public int degree(int vertex){
            return adjList.get(vertex).size();
        }

        public int minDegree(){
            int minDegree = Integer.MAX_VALUE;
            for(ArrayList<Integer> li : adjList){
                if(li.size() < minDegree && li.size() != 0){
                    minDegree = li.size();
                }
            }
            return minDegree;
        }

        public int maxDegree(){
            int maxDegree = 0;
            for (ArrayList<Integer> li : adjList){
                if(li.size() > maxDegree){
                    maxDegree = li.size();
                }
            }
            return maxDegree;
        }

        public int neighborsInList(ArrayList<Integer> orderingList, int n, int index){
            //System.out.print("Number: " + n);
            //System.out.print("     Index: " + index);
            Graph t = new Graph(this.mat);
            int nInList = 0;
            for(int i = index; i < orderingList.size(); i++){
                if (t.adjList.get(i).contains(n)){
                    nInList++;
                }
            }
            //System.out.println("     Score: " + nInList);
            return nInList;
        }

        public int graphPrune(){
            Stack<Integer> allocHelper = new Stack<>();
            int cur_degree;
            int max_min_degree = 0;
            while(allocHelper.size() < this.V) {
                cur_degree = minDegree();

                if(cur_degree == Integer.MAX_VALUE){
                    cur_degree = 0;
                    for (int j = 0; j < this.adjList.size(); j++) {
                        if(!allocHelper.contains(j)){
                            allocHelper.push(j);
                        }
                    }
                    break;
                }
                //System.out.println("Current Min Degree: " + cur_degree);
                max_min_degree = (max_min_degree < cur_degree) ? cur_degree : max_min_degree;


                for (int j = 0; j < this.adjList.size(); j++) {
                    if(degree(j) == cur_degree){
                        //System.out.println("J: " + j);
                        allocHelper.push(j);
                        removeNode(j);
                    }
                }
            //http://graphonline.ru/en/?graph=fKoQvGmvIPdUkZUL
            }

            ArrayList<Integer> orderingList = new ArrayList<>();
            while(allocHelper.size() > 0){
                orderingList.add(allocHelper.pop());
            }
            /*
            orderingList = new ArrayList<>(){{
                add(7);
                add(0);add(1);add(3);add(9);add(5);add(6);add(2);add(4);add(8);
            }};
            */


            int max_mvalue = 0, rValue;
            /*System.out.println("List:" );
            for(Integer l : orderingList){
                System.out.println(l);
            }*/
            Collections.reverse(orderingList);
            for(int i = 0; i < orderingList.size(); i++){
                rValue = neighborsInList(orderingList, orderingList.get(i), i);
                max_mvalue = (rValue > max_mvalue) ? rValue : max_mvalue;
            }
            return max_min_degree;
            //return max_mvalue;
        }
    }

}

/*
10
0110100111
1001111001
1000111011
0100011011
1110011110
0111101011
0111110111
1000101010
1011111101
1111011010


4
0111
1011
1101
1110

4
0101
1010
0101
1010
5
01111
10000
10000
10000
10000

 */
