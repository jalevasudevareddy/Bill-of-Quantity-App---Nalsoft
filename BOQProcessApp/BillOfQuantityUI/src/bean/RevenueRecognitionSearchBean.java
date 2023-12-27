package bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputComboboxListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.event.PopupCanceledEvent;

import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class RevenueRecognitionSearchBean extends CommonBean {
    private RichPopup createPopup;
    private RichTable searchTable;

    public RevenueRecognitionSearchBean() {
        super();
    }

    public void createHeaderRow(ActionEvent ae) {
        executeMethod("createHeaderRow");
        showPopup(getCreatePopup());
    }

    public void createDocument(ActionEvent ae) {
        Object msg = executeMethod("createDocument");
        if (msg != null && msg.toString().length() > 0) {
            showPopupMessage(msg.toString(), FacesMessage.SEVERITY_ERROR);
        } else {
            save();
            navigate(ae, "Create");
        }
    }


    public void cancelPopup(PopupCanceledEvent popupCanceledEvent) {
        executeMethod("cancelDocument");
        save();
        if (getCreatePopup() != null)
            oracle.adf.view.rich.util.ResetUtils.reset(getCreatePopup());
        getCreatePopup().hide();
        addPartialTrigger(getSearchTable());
    }

    public void editDocument(ActionEvent ae) {
        executeMethod("editDocument");
        navigate(ae, "Create");
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

    public void cancelDoc(ActionEvent actionEvent) {
        executeMethod("cancelDocument");
        save();
        if (getCreatePopup() != null)
            oracle.adf.view.rich.util.ResetUtils.reset(getCreatePopup());
        getCreatePopup().hide();
        addPartialTrigger(getSearchTable());
    }
}
