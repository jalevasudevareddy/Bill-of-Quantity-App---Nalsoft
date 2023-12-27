package model.view;

import java.math.BigDecimal;

import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Feb 08 11:18:46 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class InvoicedUploadIntVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        InvoicedCummulativeQty {
            public Object get(InvoicedUploadIntVORowImpl obj) {
                return obj.getInvoicedCummulativeQty();
            }

            public void put(InvoicedUploadIntVORowImpl obj, Object value) {
                obj.setInvoicedCummulativeQty((BigDecimal)value);
            }
        },
        InvoicedEstimatedQty {
            public Object get(InvoicedUploadIntVORowImpl obj) {
                return obj.getInvoicedEstimatedQty();
            }

            public void put(InvoicedUploadIntVORowImpl obj, Object value) {
                obj.setInvoicedEstimatedQty((BigDecimal)value);
            }
        },
        InvoicedLineNumber {
            public Object get(InvoicedUploadIntVORowImpl obj) {
                return obj.getInvoicedLineNumber();
            }

            public void put(InvoicedUploadIntVORowImpl obj, Object value) {
                obj.setInvoicedLineNumber((BigDecimal)value);
            }
        },
        InvoicedPrevCummulativeQty {
            public Object get(InvoicedUploadIntVORowImpl obj) {
                return obj.getInvoicedPrevCummulativeQty();
            }

            public void put(InvoicedUploadIntVORowImpl obj, Object value) {
                obj.setInvoicedPrevCummulativeQty((BigDecimal)value);
            }
        },
        RowID {
            public Object get(InvoicedUploadIntVORowImpl obj) {
                return obj.getRowID();
            }

            public void put(InvoicedUploadIntVORowImpl obj, Object value) {
                obj.setRowID((String)value);
            }
        },
        UploadId {
            public Object get(InvoicedUploadIntVORowImpl obj) {
                return obj.getUploadId();
            }

            public void put(InvoicedUploadIntVORowImpl obj, Object value) {
                obj.setUploadId((BigDecimal)value);
            }
        },
        ActualInvoiceQty {
            public Object get(InvoicedUploadIntVORowImpl obj) {
                return obj.getActualInvoiceQty();
            }

            public void put(InvoicedUploadIntVORowImpl obj, Object value) {
                obj.setActualInvoiceQty((BigDecimal)value);
            }
        },
        Remarks {
            public Object get(InvoicedUploadIntVORowImpl obj) {
                return obj.getRemarks();
            }

            public void put(InvoicedUploadIntVORowImpl obj, Object value) {
                obj.setRemarks((String)value);
            }
        },
        InvoiceLineId {
            public Object get(InvoicedUploadIntVORowImpl obj) {
                return obj.getInvoiceLineId();
            }

            public void put(InvoicedUploadIntVORowImpl obj, Object value) {
                obj.setInvoiceLineId((BigDecimal)value);
            }
        },
        WdCummQty {
            public Object get(InvoicedUploadIntVORowImpl obj) {
                return obj.getWdCummQty();
            }

            public void put(InvoicedUploadIntVORowImpl obj, Object value) {
                obj.setWdCummQty((BigDecimal)value);
            }
        },
        InvoicedCummulativeAmt {
            public Object get(InvoicedUploadIntVORowImpl obj) {
                return obj.getInvoicedCummulativeAmt();
            }

            public void put(InvoicedUploadIntVORowImpl obj, Object value) {
                obj.setInvoicedCummulativeAmt((BigDecimal)value);
            }
        },
        CurrentBoqRate {
            public Object get(InvoicedUploadIntVORowImpl obj) {
                return obj.getCurrentBoqRate();
            }

            public void put(InvoicedUploadIntVORowImpl obj, Object value) {
                obj.setCurrentBoqRate((BigDecimal)value);
            }
        },
        InvoicedCummQtyPerct {
            public Object get(InvoicedUploadIntVORowImpl obj) {
                return obj.getInvoicedCummQtyPerct();
            }

            public void put(InvoicedUploadIntVORowImpl obj, Object value) {
                obj.setInvoicedCummQtyPerct((BigDecimal)value);
            }
        };
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public abstract Object get(InvoicedUploadIntVORowImpl object);

        public abstract void put(InvoicedUploadIntVORowImpl object,
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


    public static final int INVOICEDCUMMULATIVEQTY =
        AttributesEnum.InvoicedCummulativeQty.index();
    public static final int INVOICEDESTIMATEDQTY =
        AttributesEnum.InvoicedEstimatedQty.index();
    public static final int INVOICEDLINENUMBER =
        AttributesEnum.InvoicedLineNumber.index();
    public static final int INVOICEDPREVCUMMULATIVEQTY =
        AttributesEnum.InvoicedPrevCummulativeQty.index();
    public static final int ROWID = AttributesEnum.RowID.index();
    public static final int UPLOADID = AttributesEnum.UploadId.index();
    public static final int ACTUALINVOICEQTY =
        AttributesEnum.ActualInvoiceQty.index();
    public static final int REMARKS = AttributesEnum.Remarks.index();
    public static final int INVOICELINEID =
        AttributesEnum.InvoiceLineId.index();
    public static final int WDCUMMQTY = AttributesEnum.WdCummQty.index();
    public static final int INVOICEDCUMMULATIVEAMT =
        AttributesEnum.InvoicedCummulativeAmt.index();
    public static final int CURRENTBOQRATE =
        AttributesEnum.CurrentBoqRate.index();
    public static final int INVOICEDCUMMQTYPERCT =
        AttributesEnum.InvoicedCummQtyPerct.index();

    /**
     * This is the default constructor (do not remove).
     */
    public InvoicedUploadIntVORowImpl() {
    }

    /**
     * Gets XxboqInvoicedIntEO entity object.
     * @return the XxboqInvoicedIntEO
     */
    public EntityImpl getXxboqInvoicedIntEO() {
        return (EntityImpl)getEntity(0);
    }

    /**
     * Gets the attribute value for INVOICED_CUMMULATIVE_QTY using the alias name InvoicedCummulativeQty.
     * @return the INVOICED_CUMMULATIVE_QTY
     */
    public BigDecimal getInvoicedCummulativeQty() {
        return (BigDecimal)getAttributeInternal(INVOICEDCUMMULATIVEQTY);
    }

    /**
     * Sets <code>value</code> as attribute value for INVOICED_CUMMULATIVE_QTY using the alias name InvoicedCummulativeQty.
     * @param value value to set the INVOICED_CUMMULATIVE_QTY
     */
    public void setInvoicedCummulativeQty(BigDecimal value) {
        setAttributeInternal(INVOICEDCUMMULATIVEQTY, value);
    }

    /**
     * Gets the attribute value for INVOICED_ESTIMATED_QTY using the alias name InvoicedEstimatedQty.
     * @return the INVOICED_ESTIMATED_QTY
     */
    public BigDecimal getInvoicedEstimatedQty() {
        return (BigDecimal)getAttributeInternal(INVOICEDESTIMATEDQTY);
    }

    /**
     * Sets <code>value</code> as attribute value for INVOICED_ESTIMATED_QTY using the alias name InvoicedEstimatedQty.
     * @param value value to set the INVOICED_ESTIMATED_QTY
     */
    public void setInvoicedEstimatedQty(BigDecimal value) {
        setAttributeInternal(INVOICEDESTIMATEDQTY, value);
    }

    /**
     * Gets the attribute value for INVOICED_LINE_NUMBER using the alias name InvoicedLineNumber.
     * @return the INVOICED_LINE_NUMBER
     */
    public BigDecimal getInvoicedLineNumber() {
        return (BigDecimal)getAttributeInternal(INVOICEDLINENUMBER);
    }

    /**
     * Sets <code>value</code> as attribute value for INVOICED_LINE_NUMBER using the alias name InvoicedLineNumber.
     * @param value value to set the INVOICED_LINE_NUMBER
     */
    public void setInvoicedLineNumber(BigDecimal value) {
        setAttributeInternal(INVOICEDLINENUMBER, value);
    }

    /**
     * Gets the attribute value for INVOICED_PREV_CUMMULATIVE_QTY using the alias name InvoicedPrevCummulativeQty.
     * @return the INVOICED_PREV_CUMMULATIVE_QTY
     */
    public BigDecimal getInvoicedPrevCummulativeQty() {
        return (BigDecimal)getAttributeInternal(INVOICEDPREVCUMMULATIVEQTY);
    }

    /**
     * Sets <code>value</code> as attribute value for INVOICED_PREV_CUMMULATIVE_QTY using the alias name InvoicedPrevCummulativeQty.
     * @param value value to set the INVOICED_PREV_CUMMULATIVE_QTY
     */
    public void setInvoicedPrevCummulativeQty(BigDecimal value) {
        setAttributeInternal(INVOICEDPREVCUMMULATIVEQTY, value);
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
     * Gets the attribute value for ACTUAL_INVOICE_QTY using the alias name ActualInvoiceQty.
     * @return the ACTUAL_INVOICE_QTY
     */
    public BigDecimal getActualInvoiceQty() {
        return (BigDecimal)getAttributeInternal(ACTUALINVOICEQTY);
    }

    /**
     * Sets <code>value</code> as attribute value for ACTUAL_INVOICE_QTY using the alias name ActualInvoiceQty.
     * @param value value to set the ACTUAL_INVOICE_QTY
     */
    public void setActualInvoiceQty(BigDecimal value) {
        setAttributeInternal(ACTUALINVOICEQTY, value);
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
     * Gets the attribute value for INVOICE_LINE_ID using the alias name InvoiceLineId.
     * @return the INVOICE_LINE_ID
     */
    public BigDecimal getInvoiceLineId() {
        return (BigDecimal)getAttributeInternal(INVOICELINEID);
    }

    /**
     * Sets <code>value</code> as attribute value for INVOICE_LINE_ID using the alias name InvoiceLineId.
     * @param value value to set the INVOICE_LINE_ID
     */
    public void setInvoiceLineId(BigDecimal value) {
        setAttributeInternal(INVOICELINEID, value);
    }

    /**
     * Gets the attribute value for WD_CUMM_QTY using the alias name WdCummQty.
     * @return the WD_CUMM_QTY
     */
    public BigDecimal getWdCummQty() {
        return (BigDecimal)getAttributeInternal(WDCUMMQTY);
    }

    /**
     * Sets <code>value</code> as attribute value for WD_CUMM_QTY using the alias name WdCummQty.
     * @param value value to set the WD_CUMM_QTY
     */
    public void setWdCummQty(BigDecimal value) {
        setAttributeInternal(WDCUMMQTY, value);
    }

    /**
     * Gets the attribute value for INVOICED_CUMMULATIVE_AMT using the alias name InvoicedCummulativeAmt.
     * @return the INVOICED_CUMMULATIVE_AMT
     */
    public BigDecimal getInvoicedCummulativeAmt() {
        return (BigDecimal)getAttributeInternal(INVOICEDCUMMULATIVEAMT);
    }

    /**
     * Sets <code>value</code> as attribute value for INVOICED_CUMMULATIVE_AMT using the alias name InvoicedCummulativeAmt.
     * @param value value to set the INVOICED_CUMMULATIVE_AMT
     */
    public void setInvoicedCummulativeAmt(BigDecimal value) {
        setAttributeInternal(INVOICEDCUMMULATIVEAMT, value);
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
     * Gets the attribute value for INVOICED_CUMM_QTY_PERCT using the alias name InvoicedCummQtyPerct.
     * @return the INVOICED_CUMM_QTY_PERCT
     */
    public BigDecimal getInvoicedCummQtyPerct() {
        return (BigDecimal)getAttributeInternal(INVOICEDCUMMQTYPERCT);
    }

    /**
     * Sets <code>value</code> as attribute value for INVOICED_CUMM_QTY_PERCT using the alias name InvoicedCummQtyPerct.
     * @param value value to set the INVOICED_CUMM_QTY_PERCT
     */
    public void setInvoicedCummQtyPerct(BigDecimal value) {
        setAttributeInternal(INVOICEDCUMMQTYPERCT, value);
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
