package bean;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputComboboxListOfValues;
import oracle.adf.view.rich.event.PopupCanceledEvent;

public class CostingDetailsSearchBean extends CommonBean {
    private RichPopup transCostingDetailsPopupBindgs;
    private RichInputComboboxListOfValues transProjectNumBindgs;

    public CostingDetailsSearchBean() {
        super();
    }

    public void transCostingDetailsPopupCL(PopupCanceledEvent popupCanceledEvent) {
        if (getTransCostingDetailsPopupBindgs() != null)
            oracle.adf.view.rich.util.ResetUtils.reset(getTransCostingDetailsPopupBindgs());
        getTransCostingDetailsPopupBindgs().hide();
    }

    public void setTransCostingDetailsPopupBindgs(RichPopup transCostingDetailsPopupBindgs) {
        this.transCostingDetailsPopupBindgs = transCostingDetailsPopupBindgs;
    }

    public RichPopup getTransCostingDetailsPopupBindgs() {
        return transCostingDetailsPopupBindgs;
    }

    public void transCostingDetailsPopupAL(ActionEvent actionEvent) {
        Object isCostDetExistsMessObj =
            executeMethod("checkWhetherCostingDetailsCreatedForSelectedPrj");
        if (isCostDetExistsMessObj != null &&
            isCostDetExistsMessObj instanceof Boolean) {
            Boolean isCostingDetExists = (Boolean)isCostDetExistsMessObj;
            if (!isCostingDetExists) {
                Object isItemAnlyExistsMessObj =
                    executeMethod("isJobItemAnalysisCreatedForProject");
                if (isItemAnlyExistsMessObj != null &&
                    isItemAnlyExistsMessObj instanceof Boolean) {
                    Boolean isItemAnyExists = (Boolean)isItemAnlyExistsMessObj;
                    if (isItemAnyExists) {
                        Object isBoqHdrCreatedObj =
                            executeMethod("isBOQHeaderCreatedForProject");
                        if (isBoqHdrCreatedObj != null &&
                            isBoqHdrCreatedObj instanceof Boolean) {
                            Boolean isBoqHdrExists =
                                (Boolean)isBoqHdrCreatedObj;
                            if (isBoqHdrExists) {
                                Object costingDetId =
                                    executeMethod("createCostingDetailsForSelectedProject");
                                save();
                                if (costingDetId != null) {
                                    this.getCurrentPageFlowScope().put("costingDetailHdrId",
                                                                       costingDetId);
                                    Object exeMessObj =
                                        executeMethod("makeAsCurrentCostingDetailsHdrForId");
                                    if (exeMessObj != null &&
                                        exeMessObj instanceof Boolean) {
                                        Boolean iscurrRowSetted =
                                            (Boolean)exeMessObj;
                                        if (iscurrRowSetted) {
                                            navigate(actionEvent,
                                                     "costingDetails");
                                        } else {
                                            showPopupMessage("Unable to navigate to the edit page due to making as current costing header failed . ",
                                                             FacesMessage.SEVERITY_ERROR);
                                        }
                                    }
                                }
                            } else {
                                showPopupMessage("BOQ Details not created for selected Project.",
                                                 FacesMessage.SEVERITY_ERROR);
                            }
                        }


                    } else {
                        showPopupMessage("Item Analysis is not created/ not approved for selected project.",
                                         FacesMessage.SEVERITY_ERROR);
                    }
                }
            } else {
                showPopupMessage("A Document is already is created for selected project.",
                                 FacesMessage.SEVERITY_ERROR);
            }
        }


    }

    public void setTransProjectNumBindgs(RichInputComboboxListOfValues transProjectNumBindgs) {
        this.transProjectNumBindgs = transProjectNumBindgs;
    }

    public RichInputComboboxListOfValues getTransProjectNumBindgs() {
        return transProjectNumBindgs;
    }

    public void transBuNameVL(ValueChangeEvent valueChangeEvent) {
        Map attributeMap = new HashMap<String, Object>();
        attributeMap.put("TransProjectId", null);
        attributeMap.put("TransProjectNumber", null);
        getCurrentPageFlowScope().put("attributeMap", attributeMap);
        executeMethod("refreshTransCostingDetailsVOWith");
        addPartialTrigger(getTransProjectNumBindgs());
    }

    public void createCostingDetailsAL(ActionEvent actionEvent) {
        executeMethod("initTransCostingDetailsaHeaderVO");
        showPopup(getTransCostingDetailsPopupBindgs());
    }

    public void editCostDetailsAL(ActionEvent actionEvent) {
        Object costingDetailsHdrId =
            getEL("#{bindings.CostingDetailsHeaderId.inputValue}");
        if (costingDetailsHdrId != null) {
            this.getCurrentPageFlowScope().put("costingDetailHdrId",
                                               costingDetailsHdrId);
            Object exeMessObj =
                executeMethod("makeAsCurrentCostingDetailsHdrForId");
            if (exeMessObj != null && exeMessObj instanceof Boolean) {
                Boolean iscurrRowSetted = (Boolean)exeMessObj;
                if (iscurrRowSetted) {
                    navigate(actionEvent, "costingDetails");
                } else {
                    showPopupMessage("Unable to navigate to the edit page due to making as current costing header failed . ",
                                     FacesMessage.SEVERITY_ERROR);
                }
            }
        } else {
            showPopupMessage("Unable to navigate to the edit page due to selected costing header id retrival failed. ",
                             FacesMessage.SEVERITY_ERROR);
        }

    }
}
