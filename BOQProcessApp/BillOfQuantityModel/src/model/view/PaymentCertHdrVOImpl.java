package model.view;

import java.math.BigDecimal;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Feb 08 14:41:04 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PaymentCertHdrVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public PaymentCertHdrVOImpl() {
    }

    /**
     * Returns the variable value for p_bu_id.
     * @return variable value for p_bu_id
     */
    public BigDecimal getp_bu_id() {
        return (BigDecimal)ensureVariableManager().getVariableValue("p_bu_id");
    }

    /**
     * Sets <code>value</code> for variable p_bu_id.
     * @param value value to bind as p_bu_id
     */
    public void setp_bu_id(BigDecimal value) {
        ensureVariableManager().setVariableValue("p_bu_id", value);
    }

    /**
     * Returns the variable value for p_project_id.
     * @return variable value for p_project_id
     */
    public BigDecimal getp_project_id() {
        return (BigDecimal)ensureVariableManager().getVariableValue("p_project_id");
    }

    /**
     * Sets <code>value</code> for variable p_project_id.
     * @param value value to bind as p_project_id
     */
    public void setp_project_id(BigDecimal value) {
        ensureVariableManager().setVariableValue("p_project_id", value);
    }

    /**
     * Returns the variable value for p_hdr_id.
     * @return variable value for p_hdr_id
     */
    public BigDecimal getp_hdr_id() {
        return (BigDecimal) ensureVariableManager().getVariableValue("p_hdr_id");
    }

    /**
     * Sets <code>value</code> for variable p_hdr_id.
     * @param value value to bind as p_hdr_id
     */
    public void setp_hdr_id(BigDecimal value) {
        ensureVariableManager().setVariableValue("p_hdr_id", value);
    }
}
