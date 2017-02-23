
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
import java.awt.Color;
//Esto es una prueba de comit
@SuppressWarnings("serial")
public class Principal extends JFrame {

	private JPanel contentPane;
	String trees[];
	String usuario;
	Principal p;
	

	public Principal(String user) {
		setTitle("Taqueria -Bienvenido- : "+user);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 383);
		setLocationRelativeTo(null);
		p=this;
		usuario=user;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 156, 334);
		contentPane.add(scrollPane);
		
		trees=new String[]{"Punto de venta","Agregar Producto","Lista de Productos","Agregar usuario"};
		JTree tree = new JTree();
		tree.setFocusable(false);
		addtree(tree);
		tree.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setViewportView(tree);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setToolTipText("");
		desktopPane.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(169, 169, 169), new Color(105, 105, 105), new Color(230, 230, 250), new Color(0, 0, 0)));
		desktopPane.setBounds(171, 5, 798, 331);
		contentPane.add(desktopPane);
		
	
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				switch(tree.getSelectionPath().getLastPathComponent().toString())
				{
				case "Agregar Producto":
					insertprod fip = new insertprod();
					fip.setVisible(true);
					desktopPane.add(fip);
					tree.setSelectionRow(tree.getMinSelectionRow()-1);
					break;
				case "Punto de venta":
					habilitar(false);
					PuntoVenta pv=new PuntoVenta(p);
					pv.setVisible(true);
					tree.setSelectionRow(tree.getMinSelectionRow()-1);
					break;
				case "Lista de Productos":
					Productos prod=new Productos();
					prod.setVisible(true);
					desktopPane.add(prod);
					tree.setSelectionRow(tree.getMinSelectionRow()-2);
					break;
				case "Agregar usuario":
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
				}
				
			}
		});
		
		
	}

	private void addtree(JTree tree) {
		tree.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("Taqueria") {
					{
						DefaultMutableTreeNode node_1;
						node_1 = new DefaultMutableTreeNode("Ventas");
							node_1.add(new DefaultMutableTreeNode(trees[0]));
						add(node_1);
						node_1 = new DefaultMutableTreeNode("Inventario");
							node_1.add(new DefaultMutableTreeNode(trees[1]));
							node_1.add(new DefaultMutableTreeNode(trees[2]));
						add(node_1);
							node_1 = new DefaultMutableTreeNode("Usuarios");
							node_1.add(new DefaultMutableTreeNode(trees[3]));
							node_1.add(new DefaultMutableTreeNode("Lista de usuarios"));
						add(node_1);
							node_1 = new DefaultMutableTreeNode("Reportes");
							node_1.add(new DefaultMutableTreeNode("Reporte de Ventas"));
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
