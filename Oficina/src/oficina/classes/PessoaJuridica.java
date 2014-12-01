/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oficina.classes;

/**
 *
 * @author carolinapascalecampos
 */
public class PessoaJuridica extends Cliente {
    String CNPJ;
    String nomeFantasia;
    String telefoneComercial;
    String FAX;

    public PessoaJuridica(String nomeFantasia, String CNPJ, String telefone, String FAX,
            String rua, String endNumero, String CEP, String bairro, String cidade,
           String estado,String email, String observacoes){
        
        super(rua, endNumero, CEP,  bairro,cidade,estado,email,  observacoes);
        
        this.setCNPJ(CNPJ);
        this.setNomeFantasia(nomeFantasia);
        this.setTelefoneComercial(telefone);
        this.setFAX(FAX);
    }
    public PessoaJuridica(){
        
    }
    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getTelefoneComercial() {
        return telefoneComercial;
    }

    public void setTelefoneComercial(String telefoneComercial) {
        this.telefoneComercial = telefoneComercial;
    }

    public String getFAX() {
        return FAX;
    }

    public void setFAX(String FAX) {
        this.FAX = FAX;
    }
    
}
