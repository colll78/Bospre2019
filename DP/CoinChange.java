public static int coinChanging(int coins[], int sum) {
		int n = coins.length;
		int table[][] = new int[n + 1][sum + 1];

		for(int i = 0; i <= n; i++)
			table[i][0] = 1;

		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= sum; j++) {
				if(j < coins[i - 1]){
					table[i][j] = table[i - 1][j];
				} else {
					table[i][j] = table[i - 1][j] + table[i][j - coins[i - 1]];
				}
			}
		}
		/* Print Table */
		/*
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= sum; j++) {
				System.out.print(table[i][j] +" ");
			}
			System.out.println();
		}
		
		*/
	return table[n][sum];

	}
