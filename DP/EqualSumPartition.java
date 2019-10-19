
	public static int partition(int set[]) {
		int n = set.length;
		int sum = 0;
		for(int i = 0; i < set.length; i++)
			sum+=set[i];

		boolean subset[][] = new boolean[n + 1][sum + 1];
		for(int i = 0; i <= n; i++)
			subset[i][0] = true;

		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= sum; j++) {
				if(j < set[i - 1]){
					subset[i][j] = subset[i - 1][j];
				} else {
					subset[i][j] = subset[i -1][j] || subset[i - 1][j - set[i - 1]];
				}
			}
		}

		int difference = Integer.MAX_VALUE;

		for(int j = sum / 2; j >= 0; j--) {
			if(subset[n][j]) {
				difference = sum - 2 * j;
				break;
			}
		}

		return difference;

	}
