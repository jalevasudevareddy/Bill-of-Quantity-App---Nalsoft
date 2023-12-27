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

public class CertifiedDetailsBean extends CommonBean {
    private RichInputFile attachFileBindgs;
    private RichInputDate fileCreationDateBindgs;
    private RichOutputText fileCcreatedByBindgs;
    private RichCommandLink fileNameBindgs;
    private RichCommandLink attachmentsColumnBind;
    private RichPopup attachmentFilesPopupBindgs;
    private RichTable certifiedSearchTable;
    private RichPopup createPopup;
    private RichPopup populateLinesPopup;
    private RichTable linesTableBindings;
    private RichPanelGroupLayout pageBindings;
    private RichInputText cummulativeQtyBindings;
    private RichPopup uploadCertifiedPopUp;
    private RichPopup validateCertifiedPopUp;
    private RichSelectBooleanCheckbox selectAllLineBindings;
    private RichTable popupTableBindings;
    private RichPopup cancelCertifiedDocPopupBindgs;
    private RichPopup deleteCertifiedPopupBindgs;
    private RichPopup reviseCertifiedDocPopupBindgs;
    private RichInputText actCertifiedQtyBindgs;
    private RichOutputText prevCummQtyBindgs;
    private RichPopup cancelCertDocPopupBindgs;
    private RichPopup deleteCertPopupBindgs;
    private RichOutputText invQtyBindgs;
    private RichOutputText prevCumultQtyBindgs;
    private RichPanelGroupLayout rejectView;
    private RichPopup approvePopupBindgs;
    private RichPopup rejectPopupBindgs;
    private RichPopup submitForApprPopupBindgs;
    private RichInputComboboxListOfValues buNameBindgs;
    private RichInputComboboxListOfValues prjNameBindgs;
    private RichInputComboboxListOfValues periodBindgs;
    private RichInputText CertQtyCummPerBindings;
    private RichInputText actCertQtyPerBindings;
    private RichInputText materialAtSiteBind;
    private RichInputText advanceRecoveredBind;
    private RichInputText retentionBind;
    private RichInputText otherPaymentsBind;
    private RichPopup cancelCertDocBind;
    private RichInputComboboxListOfValues contractNumberBInd;
    private RichInputText retentionReleaseBind;

    public CertifiedDetailsBean() {
        super();
    }

    public void createHeaderRow(ActionEvent ae) {
        executeMethod("createCertifiedHeaderRow");
        showPopup(getCreatePopup());
    }

