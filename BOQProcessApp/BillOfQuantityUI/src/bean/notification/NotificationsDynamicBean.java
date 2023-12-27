package bean.notification;

import oracle.adf.controller.TaskFlowId;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;

public class NotificationsDynamicBean {
    private String notificationtaskFlowId;
    private RichRegion notificationDynRegBindgs;
    private RichPanelGroupLayout notificationPageBindgs;

    public NotificationsDynamicBean() {
        super();
    }

    public TaskFlowId getNotificarionTaskFlowId() {
        return TaskFlowId.parse(notificationtaskFlowId);
    }


    public void setNotificationDynRegBindgs(RichRegion notificationDynRegBindgs) {
        this.notificationDynRegBindgs = notificationDynRegBindgs;
    }

    public RichRegion getNotificationDynRegBindgs() {
        return notificationDynRegBindgs;
    }

    public void setNotificationPageBindgs(RichPanelGroupLayout notificationPageBindgs) {
        this.notificationPageBindgs = notificationPageBindgs;
    }

    public RichPanelGroupLayout getNotificationPageBindgs() {

        notificationtaskFlowId =
                "/WEB-INF/notification/Customer_Notification.xml#Customer_NotificationTF";
        return notificationPageBindgs;
    }


    public void setNotificationtaskFlowId(String notificationtaskFlowId) {
        this.notificationtaskFlowId = notificationtaskFlowId;
    }

    public String getNotificationtaskFlowId() {
        return notificationtaskFlowId;
    }
}
