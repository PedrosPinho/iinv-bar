/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inv;

/**
 *
 *  
 */
public class Funcionario {
       public String nome;
    private String cpf, telefone,funcao,registro;
 
    
    public String getNome() {
    return this.nome;
  }
    public void setNome (String nome) {
    this.nome = nome;
    }
    public String getCpf() {
    return this.cpf;
    }
    public void setCpf (String Cpf) {
    this.cpf = Cpf;
    }
    public String getTelefone() {
    return this.telefone;
    }
    public void setTelefone (String Telefone) {
    this.telefone = Telefone;
     }
    public String getFuncao() {
    return this.funcao;
    }
    public void setFuncao (String funcao) {
    this.funcao = funcao;
      }
    public String getRegistro() {
    return this.registro;
    }
   
    public void setRegistro (String registro) {
    this.funcao = funcao;
      }
      }
