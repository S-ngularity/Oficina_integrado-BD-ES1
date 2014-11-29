/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oficina.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.HashSet;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import oficina.classes.Carro;
import oficina.classes.Cliente;
import oficina.classes.Funcionario;
import oficina.classes.OrdemDeServico;
import oficina.classes.PessoaFisica;
import oficina.classes.PessoaJuridica;

/**
 *
 * @author Rich
 */
public class ConexaoBd {
     private Connection myConnection;

    //Objeto usado para enviar comandos SQL no SGBD
    private Statement st;

    public ConexaoBd(){
        String user = "postgres";
        String password = "manezao29";
        try{
            Class.forName("org.postgresql.Driver").newInstance();
            myConnection = DriverManager.getConnection("jdbc:postgresql:"+"//localhost/Oficina?user=" + 
                    user + "&password=" + password);
            st = myConnection.createStatement();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
    
    //==========================LOGIN===========================================
    
    public boolean autenticaFuncionario(Funcionario f){
        //Funcionario func = f;
        ResultSet busca;
        String selectFuncionario = "SELECT codFuncionario FROM Funcionario WHERE nomeFuncionario='"
                + f.getNome()+"' AND senha='"+f.getSenha()+"' AND atendente = TRUE;";     
         try{
           st.execute(selectFuncionario);
           busca =  st.getResultSet();
           
           if(!busca.next())
               return false;
           else
           {              
               f.setCodFuncionario(busca.getInt(1));
               f.setAtendente(true);
               f.setSenha(null);
              
                return true;
           }

       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar selecao em autenticaFuncionario: "+e.getMessage());
       }
         
        return false;
    }
    
    //======================CARRO=========================================================
    // to do: resolver codDono x cpf/cnpj - como conseguir codDono?
    
    public void cadastraCarro(Carro carro, Funcionario func){
        String  insertCarro = "INSERT INTO Carro(codCadastrante,placaCarro, codModelo, cor,"
                  + "ano, observacoesCarro, codDono) VALUES(";

        insertCarro += func.getCodFuncionario() + ","; 
        insertCarro += "'" + carro.getPlaca() +"',";
        insertCarro += carro.getCodModelo() + ",";
        insertCarro += "'" + carro.getCor() + "',";
        insertCarro += carro.getAno() + ",";
        insertCarro += "'" + carro.getObservacoes() + "',";
        insertCarro +=  carro.getCodDono() + ");";

        try{
             st.execute(insertCarro);

         }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Erro ao executar insercao em Carro: "+e.getMessage());
         }
       }
    
    public Carro buscaCarro(String placa){
        String selectCarro = "SELECT * FROM Carro where placaCarro='"+placa+"';";
        ResultSet rs;
        
        try{
           st.execute(selectCarro);
           rs =  st.getResultSet();
           
           if(!rs.next())
               return null;
           else
           {
               Carro carro = new Carro();
               carro.setPlaca(rs.getString(1));
               carro.setCodModelo(rs.getInt(2));
               carro.setCor(rs.getString(3));
               carro.setAno(rs.getString(4));
               carro.setObservacoes(rs.getString(5));
               carro.setCodDono(rs.getInt(6));
               return carro;
           }
        
      }
      catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar selecao em buscaCarro: "+e.getMessage());
       }
      
      return null;
    }
    
