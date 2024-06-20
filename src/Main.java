import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
public class Main {
    public static void main(String[] args) {
        LinkedList <Caixa> caixas = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/CasosTeste/caso00010.txt"))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split(" ");
                int val1 = Integer.parseInt(valores[0]);
                int val2 = Integer.parseInt(valores[1]);
                int val3 = Integer.parseInt(valores[2]);
                int largura, altura, profundidade;
                if(val1>val2 && val1>val3) {
                    largura = val1;
                   if(val2>val3) {
                       altura = val2;
                        profundidade = val3;
                   } else {
                        altura = val3;
                        profundidade = val2;
                   }
               } else if(val2>val1 && val2>val3) {
                    largura = val2;
                   if(val1>val3) {
                       altura = val1;
                       profundidade = val3;
                   } else {
                       altura = val3;
                       profundidade = val1;
                   }
               } else {
                   largura = val3;
                   if(val1>val2) {
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
        
        System.out.println(diagraph.toDot());

        System.out.println("Caixa com mais filhos "+ diagraph.getCaixaWithMoreVerts());
    }
}