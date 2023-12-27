package model.approvalview;

import java.math.BigDecimal;

import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Dec 01 11:16:30 IST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class EmployeeWorkListVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        ReqDocumentId {
            public Object get(EmployeeWorkListVORowImpl obj) {
                return obj.getReqDocumentId();
            }

            public void put(EmployeeWorkListVORowImpl obj, Object value) {
                obj.setReqDocumentId((BigDecimal)value);
            }
        },
        DocumentTypeId {
            public Object get(EmployeeWorkListVORowImpl obj) {
                return obj.getDocumentTypeId();
            }

            public void put(EmployeeWorkListVORowImpl obj, Object value) {
                obj.setDocumentTypeId((BigDecimal)value);
            }
        },
        ApproverLevel {
            public Object get(EmployeeWorkListVORowImpl obj) {
                return obj.getApproverLevel();
            }

            public void put(EmployeeWorkListVORowImpl obj, Object value) {
                obj.setApproverLevel((BigDecimal)value);
            }
        },
        FromEmployeeId {
            public Object get(EmployeeWorkListVORowImpl obj) {
                return obj.getFromEmployeeId();
            }

            public void put(EmployeeWorkListVORowImpl obj, Object value) {
                obj.setFromEmployeeId((BigDecimal)value);
            }
        },
        ToEmployeeId {
            public Object get(EmployeeWorkListVORowImpl obj) {
                return obj.getToEmployeeId();
            }

            public void put(EmployeeWorkListVORowImpl obj, Object value) {
                obj.setToEmployeeId((BigDecimal)value);
            }
        },
        Status {
            public Object get(EmployeeWorkListVORowImpl obj) {
                return obj.getStatus();
            }

            public void put(EmployeeWorkListVORowImpl obj, Object value) {
                obj.setStatus((String)value);
            }
        };
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public abstract Object get(EmployeeWorkListVORowImpl object);

        public abstract void put(EmployeeWorkListVORowImpl object,
                                 Object value);

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static final int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() +
                AttributesEnum.staticValues().length;
        }

        public static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }


    public static final int REQDOCUMENTID =
        AttributesEnum.ReqDocumentId.index();
    public static final int DOCUMENTTYPEID =
        AttributesEnum.DocumentTypeId.index();
    public static final int APPROVERLEVEL =
        AttributesEnum.ApproverLevel.index();
    public static final int FROMEMPLOYEEID =
        AttributesEnum.FromEmployeeId.index();
    public static final int TOEMPLOYEEID = AttributesEnum.ToEmployeeId.index();
    public static final int STATUS = AttributesEnum.Status.index();

    /**
     * This is the default constructor (do not remove).
     */
    public EmployeeWorkListVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute ReqDocumentId.
     * @return the ReqDocumentId
     */
    public BigDecimal getReqDocumentId() {
        return (BigDecimal)getAttributeInternal(REQDOCUMENTID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ReqDocumentId.
     * @param value value to set the  ReqDocumentId
     */
    public void setReqDocumentId(BigDecimal value) {
        setAttributeInternal(REQDOCUMENTID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute DocumentTypeId.
     * @return the DocumentTypeId
     */
    public BigDecimal getDocumentTypeId() {
        return (BigDecimal)getAttributeInternal(DOCUMENTTYPEID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute DocumentTypeId.
     * @param value value to set the  DocumentTypeId
     */
    public void setDocumentTypeId(BigDecimal value) {
        setAttributeInternal(DOCUMENTTYPEID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ApproverLevel.
     * @return the ApproverLevel
     */
    public BigDecimal getApproverLevel() {
        return (BigDecimal)getAttributeInternal(APPROVERLEVEL);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ApproverLevel.
     * @param value value to set the  ApproverLevel
     */
    public void setApproverLevel(BigDecimal value) {
        setAttributeInternal(APPROVERLEVEL, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FromEmployeeId.
     * @return the FromEmployeeId
     */
    public BigDecimal getFromEmployeeId() {
        return (BigDecimal)getAttributeInternal(FROMEMPLOYEEID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FromEmployeeId.
     * @param value value to set the  FromEmployeeId
     */
    public void setFromEmployeeId(BigDecimal value) {
        setAttributeInternal(FROMEMPLOYEEID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ToEmployeeId.
     * @return the ToEmployeeId
     */
    public BigDecimal getToEmployeeId() {
        return (BigDecimal)getAttributeInternal(TOEMPLOYEEID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ToEmployeeId.
     * @param value value to set the  ToEmployeeId
     */
    public void setToEmployeeId(BigDecimal value) {
        setAttributeInternal(TOEMPLOYEEID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Status.
     * @return the Status
     */
    public String getStatus() {
        return (String)getAttributeInternal(STATUS);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Status.
     * @param value value to set the  Status
     */
    public void setStatus(String value) {
        setAttributeInternal(STATUS, value);
    }

    /**
     * getAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param attrDef the attribute

     * @return the attribute value
     * @throws Exception
     */
    protected Object getAttrInvokeAccessor(int index,
                                           AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) &&
            (index < AttributesEnum.count())) {
            return AttributesEnum.staticValues()[index -
                AttributesEnum.firstIndex()].get(this);
        }
        return super.getAttrInvokeAccessor(index, attrDef);
    }

    /**
     * setAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param value the value to assign to the attribute
     * @param attrDef the attribute

     * @throws Exception
     */
    protected void setAttrInvokeAccessor(int index, Object value,
                                         AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) &&
            (index < AttributesEnum.count())) {
            AttributesEnum.staticValues()[index -
                AttributesEnum.firstIndex()].put(this, value);
            return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
    }
}
