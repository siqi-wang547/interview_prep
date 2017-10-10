package topologicalsort;
import java.util.*;
public class KillProcess {
    public static List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < ppid.size(); i++) {
            int parent = ppid.get(i), child = pid.get(i);
            if (parent != 0) {
                if (!map.containsKey(parent)) map.put(parent, new LinkedList<>());
                map.get(parent).add(child);
            }
        }
//        System.out.println(map.toString());
        List<Integer> res = new ArrayList<>();
        q.offer(kill);
//        System.out.println("finding kill process" + q.toString());
        while (!q.isEmpty()) {
            int pp = q.poll();
            res.add(pp);
            if (!map.containsKey(pp)) continue;
            for (int child: map.get(pp)) {
                q.offer(child);
            }
        }
        return res;
    }

    public static void main(String[] args) {

        System.out.println(killProcess(Arrays.asList(1,3,10,5), Arrays.asList(3,0,5,3), 5));
    }
}
