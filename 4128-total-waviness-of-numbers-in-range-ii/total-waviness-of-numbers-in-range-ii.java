class Solution {

    static class Pair {
        long ways;
        long waviness;

        Pair(long w, long v) {
            ways = w;
            waviness = v;
        }
    }

    String s;
    Pair[][][][] memo;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long x) {
        if (x < 0) return 0;

        s = String.valueOf(x);

        memo = new Pair[s.length()][11][11][2];

        return dfs(0, 10, 10, 0, true).waviness;
    }

    private Pair dfs(
            int pos,
            int prev1,
            int prev2,
            int started,
            boolean tight) {

        if (pos == s.length()) {
            return new Pair(started == 1 ? 1 : 0, 0);
        }

        if (!tight &&
                memo[pos][prev1][prev2][started] != null) {
            return memo[pos][prev1][prev2][started];
        }

        int limit = tight
                ? s.charAt(pos) - '0'
                : 9;

        long totalWays = 0;
        long totalWave = 0;

        for (int d = 0; d <= limit; d++) {

            boolean nt = tight && (d == limit);

            if (started == 0 && d == 0) {

                Pair child =
                        dfs(pos + 1,
                                10,
                                10,
                                0,
                                nt);

                totalWays += child.ways;
                totalWave += child.waviness;
            }
            else {

                int add = 0;

                if (prev2 != 10) {

                    if ((prev1 > prev2 && prev1 > d)
                            ||
                            (prev1 < prev2 && prev1 < d)) {
                        add = 1;
                    }
                }

                int nPrev2 =
                        (prev1 == 10) ? 10 : prev1;

                Pair child =
                        dfs(pos + 1,
                                d,
                                nPrev2,
                                1,
                                nt);

                totalWays += child.ways;

                totalWave += child.waviness
                        + (long) add * child.ways;
            }
        }

        Pair ans =
                new Pair(totalWays, totalWave);

        if (!tight) {
            memo[pos][prev1][prev2][started] = ans;
        }

        return ans;
    }
}