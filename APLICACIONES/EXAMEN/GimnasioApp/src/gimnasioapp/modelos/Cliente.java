package gimnasioapp.modelos;

public class Cliente {
    private int id;
    private String nombre;
    private String cedula;
    private String telefono;
    private String correo;
    private String direccion;

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    // ✅ Este método se usa en JComboBox para mostrar el nombre del cliente
    @Override
    public String toString() {
        return nombre;
    }


}
