package model.searchview;

import java.math.BigDecimal;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jan 25 16:13:02 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CostingDetailsBOQLinePopulateVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public CostingDetailsBOQLinePopulateVOImpl() {
    }

    /**
     * Returns the bind variable value for p_cost_det_Hdr_id.
     * @return bind variable value for p_cost_det_Hdr_id
     */
    public BigDecimal getp_cost_det_Hdr_id() {
        return (BigDecimal)getNamedWhereClauseParam("p_cost_det_Hdr_id");
    }

    /**
     * Sets <code>value</code> for bind variable p_cost_det_Hdr_id.
     * @param value value to bind as p_cost_det_Hdr_id
     */
    public void setp_cost_det_Hdr_id(BigDecimal value) {
        setNamedWhereClauseParam("p_cost_det_Hdr_id", value);
    }

    /**
     * Returns the bind variable value for p_boq_hdr_id.
     * @return bind variable value for p_boq_hdr_id
     */
    public BigDecimal getp_boq_hdr_id() {
        return (BigDecimal)getNamedWhereClauseParam("p_boq_hdr_id");
    }

    /**
     * Sets <code>value</code> for bind variable p_boq_hdr_id.
     * @param value value to bind as p_boq_hdr_id
     */
    public void setp_boq_hdr_id(BigDecimal value) {
        setNamedWhereClauseParam("p_boq_hdr_id", value);
    }
}
