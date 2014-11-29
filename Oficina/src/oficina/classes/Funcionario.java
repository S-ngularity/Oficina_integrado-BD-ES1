/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oficina.classes;

/**
 *
 * @author filiperocchi
 */
public class Funcionario {
    private int codFuncionario;
    private String nome;
    private String senha;
    private boolean atendente ;

    /**
     * @return the codFuncionario
     */
    public int getCodFuncionario()
    {
        return codFuncionario;
    }

    /**
     * @param codFuncionario the codFuncionario to set
     */
    public void setCodFuncionario(int codFuncionario)
    {
        this.codFuncionario = codFuncionario;
    }

    /**
     * @return the nome
     */
    public String getNome()
    {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome)
    {
        this.nome = nome;
    }

    /**
     * @return the senha
     */
    public String getSenha()
    {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    /**
     * @return the permissao
     */
    public boolean getAtendente()
    {
        return atendente;
    }

    /**
     * @param permissao the permissao to set
     */
    public void setAtendente(boolean permissao)
    {
        this.atendente = permissao;
    }
}
