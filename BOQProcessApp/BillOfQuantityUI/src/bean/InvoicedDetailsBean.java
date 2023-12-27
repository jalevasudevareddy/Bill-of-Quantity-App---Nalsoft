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
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelHeader;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;

import oracle.binding.BindingContainer;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.BlobDomain;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class InvoicedDetailsBean extends CommonBean {
    private RichInputFile attachFileBindgs;
    private RichInputDate fileCreationDateBindgs;
    private RichOutputText fileCcreatedByBindgs;
    private RichCommandLink fileNameBindgs;
    private RichCommandLink attachmentsColumnBind;
    private RichPopup attachmentFilesPopupBindgs;
    private RichTable invoicedSearchTable;
    private RichPopup createPopup;
    private RichPopup populateLinesPopup;
    private RichTable linesTableBindings;
    private RichInputText cummulativeQtyBindings;
    private RichPopup uploadInvoicedPopUp;
    private RichPopup validateInvoicedPopUp;
    private RichPanelGroupLayout pageBindings;
    private RichSelectBooleanCheckbox selectAllLineBindings;
    private RichTable popupTableBindings;
    private RichPopup cancelInvoiceDocPopupBindgs;
    private RichPopup deleteInvoicePopupBindgs;
    private RichPopup reviseInvoiceDocPopupBindgs;
    private RichInputText actInvoiceQtyBindgs;
    private RichOutputText prevCummQtyBindgs;
    private RichPopup withdrawInvDocPopupBindgs;
    private RichPanelGroupLayout rejectView;
    private RichInputText invQtyCummPerBindings;
    private RichInputText actInvQtyPerBindings;
    private RichPopup submitConfirmationPopupBind;
    private RichInputText materialAtSiteBind;
    private RichInputText advanceRecoveredBind;
    private RichInputText retentionBind;
    private RichInputText otherPaymentsBind;
    private RichPopup cancelInvoiceDocBind;
    private RichInputText retentionReleaseBind;

    public InvoicedDetailsBean() {
        super();
    }

    public void createHeaderRow(ActionEvent ae) {
        executeMethod("createInvoicedHeaderRow");
        showPopup(getCreatePopup());
    }

    public void createDocument(ActionEvent ae) {
        try {
            save();
            Object message = executeMethod("validateInvoicedHdr");
            if ("Success".equals(message)) {
                message = executeMethod("createInvoicedDocument");
                if ("Success".equals(message)) {
                    save();
                    executeMethod("editInvoicedDocument");
                    navigate(ae, "Edit");
                } else {
                    CancelInvoiced(ae);
                    showPopupMessage(message != null ? message.toString() :
                                     "Unable to create new invoice document.",
                                     FacesMessage.SEVERITY_ERROR);
                }
            } else {
                CancelInvoiced(ae);
                showPopupMessage(message != null ? message.toString() :
                                 "Unable to create new invoice document.",
                                 FacesMessage.SEVERITY_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }


    public void cancelPopup(PopupCanceledEvent popupCanceledEvent) {
        executeMethod("cancelInvoicedDocument");
        save();
        if (getCreatePopup() != null)
            oracle.adf.view.rich.util.ResetUtils.reset(getCreatePopup());
        getCreatePopup().hide();
        addPartialTrigger(getInvoicedSearchTable());
    }

    public void editDocument(ActionEvent ae) {
        executeMethod("editInvoicedDocument");
        navigate(ae, "Edit");
    }

    public void setCreatePopup(RichPopup createPopup) {
        this.createPopup = createPopup;
    }

    public RichPopup getCreatePopup() {
        return createPopup;
    }

    public void setInvoicedSearchTable(RichTable invoicedSearchTable) {
        this.invoicedSearchTable = invoicedSearchTable;
    }

    public RichTable getInvoicedSearchTable() {
        return invoicedSearchTable;
    }

    public void saveInvoiced(ActionEvent ae) {
        try {
            save();
            Object invHeaderId =
                getEL("#{bindings.InvoicedHeaderId.inputValue}");
            if (invHeaderId != null) {
                getCurrentPageFlowScope().put("invHeaderId", invHeaderId);
                Object isSettedObj =
                    executeMethod("makeAsCurrentInvoiceHdrRow");
                if (isSettedObj != null && isSettedObj instanceof Boolean) {
                    Boolean isSetted = (Boolean)isSettedObj;
                    if (isSetted) {
                        executeMethod("executeInvoicedLinesQuery");
                        Object message =
                            executeMethod("validateInvoicedLines");
                        if (message != null) {
                            if (!"Success".equals(message)) {
                                addPartialTrigger(getLinesTableBindings());
                                showPopupMessage(message.toString(),
                                                 FacesMessage.SEVERITY_ERROR);
                            } else {
                                addPartialTrigger(getLinesTableBindings());
                                addPartialTrigger(getPageBindings());
                            }
                        }
                        addPartialTrigger(getPageBindings());
                        //            Object message = executeMethod("calculateInvoiceLineValues");
                        //            save();
                        //            if (message != null && message.toString().length() > 0) {
                        //                showPopupMessage(message != null ? message.toString() : "",
                        //                                 FacesMessage.SEVERITY_INFO);
                        //            }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void saveAndCloseInvoiced(ActionEvent ae) {
        try {
            save();
            executeMethod("executeInvoicedLinesQuery");
            //            Object message = executeMethod("calculateInvoiceLineValues");
            //            save();
            //            if (message != null && message.toString().length() > 0) {
            //                showPopupMessage(message != null ? message.toString() : "",
            //                                 FacesMessage.SEVERITY_INFO);
            //            }else{
            navigate(ae, "Done");
            //            }
        } catch (Exception e) {
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

    }

    public void cancelInvoiced(ActionEvent ae) {
        cancel();
    }


    public void getLines(ActionEvent actionEvent) {
        try {
            save();
            Object invHeaderId =
                getEL("#{bindings.InvoicedHeaderId.inputValue}");
            if (invHeaderId != null) {
                getCurrentPageFlowScope().put("invHeaderId", invHeaderId);
                Object isSettedObj =
                    executeMethod("makeAsCurrentInvoiceHdrRow");
                if (isSettedObj != null && isSettedObj instanceof Boolean) {
                    Boolean isSetted = (Boolean)isSettedObj;
                    if (isSetted) {
                        executeMethod("executeInvoicedLinesQuery");
                        Object exeMessObj =
                            executeMethod("executePopulateInvoicedLinesVO");
                        showPopup(getPopulateLinesPopup());
                    } else {
                        showPopupMessage("Error while setting current row. Please refresh the page.",
                                         FacesMessage.SEVERITY_ERROR);
                    }
                }
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
        executeMethod("populateInvoicedLines");
            Object invHeaderId =
                getEL("#{bindings.InvoicedHeaderId.inputValue}");
            if (invHeaderId != null) {
                getCurrentPageFlowScope().put("invHeaderId", invHeaderId);
                Object isSettedObj =
                    executeMethod("makeAsCurrentInvoiceHdrRow");
            }
        //        save();
        executeMethod("executeInvoicedLinesQuery");
        addPartialTrigger(getLinesTableBindings());
    }

    public void setLinesTableBindings(RichTable linesTableBindings) {
        this.linesTableBindings = linesTableBindings;
    }

    public RichTable getLinesTableBindings() {
        return linesTableBindings;
    }

    public void SubmitInvoiced(ActionEvent actionEvent) {
        Object paymentDueDate = getEL("#{bindings.PaymentDueDate.inputValue}");
        if (paymentDueDate!=null){
        Object cummInvAmt = getEL("#{bindings.TransGrandTotal.inputValue}");
        Double cummAmt =
            cummInvAmt != null ? new Double(cummInvAmt.toString()) : -1;
        if (cummAmt < 0) {
            showPopupMessage("Cumulative amount for a invoice cannot be negative.",
                             FacesMessage.SEVERITY_ERROR);
        } else {
            Object message = executeMethod("validateInvoicedLines");
            Object obj = getEL("#{bindings.TransBOQSatus.inputValue}");
            if (message != null) {
                if (!"Success".equals(message)) {
                    showPopupMessage(message.toString(),
                                     FacesMessage.SEVERITY_ERROR);
                } else {
                    if ("RV".equals(obj)) {
                        getCurrentPageFlowScope().put("BOQStatus", "RV");
                    } else {
                        getCurrentPageFlowScope().put("BOQStatus", "AC");
                    }
                    showPopup(getSubmitConfirmationPopupBind());
                    //                    else{
                    //                        Object msg = submitDocForApproval();
                    //                        save();
                    //                        addPartialTrigger(getPageBindings());
                    //                        if (msg != null) {
                    //                            if ("Success".equals(msg)) {
                    //                                msg = "The document was submitted for approval.";
                    //                            }
                    //                        } else {
                    //                            msg = "Unable to submit the document.";
                    //                        }
                    //                        showPopupMessage(msg != null ? msg.toString() : "", FacesMessage.SEVERITY_INFO);
                    //                    }
                }
                addPartialTrigger(getPageBindings());
            }
        }
        }else{
            showPopupMessage("Payment due date is required. ", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void submitForApprovalPopupDialogListener(DialogEvent dialogEvent) {
        Object msg = submitDocForApproval();
        save();
            Object invHeaderId =
                getEL("#{bindings.InvoicedHeaderId.inputValue}");
            if (invHeaderId != null) {
                getCurrentPageFlowScope().put("invHeaderId", invHeaderId);
                Object isSettedObj =
                    executeMethod("makeAsCurrentInvoiceHdrRow");
            }
        addPartialTrigger(getPageBindings());
        if (msg != null) {
            if ("Success".equals(msg)) {
                msg = "The document was submitted for approval.";
            }
        } else {
            msg = "Unable to submit the document.";
        }
        showPopupMessage(msg != null ? msg.toString() : "",
                         FacesMessage.SEVERITY_INFO);
        addPartialTrigger(getPageBindings());
    }

    public void CancelInvoiced(ActionEvent actionEvent) {

        executeMethod("cancelInvoicedDocument");
        cancel();
        if (getCreatePopup() != null)
            oracle.adf.view.rich.util.ResetUtils.reset(getCreatePopup());
        getCreatePopup().hide();
        addPartialTrigger(getInvoicedSearchTable());
    }

    public void InvoicedLinesDeleteAll(DialogEvent dialogEvent) {
        Object msg = executeMethod("InvoicedLinesDeleteAll");
        if (msg != null && (!"Success".equals(msg))) {
            showPopupMessage(msg.toString(), FacesMessage.SEVERITY_ERROR);
        } else {
            addPartialTrigger(getLinesTableBindings());
        }
    }

    public Object submitDocForApproval() {
        Object msg = executeMethod("callpkgForAMEProcessSubmit1");
        //        Object msg = null;
        try {
            setEL("#{bindings.InvoicedStatus.inputValue}", "I");
            save();
            Object invHeaderId =
                getEL("#{bindings.InvoicedHeaderId.inputValue}");
            if (invHeaderId != null) {
                getCurrentPageFlowScope().put("invHeaderId", invHeaderId);
                Object isSettedObj =
                    executeMethod("makeAsCurrentInvoiceHdrRow");
            }
            executeMethod("executeInvoicedLinesQuery");
            msg = "Success";
        } catch (Exception e) {
            msg = e.getMessage();
        }
        return msg;
    }

    public void actHistAL(ActionEvent actionEvent) {
        Object qacHdrId = getEL("#{bindings.InvoicedHeaderId.inputValue}");
        getCurrentPageFlowScope().put("docId", qacHdrId);
        getCurrentPageFlowScope().put("docTypeId", "3");
        navigate(actionEvent, "ActionHistory");
    }

    public void ReviseInvoiced(ActionEvent actionEvent) {
        Object message = executeMethod("ReviseInvoicedDoc");
        if (message != null && message.toString().length() > 0) {
            showPopupMessage(message.toString(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getPageBindings());
    }

    public void validateLineQuantities(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        getCummulativeQtyBindings().setValue(((BigDecimal)valueChangeEvent.getNewValue()).setScale(10,
                                                                                                   BigDecimal.ROUND_HALF_UP));
        //        Object msg = executeMethod("validateInvoicedLineQuantities");
        //        if (msg != null && "Error".equals(msg)) {
        //            addPartialTrigger(getCummulativeQtyBindings());
        //            showPopupMessage("Cummulative Invoiced Qty Cannot be more than Bill Quantity.",
        //                             FacesMessage.SEVERITY_ERROR);
        //        } else if (msg != null && msg.toString().length() > 0) {
        //            addPartialTrigger(getCummulativeQtyBindings());
        //            showPopupMessage(msg.toString(), FacesMessage.SEVERITY_ERROR);
        //        }

        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        //            Object exeMess = executeMethod("validateWDLineCumltQty");
        Object invQtyObj = valueChangeEvent.getNewValue();
        Object billQtyObj = getEL("#{bindings.BillQty.inputValue}");
        if (invQtyObj != null && billQtyObj != null) {
            Double wdQty = new Double(invQtyObj.toString());
            Double billQty = new Double(billQtyObj.toString());
            //                if(wdQty>billQty){
            //                    getCummulativeQtyBindings().setValue(valueChangeEvent.getOldValue());
            //                    showPopupMessage("Cummulative Invoice Qty cannot be greater than Bill Qty i.e."+billQty, FacesMessage.SEVERITY_ERROR);
            //                }
        } else {
            getCummulativeQtyBindings().setValue(valueChangeEvent.getOldValue());
            showPopupMessage("Invalid values found i.e bill quantity=" +
                             billQtyObj + " , cumulative quantity =" +
                             invQtyObj, FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getInvQtyCummPerBindings());
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
        byte[] report = (byte[])executeMethod("downloadInvoicedDetails");
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
        Object msg = executeMethod("uploadInvoicedXLFile", params);
        if (msg != null && !("UPLOADED".equals(msg)))
            showPopupMessage(msg.toString(), FacesMessage.SEVERITY_ERROR);
        else if (msg != null && ("UPLOADED".equals(msg))) {
            save();
            executeMethod("executeInvoicedLinesQuery");
            //            showPopup(getValidateInvoicedPopUp());
        }
    }

    public void uploadInvoicedDialog(DialogEvent dialogEvent) {
        try {
            if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
                Object exeMessObj =
                    executeMethod("validateInvoicedUploadDetails");
                if ("Success".equals(exeMessObj)) {
                    //                    refreshPage();
                    showPopupMessage("File import successful.",
                                     FacesMessage.SEVERITY_INFO);
                    addPartialTrigger(getLinesTableBindings());
                } else {
                    showPopupMessage(exeMessObj != null ?
                                     exeMessObj.toString() :
                                     "File import failed.",
                                     FacesMessage.SEVERITY_ERROR);
                }
                addPartialTrigger(getPageBindings());

            }
            getUploadInvoicedPopUp().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUploadInvoicedPopUp(RichPopup uploadInvoicedPopUp) {
        this.uploadInvoicedPopUp = uploadInvoicedPopUp;
    }

    public RichPopup getUploadInvoicedPopUp() {
        return uploadInvoicedPopUp;
    }

    public void setValidateInvoicedPopUp(RichPopup validateInvoicedPopUp) {
        this.validateInvoicedPopUp = validateInvoicedPopUp;
    }

    public RichPopup getValidateInvoicedPopUp() {
        return validateInvoicedPopUp;
    }

    public void uploadInvoicedDetails(ActionEvent actionEvent) {
        showPopup(getUploadInvoicedPopUp());
    }

    public void approve(ActionEvent actionEvent) {
        setEL("#{bindings.InvoicedStatus.inputValue}", "Approved");
        save();
        addPartialTrigger(getPageBindings());
    }


    public void selectAllVL(ValueChangeEvent valueChangeEvent) {
        if (!(valueChangeEvent.getNewValue().equals(valueChangeEvent.getOldValue()))) {
            Boolean markAs = (Boolean)valueChangeEvent.getNewValue();
            getCurrentPageFlowScope().put("markAs", markAs);
            executeMethod("markAllInvLinesAs");
            addPartialTrigger(getPopupTableBindings());
        }
    }


    public void cancelInvoicePopupAL(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.yes) {
            showPopup(getCancelInvoiceDocBind());
        }
        addPartialTrigger(getPageBindings());
    }

    public void deleteInvoicePopupAL(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.yes) {
            Object exeMessObj = executeMethod("deleteInvoiceRow");
            if ("Success".equals(exeMessObj)) {
                save();
                navigate(dialogEvent, "Done");
            } else {
                showPopupMessage(exeMessObj != null ? exeMessObj.toString() :
                                 "Deleting the selected document failed.",
                                 FacesMessage.SEVERITY_ERROR);
            }
        }
    }

    public void reviseInvoicePopupAL(DialogEvent dialogEvent) {
        try {
            if (dialogEvent.getOutcome() == DialogEvent.Outcome.yes) {
                Object message = executeMethod("ReviseInvoicedDoc");
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


    public void actInvQtyVL(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        Object msg = executeMethod("validateInvLineActQty");
        if (msg != null && "Error".equals(msg)) {
            showPopupMessage("Cumulative actual quantity cannot be more than bill quantity.",
                             FacesMessage.SEVERITY_ERROR);
        } else if (msg != null && msg.toString().length() > 0) {
            showPopupMessage(msg.toString(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getActInvoiceQtyBindgs());
        addPartialTrigger(getActInvQtyPerBindings());
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

    public void setCancelInvoiceDocPopupBindgs(RichPopup cancelInvoiceDocPopupBindgs) {
        this.cancelInvoiceDocPopupBindgs = cancelInvoiceDocPopupBindgs;
    }

    public RichPopup getCancelInvoiceDocPopupBindgs() {
        return cancelInvoiceDocPopupBindgs;
    }

    public void setDeleteInvoicePopupBindgs(RichPopup deleteInvoicePopupBindgs) {
        this.deleteInvoicePopupBindgs = deleteInvoicePopupBindgs;
    }

    public RichPopup getDeleteInvoicePopupBindgs() {
        return deleteInvoicePopupBindgs;
    }

    public void setReviseInvoiceDocPopupBindgs(RichPopup reviseInvoiceDocPopupBindgs) {
        this.reviseInvoiceDocPopupBindgs = reviseInvoiceDocPopupBindgs;
    }

    public RichPopup getReviseInvoiceDocPopupBindgs() {
        return reviseInvoiceDocPopupBindgs;
    }

    public void setActInvoiceQtyBindgs(RichInputText actInvoiceQtyBindgs) {
        this.actInvoiceQtyBindgs = actInvoiceQtyBindgs;
    }

    public RichInputText getActInvoiceQtyBindgs() {
        return actInvoiceQtyBindgs;
    }

    public void setPrevCummQtyBindgs(RichOutputText prevCummQtyBindgs) {
        this.prevCummQtyBindgs = prevCummQtyBindgs;
    }

    public RichOutputText getPrevCummQtyBindgs() {
        return prevCummQtyBindgs;
    }

    public void validateInvDoc(ActionEvent actionEvent) {
        Object message = executeMethod("validateInvoicedLines");
        if (message != null) {
            if (!"Success".equals(message)) {
                addPartialTrigger(getLinesTableBindings());
                showPopupMessage(message.toString(),
                                 FacesMessage.SEVERITY_ERROR);
            } else {
                save();
                addPartialTrigger(getLinesTableBindings());
                addPartialTrigger(getPageBindings());
            }
        }
        addPartialTrigger(getPageBindings());
    }


    public void withdrawInvDoc(ActionEvent actionEvent) {
        showPopup(getWithdrawInvDocPopupBindgs());
    }

    public void setWithdrawInvDocPopupBindgs(RichPopup withdrawInvDocPopupBindgs) {
        this.withdrawInvDocPopupBindgs = withdrawInvDocPopupBindgs;
    }

    public RichPopup getWithdrawInvDocPopupBindgs() {
        return withdrawInvDocPopupBindgs;
    }

    public void withdrawSubmission(ActionEvent actionEvent) {
        try {
            save();
            Object invHdrId = getEL("#{bindings.InvoicedHeaderId.inputValue}");
            getCurrentPageFlowScope().put("docId", invHdrId);
            getCurrentPageFlowScope().put("docTypeId", "3");
            Object withdtwSubMesObj = executeMethod("withdrawFromApproval");
            if ("Success".equals(withdtwSubMesObj)) {
                save();
                setEL("#{bindings.InvoicedStatus.inputValue}", "W");
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
                showPopupMessage("Not Approved", FacesMessage.SEVERITY_INFO);
        }
        return rejectView;
    }

    public void setInvQtyCummPerBindings(RichInputText invQtyCummPerBindings) {
        this.invQtyCummPerBindings = invQtyCummPerBindings;
    }

    public RichInputText getInvQtyCummPerBindings() {
        return invQtyCummPerBindings;
    }

    public void InvCummQtyPerChanged(ValueChangeEvent valueChangeEvent) {

        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        Object msg = executeMethod("validateInvoiceLinePercentage");
        if (msg != null && msg.toString().length() > 0) {
            showPopupMessage(msg.toString(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getCummulativeQtyBindings());
        addPartialTrigger(getInvQtyCummPerBindings());
    }

    public void setActInvQtyPerBindings(RichInputText actInvQtyPerBindings) {
        this.actInvQtyPerBindings = actInvQtyPerBindings;
    }

    public RichInputText getActInvQtyPerBindings() {
        return actInvQtyPerBindings;
    }

    public void ActInvQtyPerChanged(ValueChangeEvent valueChangeEvent) {

        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        Object msg = executeMethod("validateInvLineActPercentage");
        if (msg != null && msg.toString().length() > 0) {
            showPopupMessage(msg.toString(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getActInvoiceQtyBindgs());
        addPartialTrigger(getActInvQtyPerBindings());
    }

    public void cancelAL(ActionEvent actionEvent) {
        //        cancel();
    }

    public void saveBoqLinesAL(ActionEvent actionEvent) {
        try {
            executeMethod("refreshInvoiceHeader");
            save();
            executeMethod("executeInvoicedLinesQuery");
            addPartialTrigger(getPageBindings());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void attachFileVL(ValueChangeEvent valueChangeEvent) {

        try {
            valueChangeEvent.getComponent().processDecodes(FacesContext.getCurrentInstance());
            Object fileObj = valueChangeEvent.getNewValue();
            if (fileObj != null) {
                UploadedFile file = (UploadedFile)fileObj;
                this.getCurrentPageFlowScope().put("attachedFile", file);
                Object exeMess = executeMethod("saveAttachedFileInvoice");
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
            bindingsImpl.findIteratorBinding("AttachmentsVO2Iterator");
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

    public void setSubmitConfirmationPopupBind(RichPopup submitConfirmationPopupBind) {
        this.submitConfirmationPopupBind = submitConfirmationPopupBind;
    }

    public RichPopup getSubmitConfirmationPopupBind() {
        return submitConfirmationPopupBind;
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
        //        BigDecimal value = (BigDecimal)valueChangeEvent.getNewValue();
        //        BigDecimal TransContractValue = (BigDecimal)getEL("#{bindings.TransContractValue.inputValue}");
        //        BigDecimal TransCurrentTotal  = (BigDecimal)getEL("#{bindings.TransCurrentTotal.inputValue}");
        //
        //        try{
        //            if(value!=null){
        //                if (TransContractValue != null && value.compareTo(TransContractValue) > 0) {
        //                    setEL("#{bindings.Retention.inputValue}", valueChangeEvent.getOldValue());
        //                    showPopupMessage("Retention Value can't be greater than Total Revenue Amount",
        //                                     FacesMessage.SEVERITY_ERROR);
        //                } else if (TransCurrentTotal != null && value.compareTo(TransCurrentTotal) > 0) {
        //                    setEL("#{bindings.Retention.inputValue}", valueChangeEvent.getOldValue());
        //                    showPopupMessage("Value can't be greater than Current Invoiced Amount",
        //                                     FacesMessage.SEVERITY_ERROR);
        //                }
        //            }
        //        }catch(Exception e){
        //            e.printStackTrace();
        //        }
        //
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

    public void PAPrintReport(FacesContext facesContext,
                              OutputStream outputStream) {
        byte[] report = (byte[])executeMethod("getPAPrintReport");
        try {
            outputStream.write(report);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getPADetailsReportAL(FacesContext facesContext,
                              OutputStream outputStream) {
        byte[] report = (byte[])executeMethod("getPADetailsReport");
        try {
            outputStream.write(report);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCancelInvoiceDocBind(RichPopup cancelInvoiceDocBind) {
        this.cancelInvoiceDocBind = cancelInvoiceDocBind;
    }

    public RichPopup getCancelInvoiceDocBind() {
        return cancelInvoiceDocBind;
    }

    public void cancelInvoiceDocAL(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
            String cancelRemarks =
                String.valueOf(getCurrentPageFlowScope().get("CANCEL_REMARKS"));
            Object exeMessObj = executeMethod("cancelInvoiceRow");
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

    public void calculateAmounts(ActionEvent actionEvent) {
        try {
            save();
            Object invHeaderId =
                getEL("#{bindings.InvoicedHeaderId.inputValue}");
            getCurrentPageFlowScope().put("invHeaderId", invHeaderId);
            DCBindingContainer binding = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding bindingIterator = binding.findIteratorBinding("InvoicedQtyDetailsVO1Iterator");
            ViewObject vo = bindingIterator.getViewObject();           
            vo.executeQuery();

            Object isSettedObj =
                executeMethod("makeAsCurrentInvoiceHdrRow");
            
            String res = (String)executeMethod("calculateInvoiceAmounts");
            save();            
            RichInputText input = (RichInputText)findComponent("it15") ; 
            input.setSubmittedValue(null);
            input.resetValue();
            AdfFacesContext.getCurrentInstance().addPartialTarget(input);  
            input = (RichInputText)findComponent("it17") ; 
            input.setSubmittedValue(null);
            input.resetValue();
            AdfFacesContext.getCurrentInstance().addPartialTarget(input);  
            vo.executeQuery();
            isSettedObj =
                executeMethod("makeAsCurrentInvoiceHdrRow");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setRetentionReleaseBind(RichInputText retentionReleaseBind) {
        this.retentionReleaseBind = retentionReleaseBind;
    }

    public RichInputText getRetentionReleaseBind() {
        return retentionReleaseBind;
    }

    public void saveAdjAmount(ActionEvent actionEvent) {
        // Included to save adjustment amount on 20-12-2021
        try {
            save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
