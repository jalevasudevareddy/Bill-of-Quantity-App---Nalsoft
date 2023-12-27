package bean;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.jbo.domain.Date;

public class ProjectLevelTaxBean extends CommonBean {
    private RichPopup createTaxSetupBind;
    private RichInputDate linesEndDateBind;
    private RichPopup deleteTaxDocPopupBindgs;
    private RichTable projectTaxHeaderTableBind;

    public ProjectLevelTaxBean() {
        super();
    }

    public void createProjectTaxHeader(ActionEvent actionEvent) {
        executeMethod("createTransTaxHeaderRow");
        showPopup(getCreateTaxSetupBind());
    }

    public void createTaxHeader(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
            Object message = executeMethod("createTaxHeaderRow");
            if (!"Success".equals(message)) {
                showPopupMessage(message == null ?
                                 "Error while creating Tax Header" :
                                 message.toString(),
                                 FacesMessage.SEVERITY_ERROR);
            } else {
                navigate(dialogEvent, "Edit");
            }
        }
    }

    public void setCreateTaxSetupBind(RichPopup createTaxSetupBind) {
        this.createTaxSetupBind = createTaxSetupBind;
    }

    public RichPopup getCreateTaxSetupBind() {
        return createTaxSetupBind;
    }

    public void saveLines(ActionEvent actionEvent) {
        Object message = executeMethod("saveLines");
        if (message != null && !"Success".equals(message)) {
            showPopupMessage(message == null ?
                             "Error while saving the tax lines" :
                             message.toString(), FacesMessage.SEVERITY_ERROR);
        } else {
            save();
        }
    }

    public void saveAndCloseLines(ActionEvent actionEvent) {
        Object message = executeMethod("saveLines");
        if (!"Success".equals(message)) {
            showPopupMessage(message == null ?
                             "Error while saving the tax lines" :
                             message.toString(), FacesMessage.SEVERITY_ERROR);
        } else {
            save();
            message = executeMethod("refreshTaxHeaderAndLines");
            navigate(actionEvent, "Done");
        }
    }

    public void cancelLines(ActionEvent actionEvent) {
        cancel();
        executeMethod("refreshTaxHeaderAndLines");
        navigate(actionEvent, "Done");
    }

    public void setLinesEndDateBind(RichInputDate linesEndDateBind) {
        this.linesEndDateBind = linesEndDateBind;
    }

    public RichInputDate getLinesEndDateBind() {
        return linesEndDateBind;
    }

    public void linesEndDateVL(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != null) {
            if (Date.getCurrentDate().dateValue().compareTo((java.sql.Date)valueChangeEvent.getNewValue()) >
                0) {
                getLinesEndDateBind().setValue(valueChangeEvent.getOldValue());
                addPartialTrigger(getLinesEndDateBind());
                showPopupMessage("End Date can't be backdated",
                                 FacesMessage.SEVERITY_ERROR);
            }
        }
    }

    public void setDeleteTaxDocPopupBindgs(RichPopup deleteTaxDocPopupBindgs) {
        this.deleteTaxDocPopupBindgs = deleteTaxDocPopupBindgs;
    }

    public RichPopup getDeleteTaxDocPopupBindgs() {
        return deleteTaxDocPopupBindgs;
    }

    public void deleteTaxHeaderDoc(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.yes) {
            Object headerId = getEL("#{bindings.HeaderId.inputValue}");
            getCurrentPageFlowScope().put("HeaderId", headerId);
            Object exeMessage = executeMethod("deleteTaxHeaderDoc");
            if (exeMessage != null) {
                if ("Success".equals(exeMessage)) {
                    save();
                    addPartialTrigger(getProjectTaxHeaderTableBind());
                } else {
                    showPopupMessage(exeMessage.toString(),
                                     FacesMessage.SEVERITY_ERROR);
                }
            }
        }
    }

    public void setProjectTaxHeaderTableBind(RichTable projectTaxHeaderTableBind) {
        this.projectTaxHeaderTableBind = projectTaxHeaderTableBind;
    }

    public RichTable getProjectTaxHeaderTableBind() {
        return projectTaxHeaderTableBind;
    }
}
