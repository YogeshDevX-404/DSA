class Solution {
    public boolean hasAllCodes(String s, int k) {
        int n = 1 << k;
        boolean[] freq = new boolean[n];

        int l = 0, r = 0;

        while (r < s.length()) {
            if (r - l + 1 > k) l++;

            if (r - l + 1 == k) {
                String temp = s.substring(l, l + k);
                int num = Integer.parseInt(temp, 2);
                freq[num] = true;
            }
            r++;
        }

        for (int i = 0; i < n; i++) {
            if (!freq[i]) return false;
        }
        return true;
    }
}