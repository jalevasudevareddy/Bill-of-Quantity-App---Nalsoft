package model.searchview;

import java.math.BigDecimal;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Jan 16 09:16:36 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ResourceItemRateForProjectSearchVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public ResourceItemRateForProjectSearchVOImpl() {
    }

    /**
     * Returns the bind variable value for p_prj_job_hdr_id.
     * @return bind variable value for p_prj_job_hdr_id
     */
    public BigDecimal getp_prj_job_hdr_id() {
        return (BigDecimal)getNamedWhereClauseParam("p_prj_job_hdr_id");
    }

    /**
     * Sets <code>value</code> for bind variable p_prj_job_hdr_id.
     * @param value value to bind as p_prj_job_hdr_id
     */
    public void setp_prj_job_hdr_id(BigDecimal value) {
        setNamedWhereClauseParam("p_prj_job_hdr_id", value);
    }

    /**
     * Returns the bind variable value for p_item_id.
     * @return bind variable value for p_item_id
     */
    public BigDecimal getp_item_id() {
        return (BigDecimal)getNamedWhereClauseParam("p_item_id");
    }

    /**
     * Sets <code>value</code> for bind variable p_item_id.
     * @param value value to bind as p_item_id
     */
    public void setp_item_id(BigDecimal value) {
        setNamedWhereClauseParam("p_item_id", value);
    }
}
