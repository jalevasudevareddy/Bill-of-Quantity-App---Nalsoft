package bean;

import java.util.HashMap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;

import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.model.RowKeySet;

public class JobTemplateBean extends CommonBean {


    private RichPopup createJobTempPopupBindgs;
    private RichInputComboboxListOfValues transBuNamaBindgs;
    private RichInputText transTempNumberBindgs;
    private RichInputText transTempNameBindgs;
    private RichTable resourceTblBindgs;
    private RichTable jobActivityTblBindgs;
    private RichPanelGroupLayout resourcePanelGrpBindgs;
    private RichPopup populateRescPopupBindgs;

    private RichTable populateRescTblBindgs;
    private RichInputText jobActivityQuantityBindgs;

    private RichPopup copyJobsPopupBindgs;

    public JobTemplateBean() {
        super();
    }

    public void createJobTempAL(ActionEvent actionEvent) {
        executeMethod("initTransJobTemplateVO");
        showPopup(getCreateJobTempPopupBindgs());
    }

    public void editJobTempAL(ActionEvent actionEvent) {
        Object templateId = getEL("#{bindings.TemplateId.inputValue}");
        if (templateId != null) {
            getCurrentPageFlowScope().put("templateId", templateId);
            Object isSettedObj =
                executeMethod("makeAsCurrentrJobActivityTemplate");
            if (isSettedObj != null && isSettedObj instanceof Boolean) {
                Boolean isSetted = (Boolean)isSettedObj;
                if (isSetted) {
                    navigate(actionEvent, "activities");
                } else {
                    getCurrentPageFlowScope().put("errorMessage",
                                                  "unable to edit the selected template.");
                    navigate(actionEvent, "error");
                }
            }

        }

    }


    public void createJobTempPopupCL(PopupCanceledEvent popupCanceledEvent) {
        if (getCreateJobTempPopupBindgs() != null)
            oracle.adf.view.rich.util.ResetUtils.reset(getCreateJobTempPopupBindgs());
        getCreateJobTempPopupBindgs().hide();
    }

    public void setCreateJobTempPopupBindgs(RichPopup createJobTempPopupBindgs) {
        this.createJobTempPopupBindgs = createJobTempPopupBindgs;
    }

    public RichPopup getCreateJobTempPopupBindgs() {
        return createJobTempPopupBindgs;
    }

    public void setTransBuNamaBindgs(RichInputComboboxListOfValues transBuNamaBindgs) {
        this.transBuNamaBindgs = transBuNamaBindgs;
    }

    public RichInputComboboxListOfValues getTransBuNamaBindgs() {
        return transBuNamaBindgs;
    }

    public void transTempBUNameVL(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        Map attributeMap = new HashMap<String, Object>();
        attributeMap.put("TransTemplateName", null);
        attributeMap.put("TransTemplateNumber", null);
        getCurrentPageFlowScope().put("attributeMap", attributeMap);
        executeMethod("refreshTransJobtemplateVO");
        addPartialTrigger(getTransTempNameBindgs());
        addPartialTrigger(getTransTempNumberBindgs());
    }

    public void setTransTempNumberBindgs(RichInputText transTempNumberBindgs) {
        this.transTempNumberBindgs = transTempNumberBindgs;
    }

    public RichInputText getTransTempNumberBindgs() {
        return transTempNumberBindgs;
    }

    public void setTransTempNameBindgs(RichInputText transTempNameBindgs) {
        this.transTempNameBindgs = transTempNameBindgs;
    }

    public RichInputText getTransTempNameBindgs() {
        return transTempNameBindgs;
    }

    public void createJobTempPopupAL(ActionEvent actionEvent) {
        Object isExistsMessObj =
            executeMethod("isJobTemplateExistsForSelectedBU");
        if (isExistsMessObj != null && isExistsMessObj instanceof Boolean) {
            Boolean isExists = (Boolean)isExistsMessObj;
            if (!isExists) {


                Object exeMessage =
                    executeMethod("createNewActivityJobTemplate");
                if (exeMessage != null) {
                    save();
                    getCurrentPageFlowScope().put("templateId", exeMessage);
                    Object statusObj =
                        executeMethod("makeAsCurrentrJobActivityTemplate");
                    if (statusObj != null && statusObj instanceof Boolean) {
                        Boolean isSetted = (Boolean)statusObj;
                        if (isSetted) {
                            navigate(actionEvent, "activities");
                        } else {
                            getCurrentPageFlowScope().put("errorMessage",
                                                          "Saving the new Template failed.");
                            navigate(actionEvent, "error");
                        }
                    } else {
                        getCurrentPageFlowScope().put("errorMessage",
                                                      "Saving the new Template failed.");
                        navigate(actionEvent, "error");
                    }
                } else {
                    getCurrentPageFlowScope().put("errorMessage",
                                                  "Creating the new job template failed");
                    navigate(actionEvent, "error");
                }
            }

            else {
                showPopupMessage("A Job Template is already exists for selected business unit.",
                                 FacesMessage.SEVERITY_ERROR);
            }
        }

    }

