package model.searchview;

import java.math.BigDecimal;

import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Nov 01 10:44:28 IST 2018
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PCDocTypeCountsScrVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        FpbCount {
            public Object get(PCDocTypeCountsScrVORowImpl obj) {
                return obj.getFpbCount();
            }

            public void put(PCDocTypeCountsScrVORowImpl obj, Object value) {
                obj.setFpbCount((BigDecimal)value);
            }
        }
        ,
        FrrCount {
            public Object get(PCDocTypeCountsScrVORowImpl obj) {
                return obj.getFrrCount();
            }

            public void put(PCDocTypeCountsScrVORowImpl obj, Object value) {
                obj.setFrrCount((BigDecimal)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public abstract Object get(PCDocTypeCountsScrVORowImpl object);

        public abstract void put(PCDocTypeCountsScrVORowImpl object,
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

    public static final int FPBCOUNT = AttributesEnum.FpbCount.index();
    public static final int FRRCOUNT = AttributesEnum.FrrCount.index();

    /**
     * This is the default constructor (do not remove).
     */
    public PCDocTypeCountsScrVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute FpbCount.
     * @return the FpbCount
     */
    public BigDecimal getFpbCount() {
        return (BigDecimal)getAttributeInternal(FPBCOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FpbCount.
     * @param value value to set the  FpbCount
     */
    public void setFpbCount(BigDecimal value) {
        setAttributeInternal(FPBCOUNT, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FrrCount.
     * @return the FrrCount
     */
    public BigDecimal getFrrCount() {
        return (BigDecimal)getAttributeInternal(FRRCOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FrrCount.
     * @param value value to set the  FrrCount
     */
    public void setFrrCount(BigDecimal value) {
        setAttributeInternal(FRRCOUNT, value);
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
