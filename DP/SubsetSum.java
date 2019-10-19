 static boolean subsetSum(int[] arr, int sum){
        boolean[][] dp= new boolean[arr.length+1][sum+1];
        int n = arr.length;
        for(int i=0;i<=n;i++)dp[i][0]=false;
        for(int i=0;i<=sum;i++)dp[0][1]=false;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=sum;j++){
                if(arr[i-1]<=j){
                    dp[i][j]=(arr[i-1]==j||(dp[i-1][j-arr[i-1]])||dp[i-1][j]);
                }else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        if(dp[n][sum]){
            return true;
        } else{
            return false;
        }
    }
