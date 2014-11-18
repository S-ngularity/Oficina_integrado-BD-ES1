/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oficina;

/**
 *
 * @author filiperocchi
 */
public class Funcionario {
    private String codFuncionario;
    private String nome;
    private String senha;
    private int permissao;

    /**
     * @return the codFuncionario
     */
    public String getCodFuncionario()
    {
        return codFuncionario;
    }

    /**
     * @param codFuncionario the codFuncionario to set
     */
    public void setCodFuncionario(String codFuncionario)
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
    public int getPermissao()
    {
        return permissao;
    }

    /**
     * @param permissao the permissao to set
     */
    public void setPermissao(int permissao)
    {
        this.permissao = permissao;
    }
}
