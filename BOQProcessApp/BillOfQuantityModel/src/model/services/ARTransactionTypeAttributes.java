package model.services;

public class ARTransactionTypeAttributes {
    
    private Long trxnTypeId;
    private String trxnTypeName;
    private String Desc;
    private String cmTrxName;
    
    public ARTransactionTypeAttributes() {
        super();
    }

    public void setTrxnTypeId(Long trxnTypeId) {
        this.trxnTypeId = trxnTypeId;
    }

    public Long getTrxnTypeId() {
        return trxnTypeId;
    }

    public void setTrxnTypeName(String trxnTypeName) {
        this.trxnTypeName = trxnTypeName;
    }

    public String getTrxnTypeName() {
        return trxnTypeName;
    }

    public void setDesc(String Desc) {
        this.Desc = Desc;
    }

    public String getDesc() {
        return Desc;
    }

    public void setCmTrxName(String cmTrxName) {
        this.cmTrxName = cmTrxName;
    }

    public String getCmTrxName() {
        return cmTrxName;
    }
}
