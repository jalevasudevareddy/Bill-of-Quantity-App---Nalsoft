package model.searchview;

import java.math.BigDecimal;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Oct 31 10:16:05 IST 2018
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class IsWDDocInvoicedScrVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public IsWDDocInvoicedScrVOImpl() {
    }


    /**
     * Returns the bind variable value for p_wd_hdr_id.
     * @return bind variable value for p_wd_hdr_id
     */
    public BigDecimal getp_wd_hdr_id() {
        return (BigDecimal)getNamedWhereClauseParam("p_wd_hdr_id");
    }

    /**
     * Sets <code>value</code> for bind variable p_wd_hdr_id.
     * @param value value to bind as p_wd_hdr_id
     */
    public void setp_wd_hdr_id(BigDecimal value) {
        setNamedWhereClauseParam("p_wd_hdr_id", value);
    }

    /**
     * Returns the bind variable value for p_bu_idqwer.
     * @return bind variable value for p_bu_idqwer
     */
    public BigDecimal getp_bu_idqwer() {
        return (BigDecimal)getNamedWhereClauseParam("p_bu_idqwer");
    }

    /**
     * Sets <code>value</code> for bind variable p_bu_idqwer.
     * @param value value to bind as p_bu_idqwer
     */
    public void setp_bu_idqwer(BigDecimal value) {
        setNamedWhereClauseParam("p_bu_idqwer", value);
    }
}
