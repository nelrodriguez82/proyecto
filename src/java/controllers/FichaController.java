package controllers;

import entities.Ficha;
import controllers.util.JsfUtil;
import controllers.util.PaginationHelper;
import entities.Usuario;
import facades.FichaFacade;

import java.io.Serializable;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.FacesEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("fichaController")
@SessionScoped
public class FichaController implements Serializable {

    private Ficha current;
    private String[] grupoAprendiz;
    private List<Integer> checkAprendiz = new ArrayList<>();

    public void seleccionarAprendiz(ValueChangeEvent event){
        String[] check=(String[]) event.getNewValue();
        for(int i=0;i<check.length;i++){
            checkAprendiz.add(Integer.valueOf((check[i])));
        }
    }
public void seleccionados()
{
    
    
for(Integer it : checkAprendiz){
    for (int i = 0; i < listaAprendicesFicha.size(); i++) {
        
        if(it.equals(listaAprendicesFicha.get(i).getIdUsuario())){
            System.out.println("Seleccionado "+getListaAprendicesFicha().get(i).getNombres());
        }
    }
}
checkAprendiz= new ArrayList<>();
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
    private DataModel items = null;
    @EJB
    private facades.FichaFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    private Ficha ficha;
    private List<Usuario> listaAprendicesFicha;
    
    @PostConstruct
    public void init(){
        ficha=new Ficha();
        listaAprendicesFicha=new ArrayList();
    }
    public FichaController() {
    }

    public Ficha getSelected() {
        if (current == null) {
            current = new Ficha();
            selectedItemIndex = -1;
        }
        return current;
    }

    private FichaFacade getFacade() {
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
        current = (Ficha) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Ficha();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FichaCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Ficha) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FichaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Ficha) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FichaDeleted"));
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

    public Ficha getFicha(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    public String[] getGrupoAprendiz() {
        return grupoAprendiz;
    }

    public void setGrupoAprendiz(String[] grupoAprendiz) {
        this.grupoAprendiz = grupoAprendiz;
    }

    @FacesConverter(forClass = Ficha.class)
    public static class FichaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FichaController controller = (FichaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "fichaController");
            return controller.getFicha(getKey(value));
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
            if (object instanceof Ficha) {
                Ficha o = (Ficha) object;
                return getStringKey(o.getIdFicha());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Ficha.class.getName());
            }
        }

    }
 public List<Usuario> consultarAprendizFicha(){
        listaAprendicesFicha= ejbFacade.consultarFichaAprendiz(ficha);
        return listaAprendicesFicha;
    }
}
