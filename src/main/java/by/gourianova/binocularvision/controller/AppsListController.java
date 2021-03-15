package by.gourianova.binocularvision.controller;

import by.gourianova.binocularvision.dao.AppItemManagementInMemory;
import by.gourianova.binocularvision.model.AppItem;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("appcontroller")
//@ViewScoped

@SessionScoped
//@SessionScoped
//@CustomScoped:
public class AppsListController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
    AppItemManagementInMemory appManagement;

    private List<AppItem> appItems;

    private AppItem selectedItem;
    private List<AppItem> selectedItems;

    private String name;
    private String category;


    public List<AppItem> getAppItems() {
        return appManagement.getAppItems();
    }

    public void buttonUpdateAction(){
        appManagement.updateAppItem(selectedItems);
    }

    public void buttonAddAction(){
        AppItem addItem = new AppItem(name, category, false);
        appManagement.addAppItem(addItem);
    }


    public void setSelectedItem(AppItem selectedItem) {
        this.selectedItem = selectedItem;
    }

    public List<AppItem> getSelectedItem() {
        return selectedItems;
    }

    public void setSelectedItems(List<AppItem> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public List<AppItem> getSelectedItems(){
        return selectedItems;
    }

    public void onRowSelect(SelectEvent<AppItem> event) {
        FacesMessage msg = new FacesMessage("AppItem Selected", event.getObject().getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent<AppItem> event) {
        FacesMessage msg = new FacesMessage("AppItem Unselected", event.getObject().getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

