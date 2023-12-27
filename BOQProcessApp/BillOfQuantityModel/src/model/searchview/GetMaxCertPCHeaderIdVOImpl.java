package model.searchview;

import java.math.BigDecimal;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Mar 03 21:18:47 IST 2020
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class GetMaxCertPCHeaderIdVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public GetMaxCertPCHeaderIdVOImpl() {
    }

    /**
     * Returns the bind variable value for p_contract_id.
     * @return bind variable value for p_contract_id
     */
    public BigDecimal getp_contract_id() {
        return (BigDecimal)getNamedWhereClauseParam("p_contract_id");
    }

    /**
     * Sets <code>value</code> for bind variable p_contract_id.
     * @param value value to bind as p_contract_id
     */
    public void setp_contract_id(BigDecimal value) {
        setNamedWhereClauseParam("p_contract_id", value);
    }
}
