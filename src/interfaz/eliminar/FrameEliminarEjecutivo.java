package interfaz.eliminar;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import colecciones.Ejecutivo;
import colecciones.Compania;
import extras.Database;
import interfaz.FrameInterfaz;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class FrameEliminarEjecutivo extends JFrame {

	private JPanel contentPane;
	private JTextField textRut;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Compania datosEmpresa) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FrameEliminarEjecutivo frame = new FrameEliminarEjecutivo(datosEmpresa);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameEliminarEjecutivo(Compania datosEmpresa) {
		ArrayList<Ejecutivo> listaEjecutivos = datosEmpresa.getEjecutivos().getLista();
		setResizable(false);
		setTitle("Eliminar administrador");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 382, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Eliminaci\u00F3n de administrador", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 21, 347, 75);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblIngreseRut = new JLabel("Ingrese rut del administrador a eliminar");
		lblIngreseRut.setBounds(21, 11, 187, 14);
		panel.add(lblIngreseRut);

		textRut = new JTextField();
		textRut.setBounds(21, 36, 151, 20);
		panel.add(textRut);
		textRut.setColumns(10);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String rut;
				rut = textRut.getText();

				// Llama a metodo en clase Compa�ia para eliminar Cliente
				if (datosEmpresa.getEjecutivos().eliminarEjecutivo(rut) == true) {
					// Si el cliente se elimino exitosamente se eliminar�
					// cliente en la BD
					
					try {
						// Creacion de conexion a base de datos
						Database.eliminarEjecutivoBD(rut);
						// Cuadro de dialogo, que informa eliminacion exitosa
						JOptionPane.showMessageDialog(null, "El Ejecutivo ha sido eliminado", "Aviso",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						System.err.println(
								"Ejecutivo no se pudo eliminar de la Base de Datos.\n" + "\nDetalles de la excepci�n:");
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
					}
				} else
					// Sino, se informa que el cliente no existe
					System.err.println("Ejecutivo no existe...");
			}
		});

		btnEliminar.setBounds(227, 31, 110, 31);
		panel.add(btnEliminar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 107, 224, 143);
		contentPane.add(scrollPane);

		JList list = new JList();
		scrollPane.setViewportView(list);
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultListModel listmodel = new DefaultListModel();
				for (int i = 0; i < listaEjecutivos.size(); i++) {
					listmodel.addElement(i+1 + "- Rut: " + listaEjecutivos.get(i).getRut());
					list.setModel(listmodel);
				}
			}
		});
		btnMostrar.setBounds(260, 126, 97, 23);
		contentPane.add(btnMostrar);

		JLabel lblListaClientes = new JLabel("Lista admin en DB");
		lblListaClientes.setBounds(260, 107, 136, 14);
		contentPane.add(lblListaClientes);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(260, 227, 97, 23);
		contentPane.add(btnVolver);

	}
}
