import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Digraph {
  protected static final String NEWLINE = System.getProperty("line.separator");
  protected Map<Caixa, List<Caixa>> graph;
  protected Set<Caixa> vertices;
  protected int totalVertices;
  protected int totalEdges;

  public Digraph(ArrayList<Caixa> caixas) {
    graph = new HashMap<>();
    vertices = new HashSet<>();
    totalVertices = totalEdges = 0;

    for (int i = 0; i < caixas.size(); i++) {
      for (int j = 0; j < caixas.size(); j++) {
        if (i != j) {
          Caixa caixa1 = (Caixa) caixas.get(i);
          Caixa caixa2 = (Caixa) caixas.get(j);
          if (caixa1.getLargura() > caixa2.getLargura() && caixa1.getAltura() > caixa2.getAltura()
              && caixa1.getProfundidade() > caixa2.getProfundidade()) {
            addEdge(caixa1, caixa2);
          }
        }
      }
    }
  }

  protected void addEdge(Caixa v, Caixa w) {
    addToList(v, w);
    if (!vertices.contains(v)) {
      vertices.add(v);
      totalVertices++;
    }
    if (!vertices.contains(w)) {
      vertices.add(w);
      totalVertices++;
    }
  }

  protected List<Caixa> addToList(Caixa v, Caixa w) {
    List<Caixa> list = graph.get(v);
    if (list == null)
      list = new ArrayList<Caixa>();
    list.add(w);
    graph.put(v, list);
    totalEdges++;
    return list;
  }

  public Set<Caixa> getVerts() {
    return vertices;
  }

  public Map<Caixa, List<Caixa>> getGraph() {
    return graph;
  }

  public Iterable<Caixa> getAdj(Caixa v) {
    List<Caixa> res = graph.get(v);
    if (res == null)
      res = new ArrayList<>();
    return res;
  }

  public String toDot() {
    StringBuilder sb = new StringBuilder();
    sb.append("digraph {" + NEWLINE);
    sb.append("rankdir = LR;" + NEWLINE);
    sb.append("node [shape = circle];" + NEWLINE);
    for (Caixa v : getVerts().stream().sorted().toList())
      for (Caixa w : getAdj(v))
        sb.append("\"" + v + "\"" + " -> " + "\"" + w + "\"" + NEWLINE);
    sb.append("}" + NEWLINE);
    return sb.toString();
  }

}
