/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oficina.forms;

import oficina.classes.PessoaFisica;
import oficina.classes.PessoaJuridica;
import java.awt.Color;
import javax.swing.JOptionPane;
import oficina.classes.ConexaoBd;
import oficina.classes.Funcionario;
import oficina.visualizar.VisualizaPessoaFisica;
import oficina.visualizar.VisualizaPessoaJuridica;

/**
 *
 * @author RF
 */
public class FormPessoaPanel extends javax.swing.JPanel {

    /**
     * Creates new form FormPessoaPanel
     */
    public FormPessoaPanel(Funcionario func) {
        
        this.funcionario = func;
        initComponents();
        //this.setLocationRelativeTo(null);
        tfFisicaCep.setForeground(Color.gray);
        tfFisicaRg.setForeground(Color.gray);
        tfFisicaCpf.setForeground(Color.gray);
        tfFisicaResidencial.setForeground(Color.gray);
        tfFisicaCelular.setForeground(Color.gray);
        clienteFisico = null;
        
        tfJuridicaCep.setForeground(Color.gray);
        tfJuridicaCnpj.setForeground(Color.gray);
        tfJuridicaFax.setForeground(Color.gray);
        tfJuridicaTelefoneComercial.setForeground(Color.gray);
        clienteJuridico = null;
    }

    private boolean verificaRg(){
        if(tfFisicaRg.getText().equals("  .   .   - ") || tfFisicaRg.getText().equals("00.000.000-0"))
            return false;
              
        return true;
            
    }
    
    private boolean verificaCpf(){
        if(tfFisicaCpf.getText().equals("   .   .   -  ") || tfFisicaCpf.getText().equals("000.000.000-00"))
            return false;
        
        return true;
    }
    
    private boolean verificaCnpj(){
        if(tfJuridicaCnpj.getText().equals("  .   .   /    -  ") || tfJuridicaCnpj.getText().equals("00.000.000/0000-00"))
            return false;
              
        return true;
    }
    
    private boolean verificaFisicaCep(){
        if(tfFisicaCep.getText().equals("     -   ") || tfFisicaCep.getText().equals("00000-000"))
            return false;
        
        return true;
    }
    
    private boolean verificaJuridicaCep(){
        if(tfJuridicaCep.getText().equals("     -   ") || tfJuridicaCep.getText().equals("00000-000"))
            return false;
        
        return true;
    }
    
    private boolean verificaTelefone(){
        if(tfFisicaResidencial.getText().equals("(  )    -    ") || tfFisicaResidencial.getText().equals("(00)0000-0000"))
            return false;
        
        return true;
    }
    
    private boolean verificaTelefoneComercial(){
        if(tfJuridicaTelefoneComercial.getText().equals("(  )    -    ") || tfJuridicaTelefoneComercial.getText().equals("(00)0000-0000"))
            return false;
        
        return true;
    }
    
    private boolean verificaCelular(){
        if(tfFisicaCelular.getText().equals("(  )     -    ") || tfFisicaCelular.getText().equals("(00)00000-0000"))
            return false;
        
        return true;
    }
    
    private boolean verificaFax(){
        if(tfJuridicaFax.getText().equals("(  )    -    ") || tfJuridicaFax.getText().equals("(00)0000-0000"))
            return false;
        
        return true;
    }
    
    //Metodo para validar entradas dos campos referentes aos Dados Pessoais
    private boolean validaFisicaDados(){
        boolean retorno = true;
        
        if(tfFisicaNome.getText().isEmpty() || !verificaRg() ||
                !verificaCpf())
        {
            lbFisicaInfoAlerta.setText("Por favor, preencha os campos destacados em vermelho.");
            retorno = false;
        }
        else
           lbFisicaInfoAlerta.setText("");

        if(tfFisicaNome.getText().isEmpty())
          lbFisicaNome.setForeground(Color.red);       
        else
            lbFisicaNome.setForeground(Color.black);
        
        if(!verificaRg())
           lbFisicaRg.setForeground(Color.red);
        else
            lbFisicaRg.setForeground(Color.black);
        
        if(!verificaCpf())
           lbFisicaCpf.setForeground(Color.red);
        else
            lbFisicaCpf.setForeground(Color.black);
        
        return retorno;
    }
    
    private boolean validaFisicaEndereco(){
        boolean retorno = true;
        
        if(tfFisicaRua.getText().isEmpty() || tfFisicaNumero.getText().isEmpty() || !verificaFisicaCep()
                || tfFisicaBairro.getText().isEmpty() || tfFisicaCidade.getText().isEmpty())
        {
            lbFisicaEnderecoAlerta.setText("Por favor, preencha os campos destacados em vermelho.");
            retorno = false;
        }
        else
            lbFisicaEnderecoAlerta.setText("");
        
        if(tfFisicaRua.getText().isEmpty())
            lbFisicaRua.setForeground(Color.red);
        else
            lbFisicaRua.setForeground(Color.black);
        
        if(tfFisicaNumero.getText().isEmpty())
            lbFisicaNumero.setForeground(Color.red);
        else
            lbFisicaNumero.setForeground(Color.black);
        
        if(!verificaFisicaCep())
            lbFisicaCep.setForeground(Color.red);
        else
            lbFisicaCep.setForeground(Color.black);
        
        if(tfFisicaBairro.getText().isEmpty())
            lbFisicaBairro.setForeground(Color.red);
        else
            lbFisicaBairro.setForeground(Color.black);
        
        if(tfFisicaCidade.getText().isEmpty())
            lbFisicaCidade.setForeground(Color.red);
        else
            lbFisicaCidade.setForeground(Color.black);
        
        return retorno;
    }
    
    private boolean validaFisicaContatos(){
        boolean retorno = true; 
        
        if(!verificaTelefone() || tfFisicaEmail.getText().isEmpty() || !verificaCelular())
        {
            lbFisicaContatoAlerta.setText("Por favor, preencha os campos destacados em vermelho.");
            retorno = false;
        }
        else
            lbFisicaContatoAlerta.setText("");
        
        if(!verificaTelefone())
            lbFisicaTelefone.setForeground(Color.red);
        else
            lbFisicaTelefone.setForeground(Color.black);
        
        if(tfFisicaEmail.getText().isEmpty())
            lbFisicaEmail.setForeground(Color.red);
        else
            lbFisicaEmail.setForeground(Color.black);
        
        if(!verificaCelular())
            lbFisicaCelular.setForeground(Color.red);
        else
            lbFisicaCelular.setForeground(Color.black);
        
        return retorno;
    }
    
    private boolean validaJuridicaDados(){
        boolean retorno = true;
        
        if(tfJuridicaNomeComercial.getText().isEmpty() || !verificaCnpj())
        {
            lbJuridicaInfoAlerta.setText("Por favor, preencha os campos destacados em vermelho.");
            retorno = false;
        }
        else
           lbJuridicaInfoAlerta.setText("");

        if(tfJuridicaNomeComercial.getText().isEmpty())
          lbJuridicaNomeComercial.setForeground(Color.red);       
        else
            lbJuridicaNomeComercial.setForeground(Color.black);
        
        if(!verificaCnpj())
           lbJuridicaCnpj.setForeground(Color.red);
        else
            lbJuridicaCnpj.setForeground(Color.black);
        
        
        return retorno;
    }
    
