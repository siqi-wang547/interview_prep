package greedy;

public class JumpGame {


    /**
     * LC55. Jump Game
     * @idea from right to left, find out if the right index can be reached, update the last reachable index
     *       if in the end the leftmost reachable idx <= 0 then true
     * @param nums input
     * @return can reach the last idx, true; otherwise false
     */
    public boolean canJump(int[] nums) {
//        return jump(nums, 0); // DFS from left to right StackOverFlow !!!

        int last=nums.length - 1;
        for(int i = nums.length - 2; i >= 0; i--){
            if(i + nums[i] >= last) last = i;
        }
        return last <= 0;
    }

//    private boolean jump(int[] nums, int start) {
//        if (start >= nums.length) return true;
//        for (int i = nums[start]; i > 0; i--) {
//            return jump(nums, start + i);
//        }
//        return false;
//    }

    /**
     * LC45. Jump Game II
     * @idea keep a
     * @param nums input
     * @return the number of jumps to make it to the last index
     */
    public int jump(int[] nums) {
        int curEnd = 0, jumps = 0, curFarthest = 0;
        for (int i = 0; i < nums.length; i++) {
            curFarthest = Math.max(i + nums[i], curFarthest);
            if (curFarthest >= nums.length - 1) return jumps + 1;
            if (i == curEnd) {
                jumps++;
                curEnd = curFarthest;
            }
        }

        

        return jumps;
    }
}