    public void saveTemplateAL(ActionEvent actionEvent) {
        Object prjJobId = getEL("#{bindings.ActivityJobId.inputValue}");
        save();
        executeMethod("refreshJobTemplateActivitiesVO");
        executeMethod("refreshJobTemplateResourcesVO");
        getCurrentPageFlowScope().put("jobActivityId", prjJobId);
        executeMethod("makeAsCurrentJobActivity");
    }

    public void saveAndCloseTemplateAL(ActionEvent actionEvent) {
        save();
        navigate(actionEvent, "done");
    }

    public void addJobTempRescAL(ActionEvent actionEvent) {
        //        save();
        //        executeMethod("refreshJobTemplateActivitiesVO");

        executeMethod("initJobTemplateResourceVO");
        addPartialTrigger(getResourceTblBindgs());
        //        addPartialTrigger(getResourcePanelGrpBindgs());

    }

    public void setResourceTblBindgs(RichTable resourceTblBindgs) {
        this.resourceTblBindgs = resourceTblBindgs;
    }

    public RichTable getResourceTblBindgs() {
        return resourceTblBindgs;
    }

    public void setJobActivityTblBindgs(RichTable jobActivityTblBindgs) {
        this.jobActivityTblBindgs = jobActivityTblBindgs;
    }

    public RichTable getJobActivityTblBindgs() {
        return jobActivityTblBindgs;
    }

    public void setResourcePanelGrpBindgs(RichPanelGroupLayout resourcePanelGrpBindgs) {
        this.resourcePanelGrpBindgs = resourcePanelGrpBindgs;
    }

    public RichPanelGroupLayout getResourcePanelGrpBindgs() {
        getCurrentPageFlowScope().remove("tempSelectedResc");
        getCurrentPageFlowScope().remove("tempSelectedJobs");
        return resourcePanelGrpBindgs;
    }

    public void deleteJobActivityPopupDL(DialogEvent dialogEvent) {
        //        RowKeySet selectedEmps = getJobActivityTblBindgs().getSelectedRowKeys();
        //        Iterator selectedEmpIter = selectedEmps.iterator();
        //        DCBindingContainer bindings =
        //            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        //        DCIteratorBinding empIter =
        //            bindings.findIteratorBinding("JobTemplateActivityVO1Iterator");
        //        RowSetIterator empRSIter = empIter.getRowSetIterator();
        //        while (selectedEmpIter.hasNext()) {
        //            Key key = (Key)((List)selectedEmpIter.next()).get(0);
        //            Row currentRow = empRSIter.getRow(key);
        //            if (currentRow != null) {
        //                currentRow.setAttribute("TransDeleteLineFlag", true);
        //            }
        //
        //        }


        executeMethod("Delete1");
        save();
        executeMethod("refreshJobTemplateActivitiesVO");
        executeMethod("refreshJobTemplateResourcesVO");
        addPartialTrigger(getJobActivityTblBindgs());
        addPartialTrigger(getResourceTblBindgs());
    }

    public void deleteRescPopupDL(DialogEvent dialogEvent) {
        RowKeySet selectedEmps = getResourceTblBindgs().getSelectedRowKeys();
        Iterator selectedEmpIter = selectedEmps.iterator();
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding empIter =
            bindings.findIteratorBinding("JobTemplateResourceVO1Iterator");
        RowSetIterator empRSIter = empIter.getRowSetIterator();
        while (selectedEmpIter.hasNext()) {
            Key key = (Key)((List)selectedEmpIter.next()).get(0);
            Row currentRow = empRSIter.getRow(key);
            if (currentRow != null) {
                currentRow.setAttribute("TransDeleteLineFlag", true);
            }
        }
        Object prjJobId = getEL("#{bindings.ActivityJobId.inputValue}");
        executeMethod("deleteJobTemplateResources");
        save();
        executeMethod("refreshJobTemplateActivitiesVO");
        executeMethod("refreshJobTemplateResourcesVO");
        getCurrentPageFlowScope().put("jobActivityId", prjJobId);
        executeMethod("makeAsCurrentJobActivity");
        addPartialTrigger(getResourceTblBindgs());
    }

    public void testResource(ActionEvent actionEvent) {
        executeMethod("addResourceData");
        save();
    }

    public void setPopulateRescPopupBindgs(RichPopup populateRescPopupBindgs) {
        this.populateRescPopupBindgs = populateRescPopupBindgs;
    }

    public RichPopup getPopulateRescPopupBindgs() {
        return populateRescPopupBindgs;
    }


    public void setPopulateRescTblBindgs(RichTable populateRescTblBindgs) {
        this.populateRescTblBindgs = populateRescTblBindgs;
    }

    public RichTable getPopulateRescTblBindgs() {
        return populateRescTblBindgs;
    }

    public void populateRescAL(ActionEvent actionEvent) {
        getPopulateRescPopupBindgs().hide();
        Object prjJobId = getEL("#{bindings.ActivityJobId.inputValue}");
        executeMethod("populateSelectedResources");
        save();
        executeMethod("refreshJobTemplateActivitiesVO");
        executeMethod("refreshJobTemplateResourcesVO");
        getCurrentPageFlowScope().put("jobActivityId", prjJobId);
        executeMethod("makeAsCurrentJobActivity");
        getCurrentPageFlowScope().remove("tempSelectedResc");
    }

