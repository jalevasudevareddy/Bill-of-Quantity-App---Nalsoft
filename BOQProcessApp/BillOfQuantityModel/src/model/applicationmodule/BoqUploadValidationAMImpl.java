package model.applicationmodule;

import au.com.bytecode.opencsv.CSVReader;


import au.com.bytecode.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import java.io.InputStreamReader;

import java.io.OutputStream;

import java.io.OutputStreamWriter;

import java.math.BigDecimal;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.SQLException;

import java.sql.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.applicationmodule.common.BoqUploadValidationAM;

import model.searchview.BOQHeaderVersionLOVVOImpl;
import model.searchview.BOQLoadedSearchVOImpl;
import model.searchview.BoqExportPrintReportVOImpl;
import model.searchview.CurrencyConversionVOImpl;
import model.searchview.ErrorBOQSearchVOImpl;
import model.searchview.GetBoqUploadedRowScrVOImpl;
import model.searchview.GetCountOfValidDocInUploadTblSearchVOImpl;
import model.searchview.GetCountOfValidDocsForProjectSearchVOImpl;

import model.searchview.GetErrorsCountOfValidHdrSearchVOImpl;
import model.searchview.GetMasterBowHdrDocForValidIdScrVOImpl;
import model.searchview.GetPrjBoqValidationStatusForHdrIdSearchVOImpl;
import model.searchview.GetValidatiionPrjDocCountForStatusSearhVOImpl;


import model.searchview.GetVersionNoForLatestPrjSearchVOImpl;
import model.searchview.InvoiceCummQtyBOQValidateVOImpl;
import model.searchview.IsErrorExistsIUploadedBoqsScrVOImpl;
import model.searchview.MaxBOQForContractVOImpl;
import model.searchview.MaxBOQForProjectVOImpl;
import model.searchview.MaxBoqUpldBuContractIdScrVOImpl;
import model.searchview.MaxBoqUpldBuPrjIdScrVOImpl;
import model.searchview.MaxPrjBoqValidationDocHdrIDSearchVOImpl;

import model.searchview.MaxValidHdrIdinUploadTblSearchVOImpl;
import model.searchview.MaxValidationHdrIdFromBoqTblSearchVOImpl;

import model.searchview.PriceCodeWithoutCostCodeVOImpl;
import model.searchview.UploadedErrorsCountSearchVOImpl;
import model.searchview.ValidationLideIdForResourceSearchVOImpl;

import model.searchview.ValidationLineIdForCostCodeSearchVOImpl;

import model.view.AttachmentsVOImpl;
import model.view.BoqValidationHeaderSearchVOImpl;
import model.view.BoqValidationHeaderVOImpl;
import model.view.BoqValidationHeaderVORowImpl;
import model.view.BoqValidationLinesVOImpl;
import model.view.BoqValidationRejectionLinesVOImpl;
import model.view.BoqValidationResourceVOImpl;
import model.view.BoqvalidationCostCodeVOImpl;
import model.view.MaxPCForContractVOImpl;
import model.view.TransBoqUploadvalidationVOImpl;

import model.view.TransBoqUploadvalidationVORowImpl;

import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewLinkImpl;
import oracle.jbo.server.ViewObjectImpl;
import oracle.jbo.domain.Number;

import java.util.Map.Entry;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletResponse;

import model.applicationmodule.common.CommonUtilsAM;

import model.searchview.BOQHeaderVersionLOVVORowImpl;
import model.searchview.BOQLoadedSearchVORowImpl;
import model.searchview.BoqExportPrintReportVORowImpl;
import model.searchview.CurrencyConversionVORowImpl;
import model.searchview.ErrorBOQSearchVORowImpl;
import model.searchview.GetBoqUploadedRowScrVORowImpl;
import model.searchview.GetCountOfValidDocInUploadTblSearchVORowImpl;
import model.searchview.GetCountOfValidDocsForProjectSearchVORowImpl;

import model.searchview.GetErrorsCountOfValidHdrSearchVORowImpl;
import model.searchview.GetMasterBowHdrDocForValidIdScrVORowImpl;
import model.searchview.GetPrjBoqValidationStatusForHdrIdSearchVORowImpl;
import model.searchview.GetValidatiionPrjDocCountForStatusSearhVORowImpl;

import model.searchview.GetVersionNoForLatestPrjSearchVORowImpl;
import model.searchview.InvoiceCummQtyBOQValidateVORowImpl;
import model.searchview.IsErrorExistsIUploadedBoqsScrVORowImpl;
import model.searchview.MaxBOQForContractVORowImpl;
import model.searchview.MaxBOQForProjectVORowImpl;
import model.searchview.MaxBoqUpldBuContractIdScrVORowImpl;
import model.searchview.MaxBoqUpldBuPrjIdScrVORowImpl;
import model.searchview.MaxPrjBoqValidationDocHdrIDSearchVORowImpl;


import model.searchview.MaxValidHdrIdinUploadTblSearchVORowImpl;

import model.searchview.PriceCodeWithoutCostCodeVORowImpl;
import model.searchview.UploadedErrorsCountSearchVORowImpl;

import model.view.AttachmentsVORowImpl;
import model.view.BOQCostingDetailsHeaderVORowImpl;
import model.view.BoqUploadErrorsVOImpl;
import model.view.BoqUploadErrorsVORowImpl;
import model.view.BoqUploadVOImpl;
import model.view.BoqUploadVORowImpl;

import model.view.BoqValidationHeaderSearchVORowImpl;
import model.view.BoqValidationLinesVORowImpl;
import model.view.BoqvalidationCostCodeVORowImpl;
import model.view.CertifiedQtyDetailsVORowImpl;
import model.view.MaxPCForContractVORowImpl;
import model.view.WorkDoneUploadIntVORowImpl;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;

import oracle.binding.BindingContainer;

import oracle.jbo.JboException;
import oracle.jbo.Row;
import oracle.jbo.RowIterator;
import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.BlobDomain;
import oracle.jbo.domain.Date;
import oracle.jbo.server.DBTransaction;

import oracle.jbo.server.SequenceImpl;

import oracle.jbo.server.ViewRowImpl;


