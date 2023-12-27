package model.applicationmodule;

//import com.bea.sslplus.extensions.CertificatePoliciesImpl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;

import java.math.BigDecimal;

import java.sql.CallableStatement;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.faces.application.FacesMessage;

import model.applicationmodule.common.BillingDetailsAM;

import model.reportview.InvoiceDetailsReportVOImpl;

import model.searchview.CalculateCertifiedValuesVOImpl;
import model.searchview.CalculateCertifiedValuesVORowImpl;
import model.searchview.CalculateInvoiceValuesVORowImpl;
import model.searchview.CalculateInvoiceValuesVOImpl;
import model.searchview.CertifiedLinesPopulateVOImpl;
import model.searchview.CertifiedLinesPopulateVORowImpl;
import model.searchview.CertifiedQtySearchVOImpl;
import model.searchview.CertifiedQtySearchVORowImpl;
import model.searchview.ContractAdvanceAmtVOImpl;
import model.searchview.ContractAdvanceAmtVORowImpl;
import model.searchview.CurrentBoqHdrScrVOImpl;
import model.searchview.CurrentBoqHdrScrVORowImpl;
import model.searchview.CurrentWdHdrVOImpl;
import model.searchview.CurrentWdHdrVORowImpl;
import model.searchview.CurrentInvoicedDocScrVOImpl;
import model.searchview.CurrentInvoicedDocScrVORowImpl;
import model.searchview.GetMaxCertPCHeaderIdVOImpl;
import model.searchview.GetMaxCertPCHeaderIdVORowImpl;
import model.searchview.InvoicedLInesPopulateVOImpl;
import model.searchview.InvoicedLInesPopulateVORowImpl;
import model.searchview.InvoicedQtySearchVOImpl;
import model.searchview.InvoicedQtySearchVORowImpl;
import model.searchview.IsCertDocScrVOImpl;
import model.searchview.IsCertDocScrVORowImpl;
import model.searchview.IsInvoiceDocCertifiedImpl;
import model.searchview.IsInvoiceDocCertifiedRowImpl;
import model.searchview.IsWDDocInvoicedScrVOImpl;
import model.searchview.IsWDDocInvoicedScrVORowImpl;
import model.searchview.MaterialAtSiteSearchVOImpl;
import model.searchview.MaterialAtSiteSearchVORowImpl;
import model.searchview.MaxCertBOQDocuemntScrVOImpl;
import model.searchview.MaxCertBOQDocuemntScrVORowImpl;
import model.searchview.MaxCertBOQHdrIdScrVOImpl;
import model.searchview.MaxCertBOQHdrIdScrVORowImpl;
import model.searchview.MaxCertifiedDocumentNumberImpl;
import model.searchview.MaxINVBOQDocumentScrVOImpl;
import model.searchview.MaxINVBOQDocumentScrVORowImpl;
import model.searchview.MaxINVBOQHdrIdScrVOImpl;
import model.searchview.MaxINVBOQHdrIdScrVORowImpl;
import model.searchview.MaxInvoicedDocumentNumberImpl;
import model.searchview.MaxWDBOQDocScrVOImpl;
import model.searchview.MaxWDBOQDocScrVORowImpl;
import model.searchview.MaxWDBOQDocumentScrVOImpl;
import model.searchview.MaxWDBOQDocumentScrVORowImpl;
import model.searchview.MaxWDBOQHdrIdScrVOImpl;
import model.searchview.MaxWDBOQHdrIdScrVORowImpl;
import model.searchview.MaxWdDocumentNumberImpl;
import model.searchview.PCDocTypeCountsScrVOImpl;
import model.searchview.PCLinesSearchWithProjectIdVOImpl;
import model.searchview.PCLinesSearchWithProjectIdVORowImpl;
import model.searchview.PCPrintReportVOImpl;
import model.searchview.ProjectSearchVOImpl;
import model.searchview.ProjectSearchVORowImpl;
import model.searchview.WorkDoneLInesPopulateVOImpl;
import model.searchview.WorkDoneLInesPopulateVORowImpl;
import model.searchview.WorkDoneQtySearchVOImpl;

import model.searchview.WorkDoneQtySearchVORowImpl;

import model.services.ExternalReportCustom;

import model.transview.TransCertifiedDocHdrVOImpl;
import model.transview.TransCertifiedDocHdrVORowImpl;
import model.transview.TransInvoiceHdrVOImpl;
import model.transview.TransInvoiceHdrVORowImpl;
import model.transview.TransPCHdrVOImpl;
import model.transview.TransPCHdrVORowImpl;
import model.transview.TransWorkdoneHdrVOImpl;

import model.transview.TransWorkdoneHdrVORowImpl;

import model.view.AttachmentsVOImpl;
import model.view.AttachmentsVORowImpl;
import model.view.BoqValidationHeaderVOImpl;
import model.view.BoqValidationHeaderVORowImpl;
import model.view.CertifiedQtyDetailsVOImpl;
import model.view.CertifiedQtyDetailsVORowImpl;
import model.view.CertifiedQtyLinesVOImpl;
import model.view.CertifiedQtyLinesVORowImpl;
import model.view.CertifiedUploadIntVOImpl;
import model.view.CertifiedUploadIntVORowImpl;
import model.view.InvoicedQtyDetailsVOImpl;
import model.view.InvoicedQtyDetailsVORowImpl;
import model.view.InvoicedQtyLinesVOImpl;
import model.view.InvoicedQtyLinesVORowImpl;
import model.view.InvoicedUploadIntVOImpl;
import model.view.InvoicedUploadIntVORowImpl;
import model.view.MaterialAtSiteHdrVOImpl;
import model.view.MaterialAtSiteHdrVORowImpl;
import model.view.MaterialAtSiteLinesVOImpl;
import model.view.PaymentCertHdrVORowImpl;
import model.view.ProgressDetailsHdrVOImpl;
import model.view.ProgressDetailsHdrVORowImpl;
import model.view.ProgressDetailsLinesVOImpl;
import model.view.WorkDoneQtyDetailsVOImpl;

import model.view.WorkDoneQtyDetailsVORowImpl;
import model.view.WorkDoneQtyLineDetailsVOImpl;
import model.view.WorkDoneQtyLineDetailsVORowImpl;
import model.view.WorkDoneQtyLinesVOImpl;

import model.view.WorkDoneQtyLinesVORowImpl;


import model.view.WorkDoneUploadIntVOImpl;
import model.view.WorkDoneUploadIntVORowImpl;

import oracle.adf.share.ADFContext;

import oracle.jbo.JboException;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.XMLInterface;
import oracle.jbo.domain.BlobDomain;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.RowQualifier;
import oracle.jbo.server.ViewLinkImpl;
import oracle.jbo.server.ViewObjectImpl;

import oracle.xml.parser.v2.XMLNode;

