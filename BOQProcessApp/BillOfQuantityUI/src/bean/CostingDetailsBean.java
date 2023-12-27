package bean;

import java.util.HashSet;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;

import oracle.jbo.domain.Number;

public class CostingDetailsBean extends CommonBean {
    private RichPopup submitForApprPopupBindgs;
    private RichPopup revisionPopupBindgs;
    private RichPopup apprPopupBindgs;
    private RichPopup populateBoqLinesPopupBindgs;
    private RichPanelGroupLayout jobItemCostingDetailsPageBindgs;
    private RichSelectBooleanCheckbox selectALLBindgs;
    private RichTable populateBoqLinesTblBindgs;
    private RichTable jobItemCostingTblBindgs;
    private RichPopup updatePopupBindgs;
    private RichPopup taskPopupBindings;

    public CostingDetailsBean() {
        super();
    }

    public void setSubmitForApprPopupBindgs(RichPopup submitForApprPopupBindgs) {
        this.submitForApprPopupBindgs = submitForApprPopupBindgs;
    }

    public RichPopup getSubmitForApprPopupBindgs() {
        return submitForApprPopupBindgs;
    }

    public void submitForApprPopupDL(DialogEvent dialogEvent) {


        Object isValifForAppr =
            executeMethod("isPrjCostingDetValidForFurtherProcess");
        if (isValifForAppr != null && isValifForAppr instanceof Boolean) {
            Boolean isValid = (Boolean)isValifForAppr;
            if (isValid) {
                save();
                getCurrentPageFlowScope().put("newStatus", "In Process");
                executeMethod("updateCostingDetailsStatusWith");
                save();

                addPartialTrigger(getJobItemCostingDetailsPageBindgs());
            } else {
                showPopupMessage("Project Item Analysis has been revised/modified. Please update the costings details document.",
                                 FacesMessage.SEVERITY_ERROR);
            }
        }


    }

    public void setRevisionPopupBindgs(RichPopup revisionPopupBindgs) {
        this.revisionPopupBindgs = revisionPopupBindgs;
    }

    public RichPopup getRevisionPopupBindgs() {
        return revisionPopupBindgs;
    }

    public void revisionPopupDL(DialogEvent dialogEvent) {
        Object revisedIdObj =
            executeMethod("reviseCurrentPrjCostingDetailsDoc");
        if (revisedIdObj != null) {
            save();
            getCurrentPageFlowScope().put("revisedCostingDocId", revisedIdObj);
            executeMethod("makeRevisedCostingDocAsCurrentRow");
            addPartialTrigger(getJobItemCostingDetailsPageBindgs());
            getCurrentPageFlowScope().remove("revisedCostingDocId");
            showPopupMessage("Costing details document has been successfully revised with lastest Item rate analysis.",
                             FacesMessage.SEVERITY_INFO);
        } else {
            showPopupMessage("Unable to revise the document.",
                             FacesMessage.SEVERITY_ERROR);
        }
    }

    public void setApprPopupBindgs(RichPopup apprPopupBindgs) {
        this.apprPopupBindgs = apprPopupBindgs;
    }

    public RichPopup getApprPopupBindgs() {
        return apprPopupBindgs;
    }

    public void apprPopupDL(DialogEvent dialogEvent) {
        save();
        getCurrentPageFlowScope().put("newStatus", "Approved");
        executeMethod("updateCostingDetailsStatusWith");
        save();
        addPartialTrigger(getJobItemCostingDetailsPageBindgs());
    }

    public void viewActHistAL(ActionEvent actionEvent) {
        // Add event code here...
    }

    public void saveCostingDetailsAL(ActionEvent actionEvent) {


        save();
        executeMethod("refreshJobItemCostingsVO");
    }

    public void saveAndCloseCostingDetlsAL(ActionEvent actionEvent) {
        save();
        navigate(actionEvent, "done");
    }

    public void populateBoqLinesPopupCL(PopupCanceledEvent popupCanceledEvent) {
        getSelectALLBindgs().setValid(false);
    }

    public void setPopulateBoqLinesPopupBindgs(RichPopup populateBoqLinesPopupBindgs) {
        this.populateBoqLinesPopupBindgs = populateBoqLinesPopupBindgs;
    }

    public RichPopup getPopulateBoqLinesPopupBindgs() {
        return populateBoqLinesPopupBindgs;
    }

    public void populateBoqLinesAL(ActionEvent actionEvent) {
        executeMethod("refreshBoqLinesPopulateVO");
        showPopup(getPopulateBoqLinesPopupBindgs());
    }

