package bean;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;

import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.jbo.domain.Number;

import java.util.Map;

import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputComboboxListOfValues;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;

import org.apache.myfaces.trinidad.model.RowKeySet;

public class ProjectJobItemAnalyBean extends CommonBean {
    private RichPopup transPrjJobHdrPopupBindgs;
    private RichInputComboboxListOfValues transPrjNumBindgs;
    private RichTable prjJobHdrTblBindgs;
    private RichTable prjJobRescTblBindgs;
    private RichPopup populateRescPopupBindgs;
    private RichPopup prjUsedRescPopupBindgs;
    private RichPanelGroupLayout projectItemAnalysisPageBindgs;
    private RichOutputText itemUnitRateBindgs;
    private RichPopup submitPrjItemAnyPopupBindgs;
    private RichPopup submitForApprPopupBindgs;
    private RichPopup approvePopupBindgs;

    public ProjectJobItemAnalyBean() {
        super();
    }

    public void transPrjJobHdrPopupCL(PopupCanceledEvent popupCanceledEvent) {
        if (getTransPrjJobHdrPopupBindgs() != null)
            oracle.adf.view.rich.util.ResetUtils.reset(getTransPrjJobHdrPopupBindgs());
        getTransPrjJobHdrPopupBindgs().hide();
    }

    public void setTransPrjJobHdrPopupBindgs(RichPopup transPrjJobHdrPopupBindgs) {
        this.transPrjJobHdrPopupBindgs = transPrjJobHdrPopupBindgs;
    }

    public RichPopup getTransPrjJobHdrPopupBindgs() {
        return transPrjJobHdrPopupBindgs;
    }

    public void setTransPrjNumBindgs(RichInputComboboxListOfValues transPrjNumBindgs) {
        this.transPrjNumBindgs = transPrjNumBindgs;
    }

    public RichInputComboboxListOfValues getTransPrjNumBindgs() {
        return transPrjNumBindgs;
    }

    public void transBuIdBindgs(ValueChangeEvent valueChangeEvent) {

    }

    public void transPrjJobHdrPopupAL(ActionEvent actionEvent) {
        Object isAnalysisExistObj =
            executeMethod("isJobItemAnalysisCreatedForProject");
        if (isAnalysisExistObj != null &&
            isAnalysisExistObj instanceof Boolean) {
            Boolean isExists = (Boolean)isAnalysisExistObj;
            if (!isExists) {
                Object prjJobHdrId =
                    executeMethod("createNewProjectJobHeaderAnalysis");
                if (prjJobHdrId != null) {
                    save();
                    getCurrentPageFlowScope().put("prjJobHdrId", prjJobHdrId);
                    executeMethod("makeAsCurrentProjectJobHeader");
                    navigate(actionEvent, "JobItemAnalysis");
                } else {
                    showPopupMessage("unable to create new item analysis for project.",
                                     FacesMessage.SEVERITY_ERROR);
                }
            } else {
                showPopupMessage("A Job Item Analysis is already exists for selected project.",
                                 FacesMessage.SEVERITY_ERROR);
            }
        }
    }

    public void createPrjJobHdrAL(ActionEvent actionEvent) {
        executeMethod("initTransProjectJobHeaderVO");
        showPopup(getTransPrjJobHdrPopupBindgs());
    }

    public void deleteJobActivityPopupDL(DialogEvent dialogEvent) {
        executeMethod("Delete");
        save();
        executeMethod("refreshItemJobAnalysisVO");
        executeMethod("refreshProjectJobResourceVO");
        addPartialTrigger(getPrjJobHdrTblBindgs());
        addPartialTrigger(getPrjJobRescTblBindgs());
    }

    public void setPrjJobHdrTblBindgs(RichTable prjJobHdrTblBindgs) {
        this.prjJobHdrTblBindgs = prjJobHdrTblBindgs;
    }

    public RichTable getPrjJobHdrTblBindgs() {
        return prjJobHdrTblBindgs;
    }

    public void editProjectJobItemAnlyAL(ActionEvent actionEvent) {
        Object prjJobHdrId =
            getEL("#{bindings.ProjectJobHeaderId.inputValue}");
        getCurrentPageFlowScope().put("prjJobHdrId", prjJobHdrId);
        executeMethod("makeAsCurrentProjectJobHeader");
        navigate(actionEvent, "JobItemAnalysis");
    }

    public void saveSetCurrentPrjJobHdr() {
        Object prjJobId = getEL("#{bindings.JobItemAnalysisId.inputValue}");
        save();
        executeMethod("refreshItemJobAnalysisVO");
        executeMethod("refreshProjectJobResourceVO");

        getCurrentPageFlowScope().put("prjJobHdrId", prjJobId);
        executeMethod("makeAsCurrentProjectJobActivity");
    }

