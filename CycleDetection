  boolean isCycle(int root, LinkedList<Integer>[] adj)throws Exception{

    LinkedList <Integer> queue = new LinkedList<Integer>(); //the stack 
    int currentDepth = 0; // level
    queue.add(root);
    vis[root] = true;
    
    while(!queue.isEmpty()){
    
      int u = queue.getLast(); //top
      //depth[u]= currentDepth;
      if(adj[u].size() > 0){
        int v = adj[u].removeFirst();
        if(!vis[v]){
          queue.add(v);
          currentDepth++;
          vis[v] = true; 
        }else {
         return true;
        }
      }else{
        int v = queue.removeLast();
        currentDepth--;
      }
    }
    return false;
  }
