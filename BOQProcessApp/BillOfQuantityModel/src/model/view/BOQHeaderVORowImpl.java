package model.view;

import java.math.BigDecimal;

import java.sql.Date;

import model.entity.XxboqBoqHeaderEOImpl;

import oracle.jbo.RowIterator;
import oracle.jbo.RowSet;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Dec 31 09:57:38 IST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class BOQHeaderVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        BuId {
            public Object get(BOQHeaderVORowImpl obj) {
                return obj.getBuId();
            }

            public void put(BOQHeaderVORowImpl obj, Object value) {
                obj.setBuId((BigDecimal)value);
            }
        },
        CreatedBy {
            public Object get(BOQHeaderVORowImpl obj) {
                return obj.getCreatedBy();
            }

            public void put(BOQHeaderVORowImpl obj, Object value) {
                obj.setCreatedBy((String)value);
            }
        },
        CreationDate {
            public Object get(BOQHeaderVORowImpl obj) {
                return obj.getCreationDate();
            }

            public void put(BOQHeaderVORowImpl obj, Object value) {
                obj.setCreationDate((Date)value);
            }
        },
        HeaderId {
            public Object get(BOQHeaderVORowImpl obj) {
                return obj.getHeaderId();
            }

            public void put(BOQHeaderVORowImpl obj, Object value) {
                obj.setHeaderId((BigDecimal)value);
            }
        },
        LastUpdateDate {
            public Object get(BOQHeaderVORowImpl obj) {
                return obj.getLastUpdateDate();
            }

            public void put(BOQHeaderVORowImpl obj, Object value) {
                obj.setLastUpdateDate((Date)value);
            }
        },
        LastUpdatedBy {
            public Object get(BOQHeaderVORowImpl obj) {
                return obj.getLastUpdatedBy();
            }

            public void put(BOQHeaderVORowImpl obj, Object value) {
                obj.setLastUpdatedBy((String)value);
            }
        },
        ProjectId {
            public Object get(BOQHeaderVORowImpl obj) {
                return obj.getProjectId();
            }

            public void put(BOQHeaderVORowImpl obj, Object value) {
                obj.setProjectId((BigDecimal)value);
            }
        },
        TransProjectNumber {
            public Object get(BOQHeaderVORowImpl obj) {
                return obj.getTransProjectNumber();
            }

            public void put(BOQHeaderVORowImpl obj, Object value) {
                obj.setTransProjectNumber((String)value);
            }
        },
        BuName {
            public Object get(BOQHeaderVORowImpl obj) {
                return obj.getBuName();
            }

            public void put(BOQHeaderVORowImpl obj, Object value) {
                obj.setBuName((String)value);
            }
        },
        BuId1 {
            public Object get(BOQHeaderVORowImpl obj) {
                return obj.getBuId1();
            }

            public void put(BOQHeaderVORowImpl obj, Object value) {
                obj.setBuId1((Number)value);
            }
        },
        ProjectName {
            public Object get(BOQHeaderVORowImpl obj) {
                return obj.getProjectName();
            }

            public void put(BOQHeaderVORowImpl obj, Object value) {
                obj.setProjectName((String)value);
            }
        },
        ProjectNumber {
            public Object get(BOQHeaderVORowImpl obj) {
                return obj.getProjectNumber();
            }

            public void put(BOQHeaderVORowImpl obj, Object value) {
                obj.setProjectNumber((String)value);
            }
        },
        Description {
            public Object get(BOQHeaderVORowImpl obj) {
                return obj.getDescription();
            }

            public void put(BOQHeaderVORowImpl obj, Object value) {
                obj.setDescription((String)value);
            }
        },
        ProjectId1 {
            public Object get(BOQHeaderVORowImpl obj) {
                return obj.getProjectId1();
            }

            public void put(BOQHeaderVORowImpl obj, Object value) {
                obj.setProjectId1((Number)value);
            }
        },
        BOQLinesVO {
            public Object get(BOQHeaderVORowImpl obj) {
                return obj.getBOQLinesVO();
            }

            public void put(BOQHeaderVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        },
        UserBUSearchVO1 {
            public Object get(BOQHeaderVORowImpl obj) {
                return obj.getUserBUSearchVO1();
            }

            public void put(BOQHeaderVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        },
        PrjProjectsVO1 {
            public Object get(BOQHeaderVORowImpl obj) {
                return obj.getPrjProjectsVO1();
            }

            public void put(BOQHeaderVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        };
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public abstract Object get(BOQHeaderVORowImpl object);

        public abstract void put(BOQHeaderVORowImpl object, Object value);

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


    public static final int BUID = AttributesEnum.BuId.index();
    public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
    public static final int CREATIONDATE = AttributesEnum.CreationDate.index();
    public static final int HEADERID = AttributesEnum.HeaderId.index();
    public static final int LASTUPDATEDATE =
        AttributesEnum.LastUpdateDate.index();
    public static final int LASTUPDATEDBY =
        AttributesEnum.LastUpdatedBy.index();
    public static final int PROJECTID = AttributesEnum.ProjectId.index();
    public static final int TRANSPROJECTNUMBER =
        AttributesEnum.TransProjectNumber.index();
    public static final int BUNAME = AttributesEnum.BuName.index();
    public static final int BUID1 = AttributesEnum.BuId1.index();
    public static final int PROJECTNAME = AttributesEnum.ProjectName.index();
    public static final int PROJECTNUMBER =
        AttributesEnum.ProjectNumber.index();
    public static final int DESCRIPTION = AttributesEnum.Description.index();
    public static final int PROJECTID1 = AttributesEnum.ProjectId1.index();
    public static final int BOQLINESVO = AttributesEnum.BOQLinesVO.index();
    public static final int USERBUSEARCHVO1 =
        AttributesEnum.UserBUSearchVO1.index();
    public static final int PRJPROJECTSVO1 =
        AttributesEnum.PrjProjectsVO1.index();

    /**
     * This is the default constructor (do not remove).
     */
    public BOQHeaderVORowImpl() {
    }

    /**
     * Gets XxboqBoqHeaderEO entity object.
     * @return the XxboqBoqHeaderEO
     */
    public XxboqBoqHeaderEOImpl getXxboqBoqHeaderEO() {
        return (XxboqBoqHeaderEOImpl)getEntity(0);
    }

    /**
     * Gets XxinvBusinessUnitsEO entity object.
     * @return the XxinvBusinessUnitsEO
     */
    public EntityImpl getXxinvBusinessUnitsEO() {
        return (EntityImpl)getEntity(1);
    }

    /**
     * Gets XxpjProjectsEO entity object.
     * @return the XxpjProjectsEO
     */
    public EntityImpl getXxpjProjectsEO() {
        return (EntityImpl)getEntity(2);
    }

    /**
     * Gets the attribute value for BU_ID using the alias name BuId.
     * @return the BU_ID
     */
    public BigDecimal getBuId() {
        return (BigDecimal)getAttributeInternal(BUID);
    }

    /**
     * Sets <code>value</code> as attribute value for BU_ID using the alias name BuId.
     * @param value value to set the BU_ID
     */
    public void setBuId(BigDecimal value) {
        setAttributeInternal(BUID, value);
    }

    /**
     * Gets the attribute value for CREATED_BY using the alias name CreatedBy.
     * @return the CREATED_BY
     */
    public String getCreatedBy() {
        return (String)getAttributeInternal(CREATEDBY);
    }

    /**
     * Sets <code>value</code> as attribute value for CREATED_BY using the alias name CreatedBy.
     * @param value value to set the CREATED_BY
     */
    public void setCreatedBy(String value) {
        setAttributeInternal(CREATEDBY, value);
    }

    /**
     * Gets the attribute value for CREATION_DATE using the alias name CreationDate.
     * @return the CREATION_DATE
     */
    public Date getCreationDate() {
        return (Date)getAttributeInternal(CREATIONDATE);
    }

    /**
     * Sets <code>value</code> as attribute value for CREATION_DATE using the alias name CreationDate.
     * @param value value to set the CREATION_DATE
     */
    public void setCreationDate(Date value) {
        setAttributeInternal(CREATIONDATE, value);
    }

    /**
     * Gets the attribute value for HEADER_ID using the alias name HeaderId.
     * @return the HEADER_ID
     */
    public BigDecimal getHeaderId() {
        return (BigDecimal)getAttributeInternal(HEADERID);
    }

    /**
     * Sets <code>value</code> as attribute value for HEADER_ID using the alias name HeaderId.
     * @param value value to set the HEADER_ID
     */
    public void setHeaderId(BigDecimal value) {
        setAttributeInternal(HEADERID, value);
    }

    /**
     * Gets the attribute value for LAST_UPDATE_DATE using the alias name LastUpdateDate.
     * @return the LAST_UPDATE_DATE
     */
    public Date getLastUpdateDate() {
        return (Date)getAttributeInternal(LASTUPDATEDATE);
    }

    /**
     * Sets <code>value</code> as attribute value for LAST_UPDATE_DATE using the alias name LastUpdateDate.
     * @param value value to set the LAST_UPDATE_DATE
     */
    public void setLastUpdateDate(Date value) {
        setAttributeInternal(LASTUPDATEDATE, value);
    }

    /**
     * Gets the attribute value for LAST_UPDATED_BY using the alias name LastUpdatedBy.
     * @return the LAST_UPDATED_BY
     */
    public String getLastUpdatedBy() {
        return (String)getAttributeInternal(LASTUPDATEDBY);
    }

    /**
     * Sets <code>value</code> as attribute value for LAST_UPDATED_BY using the alias name LastUpdatedBy.
     * @param value value to set the LAST_UPDATED_BY
     */
    public void setLastUpdatedBy(String value) {
        setAttributeInternal(LASTUPDATEDBY, value);
    }

    /**
     * Gets the attribute value for PROJECT_ID using the alias name ProjectId.
     * @return the PROJECT_ID
     */
    public BigDecimal getProjectId() {
        return (BigDecimal)getAttributeInternal(PROJECTID);
    }

    /**
     * Sets <code>value</code> as attribute value for PROJECT_ID using the alias name ProjectId.
     * @param value value to set the PROJECT_ID
     */
    public void setProjectId(BigDecimal value) {
        setAttributeInternal(PROJECTID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TransProjectNumber.
     * @return the TransProjectNumber
     */
    public String getTransProjectNumber() {
        return (String)getAttributeInternal(TRANSPROJECTNUMBER);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TransProjectNumber.
     * @param value value to set the  TransProjectNumber
     */
    public void setTransProjectNumber(String value) {
        setAttributeInternal(TRANSPROJECTNUMBER, value);
    }

    /**
     * Gets the attribute value for BU_NAME using the alias name BuName.
     * @return the BU_NAME
     */
    public String getBuName() {
        return (String)getAttributeInternal(BUNAME);
    }

    /**
     * Sets <code>value</code> as attribute value for BU_NAME using the alias name BuName.
     * @param value value to set the BU_NAME
     */
    public void setBuName(String value) {
        setAttributeInternal(BUNAME, value);
    }

    /**
     * Gets the attribute value for BU_ID using the alias name BuId1.
     * @return the BU_ID
     */
    public Number getBuId1() {
        return (Number)getAttributeInternal(BUID1);
    }

    /**
     * Sets <code>value</code> as attribute value for BU_ID using the alias name BuId1.
     * @param value value to set the BU_ID
     */
    public void setBuId1(Number value) {
        setAttributeInternal(BUID1, value);
    }

    /**
     * Gets the attribute value for PROJECT_NAME using the alias name ProjectName.
     * @return the PROJECT_NAME
     */
    public String getProjectName() {
        return (String)getAttributeInternal(PROJECTNAME);
    }

    /**
     * Sets <code>value</code> as attribute value for PROJECT_NAME using the alias name ProjectName.
     * @param value value to set the PROJECT_NAME
     */
    public void setProjectName(String value) {
        setAttributeInternal(PROJECTNAME, value);
    }

    /**
     * Gets the attribute value for PROJECT_NUMBER using the alias name ProjectNumber.
     * @return the PROJECT_NUMBER
     */
    public String getProjectNumber() {
        return (String)getAttributeInternal(PROJECTNUMBER);
    }

    /**
     * Sets <code>value</code> as attribute value for PROJECT_NUMBER using the alias name ProjectNumber.
     * @param value value to set the PROJECT_NUMBER
     */
    public void setProjectNumber(String value) {
        setAttributeInternal(PROJECTNUMBER, value);
    }

    /**
     * Gets the attribute value for DESCRIPTION using the alias name Description.
     * @return the DESCRIPTION
     */
    public String getDescription() {
        return (String)getAttributeInternal(DESCRIPTION);
    }

    /**
     * Sets <code>value</code> as attribute value for DESCRIPTION using the alias name Description.
     * @param value value to set the DESCRIPTION
     */
    public void setDescription(String value) {
        setAttributeInternal(DESCRIPTION, value);
    }

    /**
     * Gets the attribute value for PROJECT_ID using the alias name ProjectId1.
     * @return the PROJECT_ID
     */
    public Number getProjectId1() {
        return (Number)getAttributeInternal(PROJECTID1);
    }

    /**
     * Sets <code>value</code> as attribute value for PROJECT_ID using the alias name ProjectId1.
     * @param value value to set the PROJECT_ID
     */
    public void setProjectId1(Number value) {
        setAttributeInternal(PROJECTID1, value);
    }

    /**
     * Gets the associated <code>RowIterator</code> using master-detail link BOQLinesVO.
     */
    public RowIterator getBOQLinesVO() {
        return (RowIterator)getAttributeInternal(BOQLINESVO);
    }

    /**
     * Gets the view accessor <code>RowSet</code> UserBUSearchVO1.
     */
    public RowSet getUserBUSearchVO1() {
        return (RowSet)getAttributeInternal(USERBUSEARCHVO1);
    }

    /**
     * Gets the view accessor <code>RowSet</code> PrjProjectsVO1.
     */
    public RowSet getPrjProjectsVO1() {
        return (RowSet)getAttributeInternal(PRJPROJECTSVO1);
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