    public void savePrjItemAnyAL(ActionEvent actionEvent) {
        Object prjJobId = getEL("#{bindings.JobItemAnalysisId.inputValue}");
        save();
        executeMethod("refreshItemJobAnalysisVO");
        executeMethod("refreshProjectJobResourceVO");

        getCurrentPageFlowScope().put("prjJobActId", prjJobId);
        executeMethod("makeAsCurrentProjectJobActivity");
    }

    public void saveAndClosePrjJobItemAnylAL(ActionEvent actionEvent) {
        save();
        navigate(actionEvent, "done");
    }

    public void initPrjJobRescAL(ActionEvent actionEvent) {
        //                save();
        executeMethod("initProjectJobResourceRow");
    }

    public void setPrjJobRescTblBindgs(RichTable prjJobRescTblBindgs) {
        this.prjJobRescTblBindgs = prjJobRescTblBindgs;
    }

    public void deleteRescPopupDL(DialogEvent dialogEvent) {
        Object prjJobId = getEL("#{bindings.JobItemAnalysisId.inputValue}");
        RowKeySet selectedEmps = getPrjJobRescTblBindgs().getSelectedRowKeys();
        Iterator selectedEmpIter = selectedEmps.iterator();
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding empIter =
            bindings.findIteratorBinding("JobItemResourceVO1Iterator");
        RowSetIterator empRSIter = empIter.getRowSetIterator();
        while (selectedEmpIter.hasNext()) {
            Key key = (Key)((List)selectedEmpIter.next()).get(0);
            Row currentRow = empRSIter.getRow(key);
            if (currentRow != null) {
                currentRow.setAttribute("TransDeleteLineFlag", true);
            }
        }
        executeMethod("deleteJobItemAnalysisResources");
        save();
        executeMethod("refreshItemJobAnalysisVO");
        executeMethod("refreshProjectJobResourceVO");
        getCurrentPageFlowScope().put("prjJobActId", prjJobId);
        executeMethod("makeAsCurrentProjectJobActivity");

        addPartialTrigger(getPrjJobHdrTblBindgs());
        addPartialTrigger(getPrjJobRescTblBindgs());
    }

    public RichTable getPrjJobRescTblBindgs() {
        return prjJobRescTblBindgs;
    }

    public void setPopulateRescPopupBindgs(RichPopup populateRescPopupBindgs) {
        this.populateRescPopupBindgs = populateRescPopupBindgs;
    }

    public RichPopup getPopulateRescPopupBindgs() {
        return populateRescPopupBindgs;
    }

    public void populateRescAL(ActionEvent actionEvent) {
        Object prjJobId = getEL("#{bindings.JobItemAnalysisId.inputValue}");
        getPopulateRescPopupBindgs().hide();
        //        Set selectedItems =  (Set<Number>)getCurrentPageFlowScope().get("selectedItems");

        executeMethod("populateSelectedResources");
        save();
        executeMethod("refreshItemJobAnalysisVO");
        executeMethod("refreshProjectJobResourceVO");

        getCurrentPageFlowScope().put("prjJobActId", prjJobId);
        executeMethod("makeAsCurrentProjectJobActivity");
        getCurrentPageFlowScope().remove("selectedItems");
    }

    public void populaterResourceAL(ActionEvent actionEvent) {
        executeMethod("refreshPopulateJobRescVO");
        showPopup(getPopulateRescPopupBindgs());
    }

    public void projectUsedRescAL(ActionEvent actionEvent) {
        executeMethod("refreshProjectUsedResourcesVO");
        showPopup(getPrjUsedRescPopupBindgs());
    }

    public void setPrjUsedRescPopupBindgs(RichPopup prjUsedRescPopupBindgs) {
        this.prjUsedRescPopupBindgs = prjUsedRescPopupBindgs;
    }

    public RichPopup getPrjUsedRescPopupBindgs() {
        return prjUsedRescPopupBindgs;
    }

    public void updateUsedRescRateAL(ActionEvent actionEvent) {
        executeMethod("updateProjectResourcesRates");
        addPartialTrigger(getPrjJobRescTblBindgs());
    }

    public void transPrjRescUnitRateVL(ValueChangeEvent valueChangeEvent) {
        //        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        //        executeMethod("updateProjectResourcesRate");
        //        addPartialTrigger(getPrjJobRescTblBindgs());
    }

    public void deleteRescAL(ActionEvent actionEvent) {
        Object prjJobId = getEL("#{bindings.JobItemAnalysisId.inputValue}");
        RowKeySet selectedEmps = getPrjJobRescTblBindgs().getSelectedRowKeys();
        Iterator selectedEmpIter = selectedEmps.iterator();
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding empIter =
            bindings.findIteratorBinding("JobItemResourceVO1Iterator");
        RowSetIterator empRSIter = empIter.getRowSetIterator();
        while (selectedEmpIter.hasNext()) {
            Key key = (Key)((List)selectedEmpIter.next()).get(0);
            Row currentRow = empRSIter.getRow(key);
            if (currentRow != null) {
                currentRow.setAttribute("TransDeleteLineFlag", true);
            }
        }
        executeMethod("deleteJobItemAnalysisResources");
        save();
        executeMethod("refreshItemJobAnalysisVO");
        executeMethod("refreshProjectJobResourceVO");
        getCurrentPageFlowScope().put("prjJobActId", prjJobId);
        executeMethod("makeAsCurrentProjectJobActivity");

        addPartialTrigger(getPrjJobHdrTblBindgs());
        addPartialTrigger(getPrjJobRescTblBindgs());
    }

