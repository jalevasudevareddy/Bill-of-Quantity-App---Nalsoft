package bean;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletResponse;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.BindingContainer;

import oracle.jbo.Row;
import oracle.jbo.domain.BlobDomain;

public class CandyBOQSearchBean extends CommonBean {
    private RichCommandLink fileNameBindgs;
    private RichSelectBooleanCheckbox baselineBind;
    private RichSelectBooleanCheckbox budgetFlagBind;
    private RichSelectBooleanCheckbox financialPlanFlagBind;
    private RichPopup createBudgetPopupBind;
    private RichPopup budgetLinesPopupBind;

    public CandyBOQSearchBean() {
        super();
    }

    public void editDocument(ActionEvent ae) {
        executeMethod("editDocument");
        navigate(ae, "Create");
    }

    public void interfaceTasks(ActionEvent actionEvent) {
        Object message = executeMethod("interfaceTasks");
        if (message != null) {
            if ("SUCCESS".equals(message)) {
                showPopupMessage("Tasks are interfaced.",
                                 FacesMessage.SEVERITY_INFO);
            } else {
                showPopupMessage("Tasks are not Interfaced.",
                                 FacesMessage.SEVERITY_ERROR);
            }
        }
    }

    public void interfaceBudget(ActionEvent actionEvent) {
        Object message = executeMethod("interfaceBudget");
        if (message != null) {
            if ("SUCCESS".equals(message)) {
                showPopupMessage("Budgets are interfaced.",
                                 FacesMessage.SEVERITY_INFO);
            } else {
                showPopupMessage("Bugets are not Interfaced.",
                                 FacesMessage.SEVERITY_ERROR);
            }
        }
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

    public void boqPcDetlsReportAL(FacesContext facesContext,
                                   OutputStream outputStream) {
        try {
            Object prjId = getEL("#{bindings.ProjectId.inputValue}");
            getCurrentPageFlowScope().put("prjId", prjId);
            if (prjId != null) {
                Object dataObj = executeMethod("runBoqPcReport");

                if (dataObj != null) {
                    outputStream.write((byte[])dataObj);
                    outputStream.flush();
                }

            }
        } catch (Exception e) {

        }
    }

    public void viewActionHistAL(ActionEvent actionEvent) {
        try {
            Object qacHdrId = getEL("#{bindings.BoqHeaderId.inputValue}");
            getCurrentPageFlowScope().put("docId", qacHdrId);
            getCurrentPageFlowScope().put("docTypeId", "1");
            navigate(actionEvent, "ActionHistory");
        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void runBoqAnalysisReport(FacesContext facesContext,
                                     OutputStream outputStream) {
        try {


            Object dataObj = executeMethod("runAndGetBoqAnalysisReportData");
            System.out.println(dataObj.toString());

            if (dataObj != null) {
                outputStream.write((byte[])dataObj);
                outputStream.flush();
            }

        } catch (Exception e) {

        }
    }

    public void interfaceBudgetFDAL(FacesContext facesContext,
                                    OutputStream outputStream) {
        try {
            Object exeObj = executeMethod("interfaceBudget");
            if (exeObj != null && exeObj instanceof byte[]) {
                byte[] dataBytes = (byte[])exeObj;
                outputStream.write(dataBytes);
                outputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void setBaselineBind(RichSelectBooleanCheckbox baselineBind) {
        this.baselineBind = baselineBind;
    }

    public RichSelectBooleanCheckbox getBaselineBind() {
        return baselineBind;
    }

    public void createBudgetAL(ActionEvent actionEvent) {
        try {
            Object version = getEL("#{bindings.Version.inputValue}");
            if(version ==null){
                setEL("#{bindings.PlanVersionName.inputValue}", "Version "+version);
            }
            showPopup(getCreateBudgetPopupBind());
        } catch (Exception e) {
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void createBudget(ActionEvent actionEvent) {
        try {
            save();
            Object obj = executeMethod("createBudget");
            if (!"Success".equals(obj)) {
                showPopupMessage(obj == null ? "Unable to create budget" :
                                 obj.toString(), FacesMessage.SEVERITY_ERROR);
            } else {
                showPopupMessage("Budget created successfully. Please refresh the page",
                                 FacesMessage.SEVERITY_INFO);
                addPartialTrigger(getBudgetFlagBind());
            }
        } catch (Exception e) {
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void createBudgetLines(ActionEvent actionEvent) {
        try {
            save();
            Object obj = executeMethod("createBudgetLines");
            if (!"Success".equals(obj)) {
                showPopupMessage(obj == null ? "Unable to create budget lines" :
                                 obj.toString(), FacesMessage.SEVERITY_ERROR);
            }else{
                showPopup(getBudgetLinesPopupBind());
            }
        } catch (Exception e) {
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void createFinancialPlan(ActionEvent actionEvent) {
        try {
            Object obj = executeMethod("createFinancialPlan");
            if (!"Success".equals(obj)) {
                showPopupMessage(obj == null ? "Unable to create financial plan" :
                                 obj.toString(), FacesMessage.SEVERITY_ERROR);
            } else {
                showPopupMessage("Financial plan created successfully. Please refresh the page",
                                 FacesMessage.SEVERITY_INFO);
                addPartialTrigger(getFinancialPlanFlagBind());
            }
        } catch (Exception e) {
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void baselineConfirmation(DialogEvent dialogEvent) {
        try {
            Object obj = executeMethod("createBaseline");
            if (obj != null && (!"Success".equals(obj))) {
                showPopupMessage(obj.toString(), FacesMessage.SEVERITY_ERROR);
            } else {
                showPopupMessage("Baseline successful. Please refresh the page.",
                                 FacesMessage.SEVERITY_INFO);
                addPartialTrigger(getBaselineBind());
            }
        } catch (Exception e) {
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void BoqExportFileDownload(FacesContext facesContext,
                                      OutputStream outputStream) {
        byte[] report = (byte[])executeMethod("getUploadedBoqData");
        try {
            outputStream.write(report);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBudgetFlagBind(RichSelectBooleanCheckbox budgetFlagBind) {
        this.budgetFlagBind = budgetFlagBind;
    }

    public RichSelectBooleanCheckbox getBudgetFlagBind() {
        return budgetFlagBind;
    }

    public void setFinancialPlanFlagBind(RichSelectBooleanCheckbox financialPlanFlagBind) {
        this.financialPlanFlagBind = financialPlanFlagBind;
    }

    public RichSelectBooleanCheckbox getFinancialPlanFlagBind() {
        return financialPlanFlagBind;
    }

    public void setCreateBudgetPopupBind(RichPopup createBudgetPopupBind) {
        this.createBudgetPopupBind = createBudgetPopupBind;
    }

    public RichPopup getCreateBudgetPopupBind() {
        return createBudgetPopupBind;
    }

    public void setBudgetLinesPopupBind(RichPopup budgetLinesPopupBind) {
        this.budgetLinesPopupBind = budgetLinesPopupBind;
    }

    public RichPopup getBudgetLinesPopupBind() {
        return budgetLinesPopupBind;
    }
}