        private boolean validaEndereco(){
        boolean retorno = true;
        
        if(tfJuridicaRua.getText().isEmpty() || tfJuridicaNumero.getText().isEmpty() || !verificaJuridicaCep()
                || tfJuridicaBairro.getText().isEmpty() || tfJuridicaCidade.getText().isEmpty())
        {
            lbJuridicaEnderecoAlerta.setText("Por favor, preencha os campos destacados em vermelho.");
            retorno = false;
        }
        else
            lbJuridicaEnderecoAlerta.setText("");
        
        if(tfJuridicaRua.getText().isEmpty())
            lbJuridicaRua.setForeground(Color.red);
        else
            lbJuridicaRua.setForeground(Color.black);
        
        if(tfJuridicaNumero.getText().isEmpty())
            lbJuridicaNumero.setForeground(Color.red);
        else
            lbJuridicaNumero.setForeground(Color.black);
        
        if(!verificaJuridicaCep())
            lbJuridicaCep.setForeground(Color.red);
        else
            lbJuridicaCep.setForeground(Color.black);
        
        if(tfJuridicaBairro.getText().isEmpty())
            lbJuridicaBairro.setForeground(Color.red);
        else
            lbJuridicaBairro.setForeground(Color.black);
        
        if(tfJuridicaCidade.getText().isEmpty())
            lbJuridicaCidade.setForeground(Color.red);
        else
            lbJuridicaCidade.setForeground(Color.black);
        
        return retorno;
    }
        
    private boolean validaContatos(){
        boolean retorno = true; 
        
        if(!verificaTelefoneComercial() || tfJuridicaEmail.getText().isEmpty() || !verificaFax())
        {
            lbJuridicaContatoAlerta.setText("Por favor, preencha os campos destacados em vermelho.");
            retorno = false;
        }
        else
            lbJuridicaContatoAlerta.setText("");
        
        if(!verificaTelefoneComercial())
            lbJuridicaTelefoneComercial.setForeground(Color.red);
        else
            lbJuridicaTelefoneComercial.setForeground(Color.black);
        
        if(tfJuridicaEmail.getText().isEmpty())
            lbJuridicaEmail.setForeground(Color.red);
        else
            lbJuridicaEmail.setForeground(Color.black);
        
        if(!verificaFax())
            lbJuridicaFax.setForeground(Color.red);
        else
            lbJuridicaFax.setForeground(Color.black);
        
        return retorno;
    }
    
    private void fisicaLimpar()
    {
        tfFisicaBairro.setText(null);
        tfFisicaCelular.setText("00000000000");
        tfFisicaCep.setText("00000000");
        tfFisicaCidade.setText(null);
        tfFisicaCpf.setText("00000000000");
        tfFisicaEmail.setText(null);
        tfFisicaNome.setText(null);
        tfFisicaNumero.setText(null);
        taFisicaObservacoes.setText(null);
        tfFisicaResidencial.setText("0000000000");
        tfFisicaRg.setText("000000000");
        tfFisicaRua.setText(null);
        
        tfFisicaCep.setForeground(Color.gray);
        tfFisicaRg.setForeground(Color.gray);
        tfFisicaCpf.setForeground(Color.gray);
        tfFisicaResidencial.setForeground(Color.gray);
        tfFisicaCelular.setForeground(Color.gray);
        
        lbFisicaInfoAlerta.setText(null);
        lbFisicaNome.setForeground(Color.BLACK);
        lbFisicaRg.setForeground(Color.BLACK);
        lbFisicaCpf.setForeground(Color.BLACK);
        lbFisicaEnderecoAlerta.setText(null);
        lbFisicaRua.setForeground(Color.black);
        lbFisicaNumero.setForeground(Color.black);
        lbFisicaCep.setForeground(Color.black);
        lbFisicaBairro.setForeground(Color.black);
        lbFisicaCidade.setForeground(Color.black);
        lbFisicaContatoAlerta.setText(null);
        lbFisicaTelefone.setForeground(Color.black);
        lbFisicaEmail.setForeground(Color.black);
        lbFisicaCelular.setForeground(Color.black);
    }
    
