package model.view;

import java.math.BigDecimal;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Feb 21 16:49:27 IST 2019
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ProjectBudgetCCScrVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public ProjectBudgetCCScrVOImpl() {
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
