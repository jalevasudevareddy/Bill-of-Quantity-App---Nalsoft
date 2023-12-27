package bean;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.layout.RichPanelHeader;

public class StructureSetupBean extends CommonBean {
    private RichPanelHeader pageBindings;

    public StructureSetupBean() {
        super();
    }

    public void setPageBindings(RichPanelHeader pageBindings) {
        this.pageBindings = pageBindings;
    }

    public RichPanelHeader getPageBindings() {
        executeMethod("createTransBuLine");
        return pageBindings;
    }

    public void ProjectNumberChanged(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
    }

    public void SaveStructure(ActionEvent actionEvent) {
        save();
    }

    public void cancelStructure(ActionEvent actionEvent) {
        cancel();
    }
}