    public void preencheCbMarca(JComboBox marca){
        String selectMarca = "SELECT nomeMarca FROM MarcaCarro;";
        ResultSet busca;
        
        try{
           st.execute(selectMarca);
           busca =  st.getResultSet();
           
           while(busca.next())
           {
               marca.addItem(busca.getString(1));
           }
      }
      catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar selecao em preencheMarca: "+e.getMessage());
       }
       
        
    }
    
    public void preencheCbModelo(JComboBox modelo, String marca){
        String selectModelo = "SELECT nomeModelo FROM ModeloCarro AS modelo, MarcaCarro AS marca"
                + " WHERE marca.codMarca= modelo.codMarca AND marca.nomeMarca = '"+marca+"';";
        ResultSet busca;
        
        modelo.removeAllItems();
        
        try{
           st.execute(selectModelo);
           busca =  st.getResultSet();
           
           while(busca.next())
           {
               modelo.addItem(busca.getString(1));
           }
      }
      catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar selecao em preencheCbModelo: "+e.getMessage());
       }
    }
    
    
    public boolean buscaCodModelo(String nomeModelo, Carro carro){
        String selectModelo = "Select codModelo FROM ModeloCarro where nomeModelo='" + nomeModelo +"';";
        ResultSet busca;
        
        
         try{
           st.execute(selectModelo);
           busca =  st.getResultSet();
           
           if(!busca.next())
               return false;
           else
             carro.setCodModelo(busca.getInt(1));
           return true;
               
           
        
      }
      catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar selecao de codigo em modeloCarro: "+e.getMessage());
       }
       return false;
        
    }
    
     //=======================ORDEM DE SERVICO ============================================

    public void cadastraOS(OrdemDeServico os, Funcionario funcionario){
       String insertOS = "INSERT INTO OrdemDeServico(placaCarroOS, codClienteOS, codFuncionarioOS,"
               + "codEstadoOS,kmEntradaOS,kmSaidaOS) VALUES(";
       DecimalFormat df = new DecimalFormat("0.##");
       
       insertOS += "'" + os.getPlacaCarro() +"',";
       insertOS += os.getCodCliente() + ",";
       insertOS += "'" + funcionario.getCodFuncionario() + "',";
       insertOS += os.getEstado() +",";
       insertOS += os.getKmEntrada()+",";
       insertOS += os.getKmEntrada()+");";//+",";
       //insertOS += os.getValorTotal() +");";

        try{
           st.execute(insertOS);

       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar insercao em OS: "+e.getMessage());
       }
       
    }
    
    //==========================CLIENTE=========================================
    
    private int cadastraCliente(Cliente cliente,Funcionario funcionario, boolean fisica){
        ResultSet keyset;

        String insertCliente = "INSERT INTO Cliente(codCadastrante,pessoaFisica, enderecoCliente,"
                + "emailCliente, observacoesCliente) VALUES(";
        
        insertCliente +=funcionario.getCodFuncionario()+ ",";
        if(fisica)
            insertCliente += "TRUE,";
        else
            insertCliente += "FALSE,";        
        
        insertCliente += "'" + cliente.getRua() + " ";
        insertCliente += cliente.getEndNumero() + " - ";
        insertCliente +=  cliente.getBairro() + " - ";
        insertCliente +=  cliente.getCidade() + " - ";
        insertCliente += cliente.getEstado() + " - ";
        insertCliente +=  cliente.getCEP()+ "',";
        insertCliente += "'" + cliente.getEmail()+"',";
        insertCliente += "'" + cliente.getObservacoes() + "');";
        
        try{
           st.execute(insertCliente, Statement.RETURN_GENERATED_KEYS);

       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar insercao em cadastraCliente: "+e.getMessage());
       }
        
       try{
           keyset =  st.getGeneratedKeys();
           keyset.next();
           return keyset.getInt(1);
        }
           catch(SQLException e){
           JOptionPane.showMessageDialog(null,"Erro ao pegar key set em cadastraCliente:"+ e.getMessage());
       }
    
       return -1;
    }
    
    
    public int buscaCodCliente(boolean pessoaFisica,String identificacao){
        String selectCliente;
         ResultSet busca;
        
        
        if(pessoaFisica)
        {
            selectCliente = "SELECT codCliente FROM PessoaFisica WHERE CPF='"+identificacao+"';";
        }
        else
        {
            selectCliente = "SELECT codCliente FROM PessoaJuridica WHERE CNPJ='"+identificacao+"';";
        }    
            
            
        try{
           st.execute(selectCliente);
           busca =  st.getResultSet();
           
           if(busca.next())
           {
               return busca.getInt(1);
           }
         
      }
      catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar selecao em preencheMarca: "+e.getMessage());
       }
            
        return -1;
    }
    
    public String buscaNomeCliente(boolean PessoaFisica,String codCliente){
        String selectCliente;
        ResultSet busca;
        
        if(PessoaFisica)
            selectCliente = "SELECT nome * FROM PessoaFisisca where codCliente="+codCliente+";";
        else
            selectCliente = "SELECT nomeFantasia * FROM PessoaJuridica where codCliente="+codCliente+";";
        
        try{
           st.execute(selectCliente);
           busca =  st.getResultSet();
           
           if(busca.next())
           {
               return busca.getString(1);
           }
         
      }
      catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar selecao em buscaNomeCliente: "+e.getMessage());
       }
            
        return null;
        
    }
    
    //========================PESSOA FISICA ===========================================
    public void CadastrarPessoaFisica(PessoaFisica cliente, Funcionario funcionario){
       
       String insertPessoaFisica = "INSERT INTO PessoaFisica(codCliente, rg,cpf,nome,"
                + "telefoneResidencial,celular) VALUES(";
       //verificar insercao depois
       insertPessoaFisica += cadastraCliente(cliente,funcionario, true) + ",";
       
       insertPessoaFisica += "'" + cliente.getRG() + "',";
       insertPessoaFisica += "'" + cliente.getCPF() + "',";
       insertPessoaFisica += "'" + cliente.getNome() + "',";
       insertPessoaFisica += "'" + cliente.getNumResidencial()+ "',";
       insertPessoaFisica += "'" + cliente.getNumCelular() + "');";
       
        try{
           st.execute(insertPessoaFisica);

       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar insercao em pessoa Fisica: "+e.getMessage());
       }
       
       
    }
    
    //================================PESSOA JURIDICA==============================================
    
    public void cadastraPessoaJuridica(PessoaJuridica cliente, Funcionario funcionario){
        String insertPessoaJuridica = "INSERT INTO PessoaJuridica(codCliente, CNPJ, nomeFantasia,"
                + "telefoneComercial,fax) VALUES(";
        
        //Verificar insercao depois
        insertPessoaJuridica += cadastraCliente(cliente,funcionario, false) + ",";
        insertPessoaJuridica += "'"+cliente.getCNPJ() + "',";
        insertPessoaJuridica += "'" + cliente.getNomeFantasia() + "',";
        insertPessoaJuridica += "'" + cliente.getTelefoneComercial() + "',";
        insertPessoaJuridica += "'" + cliente.getFAX()+ "');";
        
        try{
           st.execute(insertPessoaJuridica);

       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar insercao em pessoa Juridica: "+e.getMessage());
       }
        
    }
        
    //============================== BUSCAS ====================================================================
    //==============================        ===================================================================
        
    public int buscaMarca(int codModelo){
        String selectMarca = "SELECT codMarca FROM Modelo where codModelo="+codModelo+";";
        ResultSet busca;
        
        try{
           st.execute(selectMarca);
           busca =  st.getResultSet();
           
           if(!busca.next())
               return -1;
           else
             return busca.getInt(1);       
      }
      catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar selecao em buscaMarca: "+e.getMessage());
       }
        return -1;
    }
    
    public String buscaNomeDono(String codDono){
        String selectCliente = "SELECT pessoaFisica FROM Cliente where codCliente="+codDono+";";
        ResultSet busca;
        
        try{
           st.execute(selectCliente);
           busca =  st.getResultSet();
           
           if(!busca.next())
               return null;
           else
           {   String selectPessoa;
               if(busca.getBoolean(1))
                   selectPessoa = "SELECT nome FROM PessoaFisica where codCliente="+codDono+";";
               else
                   selectPessoa = "SELECT nomeFantasia FROM PessoaJuridica where codCliente="+codDono+";";
               
               st.execute(selectPessoa);
               busca = st.getResultSet();
               
               if(!busca.next())
                   return busca.getString(1);
                   
           }       
      }
      catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar selecao em buscaMarca: "+e.getMessage());
       }
        
        return null;
    }

}

   