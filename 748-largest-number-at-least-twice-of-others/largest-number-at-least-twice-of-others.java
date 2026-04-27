class Solution {
    public int dominantIndex(int[] nums) {
    //     int n = nums.length;

    //     for (int i = 0; i < n; i++) {

    //         boolean isValid = true;

    //         for (int j = 0; j < n; j++) {

    //             if (i == j) continue;

    //             if (nums[i] < 2 * nums[j]) {
    //                 isValid = false;
    //                 break;
    //             }
    //         }

    //         if (isValid) {
    //             return i;
    //         }
    //     }

    //     return -1;
    // }



             int idx = -1;
        int max1 = -1;
        int max2 = -1;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] > max1) {
                max2 = max1;
                max1 = nums[i];
                idx = i;

            } else if (nums[i] > max2) {
                max2 = nums[i];
            }
        }

        return (max1 >= 2 * max2) ? idx : -1;
    }
}