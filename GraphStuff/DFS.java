public static void dfs(int[][]adj ,boolean[] vis ,int src ,int n)throws Exception{
			
		LinkedList<Integer> stack = new LinkedList<Integer>();
	    	stack.addLast(src);
		vis[src] = true;
		int level = 0;
	//	print(src,level);
		while(!stack.isEmpty()){
			int node = stack.getLast(); // DFS-BFS Decesion
			int check = 0;
			for(int j=1;j<=n;j++){
				if(adj[node][j]==1 && (!vis[j])){
					check = 1;
					level++;
					stack.addLast(j);
	//				print(j,level); 
					vis[j]=true;
					break;
				}
				
			}
			
			// DFS-BFS Decesion remove IF Block
			if(check==0){
				//adj[node][1..n] is empty
			  stack.removeLast();
			  level--;
			}
		}
	}