import org.apache.myfaces.trinidad.model.UploadedFile;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utils.system;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Jan 03 17:39:49 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class BillingDetailsAMImpl extends ApplicationModuleImpl
  implements BillingDetailsAM {
    private final String validate_work_done_period =
        "begin xxboq_bill_of_quantity_pkg.validate_work_done_period" +
        "(:p_contract_id,:p_period,:p_flag);end;";
    private final String validate_invoiced_period =
        "begin xxboq_bill_of_quantity_pkg.validate_invoiced_period" +
        "(:p_prj_id,:p_period,:p_flag);end;";
    private final String validate_certified_period =
        "begin xxboq_bill_of_quantity_pkg.validate_certified_period" +
        "(:p_contract_id,:p_period,:p_flag);end;";
    private final String POPULATE_REVISE_DOC_WD =
        "begin xxboq_bill_of_quantity_pkg.REVISE_WD_HDR" +
        "(:p_header_id,:p_username,:p_out_hdr_id,:p_message);end;";
    private final String populate_revise_doc_Invoice =
        "begin xxboq_bill_of_quantity_pkg.populate_revise_doc_Invoice" +
        "(:p_header_id,:p_username,:p_out_hdr_id);end;";


    private final String REVISE_CERT_BOQ_HDR =
        "begin xxboq_bill_of_quantity_pkg.REVISE_CERT_BOQ_HDR" +
        "(:p_header_id,:p_username,:p_out_hdr_id,:p_message);end;";


    private final String UPDATE_BOQ_QTY_HDR_STATUS =
        "begin xxboq_boq_candy_pkg.UPDATE_BOQ_QTY_HDR_STATUS" +
        "(:p_hdr_id,:p_from);end;";

    private final String validate_material_site_hdr =
        "begin xxboq_bill_of_quantity_pkg.validate_material_site_hdr" +
        "(:p_bu_id,:p_prj_id,:p_period,:p_flag);end;";
    private final String revise_material_site_doc =
        "begin xxboq_bill_of_quantity_pkg.revise_material_site_doc" +
        "(:p_header_id,:p_username,:p_out_hdr_id);end;";
    private final String EXTEND_WD_BOQ_LINES =
        "begin xxboq_bill_of_quantity_pkg.EXTEND_WD_LINES" +
        "(:p_prev_hdr_id,:p_curr_hdr_id,:p_user,:p_message);end;";
    private final String EXTEND_INVOICE_LINES =
        "begin xxboq_bill_of_quantity_pkg.EXTEND_INVOICE_LINES" +
        "(:p_prev_hdr_id,:p_curr_hdr_id,:p_user,:p_message);end;";


    private final String EXTEND_CERT_BOQ_LINES =
        "begin xxboq_bill_of_quantity_pkg.EXTEND_CERT_BOQ_LINES" +
        "(:p_prev_hdr_id,:p_curr_hdr_id,:p_user,:p_message);end;";

    private final String VALIDATE_WD_BOQ_LINES =
        "begin xxboq_bill_of_quantity_pkg.validate_wd_quantities" +
        "(:p_wd_hdr_id,:p_user_name,:p_message);end;";

    private final String VALIDATE_INV_BOQ_LINES =
        "begin xxboq_bill_of_quantity_pkg.validate_invoice_lines" +
        "(:p_invoice_header_id,:p_user_name,:p_message);end;";

    private final String VALIDATE_CERT_BOQ_LINES =
        "begin xxboq_bill_of_quantity_pkg.validate_cert_quantities" +
        "(:p_cert_hdr_id,:p_user_name,:p_message);end;";


    private final String UPDATE_CERT_STATUS =
        "begin xxboq_bill_of_quantity_pkg.update_cert_doc_status" +
        "(:p_hdr_id,:p_curr_flag,:p_status,:p_user,:p_message);end;";


    private final String UPDATE_WD_STATUS =
        "begin xxboq_bill_of_quantity_pkg.update_cert_doc_status" +
        "(:p_hdr_id,:p_status,:p_user,:p_message);end;";


    private final String WITHDRAW_APPRL_SUBMISSION =
        "begin xxame_pkg.withdraw_submission(:p_document_type_id ,:p_document_id,:p_remarks,:p_user,:p_message);end;";


    /**
     * This is the default constructor (do not remove).
     */
    public BillingDetailsAMImpl() {
    }

    public String validateCertLineActPercentage() {
        CertifiedQtyLinesVORowImpl linesRow =
            (CertifiedQtyLinesVORowImpl)getCertifiedQtyLinesVO1().getCurrentRow();
        if (linesRow != null) {
            if (linesRow.getBillQty() != null &&
                linesRow.getActCertQtyPer() != null) {
                Double certActPer = linesRow.getActCertQtyPer().doubleValue();
                Double billQty = linesRow.getBillQty().doubleValue();
                if (certActPer.doubleValue() > 100) {
                    linesRow.setActCertQtyPer(null);
                    return "Cannot be more than 100.";
                }
                if (certActPer.doubleValue() < 0) {
                    linesRow.setActCertQtyPer(null);
                    return "Cannot be a negative value.";
                }
                Double invCummQty = getPercentageQty(certActPer, billQty);
                linesRow.setActualCertifiedQty(new BigDecimal(invCummQty.toString()));
            }
        }

        return "";
    }

    public String validateCertLineActQty() {
        CertifiedQtyLinesVORowImpl linesRow =
            (CertifiedQtyLinesVORowImpl)getCertifiedQtyLinesVO1().getCurrentRow();
        if (linesRow != null) {
            if (linesRow.getBillQty() != null &&
                linesRow.getActualCertifiedQty() != null) {
                if (linesRow.getBillQty().compareTo(linesRow.getActualCertifiedQty()) <
                    0) {
                    linesRow.setActualCertifiedQty(null);
                    return "Error";
                }
                if (linesRow.getActualCertifiedQty().doubleValue() < 0) {
                    linesRow.setActualCertifiedQty(null);
                    return "Cannot be a negative value.";
                }
                Double invActQty =
                    linesRow.getActualCertifiedQty().doubleValue();
                Double billQty = linesRow.getBillQty().doubleValue();
                Double invActQtyPer = getPercentage(invActQty, billQty);
                linesRow.setActCertQtyPer(new BigDecimal(invActQtyPer.toString()));
            }
        }

        return "";
    }

    public String validateCertLinePercentage() {
        CertifiedQtyLinesVORowImpl linesRow =
            (CertifiedQtyLinesVORowImpl)getCertifiedQtyLinesVO1().getCurrentRow();
        if (linesRow != null) {
            if (linesRow.getBillQty() != null &&
                linesRow.getCertCummQtyPer() != null) {
                Double invCummPer = linesRow.getCertCummQtyPer().doubleValue();
                Double billQty = linesRow.getBillQty().doubleValue();
                if (invCummPer.doubleValue() > 100) {
                    linesRow.setCertCummQtyPer(null);
                    return "Cannot be more than 100.";
                }
                if (invCummPer.doubleValue() < 0) {
                    linesRow.setCertCummQtyPer(null);
                    return "Cannot be a negative value.";
                }
                Double invCummQty = getPercentageQty(invCummPer, billQty);
                linesRow.setCertifiedCummulativeQty(new BigDecimal(invCummQty.toString()));
            }
        }

        return "";
    }

    public String validateCertLineQuantities() {
        CertifiedQtyLinesVORowImpl linesRow =
            (CertifiedQtyLinesVORowImpl)getCertifiedQtyLinesVO1().getCurrentRow();
        if (linesRow != null) {
            if (linesRow.getBillQty() != null &&
                linesRow.getCertifiedCummulativeQty() != null) {
                if (linesRow.getBillQty().compareTo(linesRow.getCertifiedCummulativeQty()) <
                    0) {
                    linesRow.setCertifiedCummulativeQty(null);
                    return "Error";
                }
                if (linesRow.getCertifiedCummulativeQty().doubleValue() < 0) {
                    linesRow.setCertifiedCummulativeQty(null);
                    return "Cannot be a negative value.";
                }
                Double invCummQty =
                    linesRow.getCertifiedCummulativeQty().doubleValue();
                Double billQty = linesRow.getBillQty().doubleValue();
//                Double invCummQtyPer = getPercentage(invCummQty, billQty);
//                linesRow.setCertCummQtyPer(new BigDecimal(invCummQtyPer.toString()));
            }
        }
        return "";
    }

    public String validateCertifiedLines() {
        CertifiedQtyLinesVOImpl linesVO = getCertifiedQtyLinesVO1();
        Row[] rows = linesVO.getAllRowsInRange();
        for (Row row : rows) {
            CertifiedQtyLinesVORowImpl linesRow =
                (CertifiedQtyLinesVORowImpl)row;
            Double invCummQty =
                linesRow.getCertifiedCummulativeQty().doubleValue();
            Double billQty = linesRow.getBillQty().doubleValue();
            Double invCummPer = linesRow.getCertCummQtyPer().doubleValue();

            Double newInvCummQty = getPercentageQty(invCummPer, billQty);
            Double newInvCummQtyPer = getPercentage(invCummQty, billQty);
            if (invCummPer.doubleValue() != newInvCummQtyPer.doubleValue() ||
                invCummQty.doubleValue() != newInvCummQty.doubleValue()) {
                return "Line quantities are not calculated correctly. Please save before submit.";
            }
            //            Double actCummQty =
            //                linesRow.getCertifiedCummulativeQty().doubleValue();
            //            Double actCummPer = linesRow.getCertCummQtyPer().doubleValue();
            //
            //            Double newActCummQty = getPercentageQty(actCummPer, billQty);
            //            Double newActCummQtyPer = getPercentage(actCummQty, billQty);
            //            if (actCummPer.doubleValue() != newActCummQtyPer.doubleValue() ||
            //                actCummQty.doubleValue() != newActCummQty.doubleValue()) {
            //                return "Line Quantities are not calculated correctly, Please save before submit";
            //            }
        }

        return "";
    }

    public String calculateCertLineValues() {
        String message = validateCertifiedLines();
        if (message != null && message.length() > 0) {
            message = "";
            CertifiedQtyLinesVOImpl linesVO = getCertifiedQtyLinesVO1();
            linesVO.executeQuery();
            Row[] rows = linesVO.getAllRowsInRange();
            for (Row row : rows) {
                CertifiedQtyLinesVORowImpl linesRow =
                    (CertifiedQtyLinesVORowImpl)row;
                Double invCummPer = linesRow.getCertCummQtyPer().doubleValue();
                Double billQty = linesRow.getBillQty().doubleValue();
                Double invCummQty =
                    linesRow.getCertifiedCummulativeQty().doubleValue();
                Double newInvCummQty = getPercentageQty(invCummPer, billQty);
                if (invCummQty.doubleValue() != newInvCummQty.doubleValue()) {
                    linesRow.setCertifiedCummulativeQty(new BigDecimal(newInvCummQty.toString()));
                }
                //                Double actCummQty =
                //                    linesRow.getCertifiedCummulativeQty().doubleValue();
                //                Double actCummPer = linesRow.getActCertQtyPer().doubleValue();
                //
                //                Double newActCummQty = getPercentageQty(actCummPer, billQty);
                //                if (actCummQty.doubleValue() != newActCummQty.doubleValue()) {
                //                    linesRow.setActualCertifiedQty(new BigDecimal(newActCummQty.toString()));
                //                }
            }
            this.getDBTransaction().commit();
            linesVO.executeQuery();
            return "Cummulative and actual quantities are adjusted as per percentage values.";
        }
        return "";
    }


    public String validateWDLineCumltQtyPercnt() {
        String message = null;
        try {

            WorkDoneQtyLinesVORowImpl linesRow =
                (WorkDoneQtyLinesVORowImpl)getWorkDoneQtyLinesVO1().getCurrentRow();
            if (linesRow != null) {
                if (linesRow.getBillQty() != null &&
                    linesRow.getWdCummulativeQtyPerct() != null) {
                    Double invCummPer =
                        linesRow.getWdCummulativeQtyPerct().doubleValue();
                    Double billQty = linesRow.getBillQty().doubleValue();
                    if (invCummPer.doubleValue() > 100) {
                        linesRow.setWdCummulativeQtyPerct(null);
                        return "Cannot be more than 100.";
                    }
                    if (invCummPer.doubleValue() < 0) {
                        linesRow.setWdCummulativeQtyPerct(null);
                        return "Cannot be a negative value.";
                    }
                    Double invCummQty = getPercentageQty(invCummPer, billQty);
                    linesRow.setWdCummulativeQty(new BigDecimal(invCummQty.toString()));
                }
            }
        } catch (Exception e) {
            message = e.getMessage();
        }
        return message;
    }


    public String validateWDLineActQtyPercnt() {
        String message = null;
        try {
            if (getWorkDoneQtyLinesVO1().getCurrentRow() != null) {
                WorkDoneQtyLinesVORowImpl line =
                    (WorkDoneQtyLinesVORowImpl)getWorkDoneQtyLinesVO1().getCurrentRow();

                Double billQty = line.getBillQty().doubleValue();
                Double actQtyPerct = line.getActualWdQtyPerct().doubleValue();

                if (actQtyPerct <= 100) {
                    Double wdQtyCal = (actQtyPerct / 100) * billQty;
                    line.setActualWdQty(new BigDecimal(wdQtyCal));
                    message = "Success";
                } else {
                    Double actQty = line.getActualWdQty().doubleValue();
                    Double actQtyPercnt = (actQty / billQty) * 100;
                    line.setActualWdQtyPerct(new BigDecimal(actQtyPercnt));
                    message =
                            "Actual work done quantity % cannot be greater than 100";
                }
            }


        } catch (Exception e) {
            message = e.getMessage();
        }
        return message;
    }


    public String validateWDLineCumltQty() {
        String message = null;
        try {
            if (getWorkDoneQtyLinesVO1().getCurrentRow() != null) {
                WorkDoneQtyLinesVORowImpl line =
                    (WorkDoneQtyLinesVORowImpl)getWorkDoneQtyLinesVO1().getCurrentRow();

                Double billQty = line.getBillQty().doubleValue();
                Double wdQty = line.getWdCummulativeQty().doubleValue();

                if (wdQty <= billQty) {
//                    Double percntCal = (wdQty / billQty) * 100;
//                    line.setWdCummulativeQtyPerct(new BigDecimal(percntCal));
                    message = "Success";
                } else {
                    Double percnt =
                        line.getWdCummulativeQtyPerct().doubleValue();
                    Double wdQtyCal = (percnt / 100) * billQty;
                    line.setWdCummulativeQty(new BigDecimal(wdQtyCal));
                    message =
                            "Cummulative work done quantity cannot be greater than bill quantity .i.e" +
                            billQty;
                }
            }


        } catch (Exception e) {
            message = e.getMessage();
        }
        return message;
    }
    
    public String validateWDLineCumltAmt() {
        String message = null;
//        try {
//            if (getWorkDoneQtyLinesVO1().getCurrentRow() != null) {
//                WorkDoneQtyLinesVORowImpl line =
//                    (WorkDoneQtyLinesVORowImpl)getWorkDoneQtyLinesVO1().getCurrentRow();
//
//                Double billQty = line.getBillQty().doubleValue();
//                Double wdAmt = ((BigDecimal)ADFContext.getCurrent().getPageFlowScope().get("cummulativeWDAmt")).doubleValue();
//                Double currBoqRate = line.getCurrentBoqRate().doubleValue();
//                if (wdAmt <= (billQty * currBoqRate)){
//                        Double wdQty = (wdAmt / (billQty*currBoqRate));
//                        line.setWdCummulativeQty(new BigDecimal(wdQty));
//                    message = "Success";
//                } else {
//                    Double wdQty =
//                        line.getWdCummulativeQty().doubleValue();
//                    line.setTransAmount(new BigDecimal(wdQty*currBoqRate));
//                    message =
//                            "Cummulative WD Quantity cannot be greater than Bill Quantity .i.e" +
//                            billQty;
//                }
//            }
//
//
//        } catch (Exception e) {
//            message = e.getMessage();
//        }
        return message;
    }


    public String validateWDLineActQty() {
        String message = null;
        try {
            if (getWorkDoneQtyLinesVO1().getCurrentRow() != null) {
                WorkDoneQtyLinesVORowImpl line =
                    (WorkDoneQtyLinesVORowImpl)getWorkDoneQtyLinesVO1().getCurrentRow();

                Double billQty = line.getBillQty().doubleValue();
                Double actQty = line.getActualWdQty().doubleValue();

                if (actQty <= billQty) {
                    Double percntCal = (actQty / billQty) * 100;
                    line.setActualWdQtyPerct(new BigDecimal(percntCal));
                    message = "Success";
                } else {
                    Double percnt = line.getActualWdQtyPerct().doubleValue();
                    Double actQtyCal = (percnt / 100) * billQty;
                    line.setActualWdQty(new BigDecimal(actQtyCal));
                    message =
                            "Actual work done quantity cannot be greater than bill quantity .i.e" +
                            billQty;
                }
            }


        } catch (Exception e) {
            message = e.getMessage();
        }
        return message;
    }

    public String refreshWDLineAttrs(Map attributesMap) {
        String message = null;
        try {
            if (attributesMap != null) {

                WorkDoneQtyLinesVOImpl pCHdrVO = getWorkDoneQtyLinesVO1();
                WorkDoneQtyLinesVORowImpl row =
                    (WorkDoneQtyLinesVORowImpl)pCHdrVO.getCurrentRow();

                Map<String, Object> attributes =
                    (HashMap<String, Object>)attributesMap;
                for (Map.Entry<String, Object> entry : attributes.entrySet()) {
                    row.setAttribute(entry.getKey(), entry.getValue());
                }
                message = "Success";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public String makeAsCurrentCertDoc(BigDecimal docId) {
        String message = null;
        try {
            CommonUtilsAMImpl commAM = (CommonUtilsAMImpl)getCommonUtilsAM1();
            commAM.makeAsCurrentRow(getCertifiedQtyDetailsVO1(), docId);
            message = "Success";
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }

    public String refreshCertHdrVOAttrs(Map attributesMap) {
        String message = null;
        try {
            if (attributesMap != null) {

                TransCertifiedDocHdrVOImpl HdrVO =
                    getTransCertifiedDocHdrVO1();
                TransCertifiedDocHdrVORowImpl row =
                    (TransCertifiedDocHdrVORowImpl)HdrVO.getCurrentRow();

                Map<String, Object> attributes =
                    (HashMap<String, Object>)attributesMap;
                for (Map.Entry<String, Object> entry : attributes.entrySet()) {
                    row.setAttribute(entry.getKey(), entry.getValue());
                }
                message = "Success";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }


    public String refreshWDHdrVOAttrs(Map attributesMap) {
        String message = null;
        try {
            if (attributesMap != null) {

                TransWorkdoneHdrVOImpl pCHdrVO = getTransWorkdoneHdrVO1();
                TransWorkdoneHdrVORowImpl row =
                    (TransWorkdoneHdrVORowImpl)pCHdrVO.getCurrentRow();

                Map<String, Object> attributes =
                    (HashMap<String, Object>)attributesMap;
                for (Map.Entry<String, Object> entry : attributes.entrySet()) {
                    row.setAttribute(entry.getKey(), entry.getValue());
                }
                message = "Success";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }


    public String callpkgForAMECertBOQDocSubmit() {
        String msg = "";
        try {
            CommonUtilsAMImpl commonAM =
                (CommonUtilsAMImpl)getCommonUtilsAM1();

            CertifiedQtyDetailsVOImpl hdrVO = getCertifiedQtyDetailsVO1();
            CertifiedQtyDetailsVORowImpl hdrRow =
                (CertifiedQtyDetailsVORowImpl)hdrVO.getCurrentRow();
            if (hdrRow != null) {
                Map paramsMap = new HashMap();
                paramsMap.put("docId",
                              hdrRow.getCertifiedHeaderId().longValue());
                paramsMap.put("docTypeId", 4);
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
                    hdrRow.setCertifiedStatus("I");
                    msg = "Success";
                }else if ("Finally Approved".equals(msg)) { //FYI approval only
                        //                        submitDocFor("Approved");
                        hdrRow.setCertifiedStatus("A");
                    msg = "Approved";
                }
                makeAsCurrentCertDoc(hdrRow.getCertifiedHeaderId());
                makeAsCurrentCertDoc(hdrRow.getCertifiedHeaderId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        return msg;
    }

    public String withdrawCertDocFromApproval(String remarks) {
        String message = null;
        try {
            if (getCertifiedQtyDetailsVO1().getCurrentRow() != null) {
                CertifiedQtyDetailsVORowImpl hdrRow =
                    (CertifiedQtyDetailsVORowImpl)getCertifiedQtyDetailsVO1().getCurrentRow();
                //                if (hdrRow.getParentLiabilityHdrId() != null) {

                CommonUtilsAMImpl commAM =
                    (CommonUtilsAMImpl)getCommonUtilsAM1();
                CallableStatement st =
                    getDBTransaction().createCallableStatement(WITHDRAW_APPRL_SUBMISSION,
                                                               0);
                st.setLong("p_document_type_id", 4);
                st.setLong("p_document_id",
                           hdrRow.getCertifiedHeaderId().longValue());
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
                        new Number(hdrRow.getCertifiedHeaderId().longValue());
                    commAM.makeAsCurrentRow(getCertifiedQtyDetailsVO1(),
                                            revHdrId);

                    CertifiedQtyDetailsVORowImpl newHdrRow =
                        (CertifiedQtyDetailsVORowImpl)getCertifiedQtyDetailsVO1().getCurrentRow();
                    if (newHdrRow.getCertifiedHeaderId().longValue() ==
                        revHdrId.longValue()) {
                        newHdrRow.setCertifiedStatus("W");
                    }

                    else {
                        message = "Unable to refersh the current row";
                    }

                    //                    getPaymentCertHdrVO1().executeQuery();
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


    public String archivePrevCertDoc() {
        String exeMessage = null;
        try {

            if (getCertifiedQtyDetailsVO1().getCurrentRow() != null) {
                CertifiedQtyDetailsVORowImpl hdrRow =
                    (CertifiedQtyDetailsVORowImpl)getCertifiedQtyDetailsVO1().getCurrentRow();
                if (hdrRow.getVersion().intValue() != 0) {


                    CallableStatement st = null;

                    st =
 getDBTransaction().createCallableStatement(UPDATE_CERT_STATUS, 0);
                    st.setLong("p_hdr_id",
                               hdrRow.getParentCertHeaderId().longValue());
                    st.setString("p_status", "RV");
                    st.setString("p_curr_flag", "N");
                    String userName =
                        ((String)((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("UserName"));
                    st.setString("p_user", userName);
                    st.registerOutParameter("p_message", Types.VARCHAR);

                    st.execute();
                    exeMessage = st.getString("p_message");
                    if ("Success".equals(exeMessage)) {
                        hdrRow.setCurrentFlag("Y");
                    }
                    this.getDBTransaction().commit();
                    getCertifiedQtyLinesVO1().executeQuery();
                } else {
                    exeMessage = "Success";
                }
            } else {
                exeMessage =
                        "Unable to find current certified details document.";
            }
            return exeMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exeMessage;
    }


    public String archivePrevWDDoc() {
        String exeMessage = null;
        try {

            if (getWorkDoneQtyDetailsVO1().getCurrentRow() != null) {
                WorkDoneQtyDetailsVORowImpl hdrRow =
                    (WorkDoneQtyDetailsVORowImpl)getWorkDoneQtyDetailsVO1().getCurrentRow();
                if (hdrRow.getVersion().intValue() != 0) {


                    CallableStatement st = null;

                    st =
 getDBTransaction().createCallableStatement(UPDATE_WD_STATUS, 0);
                    st.setLong("p_hdr_id",
                               hdrRow.getParentWdHeaderId().longValue());
                    st.setString("p_status", "RV");
                    String userName =
                        ((String)((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("UserName"));
                    st.setString("p_user", userName);
                    st.registerOutParameter("p_message", Types.VARCHAR);

                    st.execute();
                    exeMessage = st.getString("p_message");

                    this.getDBTransaction().commit();
                    getCertifiedQtyLinesVO1().executeQuery();
                } else {
                    exeMessage = "Success";
                }
            } else {
                exeMessage =
                        "Unable to find current certified details document.";
            }
            return exeMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exeMessage;
    }


    public String validateCertLines() {
        String message = null;
        try {
            String exeMessage = "";
//            exeMessage = validateInvoiceLines();
            if (exeMessage != null) {
                if (exeMessage.length() > 0) {
                    return exeMessage;
                } else {
                    if (getCertifiedQtyDetailsVO1().getCurrentRow() != null) {
                        CertifiedQtyDetailsVORowImpl hdrRow =
                            (CertifiedQtyDetailsVORowImpl)getCertifiedQtyDetailsVO1().getCurrentRow();
                        CallableStatement st = null;
                        st =
 getDBTransaction().createCallableStatement(VALIDATE_CERT_BOQ_LINES, 0);
                        st.setLong("p_cert_hdr_id",
                                   hdrRow.getCertifiedHeaderId().longValue());
                        String userName =
                            ((String)((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("UserName"));
                        st.setString("p_user_name", userName);
                        st.registerOutParameter("p_message", Types.VARCHAR);

                        st.execute();
                        exeMessage = st.getString("p_message");

                        this.getDBTransaction().commit();
                        getCertifiedQtyLinesVO1().executeQuery();
                    } else {
                        exeMessage =
                                "Unable to find current certified details document.";
                    }
                }
            }
            return exeMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public void executePopulateCertifiedLinesVO() {
        try {
            CertifiedQtyDetailsVORowImpl hdrRow =
                (CertifiedQtyDetailsVORowImpl)getCertifiedQtyDetailsVO1().getCurrentRow();
            if (hdrRow != null) {

                getCertifiedLinesPopulateVO1().setNamedWhereClauseParam("p_hdr_id",
                                                                        hdrRow.getCertifiedHeaderId());
                getCertifiedLinesPopulateVO1().setNamedWhereClauseParam("p_inv_hdr_id",
                                                                        hdrRow.getInvoiceHeaderId());
                getCertifiedLinesPopulateVO1().setNamedWhereClauseParam("p_prnt_hdr_id",
                                                                        hdrRow.getParentCertHeaderId());
                getCertifiedLinesPopulateVO1().setNamedWhereClauseParam("p_doc_ver",
                                                                        hdrRow.getVersion());
                getCertifiedLinesPopulateVO1().executeQuery();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String populateCertifiedLines() {
        String message = null;
        try {
            CertifiedQtyDetailsVORowImpl hdrRow =
                (CertifiedQtyDetailsVORowImpl)getCertifiedQtyDetailsVO1().getCurrentRow();


            CertifiedLinesPopulateVOImpl populateVO =
                (CertifiedLinesPopulateVOImpl)getCertifiedLinesPopulateVO1();
            RowQualifier rq = new RowQualifier("TransFlag='true'");
            Row[] rows = populateVO.getFilteredRows(rq);
            if (rows.length > 0) {
                for (int i = 0; i < rows.length; i++) {
                    CertifiedLinesPopulateVORowImpl poplateRow =
                        (CertifiedLinesPopulateVORowImpl)rows[i];
                    CertifiedQtyLinesVOImpl linesValidateVO =
                        getCertifiedQtyLinesVO2();
                    linesValidateVO.setNamedWhereClauseParam("p_boq_line_id",
                                                             poplateRow.getLineId());
                    linesValidateVO.setApplyViewCriteriaName("CertifiedLineExistsCriteria");
                    linesValidateVO.executeQuery();
                    if(linesValidateVO.first()==null){
                        CertifiedQtyLinesVORowImpl linesRow =
                            (CertifiedQtyLinesVORowImpl)getCertifiedQtyLinesVO1().createRow();
                        linesRow.setBoqLineId(poplateRow.getBoqLineId());
                        linesRow.setBoqHeaderId(poplateRow.getBoqHeaderId());
                        linesRow.setPreviousCummulativeQty(poplateRow.getPrevCummulativeQty());
                        linesRow.setCertifiedCummulativeQty(poplateRow.getInvoiceCummulativeQty());
                        linesRow.setWdCummulativeQty(poplateRow.getWdCummulativeQty());
                        linesRow.setInvoiceCummulativeQty(poplateRow.getInvoiceCummulativeQty());
                        linesRow.setCurrentBoqRate(poplateRow.getCurrentRate());
                        linesRow.setPreviousBoqRate(poplateRow.getPreviousRate());
                        linesRow.setActualCertifiedQty(poplateRow.getPrevActualCertQty());
                        linesRow.setMasterBoqHeaderId(hdrRow.getMasterBoqHeaderId());
                        linesRow.setMasterBoqLineId(poplateRow.getMasterBoqLineId());
                        linesRow.setExtendedFlag(poplateRow.getExtendedFlag());
                        linesRow.setBaseLineBoqLineId(poplateRow.getBaseLineBoqLineId());
                        linesRow.setMasterInvoiceLineId(poplateRow.getMasterInvoiceLineId());
                        linesRow.setInvoiceLineId(poplateRow.getLineId());
                        linesRow.setMasterBoqHeaderId(hdrRow.getMasterBoqHeaderId());
                        linesRow.setMasterBoqLineId(poplateRow.getMasterBoqLineId());
                        linesRow.setMasterCertHeaderId(hdrRow.getMasterCertHeaderId());
                        linesRow.setMasterCertLineId("Y".equals(poplateRow.getExtendedFlag()) ?
                                                     poplateRow.getMasterCertLineId() :
                                                     linesRow.getMasterCertLineId());
                        linesRow.setExtendedFlag(poplateRow.getExtendedFlag());
                        linesRow.setCertCummQtyPer(poplateRow.getInvCummQtyPer());
                        linesRow.setTransCertifiedPeriod(linesRow.getCertifiedCummulativeQty().subtract(linesRow.getPreviousCummulativeQty()));
                        getWorkDoneQtyLinesVO1().insertRow(poplateRow);
                    }
                }

                this.getDBTransaction().commit();
                getCertifiedQtyLinesVO1().executeQuery();
            }                
            message =
                        populatePrevCertLines(hdrRow.getCertifiedHeaderId().longValue(),
                                              hdrRow.getParentCertHeaderId().longValue(),
                                              hdrRow.getMasterCertHeaderId().longValue());
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }

    public String deleteCertDocRow() {
        String message = null;
        try {
            CertifiedQtyDetailsVOImpl detailsVO1 = getCertifiedQtyDetailsVO1();
            if (detailsVO1.getCurrentRow() != null) {
                detailsVO1.getCurrentRow().remove();
                message = "Success";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }


    public String cancelCertDocRow() {
        String message = null;
        try {
            CertifiedQtyDetailsVOImpl detailsVO1 = getCertifiedQtyDetailsVO1();
            if (detailsVO1.getCurrentRow() != null) {
                CertifiedQtyDetailsVORowImpl row =
                    (CertifiedQtyDetailsVORowImpl)detailsVO1.getCurrentRow();
                row.setCertifiedStatus("CL");
                row.setCurrentFlag("N");
                message = "Success";
                CommonUtilsAMImpl commam =
                    (CommonUtilsAMImpl)getCommonUtilsAM1();
                String remarks =
                    String.valueOf(ADFContext.getCurrent().getPageFlowScope().get("CANCEL_REMARKS"));
                String msg =
                    commam.cancelDocument(remarks, row.getCertifiedHeaderId().longValue(),
                                          4L);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }


    public MaxCertBOQHdrIdScrVORowImpl getMaxCertDocHdrIdRow(BigDecimal contractId,
                                                             BigDecimal buId) {
        MaxCertBOQHdrIdScrVORowImpl maxRow = null;
        try {
            MaxCertBOQHdrIdScrVOImpl scrVO = getMaxCertBOQHdrIdScrVO1();
            scrVO.setNamedWhereClauseParam("p_contract_id", contractId);
            scrVO.setNamedWhereClauseParam("p_bu_id", buId);
            scrVO.executeQuery();
            if (scrVO.first() != null) {
                maxRow = (MaxCertBOQHdrIdScrVORowImpl)scrVO.first();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxRow;
    }

    public MaxCertBOQDocuemntScrVORowImpl getMaxCertBOQDocRow(BigDecimal maxHdrId) {
        MaxCertBOQDocuemntScrVORowImpl maxRow = null;
        try {
            MaxCertBOQDocuemntScrVOImpl scrVO = getMaxCertBOQDocuemntScrVO1();
            scrVO.setNamedWhereClauseParam("p_hdr_id", maxHdrId);

            scrVO.executeQuery();
            if (scrVO.first() != null) {
                maxRow = (MaxCertBOQDocuemntScrVORowImpl)scrVO.first();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxRow;
    }

    public CurrentInvoicedDocScrVORowImpl getCurrentInvoiceHdrDocRow(BigDecimal contractId,
                                                                     BigDecimal buId,
                                                                     String periodName) {
        CurrentInvoicedDocScrVORowImpl maxRow = null;
        try {
            CurrentInvoicedDocScrVOImpl scrVO = getCurrentInvoicedDocScrVO1();
            scrVO.setNamedWhereClauseParam("p_contract_id", contractId);
            scrVO.setNamedWhereClauseParam("p_bu_id", buId);
            scrVO.setNamedWhereClauseParam("p_period", periodName);
            scrVO.executeQuery();
            if (scrVO.first() != null) {
                maxRow = (CurrentInvoicedDocScrVORowImpl)scrVO.first();

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxRow;
    }

    public Boolean isPCExistsForCertDoc(BigDecimal certHdrId) {
        Boolean isExists = null;
        try {

            IsCertDocScrVOImpl scrVO = getIsCertDocScrVO1();
            scrVO.setNamedWhereClauseParam("p_cert_hdr_id", certHdrId);
            scrVO.executeQuery();
            if (scrVO.first() != null) {
                IsCertDocScrVORowImpl scrRow =
                    (IsCertDocScrVORowImpl)scrVO.first();
                if (scrRow != null) {
                    if ("Y".equals(scrRow.getIsinvextsflg())) {
                        isExists = true;
                    } else if ("N".equals(scrRow.getIsinvextsflg())) {
                        isExists = false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return isExists;
    }

    public String createCertifiedDocument() {
        String message = null;
        BigDecimal maxHdrId = null;
        String docNum = null;
        String maxDocNum = null;
        BigDecimal parntHdrId = null;
        //        BigDecimal mastHdrId = null;
        CertifiedQtyDetailsVORowImpl certRow = null;
        try {

            if (getTransCertifiedDocHdrVO1() != null) {


                TransCertifiedDocHdrVOImpl searchVO =
                    getTransCertifiedDocHdrVO1();
                TransCertifiedDocHdrVORowImpl searchRow =
                    (TransCertifiedDocHdrVORowImpl)searchVO.getCurrentRow();
                //            Number hdrId = new Number(searchRow.getCertifiedHeaderId());
                //            searchVO.executeQuery();
                //            Key masterkey = new Key(new Object[] { hdrId });
                //            searchVO.findAndSetCurrentRowByKey(masterkey, 0);
                //            searchRow = (CertifiedQtySearchVORowImpl)searchVO.getCurrentRow();

                MaxCertBOQHdrIdScrVORowImpl maxIdRow =
                    (MaxCertBOQHdrIdScrVORowImpl)getMaxCertDocHdrIdRow(searchRow.getTransContractId(),
                                                                       searchRow.getTransBuId());

                //            if (maxIdRow != null) {
                if (maxIdRow != null &&
                    maxIdRow.getRowCount().doubleValue() > 0) {
                    MaxCertBOQDocuemntScrVORowImpl maxRow =
                        getMaxCertBOQDocRow(maxIdRow.getMaxCertHdrId());
                    if (maxRow != null) {
                        if ("A".equals(maxRow.getCertifiedStatus()) ||
                            "CL".equals(maxRow.getCertifiedStatus())) {
                            Boolean isInvExists =
                                isPCExistsForCertDoc(maxRow.getCertifiedHeaderId());
                                if (isInvExists != null && isInvExists) {
                                    message = "Success";
                                } else {
                                message =
                                        "You cannot create new document for this project unless previous document is interfaced to AR.";
                                }
                            if ("Success".equals(message)) {
                                maxHdrId = maxRow.getCertifiedHeaderId();
                                parntHdrId = maxHdrId;
                                //                        mastHdrId = searchRow.getCertifiedHeaderId();
                                maxDocNum = maxRow.getCertifiedDocumentNum();
                                String[] maxDocNumArr = maxDocNum.split("-");
                                if (maxDocNumArr != null) {
                                    docNum =
                                            searchRow.getTransContractNumber() + "-CE-" +
                                            formatedNumber((new Integer(maxDocNumArr[maxDocNumArr.length-1])) +
                                                           1, 4);
                                    //                            parntHdrId =
                                }
                            }
                        } else {
                            message =
                                    "You cannot create new document when there is already a working document for this project";
                        }
                    } else {
                        message =
                                "Retriving max certified details row is failed.";
                    }
                } else {
                    docNum =
                            searchRow.getTransContractNumber() + "-CE-" + formatedNumber(1,
                                                                                       4);
                    //                parntHdrId = searchRow.getCertifiedHeaderId();
                    //                mastHdrId = searchRow.getCertifiedHeaderId();
                }
                //            } else {
                //                message = "Retriving Max Work Done Row Id is failed.";
                //            }
                if (docNum != null) {

                    CurrentInvoicedDocScrVORowImpl currInvHdrRow =
                        getCurrentInvoiceHdrDocRow(searchRow.getTransContractId(),
                                                   searchRow.getTransBuId(),
                                                   searchRow.getTransPeriod());
                    if (currInvHdrRow != null &&
                        currInvHdrRow.getInvoiceHeaderId() != null &&
                        currInvHdrRow.getMasterInvoiceHeaderId() != null) {
                        certRow =
                                (CertifiedQtyDetailsVORowImpl)getCertifiedQtyDetailsVO1().createRow();

                        certRow.setInvoiceHeaderId(currInvHdrRow.getInvoiceHeaderId());
                        certRow.setMasterInvoiceHeaderId(currInvHdrRow.getMasterInvoiceHeaderId());
                        certRow.setCertifiedDocumentNum(docNum);
                        certRow.setVersion(new BigDecimal(0));
                        certRow.setCertifiedStatus("D");
                        certRow.setParentCertHeaderId(parntHdrId != null ?
                                                      parntHdrId :
                                                      certRow.getCertifiedHeaderId());
                        certRow.setMasterBoqHeaderId(currInvHdrRow.getMasterBoqHeaderId());
                        certRow.setBoqHeaderId(currInvHdrRow.getBoqHeaderId());
                        certRow.setMasterCertHeaderId(certRow.getCertifiedHeaderId());
                        certRow.setPeriodOfMeasure(searchRow.getTransPeriod());
                        certRow.setBusinessUnitId(searchRow.getTransBuId());
                        //certRow.setProjectId(searchRow.getTransProjectId());
                        certRow.setContractId(searchRow.getTransContractId());
                        certRow.setValuationDate(currInvHdrRow.getValuationDate());
                        certRow.setMaterialAtSite(currInvHdrRow.getMaterialAtSite());
                        certRow.setAdvanceReceived(currInvHdrRow.getAdvanceReceived());
                        certRow.setAdvanceRecovered(currInvHdrRow.getAdvanceRecovered());
                        certRow.setRetention(currInvHdrRow.getRetention());
                        certRow.setRetentionRelease(currInvHdrRow.getRetentionRelease());
                        certRow.setOtherPayments(currInvHdrRow.getOtherPayments());
                        getCertifiedQtyDetailsVO1().insertRow(certRow);
                        message = "Success";
                        ADFContext.getCurrent().getPageFlowScope().put("docId",
                                                                       certRow.getCertifiedHeaderId());
                    } else {
                        message =
                                "Unable to retrive approved invoiced document for the selected project and business unit.";
                    }
                }

            } else {
                message = "Trans certified document row not found.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }

    //--------------------------------------------------------------------------------------------


    public String makeAsCurrentWDdoc(BigDecimal docId) {
        String message = null;
        try {
            CommonUtilsAMImpl commAM = (CommonUtilsAMImpl)getCommonUtilsAM1();
            commAM.makeAsCurrentRow(getWorkDoneQtyDetailsVO1(), docId);
            message = "Success";
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }

    public String createWDDocument() {
        String message = null;
        BigDecimal maxHdrId = null;
        String docNum = null;
        String maxDocNum = null;
        String isDocEligble = null;
        BigDecimal parntHdrId = null;
        //        BigDecimal mastHdrId = null;
        WorkDoneQtyDetailsVORowImpl wrkDoneRow = null;

        try {
            if (getTransWorkdoneHdrVO1().getCurrentRow() != null) {
                TransWorkdoneHdrVORowImpl searchRow =
                    (TransWorkdoneHdrVORowImpl)getTransWorkdoneHdrVO1().getCurrentRow();


                MaxWDBOQHdrIdScrVORowImpl maxIdRow =
                    (MaxWDBOQHdrIdScrVORowImpl)getMaxWDBOQDocHdrIdRow(searchRow.getTransContractId(),
                                                                      searchRow.getTransBuId());

                //            if (maxIdRow != null) {
                if (maxIdRow != null &&
                    maxIdRow.getRowCount().doubleValue() > 0) {

                    MaxWDBOQDocumentScrVORowImpl maxRow =
                        getMaxWDBOQDocRow(maxIdRow.getMaxWdHdrId());

                    if (maxRow != null) {
                        if ("A".equals(maxRow.getWdStatus()) ||
                            "CL".equals(maxRow.getWdStatus())) {
                            if (searchRow.getTransPeriod().equals(maxRow.getPeriodOfMeasure())) {
                                Boolean isEligible =
                                    isWDDocInvoiced(maxRow.getWdHeaderId());//TODO
                                if (isEligible != null && isEligible) {
                                    message = "Success";
                                } else {
                                    message =
                                            "There already exists a working document for selected contract and period. Search for the document instead.";
                                }
                            } else {
                                message = "Success";
                            }
                            if ("Success".equals(message)) {


                                maxHdrId = maxRow.getWdHeaderId();
                                parntHdrId = maxHdrId;
                                //                        mastHdrId = wrkDoneRow.getWdHeaderId();
                                //                        searchRow.setParentWdHeaderId(maxHdrId);
                                maxDocNum = maxRow.getWdDocumentNum();
                                String[] maxDocNumArr = maxDocNum.split("-");
                                if (maxDocNumArr != null) {
                                    docNum =
                                            searchRow.getTransContractNumber() + "-WD-" +
                                            formatedNumber((new Integer(maxDocNumArr[maxDocNumArr.length-1])) +
                                                           1, 4);
                                }
                            }

                        } else {
                            message =
                                    "There already exists a working document. Search for the document instead.";
                        }
                    } else {
                        message = "Retriving Max Work Done Doc Row is failed.";
                    }
                } else {
                    docNum =
                            searchRow.getTransContractNumber() + "-WD-" + formatedNumber(1,
                                                                                     4);
                    //                parntHdrId = searchRow.getWdHeaderId();
                    //                mastHdrId = searchRow.getWdHeaderId();
                }

                if (docNum != null) {


                    CurrentBoqHdrScrVORowImpl currBOqHdrRow =
                        getCurrentBoqHdrDocRow(searchRow.getTransContractId(),
                                               searchRow.getTransBuId());

// Commented on 28-01-2020 because work done can be created even though there is no base line BOQ for selected project
                    if (currBOqHdrRow != null &&
                        currBOqHdrRow.getBoqHeaderId() != null &&
                        currBOqHdrRow.getMasterBoqHeaderId() != null
//                        currBOqHdrRow.getBaseLineBoqHdrId() !=null
                        ) {
                        wrkDoneRow =
                                (WorkDoneQtyDetailsVORowImpl)getWorkDoneQtyDetailsVO1().createRow();
                        wrkDoneRow.setBusinessUnitId(searchRow.getTransBuId());
                        //wrkDoneRow.setProjectId(searchRow.getTransProjectId());
                        wrkDoneRow.setContractId(searchRow.getTransContractId());
                        wrkDoneRow.setPeriodOfMeasure(searchRow.getTransPeriod());
                        wrkDoneRow.setBoqHeaderId(currBOqHdrRow.getBoqHeaderId());
                        wrkDoneRow.setMasterBoqHeaderId(currBOqHdrRow.getMasterBoqHeaderId());
                        wrkDoneRow.setContractType(currBOqHdrRow.getContractType());
                        wrkDoneRow.setWdDocumentNum(docNum);
                        wrkDoneRow.setVersion(new BigDecimal(0));
                        wrkDoneRow.setWdStatus("D");
                        wrkDoneRow.setMasterWdHeaderId(wrkDoneRow.getWdHeaderId());
                        wrkDoneRow.setParentWdHeaderId(parntHdrId != null ?
                                                       parntHdrId :
                                                       wrkDoneRow.getWdHeaderId());
                        ADFContext.getCurrent().getPageFlowScope().put("docId",
                                                                       wrkDoneRow.getWdHeaderId());
                        message = "Success";
                    } else {
                        message =
                                "You cannot create a document as there is no BOQ for selected contract.";
                    }
                }
                //            }
                //                else{
                //                    message = "A Workdone Document with same Project and Period is already exists. ";
                //                }
            } else {
                message = "Current trans row not found.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;

    }


    public CurrentBoqHdrScrVORowImpl getCurrentBoqHdrDocRow(Number contractId,
                                                            BigDecimal buId) {
        CurrentBoqHdrScrVORowImpl maxRow = null;
        try {
            CurrentBoqHdrScrVOImpl scrVO = getCurrentBoqHdrScrVO1();
            scrVO.setNamedWhereClauseParam("p_contract_id", contractId);
            scrVO.setNamedWhereClauseParam("p_bu_id", buId);
            scrVO.executeQuery();
            if (scrVO.first() != null) {
                maxRow = (CurrentBoqHdrScrVORowImpl)scrVO.first();

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxRow;
    }

    public MaxWDBOQHdrIdScrVORowImpl getMaxWDBOQDocHdrIdRow(Number prjId,
                                                            BigDecimal buId) {
        MaxWDBOQHdrIdScrVORowImpl maxRow = null;
        try {
            MaxWDBOQHdrIdScrVOImpl scrVO = getMaxWDBOQHdrIdScrVO1();
            scrVO.setNamedWhereClauseParam("p_contract_id", prjId);
            scrVO.setNamedWhereClauseParam("p_bu_id", buId);
            scrVO.executeQuery();
            if (scrVO.first() != null) {
                maxRow = (MaxWDBOQHdrIdScrVORowImpl)scrVO.first();

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxRow;
    }

    public MaxWDBOQDocumentScrVORowImpl getMaxWDBOQDocRow(BigDecimal maxHdrId) {
        MaxWDBOQDocumentScrVORowImpl maxRow = null;
        try {
            MaxWDBOQDocumentScrVOImpl scrVO = getMaxWDBOQDocumentScrVO1();
            scrVO.setNamedWhereClauseParam("p_hdr_id", maxHdrId);

            scrVO.executeQuery();
            if (scrVO.first() != null) {
                maxRow = (MaxWDBOQDocumentScrVORowImpl)scrVO.first();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxRow;
    }
    public Boolean isWDDocInvoiced(BigDecimal wdHdrId) {
        Boolean isExists = null;
        try {
            IsWDDocInvoicedScrVOImpl scrVO = getIsWDDocInvoicedScrVO1();
            scrVO.setNamedWhereClauseParam("p_wd_hdr_id", wdHdrId);
            scrVO.executeQuery();
            if (scrVO.first() != null) {
                IsWDDocInvoicedScrVORowImpl scrRow =
                    (IsWDDocInvoicedScrVORowImpl)scrVO.first();
                if (scrRow != null) {
                    if ("Y".equals(scrRow.getIsinvextsflg())) {
                        isExists = true;
                    } else if ("N".equals(scrRow.getIsinvextsflg())) {
                        isExists = false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isExists;
    }
    public int isInvoiceDocCertified(BigDecimal wdHdrId) {
        Boolean isExists = null;
        try {
            IsInvoiceDocCertifiedImpl scrVO = getIsInvoiceDocCertified1();
            scrVO.setNamedWhereClauseParam("p_hdr_id", wdHdrId);
            scrVO.executeQuery();
            IsInvoiceDocCertifiedRowImpl curRow = (IsInvoiceDocCertifiedRowImpl)scrVO.first();
            Long rowCount = scrVO.getEstimatedRowCount();
            if(rowCount >= 1L){
                 Number num = new Number(curRow.getCertifiedHeaderId());
                 return Integer.parseInt(num.toString());
            }else{
                return -1;
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return -1;        
    }


    private String formatedNumber(Integer number, int length) {
        String strNumber = null;
        try {
            if (number != null && length > 0) {
                String strInt = number.toString();
                int strIntLength = strInt.length();
                int remLength = length - strIntLength;
                for (int inx = 1; inx <= remLength; inx++) {
                    strNumber =
                            strNumber != null ? strNumber.trim() + "0" : "0";
                }
                strNumber = strNumber + number;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strNumber;
    }


    public String extendCertDocLines(Long prevHdrId, Long currHdrId,
                                     String user) {
        String message = null;

        try {
            CallableStatement st =
                getDBTransaction().createCallableStatement(EXTEND_CERT_BOQ_LINES,
                                                           0);
            st.setLong("p_prev_hdr_id", prevHdrId);
            st.setLong("p_curr_hdr_id", currHdrId);
            st.setString("p_user", user);


            st.registerOutParameter("p_message", Types.VARCHAR);
            st.execute();
            message = st.getString("p_message");
            if ("Success".equals(message)) {
                this.getDBTransaction().commit();
                getCertifiedQtyLinesVO1().executeQuery();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }


    public String extendWDBOQLines(Long prevHdrId, Long currHdrId,
                                   String user) {
        String message = null;

        try {
            CallableStatement st =
                getDBTransaction().createCallableStatement(EXTEND_WD_BOQ_LINES,
                                                           0);
            st.setLong("p_prev_hdr_id", prevHdrId);
            st.setLong("p_curr_hdr_id", currHdrId);
            st.setString("p_user", user);


            st.registerOutParameter("p_message", Types.VARCHAR);
            st.execute();
            message = st.getString("p_message");
            if ("Success".equals(message)) {
                this.getDBTransaction().commit();
                getWorkDoneQtyLinesVO1().executeQuery();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            message = e.getMessage();

        }


        return message;
    }

    public String deleteWRKDoneRow() {
        String message = null;
        try {
            WorkDoneQtyDetailsVOImpl detailsVO1 = getWorkDoneQtyDetailsVO1();
            if (detailsVO1.getCurrentRow() != null) {
                detailsVO1.getCurrentRow().remove();
                message = "Success";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }


    public String cancelWRKDoneRow() {
        String message = null;
        try {
            WorkDoneQtyDetailsVOImpl detailsVO1 = getWorkDoneQtyDetailsVO1();
            if (detailsVO1.getCurrentRow() != null) {
                WorkDoneQtyDetailsVORowImpl row =
                    (WorkDoneQtyDetailsVORowImpl)detailsVO1.getCurrentRow();
                row.setWdStatus("CL");
                row.setCurrentFlag("N");
                message = "Success";
                getDBTransaction().commit();
                CommonUtilsAMImpl commam =
                    (CommonUtilsAMImpl)getCommonUtilsAM1();
                String remarks =
                    String.valueOf(ADFContext.getCurrent().getPageFlowScope().get("CANCEL_REMARKS"));
                String msg =
                    commam.cancelDocument(remarks, row.getWdHeaderId().longValue(),
                                          2L);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }

    public void markAllWdLinesAs(Boolean markAs) {
        if (markAs != null) {
            getWorkDoneLInesPopulateVO1().setRangeSize(-1);
            Row[] rows = getWorkDoneLInesPopulateVO1().getAllRowsInRange();
            for (Row row : rows) {
                WorkDoneLInesPopulateVORowImpl popLine =
                    (WorkDoneLInesPopulateVORowImpl)row;
                if (markAs == true) {
                    popLine.setTransFlag("true");
                } else {
                    popLine.setTransFlag("false");
                }
            }

        }
    }

    public void markAllInvLinesAs(Boolean markAs) {
        if (markAs != null) {
            getInvoicedLInesPopulateVO1().setRangeSize(-1);
            Row[] rows = getInvoicedLInesPopulateVO1().getAllRowsInRange();
            for (Row row : rows) {
                InvoicedLInesPopulateVORowImpl popLine =
                    (InvoicedLInesPopulateVORowImpl)row;
                if (markAs == true) {
                    popLine.setTransFlag("true");
                } else {
                    popLine.setTransFlag("false");
                }
            }

        }
    }

    public void markAllCertLinesAs(Boolean markAs) {
        if (markAs != null) {
            getCertifiedLinesPopulateVO1().setRangeSize(-1);
            Row[] rows = getCertifiedLinesPopulateVO1().getAllRowsInRange();
            for (Row row : rows) {
                CertifiedLinesPopulateVORowImpl popLine =
                    (CertifiedLinesPopulateVORowImpl)row;
                if (markAs == true) {
                    popLine.setTransFlag("true");
                } else {
                    popLine.setTransFlag("false");
                }
            }

        }
    }


    public String deleteInvoiceRow() {
        String message = null;
        try {
            InvoicedQtyDetailsVOImpl detailsVO1 = getInvoicedQtyDetailsVO1();
            if (detailsVO1.getCurrentRow() != null) {
                detailsVO1.getCurrentRow().remove();
                message = "Success";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }


    public String cancelInvoiceRow() {
        String message = null;
        try {
            InvoicedQtyDetailsVOImpl detailsVO1 = getInvoicedQtyDetailsVO1();
            if (detailsVO1.getCurrentRow() != null) {
                InvoicedQtyDetailsVORowImpl row =
                    (InvoicedQtyDetailsVORowImpl)detailsVO1.getCurrentRow();
                row.setInvoiceStatus("CL");
                row.setCurrentFlag("N");
                message = "Success";
                getDBTransaction().commit();
                CommonUtilsAMImpl commam =
                    (CommonUtilsAMImpl)getCommonUtilsAM1();
                String remarks =
                    String.valueOf(ADFContext.getCurrent().getPageFlowScope().get("CANCEL_REMARKS"));
                String msg =
                    commam.cancelDocument(remarks, row.getInvoiceHeaderId().longValue(),
                                          3L);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }


    public String deleteCertifiedRow() {
        String message = null;
        try {
            CertifiedQtyDetailsVOImpl detailsVO1 = getCertifiedQtyDetailsVO1();
            if (detailsVO1.getCurrentRow() != null) {
                detailsVO1.getCurrentRow().remove();
                message = "Success";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }


    public String cancelCertifiedRow() {
        String message = null;
        try {
            CertifiedQtyDetailsVOImpl detailsVO1 = getCertifiedQtyDetailsVO1();
            if (detailsVO1.getCurrentRow() != null) {
                CertifiedQtyDetailsVORowImpl row =
                    (CertifiedQtyDetailsVORowImpl)detailsVO1.getCurrentRow();
                row.setCertifiedStatus("CL");
                row.setCurrentFlag("N");
                message = "Success";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }

    /**
     * Container's getter for WorkDoneQtySearchVO1.
     * @return WorkDoneQtySearchVO1
     */
    public WorkDoneQtySearchVOImpl getWorkDoneQtySearchVO1() {
        return (WorkDoneQtySearchVOImpl)findViewObject("WorkDoneQtySearchVO1");
    }

    /**
     * Container's getter for WorkDoneQtyDetailsVO1.
     * @return WorkDoneQtyDetailsVO1
     */
    public WorkDoneQtyDetailsVOImpl getWorkDoneQtyDetailsVO1() {
        return (WorkDoneQtyDetailsVOImpl)findViewObject("WorkDoneQtyDetailsVO1");
    }

    /**
     * Container's getter for WorkDoneQtyLinesVO1.
     * @return WorkDoneQtyLinesVO1
     */
    public WorkDoneQtyLinesVOImpl getWorkDoneQtyLinesVO1() {
        return (WorkDoneQtyLinesVOImpl)findViewObject("WorkDoneQtyLinesVO1");
    }

    /**
     * Container's getter for WorkDoneDetailsToLinesVL1.
     * @return WorkDoneDetailsToLinesVL1
     */
    public ViewLinkImpl getWorkDoneDetailsToLinesVL1() {
        return (ViewLinkImpl)findViewLink("WorkDoneDetailsToLinesVL1");
    }

    public void editWDDocument() {
        WorkDoneQtyDetailsVOImpl hdrVO = getWorkDoneQtyDetailsVO1();
        hdrVO.executeQuery();
        WorkDoneQtySearchVOImpl searchVO = getWorkDoneQtySearchVO1();
        Row currentRow = searchVO.getCurrentRow();
        if (currentRow != null) {
            Key masterkey =
                new Key(new Object[] { currentRow.getAttribute("WdHeaderId") });
            hdrVO.findAndSetCurrentRowByKey(masterkey, 0);
        }
        ADFContext.getCurrent().getPageFlowScope().put("docId",
                                                       currentRow.getAttribute("WdHeaderId"));
    }

    public void cancelWDDocument() {
        //        getWorkDoneQtySearchVO1().getCurrentRow().remove();
    }


    public String validateWorkDoneHdr() {
        WorkDoneQtySearchVORowImpl hdrRow =
            (WorkDoneQtySearchVORowImpl)getWorkDoneQtySearchVO1().getCurrentRow();
        if (hdrRow != null) {
            RowQualifier rq =
                new RowQualifier("BusinessUnitId=" + hdrRow.getBusinessUnitId() +
                                 " AND ProjectId=" + hdrRow.getProjectId() +
                                 " AND (WdStatus='Draft' OR WdStatus='Rejected')");
            Row[] rows = getWorkDoneQtyDetailsVO1().getFilteredRows(rq);
            if (rows.length > 1) {
                return "There already exists a working document for this project.";
            } else {
                CallableStatement st = null;
                String returnCode = null;
                try {
                    st =
 getDBTransaction().createCallableStatement(validate_work_done_period, 0);
                    st.setLong("p_contract_id", hdrRow.getContractId().longValue());
                    st.setString("p_period", hdrRow.getPeriodOfMeasure());
                    st.registerOutParameter("p_flag", Types.VARCHAR);
                    st.execute();
                    returnCode = st.getString("p_flag");
                    if (!("Y".equals(returnCode))) {
                        return "Invalid measurement period.";
                    }

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
            }
        }
        return "";
    }

    public void createWDHeaderRow() {

        try {
            getTransWorkdoneHdrVO1().insertRow(getTransWorkdoneHdrVO1().createRow());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //        WorkDoneQtySearchVOImpl searchVO = getWorkDoneQtySearchVO1();
        //        WorkDoneQtySearchVORowImpl searchRow =
        //            (WorkDoneQtySearchVORowImpl)searchVO.createRow();
        //        searchVO.insertRow(searchRow);
    }

    /**
     * Container's getter for WorkDoneLInesPopulateVO1.
     * @return WorkDoneLInesPopulateVO1
     */
    public WorkDoneLInesPopulateVOImpl getWorkDoneLInesPopulateVO1() {
        return (WorkDoneLInesPopulateVOImpl)findViewObject("WorkDoneLInesPopulateVO1");
    }
    
    public String executePopulateLinesVO() {
        String message = null;
        try {
                getWorkDoneQtyLinesVO1().executeQuery();
            WorkDoneQtyDetailsVORowImpl hdrRow =
                (WorkDoneQtyDetailsVORowImpl)getWorkDoneQtyDetailsVO1().getCurrentRow();
            if (hdrRow != null) {
                Long BuId = hdrRow.getBuId().longValue();
               // Long prjId = hdrRow.getProjectId().longValue();
                Long contractId = hdrRow.getContractId().longValue();
                Long wdHdrId = hdrRow.getWdHeaderId().longValue();
                getWorkDoneLInesPopulateVO1().setNamedWhereClauseParam("p_bu_id",
                                                                       BuId);
                getWorkDoneLInesPopulateVO1().setNamedWhereClauseParam("p_contract_id",
                                                                       contractId);
                getWorkDoneLInesPopulateVO1().setNamedWhereClauseParam("p_wdhdr_id",
                                                                       wdHdrId);
                getWorkDoneLInesPopulateVO1().setNamedWhereClauseParam("p_prev_hdr_id",
                                                                       hdrRow.getParentWdHeaderId());
                getWorkDoneLInesPopulateVO1().setNamedWhereClauseParam("p_boq_hdr_id",
                                                                       hdrRow.getBoqHeaderId());
                getWorkDoneLInesPopulateVO1().setNamedWhereClauseParam("p_doc_ver",
                                                                       hdrRow.getVersion());
                System.out.println(BuId);
                System.out.println(contractId);
                System.out.println(wdHdrId);
                System.out.println(hdrRow.getParentWdHeaderId());
                System.out.println(hdrRow.getBoqHeaderId());
                System.out.println(hdrRow.getVersion());
                getWorkDoneLInesPopulateVO1().executeQuery();
                message = "Success";
            }            
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }

    public String populatePrevBoqLines(Long currHdrId, Long prevHdrId,
                                       Long mastHdrId) {
        String message = null;
        try {
            if (currHdrId != null && prevHdrId != null && mastHdrId != null) {
                if (prevHdrId != null) {
                    CommonUtilsAMImpl commAM =
                        (CommonUtilsAMImpl)getCommonUtilsAM1();
                    message =
                            extendWDBOQLines(prevHdrId, currHdrId, commAM.getCurrentEmployeeUserName());
                } else {
                    message = "Previous work done header id found null";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }

    public String populateWdLines() {
        String message = null;
        try {
            WorkDoneQtyDetailsVORowImpl hdrRow =
                (WorkDoneQtyDetailsVORowImpl)getWorkDoneQtyDetailsVO1().getCurrentRow();

            //            if (getWorkDoneQtyLinesVO1().getEstimatedRowCount() == 0 &&
            //                hdrRow.getParentWdHeaderId() != null) {
            //
            //                if (!"Success".equals(message)) {
            //                    return message;
            //                }
            //            }

            WorkDoneLInesPopulateVOImpl populateVO =
                (WorkDoneLInesPopulateVOImpl)getWorkDoneLInesPopulateVO1();
            RowQualifier rq = new RowQualifier("TransFlag='true'");
            Row[] rows = populateVO.getFilteredRows(rq);
            if (rows.length > 0) {
                for (int i = 0; i < rows.length; i++) {
                    WorkDoneLInesPopulateVORowImpl poplateRow =
                        (WorkDoneLInesPopulateVORowImpl)rows[i];
                    WorkDoneQtyLinesVOImpl linesValidateVO =
                        getWorkDoneQtyLinesVO2();
                    linesValidateVO.setNamedWhereClauseParam("p_boq_line_id",
                                                             poplateRow.getLineId());
                    linesValidateVO.setApplyViewCriteriaName("WorkdoneLineExistsCriteria");
                    linesValidateVO.executeQuery();
                    if(linesValidateVO.first()==null){
                        WorkDoneQtyLinesVORowImpl linesRow =
                            (WorkDoneQtyLinesVORowImpl)getWorkDoneQtyLinesVO1().createRow();
                        //                linesRow.setWdLineId(new BigDecimal(i + 1));
                        linesRow.setBoqHeaderId(poplateRow.getHeaderId());
    //                    linesRow.setPriceCode(poplateRow.getPriceCode());
                        linesRow.setBoqLineId(poplateRow.getLineId());
                        linesRow.setMasterBoqHeaderId(poplateRow.getMasterBoqHeaderId());
                        linesRow.setBaseLineBoqLineId(poplateRow.getBaseLineBoqLineId());
                        linesRow.setMasterBoqLineId(poplateRow.getMasterBoqLineId());
                        linesRow.setPreviousCummulativeQty(poplateRow.getCumulativeBillQty() ==
                                                           null ?
                                                           new BigDecimal(0) :
                                                           poplateRow.getCumulativeBillQty());
                        linesRow.setWdCummulativeQty(poplateRow.getCumulativeBillQty() ==
                                                     null ? new BigDecimal(0) :
                                                     poplateRow.getCumulativeBillQty());
                        linesRow.setCurrentBoqRate(poplateRow.getSellingRate());
                        linesRow.setActualWdQty(new BigDecimal(0));
                        linesRow.setPreviousBoqRate(poplateRow.getPreviousRate() !=
                                                    null ?
                                                    poplateRow.getPreviousRate() :
                                                    poplateRow.getSellingRate());
                        linesRow.setExtendedFlag(poplateRow.getExtendedFlag());
    
                        linesRow.setWdCummulativeQtyPerct(poplateRow.getCummulativeQtyPerct());
                        getWorkDoneQtyLinesVO1().insertRow(poplateRow);
                    }
                }
                this.getDBTransaction().commit();
                getWorkDoneQtyLinesVO1().executeQuery();
            }
            message =
                    populatePrevBoqLines(hdrRow.getWdHeaderId().longValue(), hdrRow.getParentWdHeaderId().longValue(),
                                             hdrRow.getMasterWdHeaderId().longValue());
//            message = "Success";
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }

    public void executeWdLinesQuery() {
        getWorkDoneQtyLinesVO1().executeQuery();
    }

    /**
     * Container's getter for MaxWdDocumentNumber1.
     * @return MaxWdDocumentNumber1
     */
    public MaxWdDocumentNumberImpl getMaxWdDocumentNumber1() {
        return (MaxWdDocumentNumberImpl)findViewObject("MaxWdDocumentNumber1");
    }

    public String callpkgForAMEProcessSubmit() {
        CommonUtilsAMImpl commonAM = (CommonUtilsAMImpl)getCommonUtilsAM1();
        WorkDoneQtyDetailsVORowImpl hdrRow =
            (WorkDoneQtyDetailsVORowImpl)getWorkDoneQtyDetailsVO1().getCurrentRow();
        Long hdrId = new Long(hdrRow.getWdHeaderId().longValue());
        String msg = "";
        if (hdrRow != null) {
            Map paramsMap = new HashMap();
            try {
                paramsMap.put("docId",
                              new oracle.jbo.domain.Number(hdrRow.getWdHeaderId()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            paramsMap.put("docTypeId", new oracle.jbo.domain.Number(2));
            paramsMap.put("ActionCode", "Submit");
            paramsMap.put("currEmpId", commonAM.getCurrentEmployeeId());
            paramsMap.put("remarks", "");
            paramsMap.put("docNum", hdrRow.getWdDocumentNum());
            msg = commonAM.callAMEProcess(paramsMap);
            if (msg != null) {
                if ("Submitted".equals(msg)) {
                    submitDocFor("I");
                    msg = "Success";
                } else if ("Finally Approved".equals(msg)) {
                    submitDocFor("A");
                    msg = "Approved";
//                    updateBoqQuantities("I");
                }
            }
            makeAsCurrentWDdoc(hdrRow.getWdHeaderId());
            makeAsCurrentWDdoc(hdrRow.getWdHeaderId());
        }
        return msg;
    }

    public void submitDocFor(String toDocStatus) {
        if (getWorkDoneQtyDetailsVO1().getCurrentRow() != null) {
            WorkDoneQtyDetailsVORowImpl hdrRow =
                (WorkDoneQtyDetailsVORowImpl)getWorkDoneQtyDetailsVO1().getCurrentRow();
            hdrRow.setWdStatus(toDocStatus);
            if ("Approved".equals(toDocStatus)) {
                CommonUtilsAMImpl commanAM =
                    (CommonUtilsAMImpl)getCommonUtilsAM1();
                String userName = commanAM.getCurrentEmployeeUserName();
                //                hdrRow.setApprovedBy(userName);
                java.sql.Timestamp datetime =
                    new java.sql.Timestamp(System.currentTimeMillis());
                oracle.jbo.domain.Date daTime =
                    new oracle.jbo.domain.Date(datetime);
                //                hdrRow.setApprovedDate(daTime);
            }
        }
    }


    /**
     * Container's getter for CommonUtilsAM1.
     * @return CommonUtilsAM1
     */
    public ApplicationModuleImpl getCommonUtilsAM1() {
        return (ApplicationModuleImpl)findApplicationModule("CommonUtilsAM1");
    }

    public String ReviseWorkDoneDoc() {
        WorkDoneQtyDetailsVORowImpl hdrRow =
            (WorkDoneQtyDetailsVORowImpl)getWorkDoneQtyDetailsVO1().getCurrentRow();
        if (hdrRow != null) {
            if (hdrRow.getTransReviseFlag() == null ||
                !("Y".equals(hdrRow.getTransReviseFlag()))) {
                CallableStatement st = null;
                Long hdrId = null;
                try {
                    st =
 getDBTransaction().createCallableStatement(POPULATE_REVISE_DOC_WD, 0);
                    st.setLong("p_header_id",
                               hdrRow.getWdHeaderId().longValue());
                    st.setString("p_username",
                                 ((String)((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("UserName")));
                    st.registerOutParameter("p_out_hdr_id", Types.NUMERIC);
                    st.registerOutParameter("p_message", Types.VARCHAR);
                    st.execute();
                    String returnCode = st.getString("p_message");
                    hdrId = st.getLong("p_out_hdr_id");

                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new JboException(e);
                } finally {
                    if (st != null) {
                        try {
                            // 7. Close the statement
                            st.close();
                        } catch (SQLException e) {
                        }
                    }
                }
                if (hdrId != null) {
                    getWorkDoneQtyDetailsVO1().clearCache();
                    getWorkDoneQtyDetailsVO1().executeQuery();
                    ViewObjectImpl Wdheader = getWorkDoneQtyDetailsVO1();
                    Key masterkey = new Key(new Object[] { hdrId });
                    Wdheader.findAndSetCurrentRowByKey(masterkey, 0);
                }
            } else {
                return "Document cannot be revised as reference certificate is approved.";
            }
        }
        return "";
    }

    public String validateLineQuantities() {
        WorkDoneQtyLinesVORowImpl linesRow =
            (WorkDoneQtyLinesVORowImpl)getWorkDoneQtyLinesVO1().getCurrentRow();
        if (linesRow != null) {
            if (linesRow.getBillQty() != null &&
                linesRow.getWdCummulativeQty() != null) {
                if (linesRow.getBillQty().compareTo(linesRow.getWdCummulativeQty()) <
                    0) {
                    linesRow.setWdCummulativeQty(null);
                    return "Error";
                }
            }
        }
        return "";
    }

    public String validateWorkdoneLines() {
        WorkDoneQtyLinesVOImpl linesVO = getWorkDoneQtyLinesVO1();
        Row[] rows = linesVO.getAllRowsInRange();
        for (Row row : rows) {
            WorkDoneQtyLinesVORowImpl linesRow =
                (WorkDoneQtyLinesVORowImpl)row;
            if (linesRow.getWdCummulativeQty() != null &&
                linesRow.getBillQty() != null &&
               linesRow.getWdCummulativeQtyPerct() != null) {
                Double wdCummQty =
                    linesRow.getWdCummulativeQty().doubleValue();
                Double billQty = linesRow.getBillQty().doubleValue();
                Double wdCummPer =
                    linesRow.getTransCummulltQtyPercnt().doubleValue();

                Double newWdCummQty = getPercentageQty(wdCummPer, billQty);
                Double newWdCummQtyPer = getPercentage(wdCummQty, billQty);
                if (wdCummPer.doubleValue() != newWdCummQtyPer.doubleValue() ||
                    wdCummQty.doubleValue() != newWdCummQty.doubleValue()) {
                    return "Line quantities are not calculated correctly. Please check before submit.";
                }
            } else {
                return "Line quantities are not calculated correctly. Please check before submit.";
            }
            //            Double actCummQty = linesRow.getActualWdQty().doubleValue();
            //            Double actCummPer = linesRow.getActualWdQtyPerct().doubleValue();
            //
            //            Double newActCummQty = getPercentageQty(actCummPer, billQty);
            //            Double newActCummQtyPer = getPercentage(actCummQty, billQty);
            //            if (actCummPer.doubleValue() != newActCummQtyPer.doubleValue() ||
            //                actCummQty.doubleValue() != newActCummQty.doubleValue()) {
            //                return "Line Quantities are not calculated correctly, Please save before submit";
            //            }
        }

        return "";
    }

    public String calculateWorkdoneLineValues() {
        String message = validateWorkdoneLines();
        if (message != null && message.length() > 0) {
            message = "";
            WorkDoneQtyLinesVOImpl linesVO = getWorkDoneQtyLinesVO1();
            linesVO.executeQuery();
            Row[] rows = linesVO.getAllRowsInRange();
            for (Row row : rows) {
                
                WorkDoneQtyLinesVORowImpl linesRow =
                    (WorkDoneQtyLinesVORowImpl)row;
                
                if (linesRow.getWdCummulativeQtyPerct() != null &&
                    linesRow.getBillQty() != null &&
                    linesRow.getWdCummulativeQty() != null) {
                    
                
                Double wdCummPer =
                    linesRow.getWdCummulativeQtyPerct().doubleValue();
                Double billQty = linesRow.getBillQty().doubleValue();
                Double wdCummQty =
                    linesRow.getWdCummulativeQty().doubleValue();
                Double newWdCummQty = getPercentageQty(wdCummPer, billQty);
                    if (wdCummQty.doubleValue() !=
                        newWdCummQty.doubleValue()) {
                    linesRow.setWdCummulativeQty(new BigDecimal(newWdCummQty.toString()));
                }
                } else {
                return "Line quantities are not calculated correctly. Please check before submit.";
            }
                //                Double actCummQty = linesRow.getActualWdQty().doubleValue();
                //                Double actCummPer =
                //                    linesRow.getActualWdQtyPerct().doubleValue();
                //
                //                Double newActCummQty = getPercentageQty(actCummPer, billQty);
                //                if (actCummQty.doubleValue() != newActCummQty.doubleValue()) {
                //                    linesRow.setActualWdQty(new BigDecimal(newActCummQty.toString()));
                //                }
            }
            this.getDBTransaction().commit();
            linesVO.executeQuery();
            return "Cumulative and actual quantities are adjusted as per percentage values.";
        }
        return "";
    }

    public String validateWDLines() {
        String message = null;
        try {
            String exeMessage = null;
            exeMessage = "";//validateWorkdoneLines();
            if (exeMessage != null) {
                if (exeMessage.length() > 0) {
                    return exeMessage;
                } else {
                    if (getWorkDoneQtyDetailsVO1().getCurrentRow() != null) {
                        WorkDoneQtyDetailsVORowImpl boqValHdrVO =
                            (WorkDoneQtyDetailsVORowImpl)getWorkDoneQtyDetailsVO1().getCurrentRow();
                        WorkDoneQtyLinesVORowImpl boqLineRow =
                            (WorkDoneQtyLinesVORowImpl)getWorkDoneQtyLinesVO1().getCurrentRow();
                        if (boqLineRow != null && boqLineRow.getTransGrandTotal().doubleValue() >=
                            0) {
                            CallableStatement st = null;
                            st =
 getDBTransaction().createCallableStatement(VALIDATE_WD_BOQ_LINES, 0);
                            st.setLong("p_wd_hdr_id",
                                       boqValHdrVO.getWdHeaderId().longValue());
                            String userName =
                                ((String)((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("UserName"));
                            st.setString("p_user_name", userName);
                            st.registerOutParameter("p_message",
                                                    Types.VARCHAR);
                            st.execute();
                            exeMessage = st.getString("p_message");
                            this.getDBTransaction().commit();
                            getWorkDoneQtyLinesVO1().executeQuery();
                        } else {
                            exeMessage =
                                    "Cumulative work done can't be less than zero"; //TODO
                        }
                    } else {
                        exeMessage =
                                "Unable to find current work done document.";
                    }
                }
            }
            return exeMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public void updateBoqQuantities(String from) {
        WorkDoneQtyDetailsVORowImpl hdrRow =
            (WorkDoneQtyDetailsVORowImpl)getWorkDoneQtyDetailsVO1().getCurrentRow();
        if (hdrRow != null) {
            CallableStatement st = null;
            Long hdrId = null;
            try {
                st =
 getDBTransaction().createCallableStatement(UPDATE_BOQ_QTY_HDR_STATUS, 0);
                st.setLong("p_hdr_id", hdrRow.getWdHeaderId().longValue());
                st.setString("p_from", from);
                st.execute();

            } catch (SQLException e) {
                e.printStackTrace();
                throw new JboException(e);
            } finally {
                if (st != null) {
                    try {
                        // 7. Close the statement
                        st.close();
                    } catch (SQLException e) {
                    }
                }
            }
        }
    }

    /**
     * Container's getter for MaterialAtSiteSearchVO1.
     * @return MaterialAtSiteSearchVO1
     */
    public MaterialAtSiteSearchVOImpl getMaterialAtSiteSearchVO1() {
        return (MaterialAtSiteSearchVOImpl)findViewObject("MaterialAtSiteSearchVO1");
    }

    /**
     * Container's getter for MaterialAtSiteHdrVO1.
     * @return MaterialAtSiteHdrVO1
     */
    public MaterialAtSiteHdrVOImpl getMaterialAtSiteHdrVO1() {
        return (MaterialAtSiteHdrVOImpl)findViewObject("MaterialAtSiteHdrVO1");
    }

    /**
     * Container's getter for MaterialAtSiteLinesVO1.
     * @return MaterialAtSiteLinesVO1
     */
    public MaterialAtSiteLinesVOImpl getMaterialAtSiteLinesVO1() {
        return (MaterialAtSiteLinesVOImpl)findViewObject("MaterialAtSiteLinesVO1");
    }

    /**
     * Container's getter for MaterialAtSiteHdrToLinesVL1.
     * @return MaterialAtSiteHdrToLinesVL1
     */
    public ViewLinkImpl getMaterialAtSiteHdrToLinesVL1() {
        return (ViewLinkImpl)findViewLink("MaterialAtSiteHdrToLinesVL1");
    }

    public void createMatHeaderRow() {
        MaterialAtSiteSearchVOImpl searchVO = getMaterialAtSiteSearchVO1();
        MaterialAtSiteSearchVORowImpl searchRow =
            (MaterialAtSiteSearchVORowImpl)searchVO.createRow();
        searchRow.setRevision(new BigDecimal(0));
        searchRow.setStatus("Draft");
        searchVO.insertRow(searchRow);
    }

    public void editMatDocument() {
        MaterialAtSiteHdrVOImpl hdrVO = getMaterialAtSiteHdrVO1();
        hdrVO.executeQuery();
        MaterialAtSiteSearchVOImpl searchVO = getMaterialAtSiteSearchVO1();
        Row currentRow = searchVO.getCurrentRow();
        if (currentRow != null) {
            Key masterkey =
                new Key(new Object[] { currentRow.getAttribute("DocHeaderId") });
            hdrVO.findAndSetCurrentRowByKey(masterkey, 0);
        }
    }

    public void cancelMatDocument() {
        getMaterialAtSiteSearchVO1().getCurrentRow().remove();
    }

    public void refreshMaterialAtSiteLines() {
        getMaterialAtSiteLinesVO1().executeQuery();
    }

    public String submitMaterialAtSite() {
        String msg = null;
        MaterialAtSiteHdrVORowImpl hdrRow =
            (MaterialAtSiteHdrVORowImpl)getMaterialAtSiteHdrVO1().getCurrentRow();
        hdrRow.setStatus("Completed");
        return msg;
    }

    public String validateMaterialHdr() {
        String msg = null;
        MaterialAtSiteSearchVORowImpl searchRow =
            (MaterialAtSiteSearchVORowImpl)getMaterialAtSiteSearchVO1().getCurrentRow();
        if (searchRow != null) {
            CallableStatement st = null;
            String returnCode = null;
            try {
                st =
 getDBTransaction().createCallableStatement(validate_material_site_hdr, 0);
                st.setLong("p_bu_id",
                           searchRow.getBusinessUnitId().longValue());
                st.setLong("p_prj_id", searchRow.getProjectId().longValue());
                st.setString("p_period", searchRow.getPeriodOfMeasure());
                st.registerOutParameter("p_flag", Types.VARCHAR);
                st.execute();
                returnCode = st.getString("p_flag");
                if ("S".equals(returnCode)) {
                    msg = "PREVDOCEXISTS";
                } else if ("E".equals(returnCode))
                    msg = "DOCEXISTS";
                else if ("P".equals(returnCode))
                    msg = "PERIODINVALID";
                else if ("Y".equals(returnCode))
                    msg = "SUCCESS";

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
        }
        return msg;

    }

    public String reviseMaterialSiteDetails() {
        String msg = null;
        MaterialAtSiteHdrVORowImpl hdrRow =
            (MaterialAtSiteHdrVORowImpl)getMaterialAtSiteHdrVO1().getCurrentRow();
        if (hdrRow != null) {
            CallableStatement st = null;
            Long returnHdrId = null;
            try {
                st =
 getDBTransaction().createCallableStatement(revise_material_site_doc, 0);
                st.setLong("p_header_id", hdrRow.getDocHeaderId().longValue());
                st.setString("p_username",
                             ((String)((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("UserName")));
                st.registerOutParameter("p_out_hdr_id", Types.NUMERIC);
                st.execute();
                returnHdrId = st.getLong("p_out_hdr_id");
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
            if (returnHdrId != null) {
                getMaterialAtSiteHdrVO1().clearCache();
                getMaterialAtSiteHdrVO1().executeQuery();
                Key masterkey = new Key(new Object[] { returnHdrId });
                getMaterialAtSiteHdrVO1().findAndSetCurrentRowByKey(masterkey,
                                                                    0);
            }

        }
        return msg;
    }

    /**
     * Container's getter for ProgressDetailsHdrVO1.
     * @return ProgressDetailsHdrVO1
     */
    public ProgressDetailsHdrVOImpl getProgressDetailsHdrVO1() {
        return (ProgressDetailsHdrVOImpl)findViewObject("ProgressDetailsHdrVO1");
    }

    /**
     * Container's getter for ProgressDetailsLinesVO1.
     * @return ProgressDetailsLinesVO1
     */
    public ProgressDetailsLinesVOImpl getProgressDetailsLinesVO1() {
        return (ProgressDetailsLinesVOImpl)findViewObject("ProgressDetailsLinesVO1");
    }

    /**
     * Container's getter for ProgressDetailsHdrToLinesVL1.
     * @return ProgressDetailsHdrToLinesVL1
     */
    public ViewLinkImpl getProgressDetailsHdrToLinesVL1() {
        return (ViewLinkImpl)findViewLink("ProgressDetailsHdrToLinesVL1");
    }

    public void createProgHeaderRow() {
        ProgressDetailsHdrVOImpl searchVO = getProgressDetailsHdrVO1();
        ProgressDetailsHdrVORowImpl searchRow =
            (ProgressDetailsHdrVORowImpl)searchVO.createRow();
        searchRow.setStatus("Draft");
        searchVO.insertRow(searchRow);
    }

    public void cancelProgDocument() {
        getProgressDetailsHdrVO1().getCurrentRow().remove();
    }

    public String validateProgressDetailsHdr() {
        String msg = null;
        ProgressDetailsHdrVORowImpl searchRow =
            (ProgressDetailsHdrVORowImpl)getProgressDetailsHdrVO1().getCurrentRow();
        RowQualifier rq =
            new RowQualifier("AsOnDate>=to_date('" + searchRow.getAsOnDate() +
                             "','yyyy-mm-dd') and BusinessUnitId=" +
                             searchRow.getBusinessUnitId() +
                             " and ProjectId=" + searchRow.getProjectId() +
                             "  and DocHeaderId!=" +
                             searchRow.getDocHeaderId());
        Row[] rows = getProgressDetailsHdrVO1().getFilteredRows(rq);
        if (rows.length > 0) {
            msg = "FAIL";
            searchRow.setAsOnDate(null);
        } else {
            msg = "SUCCESS";
        }
        return msg;
    }

    public void refreshProgressDetails() {
        getProgressDetailsHdrVO1().executeQuery();
    }

    /**
     * Container's getter for CertifiedQtyDetailsVO1.
     * @return CertifiedQtyDetailsVO1
     */
    public CertifiedQtyDetailsVOImpl getCertifiedQtyDetailsVO1() {
        return (CertifiedQtyDetailsVOImpl)findViewObject("CertifiedQtyDetailsVO1");
    }

    /**
     * Container's getter for CertifiedQtyLinesVO1.
     * @return CertifiedQtyLinesVO1
     */
    public CertifiedQtyLinesVOImpl getCertifiedQtyLinesVO1() {
        return (CertifiedQtyLinesVOImpl)findViewObject("CertifiedQtyLinesVO1");
    }

    /**
     * Container's getter for CertifiedDetailsToLinesVL1.
     * @return CertifiedDetailsToLinesVL1
     */
    public ViewLinkImpl getCertifiedDetailsToLinesVL1() {
        return (ViewLinkImpl)findViewLink("CertifiedDetailsToLinesVL1");
    }

    /**
     * Container's getter for InvoicedQtyDetailsVO1.
     * @return InvoicedQtyDetailsVO1
     */
    public InvoicedQtyDetailsVOImpl getInvoicedQtyDetailsVO1() {
        return (InvoicedQtyDetailsVOImpl)findViewObject("InvoicedQtyDetailsVO1");
    }


    /**
     * Container's getter for CertifiedQtySearchVO1.
     * @return CertifiedQtySearchVO1
     */
    public CertifiedQtySearchVOImpl getCertifiedQtySearchVO1() {
        return (CertifiedQtySearchVOImpl)findViewObject("CertifiedQtySearchVO1");
    }


    public void editCertifiedDocument() {
        CertifiedQtyDetailsVOImpl hdrVO = getCertifiedQtyDetailsVO1();
        hdrVO.executeQuery();
        CertifiedQtySearchVOImpl searchVO = getCertifiedQtySearchVO1();
        Row currentRow = searchVO.getCurrentRow();
        if (currentRow != null) {
            Key masterkey =
                new Key(new Object[] { currentRow.getAttribute("CertifiedHeaderId") });
            hdrVO.findAndSetCurrentRowByKey(masterkey, 0);
        }
        System.out.println( currentRow.getAttribute("CertifiedHeaderId") );
        ADFContext.getCurrent().getPageFlowScope().put("docId",
                                                       currentRow.getAttribute("CertifiedHeaderId"));
    }

    public void cancelCertifiedDocument() {
        //        getCertifiedQtySearchVO1().getCurrentRow().remove();
    }


    public String validateCertifiedHdr() {
        String message = null;
        try {
            CertifiedQtySearchVORowImpl hdrRow =
                (CertifiedQtySearchVORowImpl)getCertifiedQtySearchVO1().getCurrentRow();
            if (hdrRow != null) {
                RowQualifier rq =
                    new RowQualifier("BusinessUnitId=" + hdrRow.getBusinessUnitId() +
                                     " AND ProjectId=" +
                                     hdrRow.getProjectId() +
                                     " AND (CertifiedStatus='Draft' OR CertifiedStatus='Rejected')");
                Row[] rows = getCertifiedQtyDetailsVO1().getFilteredRows(rq);
                if (rows.length > 1) {
                    return "There already exists a working document. Search for the document instead.";
                } else {
                    CallableStatement st = null;
                    String returnCode = null;
                    try {
                        st =
 getDBTransaction().createCallableStatement(validate_certified_period, 0);
                        st.setLong("p_contract_id",
                                   hdrRow.getContractId().longValue());
                        st.setString("p_period", hdrRow.getPeriodOfMeasure());
                        st.registerOutParameter("p_flag", Types.VARCHAR);
                        st.execute();
                        message = st.getString("p_flag");
                        if (!("Y".equals(returnCode))) {
                            message = "Invalid Measurement Period.";
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                        message = e.getMessage();
                        //                    return e.getLocalizedMessage();

                    } finally {
                        if (st != null) {
                            try {
                                // 7. Close the statement
                                st.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                                message = e.getMessage();
                            }
                        }
                    }
                }
            }
        } catch (Exception je) {
            je.getMessage();
        }
        return message;
    }

    public void createCertifiedHeaderRow() {
        try {
            //            CertifiedQtySearchVOImpl searchVO = getCertifiedQtySearchVO1();
            //            CertifiedQtySearchVORowImpl searchRow =
            //                (CertifiedQtySearchVORowImpl)searchVO.createRow();
            //            searchVO.insertRow(searchRow);
            getTransCertifiedDocHdrVO1().insertRow(getTransCertifiedDocHdrVO1().createRow());
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();

        }
    }
    
    public String workDoneLinesDeleteAll()
  {
    String message = null;
    try
    {
      message = "Success";
      WorkDoneQtyDetailsVORowImpl wdRow = (WorkDoneQtyDetailsVORowImpl) getWorkDoneQtyDetailsVO1().getCurrentRow();
      PreparedStatement stmt1 =
        getDBTransaction().createPreparedStatement("Delete xxboq_wd_line_dtls_t where WD_HEADER_ID = ?", 0);
      stmt1.setInt(1, wdRow.getWdHeaderId().intValue());
      int j = stmt1.executeUpdate();
      System.out.println(j);
      PreparedStatement stmt =
        getDBTransaction().createPreparedStatement("Delete xxboq_work_done_lines_t where WD_HEADER_ID = ?", 0);
      stmt.setInt(1, wdRow.getWdHeaderId().intValue());
      int i = stmt.executeUpdate();
      System.out.println(i);
      getDBTransaction().commit();
      getWorkDoneQtyLineDetailsVO1().executeQuery();
      getWorkDoneQtyLinesVO1().executeQuery();
    }
    catch (Exception e)
    {
      message = e.getMessage();
    }
    return message;
  }
    
    public String InvoicedLinesDeleteAll(){
        String message = null;
        try{
            message= "Success";
            InvoicedQtyDetailsVORowImpl InvoiceRow =
                (InvoicedQtyDetailsVORowImpl)getInvoicedQtyDetailsVO1().getCurrentRow();
            PreparedStatement stmt =
                getDBTransaction().createPreparedStatement("Delete xxboq_invoice_lines_t where INVOICE_HEADER_ID = ?",
                                                           0);
            stmt.setInt(1, InvoiceRow.getInvoiceHeaderId().intValue());
            int i = stmt.executeUpdate();
            System.out.println(i);
            getDBTransaction().commit();
            getInvoicedQtyLinesVO1().executeQuery();
        }catch(Exception e){
            message = e.getMessage();
        }
        return message;
    }
    
    public String CertifiedLinesDeleteAll(){
        String message = null;
        try{
            message= "Success";
            CertifiedQtyDetailsVORowImpl CertifiedRow =
                (CertifiedQtyDetailsVORowImpl)getCertifiedQtyDetailsVO1().getCurrentRow();
            PreparedStatement stmt =
                getDBTransaction().createPreparedStatement("Delete xxboq_certified_lines_t where Certified_HEADER_ID = ?",
                                                           0);
            stmt.setInt(1, CertifiedRow.getCertifiedHeaderId().intValue());
            int i = stmt.executeUpdate();
            System.out.println(i);
            getDBTransaction().commit();
            getCertifiedQtyLinesVO1().executeQuery();
        }catch(Exception e){
            message = e.getMessage();
        }
        return message;
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
                    message = "Invalid file detials Found.";
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
    
    public String saveAttachedFileInvoice(UploadedFile attachedFile) {
        String message = null;
        try {
            if (getAttachmentsVO2().getCurrentRow() != null) {
                String fileName = attachedFile.getFilename();
                String contType = attachedFile.getContentType();
                BlobDomain fileBolb = createBlobDomain(attachedFile);
                if (fileName != null && contType != null) {
                    AttachmentsVORowImpl attachmnt =
                        (AttachmentsVORowImpl)getAttachmentsVO2().getCurrentRow();
                        attachmnt.setFileName(fileName);
                        attachmnt.setFileType(contType);
                        attachmnt.setAttachmentFile(fileBolb);
                        message = "Success";
    //                        this.getDBTransaction().commit();
                } else {
                    message = "Invalid file detials found.";
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
    
    public String saveAttachedFileCertification(UploadedFile attachedFile) {
        String message = null;
        try {
            if (getAttachmentsVO3().getCurrentRow() != null) {
                String fileName = attachedFile.getFilename();
                String contType = attachedFile.getContentType();
                BlobDomain fileBolb = createBlobDomain(attachedFile);
                if (fileName != null && contType != null) {
                    AttachmentsVORowImpl attachmnt =
                        (AttachmentsVORowImpl)getAttachmentsVO3().getCurrentRow();
                        attachmnt.setFileName(fileName);
                        attachmnt.setFileType(contType);
                        attachmnt.setAttachmentFile(fileBolb);
                        message = "Success";
    //                        this.getDBTransaction().commit();
                } else {
                    message = "Invalid file detials found.";
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

    public String populatePrevCertLines(Long currHdrId, Long prevHdrId,
                                        Long mastHdrId) {
        String message = null;
        try {
            if (currHdrId != null && prevHdrId != null && mastHdrId != null) {
                if (prevHdrId != null) {
                    CommonUtilsAMImpl commAM =
                        (CommonUtilsAMImpl)getCommonUtilsAM1();
                    message =
                            extendCertDocLines(prevHdrId, currHdrId, commAM.getCurrentEmployeeUserName());
                } else {
                    message = "Previous certified header id found null";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }


    public void executeCertifiedLinesQuery() {
        getCertifiedQtyLinesVO1().executeQuery();
    }


    public String callpkgForAMEProcessSubmit2() {
        String msg = "";
        CommonUtilsAMImpl commonAM = (CommonUtilsAMImpl)getCommonUtilsAM1();
        CertifiedQtyDetailsVORowImpl hdrRow =
            (CertifiedQtyDetailsVORowImpl)getCertifiedQtyDetailsVO1().getCurrentRow();
        //        Long hdrId = new Long(hdrRow.getCertifiedHeaderId().longValue());
        if (hdrRow != null) {
            Map paramsMap = new HashMap();
            try {
                paramsMap.put("docId",
                              new oracle.jbo.domain.Number(hdrRow.getCertifiedHeaderId()));
            } catch (SQLException e) {
            }
            paramsMap.put("docTypeId", new oracle.jbo.domain.Number(13));
            paramsMap.put("ActionCode", "Submit");
            paramsMap.put("currEmpId", commonAM.getCurrentEmployeeId());
            paramsMap.put("remarks", "");
            paramsMap.put("docNum", hdrRow.getCertifiedDocumentNum());
            msg = commonAM.callAMEProcess(paramsMap);

            //            getCertifiedQtyDetailsVO1().executeQuery();
            //            ViewObjectImpl Custheader = getCertifiedQtyDetailsVO1();
            //            Key masterkey = new Key(new Object[] { hdrId });
            //            Custheader.findAndSetCurrentRowByKey(masterkey, 0);
            if (msg != null) {
                submitCertifiedDocFor("In Process");
                msg = "Success";
                if ("Finally Approved".equals(msg)) { //FYI approval only
                    submitCertifiedDocFor("A");
                    msg = "Approved";
//                    updateBoqQuantities("C");
                }
            }
        }
        return msg;
    }

    public void submitCertifiedDocFor(String toDocStatus) {
        if (getCertifiedQtyDetailsVO1().getCurrentRow() != null) {
            CertifiedQtyDetailsVORowImpl hdrRow =
                (CertifiedQtyDetailsVORowImpl)getCertifiedQtyDetailsVO1().getCurrentRow();
            hdrRow.setCertifiedStatus(toDocStatus);
            if ("A".equals(toDocStatus)) {
                CommonUtilsAMImpl commanAM =
                    (CommonUtilsAMImpl)getCommonUtilsAM1();
                String userName = commanAM.getCurrentEmployeeUserName();
                //                hdrRow.setApprovedBy(userName);
                java.sql.Timestamp datetime =
                    new java.sql.Timestamp(System.currentTimeMillis());
                oracle.jbo.domain.Date daTime =
                    new oracle.jbo.domain.Date(datetime);
                //                hdrRow.setApprovedDate(daTime);
            }
        }
    }


    public String ReviseCertifiedDoc() {
        String message = null;
        try {
            CertifiedQtyDetailsVORowImpl hdrRow =
                (CertifiedQtyDetailsVORowImpl)getCertifiedQtyDetailsVO1().getCurrentRow();
            if (hdrRow != null) {
                //            if(hdrRow.getTransReviseFlag()==null || !("Y".equals(hdrRow.getTransReviseFlag()))){
                CallableStatement st = null;
                Long hdrId = null;

                st =
 getDBTransaction().createCallableStatement(REVISE_CERT_BOQ_HDR, 0);
                st.setLong("p_header_id",
                           hdrRow.getCertifiedHeaderId().longValue());
                st.setString("p_username",
                             ((String)((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("UserName")));
                st.registerOutParameter("p_out_hdr_id", Types.NUMERIC);
                st.registerOutParameter("p_message", Types.VARCHAR);
                st.execute();

                hdrId = st.getLong("p_out_hdr_id");
                message = st.getString("p_message");


                if ("Success".equals(message) && hdrId != null) {
                    getCertifiedQtyDetailsVO1().clearCache();
                    getCertifiedQtyDetailsVO1().executeQuery();
                    ViewObjectImpl Certifiedheader =
                        getCertifiedQtyDetailsVO1();
                    Key masterkey = new Key(new Object[] { hdrId });
                    Certifiedheader.findAndSetCurrentRowByKey(masterkey, 0);
                }
                //            }else{
                //                return "Invoice is Approved for this Period, So Can't be Revised.";
                //            }
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }

    public String validateCertifiedLineQuantities() {
        CertifiedQtyLinesVORowImpl linesRow =
            (CertifiedQtyLinesVORowImpl)getCertifiedQtyLinesVO1().getCurrentRow();
        if (linesRow != null) {
            if (linesRow.getInvoiceCummulativeQty() != null &&
                linesRow.getCertifiedCummulativeQty() != null) {
                if (linesRow.getInvoiceCummulativeQty().compareTo(linesRow.getCertifiedCummulativeQty()) <
                    0) {
                    linesRow.setCertifiedCummulativeQty(null);
                    return "Error";
                }
            }
        }
        return "";
    }

    /**
     * Container's getter for MaxCertifiedDocumentNumber1.
     * @return MaxCertifiedDocumentNumber1
     */
    public MaxCertifiedDocumentNumberImpl getMaxCertifiedDocumentNumber1() {
        return (MaxCertifiedDocumentNumberImpl)findViewObject("MaxCertifiedDocumentNumber1");
    }

    /**
     * Container's getter for MaxInvoicedDocumentNumber1.
     * @return MaxInvoicedDocumentNumber1
     */
    public MaxInvoicedDocumentNumberImpl getMaxInvoicedDocumentNumber1() {
        return (MaxInvoicedDocumentNumberImpl)findViewObject("MaxInvoicedDocumentNumber1");
    }

    public void editInvoicedDocument() {
        InvoicedQtyDetailsVOImpl hdrVO = getInvoicedQtyDetailsVO1();
        hdrVO.executeQuery();
        InvoicedQtySearchVOImpl searchVO = getInvoicedQtySearchVO1();
        Row currentRow = searchVO.getCurrentRow();
        if (currentRow != null) {
            Key masterkey =
                new Key(new Object[] { currentRow.getAttribute("InvoiceHeaderId") });
            hdrVO.findAndSetCurrentRowByKey(masterkey, 0);
        }
        ADFContext.getCurrent().getPageFlowScope().put("docId",
                                                              currentRow.getAttribute("InvoiceHeaderId"));
    }

    public void cancelInvoicedDocument() {
        getTransInvoiceHdrVO1().getCurrentRow().remove();
    }

    public MaxINVBOQHdrIdScrVORowImpl getMaxINVBOQDocHdrIdRow(BigDecimal contractId) {
        MaxINVBOQHdrIdScrVORowImpl maxRow = null;
        try {
            MaxINVBOQHdrIdScrVOImpl scrVO = getMaxINVBOQHdrIdScrVO1();
            scrVO.setNamedWhereClauseParam("p_cntrct_id", contractId);
            scrVO.executeQuery();
            if (scrVO.first() != null) {
                maxRow = (MaxINVBOQHdrIdScrVORowImpl)scrVO.first();

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxRow;
    }

    public MaxINVBOQDocumentScrVORowImpl getMaxINVBOQDocRow(BigDecimal maxHdrId) {
        MaxINVBOQDocumentScrVORowImpl maxRow = null;
        try {
            MaxINVBOQDocumentScrVOImpl scrVO = getMaxINVBOQDocumentScrVO1();
            scrVO.setNamedWhereClauseParam("p_hdr_id", maxHdrId);

            scrVO.executeQuery();
            if (scrVO.first() != null) {
                maxRow = (MaxINVBOQDocumentScrVORowImpl)scrVO.first();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxRow;
    }

    public CurrentWdHdrVORowImpl getCurrentWdHdrDocRow(BigDecimal contractId,
                                                       String period) {
        CurrentWdHdrVORowImpl maxRow = null;
        try {
            CurrentWdHdrVOImpl scrVO = getCurrentWdHdrVO1();
            scrVO.setNamedWhereClauseParam("p_contract_id", contractId);
            scrVO.setNamedWhereClauseParam("p_period", period);
            scrVO.executeQuery();
            if (scrVO.first() != null) {
                maxRow = (CurrentWdHdrVORowImpl)scrVO.first();

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxRow;
    }

    public synchronized String createInvoicedDocument() {
        String message = null;
        BigDecimal maxHdrId = null;
        String docNum = null;
        String maxDocNum = null;
        try {
            TransInvoiceHdrVORowImpl trnsRow =
                (TransInvoiceHdrVORowImpl)getTransInvoiceHdrVO1().getCurrentRow();
            if (trnsRow != null) {
                InvoicedQtySearchVOImpl searchVO = getInvoicedQtySearchVO1();
                InvoicedQtySearchVORowImpl searchRow =
                    (InvoicedQtySearchVORowImpl)searchVO.createRow();
                MaxINVBOQHdrIdScrVORowImpl maxIdRow =
                    (MaxINVBOQHdrIdScrVORowImpl)getMaxINVBOQDocHdrIdRow(trnsRow.getTransContractId());

                if (maxIdRow != null &&
                    maxIdRow.getRowCount().doubleValue() > 0) {
                    MaxINVBOQDocumentScrVORowImpl maxRow =
                        getMaxINVBOQDocRow(maxIdRow.getMaxInvHdrId());
                    if (maxRow != null) {
                        if ("A".equals(maxRow.getInvoiceStatus()) ||
                            "CL".equals(maxRow.getInvoiceStatus())) {
                            int num = isInvoiceDocCertified(maxRow.getInvoiceHeaderId());
                            if (num != -1) {
                                searchRow.setPreviosCertifiedId(new BigDecimal(num));
                                message = "Success";
                            }else{
                                if (trnsRow.getTransPeriod().equals(maxRow.getPeriodOfMeasure())) {
                                    message =
                                        "There already exists a working document for selected contract and period. Search for the document instead.";
                                }else{
                                    message = "Success";
                                }
                            }
                            maxHdrId = maxRow.getInvoiceHeaderId();
                            searchRow.setParentInvoiceHeaderId(maxHdrId);
                            maxDocNum = maxRow.getInvoiceDocumentNum();
                            String[] maxDocNumArr = maxDocNum.split("-");
                            if (maxDocNumArr != null) {
                                docNum =
                                        trnsRow.getTransContractNum() + "-PA-" +
                                        formatedNumber((new Integer(maxDocNumArr[maxDocNumArr.length-1])) +
                                                       1, 4);
                            }
                        } else {
                            message =
                                    "There already exists a working document. Search for the document instead.";
                        }
                    } else {
                        message =
                                "Retriving max invoice document row is failed.";
                    }
                } else {
                    docNum =
                            trnsRow.getTransContractNum() + "-PA-" + formatedNumber(1,
                                                                                    4);
                }
                if (docNum != null) {

                    CurrentWdHdrVORowImpl currWdHdrRow =
                        getCurrentWdHdrDocRow(trnsRow.getTransContractId(),
                                              trnsRow.getTransPeriod());
                    ProjectSearchVOImpl prjVO =
                        (ProjectSearchVOImpl)getProjectSearchVO1();
                    prjVO.setNamedWhereClauseParam("p_bu_id",
                                                   trnsRow.getTransBuId());
                    prjVO.setNamedWhereClauseParam("p_project_id",
                                                   trnsRow.getTransProjectId());
                    /*
                    prjVO.setApplyViewCriteriaName("ProjectSearchVOCriteria1");
                    prjVO.executeQuery();
                    ProjectSearchVORowImpl prjSearchRow = (ProjectSearchVORowImpl)prjVO.first();
                    */
                    /*
                     * Commented on 30-01-2020 by dharani as values shoulf be taken from DFF defined in Contracts
                     */
                    ContractAdvanceAmtVOImpl contractVO = getContractAdvanceAmtVO1();
                    contractVO.setNamedWhereClauseParam("p_bu_id", trnsRow.getTransBuId());
                    contractVO.setNamedWhereClauseParam("p_contract_id", trnsRow.getTransContractId());
                    contractVO.setApplyViewCriteriaName("ContractAdvanceAmtVOCriteria");
                    contractVO.executeQuery();
                    ContractAdvanceAmtVORowImpl contractSearchRow = (ContractAdvanceAmtVORowImpl)contractVO.first();
                    
                    PCLinesSearchWithProjectIdVOImpl PCLinesVO = getPCLinesSearchWithProjectIdVO1();
                    PCLinesVO.setNamedWhereClauseParam("p_bu_id", trnsRow.getTransBuId());
                    PCLinesVO.setNamedWhereClauseParam("p_contract_id", trnsRow.getTransContractId());
                    PCLinesVO.setNamedWhereClauseParam("p_particulars", "ADV");
                    PCLinesVO.executeQuery();
                    PCLinesSearchWithProjectIdVORowImpl PCLinesRow =
                        (PCLinesSearchWithProjectIdVORowImpl)PCLinesVO.first();
                    
                    PCLinesVO.setNamedWhereClauseParam("p_bu_id", trnsRow.getTransBuId());
                    PCLinesVO.setNamedWhereClauseParam("p_contract_id", trnsRow.getTransContractId());
                    PCLinesVO.setNamedWhereClauseParam("p_particulars", "MAT_AT_SITE");
                    PCLinesVO.executeQuery();
                    PCLinesSearchWithProjectIdVORowImpl PCLinesRow1 = (PCLinesSearchWithProjectIdVORowImpl) PCLinesVO.first();
                    //OTH_DED RET_REL
                    
                    PCLinesVO.setNamedWhereClauseParam("p_bu_id", trnsRow.getTransBuId());
                    PCLinesVO.setNamedWhereClauseParam("p_contract_id", trnsRow.getTransContractId());
                    PCLinesVO.setNamedWhereClauseParam("p_particulars", "OTH_DED");
                    PCLinesVO.executeQuery();
                    PCLinesSearchWithProjectIdVORowImpl PCLinesRow2 = (PCLinesSearchWithProjectIdVORowImpl) PCLinesVO.first();
                                         
                    PCLinesVO.setNamedWhereClauseParam("p_bu_id", trnsRow.getTransBuId());
                    PCLinesVO.setNamedWhereClauseParam("p_contract_id", trnsRow.getTransContractId());
                    PCLinesVO.setNamedWhereClauseParam("p_particulars", "RET_REL");
                    PCLinesVO.executeQuery();
                    PCLinesSearchWithProjectIdVORowImpl PCLinesRow4 = (PCLinesSearchWithProjectIdVORowImpl) PCLinesVO.first();
                    
                    if (currWdHdrRow != null &&
                        currWdHdrRow.getBoqHeaderId() != null &&
                        currWdHdrRow.getMasterBoqHeaderId() != null) {
                        searchRow.setBusinessUnitId(trnsRow.getTransBuId());
                        //searchRow.setProjectId(trnsRow.getTransProjectId());
                        searchRow.setContractId(trnsRow.getTransContractId());
                        searchRow.setPeriodOfMeasure(trnsRow.getTransPeriod());
                        searchRow.setMasterInvoiceHeaderId(searchRow.getInvoiceHeaderId());
                        searchRow.setWdHeaderId(currWdHdrRow.getWdHeaderId());
                        searchRow.setMasterWdHeaderId(currWdHdrRow.getMasterWdHeaderId());
                        searchRow.setBoqHeaderId(currWdHdrRow.getBoqHeaderId());
                        searchRow.setMasterBoqHeaderId(currWdHdrRow.getMasterBoqHeaderId());
                        searchRow.setValuationDate(currWdHdrRow.getValuationDate());
                        searchRow.setInvoiceDocumentNum(docNum);
                        searchRow.setVersion(new BigDecimal(0));
                        searchRow.setInvoiceStatus("D");
                        searchRow.setAdvanceReceived(PCLinesRow == null ?
                                                     new BigDecimal(0) :
                                                     PCLinesRow.getCummulative());
                        searchRow.setMaterialAtSite(PCLinesRow1 == null ? new BigDecimal(0):PCLinesRow1.getCummulative()); //Added on 30-01-2020 as material at site should be defaulted
                        searchRow.setOtherPayments(PCLinesRow2 == null ? new BigDecimal(0):PCLinesRow2.getCummulative());
                      //  searchRow.setRetention(PCLinesRow3 == null ? new BigDecimal(0):PCLinesRow3.getCummulative()); //Getting retention value from Attribute in okc_headers_vl
                        searchRow.setRetentionRelease(PCLinesRow4 == null ? new BigDecimal(0):PCLinesRow4.getCummulative());
                        if(contractSearchRow != null){
                            if(contractSearchRow.getAttributeNumber2() !=null){
                                Double d = round((contractSearchRow.getAttributeNumber2()).multiply(currWdHdrRow.getCurrentMainContractWdAmount()).divide(new BigDecimal(100)).doubleValue(),2);
                                if(PCLinesRow == null){
                                    searchRow.setAdvanceRecovered(new BigDecimal(0));
                                } else if (PCLinesRow.getCummulative().doubleValue() <d) {
                                    searchRow.setAdvanceRecovered(PCLinesRow.getCummulative());
                                }else{
                                    searchRow.setAdvanceRecovered(new BigDecimal(d.toString()));
                                }
                            }else{
                                searchRow.setAdvanceRecovered(new BigDecimal(0));
                            }
                            if(contractSearchRow.getAttributeNumber3() !=null){
                                Double d = round((contractSearchRow.getAttributeNumber3()).multiply(currWdHdrRow.getCurrentWdAmount()).divide(new BigDecimal(100)).doubleValue(),2);
                                searchRow.setRetention(new BigDecimal(d.toString()));
                            }else{
                                searchRow.setRetention(new BigDecimal(0));
                            }
                        } else {
                            searchRow.setAdvanceRecovered(new BigDecimal(0));
                            searchRow.setRetention(new BigDecimal(0));
                            searchRow.setRetentionRelease(new BigDecimal(0));
                        }
                        searchRow.setCurrentFlag("Y");
                            GetMaxCertPCHeaderIdVOImpl maxVO = getGetMaxCertPCHeaderIdVO1();
                            maxVO.setNamedWhereClauseParam("p_contract_id", trnsRow.getTransContractId());
                            maxVO.executeQuery();
                            GetMaxCertPCHeaderIdVORowImpl maxPCCertRow = (GetMaxCertPCHeaderIdVORowImpl)maxVO.first();
                            if(maxPCCertRow != null){
                                searchRow.setPreviousPcHdrId(maxPCCertRow.getMaxpc());
                            }
                      //  searchRow.setMaterialAtSite(new BigDecimal(0));
                      //  searchRow.setOtherPayments(new BigDecimal(0));
                        searchVO.insertRow(searchRow);
                        message = "Success";
                    } else {
                        message =
                                "You cannot create a document as there is no baselined BOQ for selected contract.";
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }
    
    public static double round(double value,
                                   int numberOfDigitsAfterDecimalPoint) {
            BigDecimal bigDecimal = new BigDecimal(value);
            bigDecimal =
                    bigDecimal.setScale(numberOfDigitsAfterDecimalPoint, BigDecimal.ROUND_HALF_UP);
            return bigDecimal.doubleValue();
    }

    public String validateInvoicedHdr() {//TODO
        TransInvoiceHdrVORowImpl hdrRow =
            (TransInvoiceHdrVORowImpl)getTransInvoiceHdrVO1().getCurrentRow();
        if (hdrRow != null) {
            RowQualifier rq =
                new RowQualifier("BusinessUnitId=" + hdrRow.getTransBuId() +
// As validation is on Contract Id // " AND ProjectId=" +
//                                 hdrRow.getTransProjectId() +
                                 " AND ContractId=" +
                                 hdrRow.getTransContractId() +
                                 " AND (InvoiceStatus='D' OR InvoiceStatus='R' OR InvoiceStatus='W' OR InvoiceStatus='I')");
            Row[] rows = getInvoicedQtyDetailsVO1().getFilteredRows(rq);
            if (rows.length > 0) {
                return "There already exists a working document. Search for the document instead.";
            }else {
                rq =
 new RowQualifier("BusinessUnitId=" + hdrRow.getTransBuId() +
                  " AND CurrentFlag = 'Y' AND ContractId=" +
                  hdrRow.getTransContractId() +
//                  " AND PeriodOfMeasure='" + hdrRow.getTransPeriod() +
                  "'");
                Row[] rows2 = getInvoicedQtyDetailsVO1().getFilteredRows(rq);
                if (rows2.length > 0) {
                    for (Row row : rows2) {
                        InvoicedQtyDetailsVORowImpl invRow =
                            (InvoicedQtyDetailsVORowImpl)row;
                        RowQualifier rq1 =
                            new RowQualifier("BusinessUnitId=" + hdrRow.getTransBuId() +
                                             " AND ContractId=" +
                                             hdrRow.getTransContractId() +
                                             " AND InvoiceHeaderId=" +
                                             invRow.getInvoiceHeaderId() +
                                             " AND CurrentFlag = 'Y' AND ( ( CertifiedStatus='A' AND TransPCPostedStatus NOT IN ( 'P', 'CL' ) ) OR CertifiedStatus NOT IN ( 'A', 'CL' ) )");
                        Row[] rows1 =
                            getCertifiedQtyDetailsVO1().getFilteredRows(rq1);
                        if (rows1.length > 0) {
                            return "You cannot create new document for this contract unless previous document is interfaced to AR.";
                        }
                    }
                }
            }
        }
        return "Success";
    }

    public void createInvoicedHeaderRow() {
        //        InvoicedQtySearchVOImpl searchVO = getInvoicedQtySearchVO1();
        //        InvoicedQtySearchVORowImpl searchRow =
        //            (InvoicedQtySearchVORowImpl)searchVO.createRow();
        //        searchVO.insertRow(searchRow);
        TransInvoiceHdrVORowImpl trnsRow =
            (TransInvoiceHdrVORowImpl)getTransInvoiceHdrVO1().createRow();
        getTransInvoiceHdrVO1().insertRow(trnsRow);
    }


    public void executePopulateInvoicedLinesVO() {
         InvoicedQtyDetailsVORowImpl hdrRow =
            (InvoicedQtyDetailsVORowImpl)getInvoicedQtyDetailsVO1().getCurrentRow();
        if (hdrRow != null) {
            getInvoicedLInesPopulateVO1().setNamedWhereClauseParam("p_bu_id",
                                                                   hdrRow.getBusinessUnitId());
            getInvoicedLInesPopulateVO1().setNamedWhereClauseParam("p_contract_id",
                                                                   hdrRow.getContractId());
            getInvoicedLInesPopulateVO1().setNamedWhereClauseParam("p_wd_hdr_id",
                                                                   hdrRow.getWdHeaderId());
            getInvoicedLInesPopulateVO1().setNamedWhereClauseParam("p_prev_hdr_id",
                                                                   hdrRow.getParentInvoiceHeaderId());
            getInvoicedLInesPopulateVO1().setNamedWhereClauseParam("p_invhdr_id",
                                                                   hdrRow.getInvoiceHeaderId());
            getInvoicedLInesPopulateVO1().executeQuery();
        }
    }


    public String populatePrevInvLines(Long currHdrId, Long prevHdrId,
                                       Long mastHdrId) {
        String message = null;
        try {
            if (currHdrId != null && prevHdrId != null && mastHdrId != null) {
                if (prevHdrId != null) {
                    CommonUtilsAMImpl commAM =
                        (CommonUtilsAMImpl)getCommonUtilsAM1();
                    message =
                            extendInvBOQLines(prevHdrId, currHdrId, commAM.getCurrentEmployeeUserName());
                } else {
                    message = "Previous invoice header id found null";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }
    public String calculateInvoiceAmounts(){
    String message = ""; //TODO
        try{
//            getInvoicedQtyLinesVO1().executeQuery();
             InvoicedQtyDetailsVOImpl invHdrVo = getInvoicedQtyDetailsVO1();
             InvoicedQtyDetailsVORowImpl curRow = (InvoicedQtyDetailsVORowImpl)invHdrVo.getCurrentRow();
//                 Number hdrId =new Number( curRow.getInvoiceHeaderId());
//                 getInvoicedQtyDetailsVO1().clearCache();
//                 getInvoicedQtyDetailsVO1().executeQuery();
//                 Key masterkey = new Key(new Object[] { hdrId });
//                 invHdrVo.findAndSetCurrentRowByKey(masterkey, 0);
            
            CalculateInvoiceValuesVOImpl calVO = getCalculateInvoiceValuesVO1();
            calVO.setNamedWhereClauseParam("p_hdr_id", curRow.getInvoiceHeaderId());
            calVO.executeQuery();
            
            InvoicedQtyLinesVOImpl linesVO = getInvoicedQtyLinesVO1();
            InvoicedQtyLinesVORowImpl lineRow = (InvoicedQtyLinesVORowImpl)linesVO.getCurrentRow();
            CalculateInvoiceValuesVORowImpl calRow = (CalculateInvoiceValuesVORowImpl)calVO.first();
            
            Number temp = new Number(0);
            Number result = new Number(0);
            Number TransContractValue = new Number(curRow.getTransContractValue() );
            Number AdvanceReceived    = new Number(curRow.getAdvanceReceived());
            Number TransGrandTotal  = new Number(lineRow.getTransGrandTotal());
            Number Advrecovered = new Number( calRow.getAdvrecovered());
            
            if (Advrecovered.compareTo(TransContractValue) > 0 || 
                 Advrecovered.compareTo(AdvanceReceived) > 0    ||
                 Advrecovered.compareTo(TransGrandTotal) > 0) {
                 
                 if( TransContractValue.compareTo(AdvanceReceived)>0){
                     temp = AdvanceReceived;
                 }else{
                     temp = TransContractValue;
                 }
                 
                 if(temp.compareTo(TransGrandTotal) > 0){
                     result=TransGrandTotal;
                 }else{
                     result=temp;
                 }
                 
                curRow.setAdvanceRecovered(new BigDecimal(result.toString()));
            }else{
                curRow.setAdvanceRecovered(calRow.getAdvrecovered());
                }
                        
             if(calRow.getRetention().compareTo(curRow.getTransContractValue()) > 0 || calRow.getRetention().compareTo(lineRow.getTransGrandTotal()) > 0){
                if(curRow.getTransContractValue().compareTo(lineRow.getTransGrandTotal()) > 0) {   
                       curRow.setRetention(lineRow.getTransGrandTotal());
                 }else{
                       curRow.setRetention(curRow.getTransContractValue());
                 }
             }else{
                curRow.setRetention(calRow.getRetention());
                }         
//                getDBTransaction().commit();
//                 hdrId =new Number( curRow.getInvoiceHeaderId());
//               getInvoicedQtyDetailsVO1().executeQuery();   
//                 masterkey = new Key(new Object[] { hdrId });
//                invHdrVo.findAndSetCurrentRowByKey(masterkey, 0);
        }catch(Exception e){
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }
    
    public String calcualteCertifiedValues(){
    String message = ""; //TODO
        try{
                CertifiedQtyDetailsVOImpl certVO = getCertifiedQtyDetailsVO1();
                CertifiedQtyDetailsVORowImpl hdrRow = (CertifiedQtyDetailsVORowImpl)certVO.getCurrentRow();
                
                CertifiedQtyLinesVOImpl cerLinesVo = getCertifiedQtyLinesVO1();
                CertifiedQtyLinesVORowImpl linesRow = (CertifiedQtyLinesVORowImpl)cerLinesVo.getCurrentRow();
            
                CalculateCertifiedValuesVOImpl certifiedCalVO = getCalculateCertifiedValuesVO1();
                certifiedCalVO.setNamedWhereClauseParam("p_hdr_id", hdrRow.getCertifiedHeaderId());
                certifiedCalVO.executeQuery();
            
                CalculateCertifiedValuesVORowImpl calRow = (CalculateCertifiedValuesVORowImpl)certifiedCalVO.first();
                Number value = new Number(calRow.getAdvrecovered());
                Number TransContractValue = new Number( hdrRow.getTransContractValue());  
                Number AdvanceReceived = new Number( hdrRow.getAdvanceReceived());
                Number TransGrandTotal = new Number(linesRow.getTransGrandTotal());
                if (value != null) {
                     if ( value.compareTo(TransContractValue) > 0) {
                            hdrRow.setAdvanceRecovered(new BigDecimal(TransContractValue.toString()));
                    } else if (value.compareTo(AdvanceReceived) > 0) {
                        hdrRow.setAdvanceRecovered(new BigDecimal(AdvanceReceived.toString()));
                    } else if (value.compareTo(TransGrandTotal) > 0) {
                        hdrRow.setAdvanceRecovered(new BigDecimal(TransGrandTotal.toString()));
                    } else {
                        hdrRow.setAdvanceRecovered(new BigDecimal(value.toString()));
                    }
                }
            
                Number valueRet = new Number(calRow.getRetention());
                if (valueRet != null) {
                    if (TransContractValue != null && valueRet.compareTo(TransContractValue) > 0) {
                        hdrRow.setRetention(new BigDecimal(TransContractValue.toString()));
                    } else if (TransGrandTotal != null  &&
                               valueRet.compareTo(TransGrandTotal) > 0) {
                        hdrRow.setRetention(new BigDecimal(TransGrandTotal.toString()));
                    } else {
                        hdrRow.setRetention(new BigDecimal(valueRet.toString()));
                    }
                } 
            }catch(Exception e){
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }
    
    public String extendInvBOQLines(Long prevHdrId, Long currHdrId,
                                    String user) {
        String message = null;

        try {
            CallableStatement st =
                getDBTransaction().createCallableStatement(EXTEND_INVOICE_LINES,
                                                           0);
            st.setLong("p_prev_hdr_id", prevHdrId);
            st.setLong("p_curr_hdr_id", currHdrId);
            st.setString("p_user", user);


            st.registerOutParameter("p_message", Types.VARCHAR);
            st.execute();
            message = st.getString("p_message");
            if ("Success".equals(message)) {
                this.getDBTransaction().commit();
                getInvoicedQtyLinesVO1().executeQuery();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            message = e.getMessage();

        }
        return message;
    }

    public Boolean makeAsCurrentInvoiceHdrRow(BigDecimal invHeaderId) {
        Boolean updated = null;
        if (invHeaderId != null) {
            CommonUtilsAMImpl commAM = (CommonUtilsAMImpl)getCommonUtilsAM1();
            commAM.makeAsCurrentRow(getInvoicedQtyDetailsVO1(), invHeaderId);
            commAM.makeAsCurrentRow(getInvoicedQtyDetailsVO1(), invHeaderId);
            if (getInvoicedQtyDetailsVO1().getCurrentRow() != null) {
                InvoicedQtyDetailsVORowImpl row =
                    (InvoicedQtyDetailsVORowImpl)getInvoicedQtyDetailsVO1().getCurrentRow();
                if (row.getInvoiceHeaderId().doubleValue() ==
                    invHeaderId.doubleValue()) {
                    updated = true;
                }
            }
        }
        return updated;
    }

    public String populateInvoicedLines() {
        String message = null;
        try {
            InvoicedLInesPopulateVOImpl populateVO =
                (InvoicedLInesPopulateVOImpl)getInvoicedLInesPopulateVO1();
            RowQualifier rq = new RowQualifier("TransFlag='true'");
            Row[] rows = populateVO.getFilteredRows(rq);
            if (rows.length > 0) {
                for (int i = 0; i < rows.length; i++) {
                    InvoicedLInesPopulateVORowImpl poplateRow =
                        (InvoicedLInesPopulateVORowImpl)rows[i];
                    InvoicedQtyLinesVOImpl linesValidateVO =
                        getInvoicedQtyLinesVO2();
                    linesValidateVO.setNamedWhereClauseParam("p_boq_line_id",
                                                             poplateRow.getLineId());
                    linesValidateVO.setApplyViewCriteriaName("InvoiceLineExistsCriteria");
                    linesValidateVO.executeQuery();
                    if(linesValidateVO.first()==null){
                        InvoicedQtyLinesVORowImpl linesRow =
                            (InvoicedQtyLinesVORowImpl)getInvoicedQtyLinesVO1().createRow();
                        linesRow.setBoqHeaderId(poplateRow.getHeaderId());
                        linesRow.setBoqLineId(poplateRow.getLineId());
                        linesRow.setWdCummulativeQty(poplateRow.getWdCummulativeQty());
                        linesRow.setPreviousCummulativeQty(poplateRow.getPrevInvQty());
                        linesRow.setPreviousCertifiedCummulativeQty(poplateRow.getPrevCertCummulativeQty());
                        linesRow.setPreviousBoqRate(poplateRow.getPrevInvRate());
                        linesRow.setBaseLineBoqLineId(poplateRow.getBaseLineBoqLineId());
                        linesRow.setMasterInvoiceLineId(linesRow.getInvoiceLineId());
                        linesRow.setMasterBoqHeaderId(poplateRow.getMasterBoqHeaderId());
                        linesRow.setMasterBoqLineId(poplateRow.getMasterBoqLineId());
                        linesRow.setMasterWdLineId(poplateRow.getMasterWdLineId());
                        linesRow.setWdLineId(poplateRow.getWdLineId());
                        linesRow.setCurrentBoqRate(poplateRow.getSellingRate());
                        linesRow.setInvoiceCummulativeQty(poplateRow.getWdCummulativeQty());
                        linesRow.setActualInvoiceQty(poplateRow.getPrevActInvQty());
                        linesRow.setRemarks(poplateRow.getPrevRemarks());
                        linesRow.setExtendedFlag(poplateRow.getExtFlag());
                        linesRow.setInvCummQtyPer(poplateRow.getWdCummulativeQtyPerct());
                        linesRow.setTransInvoicePeriod(poplateRow.getWdCummulativeQty().subtract(poplateRow.getPrevInvQty()));
                        getInvoicedQtyLinesVO1().insertRow(poplateRow);
                    }
                }
                this.getDBTransaction().commit();
            }
            getInvoicedQtyLinesVO1().executeQuery();
            InvoicedQtyDetailsVORowImpl hdrRow =
                    (InvoicedQtyDetailsVORowImpl)getInvoicedQtyDetailsVO1().getCurrentRow();
                if (hdrRow.getParentInvoiceHeaderId() != null) {
                    message =
                            populatePrevInvLines(hdrRow.getInvoiceHeaderId().longValue(),
                                                 hdrRow.getParentInvoiceHeaderId().longValue(),
                                                 hdrRow.getMasterInvoiceHeaderId().longValue());
                    if (!"Success".equals(message)) {
                        return message;
                    }
                }
//            message = "Success";
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }

    public void executeInvoicedLinesQuery() {
        getInvoicedQtyLinesVO1().executeQuery();
    }


    public String callpkgForAMEProcessSubmit1() {
        String msg = "";
        CommonUtilsAMImpl commonAM = (CommonUtilsAMImpl)getCommonUtilsAM1();
        InvoicedQtyDetailsVORowImpl hdrRow =
            (InvoicedQtyDetailsVORowImpl)getInvoicedQtyDetailsVO1().getCurrentRow();
        if (hdrRow != null) {
            Long hdrId = new Long(hdrRow.getInvoiceHeaderId().longValue());
            Map paramsMap = new HashMap();
            try {
                paramsMap.put("docId",
                              new oracle.jbo.domain.Number(hdrRow.getInvoiceHeaderId()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            paramsMap.put("docTypeId", new oracle.jbo.domain.Number(3));
            paramsMap.put("ActionCode", "Submit");
            paramsMap.put("currEmpId", commonAM.getCurrentEmployeeId());
            paramsMap.put("remarks", "");
            paramsMap.put("docNum", hdrRow.getInvoiceDocumentNum());
            msg = commonAM.callAMEProcess(paramsMap);
            if (msg != null) {
                if ("Submitted".equals(msg)) {
                    submitInvoicedDocFor("I");
                    msg = "Success";
                } else if ("Finally Approved".equals(msg)) { //FYI approval only
                    submitInvoicedDocFor("A");
//                    updateBoqQuantities("I");
                }
            }
        }
        return msg;
    }

    public void submitInvoicedDocFor(String toDocStatus) {
        if (getInvoicedQtyDetailsVO1().getCurrentRow() != null) {
            InvoicedQtyDetailsVORowImpl hdrRow =
                (InvoicedQtyDetailsVORowImpl)getInvoicedQtyDetailsVO1().getCurrentRow();
            hdrRow.setInvoiceStatus(toDocStatus);
            if ("A".equals(toDocStatus)) {
                CommonUtilsAMImpl commanAM =
                    (CommonUtilsAMImpl)getCommonUtilsAM1();
                String userName = commanAM.getCurrentEmployeeUserName();
                //                hdrRow.setApprovedBy(userName);
                java.sql.Timestamp datetime =
                    new java.sql.Timestamp(System.currentTimeMillis());
                oracle.jbo.domain.Date daTime =
                    new oracle.jbo.domain.Date(datetime);
                //                hdrRow.setApprovedDate(daTime);
            }
        }
    }


    public String ReviseInvoicedDoc() {
        InvoicedQtyDetailsVORowImpl hdrRow =
            (InvoicedQtyDetailsVORowImpl)getInvoicedQtyDetailsVO1().getCurrentRow();
        if (hdrRow != null) {
            //            if(hdrRow.getTransReviseFlag()==null || !("Y".equals(hdrRow.getTransReviseFlag()))){
            CallableStatement st = null;
            Long hdrId = null;
            try {
                st =
 getDBTransaction().createCallableStatement(populate_revise_doc_Invoice, 0);
                st.setLong("p_header_id",
                           hdrRow.getInvoiceHeaderId().longValue());
                st.setString("p_username",
                             ((String)((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("UserName")));
                st.registerOutParameter("p_out_hdr_id", Types.NUMERIC);
                st.execute();

                hdrId = st.getLong("p_out_hdr_id");

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
            if (hdrId != null) {
                getInvoicedQtyDetailsVO1().clearCache();
                getInvoicedQtyDetailsVO1().executeQuery();
                ViewObjectImpl Invoicedheader = getInvoicedQtyDetailsVO1();
                Key masterkey = new Key(new Object[] { hdrId });
                Invoicedheader.findAndSetCurrentRowByKey(masterkey, 0);
            }
            //            }else{
            //                return "Invoice is Approved for this Period, So Can't be Revised.";
            //            }
        }
        return "";
    }
    
    public String refreshInvoiceHeader(){
        InvoicedQtyDetailsVORowImpl curRow = (InvoicedQtyDetailsVORowImpl)getInvoicedQtyDetailsVO1().getCurrentRow();
        BigDecimal hdrId = curRow.getInvoiceHeaderId();
        getInvoicedQtyDetailsVO1().executeQuery();
        ViewObjectImpl Invoicedheader = getInvoicedQtyDetailsVO1();
        Key masterkey = new Key(new Object[] { hdrId });
        Invoicedheader.findAndSetCurrentRowByKey(masterkey, 0); 
        return "Success";
    }
    public String validateInvLineActPercentage() {
        InvoicedQtyLinesVORowImpl linesRow =
            (InvoicedQtyLinesVORowImpl)getInvoicedQtyLinesVO1().getCurrentRow();
        if (linesRow != null) {
            if (linesRow.getBillQty() != null &&
                linesRow.getActInvQtyPer() != null) {
                Double invActPer = linesRow.getActInvQtyPer().doubleValue();
                Double billQty = linesRow.getBillQty().doubleValue();
                if (invActPer.doubleValue() > 100) {
                    linesRow.setActInvQtyPer(null);
                    return "Cannot be more than 100.";
                }
                if (invActPer.doubleValue() < 0) {
                    linesRow.setActInvQtyPer(null);
                    return "Cannot be a negative value.";
                }
                Double invCummQty = getPercentageQty(invActPer, billQty);
                linesRow.setActualInvoiceQty(new BigDecimal(invCummQty.toString()));
            }
        }

        return "";
    }

    public String validateInvLineActQty() {
        InvoicedQtyLinesVORowImpl linesRow =
            (InvoicedQtyLinesVORowImpl)getInvoicedQtyLinesVO1().getCurrentRow();
        if (linesRow != null) {
            if (linesRow.getBillQty() != null &&
                linesRow.getActualInvoiceQty() != null) {
                if (linesRow.getBillQty().compareTo(linesRow.getActualInvoiceQty()) <
                    0) {
                    linesRow.setActualInvoiceQty(null);
                    return "Error";
                }
                if (linesRow.getActualInvoiceQty().doubleValue() < 0) {
                    linesRow.setActualInvoiceQty(null);
                    return "Cannot be a negative value.";
                }
                Double invActQty =
                    linesRow.getActualInvoiceQty().doubleValue();
                Double billQty = linesRow.getBillQty().doubleValue();
                Double invActQtyPer = getPercentage(invActQty, billQty);
                linesRow.setActInvQtyPer(new BigDecimal(invActQtyPer.toString()));
            }
        }

        return "";
    }

    public String validateInvoiceLinePercentage() {
        InvoicedQtyLinesVORowImpl linesRow =
            (InvoicedQtyLinesVORowImpl)getInvoicedQtyLinesVO1().getCurrentRow();
        if (linesRow != null) {
            if (linesRow.getBillQty() != null &&
                linesRow.getInvCummQtyPer() != null) {
                Double invCummPer = linesRow.getInvCummQtyPer().doubleValue();
                Double billQty = linesRow.getBillQty().doubleValue();
                if (invCummPer.doubleValue() > 100) {
                    linesRow.setInvCummQtyPer(null);
                    return "Cannot be more than 100.";
                }
                if (invCummPer.doubleValue() < 0) {
                    linesRow.setInvCummQtyPer(null);
                    return "Cannot be a negative value.";
                }
                Double invCummQty = getPercentageQty(invCummPer, billQty);
                linesRow.setInvoiceCummulativeQty(new BigDecimal(invCummQty.toString()));
            }
        }

        return "";
    }

    public String validateInvoicedLineQuantities() {
        InvoicedQtyLinesVORowImpl linesRow =
            (InvoicedQtyLinesVORowImpl)getInvoicedQtyLinesVO1().getCurrentRow();
        if (linesRow != null) {
            if (linesRow.getBillQty() != null &&
                linesRow.getInvoiceCummulativeQty() != null) {
                if (linesRow.getBillQty().compareTo(linesRow.getInvoiceCummulativeQty()) <
                    0) {
                    linesRow.setInvoiceCummulativeQty(null);
                    return "Error";
                }
                if (linesRow.getInvoiceCummulativeQty().doubleValue() < 0) {
                    linesRow.setInvoiceCummulativeQty(null);
                    return "Cannot be a negative value.";
                }
                Double invCummQty =
                    linesRow.getInvoiceCummulativeQty().doubleValue();
                Double billQty = linesRow.getBillQty().doubleValue();
//                Double invCummQtyPer = getPercentage(invCummQty, billQty);
//                linesRow.setInvCummQtyPer(new BigDecimal(invCummQtyPer.toString()));
            }
        }
        return "";
    }

    public String validateInvoiceLines() {
        InvoicedQtyLinesVOImpl linesVO = getInvoicedQtyLinesVO1();
        Row[] rows = linesVO.getAllRowsInRange();
        for (Row row : rows) {
            InvoicedQtyLinesVORowImpl linesRow =
                (InvoicedQtyLinesVORowImpl)row;
            Double invCummQty =
                linesRow.getInvoiceCummulativeQty().doubleValue();
            Double billQty = linesRow.getBillQty().doubleValue();
            Double invCummPer = linesRow.getInvCummQtyPer().doubleValue();

            Double newInvCummQty = getPercentageQty(invCummPer, billQty);
            Double newInvCummQtyPer = getPercentage(invCummQty, billQty);
            if (invCummPer.doubleValue() != newInvCummQtyPer.doubleValue() ||
                invCummQty.doubleValue() != newInvCummQty.doubleValue()) {
                return "Line quantities are not calculated correctly. Please save before submit.";
            }
            //            Double actCummQty =
            //                linesRow.getInvoiceCummulativeQty().doubleValue();
            //            Double actCummPer = linesRow.getInvCummQtyPer().doubleValue();
            //
            //            Double newActCummQty = getPercentageQty(actCummPer, billQty);
            //            Double newActCummQtyPer = getPercentage(actCummQty, billQty);
            //            if (actCummPer.doubleValue() != newActCummQtyPer.doubleValue() ||
            //                actCummQty.doubleValue() != newActCummQty.doubleValue()) {
            //                return "Line Quantities are not calculated correctly, Please save before submit";
            //            }
        }

        return "";
    }

    public String calculateInvoiceLineValues() {
        String message = validateInvoiceLines();
        if (message != null && message.length() > 0) {
            message = "";
            InvoicedQtyLinesVOImpl linesVO = getInvoicedQtyLinesVO1();
            linesVO.executeQuery();
            Row[] rows = linesVO.getAllRowsInRange();
            for (Row row : rows) {
                InvoicedQtyLinesVORowImpl linesRow =
                    (InvoicedQtyLinesVORowImpl)row;
                Double invCummPer = linesRow.getInvCummQtyPer().doubleValue();
                Double billQty = linesRow.getBillQty().doubleValue();
                Double invCummQty =
                    linesRow.getInvoiceCummulativeQty().doubleValue();
                Double newInvCummQty = getPercentageQty(invCummPer, billQty);
                if (invCummQty.doubleValue() != newInvCummQty.doubleValue()) {
                    linesRow.setInvoiceCummulativeQty(new BigDecimal(newInvCummQty.toString()));
                }
                //                Double actCummQty =
                //                    linesRow.getInvoiceCummulativeQty().doubleValue();
                //                Double actCummPer = linesRow.getActInvQtyPer().doubleValue();
                //
                //                Double newActCummQty = getPercentageQty(actCummPer, billQty);
                //                if (actCummQty.doubleValue() != newActCummQty.doubleValue()) {
                //                    linesRow.setActualInvoiceQty(new BigDecimal(newActCummQty.toString()));
                //                }
            }
            this.getDBTransaction().commit();
            linesVO.executeQuery();
            return "Cumulative and actual quantities are adjusted as per percentage values.";
        }
        return "";
    }

    public double getPercentage(Double perValue, Double totalValue) {
        Double percent = 0.0;
        if (perValue != null && totalValue != null && totalValue != 0) {
            percent = (perValue / totalValue) * 100;
            percent = round(percent, 2);
        }
        return percent;
    }

    public double getPercentageQty(Double percent, Double totalValue) {
        Double qty = 0.0;
        if (percent != null && totalValue != null) {
            qty = (percent * totalValue) / 100;
        }
        return qty;
    }

    public String validateInvoicedLines() {
        String message = null;
        try {
            String exeMessage = "";
            InvoicedQtyDetailsVORowImpl hdrRow =
                (InvoicedQtyDetailsVORowImpl)getInvoicedQtyDetailsVO1().getCurrentRow();
//            exeMessage = validateInvoiceLines();
            if (exeMessage != null) {
                if (exeMessage.length() > 0) {
                    return exeMessage;
                } else {
                    if (hdrRow != null) {
                        CallableStatement st = null;
                        st =
 getDBTransaction().createCallableStatement(VALIDATE_INV_BOQ_LINES, 0);
                        st.setLong("p_invoice_header_id",
                                   hdrRow.getInvoiceHeaderId().longValue());
                        String userName =
                            ((String)((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("UserName"));
                        st.setString("p_user_name", userName);
                        st.registerOutParameter("p_message", Types.VARCHAR);

                        st.execute();
                        exeMessage = st.getString("p_message");

                        this.getDBTransaction().commit();
                        makeAsCurrentInvoiceHdrRow(hdrRow.getInvoiceHeaderId());
                        getInvoicedQtyLinesVO1().executeQuery();
//                        getWorkDoneQtyLinesVO1().executeQuery();
                    } else {
                        exeMessage =
                                "Unable to find current work done document.";
                    }
                }
            }
            return exeMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }


    /**
     * Container's getter for InvoicedQtySearchVO1.
     * @return InvoicedQtySearchVO1
     */
    public InvoicedQtySearchVOImpl getInvoicedQtySearchVO1() {
        return (InvoicedQtySearchVOImpl)findViewObject("InvoicedQtySearchVO1");
    }


    public byte[] downloadWorkDoneDetails() {

        WorkDoneQtyDetailsVORowImpl hdrRow =
            (WorkDoneQtyDetailsVORowImpl)getWorkDoneQtyDetailsVO1().getCurrentRow();

        byte[] data = null;

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            // create a new sheet
            XSSFSheet worksheet =
                workbook.createSheet("Import workdone quantity");

            XSSFRow excelrow = null;
            XSSFFont font = workbook.createFont();
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeight(new Double(10));
            font.setFontName("Tahoma");
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            
            XSSFFont Headingfont = workbook.createFont();
            Headingfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            Headingfont.setFontHeight(new Double(14));
            Headingfont.setFontName("Tahoma");
            CellStyle cellStyleHeading = workbook.createCellStyle();
            cellStyleHeading.setFont(Headingfont);
            cellStyleHeading.setLocked(true);
            
            XSSFFont reqfont = workbook.createFont();
           reqfont.setFontHeight(new Double(8));
            reqfont.setFontName("Tahoma");
            CellStyle cellStylereq = workbook.createCellStyle();
            cellStylereq.setFont(reqfont);
            cellStylereq.setLocked(true);
            
            CellStyle cellStyleHdr = workbook.createCellStyle();
            cellStyleHdr.setFont(font);
            cellStyleHdr.setLocked(true);
           
            CellStyle cellStyleHdrVal = workbook.createCellStyle();
            cellStyleHdrVal.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            cellStyleHdrVal.setFillPattern(CellStyle.BORDER_MEDIUM);
            XSSFFont font2 = workbook.createFont();
            font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font2.setFontName("Tahoma");
            font2.setFontHeight(new Double(10));
            cellStyleHdrVal.setFont(font2);
           
            cellStyleHdrVal.setLocked(true);
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setLocked(true);
            cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            cellStyle.setFillPattern(CellStyle.BORDER_MEDIUM);
            
            worksheet.protectSheet("pwd");
            worksheet.setDisplayGridlines(false);

            worksheet.createFreezePane(0, 1);
            workbook.getSheetAt(workbook.getActiveSheetIndex()).createFreezePane(0,
                                                                                 5);
            cellStyleHdr.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
            cellStyleHdr.setFillPattern(CellStyle.SOLID_FOREGROUND);

            DataFormat fmt = workbook.createDataFormat();
            CellStyle textStyle = workbook.createCellStyle();
            textStyle.setDataFormat(fmt.getFormat("@"));
            // worksheet.setDefaultColumnStyle(0, textStyle);

            XSSFFont font1 = workbook.createFont();
            font1.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
            font1.setFontHeight(new Double(10));
            CellStyle cellStyle1 = workbook.createCellStyle();
            cellStyle1.setFont(font1);

            for (int k = 0; k < 17; k++) {
                worksheet.setColumnWidth(k, 6500);
            }

            excelrow = (XSSFRow)worksheet.createRow(0);
            XSSFCell cellV0 = excelrow.createCell(0);
            cellV0.setCellValue("Version V1");
            cellV0.setCellStyle(cellStyleHdr);
            cellV0.setCellType(Cell.CELL_TYPE_STRING);
           
            XSSFCell cellV1 = excelrow.createCell(1);
            cellV1.setCellValue("Import Work Done");
            cellV1.setCellStyle(cellStyleHeading);
            cellV1.setCellType(Cell.CELL_TYPE_STRING);

            excelrow = (XSSFRow)worksheet.createRow(2);
            XSSFCell cellV01 = excelrow.createCell(1);
            cellV01.setCellValue("** One of the field is Required");
            cellV01.setCellStyle(cellStylereq);
            cellV01.setCellType(Cell.CELL_TYPE_STRING);
            
            excelrow = (XSSFRow)worksheet.createRow(4);
            XSSFCell cellV2 = excelrow.createCell(1);
            cellV2.setCellValue("Business Unit");
            cellV2.setCellStyle(cellStyleHdr);
            
            cellV2.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV3 = excelrow.createCell(3);
            cellV3.setCellValue(hdrRow != null ? hdrRow.getBuName() : "");
            cellV3.setCellStyle(cellStyleHdrVal);
            
            cellV3.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV4 = excelrow.createCell(4);
            cellV4.setCellValue("Contract Number");
            cellV4.setCellStyle(cellStyleHdr);
            
            cellV4.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV5 = excelrow.createCell(5);
            cellV5.setCellValue(hdrRow != null ? hdrRow.getContractNumber() : "");
            cellV5.setCellStyle(cellStyleHdrVal);
            
            cellV5.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV6 = excelrow.createCell(6);
            cellV6.setCellValue("Document Number");
            cellV6.setCellStyle(cellStyleHdr);
            
            cellV6.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV7 = excelrow.createCell(8);
            cellV7.setCellValue(hdrRow != null ? hdrRow.getWdDocumentNum() :
                                "");
            cellV7.setCellStyle(cellStyleHdrVal);
            cellV7.setCellType(Cell.CELL_TYPE_STRING);

            XSSFCell cellV16 = excelrow.createCell(10);
            cellV16.setCellValue("Contract Type");
            cellV16.setCellStyle(cellStyleHdr);
            cellV16.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV27 = excelrow.createCell(11);
            cellV27.setCellValue(hdrRow != null ? hdrRow.getContractType() :
                                "");
            cellV27.setCellStyle(cellStyleHdrVal);
            cellV27.setCellType(Cell.CELL_TYPE_STRING);
            
            XSSFCell cellV106 = excelrow.createCell(12);
            cellV106.setCellValue("Currency");
            cellV106.setCellStyle(cellStyleHdr);
            cellV106.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV207 = excelrow.createCell(13);
            cellV207.setCellValue(hdrRow != null ? hdrRow.getCurrencyCode() :
                                "");
            cellV207.setCellStyle(cellStyleHdrVal);
            cellV207.setCellType(Cell.CELL_TYPE_STRING);
            
            XSSFCell cellV8 = excelrow.createCell(16);
            cellV8.setCellValue("Period");
            cellV8.setCellStyle(cellStyleHdr);
            cellV8.setCellType(Cell.CELL_TYPE_STRING);

            XSSFCell cellV9 = excelrow.createCell(18);
            cellV9.setCellValue(hdrRow != null ? hdrRow.getPeriodOfMeasure() :
                                "");
            cellV9.setCellStyle(cellStyleHdrVal);
            cellV9.setCellType(Cell.CELL_TYPE_STRING);
            
            excelrow = (XSSFRow)worksheet.createRow(6);
            
            XSSFCell cellA0 = excelrow.createCell(0);
            cellA0.setCellValue("BOQ_LINE ID");
            cellA0.setCellStyle(cellStyleHdr);
            cellA0.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA1 = excelrow.createCell(1);
            cellA1.setCellValue("Bill/Page/Item");
            cellA1.setCellStyle(cellStyleHdr);
            cellA1.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA2 = excelrow.createCell(2);
            cellA2.setCellValue("Level");
            cellA2.setCellStyle(cellStyleHdr);
            XSSFCell cellA2000 = excelrow.createCell(3);
            cellA2000.setCellValue("Line Type");
            cellA2000.setCellStyle(cellStyleHdr);
            cellA2000.setCellType(Cell.CELL_TYPE_STRING);
//            XSSFCell cellA3 = excelrow.createCell(4);
//            cellA3.setCellValue("Page/Item");
//            cellA3.setCellStyle(cellStyleHdr);
//            cellA3.setCellType(Cell.CELL_TYPE_STRING);
//            XSSFCell cellA4 = excelrow.createCell(5);
//            cellA4.setCellValue("Item");
//            cellA4.setCellStyle(cellStyleHdr);
//            cellA4.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA5 = excelrow.createCell(4);
            cellA5.setCellValue("Bill Description");
            cellA5.setCellStyle(cellStyleHdr);
            cellA5.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA6 = excelrow.createCell(5);
            cellA6.setCellValue("Bill Quantity");
            cellA6.setCellStyle(cellStyleHdr);
            cellA6.setCellType(Cell.CELL_TYPE_STRING);
             XSSFCell cellA4 = excelrow.createCell(6);
            cellA4.setCellValue("Final Quantity");
            cellA4.setCellStyle(cellStyleHdr);
            cellA4.setCellType(Cell.CELL_TYPE_STRING);
            
            XSSFCell cellA24 = excelrow.createCell(7); 
            cellA24.setCellValue("UOM");
            cellA24.setCellStyle(cellStyleHdr);
            cellA24.setCellType(Cell.CELL_TYPE_STRING);
            
            XSSFCell cellA7 = excelrow.createCell(8);
            cellA7.setCellValue("Bill Rate");
            cellA7.setCellStyle(cellStyleHdr);
            cellA7.setCellType(Cell.CELL_TYPE_STRING);
            
            XSSFCell cellA8 = excelrow.createCell(9);
            cellA8.setCellValue("Previous Bill Rate");
            cellA8.setCellStyle(cellStyleHdr);
            cellA8.setCellType(Cell.CELL_TYPE_STRING);
            
            XSSFCell cellA20 = excelrow.createCell(10);
            cellA20.setCellValue("Previous WD Qty");
            cellA20.setCellStyle(cellStyleHdr);
            cellA20.setCellType(Cell.CELL_TYPE_STRING);
            
            XSSFCell cellA9 = excelrow.createCell(11);
            cellA9.setCellValue("** Cumulative WD Qty");
            cellA9.setCellStyle(cellStyleHdr);
            cellA9.setCellType(Cell.CELL_TYPE_STRING);
            
            XSSFCell cellA200 = excelrow.createCell(12);
            cellA200.setCellValue("** Cumulative WD Qty %");
            cellA200.setCellStyle(cellStyleHdr);
            cellA200.setCellType(Cell.CELL_TYPE_STRING);
            
            XSSFCell cellA91 = excelrow.createCell(13);
            cellA91.setCellValue("** Cumulative WD Amount");
            cellA91.setCellStyle(cellStyleHdr);
            cellA91.setCellType(Cell.CELL_TYPE_STRING);
            
            XSSFCell cellA90 = excelrow.createCell(14);
            cellA90.setCellValue("Actual Workdone %");
            cellA90.setCellStyle(cellStyleHdr);
            cellA90.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA100 = excelrow.createCell(15);
            cellA100.setCellValue("Actual Workdone");
            cellA100.setCellStyle(cellStyleHdr);
            cellA100.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA101 = excelrow.createCell(16);
            cellA101.setCellValue("Remarks");
            cellA101.setCellStyle(cellStyleHdr);
            cellA101.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA1002 = excelrow.createCell(17);
            cellA1002.setCellValue("WD_LINE_ID");
            cellA1002.setCellStyle(cellStyleHdr);
            cellA1002.setCellType(Cell.CELL_TYPE_STRING);
            hdrRow.getWdHeaderId();  
//            RowSetIterator iter =
//                (RowSetIterator)hdrRow.getWorkDoneQtyLinesVO();
            RowSetIterator iter =getWorkDoneQtyLinesVO1().createRowSetIterator(null);
            int i = 6;
            while (iter.hasNext()) {
                //print data from next row in excel

                WorkDoneQtyLinesVORowImpl newrow =
                    (WorkDoneQtyLinesVORowImpl)iter.next();
                i++;
                excelrow = (XSSFRow)worksheet.createRow((int)i);

                XSSFCell cellA10 = excelrow.createCell(0);
                cellA10.setCellValue(newrow.getBoqLineId().doubleValue());
                cellA10.setCellStyle(cellStyle);
                XSSFCell cellA11 = excelrow.createCell(1);
                cellA11.setCellValue(newrow.getBillPageItem());
                cellA11.setCellStyle(cellStyle);
                XSSFCell cellA12 = excelrow.createCell(2);
                cellA12.setCellValue(newrow.getBoqLevel());
                cellA12.setCellStyle(cellStyle);
                XSSFCell cellA1200 = excelrow.createCell(3);
                cellA1200.setCellValue(newrow.getTransLineType());
                cellA1200.setCellStyle(cellStyle);
//                XSSFCell cellA13 = excelrow.createCell(4);
//                cellA13.setCellValue(newrow.getPageItem());
//                cellA13.setCellStyle(cellStyle);
//                XSSFCell cellA14 = excelrow.createCell(5);
//                cellA14.setCellValue(newrow.getItem());
//                cellA14.setCellStyle(cellStyle);
                XSSFCell cellA15 = excelrow.createCell(4);
                cellA15.setCellValue(newrow.getDescription());
                cellA15.setCellStyle(cellStyle);
                XSSFCell cellA16 = excelrow.createCell(5);
                cellA16.setCellValue(newrow.getBillQty().doubleValue());
                cellA16.setCellType(Cell.CELL_TYPE_NUMERIC);
                cellA16.setCellStyle(cellStyle);
                XSSFCell cellA13 = excelrow.createCell(6);
                cellA13.setCellValue(newrow.getFinalQty().doubleValue());
                cellA13.setCellType(Cell.CELL_TYPE_NUMERIC);
                cellA13.setCellStyle(cellStyle);
                
                XSSFCell cellA113 = excelrow.createCell(7);
                cellA113.setCellStyle(cellStyle);
                cellA113.setCellValue(newrow.getUom());
                cellA113.setCellType(Cell.CELL_TYPE_NUMERIC);
                
                XSSFCell cellA17 = excelrow.createCell(8);
                cellA17.setCellStyle(cellStyle);
                cellA17.setCellValue(newrow.getSellingRate() != null ?
                                     newrow.getSellingRate().doubleValue() :
                                     0);
                cellA17.setCellType(Cell.CELL_TYPE_NUMERIC);
                
                XSSFCell cellA18 = excelrow.createCell(9);
                cellA18.setCellStyle(cellStyle);
                cellA18.setCellValue(newrow.getPreviousBoqRate() != null ?
                                     newrow.getPreviousBoqRate().doubleValue() :
                                     0);
                cellA18.setCellType(Cell.CELL_TYPE_NUMERIC);
                CellStyle cellStyleunlock = workbook.createCellStyle();
                cellStyleunlock.setLocked(false);
                
                XSSFCell cellA1180 = excelrow.createCell(10);
                cellA1180.setCellStyle(cellStyle);
                cellA1180.setCellValue(newrow.getPreviousCummulativeQty() != null ?
                                     newrow.getPreviousCummulativeQty().doubleValue() :
                                     0);
                cellA1180.setCellType(Cell.CELL_TYPE_NUMERIC);
                cellA1180.setCellStyle(cellStyle);
                
                XSSFCell cellA190 = excelrow.createCell(11);
                cellA190.setCellValue(newrow.getWdCummulativeQty() != null ?
                                      newrow.getWdCummulativeQty().doubleValue() :
                                      0);
                cellA190.setCellType(Cell.CELL_TYPE_NUMERIC);
                cellA190.setCellStyle(cellStyleunlock);
                
                XSSFCell cellA118 = excelrow.createCell(12);
                cellA118.setCellValue(newrow.getWdCummulativeQty() != null ?
                                      newrow.getWdCummulativeQty().divide(newrow.getBillQty(),
                                                                          4,
                                                                          BigDecimal.ROUND_HALF_UP).doubleValue() *
                                      100 : 0);
                cellA118.setCellType(Cell.CELL_TYPE_NUMERIC);
                cellA118.setCellStyle(cellStyleunlock);
                
                XSSFCell cellA191 = excelrow.createCell(13);
                cellA191.setCellValue(newrow.getWdCummulativeAmount() != null ?
                                      newrow.getWdCummulativeAmount().doubleValue() :
                                      0);
                cellA191.setCellType(Cell.CELL_TYPE_NUMERIC);
                cellA191.setCellStyle(cellStyleunlock);
                
                XSSFCell cellA19 = excelrow.createCell(14);
                cellA19.setCellValue(newrow.getActualWdQtyPerct() != null ?
                                      newrow.getActualWdQtyPerct().doubleValue() :
                                      0);
                cellA19.setCellType(Cell.CELL_TYPE_NUMERIC);
                cellA19.setCellStyle(cellStyle);
                
                XSSFCell cellA119 = excelrow.createCell(15);
                cellA119.setCellValue(newrow.getActualWdQty() != null ?
                                     newrow.getActualWdQty().doubleValue() :
                                     0);
                cellA119.setCellType(Cell.CELL_TYPE_NUMERIC);
                cellA119.setCellStyle(cellStyle);
                
                XSSFCell cellA102 = excelrow.createCell(16);
                cellA102.setCellValue(newrow.getRemarks() != null ?
                                      newrow.getRemarks() : "");
                cellA102.setCellStyle(cellStyleunlock);
                
                XSSFCell cellA1001 = excelrow.createCell(17);
                cellA1001.setCellValue(newrow.getWdLineId().doubleValue());
                cellA1001.setCellType(Cell.CELL_TYPE_NUMERIC);
                cellA1001.setCellStyle(cellStyle);
            }
            iter.closeRowSetIterator();

            //Hiding Columns
            worksheet.setColumnHidden(0, true);
            worksheet.setColumnHidden(2, true);
            worksheet.setColumnHidden(7, true); //As it is extra column it is hidden
            worksheet.setColumnHidden(9, true);
            worksheet.setColumnHidden(14, true);
            worksheet.setColumnHidden(15, true);
            worksheet.setColumnHidden(17, true);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                workbook.write(bos);
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            data = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }


    public String uploadWDXLFile(UploadedFile uploadedFile) {
        String message = null;
        try {
            CommonUtilsAMImpl commonAM =
                (CommonUtilsAMImpl)getCommonUtilsAM1();
            Number uploadId = commonAM.getUserUploadId();
            WorkDoneQtyDetailsVORowImpl hdrRow =
                (WorkDoneQtyDetailsVORowImpl)getWorkDoneQtyDetailsVO1().getCurrentRow();
            hdrRow.setFileName(uploadedFile.getFilename());
            try {
                if (uploadedFile != null) {
                    InputStream inputStream = uploadedFile.getInputStream();
                    XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                    inputStream.close();
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    Iterator<org.apache.poi.ss.usermodel.Row> rowIterator =
                        sheet.iterator();
                    int i = 0;
                    for (i = 0; i < 4; i++) {
                        org.apache.poi.ss.usermodel.Row HdrRow =
                            rowIterator.next();
                        if (i == 0 &&
                            !("Version V1".equals(HdrRow.getCell(0).getStringCellValue()))) {
                            return "Verion mismatch. Please download latest template.";
                        }
                    }
                    while (rowIterator.hasNext()) {
                        org.apache.poi.ss.usermodel.Row row =
                            rowIterator.next();

                        WorkDoneUploadIntVORowImpl uploadRow =
                            (WorkDoneUploadIntVORowImpl)getWorkDoneUploadIntVO1().createRow();

                        uploadRow.setUploadId(uploadId.bigDecimalValue());

                        for (int j = 0; j <= 17; j++) {
                            Cell cell = null;
                            cell = row.getCell(j);
                            Object cellValue = null;
                            if (cell != null) {
                                cellValue =
                                        commonAM.getCellValue((XSSFCell)cell);

                                if (cellValue != null) {
                                    try {
                                        switch (j) {
                                        case 0:
                                            uploadRow.setWdLineNumber(new BigDecimal(cellValue.toString()));
                                            break;
                                        case 5:
                                            uploadRow.setBillQuantity(new BigDecimal(cellValue !=
                                                                                     null ?
                                                                                     cellValue.toString() :
                                                                                     "0"));
                                            break;
                                        case 6:
                                            uploadRow.setFinalQty(new BigDecimal(cellValue !=
                                                                                     null ?
                                                                                     cellValue.toString() :
                                                                                     "0"));
                                            break;
                                        case 9:
                                            uploadRow.setCurrentBoqRate(new BigDecimal(cellValue !=
                                                                                     null ?
                                                                                     cellValue.toString() :
                                                                                     "0"));
                                            break;
                                        case 11:
                                            uploadRow.setWdCummulativeQty(new BigDecimal(cellValue !=
                                                                                              null ?
                                                                                              cellValue.toString() :
                                                                                              "0"));
                                            break;
                                        case 12:
                                            uploadRow.setWdCummulativeQtyPerct(new BigDecimal(cellValue !=
                                                                                         null ?
                                                                                         cellValue.toString() :
                                                                                         "0"));
                                            break;
                                        case 13:
                                            uploadRow.setWdCummulativeAmount(new BigDecimal(cellValue !=
                                                                                            null ?
                                                                                            cellValue.toString() :
                                                                                            "0"));
                                            break;
                                        case 14:
                                            uploadRow.setActualWdQtyPerct(new BigDecimal(cellValue !=
                                                                                         null ?
                                                                                         cellValue.toString() :
                                                                                         "0"));
                                            break;
                                        case 15:
                                            uploadRow.setActualWdQty(new BigDecimal(cellValue !=
                                                                                    null ?
                                                                                    cellValue.toString() :
                                                                                    "0"));
                                            break;
                                        case 16:
                                            uploadRow.setRemarks(cellValue !=
                                                                 null ?
                                                                 cellValue.toString() :
                                                                 null);
                                            break;
                                        case 17:
                                            uploadRow.setWdLineId(new BigDecimal(cellValue !=
                                                                                 null ?
                                                                                 cellValue.toString() :
                                                                                 "0"));
                                            break;

                                        default:
                                            break;
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        workbook.close();
                                        return "Invalid values at row no: " +
                                            (i + 1) + " column no: " +
                                            (j + 1) + " Error Message:  " +
                                            e.getMessage();
                                    }
                                }
                            } //END IF
                            else {
                                //Cell value is null
                                if (!(j == 5 || j == 12)) {
                                    workbook.close();
                                    return "Required values at row no: " +
                                        (i + 1) + " column no: " + (j + 1) +
                                        "";
                                }
                            }
                        } //END FOR
                        getWorkDoneUploadIntVO1().insertRow(uploadRow);
                        i++;
                    }
                    workbook.close();
                }
            } catch (Exception e) {
            e.printStackTrace();
                return e.getMessage();
            }
            this.getDBTransaction().commit();
            getWorkDoneQtyLinesVO1().executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "Success";
    }

    /**
     * Container's getter for WorkDoneUploadIntVO1.
     * @return WorkDoneUploadIntVO1
     */
    public WorkDoneUploadIntVOImpl getWorkDoneUploadIntVO1() {
        return (WorkDoneUploadIntVOImpl)findViewObject("WorkDoneUploadIntVO1");
    }

    public String validateWDUploadDetails() {
        String msg = null;
        if (((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("uploadId") !=
            null) {
            CommonUtilsAMImpl commonAM =
                (CommonUtilsAMImpl)getCommonUtilsAM1();
            Number uploadId =
                (Number)((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("uploadId");


            WorkDoneQtyDetailsVORowImpl hdrRow =
                (WorkDoneQtyDetailsVORowImpl)getWorkDoneQtyDetailsVO1().getCurrentRow();
            if (hdrRow != null) {
                Map paramsMap = new HashMap();
                paramsMap.put("BuId", hdrRow.getBuId());
                paramsMap.put("ProjectId", hdrRow.getProjectId());
                paramsMap.put("UploadId", uploadId);
                paramsMap.put("WDHeaderId", hdrRow.getWdHeaderId());
                paramsMap.put("WDDocNum", hdrRow.getWdDocumentNum());

                BOQUploadsAMImpl uploadAM =
                    (BOQUploadsAMImpl)getBOQUploadsAM1();
                String returnMsg = uploadAM.validateWDUpload(paramsMap);
                if (returnMsg != null) {
                    msg = returnMsg;
                    getWorkDoneQtyLinesVO1().executeQuery();
                } else
                    msg = "NOMSG";
            } else
                msg = "NOHDR";
        } else {
            msg = "Upload id not found.";
        }
        return msg;
    }

    /**
     * Container's getter for BOQUploadsAM1.
     * @return BOQUploadsAM1
     */
    public ApplicationModuleImpl getBOQUploadsAM1() {
        return (ApplicationModuleImpl)findApplicationModule("BOQUploadsAM1");
    }

    /**
     * Container's getter for InvoicedQtyLinesVO1.
     * @return InvoicedQtyLinesVO1
     */
    public InvoicedQtyLinesVOImpl getInvoicedQtyLinesVO1() {
        return (InvoicedQtyLinesVOImpl)findViewObject("InvoicedQtyLinesVO1");
    }

    /**
     * Container's getter for InvoiceDetailsToLinesVL1.
     * @return InvoiceDetailsToLinesVL1
     */
    public ViewLinkImpl getInvoiceDetailsToLinesVL1() {
        return (ViewLinkImpl)findViewLink("InvoiceDetailsToLinesVL1");
    }

    /**
     * Container's getter for InvoicedLInesPopulateVO1.
     * @return InvoicedLInesPopulateVO1
     */
    public InvoicedLInesPopulateVOImpl getInvoicedLInesPopulateVO1() {
        return (InvoicedLInesPopulateVOImpl)findViewObject("InvoicedLInesPopulateVO1");
    }

    /**
     * Container's getter for CertifiedLinesPopulateVO1.
     * @return CertifiedLinesPopulateVO1
     */
    public CertifiedLinesPopulateVOImpl getCertifiedLinesPopulateVO1() {
        return (CertifiedLinesPopulateVOImpl)findViewObject("CertifiedLinesPopulateVO1");
    }


    public byte[] downloadInvoicedDetails() {

        InvoicedQtyDetailsVORowImpl hdrRow =
            (InvoicedQtyDetailsVORowImpl)getInvoicedQtyDetailsVO1().getCurrentRow();

        byte[] data = null;

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            // create a new sheet
            XSSFSheet worksheet =
                workbook.createSheet("Import invoiced quantity");

            XSSFRow excelrow = null;
            XSSFFont font = workbook.createFont();
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            CellStyle cellStyleHdr = workbook.createCellStyle();
            cellStyleHdr.setFont(font);
            cellStyleHdr.setLocked(true);
            CellStyle cellStyleHdrVal = workbook.createCellStyle();
            XSSFFont font2 = workbook.createFont();
            font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            cellStyleHdrVal.setFont(font2);
            cellStyleHdrVal.setLocked(true);
            worksheet.protectSheet("pInvoiced");
            worksheet.setDisplayGridlines(false);

            worksheet.createFreezePane(0, 1);
            workbook.getSheetAt(workbook.getActiveSheetIndex()).createFreezePane(0,
                                                                                 5);
            cellStyleHdr.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
            cellStyleHdr.setFillPattern(CellStyle.SOLID_FOREGROUND);

            DataFormat fmt = workbook.createDataFormat();
            CellStyle textStyle = workbook.createCellStyle();
            textStyle.setDataFormat(fmt.getFormat("@"));
            // worksheet.setDefaultColumnStyle(0, textStyle);

            XSSFFont font1 = workbook.createFont();
            font1.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
            CellStyle cellStyle1 = workbook.createCellStyle();
            cellStyle1.setFont(font1);

            for (int k = 0; k < 17; k++) {
                worksheet.setColumnWidth(k, 8000);

            }

            excelrow = (XSSFRow)worksheet.createRow(0);
            XSSFCell cellV0 = excelrow.createCell(0);
            cellV0.setCellValue("Version V1");
            cellV0.setCellStyle(cellStyleHdr);
            cellV0.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV1 = excelrow.createCell(1);
            cellV1.setCellValue("Payment Application Import File");
            cellV1.setCellStyle(cellStyleHdrVal);
            cellV1.setCellType(Cell.CELL_TYPE_STRING);

            excelrow = (XSSFRow)worksheet.createRow(2);
            XSSFCell cellV2 = excelrow.createCell(1);
            cellV2.setCellValue("Business Unit:");
            cellV2.setCellStyle(cellStyleHdr);
            cellV2.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV3 = excelrow.createCell(3);
            cellV3.setCellValue(hdrRow != null ? hdrRow.getBuName() : "");
            cellV3.setCellStyle(cellStyleHdrVal);
            cellV3.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV4 = excelrow.createCell(5);
            cellV4.setCellValue("Contract Number - Name:");
            cellV4.setCellStyle(cellStyleHdr);
            cellV4.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV5 = excelrow.createCell(6);
            cellV5.setCellValue(hdrRow != null ?
                                hdrRow.getContractNumber() + " - " +
                                hdrRow.getCognomen() : "");
            cellV5.setCellStyle(cellStyleHdrVal);
            cellV5.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV6 = excelrow.createCell(8);
            cellV6.setCellValue("Document Number:");
            cellV6.setCellStyle(cellStyleHdr);
            cellV6.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV7 = excelrow.createCell(9);
            cellV7.setCellValue(hdrRow != null ?
                                hdrRow.getInvoiceDocumentNum() : "");
            cellV7.setCellStyle(cellStyleHdrVal);
            cellV7.setCellType(Cell.CELL_TYPE_STRING);

            XSSFCell cellV8 = excelrow.createCell(12);
            cellV8.setCellValue("Period:");
            cellV8.setCellStyle(cellStyleHdr);
            cellV8.setCellType(Cell.CELL_TYPE_STRING);

            XSSFCell cellV9 = excelrow.createCell(13);
            cellV9.setCellValue(hdrRow != null ? hdrRow.getPeriodOfMeasure() :
                                "");
            cellV9.setCellStyle(cellStyleHdrVal);
            cellV9.setCellType(Cell.CELL_TYPE_STRING);


            excelrow = (XSSFRow)worksheet.createRow(4);


            XSSFCell cellA0 = excelrow.createCell(0);
            cellA0.setCellValue("BOQ_LINE ID");
            cellA0.setCellStyle(cellStyleHdr);
            cellA0.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA1 = excelrow.createCell(1);
            cellA1.setCellValue("Bill/Page/Item");
            cellA1.setCellStyle(cellStyleHdr);
            cellA1.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA100 = excelrow.createCell(2);
            cellA100.setCellValue("Level");
            cellA100.setCellStyle(cellStyleHdr);
            cellA100.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA103 = excelrow.createCell(3);
            cellA103.setCellValue("Line Type");
            cellA103.setCellStyle(cellStyleHdr);
            cellA103.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA2 = excelrow.createCell(4);
            cellA2.setCellValue("Page/Item");
            cellA2.setCellStyle(cellStyleHdr);
            cellA2.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA3 = excelrow.createCell(5);
            cellA3.setCellValue("Description");
            cellA3.setCellStyle(cellStyleHdr);
            cellA3.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA4 = excelrow.createCell(6);
            cellA4.setCellValue("Bill Quantity");
            cellA4.setCellStyle(cellStyleHdr);
            cellA4.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA5 = excelrow.createCell(7);
            cellA5.setCellValue("Cumulative Work Done Quantity");
            cellA5.setCellStyle(cellStyleHdr);
            cellA5.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA6 = excelrow.createCell(8);
            cellA6.setCellValue("Previous Invoiced");
            cellA6.setCellStyle(cellStyleHdr);
            cellA6.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA60 = excelrow.createCell(9);
            cellA60.setCellValue("Previous Certified");
            cellA60.setCellStyle(cellStyleHdr);
            cellA60.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA7 = excelrow.createCell(10);
            cellA7.setCellValue("Previous Bill Rate");
            cellA7.setCellStyle(cellStyleHdr);
            cellA7.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA8 = excelrow.createCell(11);
            cellA8.setCellValue("Bill Rate");
            cellA8.setCellStyle(cellStyleHdr);
            cellA8.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA9 = excelrow.createCell(12);
            cellA9.setCellValue("Cumulative WD Qty");
            cellA9.setCellStyle(cellStyleHdr);
            cellA9.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA122 = excelrow.createCell(13);
            cellA122.setCellValue("** Cumulative Invoiced Qty");
            cellA122.setCellStyle(cellStyleHdr);
            cellA122.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA120 = excelrow.createCell(14);
            cellA120.setCellValue("** Cumulative Invoiced Qty %");
            cellA120.setCellStyle(cellStyleHdr);
            cellA120.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA90 = excelrow.createCell(15);
            cellA90.setCellValue("** Cumulative Invoiced Amount");
            cellA90.setCellStyle(cellStyleHdr);
            cellA90.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA20 = excelrow.createCell(16);
            cellA20.setCellValue("Remarks");
            cellA20.setCellStyle(cellStyleHdr);
            cellA20.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA101 = excelrow.createCell(17);
            cellA101.setCellValue("INVOICE_LINE_ID");
            cellA101.setCellStyle(cellStyleHdr);
            cellA101.setCellType(Cell.CELL_TYPE_STRING);
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setLocked(true);
            cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            cellStyle.setFillPattern(CellStyle.BORDER_MEDIUM);
            RowSetIterator iter =
                (RowSetIterator)hdrRow.getInvoicedQtyLinesVO();
            int i = 4;
            while (iter.hasNext()) {
                //print data from next row in excel

                InvoicedQtyLinesVORowImpl newrow =
                    (InvoicedQtyLinesVORowImpl)iter.next();

                i++;
                excelrow = (XSSFRow)worksheet.createRow((int)i);

                XSSFCell cellA10 = excelrow.createCell(0);
                cellA10.setCellValue(newrow.getBoqLineId().doubleValue());
                cellA10.setCellStyle(cellStyle);
                XSSFCell cellA104 = excelrow.createCell(1);
                cellA104.setCellValue(newrow.getBillPageItem());
                cellA104.setCellStyle(cellStyle);
                XSSFCell cellA11 = excelrow.createCell(2);
                cellA11.setCellValue(newrow.getBoqLevel());
                cellA11.setCellStyle(cellStyle);
                XSSFCell cellA105 = excelrow.createCell(3);
                cellA105.setCellValue(newrow.getTransLineType());
                cellA105.setCellStyle(cellStyle);
                XSSFCell cellA12 = excelrow.createCell(4);
                cellA12.setCellValue(newrow.getPageItem());
                cellA12.setCellStyle(cellStyle);
                XSSFCell cellA13 = excelrow.createCell(5);
                cellA13.setCellValue(newrow.getDescription());
                cellA13.setCellStyle(cellStyle);
                XSSFCell cellA14 = excelrow.createCell(6);
                cellA14.setCellValue(newrow.getBillQty().doubleValue());
                cellA14.setCellStyle(cellStyle);
                XSSFCell cellA15 = excelrow.createCell(7);
                cellA15.setCellValue(newrow.getWdCummulativeQty().doubleValue());
                cellA15.setCellStyle(cellStyle);
                cellA15.setCellType(Cell.CELL_TYPE_NUMERIC);
                XSSFCell cellA16 = excelrow.createCell(8);
                cellA16.setCellValue(newrow.getPreviousCummulativeQty() !=
                                     null ?
                                     newrow.getPreviousCummulativeQty().doubleValue() :
                                     0);
                cellA16.setCellType(Cell.CELL_TYPE_NUMERIC);
                cellA16.setCellStyle(cellStyle);
                XSSFCell cellA160 = excelrow.createCell(9);
                cellA160.setCellValue(newrow.getPreviousCertifiedCummulativeQty() !=
                                     null ?
                                     newrow.getPreviousCertifiedCummulativeQty().doubleValue() :
                                     0);
                cellA160.setCellType(Cell.CELL_TYPE_NUMERIC);
                cellA160.setCellStyle(cellStyle);
                XSSFCell cellA17 = excelrow.createCell(10);
                cellA17.setCellStyle(cellStyle);
                cellA17.setCellValue(newrow.getPreviousBoqRate() != null ?
                                     newrow.getPreviousBoqRate().doubleValue() :
                                     0);
                cellA17.setCellType(Cell.CELL_TYPE_NUMERIC);
                XSSFCell cellA18 = excelrow.createCell(11);
                cellA18.setCellStyle(cellStyle);
                cellA18.setCellValue(newrow.getSellingRate() != null ?
                                     newrow.getSellingRate().doubleValue() :
                                     0);
                cellA18.setCellType(Cell.CELL_TYPE_NUMERIC);
                CellStyle cellStyleunlock = workbook.createCellStyle();
                cellStyleunlock.setLocked(false);
                XSSFCell cellA19 = excelrow.createCell(12);
                cellA19.setCellValue(newrow.getWdCummulativeQty() != null ?
                                     newrow.getWdCummulativeQty().doubleValue() :
                                     0);
                cellA19.setCellType(Cell.CELL_TYPE_NUMERIC);
                cellA19.setCellStyle(cellStyle);

                XSSFCell cellA119 = excelrow.createCell(13);
                cellA119.setCellValue(newrow.getInvoiceCummulativeQty() !=
                                      null ?
                                      newrow.getInvoiceCummulativeQty().doubleValue() :
                                      0);
                cellA119.setCellType(Cell.CELL_TYPE_NUMERIC);
                cellA119.setCellStyle(cellStyle);
                cellA119.setCellStyle(cellStyleunlock);
                XSSFCell cellA121 = excelrow.createCell(14);
                cellA121.setCellValue(newrow.getInvoiceCummulativeQty() !=
                                      null ?
                                      newrow.getInvoiceCummulativeQty().divide(newrow.getBillQty(),
                                                                               4,
                                                                               BigDecimal.ROUND_HALF_UP).doubleValue() *
                                      100 : 0);
                cellA121.setCellType(Cell.CELL_TYPE_NUMERIC);
                cellA121.setCellStyle(cellStyle);
                cellA121.setCellStyle(cellStyleunlock);
                XSSFCell cellA1191 = excelrow.createCell(15);
                cellA1191.setCellValue(newrow.getTransAmount() != null ?
                                       newrow.getTransAmount().doubleValue() :
                                       0);
                cellA1191.setCellType(Cell.CELL_TYPE_NUMERIC);
                cellA1191.setCellStyle(cellStyle);
                cellA1191.setCellStyle(cellStyleunlock);
                XSSFCell cellA110 = excelrow.createCell(16);
                cellA110.setCellValue(newrow.getRemarks() != null ?
                                      newrow.getRemarks() : "");
                cellA110.setCellStyle(cellStyleunlock);
                XSSFCell cellA102 = excelrow.createCell(17);
                cellA102.setCellValue(newrow.getInvoiceLineId().doubleValue());
                cellA102.setCellType(Cell.CELL_TYPE_NUMERIC);
                cellA102.setCellStyle(cellStyle);

            }
            iter.closeRowSetIterator();

            //Hiding Columns
            worksheet.setColumnHidden(0, true);
            worksheet.setColumnHidden(2, true);
            worksheet.setColumnHidden(4, true);
            worksheet.setColumnHidden(10, true);
            worksheet.setColumnHidden(17, true);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                workbook.write(bos);
                bos.close();
            } catch (IOException e) {
            }
            data = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public String uploadInvoicedXLFile(UploadedFile uploadedFile) {
        CommonUtilsAMImpl commonAM = (CommonUtilsAMImpl)getCommonUtilsAM1();
        Number uploadId = commonAM.getUserUploadId();
        try {
            if (uploadedFile != null) {
                InvoicedQtyDetailsVORowImpl hdrRow =
                    (InvoicedQtyDetailsVORowImpl)getInvoicedQtyDetailsVO1().getCurrentRow();
                hdrRow.setFileName(uploadedFile.getFilename());
                hdrRow.getInvoiceHeaderId();
                InputStream inputStream = uploadedFile.getInputStream();
                XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                inputStream.close();
                XSSFSheet sheet = workbook.getSheetAt(0);
                Iterator<org.apache.poi.ss.usermodel.Row> rowIterator =
                    sheet.iterator();
                int i = 0;
                for (i = 0; i < 3; i++) {
                    org.apache.poi.ss.usermodel.Row HdrRow =
                        rowIterator.next();
                    if (i == 0 &&
                        !("Version V1".equals(HdrRow.getCell(0).getStringCellValue()))) {
                        return "Version mismatch. Please download latest template.";
                    }
                }
                while (rowIterator.hasNext()) {
                    org.apache.poi.ss.usermodel.Row row = rowIterator.next();

                    InvoicedUploadIntVORowImpl uploadRow =
                        (InvoicedUploadIntVORowImpl)getInvoicedUploadIntVO1().createRow();

                    uploadRow.setUploadId(uploadId.bigDecimalValue());

                    for (int j = 0; j <= 17; j++) {
                        Cell cell = null;
                        cell = row.getCell(j);
                        Object cellValue = null;
                        if (cell != null) {
                            cellValue = commonAM.getCellValue((XSSFCell)cell);

                            if (cellValue != null) {
                                try {
                                    switch (j) {
                                    case 0:
                                        uploadRow.setInvoicedLineNumber(new BigDecimal(cellValue.toString()));
                                        break;
                                    case 8:
                                        uploadRow.setInvoicedPrevCummulativeQty(new BigDecimal(cellValue.toString()));
                                        break;
                                    case 11:
                                        uploadRow.setCurrentBoqRate(new BigDecimal(cellValue.toString()));
                                        break;
                                    case 12:
                                        uploadRow.setWdCummQty(new BigDecimal(cellValue.toString()));
                                        break;
                                    case 13:
                                        uploadRow.setInvoicedCummulativeQty(new BigDecimal(cellValue.toString()));
                                        break;
                                    case 14:
                                        uploadRow.setInvoicedCummQtyPerct(new BigDecimal(cellValue.toString()));
                                        break;
                                    case 15:
                                        uploadRow.setInvoicedCummulativeAmt(new BigDecimal(cellValue.toString()));
                                        break;
                                    case 16:
                                        uploadRow.setRemarks(cellValue !=
                                                             null ?
                                                             cellValue.toString() :
                                                             "");
                                        break;
                                    case 17:
                                        uploadRow.setInvoiceLineId(new BigDecimal(cellValue.toString()));
                                        break;

                                    default:
                                        break;
                                    }
                                } catch (Exception e) {
                                    workbook.close();
                                    return "Invalid values at row no: " +
                                        (i + 1) + " column no: " + (j + 1) +
                                        " Error Message:  " + e.getMessage();
                                }
                            }
                        } //END IF
                        else {
                            //Cell value is null
                            if (!(j == 5 || j == 12)) {
                                workbook.close();
                                return "Required values at row no: " +
                                    (i + 1) + " column no: " + (j + 1) + "";
                            }

                        }
                    } //END FOR

                    getInvoicedUploadIntVO1().insertRow(uploadRow);

                    i++;
                }
                workbook.close();
                this.getDBTransaction().commit();
            }

        } catch (Exception e) {
            return e.getMessage();
        }

        this.getDBTransaction().commit();

        return "UPLOADED";
    }

    public String validateInvoicedUploadDetails() {
        String msg = null;
        if (((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("uploadId") !=
            null) {
            CommonUtilsAMImpl commonAM =
                (CommonUtilsAMImpl)getCommonUtilsAM1();
            Number uploadId =
                (Number)((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("uploadId");
            InvoicedQtyDetailsVORowImpl hdrRow =
                (InvoicedQtyDetailsVORowImpl)getInvoicedQtyDetailsVO1().getCurrentRow();
            if (hdrRow != null) {
                Map paramsMap = new HashMap();
                paramsMap.put("UploadId", uploadId);
                paramsMap.put("InvoicedHeaderId", hdrRow.getInvoiceHeaderId());
                paramsMap.put("InvoicedDocNum",
                              hdrRow.getInvoiceDocumentNum());

                BOQUploadsAMImpl uploadAM =
                    (BOQUploadsAMImpl)getBOQUploadsAM1();
                String returnMsg = uploadAM.validateInvoicedUpload(paramsMap);
                if (returnMsg != null) {
                    msg = returnMsg;
                    getInvoicedQtyLinesVO1().executeQuery();
                } else {
                    msg = "NOMSG";
                }
            } else
                msg = "NOHDR";
        } else {
            msg = "Upload id not found.";
        }
        return msg;
    }


    public byte[] downloadCertifiedDetails() {

        CertifiedQtyDetailsVORowImpl hdrRow =
            (CertifiedQtyDetailsVORowImpl)getCertifiedQtyDetailsVO1().getCurrentRow();

        byte[] data = null;

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            // create a new sheet
            XSSFSheet worksheet =
                workbook.createSheet("Import certified quantity");

            XSSFRow excelrow = null;
            XSSFFont font = workbook.createFont();
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            CellStyle cellStyleHdr = workbook.createCellStyle();
            cellStyleHdr.setFont(font);
            cellStyleHdr.setLocked(true);
            CellStyle cellStyleHdrVal = workbook.createCellStyle();
            XSSFFont font2 = workbook.createFont();
            font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            cellStyleHdrVal.setFont(font2);
            cellStyleHdrVal.setLocked(true);
            worksheet.protectSheet("pCertified");
            worksheet.setDisplayGridlines(false);

            worksheet.createFreezePane(0, 1);
            workbook.getSheetAt(workbook.getActiveSheetIndex()).createFreezePane(0,
                                                                                 5);
            cellStyleHdr.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
            cellStyleHdr.setFillPattern(CellStyle.SOLID_FOREGROUND);

            DataFormat fmt = workbook.createDataFormat();
            CellStyle textStyle = workbook.createCellStyle();
            textStyle.setDataFormat(fmt.getFormat("@"));
            // worksheet.setDefaultColumnStyle(0, textStyle);

            XSSFFont font1 = workbook.createFont();
            font1.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
            CellStyle cellStyle1 = workbook.createCellStyle();
            cellStyle1.setFont(font1);

            for (int k = 0; k < 16; k++) {
                worksheet.setColumnWidth(k, 8000);

            }

            excelrow = (XSSFRow)worksheet.createRow(0);
            XSSFCell cellV0 = excelrow.createCell(0);
            cellV0.setCellValue("Version V1");
            cellV0.setCellStyle(cellStyleHdr);
            cellV0.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV1 = excelrow.createCell(1);
            cellV1.setCellValue("Certified Import File");
            cellV1.setCellStyle(cellStyleHdrVal);
            cellV1.setCellType(Cell.CELL_TYPE_STRING);

            excelrow = (XSSFRow)worksheet.createRow(2);
            XSSFCell cellV2 = excelrow.createCell(1);
            cellV2.setCellValue("Business Unit:");
            cellV2.setCellStyle(cellStyleHdr);
            cellV2.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV3 = excelrow.createCell(2);
            cellV3.setCellValue(hdrRow != null ? hdrRow.getBuName() : "");
            cellV3.setCellStyle(cellStyleHdrVal);
            cellV3.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV4 = excelrow.createCell(4);
            cellV4.setCellValue("Contract Number - Name:");
            cellV4.setCellStyle(cellStyleHdr);
            cellV4.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV5 = excelrow.createCell(5);
            cellV5.setCellValue(hdrRow != null ?
                                hdrRow.getContractNumber() + " - " +
                                hdrRow.getCognomen() : "");
            cellV5.setCellStyle(cellStyleHdrVal);
            cellV5.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV6 = excelrow.createCell(7);
            cellV6.setCellValue("Document Number:");
            cellV6.setCellStyle(cellStyleHdr);
            cellV6.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellV7 = excelrow.createCell(9);
            cellV7.setCellValue(hdrRow != null ?
                                hdrRow.getCertifiedDocumentNum() : "");
            cellV7.setCellStyle(cellStyleHdrVal);
            cellV7.setCellType(Cell.CELL_TYPE_STRING);

            XSSFCell cellV8 = excelrow.createCell(11);
            cellV8.setCellValue("Period:");
            cellV8.setCellStyle(cellStyleHdr);
            cellV8.setCellType(Cell.CELL_TYPE_STRING);

            XSSFCell cellV9 = excelrow.createCell(12);
            cellV9.setCellValue(hdrRow != null ? hdrRow.getPeriodOfMeasure() :
                                "");
            cellV9.setCellStyle(cellStyleHdrVal);
            cellV9.setCellType(Cell.CELL_TYPE_STRING);


            excelrow = (XSSFRow)worksheet.createRow(4);


            XSSFCell cellA0 = excelrow.createCell(0);
            cellA0.setCellValue("BOQ_LINE ID");
            cellA0.setCellStyle(cellStyleHdr);
            cellA0.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA1 = excelrow.createCell(1);
            cellA1.setCellValue("Price Code");
            cellA1.setCellStyle(cellStyleHdr);
            cellA1.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA109 = excelrow.createCell(2);
            cellA109.setCellValue("Line Type");
            cellA109.setCellStyle(cellStyleHdr);
            cellA109.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA2 = excelrow.createCell(3);
            cellA2.setCellValue("Page/Item");
            cellA2.setCellStyle(cellStyleHdr);
            cellA2.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA3 = excelrow.createCell(4);
            cellA3.setCellValue("Item");
            cellA3.setCellStyle(cellStyleHdr);
            cellA3.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA4 = excelrow.createCell(5);
            cellA4.setCellValue("Bill Description");
            cellA4.setCellStyle(cellStyleHdr);
            cellA4.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA5 = excelrow.createCell(6);
            cellA5.setCellValue("Bill Quantity");
            cellA5.setCellStyle(cellStyleHdr);
            cellA5.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA6 = excelrow.createCell(7);
            cellA6.setCellValue("Previous Certified");
            cellA6.setCellStyle(cellStyleHdr);
            cellA6.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA7 = excelrow.createCell(8);
            cellA7.setCellValue("Previous Bill Rate");
            cellA7.setCellStyle(cellStyleHdr);
            cellA7.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA8 = excelrow.createCell(9);
            cellA8.setCellValue("Bill Rate");
            cellA8.setCellStyle(cellStyleHdr);
            cellA8.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA9 = excelrow.createCell(10);
            cellA9.setCellValue("Cumulative WD Qty");
            cellA9.setCellStyle(cellStyleHdr);
            cellA9.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA20 = excelrow.createCell(11);
            cellA20.setCellValue("Cumulative Invoiced Qty");
            cellA20.setCellStyle(cellStyleHdr);
            cellA20.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA120 = excelrow.createCell(12);
            cellA120.setCellValue("** Cumulative Certified Qty");
            cellA120.setCellStyle(cellStyleHdr);
            cellA120.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA121 = excelrow.createCell(13);
            cellA121.setCellValue("** Cumulative Certified Qty %");
            cellA121.setCellStyle(cellStyleHdr);
            cellA121.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA21 = excelrow.createCell(14);
            cellA21.setCellValue("** Cumulative Certified Amount");
            cellA21.setCellStyle(cellStyleHdr);
            cellA21.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA100 = excelrow.createCell(15);
            cellA100.setCellValue("Remarks");
            cellA100.setCellStyle(cellStyleHdr);
            cellA100.setCellType(Cell.CELL_TYPE_STRING);
            XSSFCell cellA101 = excelrow.createCell(16);
            cellA101.setCellValue("CERTIFIED_LINE_ID");
            cellA101.setCellStyle(cellStyleHdr);
            cellA101.setCellType(Cell.CELL_TYPE_STRING);

            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setLocked(true);
            cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            cellStyle.setFillPattern(CellStyle.BORDER_MEDIUM);

            RowSetIterator iter =
                (RowSetIterator)hdrRow.getCertifiedQtyLinesVO();
            int i = 4;
            while (iter.hasNext()) {
                //print data from next row in excel

                CertifiedQtyLinesVORowImpl newrow =
                    (CertifiedQtyLinesVORowImpl)iter.next();

                i++;
                excelrow = (XSSFRow)worksheet.createRow((int)i);

                XSSFCell cellA10 = excelrow.createCell(0);
                cellA10.setCellValue(newrow.getBoqLineId().doubleValue());
                cellA10.setCellStyle(cellStyle);
                XSSFCell cellA11 = excelrow.createCell(1);
                cellA11.setCellValue(newrow.getPriceCode());
                cellA11.setCellStyle(cellStyle);
                XSSFCell cellA111 = excelrow.createCell(2);
                cellA111.setCellValue(newrow.getTransLineType());
                cellA111.setCellStyle(cellStyle);
                XSSFCell cellA12 = excelrow.createCell(3);
                cellA12.setCellValue(newrow.getPageItem());
                cellA12.setCellStyle(cellStyle);
                XSSFCell cellA13 = excelrow.createCell(4);
                cellA13.setCellValue(newrow.getItem());
                cellA13.setCellStyle(cellStyle);
                XSSFCell cellA14 = excelrow.createCell(5);
                cellA14.setCellValue(newrow.getDescription());
                cellA14.setCellStyle(cellStyle);
                XSSFCell cellA15 = excelrow.createCell(6);
                cellA15.setCellValue(newrow.getBillQty().doubleValue());
                cellA15.setCellStyle(cellStyle);
                cellA15.setCellType(Cell.CELL_TYPE_NUMERIC);
                XSSFCell cellA16 = excelrow.createCell(7);
                cellA16.setCellValue(newrow.getPreviousCummulativeQty() !=
                                     null ?
                                     newrow.getPreviousCummulativeQty().doubleValue() :
                                     0);
                cellA16.setCellType(Cell.CELL_TYPE_NUMERIC);
                cellA16.setCellStyle(cellStyle);
                XSSFCell cellA17 = excelrow.createCell(8);
                cellA17.setCellStyle(cellStyle);
                cellA17.setCellValue(newrow.getPreviousBoqRate() != null ?
                                     newrow.getPreviousBoqRate().doubleValue() :
                                     0);
                cellA17.setCellType(Cell.CELL_TYPE_NUMERIC);
                XSSFCell cellA18 = excelrow.createCell(9);
                cellA18.setCellStyle(cellStyle);
                cellA18.setCellValue(newrow.getSellingRate() != null ?
                                     newrow.getSellingRate().doubleValue() :
                                     0);
                cellA18.setCellType(Cell.CELL_TYPE_NUMERIC);

                XSSFCell cellA19 = excelrow.createCell(10);
                cellA19.setCellValue(newrow.getWdCummulativeQty() != null ?
                                     newrow.getWdCummulativeQty().doubleValue() :
                                     0);
                cellA19.setCellType(Cell.CELL_TYPE_NUMERIC);
                cellA19.setCellStyle(cellStyle);

                XSSFCell cellA110 = excelrow.createCell(11);
                cellA110.setCellValue(newrow.getInvoiceCummulativeQty() !=
                                      null ?
                                      newrow.getInvoiceCummulativeQty().doubleValue() :
                                      0);
                cellA110.setCellType(Cell.CELL_TYPE_NUMERIC);
                cellA110.setCellStyle(cellStyle);

                CellStyle cellStyleunlock = workbook.createCellStyle();
                cellStyleunlock.setLocked(false);
                XSSFCell cellA1119 = excelrow.createCell(12);
                cellA1119.setCellValue(newrow.getCertifiedCummulativeQty() !=
                                       null ?
                                       newrow.getCertifiedCummulativeQty().doubleValue() :
                                       0);
                cellA1119.setCellType(Cell.CELL_TYPE_NUMERIC);
                cellA1119.setCellStyle(cellStyleunlock);
                XSSFCell cellA1009 = excelrow.createCell(13);
                cellA1009.setCellValue(newrow.getCertifiedCummulativeQty() !=
                                       null ?
                                       newrow.getCertifiedCummulativeQty().divide(newrow.getBillQty(),
                                                                                  4,
                                                                                  BigDecimal.ROUND_HALF_UP).doubleValue() *
                                       100 : 0);
                cellA1009.setCellType(Cell.CELL_TYPE_NUMERIC);
                cellA1009.setCellStyle(cellStyleunlock);
                XSSFCell cellA119 = excelrow.createCell(14);
                cellA119.setCellValue(newrow.getTransAmount() != null ?
                                      newrow.getTransAmount().doubleValue() :
                                      0);
                cellA119.setCellType(Cell.CELL_TYPE_NUMERIC);
                cellA119.setCellStyle(cellStyleunlock);
                XSSFCell cellA1109 = excelrow.createCell(15);
                cellA1109.setCellValue(newrow.getRemarks() != null ?
                                       newrow.getRemarks() : "");
                cellA1109.setCellStyle(cellStyleunlock);
                XSSFCell cellA102 = excelrow.createCell(16);
                cellA102.setCellValue(newrow.getCertifiedLineId().doubleValue());
                cellA19.setCellType(Cell.CELL_TYPE_NUMERIC);
                cellA102.setCellStyle(cellStyle);


            }
            iter.closeRowSetIterator();

            //Hiding Columns
            worksheet.setColumnHidden(0, true);
            worksheet.setColumnHidden(8, true);
            worksheet.setColumnHidden(16, true);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                workbook.write(bos);
                bos.close();
            } catch (IOException e) {
            }
            data = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public String uploadCertifiedXLFile(UploadedFile uploadedFile) {
        String message = null;
        try {
            CertifiedQtyDetailsVORowImpl CertRow =
                (CertifiedQtyDetailsVORowImpl)getCertifiedQtyDetailsVO1().getCurrentRow();
            CertRow.setFileName(uploadedFile.getFilename());
            CommonUtilsAMImpl commonAM =
                (CommonUtilsAMImpl)getCommonUtilsAM1();
            Number uploadId = commonAM.getUserUploadId();
            if (uploadedFile != null) {
                InputStream inputStream = uploadedFile.getInputStream();
                XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                inputStream.close();
                XSSFSheet sheet = workbook.getSheetAt(0);
                Iterator<org.apache.poi.ss.usermodel.Row> rowIterator =
                    sheet.iterator();
                int i = 0;
                for (i = 0; i < 3; i++) {
                    org.apache.poi.ss.usermodel.Row HdrRow =
                        rowIterator.next();
                    if (i == 0 &&
                        !("Version V1".equals(HdrRow.getCell(0).getStringCellValue()))) {
                        return "Verion mismatch. Please download latest template.";
                    }
                }
                while (rowIterator.hasNext()) {
                    org.apache.poi.ss.usermodel.Row row = rowIterator.next();

                    CertifiedUploadIntVORowImpl uploadRow =
                        (CertifiedUploadIntVORowImpl)getCertifiedUploadIntVO1().createRow();

                    uploadRow.setUploadId(uploadId.bigDecimalValue());

                    for (int j = 0; j <= 16; j++) {
                        Cell cell = null;
                        cell = row.getCell(j);
                        Object cellValue = null;
                        if (cell != null) {
                            cellValue = commonAM.getCellValue((XSSFCell)cell);

                            if (cellValue != null) {
                                try {
                                    switch (j) {
                                    case 0:
                                        uploadRow.setCertifiedLineNumber(new BigDecimal(cellValue !=
                                                                                        null ?
                                                                                        cellValue.toString() :
                                                                                        ""));
                                        break;
                                    case 6:
                                        uploadRow.setBillQuantity(new BigDecimal(cellValue !=
                                                                                 null ?
                                                                                 cellValue.toString() :
                                                                                 ""));
                                        break;
                                    case 9:
                                        uploadRow.setCurrentBoqRate(new BigDecimal(cellValue !=
                                                                                    null ?
                                                                                    cellValue.toString() :
                                                                                    ""));
                                        break;
                                    case 11:
                                        uploadRow.setInvoicedQuantity(new BigDecimal(cellValue !=
                                                                                     null ?
                                                                                     cellValue.toString() :
                                                                                     ""));
                                        break;
                                    case 12:
                                        uploadRow.setCertifiedCummulativeQty(new BigDecimal(cellValue !=
                                                                                            null ?
                                                                                            cellValue.toString() :
                                                                                            ""));
                                        break;
                                    case 13:
                                        uploadRow.setCertifiedCummQtyPerct(new BigDecimal(cellValue !=
                                                                                            null ?
                                                                                            cellValue.toString() :
                                                                                            ""));
                                        break;
                                    case 14:
                                        uploadRow.setCertifiedCummulativeAmt(new BigDecimal(cellValue !=
                                                                                       null ?
                                                                                       cellValue.toString() :
                                                                                       ""));
                                        break;
                                    case 15:
                                        uploadRow.setRemarks(cellValue !=
                                                             null ?
                                                             cellValue.toString() :
                                                             "");
                                        break;
                                    case 16:
                                        uploadRow.setCertifiedLineId(new BigDecimal(cellValue.toString()));
                                        break;

                                    default:
                                        break;
                                    }
                                } catch (Exception e) {
                                    workbook.close();
                                    return "Invalid values at row no: " +
                                        (i + 1) + " column no: " + (j + 1) +
                                        " Error Message:  " + e.getMessage();
                                }
                            }
                        } //END IF
                        else {
                            //Cell value is null
                            if (!(j == 4 || j == 10)) {
                                workbook.close();
                                return "Required values at row no: " +
                                    (i + 1) + " column no: " + (j + 1) + "";
                            }

                        }
                    } //END FOR

                    getCertifiedUploadIntVO1().insertRow(uploadRow);

                    i++;
                }
                workbook.close();
            } else {
                message = "Upload Id Found null;";
            }

        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();

        }

        this.getDBTransaction().commit();

        return "Success";
    }

    public String validateCertifiedUploadDetails() {

        String msg = null;
        if (((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("uploadId") !=
            null) {
            CommonUtilsAMImpl commonAM =
                (CommonUtilsAMImpl)getCommonUtilsAM1();
            Number uploadId =
                (Number)((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("uploadId");
            CertifiedQtyDetailsVORowImpl hdrRow =
                (CertifiedQtyDetailsVORowImpl)getCertifiedQtyDetailsVO1().getCurrentRow();
            if (hdrRow != null) {
                Map paramsMap = new HashMap();
                paramsMap.put("BuId", hdrRow.getBuId());
                paramsMap.put("ProjectId", hdrRow.getProjectId());
                paramsMap.put("UploadId", uploadId);
                paramsMap.put("CertifiedHeaderId",
                              hdrRow.getCertifiedHeaderId());
                paramsMap.put("CertifiedDocNum",
                              hdrRow.getCertifiedDocumentNum());

                BOQUploadsAMImpl uploadAM =
                    (BOQUploadsAMImpl)getBOQUploadsAM1();
                msg = uploadAM.validateCertifiedUpload(paramsMap);
                this.getDBTransaction().commit();
                getCertifiedQtyLinesVO1().executeQuery();

            } else
                msg = "No header id found.";
        } else {
            msg = "Upload id found null.";
        }
        return msg;
    }
    
    public byte[] getPAPrintReport(){
        byte[] reportData = null;
        try{
            InvoicedQtyDetailsVORowImpl PARow =
                (InvoicedQtyDetailsVORowImpl)getInvoicedQtyDetailsVO1().getCurrentRow();
            PCPrintReportVOImpl PCPrintVO = getPCPrintReportVO1();
            PCPrintVO.setNamedWhereClauseParam("p_pc_header_id",
                                               PARow.getInvoiceHeaderId());
            PCPrintVO.setNamedWhereClauseParam("p_report_type", "PA");
            PCPrintVO.executeQuery();
            String reportPath =
                "/Custom/PAAS/Master Reports/Projects/Client Payment Application Print Report.xdo";
            String format = "pdf";
            ByteArrayOutputStream opStream = new ByteArrayOutputStream();
            ((XMLNode)PCPrintVO.writeXML(1,
                                         XMLInterface.XML_OPT_ALL_ROWS)).print(opStream);
            ExternalReportCustom ext = new ExternalReportCustom();
            reportData = ext.generatePDFReport(reportPath, format, opStream);
    //            File file = new File("C:\\Users\\sumanth.yamani\\Downloads\\BOQ Uploads/invPrintXml2.xml");
    //            FileOutputStream fop = new FileOutputStream(file);
    //            opStream.writeTo(fop);
    //            opStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return reportData;
    }
    
    public byte[] getPADetailsReport(){
        byte[] reportData = null;
        try{
            InvoicedQtyDetailsVORowImpl PARow =
                (InvoicedQtyDetailsVORowImpl)getInvoicedQtyDetailsVO1().getCurrentRow();
            InvoiceDetailsReportVOImpl PrintVO = getInvoiceDetailsReportVO1();
            PrintVO.setNamedWhereClauseParam("p_header_id",
                                               PARow.getInvoiceHeaderId());
            PrintVO.executeQuery();
            String reportPath =
                "/Custom/PAAS/Master Reports/Projects/Payment Application Details Report.xdo";
            String format = "xlsx";
            ByteArrayOutputStream opStream = new ByteArrayOutputStream();
            ((XMLNode)PrintVO.writeXML(1,
                                         XMLInterface.XML_OPT_ALL_ROWS)).print(opStream);
//            File file = new File("C:\\Users\\sumanth.yamani\\Downloads\\BOQ Uploads/InvoiceDetails.xml");
//            FileOutputStream fop = new FileOutputStream(file);
//            opStream.writeTo(fop);
//            opStream.close();
            ExternalReportCustom ext = new ExternalReportCustom();
            reportData = ext.generatePDFReport(reportPath, format, opStream);
        }catch(Exception e){
            e.printStackTrace();
        }
        return reportData;
    }

    /**
     * Container's getter for CertifiedUploadIntVO1.
     * @return CertifiedUploadIntVO1
     */
    public CertifiedUploadIntVOImpl getCertifiedUploadIntVO1() {
        return (CertifiedUploadIntVOImpl)findViewObject("CertifiedUploadIntVO1");
    }

    /**
     * Container's getter for InvoicedUploadIntVO1.
     * @return InvoicedUploadIntVO1
     */
    public InvoicedUploadIntVOImpl getInvoicedUploadIntVO1() {
        return (InvoicedUploadIntVOImpl)findViewObject("InvoicedUploadIntVO1");
    }


    /**
     * Container's getter for MaxWDBOQHdrIdScrVO1.
     * @return MaxWDBOQHdrIdScrVO1
     */
    public MaxWDBOQHdrIdScrVOImpl getMaxWDBOQHdrIdScrVO1() {
        return (MaxWDBOQHdrIdScrVOImpl)findViewObject("MaxWDBOQHdrIdScrVO1");
    }

    /**
     * Container's getter for CurrentBoqHdrScrVO1.
     * @return CurrentBoqHdrScrVO1
     */
    public CurrentBoqHdrScrVOImpl getCurrentBoqHdrScrVO1() {
        return (CurrentBoqHdrScrVOImpl)findViewObject("CurrentBoqHdrScrVO1");
    }

    /**
     * Container's getter for MaxWDBOQDocumentScrVO1.
     * @return MaxWDBOQDocumentScrVO1
     */
    public MaxWDBOQDocumentScrVOImpl getMaxWDBOQDocumentScrVO1() {
        return (MaxWDBOQDocumentScrVOImpl)findViewObject("MaxWDBOQDocumentScrVO1");
    }

    /**
     * Container's getter for MaxINVBOQDocumentScrVO1.
     * @return MaxINVBOQDocumentScrVO1
     */
    public MaxINVBOQDocumentScrVOImpl getMaxINVBOQDocumentScrVO1() {
        return (MaxINVBOQDocumentScrVOImpl)findViewObject("MaxINVBOQDocumentScrVO1");
    }

    /**
     * Container's getter for MaxINVBOQHdrIdScrVO1.
     * @return MaxINVBOQHdrIdScrVO1
     */
    public MaxINVBOQHdrIdScrVOImpl getMaxINVBOQHdrIdScrVO1() {
        return (MaxINVBOQHdrIdScrVOImpl)findViewObject("MaxINVBOQHdrIdScrVO1");
    }

    /**
     * Container's getter for CurrentWdHdrVO1.
     * @return CurrentWdHdrVO1
     */
    public CurrentWdHdrVOImpl getCurrentWdHdrVO1() {
        return (CurrentWdHdrVOImpl)findViewObject("CurrentWdHdrVO1");
    }

    /**
     * Container's getter for MaxCertBOQHdrIdScrVO1.
     * @return MaxCertBOQHdrIdScrVO1
     */
    public MaxCertBOQHdrIdScrVOImpl getMaxCertBOQHdrIdScrVO1() {
        return (MaxCertBOQHdrIdScrVOImpl)findViewObject("MaxCertBOQHdrIdScrVO1");
    }

    /**
     * Container's getter for MaxCertBOQDocuemntScrVO1.
     * @return MaxCertBOQDocuemntScrVO1
     */
    public MaxCertBOQDocuemntScrVOImpl getMaxCertBOQDocuemntScrVO1() {
        return (MaxCertBOQDocuemntScrVOImpl)findViewObject("MaxCertBOQDocuemntScrVO1");
    }

    /**
     * Container's getter for CurrentInvoicedDocScrVO1.
     * @return CurrentInvoicedDocScrVO1
     */
    public CurrentInvoicedDocScrVOImpl getCurrentInvoicedDocScrVO1() {
        return (CurrentInvoicedDocScrVOImpl)findViewObject("CurrentInvoicedDocScrVO1");
    }

    public String submitInvoiceRequestToAME() {
        CommonUtilsAMImpl commonAM = (CommonUtilsAMImpl)getCommonUtilsAM1();
        InvoicedQtyDetailsVORowImpl hdrRow =
            (InvoicedQtyDetailsVORowImpl)getInvoicedQtyDetailsVO1().getCurrentRow();
        String msg = "";
        if (hdrRow != null) {
            Map paramsMap = new HashMap();
            try {
                paramsMap.put("docId",
                              new oracle.jbo.domain.Number(hdrRow.getInvoiceHeaderId()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            paramsMap.put("docTypeId", new oracle.jbo.domain.Number(10));
            paramsMap.put("ActionCode", "Submit");
            paramsMap.put("currEmpId", commonAM.getCurrentEmployeeId());
            paramsMap.put("remarks", "");
            paramsMap.put("docNum", hdrRow.getInvoiceDocumentNum());
            msg = commonAM.callAMEProcess(paramsMap);
            if (msg != null) {
                if ("Finally Approved".equals(msg)) { //FYI approval only
                    hdrRow.setInvoiceStatus("A");
                } else if ("Submitted".equals(msg)) {
                    hdrRow.setInvoiceStatus("I");
                }
            }
        }
        return msg;
    }

    public String submitCertifiedRequestToAME() {
        CommonUtilsAMImpl commonAM = (CommonUtilsAMImpl)getCommonUtilsAM1();
        CertifiedQtyDetailsVORowImpl hdrRow =
            (CertifiedQtyDetailsVORowImpl)getCertifiedQtyDetailsVO1().getCurrentRow();

        String msg = "";
        if (hdrRow != null) {
            Map paramsMap = new HashMap();
            try {
                paramsMap.put("docId",
                              new oracle.jbo.domain.Number(hdrRow.getCertifiedHeaderId()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            paramsMap.put("docTypeId", new oracle.jbo.domain.Number(10));
            paramsMap.put("ActionCode", "Submit");
            paramsMap.put("currEmpId", commonAM.getCurrentEmployeeId());
            paramsMap.put("remarks", "");
            paramsMap.put("docNum", hdrRow.getCertifiedDocumentNum());
            msg = commonAM.callAMEProcess(paramsMap);
            if (msg != null) {
                if ("Finally Approved".equals(msg)) { //FYI approval only
                    hdrRow.setCertifiedStatus("A");
                } else if ("Submitted".equals(msg)) {
                    hdrRow.setCertifiedStatus("I");
                }
            }
        }
        return msg;
    }

    /**
     * Container's getter for TransWorkdoneHdrVO1.
     * @return TransWorkdoneHdrVO1
     */
    public TransWorkdoneHdrVOImpl getTransWorkdoneHdrVO1() {
        return (TransWorkdoneHdrVOImpl)findViewObject("TransWorkdoneHdrVO1");
    }

    /**
     * Container's getter for TransCertifiedDocHdrVO1.
     * @return TransCertifiedDocHdrVO1
     */
    public TransCertifiedDocHdrVOImpl getTransCertifiedDocHdrVO1() {
        return (TransCertifiedDocHdrVOImpl)findViewObject("TransCertifiedDocHdrVO1");
    }

    /**
     * Container's getter for TransInvoiceHdrVO1.
     * @return TransInvoiceHdrVO1
     */
    public TransInvoiceHdrVOImpl getTransInvoiceHdrVO1() {
        return (TransInvoiceHdrVOImpl)findViewObject("TransInvoiceHdrVO1");
    }

    /**
     * Container's getter for IsWDDocInvoicedScrVO1.
     * @return IsWDDocInvoicedScrVO1
     */
    public IsWDDocInvoicedScrVOImpl getIsWDDocInvoicedScrVO1() {
        return (IsWDDocInvoicedScrVOImpl)findViewObject("IsWDDocInvoicedScrVO1");
    }

    /**
     * Container's getter for IsCertDocScrVO1.
     * @return IsCertDocScrVO1
     */
    public IsCertDocScrVOImpl getIsCertDocScrVO1() {
        return (IsCertDocScrVOImpl)findViewObject("IsCertDocScrVO1");
    }

    /**
     * Container's getter for PCDocTypeCountsScrVO1.
     * @return PCDocTypeCountsScrVO1
     */
    public PCDocTypeCountsScrVOImpl getPCDocTypeCountsScrVO1() {
        return (PCDocTypeCountsScrVOImpl)findViewObject("PCDocTypeCountsScrVO1");
    }

    /**
     * Container's getter for AttachmentsVO1.
     * @return AttachmentsVO1
     */
    public AttachmentsVOImpl getAttachmentsVO1() {
        return (AttachmentsVOImpl)findViewObject("AttachmentsVO1");
    }

    /**
     * Container's getter for WorkDoneQtyDetailsToAttachmentsVL1.
     * @return WorkDoneQtyDetailsToAttachmentsVL1
     */
    public ViewLinkImpl getWorkDoneQtyDetailsToAttachmentsVL1() {
        return (ViewLinkImpl)findViewLink("WorkDoneQtyDetailsToAttachmentsVL1");
    }

    /**
     * Container's getter for AttachmentsVO2.
     * @return AttachmentsVO2
     */
    public AttachmentsVOImpl getAttachmentsVO2() {
        return (AttachmentsVOImpl)findViewObject("AttachmentsVO2");
    }

    /**
     * Container's getter for InvoiceToAttachmentsVL1.
     * @return InvoiceToAttachmentsVL1
     */
    public ViewLinkImpl getInvoiceToAttachmentsVL1() {
        return (ViewLinkImpl)findViewLink("InvoiceToAttachmentsVL1");
    }

    /**
     * Container's getter for ProjectSearchVO1.
     * @return ProjectSearchVO1
     */
    public ViewObjectImpl getProjectSearchVO1() {
        return (ViewObjectImpl)findViewObject("ProjectSearchVO1");
    }

    /**
     * Container's getter for PCLinesSearchWithProjectIdVO1.
     * @return PCLinesSearchWithProjectIdVO1
     */
    public PCLinesSearchWithProjectIdVOImpl getPCLinesSearchWithProjectIdVO1() {
        return (PCLinesSearchWithProjectIdVOImpl)findViewObject("PCLinesSearchWithProjectIdVO1");
    }

    /**
     * Container's getter for AttachmentsVO3.
     * @return AttachmentsVO3
     */
    public AttachmentsVOImpl getAttachmentsVO3() {
        return (AttachmentsVOImpl)findViewObject("AttachmentsVO3");
    }

    /**
     * Container's getter for CertificationToAttachmentsVL1.
     * @return CertificationToAttachmentsVL1
     */
    public ViewLinkImpl getCertificationToAttachmentsVL1() {
        return (ViewLinkImpl)findViewLink("CertificationToAttachmentsVL1");
    }

    /**
     * Container's getter for PCPrintReportVO1.
     * @return PCPrintReportVO1
     */
    public PCPrintReportVOImpl getPCPrintReportVO1() {
        return (PCPrintReportVOImpl)findViewObject("PCPrintReportVO1");
    }

    /**
     * Container's getter for WorkDoneQtyLinesVO2.
     * @return WorkDoneQtyLinesVO2
     */
    public WorkDoneQtyLinesVOImpl getWorkDoneQtyLinesVO2() {
        return (WorkDoneQtyLinesVOImpl)findViewObject("WorkDoneQtyLinesVO2");
    }

    /**
     * Container's getter for WorkDoneDetailsToLinesVL2.
     * @return WorkDoneDetailsToLinesVL2
     */
    public ViewLinkImpl getWorkDoneDetailsToLinesVL2() {
        return (ViewLinkImpl)findViewLink("WorkDoneDetailsToLinesVL2");
    }

    /**
     * Container's getter for InvoicedQtyLinesVO2.
     * @return InvoicedQtyLinesVO2
     */
    public InvoicedQtyLinesVOImpl getInvoicedQtyLinesVO2() {
        return (InvoicedQtyLinesVOImpl)findViewObject("InvoicedQtyLinesVO2");
    }

    /**
     * Container's getter for InvoiceDetailsToLinesVL2.
     * @return InvoiceDetailsToLinesVL2
     */
    public ViewLinkImpl getInvoiceDetailsToLinesVL2() {
        return (ViewLinkImpl)findViewLink("InvoiceDetailsToLinesVL2");
    }

    /**
     * Container's getter for CertifiedQtyLinesVO2.
     * @return CertifiedQtyLinesVO2
     */
    public CertifiedQtyLinesVOImpl getCertifiedQtyLinesVO2() {
        return (CertifiedQtyLinesVOImpl)findViewObject("CertifiedQtyLinesVO2");
    }

    /**
     * Container's getter for CertifiedDetailsToLinesVL2.
     * @return CertifiedDetailsToLinesVL2
     */
    public ViewLinkImpl getCertifiedDetailsToLinesVL2() {
        return (ViewLinkImpl)findViewLink("CertifiedDetailsToLinesVL2");
    }

    /**
     * Container's getter for ContractAdvanceAmtVO1.
     * @return ContractAdvanceAmtVO1
     */
    public ContractAdvanceAmtVOImpl getContractAdvanceAmtVO1() {
        return (ContractAdvanceAmtVOImpl)findViewObject("ContractAdvanceAmtVO1");
}

    /**
     * Container's getter for CalculateInvoiceValuesVO1.
     * @return CalculateInvoiceValuesVO1
     */
    public CalculateInvoiceValuesVOImpl getCalculateInvoiceValuesVO1() {
        return (CalculateInvoiceValuesVOImpl)findViewObject("CalculateInvoiceValuesVO1");
    }

    /**
     * Container's getter for CalculateCertifiedValuesVO1.
     * @return CalculateCertifiedValuesVO1
     */
    public CalculateCertifiedValuesVOImpl getCalculateCertifiedValuesVO1() {
        return (CalculateCertifiedValuesVOImpl)findViewObject("CalculateCertifiedValuesVO1");
    }

    /**
     * Container's getter for IsInvoiceDocCertified1.
     * @return IsInvoiceDocCertified1
     */
    public IsInvoiceDocCertifiedImpl getIsInvoiceDocCertified1() {
        return (IsInvoiceDocCertifiedImpl)findViewObject("IsInvoiceDocCertified1");
    }

    /**
     * Container's getter for GetMaxCertPCHeaderIdVO1.
     * @return GetMaxCertPCHeaderIdVO1
     */
    public GetMaxCertPCHeaderIdVOImpl getGetMaxCertPCHeaderIdVO1() {
        return (GetMaxCertPCHeaderIdVOImpl)findViewObject("GetMaxCertPCHeaderIdVO1");
    }

    /**
     * Container's getter for InvoiceDetailsReportVO1.
     * @return InvoiceDetailsReportVO1
     */
    public InvoiceDetailsReportVOImpl getInvoiceDetailsReportVO1() {
        return (InvoiceDetailsReportVOImpl)findViewObject("InvoiceDetailsReportVO1");
    }

  /**
   * Container's getter for WorkDoneQtyLineDetailsVO1.
   * @return WorkDoneQtyLineDetailsVO1
   */
  public WorkDoneQtyLineDetailsVOImpl getWorkDoneQtyLineDetailsVO1()
  {
    return (WorkDoneQtyLineDetailsVOImpl) findViewObject("WorkDoneQtyLineDetailsVO1");
  }

  /**
   * Container's getter for WorkDoneQtyLineToDetailsVL1.
   * @return WorkDoneQtyLineToDetailsVL1
   */
  public ViewLinkImpl getWorkDoneQtyLineToDetailsVL1()
  {
    return (ViewLinkImpl) findViewLink("WorkDoneQtyLineToDetailsVL1");
  }
  
  public String deleteWdLineWithDetails()
  {
    String message = "";
    try
    {
      message = "Success";
      WorkDoneQtyLinesVORowImpl hdrRow =
          (WorkDoneQtyLinesVORowImpl)getWorkDoneQtyLinesVO1().getCurrentRow();
      getWorkDoneQtyLineDetailsVO1().setApplyViewCriteriaName(null);
      getWorkDoneQtyLineDetailsVO1().executeQuery();
      
      getWorkDoneQtyLineDetailsVO1().setApplyViewCriteriaName("WorkDoneQtyLineDetailsVOCriteria");
      getWorkDoneQtyLineDetailsVO1().setNamedWhereClauseParam("p_wd_line_id", hdrRow.getWdLineId());
      getWorkDoneQtyLineDetailsVO1().executeQuery();
      
      RowSetIterator lineDetailRows = getWorkDoneQtyLineDetailsVO1().createRowSetIterator(null);
      while(lineDetailRows.hasNext())
      {
        WorkDoneQtyLineDetailsVORowImpl lineDetailRow = (WorkDoneQtyLineDetailsVORowImpl) lineDetailRows.next();
        lineDetailRow.remove();
      }
      hdrRow.remove();
    }
    catch (Exception e)
    {
      message = e.getMessage();
    }
    return message;
  }
}
