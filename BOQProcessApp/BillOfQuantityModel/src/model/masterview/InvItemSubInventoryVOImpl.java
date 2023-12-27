package model.masterview;

import java.math.BigDecimal;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Oct 17 17:29:33 IST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class InvItemSubInventoryVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public InvItemSubInventoryVOImpl() {
    }


    /**
     * Returns the variable value for p_item_id.
     * @return variable value for p_item_id
     */
    public BigDecimal getp_item_id() {
        return (BigDecimal)ensureVariableManager().getVariableValue("p_item_id");
    }

    /**
     * Sets <code>value</code> for variable p_item_id.
     * @param value value to bind as p_item_id
     */
    public void setp_item_id(BigDecimal value) {
        ensureVariableManager().setVariableValue("p_item_id", value);
    }

    /**
     * Returns the bind variable value for p_org_id.
     * @return bind variable value for p_org_id
     */
    public BigDecimal getp_org_id() {
        return (BigDecimal)getNamedWhereClauseParam("p_org_id");
    }

    /**
     * Sets <code>value</code> for bind variable p_org_id.
     * @param value value to bind as p_org_id
     */
    public void setp_org_id(BigDecimal value) {
        setNamedWhereClauseParam("p_org_id", value);
    }

    /**
     * Returns the variable value for p_sub_inv_code.
     * @return variable value for p_sub_inv_code
     */
    public String getp_sub_inv_code() {
        return (String)ensureVariableManager().getVariableValue("p_sub_inv_code");
    }

    /**
     * Sets <code>value</code> for variable p_sub_inv_code.
     * @param value value to bind as p_sub_inv_code
     */
    public void setp_sub_inv_code(String value) {
        ensureVariableManager().setVariableValue("p_sub_inv_code", value);
    }
}
