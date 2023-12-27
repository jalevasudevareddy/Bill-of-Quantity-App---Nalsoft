package model.services;

public class ARTransactionSourceAttributes {
    
    private Long batchId;
    private Long batchSourceId;
    private String batchName;
    
    public ARTransactionSourceAttributes() {
        super();
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchSourceId(Long batchSourceId) {
        this.batchSourceId = batchSourceId;
    }

    public Long getBatchSourceId() {
        return batchSourceId;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getBatchName() {
        return batchName;
    }
}
