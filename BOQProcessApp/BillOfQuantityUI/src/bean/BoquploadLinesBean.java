package bean;

import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStream;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpServletResponse;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputComboboxListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelLabelAndMessage;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.adf.view.rich.event.PopupCanceledEvent;

import oracle.binding.BindingContainer;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.BlobDomain;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class BoquploadLinesBean extends CommonBean {
    private RichPopup submitForApprPopupBindgs;
    private RichPopup revisionPopupBindgs;
    private RichPopup approvePopupBindgs;
    private RichPopup browseFilePopupBindgs;
    private RichPopup deleteBoqDocPopupBindgs;
    private RichPanelFormLayout browseFileFormBindgs;
    //    private RichInputText browseFileNameTextBindgs;
    private RichInputFile browseFileBindgs;
    private RichPanelGroupLayout uploadBoqLinesPageBindgs;
    private RichPopup loadBoqsPopupBindgs;
    private RichPopup rejectPopupBindgs;
    private RichOutputText transCanTotCostBudBindgs;
    private RichOutputText transTotCostBudBindgs;
    private RichOutputText transTotAbsErrPer;
    private RichPopup validRemarksBindgs;
    private Number resNetUseAmnt;
    private Number resFinalRate;
    private RichPopup attachmentFilesPopupBindgs;
    private RichInputFile attachFileBindgs;
    private RichOutputText fileCcreatedByBindgs;
    private RichInputDate fileCreationDateBindgs;
    private RichCommandLink fileNameBindgs;
    private RichCommandLink attachmentsColumnBind;
    private RichTable validationCostCodeTableBind;
    private RichInputText costCodeQtyBind;
    private RichInputText costCodeRateBind;
    private RichOutputText costCodeAmountBind;
    private RichTable validationLinesTableBind;
    private RichOutputText costBudgetTotalBind;
    private RichSelectBooleanCheckbox approvedBind;
    private RichSelectBooleanCheckbox submittedBind;
    private RichSelectBooleanCheckbox estimatedBind;
    private RichPopup costCodeConfirmationPopupBind;
    private RichInputComboboxListOfValues costCodeBind;
    private RichInputText anticipatedValueBind;
    private RichPopup cancelBoqDocBind;
    private RichInputDate ledgerCurrencyConvDateBind;
    private RichSelectOneChoice ledgerCurrencyConvRateTypeBind;


    public BoquploadLinesBean() {
        super();
    }

    public void actHistAL(ActionEvent actionEvent) {
        // Add event code here...
    }

    public void saveBoqLinesAL(ActionEvent actionEvent) {
        try {
            save();
            //            executeMethod("refreshBoqValidateHdrVO");
            Object validationId =
                getEL("#{bindings.ValidationHeaderId.inputValue}");
            if (validationId != null) {
                getCurrentPageFlowScope().put("boqValHdrId", validationId);
                Object isSettedObj =
                    executeMethod("makeAsCurrentBoqValHdrRow");
                if (isSettedObj != null && isSettedObj instanceof Boolean) {
                    Boolean isSetted = (Boolean)isSettedObj;
                    if (isSetted) {
                        navigate(actionEvent, "BoqUploadLines");
                    }
                }
            }
            addPartialTrigger(getAttachmentsColumnBind());
        } catch (Exception e) {
        }
    }

    public void saveAndCloseBoqLinesAl(ActionEvent actionEvent) {
        try {
            save();
            executeMethod("refreshBoqValidateHdrVO");
            navigate(actionEvent, "done");
        } catch (Exception e) {
        }
    }

    public void setSubmitForApprPopupBindgs(RichPopup submitForApprPopupBindgs) {
        this.submitForApprPopupBindgs = submitForApprPopupBindgs;
    }

    public RichPopup getSubmitForApprPopupBindgs() {
        return submitForApprPopupBindgs;
    }

    public void submitForApprPopupDL(DialogEvent dialogEvent) {
        try {
            Object hdrIdObj =
                getEL("#{bindings.ValidationHeaderId.inputValue}");
            getCurrentPageFlowScope().put("headerId", hdrIdObj);
            Object isExistObj = executeMethod("isErrorExistsInUploaboqs");
            if (isExistObj != null && isExistObj instanceof Boolean) {
                Boolean isExists = (Boolean)isExistObj;
                if (!isExists) {
                    this.getCurrentPageFlowScope().put("updateWith", "I");
                    Object exeMessObj =
                        executeMethod("updateValidationDocumentAs");
                    if (exeMessObj != null && exeMessObj instanceof Boolean) {
                        Boolean isUpdated = (Boolean)exeMessObj;
                        save();
                        if (!isUpdated) {
                            showPopupMessage("Unable to submit the document for approval.",
                                             FacesMessage.SEVERITY_ERROR);
                        }
                    } else {
                        showPopupMessage("Unable to submit the document.",
                                         FacesMessage.SEVERITY_ERROR);
                    }
                } else {
                    showPopupMessage("You cannot submit document when errors exist. Please correct the errors and resubmit for approval.",
                                     FacesMessage.SEVERITY_ERROR);
                }
            } else {
                showPopupMessage("Checking for errors existance is failed. Unable to submit for approval",
                                 FacesMessage.SEVERITY_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        addPartialTrigger(getUploadBoqLinesPageBindgs());
    }

    public void setRevisionPopupBindgs(RichPopup revisionPopupBindgs) {
        this.revisionPopupBindgs = revisionPopupBindgs;
    }

    public RichPopup getRevisionPopupBindgs() {
        return revisionPopupBindgs;
    }

    public void revisionPopupDL(DialogEvent dialogEvent) {
        // Add event code here...
    }

    public void setApprovePopupBindgs(RichPopup approvePopupBindgs) {
        this.approvePopupBindgs = approvePopupBindgs;
    }

    public RichPopup getApprovePopupBindgs() {
        return approvePopupBindgs;
    }

    public void approvePopupDL(DialogEvent dialogEvent) {
        try {
            Object hdrIdObj =
                getEL("#{bindings.ValidationHeaderId.inputValue}");
            getCurrentPageFlowScope().put("headerId", hdrIdObj);
            Object isExistObj = executeMethod("isErrorExistsInUploaboqs");
            if (isExistObj != null && isExistObj instanceof Boolean) {
                Boolean isExists = (Boolean)isExistObj;
                if (!isExists) {
                    this.getCurrentPageFlowScope().put("updateWith", "A");
                    Object exeMessObj =
                        executeMethod("updateValidationDocumentAs");
                    if (exeMessObj != null && exeMessObj instanceof Boolean) {
                        Boolean isUpdated = (Boolean)exeMessObj;
                        save();
                        if (!isUpdated) {
                            showPopupMessage("Unable to approve the document.",
                                             FacesMessage.SEVERITY_ERROR);
                        }
                    } else {
                        showPopupMessage("Unable to submit the document for approval.",
                                         FacesMessage.SEVERITY_ERROR);
                    }
                } else {
                    showPopupMessage("You cannot submit document when errors exist. Please correct the errors and resubmit for approval.",
                                     FacesMessage.SEVERITY_ERROR);
                }
            } else {
                showPopupMessage("Checking for errors existance is failed. Unable to submit for approval",
                                 FacesMessage.SEVERITY_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        addPartialTrigger(getUploadBoqLinesPageBindgs());
    }

    public void setBrowseFilePopupBindgs(RichPopup browseFilePopupBindgs) {
        this.browseFilePopupBindgs = browseFilePopupBindgs;
    }

    public RichPopup getBrowseFilePopupBindgs() {
        return browseFilePopupBindgs;
    }

    public void setBrowseFileFormBindgs(RichPanelFormLayout browseFileFormBindgs) {
        this.browseFileFormBindgs = browseFileFormBindgs;
    }

    public RichPanelFormLayout getBrowseFileFormBindgs() {
        return browseFileFormBindgs;
    }

    //    public void setBrowseFileNameTextBindgs(RichInputText browseFileNameTextBindgs) {
    //        this.browseFileNameTextBindgs = browseFileNameTextBindgs;
    //    }
    //
    //    public RichInputText getBrowseFileNameTextBindgs() {
    //        return browseFileNameTextBindgs;
    //    }

    public void setBrowseFileBindgs(RichInputFile browseFileBindgs) {
        this.browseFileBindgs = browseFileBindgs;
    }

    public RichInputFile getBrowseFileBindgs() {
        return browseFileBindgs;
    }

    private String getFileExtension(String file) {
        String extension = null;
        if (file != null) {
            String[] fileSplitArr = file.split("\\.");
            if (fileSplitArr != null && fileSplitArr.length == 2) {
                extension = fileSplitArr[1];
            }
        }
        return extension;
    }

    public void browseFileVL(ValueChangeEvent valueChangeEvent) {
        try {
            UploadedFile uploadedFile =
                (UploadedFile)valueChangeEvent.getNewValue();
            getCurrentPageFlowScope().put("uploadedFile", uploadedFile);
            if (uploadedFile != null) {
                String fileName = uploadedFile.getFilename();
                //                getBrowseFileNameTextBindgs().setValue(fileName);
                //                addPartialTrigger(getBrowseFileNameTextBindgs());
                String fileExt = getFileExtension(fileName);
                if (fileExt != null &&
                    "xlsx".toUpperCase().equals(fileExt.toUpperCase())) {

                    Object exeMess = executeMethod("clearUploadedData");
                    if (exeMess != null) {
                        if (("Success".equals(exeMess.toString()))) {
//                            save();
                            Object hdrIdObj =
                                getEL("#{bindings.ValidationHeaderId.inputValue}");
                            getCurrentPageFlowScope().put("boqValHdrId", hdrIdObj);
                            Object isSettedObj =
                                executeMethod("makeAsCurrentBoqValHdrRow");
                            executeMethod("refreshUploadInterfaceVO");
                            Object messageObj = executeMethod("uploadBoqs");
                            if (messageObj != null &&
                                "Success".equals(messageObj)) {
                                save();
                                getCurrentPageFlowScope().put("boqValHdrId", hdrIdObj);
                                isSettedObj =
                                    executeMethod("makeAsCurrentBoqValHdrRow");
                            } else {
                                showPopupMessage("Import failed."+messageObj.toString(),
                                                 FacesMessage.SEVERITY_ERROR);
                            }
                        } else {
                            showPopupMessage(exeMess.toString(),
                                             FacesMessage.SEVERITY_ERROR);
                        }
                    } else {
                        showPopupMessage("Import failed due to interface errors.  ",
                                         FacesMessage.SEVERITY_ERROR);
                    }
                } else {
                    showPopupMessage("Invalid file format. Please import excel (xlsx) file.",
                                     FacesMessage.SEVERITY_ERROR);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void browseFilePopupCL(PopupCanceledEvent popupCanceledEvent) {
        if (getBrowseFilePopupBindgs() != null) {
            oracle.adf.view.rich.util.ResetUtils.reset(getBrowseFilePopupBindgs());
            //            getBrowseFileNameTextBindgs().setValue(null);
            getBrowseFilePopupBindgs().hide();
        }
    }

    public void uploadBoqFileAL(ActionEvent actionEvent) {
        if (getCurrentPageFlowScope().containsKey("uploadedFile")) {
            try {
                Object clearValidatedDataObj =
                    executeMethod("clearValidatedData");
                if (clearValidatedDataObj != null) {
                    if ("Success".equals(clearValidatedDataObj)) {
                        Object spilitDataExeMess =
                            executeMethod("splitUploadedData");
                        Object currValHdrId =
                            getEL("#{bindings.ValidationHeaderId.inputValue}");
                        if (spilitDataExeMess != null) {
                            if ("Success".equals(spilitDataExeMess)) {
                                save();
                                executeMethod("refreshBoqValidateHdrVO");
                                getCurrentPageFlowScope().put("boqValHdrId",
                                                              currValHdrId);
                                Object exeMessObj =
                                    executeMethod("makeAsCurrentBoqValHdrRow");
                                if (exeMessObj != null &&
                                    exeMessObj instanceof Boolean) {
                                    Boolean isSetted = (Boolean)exeMessObj;
                                    if (isSetted) {
                                        getCurrentPageFlowScope().put("imported",
                                                                      "yes");
                                        validateBoqAL(actionEvent);

                                    } else {
                                        showPopupMessage("Import successful. Please refresh the page.",
                                                         FacesMessage.SEVERITY_WARN);
                                    }
                                } else {
                                    showPopupMessage("Import successful. Please refresh the page.",
                                                     FacesMessage.SEVERITY_WARN);
                                }

                            } else {
                                save();
                                showPopupMessage(spilitDataExeMess.toString(),
                                                 FacesMessage.SEVERITY_ERROR);
                            }
                        }
                    } else {
                        showPopupMessage(clearValidatedDataObj.toString(),
                                         FacesMessage.SEVERITY_ERROR);
                    }
                } else {
                    showPopupMessage("Import failed due to deleting interface lines.",
                                     FacesMessage.SEVERITY_ERROR);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            addPartialTrigger(getUploadBoqLinesPageBindgs());
            //        addPartialTrigger(getTransCanTotCostBudBindgs());
            addPartialTrigger(getTransTotCostBudBindgs());
            addPartialTrigger(getTransTotAbsErrPer());
        } else {
            showPopupMessage("Please select a file to import",
                             FacesMessage.SEVERITY_ERROR);
        }
    }

    public void setUploadBoqLinesPageBindgs(RichPanelGroupLayout uploadBoqLinesPageBindgs) {
        this.uploadBoqLinesPageBindgs = uploadBoqLinesPageBindgs;
    }

    public RichPanelGroupLayout getUploadBoqLinesPageBindgs() {
        return uploadBoqLinesPageBindgs;
    }

    public void validateBoqAL(ActionEvent actionEvent) {
        try {
            save();
            Object exeMessage = executeMethod("validateUploadedBoqs");
            Object currValHdrId =
                getEL("#{bindings.ValidationHeaderId.inputValue}");
            if (exeMessage != null) {
//                    executeMethod("refreshBoqValidateHdrVO");
                    getCurrentPageFlowScope().put("boqValHdrId", currValHdrId);
                    Object exeMessObj =
                        executeMethod("makeAsCurrentBoqValHdrRow");
                    if (exeMessObj != null && exeMessObj instanceof Boolean) {
                        Boolean isSetted = (Boolean)exeMessObj;
                        if (isSetted) {
                            addPartialTrigger(getUploadBoqLinesPageBindgs());
                            getBrowseFilePopupBindgs().hide();
                            getCurrentPageFlowScope().put("validationHdrId",
                                                          currValHdrId);

                            //                        Object errCountObj = //commented by Sumanth
                            //                            executeMethod("getErrorsCountForCurrentDoc");
                            //                        if (errCountObj != null &&
                            //                            errCountObj instanceof Long) {
                            //                            Long errCount = (Long)errCountObj;
                            //                            if (errCount == 0) {
                            //                                getCurrentPageFlowScope().put("updateWith",
                            //                                                              "Valid");
                            //                                Object updateMessObj =
                            //                                    executeMethod("updateBoqUploadedDocWith");
                            //                                if (updateMessObj != null &&
                            //                                    updateMessObj instanceof Boolean) {
                            //                                    Boolean isUpdated = (Boolean)updateMessObj;
                            //                                    save();
                            //                                    if (isUpdated) {
                            if (!"Success".equals(exeMessage)) {
                                showPopupMessage(exeMessage.toString(),
                                                 FacesMessage.SEVERITY_ERROR);
                            }
                            else{
                                showPopupMessage(getCurrentPageFlowScope().containsKey("import") ?
                                             "Imported and Validated successfully" :
                                             "Validated successfully.",
                                             FacesMessage.SEVERITY_INFO);
                            }
                            //                                    }
                            //                                }
                            //                            } else {
                            //                                navigate(actionEvent, "UploadRemarks");
                            //                            }
                            //                        }

                        } else {
                            showPopupMessage("Validated successfully. Please refresh the page.",
                                             FacesMessage.SEVERITY_WARN);
                        }
                    } else {
                        showPopupMessage("Validated successfully. Please refresh the page.",
                                         FacesMessage.SEVERITY_WARN);
                    }

                    addPartialTrigger(getUploadBoqLinesPageBindgs());
                    //                    }


                    //
            }
            addPartialTrigger(getUploadBoqLinesPageBindgs());
            //        addPartialTrigger(getTransCanTotCostBudBindgs());
            addPartialTrigger(getTransTotCostBudBindgs());
            addPartialTrigger(getTransTotAbsErrPer());
        } catch (Exception e) {
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void setLoadBoqsPopupBindgs(RichPopup loadBoqsPopupBindgs) {
        this.loadBoqsPopupBindgs = loadBoqsPopupBindgs;
    }

    public RichPopup getLoadBoqsPopupBindgs() {
        return loadBoqsPopupBindgs;
    }

    public void loadBoqsPopupCL(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.yes) {
            Object exeMessage = executeMethod("loadUploadedBoqs");
            if (exeMessage != null) {
                if ("Success".equals(exeMessage)) {
                    save();
                    showPopupMessage("Document Set to Active.",
                                     FacesMessage.SEVERITY_INFO);
                } else {
                    showPopupMessage(exeMessage.toString(),
                                     FacesMessage.SEVERITY_ERROR);
                }
            }
            addPartialTrigger(getUploadBoqLinesPageBindgs());
        }
    }


    public void cancelUploadedBoqDocAL(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.yes) {
            showPopup(getCancelBoqDocBind());
        }
    }

    public void deleteBoqsPopupAL2(DialogEvent dialogEvent) {
        //        Object obj = getEL("#{bindings.BaseLineValidationHdrId.inputValue}"); //Commented to remove the validation i.e. draft document can't be deleted when it is base lined.
        //        if (obj !=null){
        //            showPopupMessage("You cannot delete documents for which contract is baselined", FacesMessage.SEVERITY_ERROR);
        //        }else{
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.yes) {
            Object status = getEL("#{bindings.Status.inputValue}");
            if (status != null && "W".equals(status)) {
                showPopupMessage("You cannot delete documents that were previously submitted for approval. Cancel the document instead.",
                                 FacesMessage.SEVERITY_ERROR);
            } else if (status != null && "R".equals(status)) {
                showPopupMessage("You cannot delete documents that were previously submitted for approval. Cancel the document instead.",
                                 FacesMessage.SEVERITY_ERROR);
            } else {
                Object exeMessage = executeMethod("deleteUploadedBOQDoc");
                if (exeMessage != null) {
                    if ("Success".equals(exeMessage)) {
                        save();
                        navigate(dialogEvent, "done");
                    } else {
                        showPopupMessage(exeMessage.toString(),
                                         FacesMessage.SEVERITY_ERROR);
                    }
                }
            }
        }
        //        }
    }

    public void setRejectPopupBindgs(RichPopup rejectPopupBindgs) {
        this.rejectPopupBindgs = rejectPopupBindgs;
    }

    public RichPopup getRejectPopupBindgs() {
        return rejectPopupBindgs;
    }

    public void rejectPopupDL(DialogEvent dialogEvent) {
        this.getCurrentPageFlowScope().put("updateWith", "R");
        Object exeMessObj = executeMethod("updateValidationDocumentAs");
        if (exeMessObj != null && exeMessObj instanceof Boolean) {
            Boolean isUpdated = (Boolean)exeMessObj;
            save();
            if (!isUpdated) {
                showPopupMessage("Unable to change the document status.",
                                 FacesMessage.SEVERITY_ERROR);
            }
        } else {
            showPopupMessage("Unable to reject the document.",
                             FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getUploadBoqLinesPageBindgs());
    }


    public void setTransCanTotCostBudBindgs(RichOutputText transCanTotCostBudBindgs) {
        this.transCanTotCostBudBindgs = transCanTotCostBudBindgs;
    }

    public RichOutputText getTransCanTotCostBudBindgs() {
        return transCanTotCostBudBindgs;
    }

    public void setTransTotCostBudBindgs(RichOutputText transTotCostBudBindgs) {
        this.transTotCostBudBindgs = transTotCostBudBindgs;
    }

    public RichOutputText getTransTotCostBudBindgs() {
        return transTotCostBudBindgs;
    }

    public void setTransTotAbsErrPer(RichOutputText transTotAbsErrPer) {
        this.transTotAbsErrPer = transTotAbsErrPer;
    }

    public RichOutputText getTransTotAbsErrPer() {
        return transTotAbsErrPer;
    }


    public void validRemarksAL(ActionEvent actionEvent) {
        Object executeMethod = executeMethod("refreshUploadErrorsVO");
        //        showPopup(getValidRemarksBindgs());

        navigate(actionEvent, "UploadRemarks");
    }

    public void setValidRemarksBindgs(RichPopup validRemarksBindgs) {
        this.validRemarksBindgs = validRemarksBindgs;
    }

    public RichPopup getValidRemarksBindgs() {
        return validRemarksBindgs;
    }

    public void updateBoqFileAL(ActionEvent actionEvent) {
        Object spilitDataExeMess = executeMethod("updateUploadedData");
        Object currValHdrId =
            getEL("#{bindings.ValidationHeaderId.inputValue}");
        if (spilitDataExeMess != null) {
            if ("Success".equals(spilitDataExeMess)) {
                save();
                executeMethod("refreshBoqValidateHdrVO");
                getCurrentPageFlowScope().put("boqValHdrId", currValHdrId);
                Object exeMessObj = executeMethod("makeAsCurrentBoqValHdrRow");
                if (exeMessObj != null && exeMessObj instanceof Boolean) {
                    Boolean isSetted = (Boolean)exeMessObj;
                    if (isSetted) {
                        validateBoqAL(actionEvent);
                        //                        getCurrentPageFlowScope().put("updateWith",
                        //                                                      "In Valid");
                        //                        executeMethod("updateBoqUploadedDocWith");
                        //                        save();
                        //                        addPartialTrigger(getUploadBoqLinesPageBindgs());
                        //                        getBrowseFilePopupBindgs().hide();
                        //                        showPopupMessage("Boq  successfully uploaded.  ",
                        //                                         FacesMessage.SEVERITY_INFO);
                    } else {
                        showPopupMessage("Update successful. Please refresh the page.",
                                         FacesMessage.SEVERITY_WARN);
                    }
                } else {
                    showPopupMessage("Update successful. Please refresh the page.",
                                     FacesMessage.SEVERITY_WARN);
                }

            } else {
                save();
                showPopupMessage(spilitDataExeMess.toString(),
                                 FacesMessage.SEVERITY_ERROR);
            }
        }
        addPartialTrigger(getUploadBoqLinesPageBindgs());
        addPartialTrigger(getTransCanTotCostBudBindgs());
        addPartialTrigger(getTransTotCostBudBindgs());
        addPartialTrigger(getTransTotAbsErrPer());
    }

    public void downloadUploladErrrors(FacesContext facesContext,
                                       OutputStream outputStream) {
        byte[] report = (byte[])executeMethod("getUploadedErrors");
        try {
            outputStream.write(report);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void downloadUploladBoqTemplate(FacesContext facesContext,
                                           OutputStream outputStream) {
        byte[] report = (byte[])executeMethod("getUploadBoqTemplate");
        try {
            outputStream.write(report);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendBoqFileAL(ActionEvent actionEvent) {
        Object spilitDataExeMess = executeMethod("appendUploadedData");
        Object currValHdrId =
            getEL("#{bindings.ValidationHeaderId.inputValue}");
        if (spilitDataExeMess != null) {
            if ("Success".equals(spilitDataExeMess)) {
                save();

                executeMethod("refreshBoqValidateHdrVO");
                getCurrentPageFlowScope().put("boqValHdrId", currValHdrId);
                Object exeMessObj = executeMethod("makeAsCurrentBoqValHdrRow");
                if (exeMessObj != null && exeMessObj instanceof Boolean) {
                    Boolean isSetted = (Boolean)exeMessObj;
                    if (isSetted) {
                        validateBoqAL(actionEvent);
                        //                        getCurrentPageFlowScope().put("updateWith",
                        //                                                      "In Valid");
                        //                        executeMethod("updateBoqUploadedDocWith");
                        //                        save();
                        //                        addPartialTrigger(getUploadBoqLinesPageBindgs());
                        //                        getBrowseFilePopupBindgs().hide();
                        //                        showPopupMessage("Boq  successfully uploaded.  ",
                        //                                         FacesMessage.SEVERITY_INFO);
                    } else {
                        showPopupMessage("Append successful. Please refresh the page.",
                                         FacesMessage.SEVERITY_WARN);
                    }
                } else {
                    showPopupMessage("Append successful. Please refresh the page.",
                                     FacesMessage.SEVERITY_WARN);
                }

            } else {
                save();
                showPopupMessage(spilitDataExeMess.toString(),
                                 FacesMessage.SEVERITY_ERROR);
            }
        }
        addPartialTrigger(getUploadBoqLinesPageBindgs());
        addPartialTrigger(getTransCanTotCostBudBindgs());
        addPartialTrigger(getTransTotCostBudBindgs());
        addPartialTrigger(getTransTotAbsErrPer());
    }

    public void setResNetUseAmnt(Number resNetUseAmnt) {
        this.resNetUseAmnt = resNetUseAmnt;
    }

    public Number getResNetUseAmnt() {
        return resNetUseAmnt;
    }

    public void setResFinalRate(Number resFinalRate) {
        this.resFinalRate = resFinalRate;
    }

    public Number getResFinalRate() {
        return resFinalRate;
    }

    public void submitForApprlPopupAL(ActionEvent actionEvent) {
        try {
//            Object obj = executeMethod("isPriceCodesWithoutCostCodesExists");
//            if ("Success".equals(obj)) {
                Object exeMess =
                    executeMethod("callpkgForAMEBOQUploadDocSubmit");
                if ("Success".equals(exeMess)) {
                    save();
                    showPopupMessage("The document was submitted for approval.",
                                     FacesMessage.SEVERITY_INFO);
                }
//            } else {
//                getCurrentPageFlowScope().put("CostCodeConfirmation", obj);
//                showPopup(getCostCodeConfirmationPopupBind());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        addPartialTrigger(getUploadBoqLinesPageBindgs());
    }

    public void submitDocForApprAL(ActionEvent actionEvent) {
        try {
            save();
            Object obj1 = getEL("#{bindings.TransCustomerName.inputValue}");
            if (obj1 != null) {
                Object exeMess = executeMethod("submitBOQUploadDoc");
                if ("Success".equals(exeMess)) {
                    Object hdrIdObj =
                        getEL("#{bindings.ValidationHeaderId.inputValue}");
                    executeMethod("refreshBoqValidateHdrVO");
                    getCurrentPageFlowScope().put("boqValHdrId", hdrIdObj);
                    Object isSettedObj =
                        executeMethod("makeAsCurrentBoqValHdrRow");
                    getCurrentPageFlowScope().put("headerId", hdrIdObj);
                    Object isExistObj =
                        executeMethod("isErrorExistsInUploaboqs");
                    if (isExistObj != null && isExistObj instanceof Boolean) {
                        Boolean isExists = (Boolean)isExistObj;
                        if (!isExists) {
                            Object obj = executeMethod("isRejectionsExists");
                            if ("Exists".equals(obj)) {
                                getCurrentPageFlowScope().put("isRejectionsExists",
                                                              true);
                            } else {
                                getCurrentPageFlowScope().put("isRejectionsExists",
                                                              false);
                            }
                            showPopup(getSubmitForApprPopupBindgs());
                        } else {
                            showPopupMessage("You cannot submit document when errors exist. Please correct the errors and resubmit for approval.",
                                             FacesMessage.SEVERITY_ERROR);
                        }
                    } else {
                        showPopupMessage("Checking for errors existance failed. Unable to submit for approval.",
                                         FacesMessage.SEVERITY_ERROR);
                    }
                } else {
                    showPopupMessage(exeMess != null ? exeMess.toString() :
                                     "Unable to submit the document for approval.",
                                     FacesMessage.SEVERITY_ERROR);
                }
            } else {
                showPopupMessage("Unable to submit for approval. Associate project with contract.",
                                 FacesMessage.SEVERITY_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        addPartialTrigger(getUploadBoqLinesPageBindgs());
    }

    public void attachmemntFilesPopupCL(PopupCanceledEvent popupCanceledEvent) {
        // Add event code here...
    }

    public void setAttachmentFilesPopupBindgs(RichPopup attachmentFilesPopupBindgs) {
        this.attachmentFilesPopupBindgs = attachmentFilesPopupBindgs;
    }

    public RichPopup getAttachmentFilesPopupBindgs() {
        return attachmentFilesPopupBindgs;
    }

    public void attachFileVL(ValueChangeEvent valueChangeEvent) {

        try {
            valueChangeEvent.getComponent().processDecodes(FacesContext.getCurrentInstance());
            Object fileObj = valueChangeEvent.getNewValue();
            if (fileObj != null) {
                UploadedFile file = (UploadedFile)fileObj;
                this.getCurrentPageFlowScope().put("attachedFile", file);
                Object exeMess = executeMethod("saveAttachedFile");
                if (!"Success".equals(exeMess)) {
                    showPopupMessage(exeMess != null ? exeMess.toString() :
                                     "Unable to add the file",
                                     FacesMessage.SEVERITY_ERROR);
                }
            }
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
            // TODO:
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getFileNameBindgs());
        addPartialTrigger(getFileCcreatedByBindgs());
        addPartialTrigger(getFileCreationDateBindgs());
    }

    public void setAttachFileBindgs(RichInputFile attachFileBindgs) {
        this.attachFileBindgs = attachFileBindgs;
    }

    public RichInputFile getAttachFileBindgs() {
        return attachFileBindgs;
    }

    public void cancelAL(ActionEvent actionEvent) {
        //        cancel();
    }


    public void setFileCcreatedByBindgs(RichOutputText fileCcreatedByBindgs) {
        this.fileCcreatedByBindgs = fileCcreatedByBindgs;
    }

    public RichOutputText getFileCcreatedByBindgs() {
        return fileCcreatedByBindgs;
    }

    public void setFileCreationDateBindgs(RichInputDate fileCreationDateBindgs) {
        this.fileCreationDateBindgs = fileCreationDateBindgs;
    }

    public RichInputDate getFileCreationDateBindgs() {
        return fileCreationDateBindgs;
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


    public void setAttachmentsColumnBind(RichCommandLink attachmentsColumnBind) {
        this.attachmentsColumnBind = attachmentsColumnBind;
    }

    public RichCommandLink getAttachmentsColumnBind() {
        return attachmentsColumnBind;
    }

    public void setValidationCostCodeTableBind(RichTable validationCostCodeTableBind) {
        this.validationCostCodeTableBind = validationCostCodeTableBind;
    }

    public RichTable getValidationCostCodeTableBind() {
        return validationCostCodeTableBind;
    }

    public void setCostCodeQtyBind(RichInputText costCodeQtyBind) {
        this.costCodeQtyBind = costCodeQtyBind;
    }

    public RichInputText getCostCodeQtyBind() {
        return costCodeQtyBind;
    }

    public void setCostCodeRateBind(RichInputText costCodeRateBind) {
        this.costCodeRateBind = costCodeRateBind;
    }

    public RichInputText getCostCodeRateBind() {
        return costCodeRateBind;
    }

    public void setCostCodeAmountBind(RichOutputText costCodeAmountBind) {
        this.costCodeAmountBind = costCodeAmountBind;
    }

    public RichOutputText getCostCodeAmountBind() {
        return costCodeAmountBind;
    }

    public void costCodeQtyVL(ValueChangeEvent valueChangeEvent) {
        processUpdates(valueChangeEvent);
        getCostCodeQtyBind().setValue(((BigDecimal)valueChangeEvent.getNewValue()).setScale(10,
                                                                                            BigDecimal.ROUND_HALF_UP));
        //        Object qty = valueChangeEvent.getNewValue();
        //        Object rate = getCostCodeRateBind().getValue();
        //        if(qty!= null && rate != null){
        //            getCostCodeAmountBind().setValue(((BigDecimal)qty).multiply(((BigDecimal)rate)));
        //        }
        //        FacesContext fc = FacesContext.getCurrentInstance();
        //        String refreshpage = fc.getViewRoot().getViewId();
        //        ViewHandler ViewH = fc.getApplication().getViewHandler();
        //        UIViewRoot UIV = ViewH.createView(fc,refreshpage);
        //        UIV.setViewId(refreshpage);
        //        fc.setViewRoot(UIV);
        addPartialTrigger(getCostCodeQtyBind());
        addPartialTrigger(getCostCodeAmountBind());
        addPartialTrigger(getCostBudgetTotalBind());
    }

    public void costCodeRateVL(ValueChangeEvent valueChangeEvent) {
        processUpdates(valueChangeEvent);
        getCostCodeRateBind().setValue(((BigDecimal)valueChangeEvent.getNewValue()).setScale(10,
                                                                                             BigDecimal.ROUND_HALF_UP));
        //        Object rate = valueChangeEvent.getNewValue();
        //        Object qty = getCostCodeQtyBind().getValue();
        //        if(qty!= null && rate != null){
        //            getCostCodeAmountBind().setValue(((BigDecimal)qty).multiply(((BigDecimal)rate)));
        //        }
        //        FacesContext fc = FacesContext.getCurrentInstance();
        //        String refreshpage = fc.getViewRoot().getViewId();
        //        ViewHandler ViewH = fc.getApplication().getViewHandler();
        //        UIViewRoot UIV = ViewH.createView(fc,refreshpage);
        //        UIV.setViewId(refreshpage);
        //        fc.setViewRoot(UIV);
        addPartialTrigger(getCostCodeRateBind());
        addPartialTrigger(getCostCodeAmountBind());
        addPartialTrigger(getCostBudgetTotalBind());
    }

    public void setValidationLinesTableBind(RichTable validationLinesTableBind) {
        this.validationLinesTableBind = validationLinesTableBind;
    }

    public RichTable getValidationLinesTableBind() {
        return validationLinesTableBind;
    }

    public void setCostBudgetTotalBind(RichOutputText costBudgetTotalBind) {
        this.costBudgetTotalBind = costBudgetTotalBind;
    }

    public RichOutputText getCostBudgetTotalBind() {
        return costBudgetTotalBind;
    }

    public void addCostCode(ActionEvent actionEvent) {
        executeMethod("CreateInsert1");
    }

    public void deleteCostCode(ActionEvent actionEvent) {
        executeMethod("Delete1");
    }

    public void cancel(ActionEvent actionEvent) {
        cancel();
    }

    public void approvedVL(ValueChangeEvent valueChangeEvent) {
        processUpdates(valueChangeEvent);
        getCurrentPageFlowScope().put("variationFlag", "A");
        Object message = executeMethod("validateVariationLines");
        if ("Success".equals(message)) {
            addPartialTrigger(getAnticipatedValueBind());
            addPartialTrigger(getSubmittedBind());
            addPartialTrigger(getEstimatedBind());
            addPartialTrigger(getValidationCostCodeTableBind());
        } else if (message != null) {
            getApprovedBind().setValue(valueChangeEvent.getOldValue());
            showPopupMessage(message.toString(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getApprovedBind());
    }

    public void submittedVL(ValueChangeEvent valueChangeEvent) {
        processUpdates(valueChangeEvent);
        getCurrentPageFlowScope().put("variationFlag", "S");
        Object message = executeMethod("validateVariationLines");
        if ("Success".equals(message)) {
            addPartialTrigger(getAnticipatedValueBind());
            addPartialTrigger(getApprovedBind());
            addPartialTrigger(getEstimatedBind());
            addPartialTrigger(getValidationCostCodeTableBind());
        } else if (message != null) {
            getSubmittedBind().setValue(valueChangeEvent.getOldValue());
            showPopupMessage(message.toString(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getSubmittedBind());
    }

    public void estimatedVL(ValueChangeEvent valueChangeEvent) {
        processUpdates(valueChangeEvent);
        getCurrentPageFlowScope().put("variationFlag", "E");
        Object message = executeMethod("validateVariationLines");
        if ("Success".equals(message)) {
            addPartialTrigger(getAnticipatedValueBind());
            addPartialTrigger(getApprovedBind());
            addPartialTrigger(getSubmittedBind());
            addPartialTrigger(getValidationCostCodeTableBind());
        } else if (message != null) {
            getEstimatedBind().setValue(valueChangeEvent.getOldValue());
            showPopupMessage(message.toString(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getEstimatedBind());
    }

    public void setApprovedBind(RichSelectBooleanCheckbox approvedBind) {
        this.approvedBind = approvedBind;
    }

    public RichSelectBooleanCheckbox getApprovedBind() {
        return approvedBind;
    }

    public void setSubmittedBind(RichSelectBooleanCheckbox submittedBind) {
        this.submittedBind = submittedBind;
    }

    public RichSelectBooleanCheckbox getSubmittedBind() {
        return submittedBind;
    }

    public void setEstimatedBind(RichSelectBooleanCheckbox estimatedBind) {
        this.estimatedBind = estimatedBind;
    }

    public RichSelectBooleanCheckbox getEstimatedBind() {
        return estimatedBind;
    }

    public void setDeleteBoqDocPopupBindgs(RichPopup deleteBoqDocPopupBindgs) {
        this.deleteBoqDocPopupBindgs = deleteBoqDocPopupBindgs;
    }

    public RichPopup getDeleteBoqDocPopupBindgs() {
        return deleteBoqDocPopupBindgs;
    }

    public void deleteBOQLine(ActionEvent actionEvent) {
        Object message = executeMethod("deleteBOQLine");
        if (message != null && "Success".equals(message)) {
            addPartialTrigger(getValidationLinesTableBind());
        } else {
            showPopupMessage(message.toString(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void setCostCodeConfirmationPopupBind(RichPopup costCodeConfirmationPopupBind) {
        this.costCodeConfirmationPopupBind = costCodeConfirmationPopupBind;
    }

    public RichPopup getCostCodeConfirmationPopupBind() {
        return costCodeConfirmationPopupBind;
    }

    public void CostCodeConfirmationYes(ActionEvent actionEvent) {
        Object exeMess = executeMethod("callpkgForAMEBOQUploadDocSubmit");
        if ("Success".equals(exeMess)) {
            save();
            showPopupMessage("The document was submitted for approval.",
                             FacesMessage.SEVERITY_INFO);
        }
        getCostCodeConfirmationPopupBind().hide();
        addPartialTrigger(getUploadBoqLinesPageBindgs());
    }

    public void CostCodeConfirmationNo(ActionEvent actionEvent) {
        getCostCodeConfirmationPopupBind().hide();
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

    public void costCodeVL(ValueChangeEvent valueChangeEvent) {
        processUpdates(valueChangeEvent);
        getCurrentPageFlowScope().put("CurrentCostCode",
                                      valueChangeEvent.getNewValue());
        Object obj = executeMethod("validateCostCode");
        if (obj != null && (!"Success".equals(obj))) {
            getCostCodeBind().setValue(valueChangeEvent.getOldValue());
            showPopupMessage(obj.toString(), FacesMessage.SEVERITY_ERROR);
            getCostCodeBind().setValue(valueChangeEvent.getOldValue());
        }
        addPartialTrigger(getCostCodeBind());
    }

    public void setCostCodeBind(RichInputComboboxListOfValues costCodeBind) {
        this.costCodeBind = costCodeBind;
    }

    public RichInputComboboxListOfValues getCostCodeBind() {
        return costCodeBind;
    }

    public void setAnticipatedValueBind(RichInputText anticipatedValueBind) {
        this.anticipatedValueBind = anticipatedValueBind;
    }

    public RichInputText getAnticipatedValueBind() {
        return anticipatedValueBind;
    }

    public void cancelBoqDocAL(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
            Object exeMessage = executeMethod("cancelUploadedBoqs");
            if (exeMessage != null) {
                if ("Success".equals(exeMessage)) {
                    showPopupMessage("Your changes were saved.",
                                     FacesMessage.SEVERITY_INFO);
                } else {
                    showPopupMessage(exeMessage.toString(),
                                     FacesMessage.SEVERITY_ERROR);
                }
            }
            addPartialTrigger(getUploadBoqLinesPageBindgs());
        }
    }

    public void setCancelBoqDocBind(RichPopup cancelBoqDocBind) {
        this.cancelBoqDocBind = cancelBoqDocBind;
    }

    public RichPopup getCancelBoqDocBind() {
        return cancelBoqDocBind;
    }

    public void LedgerCurrencyConvDateVL(ValueChangeEvent valueChangeEvent) {
        processUpdates(valueChangeEvent);
        Object message = executeMethod("updateLedgerCurrencyConvRate");
        if (message != null && (!"Success".equals(message))) {
            showPopupMessage(message.toString(), FacesMessage.SEVERITY_ERROR);
            getLedgerCurrencyConvDateBind().setValue(valueChangeEvent.getOldValue());
            addPartialTrigger(getLedgerCurrencyConvDateBind());
        }
    }

    public void setLedgerCurrencyConvDateBind(RichInputDate ledgerCurrencyConvDateBind) {
        this.ledgerCurrencyConvDateBind = ledgerCurrencyConvDateBind;
    }

    public RichInputDate getLedgerCurrencyConvDateBind() {
        return ledgerCurrencyConvDateBind;
    }

    public void setLedgerCurrencyConvRateTypeBind(RichSelectOneChoice ledgerCurrencyConvRateTypeBind) {
        this.ledgerCurrencyConvRateTypeBind = ledgerCurrencyConvRateTypeBind;
    }

    public RichSelectOneChoice getLedgerCurrencyConvRateTypeBind() {
        return ledgerCurrencyConvRateTypeBind;
    }

    public void ledgerCurrencyConvRateTypeVL(ValueChangeEvent valueChangeEvent) {
        processUpdates(valueChangeEvent);
        Object message = executeMethod("updateLedgerCurrencyConvRate");
        if (message != null && (!"Success".equals(message))) {
            showPopupMessage(message.toString(), FacesMessage.SEVERITY_ERROR);
            getLedgerCurrencyConvRateTypeBind().setValue(valueChangeEvent.getOldValue());
            addPartialTrigger(getLedgerCurrencyConvRateTypeBind());
        }
    }
}
