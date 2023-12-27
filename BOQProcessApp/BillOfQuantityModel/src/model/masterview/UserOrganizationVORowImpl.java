package model.masterview;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Oct 10 11:49:33 IST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class UserOrganizationVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        BuAccessId {
            public Object get(UserOrganizationVORowImpl obj) {
                return obj.getBuAccessId();
            }

            public void put(UserOrganizationVORowImpl obj, Object value) {
                obj.setBuAccessId((Number)value);
            }
        },
        OrganizationId {
            public Object get(UserOrganizationVORowImpl obj) {
                return obj.getOrganizationId();
            }

            public void put(UserOrganizationVORowImpl obj, Object value) {
                obj.setOrganizationId((Number)value);
            }
        },
        OrganizationCode {
            public Object get(UserOrganizationVORowImpl obj) {
                return obj.getOrganizationCode();
            }

            public void put(UserOrganizationVORowImpl obj, Object value) {
                obj.setOrganizationCode((String)value);
            }
        },
        OrganizationId1 {
            public Object get(UserOrganizationVORowImpl obj) {
                return obj.getOrganizationId1();
            }

            public void put(UserOrganizationVORowImpl obj, Object value) {
                obj.setOrganizationId1((Number)value);
            }
        },
        OrganizationName {
            public Object get(UserOrganizationVORowImpl obj) {
                return obj.getOrganizationName();
            }

            public void put(UserOrganizationVORowImpl obj, Object value) {
                obj.setOrganizationName((String)value);
            }
        };
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public abstract Object get(UserOrganizationVORowImpl object);

        public abstract void put(UserOrganizationVORowImpl object,
                                 Object value);

        public int index() {
            return UserOrganizationVORowImpl.AttributesEnum.firstIndex() +
                ordinal();
        }

        public static final int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return UserOrganizationVORowImpl.AttributesEnum.firstIndex() +
                UserOrganizationVORowImpl.AttributesEnum.staticValues().length;
        }

        public static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = UserOrganizationVORowImpl.AttributesEnum.values();
            }
            return vals;
        }
    }


    public static final int BUACCESSID = AttributesEnum.BuAccessId.index();
    public static final int ORGANIZATIONID =
        AttributesEnum.OrganizationId.index();
    public static final int ORGANIZATIONCODE =
        AttributesEnum.OrganizationCode.index();
    public static final int ORGANIZATIONID1 =
        AttributesEnum.OrganizationId1.index();
    public static final int ORGANIZATIONNAME =
        AttributesEnum.OrganizationName.index();

    /**
     * This is the default constructor (do not remove).
     */
    public UserOrganizationVORowImpl() {
    }

    /**
     * Gets XxinvUserOrganizationsEO entity object.
     * @return the XxinvUserOrganizationsEO
     */
    public EntityImpl getXxinvUserOrganizationsEO() {
        return (EntityImpl)getEntity(0);
    }

    /**
     * Gets XxinvOrganizationsEO entity object.
     * @return the XxinvOrganizationsEO
     */
    public EntityImpl getXxinvOrganizationsEO() {
        return (EntityImpl)getEntity(1);
    }

    /**
     * Gets the attribute value for BU_ACCESS_ID using the alias name BuAccessId.
     * @return the BU_ACCESS_ID
     */
    public Number getBuAccessId() {
        return (Number)getAttributeInternal(BUACCESSID);
    }

    /**
     * Sets <code>value</code> as attribute value for BU_ACCESS_ID using the alias name BuAccessId.
     * @param value value to set the BU_ACCESS_ID
     */
    public void setBuAccessId(Number value) {
        setAttributeInternal(BUACCESSID, value);
    }

    /**
     * Gets the attribute value for ORGANIZATION_ID using the alias name OrganizationId.
     * @return the ORGANIZATION_ID
     */
    public Number getOrganizationId() {
        return (Number)getAttributeInternal(ORGANIZATIONID);
    }

    /**
     * Sets <code>value</code> as attribute value for ORGANIZATION_ID using the alias name OrganizationId.
     * @param value value to set the ORGANIZATION_ID
     */
    public void setOrganizationId(Number value) {
        setAttributeInternal(ORGANIZATIONID, value);
    }

    /**
     * Gets the attribute value for ORGANIZATION_CODE using the alias name OrganizationCode.
     * @return the ORGANIZATION_CODE
     */
    public String getOrganizationCode() {
        return (String)getAttributeInternal(ORGANIZATIONCODE);
    }

    /**
     * Sets <code>value</code> as attribute value for ORGANIZATION_CODE using the alias name OrganizationCode.
     * @param value value to set the ORGANIZATION_CODE
     */
    public void setOrganizationCode(String value) {
        setAttributeInternal(ORGANIZATIONCODE, value);
    }

    /**
     * Gets the attribute value for ORGANIZATION_ID using the alias name OrganizationId1.
     * @return the ORGANIZATION_ID
     */
    public Number getOrganizationId1() {
        return (Number)getAttributeInternal(ORGANIZATIONID1);
    }

    /**
     * Sets <code>value</code> as attribute value for ORGANIZATION_ID using the alias name OrganizationId1.
     * @param value value to set the ORGANIZATION_ID
     */
    public void setOrganizationId1(Number value) {
        setAttributeInternal(ORGANIZATIONID1, value);
    }

    /**
     * Gets the attribute value for ORGANIZATION_NAME using the alias name OrganizationName.
     * @return the ORGANIZATION_NAME
     */
    public String getOrganizationName() {
        return (String)getAttributeInternal(ORGANIZATIONNAME);
    }

    /**
     * Sets <code>value</code> as attribute value for ORGANIZATION_NAME using the alias name OrganizationName.
     * @param value value to set the ORGANIZATION_NAME
     */
    public void setOrganizationName(String value) {
        setAttributeInternal(ORGANIZATIONNAME, value);
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
