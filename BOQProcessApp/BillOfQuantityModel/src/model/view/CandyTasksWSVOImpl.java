package model.view;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Mar 17 12:42:59 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CandyTasksWSVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public CandyTasksWSVOImpl() {
    }

    /**
     * Returns the bind variable value for p_hdr_id.
     * @return bind variable value for p_hdr_id
     */
    public Long getp_hdr_id() {
        return (Long)getNamedWhereClauseParam("p_hdr_id");
    }

    /**
     * Sets <code>value</code> for bind variable p_hdr_id.
     * @param value value to bind as p_hdr_id
     */
    public void setp_hdr_id(Long value) {
        setNamedWhereClauseParam("p_hdr_id", value);
    }
}
