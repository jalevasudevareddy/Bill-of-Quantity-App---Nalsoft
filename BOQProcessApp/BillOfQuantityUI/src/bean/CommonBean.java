package bean;

import com.sun.faces.component.visit.FullVisitContext;

import java.io.IOException;
import java.io.OutputStream;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.FacesEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.DataControlFrame;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;

import oracle.jbo.ViewObject;

import oracle.jbo.server.ViewRowImpl;

import oracle.ui.pattern.dynamicShell.TabContext;

import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.UploadedFile;

public class CommonBean {
    public CommonBean() {
        super();
    }

    public void fileUploaded(ValueChangeEvent valueChangeEvent) {
        UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();

        valueChangeEvent.getNewValue();

    }


    public Object executeMethod(String methodName) {
        BindingContainer bindingContainer =
            BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding operationBinding =
            bindingContainer.getOperationBinding(methodName);
        Object object = operationBinding.execute();
        return object;
    }

    public Object executeMethod(String methodName, Map paramsMap) {
        BindingContainer bindingContainer =
            BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding operationBinding =
            bindingContainer.getOperationBinding(methodName);
        Map parameters = operationBinding.getParamsMap();
        parameters.putAll(paramsMap);
        Object object = operationBinding.execute();
        return object;
    }

    public void showPopup(RichPopup popup) {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popup.show(hints);
    }


    public void showPopupMessage(String message,
                                 FacesMessage.Severity messageType) {
        FacesMessage faceMessage = new FacesMessage(message);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        faceMessage.setSeverity(messageType);
        facesContext.addMessage("", faceMessage);
    }

    public void save() {
        BindingContext bc = BindingContext.getCurrent();
        String dcfName = bc.getCurrentDataControlFrame();
        DataControlFrame dcf = bc.findDataControlFrame(dcfName);
        dcf.commit();
    }

    public void cancel() {
        BindingContext bc = BindingContext.getCurrent();
        String dcfName = bc.getCurrentDataControlFrame();
        DataControlFrame dcf = bc.findDataControlFrame(dcfName);
        dcf.rollback();
    }


    public Map getCurrentPageFlowScope() {
        Map pageFlowScope =
            AdfFacesContext.getCurrentInstance().getPageFlowScope();
        return pageFlowScope;
    }

    public void editDocument(ActionEvent ae) {
        BindingContainer bindings =
            BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding methodbind =
            bindings.getOperationBinding("editDocument");
        //        methodbind.execute();
        navigate(ae, "edit");
    }


    public void navigate(FacesEvent event, String outcome) {
        RichRegion regionComponent = null;
        for (UIComponent uic = event.getComponent().getParent(); uic != null;
             uic = uic.getParent()) {
            if (uic instanceof RichRegion) {
                regionComponent = (RichRegion)uic;
                break;
            }
        }
        if (regionComponent != null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            ExpressionFactory ef = fc.getApplication().getExpressionFactory();
            ELContext elc = fc.getELContext();
            MethodExpression me =
                ef.createMethodExpression(elc, outcome, String.class,
                                          new Class[] { });
            regionComponent.queueActionEventInRegion(me, null, null, false, -1,
                                                     -1, event.getPhaseId());
        }
    }

    public void setEL(String expression, Object newValue) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application app = facesContext.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        ValueExpression valueExp =
            elFactory.createValueExpression(elContext, expression,
                                            Object.class);
        Class bindClass = valueExp.getType(elContext);
        if (bindClass.isPrimitive() || bindClass.isInstance(newValue)) {
            valueExp.setValue(elContext, newValue);
        }
    }

    public void addPartialTrigger(UIComponent uiComponent) {
        AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
        adfFacesContext.addPartialTarget(uiComponent);
    }

    public void processUpdates(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
    }

    public Object getEL(String expression) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application app = facesContext.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        ValueExpression valueExp =
            elFactory.createValueExpression(elContext, expression,
                                            Object.class);
        return valueExp.getValue(elContext);
    }


    public void deleteRows(RowKeySet rowKeySet,
                           String iterator) throws Exception {
        RowKeySet selectedRowsSet = rowKeySet;
        Iterator selectedEmpIter = selectedRowsSet.iterator();
        DCBindingContainer dcBindingContainer =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcIter =
            dcBindingContainer.findIteratorBinding(iterator);
        RowSetIterator empRSIter = dcIter.getRowSetIterator();
        while (selectedEmpIter.hasNext()) {
            Key key = (Key)((List)selectedEmpIter.next()).get(0);
            Row currentRow = empRSIter.getRow(key);
            if (currentRow != null) {
                currentRow.remove();
            }
        }
    }

    public String updateColumn(String iterator, String attrName,
                               String attrValue) {
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcIteratorBindings =
            bindings.findIteratorBinding(iterator);
        ViewObject viewObject = dcIteratorBindings.getViewObject();
        ViewRowImpl row = (ViewRowImpl)viewObject.first();
        while (row != null) {
            row.setAttribute(attrName, attrValue);
            if (viewObject.hasNext()) {
                row = (ViewRowImpl)viewObject.next();
            } else {
                row = null;
            }
        }
        viewObject.executeQuery();
        return null;
    }

    public void refreshPage() {
        FacesContext fc = FacesContext.getCurrentInstance();
        String refreshpage = fc.getViewRoot().getViewId();
        ViewHandler ViewH = fc.getApplication().getViewHandler();
        UIViewRoot UIV = ViewH.createView(fc, refreshpage);
        UIV.setViewId(refreshpage);
        fc.setViewRoot(UIV);
    }

    public static Object invokeEL(String el, Class[] paramTypes,
                                  Object[] params) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory =
            facesContext.getApplication().getExpressionFactory();
        MethodExpression exp =
            expressionFactory.createMethodExpression(elContext, el,
                                                     Object.class, paramTypes);
        return exp.invoke(elContext, params);
    }

    public static Object evaluateEL(String el) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory =
            facesContext.getApplication().getExpressionFactory();
        ValueExpression exp =
            expressionFactory.createValueExpression(elContext, el,
                                                    Object.class);
        return exp.getValue(elContext);
    }
    
    public void closeCurrentActivity(ActionEvent actionEvent) {
        TabContext tabContext = TabContext.getCurrentInstance();
        int tabIndex = tabContext.getSelectedTabIndex();
        if (tabIndex != -1) {
            tabContext.removeTab(tabIndex);
        }
    }
    
    public UIComponent findComponent(final String id) {
        FacesContext context = FacesContext.getCurrentInstance(); 
        UIViewRoot root = context.getViewRoot();
        final UIComponent[] found = new UIComponent[1];
        root.visitTree(new FullVisitContext(context), new VisitCallback() {     
            @Override
            public VisitResult visit(VisitContext context, UIComponent component) {
                if (component != null 
                    && id.equals(component.getId())) {
                    found[0] = component;
                    return VisitResult.COMPLETE;
                }
                return VisitResult.ACCEPT;              
            }
        });
        return found[0];
    }
}
