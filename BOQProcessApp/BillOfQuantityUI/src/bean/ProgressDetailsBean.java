package bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputComboboxListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.event.PopupCanceledEvent;

public class ProgressDetailsBean extends CommonBean {
    private RichPopup createPopup;
    private RichTable searchTable;
    private RichInputComboboxListOfValues prjNumber;
    private RichInputDate asOnDateBind;

    public ProgressDetailsBean() {
        super();
    }

    public void createHeaderRow(ActionEvent ae) {
        executeMethod("createProgHeaderRow");
        showPopup(getCreatePopup());
    }

    public void createDocument(ActionEvent ae) {
        save();
        if (getCreatePopup() != null) {
            oracle.adf.view.rich.util.ResetUtils.reset(getCreatePopup());
            getCreatePopup().hide();
        }
        executeMethod("refreshProgressDetails");
        addPartialTrigger(getSearchTable());

    }


    public void cancelPopup(PopupCanceledEvent popupCanceledEvent) {
        executeMethod("cancelProgDocument");
        save();
        if (getCreatePopup() != null)
            oracle.adf.view.rich.util.ResetUtils.reset(getCreatePopup());
        getCreatePopup().hide();
        addPartialTrigger(getSearchTable());
    }

    public void editDocument(ActionEvent ae) {
        //        executeMethod("editMatDocument");
        //        navigate(ae, "Edit");
    }

    public void cancelDoc(ActionEvent ae) {
        cancel();
    }

    public void setCreatePopup(RichPopup createPopup) {
        this.createPopup = createPopup;
    }

    public RichPopup getCreatePopup() {
        return createPopup;
    }

    public void setSearchTable(RichTable searchTable) {
        this.searchTable = searchTable;
    }

    public RichTable getSearchTable() {
        return searchTable;
    }

    public void buChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        getPrjNumber().setValue("");
        addPartialTrigger(getPrjNumber());
    }

    public void setPrjNumber(RichInputComboboxListOfValues prjNumber) {
        this.prjNumber = prjNumber;
    }

    public RichInputComboboxListOfValues getPrjNumber() {
        return prjNumber;
    }

    public void saveProgress(ActionEvent ae) {
        save();
    }

    public void cancelProgress(ActionEvent ae) {
        cancel();
    }

    public void dateChange(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        Object msg = executeMethod("validateProgressDetailsHdr");
        if (!("SUCCESS".equals(msg))) {
            addPartialTrigger(getAsOnDateBind());
            showPopupMessage("Invalid Date entered",
                             FacesMessage.SEVERITY_ERROR);
        }

    }

    public void setAsOnDateBind(RichInputDate asOnDateBind) {
        this.asOnDateBind = asOnDateBind;
    }

    public RichInputDate getAsOnDateBind() {
        return asOnDateBind;
    }
}
