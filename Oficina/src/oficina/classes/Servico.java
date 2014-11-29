/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oficina.classes;

/**
 *
 * @author Administrator
 */
public class Servico
{
    private String nomeServico;
    private String preco;
    private String nomeMecanico;
    
    public Servico(String nomeServico, String preco, String nomeMecanico)
    {
        this.nomeServico = nomeServico;
        this.preco = preco;
        this.nomeMecanico = nomeMecanico;
    }
    
    @Override
    public String toString() // para lista do formOs
    {
        return "R$ " + this.preco + ",00" + " | " + this.nomeServico;
    }

    /**
     * @return the nomeServico
     */
    public String getNomeServico()
    {
        return nomeServico;
    }

    /**
     * @param nomeServico the nomeServico to set
     */
    public void setNomeServico(String nomeServico)
    {
        this.nomeServico = nomeServico;
    }

    /**
     * @return the preco
     */
    public String getPreco()
    {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(String preco)
    {
        this.preco = preco;
    }

    /**
     * @return the nomeMecanico
     */
    public String getNomeMecanico()
    {
        return nomeMecanico;
    }

    /**
     * @param nomeMecanico the nomeMecanico to set
     */
    public void setNomeMecanico(String nomeMecanico)
    {
        this.nomeMecanico = nomeMecanico;
    }
}
