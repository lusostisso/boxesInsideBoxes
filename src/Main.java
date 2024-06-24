import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<Caixa, Integer> longestPathCache = new HashMap<>();
    private static int longestPathLength = 0;

    public static void main(String[] args) {
        ArrayList<Caixa> caixas = new ArrayList<>();
        long startTime = System.nanoTime();
        try (BufferedReader br = new BufferedReader(new FileReader("src/CasosTeste/caso10000.txt"))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split(" ");
                int val1 = Integer.parseInt(valores[0]);
                int val2 = Integer.parseInt(valores[1]);
                int val3 = Integer.parseInt(valores[2]);
                int largura, altura, profundidade;
                if (val1 > val2 && val1 > val3) {
                    largura = val1;
                    if (val2 > val3) {
                        altura = val2;
                        profundidade = val3;
                    } else {
                        altura = val3;
                        profundidade = val2;
                    }
                } else if (val2 > val1 && val2 > val3) {
                    largura = val2;
                    if (val1 > val3) {
                        altura = val1;
                        profundidade = val3;
                    } else {
                        altura = val3;
                        profundidade = val1;
                    }
                } else {
                    largura = val3;
                    if (val1 > val2) {
                        altura = val1;
                        profundidade = val2;
                    } else {
                        altura = val2;
                        profundidade = val1;
                    }
                }
                caixas.add(new Caixa(largura, altura, profundidade));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Digraph diagraph = new Digraph(caixas);

        // System.out.println(diagraph.toDot());

        for (Caixa caixa : diagraph.getGraph().keySet()) {
            int currentPathLength = findLongestPath(diagraph, caixa);
            longestPathLength = Math.max(longestPathLength, currentPathLength);
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        double seconds = duration / 1.0E9;
        System.out.println("Tempo de execução: " + seconds + " segundos");
        System.out.println("O maior caminho possível é: " + longestPathLength);
    }

    private static int findLongestPath(Digraph diagraph, Caixa caixa) {
        if (longestPathCache.containsKey(caixa)) {
            return longestPathCache.get(caixa);
        }
        int maxLength = 0;
        for (Caixa adjCaixa : diagraph.getAdj(caixa)) {
            int pathLength = findLongestPath(diagraph, adjCaixa);
            maxLength = Math.max(maxLength, pathLength);
        }
        longestPathCache.put(caixa, 1 + maxLength);
        return 1 + maxLength;
    }
}