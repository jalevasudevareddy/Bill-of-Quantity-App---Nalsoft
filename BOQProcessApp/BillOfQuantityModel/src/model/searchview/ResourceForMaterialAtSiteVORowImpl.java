package model.searchview;

import java.math.BigDecimal;

import java.sql.Date;

import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Jan 20 16:29:36 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ResourceForMaterialAtSiteVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        ItemId {
            public Object get(ResourceForMaterialAtSiteVORowImpl obj) {
                return obj.getItemId();
            }

            public void put(ResourceForMaterialAtSiteVORowImpl obj,
                            Object value) {
                obj.setItemId((BigDecimal)value);
            }
        },
        ResourceSubGroupId {
            public Object get(ResourceForMaterialAtSiteVORowImpl obj) {
                return obj.getResourceSubGroupId();
            }

            public void put(ResourceForMaterialAtSiteVORowImpl obj,
                            Object value) {
                obj.setResourceSubGroupId((BigDecimal)value);
            }
        },
        ResourceGroupId {
            public Object get(ResourceForMaterialAtSiteVORowImpl obj) {
                return obj.getResourceGroupId();
            }

            public void put(ResourceForMaterialAtSiteVORowImpl obj,
                            Object value) {
                obj.setResourceGroupId((BigDecimal)value);
            }
        },
        BuId {
            public Object get(ResourceForMaterialAtSiteVORowImpl obj) {
                return obj.getBuId();
            }

            public void put(ResourceForMaterialAtSiteVORowImpl obj,
                            Object value) {
                obj.setBuId((BigDecimal)value);
            }
        },
        ItemCode {
            public Object get(ResourceForMaterialAtSiteVORowImpl obj) {
                return obj.getItemCode();
            }

            public void put(ResourceForMaterialAtSiteVORowImpl obj,
                            Object value) {
                obj.setItemCode((String)value);
            }
        },
        ItemDescription {
            public Object get(ResourceForMaterialAtSiteVORowImpl obj) {
                return obj.getItemDescription();
            }

            public void put(ResourceForMaterialAtSiteVORowImpl obj,
                            Object value) {
                obj.setItemDescription((String)value);
            }
        },
        Uom {
            public Object get(ResourceForMaterialAtSiteVORowImpl obj) {
                return obj.getUom();
            }

            public void put(ResourceForMaterialAtSiteVORowImpl obj,
                            Object value) {
                obj.setUom((String)value);
            }
        },
        ItemRate {
            public Object get(ResourceForMaterialAtSiteVORowImpl obj) {
                return obj.getItemRate();
            }

            public void put(ResourceForMaterialAtSiteVORowImpl obj,
                            Object value) {
                obj.setItemRate((BigDecimal)value);
            }
        },
        ExpenditureType {
            public Object get(ResourceForMaterialAtSiteVORowImpl obj) {
                return obj.getExpenditureType();
            }

            public void put(ResourceForMaterialAtSiteVORowImpl obj,
                            Object value) {
                obj.setExpenditureType((String)value);
            }
        },
        StartDate {
            public Object get(ResourceForMaterialAtSiteVORowImpl obj) {
                return obj.getStartDate();
            }

            public void put(ResourceForMaterialAtSiteVORowImpl obj,
                            Object value) {
                obj.setStartDate((Date)value);
            }
        },
        EndDate {
            public Object get(ResourceForMaterialAtSiteVORowImpl obj) {
                return obj.getEndDate();
            }

            public void put(ResourceForMaterialAtSiteVORowImpl obj,
                            Object value) {
                obj.setEndDate((Date)value);
            }
        },
        ResourceSubGroup {
            public Object get(ResourceForMaterialAtSiteVORowImpl obj) {
                return obj.getResourceSubGroup();
            }

            public void put(ResourceForMaterialAtSiteVORowImpl obj,
                            Object value) {
                obj.setResourceSubGroup((String)value);
            }
        },
        ResourceGroup {
            public Object get(ResourceForMaterialAtSiteVORowImpl obj) {
                return obj.getResourceGroup();
            }

            public void put(ResourceForMaterialAtSiteVORowImpl obj,
                            Object value) {
                obj.setResourceGroup((String)value);
            }
        },
        ResourceType {
            public Object get(ResourceForMaterialAtSiteVORowImpl obj) {
                return obj.getResourceType();
            }

            public void put(ResourceForMaterialAtSiteVORowImpl obj,
                            Object value) {
                obj.setResourceType((String)value);
            }
        };
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public abstract Object get(ResourceForMaterialAtSiteVORowImpl object);

        public abstract void put(ResourceForMaterialAtSiteVORowImpl object,
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


    public static final int ITEMID = AttributesEnum.ItemId.index();
    public static final int RESOURCESUBGROUPID =
        AttributesEnum.ResourceSubGroupId.index();
    public static final int RESOURCEGROUPID =
        AttributesEnum.ResourceGroupId.index();
    public static final int BUID = AttributesEnum.BuId.index();
    public static final int ITEMCODE = AttributesEnum.ItemCode.index();
    public static final int ITEMDESCRIPTION =
        AttributesEnum.ItemDescription.index();
    public static final int UOM = AttributesEnum.Uom.index();
    public static final int ITEMRATE = AttributesEnum.ItemRate.index();
    public static final int EXPENDITURETYPE =
        AttributesEnum.ExpenditureType.index();
    public static final int STARTDATE = AttributesEnum.StartDate.index();
    public static final int ENDDATE = AttributesEnum.EndDate.index();
    public static final int RESOURCESUBGROUP =
        AttributesEnum.ResourceSubGroup.index();
    public static final int RESOURCEGROUP =
        AttributesEnum.ResourceGroup.index();
    public static final int RESOURCETYPE = AttributesEnum.ResourceType.index();

    /**
     * This is the default constructor (do not remove).
     */
    public ResourceForMaterialAtSiteVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute ItemId.
     * @return the ItemId
     */
    public BigDecimal getItemId() {
        return (BigDecimal)getAttributeInternal(ITEMID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ItemId.
     * @param value value to set the  ItemId
     */
    public void setItemId(BigDecimal value) {
        setAttributeInternal(ITEMID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ResourceSubGroupId.
     * @return the ResourceSubGroupId
     */
    public BigDecimal getResourceSubGroupId() {
        return (BigDecimal)getAttributeInternal(RESOURCESUBGROUPID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ResourceSubGroupId.
     * @param value value to set the  ResourceSubGroupId
     */
    public void setResourceSubGroupId(BigDecimal value) {
        setAttributeInternal(RESOURCESUBGROUPID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ResourceGroupId.
     * @return the ResourceGroupId
     */
    public BigDecimal getResourceGroupId() {
        return (BigDecimal)getAttributeInternal(RESOURCEGROUPID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ResourceGroupId.
     * @param value value to set the  ResourceGroupId
     */
    public void setResourceGroupId(BigDecimal value) {
        setAttributeInternal(RESOURCEGROUPID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute BuId.
     * @return the BuId
     */
    public BigDecimal getBuId() {
        return (BigDecimal)getAttributeInternal(BUID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute BuId.
     * @param value value to set the  BuId
     */
    public void setBuId(BigDecimal value) {
        setAttributeInternal(BUID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ItemCode.
     * @return the ItemCode
     */
    public String getItemCode() {
        return (String)getAttributeInternal(ITEMCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ItemCode.
     * @param value value to set the  ItemCode
     */
    public void setItemCode(String value) {
        setAttributeInternal(ITEMCODE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ItemDescription.
     * @return the ItemDescription
     */
    public String getItemDescription() {
        return (String)getAttributeInternal(ITEMDESCRIPTION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ItemDescription.
     * @param value value to set the  ItemDescription
     */
    public void setItemDescription(String value) {
        setAttributeInternal(ITEMDESCRIPTION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Uom.
     * @return the Uom
     */
    public String getUom() {
        return (String)getAttributeInternal(UOM);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Uom.
     * @param value value to set the  Uom
     */
    public void setUom(String value) {
        setAttributeInternal(UOM, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ItemRate.
     * @return the ItemRate
     */
    public BigDecimal getItemRate() {
        return (BigDecimal)getAttributeInternal(ITEMRATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ItemRate.
     * @param value value to set the  ItemRate
     */
    public void setItemRate(BigDecimal value) {
        setAttributeInternal(ITEMRATE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ExpenditureType.
     * @return the ExpenditureType
     */
    public String getExpenditureType() {
        return (String)getAttributeInternal(EXPENDITURETYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ExpenditureType.
     * @param value value to set the  ExpenditureType
     */
    public void setExpenditureType(String value) {
        setAttributeInternal(EXPENDITURETYPE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute StartDate.
     * @return the StartDate
     */
    public Date getStartDate() {
        return (Date)getAttributeInternal(STARTDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute StartDate.
     * @param value value to set the  StartDate
     */
    public void setStartDate(Date value) {
        setAttributeInternal(STARTDATE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute EndDate.
     * @return the EndDate
     */
    public Date getEndDate() {
        return (Date)getAttributeInternal(ENDDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute EndDate.
     * @param value value to set the  EndDate
     */
    public void setEndDate(Date value) {
        setAttributeInternal(ENDDATE, value);
    }


    /**
     * Gets the attribute value for the calculated attribute ResourceSubGroup.
     * @return the ResourceSubGroup
     */
    public String getResourceSubGroup() {
        return (String)getAttributeInternal(RESOURCESUBGROUP);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ResourceSubGroup.
     * @param value value to set the  ResourceSubGroup
     */
    public void setResourceSubGroup(String value) {
        setAttributeInternal(RESOURCESUBGROUP, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ResourceGroup.
     * @return the ResourceGroup
     */
    public String getResourceGroup() {
        return (String)getAttributeInternal(RESOURCEGROUP);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ResourceGroup.
     * @param value value to set the  ResourceGroup
     */
    public void setResourceGroup(String value) {
        setAttributeInternal(RESOURCEGROUP, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ResourceType.
     * @return the ResourceType
     */
    public String getResourceType() {
        return (String)getAttributeInternal(RESOURCETYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ResourceType.
     * @param value value to set the  ResourceType
     */
    public void setResourceType(String value) {
        setAttributeInternal(RESOURCETYPE, value);
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
