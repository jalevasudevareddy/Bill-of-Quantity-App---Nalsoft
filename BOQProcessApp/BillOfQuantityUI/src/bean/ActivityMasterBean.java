package bean;

import javax.faces.event.ActionEvent;

public class ActivityMasterBean extends CommonBean {
    public ActivityMasterBean() {
        super();
    }

    public void CanelActivity(ActionEvent actionEvent) {
        cancel();
    }

    public void SaveActivity(ActionEvent actionEvent) {
        save();
    }

    public void CreateActivityLine(ActionEvent actionEvent) {
        executeMethod("CreateActivityLine");
    }
}
