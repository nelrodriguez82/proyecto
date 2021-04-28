package controllers;

import entities.Programaformacion;
import controllers.util.JsfUtil;
import controllers.util.PaginationHelper;
import facades.ProgramaformacionFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import mensajes.Mensajes;

@Named("programaformacionController")
@SessionScoped
public class ProgramaformacionController implements Serializable {

    private Programaformacion current;
    private DataModel items = null;
    @EJB
    private facades.ProgramaformacionFacade ejbFacade;
    @Inject
    private Mensajes mensaje;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ProgramaformacionController() {
    }

    public Programaformacion getSelected() {
        if (current == null) {
            current = new Programaformacion();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ProgramaformacionFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Programaformacion) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Programaformacion();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
          
        try {
            getFacade().create(current);
           
             String  mostrar=ResourceBundle.getBundle("/Bundle").getString("ProgramaformacionCreated");
           mensaje.setMensaje("Mensaje('Correcto!', '"+ mostrar+"', 'success');");
            return prepareCreate();
        } catch (Exception e) {
            
             mensaje.setMensaje("Mensaje('Error!', 'Error al registrar', 'error');");
            return null;
        }
    }

    public String prepareEdit() {
        current = (Programaformacion) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        
        try {
            getFacade().edit(current);
           String mostrar=ResourceBundle.getBundle("/Bundle").getString("ProgramaformacionUpdated");
            mensaje.setMensaje("Mensaje('Correcto!', '"+ mostrar+"', 'success');");
            return "List";
        } catch (Exception e) {
            mensaje.setMensaje("Mensaje('Error!', 'Error al actualizar', 'error');");
            return null;
        }
    }

    public String destroy() {
        current = (Programaformacion) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
           String mostrar=ResourceBundle.getBundle("/Bundle").getString("ProgramaformacionDeleted");
         mensaje.setMensaje("Mensaje('Correcto!', '"+ mostrar+"', 'success');");
        } catch (Exception e) {
             mensaje.setMensaje("Mensaje('Error!', 'Error al eliminar', 'error');");
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Programaformacion getProgramaformacion(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Programaformacion.class)
    public static class ProgramaformacionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProgramaformacionController controller = (ProgramaformacionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "programaformacionController");
            return controller.getProgramaformacion(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Programaformacion) {
                Programaformacion o = (Programaformacion) object;
                return getStringKey(o.getIdProgramaFormacion());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Programaformacion.class.getName());
            }
        }

    }
    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

}
