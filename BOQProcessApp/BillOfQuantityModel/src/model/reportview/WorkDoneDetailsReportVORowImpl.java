package model.reportview;

import java.math.BigDecimal;

import java.sql.Date;

import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Jun 02 15:42:44 IST 2020
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class WorkDoneDetailsReportVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        ContractNumber {
            public Object get(WorkDoneDetailsReportVORowImpl obj) {
                return obj.getContractNumber();
            }

            public void put(WorkDoneDetailsReportVORowImpl obj, Object value) {
                obj.setContractNumber((String)value);
            }
        }
        ,
        Cognomen {
            public Object get(WorkDoneDetailsReportVORowImpl obj) {
                return obj.getCognomen();
            }

            public void put(WorkDoneDetailsReportVORowImpl obj, Object value) {
                obj.setCognomen((String)value);
            }
        }
        ,
        CurrencyCode {
            public Object get(WorkDoneDetailsReportVORowImpl obj) {
                return obj.getCurrencyCode();
            }

            public void put(WorkDoneDetailsReportVORowImpl obj, Object value) {
                obj.setCurrencyCode((String)value);
            }
        }
        ,
        WdDocumentNum {
            public Object get(WorkDoneDetailsReportVORowImpl obj) {
                return obj.getWdDocumentNum();
            }

            public void put(WorkDoneDetailsReportVORowImpl obj, Object value) {
                obj.setWdDocumentNum((String)value);
            }
        }
        ,
        PeriodOfMeasure {
            public Object get(WorkDoneDetailsReportVORowImpl obj) {
                return obj.getPeriodOfMeasure();
            }

            public void put(WorkDoneDetailsReportVORowImpl obj, Object value) {
                obj.setPeriodOfMeasure((String)value);
            }
        }
        ,
        ValuationDate {
            public Object get(WorkDoneDetailsReportVORowImpl obj) {
                return obj.getValuationDate();
            }

            public void put(WorkDoneDetailsReportVORowImpl obj, Object value) {
                obj.setValuationDate((Date)value);
            }
        }
        ,
        BillPageItem {
            public Object get(WorkDoneDetailsReportVORowImpl obj) {
                return obj.getBillPageItem();
            }

            public void put(WorkDoneDetailsReportVORowImpl obj, Object value) {
                obj.setBillPageItem((String)value);
            }
        }
        ,
        PageItem {
            public Object get(WorkDoneDetailsReportVORowImpl obj) {
                return obj.getPageItem();
            }

            public void put(WorkDoneDetailsReportVORowImpl obj, Object value) {
                obj.setPageItem((String)value);
            }
        }
        ,
        Description {
            public Object get(WorkDoneDetailsReportVORowImpl obj) {
                return obj.getDescription();
            }

            public void put(WorkDoneDetailsReportVORowImpl obj, Object value) {
                obj.setDescription((String)value);
            }
        }
        ,
        Uom {
            public Object get(WorkDoneDetailsReportVORowImpl obj) {
                return obj.getUom();
            }

            public void put(WorkDoneDetailsReportVORowImpl obj, Object value) {
                obj.setUom((String)value);
            }
        }
        ,
        BillQty {
            public Object get(WorkDoneDetailsReportVORowImpl obj) {
                return obj.getBillQty();
            }

            public void put(WorkDoneDetailsReportVORowImpl obj, Object value) {
                obj.setBillQty((BigDecimal)value);
            }
        }
        ,
        SellingRate {
            public Object get(WorkDoneDetailsReportVORowImpl obj) {
                return obj.getSellingRate();
            }

            public void put(WorkDoneDetailsReportVORowImpl obj, Object value) {
                obj.setSellingRate((BigDecimal)value);
            }
        }
        ,
        SellingAmount {
            public Object get(WorkDoneDetailsReportVORowImpl obj) {
                return obj.getSellingAmount();
            }

            public void put(WorkDoneDetailsReportVORowImpl obj, Object value) {
                obj.setSellingAmount((BigDecimal)value);
            }
        }
        ,
        PreviousCummulativeQty {
            public Object get(WorkDoneDetailsReportVORowImpl obj) {
                return obj.getPreviousCummulativeQty();
            }

            public void put(WorkDoneDetailsReportVORowImpl obj, Object value) {
                obj.setPreviousCummulativeQty((BigDecimal)value);
            }
        }
        ,
        PreviousPercent {
            public Object get(WorkDoneDetailsReportVORowImpl obj) {
                return obj.getPreviousPercent();
            }

            public void put(WorkDoneDetailsReportVORowImpl obj, Object value) {
                obj.setPreviousPercent((BigDecimal)value);
            }
        }
        ,
        PreviousAmount {
            public Object get(WorkDoneDetailsReportVORowImpl obj) {
                return obj.getPreviousAmount();
            }

            public void put(WorkDoneDetailsReportVORowImpl obj, Object value) {
                obj.setPreviousAmount((BigDecimal)value);
            }
        }
        ,
        CurrentQty {
            public Object get(WorkDoneDetailsReportVORowImpl obj) {
                return obj.getCurrentQty();
            }

            public void put(WorkDoneDetailsReportVORowImpl obj, Object value) {
                obj.setCurrentQty((BigDecimal)value);
            }
        }
        ,
        CurrentPercent {
            public Object get(WorkDoneDetailsReportVORowImpl obj) {
                return obj.getCurrentPercent();
            }

            public void put(WorkDoneDetailsReportVORowImpl obj, Object value) {
                obj.setCurrentPercent((BigDecimal)value);
            }
        }
        ,
        CurrentAmount {
            public Object get(WorkDoneDetailsReportVORowImpl obj) {
                return obj.getCurrentAmount();
            }

            public void put(WorkDoneDetailsReportVORowImpl obj, Object value) {
                obj.setCurrentAmount((BigDecimal)value);
            }
        }
        ,
        CumulativeQty {
            public Object get(WorkDoneDetailsReportVORowImpl obj) {
                return obj.getCumulativeQty();
            }

            public void put(WorkDoneDetailsReportVORowImpl obj, Object value) {
                obj.setCumulativeQty((BigDecimal)value);
            }
        }
        ,
        CumulativePercent {
            public Object get(WorkDoneDetailsReportVORowImpl obj) {
                return obj.getCumulativePercent();
            }

            public void put(WorkDoneDetailsReportVORowImpl obj, Object value) {
                obj.setCumulativePercent((BigDecimal)value);
            }
        }
        ,
        CumulativeAmount {
            public Object get(WorkDoneDetailsReportVORowImpl obj) {
                return obj.getCumulativeAmount();
            }

            public void put(WorkDoneDetailsReportVORowImpl obj, Object value) {
                obj.setCumulativeAmount((BigDecimal)value);
            }
        }
        ,
        HeaderId {
            public Object get(WorkDoneDetailsReportVORowImpl obj) {
                return obj.getHeaderId();
            }

            public void put(WorkDoneDetailsReportVORowImpl obj, Object value) {
                obj.setHeaderId((BigDecimal)value);
            }
        }
        ,
        LineId {
            public Object get(WorkDoneDetailsReportVORowImpl obj) {
                return obj.getLineId();
            }

            public void put(WorkDoneDetailsReportVORowImpl obj, Object value) {
                obj.setLineId((BigDecimal)value);
            }
        }
        ,
        BillNumber {
            public Object get(WorkDoneDetailsReportVORowImpl obj) {
                return obj.getBillNumber();
            }

            public void put(WorkDoneDetailsReportVORowImpl obj, Object value) {
                obj.setBillNumber((String)value);
            }
        }
        ,
        RecordType {
            public Object get(WorkDoneDetailsReportVORowImpl obj) {
                return obj.getRecordType();
            }

            public void put(WorkDoneDetailsReportVORowImpl obj, Object value) {
                obj.setRecordType((String)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public abstract Object get(WorkDoneDetailsReportVORowImpl object);

        public abstract void put(WorkDoneDetailsReportVORowImpl object,
                                 Object value);

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static final int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        public static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }


    public static final int CONTRACTNUMBER = AttributesEnum.ContractNumber.index();
    public static final int COGNOMEN = AttributesEnum.Cognomen.index();
    public static final int CURRENCYCODE = AttributesEnum.CurrencyCode.index();
    public static final int WDDOCUMENTNUM = AttributesEnum.WdDocumentNum.index();
    public static final int PERIODOFMEASURE = AttributesEnum.PeriodOfMeasure.index();
    public static final int VALUATIONDATE = AttributesEnum.ValuationDate.index();
    public static final int BILLPAGEITEM = AttributesEnum.BillPageItem.index();
    public static final int PAGEITEM = AttributesEnum.PageItem.index();
    public static final int DESCRIPTION = AttributesEnum.Description.index();
    public static final int UOM = AttributesEnum.Uom.index();
    public static final int BILLQTY = AttributesEnum.BillQty.index();
    public static final int SELLINGRATE = AttributesEnum.SellingRate.index();
    public static final int SELLINGAMOUNT = AttributesEnum.SellingAmount.index();
    public static final int PREVIOUSCUMMULATIVEQTY = AttributesEnum.PreviousCummulativeQty.index();
    public static final int PREVIOUSPERCENT = AttributesEnum.PreviousPercent.index();
    public static final int PREVIOUSAMOUNT = AttributesEnum.PreviousAmount.index();
    public static final int CURRENTQTY = AttributesEnum.CurrentQty.index();
    public static final int CURRENTPERCENT = AttributesEnum.CurrentPercent.index();
    public static final int CURRENTAMOUNT = AttributesEnum.CurrentAmount.index();
    public static final int CUMULATIVEQTY = AttributesEnum.CumulativeQty.index();
    public static final int CUMULATIVEPERCENT = AttributesEnum.CumulativePercent.index();
    public static final int CUMULATIVEAMOUNT = AttributesEnum.CumulativeAmount.index();
    public static final int HEADERID = AttributesEnum.HeaderId.index();
    public static final int LINEID = AttributesEnum.LineId.index();
    public static final int BILLNUMBER = AttributesEnum.BillNumber.index();
    public static final int RECORDTYPE = AttributesEnum.RecordType.index();

    /**
     * This is the default constructor (do not remove).
     */
    public WorkDoneDetailsReportVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute ContractNumber.
     * @return the ContractNumber
     */
    public String getContractNumber() {
        return (String) getAttributeInternal(CONTRACTNUMBER);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ContractNumber.
     * @param value value to set the  ContractNumber
     */
    public void setContractNumber(String value) {
        setAttributeInternal(CONTRACTNUMBER, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Cognomen.
     * @return the Cognomen
     */
    public String getCognomen() {
        return (String) getAttributeInternal(COGNOMEN);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Cognomen.
     * @param value value to set the  Cognomen
     */
    public void setCognomen(String value) {
        setAttributeInternal(COGNOMEN, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CurrencyCode.
     * @return the CurrencyCode
     */
    public String getCurrencyCode() {
        return (String) getAttributeInternal(CURRENCYCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CurrencyCode.
     * @param value value to set the  CurrencyCode
     */
    public void setCurrencyCode(String value) {
        setAttributeInternal(CURRENCYCODE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute WdDocumentNum.
     * @return the WdDocumentNum
     */
    public String getWdDocumentNum() {
        return (String) getAttributeInternal(WDDOCUMENTNUM);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute WdDocumentNum.
     * @param value value to set the  WdDocumentNum
     */
    public void setWdDocumentNum(String value) {
        setAttributeInternal(WDDOCUMENTNUM, value);
    }

    /**
     * Gets the attribute value for the calculated attribute PeriodOfMeasure.
     * @return the PeriodOfMeasure
     */
    public String getPeriodOfMeasure() {
        return (String) getAttributeInternal(PERIODOFMEASURE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute PeriodOfMeasure.
     * @param value value to set the  PeriodOfMeasure
     */
    public void setPeriodOfMeasure(String value) {
        setAttributeInternal(PERIODOFMEASURE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ValuationDate.
     * @return the ValuationDate
     */
    public Date getValuationDate() {
        return (Date) getAttributeInternal(VALUATIONDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ValuationDate.
     * @param value value to set the  ValuationDate
     */
    public void setValuationDate(Date value) {
        setAttributeInternal(VALUATIONDATE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute BillPageItem.
     * @return the BillPageItem
     */
    public String getBillPageItem() {
        return (String) getAttributeInternal(BILLPAGEITEM);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute BillPageItem.
     * @param value value to set the  BillPageItem
     */
    public void setBillPageItem(String value) {
        setAttributeInternal(BILLPAGEITEM, value);
    }

    /**
     * Gets the attribute value for the calculated attribute PageItem.
     * @return the PageItem
     */
    public String getPageItem() {
        return (String) getAttributeInternal(PAGEITEM);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute PageItem.
     * @param value value to set the  PageItem
     */
    public void setPageItem(String value) {
        setAttributeInternal(PAGEITEM, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Description.
     * @return the Description
     */
    public String getDescription() {
        return (String) getAttributeInternal(DESCRIPTION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Description.
     * @param value value to set the  Description
     */
    public void setDescription(String value) {
        setAttributeInternal(DESCRIPTION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Uom.
     * @return the Uom
     */
    public String getUom() {
        return (String) getAttributeInternal(UOM);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Uom.
     * @param value value to set the  Uom
     */
    public void setUom(String value) {
        setAttributeInternal(UOM, value);
    }

    /**
     * Gets the attribute value for the calculated attribute BillQty.
     * @return the BillQty
     */
    public BigDecimal getBillQty() {
        return (BigDecimal) getAttributeInternal(BILLQTY);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute BillQty.
     * @param value value to set the  BillQty
     */
    public void setBillQty(BigDecimal value) {
        setAttributeInternal(BILLQTY, value);
    }

    /**
     * Gets the attribute value for the calculated attribute SellingRate.
     * @return the SellingRate
     */
    public BigDecimal getSellingRate() {
        return (BigDecimal) getAttributeInternal(SELLINGRATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute SellingRate.
     * @param value value to set the  SellingRate
     */
    public void setSellingRate(BigDecimal value) {
        setAttributeInternal(SELLINGRATE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute SellingAmount.
     * @return the SellingAmount
     */
    public BigDecimal getSellingAmount() {
        return (BigDecimal) getAttributeInternal(SELLINGAMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute SellingAmount.
     * @param value value to set the  SellingAmount
     */
    public void setSellingAmount(BigDecimal value) {
        setAttributeInternal(SELLINGAMOUNT, value);
    }

    /**
     * Gets the attribute value for the calculated attribute PreviousCummulativeQty.
     * @return the PreviousCummulativeQty
     */
    public BigDecimal getPreviousCummulativeQty() {
        return (BigDecimal) getAttributeInternal(PREVIOUSCUMMULATIVEQTY);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute PreviousCummulativeQty.
     * @param value value to set the  PreviousCummulativeQty
     */
    public void setPreviousCummulativeQty(BigDecimal value) {
        setAttributeInternal(PREVIOUSCUMMULATIVEQTY, value);
    }

    /**
     * Gets the attribute value for the calculated attribute PreviousPercent.
     * @return the PreviousPercent
     */
    public BigDecimal getPreviousPercent() {
        return (BigDecimal) getAttributeInternal(PREVIOUSPERCENT);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute PreviousPercent.
     * @param value value to set the  PreviousPercent
     */
    public void setPreviousPercent(BigDecimal value) {
        setAttributeInternal(PREVIOUSPERCENT, value);
    }

    /**
     * Gets the attribute value for the calculated attribute PreviousAmount.
     * @return the PreviousAmount
     */
    public BigDecimal getPreviousAmount() {
        return (BigDecimal) getAttributeInternal(PREVIOUSAMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute PreviousAmount.
     * @param value value to set the  PreviousAmount
     */
    public void setPreviousAmount(BigDecimal value) {
        setAttributeInternal(PREVIOUSAMOUNT, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CurrentQty.
     * @return the CurrentQty
     */
    public BigDecimal getCurrentQty() {
        return (BigDecimal) getAttributeInternal(CURRENTQTY);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CurrentQty.
     * @param value value to set the  CurrentQty
     */
    public void setCurrentQty(BigDecimal value) {
        setAttributeInternal(CURRENTQTY, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CurrentPercent.
     * @return the CurrentPercent
     */
    public BigDecimal getCurrentPercent() {
        return (BigDecimal) getAttributeInternal(CURRENTPERCENT);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CurrentPercent.
     * @param value value to set the  CurrentPercent
     */
    public void setCurrentPercent(BigDecimal value) {
        setAttributeInternal(CURRENTPERCENT, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CurrentAmount.
     * @return the CurrentAmount
     */
    public BigDecimal getCurrentAmount() {
        return (BigDecimal) getAttributeInternal(CURRENTAMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CurrentAmount.
     * @param value value to set the  CurrentAmount
     */
    public void setCurrentAmount(BigDecimal value) {
        setAttributeInternal(CURRENTAMOUNT, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CumulativeQty.
     * @return the CumulativeQty
     */
    public BigDecimal getCumulativeQty() {
        return (BigDecimal) getAttributeInternal(CUMULATIVEQTY);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CumulativeQty.
     * @param value value to set the  CumulativeQty
     */
    public void setCumulativeQty(BigDecimal value) {
        setAttributeInternal(CUMULATIVEQTY, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CumulativePercent.
     * @return the CumulativePercent
     */
    public BigDecimal getCumulativePercent() {
        return (BigDecimal) getAttributeInternal(CUMULATIVEPERCENT);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CumulativePercent.
     * @param value value to set the  CumulativePercent
     */
    public void setCumulativePercent(BigDecimal value) {
        setAttributeInternal(CUMULATIVEPERCENT, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CumulativeAmount.
     * @return the CumulativeAmount
     */
    public BigDecimal getCumulativeAmount() {
        return (BigDecimal) getAttributeInternal(CUMULATIVEAMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CumulativeAmount.
     * @param value value to set the  CumulativeAmount
     */
    public void setCumulativeAmount(BigDecimal value) {
        setAttributeInternal(CUMULATIVEAMOUNT, value);
    }

    /**
     * Gets the attribute value for the calculated attribute HeaderId.
     * @return the HeaderId
     */
    public BigDecimal getHeaderId() {
        return (BigDecimal) getAttributeInternal(HEADERID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute HeaderId.
     * @param value value to set the  HeaderId
     */
    public void setHeaderId(BigDecimal value) {
        setAttributeInternal(HEADERID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute LineId.
     * @return the LineId
     */
    public BigDecimal getLineId() {
        return (BigDecimal) getAttributeInternal(LINEID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute LineId.
     * @param value value to set the  LineId
     */
    public void setLineId(BigDecimal value) {
        setAttributeInternal(LINEID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute BillNumber.
     * @return the BillNumber
     */
    public String getBillNumber() {
        return (String) getAttributeInternal(BILLNUMBER);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute BillNumber.
     * @param value value to set the  BillNumber
     */
    public void setBillNumber(String value) {
        setAttributeInternal(BILLNUMBER, value);
    }

    /**
     * Gets the attribute value for the calculated attribute RecordType.
     * @return the RecordType
     */
    public String getRecordType() {
        return (String) getAttributeInternal(RECORDTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute RecordType.
     * @param value value to set the  RecordType
     */
    public void setRecordType(String value) {
        setAttributeInternal(RECORDTYPE, value);
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
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            return AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].get(this);
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
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].put(this, value);
            return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
    }
}
