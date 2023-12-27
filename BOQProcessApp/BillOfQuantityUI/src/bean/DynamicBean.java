package bean;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.controller.TaskFlowId;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;

import oracle.ui.pattern.dynamicShell.TabContext;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class DynamicBean extends CommonBean {

    private String taskFlowId = "/WEB-INF/JWTValidationTF.xml#JWTValidationTF";
    private RichShowDetailItem showDetailItemBind;
    private RichRegion regionBinding;

    public DynamicBean() {
        super();
    }

    public TaskFlowId getDynamicTaskFlowId() {
        return TaskFlowId.parse(taskFlowId);
    }

    public String ActivityMasterTF() {
        taskFlowId = "/WEB-INF/ActivityMasterTF.xml#ActivityMasterTF";
        return null;
    }

    public String billOfQuantityTF() {
        taskFlowId = "/WEB-INF/BillOfQuantityTF.xml#BillOfQuantityTF";
        return null;
    }

    public String ResourceMasterTF() {
        taskFlowId = "/WEB-INF/ResourceMasterTF.xml#ResourceMasterTF";
        return null;
    }

    public String structureSetupTF() {
        taskFlowId = "/WEB-INF/StructureSetupTF.xml#StructureSetupTF";
        return null;
    }

    public String jobItemAnalysisTF() {
        taskFlowId = "/WEB-INF/JobItemAnalysisTF.xml#JobItemAnalysisTF";
        return null;
    }

    public String jobTemplateTF() {
        taskFlowId = "/WEB-INF/JobTemplateTF.xml#JobTemplateTF";
        return null;
    }

    public void workDoneSearchTF(ActionEvent actionEvent) {
        getShowDetailItemBind().setDisclosed(false);
        _launchActivity("Work Done", "/WEB-INF/WorkDoneSearchTF.xml#WorkDoneSearchTF", false);
//        taskFlowId = "/WEB-INF/WorkDoneSearchTF.xml#WorkDoneSearchTF";
//        return null;
    }

    public void certifiedSearchTF(ActionEvent actionEvent) {
        getShowDetailItemBind().setDisclosed(false);
        _launchActivity("Certified Details", "/WEB-INF/CertifiedSearchTF.xml#CertifiedSearchTF", false);
//        taskFlowId = "/WEB-INF/CertifiedSearchTF.xml#CertifiedSearchTF";
//        return null;
    }

    public void invoicedSearchTF(ActionEvent actionEvent) {
        getShowDetailItemBind().setDisclosed(false);
        _launchActivity("Payment Application", "/WEB-INF/InvoicedSearchTF.xml#InvoicedSearchTF", false);
//        taskFlowId = "/WEB-INF/InvoicedSearchTF.xml#InvoicedSearchTF";
//        return null;
    }

    public String planningScheduleTF() {
        taskFlowId = "/WEB-INF/PlanningScheduleTF.xml#PlanningScheduleTF";
        return null;
    }

    public String projectJobItemAnalysisTF() {
        taskFlowId =
                "/WEB-INF/ProjectJobItemAnalysisTF.xml#ProjectJobItemAnalysisTF";
        return null;
    }


    public String costingDetailsTF() {
        taskFlowId = "/WEB-INF/CostingDetailsTF.xml#CostingDetailsTF";
        return null;
    }

    public String materialAtSiteSearchTF() {
        taskFlowId =
                "/WEB-INF/MaterialAtSiteSearchTF.xml#MaterialAtSiteSearchTF";
        return null;
    }

    public String progressDetailsTF() {
        taskFlowId = "/WEB-INF/ProgressDetailsTF.xml#ProgressDetailsTF";
        return null;
    }

    public void paymentCertificateTF(ActionEvent actionEvent) {
        getShowDetailItemBind().setDisclosed(false);
        _launchActivity("Payment Certificate", "/WEB-INF/PaymentCertificateTF.xml#PaymentCertificateTF", false);
//        taskFlowId = "/WEB-INF/PaymentCertificateTF.xml#PaymentCertificateTF";
//        return null;
    }

    public void boqUploadValidationTF(ActionEvent actionEvent) {
        getShowDetailItemBind().setDisclosed(false);
        _launchActivity("Create Bill of Quantity", "/WEB-INF/BOQUploadValidationTF.xml#BOQUploadValidationTF", false);
//        taskFlowId =
//                "/WEB-INF/BOQUploadValidationTF.xml#BOQUploadValidationTF";
//        return null;
    }

    public void candyBOQDetailsTF(ActionEvent actionEvent) {
        getShowDetailItemBind().setDisclosed(false);
        _launchActivity("Manage Bill of Quantity", "/WEB-INF/CandyBOQDetailsTF.xml#CandyBOQDetailsTF", false);
//        taskFlowId = "/WEB-INF/CandyBOQDetailsTF.xml#CandyBOQDetailsTF";
//        return null;
    }

    public String revenueRecognitionTF() {
        taskFlowId = "/WEB-INF/RevenueRecognitionTF.xml#RevenueRecognitionTF";
        return null;
    }

    public void bOQAccountsSetupTF(ActionEvent actionEvent) {
        getShowDetailItemBind().setDisclosed(false);
        _launchActivity("Accounting Setup", "/WEB-INF/BOQAccountsSetupTF.xml#BOQAccountsSetupTF", false);
//        taskFlowId = "/WEB-INF/BOQAccountsSetupTF.xml#BOQAccountsSetupTF";
//        return null;
    }

    public String workListTF() {
        taskFlowId = "/WEB-INF/notification/workListTF.xml#workListTF";
        return null;
    }

    public String homePageTF() {
        getShowDetailItemBind().setDisclosed(false);
        taskFlowId = "/WEB-INF/HomePageTF.xml#HomePageTF";
        return null;
    }

    public void notificationsAL(ActionEvent actionEvent) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks =
            Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        StringBuilder script = new StringBuilder();
        Map jwtMap =
            (Map)ADFContext.getCurrent().getSessionScope().get("pHeader");
        String appUrl = "";
        String jwt = "";
        if (jwtMap != null) {
            appUrl = (String)jwtMap.get("ameAppURL");
            jwt = (String)jwtMap.get("fusionJWTToken");
        }
        script.append("window.open('" + appUrl + jwt + "');");
        //        script.append("window.open('"+appUrl+ "faces/adf.task-flow?adf.tfId=CustomWorklistTF&adf.tfDoc=/WEB-INF/CustomWorklistTF.xml&jwt="+jwt+"');");
        erks.addScript(FacesContext.getCurrentInstance(), script.toString());
    }

    public void setShowDetailItemBind(RichShowDetailItem showDetailItemBind) {
        this.showDetailItemBind = showDetailItemBind;
    }

    public RichShowDetailItem getShowDetailItemBind() {
        return showDetailItemBind;
    }

    public void projectLevelTaxTF(ActionEvent actionEvent) {
        getShowDetailItemBind().setDisclosed(false);
        _launchActivity("Tax Setup", "/WEB-INF/ProjectLevelTaxTF.xml#ProjectLevelTaxTF", false);
//        taskFlowId = "/WEB-INF/ProjectLevelTaxTF.xml#ProjectLevelTaxTF";
//        return null;
    }

    public void boqReportsAL(ActionEvent actionEvent) {
        HashMap jwtMap = (HashMap)getEL("#{sessionScope.pHeader}");
        Object jwtObj = getEL("#{sessionScope.jwt}");
        if (jwtMap != null && jwtMap.containsKey("boqReportsURL") &&
            jwtObj != null) {
            String boqReportsURL = jwtMap.get("boqReportsURL").toString();
            FacesContext fctx = FacesContext.getCurrentInstance();
            ExtendedRenderKitService erks =
                Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
            StringBuilder script = new StringBuilder();
            script.append("window.open('" + boqReportsURL + jwtObj.toString() +
                          "')");
            erks.addScript(FacesContext.getCurrentInstance(),
                           script.toString());
        }
    }
    
    private void _launchActivity(String title, String taskflowId, boolean newTab) {
        try {
            if (newTab) {
                TabContext.getCurrentInstance().addTab(title, taskflowId);
            } else {
                TabContext.getCurrentInstance().addOrSelectTab(title, taskflowId);
            }
        } catch (TabContext.TabOverflowException toe) {
            // causes a dialog to be displayed to the user saying that there are
            // too many tabs open - the new tab will not be opened...
            toe.handleDefault();
        }
    }
    
    public void setRegionBinding(RichRegion regionBinding) {
        this.regionBinding = regionBinding;
    }

    public RichRegion getRegionBinding() {
        return regionBinding;
    }
}
