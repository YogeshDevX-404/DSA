class Solution {
    public int thirdMax(int[] nums) {
        Long first = Long.MIN_VALUE;
        Long second = Long.MIN_VALUE;
        Long third = Long.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            long num = nums[i];

            if (num == first || num == second || num == third) {
                continue;
            }

            if (num > first) {
                third = second;
                second = first;
                first = num;

            } else if (num > second) {
                third = second;
                second = num;

            } else if (num > third) {
                third = num;
            }
        }

        if (third == Long.MIN_VALUE) {
            return first.intValue();
        }

        return third.intValue();
    }
}