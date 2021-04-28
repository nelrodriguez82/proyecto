package controllers;

import entities.Grupotrabajo;
import controllers.util.JsfUtil;
import controllers.util.PaginationHelper;
import entities.Entregable;
import entities.Ficha;
import entities.Grupoaprendiz;
import entities.Usuario;
import facades.AprendizFacade;
import facades.FichaFacade;
import facades.GrupoaprendizFacade;
import facades.GrupotrabajoFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import mensajes.Mensajes;

@Named("grupotrabajoController")
@SessionScoped
public class GrupotrabajoController implements Serializable {

    private Grupotrabajo current;
    
    private DataModel items = null;
    @EJB
    private facades.GrupotrabajoFacade ejbFacade;
    @EJB
    private FichaFacade fichaFacade;
    @EJB
    private AprendizFacade aprendizfFacade;
    @EJB
    GrupoaprendizFacade grupoAprendizFacade;
    
    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }
    @Inject
    Mensajes mensaje;
    Grupoaprendiz grupoAprendiz;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    Entregable entregable;

    public Entregable getEntregable() {
        return entregable;
    }

    public void setEntregable(Entregable entregable) {
        this.entregable = entregable;
    }

   
    private List<Integer> checkAprendiz = new ArrayList<>();
    private Ficha ficha;
    private List<Usuario> listaAprendicesFicha;
    private List<Entregable> listaEntregablesFicha;
    private List<Grupotrabajo> listaGruposFicha;
    private List<Usuario> listaAprendicesGrupo;

    public List<Usuario> getListaAprendicesGrupo() {
        return listaAprendicesGrupo;
    }

    public void setListaAprendicesGrupo(List<Usuario> listaAprendicesGrupo) {
        this.listaAprendicesGrupo = listaAprendicesGrupo;
    }

    public List<Entregable> getListaEntregablesFicha() {
        return listaEntregablesFicha;
    }

    public void setListaEntregablesFicha(List<Entregable> listaEntregablesFicha) {
        this.listaEntregablesFicha = listaEntregablesFicha;
    }

    @PostConstruct
    public void init() {
        ficha = new Ficha();
        entregable=new Entregable();
        listaAprendicesFicha = new ArrayList();
        listaAprendicesGrupo = new ArrayList();
        listaEntregablesFicha = new ArrayList();
        listaGruposFicha = new ArrayList();
    }

    public List<Grupotrabajo> getListaGruposFicha() {
        return listaGruposFicha;
    }

    public void setListaGruposFicha(List<Grupotrabajo> listaGruposFicha) {
        this.listaGruposFicha = listaGruposFicha;
    }

    public void seleccionarAprendiz(ValueChangeEvent event) {
        String[] check = (String[]) event.getNewValue();
        for (int i = 0; i < check.length; i++) {
            checkAprendiz.add(Integer.valueOf((check[i])));
        }
    }

    public void seleccionados() {

        for (Integer it : checkAprendiz) {
            for (int i = 0; i < listaAprendicesFicha.size(); i++) {

                if (it.equals(listaAprendicesFicha.get(i).getIdUsuario())) {
                    System.out.println("Seleccionado " + getListaAprendicesFicha().get(i).getNombres());
                }
            }
        }
        checkAprendiz = new ArrayList<>();
    }

    public GrupotrabajoController() {
    }

    public Grupotrabajo getSelected() {
        if (current == null) {
            current = new Grupotrabajo();
            selectedItemIndex = -1;
        }
        return current;
    }

    private GrupotrabajoFacade getFacade() {
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
        current = (Grupotrabajo) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
       // current = new Grupotrabajo();
       current.setNombreGrupo("");
        
        selectedItemIndex = -1;
        return "CrearGrupos";
    }

    public String create() {
        try {
            getFacade().create(current);
            for (Integer it : checkAprendiz) {
                for (int i = 0; i < listaAprendicesFicha.size(); i++) {

                    if (it.equals(listaAprendicesFicha.get(i).getIdUsuario())) {
                        grupoAprendiz =new Grupoaprendiz();
                        grupoAprendiz.setIdGrupo(current);
                        grupoAprendiz.setIdAprendiz(aprendizfFacade.find(listaAprendicesFicha.get(i).getIdUsuario()));
                        grupoAprendizFacade.create(grupoAprendiz);
                    }
                }
            }
            checkAprendiz = new ArrayList<>();
            String mostrar=ResourceBundle.getBundle("/Bundle").getString("GrupotrabajoCreated");
            mensaje.setMensaje("Mensaje('Correcto!', '"+ mostrar+"', 'success');");
           
            return prepareCreate();
        } catch (Exception e) {
           mensaje.setMensaje("Mensaje('Error!', 'Error al registrar el grupo', 'error');");
            return null;
        }
    }

    public String prepareEdit() {
        current = (Grupotrabajo) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("GrupotrabajoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Grupotrabajo) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("GrupotrabajoDeleted"));
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

    public Grupotrabajo getGrupotrabajo(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    public List<Integer> getCheckAprendiz() {
        return checkAprendiz;
    }

    public void setCheckAprendiz(List<Integer> checkAprendiz) {
        this.checkAprendiz = checkAprendiz;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public List<Usuario> getListaAprendicesFicha() {
        return listaAprendicesFicha;
    }

    public void setListaAprendicesFicha(List<Usuario> listaAprendicesFicha) {
        this.listaAprendicesFicha = listaAprendicesFicha;
    }

    @FacesConverter(forClass = Grupotrabajo.class)
    public static class GrupotrabajoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            GrupotrabajoController controller = (GrupotrabajoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "grupotrabajoController");
            return controller.getGrupotrabajo(getKey(value));
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
            if (object instanceof Grupotrabajo) {
                Grupotrabajo o = (Grupotrabajo) object;
                return getStringKey(o.getIdGrupoTrabajo());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Grupotrabajo.class.getName());
            }
        }

    }
 public String verDetalle(Grupotrabajo grupoTrabajo){
     current=ejbFacade.find(grupoTrabajo.getIdGrupoTrabajo());
     //current=grupoTrabajo;
     consultarAprendizGrupo();
     return "VerDetalle";
 }
 public String volver()
 {
 return "List";
 }
    public List<Usuario> consultarAprendizFicha() {
        listaAprendicesFicha = fichaFacade.consultarFichaAprendiz(ficha);
        return listaAprendicesFicha;
    }
    
    
    public List<Entregable> consultarEntregablesFicha() {
        listaEntregablesFicha = fichaFacade.consultarEntregableFicha(ficha);
        return listaEntregablesFicha;
    }
     public List<Grupotrabajo> listarGruposFicha() {
        listaGruposFicha = fichaFacade.consultarEntregables(ficha, entregable);
        return listaGruposFicha;
    }
public List<Usuario> consultarAprendizGrupo() {
        listaAprendicesGrupo = ejbFacade.consultarAprendicesGrupo(current);
        return listaAprendicesGrupo;
    }
}
