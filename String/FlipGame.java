package String;
import java.util.*;

/**
 * Created by siqi on 9/16/17.
 */
public class FlipGame {
  public static List<String> generatePossibleNextMoves(String s) {
    List<String> res = new ArrayList<>();
    for (int i = 0; i + 1 < s.length(); i++) {
      if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
        res.add(s.substring(0, i) + "--" + s.substring(i + 2));
      }
    }
    return res;
  }

  public boolean canWin(String s) {
    if (s == null || s.length() < 2) return false;
    Map<String, Boolean> map = new HashMap<>();
    return canWin(s, map);
  }

  private boolean canWin(String s, Map<String, Boolean> map) {
    if (map.containsKey(s)) return map.get(s);
    // boolean win = false;
    for (int i = 0; i + 1 < s.length(); i++) {
      if (s.startsWith("++", i)) {
        boolean win = canWin(s.substring(0, i) + "--" + s.substring(i + 2), map);
        if (!win) {
          map.put(s, true);
          return true;
        }
      }
    }
    map.put(s, false);
    return false;
  }
}
