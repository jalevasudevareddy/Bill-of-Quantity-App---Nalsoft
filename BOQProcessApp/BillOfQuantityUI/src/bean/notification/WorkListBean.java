package bean.notification;

import bean.CommonBean;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.controller.ControllerContext;
import oracle.adf.controller.TaskFlowId;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.layout.RichPanelHeader;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.jbo.domain.Number;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.event.PollEvent;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class WorkListBean extends CommonBean {
    private RichTable workListTable;
    private String taskFlowId = "NotificationTF";
    private String taskFlowDocument =
        "/WEB-INF/notification/NotificationTF.xml";
    private RichPanelHeader panelHdrBind;

    public WorkListBean() {
        super();
    }

    public void showActionHistory(ActionEvent actionEvent) {
        // Add event code here...
        JUCtrlHierNodeBinding rowdata =
            (JUCtrlHierNodeBinding)getWorkListTable().getSelectedRowData();
        if (rowdata != null) {
            Number docId = (Number)rowdata.getAttribute("ReqDocumentId");
            Number docTypeId = (Number)rowdata.getAttribute("DocumentTypeId");
            Map pageFlowScope =
                AdfFacesContext.getCurrentInstance().getPageFlowScope();
            pageFlowScope.put("docId", docId);
            pageFlowScope.put("docTypeId", docTypeId);
            navigate(actionEvent, "actionHistory");
        }
    }

    public void setWorkListTable(RichTable workListTable) {
        this.workListTable = workListTable;
    }

    public RichTable getWorkListTable() {
        return workListTable;
    }

    public void refreshWorkList(ActionEvent actionEvent) {
        executeMethod("setWorkListForEmployee");
        addPartialTrigger(getWorkListTable());
    }

    public void workListNotification(ActionEvent actionEvent) {
        // Add event code here...
        JUCtrlHierNodeBinding rowdata =
            (JUCtrlHierNodeBinding)getWorkListTable().getSelectedRowData();
        if (rowdata != null) {
            Number typeId = (Number)rowdata.getAttribute("DocumentTypeId");
            Number reqId = (Number)rowdata.getAttribute("ReqDocumentId");
            Number currEmpId = (Number)rowdata.getAttribute("ToEmployeeId");
            Number currEmpLevel =
                (Number)rowdata.getAttribute("ApproverLevel");
            String docNum = (String)rowdata.getAttribute("Subject");
            String apprvCat = (String)rowdata.getAttribute("ApprovalCategory");
            String apprvName = (String)rowdata.getAttribute("ToUserName");

            if (typeId != null && reqId != null) {
                Map<String, Object> params = getCurrentPageFlowScope();
                params.put("docTypeId", typeId.longValue());
                params.put("docId", reqId.longValue());
                params.put("currEmpId",
                           currEmpId != null ? currEmpId.longValue() : null);
                params.put("currEmpLevel",
                           currEmpLevel != null ? currEmpLevel.longValue() :
                           0L);
                params.put("docNum", docNum);
                params.put("apprvCat", apprvCat);
                params.put("apprvName", apprvName);

                ADFContext.getCurrent().getSessionScope().put("fromApplication",
                                                              "Y");
                navigate(actionEvent, "approvalNotification");
                //            String taskflowURL =
                //                ControllerContext.getInstance().getTaskFlowURL(false,
                //                                                               new TaskFlowId(taskFlowDocument,
                //                                                                              taskFlowId),
                //                                                               params);
                //            FacesContext fctx = FacesContext.getCurrentInstance();
                //            ExtendedRenderKitService erks =
                //                Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
                //            StringBuilder script = new StringBuilder();
                //            script.append("window.open(\"" + taskflowURL +"\"" ).append(",\"location=0,status=0,scrollbars=0\");");
                //            erks.addScript(FacesContext.getCurrentInstance(),
                //                           script.toString());
            }
        }
    }

    public void setPanelHdrBind(RichPanelHeader panelHdrBind) {
        this.panelHdrBind = panelHdrBind;
    }

    public RichPanelHeader getPanelHdrBind() {
        executeMethod("setWorkListForEmployee");
        return panelHdrBind;
    }

    public void refreshWorkList(PollEvent pollEvent) {
        executeMethod("Execute");
    }

    public void viewClosedNotifications(ActionEvent actionEvent) {
        executeMethod("viewClosedNotifications");
        addPartialTrigger(getWorkListTable());
    }
}

