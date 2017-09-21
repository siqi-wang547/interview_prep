package facebook.easy;

public class LC461_HammingDistance {
    public int hammingDistance(int x, int y) {
        int dis = 0, xor = x ^ y;
        for (int i = 0; i < 32; i++) dis += ((xor >> i) & 1);
        return dis;
    }
}
