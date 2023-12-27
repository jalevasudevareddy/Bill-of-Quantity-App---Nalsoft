package bean;

import bean.notification.NotificationBean;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;

import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class BoqUploadValidationBean extends CommonBean {
    private RichPopup boqUploadPopupBindgs;
    private RichPopup boqRevicePopupBindgs;
    private RichPopup overrideBoqExistDocPopupBindgs;
    private RichPopup deleteBoqDocPopupBindgs;
    private RichPopup cancelUploadedBoqDocPopupBindgs;
    private RichPopup withdrawlApprlPopupBindgs;
    private RichPanelGroupLayout rejectView;
    private RichTable BOQValidationHeaderTableBind;

    public BoqUploadValidationBean() {
        super();
    }

    public void createBoqUploadAL(ActionEvent actionEvent) {
        executeMethod("initTransBoqUploadValVO");
        showPopup(getBoqUploadPopupBindgs());
    }

    public void editBoqUploadAL(ActionEvent actionEvent) {
        getCurrentPageFlowScope().put("zero", new Number(0));
        Object validationId =
            getEL("#{bindings.ValidationHeaderId.inputValue}");
        if (validationId != null) {
            getCurrentPageFlowScope().put("boqValHdrId", validationId);
            Object isSettedObj = executeMethod("makeAsCurrentBoqValHdrRow");
            if (isSettedObj != null && isSettedObj instanceof Boolean) {
                Boolean isSetted = (Boolean)isSettedObj;
                if (isSetted) {
                    navigate(actionEvent, "BoqUploadLines");
                } else {
                    showPopupMessage("Unable to navigate.",
                                     FacesMessage.SEVERITY_ERROR);
                }
            } else {
                showPopupMessage("Unable to navigate.",
                                 FacesMessage.SEVERITY_ERROR);
            }
        } else {
            showPopupMessage("Unable to navigate.",
                             FacesMessage.SEVERITY_ERROR);
        }
    }

    public void boqUploadPopupCL(PopupCanceledEvent popupCanceledEvent) {
        if (getBoqUploadPopupBindgs() != null)
            oracle.adf.view.rich.util.ResetUtils.reset(getBoqUploadPopupBindgs());
        getBoqUploadPopupBindgs().hide();
    }

    public void setBoqUploadPopupBindgs(RichPopup boqUploadPopupBindgs) {
        this.boqUploadPopupBindgs = boqUploadPopupBindgs;
    }

    public RichPopup getBoqUploadPopupBindgs() {
        return boqUploadPopupBindgs;
    }

    public void deleteBoqsfirstPopupAL(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.yes) {
            Object validationId =
                getEL("#{bindings.ValidationHeaderId.inputValue}");
            getCurrentPageFlowScope().put("ValidationHeaderId", validationId);
            Object exeMessage = executeMethod("deleteUploadedBOQDoc");
            if (exeMessage != null) {
                if ("Success".equals(exeMessage)) {
                    save();
                    addPartialTrigger(getBOQValidationHeaderTableBind());
                } else {
                    showPopupMessage(exeMessage.toString(),
                                     FacesMessage.SEVERITY_ERROR);
                }
            }
        }
    }

    public void creataBoqUploadAL(ActionEvent actionEvent) {
        getCurrentPageFlowScope().put("zero", new Number(0));
        try {
            Object exeMess = executeMethod("createUploadBOQDoc");
            if ("Success".equals(exeMess)) {
                navigate(actionEvent, "BoqUploadLines");
            } else {
                showPopupMessage(exeMess != null ? exeMess.toString() :
                                 "Creating new document failed.",
                                 FacesMessage.SEVERITY_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //
    //    public void creataBoqUploadAL(ActionEvent actionEvent) {
    //        Object isExistObj = executeMethod("isBoqDocExistsForProject");
    //        if (isExistObj != null && isExistObj instanceof Boolean) {
    //            Boolean isExists = (Boolean)isExistObj;
    //            if (!isExists) {
    //                Object isExistInUplTblObj =
    //                    executeMethod("isBoqDocExistsForProjectInUpldTbl");
    //                Boolean isExistsInUplTbl = (Boolean)isExistInUplTblObj;
    //                if (!isExistsInUplTbl) {
    //
    //                    Object exeMesasgeObj =
    //                        executeMethod("createBoqUploadValidationHdrRow");
    //                    if (exeMesasgeObj != null &&
    //                        exeMesasgeObj instanceof Number) {
    //                        Number boqHdrId = (Number)exeMesasgeObj;
    //                        save();
    //                        executeMethod("refreshBoqValidateHdrVO");
    //                        getCurrentPageFlowScope().put("boqValHdrId", boqHdrId);
    //                        Object isHdrRowSettedObj =
    //                            executeMethod("makeAsCurrentBoqValHdrRow");
    //                        if (isHdrRowSettedObj != null &&
    //                            isHdrRowSettedObj instanceof Boolean) {
    //
    //                            navigate(actionEvent, "BoqUploadLines");
    //
    //                        } else {
    //                            showPopupMessage("Document successfully created. Unable to set as current Document.",
    //                                             FacesMessage.SEVERITY_ERROR);
    //                        }
    //                    } else {
    //                        showPopupMessage("New Document creation failed.",
    //                                         FacesMessage.SEVERITY_ERROR);
    //                    }
    //
    //                } else {
    //                    showPopupMessage("A Document is already  exist in upload Bill of Quantity  for selected project.",
    //                                     FacesMessage.SEVERITY_ERROR);
    //                }
    //
    //            } else {
    //                showPopupMessage("A Document is already  exist in Bill of Quantity main  for selected project.",
    //                                 FacesMessage.SEVERITY_ERROR);
    //            }
    //        } else {
    //            showPopupMessage("Document creation failed due to checking whether a document is exists for selected project is failed.",
    //                             FacesMessage.SEVERITY_ERROR);
    //        }
    //
    //    }


    public void boqRevicePopupCL(PopupCanceledEvent popupCanceledEvent) {
        if (getBoqRevicePopupBindgs() != null)
            oracle.adf.view.rich.util.ResetUtils.reset(getBoqRevicePopupBindgs());
        getBoqRevicePopupBindgs().hide();
    }

    public void setBoqRevicePopupBindgs(RichPopup boqRevicePopupBindgs) {
        this.boqRevicePopupBindgs = boqRevicePopupBindgs;
    }

    public RichPopup getBoqRevicePopupBindgs() {
        return boqRevicePopupBindgs;
    }

    public Boolean getDocStatusInUploadTbl() {
        Boolean docStatus = null;


        Object maxPrjBoqValDocHdrIdObj =
            executeMethod("getPrjBoqMaxValidationDocHdrIdFromUploadTbl");

        if (maxPrjBoqValDocHdrIdObj != null &&
            maxPrjBoqValDocHdrIdObj instanceof Number) {
            getCurrentPageFlowScope().put("prjBoqValId",
                                          maxPrjBoqValDocHdrIdObj);
            Object isprevDocApprovedObj =
                executeMethod("isPrevBoqValidationDocApproved");
            if (isprevDocApprovedObj != null &&
                isprevDocApprovedObj instanceof Boolean) {
                docStatus = (Boolean)isprevDocApprovedObj;

            }
        }


        return docStatus;
    }

    public void creataBoqReviceAL(ActionEvent actionEvent) {
        Boolean isPrevDocInUpldTblApprd = getDocStatusInUploadTbl();
        if (isPrevDocInUpldTblApprd != null) {
            if (isPrevDocInUpldTblApprd) {
                showPopup(getOverrideBoqExistDocPopupBindgs());
            } else {
                showPopupMessage("There already exists a current working document for this project. Search the document instead.",
                                 FacesMessage.SEVERITY_ERROR);
            }
        } else {
            Object isExistObj = executeMethod("isBoqDocExistsForProject");
            if (isExistObj != null && isExistObj instanceof Boolean) {
                Boolean isExists = (Boolean)isExistObj;
                if (isExists) {
                    Object docIdObj =
                        executeMethod("createBoqRevisedValidationHdrRow");

                    if (docIdObj != null) {
                        Number boqHdrId = (Number)docIdObj;
                        save();
//                        executeMethod("refreshBoqValidateHdrVO");
                        getCurrentPageFlowScope().put("boqValHdrId", boqHdrId);
                        Object isHdrRowSettedObj =
                            executeMethod("makeAsCurrentBoqValHdrRow");
                        if (isHdrRowSettedObj != null &&
                            isHdrRowSettedObj instanceof Boolean) {

                            navigate(actionEvent, "BoqUploadLines");

                        } else {
                            showPopupMessage("Document successfully created. Unable to set as current.",
                                             FacesMessage.SEVERITY_ERROR);
                        }
                    } else {
                        showPopupMessage("Unable to create document(revised) for selected project.",
                                         FacesMessage.SEVERITY_ERROR);
                    }
                }
            } else {
                showPopupMessage("A document with master budget is not created for selected project. ",
                                 FacesMessage.SEVERITY_ERROR);
            }
        }
    }

    //    public void creataBoqReviceAL(ActionEvent actionEvent) {
    //        Object maxPrjBoqValDocHdrIdObj =
    //            executeMethod("getPrjBoqMaxValidationDocHdrId");
    //        if (maxPrjBoqValDocHdrIdObj != null &&
    //            maxPrjBoqValDocHdrIdObj instanceof Number) {
    //            getCurrentPageFlowScope().put("prjBoqValId",
    //                                          maxPrjBoqValDocHdrIdObj);
    //            Object isprevDocApprovedObj =
    //                executeMethod("isPrevBoqValidationDocApproved");
    //            if (isprevDocApprovedObj != null &&
    //                isprevDocApprovedObj instanceof Boolean) {
    //                Boolean isprevDocApproved = (Boolean)isprevDocApprovedObj;
    //                if (isprevDocApproved) {
    //                    Object docIdObj =
    //                        executeMethod("createBoqRevisedValidationHdrRow");
    //
    //                    if (docIdObj != null) {
    //                        Number boqHdrId = (Number)docIdObj;
    //                        save();
    //                        executeMethod("refreshBoqValidateHdrVO");
    //                        getCurrentPageFlowScope().put("boqValHdrId", boqHdrId);
    //                        Object isHdrRowSettedObj =
    //                            executeMethod("makeAsCurrentBoqValHdrRow");
    //                        if (isHdrRowSettedObj != null &&
    //                            isHdrRowSettedObj instanceof Boolean) {
    //
    //                            navigate(actionEvent, "BoqUploadLines");
    //
    //                        } else {
    //                            showPopupMessage("Document successfully created. Unable to set as current Document.",
    //                                             FacesMessage.SEVERITY_ERROR);
    //                        }
    //                    } else {
    //                        showPopupMessage("Unable to create  document(revised) for selected project.",
    //                                         FacesMessage.SEVERITY_ERROR);
    //                    }
    //                } else {
    //                    showPopupMessage("Unable to create  document(revised) for selected project  due a document is already exist and which is not approved. ",
    //                                     FacesMessage.SEVERITY_ERROR);
    //                }
    //
    //            } else {
    //                showPopupMessage("Unable to create  document(revised) for selected project  due a retriving the previous document to check status is failed . ",
    //                                 FacesMessage.SEVERITY_ERROR);
    //            }
    //        } else {
    //            showPopupMessage("Unable to create  document(revised) for selected project  due to a document with Master budget type may not be created or previous document may not be approved. ",
    //                             FacesMessage.SEVERITY_ERROR);
    //        }
    //    }

    public void createBoqReviceAL(ActionEvent actionEvent) {
        executeMethod("initTransBoqUploadRevisionVO");
        showPopup(getBoqRevicePopupBindgs());
    }


    public void setOverrideBoqExistDocPopupBindgs(RichPopup overrideBoqExistDocPopupBindgs) {
        this.overrideBoqExistDocPopupBindgs = overrideBoqExistDocPopupBindgs;
    }

    public RichPopup getOverrideBoqExistDocPopupBindgs() {
        return overrideBoqExistDocPopupBindgs;
    }

    public void overrideDocDL(DialogEvent dialogEvent) {

        Object maxPrjBoqValDocHdrIdObj =
            executeMethod("getPrjBoqMaxValidationDocHdrIdFromUploadTbl");
        if (maxPrjBoqValDocHdrIdObj != null) {
            if (maxPrjBoqValDocHdrIdObj != null &&
                maxPrjBoqValDocHdrIdObj instanceof Number) {
                Number validationHdrId = (Number)maxPrjBoqValDocHdrIdObj;
                getCurrentPageFlowScope().put("validationHdrId",
                                              validationHdrId);
                getCurrentPageFlowScope().put("overrideWith", "Draft");
                Object isUpdatedMessObj =
                    executeMethod("overrideExistingUploadDocWith");
                if (isUpdatedMessObj != null &&
                    isUpdatedMessObj instanceof Boolean) {
                    Boolean isUpdated = (Boolean)isUpdatedMessObj;
                    if (isUpdated) {
                        if (validationHdrId != null) {
                            getCurrentPageFlowScope().put("boqValHdrId",
                                                          validationHdrId);
                            Object isSettedObj =
                                executeMethod("makeAsCurrentBoqValHdrRow");
                            if (isSettedObj != null &&
                                isSettedObj instanceof Boolean) {
                                Boolean isSetted = (Boolean)isSettedObj;
                                if (isSetted) {
                                    navigate(dialogEvent, "BoqUploadLines");
                                } else {
                                    showPopupMessage("Unable to navigate.",
                                                     FacesMessage.SEVERITY_ERROR);
                                }
                            } else {
                                showPopupMessage("Unable to navigate.",
                                                 FacesMessage.SEVERITY_ERROR);
                            }
                        } else {
                            showPopupMessage("Unable to navigate.",
                                             FacesMessage.SEVERITY_ERROR);
                        }
                    }
                }
            }
        }
    }


    public void setDeleteBoqDocPopupBindgs(RichPopup deleteBoqDocPopupBindgs) {
        this.deleteBoqDocPopupBindgs = deleteBoqDocPopupBindgs;
    }

    public RichPopup getDeleteBoqDocPopupBindgs() {
        return deleteBoqDocPopupBindgs;
    }

    public void setCancelUploadedBoqDocPopupBindgs(RichPopup cancelUploadedBoqDocPopupBindgs) {
        this.cancelUploadedBoqDocPopupBindgs = cancelUploadedBoqDocPopupBindgs;
    }

    public RichPopup getCancelUploadedBoqDocPopupBindgs() {
        return cancelUploadedBoqDocPopupBindgs;
    }

    public void withdrawApprlSubmsnAL(ActionEvent actionEvent) {
        showPopup(getWithdrawlApprlPopupBindgs());
    }


    public void viewActionHistAL(ActionEvent actionEvent) {
        try {
            Object qacHdrId =
                getEL("#{bindings.ValidationHeaderId.inputValue}");
            getCurrentPageFlowScope().put("docId", qacHdrId);
            getCurrentPageFlowScope().put("docTypeId", "1");
            navigate(actionEvent, "ActionHistory");
        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void setWithdrawlApprlPopupBindgs(RichPopup withdrawlApprlPopupBindgs) {
        this.withdrawlApprlPopupBindgs = withdrawlApprlPopupBindgs;
    }

    public RichPopup getWithdrawlApprlPopupBindgs() {
        return withdrawlApprlPopupBindgs;
    }

    public void wthdrawPopupDL(DialogEvent dialogEvent) {
        // Add event code here...
    }

    public void withdrawSubmission(ActionEvent actionEvent) {
        try {
            save();
            Object remarksObj = getEL("#{pageFlowScope.WITHDRAWL_REASON}");
            this.getCurrentPageFlowScope().put("remarks", remarksObj);
            Object withdtwSubMesObj = executeMethod("withdrawFromApproval");
            if ("Success".equals(withdtwSubMesObj)) {
                save();
                //                executeMethod("refreshBoqValidateHdrVO");
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

    public void docSubmtPopupAL(ActionEvent actionEvent) {
        // Add event code here...
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

    public void setBOQValidationHeaderTableBind(RichTable BOQValidationHeaderTableBind) {
        this.BOQValidationHeaderTableBind = BOQValidationHeaderTableBind;
    }

    public RichTable getBOQValidationHeaderTableBind() {
        return BOQValidationHeaderTableBind;
    }

    public void ProjectNumberVL(ValueChangeEvent valueChangeEvent) {
        processUpdates(valueChangeEvent);
        Object obj = executeMethod("createBOQProjectNumberVL");
        if (!"Success".equals(obj)) {
            showPopupMessage(obj == null ?
                             "Error while getting version for current project" :
                             obj.toString(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void ContractNumberVL(ValueChangeEvent valueChangeEvent) {
        processUpdates(valueChangeEvent);
        Object obj = executeMethod("createBOQContractNumberVL");
        if (!"Success".equals(obj)) {
            showPopupMessage(obj == null ?
                             "Error while getting version for current contract" :
                             obj.toString(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void openProjectAL(ActionEvent actionEvent) {
        HashMap jwtMap = (HashMap)getEL("#{sessionScope.pHeader}");
        if (jwtMap != null && jwtMap.containsKey("fusionEndpointURL")) {
            String fusionEndpointURL =
                jwtMap.get("fusionEndpointURL").toString();
            Object obj = getEL("#{bindings.ProjectId.inputValue}");
            FacesContext fctx = FacesContext.getCurrentInstance();
            ExtendedRenderKitService erks =
                Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
            StringBuilder script = new StringBuilder();
            if (obj != null) {
                script.append("window.open('" + fusionEndpointURL +
                              "/fscmUI/faces/deeplink?objType=PROJECT&objKey=ProjectId=" +
                              obj.toString() + "&action=OVERVIEW')");
                erks.addScript(FacesContext.getCurrentInstance(),
                               script.toString());
            }
        }
    }

    public void openProjectBudgetAL(ActionEvent actionEvent) {
        HashMap jwtMap = (HashMap)getEL("#{sessionScope.pHeader}");
        if (jwtMap != null && jwtMap.containsKey("fusionEndpointURL")) {
            String fusionEndpointURL =
                jwtMap.get("fusionEndpointURL").toString();
            Object obj = getEL("#{bindings.ProjectId.inputValue}");
            FacesContext fctx = FacesContext.getCurrentInstance();
            ExtendedRenderKitService erks =
                Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
            StringBuilder script = new StringBuilder();
            if (obj != null) {
                script.append("window.open('" + fusionEndpointURL +
                              "/fscmUI/faces/deeplink?objType=PROJECT&objKey=ProjectId=" +
                              obj.toString() + "&action=MANAGE_BUDGET')");
                erks.addScript(FacesContext.getCurrentInstance(),
                               script.toString());
            }
        }
    }
}
