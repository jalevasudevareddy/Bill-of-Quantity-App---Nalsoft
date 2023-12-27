package model.searchview;

import java.math.BigDecimal;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jan 29 16:29:53 IST 2020
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ContractAdvanceAmtVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public ContractAdvanceAmtVOImpl() {
    }


    /**
     * Returns the variable value for p_contract_id.
     * @return variable value for p_contract_id
     */
    public BigDecimal getp_contract_id() {
        return (BigDecimal)ensureVariableManager().getVariableValue("p_contract_id");
    }

    /**
     * Sets <code>value</code> for variable p_contract_id.
     * @param value value to bind as p_contract_id
     */
    public void setp_contract_id(BigDecimal value) {
        ensureVariableManager().setVariableValue("p_contract_id", value);
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
}
