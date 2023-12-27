package bean;

import bean.notification.NotificationBean;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpServletResponse;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputComboboxListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelHeader;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;

import oracle.binding.BindingContainer;

import oracle.jbo.Row;
import oracle.jbo.domain.BlobDomain;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class WorkDoneDetailsBean extends CommonBean {
    private RichTable workDoneSearchTable;
    private RichCommandLink attachmentsColumnBind;
    private RichOutputText fileCcreatedByBindgs;
    private RichPopup createPopup;
    private RichCommandLink fileNameBindgs;
    private RichPopup populateLinesPopup;
    private RichTable linesTableBindings;
    private RichInputText cummulativeQtyBindings;
    private RichPopup uploadWDPopUp;
    private RichPopup validateWDPopUp;
    private RichPanelGroupLayout pageBindings;
    private RichSelectBooleanCheckbox selectAllLineBindings;
    private RichTable popupTableBindings;
    private RichPopup cancelWRKDoneDocPopupBindgs;
    private RichPopup deleteWRKDonePopupBindgs;
    private RichPopup reviseWRKDoneDocPopupBindgs;
    private RichInputText actWRKDoneQtyBindgs;
    private RichOutputText prevCummQtyBindgs;
    private RichInputText billQtyBindgs;
    private RichInputComboboxListOfValues periodBindgs;
    private RichPopup withdrawWdDocPopupBindgs;
    private RichInputComboboxListOfValues prjNumBindgs;
    private RichInputComboboxListOfValues buNameBindgs;
    private RichInputDate fileCreationDateBindgs;
    private RichInputFile attachFileBindgs;
    private RichPanelGroupLayout rejectView;
    private RichInputText cummulativeQtyPerctBindings;
    private RichInputText actWRKDoneQtyPerctBindgs;
    private RichPopup attachmentFilesPopupBindgs;
    private RichInputText cummulativeWDAmountBind;
    private RichPopup submitConfirmationPopupBind;
    private RichPopup cancelWDDocBind;
    private RichInputComboboxListOfValues contractNumBind;


    public WorkDoneDetailsBean() {
        super();
    }

    public void createHeaderRow(ActionEvent ae) {
        executeMethod("createWDHeaderRow");
        showPopup(getCreatePopup());
    }

    public void createDocument(ActionEvent ae) {
        try {
            save();
            Object message = executeMethod("createWDDocument");
            //             executeMethod("validateWorkDoneHdr");
            if ("Success".equals(message)) {
                save();
                //                executeMethod("editWDDocument");
                navigate(ae, "view");
            } else {
                CancelWD(ae);
                showPopupMessage(message != null ? message.toString() :
                                 "Unable to create new document.",
                                 FacesMessage.SEVERITY_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }


    public void cancelPopup(PopupCanceledEvent popupCanceledEvent) {
        executeMethod("cancelWDDocument");
        save();
        if (getCreatePopup() != null)
            oracle.adf.view.rich.util.ResetUtils.reset(getCreatePopup());
        getCreatePopup().hide();
        addPartialTrigger(getWorkDoneSearchTable());
    }

    public void editDocument(ActionEvent ae) {
        executeMethod("editWDDocument");
        navigate(ae, "view");
    }

    public void setCreatePopup(RichPopup createPopup) {
        this.createPopup = createPopup;
    }

    public RichPopup getCreatePopup() {
        return createPopup;
    }

    public void setWorkDoneSearchTable(RichTable workDoneSearchTable) {
        this.workDoneSearchTable = workDoneSearchTable;
    }

    public RichTable getWorkDoneSearchTable() {
        return workDoneSearchTable;
    }

    public void saveWD(ActionEvent ae) {
        try {
            save();
            Object message = executeMethod("validateWDLines");
            //            executeMethod("executeWdLinesQuery");
            //            Object message = executeMethod("calculateWorkdoneLineValues");
            //            save();
            if (message != null && message.toString().length() > 0 &&
                (!"Success".equals(message.toString()))) {
                showPopupMessage(message != null ? message.toString() : "",
                                 FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception e) {
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getLinesTableBindings());
    }

    public void saveAndCloseWD(ActionEvent ae) {
        try {
            save();
            //            Object message = executeMethod("calculateWorkdoneLineValues");
            //            save();
            //            if (message != null && message.toString().length() > 0) {
            navigate(ae, "done");

            //            } else {
            //                showPopupMessage(message != null && message.toString().length()>0 ? message.toString() : "Error. Values are not updated based on perccentages.  ",
            //                                 FacesMessage.SEVERITY_ERROR);
            //
            //            }
        } catch (Exception e) {
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

    }

    public void cancelWD(ActionEvent ae) {
        cancel();
    }


    public void getLines(ActionEvent actionEvent) {
        try {
            save();
            executeMethod("executeWdLinesQuery");
            Object exeMessObj = executeMethod("executePopulateLinesVO");
            if (!"Success".equals(exeMessObj)) {
                showPopupMessage("Unbale to refresh lines.",
                                 FacesMessage.SEVERITY_ERROR);
            } else {
                showPopup(getPopulateLinesPopup());
            }
        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void setPopulateLinesPopup(RichPopup populateLinesPopup) {
        this.populateLinesPopup = populateLinesPopup;
    }

    public RichPopup getPopulateLinesPopup() {
        return populateLinesPopup;
    }

    public void populateLines(ActionEvent actionEvent) {
        executeMethod("populateWdLines");
        save();
        executeMethod("executeWdLinesQuery");
        addPartialTrigger(getLinesTableBindings());
    }

    public void setLinesTableBindings(RichTable linesTableBindings) {
        this.linesTableBindings = linesTableBindings;
    }

    public RichTable getLinesTableBindings() {
        return linesTableBindings;
    }

    public void SubmitWd(ActionEvent actionEvent) {
        Object cummTolObj = getEL("#{bindings.TransGrandTotal.inputValue}");
        if (cummTolObj != null) {
            Double cummTol = new Double(cummTolObj.toString());
            if (cummTol >= 0) {
                Object message = executeMethod("validateWDLines");

                //             Object message = "Success";
                if (message != null) {
                    if (!"Success".equals(message)) {
                        showPopupMessage(message.toString(),
                                         FacesMessage.SEVERITY_ERROR);
                    } else {
                        Object obj =
                            getEL("#{bindings.TransBOQStatus.inputValue}");
                        if ("RV".equals(obj)) {
                            getCurrentPageFlowScope().put("BOQStatus", "RV");
                        } else {
                            getCurrentPageFlowScope().put("BOQStatus", "AC");
                        }
                        showPopup(getSubmitConfirmationPopupBind());
                        //                            Object messObj = submitDocForApproval();
                        //                            if ("Success".equals(messObj)) {
                        //                                showPopupMessage("The document was submitted for approval.", FacesMessage.SEVERITY_INFO);
                        //                                try {
                        //                                    save();
                        //                                } catch (Exception e) {
                        //                                    showPopupMessage("Another user working on same document.",
                        //                                                     FacesMessage.SEVERITY_ERROR);
                        //                                }
                        //                                addPartialTrigger(getPageBindings());
                        //                                save();
                        //                            } else {
                        //                                showPopupMessage(messObj != null ? messObj.toString() : "Submitting the selected work done is failed.",
                        //                                                 FacesMessage.SEVERITY_ERROR);
                        //                            }
                    }
                }
            } else {
                showPopupMessage("Cumulative work done can't be less than zero.",
                                 FacesMessage.SEVERITY_ERROR);
            }
        }
        addPartialTrigger(getPageBindings());
    }

    public void CancelWD(ActionEvent actionEvent) {
        //
        //        executeMethod("cancelWDDocument");
        //        save();
        if (getCreatePopup() != null)
            oracle.adf.view.rich.util.ResetUtils.reset(getCreatePopup());
        getCreatePopup().hide();
        addPartialTrigger(getWorkDoneSearchTable());
    }

    public Object submitDocForApproval() {
        Object msg = executeMethod("callpkgForAMEProcessSubmit");
        return msg;
    }

    public void actHistAL(ActionEvent actionEvent) {
        Object qacHdrId = getEL("#{bindings.WdHeaderId.inputValue}");
        getCurrentPageFlowScope().put("docId", qacHdrId);
        getCurrentPageFlowScope().put("docTypeId", "2");
        navigate(actionEvent, "ActionHistory");
    }

    public void ReviseWD(ActionEvent actionEvent) {

    }


    public void setCummulativeQtyBindings(RichInputText cummulativeQtyBindings) {
        this.cummulativeQtyBindings = cummulativeQtyBindings;
    }

    public RichInputText getCummulativeQtyBindings() {
        return cummulativeQtyBindings;
    }

    public void downloadExcel(FacesContext facesContext,
                              OutputStream outputStream) {
        byte[] report = (byte[])executeMethod("downloadWorkDoneDetails");
        try {
            outputStream.write(report);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fileXLUploaded(ValueChangeEvent valueChangeEvent) {
        try {
            UploadedFile uploadedFile =
                (UploadedFile)valueChangeEvent.getNewValue();
            Map params = new HashMap();
            params.put("uploadedFile", uploadedFile);
            Object msg = executeMethod("uploadWDXLFile", params);
            if (msg != null && !("Success".equals(msg)))
                showPopupMessage(msg.toString(), FacesMessage.SEVERITY_ERROR);
            else if (msg != null && ("Success".equals(msg))) {
                save();
                //                showPopup(getValidateWDPopUp());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void uploadWDDialog(DialogEvent dialogEvent) {
        try {
            if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
                Object exeMessObj = executeMethod("validateWDUploadDetails");
                if ("Success".equals(exeMessObj)) {
//                    refreshPage();
                    showPopupMessage("File import successful.",
                                     FacesMessage.SEVERITY_INFO);
                } else {
//                    refreshPage();
                    showPopupMessage(exeMessObj != null ?
                                     exeMessObj.toString() :
                                     "File import failed.",
                                     FacesMessage.SEVERITY_ERROR);
                }
                getLinesTableBindings().resetStampState();
                addPartialTrigger(getLinesTableBindings());
                addPartialTrigger(getPageBindings());
            }
            getUploadWDPopUp().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUploadWDPopUp(RichPopup uploadWDPopUp) {
        this.uploadWDPopUp = uploadWDPopUp;
    }

    public RichPopup getUploadWDPopUp() {
        return uploadWDPopUp;
    }

    public void setValidateWDPopUp(RichPopup validateWDPopUp) {
        this.validateWDPopUp = validateWDPopUp;
    }

    public RichPopup getValidateWDPopUp() {
        return validateWDPopUp;
    }

    public void uploadWDDetails(ActionEvent actionEvent) {

        if (getUploadWDPopUp() != null)
            oracle.adf.view.rich.util.ResetUtils.reset(getUploadWDPopUp());
        showPopup(getUploadWDPopUp());
    }

    public void approve(ActionEvent actionEvent) {
        setEL("#{bindings.WdStatus.inputValue}", "Approved");
        save();
        addPartialTrigger(getPageBindings());
    }

    public void setPageBindings(RichPanelGroupLayout pageBindings) {
        this.pageBindings = pageBindings;
    }

    public RichPanelGroupLayout getPageBindings() {
        return pageBindings;
    }

    public void setSelectAllLineBindings(RichSelectBooleanCheckbox selectAllLineBindings) {
        this.selectAllLineBindings = selectAllLineBindings;
    }

    public RichSelectBooleanCheckbox getSelectAllLineBindings() {
        return selectAllLineBindings;
    }

    public void selectAllVL(ValueChangeEvent valueChangeEvent) {
        if (!(valueChangeEvent.getNewValue().equals(valueChangeEvent.getOldValue()))) {
            Boolean markAs = (Boolean)valueChangeEvent.getNewValue();
            getCurrentPageFlowScope().put("markAs", markAs);
            executeMethod("markAllWdLinesAs");
            addPartialTrigger(getPopupTableBindings());
        }
    }

    public void setPopupTableBindings(RichTable popupTableBindings) {
        this.popupTableBindings = popupTableBindings;
    }

    public RichTable getPopupTableBindings() {
        return popupTableBindings;
    }

    public void setCancelWRKDoneDocPopupBindgs(RichPopup cancelWRKDoneDocPopupBindgs) {
        this.cancelWRKDoneDocPopupBindgs = cancelWRKDoneDocPopupBindgs;
    }

    public RichPopup getCancelWRKDoneDocPopupBindgs() {
        return cancelWRKDoneDocPopupBindgs;
    }

    public void cancelWrkDoneDoPopupAL(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.yes) {
            showPopup(getCancelWDDocBind());
        }
    }

    public void deleteWrkDoneDoPopupAL(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.yes) {
            Object exeMessObj = executeMethod("deleteWRKDoneRow");
            if ("Success".equals(exeMessObj)) {
                save();
                navigate(dialogEvent, "done");
            } else {
                showPopupMessage(exeMessObj != null ? exeMessObj.toString() :
                                 "Deleting the selected document failed.",
                                 FacesMessage.SEVERITY_ERROR);
            }
        }
    }

    public void reviseWrkDoneDoPopupAL(DialogEvent dialogEvent) {
        try {
            if (dialogEvent.getOutcome() == DialogEvent.Outcome.yes) {
                  Object message = executeMethod("ReviseWorkDoneDoc");
                if (message != null && message.toString().length() > 0) {
                    showPopupMessage(message.toString(),
                                     FacesMessage.SEVERITY_ERROR);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        addPartialTrigger(getPageBindings());
    }


    public void setDeleteWRKDonePopupBindgs(RichPopup deleteWRKDonePopupBindgs) {
        this.deleteWRKDonePopupBindgs = deleteWRKDonePopupBindgs;
    }

    public RichPopup getDeleteWRKDonePopupBindgs() {
        return deleteWRKDonePopupBindgs;
    }

    public void setReviseWRKDoneDocPopupBindgs(RichPopup reviseWRKDoneDocPopupBindgs) {
        this.reviseWRKDoneDocPopupBindgs = reviseWRKDoneDocPopupBindgs;
    }

    public RichPopup getReviseWRKDoneDocPopupBindgs() {
        return reviseWRKDoneDocPopupBindgs;
    }

    public void setActWRKDoneQtyBindgs(RichInputText actWRKDoneQtyBindgs) {
        this.actWRKDoneQtyBindgs = actWRKDoneQtyBindgs;
    }

    public RichInputText getActWRKDoneQtyBindgs() {
        return actWRKDoneQtyBindgs;
    }

    public void setPrevCummQtyBindgs(RichOutputText prevCummQtyBindgs) {
        this.prevCummQtyBindgs = prevCummQtyBindgs;
    }

    public RichOutputText getPrevCummQtyBindgs() {
        return prevCummQtyBindgs;
    }

    public void setBillQtyBindgs(RichInputText billQtyBindgs) {
        this.billQtyBindgs = billQtyBindgs;
    }

    public RichInputText getBillQtyBindgs() {
        return billQtyBindgs;
    }

    public void validateWDLinesAL(ActionEvent actionEvent) {
        Object message = executeMethod("validateWDLines");
        if (message != null) {
            if ("Success".equals(message)) {
                showPopupMessage("Validated successfully.",
                                 FacesMessage.SEVERITY_INFO);
            } else {

                showPopupMessage(message != null ? message.toString() :
                                 "Validation failed.",
                                 FacesMessage.SEVERITY_ERROR);

            }
        }
        addPartialTrigger(getPageBindings());
    }

    public void setPeriodBindgs(RichInputComboboxListOfValues periodBindgs) {
        this.periodBindgs = periodBindgs;
    }

    public RichInputComboboxListOfValues getPeriodBindgs() {
        return periodBindgs;
    }

    public void setWithdrawWdDocPopupBindgs(RichPopup withdrawWdDocPopupBindgs) {
        this.withdrawWdDocPopupBindgs = withdrawWdDocPopupBindgs;
    }

    public RichPopup getWithdrawWdDocPopupBindgs() {
        return withdrawWdDocPopupBindgs;
    }

    public void withdrawWdDoc(ActionEvent actionEvent) {
        showPopup(getWithdrawWdDocPopupBindgs());
    }

    public void withdrawSubmission(ActionEvent actionEvent) {
        try {
            save();
            Object wdHdrId = getEL("#{bindings.WdHeaderId.inputValue}");
            getCurrentPageFlowScope().put("docId", wdHdrId);
            getCurrentPageFlowScope().put("docTypeId", "2");
            Object withdtwSubMesObj = executeMethod("withdrawFromApproval");
            if ("Success".equals(withdtwSubMesObj)) {
                save();
                setEL("#{bindings.WdStatus.inputValue}", "W");
                save();
                showPopupMessage("Your changes were saved.",
                                 FacesMessage.SEVERITY_INFO);
                addPartialTrigger(getPageBindings());
            } else {
                showPopupMessage(withdtwSubMesObj != null ?
                                 withdtwSubMesObj.toString() :
                                 "Withdraw action failed. Please try again.",
                                 FacesMessage.SEVERITY_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void prjNumVL(ValueChangeEvent valueChangeEvent) {
        try {
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            Map<String, Object> attrMap = new HashMap<String, Object>();
            attrMap.put("TransPeriod", null);
            getCurrentPageFlowScope().put("attributesMap", attrMap);
            Object exeMessObj = executeMethod("refreshWDHdrVOAttrs");
            if (!"Success".equals(exeMessObj)) {
                showPopupMessage(exeMessObj != null ? exeMessObj.toString() :
                                 "Unable to refresh trans workdone row.",
                                 FacesMessage.SEVERITY_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getPeriodBindgs());
    }

    public void buNameVL(ValueChangeEvent valueChangeEvent) {
        try {
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            Map<String, Object> attrMap = new HashMap<String, Object>();
          //  attrMap.put("TransProjectNum", null); As project is removed
          //  attrMap.put("TransProjectId", null);
            attrMap.put("TransContractNumber", null);
            attrMap.put("TransContractId", null);
            attrMap.put("TransPeriod", null);
            getCurrentPageFlowScope().put("attributesMap", attrMap);
            Object exeMessObj = executeMethod("refreshWDHdrVOAttrs");
            if (!"Success".equals(exeMessObj)) {
                showPopupMessage(exeMessObj != null ? exeMessObj.toString() :
                                 "Unable to refresh trans workdone row.",
                                 FacesMessage.SEVERITY_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        //addPartialTrigger(getPrjNumBindgs());
        addPartialTrigger(getContractNumBind());
        addPartialTrigger(getPeriodBindgs());
    }

    public void setPrjNumBindgs(RichInputComboboxListOfValues prjNumBindgs) {
        this.prjNumBindgs = prjNumBindgs;
    }

    public RichInputComboboxListOfValues getPrjNumBindgs() {
        return prjNumBindgs;
    }

    public void setBuNameBindgs(RichInputComboboxListOfValues buNameBindgs) {
        this.buNameBindgs = buNameBindgs;
    }

    public RichInputComboboxListOfValues getBuNameBindgs() {
        return buNameBindgs;
    }


    public void rejectDoc(ActionEvent actionEvent) {
        // Add event code here...
        String actionRemarks =
            (String)getEL("#{pageFlowScope.APPROVAL_REMARKS}");
        if (actionRemarks != null && !(actionRemarks.equals(""))) {
            NotificationBean bean = new NotificationBean();
            bean.rejectDialogEvent();
        } else {
            showPopupMessage("Rejection remarks are required.",
                             FacesMessage.SEVERITY_ERROR);
        }
    }

    public void setRejectView(RichPanelGroupLayout rejectView) {
        this.rejectView = rejectView;
    }

    public RichPanelGroupLayout getRejectView() {

        Map sessionMap = (Map)(ADFContext.getCurrent().getSessionScope());
        String userName = (String)getEL("#{sessionScope.userName}");
        Map userMap = new HashMap();
        userMap.put("UserMap", userName);

        String actionCode = (String)getEL("#{pageFlowScope.actionCode}");
        if ("Approve".equals(actionCode)) {
            NotificationBean bean = new NotificationBean();
            bean.approveDialogEvent();
        } else {
            if ("Reject".equals(actionCode))
                showPopupMessage("Rejection remarks are required.",
                                 FacesMessage.SEVERITY_INFO);
            else
                showPopupMessage("Not approved", FacesMessage.SEVERITY_INFO);
        }
        return rejectView;
    }

    public void setCummulativeQtyPerctBindings(RichInputText cummulativeQtyPerctBindings) {
        this.cummulativeQtyPerctBindings = cummulativeQtyPerctBindings;
    }

    public RichInputText getCummulativeQtyPerctBindings() {
        return cummulativeQtyPerctBindings;
    }

    public void setActWRKDoneQtyPerctBindgs(RichInputText actWRKDoneQtyPerctBindgs) {
        this.actWRKDoneQtyPerctBindgs = actWRKDoneQtyPerctBindgs;
    }

    public RichInputText getActWRKDoneQtyPerctBindgs() {
        return actWRKDoneQtyPerctBindgs;
    }

    public void validateLineQuantitiesPerctAL(ValueChangeEvent valueChangeEvent) {
        try {

            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            Object msg = executeMethod("validateWDLineCumltQtyPercnt");
            if (msg != null && msg.toString().length() > 0) {
                showPopupMessage(msg.toString(), FacesMessage.SEVERITY_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        addPartialTrigger(getCummulativeQtyPerctBindings());
        addPartialTrigger(getCummulativeQtyBindings());
        addPartialTrigger(getPageBindings());

    }


    public void validateLineQuantities(ValueChangeEvent valueChangeEvent) {
        //        try {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        getCummulativeQtyBindings().setValue(((BigDecimal)valueChangeEvent.getNewValue()).setScale(10,
                                                                                                   BigDecimal.ROUND_HALF_UP));
        //            Object exeMess = executeMethod("validateWDLineCumltQty");
        //            Object wdQtyObj = valueChangeEvent.getNewValue();
        //            Object billQtyObj = getEL("#{bindings.BillQty.inputValue}");
        //            if(wdQtyObj!=null && billQtyObj!=null){
        //                Double wdQty = new Double(wdQtyObj.toString());
        //                Double billQty = new Double(billQtyObj.toString());
        //                if(wdQty>billQty){
        //                    getCummulativeQtyBindings().setValue(valueChangeEvent.getOldValue());
        //                    showPopupMessage("Cummulative Workdone Qty cannot be greater than Bill Qty i.e."+billQty, FacesMessage.SEVERITY_ERROR);
        //                }
        //            }
        //            else{
        //                getCummulativeQtyBindings().setValue(valueChangeEvent.getOldValue());
        //                showPopupMessage("Invalid values found i.e bill Qty="+billQtyObj +" , Cumulative Qty ="+ wdQtyObj, FacesMessage.SEVERITY_ERROR);
        //            }
        //
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }
        addPartialTrigger(getCummulativeQtyBindings());
        //        addPartialTrigger(getCummulativeQtyPerctBindings());
    }

    public void actWDQtyPerctVL(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        try {
            //            Object exeMess = executeMethod("validateWDLineActQtyPercnt");

            //            if (!"Success".equals(exeMess)) {
            //                showPopupMessage(exeMess != null ? exeMess.toString() :
            //                                 "Uable to refresh the Actual WD Quantity%.",
            //                                 FacesMessage.SEVERITY_ERROR);
            //            }

        } catch (Exception e) {
            e.getMessage();
        }
        addPartialTrigger(getActWRKDoneQtyBindgs());
        addPartialTrigger(getActWRKDoneQtyPerctBindgs());
    }

    public void actWDQtyVL(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        try {

            Object exeMess = executeMethod("validateWDLineActQty");

            if (!"Success".equals(exeMess)) {
                showPopupMessage(exeMess != null ? exeMess.toString() :
                                 "Uable to refresh the actual work done quantity.",
                                 FacesMessage.SEVERITY_ERROR);
            }

        } catch (Exception e) {
            e.getMessage();
        }
        addPartialTrigger(getActWRKDoneQtyBindgs());
        addPartialTrigger(getActWRKDoneQtyPerctBindgs());
    }

    public void getWdDocReportAL(FacesContext facesContext,
                                 OutputStream outputStream) {
        try {
            Object wdHdrId = getEL("#{bindings.WdHeaderId.inputValue}");
            getCurrentPageFlowScope().put("wdHdrId", wdHdrId);
            if (wdHdrId != null) {
                Object dataObj = executeMethod("runWorkDoneDetailsReport");
                //                System.out.println(dataObj.toString());

                if (dataObj != null) {
                    //                    String xmlData = dataObj.toString();
                    outputStream.write((byte[])dataObj);
                    outputStream.flush();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void workDoneAmountVL(ValueChangeEvent valueChangeEvent) {
        //        try {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        //            getCurrentPageFlowScope().put("cummulativeWDAmt", valueChangeEvent.getNewValue());
        //            Object exeMess = executeMethod("validateWDLineCumltAmt");
        //            Object wdAmtObj = valueChangeEvent.getNewValue();
        //            Object billQtyObj = getEL("#{bindings.BillQty.inputValue}");
        //            Object currBoqRateObj = getEL("#{bindings.CurrentBoqRate.inputValue}");
        //            if(wdAmtObj!=null && billQtyObj!=null && currBoqRateObj!=null){
        //                Double wdAmt = new Double(wdAmtObj.toString());
        //                Double billQty = new Double(billQtyObj.toString());
        //                Double currBoqRate = new Double(currBoqRateObj.toString());
        //                if(wdAmt>(billQty * currBoqRate)){
        //                    getCummulativeWDAmountBind().setValue(valueChangeEvent.getOldValue());
        //                    showPopupMessage("Cummulative Workdone Amount cannot be greater than Bill Amount i.e."+(billQty*currBoqRate), FacesMessage.SEVERITY_ERROR);
        //                }
        //            }
        //            else{
        //                getCummulativeWDAmountBind().setValue(valueChangeEvent.getOldValue());
        //                showPopupMessage("Invalid values found i.e bill Qty="+billQtyObj +" , Cumulative Amount ="+ wdAmtObj+", Current Boq Rate ="+currBoqRateObj, FacesMessage.SEVERITY_ERROR);
        //            }
        //
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }
        //        addPartialTrigger(getCummulativeQtyBindings());
        //        addPartialTrigger(getCummulativeQtyPerctBindings());
    }

    public void setCummulativeWDAmountBind(RichInputText cummulativeWDAmountBind) {
        this.cummulativeWDAmountBind = cummulativeWDAmountBind;
    }

    public RichInputText getCummulativeWDAmountBind() {
        return cummulativeWDAmountBind;
    }

    public void cancelAL(ActionEvent actionEvent) {
        //        cancel();
    }

    public void saveBoqLinesAL(ActionEvent actionEvent) {
        try {
            save();
        } catch (Exception e) {
        }
    }

    public void attachFileVL(ValueChangeEvent valueChangeEvent) {

        try {
            valueChangeEvent.getComponent().processDecodes(FacesContext.getCurrentInstance());
            Object fileObj = valueChangeEvent.getNewValue();
            if (fileObj != null) {
                UploadedFile file = (UploadedFile)fileObj;
                this.getCurrentPageFlowScope().put("attachedFile", file);
                Object exeMess = executeMethod("saveAttachedFile");
                if (!"Success".equals(exeMess)) {
                    showPopupMessage(exeMess != null ? exeMess.toString() :
                                     "Unable to add the file.",
                                     FacesMessage.SEVERITY_ERROR);
                }
            }
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
            // TODO:
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getFileNameBindgs());
        addPartialTrigger(getFileCcreatedByBindgs());
    }

    public void attachmemntFilesPopupCL(PopupCanceledEvent popupCanceledEvent) {
        // Add event code here...
    }

    public void downloadFileAction(FacesContext ctx, OutputStream out) {

        ExternalContext ectx = ctx.getExternalContext();
        BindingContext bindingctx = BindingContext.getCurrent();
        BindingContainer bindings = bindingctx.getCurrentBindingsEntry();
        DCBindingContainer bindingsImpl = (DCBindingContainer)bindings;
        DCIteratorBinding iter =
            bindingsImpl.findIteratorBinding("AttachmentsVO1Iterator");
        Row currentRow = iter.getCurrentRow();
        String fileName = (String)currentRow.getAttribute("FileName");
        BlobDomain content =
            (BlobDomain)currentRow.getAttribute("AttachmentFile");
        Long length = content.getLength();
        HttpServletResponse response = (HttpServletResponse)ectx.getResponse();
        response.setHeader("Content-Disposition",
                           "attachment;filename=\"" + fileName + "\"");
        response.setContentLength(length.intValue());
        try {
            InputStream in = content.getBinaryStream();
            byte[] buf = new byte[1024];
            int count;
            while ((count = in.read(buf)) >= 0) {
                out.write(buf, 0, count);
            }
            in.close();
            out.flush();
            out.close();
            ctx.responseComplete();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        iter.executeQuery();
        //        AdfFacesContext.getCurrentInstance().addPartialTarget(getAttachmentsTable());

    }

    public void setAttachFileBindgs(RichInputFile attachFileBindgs) {
        this.attachFileBindgs = attachFileBindgs;
    }

    public RichInputFile getAttachFileBindgs() {
        return attachFileBindgs;
    }

    public void setFileCreationDateBindgs(RichInputDate fileCreationDateBindgs) {
        this.fileCreationDateBindgs = fileCreationDateBindgs;
    }

    public RichInputDate getFileCreationDateBindgs() {
        return fileCreationDateBindgs;
    }

    public void setFileCcreatedByBindgs(RichOutputText fileCcreatedByBindgs) {
        this.fileCcreatedByBindgs = fileCcreatedByBindgs;
    }

    public RichOutputText getFileCcreatedByBindgs() {
        return fileCcreatedByBindgs;
    }

    public void setFileNameBindgs(RichCommandLink fileNameBindgs) {
        this.fileNameBindgs = fileNameBindgs;
    }

    public RichCommandLink getFileNameBindgs() {
        return fileNameBindgs;
    }

    public void setAttachmentsColumnBind(RichCommandLink attachmentsColumnBind) {
        this.attachmentsColumnBind = attachmentsColumnBind;
    }

    public RichCommandLink getAttachmentsColumnBind() {
        return attachmentsColumnBind;
    }

    public void setAttachmentFilesPopupBindgs(RichPopup attachmentFilesPopupBindgs) {
        this.attachmentFilesPopupBindgs = attachmentFilesPopupBindgs;
    }

    public RichPopup getAttachmentFilesPopupBindgs() {
        return attachmentFilesPopupBindgs;
    }

    public void workDoneLinesDeleteAll(DialogEvent dialogEvent) {
        Object msg = executeMethod("workDoneLinesDeleteAll");
        if (msg != null && (!"Success".equals(msg))) {
            showPopupMessage(msg.toString(), FacesMessage.SEVERITY_ERROR);
        } else {
            addPartialTrigger(getLinesTableBindings());
        }
    }

    public void setSubmitConfirmationPopupBind(RichPopup submitConfirmationPopupBind) {
        this.submitConfirmationPopupBind = submitConfirmationPopupBind;
    }

    public RichPopup getSubmitConfirmationPopupBind() {
        return submitConfirmationPopupBind;
    }

    public void submitForApprovalPopupDialogListener(DialogEvent dialogEvent) {
        Object messObj = submitDocForApproval();
        if ("Success".equals(messObj)) {
            showPopupMessage("The document was submitted for approval.",
                             FacesMessage.SEVERITY_INFO);
            try {
                save();
            } catch (Exception e) {
                showPopupMessage("Another User working on same document.",
                                 FacesMessage.SEVERITY_ERROR);
            }
            addPartialTrigger(getPageBindings());
            save();
        } else if("Approved".equals(messObj)){
            showPopupMessage("Document  successfully approved.",
                             FacesMessage.SEVERITY_INFO);
            save();
            addPartialTrigger(getPageBindings());
        }else {
            showPopupMessage(messObj != null ? messObj.toString() :
                             "Submitting the selected work done is failed.",
                             FacesMessage.SEVERITY_ERROR);
        }
    }

    public void setCancelWDDocBind(RichPopup cancelWDDocBind) {
        this.cancelWDDocBind = cancelWDDocBind;
    }

    public RichPopup getCancelWDDocBind() {
        return cancelWDDocBind;
    }

    public void cancelWDDocAL(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
            String cancelRemarks =
                String.valueOf(getCurrentPageFlowScope().get("CANCEL_REMARKS"));
            Object exeMessObj = executeMethod("cancelWRKDoneRow");
            if ("Success".equals(exeMessObj)) {
                //                save();
                showPopupMessage("Cancel action successful.",
                                 FacesMessage.SEVERITY_INFO);
            } else {
                showPopupMessage(exeMessObj != null ? exeMessObj.toString() :
                                 "Cancelling the selected document failed.",
                                 FacesMessage.SEVERITY_ERROR);
            }
        }
        addPartialTrigger(getPageBindings());
    }

    public void setContractNumBind(RichInputComboboxListOfValues contractNumBind) {
        this.contractNumBind = contractNumBind;
    }

    public RichInputComboboxListOfValues getContractNumBind() {
        return contractNumBind;
    }

  public void deleteAL(ActionEvent actionEvent)
  {
    try
    {
      String msg = (String) executeMethod("deleteWdLineWithDetails");
      if(!"Success".equalsIgnoreCase(msg))
      {
        showPopupMessage("Error raised while deleting Work Done Line along with details.",
                         FacesMessage.SEVERITY_ERROR);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
