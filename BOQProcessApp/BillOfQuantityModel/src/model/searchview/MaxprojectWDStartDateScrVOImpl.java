package model.searchview;

import java.math.BigDecimal;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Oct 13 10:29:04 IST 2018
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class MaxprojectWDStartDateScrVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public MaxprojectWDStartDateScrVOImpl() {
    }

    /**
     * Returns the bind variable value for p_bu_id.
     * @return bind variable value for p_bu_id
     */
    public BigDecimal getp_bu_id() {
        return (BigDecimal)getNamedWhereClauseParam("p_bu_id");
    }

    /**
     * Sets <code>value</code> for bind variable p_bu_id.
     * @param value value to bind as p_bu_id
     */
    public void setp_bu_id(BigDecimal value) {
        setNamedWhereClauseParam("p_bu_id", value);
    }

    /**
     * Returns the bind variable value for p_prj_id.
     * @return bind variable value for p_prj_id
     */
    public BigDecimal getp_prj_id() {
        return (BigDecimal)getNamedWhereClauseParam("p_prj_id");
    }

    /**
     * Sets <code>value</code> for bind variable p_prj_id.
     * @param value value to bind as p_prj_id
     */
    public void setp_prj_id(BigDecimal value) {
        setNamedWhereClauseParam("p_prj_id", value);
    }
}
