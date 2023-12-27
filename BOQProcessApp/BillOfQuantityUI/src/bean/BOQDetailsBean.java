package bean;

import bean.notification.NotificationBean;

import java.io.IOException;
import java.io.OutputStream;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class BOQDetailsBean extends CommonBean {
    private RichPopup uploadBOQPopUp;
    private RichPopup validateBOQPopUp;
    private RichTable boqLinesTable;

    public BOQDetailsBean() {
        super();
    }

    public void saveBOQ(ActionEvent actionEvent) {
        Object message = executeMethod("validateBOQLines");
        if (message != null && message.toString().length() > 0) {
            showPopupMessage(message.toString(), FacesMessage.SEVERITY_ERROR);
        } else {
            save();
        }
    }

    public void cancelBOQ(ActionEvent actionEvent) {
        cancel();
    }

    public void createBOQParantLine(ActionEvent actionEvent) {
        //        save();
        executeMethod("createBOQParantLine");
    }

    public void createBOQChildLine(ActionEvent actionEvent) {
        executeMethod("createBOQChildLine");
    }

    public void splitBOQLine(ActionEvent actionEvent) {
        Object message = executeMethod("validateBOQLines");
        if (message != null && message.toString().length() > 0) {
            showPopupMessage(message.toString(), FacesMessage.SEVERITY_ERROR);
        } else {
            save();
            executeMethod("splitBOQLine");
        }
    }


    public void deleteBOQLine(ActionEvent actionEvent) {
        executeMethod("deleteBOQLine");
        save();
    }

    public void deleteBOQChildLine(ActionEvent actionEvent) {
        executeMethod("deleteBOQChildLine");
        save();
    }

    public void SaveAndClose(ActionEvent actionEvent) {
        Object message = executeMethod("validateBOQLines");
        if (message != null && message.toString().length() > 0) {
            showPopupMessage(message.toString(), FacesMessage.SEVERITY_ERROR);
        } else {
            save();
            navigate(actionEvent, "Done");
        }
    }

    public void submitDocForApproval() {
        executeMethod("callpkgForAMEProcessSubmit");
    }


    public void formatDownload(FacesContext facesContext,
                               OutputStream outputStream) {
        byte[] bytes = (byte[])executeMethod("downloadExcelSheet");
        try {
            outputStream.write(bytes);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setUploadBOQPopUp(RichPopup uploadBOQPopUp) {
        this.uploadBOQPopUp = uploadBOQPopUp;
    }

    public RichPopup getUploadBOQPopUp() {
        return uploadBOQPopUp;
    }

    public void fileXLUploaded(ValueChangeEvent valueChangeEvent) {
        UploadedFile uploadedFile =
            (UploadedFile)valueChangeEvent.getNewValue();
        Map params = new HashMap();
        params.put("uploadedFile", uploadedFile);
        Object msg = executeMethod("uploadXLFile", params);
        if (msg != null && !("UPLOADED".equals(msg)))
            showPopupMessage(msg.toString(), FacesMessage.SEVERITY_ERROR);
        else if (msg != null && ("UPLOADED".equals(msg))) {
            save();
            showPopup(getValidateBOQPopUp());
        }
    }

    public void setValidateBOQPopUp(RichPopup validateBOQPopUp) {
        this.validateBOQPopUp = validateBOQPopUp;
    }

    public RichPopup getValidateBOQPopUp() {
        return validateBOQPopUp;
    }


    public void uploadBOQDialog(DialogEvent dialogEvent) {
        try {
            if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
                Object msg = executeMethod("validateBOQUploadDetails");
                String popUpMsg = "";
                if ("NOMSG".equals(msg) || "NOHDR".equals(msg)) {
                    popUpMsg = "Validation failed. Please try again.";
                } else if ("SYS_FAILURE".equals(msg) ||
                           "LOG_FAILURE".equals(msg)) {
                    popUpMsg = "Validation failed. System Error.";
                } else if ("FAILED".equals(msg)) {
                    popUpMsg =
                            "Validation failed. Navigating to the error logs.";
                } else if ("SUCCESS".equals(msg)) {
                    popUpMsg = "Import successful";
                    addPartialTrigger(getBoqLinesTable());
                }
                showPopupMessage(popUpMsg, FacesMessage.SEVERITY_INFO);
                if ("FAILED".equals(msg)) {
                    oracle.jbo.domain.Number uploadId =
                        new oracle.jbo.domain.Number(((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("uploadId").toString());
                    setEL("#{pageFlowScope.uploadId}", uploadId);
                    navigate(dialogEvent, "errors");
                }
            }
            getUploadBOQPopUp().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void uploadFileBOQ(ActionEvent actionEvent) {
        showPopup(uploadBOQPopUp);
    }

    public void setBoqLinesTable(RichTable boqLinesTable) {
        this.boqLinesTable = boqLinesTable;
    }

    public RichTable getBoqLinesTable() {
        return boqLinesTable;
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

    public void viewActionHistAL(ActionEvent actionEvent) {
        try {
            Object hdrId = getEL("#{bindings.HeaderId.inputValue}");
            getCurrentPageFlowScope().put("docId", hdrId);
            getCurrentPageFlowScope().put("docTypeId", "17");
            navigate(actionEvent, "ActionHistory");
        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
}
