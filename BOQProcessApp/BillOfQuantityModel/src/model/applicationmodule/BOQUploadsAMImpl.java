package model.applicationmodule;

import java.io.InputStream;

import java.math.BigDecimal;

import java.sql.CallableStatement;

import java.sql.Types;

import java.util.Map;

import model.applicationmodule.common.BOQUploadsAM;

import model.masterview.UploadErrorsVOImpl;

import model.view.BOQUploadIntVOImpl;

import oracle.adf.share.ADFContext;

import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewObjectImpl;
import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.model.UploadedFile;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Dec 30 11:36:56 IST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class BOQUploadsAMImpl extends ApplicationModuleImpl implements BOQUploadsAM {


    private final String VALIDATE_BOQS =
        "begin xxboq_bill_of_quantity_pkg.validate_boqs" +
        "(:p_upload_id,:p_user_name,:p_bu_id,:p_project_id,:p_message);end;";

    private final String VALIDATE_WD =
        "begin xxboq_bill_of_quantity_pkg.validate_upld_wd_quantities" +
        "(:p_upload_id," + ":p_wd_hdr_id," + ":p_user_name," +
        ":p_message);end;";
    private final String VALIDATE_INVOICED =
        "begin xxboq_bill_of_quantity_pkg.validate_update_inv_lines" +
        "(:p_upload_id,:p_invoiced_header_id,:p_user_name,:p_message);end;";
    private final String VALIDATE_CERTIFIED =
        "begin xxboq_bill_of_quantity_pkg.validate_upld_cert_quantities" +
        "(:p_upload_id,:p_cert_hdr_id,:p_user_name,:p_message);end;";


    /**
     * This is the default constructor (do not remove).
     */
    public BOQUploadsAMImpl() {
    }

    /**
     * Container's getter for BOQUploadIntVO1.
     * @return BOQUploadIntVO1
     */
    public ViewObjectImpl getBOQUploadIntVO1() {
        return (ViewObjectImpl)findViewObject("BOQUploadIntVO1");
    }

    /**
     * Container's getter for UploadErrorsVO1.
     * @return UploadErrorsVO1
     */
    public UploadErrorsVOImpl getUploadErrorsVO1() {
        return (UploadErrorsVOImpl)findViewObject("UploadErrorsVO1");
    }

    /**
     * Container's getter for CommonUtilsAM1.
     * @return CommonUtilsAM1
     */
    public ApplicationModuleImpl getCommonUtilsAM1() {
        return (ApplicationModuleImpl)findApplicationModule("CommonUtilsAM1");
    }


    public String validateBOQUpload(Map paramsMap) {

        CallableStatement st = null;
        String retCode = null;
        Map sessionScope = ADFContext.getCurrent().getSessionScope();
        String userName =
            (String)((Map)(sessionScope.get("userMap"))).get("UserName");
        try {
            st = getDBTransaction().createCallableStatement(VALIDATE_BOQS, 0);
            st.setLong("p_upload_id",
                       ((Number)paramsMap.get("UploadId")).longValue());
            st.setString("p_user_name", userName);
            st.setLong("p_bu_id",
                       ((BigDecimal)paramsMap.get("BuId")).longValue());
            st.setLong("p_project_id",
                       ((BigDecimal)paramsMap.get("ProjectId")).longValue());

            st.registerOutParameter("p_message", Types.VARCHAR);

            st.execute();
            retCode = st.getString("p_message");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return retCode;
    }

    public String validateWDUpload(Map paramsMap) {
        String message = null;
        try {
            CallableStatement st = null;

            Map sessionScope = ADFContext.getCurrent().getSessionScope();
            String userName =
                (String)((Map)(sessionScope.get("userMap"))).get("UserName");

            st = getDBTransaction().createCallableStatement(VALIDATE_WD, 0);
            st.setLong("p_upload_id",
                       ((Number)paramsMap.get("UploadId")).longValue());

            st.setLong("p_wd_hdr_id",
                       ((BigDecimal)paramsMap.get("WDHeaderId")).longValue());
            st.setString("p_user_name", userName);

            st.registerOutParameter("p_message", Types.VARCHAR);

            st.execute();
            message = st.getString("p_message");
            this.getDBTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public String validateInvoicedUpload(Map paramsMap) {

        CallableStatement st = null;
        String retCode = null;
        Map sessionScope = ADFContext.getCurrent().getSessionScope();
        String userName =
            (String)((Map)(sessionScope.get("userMap"))).get("UserName");
        try {
            st =
 getDBTransaction().createCallableStatement(VALIDATE_INVOICED, 0);
            st.setLong("p_upload_id",
                       ((Number)paramsMap.get("UploadId")).longValue());
            st.setLong("p_invoiced_header_id",
                       ((BigDecimal)paramsMap.get("InvoicedHeaderId")).longValue());
            st.setString("p_user_name", userName);
            st.registerOutParameter("p_message", Types.VARCHAR);

            st.execute();
            retCode = st.getString("p_message");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return retCode;
    }

    public String validateCertifiedUpload(Map paramsMap) {

        CallableStatement st = null;
        String retCode = null;
        Map sessionScope = ADFContext.getCurrent().getSessionScope();
        String userName =
            (String)((Map)(sessionScope.get("userMap"))).get("UserName");
        try {
            st =
 getDBTransaction().createCallableStatement(VALIDATE_CERTIFIED, 0);
            st.setLong("p_upload_id",
                       ((Number)paramsMap.get("UploadId")).longValue());

            st.setLong("p_cert_hdr_id",
                       ((BigDecimal)paramsMap.get("CertifiedHeaderId")).longValue());
            st.setString("p_user_name", userName);

            st.registerOutParameter("p_message", Types.VARCHAR);

            st.execute();
            retCode = st.getString("p_message");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return retCode;
    }

    public void setUploadErrorDetails() {
        UploadErrorsVOImpl errorVO = getUploadErrorsVO1();
        errorVO.applyViewCriteria(null);
        Map pageflowScope = ADFContext.getCurrent().getPageFlowScope();
        errorVO.setNamedWhereClauseParam("p_table_code",
                                         pageflowScope.get("TableCode"));
        errorVO.setNamedWhereClauseParam("p_upload_id",
                                         pageflowScope.get("uploadId"));
        errorVO.setApplyViewCriteriaName("UploadErrorsVOCriteria");
        errorVO.executeQuery();
    }
}
