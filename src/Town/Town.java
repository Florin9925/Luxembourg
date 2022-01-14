package Town;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Town {

    private List<NodeTown> nodeTowns;
    private List<List<NodeArc>> adj_list;

    private int maxLongitude;
    private int maxLatitude;

    public Town() {
        nodeTowns = new ArrayList<>();
        this.adj_list = new ArrayList<List<NodeArc>>();
        this.maxLatitude = 0;
        this.maxLongitude = 0;
    }

    public void initAdj_list() {
        for (int i = 0; i < this.nodeTowns.size(); i++) {
            List<NodeArc> item = new ArrayList<NodeArc>();
            this.adj_list.add(item);
        }

    }

    public void addNode(NodeTown nodeTown) {
        this.nodeTowns.add(nodeTown);
        if (nodeTown.getLatitude() > this.maxLatitude)
            this.maxLatitude = nodeTown.getLatitude();
        if (nodeTown.getLongitude() > this.maxLongitude)
            this.maxLongitude = nodeTown.getLongitude();
    }

    public void addArc(int from, int to, int length) {
        this.adj_list.get(from).add(new NodeArc(to, length));
    }

    private int max2(int aux) {
        int x = 1;
        do {
            x *= 2;
        } while (x < aux);
        return x;
    }

    public void drawLuxembourg(Graphics g, int start, int end, int[] path) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

//        for(NodeTown i : this.nodeTowns){
//            double x = (double) (i.getLongitude()) / max2(this.maxLongitude) * 10000 - 5400;
//            double y = (double) (i.getLatitude()) / max2(this.maxLatitude) * 130000 - 76500;
//            g.setColor(Color.BLACK);
//            g.fillOval((int)y, (int)x,2,2);
//            g.setColor(Color.BLACK);
//            g.drawOval((int)y, (int)x,2,2);
//
//        }

        for (int i = 0; i < this.adj_list.size(); ++i) {

            for (NodeArc j : this.adj_list.get(i)) {
                double x1 = (double) this.nodeTowns.get(i).getLongitude() / max2(this.maxLongitude) * 10000 - 5400;
                double y1 = (double) this.nodeTowns.get(i).getLatitude() / max2(this.maxLatitude) * 130000 - 76500;

                double x2 = (double) this.nodeTowns.get(j.node).getLongitude() / max2(this.maxLongitude) * 10000 - 5400;
                double y2 = (double) this.nodeTowns.get(j.node).getLatitude() / max2(this.maxLatitude) * 130000 - 76500;

                g2.setColor(Color.BLACK);

                g2.drawLine((int) y1, (int) x1, (int) y2, (int) x2);
            }
        }
        if (start != -1 && end != -1 && start != end)
            while (path[end] != start) {
                double x1 = (double) this.nodeTowns.get(path[end]).getLongitude() / max2(this.maxLongitude) * 10000 - 5400;
                double y1 = (double) this.nodeTowns.get(path[end]).getLatitude() / max2(this.maxLatitude) * 130000 - 76500;

                double x2 = (double) this.nodeTowns.get(end).getLongitude() / max2(this.maxLongitude) * 10000 - 5400;
                double y2 = (double) this.nodeTowns.get(end).getLatitude() / max2(this.maxLatitude) * 130000 - 76500;

                g2.setColor(Color.RED);
                g2.drawLine((int) y1, (int) x1, (int) y2, (int) x2);
                end = path[end];
            }
        System.out.println("Done draw!");
    }

    private double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public int getNearestArc(int x, int y) {
        double maxim = Double.MAX_VALUE;
        int node = -1;

        for (NodeTown i : this.nodeTowns) {
            double x1 = (double) i.getLongitude() / max2(this.maxLongitude) * 10000 - 5400;
            double y1 = (double) i.getLatitude() / max2(this.maxLatitude) * 130000 - 76500;
            if (distance(y, x, (int) x1, (int) y1) <= maxim) {
                node = i.getId();
                maxim = distance(y, x, (int) x1, (int) y1);
            }
        }
        return node;
    }


    public int[] dijkstra(int src1, int src2) {
        PriorityQueue<NodeArc> pq = new PriorityQueue<NodeArc>(this.nodeTowns.size(), new NodeArc());
        int dist[] = new int[this.nodeTowns.size()];

        for (int index = 0; index < this.nodeTowns.size(); ++index)
            dist[index] = Integer.MAX_VALUE;
        pq.add(new NodeArc(src1, 0));
        dist[src1] = 0;
        int[] previous = new int[this.nodeTowns.size()];
        for (int index = 0; index < this.nodeTowns.size(); ++index) {
            previous[index] = -1;
        }
        while (!pq.isEmpty()) {
            int u = pq.peek().node;
            pq.remove();
            if (u == src2) break;
            for (NodeArc x : this.adj_list.get(u)) {
                int v = x.node;
                int weight = x.cost;
                if (dist[v] > dist[u] + weight) {
                    previous[v] = u;
                    dist[v] = dist[u] + weight;
                    pq.add(new NodeArc(v, dist[v]));
                }
            }
        }
        return previous;
    }


    public int[] bellmanFord(int start, int end) {
        int[] dist = new int[this.nodeTowns.size()];
        for (int i = 0; i < this.nodeTowns.size(); ++i)
            dist[i] = Integer.MAX_VALUE;

        dist[start] = 0;
        int[] previous = new int[this.nodeTowns.size()];
        for (int index = 0; index < this.nodeTowns.size(); ++index) {
            previous[index] = -1;
        }
        for (int i = 0; i < this.nodeTowns.size(); ++i) {
            for (int j = 0; j < this.adj_list.size(); ++j) {
                for (int k = 0; k < this.adj_list.get(j).size(); ++k) {
                    int v = this.adj_list.get(j).get(k).node;
                    int w = this.adj_list.get(j).get(k).cost;
                    if (dist[j] != Integer.MAX_VALUE && dist[j] + w < dist[v]) {
                        dist[v] = dist[j] + w;
                        previous[v] = j;
                        if (v == end) i = this.nodeTowns.size();
                    }
                }
            }
        }
        return previous;
    }


}
