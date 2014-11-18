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
public class Cliente {
    
    private int codCliente;
    private String rua;
    private String bairro;
    private String cidade;
    private String CEP;
    private String estado;
    private String endNumero; //numero do endereco
    private String email;
    private String observacoes;
    
   Cliente(String rua, String endNumero, String CEP, String bairro, String cidade,
           String estado,String email, String observacoes){
       this.setRua(rua);
       this.setEndNumero(endNumero);
       this.setCEP(CEP);
       this.setBairro(bairro);
       this.setCidade(cidade);
       this.setEstado(estado);
       this.setEmail(email);
       this.setObservacoes(observacoes);
   }
    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
    public String getCidade(){
        return this.cidade;
    }
    
    public void setCidade(String cidade){
        this.cidade = cidade;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getEndNumero() {
        return endNumero;
    }

    public void setEndNumero(String endNumero) {
        this.endNumero = endNumero;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    
    public String getEstado(){
        return this.estado;
    }
    public void setEstado(String estado){
        this.estado = estado;
    }
    
    
}