    public void populateResourceAL(ActionEvent actionEvent) {
        //        getPopulateRescPopupBindgs().hide();
        save();
        executeMethod("refreshJobTemplateRescVO");
        showPopup(getPopulateRescPopupBindgs());
    }

    public void setJobActivityQuantityBindgs(RichInputText jobActivityQuantityBindgs) {
        this.jobActivityQuantityBindgs = jobActivityQuantityBindgs;
    }

    public RichInputText getJobActivityQuantityBindgs() {
        return jobActivityQuantityBindgs;
    }

    public void jobActivityQuantityVL(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != null) {
            Double newValue =
                new Double(valueChangeEvent.getNewValue().toString());
            if (newValue < 0) {
                getJobActivityQuantityBindgs().setValue(valueChangeEvent.getOldValue());
            }

        }
        addPartialTrigger(getJobActivityQuantityBindgs());
    }

    public void copyJobsAL(ActionEvent actionEvent) {
        executeMethod("initCopyJobsToProjectsRow");
        executeMethod("refreshTemplateJobsVO");
        showPopup(getCopyJobsPopupBindgs());
    }

    public void setCopyJobsPopupBindgs(RichPopup copyJobsPopupBindgs) {
        this.copyJobsPopupBindgs = copyJobsPopupBindgs;
    }

    public RichPopup getCopyJobsPopupBindgs() {
        return copyJobsPopupBindgs;
    }

    public void copyJobsToProjectAL(ActionEvent actionEvent) {
        executeMethod("copyJobsToProject");
        //        save();
        getCurrentPageFlowScope().remove("tempSelectedJobs");
    }

    public void copyJobsToProjectsCL(PopupCanceledEvent popupCanceledEvent) {
        if (getCopyJobsPopupBindgs() != null)
            oracle.adf.view.rich.util.ResetUtils.reset(getCopyJobsPopupBindgs());
        getCopyJobsPopupBindgs().hide();

        getCurrentPageFlowScope().remove("tempSelectedJobs");
    }

    public void deleteRescAL(ActionEvent actionEvent) {
        RowKeySet selectedEmps = getResourceTblBindgs().getSelectedRowKeys();
        Iterator selectedEmpIter = selectedEmps.iterator();
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding empIter =
            bindings.findIteratorBinding("JobTemplateResourceVO1Iterator");
        RowSetIterator empRSIter = empIter.getRowSetIterator();
        while (selectedEmpIter.hasNext()) {
            Key key = (Key)((List)selectedEmpIter.next()).get(0);
            Row currentRow = empRSIter.getRow(key);
            if (currentRow != null) {
                currentRow.setAttribute("TransDeleteLineFlag", true);
            }
        }
        Object prjJobId = getEL("#{bindings.ActivityJobId.inputValue}");
        executeMethod("deleteJobTemplateResources");
        save();
        executeMethod("refreshJobTemplateActivitiesVO");
        executeMethod("refreshJobTemplateResourcesVO");
        getCurrentPageFlowScope().put("jobActivityId", prjJobId);
        executeMethod("makeAsCurrentJobActivity");
        addPartialTrigger(getResourceTblBindgs());
    }

    public void selectTempRescVL(ValueChangeEvent valueChangeEvent) {
        try {
            Set tempSelectedRescSet =
                getCurrentPageFlowScope().get("tempSelectedResc") != null ?
                (HashSet<Number>)getCurrentPageFlowScope().get("tempSelectedResc") :
                new HashSet<Number>();
            Number itemId =
                new Number(getEL("#{bindings.ItemId.inputValue}").toString());
            if ((Boolean)valueChangeEvent.getNewValue()) {
                tempSelectedRescSet.add(itemId);
            } else {
                tempSelectedRescSet.remove(itemId);
            }
            getCurrentPageFlowScope().put("tempSelectedResc",
                                          tempSelectedRescSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tempJobSelectedVL(ValueChangeEvent valueChangeEvent) {
        try {
            //            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            Set tempSelectedJobsSet =
                getCurrentPageFlowScope().get("tempSelectedJobs") != null ?
                (HashSet<Number>)getCurrentPageFlowScope().get("tempSelectedJobs") :
                new HashSet<Number>();
            Number jobId =
                new Number(getEL("#{bindings.ActivityJobId1.inputValue}").toString());
            if ((Boolean)valueChangeEvent.getNewValue()) {
                tempSelectedJobsSet.add(jobId);
            } else {
                tempSelectedJobsSet.remove(jobId);
            }
            getCurrentPageFlowScope().put("tempSelectedJobs",
                                          tempSelectedJobsSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void populateRescCL(PopupCanceledEvent popupCanceledEvent) {
        //        if (getPopulateRescPopupBindgs() != null)
        //            oracle.adf.view.rich.util.ResetUtils.reset(getPopulateRescPopupBindgs());
        //        getPopulateRescPopupBindgs().hide();
        //
        //        getCurrentPageFlowScope().remove("tempSelectedResc");
    }
}
