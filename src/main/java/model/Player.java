package model;

public class Player {

    private String nome;
    private int posicao;
    private int pontos;

    public Player(String nome){
        this.nome = nome;
        this.posicao = 0;
        this.pontos = 0;
    }

    public void mover(int casas){
        posicao += casas;
        if(posicao > 49) posicao = 49;
    }

    public void voltar(int casas){
        posicao -= casas;
        if(posicao < 0) posicao = 0;
    }

    public void addPontos(int p){
        pontos += p;
    }

    public int getPosicao(){ return posicao; }
    public int getPontos(){ return pontos; }
    public String getNome(){ return nome; }

}