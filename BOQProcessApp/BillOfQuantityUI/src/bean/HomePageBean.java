package bean;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;

import oracle.jbo.Row;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class HomePageBean extends CommonBean {
    private RichPanelGroupLayout homePageBind;

    public HomePageBean() {
        super();
    }

    public void BOQBoxAL(ActionEvent actionEvent) {
        getCurrentPageFlowScope().put("switcher", "BOQ");
        getCurrentPageFlowScope().remove("DocType");
        Object object = executeMethod("homePageDoc");
        if (!"Success".equals(object)) {
            showPopupMessage(object == null ? "Error while getting BOQ lines" :
                             object.toString(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getHomePageBind());
    }

    public void setHomePageBind(RichPanelGroupLayout homePageBind) {
        this.homePageBind = homePageBind;
    }

    public RichPanelGroupLayout getHomePageBind() {
        return homePageBind;
    }

    public void WDBoxAL(ActionEvent actionEvent) {
        getCurrentPageFlowScope().put("switcher", "WD");
        getCurrentPageFlowScope().remove("DocType");
        Object object = executeMethod("homePageDoc");
        if (!"Success".equals(object)) {
            showPopupMessage(object == null ? "Error while getting WD lines" :
                             object.toString(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getHomePageBind());
    }

    public void InvBoxAL(ActionEvent actionEvent) {
        getCurrentPageFlowScope().put("switcher", "INV");
        getCurrentPageFlowScope().remove("DocType");
        Object object = executeMethod("homePageDoc");
        if (!"Success".equals(object)) {
            showPopupMessage(object == null ?
                             "Error while getting payment application lines" :
                             object.toString(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getHomePageBind());
    }

    public void CerBoxAL(ActionEvent actionEvent) {
        getCurrentPageFlowScope().put("switcher", "CER");
        getCurrentPageFlowScope().remove("DocType");
        Object object = executeMethod("homePageDoc");
        if (!"Success".equals(object)) {
            showPopupMessage(object == null ?
                             "Error while getting certified lines" :
                             object.toString(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getHomePageBind());
    }

    public void PCBoxAL(ActionEvent actionEvent) {
        getCurrentPageFlowScope().put("switcher", "PC");
        getCurrentPageFlowScope().remove("DocType");
        Object object = executeMethod("homePageDoc");
        if (!"Success".equals(object)) {
            showPopupMessage(object == null ?
                             "Error while getting payment certificate lines" :
                             object.toString(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getHomePageBind());
    }

    public void WDDocAL(ActionEvent actionEvent) {
        getCurrentPageFlowScope().put("switcher", "WD");
        Object obj = getEL("#{bindings.DocType1.inputValue}");
        getCurrentPageFlowScope().put("DocType", obj);
        Object object = executeMethod("homePageDoc");
        if (!"Success".equals(object)) {
            showPopupMessage(object == null ? "Error while getting WD lines" :
                             object.toString(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getHomePageBind());
    }

    public void BOQDocAL(ActionEvent actionEvent) {
        getCurrentPageFlowScope().put("switcher", "BOQ");
        Object object = executeMethod("homePageDoc");
        if (!"Success".equals(object)) {
            showPopupMessage(object == null ? "Error while getting BOQ lines" :
                             object.toString(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getHomePageBind());
    }

    public void BOQSelectionListener(SelectionEvent selectionEvent) {
        getCurrentPageFlowScope().put("switcher", "BOQ");
        invokeEL("#{bindings.AllPageCountVO11.collectionModel.makeCurrent}",
                 new Class[] { SelectionEvent.class },
                 new Object[] { selectionEvent });
        Row selectedRow =
            (Row)evaluateEL("#{bindings.AllPageCountVO1Iterator.currentRow}");
        getCurrentPageFlowScope().put("DocType",
                                      selectedRow.getAttribute("Meaning"));
        Object object = executeMethod("homePageDoc");
        if (!"Success".equals(object)) {
            showPopupMessage(object == null ? "Error while getting BOQ lines" :
                             object.toString(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getHomePageBind());
    }

    public void WDSelectionListener(SelectionEvent selectionEvent) {
        getCurrentPageFlowScope().put("switcher", "WD");
        invokeEL("#{bindings.AllPageCountVO21.collectionModel.makeCurrent}",
                 new Class[] { SelectionEvent.class },
                 new Object[] { selectionEvent });
        Row selectedRow =
            (Row)evaluateEL("#{bindings.AllPageCountVO2Iterator.currentRow}");
        getCurrentPageFlowScope().put("DocType",
                                      selectedRow.getAttribute("Meaning"));
        Object object = executeMethod("homePageDoc");
        if (!"Success".equals(object)) {
            showPopupMessage(object == null ? "Error while getting WD lines" :
                             object.toString(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getHomePageBind());
    }

    public void InvSelectionListener(SelectionEvent selectionEvent) {
        getCurrentPageFlowScope().put("switcher", "INV");
        //        invokeEL("#{bindings.AllPageCountVO3.collectionModel.makeCurrent}", new Class[]{SelectionEvent.class},new Object[]{selectionEvent});
        invokeEL("#{bindings.AllPageCountVO31.collectionModel.makeCurrent}",
                 new Class[] { SelectionEvent.class },
                 new Object[] { selectionEvent });
        Row selectedRow =
            (Row)evaluateEL("#{bindings.AllPageCountVO3Iterator.currentRow}");
        getCurrentPageFlowScope().put("DocType",
                                      selectedRow.getAttribute("Meaning"));
        Object object = executeMethod("homePageDoc");
        if (!"Success".equals(object)) {
            showPopupMessage(object == null ?
                             "Error while getting payment application lines" :
                             object.toString(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getHomePageBind());
    }

    public void CerSelectionListener(SelectionEvent selectionEvent) {
        getCurrentPageFlowScope().put("switcher", "CER");
        //        invokeEL("#{bindings.AllPageCountVO4.collectionModel.makeCurrent}", new Class[]{SelectionEvent.class},new Object[] {selectionEvent});
        invokeEL("#{bindings.AllPageCountVO41.collectionModel.makeCurrent}",
                 new Class[] { SelectionEvent.class },
                 new Object[] { selectionEvent });
        Row selectedRow =
            (Row)evaluateEL("#{bindings.AllPageCountVO4Iterator.currentRow}");
        getCurrentPageFlowScope().put("DocType",
                                      selectedRow.getAttribute("Meaning"));
        Object object = executeMethod("homePageDoc");
        if (!"Success".equals(object)) {
            showPopupMessage(object == null ?
                             "Error while getting certified lines" :
                             object.toString(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getHomePageBind());
    }

    public void PCSelectionListener(SelectionEvent selectionEvent) {
        getCurrentPageFlowScope().put("switcher", "PC");
        invokeEL("#{bindings.AllPageCountVO5.collectionModel.makeCurrent}",
                 new Class[] { SelectionEvent.class },
                 new Object[] { selectionEvent });
        Row selectedRow =
            (Row)evaluateEL("#{bindings.AllPageCountVO5Iterator.currentRow}");
        getCurrentPageFlowScope().put("DocType",
                                      selectedRow.getAttribute("Meaning"));
        Object object = executeMethod("homePageDoc");
        if (!"Success".equals(object)) {
            showPopupMessage(object == null ?
                             "Error while getting payment certificate lines" :
                             object.toString(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getHomePageBind());
    }
}
