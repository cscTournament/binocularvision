
package by.gourianova.binocularvision.dao.impl;

import by.gourianova.binocularvision.dao.ItemManagement;
import by.gourianova.binocularvision.bean.AppItem;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@ApplicationScoped
public class AppItemManagementInMemory implements ItemManagement {

    private CopyOnWriteArrayList<AppItem> appItems = new CopyOnWriteArrayList<AppItem>();

    public  CopyOnWriteArrayList<AppItem> getAppItems() {
        return appItems;
    }

    public  void setAppItems(CopyOnWriteArrayList<AppItem> appItems) {
        this.appItems = appItems;
    }

    public void addAppItem(AppItem item) {
        synchronized (this){
            int size = appItems.size();
            long id = 0;
            if(size != 0){
                id = appItems.get(size - 1).getId();
                id++;
            }
            item.setId(id);
            appItems.add(item);
        }
    }

    public void updateAppItem(List<AppItem> items){
        items.stream().forEach(item -> {
            item.setComplete(true);
            synchronized (this) {
                appItems.set(item.getId().intValue(), item);
            }
        });
    }

}
