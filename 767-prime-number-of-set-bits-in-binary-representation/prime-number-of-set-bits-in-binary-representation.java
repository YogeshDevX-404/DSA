class Solution {
     private boolean isPrime(int x) {
        if (x < 2)
            return false;
        for (int d = 2; d * d <= x; d++){
            if (x % d == 0)
                return false;
        }
        return true;
    }
    public int countPrimeSetBits(int left, int right){
        int res = 0;
        for (int i = left; i <= right; i++){
            int bits = Integer.bitCount(i);
            if (isPrime(bits))
                res++;
        }
        return res;
    }
}