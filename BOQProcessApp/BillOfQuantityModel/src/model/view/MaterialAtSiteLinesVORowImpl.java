package model.view;

import java.math.BigDecimal;

import java.sql.Date;

import model.entity.XxboqActivityDetailsEOImpl;
import model.entity.XxboqMaterialAtSiteHdrEOImpl;
import model.entity.XxboqMaterialAtSiteLinesEOImpl;

import model.entity.XxboqResourceItemsEOImpl;

import oracle.jbo.RowSet;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Jan 20 10:04:35 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class MaterialAtSiteLinesVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        ActivityId {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getActivityId();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setActivityId((BigDecimal)value);
            }
        },
        CreatedBy {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getCreatedBy();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setCreatedBy((String)value);
            }
        },
        CreationDate {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getCreationDate();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setCreationDate((Date)value);
            }
        },
        DocHeaderId {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getDocHeaderId();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setDocHeaderId((BigDecimal)value);
            }
        },
        LastUpdatedBy {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getLastUpdatedBy();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setLastUpdatedBy((String)value);
            }
        },
        LastUpdatedDate {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getLastUpdatedDate();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setLastUpdatedDate((Date)value);
            }
        },
        ResourceItemId {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getResourceItemId();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setResourceItemId((BigDecimal)value);
            }
        },
        BusinessUnitId {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getBusinessUnitId();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setBusinessUnitId((BigDecimal)value);
            }
        },
        DocHeaderId1 {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getDocHeaderId1();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setDocHeaderId1((BigDecimal)value);
            }
        },
        ProjectId {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getProjectId();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setProjectId((BigDecimal)value);
            }
        },
        TransActivityCode {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getTransActivityCode();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setTransActivityCode((String)value);
            }
        },
        TransResourceItem {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getTransResourceItem();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setTransResourceItem((String)value);
            }
        },
        ActivityCode {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getActivityCode();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setActivityCode((String)value);
            }
        },
        ActivityId1 {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getActivityId1();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setActivityId1((BigDecimal)value);
            }
        },
        ItemCode {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getItemCode();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setItemCode((String)value);
            }
        },
        ItemId {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getItemId();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setItemId((BigDecimal)value);
            }
        },
        ItemDescription {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getItemDescription();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setItemDescription((String)value);
            }
        },
        ResourceGroupId {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getResourceGroupId();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setResourceGroupId((BigDecimal)value);
            }
        },
        ResourceSubGroupId {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getResourceSubGroupId();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setResourceSubGroupId((BigDecimal)value);
            }
        },
        ItemRate {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getItemRate();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setItemRate((BigDecimal)value);
            }
        },
        TransSubGroup {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getTransSubGroup();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setTransSubGroup((String)value);
            }
        },
        TransGroup {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getTransGroup();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setTransGroup((String)value);
            }
        },
        TransType {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getTransType();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setTransType((String)value);
            }
        },
        TransResourceType {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getTransResourceType();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setTransResourceType((String)value);
            }
        },
        TransResourceGroup {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getTransResourceGroup();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setTransResourceGroup((String)value);
            }
        },
        TransResourceSubGroup {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getTransResourceSubGroup();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setTransResourceSubGroup((String)value);
            }
        },
        TransResourceDesc {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getTransResourceDesc();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setTransResourceDesc((String)value);
            }
        },
        TransResourceRate {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getTransResourceRate();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setTransResourceRate((BigDecimal)value);
            }
        },
        Quantity {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getQuantity();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setQuantity((BigDecimal)value);
            }
        },
        TransTotalValue {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getTransTotalValue();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setTransTotalValue((BigDecimal)value);
            }
        },
        RowID {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getRowID();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setRowID((String)value);
            }
        },
        ActivityForMaterialAtSiteVO1 {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getActivityForMaterialAtSiteVO1();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        },
        ResourceForMaterialAtSiteVO1 {
            public Object get(MaterialAtSiteLinesVORowImpl obj) {
                return obj.getResourceForMaterialAtSiteVO1();
            }

            public void put(MaterialAtSiteLinesVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        };
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public abstract Object get(MaterialAtSiteLinesVORowImpl object);

        public abstract void put(MaterialAtSiteLinesVORowImpl object,
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


    public static final int ACTIVITYID = AttributesEnum.ActivityId.index();
    public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
    public static final int CREATIONDATE = AttributesEnum.CreationDate.index();
    public static final int DOCHEADERID = AttributesEnum.DocHeaderId.index();
    public static final int LASTUPDATEDBY =
        AttributesEnum.LastUpdatedBy.index();
    public static final int LASTUPDATEDDATE =
        AttributesEnum.LastUpdatedDate.index();
    public static final int RESOURCEITEMID =
        AttributesEnum.ResourceItemId.index();
    public static final int BUSINESSUNITID =
        AttributesEnum.BusinessUnitId.index();
    public static final int DOCHEADERID1 = AttributesEnum.DocHeaderId1.index();
    public static final int PROJECTID = AttributesEnum.ProjectId.index();
    public static final int TRANSACTIVITYCODE =
        AttributesEnum.TransActivityCode.index();
    public static final int TRANSRESOURCEITEM =
        AttributesEnum.TransResourceItem.index();
    public static final int ACTIVITYCODE = AttributesEnum.ActivityCode.index();
    public static final int ACTIVITYID1 = AttributesEnum.ActivityId1.index();
    public static final int ITEMCODE = AttributesEnum.ItemCode.index();
    public static final int ITEMID = AttributesEnum.ItemId.index();
    public static final int ITEMDESCRIPTION =
        AttributesEnum.ItemDescription.index();
    public static final int RESOURCEGROUPID =
        AttributesEnum.ResourceGroupId.index();
    public static final int RESOURCESUBGROUPID =
        AttributesEnum.ResourceSubGroupId.index();
    public static final int ITEMRATE = AttributesEnum.ItemRate.index();
    public static final int TRANSSUBGROUP =
        AttributesEnum.TransSubGroup.index();
    public static final int TRANSGROUP = AttributesEnum.TransGroup.index();
    public static final int TRANSTYPE = AttributesEnum.TransType.index();
    public static final int TRANSRESOURCETYPE =
        AttributesEnum.TransResourceType.index();
    public static final int TRANSRESOURCEGROUP =
        AttributesEnum.TransResourceGroup.index();
    public static final int TRANSRESOURCESUBGROUP =
        AttributesEnum.TransResourceSubGroup.index();
    public static final int TRANSRESOURCEDESC =
        AttributesEnum.TransResourceDesc.index();
    public static final int TRANSRESOURCERATE =
        AttributesEnum.TransResourceRate.index();
    public static final int QUANTITY = AttributesEnum.Quantity.index();
    public static final int TRANSTOTALVALUE =
        AttributesEnum.TransTotalValue.index();
    public static final int ROWID = AttributesEnum.RowID.index();
    public static final int ACTIVITYFORMATERIALATSITEVO1 =
        AttributesEnum.ActivityForMaterialAtSiteVO1.index();
    public static final int RESOURCEFORMATERIALATSITEVO1 =
        AttributesEnum.ResourceForMaterialAtSiteVO1.index();

    /**
     * This is the default constructor (do not remove).
     */
    public MaterialAtSiteLinesVORowImpl() {
    }

    /**
     * Gets XxboqMaterialAtSiteLinesEO entity object.
     * @return the XxboqMaterialAtSiteLinesEO
     */
    public XxboqMaterialAtSiteLinesEOImpl getXxboqMaterialAtSiteLinesEO() {
        return (XxboqMaterialAtSiteLinesEOImpl)getEntity(0);
    }

    /**
     * Gets XxboqMaterialAtSiteHdrEO entity object.
     * @return the XxboqMaterialAtSiteHdrEO
     */
    public XxboqMaterialAtSiteHdrEOImpl getXxboqMaterialAtSiteHdrEO() {
        return (XxboqMaterialAtSiteHdrEOImpl)getEntity(1);
    }

    /**
     * Gets XxboqActivityDetailsEO entity object.
     * @return the XxboqActivityDetailsEO
     */
    public XxboqActivityDetailsEOImpl getXxboqActivityDetailsEO() {
        return (XxboqActivityDetailsEOImpl)getEntity(2);
    }

    /**
     * Gets XxboqResourceItemsEO entity object.
     * @return the XxboqResourceItemsEO
     */
    public XxboqResourceItemsEOImpl getXxboqResourceItemsEO() {
        return (XxboqResourceItemsEOImpl)getEntity(3);
    }

    /**
     * Gets the attribute value for ACTIVITY_ID using the alias name ActivityId.
     * @return the ACTIVITY_ID
     */
    public BigDecimal getActivityId() {
        return (BigDecimal)getAttributeInternal(ACTIVITYID);
    }

    /**
     * Sets <code>value</code> as attribute value for ACTIVITY_ID using the alias name ActivityId.
     * @param value value to set the ACTIVITY_ID
     */
    public void setActivityId(BigDecimal value) {
        setAttributeInternal(ACTIVITYID, value);
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
     * Gets the attribute value for DOC_HEADER_ID using the alias name DocHeaderId.
     * @return the DOC_HEADER_ID
     */
    public BigDecimal getDocHeaderId() {
        return (BigDecimal)getAttributeInternal(DOCHEADERID);
    }

    /**
     * Sets <code>value</code> as attribute value for DOC_HEADER_ID using the alias name DocHeaderId.
     * @param value value to set the DOC_HEADER_ID
     */
    public void setDocHeaderId(BigDecimal value) {
        setAttributeInternal(DOCHEADERID, value);
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
     * Gets the attribute value for RESOURCE_ITEM_ID using the alias name ResourceItemId.
     * @return the RESOURCE_ITEM_ID
     */
    public BigDecimal getResourceItemId() {
        return (BigDecimal)getAttributeInternal(RESOURCEITEMID);
    }

    /**
     * Sets <code>value</code> as attribute value for RESOURCE_ITEM_ID using the alias name ResourceItemId.
     * @param value value to set the RESOURCE_ITEM_ID
     */
    public void setResourceItemId(BigDecimal value) {
        setAttributeInternal(RESOURCEITEMID, value);
    }

    /**
     * Gets the attribute value for BUSINESS_UNIT_ID using the alias name BusinessUnitId.
     * @return the BUSINESS_UNIT_ID
     */
    public BigDecimal getBusinessUnitId() {
        return (BigDecimal)getAttributeInternal(BUSINESSUNITID);
    }

    /**
     * Sets <code>value</code> as attribute value for BUSINESS_UNIT_ID using the alias name BusinessUnitId.
     * @param value value to set the BUSINESS_UNIT_ID
     */
    public void setBusinessUnitId(BigDecimal value) {
        setAttributeInternal(BUSINESSUNITID, value);
    }

    /**
     * Gets the attribute value for DOC_HEADER_ID using the alias name DocHeaderId1.
     * @return the DOC_HEADER_ID
     */
    public BigDecimal getDocHeaderId1() {
        return (BigDecimal)getAttributeInternal(DOCHEADERID1);
    }

    /**
     * Sets <code>value</code> as attribute value for DOC_HEADER_ID using the alias name DocHeaderId1.
     * @param value value to set the DOC_HEADER_ID
     */
    public void setDocHeaderId1(BigDecimal value) {
        setAttributeInternal(DOCHEADERID1, value);
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
     * Gets the attribute value for the calculated attribute TransActivityCode.
     * @return the TransActivityCode
     */
    public String getTransActivityCode() {
        return (String)getAttributeInternal(TRANSACTIVITYCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TransActivityCode.
     * @param value value to set the  TransActivityCode
     */
    public void setTransActivityCode(String value) {
        setAttributeInternal(TRANSACTIVITYCODE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TransResourceItem.
     * @return the TransResourceItem
     */
    public String getTransResourceItem() {
        return (String)getAttributeInternal(TRANSRESOURCEITEM);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TransResourceItem.
     * @param value value to set the  TransResourceItem
     */
    public void setTransResourceItem(String value) {
        setAttributeInternal(TRANSRESOURCEITEM, value);
    }

    /**
     * Gets the attribute value for ACTIVITY_CODE using the alias name ActivityCode.
     * @return the ACTIVITY_CODE
     */
    public String getActivityCode() {
        return (String)getAttributeInternal(ACTIVITYCODE);
    }

    /**
     * Sets <code>value</code> as attribute value for ACTIVITY_CODE using the alias name ActivityCode.
     * @param value value to set the ACTIVITY_CODE
     */
    public void setActivityCode(String value) {
        setAttributeInternal(ACTIVITYCODE, value);
    }

    /**
     * Gets the attribute value for ACTIVITY_ID using the alias name ActivityId1.
     * @return the ACTIVITY_ID
     */
    public BigDecimal getActivityId1() {
        return (BigDecimal)getAttributeInternal(ACTIVITYID1);
    }

    /**
     * Sets <code>value</code> as attribute value for ACTIVITY_ID using the alias name ActivityId1.
     * @param value value to set the ACTIVITY_ID
     */
    public void setActivityId1(BigDecimal value) {
        setAttributeInternal(ACTIVITYID1, value);
    }

    /**
     * Gets the attribute value for ITEM_CODE using the alias name ItemCode.
     * @return the ITEM_CODE
     */
    public String getItemCode() {
        return (String)getAttributeInternal(ITEMCODE);
    }

    /**
     * Sets <code>value</code> as attribute value for ITEM_CODE using the alias name ItemCode.
     * @param value value to set the ITEM_CODE
     */
    public void setItemCode(String value) {
        setAttributeInternal(ITEMCODE, value);
    }

    /**
     * Gets the attribute value for ITEM_ID using the alias name ItemId.
     * @return the ITEM_ID
     */
    public BigDecimal getItemId() {
        return (BigDecimal)getAttributeInternal(ITEMID);
    }

    /**
     * Sets <code>value</code> as attribute value for ITEM_ID using the alias name ItemId.
     * @param value value to set the ITEM_ID
     */
    public void setItemId(BigDecimal value) {
        setAttributeInternal(ITEMID, value);
    }

    /**
     * Gets the attribute value for ITEM_DESCRIPTION using the alias name ItemDescription.
     * @return the ITEM_DESCRIPTION
     */
    public String getItemDescription() {
        return (String)getAttributeInternal(ITEMDESCRIPTION);
    }

    /**
     * Sets <code>value</code> as attribute value for ITEM_DESCRIPTION using the alias name ItemDescription.
     * @param value value to set the ITEM_DESCRIPTION
     */
    public void setItemDescription(String value) {
        setAttributeInternal(ITEMDESCRIPTION, value);
    }

    /**
     * Gets the attribute value for RESOURCE_GROUP_ID using the alias name ResourceGroupId.
     * @return the RESOURCE_GROUP_ID
     */
    public BigDecimal getResourceGroupId() {
        return (BigDecimal)getAttributeInternal(RESOURCEGROUPID);
    }

    /**
     * Sets <code>value</code> as attribute value for RESOURCE_GROUP_ID using the alias name ResourceGroupId.
     * @param value value to set the RESOURCE_GROUP_ID
     */
    public void setResourceGroupId(BigDecimal value) {
        setAttributeInternal(RESOURCEGROUPID, value);
    }

    /**
     * Gets the attribute value for RESOURCE_SUB_GROUP_ID using the alias name ResourceSubGroupId.
     * @return the RESOURCE_SUB_GROUP_ID
     */
    public BigDecimal getResourceSubGroupId() {
        return (BigDecimal)getAttributeInternal(RESOURCESUBGROUPID);
    }

    /**
     * Sets <code>value</code> as attribute value for RESOURCE_SUB_GROUP_ID using the alias name ResourceSubGroupId.
     * @param value value to set the RESOURCE_SUB_GROUP_ID
     */
    public void setResourceSubGroupId(BigDecimal value) {
        setAttributeInternal(RESOURCESUBGROUPID, value);
    }

    /**
     * Gets the attribute value for ITEM_RATE using the alias name ItemRate.
     * @return the ITEM_RATE
     */
    public BigDecimal getItemRate() {
        return (BigDecimal)getAttributeInternal(ITEMRATE);
    }

    /**
     * Sets <code>value</code> as attribute value for ITEM_RATE using the alias name ItemRate.
     * @param value value to set the ITEM_RATE
     */
    public void setItemRate(BigDecimal value) {
        setAttributeInternal(ITEMRATE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TransSubGroup.
     * @return the TransSubGroup
     */
    public String getTransSubGroup() {
        return (String)getAttributeInternal(TRANSSUBGROUP);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TransSubGroup.
     * @param value value to set the  TransSubGroup
     */
    public void setTransSubGroup(String value) {
        setAttributeInternal(TRANSSUBGROUP, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TransGroup.
     * @return the TransGroup
     */
    public String getTransGroup() {
        return (String)getAttributeInternal(TRANSGROUP);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TransGroup.
     * @param value value to set the  TransGroup
     */
    public void setTransGroup(String value) {
        setAttributeInternal(TRANSGROUP, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TransType.
     * @return the TransType
     */
    public String getTransType() {
        return (String)getAttributeInternal(TRANSTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TransType.
     * @param value value to set the  TransType
     */
    public void setTransType(String value) {
        setAttributeInternal(TRANSTYPE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TransResourceType.
     * @return the TransResourceType
     */
    public String getTransResourceType() {
        if (getAttributeInternal(TRANSRESOURCETYPE) != null)
            return (String)getAttributeInternal(TRANSRESOURCETYPE);
        return getTransType();
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TransResourceType.
     * @param value value to set the  TransResourceType
     */
    public void setTransResourceType(String value) {
        setAttributeInternal(TRANSRESOURCETYPE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TransResourceGroup.
     * @return the TransResourceGroup
     */
    public String getTransResourceGroup() {
        if (getAttributeInternal(TRANSRESOURCEGROUP) != null)
            return (String)getAttributeInternal(TRANSRESOURCEGROUP);
        return getTransGroup();
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TransResourceGroup.
     * @param value value to set the  TransResourceGroup
     */
    public void setTransResourceGroup(String value) {
        setAttributeInternal(TRANSRESOURCEGROUP, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TransResourceSubGroup.
     * @return the TransResourceSubGroup
     */
    public String getTransResourceSubGroup() {
        if (getAttributeInternal(TRANSRESOURCESUBGROUP) != null)
            return (String)getAttributeInternal(TRANSRESOURCESUBGROUP);
        return getTransSubGroup();
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TransResourceSubGroup.
     * @param value value to set the  TransResourceSubGroup
     */
    public void setTransResourceSubGroup(String value) {
        setAttributeInternal(TRANSRESOURCESUBGROUP, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TransResourceDesc.
     * @return the TransResourceDesc
     */
    public String getTransResourceDesc() {
        if (getAttributeInternal(TRANSRESOURCEDESC) != null)
            return (String)getAttributeInternal(TRANSRESOURCEDESC);
        return getItemDescription();
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TransResourceDesc.
     * @param value value to set the  TransResourceDesc
     */
    public void setTransResourceDesc(String value) {
        setAttributeInternal(TRANSRESOURCEDESC, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TransResourceRate.
     * @return the TransResourceRate
     */
    public BigDecimal getTransResourceRate() {
        if (getAttributeInternal(TRANSRESOURCERATE) != null)
            return (BigDecimal)getAttributeInternal(TRANSRESOURCERATE);
        return getItemRate();
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TransResourceRate.
     * @param value value to set the  TransResourceRate
     */
    public void setTransResourceRate(BigDecimal value) {
        setAttributeInternal(TRANSRESOURCERATE, value);
    }

    /**
     * Gets the attribute value for QUANTITY using the alias name Quantity.
     * @return the QUANTITY
     */
    public BigDecimal getQuantity() {
        return (BigDecimal)getAttributeInternal(QUANTITY);
    }

    /**
     * Sets <code>value</code> as attribute value for QUANTITY using the alias name Quantity.
     * @param value value to set the QUANTITY
     */
    public void setQuantity(BigDecimal value) {
        setAttributeInternal(QUANTITY, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TransTotalValue.
     * @return the TransTotalValue
     */
    public BigDecimal getTransTotalValue() {
        //        return (BigDecimal) getAttributeInternal(TRANSTOTALVALUE);
        return ((getTransResourceRate() != null ? getTransResourceRate() :
                 new BigDecimal(1)).multiply((getQuantity() != null ?
                                              getQuantity() :
                                              new BigDecimal(0))));
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TransTotalValue.
     * @param value value to set the  TransTotalValue
     */
    public void setTransTotalValue(BigDecimal value) {
        setAttributeInternal(TRANSTOTALVALUE, value);
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
     * Gets the view accessor <code>RowSet</code> ActivityForMaterialAtSiteVO1.
     */
    public RowSet getActivityForMaterialAtSiteVO1() {
        return (RowSet)getAttributeInternal(ACTIVITYFORMATERIALATSITEVO1);
    }

    /**
     * Gets the view accessor <code>RowSet</code> ResourceForMaterialAtSiteVO1.
     */
    public RowSet getResourceForMaterialAtSiteVO1() {
        return (RowSet)getAttributeInternal(RESOURCEFORMATERIALATSITEVO1);
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
