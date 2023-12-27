package model.searchview;

import java.math.BigDecimal;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Mar 31 11:47:45 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ValidationLideIdForResourceSearchVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public ValidationLideIdForResourceSearchVOImpl() {
    }

    /**
     * Returns the bind variable value for p_resc_id.
     * @return bind variable value for p_resc_id
     */
    public BigDecimal getp_resc_id() {
        return (BigDecimal)getNamedWhereClauseParam("p_resc_id");
    }

    /**
     * Sets <code>value</code> for bind variable p_resc_id.
     * @param value value to bind as p_resc_id
     */
    public void setp_resc_id(BigDecimal value) {
        setNamedWhereClauseParam("p_resc_id", value);
    }

    /**
     * Returns the bind variable value for p_hdr_id.
     * @return bind variable value for p_hdr_id
     */
    public BigDecimal getp_hdr_id() {
        return (BigDecimal)getNamedWhereClauseParam("p_hdr_id");
    }

    /**
     * Sets <code>value</code> for bind variable p_hdr_id.
     * @param value value to bind as p_hdr_id
     */
    public void setp_hdr_id(BigDecimal value) {
        setNamedWhereClauseParam("p_hdr_id", value);
    }
}
