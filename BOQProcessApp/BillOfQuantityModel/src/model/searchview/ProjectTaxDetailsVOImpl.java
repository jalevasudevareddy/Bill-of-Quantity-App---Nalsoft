package model.searchview;

import java.math.BigDecimal;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Aug 28 09:57:45 IST 2019
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ProjectTaxDetailsVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public ProjectTaxDetailsVOImpl() {
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
     * Returns the variable value for p_particulars.
     * @return variable value for p_particulars
     */
    public String getp_particulars() {
        return (String)ensureVariableManager().getVariableValue("p_particulars");
    }

    /**
     * Sets <code>value</code> for variable p_particulars.
     * @param value value to bind as p_particulars
     */
    public void setp_particulars(String value) {
        ensureVariableManager().setVariableValue("p_particulars", value);
    }

    /**
     * Returns the variable value for p_contract_id.
     * @return variable value for p_contract_id
     */
    public String getp_contract_id() {
        return (String)ensureVariableManager().getVariableValue("p_contract_id");
    }

    /**
     * Sets <code>value</code> for variable p_contract_id.
     * @param value value to bind as p_contract_id
     */
    public void setp_contract_id(String value) {
        ensureVariableManager().setVariableValue("p_contract_id", value);
    }
}
