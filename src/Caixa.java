public class Caixa{
    private int largura;
    private int altura;
    private int profundidade;

    public Caixa (int largura, int altura, int profundidade) {
        this.largura = largura;
        this.altura = altura;
        this.profundidade = profundidade;
    }

    public int getLargura() {
        return largura;
    }
    
    public int getAltura() {
        return altura;
    }

    public int getProfundidade() {
        return profundidade;
    }

    @Override
    public String toString() {
        return "Largura: " + largura + " Altura: " + altura + " Profundidade: " + profundidade;
    }
}
