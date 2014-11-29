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
public class Carro {
    private String placa;
    private String nomeModelo;
    private int codModelo;
    private String nomeMarca;
    private int codMarca;
    private String cor;
    private String ano;
    private String observacoes;
    private int codDono;
    private String CPFDono;
    
    /**
     * @return the placa
     */
    public String getPlaca()
    {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa)
    {
        this.placa = placa.toUpperCase();
    }

    /**
     * @return the modelo
     */
    public String getNomeModelo()
    {
        return nomeModelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setNomeModelo(String modelo)
    {
        this.nomeModelo = modelo;
    }
    
    public int getCodModelo(){
        return this.codModelo;
    }
    
    public void setCodModelo(int modelo){
        this.codModelo = modelo;
    }
    
    public String getNomeMarca()
    {
        return nomeMarca;
    }

    /**
     * @param marca the modelo to set
     */
    public void setNomeMarca(String marca)
    {
        this.nomeMarca = marca;
    }
    
     public int getCodMarca(){
        return this.codMarca;
    }
    
    public void setCodMarca(int marca){
        this.codMarca = marca;
    }
    

    /**
     * @return the cor
     */
    public String getCor()
    {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(String cor)
    {
        this.cor = cor;
    }
    
    /**
     * @return the ano
     */
    public String getAno()
    {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(String ano)
    {
        this.ano = ano;
    }

    /**
     * @return the observacoes
     */
    public String getObservacoes()
    {
        return observacoes;
    }

    /**
     * @param observacoes the observacoes to set
     */
    public void setObservacoes(String observacoes)
    {
        this.observacoes = observacoes;
    }

    /**
     * @return the codDono
     */
    public int getCodDono()
    {
        return codDono;
    }

    /**
     * @param codDono the codDono to set
     */
    public void setCodDono(int codDono)
    {
        this.codDono = codDono;
    }
    
    public String getCPFDono(){
        return this.CPFDono;
    }
    
    public void setCPFDono(String cpf){
        this.CPFDono = cpf;
    }
}
