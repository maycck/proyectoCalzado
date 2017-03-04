
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

@SuppressWarnings("serial")
public class Principal extends JFrame {

	private JPanel contentPane;

	String usuario;
	Principal p;
	
//Miki no la cajeties 
	public Principal(String user) {
		setTitle("Sistema GPPCINCA   -Bienvenido- : "+user);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 600);
		setLocationRelativeTo(null);
		p=this;
		usuario=user;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 156, 334);
		contentPane.add(scrollPane,BorderLayout.WEST);
		
		
		JTree tree = new JTree();
		tree.setFocusable(false);
		addtree(tree);
		tree.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setViewportView(tree);
		tree.setOpaque(true);
		scrollPane.setOpaque(true);
		scrollPane.setBackground(new Color(132, 138, 120));
		tree.setBackground(new Color(132, 138, 120));
		contentPane.setBackground(new Color(132, 138, 120));
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setToolTipText("");
		desktopPane.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(169, 169, 169), new Color(105, 105, 105), new Color(230, 230, 250), new Color(0, 0, 0)));
		desktopPane.setBounds(171, 5, 798, 331);
		contentPane.add(desktopPane,BorderLayout.CENTER);
		desktopPane.setBackground(new Color(132, 138, 120));
		
	
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				switch(tree.getSelectionPath().getLastPathComponent().toString())
				{
				case "Capturar Clientes":
					CapturaClientes var = new CapturaClientes();
					var.setVisible(true);
					desktopPane.add(var);
					tree.setSelectionRow(tree.getMinSelectionRow()-1);
					break;
				case "Capturar Modelos":
					CapturaModelos prod=new CapturaModelos();
					prod.setVisible(true);
					desktopPane.add(prod);
					tree.setSelectionRow(tree.getMinSelectionRow()-2);
					break;
				case "Capturar Suelas":
					CapturaSuelas su=new CapturaSuelas();
					su.setVisible(true);
					desktopPane.add(su);
					tree.setSelectionRow(tree.getMinSelectionRow()-3);
					break;
				case "Capturar Tallas":
					CapturaTallas ta=new CapturaTallas();
					ta.setVisible(true);
					desktopPane.add(ta);
					tree.setSelectionRow(tree.getMinSelectionRow()-4);
					break;
				case "Capturar Colores":
					CapturaColores colo=new CapturaColores();
					colo.setVisible(true);
					desktopPane.add(colo);
					tree.setSelectionRow(tree.getMinSelectionRow()-5);
					break;
			}
				
			}
		});
		
		
	}

	private void addtree(JTree tree) {
		tree.setCellRenderer(new DefaultTreeCellRenderer()
        {
            public Component getTreeCellRendererComponent(JTree pTree,
                Object pValue, boolean pIsSelected, boolean pIsExpanded,
                boolean pIsLeaf, int pRow, boolean pHasFocus)
            {
	    DefaultMutableTreeNode node = (DefaultMutableTreeNode)pValue;
	    super.getTreeCellRendererComponent(pTree, pValue, pIsSelected,
                    pIsExpanded, pIsLeaf, pRow, pHasFocus);
	    setBackgroundNonSelectionColor(new Color(132, 138, 120));
	    setBackgroundSelectionColor(new Color(220,210,199));
	    setClosedIcon(new ImageIcon(getClass().getResource("img/X.png")));
	    setOpenIcon(new ImageIcon(getClass().getResource("img/a.gif")));
	 //   setIcon(new ImageIcon(getClass().getResource("img/a.gif")));
	    setLeafIcon(new ImageIcon(getClass().getResource("img/X.png")));
                if (node.isRoot())
                {}
	    else if (node.getChildCount() > 0)
	       {}
	    else if (pIsLeaf)
	    {}// setBackgroundSelectionColor(Color.green);
	    return (this);
	}
       });
		tree.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("Sistema de Calzado artesanal") {
					{
						DefaultMutableTreeNode node_1;
						node_1 = new DefaultMutableTreeNode("Inventario");
							node_1.add(new DefaultMutableTreeNode("Capturar Clientes"));
							node_1.add(new DefaultMutableTreeNode("Capturar Modelos"));
							node_1.add(new DefaultMutableTreeNode("Capturar Suelas"));
							node_1.add(new DefaultMutableTreeNode("Capturar Tallas"));
							node_1.add(new DefaultMutableTreeNode("Capturar Colores"));
						add(node_1);
						node_1 = new DefaultMutableTreeNode("");
							node_1.add(new DefaultMutableTreeNode(""));
							node_1.add(new DefaultMutableTreeNode(""));
						add(node_1);
							node_1 = new DefaultMutableTreeNode("");
							node_1.add(new DefaultMutableTreeNode(""));
							node_1.add(new DefaultMutableTreeNode(""));
						add(node_1);
							node_1 = new DefaultMutableTreeNode("");
							node_1.add(new DefaultMutableTreeNode(""));
						add(node_1);
					}
				}
			));
		
		
	}
	public void habilitar(boolean hab)
	{
		this.setVisible(hab);
		this.setEnabled(hab);
	}
}
