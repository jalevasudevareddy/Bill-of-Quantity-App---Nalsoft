package bean;

import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.HashMap;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputComboboxListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelHeader;

public class BOQAccounntsSetupBean extends CommonBean {
    private RichPanelHeader pageBindings;
    private RichTable tableBindings;
    private java.util.Date minStartDate;
    private java.util.Date minEndDate;
    private RichInputComboboxListOfValues billCatgryBindgs;
    private RichInputDate endDateBindgs;
    private RichInputDate startDateBindgs;
    private RichInputText custTypeBindgs;
    private RichInputText arContBindgs;

    public BOQAccounntsSetupBean() {
        super();
    }

    public void setPageBindings(RichPanelHeader pageBindings) {
        this.pageBindings = pageBindings;
    }

    public RichPanelHeader getPageBindings() {
        executeMethod("crateTransAccountsRow");
        return pageBindings;
    }

    public void saveAcc(ActionEvent actionEvent) {
        try {

            save();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelAL(ActionEvent actionEvent) {
        try {

            cancel();
            closeCurrentActivity(actionEvent);
//            navigate(actionEvent, "done");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addAccount(ActionEvent actionEvent) {
        save();
        executeMethod("createAccountsSetupRow");
    }

    public void validateBillCategory(ValueChangeEvent valueChangeEvent) {
        try {
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            Object message = executeMethod("validateAccountingRow");
            if (!"Success".equals(message)) {
                showPopupMessage(message.toString(),
                                 FacesMessage.SEVERITY_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getBillCatgryBindgs());
        addPartialTrigger(getStartDateBindgs());
        addPartialTrigger(getEndDateBindgs());

    }

    public void setTableBindings(RichTable tableBindings) {
        this.tableBindings = tableBindings;
    }

    public RichTable getTableBindings() {
        return tableBindings;
    }

    public void destAL(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        try {
            Map<String, Object> attributesMap = new HashMap<String, Object>();

            attributesMap.put("CustomerType", null);
            attributesMap.put("ArContext", null);
            attributesMap.put("BillCategory", null);
            attributesMap.put("StartDate", null);
            attributesMap.put("EndDate", null);

            this.getCurrentPageFlowScope().put("attributesMap", attributesMap);

            Object exeMess = executeMethod("refreshBOQAccntSetupRow");
            if (!"Success".equals(exeMess)) {
                showPopupMessage(exeMess != null ? exeMess.toString() :
                                 "Unable to refresh the line.",
                                 FacesMessage.SEVERITY_ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        addPartialTrigger(getCustTypeBindgs());
        addPartialTrigger(getArContBindgs());
        addPartialTrigger(getBillCatgryBindgs());
        addPartialTrigger(getStartDateBindgs());
        addPartialTrigger(getEndDateBindgs());

        //        Object exemess = executeMethod("createGLAccountCombination");
        //        if (!"Sucecss".equals(exemess)) {
        //            showPopupMessage(exemess != null ? exemess.toString() :
        //                             "Error Raised while updating GL Account Details.  ",
        //                             FacesMessage.SEVERITY_ERROR);
        //        }
    }

    public void setMinStartDate(Date minStartDate) {
        this.minStartDate = minStartDate;
    }

    private Date getJavaDate(String dateStr) {
        Date jDate = null;
        try {
            SimpleDateFormat dateFrmtr = new SimpleDateFormat("");
            jDate = dateFrmtr.parse(dateStr);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jDate;
    }

    public Date getMinStartDate() {
        Object maxStartDateObj =
            getEL("#{bindings.TransMaxStartDate.inputValue}");
        if (maxStartDateObj != null) {
            minStartDate = getJavaDate(maxStartDateObj.toString());
        }
        return minStartDate;
    }

    public void setMinEndDate(Date minEndDate) {
        this.minEndDate = minEndDate;
    }

    private java.util.Date getDate(String strDate) {
        java.util.Date utilDate = null;
        try {
            if (strDate != null) {
                SimpleDateFormat dateFormater =
                    new SimpleDateFormat("yyyy-MM-dd");
                utilDate = dateFormater.parse(strDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return utilDate;
    }

    public Date getMinEndDate() {

        try {
            Object startDateObj = getStartDateBindgs().getValue();
            if (startDateObj != null) {
                String startDateStr = startDateObj.toString();
                minEndDate = getDate(startDateStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //        getStartDateBindgs().setValue(getStartDateBindgs().getValue());

        return minEndDate;
    }

    public void setBillCatgryBindgs(RichInputComboboxListOfValues billCatgryBindgs) {
        this.billCatgryBindgs = billCatgryBindgs;
    }

    public RichInputComboboxListOfValues getBillCatgryBindgs() {
        return billCatgryBindgs;
    }

    public void setEndDateBindgs(RichInputDate endDateBindgs) {
        this.endDateBindgs = endDateBindgs;
    }

    public RichInputDate getEndDateBindgs() {
        return endDateBindgs;
    }

    public void setStartDateBindgs(RichInputDate startDateBindgs) {
        this.startDateBindgs = startDateBindgs;
    }

    public RichInputDate getStartDateBindgs() {
        return startDateBindgs;
    }

    public void setCustTypeBindgs(RichInputText custTypeBindgs) {
        this.custTypeBindgs = custTypeBindgs;
    }

    public RichInputText getCustTypeBindgs() {
        return custTypeBindgs;
    }

    public void custTypeVL(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        try {
            Map<String, Object> attributesMap = new HashMap<String, Object>();

            attributesMap.put("ArContext", null);
            attributesMap.put("BillCategory", null);

            this.getCurrentPageFlowScope().put("attributesMap", attributesMap);

            Object exeMess = executeMethod("refreshBOQAccntSetupRow");
            if (!"Success".equals(exeMess)) {
                showPopupMessage(exeMess != null ? exeMess.toString() :
                                 "Unable to refresh the line.",
                                 FacesMessage.SEVERITY_ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

        addPartialTrigger(getArContBindgs());
        addPartialTrigger(getBillCatgryBindgs());
    }

    public void setArContBindgs(RichInputText arContBindgs) {
        this.arContBindgs = arContBindgs;
    }

    public RichInputText getArContBindgs() {
        return arContBindgs;
    }

    public void arCntxtVL(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        try {
            Map<String, Object> attributesMap = new HashMap<String, Object>();


            //            attributesMap.put("BillCategory", null);

            this.getCurrentPageFlowScope().put("attributesMap", attributesMap);

            Object exeMess = executeMethod("refreshBOQAccntSetupRow");
            if (!"Success".equals(exeMess)) {
                showPopupMessage(exeMess != null ? exeMess.toString() :
                                 "Unable to refresh the line.",
                                 FacesMessage.SEVERITY_ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
            showPopupMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }


        //        addPartialTrigger(getBillCatgryBindgs());
    }
}