    private void juridicaLimpar()
    {
        tfJuridicaBairro.setText(null);
        tfJuridicaCep.setText("00000000");
        tfJuridicaCidade.setText(null);
        tfJuridicaCnpj.setText("00000000000000");
        tfJuridicaEmail.setText(null);
        tfJuridicaFax.setText("0000000000");
        tfJuridicaNomeComercial.setText(null);
        tfJuridicaNumero.setText(null);
        taJuridicaObservacoes.setText(null);
        tfJuridicaRua.setText(null);
        tfJuridicaTelefoneComercial.setText("0000000000");
        
        tfJuridicaCep.setForeground(Color.gray);
        tfJuridicaCnpj.setForeground(Color.gray);
        tfJuridicaFax.setForeground(Color.gray);
        tfJuridicaTelefoneComercial.setForeground(Color.gray);
        
        lbJuridicaInfoAlerta.setText(null);
        lbJuridicaNomeComercial.setForeground(Color.black);
        lbJuridicaCnpj.setForeground(Color.black);
        lbJuridicaEnderecoAlerta.setText(null);
        lbJuridicaRua.setForeground(Color.black);
        lbJuridicaNumero.setForeground(Color.black);
        lbJuridicaCep.setForeground(Color.black);
        lbJuridicaBairro.setForeground(Color.black);
        lbJuridicaCidade.setForeground(Color.black);
        lbJuridicaContatoAlerta.setText(null);
        lbJuridicaTelefoneComercial.setForeground(Color.black);
        lbJuridicaEmail.setForeground(Color.black);
        lbJuridicaFax.setForeground(Color.black);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTitulo = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelPessoaFisica = new javax.swing.JPanel();
        panelFisicaLbDadosHolder = new javax.swing.JPanel();
        lbDadosPessoais = new javax.swing.JLabel();
        panelFisicaDadosRow1 = new javax.swing.JPanel();
        lbFisicaNome = new javax.swing.JLabel();
        tfFisicaNome = new javax.swing.JTextField();
        panelFisicaDadosRow2 = new javax.swing.JPanel();
        lbFisicaRg = new javax.swing.JLabel();
        tfFisicaRg = new javax.swing.JFormattedTextField();
        lbFisicaCpf = new javax.swing.JLabel();
        tfFisicaCpf = new javax.swing.JFormattedTextField();
        panelFisicaInfoAlertaHolder = new javax.swing.JPanel();
        lbFisicaInfoAlerta = new javax.swing.JLabel();
        panelFisicaLbEndrcHolder = new javax.swing.JPanel();
        lbFisicaFisicaEndereco = new javax.swing.JLabel();
        panelFisicaEndrcRow1 = new javax.swing.JPanel();
        lbFisicaRua = new javax.swing.JLabel();
        tfFisicaRua = new javax.swing.JTextField();
        lbFisicaNumero = new javax.swing.JLabel();
        tfFisicaNumero = new javax.swing.JTextField();
        lbFisicaCep = new javax.swing.JLabel();
        tfFisicaCep = new javax.swing.JFormattedTextField();
        panelFisicaEndrcRow2 = new javax.swing.JPanel();
        lbFisicaBairro = new javax.swing.JLabel();
        tfFisicaBairro = new javax.swing.JTextField();
        lbFisicaCidade = new javax.swing.JLabel();
        tfFisicaCidade = new javax.swing.JTextField();
        panelFisicaEndrcRow3 = new javax.swing.JPanel();
        lbFisicaEstado = new javax.swing.JLabel();
        cbFisicaEstado = new javax.swing.JComboBox();
        panelFisicaEndrcAlertaHolder = new javax.swing.JPanel();
        lbFisicaEnderecoAlerta = new javax.swing.JLabel();
        panelFisicaLbConttsHolder = new javax.swing.JPanel();
        lbFisicaContatos = new javax.swing.JLabel();
        panelFisicaConttsRow1 = new javax.swing.JPanel();
        lbFisicaTelefone = new javax.swing.JLabel();
        tfFisicaResidencial = new javax.swing.JFormattedTextField();
        lbFisicaCelular = new javax.swing.JLabel();
        tfFisicaCelular = new javax.swing.JFormattedTextField();
        panelFisicaCottsRow2 = new javax.swing.JPanel();
        lbFisicaEmail = new javax.swing.JLabel();
        tfFisicaEmail = new javax.swing.JTextField();
        panelFisicaConttsAlertaHolder = new javax.swing.JPanel();
        lbFisicaContatoAlerta = new javax.swing.JLabel();
        panelFisicaObs = new javax.swing.JPanel();
        lbFisicaObs = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        taFisicaObservacoes = new javax.swing.JTextArea();
        paneFisicalBotoes = new javax.swing.JPanel();
        btFisicaCadastrar = new javax.swing.JButton();
        btFisicaLimpar = new javax.swing.JButton();
        btFisicaVisualizar = new javax.swing.JButton();
        panelPessoaJuridica = new javax.swing.JPanel();
        panelJuridicaLbDadosHolder = new javax.swing.JPanel();
        lbJuridicaDados = new javax.swing.JLabel();
        panelJuridicaDadosRow1 = new javax.swing.JPanel();
        lbJuridicaNomeComercial = new javax.swing.JLabel();
        tfJuridicaNomeComercial = new javax.swing.JTextField();
        panelDadosRow2 = new javax.swing.JPanel();
        lbJuridicaCnpj = new javax.swing.JLabel();
        tfJuridicaCnpj = new javax.swing.JFormattedTextField();
        panelJuridicaInfoAlertaHolder = new javax.swing.JPanel();
        lbJuridicaInfoAlerta = new javax.swing.JLabel();
        panelJuridicaLbEndrcHolder = new javax.swing.JPanel();
        lbJuridicaEndereco = new javax.swing.JLabel();
        panelEndrcRow1 = new javax.swing.JPanel();
        lbJuridicaRua = new javax.swing.JLabel();
        tfJuridicaRua = new javax.swing.JTextField();
        lbJuridicaNumero = new javax.swing.JLabel();
        tfJuridicaNumero = new javax.swing.JTextField();
        lbJuridicaCep = new javax.swing.JLabel();
        tfJuridicaCep = new javax.swing.JFormattedTextField();
        panelJuridicaEndrcRow2 = new javax.swing.JPanel();
        lbJuridicaBairro = new javax.swing.JLabel();
        tfJuridicaBairro = new javax.swing.JTextField();
        lbJuridicaCidade = new javax.swing.JLabel();
        tfJuridicaCidade = new javax.swing.JTextField();
        panelJuridicaEndrcRow3 = new javax.swing.JPanel();
        lbJuridicaEstado = new javax.swing.JLabel();
        cbJuridicaEstado = new javax.swing.JComboBox();
        panelJuridicaEndrcAlertaHolder = new javax.swing.JPanel();
        lbJuridicaEnderecoAlerta = new javax.swing.JLabel();
        panelJuridicaLbConttsHolder = new javax.swing.JPanel();
        lbJuridicaContatos = new javax.swing.JLabel();
        panelJuridicaConttsRow1 = new javax.swing.JPanel();
        lbJuridicaTelefoneComercial = new javax.swing.JLabel();
        tfJuridicaTelefoneComercial = new javax.swing.JFormattedTextField();
        lbJuridicaFax = new javax.swing.JLabel();
        tfJuridicaFax = new javax.swing.JFormattedTextField();
        panelJuridicaConttsRow2 = new javax.swing.JPanel();
        lbJuridicaEmail = new javax.swing.JLabel();
        tfJuridicaEmail = new javax.swing.JTextField();
        panelJuridicaConttsAlertaHolder = new javax.swing.JPanel();
        lbJuridicaContatoAlerta = new javax.swing.JLabel();
        panelJuridicaObs = new javax.swing.JPanel();
        lbJuridicaObs = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taJuridicaObservacoes = new javax.swing.JTextArea();
        panelJuridicaBotoes = new javax.swing.JPanel();
        btJuridicaCadastrar = new javax.swing.JButton();
        btJuridicaLimpar = new javax.swing.JButton();
        btJuridicaVisualizar = new javax.swing.JButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        lbTitulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbTitulo.setText("Cadastro de Pessoa Física/Jurídica");
        panelTitulo.add(lbTitulo);

        add(panelTitulo);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

        panelPessoaFisica.setLayout(new javax.swing.BoxLayout(panelPessoaFisica, javax.swing.BoxLayout.PAGE_AXIS));

        panelFisicaLbDadosHolder.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbDadosPessoais.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbDadosPessoais.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDadosPessoais.setText("Dados Pessoais: ");
        panelFisicaLbDadosHolder.add(lbDadosPessoais);

        panelPessoaFisica.add(panelFisicaLbDadosHolder);

        panelFisicaDadosRow1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbFisicaNome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbFisicaNome.setText("  Nome:");
        panelFisicaDadosRow1.add(lbFisicaNome);

        tfFisicaNome.setColumns(45);
        tfFisicaNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfFisicaNomeKeyTyped(evt);
            }
        });
        panelFisicaDadosRow1.add(tfFisicaNome);

        panelPessoaFisica.add(panelFisicaDadosRow1);

        panelFisicaDadosRow2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbFisicaRg.setText("  RG:");
        panelFisicaDadosRow2.add(lbFisicaRg);

        tfFisicaRg.setColumns(12);
        try {
            tfFisicaRg.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfFisicaRg.setText("000000000");
        tfFisicaRg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfFisicaRgFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfFisicaRgFocusLost(evt);
            }
        });
        panelFisicaDadosRow2.add(tfFisicaRg);

        lbFisicaCpf.setText("CPF:");
        panelFisicaDadosRow2.add(lbFisicaCpf);

        tfFisicaCpf.setColumns(14);
        try {
            tfFisicaCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfFisicaCpf.setText("00000000000");
        tfFisicaCpf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfFisicaCpfFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfFisicaCpfFocusLost(evt);
            }
        });
        panelFisicaDadosRow2.add(tfFisicaCpf);

        panelPessoaFisica.add(panelFisicaDadosRow2);

        panelFisicaInfoAlertaHolder.setPreferredSize(new java.awt.Dimension(286, 24));
        panelFisicaInfoAlertaHolder.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbFisicaInfoAlerta.setForeground(new java.awt.Color(255, 0, 0));
        panelFisicaInfoAlertaHolder.add(lbFisicaInfoAlerta);

        panelPessoaFisica.add(panelFisicaInfoAlertaHolder);

        panelFisicaLbEndrcHolder.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbFisicaFisicaEndereco.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbFisicaFisicaEndereco.setText("Endereço: ");
        panelFisicaLbEndrcHolder.add(lbFisicaFisicaEndereco);

        panelPessoaFisica.add(panelFisicaLbEndrcHolder);

        panelFisicaEndrcRow1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbFisicaRua.setText("  Rua:");
        panelFisicaEndrcRow1.add(lbFisicaRua);

        tfFisicaRua.setColumns(20);
        tfFisicaRua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfFisicaRuaKeyTyped(evt);
            }
        });
        panelFisicaEndrcRow1.add(tfFisicaRua);

        lbFisicaNumero.setText("Número:");
        panelFisicaEndrcRow1.add(lbFisicaNumero);

        tfFisicaNumero.setColumns(7);
        tfFisicaNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfFisicaNumeroKeyTyped(evt);
            }
        });
        panelFisicaEndrcRow1.add(tfFisicaNumero);

        lbFisicaCep.setText("CEP:");
        panelFisicaEndrcRow1.add(lbFisicaCep);

        tfFisicaCep.setColumns(11);
        try {
            tfFisicaCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfFisicaCep.setText("0000000000");
        tfFisicaCep.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfFisicaCepFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfFisicaCepFocusLost(evt);
            }
        });
        panelFisicaEndrcRow1.add(tfFisicaCep);

        panelPessoaFisica.add(panelFisicaEndrcRow1);

        panelFisicaEndrcRow2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbFisicaBairro.setText("  Bairro:");
        panelFisicaEndrcRow2.add(lbFisicaBairro);

        tfFisicaBairro.setColumns(20);
        tfFisicaBairro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfFisicaBairroKeyTyped(evt);
            }
        });
        panelFisicaEndrcRow2.add(tfFisicaBairro);

        lbFisicaCidade.setText("Cidade:");
        panelFisicaEndrcRow2.add(lbFisicaCidade);

        tfFisicaCidade.setColumns(20);
        tfFisicaCidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfFisicaCidadeKeyTyped(evt);
            }
        });
        panelFisicaEndrcRow2.add(tfFisicaCidade);

        panelPessoaFisica.add(panelFisicaEndrcRow2);

        panelFisicaEndrcRow3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbFisicaEstado.setText("  Estado:");
        panelFisicaEndrcRow3.add(lbFisicaEstado);

        cbFisicaEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SP", "MG", "RJ", "PE", "RS", "MT", "DF" }));
        panelFisicaEndrcRow3.add(cbFisicaEstado);

        panelPessoaFisica.add(panelFisicaEndrcRow3);

        panelFisicaEndrcAlertaHolder.setPreferredSize(new java.awt.Dimension(286, 24));
        panelFisicaEndrcAlertaHolder.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbFisicaEnderecoAlerta.setForeground(new java.awt.Color(255, 0, 0));
        panelFisicaEndrcAlertaHolder.add(lbFisicaEnderecoAlerta);

        panelPessoaFisica.add(panelFisicaEndrcAlertaHolder);

        panelFisicaLbConttsHolder.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbFisicaContatos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbFisicaContatos.setText("Contatos: ");
        panelFisicaLbConttsHolder.add(lbFisicaContatos);

        panelPessoaFisica.add(panelFisicaLbConttsHolder);

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT);
        flowLayout1.setAlignOnBaseline(true);
        panelFisicaConttsRow1.setLayout(flowLayout1);

        lbFisicaTelefone.setText("  Tel. Residencial:");
        panelFisicaConttsRow1.add(lbFisicaTelefone);

        tfFisicaResidencial.setColumns(14);
        try {
            tfFisicaResidencial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfFisicaResidencial.setText("0000000000");
        tfFisicaResidencial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfFisicaResidencialFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfFisicaResidencialFocusLost(evt);
            }
        });
        panelFisicaConttsRow1.add(tfFisicaResidencial);

        lbFisicaCelular.setText("  Celular:");
        panelFisicaConttsRow1.add(lbFisicaCelular);

        tfFisicaCelular.setColumns(15);
        try {
            tfFisicaCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfFisicaCelular.setText("00000000000");
        tfFisicaCelular.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfFisicaCelularFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfFisicaCelularFocusLost(evt);
            }
        });
        panelFisicaConttsRow1.add(tfFisicaCelular);

        panelPessoaFisica.add(panelFisicaConttsRow1);

        panelFisicaCottsRow2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbFisicaEmail.setText("  E-mail:");
        panelFisicaCottsRow2.add(lbFisicaEmail);

        tfFisicaEmail.setColumns(20);
        tfFisicaEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfFisicaEmailKeyTyped(evt);
            }
        });
        panelFisicaCottsRow2.add(tfFisicaEmail);

        panelPessoaFisica.add(panelFisicaCottsRow2);

        panelFisicaConttsAlertaHolder.setPreferredSize(new java.awt.Dimension(286, 24));
        panelFisicaConttsAlertaHolder.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbFisicaContatoAlerta.setForeground(new java.awt.Color(255, 0, 0));
        panelFisicaConttsAlertaHolder.add(lbFisicaContatoAlerta);

        panelPessoaFisica.add(panelFisicaConttsAlertaHolder);

        panelFisicaObs.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbFisicaObs.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbFisicaObs.setText("Observações: ");
        panelFisicaObs.add(lbFisicaObs);

        taFisicaObservacoes.setColumns(45);
        taFisicaObservacoes.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        taFisicaObservacoes.setLineWrap(true);
        taFisicaObservacoes.setRows(5);
        taFisicaObservacoes.setWrapStyleWord(true);
        jScrollPane3.setViewportView(taFisicaObservacoes);

        panelFisicaObs.add(jScrollPane3);

        panelPessoaFisica.add(panelFisicaObs);

        btFisicaCadastrar.setText("Cadastrar");
        btFisicaCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFisicaCadastrarActionPerformed(evt);
            }
        });
        paneFisicalBotoes.add(btFisicaCadastrar);

        btFisicaLimpar.setText("Limpar");
        btFisicaLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFisicaLimparActionPerformed(evt);
            }
        });
        paneFisicalBotoes.add(btFisicaLimpar);

        btFisicaVisualizar.setText("Visualizar");
        btFisicaVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFisicaVisualizarActionPerformed(evt);
            }
        });
        paneFisicalBotoes.add(btFisicaVisualizar);

        panelPessoaFisica.add(paneFisicalBotoes);

        jTabbedPane1.addTab("Pessoa Física", panelPessoaFisica);

        panelPessoaJuridica.setLayout(new javax.swing.BoxLayout(panelPessoaJuridica, javax.swing.BoxLayout.PAGE_AXIS));

        panelJuridicaLbDadosHolder.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbJuridicaDados.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbJuridicaDados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbJuridicaDados.setText("Dados da Empresa: ");
        panelJuridicaLbDadosHolder.add(lbJuridicaDados);

        panelPessoaJuridica.add(panelJuridicaLbDadosHolder);

        panelJuridicaDadosRow1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbJuridicaNomeComercial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbJuridicaNomeComercial.setText(" Nome Comercial: ");
        lbJuridicaNomeComercial.setToolTipText("");
        panelJuridicaDadosRow1.add(lbJuridicaNomeComercial);

        tfJuridicaNomeComercial.setColumns(42);
        tfJuridicaNomeComercial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfJuridicaNomeComercialKeyTyped(evt);
            }
        });
        panelJuridicaDadosRow1.add(tfJuridicaNomeComercial);

        panelPessoaJuridica.add(panelJuridicaDadosRow1);

        panelDadosRow2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbJuridicaCnpj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbJuridicaCnpj.setText(" CNPJ: ");
        lbJuridicaCnpj.setToolTipText("");
        panelDadosRow2.add(lbJuridicaCnpj);

        tfJuridicaCnpj.setColumns(20);
        try {
            tfJuridicaCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfJuridicaCnpj.setText("00000000000000");
        tfJuridicaCnpj.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfJuridicaCnpjFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfJuridicaCnpjFocusLost(evt);
            }
        });
        panelDadosRow2.add(tfJuridicaCnpj);

        panelPessoaJuridica.add(panelDadosRow2);

        panelJuridicaInfoAlertaHolder.setPreferredSize(new java.awt.Dimension(286, 24));
        panelJuridicaInfoAlertaHolder.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbJuridicaInfoAlerta.setForeground(new java.awt.Color(255, 0, 0));
        panelJuridicaInfoAlertaHolder.add(lbJuridicaInfoAlerta);

        panelPessoaJuridica.add(panelJuridicaInfoAlertaHolder);

        panelJuridicaLbEndrcHolder.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbJuridicaEndereco.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbJuridicaEndereco.setText("Endereço: ");
        panelJuridicaLbEndrcHolder.add(lbJuridicaEndereco);

        panelPessoaJuridica.add(panelJuridicaLbEndrcHolder);

        panelEndrcRow1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbJuridicaRua.setText("  Rua:");
        panelEndrcRow1.add(lbJuridicaRua);

        tfJuridicaRua.setColumns(20);
        tfJuridicaRua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfJuridicaRuaKeyTyped(evt);
            }
        });
        panelEndrcRow1.add(tfJuridicaRua);

        lbJuridicaNumero.setText("Número:");
        panelEndrcRow1.add(lbJuridicaNumero);

        tfJuridicaNumero.setColumns(7);
        tfJuridicaNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfJuridicaNumeroKeyTyped(evt);
            }
        });
        panelEndrcRow1.add(tfJuridicaNumero);

        lbJuridicaCep.setText("CEP:");
        panelEndrcRow1.add(lbJuridicaCep);

        tfJuridicaCep.setColumns(11);
        try {
            tfJuridicaCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfJuridicaCep.setText("00000000");
        tfJuridicaCep.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfJuridicaCepFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfJuridicaCepFocusLost(evt);
            }
        });
        panelEndrcRow1.add(tfJuridicaCep);

        panelPessoaJuridica.add(panelEndrcRow1);

        panelJuridicaEndrcRow2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbJuridicaBairro.setText("  Bairro:");
        panelJuridicaEndrcRow2.add(lbJuridicaBairro);

        tfJuridicaBairro.setColumns(20);
        tfJuridicaBairro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfJuridicaBairroKeyTyped(evt);
            }
        });
        panelJuridicaEndrcRow2.add(tfJuridicaBairro);

        lbJuridicaCidade.setText("Cidade:");
        panelJuridicaEndrcRow2.add(lbJuridicaCidade);

        tfJuridicaCidade.setColumns(20);
        tfJuridicaCidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfJuridicaCidadeKeyTyped(evt);
            }
        });
        panelJuridicaEndrcRow2.add(tfJuridicaCidade);

        panelPessoaJuridica.add(panelJuridicaEndrcRow2);

        panelJuridicaEndrcRow3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbJuridicaEstado.setText("  Estado:");
        panelJuridicaEndrcRow3.add(lbJuridicaEstado);

        cbJuridicaEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SP", "MG", "RJ", "PE", "RS", "MT", "DF" }));
        panelJuridicaEndrcRow3.add(cbJuridicaEstado);

        panelPessoaJuridica.add(panelJuridicaEndrcRow3);

        panelJuridicaEndrcAlertaHolder.setPreferredSize(new java.awt.Dimension(286, 24));
        panelJuridicaEndrcAlertaHolder.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbJuridicaEnderecoAlerta.setForeground(new java.awt.Color(255, 0, 0));
        panelJuridicaEndrcAlertaHolder.add(lbJuridicaEnderecoAlerta);

        panelPessoaJuridica.add(panelJuridicaEndrcAlertaHolder);

        panelJuridicaLbConttsHolder.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbJuridicaContatos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbJuridicaContatos.setText("Contatos: ");
        panelJuridicaLbConttsHolder.add(lbJuridicaContatos);

        panelPessoaJuridica.add(panelJuridicaLbConttsHolder);

        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT);
        flowLayout2.setAlignOnBaseline(true);
        panelJuridicaConttsRow1.setLayout(flowLayout2);

        lbJuridicaTelefoneComercial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbJuridicaTelefoneComercial.setText(" Tel. Comercial:");
        lbJuridicaTelefoneComercial.setToolTipText("");
        panelJuridicaConttsRow1.add(lbJuridicaTelefoneComercial);

        tfJuridicaTelefoneComercial.setColumns(15);
        try {
            tfJuridicaTelefoneComercial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfJuridicaTelefoneComercial.setText("0000000000");
        tfJuridicaTelefoneComercial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfJuridicaTelefoneComercialFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfJuridicaTelefoneComercialFocusLost(evt);
            }
        });
        panelJuridicaConttsRow1.add(tfJuridicaTelefoneComercial);

        lbJuridicaFax.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbJuridicaFax.setText("FAX:");
        lbJuridicaFax.setToolTipText("");
        panelJuridicaConttsRow1.add(lbJuridicaFax);

        tfJuridicaFax.setColumns(15);
        try {
            tfJuridicaFax.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfJuridicaFax.setText("0000000000");
        tfJuridicaFax.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfJuridicaFaxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfJuridicaFaxFocusLost(evt);
            }
        });
        panelJuridicaConttsRow1.add(tfJuridicaFax);

        panelPessoaJuridica.add(panelJuridicaConttsRow1);

        panelJuridicaConttsRow2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbJuridicaEmail.setText("  E-mail:");
        panelJuridicaConttsRow2.add(lbJuridicaEmail);

        tfJuridicaEmail.setColumns(20);
        tfJuridicaEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfJuridicaEmailKeyTyped(evt);
            }
        });
        panelJuridicaConttsRow2.add(tfJuridicaEmail);

        panelPessoaJuridica.add(panelJuridicaConttsRow2);

        panelJuridicaConttsAlertaHolder.setPreferredSize(new java.awt.Dimension(286, 24));
        panelJuridicaConttsAlertaHolder.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbJuridicaContatoAlerta.setForeground(new java.awt.Color(255, 0, 0));
        panelJuridicaConttsAlertaHolder.add(lbJuridicaContatoAlerta);

        panelPessoaJuridica.add(panelJuridicaConttsAlertaHolder);

        panelJuridicaObs.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbJuridicaObs.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbJuridicaObs.setText("Observações: ");
        panelJuridicaObs.add(lbJuridicaObs);

        taJuridicaObservacoes.setColumns(45);
        taJuridicaObservacoes.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        taJuridicaObservacoes.setLineWrap(true);
        taJuridicaObservacoes.setRows(5);
        taJuridicaObservacoes.setWrapStyleWord(true);
        jScrollPane1.setViewportView(taJuridicaObservacoes);

        panelJuridicaObs.add(jScrollPane1);

        panelPessoaJuridica.add(panelJuridicaObs);

        btJuridicaCadastrar.setText("Cadastrar");
        btJuridicaCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btJuridicaCadastrarActionPerformed(evt);
            }
        });
        panelJuridicaBotoes.add(btJuridicaCadastrar);

        btJuridicaLimpar.setText("Limpar");
        btJuridicaLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btJuridicaLimparActionPerformed(evt);
            }
        });
        panelJuridicaBotoes.add(btJuridicaLimpar);

        btJuridicaVisualizar.setText("Visualizar");
        btJuridicaVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btJuridicaVisualizarActionPerformed(evt);
            }
        });
        panelJuridicaBotoes.add(btJuridicaVisualizar);

        panelPessoaJuridica.add(panelJuridicaBotoes);

        jTabbedPane1.addTab("Pessoa Jurídica", panelPessoaJuridica);

        jPanel1.add(jTabbedPane1);

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void tfFisicaNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfFisicaNumeroKeyTyped
        if(!Character.isDigit(evt.getKeyChar()) || tfFisicaNumero.getText().length()> 3)
            evt.consume();
    }//GEN-LAST:event_tfFisicaNumeroKeyTyped

    private void tfJuridicaNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfJuridicaNumeroKeyTyped
        if(!Character.isDigit(evt.getKeyChar()) || tfJuridicaNumero.getText().length()> 3)
            evt.consume();
    }//GEN-LAST:event_tfJuridicaNumeroKeyTyped

    private void tfFisicaRgFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfFisicaRgFocusGained
        if("00.000.000-0".equals(tfFisicaRg.getText()))
        {
            tfFisicaRg.setText(null);
            tfFisicaRg.setForeground(Color.black);
        }
    }//GEN-LAST:event_tfFisicaRgFocusGained

    private void tfFisicaRgFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfFisicaRgFocusLost
        if(!verificaRg())
        {
            tfFisicaRg.setText("00.000.000-0");
            tfFisicaRg.setForeground(Color.gray);
        }
    }//GEN-LAST:event_tfFisicaRgFocusLost

    private void tfFisicaCpfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfFisicaCpfFocusGained
        if("000.000.000-00".equals(tfFisicaCpf.getText()))
        {
            tfFisicaCpf.setText(null);
            tfFisicaCpf.setForeground(Color.black);
        }
    }//GEN-LAST:event_tfFisicaCpfFocusGained

    private void tfFisicaCpfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfFisicaCpfFocusLost
        if(!verificaCpf())
        {
            tfFisicaCpf.setText("000.000.000-00");
            tfFisicaCpf.setForeground(Color.gray);
        }
    }//GEN-LAST:event_tfFisicaCpfFocusLost

    private void tfFisicaCepFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfFisicaCepFocusGained
        if(tfFisicaCep.getText().equals("00000-000"))
        {
            tfFisicaCep.setText("");
            tfFisicaCep.setForeground(Color.black);
        }
    }//GEN-LAST:event_tfFisicaCepFocusGained

    private void tfFisicaCepFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfFisicaCepFocusLost
        if(!verificaFisicaCep())
        {
            tfFisicaCep.setText("00000-000");
            tfFisicaCep.setForeground(Color.gray);
        }
    }//GEN-LAST:event_tfFisicaCepFocusLost

    private void tfFisicaResidencialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfFisicaResidencialFocusGained
        if(tfFisicaResidencial.getText().equals("(00)0000-0000"))
        {
            tfFisicaResidencial.setText("");
            tfFisicaResidencial.setForeground(Color.black);
        }
    }//GEN-LAST:event_tfFisicaResidencialFocusGained

    private void tfFisicaResidencialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfFisicaResidencialFocusLost
        if(!verificaTelefone())
        {
            tfFisicaResidencial.setText("(00)0000-0000");
            tfFisicaResidencial.setForeground(Color.gray);
        }
    }//GEN-LAST:event_tfFisicaResidencialFocusLost

    private void tfFisicaCelularFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfFisicaCelularFocusGained
        if(tfFisicaCelular.getText().equals("(00)00000-0000"))
        {
            tfFisicaCelular.setText("");
            tfFisicaCelular.setForeground(Color.black);
        }
    }//GEN-LAST:event_tfFisicaCelularFocusGained

    private void tfFisicaCelularFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfFisicaCelularFocusLost
        if(!verificaCelular())
        {
            tfFisicaCelular.setText("(00)00000-0000");
            tfFisicaCelular.setForeground(Color.gray);
        }
    }//GEN-LAST:event_tfFisicaCelularFocusLost

    private void btFisicaCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFisicaCadastrarActionPerformed
        boolean verifica = validaFisicaDados();
        verifica = validaFisicaEndereco() && verifica;
        verifica = validaFisicaContatos() && verifica;

        if(verifica)
        {
            this.clienteFisico = new PessoaFisica(tfFisicaNome.getText(), tfFisicaRg.getText(), tfFisicaCpf.getText(), tfFisicaRua.getText(),
                tfFisicaNumero.getText(),tfFisicaCep.getText(),tfFisicaBairro.getText(),tfFisicaCidade.getText(),cbFisicaEstado.getSelectedItem().toString(),tfFisicaEmail.getText(),
                taFisicaObservacoes.getText(), tfFisicaResidencial.getText(), tfFisicaCelular.getText());
            
            ConexaoBd bd  = new ConexaoBd();
            bd.CadastrarPessoaFisica(this.clienteFisico, this.funcionario);
            
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
            fisicaLimpar();
        }
    }//GEN-LAST:event_btFisicaCadastrarActionPerformed

    private void btFisicaLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFisicaLimparActionPerformed
        fisicaLimpar();
    }//GEN-LAST:event_btFisicaLimparActionPerformed

    private void btFisicaVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFisicaVisualizarActionPerformed
        if(clienteFisico != null)
        {
            new VisualizaPessoaFisica(clienteFisico).setVisible(true);
        }
        else
            JOptionPane.showMessageDialog(null, "Cliente não cadastrado!");
    }//GEN-LAST:event_btFisicaVisualizarActionPerformed

    private void tfJuridicaCnpjFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfJuridicaCnpjFocusGained
        if("00.000.000/0000-00".equals(tfJuridicaCnpj.getText()))
        {
            tfJuridicaCnpj.setText(null);
            tfJuridicaCnpj.setForeground(Color.black);
        }
    }//GEN-LAST:event_tfJuridicaCnpjFocusGained

    private void tfJuridicaCnpjFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfJuridicaCnpjFocusLost
        if(!verificaCnpj())
        {
            tfJuridicaCnpj.setText("00.000.000/0000-00");
            tfJuridicaCnpj.setForeground(Color.gray);
        }
    }//GEN-LAST:event_tfJuridicaCnpjFocusLost

    private void tfJuridicaCepFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfJuridicaCepFocusGained
        if(tfJuridicaCep.getText().equals("00000-000"))
        {
            tfJuridicaCep.setText("");
            tfJuridicaCep.setForeground(Color.black);
        }
    }//GEN-LAST:event_tfJuridicaCepFocusGained

    private void tfJuridicaCepFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfJuridicaCepFocusLost
        if(!verificaJuridicaCep())
        {
            tfJuridicaCep.setText("00000-000");
            tfJuridicaCep.setForeground(Color.gray);
        }
    }//GEN-LAST:event_tfJuridicaCepFocusLost

    private void tfJuridicaTelefoneComercialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfJuridicaTelefoneComercialFocusGained
        if(tfJuridicaTelefoneComercial.getText().equals("(00)0000-0000"))
        {
            tfJuridicaTelefoneComercial.setText("");
            tfJuridicaTelefoneComercial.setForeground(Color.black);
        }
    }//GEN-LAST:event_tfJuridicaTelefoneComercialFocusGained

    private void tfJuridicaTelefoneComercialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfJuridicaTelefoneComercialFocusLost
        if(!verificaTelefoneComercial())
        {
            tfJuridicaTelefoneComercial.setText("(00)0000-0000");
            tfJuridicaTelefoneComercial.setForeground(Color.gray);
        }
    }//GEN-LAST:event_tfJuridicaTelefoneComercialFocusLost

    private void tfJuridicaFaxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfJuridicaFaxFocusGained
        if(tfJuridicaFax.getText().equals("(00)0000-0000"))
        {
            tfJuridicaFax.setText("");
            tfJuridicaFax.setForeground(Color.black);
        }
    }//GEN-LAST:event_tfJuridicaFaxFocusGained

    private void tfJuridicaFaxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfJuridicaFaxFocusLost
        if(!verificaFax())
        {
            tfJuridicaFax.setText("(00)0000-0000");
            tfJuridicaFax.setForeground(Color.gray);
        }
    }//GEN-LAST:event_tfJuridicaFaxFocusLost

    private void btJuridicaCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btJuridicaCadastrarActionPerformed
        boolean verifica = validaJuridicaDados();
        verifica = validaEndereco() && verifica;
        verifica = validaContatos() && verifica;
        if(verifica)
        {
           this.clienteJuridico = new PessoaJuridica(tfJuridicaNomeComercial.getText(), tfJuridicaCnpj.getText(), tfJuridicaTelefoneComercial.getText(),
                tfJuridicaFax.getText(),  tfJuridicaRua.getText(), tfJuridicaNumero.getText(), tfJuridicaCep.getText(), tfJuridicaBairro.getText(),
                tfJuridicaCidade.getText(),cbJuridicaEstado.getSelectedItem().toString(),tfJuridicaEmail.getText(),
                taJuridicaObservacoes.getText());
            
            ConexaoBd bd = new ConexaoBd();
            bd.cadastraPessoaJuridica(clienteJuridico, this.funcionario);
            
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
            juridicaLimpar();
        }
    }//GEN-LAST:event_btJuridicaCadastrarActionPerformed

    private void btJuridicaLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btJuridicaLimparActionPerformed
        juridicaLimpar();
    }//GEN-LAST:event_btJuridicaLimparActionPerformed

    private void btJuridicaVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btJuridicaVisualizarActionPerformed
        if(clienteJuridico != null)
            new VisualizaPessoaJuridica(clienteJuridico).setVisible(true);
        else
            JOptionPane.showMessageDialog(null, "Cliente não cadastrado!");
    }//GEN-LAST:event_btJuridicaVisualizarActionPerformed

    private void tfJuridicaCidadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfJuridicaCidadeKeyTyped
        // TODO add your handling code here:
        if(tfJuridicaCidade.getText().length() > 34)
            evt.consume();
    }//GEN-LAST:event_tfJuridicaCidadeKeyTyped

    private void tfJuridicaBairroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfJuridicaBairroKeyTyped
        // TODO add your handling code here:
        if(tfJuridicaBairro.getText().length() > 34)
            evt.consume();
    }//GEN-LAST:event_tfJuridicaBairroKeyTyped

    private void tfJuridicaRuaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfJuridicaRuaKeyTyped
        // TODO add your handling code here:
        if(tfJuridicaRua.getText().length() > 264)
            evt.consume();
    }//GEN-LAST:event_tfJuridicaRuaKeyTyped

    private void tfJuridicaEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfJuridicaEmailKeyTyped
        // TODO add your handling code here:
        if(tfJuridicaEmail.getText().length() > 149)
            evt.consume();
    }//GEN-LAST:event_tfJuridicaEmailKeyTyped

    private void tfJuridicaNomeComercialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfJuridicaNomeComercialKeyTyped
        // TODO add your handling code here:
        if(tfJuridicaNomeComercial.getText().length() > 99)
            evt.consume();
    }//GEN-LAST:event_tfJuridicaNomeComercialKeyTyped

    private void tfFisicaNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfFisicaNomeKeyTyped
        // TODO add your handling code here:
        if(tfFisicaNome.getText().length() > 99)
            evt.consume();
    }//GEN-LAST:event_tfFisicaNomeKeyTyped

    private void tfFisicaRuaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfFisicaRuaKeyTyped
        // TODO add your handling code here:
        if(tfFisicaRua.getText().length() > 264)
            evt.consume();
    }//GEN-LAST:event_tfFisicaRuaKeyTyped

    private void tfFisicaBairroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfFisicaBairroKeyTyped
        // TODO add your handling code here:
        if(tfFisicaBairro.getText().length() > 34)
            evt.consume();
    }//GEN-LAST:event_tfFisicaBairroKeyTyped

    private void tfFisicaCidadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfFisicaCidadeKeyTyped
        // TODO add your handling code here:
        if(tfFisicaCidade.getText().length() > 34)
            evt.consume();
    }//GEN-LAST:event_tfFisicaCidadeKeyTyped

    private void tfFisicaEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfFisicaEmailKeyTyped
        // TODO add your handling code here:
        if(tfFisicaEmail.getText().length() > 149)
            evt.consume();
    }//GEN-LAST:event_tfFisicaEmailKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btFisicaCadastrar;
    private javax.swing.JButton btFisicaLimpar;
    private javax.swing.JButton btFisicaVisualizar;
    private javax.swing.JButton btJuridicaCadastrar;
    private javax.swing.JButton btJuridicaLimpar;
    private javax.swing.JButton btJuridicaVisualizar;
    private javax.swing.JComboBox cbFisicaEstado;
    private javax.swing.JComboBox cbJuridicaEstado;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbDadosPessoais;
    private javax.swing.JLabel lbFisicaBairro;
    private javax.swing.JLabel lbFisicaCelular;
    private javax.swing.JLabel lbFisicaCep;
    private javax.swing.JLabel lbFisicaCidade;
    private javax.swing.JLabel lbFisicaContatoAlerta;
    private javax.swing.JLabel lbFisicaContatos;
    private javax.swing.JLabel lbFisicaCpf;
    private javax.swing.JLabel lbFisicaEmail;
    private javax.swing.JLabel lbFisicaEnderecoAlerta;
    private javax.swing.JLabel lbFisicaEstado;
    private javax.swing.JLabel lbFisicaFisicaEndereco;
    private javax.swing.JLabel lbFisicaInfoAlerta;
    private javax.swing.JLabel lbFisicaNome;
    private javax.swing.JLabel lbFisicaNumero;
    private javax.swing.JLabel lbFisicaObs;
    private javax.swing.JLabel lbFisicaRg;
    private javax.swing.JLabel lbFisicaRua;
    private javax.swing.JLabel lbFisicaTelefone;
    private javax.swing.JLabel lbJuridicaBairro;
    private javax.swing.JLabel lbJuridicaCep;
    private javax.swing.JLabel lbJuridicaCidade;
    private javax.swing.JLabel lbJuridicaCnpj;
    private javax.swing.JLabel lbJuridicaContatoAlerta;
    private javax.swing.JLabel lbJuridicaContatos;
    private javax.swing.JLabel lbJuridicaDados;
    private javax.swing.JLabel lbJuridicaEmail;
    private javax.swing.JLabel lbJuridicaEndereco;
    private javax.swing.JLabel lbJuridicaEnderecoAlerta;
    private javax.swing.JLabel lbJuridicaEstado;
    private javax.swing.JLabel lbJuridicaFax;
    private javax.swing.JLabel lbJuridicaInfoAlerta;
    private javax.swing.JLabel lbJuridicaNomeComercial;
    private javax.swing.JLabel lbJuridicaNumero;
    private javax.swing.JLabel lbJuridicaObs;
    private javax.swing.JLabel lbJuridicaRua;
    private javax.swing.JLabel lbJuridicaTelefoneComercial;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JPanel paneFisicalBotoes;
    private javax.swing.JPanel panelDadosRow2;
    private javax.swing.JPanel panelEndrcRow1;
    private javax.swing.JPanel panelFisicaConttsAlertaHolder;
    private javax.swing.JPanel panelFisicaConttsRow1;
    private javax.swing.JPanel panelFisicaCottsRow2;
    private javax.swing.JPanel panelFisicaDadosRow1;
    private javax.swing.JPanel panelFisicaDadosRow2;
    private javax.swing.JPanel panelFisicaEndrcAlertaHolder;
    private javax.swing.JPanel panelFisicaEndrcRow1;
    private javax.swing.JPanel panelFisicaEndrcRow2;
    private javax.swing.JPanel panelFisicaEndrcRow3;
    private javax.swing.JPanel panelFisicaInfoAlertaHolder;
    private javax.swing.JPanel panelFisicaLbConttsHolder;
    private javax.swing.JPanel panelFisicaLbDadosHolder;
    private javax.swing.JPanel panelFisicaLbEndrcHolder;
    private javax.swing.JPanel panelFisicaObs;
    private javax.swing.JPanel panelJuridicaBotoes;
    private javax.swing.JPanel panelJuridicaConttsAlertaHolder;
    private javax.swing.JPanel panelJuridicaConttsRow1;
    private javax.swing.JPanel panelJuridicaConttsRow2;
    private javax.swing.JPanel panelJuridicaDadosRow1;
    private javax.swing.JPanel panelJuridicaEndrcAlertaHolder;
    private javax.swing.JPanel panelJuridicaEndrcRow2;
    private javax.swing.JPanel panelJuridicaEndrcRow3;
    private javax.swing.JPanel panelJuridicaInfoAlertaHolder;
    private javax.swing.JPanel panelJuridicaLbConttsHolder;
    private javax.swing.JPanel panelJuridicaLbDadosHolder;
    private javax.swing.JPanel panelJuridicaLbEndrcHolder;
    private javax.swing.JPanel panelJuridicaObs;
    private javax.swing.JPanel panelPessoaFisica;
    private javax.swing.JPanel panelPessoaJuridica;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JTextArea taFisicaObservacoes;
    private javax.swing.JTextArea taJuridicaObservacoes;
    private javax.swing.JTextField tfFisicaBairro;
    private javax.swing.JFormattedTextField tfFisicaCelular;
    private javax.swing.JFormattedTextField tfFisicaCep;
    private javax.swing.JTextField tfFisicaCidade;
    private javax.swing.JFormattedTextField tfFisicaCpf;
    private javax.swing.JTextField tfFisicaEmail;
    private javax.swing.JTextField tfFisicaNome;
    private javax.swing.JTextField tfFisicaNumero;
    private javax.swing.JFormattedTextField tfFisicaResidencial;
    private javax.swing.JFormattedTextField tfFisicaRg;
    private javax.swing.JTextField tfFisicaRua;
    private javax.swing.JTextField tfJuridicaBairro;
    private javax.swing.JFormattedTextField tfJuridicaCep;
    private javax.swing.JTextField tfJuridicaCidade;
    private javax.swing.JFormattedTextField tfJuridicaCnpj;
    private javax.swing.JTextField tfJuridicaEmail;
    private javax.swing.JFormattedTextField tfJuridicaFax;
    private javax.swing.JTextField tfJuridicaNomeComercial;
    private javax.swing.JTextField tfJuridicaNumero;
    private javax.swing.JTextField tfJuridicaRua;
    private javax.swing.JFormattedTextField tfJuridicaTelefoneComercial;
    // End of variables declaration//GEN-END:variables
    private PessoaFisica clienteFisico;
    private PessoaJuridica clienteJuridico;
    private Funcionario funcionario;
}