    public void SelectRescVL(ValueChangeEvent valueChangeEvent) {
        try {
            Set prjSelectedRescSet =
                getCurrentPageFlowScope().get("selectedItems") != null ?
                (HashSet<Number>)getCurrentPageFlowScope().get("selectedItems") :
                new HashSet<Number>();
            Number itemId =
                new Number(getEL("#{bindings.ItemId.inputValue}").toString());
            if ((Boolean)valueChangeEvent.getNewValue()) {
                prjSelectedRescSet.add(itemId);
            } else {
                prjSelectedRescSet.remove(itemId);
            }
            getCurrentPageFlowScope().put("selectedItems", prjSelectedRescSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setProjectItemAnalysisPageBindgs(RichPanelGroupLayout projectItemAnalysisPageBindgs) {
        this.projectItemAnalysisPageBindgs = projectItemAnalysisPageBindgs;
    }

    public RichPanelGroupLayout getProjectItemAnalysisPageBindgs() {
        getCurrentPageFlowScope().remove("selectedItems");
        return projectItemAnalysisPageBindgs;
    }

    public void rescPopulateCL(PopupCanceledEvent popupCanceledEvent) {
        if (getPopulateRescPopupBindgs() != null)
            oracle.adf.view.rich.util.ResetUtils.reset(getPopulateRescPopupBindgs());
        getPopulateRescPopupBindgs().hide();
        getCurrentPageFlowScope().remove("selectedItems");
    }

    public void itemNumberVL(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        executeMethod("calculateAndUpdatePrjItemUnitRate");
        addPartialTrigger(getItemUnitRateBindgs());
    }

    public void setItemUnitRateBindgs(RichOutputText itemUnitRateBindgs) {
        this.itemUnitRateBindgs = itemUnitRateBindgs;
    }

    public RichOutputText getItemUnitRateBindgs() {
        return itemUnitRateBindgs;
    }

    public void setSubmitPrjItemAnyPopupBindgs(RichPopup submitPrjItemAnyPopupBindgs) {
        this.submitPrjItemAnyPopupBindgs = submitPrjItemAnyPopupBindgs;
    }

    public RichPopup getSubmitPrjItemAnyPopupBindgs() {
        return submitPrjItemAnyPopupBindgs;
    }

    public void actHistAL(ActionEvent actionEvent) {
        // Add event code here...
    }

    public void setSubmitForApprPopupBindgs(RichPopup submitForApprPopupBindgs) {
        this.submitForApprPopupBindgs = submitForApprPopupBindgs;
    }

    public RichPopup getSubmitForApprPopupBindgs() {
        return submitForApprPopupBindgs;
    }

    public void submitForApprPopupDL(DialogEvent dialogEvent) {
        getCurrentPageFlowScope().put("newStatus", "In Process");
        executeMethod("updateItemAnyStatusWith");
        save();
        addPartialTrigger(getProjectItemAnalysisPageBindgs());
    }

    public void prjItemAnyRevisionPopupDL(DialogEvent dialogEvent) {
        Object revisedIdObj = executeMethod("reviseCurrentPrjJobItemAnalysis");
        if (revisedIdObj != null) {
            save();
            getCurrentPageFlowScope().put("revisedItemAnyId", revisedIdObj);
            executeMethod("makeRevicesItemAnalysisAsCurrentRow");
            addPartialTrigger(getProjectItemAnalysisPageBindgs());
        } else {
            showPopupMessage("Unable to revise the selected item analysis.",
                             FacesMessage.SEVERITY_ERROR);
        }
    }


    public void setApprovePopupBindgs(RichPopup approvePopupBindgs) {
        this.approvePopupBindgs = approvePopupBindgs;
    }

    public RichPopup getApprovePopupBindgs() {
        return approvePopupBindgs;
    }

    public void approvePopupDL(DialogEvent dialogEvent) {

        getCurrentPageFlowScope().put("newStatus", "Approved");
        executeMethod("updateItemAnyStatusWith");
        save();
        addPartialTrigger(getProjectItemAnalysisPageBindgs());
    }

    public void copyJobPopupDL(DialogEvent dialogEvent) {
        save();
        Object isCopiedObj = executeMethod("copyPrjJobItemAnalysis");
        if (isCopiedObj != null && "Copied".equals(isCopiedObj)) {
            executeMethod("refreshItemJobAnalysisVO");
            addPartialTrigger(getPrjJobHdrTblBindgs());
        } else {
            showPopupMessage("Copying selected job failed.",
                             FacesMessage.SEVERITY_ERROR);
        }

    }
}
