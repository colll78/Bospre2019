public static int findLongestFromCell(int i, int j, int a[][], int dp[][]) {
		
		//Base Case
		if(i < 0 || i >= n || j < 0 || j >= n)
			return 0;

		//Subproblem already solved
		if(dp[i][j]!= -1)
			return dp[i][j];

		if(j < n - 1 && ((a[i][j] + 1 ) == a[i][j + 1]))
			return dp[i][j] = 1 + findLongestFromCell(i, j + 1, a, dp);

		if(j > 0 && ((a[i][j] + 1 ) == a[i][j - 1]))
			return dp[i][j] = 1 + findLongestFromCell(i, j - 1, a, dp);

		if(i > 0 && ((a[i][j] + 1 ) == a[i - 1][j]))
			return dp[i][j] = 1 + findLongestFromCell(i - 1, j, a, dp);

		if(i < n - 1 && ((a[i][j] + 1 ) == a[i + 1][j]))
			return dp[i][j] = 1 + findLongestFromCell(i + 1, j, a, dp);

		return dp[i][j] = 1;

	}

	public static int longestPathMatrix(int a[][]) {
		int result = 1;
		int dp[][] = new int[n][n];
		
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				dp[i][j] = -1;

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(dp[i][j] == -1) {
					findLongestFromCell(i, j, a, dp);
				}
				result = Math.max(result, dp[i][j]);
			}
		}

		return result;
	}