    public void createDocument(ActionEvent ae) {
        try {
            save();
            Object message = executeMethod("createCertifiedDocument");
            //            Object message = executeMethod("validateCertifiedHdr");
            if ("Success".equals(message)) {
                save();
                //                executeMethod("editCertifiedDocument");
                navigate(ae, "edit");

            } else {
                CancelCertified(ae);
                showPopupMessage(message.toString(),
                                 FacesMessage.SEVERITY_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void cancelPopup(PopupCanceledEvent popupCanceledEvent) {
        executeMethod("cancelCertifiedDocument");
        save();
        if (getCreatePopup() != null)
            oracle.adf.view.rich.util.ResetUtils.reset(getCreatePopup());
        getCreatePopup().hide();
        addPartialTrigger(getCertifiedSearchTable());
    }

    public void editDocument(ActionEvent ae) {
        executeMethod("editCertifiedDocument");
        navigate(ae, "edit");
    }

    public void setCreatePopup(RichPopup createPopup) {
        this.createPopup = createPopup;
    }

    public RichPopup getCreatePopup() {
        return createPopup;
    }

    public void setCertifiedSearchTable(RichTable certifiedSearchTable) {
        this.certifiedSearchTable = certifiedSearchTable;
    }

    public RichTable getCertifiedSearchTable() {
        return certifiedSearchTable;
    }

    public void saveCertified(ActionEvent ae) {
        try {
            save();
            Object exemessIbj = executeMethod("validateCertLines");
            if (!"Success".equals(exemessIbj)) {
                showPopupMessage(exemessIbj != null ? exemessIbj.toString() :
                                 "Validation failed.",
                                 FacesMessage.SEVERITY_ERROR);
            }
            addPartialTrigger(getLinesTableBindings());
            addPartialTrigger(getPageBindings());
            //            Object message = executeMethod("calculateCertLineValues");
            //            save();
            //            if (message != null && message.toString().length() > 0) {
            //                showPopupMessage(message != null ? message.toString() : "",
            //                                 FacesMessage.SEVERITY_INFO);
            //            }
        } catch (Exception e) {
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void saveAndCloseCertified(ActionEvent ae) {
        try {
            save();
            //            Object message = executeMethod("calculateCertLineValues");
            //            save();
            //            if (message != null && message.toString().length() > 0) {
            //                showPopupMessage(message != null ? message.toString() : "",
            //                                 FacesMessage.SEVERITY_INFO);
            //            } else {
            navigate(ae, "done");
            //            }
        } catch (Exception e) {
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void cancelCertified(ActionEvent ae) {
        cancel();
    }


    public void getLines(ActionEvent actionEvent) {
        executeMethod("executePopulateCertifiedLinesVO");
        showPopup(getPopulateLinesPopup());
    }

    public void setPopulateLinesPopup(RichPopup populateLinesPopup) {
        this.populateLinesPopup = populateLinesPopup;
    }

    public RichPopup getPopulateLinesPopup() {
        return populateLinesPopup;
    }

    public void populateLines(ActionEvent actionEvent) {
        Object messObj = executeMethod("populateCertifiedLines");
        if ("Success".equals(messObj)) {
            save();
            executeMethod("executeCertifiedLinesQuery");
            addPartialTrigger(getLinesTableBindings());
        } else {
            showPopupMessage(messObj != null ? messObj.toString() :
                             "Unable to populate selected lines.",
                             FacesMessage.SEVERITY_ERROR);
        }
    }

    public void setLinesTableBindings(RichTable linesTableBindings) {
        this.linesTableBindings = linesTableBindings;
    }

    public RichTable getLinesTableBindings() {
        return linesTableBindings;
    }

    public void SubmitCertified(ActionEvent actionEvent) {
        try {
            save();
            Object docValObj = getEL("#{bindings.TransCumltTotal.inputValue}");
            if (docValObj != null) {
                Double docVal = new Double(docValObj.toString());
                if (docVal >= 0) {
                    Object message = executeMethod("validateCertLines");
                    if (message != null) {
                        save();
                        if ("Success".equals(message)) {
                            showPopup(getSubmitForApprPopupBindgs());
                        } else {
                            showPopupMessage(message != null ?
                                             message.toString() :
                                             "Document validation failed.",
                                             FacesMessage.SEVERITY_ERROR);
                        }
                    }
                } else {
                    showPopupMessage("Total cumulative amount cannot be less than 0.",
                                     FacesMessage.SEVERITY_ERROR);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        addPartialTrigger(getPageBindings());
    }


    public void CancelCertified(ActionEvent actionEvent) {
        try {
            executeMethod("cancelCertifiedDocument");
            save();
            if (getCreatePopup() != null)
                oracle.adf.view.rich.util.ResetUtils.reset(getCreatePopup());
            getCreatePopup().hide();
            addPartialTrigger(getCertifiedSearchTable());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void submitDocForApproval() {
        executeMethod("callpkgForAMEProcessSubmit2");
    }

    public void actHistAL(ActionEvent actionEvent) {
        Object qacHdrId = getEL("#{bindings.CertifiedHeaderId.inputValue}");
        getCurrentPageFlowScope().put("docId", qacHdrId);
        getCurrentPageFlowScope().put("docTypeId", "4");
        navigate(actionEvent, "ActionHistory");
    }

    public void ReviseCertified(ActionEvent actionEvent) {
        Object message = executeMethod("ReviseCertifiedDoc");
        if ("Success".equals(message)) {
            showPopupMessage("Document revised successfully.",
                             FacesMessage.SEVERITY_INFO);
        } else {
            showPopupMessage(message.toString(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getPageBindings());
    }

    public void validateLineQuantities(ValueChangeEvent valueChangeEvent) {

        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        getCummulativeQtyBindings().setValue(((BigDecimal)valueChangeEvent.getNewValue()).setScale(10,
                                                                                                   BigDecimal.ROUND_HALF_UP));
        //        Object msg = executeMethod("validateCertLineQuantities");
        //        if (msg != null && "Error".equals(msg)) {
        //            addPartialTrigger(getCummulativeQtyBindings());
        //            showPopupMessage("Cummulative Certified Qty Cannot be more than Bill Quantity.",
        //                             FacesMessage.SEVERITY_ERROR);
        //        } else if (msg != null && msg.toString().length() > 0) {
        //            addPartialTrigger(getCummulativeQtyBindings());
        //            showPopupMessage(msg.toString(), FacesMessage.SEVERITY_ERROR);
        //        }
        Object cerQtyBObj = valueChangeEvent.getNewValue();
        Object billQtyObj = getEL("#{bindings.BillQty.inputValue}");
        Object invCumltQtyObj =
            getEL("#{bindings.InvoiceCummulativeQty.inputValue}");
        if (cerQtyBObj != null && billQtyObj != null &&
            invCumltQtyObj != null) {
            Double certQty = new Double(cerQtyBObj.toString());
            Double billQty = new Double(billQtyObj.toString());
            Double invCumltQty = new Double(invCumltQtyObj.toString());
            if (billQty > 0) {
                if (certQty > billQty) {
                    getCummulativeQtyBindings().setValue(valueChangeEvent.getOldValue());
                    showPopupMessage("Cumulative certified quantity cannot be greater than bill quantity for a postive bill quantity i.e." +
                                     billQty, FacesMessage.SEVERITY_ERROR);
                } else if (certQty < 0) {
                    getCummulativeQtyBindings().setValue(valueChangeEvent.getOldValue());
                    showPopupMessage("Cumulative certified quantity cannot be less than zero for a postive bill quantity i.e." +
                                     billQty, FacesMessage.SEVERITY_ERROR);
//                } else if (certQty > invCumltQty) {
//                    getCummulativeQtyBindings().setValue(valueChangeEvent.getOldValue());
//                    showPopupMessage("Cumulative certified quantity cannot be greater than invoiced quantity for a postive bill quantity i.e." +
//                                     billQty, FacesMessage.SEVERITY_ERROR);
                }
            } else if (billQty < 0) {
                if (certQty < billQty) {
                    getCummulativeQtyBindings().setValue(valueChangeEvent.getOldValue());
                    showPopupMessage("Cumulative certified quantity cannot be less than bill quantity for a negative bill quantity i.e." +
                                     billQty, FacesMessage.SEVERITY_ERROR);
                } else if (certQty > 0) {
                    getCummulativeQtyBindings().setValue(valueChangeEvent.getOldValue());
                    showPopupMessage("Cumulative certified quantity cannot be greater than zero for a negative bill quantity i.e." +
                                     billQty, FacesMessage.SEVERITY_ERROR);
//                } else if (certQty < invCumltQty) {
//                    getCummulativeQtyBindings().setValue(valueChangeEvent.getOldValue());
//                    showPopupMessage("Cumulative certified quantity cannot be less than invoiced quantity for a postive bill quantity i.e." +
//                                     billQty, FacesMessage.SEVERITY_ERROR);
                }
            }

            //                else if(billQty<prevCumltQty){
            //                    getCummulativeQtyBindings().setValue(valueChangeEvent.getOldValue());
            //                    showPopupMessage("Cummulative Certified Qty cannot be less than previous cumulative certified Qty i.e."+cerQtyBObj, FacesMessage.SEVERITY_ERROR);
            //                }
        } else {
            getCummulativeQtyBindings().setValue(valueChangeEvent.getOldValue());
            showPopupMessage("Invalid values found i.e bill quantity=" +
                             billQtyObj + " , cumulative quantity =" +
                             cerQtyBObj, FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getCertQtyCummPerBindings());
        addPartialTrigger(getCummulativeQtyBindings());
    }

    public void setCummulativeQtyBindings(RichInputText cummulativeQtyBindings) {
        this.cummulativeQtyBindings = cummulativeQtyBindings;
    }

    public RichInputText getCummulativeQtyBindings() {
        return cummulativeQtyBindings;
    }


    public void downloadExcel(FacesContext facesContext,
                              OutputStream outputStream) {
        byte[] report = (byte[])executeMethod("downloadCertifiedDetails");
        try {
            outputStream.write(report);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fileXLUploaded(ValueChangeEvent valueChangeEvent) {
        UploadedFile uploadedFile =
            (UploadedFile)valueChangeEvent.getNewValue();
        Map params = new HashMap();
        params.put("uploadedFile", uploadedFile);
        Object msg = executeMethod("uploadCertifiedXLFile", params);
        if ("Success".equals(msg)) {
            save();
            //            showPopup(getValidateCertifiedPopUp());
        } else {
            showPopupMessage(msg != null ? msg.toString() :
                             "File import failed.",
                             FacesMessage.SEVERITY_ERROR);
        }
    }

    public void uploadCertifiedDialog(DialogEvent dialogEvent) {
        try {
            if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
                Object exeMessObj =
                    executeMethod("validateCertifiedUploadDetails");
                if ("Success".equals(exeMessObj)) {
                    //                    refreshPage();
                    executeMethod("executeCertifiedLinesQuery");
                    showPopupMessage("File import successful.",
                                     FacesMessage.SEVERITY_INFO);
                } else {
                    showPopupMessage(exeMessObj != null ?
                                     exeMessObj.toString() :
                                     "File import failed.",
                                     FacesMessage.SEVERITY_ERROR);
                }
            }
            getUploadCertifiedPopUp().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
        addPartialTrigger(getLinesTableBindings());

        addPartialTrigger(getPageBindings());
    }

    public void setUploadCertifiedPopUp(RichPopup uploadCertifiedPopUp) {
        this.uploadCertifiedPopUp = uploadCertifiedPopUp;
    }

    public RichPopup getUploadCertifiedPopUp() {
        return uploadCertifiedPopUp;
    }

    public void setValidateCertifiedPopUp(RichPopup validateCertifiedPopUp) {
        this.validateCertifiedPopUp = validateCertifiedPopUp;
    }

    public RichPopup getValidateCertifiedPopUp() {
        return validateCertifiedPopUp;
    }

    public void uploadCertifiedDetails(ActionEvent actionEvent) {

        if (getUploadCertifiedPopUp() != null)
            oracle.adf.view.rich.util.ResetUtils.reset(getUploadCertifiedPopUp());

        showPopup(getUploadCertifiedPopUp());
    }

    public void approve(ActionEvent actionEvent) {
        setEL("#{bindings.CertifiedStatus.inputValue}", "Approved");
        save();
        addPartialTrigger(getPageBindings());
    }

    public void selectAllVL(ValueChangeEvent valueChangeEvent) {
        if (!(valueChangeEvent.getNewValue().equals(valueChangeEvent.getOldValue()))) {
            Boolean markAs = (Boolean)valueChangeEvent.getNewValue();
            getCurrentPageFlowScope().put("markAs", markAs);
            executeMethod("markAllCertLinesAs");
            addPartialTrigger(getPopupTableBindings());
        }
    }


    public void cancelCertifiedPopupAL(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.yes) {
            Object exeMessObj = executeMethod("cancelInvoiceRow");
            if ("Success".equals(exeMessObj)) {
                save();
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

    public void deleteCertifiedPopupAL(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.yes) {
            Object exeMessObj = executeMethod("deleteCertifiedRow");
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

    public void CertifiedLinesDeleteAll(DialogEvent dialogEvent) {
        Object msg = executeMethod("CertifiedLinesDeleteAll");
        if (msg != null && (!"Success".equals(msg))) {
            showPopupMessage(msg.toString(), FacesMessage.SEVERITY_ERROR);
        } else {
            addPartialTrigger(getLinesTableBindings());
        }
    }

    public void reviseCertifiedPopupAL(DialogEvent dialogEvent) {
        Object message = executeMethod("ReviseCertifiedDoc");
        if ("Success".equals(message)) {
            showPopupMessage("Document revision successful.",
                             FacesMessage.SEVERITY_INFO);
        } else {
            showPopupMessage(message.toString(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getPageBindings());
    }


    public void actCertQtyVL(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        try {
            Object billQtyObj = getEL("#{bindings.BillQty.inputValue}");
            if (billQtyObj != null && valueChangeEvent.getNewValue() != null) {
                Double billQty = new Double(billQtyObj.toString());
                Double newVal =
                    new Double(valueChangeEvent.getNewValue().toString());
                if (newVal.doubleValue() > 0) {
                    if (billQty.doubleValue() < newVal.doubleValue()) {
                        getActCertifiedQtyBindgs().setValue(valueChangeEvent.getOldValue());
                        showPopupMessage("Actual invoice amount cannot be greater than bill quantity i.e. " +
                                         billQty, FacesMessage.SEVERITY_ERROR);
                    }
                } else {
                    getActCertifiedQtyBindgs().setValue(valueChangeEvent.getOldValue());
                    showPopupMessage("Actual invoice amount cannot be less than 0.",
                                     FacesMessage.SEVERITY_ERROR);
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        addPartialTrigger(getActCertifiedQtyBindgs());


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

    public void setPopupTableBindings(RichTable popupTableBindings) {
        this.popupTableBindings = popupTableBindings;
    }

    public RichTable getPopupTableBindings() {
        return popupTableBindings;
    }

    public void setCancelCertifiedDocPopupBindgs(RichPopup cancelCertifiedDocPopupBindgs) {
        this.cancelCertifiedDocPopupBindgs = cancelCertifiedDocPopupBindgs;
    }

    public RichPopup getCancelCertifiedDocPopupBindgs() {
        return cancelCertifiedDocPopupBindgs;
    }

    public void setDeleteCertifiedPopupBindgs(RichPopup deleteCertifiedPopupBindgs) {
        this.deleteCertifiedPopupBindgs = deleteCertifiedPopupBindgs;
    }

    public RichPopup getDeleteCertifiedPopupBindgs() {
        return deleteCertifiedPopupBindgs;
    }

    public void setReviseCertifiedDocPopupBindgs(RichPopup reviseCertifiedDocPopupBindgs) {
        this.reviseCertifiedDocPopupBindgs = reviseCertifiedDocPopupBindgs;
    }

    public RichPopup getReviseCertifiedDocPopupBindgs() {
        return reviseCertifiedDocPopupBindgs;
    }

    public void setActCertifiedQtyBindgs(RichInputText actCertifiedQtyBindgs) {
        this.actCertifiedQtyBindgs = actCertifiedQtyBindgs;
    }

    public RichInputText getActCertifiedQtyBindgs() {
        return actCertifiedQtyBindgs;
    }

    public void setPrevCummQtyBindgs(RichOutputText prevCummQtyBindgs) {
        this.prevCummQtyBindgs = prevCummQtyBindgs;
    }

    public RichOutputText getPrevCummQtyBindgs() {
        return prevCummQtyBindgs;
    }

    public void setCancelCertDocPopupBindgs(RichPopup cancelCertDocPopupBindgs) {
        this.cancelCertDocPopupBindgs = cancelCertDocPopupBindgs;
    }

    public RichPopup getCancelCertDocPopupBindgs() {
        return cancelCertDocPopupBindgs;
    }

    public void setDeleteCertPopupBindgs(RichPopup deleteCertPopupBindgs) {
        this.deleteCertPopupBindgs = deleteCertPopupBindgs;
    }

    public RichPopup getDeleteCertPopupBindgs() {
        return deleteCertPopupBindgs;
    }

    public void cancelCertDocPopupAL(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.yes) {
            showPopup(getCancelCertDocBind());
        }
    }

    public void deleteCertDocPopupAL(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.yes) {
            Object exeMessObj = executeMethod("deleteCertDocRow");
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

    public void actualQtyVL(ValueChangeEvent valueChangeEvent) {

        Object msg = executeMethod("validateCertLineActQty");
        if (msg != null && "Error".equals(msg)) {
            addPartialTrigger(getCummulativeQtyBindings());
            showPopupMessage("Cumulative actual quantity cannot be more than bill quantity.",
                             FacesMessage.SEVERITY_ERROR);
        } else if (msg != null && msg.toString().length() > 0) {
            addPartialTrigger(getCummulativeQtyBindings());
            showPopupMessage(msg.toString(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getCertQtyCummPerBindings());
    }

    public void setInvQtyBindgs(RichOutputText invQtyBindgs) {
        this.invQtyBindgs = invQtyBindgs;
    }

    public RichOutputText getInvQtyBindgs() {
        return invQtyBindgs;
    }

    public void setPrevCumultQtyBindgs(RichOutputText prevCumultQtyBindgs) {
        this.prevCumultQtyBindgs = prevCumultQtyBindgs;
    }

    public RichOutputText getPrevCumultQtyBindgs() {
        return prevCumultQtyBindgs;
    }

    public void validateCertDocAL(ActionEvent actionEvent) {
        save();
        Object exemessIbj = executeMethod("validateCertLines");
        if ("Success".equals(exemessIbj)) {
            showPopupMessage("Validated successfully.",
                             FacesMessage.SEVERITY_INFO);
        } else {
            showPopupMessage(exemessIbj != null ? exemessIbj.toString() :
                             "Validation failed.",
                             FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getLinesTableBindings());
        addPartialTrigger(getPageBindings());
        //
    }

    public void rejectDoc(ActionEvent actionEvent) {
        // Add event code here...
        String actionRemarks =
            (String)getEL("#{pageFlowScope.APPROVAL_REMARKS}");
        if (actionRemarks != null && !(actionRemarks.equals(""))) {
            NotificationBean bean = new NotificationBean();
            bean.rejectDialogEvent();
        } else {
            showPopupMessage("Rejection remarks are required",
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

    public void withdrawSubmission(ActionEvent actionEvent) {
        try {
            save();
            Object remarksObj = getEL("#{pageFlowScope.WITHDRAWL_REASON}");
            this.getCurrentPageFlowScope().put("remarks", remarksObj);
            Object withdtwSubMesObj =
                executeMethod("withdrawCertDocFromApproval");
            if ("Success".equals(withdtwSubMesObj)) {
                save();
                showPopupMessage("Your changes were saved.",
                                 FacesMessage.SEVERITY_INFO);
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

    public void setApprovePopupBindgs(RichPopup approvePopupBindgs) {
        this.approvePopupBindgs = approvePopupBindgs;
    }

    public RichPopup getApprovePopupBindgs() {
        return approvePopupBindgs;
    }


    public void setRejectPopupBindgs(RichPopup rejectPopupBindgs) {
        this.rejectPopupBindgs = rejectPopupBindgs;
    }

    public RichPopup getRejectPopupBindgs() {
        return rejectPopupBindgs;
    }


    public void setSubmitForApprPopupBindgs(RichPopup submitForApprPopupBindgs) {
        this.submitForApprPopupBindgs = submitForApprPopupBindgs;
    }

    public RichPopup getSubmitForApprPopupBindgs() {
        return submitForApprPopupBindgs;
    }

    public void submitForApprlPopupAL(ActionEvent actionEvent) {
        try {
            Object exeMess = executeMethod("callpkgForAMECertBOQDocSubmit");
            if ("Success".equals(exeMess)) {
                save();
                showPopupMessage("The document was submitted for approval.",
                                 FacesMessage.SEVERITY_INFO);
            } else if("Approved".equals(exeMess)){
            showPopupMessage("Document  successfully approved.",
                             FacesMessage.SEVERITY_INFO);
            } else {
                showPopupMessage(exeMess != null ? exeMess.toString() : "",
                                 FacesMessage.SEVERITY_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        addPartialTrigger(getPageBindings());
    }

    public void setBuNameBindgs(RichInputComboboxListOfValues buNameBindgs) {
        this.buNameBindgs = buNameBindgs;
    }

    public RichInputComboboxListOfValues getBuNameBindgs() {
        return buNameBindgs;
    }

    public void buNameVL(ValueChangeEvent valueChangeEvent) {
        try {
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            Map<String, Object> attrMap = new HashMap<String, Object>();
            attrMap.put("TransProjectNum", null);
            attrMap.put("TransProjectId", null);

            attrMap.put("TransPeriod", null);


            getCurrentPageFlowScope().put("attributesMap", attrMap);
            Object exeMessObj = executeMethod("refreshCertHdrVOAttrs");

            if (!"Success".equals(exeMessObj)) {
                showPopupMessage(exeMessObj != null ? exeMessObj.toString() :
                                 "Unable to refresh trans certified row.",
                                 FacesMessage.SEVERITY_ERROR);
            }


        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getContractNumberBInd());

        addPartialTrigger(getPeriodBindgs());
    }

    public void setPrjNameBindgs(RichInputComboboxListOfValues prjNameBindgs) {
        this.prjNameBindgs = prjNameBindgs;
    }

    public RichInputComboboxListOfValues getPrjNameBindgs() {
        return prjNameBindgs;
    }

    public void prjNumVL(ValueChangeEvent valueChangeEvent) {
        try {
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            Map<String, Object> attrMap = new HashMap<String, Object>();
            attrMap.put("TransPeriod", null);
            getCurrentPageFlowScope().put("attributesMap", attrMap);
            Object exeMessObj = executeMethod("refreshCertHdrVOAttrs");

            if (!"Success".equals(exeMessObj)) {
                showPopupMessage(exeMessObj != null ? exeMessObj.toString() :
                                 "Unable to refresh trans certified row.",
                                 FacesMessage.SEVERITY_ERROR);
            }


        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getPeriodBindgs());
    }

    public void contractNumberVL(ValueChangeEvent valueChangeEvent) {
        try {
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            Map<String, Object> attrMap = new HashMap<String, Object>();
            attrMap.put("TransPeriod", null);
            getCurrentPageFlowScope().put("attributesMap", attrMap);
            Object exeMessObj = executeMethod("refreshCertHdrVOAttrs");

            if (!"Success".equals(exeMessObj)) {
                showPopupMessage(exeMessObj != null ? exeMessObj.toString() :
                                 "Unable to refresh trans certified row.",
                                 FacesMessage.SEVERITY_ERROR);
            }


        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getPeriodBindgs());
    }
    public void setPeriodBindgs(RichInputComboboxListOfValues periodBindgs) {
        this.periodBindgs = periodBindgs;
    }

    public RichInputComboboxListOfValues getPeriodBindgs() {
        return periodBindgs;
    }

    public void setCertQtyCummPerBindings(RichInputText CertQtyCummPerBindings) {
        this.CertQtyCummPerBindings = CertQtyCummPerBindings;
    }

    public RichInputText getCertQtyCummPerBindings() {
        return CertQtyCummPerBindings;
    }

    public void CertCummQtyPerChanged(ValueChangeEvent valueChangeEvent) {

        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        Object msg = executeMethod("validateCertLinePercentage");
        if (msg != null && msg.toString().length() > 0) {
            showPopupMessage(msg.toString(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getCummulativeQtyBindings());
        addPartialTrigger(getCertQtyCummPerBindings());
    }

    public void setActCertQtyPerBindings(RichInputText actCertQtyPerBindings) {
        this.actCertQtyPerBindings = actCertQtyPerBindings;
    }

    public RichInputText getActCertQtyPerBindings() {
        return actCertQtyPerBindings;
    }

    public void ActCertQtyPerChanged(ValueChangeEvent valueChangeEvent) {

        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        Object msg = executeMethod("validateCertLineActPercentage");
        if (msg != null && msg.toString().length() > 0) {
            showPopupMessage(msg.toString(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getActCertifiedQtyBindgs());
        addPartialTrigger(getActCertQtyPerBindings());
    }

    public void cancelAL(ActionEvent actionEvent) {
        //        cancel();
    }

    public void saveBoqLinesAL(ActionEvent actionEvent) {
        try {
            save();
            executeMethod("executeInvoicedLinesQuery");
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
                Object exeMess =
                    executeMethod("saveAttachedFileCertification");
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
            bindingsImpl.findIteratorBinding("AttachmentsVO3Iterator");
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

    public void materialAtSiteVL(ValueChangeEvent valueChangeEvent) {
        processUpdates(valueChangeEvent);
        addPartialTrigger(getMaterialAtSiteBind());
    }

    public void setMaterialAtSiteBind(RichInputText materialAtSiteBind) {
        this.materialAtSiteBind = materialAtSiteBind;
    }

    public RichInputText getMaterialAtSiteBind() {
        return materialAtSiteBind;
    }

    public void AdvanceRecoveredVL(ValueChangeEvent valueChangeEvent) {
        processUpdates(valueChangeEvent);
        addPartialTrigger(getAdvanceRecoveredBind());
    }

    public void setAdvanceRecoveredBind(RichInputText advanceRecoveredBind) {
        this.advanceRecoveredBind = advanceRecoveredBind;
    }

    public RichInputText getAdvanceRecoveredBind() {
        return advanceRecoveredBind;
    }

    public void setRetentionBind(RichInputText retentionBind) {
        this.retentionBind = retentionBind;
    }

    public RichInputText getRetentionBind() {
        return retentionBind;
    }

    public void RetentionVL(ValueChangeEvent valueChangeEvent) {
        processUpdates(valueChangeEvent);
        addPartialTrigger(getRetentionBind());
    }
    public void retentionReleaseVL(ValueChangeEvent valueChangeEvent) {
        processUpdates(valueChangeEvent);
        addPartialTrigger(getRetentionReleaseBind());
    }
    public void OtherPaymentsVL(ValueChangeEvent valueChangeEvent) {
        processUpdates(valueChangeEvent);
        addPartialTrigger(getOtherPaymentsBind());
    }

    public void setOtherPaymentsBind(RichInputText otherPaymentsBind) {
        this.otherPaymentsBind = otherPaymentsBind;
    }

    public RichInputText getOtherPaymentsBind() {
        return otherPaymentsBind;
    }

    public void setCancelCertDocBind(RichPopup cancelCertDocBind) {
        this.cancelCertDocBind = cancelCertDocBind;
    }

    public RichPopup getCancelCertDocBind() {
        return cancelCertDocBind;
    }

    public void cancelCertDocAL(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
            String cancelRemarks =
                String.valueOf(getCurrentPageFlowScope().get("CANCEL_REMARKS"));
            Object exeMessObj = executeMethod("cancelCertDocRow");
            if ("Success".equals(exeMessObj)) {
                                save();
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


    public void recalculateValues(ActionEvent actionEvent) {
        try{
            String res = (String)executeMethod("calcualteCertifiedValues");
            save();
            addPartialTrigger(getPageBindings());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void setContractNumberBInd(RichInputComboboxListOfValues contractNumberBInd) {
        this.contractNumberBInd = contractNumberBInd;
    }

    public RichInputComboboxListOfValues getContractNumberBInd() {
        return contractNumberBInd;
    }

    public void setRetentionReleaseBind(RichInputText retentionReleaseBind) {
        this.retentionReleaseBind = retentionReleaseBind;
    }

    public RichInputText getRetentionReleaseBind() {
        return retentionReleaseBind;
    }

}
