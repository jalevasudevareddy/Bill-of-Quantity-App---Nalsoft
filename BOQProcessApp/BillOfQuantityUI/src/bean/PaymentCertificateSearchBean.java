package bean;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputComboboxListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.event.PopupCanceledEvent;

import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class PaymentCertificateSearchBean extends CommonBean {
    private RichPopup createPopup;
    private RichTable searchTable;
    private RichInputComboboxListOfValues prjNumBindgs;
    private RichInputComboboxListOfValues certNumBindgs;
    private RichInputComboboxListOfValues perdBindgs;
    private RichInputComboboxListOfValues docTypeBindgs;
    private RichPopup revisePCDocPopupBindgs;
    private RichInputComboboxListOfValues contractNumBind;

    public PaymentCertificateSearchBean() {
        super();
    }

    public void createHeaderRow(ActionEvent ae) {
        executeMethod("createHeaderRow");
        showPopup(getCreatePopup());
    }

    public void createDocument(ActionEvent ae) {
        try {
            Object message = executeMethod("createDocument");
            if ("Success".equals(message)) {
                save();
                Object exeMess = executeMethod("refreshHdrRow");
                if ("Success".equals(exeMess)) {
                    navigate(ae, "view");
                } else {
                    showPopupMessage(exeMess != null ? exeMess.toString() :
                                     "Unable to refresh the current document.",
                                     FacesMessage.SEVERITY_ERROR);
                }
                //            addPartialTrigger(getPageBindings());


            } else {
                CancelPC(ae);
                showPopupMessage(message != null ? message.toString() :
                                 "Unable to create a new document.",
                                 FacesMessage.SEVERITY_ERROR);

            }
        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
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

    public void CancelPC(ActionEvent actionEvent) {
        try {
            executeMethod("cancelDocument");
            save();
            if (getCreatePopup() != null)
                oracle.adf.view.rich.util.ResetUtils.reset(getCreatePopup());
            getCreatePopup().hide();
            addPartialTrigger(getSearchTable());
        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void buNameVL(ValueChangeEvent valueChangeEvent) {
        try {
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            Map<String, Object> attrMap = new HashMap<String, Object>();
            attrMap.put("TransProjectNum", null);
            attrMap.put("TransProjectId", null);
            attrMap.put("TransDocType", null);
            attrMap.put("TransCertNum", null);
            attrMap.put("TransPeriod", null);


            getCurrentPageFlowScope().put("attributesMap", attrMap);
            Object exeMessObj = executeMethod("refreshHdrVOAttrs");

            if (!"Success".equals(exeMessObj)) {
                showPopupMessage(exeMessObj != null ? exeMessObj.toString() :
                                 "Unable to refresh trans liability row.",
                                 FacesMessage.SEVERITY_ERROR);
            }


        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getCertNumBindgs());
        addPartialTrigger(getDocTypeBindgs());
        addPartialTrigger(getCertNumBindgs());
        addPartialTrigger(getPerdBindgs());
    }

    public void prjNumVL(ValueChangeEvent valueChangeEvent) {
        try {
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            Map<String, Object> attrMap = new HashMap<String, Object>();


            attrMap.put("TransPeriod", null);
            attrMap.put("TransDocType", null);

            getCurrentPageFlowScope().put("attributesMap", attrMap);
            Object exeMessObj = executeMethod("refreshHdrVOAttrs");

            if (!"Success".equals(exeMessObj)) {
                showPopupMessage(exeMessObj != null ? exeMessObj.toString() :
                                 "Unable to refresh trans liability row.",
                                 FacesMessage.SEVERITY_ERROR);
            }


        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

        addPartialTrigger(getCertNumBindgs());
        addPartialTrigger(getCertNumBindgs());
        addPartialTrigger(getDocTypeBindgs());
        addPartialTrigger(getPerdBindgs());
    }

    public void contractNumVL(ValueChangeEvent valueChangeEvent) {
        try {
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            Map<String, Object> attrMap = new HashMap<String, Object>();
           
           
            attrMap.put("TransPeriod", null);
            attrMap.put("TransDocType", null);

            getCurrentPageFlowScope().put("attributesMap", attrMap);
            Object exeMessObj = executeMethod("refreshHdrVOAttrs");

            if (!"Success".equals(exeMessObj)) {
                showPopupMessage(exeMessObj != null ? exeMessObj.toString() :
                                 "Unable to refresh trans liability row.",
                                 FacesMessage.SEVERITY_ERROR);
            }


        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        
        addPartialTrigger(getCertNumBindgs());
        addPartialTrigger(getCertNumBindgs());
        addPartialTrigger(getDocTypeBindgs());
        addPartialTrigger(getPerdBindgs());
    }
    public void setPrjNumBindgs(RichInputComboboxListOfValues prjNumBindgs) {
        this.prjNumBindgs = prjNumBindgs;
    }

    public RichInputComboboxListOfValues getPrjNumBindgs() {
        return prjNumBindgs;
    }

    public void custNumVL(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        addPartialTrigger(getDocTypeBindgs());
    }

    public void certNumVL(ValueChangeEvent valueChangeEvent) {
        try {
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            Map<String, Object> attrMap = new HashMap<String, Object>();


            //            attrMap.put("Period", null);


            getCurrentPageFlowScope().put("attributesMap", attrMap);
            //            Object exeMessObj = executeMethod("refreshHdrVOAttrs");
            //
            //            if (!"Success".equals(exeMessObj)) {
            //                showPopupMessage(exeMessObj != null ? exeMessObj.toString() :
            //                                 "Unable to refresh Trans Liability Row.",
            //                                 FacesMessage.SEVERITY_ERROR);
            //            }


        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

        addPartialTrigger(getPerdBindgs());
    }

    public void setCertNumBindgs(RichInputComboboxListOfValues certNumBindgs) {
        this.certNumBindgs = certNumBindgs;
    }

    public RichInputComboboxListOfValues getCertNumBindgs() {
        return certNumBindgs;
    }

    public void setPerdBindgs(RichInputComboboxListOfValues perdBindgs) {
        this.perdBindgs = perdBindgs;
    }

    public RichInputComboboxListOfValues getPerdBindgs() {
        return perdBindgs;
    }

    public void peridVL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
    }

    public void docTypeVL(ValueChangeEvent valueChangeEvent) {
        try {
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            Map<String, Object> attrMap = new HashMap<String, Object>();

            attrMap.put("TransCertNum", null);
            //            attrMap.put("TransDocType", null);


            getCurrentPageFlowScope().put("attributesMap", attrMap);
            Object exeMessObj = executeMethod("refreshHdrVOAttrs");

            if (!"Success".equals(exeMessObj)) {
                showPopupMessage(exeMessObj != null ? exeMessObj.toString() :
                                 "Unable to refresh trans liability row.",
                                 FacesMessage.SEVERITY_ERROR);
            }


        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

        addPartialTrigger(getCertNumBindgs());
        addPartialTrigger(getPerdBindgs());
    }

    public void setDocTypeBindgs(RichInputComboboxListOfValues docTypeBindgs) {
        this.docTypeBindgs = docTypeBindgs;
    }

    public RichInputComboboxListOfValues getDocTypeBindgs() {
        return docTypeBindgs;
    }

    public void setRevisePCDocPopupBindgs(RichPopup revisePCDocPopupBindgs) {
        this.revisePCDocPopupBindgs = revisePCDocPopupBindgs;
    }

    public RichPopup getRevisePCDocPopupBindgs() {
        return revisePCDocPopupBindgs;
    }

    public void editInvoiceAL(ActionEvent actionEvent) {
        HashMap jwtMap = (HashMap)getEL("#{sessionScope.pHeader}");
        if (jwtMap != null && jwtMap.containsKey("fusionEndpointURL")) {
            String fusionEndpointURL =
                jwtMap.get("fusionEndpointURL").toString();
            Object pcNum = getEL("#{bindings.PcNum.inputValue}");
            Object buName = getEL("#{bindings.BuName2.inputValue}");
            FacesContext fctx = FacesContext.getCurrentInstance();
            ExtendedRenderKitService erks =
                Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
            StringBuilder script = new StringBuilder();
            if (pcNum != null && buName != null) {
                script.append("window.open('" + fusionEndpointURL +
                              "/fscmUI/faces/deeplink?objType=AR_TRANSACTION&action=EDIT&objKey=transactionNumber=" +
                              pcNum.toString() + ";buName=" +
                              buName.toString() + "')");
                erks.addScript(FacesContext.getCurrentInstance(),
                               script.toString());
            }
        }
    }

    public void setContractNumBind(RichInputComboboxListOfValues contractNumBind) {
        this.contractNumBind = contractNumBind;
    }

    public RichInputComboboxListOfValues getContractNumBind() {
        return contractNumBind;
    }

}