import org.apache.myfaces.trinidad.model.UploadedFile;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Mar 13 10:45:09 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class BoqUploadValidationAMImpl extends ApplicationModuleImpl implements BoqUploadValidationAM {

    private final String CLEAR_UPLOADED_DATA =
        "begin xxboq_boq_candy_pkg.clear_uploaded_data" +
        "(:p_header_id,:p_message);end;";

    private final String INSERT_BASELINE_BOQ_LINES =
        "begin xxboq_boq_candy_pkg.insert_baseline_boq_lines_prc" +
        "(:P_BASE_LINE_HDR_ID,:P_VALIDATION_HEADER_ID,:P_USER_NAME,:p_msg);end;";

    private final String CLEAR_VALIDATED_DATA =
        "begin xxboq_boq_candy_pkg.clear_validation_data" +
        "(:p_header_id,:p_message);end;";


    private final String UPDATE_UPLOADED_DATA =
        "begin xxboq_boq_candy_pkg.update_boqs" +
        "(:p_header_id,:p_user_name,:p_message);end;";

    private final String SPLIT_UPLOADED_DATA =
        "begin xxboq_boq_candy_pkg.split_boqs" +
        "(:p_header_id,:p_user_name,:p_message);end;";

    private final String APPEND_UPLOADED_DATA =
        "begin xxboq_boq_candy_pkg.append_boqs" +
        "(:p_header_id,:p_user_name,:p_message);end;";


    private final String VALIDATE_UPLOADED_DATA =
        "begin xxboq_boq_candy_pkg.Validate_boqs_quantities" +
        "(:p_header_id,:p_user_name,:p_message);end;";


    private final String LOAD_UPLOADED_BOQ_DATA =
        "begin xxboq_boq_candy_pkg.load_boqs" +
        "(:p_header_id,:p_user_name,:p_message);end;";

    private final String WITHDRAW_APPRL_SUBMISSION =
        "begin xxame_pkg.withdraw_submission(:p_document_type_id ,:p_document_id,:p_remarks,:p_user,:p_message);end;";


    /**
     * This is the default constructor (do not remove).
     */
    public BoqUploadValidationAMImpl() {
    }

    private BlobDomain createBlobDomain(UploadedFile file) {
        InputStream in = null;
        BlobDomain blobDomain = null;
        OutputStream out = null;
        try {
            in = file.getInputStream();
            blobDomain = new BlobDomain();
            out = blobDomain.getBinaryOutputStream();
            byte[] buffer = new byte[8192];
            int bytesRead = 0;
            while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            e.fillInStackTrace();
        }
        return blobDomain;
    }


    public String saveAttachedFile(UploadedFile attachedFile) {
        String message = null;
        try {
            if (getAttachmentsVO1().getCurrentRow() != null) {
                String fileName = attachedFile.getFilename();
                String contType = attachedFile.getContentType();
                BlobDomain fileBolb = createBlobDomain(attachedFile);
                if (fileName != null && contType != null) {
                    AttachmentsVORowImpl attachmnt =
                        (AttachmentsVORowImpl)getAttachmentsVO1().getCurrentRow();
                    attachmnt.setFileName(fileName);
                    attachmnt.setFileType(contType);
                    attachmnt.setAttachmentFile(fileBolb);
                    message = "Success";
                    //                        this.getDBTransaction().commit();
                } else {
                    message = "Invalid file..";
                }
            } else {
                message = "Attached row not found.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }

    public String callpkgForAMEBOQUploadDocSubmit() {
        String msg = "";
        try {
            CommonUtilsAMImpl commonAM =
                (CommonUtilsAMImpl)getCommonUtilsAM1();

            BoqValidationHeaderVOImpl hdrVO = getBoqValidationHeaderVO1();
            BoqValidationHeaderVORowImpl hdrRow =
                (BoqValidationHeaderVORowImpl)hdrVO.getCurrentRow();
            if (hdrRow != null) {
                Map paramsMap = new HashMap();
                paramsMap.put("docId",
                              hdrRow.getValidationHeaderId().longValue());
                paramsMap.put("docTypeId", 1);
                paramsMap.put("ActionCode", "Submit");
                paramsMap.put("currEmpId",
                              commonAM.getCurrentEmployeeId() != null ?
                              commonAM.getCurrentEmployeeId().longValue() :
                              null);
                paramsMap.put("remarks", "");
                paramsMap.put("docNum", hdrRow.getContractNumber().toString());
                msg = commonAM.callAMEProcess(paramsMap);
                if ("Submitted".equals(msg)) {
                    //                    submitDocFor("In Process");
                    hdrRow.setStatus("I");
                    msg = "Success";
                    if ("Fianlly Approved".equals(msg)) { //FYI approval only
                        //                        submitDocFor("Approved");
                        hdrRow.setStatus("A");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        return msg;
    }

    public String submitBOQUploadDoc() {
        String message = null;
        try {
            message = validateUploadedBoqs();
            if("Success".equals(message)){
                BoqValidationHeaderVORowImpl BoqRow =
                    (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().getCurrentRow();
                if (BoqRow != null) {
                    if (getBoqValidationLinesVO1().getEstimatedRowCount() > 0) {
                        BoqValidationLinesVORowImpl BoqLine =
                            (BoqValidationLinesVORowImpl)getBoqValidationLinesVO1().first();
                        //Commented by Sumanth on 02-09-2020 to allow contract amount<invoiced amount
//                        getInvoiceCummQtyBOQValidateVO1().setNamedWhereClauseParam("p_contract_id",
//                                                                                   BoqRow.getContractId());
//                        getInvoiceCummQtyBOQValidateVO1().executeQuery();
//                        InvoiceCummQtyBOQValidateVORowImpl validRow =
//                            (InvoiceCummQtyBOQValidateVORowImpl)getInvoiceCummQtyBOQValidateVO1().first();
//                        if (validRow == null ||
//                            validRow.getInvoiceCummAmt() == null ||
//                            BoqLine.getTransTotalRevenueAmount().doubleValue() >=
//                            validRow.getInvoiceCummAmt().doubleValue()) {
//                            message = "Success";
                            MaxPCForContractVOImpl maxPcVO = getMaxPCForContractVO1();
                            maxPcVO.setNamedWhereClauseParam("p_contract_id", BoqRow.getContractId());
                            maxPcVO.executeQuery();
                            MaxPCForContractVORowImpl pcRow = (MaxPCForContractVORowImpl) maxPcVO.first();
                            if (pcRow == null ||
                                pcRow.getPcTotal() == null ||
                                BoqLine.getTransTotalRevenueAmount().doubleValue() >=
                                pcRow.getPcTotal().doubleValue()) {
                                message = "Success";
                            } else {
                                message =
                                        "Revised Contract Amount should always be greater than Cumulative Certified amount i.e. " +
                                        pcRow.getPcTotal().doubleValue();
                            }
                        //Commented by Sumanth on 02-09-2020 to allow contract amount<invoiced amount
//                        } else {
//                            message =
//                                    "Total revenue amount should be always greater than latest invoiced amount i.e. " +
//                                    validRow.getInvoiceCummAmt().doubleValue();
//                        }
                    } else {
                        message =
                                "Please enter the boq lines before submitting the document.";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }

    public String withdrawFromApproval(String remarks) {
        String message = null;
        try {
            if (getBoqValidationHeaderVO1().getCurrentRow() != null) {
                BoqValidationHeaderVORowImpl hdrRow =
                    (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().getCurrentRow();
                //                if (hdrRow.getParentLiabilityHdrId() != null) {

                CommonUtilsAMImpl commAM =
                    (CommonUtilsAMImpl)getCommonUtilsAM1();
                CallableStatement st =
                    getDBTransaction().createCallableStatement(WITHDRAW_APPRL_SUBMISSION,
                                                               0);
                st.setLong("p_document_type_id", 1);
                st.setLong("p_document_id",
                           hdrRow.getValidationHeaderId().longValue());
                st.setString("p_remarks", remarks);
                st.setLong("p_user",
                           commAM.getCurrentEmployeeId() != null ? commAM.getCurrentEmployeeId().longValue() :
                           -1);

                st.setString("p_message", null);

                st.registerOutParameter("p_message", Types.VARCHAR);
                st.execute();

                message = st.getString("p_message");
                if ("Success".equals(message)) {
                    this.getDBTransaction().commit();
                    Number revHdrId =
                        new Number(hdrRow.getValidationHeaderId().longValue());
                    makeAsCurrentBoqValHdrRow(revHdrId);
                    getBoqValidationHeaderVO1().getCurrentRow().setAttribute("Status",
                                                                             "W");
                    //                    revHdrId.setStatus("Draft");
                    getBoqValidationLinesVO1().executeQuery();
                }
                //                }
                //                else{
                //                    message= "No Parent liability header id info found for the current Liability Document.";
                //                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }


    public String cancelUploadedBoqs() {
        String message = null;
        try {
            if (getBoqValidationHeaderVO1().getCurrentRow() != null) {
                BoqValidationHeaderVORowImpl row =
                    (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().getCurrentRow();
                row.setStatus("CL");
                row.setCurrentFlag("N");
                getDBTransaction().commit();
                CommonUtilsAMImpl commam =
                    (CommonUtilsAMImpl)getCommonUtilsAM1();
                String remarks =
                    String.valueOf(ADFContext.getCurrent().getPageFlowScope().get("CANCEL_REMARKS"));
                String msg =
                    commam.cancelDocument(remarks, row.getValidationHeaderId().longValue(),
                                          1L);
                CallableStatement st = null;
                st =
 getDBTransaction().createCallableStatement(LOAD_UPLOADED_BOQ_DATA, 0);
                st.setLong("p_header_id",
                           row.getValidationHeaderId().longValue());
                String userName =
                    ((String)((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("UserName"));
                st.setString("p_user_name", userName);
                st.registerOutParameter("p_message", Types.VARCHAR);

                st.execute();
                message = st.getString("p_message");
                if (message != null && "Success".equals(message)) {
                    this.getDBTransaction().commit();
                    Boolean isUpdated =
                        makeAsCurrentBoqValHdrRow(row.getValidationHeaderId());
                    message =
                            isUpdated ? "Success" : "Document successfully loaded. Please refresh the page.";
                }
                message = "Success";
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (getBoqValidationHeaderVO1().getCurrentRow() != null) {
                BoqValidationHeaderVORowImpl row =
                    (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().getCurrentRow();
                row.setStatus("A");
                row.setCurrentFlag("Y");
                getDBTransaction().commit();
            }
            message = e.getMessage();
        }
        return message;
    }

    public String deleteUploadedBOQDoc() {
        String message = null;
        try {
            Number ValidationHeaderId =
                (Number)ADFContext.getCurrent().getPageFlowScope().get("ValidationHeaderId");
            ADFContext.getCurrent().getPageFlowScope().remove("ValidationHeaderId");
            if (ValidationHeaderId == null &&
                getBoqValidationHeaderVO1().getCurrentRow() != null) {
                BoqValidationHeaderVORowImpl row =
                    (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().getCurrentRow();
                row.remove();
                message = "Success";
            } else {
                getBoqValidationHeaderVO1().setNamedWhereClauseParam("p_validation_hdr_id",
                                                                     ValidationHeaderId);
                getBoqValidationHeaderVO1().setApplyViewCriteriaName("BoqValidationHeaderVOCriteria");
                getBoqValidationHeaderVO1().executeQuery();
                BoqValidationHeaderVORowImpl row =
                    (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().first();
                row.remove();
                getBoqValidationHeaderVO1().applyViewCriteria(null);
                getBoqValidationHeaderVO1().executeQuery();
                getBoqValidationHeaderSearchVO1().executeQuery();
                message = "Success";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }

    public String isRejectionsExists() {
        String message = null;
        try {
            if (getBoqValidationRejectionLinesVO1().getEstimatedRowCount() >
                0) {
                message = "Exists";
            } else {
                message = "NotExists";
            }
        } catch (Exception e) {
        }
        return message;
    }

    public String isPriceCodesWithoutCostCodesExists() {
        String message = "";
        try {
            BoqValidationHeaderVORowImpl headerRow =
                (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().getCurrentRow();
            getPriceCodeWithoutCostCodeVO1().setNamedWhereClauseParam("p_validation_header_id",
                                                                      headerRow.getValidationHeaderId());
            getPriceCodeWithoutCostCodeVO1().executeQuery();
            RowSetIterator iter =
                getPriceCodeWithoutCostCodeVO1().createRowSetIterator(null);
            while (iter.hasNext()) {
                PriceCodeWithoutCostCodeVORowImpl linesRow =
                    (PriceCodeWithoutCostCodeVORowImpl)iter.next();
                message += linesRow.getPriceCode() + ", ";
            }
            if (message.length() == 0) {
                message = "Success";
            } else {
                message +=
                        "Price codes are not having cost codes. Do you want to continue?";
            }
        } catch (Exception e) {
        }
        return message;
    }

    public Boolean isErrorExistsInUploaboqs(BigDecimal headerId) {
        Boolean isExists = null;
        try {
            IsErrorExistsIUploadedBoqsScrVOImpl scrVO =
                getIsErrorExistsIUploadedBoqsScrVO1();
            scrVO.setNamedWhereClauseParam("p_header_id", headerId);
            scrVO.executeQuery();
            if (scrVO.first() != null) {
                IsErrorExistsIUploadedBoqsScrVORowImpl row =
                    (IsErrorExistsIUploadedBoqsScrVORowImpl)scrVO.first();
                if ("Y".equals(row.getErrorExists())) {
                    isExists = true;
                } else {
                    isExists = false;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isExists;
    }

    public BigDecimal getMaxBoqBuPrjUpldHdrId(BigDecimal buId,
                                              BigDecimal prjId) {
        BigDecimal masHdrId = null;
        try {
            MaxBoqUpldBuPrjIdScrVOImpl boqUpldBuPrjIdScrVO1 =
                getMaxBoqUpldBuPrjIdScrVO1();
            boqUpldBuPrjIdScrVO1.setNamedWhereClauseParam("p_bu_id", buId);
            boqUpldBuPrjIdScrVO1.setNamedWhereClauseParam("p_prj_id", prjId);
            boqUpldBuPrjIdScrVO1.executeQuery();
            if (boqUpldBuPrjIdScrVO1.first() != null) {
                MaxBoqUpldBuPrjIdScrVORowImpl maxRow =
                    (MaxBoqUpldBuPrjIdScrVORowImpl)boqUpldBuPrjIdScrVO1.first();

                masHdrId = maxRow.getMaxvalhrid();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return masHdrId;
    }

    public BigDecimal getMaxBoqBuContractUpldHdrId(BigDecimal buId,
                                                   BigDecimal contractId) {
        BigDecimal masHdrId = null;
        try {
            MaxBoqUpldBuContractIdScrVOImpl boq =
                getMaxBoqUpldBuContractIdScrVO1();
            boq.setNamedWhereClauseParam("p_bu_id", buId);
            boq.setNamedWhereClauseParam("p_contract_id", contractId);
            boq.executeQuery();
            if (boq.first() != null) {
                MaxBoqUpldBuContractIdScrVORowImpl maxRow =
                    (MaxBoqUpldBuContractIdScrVORowImpl)boq.first();
                masHdrId = maxRow.getMaxvalhrid();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return masHdrId;
    }

    public String isPrevUploadedBoqDocExported(BigDecimal uploadBoqHdrId) {
        String message = null;
        try {
            GetMasterBowHdrDocForValidIdScrVOImpl scrRow =
                getGetMasterBowHdrDocForValidIdScrVO1();
            scrRow.setNamedWhereClauseParam("p_hdr_id", uploadBoqHdrId);
            scrRow.executeQuery();
            if (scrRow.first() != null) {
                GetMasterBowHdrDocForValidIdScrVORowImpl row =
                    (GetMasterBowHdrDocForValidIdScrVORowImpl)scrRow.first();
                if (uploadBoqHdrId.doubleValue() ==
                    row.getValidationHeaderId().doubleValue()) {
                    message = "Exists";
                } else {
                    message = "Not Exists";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public GetBoqUploadedRowScrVORowImpl getBoqUploadedRowScrVORow(BigDecimal maxValdHdrId) {
        GetBoqUploadedRowScrVORowImpl row = null;
        try {

            GetBoqUploadedRowScrVOImpl scrRow = getGetBoqUploadedRowScrVO1();
            scrRow.setNamedWhereClauseParam("p_hdr_id", maxValdHdrId);
            scrRow.executeQuery();
            if (scrRow.first() != null) {
                row = (GetBoqUploadedRowScrVORowImpl)scrRow.first();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return row;
    }

    public Number createBoqUploadValidationHdrRow(String budgetType,
                                                  int version,
                                                  BigDecimal prevHdrId,
                                                  BigDecimal baseLineHdrId) {
        Number boqUploadHdrId = null;
        try {
            if (getTransBoqUploadvalidationVO1().getCurrentRow() != null) {
                TransBoqUploadvalidationVORowImpl transRow =
                    (TransBoqUploadvalidationVORowImpl)getTransBoqUploadvalidationVO1().getCurrentRow();
                BoqValidationHeaderVORowImpl boqValHdrRow =
                    (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().createRow();
                boqValHdrRow.setBusinessUnitId(new Number(transRow.getTransBuId()));
                //                boqValHdrRow.setProjectId(new Number(transRow.getTransPrjId()));
                boqValHdrRow.setContractId(transRow.getTransContractId());
                boqValHdrRow.setLedgerCurrencyConvDate(transRow.getTransLedgerCurrencyConvDate());
                boqValHdrRow.setLedgerCurrencyConvRateType(transRow.getTransLedgerCurrencyConvRateType());
                boqValHdrRow.setLedgerCurrencyConvRateType("C");
                boqValHdrRow.setVersion(new Number(0));
                boqValHdrRow.setIbcNumber(transRow.getTransIbcNumber());
                boqValHdrRow.setStatus("D");
                boqValHdrRow.setBudgetType(budgetType);
                boqValHdrRow.setIsDocumentValid("InValid");
                boqValHdrRow.setVersion(new Number(version));
                boqValHdrRow.setBaseLineValidationHdrId(baseLineHdrId);
                boqValHdrRow.setParentValidationHeaderId(prevHdrId);
                GetBoqUploadedRowScrVORowImpl maxRow =
                    getBoqUploadedRowScrVORow(prevHdrId);
                boqValHdrRow.setMasterValidationHeaderId(version != 0 ?
                                                         maxRow.getMasterValidationHeaderId() :
                                                         boqValHdrRow.getValidationHeaderId().bigDecimalValue());
                //                                boqValHdrRow.setBudgetType(transRow.getTransBudgetType());
                boqValHdrRow.setContractType(maxRow==null?null:maxRow.getContractType());
                boqValHdrRow.setDescription(maxRow==null?null:maxRow.getDescription());
                boqValHdrRow.setRevisionDate(version != 0 ?
                                             new oracle.jbo.domain.Date(new java.sql.Date(System.currentTimeMillis())) :
                                             null);
                getBoqValidationHeaderVO1().insertRow(boqValHdrRow);
                boqUploadHdrId = boqValHdrRow.getValidationHeaderId();

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return boqUploadHdrId;
    }


    public String createUploadBOQDoc() {
        String message = null;
        try {
            Number currHdrId = null;

            if (getTransBoqUploadvalidationVO1().getCurrentRow() != null) {
                TransBoqUploadvalidationVORowImpl transRow =
                    (TransBoqUploadvalidationVORowImpl)getTransBoqUploadvalidationVO1().getCurrentRow();
                BigDecimal maxbuPrjupldHdrId =
                    //                    getMaxBoqBuPrjUpldHdrId(transRow.getTransBuId(),//Commented bby Sumanth on 18-01-2020
                    //                                            transRow.getTransPrjId());
                    getMaxBoqBuContractUpldHdrId(transRow.getTransBuId(),
                                                 transRow.getTransContractId());
                GetBoqUploadedRowScrVORowImpl maxRow =
                    getBoqUploadedRowScrVORow(maxbuPrjupldHdrId);
                if (maxbuPrjupldHdrId == null) {
                    currHdrId =
                            createBoqUploadValidationHdrRow("M", 0, null, null);
                } else if (maxbuPrjupldHdrId != null) {
                    if ("E".equals(maxRow.getStatus())) {
                        message =
                                isPrevUploadedBoqDocExported(maxbuPrjupldHdrId);
                        if ("Exists".equals(message)) {
                            BOQHeaderVersionLOVVOImpl versionVO =
                                getBOQHeaderVersionLOVVO1();
                            versionVO.setNamedWhereClauseParam("p_contract_id",
                                                               maxRow.getContractId());
                            versionVO.executeQuery();
                            BOQHeaderVersionLOVVORowImpl versionRow =
                                (BOQHeaderVersionLOVVORowImpl)versionVO.last();
                            if (maxRow.getBaseLineValidationHdrId() != null) {
                                currHdrId =
                                        createBoqUploadValidationHdrRow("R",
                                                                        versionRow.getVersion().intValue() +
                                                                        1,
                                                                        maxRow.getValidationHeaderId(),
                                                                        maxRow.getBaseLineValidationHdrId());
                            } else {
                                currHdrId =
                                        createBoqUploadValidationHdrRow("M",
                                                                        versionRow.getVersion().intValue() +
                                                                        1,
                                                                        maxRow.getValidationHeaderId(),
                                                                        null);
                            }
                            getBaseLineBOQLines(maxRow.getValidationHeaderId(),
                                                currHdrId);
                        } else {
                            message =
                                    "Previous Boq uploaded Doc is Exported/Created in main BOQ Dcoument.";
                        }
                    } else {
                        message =
                                "You cannot create a new document version when there already exists a working BOQ for selected project. Please search for the version instead. ";
                    }
                }
                //                }

                if (currHdrId != null) {
                    this.getDBTransaction().commit();
                    CommonUtilsAMImpl commam =
                        (CommonUtilsAMImpl)getCommonUtilsAM1();
                    makeAsCurrentBoqValHdrRow(currHdrId);
                    if (getBoqValidationHeaderVO1().getCurrentRow() != null) {
                        BoqValidationHeaderVORowImpl newHdrRow =
                            (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().getCurrentRow();
                        if (newHdrRow.getValidationHeaderId().doubleValue() ==
                            currHdrId.doubleValue()) {
                            updateLedgerCurrencyConvRate();
                            message = "Success";
                        }
                    } else {
                        message =
                                "New BOQ upload Doc is created. Unable to refresh.";
                    }
                } else {
                    message =
                            (message != null ? message : "") + " .Document Creation Failed.";
                }
            } else {
                message = "Trans BOQ Validation Row found null";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }

    public String getBaseLineBOQLines(BigDecimal baseLineHdrId,
                                      Number currHdrId) {
        String message = "";
        try {
            message = "Success";
            CallableStatement st = null;
            try {
                st =
 getDBTransaction().createCallableStatement(INSERT_BASELINE_BOQ_LINES, 0);
                st.setLong("P_BASE_LINE_HDR_ID", baseLineHdrId.longValue());
                st.setLong("P_VALIDATION_HEADER_ID", currHdrId.longValue());
                String userName =
                    ((String)((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("UserName"));
                st.setString("p_user_name", userName);
                st.registerOutParameter("p_msg", Types.VARCHAR);
                st.execute();
                message = st.getString("p_msg");
                getDBTransaction().commit();
            } catch (SQLException e) {
                e.printStackTrace();
                //                throw new JboException(e);
            } finally {
                if (st != null) {
                    try {
                        st.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
        //TODO
    }

    public Double getResTotalNetUseQty() {
        Double qty = null;
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return qty;
    }

    public Long getErrorsCountForCurrentDoc() {

        Long count = null;
        if (getBoqValidationHeaderVO1().getCurrentRow() != null) {
            BoqValidationHeaderVORowImpl boqValHdrVO =
                (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().getCurrentRow();
            UploadedErrorsCountSearchVOImpl errCountSearchVo =
                getUploadedErrorsCountSearchVO1();
            errCountSearchVo.setNamedWhereClauseParam("p_upload_id",
                                                      boqValHdrVO.getValidationHeaderId());
            errCountSearchVo.executeQuery();
            if (errCountSearchVo.first() != null) {
                UploadedErrorsCountSearchVORowImpl errCountRow =
                    (UploadedErrorsCountSearchVORowImpl)errCountSearchVo.first();
                if (errCountRow.getCount1() != null) {
                    count = errCountRow.getCount1().longValue();
                }
            }
        }
        return count;
    }

    private List<String> createErrorBoqCostCodeORRescCode(BoqUploadErrorsVORowImpl errorBoqRow) {
        List<String> ErrorBoqCostCodeORRescCodeLine = null;
        if (errorBoqRow != null) {
            ErrorBoqCostCodeORRescCodeLine = new ArrayList<String>();

            ErrorBoqCostCodeORRescCodeLine.add(errorBoqRow.getCostCode());
            ErrorBoqCostCodeORRescCodeLine.add(errorBoqRow.getCostCodeDescription());
            ErrorBoqCostCodeORRescCodeLine.add(errorBoqRow.getResourceCode());
            ErrorBoqCostCodeORRescCodeLine.add(errorBoqRow.getResourceDescription());
            ErrorBoqCostCodeORRescCodeLine.add(errorBoqRow.getUom1());
            ErrorBoqCostCodeORRescCodeLine.add(errorBoqRow.getNetUseUnit());
            ErrorBoqCostCodeORRescCodeLine.add(errorBoqRow.getFinalRate());
            ErrorBoqCostCodeORRescCodeLine.add(errorBoqRow != null ?
                                               errorBoqRow.getLineNumber().toString() :
                                               "");
        }
        return ErrorBoqCostCodeORRescCodeLine;
    }

    private List<String> createErrorBoqLine(BoqUploadErrorsVORowImpl errorBoqRow) {
        List<String> errorBoqLine = null;
        if (errorBoqRow != null) {
            errorBoqLine = new ArrayList<String>();
            errorBoqLine.add(errorBoqRow.getBoqLevel());
            errorBoqLine.add(errorBoqRow.getPageItem());
            errorBoqLine.add(errorBoqRow.getItem());
            errorBoqLine.add(errorBoqRow.getBillDescription());
            errorBoqLine.add(errorBoqRow.getBillQuantity());
            errorBoqLine.add(errorBoqRow.getFinalQuantity());
            errorBoqLine.add(errorBoqRow.getUom());
            errorBoqLine.add(errorBoqRow.getCandyNetRate());
            errorBoqLine.add(errorBoqRow.getSellingRate());
            errorBoqLine.add(errorBoqRow.getCandyNetFinalAmount());
            errorBoqLine.add(errorBoqRow.getSellingAmount());
            errorBoqLine.add(errorBoqRow.getTrade());
            errorBoqLine.add(errorBoqRow.getPriceCode());
        }
        return errorBoqLine;
    }

    private StringBuilder getBoqUploadCSVFile() {
        //        String FILE_HEADER1 =
        //            ",,,,,,,Net,Selling,Net,Selling,,Price,Resources analysed to Simple resources,,,,,,";
        String FILE_HEADER1 =
            "Level,Line Type,PageItem,Item,Bill Description,Bill Qty,Final Qty,Unit,Selling Rate,Trade,Price Code,Cost Code,Cost Description,Quantity,Cost Rate";

        StringBuilder csv = new StringBuilder();
        //        csv.append(FILE_HEADER1);
        //        csv.append("\n");
        csv.append(FILE_HEADER1);
        return csv;
    }

    private StringBuilder getBoqUpdateCSVFile() {
        String FILE_HEADER1 =
            ",,,,,,,Net,Selling,Net,Selling,,Price,Resources analysed to Simple resources,,,,,,";
        String FILE_HEADER2 =
            "Level,PageItem,Item,Bill description,Bill Qty,Final Qty,Unit,Rate,Rate,Final amount,Amount,Trade,Code,Cost code,Cost description,Code*,Description,Unit,Net useunit,Final rate,Line Number";
        StringBuilder csv = new StringBuilder();
        csv.append(FILE_HEADER1);
        csv.append("\n");
        csv.append(FILE_HEADER2);
        return csv;
    }

    private Long getValidationLineIdFrom(String tableCode, BigDecimal hdrId,
                                         BigDecimal costCodeOrRescId) {
        Long lineId = null;
        if (tableCode != null && hdrId != null && costCodeOrRescId != null) {
            ViewObjectImpl searchVO = null;
            if ("BOQ_COST_CODES".equals(tableCode)) {
                searchVO = getValidationLineIdForCostCodeSearchVO1();
                searchVO.setNamedWhereClauseParam("p_hdr_id", hdrId);
                searchVO.setNamedWhereClauseParam("p_cost_code_id",
                                                  costCodeOrRescId);


            } else if ("BOQ_RESOURCE_CODES".equals(tableCode)) {
                searchVO = getValidationLideIdForResourceSearchVO1();
                searchVO.setNamedWhereClauseParam("p_hdr_id", hdrId);
                searchVO.setNamedWhereClauseParam("p_resc_id",
                                                  costCodeOrRescId);
            }
            if (searchVO != null) {
                searchVO.executeQuery();
                if (searchVO.first() != null) {
                    ViewRowImpl viewRow = (ViewRowImpl)searchVO.first();
                    if (viewRow.getAttribute("ValidationLineId") != null) {
                        lineId =
                                new Long(viewRow.getAttribute("ValidationLineId").toString());
                    }
                }
            }
        }
        return lineId;

    }

    private StringBuilder writeToCSV(Map<Long, List<String>> boqLineMap,
                                     Map<Long, List<List<String>>> costCodeOrRescMap,
                                     StringBuilder sb) {
        if (boqLineMap != null && boqLineMap.size() > 0 &&
            costCodeOrRescMap != null && costCodeOrRescMap.size() > 0 &&
            sb != null) {
            for (Entry<Long, List<String>> boqEntry : boqLineMap.entrySet()) {
                Long LineId = boqEntry.getKey();
                List<String> boqColsList = boqEntry.getValue();
                String errorStr = "";
                Boolean isHdrInserted = false;
                if (costCodeOrRescMap.containsKey(LineId)) {
                    List<List<String>> costCodeOrRescListForBoq =
                        costCodeOrRescMap.get(LineId);

                    for (List<String> costCodeOrRescLine :
                         costCodeOrRescListForBoq) {
                        for (String boqInf : boqColsList) {
                            if (!isHdrInserted) {
                                if (boqInf != null && boqInf != "" &&
                                    boqInf.contains(",")) {
                                    boqInf = "\"" + boqInf + "\"";
                                }
                                errorStr =
                                        errorStr + (boqInf != null ? boqInf :
                                                    "") + ",";
                            } else {
                                errorStr = errorStr + ",";
                            }
                        }
                        isHdrInserted = true;
                        for (int inx = 0; inx < costCodeOrRescLine.size();
                             inx++) {
                            String costCodeOrRescInf =
                                costCodeOrRescLine.get(inx);
                            if (costCodeOrRescInf != null &&
                                costCodeOrRescInf != "" &&
                                costCodeOrRescInf.contains(",")) {
                                costCodeOrRescInf =
                                        "\"" + costCodeOrRescInf + "\"";
                            }
                            errorStr =
                                    errorStr + (costCodeOrRescInf != null ? costCodeOrRescInf :
                                                "");
                            if (inx < costCodeOrRescLine.size() - 1) {
                                errorStr = errorStr + ",";
                            }
                        }
                        sb.append("\n");
                        sb.append(errorStr);
                        errorStr = "";
                    }
                    sb.append("\n");
                }

            }
        }
        return sb;
    }

    public byte[] getUploadedErrors() {
        byte[] errors = null;
        try {
            StringBuilder csv = getBoqUpdateCSVFile();
            if (getBoqValidationHeaderVO1().getCurrentRow() != null) {

                Map boqLineMap = new HashMap<Long, List<String>>();
                Map<Long, List<List<String>>> costCodeOrRescMap =
                    new HashMap<Long, List<List<String>>>();

                BoqValidationHeaderVORowImpl validHdrRow =
                    (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().getCurrentRow();
                RowIterator uploadErrs = validHdrRow.getBoqUploadErrorsVO();
                RowSetIterator rowSetItr =
                    getBoqUploadErrorsVO1().createRowSetIterator(null);
                rowSetItr.reset();

                BoqUploadErrorsVORowImpl errRow =
                    (BoqUploadErrorsVORowImpl)rowSetItr.first();
                while (errRow != null) {
                    errRow = (BoqUploadErrorsVORowImpl)uploadErrs.next();
                    if (errRow.getLineNumber() != null) {
                        Long lineNum = errRow.getLineNumber().longValue();
                        //                        ErrorBOQSearchVOImpl errorBoqSerVO =
                        //                            getErrorBOQSearchVO1();
                        //                        errorBoqSerVO.setNamedWhereClauseParam("p_hdr_id",
                        //                                                               validHdrRow.getValidationHeaderId());
                        //                        errorBoqSerVO.setNamedWhereClauseParam("p_line_seq_id",
                        //                                                               lineNum);
                        //                        errorBoqSerVO.executeQuery();
                        //                        if (errorBoqSerVO.getEstimatedRowCount() > 0) {
                        //                            RowIterator boqErrRI = errorBoqSerVO;
                        //                            while (boqErrRI.hasNext()) {
                        //                                ErrorBOQSearchVORowImpl errorBoqRow =
                        //                                    (ErrorBOQSearchVORowImpl)boqErrRI.next();
                        if (errRow.getReferenceId() != null &&
                            errRow.getTableCode() != null) {
                            Long lineId =
                                "BOQ_LINES".equals(errRow.getTableCode()) ?
                                errRow.getReferenceId().longValue() :
                                getValidationLineIdFrom(errRow.getTableCode(),
                                                        validHdrRow.getValidationHeaderId().bigDecimalValue(),
                                                        errRow.getReferenceId());
                            if (lineId != null) {
                                if (!boqLineMap.containsKey(lineId)) {
                                    List<String> loqLine =
                                        createErrorBoqLine(errRow);
                                    boqLineMap.put(lineId, loqLine);
                                }
                                List<String> errorCostCodeOrResc =
                                    createErrorBoqCostCodeORRescCode(errRow);

                                if (costCodeOrRescMap.containsKey(lineId)) {
                                    List<List<String>> cc =
                                        costCodeOrRescMap.get(lineId);
                                    cc.add(errorCostCodeOrResc);
                                } else {
                                    List<List<String>> newCostCodeOrRescList =
                                        new ArrayList<List<String>>();
                                    newCostCodeOrRescList.add(errorCostCodeOrResc);
                                    costCodeOrRescMap.put(lineId,
                                                          newCostCodeOrRescList);

                                }


                            }
                        } else {
                            System.out.println(errRow.getLineNumber().longValue());
                        }
                        //                            }
                        //                        }
                    }
                    errRow = (BoqUploadErrorsVORowImpl)rowSetItr.next();
                }
                csv = writeToCSV(boqLineMap, costCodeOrRescMap, csv);
                errors = csv.toString().getBytes();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return errors;
    }

    public String appendUploadedData() {
        String exeMessage = null;
        if (getBoqValidationHeaderVO1().getCurrentRow() != null) {
            BoqValidationHeaderVORowImpl boqValHdrVO =
                (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().getCurrentRow();
            CallableStatement st = null;
            try {
                st =
 getDBTransaction().createCallableStatement(APPEND_UPLOADED_DATA, 0);
                st.setLong("p_header_id",
                           boqValHdrVO.getValidationHeaderId().longValue());
                String userName =
                    ((String)((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("UserName"));
                st.setString("p_user_name", userName);
                st.registerOutParameter("p_message", Types.VARCHAR);

                st.execute();
                exeMessage = st.getString("p_message");
                boqValHdrVO.setImportDate(new Date(new java.sql.Date(System.currentTimeMillis())));

            } catch (SQLException e) {
                e.printStackTrace();

                throw new JboException(e);
            } finally {
                if (st != null) {
                    try {
                        // 7. Close the statement
                        st.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            exeMessage =
                    "Deleting previous uploaded data for selected document is failed due to retring the current document is failed. ";
        }
        return exeMessage;
    }

    public byte[] getUploadBoqTemplate() {
        StringBuilder sb = getBoqUploadCSVFile();
        if (sb != null) {
            //return sb.toString().getBytes();
            XSSFWorkbook workbook = new XSSFWorkbook();
            // create a new sheet
            XSSFSheet worksheet = workbook.createSheet("Import BOQ");

            XSSFRow excelrow = null;
            XSSFFont font = workbook.createFont();
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            CellStyle cellStyleHdr = workbook.createCellStyle();
            cellStyleHdr.setFont(font);
            cellStyleHdr.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
            cellStyleHdr.setFillPattern(CellStyle.SOLID_FOREGROUND);
            for (int k = 0; k < 24; k++) {
                worksheet.setColumnWidth(k, 5000);
            }
            excelrow = (XSSFRow)worksheet.createRow(0);
            XSSFCell cellV0 = excelrow.createCell(0);
            cellV0.setCellValue("Level");
            cellV0.setCellStyle(cellStyleHdr);
            cellV0.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV1 = excelrow.createCell(1);
            cellV1.setCellValue("Bill No.");
            cellV1.setCellStyle(cellStyleHdr);
            cellV1.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV2 = excelrow.createCell(2);
            cellV2.setCellValue("Page");
            cellV2.setCellStyle(cellStyleHdr);
            cellV2.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV3 = excelrow.createCell(3);
            cellV3.setCellValue("Item");
            cellV3.setCellStyle(cellStyleHdr);
            cellV3.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV100 = excelrow.createCell(4);
            cellV100.setCellValue("Line Type");
            cellV100.setCellStyle(cellStyleHdr);
            cellV100.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV17 = excelrow.createCell(5);
            cellV17.setCellValue("Variation Category");
            cellV17.setCellStyle(cellStyleHdr);
            cellV17.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV4 = excelrow.createCell(6);
            cellV4.setCellValue("Description");
            cellV4.setCellStyle(cellStyleHdr);
            cellV4.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV5 = excelrow.createCell(7);
            cellV5.setCellValue("Bill Quantity");
            cellV5.setCellStyle(cellStyleHdr);
            cellV5.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV23 = excelrow.createCell(8);
            cellV23.setCellValue("Final Quantity");
            cellV23.setCellStyle(cellStyleHdr);
            cellV23.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV7 = excelrow.createCell(9);
            cellV7.setCellValue("UOM");
            cellV7.setCellStyle(cellStyleHdr);
            cellV7.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV8 = excelrow.createCell(10);
            cellV8.setCellValue("Bill Rate in Contract Currency");
            cellV8.setCellStyle(cellStyleHdr);
            cellV8.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV21 = excelrow.createCell(11);
            cellV21.setCellValue("Bill Amount in Contract Currency");
            cellV21.setCellStyle(cellStyleHdr);
            cellV21.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV210 = excelrow.createCell(12);
            cellV210.setCellValue("Bill Amount in Ledger Currency");
            cellV210.setCellStyle(cellStyleHdr);
            cellV210.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV211 = excelrow.createCell(13);
            cellV211.setCellValue("Final Amount in Contract Currency");
            cellV211.setCellStyle(cellStyleHdr);
            cellV211.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV212 = excelrow.createCell(14);
            cellV212.setCellValue("Final Amount in Ledger Currency");
            cellV212.setCellStyle(cellStyleHdr);
            cellV212.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV213 = excelrow.createCell(15);
            cellV213.setCellValue("Contract Line Number");
            cellV213.setCellStyle(cellStyleHdr);
            cellV213.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV214 = excelrow.createCell(16);
            cellV214.setCellValue("Associated Project Number");
            cellV214.setCellStyle(cellStyleHdr);
            cellV214.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV90 = excelrow.createCell(17);
            cellV90.setCellValue("Task Number");
            cellV90.setCellStyle(cellStyleHdr);
            cellV90.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV215 = excelrow.createCell(18);
            cellV215.setCellValue("Task Name");
            cellV215.setCellStyle(cellStyleHdr);
            cellV215.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV9 = excelrow.createCell(19);
            cellV9.setCellValue("Trade");
            cellV9.setCellStyle(cellStyleHdr);
            cellV9.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV10 = excelrow.createCell(20);
            cellV10.setCellValue("Price Code");
            cellV10.setCellStyle(cellStyleHdr);
            cellV10.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV22 = excelrow.createCell(21);
            cellV22.setCellValue("Cost Rate");
            cellV22.setCellStyle(cellStyleHdr);
            cellV22.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV6 = excelrow.createCell(22);
            cellV6.setCellValue("Cost Amount");
            cellV6.setCellStyle(cellStyleHdr);
            cellV6.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV11 = excelrow.createCell(23);
            cellV11.setCellValue("Line Remarks");
            cellV11.setCellStyle(cellStyleHdr);
            cellV11.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV12 = excelrow.createCell(24);
            cellV12.setCellValue("Resource");
            cellV12.setCellStyle(cellStyleHdr);
            cellV12.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV13 = excelrow.createCell(25);
            cellV13.setCellValue("Resource Description");
            cellV13.setCellStyle(cellStyleHdr);
            cellV13.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV130 = excelrow.createCell(26);
            cellV130.setCellValue("Resource Class");
            cellV130.setCellStyle(cellStyleHdr);
            cellV130.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV131 = excelrow.createCell(27);
            cellV131.setCellValue("Resource UOM");
            cellV131.setCellStyle(cellStyleHdr);
            cellV131.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV14 = excelrow.createCell(28);
            cellV14.setCellValue("Resource Quantity");
            cellV14.setCellStyle(cellStyleHdr);
            cellV14.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV15 = excelrow.createCell(29);
            cellV15.setCellValue("Resource Rate");
            cellV15.setCellStyle(cellStyleHdr);
            cellV15.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV24 = excelrow.createCell(30);
            cellV24.setCellValue("Resource Amount");
            cellV24.setCellStyle(cellStyleHdr);
            cellV24.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV16 = excelrow.createCell(31);
            cellV16.setCellValue("Resource Remarks");
            cellV16.setCellStyle(cellStyleHdr);
            cellV16.setCellType(Cell.CELL_TYPE_STRING);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                workbook.write(bos);
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bos.toByteArray();
        }
        return null;
    }

    public byte[] getUploadedBoqData() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet worksheet = workbook.createSheet("Import BOQ");
        XSSFRow excelrow = null;
        int rowNum = 0;
        XSSFFont hdrFont = workbook.createFont();
        hdrFont.setColor(IndexedColors.WHITE.getIndex());
        hdrFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        CellStyle cellStyleHdr = workbook.createCellStyle();
        cellStyleHdr.setFont(hdrFont);
        cellStyleHdr.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
        cellStyleHdr.setFillPattern(CellStyle.SOLID_FOREGROUND);
        XSSFFont lineFont = workbook.createFont();
        lineFont.setColor(IndexedColors.BLACK.getIndex());
        CellStyle cellStyleLine = workbook.createCellStyle();
        cellStyleLine.setFont(lineFont);
        for (int k = 0; k < 27; k++) {
            worksheet.setColumnWidth(k, 5000);
        }
        excelrow = (XSSFRow)worksheet.createRow(0);
        XSSFCell cellV0 = excelrow.createCell(0);
        cellV0.setCellValue("Level");
        cellV0.setCellStyle(cellStyleHdr);
        cellV0.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV1 = excelrow.createCell(1);
        cellV1.setCellValue("Bill No.");
        cellV1.setCellStyle(cellStyleHdr);
        cellV1.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV2 = excelrow.createCell(2);
        cellV2.setCellValue("Page");
        cellV2.setCellStyle(cellStyleHdr);
        cellV2.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV3 = excelrow.createCell(3);
        cellV3.setCellValue("Item");
        cellV3.setCellStyle(cellStyleHdr);
        cellV3.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV100 = excelrow.createCell(4);
        cellV100.setCellValue("Line Type");
        cellV100.setCellStyle(cellStyleHdr);
        cellV100.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV17 = excelrow.createCell(5);
        cellV17.setCellValue("Variation Category");
        cellV17.setCellStyle(cellStyleHdr);
        cellV17.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV4 = excelrow.createCell(6);
        cellV4.setCellValue("Description");
        cellV4.setCellStyle(cellStyleHdr);
        cellV4.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV5 = excelrow.createCell(7);
        cellV5.setCellValue("Bill Quantity");
        cellV5.setCellStyle(cellStyleHdr);
        cellV5.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV23 = excelrow.createCell(8);
        cellV23.setCellValue("Final Quantity");
        cellV23.setCellStyle(cellStyleHdr);
        cellV23.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV7 = excelrow.createCell(9);
        cellV7.setCellValue("UOM");
        cellV7.setCellStyle(cellStyleHdr);
        cellV7.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV8 = excelrow.createCell(10);
        cellV8.setCellValue("Bill Rate in Contract Currency");
        cellV8.setCellStyle(cellStyleHdr);
        cellV8.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV21 = excelrow.createCell(11);
        cellV21.setCellValue("Bill Amount in Contract Currency");
        cellV21.setCellStyle(cellStyleHdr);
        cellV21.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV210 = excelrow.createCell(12);
        cellV210.setCellValue("Bill Amount in Ledger Currency");
        cellV210.setCellStyle(cellStyleHdr);
        cellV210.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV211 = excelrow.createCell(13);
        cellV211.setCellValue("Final Amount in Contract Currency");
        cellV211.setCellStyle(cellStyleHdr);
        cellV211.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV212 = excelrow.createCell(14);
        cellV212.setCellValue("Final Amount in Ledger Currency");
        cellV212.setCellStyle(cellStyleHdr);
        cellV212.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV213 = excelrow.createCell(15);
        cellV213.setCellValue("Contract Line Number");
        cellV213.setCellStyle(cellStyleHdr);
        cellV213.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV214 = excelrow.createCell(16);
        cellV214.setCellValue("Associated Project Number");
        cellV214.setCellStyle(cellStyleHdr);
        cellV214.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV90 = excelrow.createCell(17);
        cellV90.setCellValue("Task Number");
        cellV90.setCellStyle(cellStyleHdr);
        cellV90.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV215 = excelrow.createCell(18);
        cellV215.setCellValue("Task Name");
        cellV215.setCellStyle(cellStyleHdr);
        cellV215.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV9 = excelrow.createCell(19);
        cellV9.setCellValue("Trade");
        cellV9.setCellStyle(cellStyleHdr);
        cellV9.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV10 = excelrow.createCell(20);
        cellV10.setCellValue("Price Code");
        cellV10.setCellStyle(cellStyleHdr);
        cellV10.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV22 = excelrow.createCell(21);
        cellV22.setCellValue("Cost Rate");
        cellV22.setCellStyle(cellStyleHdr);
        cellV22.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV6 = excelrow.createCell(22);
        cellV6.setCellValue("Cost Amount");
        cellV6.setCellStyle(cellStyleHdr);
        cellV6.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV11 = excelrow.createCell(23);
        cellV11.setCellValue("Line Remarks");
        cellV11.setCellStyle(cellStyleHdr);
        cellV11.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV12 = excelrow.createCell(24);
        cellV12.setCellValue("Resource");
        cellV12.setCellStyle(cellStyleHdr);
        cellV12.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV13 = excelrow.createCell(25);
        cellV13.setCellValue("Resource Description");
        cellV13.setCellStyle(cellStyleHdr);
        cellV13.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV130 = excelrow.createCell(26);
        cellV130.setCellValue("Resource Class");
        cellV130.setCellStyle(cellStyleHdr);
        cellV130.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV131 = excelrow.createCell(27);
        cellV131.setCellValue("Resource UOM");
        cellV131.setCellStyle(cellStyleHdr);
        cellV131.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV14 = excelrow.createCell(28);
        cellV14.setCellValue("Resource Quantity");
        cellV14.setCellStyle(cellStyleHdr);
        cellV14.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV15 = excelrow.createCell(29);
        cellV15.setCellValue("Resource Rate");
        cellV15.setCellStyle(cellStyleHdr);
        cellV15.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV24 = excelrow.createCell(30);
        cellV24.setCellValue("Resource Amount");
        cellV24.setCellStyle(cellStyleHdr);
        cellV24.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV16 = excelrow.createCell(31);
        cellV16.setCellValue("Resource Remarks");
        cellV16.setCellStyle(cellStyleHdr);
        cellV16.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV25 = excelrow.createCell(32);
        cellV25.setCellValue("Line Error Information");
        cellV25.setCellStyle(cellStyleHdr);
        cellV25.setCellType(Cell.CELL_TYPE_STRING);
        XSSFCell cellV26 = excelrow.createCell(33);
        cellV26.setCellValue("Cost Code Error Information");
        cellV26.setCellStyle(cellStyleHdr);
        cellV26.setCellType(Cell.CELL_TYPE_STRING);
        //lines
        BoqValidationHeaderVORowImpl headerRow =
            (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().getCurrentRow();
        BoqExportPrintReportVOImpl boqExportVO = getBoqExportPrintReportVO1();
        boqExportVO.setNamedWhereClauseParam("p_validation_header_id",
                                             headerRow.getValidationHeaderId());
        boqExportVO.executeQuery();
        RowSetIterator iter = boqExportVO.createRowSetIterator(null);
        String billPageItem = "", newBillPageItem = "";
        String columns[] =
        { "BoqLevel", "BillNumber", "PageItem", "Item", "LineType",
          "VariationCategory", "BillDescription", "BillQuantityNum",
          "FinalQuantityNum", "Uom", "SellingRate", "SellingAmountContract",
          "SellingAmountLedger", "FinalAmountContract", "FinalAmountLedger",
          "ContractLineNumber", "ProjectNumber", "TaskNumber", "TaskName",
          "Trade", "PriceCode", "BudgetRate", "BudgetAmount", "BoqLineRemarks",
          "CostCode", "CostCodeDescription", "ResourceClass", "CostCodeUom",
          "CostCodeQuantity", "CostCodeRate", "CostCodeAmount",
          "CostCodeRemarks", "ErrorDetails", "CostCodeErrorDetails" };
        while (iter.hasNext()) {
            int columnNum = 0;
            int billPageItemExists = 0;
            BoqExportPrintReportVORowImpl exportRow =
                (BoqExportPrintReportVORowImpl)iter.next();
            newBillPageItem = exportRow.getBillPageItem();
            if (billPageItem != null &&
                billPageItem.equalsIgnoreCase(newBillPageItem)) {
                columnNum = 24;
                billPageItemExists = 1;
            } else {
                billPageItem = newBillPageItem;
                rowNum++;
            }
            excelrow = worksheet.createRow(++rowNum);
            while (columnNum < columns.length) {
                if (!(billPageItemExists == 1 && columnNum == 32)) {
                    XSSFCell cellV30 = excelrow.createCell(columnNum);
                    cellV30.setCellValue(exportRow.getAttribute(columns[columnNum]) !=
                                         null ?
                                         String.valueOf(exportRow.getAttribute(columns[columnNum])) :
                                         null);
                    cellV30.setCellStyle(cellStyleLine);
                    //                if(columnNum==8 || columnNum==10||columnNum==11||columnNum==14||columnNum==18||columnNum==19){
                    //                    cellV30.setCellType(Cell.CELL_TYPE_NUMERIC);
                    //                }else{
                    cellV30.setCellType(Cell.CELL_TYPE_STRING);
                    //                }
                }
                columnNum++;
            }
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }

    public String updateUploadedData() {
        String exeMessage = null;
        if (getBoqValidationHeaderVO1().getCurrentRow() != null) {
            BoqValidationHeaderVORowImpl boqValHdrVO =
                (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().getCurrentRow();
            CallableStatement st = null;
            try {
                st =
 getDBTransaction().createCallableStatement(UPDATE_UPLOADED_DATA, 0);
                st.setLong("p_header_id",
                           boqValHdrVO.getValidationHeaderId().longValue());
                String userName =
                    ((String)((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("UserName"));
                st.setString("p_user_name", userName);
                st.registerOutParameter("p_message", Types.VARCHAR);

                st.execute();
                exeMessage = st.getString("p_message");
                boqValHdrVO.setImportDate(new Date(new java.sql.Date(System.currentTimeMillis())));

            } catch (SQLException e) {
                e.printStackTrace();

                throw new JboException(e);
            } finally {
                if (st != null) {
                    try {
                        // 7. Close the statement
                        st.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            exeMessage =
                    "Deleting previous uploaded data for selected document is failed due to retrieving the current document is failed. ";
        }
        return exeMessage;
    }

    public void refreshUploadErrorsVO() {
        if (getBoqValidationHeaderVO1().getCurrentRow() != null) {
            getBoqUploadErrorsVO1().executeQuery();
        }
    }

    private Boolean isBoqAlreadyLoaded(Number valHdrId) {
        Boolean isLoaded = false;
        try {
            if (valHdrId != null) {
                BOQLoadedSearchVOImpl boqLoadSearchVO =
                    getBOQLoadedSearchVO1();
                boqLoadSearchVO.setNamedWhereClauseParam("p_boq_hdr_id",
                                                         valHdrId);
                boqLoadSearchVO.executeQuery();
                if (boqLoadSearchVO.first() != null) {
                    BOQLoadedSearchVORowImpl loadedScrRow =
                        (BOQLoadedSearchVORowImpl)boqLoadSearchVO.first();
                    if (loadedScrRow.getRowCount() != null &&
                        loadedScrRow.getRowCount().longValue() > 0) {
                        long ii = loadedScrRow.getRowCount().longValue();
                        isLoaded = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isLoaded;
    }

    public void refreshUploadInterfaceVO() {
        getBoqUploadVO1().executeQuery();
    }

    public Boolean updateBoqUploadedDocWith(String updateWith) {
        Boolean isUPdated = false;
        if (updateWith != null) {
            if (getBoqValidationHeaderVO1().getCurrentRow() != null) {
                BoqValidationHeaderVORowImpl hdrRow =
                    (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().getCurrentRow();
                hdrRow.setIsDocumentValid(updateWith);
                isUPdated = true;
            }
        }
        return isUPdated;
    }

    public String validateUploadedBoqs() {
        String exeMessage = null;
        if (getBoqValidationHeaderVO1().getCurrentRow() != null) {
            BoqValidationHeaderVORowImpl boqValHdrVO =
                (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().getCurrentRow();
            CallableStatement st = null;
            try {

                st =
 getDBTransaction().createCallableStatement(VALIDATE_UPLOADED_DATA, 0);
                st.setLong("p_header_id",
                           boqValHdrVO.getValidationHeaderId().longValue());
                String userName =
                    ((String)((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("UserName"));
                st.setString("p_user_name", userName);
                st.registerOutParameter("p_message", Types.VARCHAR);

                st.execute();
                exeMessage = st.getString("p_message");


            } catch (SQLException e) {
                e.printStackTrace();
                exeMessage = e.getMessage();
                //                    return e.getLocalizedMessage();

            } finally {
                if (st != null) {
                    try {
                        // 7. Close the statement
                        st.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            exeMessage =
                    "Deleting previous uploaded data for selected document is failed due to retrieving the current document is failed. ";
        }
        return exeMessage;
    }

    public String loadUploadedBoqs() {
        String exeMessage = null;
        if (getBoqValidationHeaderVO1().getCurrentRow() != null) {
            CallableStatement st = null;
            try {
                BoqValidationHeaderVORowImpl boqValHdrVO =
                    (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().getCurrentRow();
                BoqValidationLinesVORowImpl BoqLine =
                    (BoqValidationLinesVORowImpl)getBoqValidationLinesVO1().first();
                        //Commented by Sumanth on 02-09-2020 to allow contract amount<invoiced amount
//                        getInvoiceCummQtyBOQValidateVO1().setNamedWhereClauseParam("p_contract_id",
//                                                                                   boqValHdrVO.getContractId());
//                        getInvoiceCummQtyBOQValidateVO1().executeQuery();
//                        InvoiceCummQtyBOQValidateVORowImpl validRow =
//                            (InvoiceCummQtyBOQValidateVORowImpl)getInvoiceCummQtyBOQValidateVO1().first();
//                        if (validRow == null ||
//                            validRow.getInvoiceCummAmt() == null ||
//                            BoqLine.getTransTotalRevenueAmount().doubleValue() >=
//                            validRow.getInvoiceCummAmt().doubleValue()) {
//                            exeMessage = "Success";
                            MaxPCForContractVOImpl maxPcVO = getMaxPCForContractVO1();
                            maxPcVO.setNamedWhereClauseParam("p_contract_id", boqValHdrVO.getContractId());
                            maxPcVO.executeQuery();
                            MaxPCForContractVORowImpl pcRow = (MaxPCForContractVORowImpl) maxPcVO.first();
                            if (pcRow == null ||
                                pcRow.getPcTotal() == null ||
                                BoqLine.getTransTotalRevenueAmount().doubleValue() >=
                                pcRow.getPcTotal().doubleValue()) {
                                exeMessage = "Success";
                    Boolean isErrorsExists =
                        isErrorExistsInUploaboqs(boqValHdrVO.getValidationHeaderId().bigDecimalValue());
                    if (!isErrorsExists) {
                        if (!isBoqAlreadyLoaded(boqValHdrVO.getValidationHeaderId())) {
                            st =
 getDBTransaction().createCallableStatement(LOAD_UPLOADED_BOQ_DATA, 0);
                            st.setLong("p_header_id",
                                       boqValHdrVO.getValidationHeaderId().longValue());
                            String userName =
                                ((String)((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("UserName"));
                            st.setString("p_user_name", userName);
                            st.registerOutParameter("p_message",
                                                    Types.VARCHAR);
                            st.execute();
                            exeMessage = st.getString("p_message");
                            if (exeMessage != null &&
                                "Success".equals(exeMessage)) {
                                this.getDBTransaction().commit();
                                Boolean isUpdated =
                                    makeAsCurrentBoqValHdrRow(boqValHdrVO.getValidationHeaderId());
                                exeMessage =
                                        isUpdated ? "Success" : "Document successfully loaded. Please refresh the page.";
                            }
                        } else {
                            boqValHdrVO.setStatus("E");
                            getDBTransaction().commit();
                            getBoqValidationHeaderVO1().executeQuery();
                            exeMessage =
                                    "Selected document is already loaded.";
                        }
                    } else {
                        exeMessage =
                                "You cannot submit document when errors exist. Please correct the errors and resubmit for approval.";
                    }
                            } else {
                                exeMessage =
                                        "Revised Contract Amount should always be greater than Cumulative Certified amount i.e. " +
                                        pcRow.getPcTotal().doubleValue();
                            }
                        //Commented by Sumanth on 02-09-2020 to allow contract amount<invoiced amount
//                        } else {
//                            exeMessage =
//                                    "Total revenue amount should be always greater than latest invoiced amount i.e. " +
//                                    validRow.getInvoiceCummAmt().doubleValue();
//                        }
            } catch (SQLException e) {
                e.printStackTrace();
                //                    return e.getLocalizedMessage();
                throw new JboException(e);
            } finally {
                if (st != null) {
                    try {
                        // 7. Close the statement
                        st.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            exeMessage =
                    "Deleting previous uploaded data for selected document is failed due to retrieving the current document is failed. ";
        }
        return exeMessage;
    }

    public String splitUploadedData() {
        String exeMessage = null;
        try {
            if (getBoqValidationHeaderVO1().getCurrentRow() != null) {

                BoqValidationHeaderVORowImpl boqValHdrVO =
                    (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().getCurrentRow();
                double currHdrId =
                    boqValHdrVO.getValidationHeaderId().doubleValue();
                CallableStatement st = null;

                st =
 getDBTransaction().createCallableStatement(SPLIT_UPLOADED_DATA, 0);
                st.setLong("p_header_id",
                           boqValHdrVO.getValidationHeaderId().longValue());
                String userName =
                    ((String)((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("UserName"));
                st.setString("p_user_name", userName);
                st.registerOutParameter("p_message", Types.VARCHAR);

                st.execute();
                exeMessage = st.getString("p_message");
                boqValHdrVO.setImportDate(new Date(new java.sql.Date(System.currentTimeMillis())));
                this.getDBTransaction().commit();

                Boolean isRefreshed =
                    makeAsCurrentBoqValHdrRow(new Number(currHdrId));
                if (!isRefreshed) {
                    exeMessage =
                            "Uploaded data successfully splitted. Please refresh the page.";
                }


            } else {
                exeMessage =
                        "Deleting previous uploaded data for selected document is failed due to retrieving the current document is failed. ";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exeMessage;
    }

    public String clearValidatedData() {
        String exeMessage = null;
        if (getBoqValidationHeaderVO1().getCurrentRow() != null) {
            BoqValidationHeaderVORowImpl boqValHdrVO =
                (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().getCurrentRow();
            CallableStatement st = null;
            try {
                st =
 getDBTransaction().createCallableStatement(CLEAR_VALIDATED_DATA, 0);
                st.setLong("p_header_id",
                           boqValHdrVO.getValidationHeaderId().longValue());
                //                String userName =
                //                    ((String)((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("UserName"));
                //                st.setString("p_user_name", userName);
                st.registerOutParameter("p_message", Types.VARCHAR);

                st.execute();
                exeMessage = st.getString("p_message");


            } catch (SQLException e) {
                e.printStackTrace();
                //                    return e.getLocalizedMessage();
                throw new JboException(e);
            } finally {
                if (st != null) {
                    try {
                        // 7. Close the statement
                        st.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            exeMessage =
                    "Deleting previous uploaded data for selected document is failed due to retrieving the current document is failed. ";
        }
        return exeMessage;
    }


    public String clearUploadedData() {
        String exeMessage = null;
        if (getBoqValidationHeaderVO1().getCurrentRow() != null) {
            BoqValidationHeaderVORowImpl boqValHdrVO =
                (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().getCurrentRow();
            CallableStatement st = null;
            try {
                st =
 getDBTransaction().createCallableStatement(CLEAR_UPLOADED_DATA, 0);
                st.setLong("p_header_id",
                           boqValHdrVO.getValidationHeaderId().longValue());
                //                String userName =
                //                    ((String)((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("UserName"));
                //                st.setString("p_user_name", userName);
                st.registerOutParameter("p_message", Types.VARCHAR);

                st.execute();
                exeMessage = st.getString("p_message");


            } catch (SQLException e) {
                e.printStackTrace();
                //                    return e.getLocalizedMessage();
                throw new JboException(e);
            } finally {
                if (st != null) {
                    try {
                        // 7. Close the statement
                        st.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            exeMessage =
                    "Deleting previous uploaded data for selected document is failed due to retrieving the current document is failed. ";
        }
        return exeMessage;
    }

    public Long geterrorsCountForValidationDoc(Number validationHdrId) {
        Long errorsCount = 0L;
        if (validationHdrId != null) {
            GetErrorsCountOfValidHdrSearchVOImpl errCountSearchVO =
                getGetErrorsCountOfValidHdrSearchVO1();
            errCountSearchVO.setNamedWhereClauseParam("p_vadl_hdr_id",
                                                      validationHdrId);
            errCountSearchVO.executeQuery();
            if (errCountSearchVO.first() != null) {
                GetErrorsCountOfValidHdrSearchVORowImpl errRow =
                    (GetErrorsCountOfValidHdrSearchVORowImpl)errCountSearchVO.first();
                if (errRow.getErrorsCount() != null) {
                    errorsCount = errRow.getErrorsCount().longValue();
                }
            }
        }
        return errorsCount;
    }

    public Boolean updateValidationDocumentAs(String updateWith) {
        Boolean isUPdated = false;
        if (getBoqValidationHeaderVO1().getCurrentRow() != null) {
            BoqValidationHeaderVORowImpl boqValHdrVO =
                (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().getCurrentRow();
            boqValHdrVO.setStatus(updateWith);
            if ("Approved".equals(updateWith)) {
                CommonUtilsAMImpl commanAM =
                    (CommonUtilsAMImpl)getCommonUtilsAM1();
                boqValHdrVO.setApprovedBy(commanAM.getCurrentEmployeeUserName());
                boqValHdrVO.setApprovedDate(new Date(new java.sql.Date(System.currentTimeMillis())));
            }
            if ("Rejected".equals(updateWith)) {
                boqValHdrVO.setIsDocumentValid("In Valid");
            }
            isUPdated = true;
        }
        return isUPdated;
    }

    public boolean insertUploadedRow(Map<String, Object> attrValMap) {
        Boolean isInserted = false;
        CommonUtilsAMImpl commanAM = (CommonUtilsAMImpl)getCommonUtilsAM1();
        try {
            if (attrValMap != null && attrValMap.size() > 0) {
                BoqUploadVOImpl boqUploadVo =
                    (BoqUploadVOImpl)getBoqUploadVO1();
                BoqUploadVORowImpl uploadRow = null;
                for (Entry<String, Object> entry : attrValMap.entrySet()) {
                    uploadRow = (BoqUploadVORowImpl)boqUploadVo.createRow();
                    //                    uploadRow.setUploadId(new Number(commanAM.getCurrentEmployeeId()));
                    uploadRow.setAttribute(entry.getKey(), entry.getValue());
                }
                boqUploadVo.insertRow(uploadRow);
                isInserted = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isInserted;

    }

    public List getUpdatedAttributes() {
        List<String> list = new ArrayList<String>();

        list.add("BoqLevel");
        list.add("BillNumber");
        list.add("PageItem");
        list.add("Item");
        list.add("LineType");

        list.add("VariationCategory");
        list.add("BillDescription");
        list.add("BillQuantity");
        list.add("FinalQuantity");
        list.add("Uom");
        //        list.add("CandyNetRate");
        list.add("SellingRate");
        //        list.add("CandyNetFinalAmount");
        //        list.add("SellingAmount");

        list.add("ContractLineNumber");
        list.add("ProjectNumber");

        list.add("TaskNumber");

        list.add("Trade");
        list.add("PriceCode");

        list.add("BoqLineRemarks");
        list.add("CostCode");
        list.add("CostCodeDescription");
        //        list.add("ResourceCode");
        //        list.add("ResourceDescription");
        //        list.add("ResourceUom");
        //        list.add("NetUseUnit");
        //        list.add("FinalRate");
        list.add("CostCodeQuantity");
        list.add("CostCodeRate");
        list.add("CostCodeAmount");
        list.add("CostCodeRemarks");
        list.add("LineNumber");

        return list;
    }

    public List getUploadAttributes() {
        List<String> list = new ArrayList<String>();

        list.add("BoqLevel");
        list.add("BillNumber");
        list.add("PageItem");
        list.add("Item");
        list.add("LineType");

        list.add("VariationCategory");
        list.add("BillDescription");
        list.add("BillQuantity");
        list.add("FinalQuantity");
        list.add("Uom");
        //        list.add("CandyNetRate");
        list.add("SellingRate");
        //        list.add("CandyNetFinalAmount");
        //        list.add("SellingAmount");

        list.add("ContractLineNumber");
        list.add("ProjectNumber");

        list.add("TaskNumber");

        list.add("Trade");
        list.add("PriceCode");

        list.add("BoqLineRemarks");
        list.add("CostCode");
        list.add("CostCodeDescription");
        //        list.add("ResourceCode");
        //        list.add("ResourceDescription");
        //        list.add("ResourceUom");
        //        list.add("NetUseUnit");
        //        list.add("FinalRate");
        list.add("CostCodeQuantity");
        list.add("CostCodeRate");
        list.add("CostCodeAmount");
        list.add("CostCodeRemarks");

        return list;
    }

    public String readCSV(UploadedFile uploadedFile) {
        String message = null;
        try {
            BoqUploadVOImpl boqUploadVo = (BoqUploadVOImpl)getBoqUploadVO1();
            CommonUtilsAMImpl commanAM =
                (CommonUtilsAMImpl)getCommonUtilsAM1();
            List<String> attrList = null;
            if (uploadedFile != null) {

                InputStream is = uploadedFile.getInputStream();
                CSVReader reader = new CSVReader(new InputStreamReader(is));
                String[] readStrArr = null;
                BoqUploadVORowImpl uploadRow = null;
                int skip = 2;
                int lineNum = 0;
                while ((readStrArr = reader.readNext()) != null) {

                    attrList =
                            attrList != null ? attrList : (readStrArr.length ==
                                                           22 ?
                                                           getUploadAttributes() :
                                                           getUpdatedAttributes());
                    lineNum++;

                    if (skip != -1) {
                        --skip;
                    }
                    //                    if (lineNum == 165) {
                    //                        System.out.println(lineNum);
                    //                    }
                    if (skip == -1) {
                        uploadRow =
                                (BoqUploadVORowImpl)boqUploadVo.createRow();
                        //                        uploadRow.setUploadId(commanAM.getCurrentEmployeeId());
                        //uploadRow.setValidationHeaderId(value);
                        for (int inx = 0; inx < readStrArr.length; inx++) {
                            uploadRow.setAttribute(attrList.get(inx),
                                                   readStrArr[inx]);

                        }
                        uploadRow.setLineSequence(new BigDecimal(lineNum));
                        boqUploadVo.insertRow(uploadRow);
                        this.getDBTransaction().commit();
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public String uploadBoqs(UploadedFile uploadedFile) {
        String message = null;
        try {
            CommonUtilsAMImpl commonAM =
                (CommonUtilsAMImpl)getCommonUtilsAM1();
            if (getBoqValidationHeaderVO1().getCurrentRow() != null) {
                BoqValidationHeaderVORowImpl hdrRow =
                    (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().getCurrentRow();
                SequenceImpl seq =
                    new SequenceImpl("XXPJ_BOQ_STRUCTURE_SETUP_S",
                                     getDBTransaction());
                BoqUploadVOImpl boqUploadVo =
                    (BoqUploadVOImpl)getBoqUploadVO1();
                CommonUtilsAMImpl commanAM =
                    (CommonUtilsAMImpl)getCommonUtilsAM1();
                List<String> attrList = null;
                if (uploadedFile != null) {

                    InputStream is = uploadedFile.getInputStream();
                    //                    CSVReader reader =
                    //                        new CSVReader(new InputStreamReader(is));
                    XSSFWorkbook workbook = new XSSFWorkbook(is);
                    is.close();
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    Iterator<org.apache.poi.ss.usermodel.Row> reader =
                        sheet.iterator();
                    Object[] readStrArr = new Object[22];
                    BoqUploadVORowImpl uploadRow = null;
                    int skip = 1;
                    int lineNum = 0;
                    while (reader.hasNext()) {
                        org.apache.poi.ss.usermodel.Row row = reader.next();
                        lineNum++;

                        skip = skip != -1 ? --skip : skip;
                        if (skip == -1) {
                            int cellSkip = 0;
                            for (int j = 0; j < 31; j++) {
                                Cell cell = null;
                                cell = row.getCell(j);
                                Object cellValue = null;
                                if (!(j == 11 || j == 12 || j == 13 ||
                                      j == 14 || j == 18 || j == 21 ||
                                      j == 22 || j == 26 || j == 27)) {
                                    if (cell != null) {
                                        cellValue =
                                                String.valueOf(commonAM.getCellValue((XSSFCell)cell));
                                        readStrArr[cellSkip] =
                                                (cellValue == null ? "" :
                                                 cellValue);
                                    } else {
                                        readStrArr[cellSkip] = null;
                                    }
                                    cellSkip++;
                                }
                            }
                            uploadRow =
                                    (BoqUploadVORowImpl)boqUploadVo.createRow();
                            uploadRow.setUploadId(seq.getSequenceNumber() +
                                                  "-" + lineNum);
                            uploadRow.setValidationHeaderId(hdrRow.getValidationHeaderId());
                            uploadRow.setLineNumber(String.valueOf(lineNum));
                            uploadRow.setFileName(uploadedFile.getFilename());
                            for (int inx = 0; inx < readStrArr.length; inx++) {
                                attrList =
                                        attrList != null ? attrList : (readStrArr.length ==
                                                                       22 ?
                                                                       getUploadAttributes() :
                                                                       getUpdatedAttributes());
                                uploadRow.setAttribute(attrList.get(inx),
                                                       readStrArr[inx]);
                                System.out.println(lineNum+"  "+inx);
                                System.out.println(attrList.get(inx)+"  "+readStrArr[inx]);

                            }
                            uploadRow.setSellingAmount("0");
                            uploadRow.setCandyNetRate(uploadRow.getSellingRate());
                            uploadRow.setCandyNetFinalAmount(uploadRow.getSellingAmount());
                            uploadRow.setLineSequence(new BigDecimal(lineNum));
                            boqUploadVo.insertRow(uploadRow);
                            this.getDBTransaction().commit();
                        }
                    }
                    message = "Success";

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public String createBOQProjectNumberVL() {
        String message = "";
        try {
            TransBoqUploadvalidationVORowImpl row =
                (TransBoqUploadvalidationVORowImpl)getTransBoqUploadvalidationVO1().getCurrentRow();
            if (row != null) {
                MaxBOQForProjectVOImpl maxBoqVO = getMaxBOQForProjectVO1();
                maxBoqVO.setNamedWhereClauseParam("p_bu_id",
                                                  row.getTransBuId());
                maxBoqVO.setNamedWhereClauseParam("p_prj_id",
                                                  row.getTransPrjId());
                maxBoqVO.executeQuery();
                MaxBOQForProjectVORowImpl maxRow =
                    (MaxBOQForProjectVORowImpl)maxBoqVO.first();
                if (maxRow != null) {
                    row.setTransVersion(maxRow.getVersion().add(new BigDecimal(1)));
                }
                message = "Success";
            }
        } catch (Exception e) {
            message = e.getMessage();
        }
        return message;
    }

    public String createBOQContractNumberVL() {
        String message = "";
        try {
            TransBoqUploadvalidationVORowImpl row =
                (TransBoqUploadvalidationVORowImpl)getTransBoqUploadvalidationVO1().getCurrentRow();
            if (row != null) {
                MaxBOQForContractVOImpl maxBoqVO = getMaxBOQForContractVO1();
                maxBoqVO.setNamedWhereClauseParam("p_bu_id",
                                                  row.getTransBuId());
                maxBoqVO.setNamedWhereClauseParam("p_contract_id",
                                                  row.getTransContractId());
                maxBoqVO.executeQuery();
                MaxBOQForContractVORowImpl maxRow =
                    (MaxBOQForContractVORowImpl)maxBoqVO.first();
                if (maxRow != null) {
                    row.setTransVersion(maxRow.getVersion().add(new BigDecimal(1)));
                }
                message = "Success";
            }
        } catch (Exception e) {
            message = e.getMessage();
        }
        return message;
    }

    public String validateCostCode() {
        String message = "";
        try {
            message = "Success";
            BoqValidationLinesVORowImpl lineRow =
                (BoqValidationLinesVORowImpl)getBoqValidationLinesVO1().getCurrentRow();
            BoqvalidationCostCodeVORowImpl costCodeLine =
                (BoqvalidationCostCodeVORowImpl)getBoqvalidationCostCodeVO1().getCurrentRow();
            BoqvalidationCostCodeVOImpl boqLines =
                getBoqvalidationCostCodeVO3();
            boqLines.applyViewCriteria(null);
            boqLines.executeQuery();
            boqLines.setNamedWhereClauseParam("p_boq_validation_line_id",
                                              lineRow.getValidationLineId());
            boqLines.setNamedWhereClauseParam("p_cost_code",
                                              ADFContext.getCurrent().getPageFlowScope().get("CurrentCostCode"));
            boqLines.setNamedWhereClauseParam("p_validation_cost_code_id",
                                              costCodeLine.getValidationCostCodeId());
            boqLines.setApplyViewCriteriaName("CostCodeValidateCriteria");
            boqLines.executeQuery();
            RowSetIterator iter = boqLines.createRowSetIterator(null);
            if (iter.hasNext()) {
                message = "Cost code already exist for the line.";
                costCodeLine.setCostCode(null);
            }
        } catch (Exception e) {
            message = e.getMessage();
        }
        return message;
    }


    /**
     * Container's getter for BoqValidationHeaderSearchVO1.
     * @return BoqValidationHeaderSearchVO1
     */
    public ViewObjectImpl getBoqValidationHeaderSearchVO1() {
        return (ViewObjectImpl)findViewObject("BoqValidationHeaderSearchVO1");
    }

    /**
     * Container's getter for TransBoqUploadvalidationVO1.
     * @return TransBoqUploadvalidationVO1
     */
    public TransBoqUploadvalidationVOImpl getTransBoqUploadvalidationVO1() {
        return (TransBoqUploadvalidationVOImpl)findViewObject("TransBoqUploadvalidationVO1");
    }

    public void refreshBoqValidateHdrVO() {
//        getBoqValidationHeaderVO1().executeQuery();

        getBoqValidationLinesVO1().executeQuery();

    }

    public Boolean makeAsCurrentBoqValHdrRow(Number boqValHdrId) {
        Boolean updated = null;
        if (boqValHdrId != null) {
            getBoqValidationHeaderVO1().setNamedWhereClauseParam("p_validation_hdr_id", boqValHdrId);
            getBoqValidationHeaderVO1().executeQuery();
//            CommonUtilsAMImpl commAM = (CommonUtilsAMImpl)getCommonUtilsAM1();
//            commAM.makeAsCurrentRow(getBoqValidationHeaderVO1(), boqValHdrId);
//            commAM.makeAsCurrentRow(getBoqValidationHeaderVO1(), boqValHdrId);
            if (getBoqValidationHeaderVO1().getCurrentRow() != null) {
                BoqValidationHeaderVORowImpl row =
                    (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().getCurrentRow();
                if (row.getValidationHeaderId().doubleValue() ==
                    boqValHdrId.doubleValue()) {
                    updated = true;
                }
            }else if(getBoqValidationHeaderVO1().hasNext()){
                getBoqValidationHeaderVO1().next();
                updated = true;
            }else if(getBoqValidationHeaderVO1().first()!=null){
                getBoqValidationHeaderVO1().setNamedWhereClauseParam("p_validation_hdr_id", boqValHdrId);
                getBoqValidationHeaderVO1().executeQuery();
            }

            }
        return updated;
    }

    public void initTransBoqUploadValVO() {
        TransBoqUploadvalidationVORowImpl transRow =
            (TransBoqUploadvalidationVORowImpl)getTransBoqUploadvalidationVO1().createRow();
        transRow.setTransBudgetType("Master Budget");
        transRow.setTransVersion(new BigDecimal(0));
        getTransBoqUploadvalidationVO1().insertRow(transRow);

    }

    public void initTransBoqUploadRevisionVO() {
        TransBoqUploadvalidationVORowImpl transRow =
            (TransBoqUploadvalidationVORowImpl)getTransBoqUploadvalidationVO2().createRow();
        transRow.setTransBudgetType("Revised Budget");
        //        transRow.setTransVersion(new BigDecimal(0));
        getTransBoqUploadvalidationVO2().insertRow(transRow);

    }

    public Boolean overrideExistingUploadDocWith(Number validationHdrId,
                                                 String overrideWith) {
        Boolean isUpdated = false;
        if (validationHdrId != null && overrideWith != null) {
            CommonUtilsAMImpl commAM = (CommonUtilsAMImpl)getCommonUtilsAM1();
            if (commAM != null) {
                Boolean isCurrRowSetted =
                    commAM.makeAsCurrentRow(getBoqValidationHeaderSearchVO1(),
                                            validationHdrId);
                if (isCurrRowSetted) {
                    BoqValidationHeaderSearchVORowImpl hdrRow =
                        (BoqValidationHeaderSearchVORowImpl)getBoqValidationHeaderSearchVO1().getCurrentRow();
                    hdrRow.setStatus(overrideWith);
                    isUpdated = true;

                }
            }
        }
        return isUpdated;
    }


    public Number getPrjBoqMaxValidationDocHdrIdFromTbl() {
        Number docHdrId = null;
        if (getTransBoqUploadvalidationVO2().getCurrentRow() != null) {
            TransBoqUploadvalidationVORowImpl transRow =
                (TransBoqUploadvalidationVORowImpl)getTransBoqUploadvalidationVO2().getCurrentRow();
            MaxValidationHdrIdFromBoqTblSearchVOImpl maxHdrSerVO =
                getMaxValidationHdrIdFromBoqTblSearchVO1();
            maxHdrSerVO.setNamedWhereClauseParam("p_prj_id",
                                                 transRow.getTransPrjId());
            maxHdrSerVO.setNamedWhereClauseParam("p_bu_id",
                                                 transRow.getTransBuId());
            maxHdrSerVO.executeQuery();
            if (maxHdrSerVO.first() != null) {
                MaxPrjBoqValidationDocHdrIDSearchVORowImpl maxPrjBoqValHdrId =
                    (MaxPrjBoqValidationDocHdrIDSearchVORowImpl)maxHdrSerVO.first();
                if (maxPrjBoqValHdrId.getMaxprjvalidationhdrid() != null) {
                    docHdrId =
                            new Number(maxPrjBoqValHdrId.getMaxprjvalidationhdrid().longValue());
                }
            }
        }


        return docHdrId;
    }

    public Number getPrjBoqMaxValidationDocHdrIdFromUploadTbl() {
        Number docHdrId = null;
        if (getTransBoqUploadvalidationVO2().getCurrentRow() != null) {
            TransBoqUploadvalidationVORowImpl transRow =
                (TransBoqUploadvalidationVORowImpl)getTransBoqUploadvalidationVO2().getCurrentRow();
            MaxPrjBoqValidationDocHdrIDSearchVOImpl maxHdrSerVO =
                getMaxPrjBoqValidationDocHdrIDSearchVO1();
            maxHdrSerVO.setNamedWhereClauseParam("p_prj_id",
                                                 transRow.getTransPrjId());
            maxHdrSerVO.setNamedWhereClauseParam("p_bu_id",
                                                 transRow.getTransBuId());
            maxHdrSerVO.executeQuery();
            if (maxHdrSerVO.first() != null) {
                MaxPrjBoqValidationDocHdrIDSearchVORowImpl maxPrjBoqValHdrId =
                    (MaxPrjBoqValidationDocHdrIDSearchVORowImpl)maxHdrSerVO.first();
                if (maxPrjBoqValHdrId.getMaxprjvalidationhdrid() != null) {
                    docHdrId =
                            new Number(maxPrjBoqValHdrId.getMaxprjvalidationhdrid().longValue());
                }
            }
        }


        return docHdrId;
    }

    public Boolean isPrevBoqValidationDocApproved(Number prjBoqValId) {
        Boolean isApproved = false;
        if (prjBoqValId != null) {


            GetPrjBoqValidationStatusForHdrIdSearchVOImpl prjBoqValDocStatusVO =
                getGetPrjBoqValidationStatusForHdrIdSearchVO1();
            prjBoqValDocStatusVO.setNamedWhereClauseParam("p_vald_hdr_id",
                                                          prjBoqValId);

            prjBoqValDocStatusVO.executeQuery();

            if (prjBoqValDocStatusVO.first() != null) {
                GetPrjBoqValidationStatusForHdrIdSearchVORowImpl statusRow =
                    (GetPrjBoqValidationStatusForHdrIdSearchVORowImpl)prjBoqValDocStatusVO.first();
                if ("Approved".equals(statusRow.getStatus())) {
                    isApproved = true;
                }

            }
        }

        return isApproved;
    }

    public Boolean isBoqDocExistsForProjectInUpldTbl() {
        Boolean isExists = false;
        if (getTransBoqUploadvalidationVO1().getCurrentRow() != null) {
            TransBoqUploadvalidationVORowImpl transRow =
                (TransBoqUploadvalidationVORowImpl)getTransBoqUploadvalidationVO1().getCurrentRow();
            GetCountOfValidDocInUploadTblSearchVOImpl getDocCountVO =
                getGetCountOfValidDocInUploadTblSearchVO1();
            getDocCountVO.setNamedWhereClauseParam("p_bu_id",
                                                   transRow.getTransBuId());
            getDocCountVO.setNamedWhereClauseParam("p_prj_id",
                                                   transRow.getTransPrjId());
            getDocCountVO.setNamedWhereClauseParam("p_budget_type",
                                                   transRow.getTransBudgetTypeCode());
            getDocCountVO.executeQuery();

            if (getDocCountVO.first() != null) {
                GetCountOfValidDocInUploadTblSearchVORowImpl countRow =
                    (GetCountOfValidDocInUploadTblSearchVORowImpl)getDocCountVO.first();
                if (countRow.getRowcount().intValue() > 0) {
                    isExists = true;
                }
            }
        }
        return isExists;
    }

    public Boolean isBoqDocExistsForProject() {
        Boolean isExists = false;
        if (getTransBoqUploadvalidationVO1().getCurrentRow() != null) {
            TransBoqUploadvalidationVORowImpl transRow =
                (TransBoqUploadvalidationVORowImpl)getTransBoqUploadvalidationVO1().getCurrentRow();
            GetCountOfValidDocsForProjectSearchVOImpl getDocCountVO =
                getGetCountOfValidDocsForProjectSearchVO1();
            getDocCountVO.setNamedWhereClauseParam("p_bu_id",
                                                   transRow.getTransBuId());
            getDocCountVO.setNamedWhereClauseParam("p_prj_id",
                                                   transRow.getTransPrjId());
            getDocCountVO.executeQuery();

            if (getDocCountVO.first() != null) {
                GetCountOfValidDocsForProjectSearchVORowImpl countRow =
                    (GetCountOfValidDocsForProjectSearchVORowImpl)getDocCountVO.first();
                if (countRow.getRowcount().intValue() > 0) {
                    isExists = true;
                }
            }
        }
        return isExists;
    }

    public Number createBoqUploadValidationHdrRow() {
        Number boqUploadHdrId = null;
        try {
            if (getTransBoqUploadvalidationVO1().getCurrentRow() != null) {
                TransBoqUploadvalidationVORowImpl transRow =
                    (TransBoqUploadvalidationVORowImpl)getTransBoqUploadvalidationVO1().getCurrentRow();
                BoqValidationHeaderVORowImpl boqValHdrRow =
                    (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().createRow();
                boqValHdrRow.setBusinessUnitId(new Number(transRow.getTransBuId()));
                boqValHdrRow.setProjectId(new Number(transRow.getTransPrjId()));
                boqValHdrRow.setVersion(new Number(0));
                boqValHdrRow.setIbcNumber(transRow.getTransIbcNumber());
                boqValHdrRow.setStatus("Draft");
                boqValHdrRow.setIsDocumentValid("InValid");
                boqValHdrRow.setBudgetType(transRow.getTransBudgetType());
                getBoqValidationHeaderVO1().insertRow(boqValHdrRow);
                boqUploadHdrId = boqValHdrRow.getValidationHeaderId();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return boqUploadHdrId;
    }

    private Integer getLatestPrjBoqVersionFromValidHdr() {
        Integer versionNum = null;
        if (getTransBoqUploadvalidationVO2().getCurrentRow() != null) {
            TransBoqUploadvalidationVORowImpl transRow =
                (TransBoqUploadvalidationVORowImpl)getTransBoqUploadvalidationVO2().getCurrentRow();
            GetVersionNoForLatestPrjSearchVOImpl versionVO =
                getGetVersionNoForLatestPrjSearchVO1();
            versionVO.setNamedWhereClauseParam("p_prj_id",
                                               transRow.getTransPrjId());
            versionVO.setNamedWhereClauseParam("p_bu_id",
                                               transRow.getTransBuId());
            versionVO.executeQuery();
            if (versionVO.first() != null) {
                GetVersionNoForLatestPrjSearchVORowImpl versonRow =
                    (GetVersionNoForLatestPrjSearchVORowImpl)versionVO.first();
                versionNum = versonRow.getVersion().intValue();
            }
        }

        return versionNum;
    }

    public Number createBoqRevisedValidationHdrRow() {
        Number boqUploadHdrId = null;
        try {
            if (getTransBoqUploadvalidationVO2().getCurrentRow() != null) {
                TransBoqUploadvalidationVORowImpl transRow =
                    (TransBoqUploadvalidationVORowImpl)getTransBoqUploadvalidationVO2().getCurrentRow();
                Integer prevVersionNum = getLatestPrjBoqVersionFromValidHdr();
                if (prevVersionNum != null) {
                    int newVersion = prevVersionNum.intValue();
                    BoqValidationHeaderVORowImpl boqValHdrRow =
                        (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().createRow();
                    boqValHdrRow.setBusinessUnitId(new Number(transRow.getTransBuId()));
                    boqValHdrRow.setProjectId(new Number(transRow.getTransPrjId()));
                    boqValHdrRow.setVersion(new Number(++newVersion));
                    boqValHdrRow.setIbcNumber(transRow.getTransIbcNumber());
                    boqValHdrRow.setStatus("Draft");
                    boqValHdrRow.setRevisionDate(new Date(new java.sql.Date(System.currentTimeMillis())));
                    boqValHdrRow.setBudgetType(transRow.getTransBudgetType());
                    getBoqValidationHeaderVO1().insertRow(boqValHdrRow);
                    boqUploadHdrId = boqValHdrRow.getValidationHeaderId();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boqUploadHdrId;
    }

    public String validateVariationLines() {
        String message = "";
        try {
            message = "Success";
            BoqValidationLinesVOImpl lines =
                (BoqValidationLinesVOImpl)getBoqValidationLinesVO1();
            BoqValidationLinesVORowImpl linesRow =
                (BoqValidationLinesVORowImpl)lines.getCurrentRow();
            String variationFlag =
                (String)ADFContext.getCurrent().getPageFlowScope().get("variationFlag");
            String state = "";
            if ("A".equals(variationFlag)) {
                state = linesRow.getApproved();
                if ("N".equals(state)) {
                    message = "The line can't be unchecked";
                } else {
                    linesRow.setAnticipatedValue(null);
                    linesRow.setEstimated("N");
                    linesRow.setSubmitted("N");
                }
            } else if ("S".equals(variationFlag)) {
                state = linesRow.getSubmitted();
                if ("N".equals(state)) {
                    message = "The line can't be unchecked";
                } else {
                    linesRow.setApproved("N");
                    linesRow.setEstimated("N");
                }
            } else if ("E".equals(variationFlag)) {
                String isUsedInWD = linesRow.getTransDeleteFlag();
                state = linesRow.getEstimated();
                if ("N".equals(state)) {
                    message = "The line can't be unchecked";
                } else if ("N".equals(isUsedInWD)) {
                    message = "Line is already used in work done.";
                } else {
                    linesRow.setApproved("N");
                    linesRow.setSubmitted("N");
                }
            }
        } catch (Exception e) {
            message = e.getMessage();
        }
        return message;
    }

    public String updateLedgerCurrencyConvRate() {
        String message = "Success";
        try {
            BoqValidationHeaderVORowImpl hdrRow =
                (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().getCurrentRow();
            if (hdrRow.getCurrencyCode().equals(hdrRow.getCurrencyCode1())) {
                hdrRow.setLedgerCurrencyConvRate(new BigDecimal(1));
            } else {
                CurrencyConversionVOImpl currVO = getCurrencyConversionVO1();
                currVO.setNamedWhereClauseParam("p_from_currency",
                                                hdrRow.getCurrencyCode());
                currVO.setNamedWhereClauseParam("p_to_currency",
                                                hdrRow.getCurrencyCode1());
                currVO.setNamedWhereClauseParam("p_conversion_date",
                                                hdrRow.getLedgerCurrencyConvDate());
                currVO.setNamedWhereClauseParam("p_conversion_type",
                                                hdrRow.getTransLedgerCurrencyConvRateType());
                currVO.executeQuery();
                CurrencyConversionVORowImpl currRow =
                    (CurrencyConversionVORowImpl)currVO.first();
                if (currRow == null) {
                    message =
                            "Conversion is not defined for the selected rate type and date.";
                } else {
                    hdrRow.setLedgerCurrencyConvRate(currRow.getConversionRate());
                }
            }
        } catch (Exception e) {
            message = e.getMessage();
        }
        return message;
    }

    public String deleteBOQLine() {
        String message = null;
        try {
            message = "Success";
            BoqValidationLinesVOImpl validationLinesVO =
                (BoqValidationLinesVOImpl)getBoqValidationLinesVO1();
            BoqValidationLinesVORowImpl validationLine =
                (BoqValidationLinesVORowImpl)validationLinesVO.getCurrentRow();
            Number ValidationLineId =
                (Number)ADFContext.getCurrent().getPageFlowScope().get("ValidationLineId");
            if (ValidationLineId.equals(validationLine.getValidationLineId())) {
                BoqvalidationCostCodeVOImpl costCodesVO =
                    (BoqvalidationCostCodeVOImpl)getBoqvalidationCostCodeVO1();
                RowSetIterator iter = costCodesVO.createRowSetIterator(null);
                while (iter.hasNext()) {
                    BoqvalidationCostCodeVORowImpl row =
                        (BoqvalidationCostCodeVORowImpl)iter.next();
                    if (ValidationLineId.equals(row.getValidationLineId())) {
                        row.remove();
                    }
                }
                validationLine.remove();
            }
        } catch (Exception e) {
            message = e.getMessage();
        }
        return message;
    }

    /**
     * Container's getter for BoqValidationHeaderVO1.
     * @return BoqValidationHeaderVO1
     */
    public BoqValidationHeaderVOImpl getBoqValidationHeaderVO1() {
        return (BoqValidationHeaderVOImpl)findViewObject("BoqValidationHeaderVO1");
    }

    /**
     * Container's getter for CommonUtilsAM1.
     * @return CommonUtilsAM1
     */
    public ApplicationModuleImpl getCommonUtilsAM1() {
        return (ApplicationModuleImpl)findApplicationModule("CommonUtilsAM1");
    }

    /**
     * Container's getter for BoqValidationLinesVO1.
     * @return BoqValidationLinesVO1
     */
    public ViewObjectImpl getBoqValidationLinesVO1() {
        return (ViewObjectImpl)findViewObject("BoqValidationLinesVO1");
    }

    /**
     * Container's getter for BoqValidationHdrToLinesVL1.
     * @return BoqValidationHdrToLinesVL1
     */
    public ViewLinkImpl getBoqValidationHdrToLinesVL1() {
        return (ViewLinkImpl)findViewLink("BoqValidationHdrToLinesVL1");
    }

    /**
     * Container's getter for BoqUploadVO1.
     * @return BoqUploadVO1
     */
    public ViewObjectImpl getBoqUploadVO1() {
        return (ViewObjectImpl)findViewObject("BoqUploadVO1");
    }

    /**
     * Container's getter for BoqvalidationCostCodeVO1.
     * @return BoqvalidationCostCodeVO1
     */
    public ViewObjectImpl getBoqvalidationCostCodeVO1() {
        return (ViewObjectImpl)findViewObject("BoqvalidationCostCodeVO1");
    }

    /**
     * Container's getter for BoqValidationLinesTocostCodeVL1.
     * @return BoqValidationLinesTocostCodeVL1
     */
    public ViewLinkImpl getBoqValidationLinesTocostCodeVL1() {
        return (ViewLinkImpl)findViewLink("BoqValidationLinesTocostCodeVL1");
    }

    /**
     * Container's getter for BoqValidationResourceVO1.
     * @return BoqValidationResourceVO1
     */
    public ViewObjectImpl getBoqValidationResourceVO1() {
        return (ViewObjectImpl)findViewObject("BoqValidationResourceVO1");
    }

    /**
     * Container's getter for BoqValidationCostCodeToResourceVL1.
     * @return BoqValidationCostCodeToResourceVL1
     */
    public ViewLinkImpl getBoqValidationCostCodeToResourceVL1() {
        return (ViewLinkImpl)findViewLink("BoqValidationCostCodeToResourceVL1");
    }

    /**
     * Container's getter for GetCountOfValidDocsForProjectSearchVO1.
     * @return GetCountOfValidDocsForProjectSearchVO1
     */
    public GetCountOfValidDocsForProjectSearchVOImpl getGetCountOfValidDocsForProjectSearchVO1() {
        return (GetCountOfValidDocsForProjectSearchVOImpl)findViewObject("GetCountOfValidDocsForProjectSearchVO1");
    }

    /**
     * Container's getter for TransBoqUploadvalidationVO2.
     * @return TransBoqUploadvalidationVO2
     */
    public TransBoqUploadvalidationVOImpl getTransBoqUploadvalidationVO2() {
        return (TransBoqUploadvalidationVOImpl)findViewObject("TransBoqUploadvalidationVO2");
    }

    /**
     * Container's getter for GetValidatiionPrjDocCountForStatusSearhVO1.
     * @return GetValidatiionPrjDocCountForStatusSearhVO1
     */
    public GetValidatiionPrjDocCountForStatusSearhVOImpl getGetValidatiionPrjDocCountForStatusSearhVO1() {
        return (GetValidatiionPrjDocCountForStatusSearhVOImpl)findViewObject("GetValidatiionPrjDocCountForStatusSearhVO1");
    }


    /**
     * Container's getter for GetPrjBoqValidationStatusForHdrIdSearchVO1.
     * @return GetPrjBoqValidationStatusForHdrIdSearchVO1
     */
    public GetPrjBoqValidationStatusForHdrIdSearchVOImpl getGetPrjBoqValidationStatusForHdrIdSearchVO1() {
        return (GetPrjBoqValidationStatusForHdrIdSearchVOImpl)findViewObject("GetPrjBoqValidationStatusForHdrIdSearchVO1");
    }

    /**
     * Container's getter for MaxPrjBoqValidationDocHdrIDSearchVO1.
     * @return MaxPrjBoqValidationDocHdrIDSearchVO1
     */
    public MaxPrjBoqValidationDocHdrIDSearchVOImpl getMaxPrjBoqValidationDocHdrIDSearchVO1() {
        return (MaxPrjBoqValidationDocHdrIDSearchVOImpl)findViewObject("MaxPrjBoqValidationDocHdrIDSearchVO1");
    }

    /**
     * Container's getter for GetVersionNoForLatestPrjSearchVO1.
     * @return GetVersionNoForLatestPrjSearchVO1
     */
    public GetVersionNoForLatestPrjSearchVOImpl getGetVersionNoForLatestPrjSearchVO1() {
        return (GetVersionNoForLatestPrjSearchVOImpl)findViewObject("GetVersionNoForLatestPrjSearchVO1");
    }

    /**
     * Container's getter for GetErrorsCountOfValidHdrSearchVO1.
     * @return GetErrorsCountOfValidHdrSearchVO1
     */
    public GetErrorsCountOfValidHdrSearchVOImpl getGetErrorsCountOfValidHdrSearchVO1() {
        return (GetErrorsCountOfValidHdrSearchVOImpl)findViewObject("GetErrorsCountOfValidHdrSearchVO1");
    }

    /**
     * Container's getter for BOQLoadedSearchVO1.
     * @return BOQLoadedSearchVO1
     */
    public BOQLoadedSearchVOImpl getBOQLoadedSearchVO1() {
        return (BOQLoadedSearchVOImpl)findViewObject("BOQLoadedSearchVO1");
    }

    /**
     * Container's getter for MaxValidationHdrIdFromBoqTblSearchVO1.
     * @return MaxValidationHdrIdFromBoqTblSearchVO1
     */
    public MaxValidationHdrIdFromBoqTblSearchVOImpl getMaxValidationHdrIdFromBoqTblSearchVO1() {
        return (MaxValidationHdrIdFromBoqTblSearchVOImpl)findViewObject("MaxValidationHdrIdFromBoqTblSearchVO1");
    }

    /**
     * Container's getter for GetCountOfValidDocInUploadTblSearchVO1.
     * @return GetCountOfValidDocInUploadTblSearchVO1
     */
    public GetCountOfValidDocInUploadTblSearchVOImpl getGetCountOfValidDocInUploadTblSearchVO1() {
        return (GetCountOfValidDocInUploadTblSearchVOImpl)findViewObject("GetCountOfValidDocInUploadTblSearchVO1");
    }

    /**
     * Container's getter for MaxValidHdrIdinUploadTblSearchVO1.
     * @return MaxValidHdrIdinUploadTblSearchVO1
     */
    public MaxValidHdrIdinUploadTblSearchVOImpl getMaxValidHdrIdinUploadTblSearchVO1() {
        return (MaxValidHdrIdinUploadTblSearchVOImpl)findViewObject("MaxValidHdrIdinUploadTblSearchVO1");
    }

    /**
     * Container's getter for BoqUploadErrorsVO1.
     * @return BoqUploadErrorsVO1
     */
    public BoqUploadErrorsVOImpl getBoqUploadErrorsVO1() {
        return (BoqUploadErrorsVOImpl)findViewObject("BoqUploadErrorsVO1");
    }

    /**
     * Container's getter for ValidationHdrToErrorsVL1.
     * @return ValidationHdrToErrorsVL1
     */
    public ViewLinkImpl getValidationHdrToErrorsVL1() {
        return (ViewLinkImpl)findViewLink("ValidationHdrToErrorsVL1");
    }


    /**
     * Container's getter for ErrorBOQSearchVO1.
     * @return ErrorBOQSearchVO1
     */
    public ErrorBOQSearchVOImpl getErrorBOQSearchVO1() {
        return (ErrorBOQSearchVOImpl)findViewObject("ErrorBOQSearchVO1");
    }

    /**
     * Container's getter for ValidationLideIdForResourceSearchVO1.
     * @return ValidationLideIdForResourceSearchVO1
     */
    public ValidationLideIdForResourceSearchVOImpl getValidationLideIdForResourceSearchVO1() {
        return (ValidationLideIdForResourceSearchVOImpl)findViewObject("ValidationLideIdForResourceSearchVO1");
    }

    /**
     * Container's getter for ValidationLineIdForCostCodeSearchVO1.
     * @return ValidationLineIdForCostCodeSearchVO1
     */
    public ValidationLineIdForCostCodeSearchVOImpl getValidationLineIdForCostCodeSearchVO1() {
        return (ValidationLineIdForCostCodeSearchVOImpl)findViewObject("ValidationLineIdForCostCodeSearchVO1");
    }

    /**
     * Container's getter for UploadedErrorsCountSearchVO1.
     * @return UploadedErrorsCountSearchVO1
     */
    public UploadedErrorsCountSearchVOImpl getUploadedErrorsCountSearchVO1() {
        return (UploadedErrorsCountSearchVOImpl)findViewObject("UploadedErrorsCountSearchVO1");
    }

    /**
     * Container's getter for MaxBoqUpldBuPrjIdScrVO1.
     * @return MaxBoqUpldBuPrjIdScrVO1
     */
    public MaxBoqUpldBuPrjIdScrVOImpl getMaxBoqUpldBuPrjIdScrVO1() {
        return (MaxBoqUpldBuPrjIdScrVOImpl)findViewObject("MaxBoqUpldBuPrjIdScrVO1");
    }

    /**
     * Container's getter for GetBoqUploadedRowScrVO1.
     * @return GetBoqUploadedRowScrVO1
     */
    public GetBoqUploadedRowScrVOImpl getGetBoqUploadedRowScrVO1() {
        return (GetBoqUploadedRowScrVOImpl)findViewObject("GetBoqUploadedRowScrVO1");
    }

    /**
     * Container's getter for GetMasterBowHdrDocForValidIdScrVO1.
     * @return GetMasterBowHdrDocForValidIdScrVO1
     */
    public GetMasterBowHdrDocForValidIdScrVOImpl getGetMasterBowHdrDocForValidIdScrVO1() {
        return (GetMasterBowHdrDocForValidIdScrVOImpl)findViewObject("GetMasterBowHdrDocForValidIdScrVO1");
    }

    /**
     * Container's getter for IsErrorExistsIUploadedBoqsScrVO1.
     * @return IsErrorExistsIUploadedBoqsScrVO1
     */
    public IsErrorExistsIUploadedBoqsScrVOImpl getIsErrorExistsIUploadedBoqsScrVO1() {
        return (IsErrorExistsIUploadedBoqsScrVOImpl)findViewObject("IsErrorExistsIUploadedBoqsScrVO1");
    }

    public String submitBoqValidationRequestToAME() {
        CommonUtilsAMImpl commonAM = (CommonUtilsAMImpl)getCommonUtilsAM1();
        BoqValidationHeaderVORowImpl hdrRow =
            (BoqValidationHeaderVORowImpl)getBoqValidationHeaderVO1().getCurrentRow();

        String msg = "";
        if (hdrRow != null) {
            Map paramsMap = new HashMap();
            paramsMap.put("docId",
                          new oracle.jbo.domain.Number(hdrRow.getValidationHeaderId()));

            paramsMap.put("docTypeId", new oracle.jbo.domain.Number(10));
            paramsMap.put("ActionCode", "Submit");
            paramsMap.put("currEmpId", commonAM.getCurrentEmployeeId());
            paramsMap.put("remarks", "");
            paramsMap.put("docNum", hdrRow.getProjectNumber());
            msg = commonAM.callAMEProcess(paramsMap);
            if (msg != null) {
                if ("Finally Approved".equals(msg)) { //FYI approval only
                    hdrRow.setStatus("A");
                } else if ("Submitted".equals(msg)) {
                    hdrRow.setStatus("I");
                }
            }
        }
        return msg;
    }


    /**
     * Container's getter for AttachmentsVO1.
     * @return AttachmentsVO1
     */
    public AttachmentsVOImpl getAttachmentsVO1() {
        return (AttachmentsVOImpl)findViewObject("AttachmentsVO1");
    }

    /**
     * Container's getter for BOQUploadToAttachmenstSVL1.
     * @return BOQUploadToAttachmenstSVL1
     */
    public ViewLinkImpl getBOQUploadToAttachmenstSVL1() {
        return (ViewLinkImpl)findViewLink("BOQUploadToAttachmenstSVL1");
    }

    /**
     * Container's getter for BoqValidationRejectionLinesVO1.
     * @return BoqValidationRejectionLinesVO1
     */
    public BoqValidationRejectionLinesVOImpl getBoqValidationRejectionLinesVO1() {
        return (BoqValidationRejectionLinesVOImpl)findViewObject("BoqValidationRejectionLinesVO1");
    }

    /**
     * Container's getter for BOQValidationHDRtoRejectionLinesVL1.
     * @return BOQValidationHDRtoRejectionLinesVL1
     */
    public ViewLinkImpl getBOQValidationHDRtoRejectionLinesVL1() {
        return (ViewLinkImpl)findViewLink("BOQValidationHDRtoRejectionLinesVL1");
    }

    /**
     * Container's getter for InvoiceCummQtyBOQValidateVO1.
     * @return InvoiceCummQtyBOQValidateVO1
     */
    public InvoiceCummQtyBOQValidateVOImpl getInvoiceCummQtyBOQValidateVO1() {
        return (InvoiceCummQtyBOQValidateVOImpl)findViewObject("InvoiceCummQtyBOQValidateVO1");
    }

    /**
     * Container's getter for BOQHeaderVersionLOVVO1.
     * @return BOQHeaderVersionLOVVO1
     */
    public BOQHeaderVersionLOVVOImpl getBOQHeaderVersionLOVVO1() {
        return (BOQHeaderVersionLOVVOImpl)findViewObject("BOQHeaderVersionLOVVO1");
    }

    /**
     * Container's getter for BoqValidationLinesVO2.
     * @return BoqValidationLinesVO2
     */
    public BoqValidationLinesVOImpl getBoqValidationLinesVO2() {
        return (BoqValidationLinesVOImpl)findViewObject("BoqValidationLinesVO2");
    }

    /**
     * Container's getter for BoqValidationHdrToLinesVL2.
     * @return BoqValidationHdrToLinesVL2
     */
    public ViewLinkImpl getBoqValidationHdrToLinesVL2() {
        return (ViewLinkImpl)findViewLink("BoqValidationHdrToLinesVL2");
    }

    /**
     * Container's getter for BoqvalidationCostCodeVO2.
     * @return BoqvalidationCostCodeVO2
     */
    public BoqvalidationCostCodeVOImpl getBoqvalidationCostCodeVO2() {
        return (BoqvalidationCostCodeVOImpl)findViewObject("BoqvalidationCostCodeVO2");
    }

    /**
     * Container's getter for BoqValidationLinesTocostCodeVL2.
     * @return BoqValidationLinesTocostCodeVL2
     */
    public ViewLinkImpl getBoqValidationLinesTocostCodeVL2() {
        return (ViewLinkImpl)findViewLink("BoqValidationLinesTocostCodeVL2");
    }

    /**
     * Container's getter for PriceCodeWithoutCostCodeVO1.
     * @return PriceCodeWithoutCostCodeVO1
     */
    public PriceCodeWithoutCostCodeVOImpl getPriceCodeWithoutCostCodeVO1() {
        return (PriceCodeWithoutCostCodeVOImpl)findViewObject("PriceCodeWithoutCostCodeVO1");
    }

    /**
     * Container's getter for BoqExportPrintReportVO1.
     * @return BoqExportPrintReportVO1
     */
    public BoqExportPrintReportVOImpl getBoqExportPrintReportVO1() {
        return (BoqExportPrintReportVOImpl)findViewObject("BoqExportPrintReportVO1");
    }

    /**
     * Container's getter for BoqvalidationCostCodeVO3.
     * @return BoqvalidationCostCodeVO3
     */
    public BoqvalidationCostCodeVOImpl getBoqvalidationCostCodeVO3() {
        return (BoqvalidationCostCodeVOImpl)findViewObject("BoqvalidationCostCodeVO3");
    }

    /**
     * Container's getter for BoqValidationLinesTocostCodeVL3.
     * @return BoqValidationLinesTocostCodeVL3
     */
    public ViewLinkImpl getBoqValidationLinesTocostCodeVL3() {
        return (ViewLinkImpl)findViewLink("BoqValidationLinesTocostCodeVL3");
    }

    /**
     * Container's getter for MaxBOQForProjectVO1.
     * @return MaxBOQForProjectVO1
     */
    public MaxBOQForProjectVOImpl getMaxBOQForProjectVO1() {
        return (MaxBOQForProjectVOImpl)findViewObject("MaxBOQForProjectVO1");
    }

    /**
     * Container's getter for MaxBOQForContractVO1.
     * @return MaxBOQForContractVO1
     */
    public MaxBOQForContractVOImpl getMaxBOQForContractVO1() {
        return (MaxBOQForContractVOImpl)findViewObject("MaxBOQForContractVO1");
    }

    /**
     * Container's getter for MaxBoqUpldBuContractIdScrVO1.
     * @return MaxBoqUpldBuContractIdScrVO1
     */
    public MaxBoqUpldBuContractIdScrVOImpl getMaxBoqUpldBuContractIdScrVO1() {
        return (MaxBoqUpldBuContractIdScrVOImpl)findViewObject("MaxBoqUpldBuContractIdScrVO1");
    }

    /**
     * Container's getter for CurrencyConversionVO1.
     * @return CurrencyConversionVO1
     */
    public CurrencyConversionVOImpl getCurrencyConversionVO1() {
        return (CurrencyConversionVOImpl)findViewObject("CurrencyConversionVO1");
    }

    /**
     * Container's getter for MaxPCForContractVO1.
     * @return MaxPCForContractVO1
     */
    public MaxPCForContractVOImpl getMaxPCForContractVO1() {
        return (MaxPCForContractVOImpl)findViewObject("MaxPCForContractVO1");
    }
}
