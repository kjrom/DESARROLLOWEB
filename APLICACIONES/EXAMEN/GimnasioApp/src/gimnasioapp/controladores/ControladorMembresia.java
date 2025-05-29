package gimnasioapp.controladores;

import gimnasioapp.gimnasioDAL.ClienteDAL;
import gimnasioapp.gimnasioDAL.MembresiaDAL;
import gimnasioapp.gimnasioDAL.PlanDAL;
import gimnasioapp.modelos.Cliente;
import gimnasioapp.modelos.Membresia;
import gimnasioapp.modelos.Plan;
import gimnasioapp.vistas.componentes.PanelFormulario;
import gimnasioapp.vistas.componentes.PanelTabla;

import javax.swing.*;
import java.sql.Connection;
import java.util.List;

public class ControladorMembresia {

    private final Connection conn;
    private PanelFormulario panelFormulario;
    private PanelTabla panelTabla;

    public ControladorMembresia(Connection conn) {
        this.conn = conn;
    }

    public void setPanelFormulario(PanelFormulario panelFormulario) {
        this.panelFormulario = panelFormulario;
        cargarClientes();
        cargarPlanes();

        // Asocia los botones
        panelFormulario.getBtnGuardar().addActionListener(e -> guardarMembresia());
        panelFormulario.getBtnLimpiar().addActionListener(e -> panelFormulario.limpiarFormulario());
    }

    public void setPanelTabla(PanelTabla panelTabla) {
        this.panelTabla = panelTabla;
        cargarMembresias(); // Ya actualiza con nombres
    }

    public void cargarClientes() {
        ClienteDAL clienteDAL = new ClienteDAL(conn);
        List<Cliente> clientes = clienteDAL.obtenerTodos();
        panelFormulario.cargarClientes(clientes);
    }

    public void cargarPlanes() {
        PlanDAL planDAL = new PlanDAL(conn);
        List<Plan> planes = planDAL.obtenerTodos();
        panelFormulario.cargarPlanes(planes);
    }

    public void cargarPlanPorCliente(int idCliente) {
        MembresiaDAL membresiaDAL = new MembresiaDAL(conn);
        Plan plan = membresiaDAL.obtenerPlanActualPorCliente(idCliente);
        if (plan != null) {
            panelFormulario.setPlanSeleccionado(plan.getNombre());
        }
    }

    // ✅ Nuevo método que usa la versión con nombres para cargar a la tabla
    public void cargarMembresias() {
        MembresiaDAL membresiaDAL = new MembresiaDAL(conn);
        List<Object[]> membresiasConNombres = membresiaDAL.obtenerMembresiasConNombres();
        panelTabla.cargarMembresiasConNombres(membresiasConNombres);
    }

    public void guardarMembresia() {
        try {
            Cliente cliente = (Cliente) panelFormulario.getComboClientes().getSelectedItem();
            String nombrePlan = (String) panelFormulario.getComboPlanes().getSelectedItem();
            String fechaInicio = panelFormulario.getTxtFechaInicio().getText().trim();
            String fechaFin = panelFormulario.getTxtFechaFin().getText().trim();
            String estadoPago = (String) panelFormulario.getComboEstadoPago().getSelectedItem();

            if (cliente == null || nombrePlan == null || fechaInicio.isEmpty() || fechaFin.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Completa todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            PlanDAL planDAL = new PlanDAL(conn);
            Plan plan = planDAL.obtenerPorNombre(nombrePlan);
            if (plan == null) {
                JOptionPane.showMessageDialog(null, "No se encontró el plan seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Membresia membresia = new Membresia();
            membresia.setIdCliente(cliente.getId());
            membresia.setIdPlan(plan.getId());
            membresia.setFechaInicio(fechaInicio);
            membresia.setFechaFin(fechaFin);
            membresia.setEstadoPago(estadoPago);

            MembresiaDAL membresiaDAL = new MembresiaDAL(conn);
            membresiaDAL.insertar(membresia);

            JOptionPane.showMessageDialog(null, "Membresía guardada correctamente.");

            panelFormulario.limpiarFormulario();
            cargarMembresias(); // Recarga con nombres también
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar membresía: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
