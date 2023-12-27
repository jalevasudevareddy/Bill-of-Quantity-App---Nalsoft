package model.view;

import java.math.BigDecimal;

import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Feb 08 11:19:37 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CertifiedUploadIntVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        CertifiedCummulativeQty {
            public Object get(CertifiedUploadIntVORowImpl obj) {
                return obj.getCertifiedCummulativeQty();
            }

            public void put(CertifiedUploadIntVORowImpl obj, Object value) {
                obj.setCertifiedCummulativeQty((BigDecimal)value);
            }
        },
        CertifiedEstimatedQty {
            public Object get(CertifiedUploadIntVORowImpl obj) {
                return obj.getCertifiedEstimatedQty();
            }

            public void put(CertifiedUploadIntVORowImpl obj, Object value) {
                obj.setCertifiedEstimatedQty((BigDecimal)value);
            }
        },
        CertifiedLineNumber {
            public Object get(CertifiedUploadIntVORowImpl obj) {
                return obj.getCertifiedLineNumber();
            }

            public void put(CertifiedUploadIntVORowImpl obj, Object value) {
                obj.setCertifiedLineNumber((BigDecimal)value);
            }
        },
        CertifiedPrevCummulativeQty {
            public Object get(CertifiedUploadIntVORowImpl obj) {
                return obj.getCertifiedPrevCummulativeQty();
            }

            public void put(CertifiedUploadIntVORowImpl obj, Object value) {
                obj.setCertifiedPrevCummulativeQty((BigDecimal)value);
            }
        },
        RowID {
            public Object get(CertifiedUploadIntVORowImpl obj) {
                return obj.getRowID();
            }

            public void put(CertifiedUploadIntVORowImpl obj, Object value) {
                obj.setRowID((String)value);
            }
        },
        UploadId {
            public Object get(CertifiedUploadIntVORowImpl obj) {
                return obj.getUploadId();
            }

            public void put(CertifiedUploadIntVORowImpl obj, Object value) {
                obj.setUploadId((BigDecimal)value);
            }
        },
        ActualCertifiedQty {
            public Object get(CertifiedUploadIntVORowImpl obj) {
                return obj.getActualCertifiedQty();
            }

            public void put(CertifiedUploadIntVORowImpl obj, Object value) {
                obj.setActualCertifiedQty((BigDecimal)value);
            }
        },
        Remarks {
            public Object get(CertifiedUploadIntVORowImpl obj) {
                return obj.getRemarks();
            }

            public void put(CertifiedUploadIntVORowImpl obj, Object value) {
                obj.setRemarks((String)value);
            }
        },
        CertifiedLineId {
            public Object get(CertifiedUploadIntVORowImpl obj) {
                return obj.getCertifiedLineId();
            }

            public void put(CertifiedUploadIntVORowImpl obj, Object value) {
                obj.setCertifiedLineId((BigDecimal)value);
            }
        },
        BillQuantity {
            public Object get(CertifiedUploadIntVORowImpl obj) {
                return obj.getBillQuantity();
            }

            public void put(CertifiedUploadIntVORowImpl obj, Object value) {
                obj.setBillQuantity((BigDecimal)value);
            }
        },
        InvoicedQuantity {
            public Object get(CertifiedUploadIntVORowImpl obj) {
                return obj.getInvoicedQuantity();
            }

            public void put(CertifiedUploadIntVORowImpl obj, Object value) {
                obj.setInvoicedQuantity((BigDecimal)value);
            }
        },
        CertifiedCummulativeAmt {
            public Object get(CertifiedUploadIntVORowImpl obj) {
                return obj.getCertifiedCummulativeAmt();
            }

            public void put(CertifiedUploadIntVORowImpl obj, Object value) {
                obj.setCertifiedCummulativeAmt((BigDecimal)value);
            }
        },
        CurrentBoqRate {
            public Object get(CertifiedUploadIntVORowImpl obj) {
                return obj.getCurrentBoqRate();
            }

            public void put(CertifiedUploadIntVORowImpl obj, Object value) {
                obj.setCurrentBoqRate((BigDecimal)value);
            }
        },
        CertifiedCummQtyPerct {
            public Object get(CertifiedUploadIntVORowImpl obj) {
                return obj.getCertifiedCummQtyPerct();
            }

            public void put(CertifiedUploadIntVORowImpl obj, Object value) {
                obj.setCertifiedCummQtyPerct((BigDecimal)value);
            }
        };
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public abstract Object get(CertifiedUploadIntVORowImpl object);

        public abstract void put(CertifiedUploadIntVORowImpl object,
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


    public static final int CERTIFIEDCUMMULATIVEQTY =
        AttributesEnum.CertifiedCummulativeQty.index();
    public static final int CERTIFIEDESTIMATEDQTY =
        AttributesEnum.CertifiedEstimatedQty.index();
    public static final int CERTIFIEDLINENUMBER =
        AttributesEnum.CertifiedLineNumber.index();
    public static final int CERTIFIEDPREVCUMMULATIVEQTY =
        AttributesEnum.CertifiedPrevCummulativeQty.index();
    public static final int ROWID = AttributesEnum.RowID.index();
    public static final int UPLOADID = AttributesEnum.UploadId.index();
    public static final int ACTUALCERTIFIEDQTY =
        AttributesEnum.ActualCertifiedQty.index();
    public static final int REMARKS = AttributesEnum.Remarks.index();
    public static final int CERTIFIEDLINEID =
        AttributesEnum.CertifiedLineId.index();
    public static final int BILLQUANTITY = AttributesEnum.BillQuantity.index();
    public static final int INVOICEDQUANTITY =
        AttributesEnum.InvoicedQuantity.index();
    public static final int CERTIFIEDCUMMULATIVEAMT =
        AttributesEnum.CertifiedCummulativeAmt.index();
    public static final int CURRENTBOQRATE =
        AttributesEnum.CurrentBoqRate.index();
    public static final int CERTIFIEDCUMMQTYPERCT =
        AttributesEnum.CertifiedCummQtyPerct.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CertifiedUploadIntVORowImpl() {
    }


    /**
     * Gets XxboqCertifiedIntEO entity object.
     * @return the XxboqCertifiedIntEO
     */
    public EntityImpl getXxboqCertifiedIntEO() {
        return (EntityImpl)getEntity(0);
    }

    /**
     * Gets the attribute value for CERTIFIED_CUMMULATIVE_QTY using the alias name CertifiedCummulativeQty.
     * @return the CERTIFIED_CUMMULATIVE_QTY
     */
    public BigDecimal getCertifiedCummulativeQty() {
        return (BigDecimal)getAttributeInternal(CERTIFIEDCUMMULATIVEQTY);
    }

    /**
     * Sets <code>value</code> as attribute value for CERTIFIED_CUMMULATIVE_QTY using the alias name CertifiedCummulativeQty.
     * @param value value to set the CERTIFIED_CUMMULATIVE_QTY
     */
    public void setCertifiedCummulativeQty(BigDecimal value) {
        setAttributeInternal(CERTIFIEDCUMMULATIVEQTY, value);
    }

    /**
     * Gets the attribute value for CERTIFIED_ESTIMATED_QTY using the alias name CertifiedEstimatedQty.
     * @return the CERTIFIED_ESTIMATED_QTY
     */
    public BigDecimal getCertifiedEstimatedQty() {
        return (BigDecimal)getAttributeInternal(CERTIFIEDESTIMATEDQTY);
    }

    /**
     * Sets <code>value</code> as attribute value for CERTIFIED_ESTIMATED_QTY using the alias name CertifiedEstimatedQty.
     * @param value value to set the CERTIFIED_ESTIMATED_QTY
     */
    public void setCertifiedEstimatedQty(BigDecimal value) {
        setAttributeInternal(CERTIFIEDESTIMATEDQTY, value);
    }

    /**
     * Gets the attribute value for CERTIFIED_LINE_NUMBER using the alias name CertifiedLineNumber.
     * @return the CERTIFIED_LINE_NUMBER
     */
    public BigDecimal getCertifiedLineNumber() {
        return (BigDecimal)getAttributeInternal(CERTIFIEDLINENUMBER);
    }

    /**
     * Sets <code>value</code> as attribute value for CERTIFIED_LINE_NUMBER using the alias name CertifiedLineNumber.
     * @param value value to set the CERTIFIED_LINE_NUMBER
     */
    public void setCertifiedLineNumber(BigDecimal value) {
        setAttributeInternal(CERTIFIEDLINENUMBER, value);
    }

    /**
     * Gets the attribute value for CERTIFIED_PREV_CUMMULATIVE_QTY using the alias name CertifiedPrevCummulativeQty.
     * @return the CERTIFIED_PREV_CUMMULATIVE_QTY
     */
    public BigDecimal getCertifiedPrevCummulativeQty() {
        return (BigDecimal)getAttributeInternal(CERTIFIEDPREVCUMMULATIVEQTY);
    }

    /**
     * Sets <code>value</code> as attribute value for CERTIFIED_PREV_CUMMULATIVE_QTY using the alias name CertifiedPrevCummulativeQty.
     * @param value value to set the CERTIFIED_PREV_CUMMULATIVE_QTY
     */
    public void setCertifiedPrevCummulativeQty(BigDecimal value) {
        setAttributeInternal(CERTIFIEDPREVCUMMULATIVEQTY, value);
    }

    /**
     * Gets the attribute value for ROWID using the alias name RowID.
     * @return the ROWID
     */
    public String getRowID() {
        return (String)getAttributeInternal(ROWID);
    }

    /**
     * Sets <code>value</code> as attribute value for ROWID using the alias name RowID.
     * @param value value to set the ROWID
     */
    public void setRowID(String value) {
        setAttributeInternal(ROWID, value);
    }

    /**
     * Gets the attribute value for UPLOAD_ID using the alias name UploadId.
     * @return the UPLOAD_ID
     */
    public BigDecimal getUploadId() {
        return (BigDecimal)getAttributeInternal(UPLOADID);
    }

    /**
     * Sets <code>value</code> as attribute value for UPLOAD_ID using the alias name UploadId.
     * @param value value to set the UPLOAD_ID
     */
    public void setUploadId(BigDecimal value) {
        setAttributeInternal(UPLOADID, value);
    }

    /**
     * Gets the attribute value for ACTUAL_CERTIFIED_QTY using the alias name ActualCertifiedQty.
     * @return the ACTUAL_CERTIFIED_QTY
     */
    public BigDecimal getActualCertifiedQty() {
        return (BigDecimal)getAttributeInternal(ACTUALCERTIFIEDQTY);
    }

    /**
     * Sets <code>value</code> as attribute value for ACTUAL_CERTIFIED_QTY using the alias name ActualCertifiedQty.
     * @param value value to set the ACTUAL_CERTIFIED_QTY
     */
    public void setActualCertifiedQty(BigDecimal value) {
        setAttributeInternal(ACTUALCERTIFIEDQTY, value);
    }

    /**
     * Gets the attribute value for REMARKS using the alias name Remarks.
     * @return the REMARKS
     */
    public String getRemarks() {
        return (String)getAttributeInternal(REMARKS);
    }

    /**
     * Sets <code>value</code> as attribute value for REMARKS using the alias name Remarks.
     * @param value value to set the REMARKS
     */
    public void setRemarks(String value) {
        setAttributeInternal(REMARKS, value);
    }

    /**
     * Gets the attribute value for CERTIFIED_LINE_ID using the alias name CertifiedLineId.
     * @return the CERTIFIED_LINE_ID
     */
    public BigDecimal getCertifiedLineId() {
        return (BigDecimal)getAttributeInternal(CERTIFIEDLINEID);
    }

    /**
     * Sets <code>value</code> as attribute value for CERTIFIED_LINE_ID using the alias name CertifiedLineId.
     * @param value value to set the CERTIFIED_LINE_ID
     */
    public void setCertifiedLineId(BigDecimal value) {
        setAttributeInternal(CERTIFIEDLINEID, value);
    }

    /**
     * Gets the attribute value for BILL_QUANTITY using the alias name BillQuantity.
     * @return the BILL_QUANTITY
     */
    public BigDecimal getBillQuantity() {
        return (BigDecimal)getAttributeInternal(BILLQUANTITY);
    }

    /**
     * Sets <code>value</code> as attribute value for BILL_QUANTITY using the alias name BillQuantity.
     * @param value value to set the BILL_QUANTITY
     */
    public void setBillQuantity(BigDecimal value) {
        setAttributeInternal(BILLQUANTITY, value);
    }

    /**
     * Gets the attribute value for INVOICED_QUANTITY using the alias name InvoicedQuantity.
     * @return the INVOICED_QUANTITY
     */
    public BigDecimal getInvoicedQuantity() {
        return (BigDecimal)getAttributeInternal(INVOICEDQUANTITY);
    }

    /**
     * Sets <code>value</code> as attribute value for INVOICED_QUANTITY using the alias name InvoicedQuantity.
     * @param value value to set the INVOICED_QUANTITY
     */
    public void setInvoicedQuantity(BigDecimal value) {
        setAttributeInternal(INVOICEDQUANTITY, value);
    }

    /**
     * Gets the attribute value for CERTIFIED_CUMMULATIVE_AMT using the alias name CertifiedCummulativeAmt.
     * @return the CERTIFIED_CUMMULATIVE_AMT
     */
    public BigDecimal getCertifiedCummulativeAmt() {
        return (BigDecimal)getAttributeInternal(CERTIFIEDCUMMULATIVEAMT);
    }

    /**
     * Sets <code>value</code> as attribute value for CERTIFIED_CUMMULATIVE_AMT using the alias name CertifiedCummulativeAmt.
     * @param value value to set the CERTIFIED_CUMMULATIVE_AMT
     */
    public void setCertifiedCummulativeAmt(BigDecimal value) {
        setAttributeInternal(CERTIFIEDCUMMULATIVEAMT, value);
    }

    /**
     * Gets the attribute value for CURRENT_BOQ_RATE using the alias name CurrentBoqRate.
     * @return the CURRENT_BOQ_RATE
     */
    public BigDecimal getCurrentBoqRate() {
        return (BigDecimal)getAttributeInternal(CURRENTBOQRATE);
    }

    /**
     * Sets <code>value</code> as attribute value for CURRENT_BOQ_RATE using the alias name CurrentBoqRate.
     * @param value value to set the CURRENT_BOQ_RATE
     */
    public void setCurrentBoqRate(BigDecimal value) {
        setAttributeInternal(CURRENTBOQRATE, value);
    }

    /**
     * Gets the attribute value for CERTIFIED_CUMM_QTY_PERCT using the alias name CertifiedCummQtyPerct.
     * @return the CERTIFIED_CUMM_QTY_PERCT
     */
    public BigDecimal getCertifiedCummQtyPerct() {
        return (BigDecimal)getAttributeInternal(CERTIFIEDCUMMQTYPERCT);
    }

    /**
     * Sets <code>value</code> as attribute value for CERTIFIED_CUMM_QTY_PERCT using the alias name CertifiedCummQtyPerct.
     * @param value value to set the CERTIFIED_CUMM_QTY_PERCT
     */
    public void setCertifiedCummQtyPerct(BigDecimal value) {
        setAttributeInternal(CERTIFIEDCUMMQTYPERCT, value);
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
