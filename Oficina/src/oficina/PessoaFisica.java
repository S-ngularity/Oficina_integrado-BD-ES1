/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oficina;

/**
 *
 * @author carolinapascalecampos
 */
public class PessoaFisica extends Cliente {
    String nome;
    int RG;
    String CPF;
    String numCelular;
    String numResidencial;
    
    PessoaFisica(String nome, int RG, String CPF,String rua, String endNumero, String CEP, String bairro, String cidade,
           String estado,String email, String observacoes,String num_residencial, String num_celular){
        super(rua, endNumero, CEP,  bairro,cidade,estado,email,  observacoes);
        this.setNome(nome);
        this.setRG(RG);
        this.setCPF(CPF);
        this.setNumCelular(num_celular);
        this.setNumResidencial(num_residencial);
        
        
      
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getRG() {
        return RG;
    }

    public void setRG(int RG) {
        this.RG = RG;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNumCelular() {
        return numCelular;
    }

    public void setNumCelular(String num_celular) {
        this.numCelular = num_celular;
    }

    public String getNumResidencial() {
        return numResidencial;
    }

    public void setNumResidencial(String num_residencial) {
        this.numResidencial = num_residencial;
    }
    
    
   
}
