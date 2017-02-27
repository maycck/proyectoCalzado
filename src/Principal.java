
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

import java.awt.BorderLayout;
import java.awt.Color;

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
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setToolTipText("");
		desktopPane.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(169, 169, 169), new Color(105, 105, 105), new Color(230, 230, 250), new Color(0, 0, 0)));
		desktopPane.setBounds(171, 5, 798, 331);
		contentPane.add(desktopPane,BorderLayout.CENTER);
		
	
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				switch(tree.getSelectionPath().getLastPathComponent().toString())
				{
				case "Capturar insumos":
					CapturaInsumos var = new CapturaInsumos();
					var.setVisible(true);
					desktopPane.add(var);
					tree.setSelectionRow(tree.getMinSelectionRow()-1);
					break;
				case "Captura Modelos":
					CapturaModelos prod=new CapturaModelos();
					prod.setVisible(true);
					desktopPane.add(prod);
					tree.setSelectionRow(tree.getMinSelectionRow()-2);
					break;
	/*			case "Agregar usuario":
					addUser au=new addUser();
					au.setVisible(true);
					desktopPane.add(au);
					tree.setSelectionRow(tree.getMinSelectionRow()-1);
					break;
				case "Lista de usuarios":
					Usuarios us=new Usuarios();
					us.setVisible(true);
					desktopPane.add(us);
					tree.setSelectionRow(tree.getMinSelectionRow()-2);
					break;
				case "Reporte de Ventas":
					tree.setSelectionRow(tree.getMinSelectionRow()-1);
					break;
		*/		}
				
			}
		});
		
		
	}

	private void addtree(JTree tree) {
		tree.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("Sistema de Calzado artesanal") {
					{
						DefaultMutableTreeNode node_1;
						node_1 = new DefaultMutableTreeNode("Inventario");
							node_1.add(new DefaultMutableTreeNode("Capturar insumos"));
							node_1.add(new DefaultMutableTreeNode("Captura Modelos"));
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
