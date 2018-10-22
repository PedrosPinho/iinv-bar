/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inv;

/**
 *
 * @ 
 */
public class Cliente {
    public String nome;
    private String Cpf, telefone;
    private int frequencia;
    
     public String getNome() {
    return this.nome;
  }
    public void setNome (String nome) {
    this.nome = nome;
    }
    public String getCpf() {
    return this.Cpf;
    }
    public void setCpf (String Cpf) {
    this.Cpf = Cpf;
    }
    public String getTelefone() {
    return this.telefone;
    }
    public void setTelefone (String Telefone) {
    this.telefone = Telefone;
     }
    public int getFrequencia() {
    return this.frequencia;
    }
   public void setFrequencia (int Frequencia) {
    this.frequencia = Frequencia;
      }
}
    
