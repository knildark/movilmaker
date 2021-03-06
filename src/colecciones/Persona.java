package colecciones;

import java.io.FileNotFoundException;

import com.itextpdf.text.DocumentException;

public abstract class Persona {

	private String rut;
	private String idCompania;
	private String nombre1;
	private String nombre2;
	private String apellido1;
	private String apellido2;
	private int fonoCel;
	private int fonoFijo;
	private String email;
	private String password;
	private int tipo = 0; // 0 = Admin, 1 = Cliente ... n = Algo m�s
	
	// CONSTRUCTOR
	/**
	 * @param rut
	 * @param idCompania
	 * @param nombre1
	 * @param nombre2
	 * @param apellido1
	 * @param apellido2
	 * @param fonoCel
	 * @param fonoFijo
	 * @param email
	 * @param tipo
	 */
	public Persona(String rut, String idCompania, String nombre1, String nombre2, String apellido1, String apellido2,
			int fonoCel, int fonoFijo, String email, int tipo) {
		this.rut = rut;
		this.nombre1 = nombre1;
		this.nombre2 = nombre2;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fonoCel = fonoCel;
		this.fonoFijo = fonoFijo;
		this.email = email;
		this.idCompania = idCompania;
		this.tipo = tipo;
	}

	/////////////////////////// * GETTERS & SETTERS *////////////////////////////////////
	
	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getIdCompania() {
		return idCompania;
	}

	public void setIdCompania(String idCompania) {
		this.idCompania = idCompania;
	}

	public String getNombre1() {
		return nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public int getFonoCel() {
		return fonoCel;
	}

	public void setFonoCel(int fonoCel) {
		this.fonoCel = fonoCel;
	}

	public int getFonoFijo() {
		return fonoFijo;
	}

	public void setFonoFijo(int fonoFijo) {
		this.fonoFijo = fonoFijo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Funcion de tipo abstract que genera un reporte utilizando la sobrescritura de metodos
	 * @param rutEmpresa Identificador de tipo INT de la empresa
	 * @param nombre Nombre de la empresa de tipo String
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	public abstract void reporte (String rutEmpresa, String nombre) throws FileNotFoundException, DocumentException;
		
}
