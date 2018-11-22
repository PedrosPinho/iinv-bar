/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classs;


public class Cardapio {

    private String id;
    private double preco;
    private String nome;
    private String descricao;
    private int quantidade;

public double getPreco() {
    return this.preco;
  }
    public void setPreco (double preco) {
    this.preco = preco;
    }
    public String getNome() {
    return this.nome;
    }
    public void setNome (String id) {
    this.nome = (String) id;
    }
    public String getDescricao() {
    	return this.descricao;
    }
    public void setDescricao (String descricao) {
    	this.descricao = descricao;
     }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
