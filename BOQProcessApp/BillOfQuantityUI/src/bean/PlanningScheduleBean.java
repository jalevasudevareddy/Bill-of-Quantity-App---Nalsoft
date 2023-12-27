package bean;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.layout.RichPanelHeader;
import oracle.adf.view.rich.event.DialogEvent;

public class PlanningScheduleBean extends CommonBean {
    private RichTable activityTableBindings;
    private RichTable jobTableBindings;
    private RichPanelHeader pageBindings;
    private RichTable activityLineTable;
    private RichTable jobLineTable;

    public PlanningScheduleBean() {
        super();
    }


    public void actHistAL(ActionEvent actionEvent) {
        Object qacHdrId = getEL("#{bindings.HeaderId.inputValue}");
        getCurrentPageFlowScope().put("docId", qacHdrId);
        getCurrentPageFlowScope().put("docTypeId", "11");
        navigate(actionEvent, "ActionHistory");
    }

    public void SavePS(ActionEvent actionEvent) {
        save();
    }

    public void CancelPS(ActionEvent actionEvent) {
        cancel();
    }

    public void SubmitPS(ActionEvent actionEvent) {
        save();
        submitDocForApproval();
        save();
        addPartialTrigger(getPageBindings());
    }

    public void submitDocForApproval() {
        executeMethod("callpkgForAMEProcessSubmit");
    }

    public void populateActivityLines(ActionEvent actionEvent) {
        save();
        executeMethod("populateActivityLines");
    }

    public void StructureCodeChanged(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        executeMethod("filterLinesUsingStructure");
        addPartialTrigger(getActivityTableBindings());
        addPartialTrigger(getJobTableBindings());
        addPartialTrigger(getActivityLineTable());
        addPartialTrigger(getJobLineTable());
    }

    public void setActivityTableBindings(RichTable activityTableBindings) {
        this.activityTableBindings = activityTableBindings;
    }

    public RichTable getActivityTableBindings() {
        return activityTableBindings;
    }

    public void setJobTableBindings(RichTable jobTableBindings) {
        this.jobTableBindings = jobTableBindings;
    }

    public RichTable getJobTableBindings() {
        return jobTableBindings;
    }

    public void RevisePS(DialogEvent dialogEvent) {
        executeMethod("revisePlanningSchedule");
        addPartialTrigger(getPageBindings());
    }

    public void setPageBindings(RichPanelHeader pageBindings) {
        this.pageBindings = pageBindings;
    }

    public RichPanelHeader getPageBindings() {
        return pageBindings;
    }

    public void splitBOQLine(DialogEvent dialogEvent) {
        if (DialogEvent.Outcome.ok == dialogEvent.getOutcome()) {
            executeMethod("splitActivityBOQLine");
            save();
            //            addPartialTrigger(getActivityTableBindings());
            //            addPartialTrigger(getJobTableBindings());
            addPartialTrigger(getPageBindings());
        }
    }

    public void setActivityLineTable(RichTable activityLineTable) {
        this.activityLineTable = activityLineTable;
    }

    public RichTable getActivityLineTable() {
        return activityLineTable;
    }

    public void setJobLineTable(RichTable jobLineTable) {
        this.jobLineTable = jobLineTable;
    }

    public RichTable getJobLineTable() {
        return jobLineTable;
    }

    public void approve(ActionEvent actionEvent) {
        setEL("#{bindings.Status.inputValue}", "Approved");
        save();
        addPartialTrigger(getPageBindings());
    }

    public void interfaceBudget(ActionEvent actionEvent) {
        executeMethod("interfaceBudget");
        save();
    }
}
