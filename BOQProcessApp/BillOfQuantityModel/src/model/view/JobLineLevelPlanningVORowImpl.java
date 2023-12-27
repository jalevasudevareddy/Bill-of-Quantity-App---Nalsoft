package model.view;

import java.math.BigDecimal;

import java.sql.Date;

import model.entity.XxboqJobPlanLinesEOImpl;

import oracle.jbo.Row;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Jan 24 15:39:20 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class JobLineLevelPlanningVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        BoqQuantity {
            public Object get(JobLineLevelPlanningVORowImpl obj) {
                return obj.getBoqQuantity();
            }

            public void put(JobLineLevelPlanningVORowImpl obj, Object value) {
                obj.setBoqQuantity((BigDecimal)value);
            }
        },
        ColumnId {
            public Object get(JobLineLevelPlanningVORowImpl obj) {
                return obj.getColumnId();
            }

            public void put(JobLineLevelPlanningVORowImpl obj, Object value) {
                obj.setColumnId((String)value);
            }
        },
        CreatedBy {
            public Object get(JobLineLevelPlanningVORowImpl obj) {
                return obj.getCreatedBy();
            }

            public void put(JobLineLevelPlanningVORowImpl obj, Object value) {
                obj.setCreatedBy((String)value);
            }
        },
        CreationDate {
            public Object get(JobLineLevelPlanningVORowImpl obj) {
                return obj.getCreationDate();
            }

            public void put(JobLineLevelPlanningVORowImpl obj, Object value) {
                obj.setCreationDate((Date)value);
            }
        },
        JobLeavelLineId {
            public Object get(JobLineLevelPlanningVORowImpl obj) {
                return obj.getJobLeavelLineId();
            }

            public void put(JobLineLevelPlanningVORowImpl obj, Object value) {
                obj.setJobLeavelLineId((BigDecimal)value);
            }
        },
        JobLineLevelId {
            public Object get(JobLineLevelPlanningVORowImpl obj) {
                return obj.getJobLineLevelId();
            }

            public void put(JobLineLevelPlanningVORowImpl obj, Object value) {
                obj.setJobLineLevelId((BigDecimal)value);
            }
        },
        LastUpdatedBy {
            public Object get(JobLineLevelPlanningVORowImpl obj) {
                return obj.getLastUpdatedBy();
            }

            public void put(JobLineLevelPlanningVORowImpl obj, Object value) {
                obj.setLastUpdatedBy((String)value);
            }
        },
        LastUpdatedDate {
            public Object get(JobLineLevelPlanningVORowImpl obj) {
                return obj.getLastUpdatedDate();
            }

            public void put(JobLineLevelPlanningVORowImpl obj, Object value) {
                obj.setLastUpdatedDate((Date)value);
            }
        },
        Period {
            public Object get(JobLineLevelPlanningVORowImpl obj) {
                return obj.getPeriod();
            }

            public void put(JobLineLevelPlanningVORowImpl obj, Object value) {
                obj.setPeriod((String)value);
            }
        },
        JobLevelPlanningVO {
            public Object get(JobLineLevelPlanningVORowImpl obj) {
                return obj.getJobLevelPlanningVO();
            }

            public void put(JobLineLevelPlanningVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        };
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public abstract Object get(JobLineLevelPlanningVORowImpl object);

        public abstract void put(JobLineLevelPlanningVORowImpl object,
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

    public static final int BOQQUANTITY = AttributesEnum.BoqQuantity.index();
    public static final int COLUMNID = AttributesEnum.ColumnId.index();
    public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
    public static final int CREATIONDATE = AttributesEnum.CreationDate.index();
    public static final int JOBLEAVELLINEID =
        AttributesEnum.JobLeavelLineId.index();
    public static final int JOBLINELEVELID =
        AttributesEnum.JobLineLevelId.index();
    public static final int LASTUPDATEDBY =
        AttributesEnum.LastUpdatedBy.index();
    public static final int LASTUPDATEDDATE =
        AttributesEnum.LastUpdatedDate.index();
    public static final int PERIOD = AttributesEnum.Period.index();
    public static final int JOBLEVELPLANNINGVO =
        AttributesEnum.JobLevelPlanningVO.index();

    /**
     * This is the default constructor (do not remove).
     */
    public JobLineLevelPlanningVORowImpl() {
    }

    /**
     * Gets XxboqJobPlanLinesEO entity object.
     * @return the XxboqJobPlanLinesEO
     */
    public XxboqJobPlanLinesEOImpl getXxboqJobPlanLinesEO() {
        return (XxboqJobPlanLinesEOImpl)getEntity(0);
    }

    /**
     * Gets the attribute value for BOQ_QUANTITY using the alias name BoqQuantity.
     * @return the BOQ_QUANTITY
     */
    public BigDecimal getBoqQuantity() {
        return (BigDecimal)getAttributeInternal(BOQQUANTITY);
    }

    /**
     * Sets <code>value</code> as attribute value for BOQ_QUANTITY using the alias name BoqQuantity.
     * @param value value to set the BOQ_QUANTITY
     */
    public void setBoqQuantity(BigDecimal value) {
        setAttributeInternal(BOQQUANTITY, value);
    }

    /**
     * Gets the attribute value for COLUMN_ID using the alias name ColumnId.
     * @return the COLUMN_ID
     */
    public String getColumnId() {
        return (String)getAttributeInternal(COLUMNID);
    }

    /**
     * Sets <code>value</code> as attribute value for COLUMN_ID using the alias name ColumnId.
     * @param value value to set the COLUMN_ID
     */
    public void setColumnId(String value) {
        setAttributeInternal(COLUMNID, value);
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
     * Gets the attribute value for JOB_LEAVEL_LINE_ID using the alias name JobLeavelLineId.
     * @return the JOB_LEAVEL_LINE_ID
     */
    public BigDecimal getJobLeavelLineId() {
        return (BigDecimal)getAttributeInternal(JOBLEAVELLINEID);
    }

    /**
     * Sets <code>value</code> as attribute value for JOB_LEAVEL_LINE_ID using the alias name JobLeavelLineId.
     * @param value value to set the JOB_LEAVEL_LINE_ID
     */
    public void setJobLeavelLineId(BigDecimal value) {
        setAttributeInternal(JOBLEAVELLINEID, value);
    }

    /**
     * Gets the attribute value for JOB_LINE_LEVEL_ID using the alias name JobLineLevelId.
     * @return the JOB_LINE_LEVEL_ID
     */
    public BigDecimal getJobLineLevelId() {
        return (BigDecimal)getAttributeInternal(JOBLINELEVELID);
    }

    /**
     * Sets <code>value</code> as attribute value for JOB_LINE_LEVEL_ID using the alias name JobLineLevelId.
     * @param value value to set the JOB_LINE_LEVEL_ID
     */
    public void setJobLineLevelId(BigDecimal value) {
        setAttributeInternal(JOBLINELEVELID, value);
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
     * Gets the attribute value for LAST_UPDATED_DATE using the alias name LastUpdatedDate.
     * @return the LAST_UPDATED_DATE
     */
    public Date getLastUpdatedDate() {
        return (Date)getAttributeInternal(LASTUPDATEDDATE);
    }

    /**
     * Sets <code>value</code> as attribute value for LAST_UPDATED_DATE using the alias name LastUpdatedDate.
     * @param value value to set the LAST_UPDATED_DATE
     */
    public void setLastUpdatedDate(Date value) {
        setAttributeInternal(LASTUPDATEDDATE, value);
    }

    /**
     * Gets the attribute value for PERIOD using the alias name Period.
     * @return the PERIOD
     */
    public String getPeriod() {
        return (String)getAttributeInternal(PERIOD);
    }

    /**
     * Sets <code>value</code> as attribute value for PERIOD using the alias name Period.
     * @param value value to set the PERIOD
     */
    public void setPeriod(String value) {
        setAttributeInternal(PERIOD, value);
    }

    /**
     * Gets the associated <code>Row</code> using master-detail link JobLevelPlanningVO.
     */
    public Row getJobLevelPlanningVO() {
        return (Row)getAttributeInternal(JOBLEVELPLANNINGVO);
    }

    /**
     * Sets the master-detail link JobLevelPlanningVO between this object and <code>value</code>.
     */
    public void setJobLevelPlanningVO(Row value) {
        setAttributeInternal(JOBLEVELPLANNINGVO, value);
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
