package bean;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputComboboxListOfValues;
import oracle.adf.view.rich.component.rich.layout.RichPanelHeader;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;

public class MaterialAtSiteDetailsBean extends CommonBean {

    private RichPopup createPopup;
    private RichTable searchTable;
    private RichInputComboboxListOfValues prjNumber;
    private RichInputComboboxListOfValues periodBind;
    private RichPopup submitPopup;
    private RichPanelHeader panelHdrBind;
    private RichPopup revisePopup;
    private RichInputComboboxListOfValues resourceBind;

    public MaterialAtSiteDetailsBean() {
        super();
    }

    public void createHeaderRow(ActionEvent ae) {
        executeMethod("createMatHeaderRow");
        showPopup(getCreatePopup());
    }

    public void createDocument(ActionEvent ae) {
        Object message = executeMethod("validateMaterialHdr");
        if (!("SUCCESS".equals(message))) {
            String msg = null;
            if ("PREVDOCEXISTS".equals(message)) {
                msg = "Previous document is not submitted";
            } else if ("DOCEXISTS".equals(message)) {
                msg = "Document exists for selected period";
            } else if ("PERIODINVALID".equals(message)) {
                msg = "Invalid period selected";
            }
            getCreatePopup().hide();
            executeMethod("cancelMatDocument");
            save();
            addPartialTrigger(getSearchTable());
            showPopupMessage(msg, FacesMessage.SEVERITY_ERROR);
        } else {
            save();
            executeMethod("editMatDocument");
            navigate(ae, "Edit");
        }
    }


    public void cancelPopup(PopupCanceledEvent popupCanceledEvent) {
        executeMethod("cancelMatDocument");
        save();
        if (getCreatePopup() != null)
            oracle.adf.view.rich.util.ResetUtils.reset(getCreatePopup());
        getCreatePopup().hide();
        addPartialTrigger(getSearchTable());
    }

    public void editDocument(ActionEvent ae) {
        executeMethod("editMatDocument");
        navigate(ae, "Edit");
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

    public void cancelDoc(ActionEvent ae) {
        cancel();
    }

    public void setPrjNumber(RichInputComboboxListOfValues prjNumber) {
        this.prjNumber = prjNumber;
    }

    public RichInputComboboxListOfValues getPrjNumber() {
        return prjNumber;
    }

    public void setPeriodBind(RichInputComboboxListOfValues periodBind) {
        this.periodBind = periodBind;
    }

    public RichInputComboboxListOfValues getPeriodBind() {
        return periodBind;
    }

    public void buChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        getPrjNumber().setValue("");
        getPeriodBind().setValue("");
        addPartialTrigger(getPrjNumber());
        addPartialTrigger(getPeriodBind());
    }

    public void saveMaterial(ActionEvent ae) {
        save();
        executeMethod("refreshMaterialAtSiteLines");

    }

    public void cancelMaterial(ActionEvent ae) {
        cancel();
    }

    public void submitDoc(ActionEvent actionEvent) {
        showPopup(getSubmitPopup());
    }

    public void submitDialog(DialogEvent dialogEvent) {
        try {
            if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
                save();
                executeMethod("submitMaterialAtSite");
                save();
                navigate(dialogEvent, "Done");
                showPopupMessage("Submitted successfully",
                                 FacesMessage.SEVERITY_INFO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSubmitPopup(RichPopup submitPopup) {
        this.submitPopup = submitPopup;
    }

    public RichPopup getSubmitPopup() {
        return submitPopup;
    }

    public void setPanelHdrBind(RichPanelHeader panelHdrBind) {
        this.panelHdrBind = panelHdrBind;
    }

    public RichPanelHeader getPanelHdrBind() {
        return panelHdrBind;
    }

    public void setRevisePopup(RichPopup revisePopup) {
        this.revisePopup = revisePopup;
    }

    public RichPopup getRevisePopup() {
        return revisePopup;
    }

    public void reviseDialog(DialogEvent dialogEvent) {
        try {
            if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
                executeMethod("reviseMaterialSiteDetails");
                addPartialTrigger(getPanelHdrBind());
                showPopupMessage("Document Revised",
                                 FacesMessage.SEVERITY_INFO);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reviseDoc(ActionEvent actionEvent) {
        showPopup(getRevisePopup());
    }


    public void activityChange(ValueChangeEvent valueChangeEvent) {
        getResourceBind().setValue("");
        addPartialTrigger(getResourceBind());
    }

    public void setResourceBind(RichInputComboboxListOfValues resourceBind) {
        this.resourceBind = resourceBind;
    }

    public RichInputComboboxListOfValues getResourceBind() {
        return resourceBind;
    }
}
