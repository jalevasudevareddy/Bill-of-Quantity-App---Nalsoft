package model.masterview;

import java.math.BigDecimal;

import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Oct 10 11:49:33 IST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class UserOrganizationVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public UserOrganizationVOImpl() {
    }

    /**
     * Returns the variable value for p_bu_access_id.
     * @return variable value for p_bu_access_id
     */
    public BigDecimal getp_bu_access_id() {
        return (BigDecimal)ensureVariableManager().getVariableValue("p_bu_access_id");
    }

    /**
     * Sets <code>value</code> for bind variable p_bu_access_id.
     * @param value value to bind as p_bu_access_id
     */
    public void setp_bu_access_id(BigDecimal value) {
        setNamedWhereClauseParam("p_bu_access_id", value);
    }
}
