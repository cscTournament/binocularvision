
package by.gourianova.binocularvision.bean;

import java.io.Serializable;


public class AppItem implements Serializable {

	private static final long serialVersionUID =  8244L;
	private Long id;
    private String category;
    private String name;
    private boolean complete;

    public AppItem() {}

    public AppItem(String name, String category, boolean complete) {
        this.name = name;
        this.category = category;
        this.complete = complete;
    }

    public AppItem(String category, String name) {
        this.category = category;
        this.name = name;
        this.complete = false;
    }

    @Override
    public String toString() {
        return String.format(
                "AppItem[id=%d, category='%s', name='%s', complete='%b']",
                id, category, name, complete);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        return;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        return;
    }

    public void setId(Long id) {
        this.id = id;
        return;
    }


    public Long getId() {
        return id;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
        return;
    }

}
