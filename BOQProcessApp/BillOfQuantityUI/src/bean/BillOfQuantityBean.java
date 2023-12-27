package bean;

import java.io.IOException;
import java.io.OutputStream;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.adf.view.rich.event.PopupCanceledEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class BillOfQuantityBean extends CommonBean {
    private RichInputText inputTextBindgs;
    private RichInputFile browseFileBindgs;
    private RichPanelFormLayout uploadFormBindgs;
    private List<UploadedFile> uploadedFiles;
    private RichPopup createPopup;
    private RichTable searchTable;

    public BillOfQuantityBean() {
        super();
    }

    public void excelDownload(FacesContext facesContext,
                              OutputStream outputStream) {
        BindingContainer bindingContainer =
            BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding operationBinding =
            bindingContainer.getOperationBinding("downloadExcelSheet");
        Object data = operationBinding.execute();
        byte[] bytes = (byte[])data;
        try {
            outputStream.write(bytes);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void popUpDialogListener(DialogEvent dialogEvent) {
        try {
            Map pageFlowScope = getCurrentPageFlowScope();
            pageFlowScope.remove("uploadedFile");
            if (dialogEvent.getOutcome() == DialogEvent.Outcome.yes) {
                List<UploadedFile> files = getUploadedFiles();
                if (files != null && files.size() == 1) {
                    UploadedFile uploadedFile = files.get(0);
                    if (uploadedFile != null &&
                        uploadedFile.getFilename() != null) {
                        getInputTextBindgs().setValue(uploadedFile.getFilename());
                        addPartialTrigger(getInputTextBindgs());
                        pageFlowScope.put("uploadedFile", uploadedFile);
                    }
                }
            } else {
                this.getUploadedFiles().clear();
                pageFlowScope.remove("uploadedFile");
                getInputTextBindgs().setValue(null);
                addPartialTrigger(getInputTextBindgs());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fileXLUploaded(ValueChangeEvent valueChangeEvent) {
        UploadedFile uploadedFile =
            (UploadedFile)valueChangeEvent.getNewValue();
        try {
            Map pageFlowScope = getCurrentPageFlowScope();
            pageFlowScope.put("uploadedFile", uploadedFile);
            getInputTextBindgs().setValue(uploadedFile.getFilename());
            addPartialTrigger(getInputTextBindgs());
            save();
            String result = (String)executeMethod("uploadXLFile");
            if (!("".equals(result))) {
                showPopupMessage(result, FacesMessage.SEVERITY_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setInputTextBindgs(RichInputText inputTextBindgs) {
        this.inputTextBindgs = inputTextBindgs;
    }

    public RichInputText getInputTextBindgs() {
        return inputTextBindgs;
    }

    public void setBrowseFileBindgs(RichInputFile browseFileBindgs) {
        this.browseFileBindgs = browseFileBindgs;
    }

    public RichInputFile getBrowseFileBindgs() {
        return browseFileBindgs;
    }

    public void setUploadFormBindgs(RichPanelFormLayout uploadFormBindgs) {
        this.uploadFormBindgs = uploadFormBindgs;
    }

    public RichPanelFormLayout getUploadFormBindgs() {
        return uploadFormBindgs;
    }

    public void setUploadedFiles(List<UploadedFile> uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }

    public List<UploadedFile> getUploadedFiles() {
        return uploadedFiles;
    }

    public void createHeaderRow(ActionEvent ae) {
        executeMethod("createHeaderRow");
        showPopup(getCreatePopup());
    }

    public void createDocument(ActionEvent ae) {

        Object message = executeMethod("validateProject");
        if ("Error".equals(message)) {
            showPopupMessage("There already exists a working document. Search for the document instead.",
                             FacesMessage.SEVERITY_ERROR);
        } else {
            save();
            executeMethod("createDocument");
            save();
            navigate(ae, "Create");
        }
    }


    public void cancelPopup(PopupCanceledEvent popupCanceledEvent) {
        executeMethod("cancelDocument");
        save();
        if (getCreatePopup() != null)
            oracle.adf.view.rich.util.ResetUtils.reset(getCreatePopup());
        getCreatePopup().hide();
        addPartialTrigger(getSearchTable());
    }

    public void editDocument(ActionEvent ae) {
        executeMethod("editDocument");
        navigate(ae, "view");
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

    public void cancelPopup(ActionEvent actionEvent) {

        executeMethod("cancelDocument");
        save();
        if (getCreatePopup() != null)
            oracle.adf.view.rich.util.ResetUtils.reset(getCreatePopup());
        getCreatePopup().hide();
        addPartialTrigger(getSearchTable());
    }

    public void projectValueChanged(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        Object message = executeMethod("validateProject");
        if ("Error".equals(message)) {
            showPopupMessage("There already exists a working document. Search for the document instead.",
                             FacesMessage.SEVERITY_ERROR);
        }
    }
}
