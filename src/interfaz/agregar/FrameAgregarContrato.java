package interfaz.agregar;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import colecciones.Cliente;
import colecciones.Compania;
import colecciones.Contrato;
import colecciones.Equipo;
import colecciones.Plan;
import extras.Database;
import interfaz.FrameInterfaz;

public class FrameAgregarContrato extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Compania datosEmpresa, Cliente cliente) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FrameAgregarContrato frame = new FrameAgregarContrato(datosEmpresa, cliente);
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
	// RECIBE DATOS DE LA EMPRESA Y DATOS DE CLIENTE (cliente para obtencion de
	// nombre mostrado en el panel y rut para su busqueda)
	public FrameAgregarContrato(Compania datosEmpresa, Cliente cliente) {
		setResizable(false);
		setTitle("Agregar Contrato");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 532, 354);
		contentPane = new JPanel();
		contentPane.setBorder(
				new TitledBorder(null, "Datos Contrato", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 53, 509, 173);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblPlanes = new JLabel("Planes");
		lblPlanes.setBounds(9, 6, 46, 14);
		panel.add(lblPlanes);

		JLabel lblEquipos = new JLabel("Equipos");
		lblEquipos.setBounds(185, 6, 46, 14);
		panel.add(lblEquipos);

		JList<String> listEquipos = new JList<String>();
		listEquipos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listEquipos.setBounds(198, 25, 193, 137);
		panel.add(listEquipos);

		JList<String> listPlanes = new JList<String>();
		listPlanes.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listPlanes.setBounds(20, 25, 150, 137);
		panel.add(listPlanes);

		// BOTON PARA MOSTRAR PLANES Y EQUIPOS DESDE LA BASE DE DATOS EN LAS
		// JLists respectivas
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnMostrar.setBounds(403, 24, 96, 23);
		panel.add(btnMostrar);
		btnMostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// INSTANCIA PARA JList Planes
				DefaultListModel<String> model1 = new DefaultListModel<String>();
				// INSTANCIA PARA JList Moviles
				DefaultListModel<String> model2 = new DefaultListModel<String>(); 
				// RECORRE PLANES DE COMPANIA
				
				model1 = datosEmpresa.getPlanes().listarAInterfazAgregar(model1);
				
				model2 = datosEmpresa.getEquipos().listarAInterfazAgregar(model2);

				// HACE A LOS ELEMENTOS VISIBLES
				listEquipos.setModel(model2);
				listPlanes.setModel(model1);
				btnMostrar.setEnabled(false);

			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(201, 250, 209, 47);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblAviso = new JLabel("");
		lblAviso.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAviso.setBounds(10, 300, 201, 14);
		contentPane.add(lblAviso);

		JComboBox<Object> comboBoxMeses = new JComboBox<Object>();
		comboBoxMeses.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		comboBoxMeses.setBounds(20, 264, 88, 20);
		contentPane.add(comboBoxMeses);

		// DATOS INGRESADOS SE ENVIAN A CLASE Cliente, metodo : crearContrato y
		// luego a la DB
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int numCuotas;
				Plan plan;
				Equipo equipo;
				Contrato contratoNuevo = null;

				// Recorre planes de compania
				for (int i = 0; i < datosEmpresa.getPlanes().size(); i++) 
					// Recorre equipos de compania
					for (int j = 0; j < datosEmpresa.getEquipos().getLista().size(); j++) 
						// Si estan Seleccionados en LA LISTA, 1 Plan y 1 Equipo
						// , se procede
						if (listPlanes.isSelectedIndex(i) && listEquipos.isSelectedIndex(j)) {
							plan = datosEmpresa.getPlanes().buscar(listPlanes.getSelectedValue().toString());
							equipo = datosEmpresa.getEquipos().buscarEquipo(listEquipos.getSelectedValue().toString());
							numCuotas = comboBoxMeses.getSelectedIndex() + 1;

							// Se crea un nuevo contrato en: CLIENTE OBTENIDO A
							// ESTA CLASE/VENTANA, POR REFERENCIA linea 44
							/*System.out.println(""+plan.getNombre());
							System.out.println(""+equipo.getNombre());*/
							contratoNuevo = cliente.crearContrato(cliente ,plan, equipo, numCuotas, datosEmpresa);

							// Cuadro de texto informa exito en asignacion de
							// contrato
							JOptionPane.showMessageDialog(null, "Contrato asignado exitosamente", "Aviso",
									JOptionPane.INFORMATION_MESSAGE);

							// Se escribira contrato en la BD
							try {
								// Creacion de conexion a base de datos
								Database.ingresarContratoBD(contratoNuevo);
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								System.err.println("Contrato no se pudo escribir en la Base de Datos.\n"
										+ "\nDetalles de la excepción:");
								System.err.println(e2.getClass().getName() + ": " + e2.getMessage());
							}
							dispose();
						}
			}
		});

		btnNewButton.setBounds(10, 11, 89, 23);
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(110, 11, 89, 23);
		panel_1.add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("Escoja cantidad de cuotas (1-12)");
		lblNewLabel.setBounds(10, 237, 191, 14);
		contentPane.add(lblNewLabel);

		JLabel labelCliente = new JLabel(cliente.getNombre1() + " " + cliente.getNombre2() + " "
				+ cliente.getApellido1() + " " + cliente.getApellido2());
		labelCliente.setBounds(10, 28, 304, 14);
		contentPane.add(labelCliente);
	}
}
