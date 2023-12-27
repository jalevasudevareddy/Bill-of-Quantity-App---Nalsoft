package model.searchview;

import java.math.BigDecimal;

import java.sql.Date;

import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Oct 10 16:54:18 IST 2018
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CurrentBoqHdrScrVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        BoqHeaderId {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getBoqHeaderId();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setBoqHeaderId((BigDecimal)value);
            }
        },
        BusinessUnitId {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getBusinessUnitId();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setBusinessUnitId((BigDecimal)value);
            }
        },
        ProjectId {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getProjectId();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setProjectId((BigDecimal)value);
            }
        },
        Version {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getVersion();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setVersion((BigDecimal)value);
            }
        },
        Status {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getStatus();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setStatus((String)value);
            }
        },
        BudgetType {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getBudgetType();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setBudgetType((String)value);
            }
        },
        IbcNumber {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getIbcNumber();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setIbcNumber((String)value);
            }
        },
        IbcReason {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getIbcReason();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setIbcReason((String)value);
            }
        },
        ImportDate {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getImportDate();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setImportDate((Date)value);
            }
        },
        RevisionDate {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getRevisionDate();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setRevisionDate((Date)value);
            }
        },
        ApprovedBy {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getApprovedBy();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setApprovedBy((String)value);
            }
        },
        ApprovedDate {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getApprovedDate();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setApprovedDate((Date)value);
            }
        },
        CreationDate {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getCreationDate();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setCreationDate((Date)value);
            }
        },
        CreatedBy {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getCreatedBy();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setCreatedBy((String)value);
            }
        },
        LastUpdatedDate {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getLastUpdatedDate();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setLastUpdatedDate((Date)value);
            }
        },
        LastUpdatedBy {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getLastUpdatedBy();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setLastUpdatedBy((String)value);
            }
        },
        ValidationHeaderId {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getValidationHeaderId();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setValidationHeaderId((BigDecimal)value);
            }
        },
        MasterValidationHeaderId {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getMasterValidationHeaderId();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setMasterValidationHeaderId((BigDecimal)value);
            }
        },
        TotalNetFinalAmount {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getTotalNetFinalAmount();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setTotalNetFinalAmount((BigDecimal)value);
            }
        },
        TotalCandyNetFinalAmount {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getTotalCandyNetFinalAmount();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setTotalCandyNetFinalAmount((BigDecimal)value);
            }
        },
        MasterBoqHeaderId {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getMasterBoqHeaderId();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setMasterBoqHeaderId((BigDecimal)value);
            }
        },
        CurrentFlag {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getCurrentFlag();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setCurrentFlag((String)value);
            }
        },
        TotalCandySellingAmount {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getTotalCandySellingAmount();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setTotalCandySellingAmount((BigDecimal)value);
            }
        },
        TransAttachmentsCode {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getTransAttachmentsCode();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setTransAttachmentsCode((String)value);
            }
        },
        ReasonForVariation {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getReasonForVariation();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setReasonForVariation((String)value);
            }
        },
        BaseLineBoqHdrId {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getBaseLineBoqHdrId();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setBaseLineBoqHdrId((BigDecimal)value);
            }
        },
        BaseLine {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getBaseLine();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setBaseLine((String)value);
            }
        }
        ,
        BudgetFlag {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getBudgetFlag();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setBudgetFlag((String)value);
            }
        }
        ,
        ParentBoqHeaderId {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getParentBoqHeaderId();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setParentBoqHeaderId((BigDecimal)value);
            }
        }
        ,
        ContractId {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getContractId();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setContractId((BigDecimal)value);
            }
        }
        ,
        ContractType {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getContractType();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setContractType((String)value);
            }
        }
        ,
        LedgerCurrencyConvDate {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getLedgerCurrencyConvDate();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setLedgerCurrencyConvDate((Date)value);
            }
        }
        ,
        LedgerCurrencyConvRateType {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getLedgerCurrencyConvRateType();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setLedgerCurrencyConvRateType((String)value);
            }
        }
        ,
        LedgerCurrencyConvRate {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getLedgerCurrencyConvRate();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setLedgerCurrencyConvRate((BigDecimal)value);
            }
        }
        ,
        Description {
            public Object get(CurrentBoqHdrScrVORowImpl obj) {
                return obj.getDescription();
            }

            public void put(CurrentBoqHdrScrVORowImpl obj, Object value) {
                obj.setDescription((String)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public abstract Object get(CurrentBoqHdrScrVORowImpl object);

        public abstract void put(CurrentBoqHdrScrVORowImpl object,
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


    public static final int BOQHEADERID = AttributesEnum.BoqHeaderId.index();
    public static final int BUSINESSUNITID =
        AttributesEnum.BusinessUnitId.index();
    public static final int PROJECTID = AttributesEnum.ProjectId.index();
    public static final int VERSION = AttributesEnum.Version.index();
    public static final int STATUS = AttributesEnum.Status.index();
    public static final int BUDGETTYPE = AttributesEnum.BudgetType.index();
    public static final int IBCNUMBER = AttributesEnum.IbcNumber.index();
    public static final int IBCREASON = AttributesEnum.IbcReason.index();
    public static final int IMPORTDATE = AttributesEnum.ImportDate.index();
    public static final int REVISIONDATE = AttributesEnum.RevisionDate.index();
    public static final int APPROVEDBY = AttributesEnum.ApprovedBy.index();
    public static final int APPROVEDDATE = AttributesEnum.ApprovedDate.index();
    public static final int CREATIONDATE = AttributesEnum.CreationDate.index();
    public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
    public static final int LASTUPDATEDDATE =
        AttributesEnum.LastUpdatedDate.index();
    public static final int LASTUPDATEDBY =
        AttributesEnum.LastUpdatedBy.index();
    public static final int VALIDATIONHEADERID =
        AttributesEnum.ValidationHeaderId.index();
    public static final int MASTERVALIDATIONHEADERID =
        AttributesEnum.MasterValidationHeaderId.index();
    public static final int TOTALNETFINALAMOUNT =
        AttributesEnum.TotalNetFinalAmount.index();
    public static final int TOTALCANDYNETFINALAMOUNT =
        AttributesEnum.TotalCandyNetFinalAmount.index();
    public static final int MASTERBOQHEADERID =
        AttributesEnum.MasterBoqHeaderId.index();
    public static final int CURRENTFLAG = AttributesEnum.CurrentFlag.index();
    public static final int TOTALCANDYSELLINGAMOUNT =
        AttributesEnum.TotalCandySellingAmount.index();
    public static final int TRANSATTACHMENTSCODE =
        AttributesEnum.TransAttachmentsCode.index();
    public static final int REASONFORVARIATION =
        AttributesEnum.ReasonForVariation.index();
    public static final int BASELINEBOQHDRID =
        AttributesEnum.BaseLineBoqHdrId.index();
    public static final int BASELINE = AttributesEnum.BaseLine.index();
    public static final int BUDGETFLAG = AttributesEnum.BudgetFlag.index();
    public static final int PARENTBOQHEADERID = AttributesEnum.ParentBoqHeaderId.index();
    public static final int CONTRACTID = AttributesEnum.ContractId.index();
    public static final int CONTRACTTYPE = AttributesEnum.ContractType.index();
    public static final int LEDGERCURRENCYCONVDATE = AttributesEnum.LedgerCurrencyConvDate.index();
    public static final int LEDGERCURRENCYCONVRATETYPE = AttributesEnum.LedgerCurrencyConvRateType.index();
    public static final int LEDGERCURRENCYCONVRATE = AttributesEnum.LedgerCurrencyConvRate.index();
    public static final int DESCRIPTION = AttributesEnum.Description.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CurrentBoqHdrScrVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute BoqHeaderId.
     * @return the BoqHeaderId
     */
    public BigDecimal getBoqHeaderId() {
        return (BigDecimal) getAttributeInternal(BOQHEADERID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute BoqHeaderId.
     * @param value value to set the  BoqHeaderId
     */
    public void setBoqHeaderId(BigDecimal value) {
        setAttributeInternal(BOQHEADERID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute BusinessUnitId.
     * @return the BusinessUnitId
     */
    public BigDecimal getBusinessUnitId() {
        return (BigDecimal) getAttributeInternal(BUSINESSUNITID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute BusinessUnitId.
     * @param value value to set the  BusinessUnitId
     */
    public void setBusinessUnitId(BigDecimal value) {
        setAttributeInternal(BUSINESSUNITID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ProjectId.
     * @return the ProjectId
     */
    public BigDecimal getProjectId() {
        return (BigDecimal) getAttributeInternal(PROJECTID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ProjectId.
     * @param value value to set the  ProjectId
     */
    public void setProjectId(BigDecimal value) {
        setAttributeInternal(PROJECTID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Version.
     * @return the Version
     */
    public BigDecimal getVersion() {
        return (BigDecimal) getAttributeInternal(VERSION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Version.
     * @param value value to set the  Version
     */
    public void setVersion(BigDecimal value) {
        setAttributeInternal(VERSION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Status.
     * @return the Status
     */
    public String getStatus() {
        return (String) getAttributeInternal(STATUS);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Status.
     * @param value value to set the  Status
     */
    public void setStatus(String value) {
        setAttributeInternal(STATUS, value);
    }

    /**
     * Gets the attribute value for the calculated attribute BudgetType.
     * @return the BudgetType
     */
    public String getBudgetType() {
        return (String) getAttributeInternal(BUDGETTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute BudgetType.
     * @param value value to set the  BudgetType
     */
    public void setBudgetType(String value) {
        setAttributeInternal(BUDGETTYPE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IbcNumber.
     * @return the IbcNumber
     */
    public String getIbcNumber() {
        return (String) getAttributeInternal(IBCNUMBER);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IbcNumber.
     * @param value value to set the  IbcNumber
     */
    public void setIbcNumber(String value) {
        setAttributeInternal(IBCNUMBER, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IbcReason.
     * @return the IbcReason
     */
    public String getIbcReason() {
        return (String) getAttributeInternal(IBCREASON);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IbcReason.
     * @param value value to set the  IbcReason
     */
    public void setIbcReason(String value) {
        setAttributeInternal(IBCREASON, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ImportDate.
     * @return the ImportDate
     */
    public Date getImportDate() {
        return (Date) getAttributeInternal(IMPORTDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ImportDate.
     * @param value value to set the  ImportDate
     */
    public void setImportDate(Date value) {
        setAttributeInternal(IMPORTDATE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute RevisionDate.
     * @return the RevisionDate
     */
    public Date getRevisionDate() {
        return (Date) getAttributeInternal(REVISIONDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute RevisionDate.
     * @param value value to set the  RevisionDate
     */
    public void setRevisionDate(Date value) {
        setAttributeInternal(REVISIONDATE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ApprovedBy.
     * @return the ApprovedBy
     */
    public String getApprovedBy() {
        return (String) getAttributeInternal(APPROVEDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ApprovedBy.
     * @param value value to set the  ApprovedBy
     */
    public void setApprovedBy(String value) {
        setAttributeInternal(APPROVEDBY, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ApprovedDate.
     * @return the ApprovedDate
     */
    public Date getApprovedDate() {
        return (Date) getAttributeInternal(APPROVEDDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ApprovedDate.
     * @param value value to set the  ApprovedDate
     */
    public void setApprovedDate(Date value) {
        setAttributeInternal(APPROVEDDATE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CreationDate.
     * @return the CreationDate
     */
    public Date getCreationDate() {
        return (Date) getAttributeInternal(CREATIONDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CreationDate.
     * @param value value to set the  CreationDate
     */
    public void setCreationDate(Date value) {
        setAttributeInternal(CREATIONDATE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CreatedBy.
     * @return the CreatedBy
     */
    public String getCreatedBy() {
        return (String) getAttributeInternal(CREATEDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CreatedBy.
     * @param value value to set the  CreatedBy
     */
    public void setCreatedBy(String value) {
        setAttributeInternal(CREATEDBY, value);
    }

    /**
     * Gets the attribute value for the calculated attribute LastUpdatedDate.
     * @return the LastUpdatedDate
     */
    public Date getLastUpdatedDate() {
        return (Date) getAttributeInternal(LASTUPDATEDDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute LastUpdatedDate.
     * @param value value to set the  LastUpdatedDate
     */
    public void setLastUpdatedDate(Date value) {
        setAttributeInternal(LASTUPDATEDDATE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute LastUpdatedBy.
     * @return the LastUpdatedBy
     */
    public String getLastUpdatedBy() {
        return (String) getAttributeInternal(LASTUPDATEDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute LastUpdatedBy.
     * @param value value to set the  LastUpdatedBy
     */
    public void setLastUpdatedBy(String value) {
        setAttributeInternal(LASTUPDATEDBY, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ValidationHeaderId.
     * @return the ValidationHeaderId
     */
    public BigDecimal getValidationHeaderId() {
        return (BigDecimal) getAttributeInternal(VALIDATIONHEADERID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ValidationHeaderId.
     * @param value value to set the  ValidationHeaderId
     */
    public void setValidationHeaderId(BigDecimal value) {
        setAttributeInternal(VALIDATIONHEADERID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute MasterValidationHeaderId.
     * @return the MasterValidationHeaderId
     */
    public BigDecimal getMasterValidationHeaderId() {
        return (BigDecimal) getAttributeInternal(MASTERVALIDATIONHEADERID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute MasterValidationHeaderId.
     * @param value value to set the  MasterValidationHeaderId
     */
    public void setMasterValidationHeaderId(BigDecimal value) {
        setAttributeInternal(MASTERVALIDATIONHEADERID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TotalNetFinalAmount.
     * @return the TotalNetFinalAmount
     */
    public BigDecimal getTotalNetFinalAmount() {
        return (BigDecimal) getAttributeInternal(TOTALNETFINALAMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TotalNetFinalAmount.
     * @param value value to set the  TotalNetFinalAmount
     */
    public void setTotalNetFinalAmount(BigDecimal value) {
        setAttributeInternal(TOTALNETFINALAMOUNT, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TotalCandyNetFinalAmount.
     * @return the TotalCandyNetFinalAmount
     */
    public BigDecimal getTotalCandyNetFinalAmount() {
        return (BigDecimal) getAttributeInternal(TOTALCANDYNETFINALAMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TotalCandyNetFinalAmount.
     * @param value value to set the  TotalCandyNetFinalAmount
     */
    public void setTotalCandyNetFinalAmount(BigDecimal value) {
        setAttributeInternal(TOTALCANDYNETFINALAMOUNT, value);
    }

    /**
     * Gets the attribute value for the calculated attribute MasterBoqHeaderId.
     * @return the MasterBoqHeaderId
     */
    public BigDecimal getMasterBoqHeaderId() {
        return (BigDecimal) getAttributeInternal(MASTERBOQHEADERID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute MasterBoqHeaderId.
     * @param value value to set the  MasterBoqHeaderId
     */
    public void setMasterBoqHeaderId(BigDecimal value) {
        setAttributeInternal(MASTERBOQHEADERID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CurrentFlag.
     * @return the CurrentFlag
     */
    public String getCurrentFlag() {
        return (String) getAttributeInternal(CURRENTFLAG);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CurrentFlag.
     * @param value value to set the  CurrentFlag
     */
    public void setCurrentFlag(String value) {
        setAttributeInternal(CURRENTFLAG, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TotalCandySellingAmount.
     * @return the TotalCandySellingAmount
     */
    public BigDecimal getTotalCandySellingAmount() {
        return (BigDecimal) getAttributeInternal(TOTALCANDYSELLINGAMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TotalCandySellingAmount.
     * @param value value to set the  TotalCandySellingAmount
     */
    public void setTotalCandySellingAmount(BigDecimal value) {
        setAttributeInternal(TOTALCANDYSELLINGAMOUNT, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TransAttachmentsCode.
     * @return the TransAttachmentsCode
     */
    public String getTransAttachmentsCode() {
        return (String) getAttributeInternal(TRANSATTACHMENTSCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TransAttachmentsCode.
     * @param value value to set the  TransAttachmentsCode
     */
    public void setTransAttachmentsCode(String value) {
        setAttributeInternal(TRANSATTACHMENTSCODE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ReasonForVariation.
     * @return the ReasonForVariation
     */
    public String getReasonForVariation() {
        return (String) getAttributeInternal(REASONFORVARIATION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ReasonForVariation.
     * @param value value to set the  ReasonForVariation
     */
    public void setReasonForVariation(String value) {
        setAttributeInternal(REASONFORVARIATION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute BaseLineBoqHdrId.
     * @return the BaseLineBoqHdrId
     */
    public BigDecimal getBaseLineBoqHdrId() {
        return (BigDecimal) getAttributeInternal(BASELINEBOQHDRID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute BaseLineBoqHdrId.
     * @param value value to set the  BaseLineBoqHdrId
     */
    public void setBaseLineBoqHdrId(BigDecimal value) {
        setAttributeInternal(BASELINEBOQHDRID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute BaseLine.
     * @return the BaseLine
     */
    public String getBaseLine() {
        return (String) getAttributeInternal(BASELINE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute BaseLine.
     * @param value value to set the  BaseLine
     */
    public void setBaseLine(String value) {
        setAttributeInternal(BASELINE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute BudgetFlag.
     * @return the BudgetFlag
     */
    public String getBudgetFlag() {
        return (String) getAttributeInternal(BUDGETFLAG);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute BudgetFlag.
     * @param value value to set the  BudgetFlag
     */
    public void setBudgetFlag(String value) {
        setAttributeInternal(BUDGETFLAG, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ParentBoqHeaderId.
     * @return the ParentBoqHeaderId
     */
    public BigDecimal getParentBoqHeaderId() {
        return (BigDecimal) getAttributeInternal(PARENTBOQHEADERID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ParentBoqHeaderId.
     * @param value value to set the  ParentBoqHeaderId
     */
    public void setParentBoqHeaderId(BigDecimal value) {
        setAttributeInternal(PARENTBOQHEADERID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ContractId.
     * @return the ContractId
     */
    public BigDecimal getContractId() {
        return (BigDecimal) getAttributeInternal(CONTRACTID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ContractId.
     * @param value value to set the  ContractId
     */
    public void setContractId(BigDecimal value) {
        setAttributeInternal(CONTRACTID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ContractType.
     * @return the ContractType
     */
    public String getContractType() {
        return (String) getAttributeInternal(CONTRACTTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ContractType.
     * @param value value to set the  ContractType
     */
    public void setContractType(String value) {
        setAttributeInternal(CONTRACTTYPE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute LedgerCurrencyConvDate.
     * @return the LedgerCurrencyConvDate
     */
    public Date getLedgerCurrencyConvDate() {
        return (Date) getAttributeInternal(LEDGERCURRENCYCONVDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute LedgerCurrencyConvDate.
     * @param value value to set the  LedgerCurrencyConvDate
     */
    public void setLedgerCurrencyConvDate(Date value) {
        setAttributeInternal(LEDGERCURRENCYCONVDATE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute LedgerCurrencyConvRateType.
     * @return the LedgerCurrencyConvRateType
     */
    public String getLedgerCurrencyConvRateType() {
        return (String) getAttributeInternal(LEDGERCURRENCYCONVRATETYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute LedgerCurrencyConvRateType.
     * @param value value to set the  LedgerCurrencyConvRateType
     */
    public void setLedgerCurrencyConvRateType(String value) {
        setAttributeInternal(LEDGERCURRENCYCONVRATETYPE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute LedgerCurrencyConvRate.
     * @return the LedgerCurrencyConvRate
     */
    public BigDecimal getLedgerCurrencyConvRate() {
        return (BigDecimal) getAttributeInternal(LEDGERCURRENCYCONVRATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute LedgerCurrencyConvRate.
     * @param value value to set the  LedgerCurrencyConvRate
     */
    public void setLedgerCurrencyConvRate(BigDecimal value) {
        setAttributeInternal(LEDGERCURRENCYCONVRATE, value);
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
