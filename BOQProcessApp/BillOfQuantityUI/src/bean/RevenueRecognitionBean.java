package bean;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;

public class RevenueRecognitionBean extends CommonBean {
    private RichTable tableBindings;
    private RichPanelGroupLayout buttonBindings;

    public RevenueRecognitionBean() {
        super();
    }

    public void saveRR(ActionEvent actionEvent) {
        save();
    }

    public void CancelRR(ActionEvent actionEvent) {
        cancel();
    }

    public void insertRevenueRecogLines(ActionEvent actionEvent) {
        executeMethod("insertRevenueRecogLines");
        addPartialTrigger(getTableBindings());
        addPartialTrigger(getButtonBindings());
    }

    public void refreshRevRecogLines(ActionEvent actionEvent) {
        executeMethod("refreshRevRecogLines");
        addPartialTrigger(getTableBindings());
    }

    public void setTableBindings(RichTable tableBindings) {
        this.tableBindings = tableBindings;
    }

    public RichTable getTableBindings() {
        return tableBindings;
    }

    public void setButtonBindings(RichPanelGroupLayout buttonBindings) {
        this.buttonBindings = buttonBindings;
    }

    public RichPanelGroupLayout getButtonBindings() {
        return buttonBindings;
    }

    public void ApproveDoc(ActionEvent actionEvent) {
        Object message = executeMethod("approveRRLines");
        if (message != null && message.toString().length() > 0) {
            showPopupMessage(message.toString(), FacesMessage.SEVERITY_ERROR);
        } else {
            save();
            executeMethod("executeLinesTable");
            addPartialTrigger(getTableBindings());
        }
    }

    public void rejectDoc(ActionEvent actionEvent) {
        Object message = executeMethod("rejectRRLines");
        if (message != null && message.toString().length() > 0) {
            showPopupMessage(message.toString(), FacesMessage.SEVERITY_ERROR);
        } else {
            save();
            executeMethod("executeLinesTable");
            addPartialTrigger(getTableBindings());
        }
    }
}
