long binarySearch(int A[], int n, int key){
    int m = 0;
    int l = 1; int r = n;
    while(l <= r){
      m = l + (r-l)/2;
      if(A[m] == key){ // first comparison
        return m;
      }else if( A[m] < key ){ // second comparison
        l = m + 1;
      }else{
        r = m - 1;
      }    
    }
    return -1;
  }
  
