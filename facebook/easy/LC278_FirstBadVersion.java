package facebook.easy;

class VersionControl {
    boolean isBadVersion(int v) {return false;}
}

/**
 * decrease the range where the first bad version could occur, make sure the first bad version is in our interval
 * if mid is bad version, move right bound to mid, because mid could be a bad version
 * if mid is not a bad version, move left bound to mid + 1
 */
public class LC278_FirstBadVersion extends VersionControl {
    public int firstBadVersion(int n) {
        int l = 1, r = n;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (isBadVersion(mid)) r = mid;
            else l = mid + 1;
        }
        return r;
    }
}
