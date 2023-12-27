package bean.notification;

import bean.CommonBean;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputComboboxListOfValues;
import oracle.adf.view.rich.component.rich.layout.RichPanelHeader;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.jbo.domain.Number;

public class NotificationBean extends CommonBean {
    private RichPopup approvePopUp;
    private RichPopup rejectPopUp;
    private RichCommandButton approveButton;
    private RichCommandButton rejectButton;
    private RichPanelHeader panelHdrBind;


    public NotificationBean() {
        super();
    }

    public void setApprovePopUp(RichPopup approvePopUp) {
        this.approvePopUp = approvePopUp;
    }

    public RichPopup getApprovePopUp() {
        return approvePopUp;
    }

    public void approveDialog(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.yes) {
            String message = submitNotification("Approve");
            if (message != null) {
                if ("Success".equals(message)) {
                    showPopupMessage("Document  successfully approved.",
                                     FacesMessage.SEVERITY_INFO);
                } else {
                    showPopupMessage(message, FacesMessage.SEVERITY_ERROR);
                }
            } else {
                showPopupMessage("Document approval failed.",
                                 FacesMessage.SEVERITY_ERROR);
            }
        }

        getApprovePopUp().hide();
        addPartialTrigger(getPanelHdrBind());
    }

    public void approveAction(ActionEvent actionEvent) {
        String message = submitNotification("Approve");
        if (message != null) {
            if ("Success".equals(message)) {
                showPopupMessage("Document  successfully approved.",
                                 FacesMessage.SEVERITY_INFO);
            } else {
                showPopupMessage(message, FacesMessage.SEVERITY_ERROR);
            }
        } else {
            showPopupMessage("Document approval failed.",
                             FacesMessage.SEVERITY_ERROR);
        }
        //        getApprovePopUp().hide();
        addPartialTrigger(getPanelHdrBind());
    }

    public void setRejectPopUp(RichPopup rejectPopUp) {
        this.rejectPopUp = rejectPopUp;
    }

    public RichPopup getRejectPopUp() {
        return rejectPopUp;
    }

    public void rejectDialog(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.yes) {
            String message = submitNotification("Reject");
            if (message != null) {
                if ("Success".equals(message)) {
                    showPopupMessage("Document  successfully rejected.",
                                     FacesMessage.SEVERITY_INFO);
                } else {
                    showPopupMessage(message, FacesMessage.SEVERITY_ERROR);
                }
            } else {
                showPopupMessage("Document rejection failed.",
                                 FacesMessage.SEVERITY_ERROR);
            }
        }
        getRejectPopUp().hide();
        addPartialTrigger(getPanelHdrBind());
    }

    public void rejectDialogEvent() {
        String message = submitNotification("Reject");
        if (message != null) {
            if ("Success".equals(message)) {
                showPopupMessage("Document  successfully rejected.",
                                 FacesMessage.SEVERITY_INFO);
            } else {
                showPopupMessage(message, FacesMessage.SEVERITY_ERROR);
            }
        } else {
            showPopupMessage("Document rejection failed.",
                             FacesMessage.SEVERITY_ERROR);
        }
    }

    public void setApproveButton(RichCommandButton approveButton) {
        this.approveButton = approveButton;
    }

    public Boolean isButtonEnabled() {

        Map<String, Object> pageFlowScope =
            ADFContext.getCurrent().getPageFlowScope();
        if (pageFlowScope != null) {
            Object docId = pageFlowScope.get("docId");
            Object docTypeId = pageFlowScope.get("docTypeId");
            if (docId != null && docTypeId != null) {
            }
        }
        return false;
    }

    public boolean isDisabled() {
        boolean buttonDisable = true;
        isButtonEnabled();
        Object exeSatusObj = executeMethod("isDocEnabledforCurrentUser");
        if (exeSatusObj != null && exeSatusObj instanceof Boolean) {
            buttonDisable = (Boolean)exeSatusObj;
        }
        return !buttonDisable;
    }

    public RichCommandButton getApproveButton() {
        return approveButton;
    }

    public void setRejectButton(RichCommandButton rejectButton) {
        this.rejectButton = rejectButton;
    }

    public RichCommandButton getRejectButton() {
        return rejectButton;
    }

    public void setPanelHdrBind(RichPanelHeader panelHdrBind) {
        this.panelHdrBind = panelHdrBind;
    }

    public RichPanelHeader getPanelHdrBind() {
        return panelHdrBind;
    }

    public String submitNotification(String actionCode) {

        Map pageFlowScope = getCurrentPageFlowScope();
        Map paramsMap = new HashMap();
        Map paramsMap2 = new HashMap();
        Map parameters = new HashMap();
        String message = null;
        try {
            parameters.put("docTypeId", pageFlowScope.get("docTypeId"));
            parameters.put("docId", pageFlowScope.get("docId"));
            parameters.put("ActionCode", actionCode);
            parameters.put("remarks", pageFlowScope.get("APPROVAL_REMARKS"));
            parameters.put("currEmpId", pageFlowScope.get("currEmpId"));
            parameters.put("docNum", pageFlowScope.get("docNum"));
            parameters.put("workListId", pageFlowScope.get("workListId"));
            paramsMap.put("paramsMap", parameters);

            Long empId =
                pageFlowScope.get("currEmpId") != null ? new Long(pageFlowScope.get("currEmpId").toString()) :
                null;
            Long docIdNum =
                pageFlowScope.get("docId") != null ? new Long(pageFlowScope.get("docId").toString()) :
                null;
            String userName =
                pageFlowScope.get("apprvName") != null ? pageFlowScope.get("apprvName").toString() :
                null;
            Long docTypeId =
                pageFlowScope.get("docTypeId") != null ? new Long(pageFlowScope.get("docTypeId").toString()) :
                null;

            Object isFinalApprObj = executeMethod("isEmployeeAFinalApprover");
            //            Object isFinalApprObj = true;

            Boolean isFinalApprover =
                (isFinalApprObj != null && isFinalApprObj instanceof Boolean) ?
                (Boolean)isFinalApprObj : null;
            if (isFinalApprover != null) {
                String approverAction =
                    "Reject".equals(actionCode) ? "Rejected" :
                    ("Approve".equals(actionCode) && isFinalApprover) ?
                    "Approved" : "In Process";

                if ("Approved".equals(approverAction)) {
                    message =
                            performUpdatesAndTxnsOnDocument(docIdNum, docTypeId,
                                                            userName);
                    if ("Success".equals(message)) {
                        String docSubmitMess =
                            (String)executeMethod("callpkgForAMEProcess",
                                                  paramsMap);
                        message =
                                docSubmitMess != null ? docSubmitMess : "Error while deleting or inserting the document in worklist. ";
                    }
                }
                if ("Rejected".equals(approverAction) ||
                    "In Process".equals(approverAction)) {
                    String docSubmitMess =
                        (String)executeMethod("callpkgForAMEProcess",
                                              paramsMap);
                    message =
                            docSubmitMess != null ? docSubmitMess : "Error while deleting or inserting the document in worklist. ";
                }
                if (("Finally Approved".equals(message) ||
                     "Submitted".equals(message) ||
                     "Rejected".equals(message)) &&
                    ("Approved".equals(approverAction) ||
                     "Rejected".equals(approverAction) ||
                     "In Process".equals(approverAction))) {
                    message =
                            updateDocumentStatus(docIdNum, docTypeId, userName,
                                                 approverAction);
                    if ("Success".equals(message)) {
                        //                        save();
                        getApproveButton().setDisabled(true);
                        getRejectButton().setDisabled(true);
                        addPartialTrigger(getApproveButton());
                        addPartialTrigger(getRejectButton());
                        addPartialTrigger(getPanelHdrBind());
                    }
                }
            } else {
                message = "Unable to find approval level of current user.";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    private String updateDocumentStatus(Long docId, Long docTypeId,
                                        String userName, String status) {
        String message = null;
        try {
            Object messageObj = null;
            if (status != null) {

                if (docId != null && userName != null) {
                    Map pageFlowScope = getCurrentPageFlowScope();
                    pageFlowScope.put("docId", docId);
                    pageFlowScope.put("userName", userName);
                    pageFlowScope.put("updateWith", status);


                    if (1 == docTypeId.intValue()) {
                        //                        if ("Approved".equals(status)) {
                        //
                        //
                        //                            Object hdrId =
                        //                                getEL("#{bindings.ValidationHeaderId.inputValue}");
                        //                            if (hdrId != null) {
                        //                                getCurrentPageFlowScope().put("docType",
                        //                                                              "BOQ");
                        //                                getCurrentPageFlowScope().put("docHdrId",
                        //                                                              hdrId);
                        //                                Object exeMess =
                        //                                    executeMethod("updateDocStatusAndCurrFlag");
                        //                                message =
                        //                                        exeMess != null ? exeMess.toString() :
                        //                                        "Unable to update Document and status";
                        //
                        //                                if ("Success".equals(messageObj)) {
                        setEL("#{bindings.Status.inputValue}",
                              getCodeCFromStatus(status));
                        //                                }
                        //                            } else {
                        //                                showPopupMessage("Header Id not found.",
                        //                                                 FacesMessage.SEVERITY_ERROR);
                        //                            }
                        //                        } else
                        if ("Rejected".equals(status)) {
                            //                            setEL("#{bindings.Status.inputValue}",
                            //                                  getCodeCFromStatus(status));
                            save();
                        }
                        message = "Success";
                    }

                    if (2 == docTypeId.intValue()) {
                        //                        if (!"Rejected".equals(status)) {
                        //                            pageFlowScope.put("docType", "WD");
                        //                            messageObj =
                        //                                    executeMethod("updateDocStatusAndCurrFlag");
                        //                            if ("Success".equals(messageObj)) {
                        //                            setEL("#{bindings.WdStatus.inputValue}",
                        //                                  getCodeCFromStatus(status));
                        //                                message =
                        //                                        messageObj != null ? messageObj.toString() :
                        //                                        "Unable to uodate Document and status";
                        //                            }
                        //                        } else {
                        setEL("#{bindings.WdStatus.inputValue}",
                              getCodeCFromStatus(status));
                        if ("Rejected".equals(status)) {
                            save();
                        }
                        message = "Success";
                        //                        }
                    } else if (3 == docTypeId.intValue()) {
                        //                        if (!"Rejected".equals(status)) {
                        //                            pageFlowScope.put("docType", "INV");
                        //                            messageObj =
                        //                                    executeMethod("updateDocStatusAndCurrFlag");
                        //                            if ("Success".equals(messageObj)) {
                        //                                setEL("#{bindings.InvoicedStatus.inputValue}",
                        //                                      getCodeCFromStatus(status));
                        //                                message =
                        //                                        messageObj != null ? messageObj.toString() :
                        //                                        "Unable to uodate Document and status";
                        //                            }
                        //                        } else {
                        setEL("#{bindings.InvoicedStatus.inputValue}",
                              getCodeCFromStatus(status));
                        if ("Rejected".equals(status)) {
                            save();
                        }
                        message = "Success";
                        //                        }
                    }


                    else if (4 == docTypeId.intValue()) {
                        //                        if ("Approved".equals(status)) {
                        //                            Object hdrId =
                        //                                getEL("#{bindings.CertifiedHeaderId.inputValue}");
                        //                            if (hdrId != null) {
                        //                                getCurrentPageFlowScope().put("docType",
                        //                                                              "CERT");
                        //                                getCurrentPageFlowScope().put("docHdrId",
                        //                                                              hdrId);
                        //                                Object exeMess =
                        //                                    executeMethod("updateDocStatusAndCurrFlag");
                        //                                message =
                        //                                        exeMess != null ? exeMess.toString() :
                        //                                        "Unable to update Document and status";
                        //
                        //                                if ("Success".equals(messageObj)) {
                        //                                setEL("#{bindings.CertifiedStatus.inputValue}",
                        //                                      getCodeCFromStatus(status));
                        //                                }
                        //
                        //                            } else {
                        //                                showPopupMessage("Header Id not found.",
                        //                                                 FacesMessage.SEVERITY_ERROR);
                        //                            }
                        //                        } else if ("Rejected".equals(status)) {
                        setEL("#{bindings.CertifiedStatus.inputValue}",
                              getCodeCFromStatus(status));
                        if ("Rejected".equals(status)) {
                            save();
                        }
                        message = "Success";
                        //                        }
                    }

                    else if (5 == docTypeId.intValue()) {

                        //                        if ("Approved".equals(status)) {
                        //                            Object hdrId =
                        //                                getEL("#{bindings.PcHeaderId.inputValue}");
                        //                            if (hdrId != null) {
                        //                                getCurrentPageFlowScope().put("docType", "PC");
                        //                                getCurrentPageFlowScope().put("docHdrId",
                        //                                                              hdrId);
                        //                                Object exeMess =
                        //                                    executeMethod("updateDocStatusAndCurrFlag");
                        //                                message =
                        //                                        exeMess != null ? exeMess.toString() :
                        //                                        "Unable to update Document and status";
                        //
                        //                                if ("Success".equals(messageObj)) {
                        setEL("#{bindings.PcStatus.inputValue}",
                              getCodeCFromStatus(status));
                        //                                }
                        //                            } else {
                        //                                showPopupMessage("Header Id not found.",
                        //                                                 FacesMessage.SEVERITY_ERROR);
                        //                            }
                        //                        } else
                        if ("Rejected".equals(status)) {
                            //                            setEL("#{bindings.PcStatus.inputValue}",
                            //                                  getCodeCFromStatus(status));
                            save();
                        }
                        message = "Success";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;

    }

    public String getCodeCFromStatus(String code) {
        String status = null;
        if (code != null) {
            if ("Draft".equals(code)) {
                status = "D";
            } else if ("In process".equals(code)) {
                status = "I";
            } else if ("Approved".equals(code)) {
                status = "A";
            } else if ("Rejected".equals(code)) {
                status = "R";
            } else if ("Submitted".equals(code)) {
                status = "S";
            }
        }

        return status;
    }

    private String performUpdatesAndTxnsOnDocument(Long docId, Long docTypeId,
                                                   String userName) {
        String message = null;
        Object messageObj = null;
        if (docId != null && userName != null) {
            //            Map pageFlowScope = getCurrentPageFlowScope();
            //            pageFlowScope.put("priceListDocHdrId", docId);
            //            pageFlowScope.put("userName", userName);

            if (1 == docTypeId.intValue()) {
                messageObj = "Success";
            } else if (2 == docTypeId.intValue()) {
                messageObj = "Success";
            } else if (3 == docTypeId.intValue()) {
                messageObj = "Success";
            } else if (4 == docTypeId.intValue()) {

                messageObj = "Success";
            } else if (5 == docTypeId.intValue()) {

                messageObj = "Success";
            }

            message =
                    messageObj != null ? messageObj.toString() : "Performing Transactions and Interfaces are failed.";
        }
        return message;
    }

    public void approveDialogEvent() {
        String message = submitNotification("Approve");
        if (message != null) {
            if ("Success".equals(message)) {
                showPopupMessage("Document  successfully approved.",
                                 FacesMessage.SEVERITY_INFO);
            } else {
                showPopupMessage(message, FacesMessage.SEVERITY_ERROR);
            }
        } else {
            showPopupMessage("Document approval failed.",
                             FacesMessage.SEVERITY_ERROR);
        }
    }

}

