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
import javax.faces.context.FacesContextFactory;
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
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelHeader;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.BindingContainer;

import oracle.jbo.Row;
import oracle.jbo.domain.BlobDomain;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class PaymentCertificateBean extends CommonBean {
    private RichTable lineTableBindings;

    private RichPopup deletePCPopupBindgs;
    private RichPopup reversePCDocPopupBindgs;
    private RichPopup cancelPCDocPopupBindgs;
    private RichInputText particularBindgs;
    private RichInputText cumltBindgs;
    private RichInputText prevBindgs;
    private Boolean readOnly;
    private RichPanelGroupLayout rejectView;

    private RichPopup approvePopupBindgs;
    private RichPopup rejectPopupBindgs;
    private RichPopup submitForApprPopupBindgs;
    private RichPanelGroupLayout pageBindings;
    private RichPopup attachmentFilesPopupBindgs;
    private RichInputFile attachFileBindgs;
    private RichOutputText fileCcreatedByBindgs;
    private RichInputDate fileCreationDateBindgs;
    private RichCommandLink fileNameBindgs;
    private RichPopup cancelPCDocBind;
    private RichInputText deductionAmountBind;
    private RichInputComboboxListOfValues deductionCostCodeBind;
    private RichInputComboboxListOfValues deductionTaskNumberBind;
    private RichTable otherDeductionTableBind;


    public PaymentCertificateBean() {
        super();
    }

    public String updatePCOtherDedAmnts() {
        String message = null;
        try {

            Object exeMess = executeMethod("updatePcOtherDedAmnt");

            if (exeMess != null) {
                message = exeMess.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        return message;
    }

    public void savePC(ActionEvent actionEvent) {
        try {

            save();
            Object exeMess = executeMethod("refreshHdrRow");
            if (!"Success".equals(exeMess)) {


                showPopupMessage(exeMess != null ? exeMess.toString() :
                                 "Unable to refresh the current document.",
                                 FacesMessage.SEVERITY_ERROR);
            }
            //            addPartialTrigger(getPageBindings());

        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getPageBindings());
    }

    public void saveAndClosePC(ActionEvent actionEvent) {
        try {

            save();
            Object exeMess = executeMethod("refreshHdrRow");
            if ("Success".equals(exeMess)) {
                navigate(actionEvent, "done");
            } else {
                showPopupMessage(exeMess != null ? exeMess.toString() :
                                 "Unable to refresh the current document.",
                                 FacesMessage.SEVERITY_ERROR);
            }
            //            addPartialTrigger(getPageBindings());

        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getPageBindings());
    }


    public void getLines(ActionEvent actionEvent) {
        try {
            save();
            Object exeMess = executeMethod("refreshHdrRow");
            Object message = executeMethod("getLines");
            if (!"Success".equals(message)) {
                save();
                exeMess = executeMethod("refreshHdrRow");
                showPopupMessage(message != null ? message.toString() :
                                 "Unable to get lines.",
                                 FacesMessage.SEVERITY_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getPageBindings());
    }

    public void cancelPC(ActionEvent actionEvent) {
        cancel();
    }

    public void updatePCLines(ActionEvent actionEvent) {
        save();
        executeMethod("updatePCLines");
        addPartialTrigger(getLineTableBindings());
    }

    public void deletePCLines(ActionEvent actionEvent) {
        save();
        executeMethod("deletePCLines");
        addPartialTrigger(getLineTableBindings());
    }

    public void setLineTableBindings(RichTable lineTableBindings) {
        this.lineTableBindings = lineTableBindings;
    }

    public RichTable getLineTableBindings() {
        return lineTableBindings;
    }

    public void cummulativeValChanged(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        try {
            Object parTypeObj = getEL("#{bindings.Particulars.inputValue}");
            Object docTypeObj = getEL("#{bindings.DocumentType.inputValue}");
            if (parTypeObj != null && docTypeObj != null) {
                String parType = parTypeObj.toString();
                String docType = docTypeObj.toString();
                Object cumltValobj = valueChangeEvent.getNewValue();
                Object prevValObj = getPrevBindgs().getValue();
                if (cumltValobj != null && prevValObj != null) {
                    Double cumltVal = new Double(cumltValobj.toString());
                    Double prevVal = new Double(prevValObj.toString());
                    if (cumltVal >= 0 || "OTH_DED".equals(parType)) {
                        //                        if (!"RET".equals(parType) &&             //To allow cummulative value less than previous value By Sumanth.
                        //                            !"MAT_AT_SITE".equals(parType)) {
                        //                            if (cumltVal < prevVal) {
                        //                                getCumltBindgs().setValue(valueChangeEvent.getOldValue());
                        //                                showPopupMessage("Cumulative value cannot be entered less than previous values i.e. " +
                        //                                                 prevValObj,
                        //                                                 FacesMessage.SEVERITY_ERROR);
                        //                            }
                        //                        }

                        if ("RR".equals(docType) && "RET".equals(parType)) {
                            if (cumltVal > prevVal) {
                                getCumltBindgs().setValue(valueChangeEvent.getOldValue());
                                showPopupMessage("Cumulative amount cannot be entered greater than previous amount i.e. " +
                                                 prevValObj,
                                                 FacesMessage.SEVERITY_ERROR);
                            }
                        }

                        //                        if("ADV".equals(parType) ){
                        //                            Object vowdCumltAmntObj =
                        //                                getEL("#{bindings.TransVOWDAmnt.inputValue}");
                        //                            if (vowdCumltAmntObj != null) {
                        //                                Double vowdCumltAmnt =
                        //                                    new Double(vowdCumltAmntObj.toString());
                        //                                if (vowdCumltAmnt < cumltVal) {
                        //                                    getCumltBindgs().setValue(valueChangeEvent.getOldValue());
                        //                                    showPopupMessage("Advance Cumulative value cannot be entered greater than the Vlaue of Work Done Amount i.e. " +
                        //                                                     vowdCumltAmntObj,
                        //                                                     FacesMessage.SEVERITY_ERROR);
                        //                                }
                        //                            }
                        //                        }


                        if ("ADV_RECV".equals(parType)) {
                            Object advPaidObj =
                                getEL("#{bindings.TransAdvancePaidAmnt.inputValue}");
                            if (advPaidObj != null) {
                                Double advPaid =
                                    new Double(advPaidObj.toString());
                                if (advPaid < cumltVal) {
                                    getCumltBindgs().setValue(valueChangeEvent.getOldValue());
                                    showPopupMessage("Advance recovery cumulative amount cannot be entered greater than the advance amount i.e. " +
                                                     advPaidObj,
                                                     FacesMessage.SEVERITY_ERROR);
                                }
                            }
                        }
                        if ("RET_REL".equals(parType)) {
                            Object retObj =
                                getEL("#{bindings.TransRetentionAmt.inputValue}");
                            if (retObj != null) {
                                Double advPaid =
                                    new Double(retObj.toString());
                                if (advPaid < cumltVal) {
                                    getCumltBindgs().setValue(valueChangeEvent.getOldValue());
                                    showPopupMessage("Retention Release amount cannot be greater than the Retention amount i.e. " +
                                                     retObj,
                                                     FacesMessage.SEVERITY_ERROR);
                                }
                            }
                        }
                        if ("RET".equals(parType)) {
                            Object vowdCumltAmntObj =
                                getEL("#{bindings.TransVOWDAmnt.inputValue}");
                            if (vowdCumltAmntObj != null) {
                                Double vowdCumltAmnt =
                                    new Double(vowdCumltAmntObj.toString());
                                if (vowdCumltAmnt < cumltVal) {
                                    getCumltBindgs().setValue(valueChangeEvent.getOldValue());
                                    showPopupMessage("Cumulative amount cannot be entered greater than the value of work done amount i.e. " +
                                                     vowdCumltAmntObj,
                                                     FacesMessage.SEVERITY_ERROR);
                                }
                            }
                        }
                    } else {
                        getCumltBindgs().setValue(valueChangeEvent.getOldValue());
                        showPopupMessage("Cumulative value cannot be negative or null.",
                                         //entered less than 0.",
                                FacesMessage.SEVERITY_ERROR);
                    }

                } else {
                    getCumltBindgs().setValue(valueChangeEvent.getOldValue());
                    showPopupMessage("Invalid cumulative or previous values retrieved.",
                                     FacesMessage.SEVERITY_ERROR);
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

        addPartialTrigger(getCumltBindgs());
    }

    public void SaveDed(ActionEvent actionEvent) {
        try {
            save();
            Object exeMess = executeMethod("refreshHdrRow");
                if ("Success".equals(exeMess)) {
                    Object message = executeMethod("updatePcOtherDedAmnt");
                    if (!"Success".equals(message)) {
                        showPopupMessage(message != null ? message.toString() :
                                         "Unable to update the deduction values.",
                                         FacesMessage.SEVERITY_ERROR);
                    }
                } else {
                    showPopupMessage(exeMess != null ? exeMess.toString() :
                                     "Unable to refresh the current document.",
                                     FacesMessage.SEVERITY_ERROR);
                }
        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }


    public void saveAndCloseAL(ActionEvent actionEvent) {
        try {
            save();
            Object exeMess = executeMethod("refreshHdrRow");
            if ("Success".equals(exeMess)) {

                Object message = executeMethod("updatePcOtherDedAmnt");
                if ("Success".equals(message)) {
                    navigate(actionEvent, "done");
                } else {
                    showPopupMessage(message != null ? message.toString() :
                                     "Unable to update the deduction values.",
                                     FacesMessage.SEVERITY_ERROR);
                }
            } else {
                showPopupMessage(exeMess != null ? exeMess.toString() :
                                 "Unable to refresh the current document.",
                                 FacesMessage.SEVERITY_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void addPCDeductionsRow(ActionEvent actionEvent) {
        executeMethod("addPCDeductionsRow");
    }

    public void CancelDed(ActionEvent actionEvent) {
        cancel();
    }

    public void submitPC(ActionEvent actionEvent) {

        try {

            save();
            Object totCummPer =
                getEL("#{bindings.TransCummulativePer.inputValue}");
            Object BOQStatus = getEL("#{bindings.TransBOQStatus.inputValue}");
            if (BOQStatus != null && "RV".equals(BOQStatus)) {
                getCurrentPageFlowScope().put("BOQexists", "Y");
            } else {
                getCurrentPageFlowScope().put("BOQexists", "N");
            }
            if (totCummPer != null) {
                Double cummPer = Double.parseDouble(totCummPer.toString());
                if (cummPer != null && cummPer <= 100) {
                    Object exeMess = executeMethod("refreshHdrRow");
                    if ("Success".equals(exeMess)) {
                        showPopup(getSubmitForApprPopupBindgs());
                    } else {
                        showPopupMessage(exeMess != null ? exeMess.toString() :
                                         "Unable to refresh the current document.",
                                         FacesMessage.SEVERITY_ERROR);
                    }
                } else {
                    showPopupMessage("Total cumulative amount cannot be more than contract amount.",
                                     FacesMessage.SEVERITY_ERROR);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getPageBindings());
    }

    public void postAL(ActionEvent actionEvent) {
        try {
            save();
            Object interfaceType = getEL("#{bindings.InterfaceType.inputValue}");
            if(interfaceType == null){
                showPopupMessage("Interface Type Should be selected.", FacesMessage.SEVERITY_ERROR);
            }else{
                Object exeMes = executeMethod("refreshHdrRow");
                Object exeMess = executeMethod("postPCDocument");
                if ("Success".equals(exeMess)) {
                    save();
                    exeMes = executeMethod("refreshHdrRow");
                    showPopupMessage("Document interfaced successfully.",
                                     FacesMessage.SEVERITY_INFO);
                } else
                    showPopupMessage(exeMess != null ? exeMess.toString() :
                                     "Interface document failed.",
                                     FacesMessage.SEVERITY_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getPageBindings());
    }

    public void submitDocForApproval() {
        executeMethod("callpkgForAMEProcessSubmit");
    }


    public void actHistAL(ActionEvent actionEvent) {
        Object qacHdrId = getEL("#{bindings.PcHeaderId.inputValue}");
        getCurrentPageFlowScope().put("docId", qacHdrId);
        getCurrentPageFlowScope().put("docTypeId", "14");
        navigate(actionEvent, "ActionHistory");
    }

    public void approve(ActionEvent actionEvent) {
        setEL("#{bindings.PcStatus.inputValue}", "Approved");
        save();
        addPartialTrigger(getPageBindings());
    }

    public void pcARInterface(ActionEvent actionEvent) {

        Object message = executeMethod("validateAccounts");
        if (message != null) {
            if (!("Success".equals(message))) {
                showPopupMessage(message.toString(),
                                 FacesMessage.SEVERITY_ERROR);
            } else {
                message = executeMethod("processInterfaceLine");
                if (message != null)
                    if ("SUCCESS".equals(message)) {
                        message =
                                executeMethod("processInterfaceDistribution");
                        if ("SUCCESS".equals(message)) {
                            showPopupMessage(message.toString(),
                                             FacesMessage.SEVERITY_INFO);
                        } else {
                            showPopupMessage(message.toString(),
                                             FacesMessage.SEVERITY_ERROR);
                        }
                    } else {
                        showPopupMessage(message.toString(),
                                         FacesMessage.SEVERITY_ERROR);
                    }
            }

        }

    }

    public void setDeletePCPopupBindgs(RichPopup deletePCPopupBindgs) {
        this.deletePCPopupBindgs = deletePCPopupBindgs;
    }

    public RichPopup getDeletePCPopupBindgs() {
        return deletePCPopupBindgs;
    }

    public void deletePCDoPopupDL(DialogEvent dialogEvent) {
        try {
            if (dialogEvent.getOutcome() == DialogEvent.Outcome.yes) {
                Object message = executeMethod("deletePCDocRow");
                if ("Success".equals(message)) {

                    showPopupMessage("Delete successful.",
                                     FacesMessage.SEVERITY_INFO);
                    navigate(dialogEvent, "done");
                } else {
                    showPopupMessage(message != null ? message.toString() :
                                     "Deleting the selected document failed.",
                                     FacesMessage.SEVERITY_ERROR);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        addPartialTrigger(getPageBindings());
    }

    public void reverseAction(ActionEvent actionEvent) {
       try {
          BigDecimal receiptAmt = (BigDecimal)getEL("#{bindings.ReceiptAmount.inputValue}");
           if(receiptAmt !=  null && receiptAmt.compareTo(new BigDecimal(0)) > 0){               
               showPopupMessage("Receipt is already created against the invoice. Please contact Receivables Team for unapplication of Receipt", FacesMessage.SEVERITY_ERROR);
           }else{
               executeMethod("Execute");
               executeMethod("CreateInsert1");
               showPopup(getReversePCDocPopupBindgs());
           }            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void reversePCDoPopupAL(DialogEvent dialogEvent) {
        try {
            if (dialogEvent.getOutcome() == DialogEvent.Outcome.yes) {
                String message = (String)executeMethod("reversePCDocRow");
                if ("Success".equals(message)) {
                    String exeMes = (String)executeMethod("refreshHdrRow");
                    showPopupMessage("Document reversed successfully.",
                                     FacesMessage.SEVERITY_INFO);
                } else {
                    showPopupMessage(message,FacesMessage.SEVERITY_ERROR);
                }

            }else{
                executeMethod("Execute");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        addPartialTrigger(getPageBindings());
    }

    public void cancelPCDoPopupAL(DialogEvent dialogEvent) {
        try {
            if (dialogEvent.getOutcome() == DialogEvent.Outcome.yes) {
                showPopup(getCancelPCDocBind());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setReversePCDocPopupBindgs(RichPopup reversePCDocPopupBindgs) {
        this.reversePCDocPopupBindgs = reversePCDocPopupBindgs;
    }

    public RichPopup getReversePCDocPopupBindgs() {
        return reversePCDocPopupBindgs;
    }

    public void setCancelPCDocPopupBindgs(RichPopup cancelPCDocPopupBindgs) {
        this.cancelPCDocPopupBindgs = cancelPCDocPopupBindgs;
    }

    public RichPopup getCancelPCDocPopupBindgs() {
        return cancelPCDocPopupBindgs;
    }

    public void setParticularBindgs(RichInputText particularBindgs) {
        this.particularBindgs = particularBindgs;
    }

    public RichInputText getParticularBindgs() {
        return particularBindgs;
    }

    public void setCumltBindgs(RichInputText cumltBindgs) {
        this.cumltBindgs = cumltBindgs;
    }

    public RichInputText getCumltBindgs() {
        return cumltBindgs;
    }

    public void setPrevBindgs(RichInputText prevBindgs) {
        this.prevBindgs = prevBindgs;
    }

    public RichInputText getPrevBindgs() {
        return prevBindgs;
    }

    public void prevVL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
    }

    public void updatePcDedAmntAL(ActionEvent actionEvent) {
        navigate(actionEvent, "done");
    }

    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

    public Boolean getReadOnly() {
        readOnly = true;
        Object docTypeObj = getEL("#{bindings.DocumentType.inputValue}");
        Object partTypeObj = getEL("#{bindings.Particulars.inputValue}");
        if (docTypeObj != null && partTypeObj != null) {
            String partType = partTypeObj.toString();
            String docType = docTypeObj.toString();

            if ("VOWD".equals(partType)) {
                readOnly = true;
            }

            else if ("MAT_AT_SITE".equals(partType)) {
                if ("PB".equals(docType) || "FPB".equals(docType)) {
                    readOnly = false;
                }
            }

            else if ("ADV".equals(partType)) {
                if ("ADV".equals(docType)) {
                    readOnly = false;
                }
            } else if ("ADV_RECV".equals(partType)) {
                if ("PB".equals(docType)) {
                    readOnly = false;
                }
            } else if ("RET".equals(partType)) {

                if ("RR".equals(docType) || "FRR".equals(docType)) {
                    readOnly = false;
                }
            }
        }
        return readOnly;
    }


    public void viewActionHistAL(ActionEvent actionEvent) {
        try {
            Object qacHdrId = getEL("#{bindings.PcHeaderId.inputValue}");
            getCurrentPageFlowScope().put("docId", qacHdrId);
            getCurrentPageFlowScope().put("docTypeId", "5");
            navigate(actionEvent, "ActionHistory");
        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
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
            Object pcValObj = getEL("#{bindings.TransTotalPCAmnt.inputValue}");
            if (pcValObj != null) {
                Double pcVal = new Double(pcValObj.toString());
                if (pcVal > 0) {
                    Object exeMess = executeMethod("callpkgForAMEPCDocSubmit");
                    if ("Success".equals(exeMess)) {
                        save();
                        showPopupMessage("The document was submitted for approval.",
                                         FacesMessage.SEVERITY_INFO);
                    } else {
                        showPopupMessage(exeMess != null ? exeMess.toString() :
                                         "Unable to submit the document for approval.",
                                         FacesMessage.SEVERITY_ERROR);
                    }
                } else {
                    showPopupMessage("Unable to submit the document for approval. PC document amount cannot be less than 0.",
                                     FacesMessage.SEVERITY_ERROR);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        addPartialTrigger(getPageBindings());
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


    public void setPageBindings(RichPanelGroupLayout pageBindings) {
        this.pageBindings = pageBindings;
    }

    public RichPanelGroupLayout getPageBindings() {
        return pageBindings;
    }


    public void setAttachmentFilesPopupBindgs(RichPopup attachmentFilesPopupBindgs) {
        this.attachmentFilesPopupBindgs = attachmentFilesPopupBindgs;
    }

    public RichPopup getAttachmentFilesPopupBindgs() {
        return attachmentFilesPopupBindgs;
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
        addPartialTrigger(getFileCreationDateBindgs());
    }

    public void setAttachFileBindgs(RichInputFile attachFileBindgs) {
        this.attachFileBindgs = attachFileBindgs;
    }

    public RichInputFile getAttachFileBindgs() {
        return attachFileBindgs;
    }

    public void cancelAL(ActionEvent actionEvent) {
        //        cancel();
    }

    public void saveAL(ActionEvent actionEvent) {
        save();
    }

    public void setFileCcreatedByBindgs(RichOutputText fileCcreatedByBindgs) {
        this.fileCcreatedByBindgs = fileCcreatedByBindgs;
    }

    public RichOutputText getFileCcreatedByBindgs() {
        return fileCcreatedByBindgs;
    }

    public void setFileCreationDateBindgs(RichInputDate fileCreationDateBindgs) {
        this.fileCreationDateBindgs = fileCreationDateBindgs;
    }

    public RichInputDate getFileCreationDateBindgs() {
        return fileCreationDateBindgs;
    }


    public void setFileNameBindgs(RichCommandLink fileNameBindgs) {
        this.fileNameBindgs = fileNameBindgs;
    }

    public RichCommandLink getFileNameBindgs() {
        return fileNameBindgs;
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

    public void PCPrintReport(FacesContext facesContext,
                              OutputStream outputStream) {
        byte[] report = (byte[])executeMethod("getPCPrintReport");
        try {
            outputStream.write(report);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCancelPCDocBind(RichPopup cancelPCDocBind) {
        this.cancelPCDocBind = cancelPCDocBind;
    }

    public RichPopup getCancelPCDocBind() {
        return cancelPCDocBind;
    }

    public void cancelPCDocAL(DialogEvent dialogEvent) {
        try {
            if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
                Object message = executeMethod("cancelPCDocRow");
                if ("Success".equals(message)) {
                    showPopupMessage("Cancel action successful.",
                                     FacesMessage.SEVERITY_INFO);
                } else {
                    showPopupMessage(message != null ? message.toString() :
                                     "Cancelling the selected document is failed.",
                                     FacesMessage.SEVERITY_ERROR);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        addPartialTrigger(getPageBindings());
    }

    public void deductionAmountVL(ValueChangeEvent valueChangeEvent) {
        processUpdates(valueChangeEvent);
        if (valueChangeEvent.getNewValue() != null) {
            Double amount =
                ((Number)valueChangeEvent.getNewValue()).doubleValue();
            if (amount < 0) {
                getDeductionCostCodeBind().setValue(null);
                getDeductionTaskNumberBind().setValue(null);
            }
        }
        addPartialTrigger(getDeductionCostCodeBind());
    }

    public void setDeductionAmountBind(RichInputText deductionAmountBind) {
        this.deductionAmountBind = deductionAmountBind;
    }

    public RichInputText getDeductionAmountBind() {
        return deductionAmountBind;
    }

    public void setDeductionCostCodeBind(RichInputComboboxListOfValues deductionCostCodeBind) {
        this.deductionCostCodeBind = deductionCostCodeBind;
    }

    public RichInputComboboxListOfValues getDeductionCostCodeBind() {
        return deductionCostCodeBind;
    }

    public void setDeductionTaskNumberBind(RichInputComboboxListOfValues deductionTaskNumberBind) {
        this.deductionTaskNumberBind = deductionTaskNumberBind;
    }

    public RichInputComboboxListOfValues getDeductionTaskNumberBind() {
        return deductionTaskNumberBind;
    }

    public void navigateToDeductions(ActionEvent actionEvent) {
        try {
            navigate(actionEvent, "deductions");
            addPartialTrigger(getOtherDeductionTableBind());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOtherDeductionTableBind(RichTable otherDeductionTableBind) {
        this.otherDeductionTableBind = otherDeductionTableBind;
    }

    public RichTable getOtherDeductionTableBind() {
        return otherDeductionTableBind;
    }
}