    public void itemSelectedFlagVL(ValueChangeEvent valueChangeEvent) {
        try {
            Set selectedBoqIdsSet =
                getCurrentPageFlowScope().get("selectedBoqId") != null ?
                (HashSet<Number>)getCurrentPageFlowScope().get("selectedBoqId") :
                new HashSet<Number>();

            Number itemId =
                new Number(getEL("#{bindings.LineId.inputValue}").toString());
            if ((Boolean)valueChangeEvent.getNewValue()) {
                selectedBoqIdsSet.add(itemId);
            } else {
                selectedBoqIdsSet.remove(itemId);
            }
            getCurrentPageFlowScope().put("selectedBoqId", selectedBoqIdsSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void populateBoqLinesPopupAL(ActionEvent actionEvent) {
        executeMethod("populateSelectedBoqLines");
        save();
        executeMethod("refreshJobItemCostingsVO");

        getCurrentPageFlowScope().remove("selectedBoqId");
        getSelectALLBindgs().setValid(false);
    }

    public void setJobItemCostingDetailsPageBindgs(RichPanelGroupLayout jobItemCostingDetailsPageBindgs) {
        this.jobItemCostingDetailsPageBindgs = jobItemCostingDetailsPageBindgs;
    }

    public RichPanelGroupLayout getJobItemCostingDetailsPageBindgs() {
        getCurrentPageFlowScope().remove("selectedBoqId");
        return jobItemCostingDetailsPageBindgs;
    }

    public void selectAllVL(ValueChangeEvent valueChangeEvent) {
        Boolean markAsSelect = (Boolean)valueChangeEvent.getNewValue();
        if (markAsSelect) {
            getCurrentPageFlowScope().put("markAs", "Y");
        } else {
            getCurrentPageFlowScope().put("markAs", "N");
        }
        Object selectedItemSetObj = executeMethod("marlAllPopulateBoqLinesAs");
        if (selectedItemSetObj != null && selectedItemSetObj instanceof Set) {
            this.getCurrentPageFlowScope().put("selectedBoqId",
                                               selectedItemSetObj);
        }
        addPartialTrigger(getPopulateBoqLinesTblBindgs());
    }

    public void setSelectALLBindgs(RichSelectBooleanCheckbox selectALLBindgs) {
        this.selectALLBindgs = selectALLBindgs;
    }

    public RichSelectBooleanCheckbox getSelectALLBindgs() {
        return selectALLBindgs;
    }

    public void setPopulateBoqLinesTblBindgs(RichTable populateBoqLinesTblBindgs) {
        this.populateBoqLinesTblBindgs = populateBoqLinesTblBindgs;
    }

    public RichTable getPopulateBoqLinesTblBindgs() {
        return populateBoqLinesTblBindgs;
    }

    public void deletePopupDL(DialogEvent dialogEvent) {
        executeMethod("Delete");
        save();
        executeMethod("refreshJobItemCostingsVO");

        addPartialTrigger(getJobItemCostingTblBindgs());

    }

    public void setJobItemCostingTblBindgs(RichTable jobItemCostingTblBindgs) {
        this.jobItemCostingTblBindgs = jobItemCostingTblBindgs;
    }

    public RichTable getJobItemCostingTblBindgs() {
        return jobItemCostingTblBindgs;
    }

    public void setUpdatePopupBindgs(RichPopup updatePopupBindgs) {
        this.updatePopupBindgs = updatePopupBindgs;
    }

    public RichPopup getUpdatePopupBindgs() {
        return updatePopupBindgs;
    }

    public String updateAndRefreshCurrentCostingDetailsDoc() {
        String updated = null;

        Object currCostingDetHdrId =
            getEL("#{ bindings.CostingDetailsHeaderId.inputValue}");
        save();
        Object exeMessageObj =
            executeMethod("updateCurrentPrjCostingDetailsDoc");
        if (exeMessageObj != null &&
            "Updated".equals(exeMessageObj.toString())) {

            save();
            executeMethod("refreshCostingDetailsHeaderVO");
            executeMethod("refreshJobItemCostingsVO");
            getCurrentPageFlowScope().put("revisedCostingDocId",
                                          currCostingDetHdrId);
            executeMethod("makeRevisedCostingDocAsCurrentRow");
            getCurrentPageFlowScope().remove("revisedCostingDocId");

            addPartialTrigger(getJobItemCostingDetailsPageBindgs());
            addPartialTrigger(getJobItemCostingTblBindgs());
            save();
            showPopupMessage("Costing details document has been successfully updated with lastest Item rate analysis.",
                             FacesMessage.SEVERITY_INFO);
            updated = "Success";
        } else {
            showPopupMessage("Document updation failed.",
                             FacesMessage.SEVERITY_ERROR);
        }
        return updated;
    }

    public void updatePopupDL(DialogEvent dialogEvent) {
        String isUpdated = updateAndRefreshCurrentCostingDetailsDoc();
        if (!"Success".equals(isUpdated)) {
            showPopupMessage("An Error occured please refresh the page.",
                             FacesMessage.SEVERITY_ERROR);
        } else {
            //            navigate(dialogEvent, "done");
        }
    }

    public void setTaskPopupBindings(RichPopup taskPopupBindings) {
        this.taskPopupBindings = taskPopupBindings;
    }

    public RichPopup getTaskPopupBindings() {
        return taskPopupBindings;
    }

    public void taskPopupDL(DialogEvent dialogEvent) {
        if (DialogEvent.Outcome.ok == dialogEvent.getOutcome()) {
            Object message = executeMethod("interfaceTasks");
            if (message != null) {
                if ("SUCCESS".equals(message)) {
                    showPopupMessage("Tasks are interfaced.",
                                     FacesMessage.SEVERITY_INFO);
                } else {
                    showPopupMessage("Tasks are not Interfaced.",
                                     FacesMessage.SEVERITY_ERROR);
                }
            }
        }
    }
}
