package bean;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

public class ResourceMasterBean extends CommonBean {
    public ResourceMasterBean() {
        super();
    }

    public void createSubGroupLine(ActionEvent actionEvent) {
        save();
        executeMethod("createSubGroupLine");
    }

    public void SaveResourceMaster(ActionEvent actionEvent) {
        save();
    }

    public void cancelResourceMaster(ActionEvent actionEvent) {
        cancel();
    }

    public void subGroupChanged(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        executeMethod("setExpenditureCategoryValue");
    }

    public void createItemLine(ActionEvent actionEvent) {
        save();
        executeMethod("createItemLine");
    }

    public void itemCodeChanged(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        executeMethod("setExpenditureTypeValue");
    }
}
