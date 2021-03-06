/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oficina.forms;

import java.awt.Color;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import oficina.classes.ConexaoBd;
import oficina.classes.Funcionario;
import oficina.classes.OrdemDeServico;
import oficina.classes.Servico;
import oficina.modal.buscaCarroModal;
import oficina.modal.formServicoModal;
import oficina.visualizar.VisualizaOs;

/**
 *
 * @author RF
 */
public class FormOsPanel extends javax.swing.JPanel {

    /**
     * Creates new form FormOsPanel
     */
    public FormOsPanel(Funcionario f) {
        initComponents();
        this.funcionario = f;
        //this.setLocationRelativeTo(null);
        this.valor = 0;
    }
    
    /*public FormOs(OrdemDeServico osAlterada) { // GAMBETA PRA GUARDAR OS ALTERADA, NÃO DEVE SER NECESSARIO COM BD
        initComponents();
        //this.setLocationRelativeTo(null);
        this.os = osAlterada;
        valor = 0;
    }*/
    
    private void calcularTotal()
    {
        DefaultListModel d = (DefaultListModel) lServicos.getModel();
        valor = 0;
        
        for(int i = 0; i < d.getSize(); i++)
        {
            valor += Double.parseDouble(((Servico) d.elementAt(i)).getPreco());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        panelTitulo = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        panelHolder = new javax.swing.JPanel();
        panelHolderInside = new javax.swing.JPanel();
        panelInfo = new javax.swing.JPanel();
        panelCarro = new javax.swing.JPanel();
        btSelecionarCarro = new javax.swing.JButton();
        lbCarroText = new javax.swing.JLabel();
        panelCliente = new javax.swing.JPanel();
        lbCliente = new javax.swing.JLabel();
        lbClienteText = new javax.swing.JLabel();
        panelEstado = new javax.swing.JPanel();
        lbEstado = new javax.swing.JLabel();
        cboxEstado = new javax.swing.JComboBox();
        panelKmEntrada = new javax.swing.JPanel();
        lbKmEntrada = new javax.swing.JLabel();
        tfKmEntrada = new javax.swing.JTextField();
        panelServicos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lServicos = new javax.swing.JList();
        panelBotoesLista = new javax.swing.JPanel();
        btAdd = new javax.swing.JButton();
        btDel = new javax.swing.JButton();
        panelValor = new javax.swing.JPanel();
        lbValor = new javax.swing.JLabel();
        lbValorText = new javax.swing.JLabel();
        panelAlerta = new javax.swing.JPanel();
        lbAlerta = new javax.swing.JLabel();
        panelBotoes = new javax.swing.JPanel();
        btCadastrar = new javax.swing.JButton();
        btLimpar = new javax.swing.JButton();

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

        lbTitulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbTitulo.setText("Cadastro de Ordem de Serviço");
        panelTitulo.add(lbTitulo);

        jPanel1.add(panelTitulo);

        panelHolder.setLayout(new javax.swing.BoxLayout(panelHolder, javax.swing.BoxLayout.LINE_AXIS));

        panelInfo.setLayout(new javax.swing.BoxLayout(panelInfo, javax.swing.BoxLayout.PAGE_AXIS));

        panelCarro.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        btSelecionarCarro.setText("* Carro:");
        btSelecionarCarro.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btSelecionarCarroActionPerformed(evt);
            }
        });
        panelCarro.add(btSelecionarCarro);

        lbCarroText.setText("NÃO INFORMADO");
        lbCarroText.setPreferredSize(new java.awt.Dimension(150, 14));
        panelCarro.add(lbCarroText);

        panelInfo.add(panelCarro);

        panelCliente.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbCliente.setText("Cliente:");
        panelCliente.add(lbCliente);

        lbClienteText.setText("NÃO INFORMADO");
        lbClienteText.setPreferredSize(new java.awt.Dimension(230, 14));
        panelCliente.add(lbClienteText);

        panelInfo.add(panelCliente);

        panelEstado.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbEstado.setText("Estado: ");
        panelEstado.add(lbEstado);

        cboxEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pendente para orçamento", "Pendente para aprovação", "Em atendimento", "Pendente para pagamento", "Finalizada", "Cancelada" }));
        cboxEstado.setEnabled(false);
        panelEstado.add(cboxEstado);

        panelInfo.add(panelEstado);

        panelKmEntrada.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbKmEntrada.setText("* Quilometragem de Entrada: ");
        panelKmEntrada.add(lbKmEntrada);

        tfKmEntrada.setColumns(10);
        tfKmEntrada.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                tfKmEntradaKeyTyped(evt);
            }
        });
        panelKmEntrada.add(tfKmEntrada);

        panelInfo.add(panelKmEntrada);

        panelServicos.setBorder(javax.swing.BorderFactory.createTitledBorder("Serviços:"));
        panelServicos.setLayout(new javax.swing.BoxLayout(panelServicos, javax.swing.BoxLayout.PAGE_AXIS));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(160, 200));

        lServicos.setModel(new DefaultListModel<Servico>()
        );
        lServicos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lServicos.setVisibleRowCount(10);
        lServicos.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                lServicosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lServicos);

        panelServicos.add(jScrollPane1);

        panelBotoesLista.setLayout(new java.awt.GridLayout(1, 2, 20, 0));

        btAdd.setText("Adicionar");
        btAdd.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btAddActionPerformed(evt);
            }
        });
        panelBotoesLista.add(btAdd);

        btDel.setText("Remover");
        btDel.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btDelActionPerformed(evt);
            }
        });
        panelBotoesLista.add(btDel);

        panelServicos.add(panelBotoesLista);

        panelValor.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbValor.setText("Valor total:");
        panelValor.add(lbValor);

        lbValorText.setText("R$ 0,00");
        lbValorText.setPreferredSize(new java.awt.Dimension(60, 14));
        panelValor.add(lbValorText);

        panelServicos.add(panelValor);

        panelInfo.add(panelServicos);

        panelAlerta.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbAlerta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbAlerta.setText("(*) Campos obrigatórios.");
        lbAlerta.setPreferredSize(new java.awt.Dimension(280, 14));
        panelAlerta.add(lbAlerta);

        panelInfo.add(panelAlerta);

        panelHolderInside.add(panelInfo);

        panelHolder.add(panelHolderInside);

        jPanel1.add(panelHolder);

        btCadastrar.setText("Cadastrar");
        btCadastrar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btCadastrarActionPerformed(evt);
            }
        });
        panelBotoes.add(btCadastrar);

        btLimpar.setText("Limpar");
        btLimpar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btLimparActionPerformed(evt);
            }
        });
        panelBotoes.add(btLimpar);

        jPanel1.add(panelBotoes);

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void btSelecionarCarroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSelecionarCarroActionPerformed
        buscaCarroModal dlg = new buscaCarroModal((JFrame)SwingUtilities.getWindowAncestor(this), true);
        placa = dlg.showDiag();
        codigoCliente = dlg.codCliente();

        if(placa != null)
        {
            String cod;
            String nome;
            cod = ""+codigoCliente;
            ConexaoBd bd = new ConexaoBd();
            nome = bd.buscaNomeCliente(true, cod);
           
            lbCarroText.setText(placa);
            lbCarroText.setForeground(Color.black);
            lbClienteText.setText(nome);
            //buscar dono atual do carro
        }

        else
            lbCarroText.setForeground(Color.red);
    }//GEN-LAST:event_btSelecionarCarroActionPerformed

    private void lServicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lServicosMouseClicked
        if(evt.getClickCount() == 2)
        {
            int index = lServicos.locationToIndex(evt.getPoint());

            if(index >= 0)
            {
                Servico servicoTempAntes = (Servico) lServicos.getModel().getElementAt(index);

                formServicoModal dlg = new formServicoModal((JFrame)SwingUtilities.getWindowAncestor(this), true, servicoTempAntes, true);
                Servico servicoTempDepois = dlg.showDiag();

                if(servicoTempDepois != null)
                {
                    DefaultListModel d = (DefaultListModel) lServicos.getModel();
                    d.set(d.indexOf(servicoTempAntes), servicoTempDepois);
                }

                calcularTotal();
                lbValorText.setText("R$ " + Integer.toString(valor) + ",00");
            }
        }
    }//GEN-LAST:event_lServicosMouseClicked

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        formServicoModal dlg = new formServicoModal((JFrame)SwingUtilities.getWindowAncestor(this), true);
        Servico servicoTemp = dlg.showDiag();

        if(servicoTemp != null)
        {
            DefaultListModel d = (DefaultListModel) lServicos.getModel();
            d.addElement(servicoTemp);

            calcularTotal();
            lbValorText.setText("R$ " + Integer.toString(valor) + ",00");
        }
    }//GEN-LAST:event_btAddActionPerformed

    private void btDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDelActionPerformed
        int selectedIndex = lServicos.getSelectedIndex();
        DefaultListModel d = (DefaultListModel) lServicos.getModel();

        if(selectedIndex != -1)
        {
            d.remove(selectedIndex);
            calcularTotal();
            lbValorText.setText("R$ " + Integer.toString(valor) + ",00");
        }
    }//GEN-LAST:event_btDelActionPerformed

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
        if(tfKmEntrada.getText().equals("") || placa == null) // || clienteCpfCnpj == null
        {
            lbAlerta.setForeground(Color.red);

            if(tfKmEntrada.getText().equals(""))
                lbKmEntrada.setForeground(Color.red);
            else
                lbKmEntrada.setForeground(Color.black);

            if(placa == null)
                lbCarroText.setForeground(Color.red);
            else
                lbCarroText.setForeground(Color.black);

            //if cliente não informado etc
        }

        else
        {
            // tira alertas
            lbAlerta.setForeground(Color.black);
            lbKmEntrada.setForeground(Color.black);
            lbCarroText.setForeground(Color.black);
            lbClienteText.setForeground(Color.black);

            // cria objeto os temporário
            os = new OrdemDeServico();

            
            os.setPlacaCarro(placa);
            os.setCpfCliente(clienteCpfCnpj);
            os.setKmEntrada(tfKmEntrada.getText());
            os.setEstado(cboxEstado.getSelectedItem().toString());
            os.setCodCliente(codigoCliente);
            os.setValorTotal(""+valor);

            // copia lista da interface pra lista do objeto
            DefaultListModel d = (DefaultListModel) lServicos.getModel();

            while(!d.isEmpty())
            {
                os.servicos.add((Servico) d.remove(0));
            }
            
            ConexaoBd bd = new ConexaoBd();
            bd.cadastraOS(os, funcionario);

            // reseta campos da interface
            tfKmEntrada.setText("");
            lbCarroText.setText("NÃO INFORMADO");
            placa = null;
            lbClienteText.setText("NÃO INFORMADO");
            clienteCpfCnpj = null;
            //cboxTipo.setSelectedIndex(0);

            JOptionPane.showMessageDialog(null, "Ordem de serviço cadastrada com sucesso!");
        }
    }//GEN-LAST:event_btCadastrarActionPerformed

    private void btLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparActionPerformed
        lbAlerta.setForeground(Color.black);
        lbKmEntrada.setForeground(Color.black);
        lbCarroText.setForeground(Color.black);
        lbClienteText.setForeground(Color.black);

        tfKmEntrada.setText("");
        lbCarroText.setText("NÃO INFORMADO");
        placa = null;
        lbClienteText.setText("NÃO INFORMADO");
        clienteCpfCnpj = null;
        //cboxTipo.setSelectedIndex(0);

        DefaultListModel d = (DefaultListModel) lServicos.getModel();

        while(!d.isEmpty())
        {
            d.remove(0);
        }
    }//GEN-LAST:event_btLimparActionPerformed

    private void tfKmEntradaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKmEntradaKeyTyped
        // TODO add your handling code here:
        if(tfKmEntrada.getText().length() > 9 || !(Character.isDigit(evt.getKeyChar()) || evt.getKeyChar() == '.'))
            evt.consume();
        
        
    }//GEN-LAST:event_tfKmEntradaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdd;
    private javax.swing.JButton btCadastrar;
    private javax.swing.JButton btDel;
    private javax.swing.JButton btLimpar;
    private javax.swing.JButton btSelecionarCarro;
    private javax.swing.JComboBox cboxEstado;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lServicos;
    private javax.swing.JLabel lbAlerta;
    private javax.swing.JLabel lbCarroText;
    private javax.swing.JLabel lbCliente;
    private javax.swing.JLabel lbClienteText;
    private javax.swing.JLabel lbEstado;
    private javax.swing.JLabel lbKmEntrada;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbValor;
    private javax.swing.JLabel lbValorText;
    private javax.swing.JPanel panelAlerta;
    private javax.swing.JPanel panelBotoes;
    private javax.swing.JPanel panelBotoesLista;
    private javax.swing.JPanel panelCarro;
    private javax.swing.JPanel panelCliente;
    private javax.swing.JPanel panelEstado;
    private javax.swing.JPanel panelHolder;
    private javax.swing.JPanel panelHolderInside;
    private javax.swing.JPanel panelInfo;
    private javax.swing.JPanel panelKmEntrada;
    private javax.swing.JPanel panelServicos;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JPanel panelValor;
    private javax.swing.JTextField tfKmEntrada;
    // End of variables declaration//GEN-END:variables
    private String placa;
    private String clienteCpfCnpj;
    private int codigoCliente;
    private int valor;
    
    private OrdemDeServico os;
    private Funcionario funcionario;
}
