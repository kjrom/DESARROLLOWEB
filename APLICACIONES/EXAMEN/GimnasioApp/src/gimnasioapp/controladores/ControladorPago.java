package gimnasioapp.controladores;

import gimnasioapp.gimnasioDAL.PagoDAL;
import gimnasioapp.modelos.Pago;
import gimnasioapp.vistas.componentes.PanelFormularioPago;
import gimnasioapp.vistas.componentes.PanelTablaPago;

import java.util.List;

public class ControladorPago {
    private final PagoDAL pagoDAL;
    private PanelFormularioPago panelFormulario;
    private PanelTablaPago panelTabla;

    public ControladorPago(PagoDAL pagoDAL) {
        this.pagoDAL = pagoDAL;
    }

    public void setPanelFormulario(PanelFormularioPago panelFormulario) {
        this.panelFormulario = panelFormulario;
    }

    public void setPanelTabla(PanelTablaPago panelTabla) {
        this.panelTabla = panelTabla;
    }

    public void guardarPago(Pago pago) {
        try {
            boolean exito;
            if (pago.getId() == 0) {
                exito = pagoDAL.insertar(pago);
            } else {
                exito = pagoDAL.actualizar(pago);
            }

            if (exito) {
                if (panelFormulario != null) {
                    panelFormulario.mostrarMensaje("Pago guardado correctamente.");
                    panelFormulario.limpiarCampos();
                }
                actualizarTabla();
            } else {
                if (panelFormulario != null) {
                    panelFormulario.mostrarMensaje("Error al guardar el pago.");
                }
            }
        } catch (Exception e) {
            if (panelFormulario != null) {
                panelFormulario.mostrarMensaje("Excepción: " + e.getMessage());
            }
        }
    }

    public void eliminarPago(int id) {
        try {
            boolean exito = pagoDAL.eliminar(id);
            if (panelFormulario != null) {
                panelFormulario.mostrarMensaje(
                    exito ? "Pago eliminado correctamente." : "Error al eliminar el pago."
                );
            }
            if (exito) {
                actualizarTabla();
            }
        } catch (Exception e) {
            if (panelFormulario != null) {
                panelFormulario.mostrarMensaje("Excepción: " + e.getMessage());
            }
        }
    }

    public void cargarPagoEnFormulario(Pago pago) {
        if (panelFormulario != null) {
            panelFormulario.setDatosPago(pago);
        }
    }

    public void actualizarTabla() {
        try {
            if (panelTabla != null) {
                List<Pago> pagos = pagoDAL.obtenerTodos();
                panelTabla.cargarPagos(pagos);
            }
        } catch (Exception e) {
            if (panelFormulario != null) {
                panelFormulario.mostrarMensaje("Error al actualizar la tabla: " + e.getMessage());
            }
        }
    }
}
