package controllers;

import entities.Fichacomponente;
import controllers.util.JsfUtil;
import controllers.util.PaginationHelper;
import entities.Componente;
import entities.Ficha;
import entities.Instructor;
import entities.Usuario;
import facades.ComponenteFacade;
import facades.FichaFacade;
import facades.FichacomponenteFacade;
import facades.InstructorFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
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

@Named("fichacomponenteController")
@SessionScoped
public class FichacomponenteController implements Serializable {

    private Fichacomponente current;
    private DataModel items = null;
    @EJB
    private facades.FichacomponenteFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    @Inject
    private Mensajes mensaje;
   
    @EJB
    FichaFacade fichaFacade;
    @EJB
    ComponenteFacade componenteFacade;
    @EJB
    InstructorFacade instructorFacade;
    private Componente componente;
    private Ficha ficha;
    private Instructor instructor;
    //private FichaComponente fichaComponente;
    private List<Fichacomponente> listaModuloFicha;

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

    public Componente getComponente() {
        return componente;
    }

    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<Fichacomponente> getListaModuloFicha() {
        return listaModuloFicha;
    }

    public void setListaModuloFicha(List<Fichacomponente> listaModuloFicha) {
        this.listaModuloFicha = listaModuloFicha;
    }

    public List<Fichacomponente> getListaModuloInstructor() {
        return listaModuloInstructor;
    }

    public void setListaModuloInstructor(List<Fichacomponente> listaModuloInstructor) {
        this.listaModuloInstructor = listaModuloInstructor;
    }
    private List<Fichacomponente> listaModuloInstructor;
    
    @PostConstruct
    public void init(){
        componente =new Componente();
        ficha = new Ficha();
        instructor=new Instructor();
        current= new Fichacomponente();
        listaModuloFicha=new ArrayList();
        listaModuloInstructor=new ArrayList();
    }
    public void registrarFichaComponente(){
        try {
              current.setIdComponente(componenteFacade.find(componente.getIdComponente()));
        current.setIdFicha(fichaFacade.find(ficha.getIdFicha()));
        current.setIdInstructor(instructorFacade.find(instructor.getIdInstructor()));
        ejbFacade.create(current);
        mensaje.setMensaje("Mensaje('Exito', 'Se ha asignado el instructor al componente!', 'success');");
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            mensaje.setMensaje("Mensaje('Error', 'Error al asignar el instructor', 'error');");
            
        }
       current=new Fichacomponente();
    }
public void consultarModulosFicha(){
    listaModuloFicha=ejbFacade.listaModulosFicha(ficha);
}
public List<Fichacomponente> consultarModulosInstructor(){
    Usuario instructor=(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("datosPersona");
    listaModuloInstructor=ejbFacade.listaModulosInstructor(instructor);
    return listaModuloInstructor;
}
    
    public FichacomponenteController() {
    }

    public Fichacomponente getSelected() {
        if (current == null) {
            current = new Fichacomponente();
            selectedItemIndex = -1;
        }
        return current;
    }

    private FichacomponenteFacade getFacade() {
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
        current = (Fichacomponente) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Fichacomponente();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FichacomponenteCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Fichacomponente) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FichacomponenteUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Fichacomponente) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FichacomponenteDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
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

    public Fichacomponente getFichacomponente(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Fichacomponente.class)
    public static class FichacomponenteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FichacomponenteController controller = (FichacomponenteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "fichacomponenteController");
            return controller.getFichacomponente(getKey(value));
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
            if (object instanceof Fichacomponente) {
                Fichacomponente o = (Fichacomponente) object;
                return getStringKey(o.getIdFichaComponente());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Fichacomponente.class.getName());
            }
        }

    }

}
