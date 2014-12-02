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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
        String password = "asdasd";
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
    public int buscaCodMecanico(String nomeFuncionario){
        String selectMecanico = "SELECT codFuncionario FROM Funcionario where nomeFuncionario ='"+nomeFuncionario+"';";
        ResultSet busca;
        
         try{
           st.execute(selectMecanico);
           busca =  st.getResultSet();
           
           if(busca.next())
           {              
             return busca.getInt(1);
              
           }

       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar selecao em buscaCodMecanico: "+e.getMessage());
       }
         return -1;
    }
    
    public String buscaNomeMecanico(int codFuncionario){
        String selectMecanico = "SELECT nomeFuncionario FROM Funcionario where codFuncionario ='"+codFuncionario+"';";
        ResultSet busca;
        Statement tempPraNaoBugar;
        try{
            tempPraNaoBugar = myConnection.createStatement();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao executar tempPraNaoBuga:" + e.getMessage());
            return null;
        }
        
        try{
           tempPraNaoBugar.execute(selectMecanico);
           busca =  tempPraNaoBugar.getResultSet();
           
           if(busca.next())
           {              
             return busca.getString(1);
              
           }

       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar selecao em buscaNomeMecanico: "+e.getMessage());
       }
         return null;
    }
    public void preencheCbMecanico(JComboBox cbMecanico){
        String selectMecanico = "SELECT nomeFuncionario FROM Funcionario where atendente = FALSE;";
        ResultSet busca;
        try{
           st.execute(selectMecanico);
           busca =  st.getResultSet();
           
           while(busca.next())
           {              
             cbMecanico.addItem(busca.getString(1));
              
           }

       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar selecao em preencheCBMecanico: "+e.getMessage());
       }
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
    
    //preenche objeto carro com resultado de busca no BD
     private void preencheCarro(Carro carro, ResultSet busca) throws SQLException{
               carro.setPlaca(busca.getString(1));
               carro.setCodModelo(busca.getInt(2));
               carro.setNomeModelo(busca.getString(10));
               carro.setCor(busca.getString(5));
               carro.setAno(busca.getString(6));
               carro.setObservacoes(busca.getString(7));
               carro.setCodDono(busca.getInt(3));
               carro.setCodMarca(busca.getInt(9));
               carro.setNomeMarca(busca.getString(12));
               
               
     }
    
    public Carro buscaCarroPlaca(String placa){
        String selectCarro = "SELECT * FROM Carro, ModeloCarro AS modelo, MarcaCarro AS marca, Cliente where placaCarro='"+placa+
                "' AND modelo.codModelo = Carro.codModelo AND marca.codMarca = modelo.codMarca AND Carro.codDono = Cliente.codCliente"+";";
        ResultSet rs;
        
        try{
           st.execute(selectCarro);
           rs =  st.getResultSet();
           
           if(!rs.next())
               return null;
           else
           {
               Carro carro = new Carro();
               preencheCarro(carro, rs);
               carro.setCPFDono(buscaNomeCliente( rs.getBoolean(15), ""+carro.getCodDono()));
               return carro;
           }
        
      }
      catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar selecao em buscaCarroPlaca: "+e.getMessage());
       }
      
      return null;
    }
    
    public List<Carro> buscaCarroClienteFisico(PessoaFisica cliente){
        List<Carro> carros = new ArrayList<Carro>();
        String selectCarro = "SELECT * FROM Carro, ModeloCarro AS modelo, MarcaCarro AS marca where "
                + "modelo.codModelo = Carro.codModelo AND marca.codMarca = modelo.codMarca AND Carro.codDono ="
                + cliente.getCodCliente() + ";";
        ResultSet busca;
        Carro carro;
        
        try{
           st.execute(selectCarro);
           busca =  st.getResultSet();
           
           while(busca.next())
           {
               carro = new Carro();
               preencheCarro(carro, busca);
               carro.setCPFDono(cliente.getCPF());
               carros.add(carro);
           }
                  
      }
      catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar selecao em buscaCarroClienteFisico: "+e.getMessage());
       }
        
        return carros;
    }
    public List<Carro> buscaCarroClienteJuridico(PessoaJuridica cliente){
        List<Carro> carros = new ArrayList<Carro>();
        String selectCarro = "SELECT * FROM Carro, ModeloCarro AS modelo, MarcaCarro AS marca where "
                + "modelo.codModelo = Carro.codModelo AND marca.codMarca = modelo.codMarca AND Carro.codDono ="
                + cliente.getCodCliente() + ";";
        ResultSet busca;
        Carro carro;
        
        try{
           st.execute(selectCarro);
           busca =  st.getResultSet();
           
           while(busca.next())
           {
               carro = new Carro();
               preencheCarro(carro, busca);
               carro.setCPFDono(cliente.getCNPJ());
               carros.add(carro);
           }
                  
      }
      catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar selecao em buscaCarroClienteJuridico: "+e.getMessage());
       }
        
        return carros;
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
       String insertOS = "INSERT INTO OrdemDeServico(placaCarroOS, codClienteOS, codAtendenteOS,"
               + "codEstadoOS,kmEntradaOS, valorTotalOS) VALUES(";
      ResultSet codOS;
       
       insertOS += "'" + os.getPlacaCarro() +"',";
       insertOS += os.getCodigoCliente() + ",";
       insertOS += funcionario.getCodFuncionario() + ",";
       insertOS += 1 +",";
       insertOS += os.getKmEntrada()+",";
       insertOS += os.getValorTotal() +");";

        try{
           st.execute(insertOS, Statement.RETURN_GENERATED_KEYS);
           codOS = st.getGeneratedKeys();
           codOS.next();
           os.setCodigoOs(codOS.getInt(1));
           CadastraServico(os);
            
            
           

       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar insercao em OS: "+e.getMessage());
       }
       
    }
    public void CadastraServico(OrdemDeServico os){
        String insertServico = "INSERT INTO ServicoFuncionarioOS(codOS, codFuncionario,descricaoServico, preco) VALUES(";
        Iterator<Servico> it = os.servicos.iterator();
        
        while(it.hasNext())
        {
            String insertServico2 = "";
            Servico temp = it.next();
            insertServico2 += os.getCodigoOs() + ",";
            insertServico2 += temp.getCodMecanico() + ",";
            insertServico2 += "'"+temp.getNomeServico()+"',";
            insertServico2 += temp.getPreco() +");";

            try{
               st.execute(insertServico+insertServico2);

           }catch(SQLException e){
               JOptionPane.showMessageDialog(null, "Erro ao executar insercao em Servicos: "+e.getMessage());
           }
        }
    }
    private String buscaNomeestadoOS(int codestado){
        String selectEstado = "SELECT nomeEstado FROM TipoEstadoOS where codEstadoOS = "+codestado+";";
        ResultSet busca;
        try{
           st.execute(selectEstado);
           busca = st.getResultSet();
           if(busca.next())
           {
               return busca.getString(1);
           }
    }catch(SQLException e){
      JOptionPane.showMessageDialog(null, "Erro ao executar buscaNomeEstadoOS: "+e.getMessage());
    }
        return null;
    }
    
    public void buscaServicosOS(OrdemDeServico os){
        String selectServicos = "SELECT codFuncionario, descricaoServico, preco FROM ServicoFuncionarioOS"
                +" WHERE codOS="+ os.getCodigoOs()+ ";";
        ResultSet busca;
        Servico serv;
        
        try{
           st.execute(selectServicos);
           busca = st.getResultSet();
           while(busca.next())
           {
               serv = new Servico(busca.getString(2), busca.getString(3), buscaNomeMecanico(busca.getInt(1)));
               serv.setCodMecanico(busca.getInt(1));
               os.servicos.add(serv);
               
           }
    }catch(SQLException e){
      JOptionPane.showMessageDialog(null, "Erro ao executar buscaServicosOS: "+e.getMessage());
    }
    }
    //Preenche objeto OS com dados buscados no BD
    private void preencheOS(OrdemDeServico os, ResultSet busca) throws SQLException{
        os.setCodigoOs(busca.getInt(1));
        os.setCodCliente(busca.getInt(2));
        os.setPlacaCarro(busca.getString(3));
        os.setKmEntrada(busca.getString(6));
        os.setKmSaida(busca.getString(7));
        os.setDataInicio(busca.getString(9));
        os.setDataFim(busca.getString(10));
        os.setValorTotal(busca.getString(8));
        os.setEstado(buscaNomeestadoOS(busca.getInt(5)));
        os.setCpfCliente(buscaNomeDono(os.getCodigoCliente()));
        buscaServicosOS(os);
    }
    public OrdemDeServico buscaOSCod(int CodigoOS){
        String selectOS = "SELECT * from OrdemDeServico where codOS = "+ CodigoOS + ";";
        OrdemDeServico os;
        ResultSet busca;
        
        try{
           st.execute(selectOS);
           busca = st.getResultSet();
           if(busca.next())
           {
               os = new OrdemDeServico();
               preencheOS(os, busca);
               return os;
           }
       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar buscaOSCod: "+e.getMessage());
       }
        return null;
    }
    public List<OrdemDeServico> buscaOSPlaca(String placa){
        String selectOS = "SELECT * FROM OrdemDeServico where placaCarroOS = '"+ placa + "';";
        OrdemDeServico os;
        List<OrdemDeServico> osList = new ArrayList<OrdemDeServico>();
        ResultSet busca;
        
        try{
           st.execute(selectOS);
           busca = st.getResultSet();
           while(busca.next())
           {
               os = new OrdemDeServico();
               preencheOS(os, busca);
               osList.add(os);
               return osList;
           }
       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar buscaOSPlaca: "+e.getMessage());
       }
        return osList;
    }
    
    public List<OrdemDeServico> buscaOSCliente(int codCliente){
        String selectOS = "SELECT * FROM OrdemDeServico where codClienteOS = "+ codCliente + ";";
        OrdemDeServico os;
        List<OrdemDeServico> osList = new ArrayList<OrdemDeServico>();
        ResultSet busca;
        
        try{
           st.execute(selectOS);
           busca = st.getResultSet();
           while(busca.next())
           {
               os = new OrdemDeServico();
               preencheOS(os, busca);
               osList.add(os);
               return osList;
           }
       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar buscaOSCliente: "+e.getMessage());
       }
        return osList;
    }
    public int buscaCodEstadoOS(String nomeEstado){
        String select = "SELECT codEstadoOS from tipoEstadoOS where nomeEstado = '"+nomeEstado+"';";
        ResultSet busca;
        
        try{
            st.execute(select);
            busca = st.getResultSet();
            
            if(busca.next())
                return busca.getInt(1);
       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar buscaCodEstado: "+e.getMessage());
       }
        return -1;
    }
    public boolean atualizarOS(OrdemDeServico os){
        String update = "UPDATE OrdemDeServico SET ";
        String condition = " WHERE codOS = "+ os.getCodigoOs()+";";
        
        update += "codEstadoOS = "+buscaCodEstadoOS(os.getEstado());
        
        if(os.getEstado() == "Finalizada")
        {
            if(os.getKmSaida().length() > 0)
                update += ", kmSaidaOS = "+os.getKmSaida();
            
            update += ", dataHoraFimOS = CURRENT_TIMESTAMP";
        }
        try{
           st.execute(update + condition);
           
           return true;
       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar atualizaOS: "+e.getMessage());
       }
        return false;
        
    }
    
    
    //==========================CLIENTE=========================================
    
    private int cadastraCliente(Cliente cliente,Funcionario funcionario, boolean fisica){
        ResultSet keyset;

        String insertCliente = "INSERT INTO Cliente(codCadastrante,pessoaFisica, ruaCliente,"
                + "numeroCliente, bairroCliente, cidadeCliente, estadoCliente, CEPCliente, emailCliente, observacoesCliente) VALUES(";
        
        insertCliente +=funcionario.getCodFuncionario()+ ",";
        if(fisica)
            insertCliente += "TRUE,";
        else
            insertCliente += "FALSE,";        
        
        insertCliente += "'" + cliente.getRua() + "',";
        insertCliente += cliente.getEndNumero() + ",";
        insertCliente +=  "'"+cliente.getBairro() + "',";
        insertCliente +=  "'"+cliente.getCidade() + "', ";
        insertCliente += "'"+cliente.getEstado() + "',";
        insertCliente +=  "'"+cliente.getCEP()+ "',";
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
            selectCliente = "SELECT nome FROM PessoaFisica where codCliente="+codCliente+";";
        else
            selectCliente = "SELECT nomeFantasia FROM PessoaJuridica where codCliente="+codCliente+";";
        
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
    
    //Preenche objeto cliente com resultado de busca em PessoaFisica por nome ou cpf
    private void preenchePessoaFisica(PessoaFisica cliente, ResultSet busca) throws SQLException{
        
            
               cliente.setCodCliente(busca.getInt(1));
               cliente.setRG(busca.getString(2));
               cliente.setCPF(busca.getString(3));
               cliente.setNome(busca.getString(4));
               cliente.setNumResidencial(busca.getString(5));
               cliente.setNumCelular(busca.getString(6));
               cliente.setEmail(busca.getString(10));
               cliente.setRua(busca.getString(11));
               cliente.setEndNumero(busca.getString(12));
               cliente.setBairro(busca.getString(13));
               cliente.setCidade(busca.getString(14));
               cliente.setEstado(busca.getString(15));
               cliente.setCEP(busca.getString(16));
               cliente.setObservacoes(busca.getString(17));
    }
    public PessoaFisica buscaPessoaFisicaCPF(String cpf){
        String selectCliente = "SELECT  * FROM PessoaFisica, Cliente where cpf='"+cpf+"'"
                + " AND Cliente.codCliente = PessoaFisica.codCliente;";
        PessoaFisica cliente = new PessoaFisica();
        ResultSet busca;
        try{
           st.execute(selectCliente);
           busca =  st.getResultSet();
           
           if(busca.next())
           {
               preenchePessoaFisica(cliente, busca);
               return cliente;
               
           }
         
      }
      catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar selecao em buscaPessoaFisicaCPF: "+e.getMessage());
       }
        
        return null;
    }
    public List<PessoaFisica> buscaPessoaFisicaNome(String nome){
        List<PessoaFisica> listaCliente =new ArrayList<PessoaFisica>();
        nome = nome.toUpperCase();
         String selectCliente = "SELECT  * FROM PessoaFisica, Cliente where upper(PessoaFisica.nome) Like '%"+nome+"%'"
                + " AND Cliente.codCliente = PessoaFisica.codCliente ORDER BY PessoaFisica.nome;";
         
         PessoaFisica cliente = new PessoaFisica();
        ResultSet busca;
        try{
           st.execute(selectCliente);
           busca =  st.getResultSet();
           
           while(busca.next())
           {
               preenchePessoaFisica(cliente, busca);
               listaCliente.add(cliente);
               cliente = new PessoaFisica();
               
           }
         
      }
      catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar selecao em buscaPessoaFisicaNome: "+e.getMessage());
       }
        
        return listaCliente;
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
    //Preenche objeto cliente com resultado de busca em PessoaFisica por nome ou cpf
    private void preenchePessoaJuridica(PessoaJuridica cliente, ResultSet busca) throws SQLException{
        
               cliente.setCodCliente(busca.getInt(1));
               cliente.setCNPJ(busca.getString(2));
               cliente.setNomeFantasia(busca.getString(3));
               cliente.setTelefoneComercial(busca.getString(4));
               cliente.setFAX(busca.getString(5));
               cliente.setEmail(busca.getString(9));
               cliente.setRua(busca.getString(10));
               cliente.setEndNumero(busca.getString(11));
               cliente.setBairro(busca.getString(12));
               cliente.setCidade(busca.getString(13));
               cliente.setEstado(busca.getString(14));
               cliente.setCEP(busca.getString(15));
               cliente.setObservacoes(busca.getString(16));
    }
    
    public PessoaJuridica buscaPessoaJuridicaCNPJ(String cnpj){
        String selectCliente = "SELECT  * FROM PessoaJuridica, Cliente where cnpj='"+cnpj+"'"
                + "AND Cliente.codCliente = PessoaJuridica.codCliente;";
        PessoaJuridica cliente = new PessoaJuridica();
        ResultSet busca;
        try{
           st.execute(selectCliente);
           busca =  st.getResultSet();
           
           if(busca.next())
           {
               preenchePessoaJuridica(cliente, busca);
               return cliente;
               
           }
         
      }
      catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar selecao em buscaPessoaJuridicaCPF: "+e.getMessage());
       }
        
        return null;
    }
    
    public List<PessoaJuridica> buscaPessoaJuridicaNome(String nome){
        List<PessoaJuridica> listaCliente =new ArrayList<PessoaJuridica>();
        nome = nome.toUpperCase();
         String selectCliente = "SELECT  * FROM PessoaJuridica, Cliente where upper(PessoaJuridica.nomeFantasia) Like '%"+nome+"%'"
                + "AND Cliente.codCliente = PessoaJuridica.codCliente;";
         
         PessoaJuridica cliente = new PessoaJuridica();
        ResultSet busca;
        try{
           st.execute(selectCliente);
           busca =  st.getResultSet();
           
           while(busca.next())
           {
               preenchePessoaJuridica(cliente, busca);
               listaCliente.add(cliente);
               cliente = new PessoaJuridica();
           }
         
      }
      catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar selecao em buscaPessoaJuridicaNome: "+e.getMessage());
       }
        
        return listaCliente;
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
    
    public String buscaNomeDono(int codDono){
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
               
               if(busca.next())
                   return busca.getString(1);
                   
           }       
      }
      catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao executar selecao em buscaMarca: "+e.getMessage());
       }
        
        return null;
    }

}

   