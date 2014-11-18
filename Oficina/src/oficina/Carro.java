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
public class Carro {
    private String placa;
    private String marca;
    private String modelo;
    private String cor;
    private String ano;
    private String observacoes;
    private String codDono;
    
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
     * @return the marca
     */
    public String getMarca()
    {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca)
    {
        this.marca = marca;
    }

    /**
     * @return the modelo
     */
    public String getModelo()
    {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo)
    {
        this.modelo = modelo;
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
    public String getCodDono()
    {
        return codDono;
    }

    /**
     * @param codDono the codDono to set
     */
    public void setCodDono(String codDono)
    {
        this.codDono = codDono;
    }
}
