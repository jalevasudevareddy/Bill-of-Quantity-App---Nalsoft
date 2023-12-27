package model.applicationmodule;

//import com.oracle.xmlns.apps.projects.foundation.projectdefinition.publicservice.maintainprojectv2.ObjectFactory;
//import com.oracle.xmlns.apps.projects.foundation.projectdefinition.publicservice.maintainprojectv2.ProjectParam;
//import com.oracle.xmlns.apps.projects.foundation.projectdefinition.publicservice.maintainprojectv2.ProjectTask;
//import com.oracle.xmlns.apps.projects.foundation.projectdefinition.publicservice.maintainprojectv2.custom.ProjectTaskServiceCustom;
//import com.oracle.xmlns.apps.projects.foundation.projectdefinition.publicservice.maintainprojectv2.types.UpdateFinancialTaskStructure;

import java.math.BigDecimal;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Set;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import model.searchview.BOQHeaderCreatedForProjectSearchVOImpl;
import model.searchview.BOQHeaderIdForProjectSearchVOImpl;

import model.searchview.CostingDetailsBOQLinePopulateVOImpl;

import model.view.BOQJobItemCostingVOImpl;


import model.view.TasksWSVOImpl;
import model.view.UnitRateAnalysisForProjectJobVOImpl;

import oracle.jbo.domain.Number;

import model.applicationmodule.common.CostingDetailsAM;

import model.searchview.BOQHeaderCreatedForProjectSearchVORowImpl;
import model.searchview.BOQHeaderIdForProjectSearchVORowImpl;
import model.searchview.CostingDetailsBOQLinePopulateVORowImpl;
import model.searchview.CostingDetailsCreatedForProjectSearchVOImpl;

import model.searchview.CostingDetailsCreatedForProjectSearchVORowImpl;

import model.searchview.ItemAnalysisExistsForProjectWithApprovedSearchVOImpl;
import model.searchview.ItemAnalysisExistsForProjectWithApprovedSearchVORowImpl;
import model.searchview.MaxProjectJobHeaderIdForProjectSearchVOImpl;
import model.searchview.MaxProjectJobHeaderIdForProjectSearchVORowImpl;
import model.searchview.SearchProjectExistInPrjJobHdrVOImpl;
import model.searchview.SearchProjectExistInPrjJobHdrVORowImpl;

import model.view.BOQCostingDetailsHeaderSearchVOImpl;
import model.view.BOQCostingDetailsHeaderVOImpl;
import model.view.BOQCostingDetailsHeaderVORowImpl;
import model.view.BOQJobItemCostingVORowImpl;
import model.view.ProjectJobHeaderVORowImpl;
import model.view.TasksWSVORowImpl;
import model.view.TransCostingDetailsHeaderVOImpl;

import model.view.TransCostingDetailsHeaderVORowImpl;
import model.view.TransJobTemplateVORowImpl;

import model.view.TransProjectJobHeaderVORowImpl;

import oracle.adf.share.ADFContext;

import oracle.jbo.JboException;
import oracle.jbo.Row;
import oracle.jbo.domain.Date;
import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewLinkImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Jan 24 14:35:17 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CostingDetailsAMImpl extends ApplicationModuleImpl implements CostingDetailsAM {
    private final String REVISE_PROJECT_COSTING_DETAILS_DOC =
        "begin xxboq_bill_of_quantity_pkg.revise_prj_costing_details_doc" +
        "(:p_cst_dtl_hdr_id,:p_user_name);end;";

    private final String UPDATE_PROJECT_COSTING_DETAILS_DOC =
        "begin xxboq_bill_of_quantity_pkg.update_prj_costing_details_doc" +
        "(:p_cst_dtl_hdr_id,:p_user_name,:p_exe_message);end;";

    /**
     * This is the default constructor (do not remove).
     */
    public CostingDetailsAMImpl() {
    }

    public void makeRevisedCostingDocAsCurrentRow(Number revisedCostingDocId) {
        if (revisedCostingDocId != null) {

            CommonUtilsAMImpl commAM = (CommonUtilsAMImpl)getCommonUtilsAM1();
            commAM.makeAsCurrentRow(getBOQCostingDetailsHeaderVO1(),
                                    revisedCostingDocId);

        }
    }

    public Number reviseCurrentPrjCostingDetailsDoc() {
        Number newCostingDetDocId = null;
        if (getBOQCostingDetailsHeaderVO1().getCurrentRow() != null) {
            BOQCostingDetailsHeaderVORowImpl costDetHdrRow =
                (BOQCostingDetailsHeaderVORowImpl)getBOQCostingDetailsHeaderVO1().getCurrentRow();
            CallableStatement st = null;
            try {
                st =
 getDBTransaction().createCallableStatement(REVISE_PROJECT_COSTING_DETAILS_DOC,
                                            0);
                st.setLong("p_cst_dtl_hdr_id",
                           costDetHdrRow.getCostingDetailsHeaderId().longValue());
                String userName =
                    ((String)((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("UserName"));
                st.setString("p_user_name", userName);
                st.registerOutParameter("p_cst_dtl_hdr_id", Types.NUMERIC);

                st.execute();
                Object newCostingDetDocIdObj = st.getLong("p_cst_dtl_hdr_id");
                if (newCostingDetDocIdObj != null) {
                    newCostingDetDocId =
                            new Number(newCostingDetDocIdObj.toString());
                }

            } catch (SQLException e) {
                e.printStackTrace();
                //                    return e.getLocalizedMessage();
                throw new JboException(e);
            } finally {
                if (st != null) {
                    try {
                        // 7. Close the statement
                        st.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return newCostingDetDocId;
    }

    public String updateCurrentPrjCostingDetailsDoc() {
        String exeMessage = null;
        if (getBOQCostingDetailsHeaderVO1().getCurrentRow() != null) {
            BOQCostingDetailsHeaderVORowImpl costDetHdrRow =
                (BOQCostingDetailsHeaderVORowImpl)getBOQCostingDetailsHeaderVO1().getCurrentRow();
            CallableStatement st = null;
            try {
                st =
 getDBTransaction().createCallableStatement(UPDATE_PROJECT_COSTING_DETAILS_DOC,
                                            0);
                st.setLong("p_cst_dtl_hdr_id",
                           costDetHdrRow.getCostingDetailsHeaderId().longValue());
                String userName =
                    ((String)((Map)ADFContext.getCurrent().getSessionScope().get("userMap")).get("UserName"));
                st.setString("p_user_name", userName);
                //                st.setString("p_exe_message", "");
                st.registerOutParameter("p_exe_message", Types.VARCHAR);

                st.execute();
                Object exeMessageObj = st.getString("p_exe_message");
                if (exeMessageObj != null) {
                    exeMessage = exeMessageObj.toString();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                //                    return e.getLocalizedMessage();
                throw new JboException(e);
            } finally {
                if (st != null) {
                    try {
                        // 7. Close the statement
                        st.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return exeMessage;
    }

    public Boolean isPrjCostingDetValidForFurtherProcess() {
        Boolean isValid = false;

        if (getBOQCostingDetailsHeaderVO1().getCurrentRow() != null) {

            BOQCostingDetailsHeaderVORowImpl costDetHdrRow =
                (BOQCostingDetailsHeaderVORowImpl)getBOQCostingDetailsHeaderVO1().getCurrentRow();
            Number maxPrjJobHdrId =
                getMaxPrjJobHdrIdForProject(costDetHdrRow.getProjectId().bigDecimalValue(),
                                            costDetHdrRow.getBusinessUnitId().bigDecimalValue());
            if (maxPrjJobHdrId == null ||
                maxPrjJobHdrId.doubleValue() == costDetHdrRow.getProjectJobHeaderId().doubleValue()) {
                isValid = true;
            }
        }
        return isValid;
    }

    public String updateCostingDetailsStatusWith(String newStatus) {
        String message = null;
        if (newStatus != null) {
            if (getBOQCostingDetailsHeaderVO1().getCurrentRow() != null) {
                BOQCostingDetailsHeaderVORowImpl costingDetHdrRow =
                    (BOQCostingDetailsHeaderVORowImpl)getBOQCostingDetailsHeaderVO1().getCurrentRow();
                costingDetHdrRow.setStatus(newStatus);
                message = "Success";
                if ("Approved".equals(newStatus)) {
                    CommonUtilsAMImpl commAm =
                        (CommonUtilsAMImpl)getCommonUtilsAM1();
                    costingDetHdrRow.setApprovedBy(commAm.getCurrentEmployeeUserName());
                    costingDetHdrRow.setApprovedDate(new java.sql.Date(System.currentTimeMillis()));
                }
            }
        }
        return message;
    }

    public Set marlAllPopulateBoqLinesAs(String markAs) {
        Set selectedBoqsList = new HashSet<Number>();
        try {
            CostingDetailsBOQLinePopulateVOImpl costingDetBoqLinePopltVO =
                getCostingDetailsBOQLinePopulateVO1();
            Row[] popLines = costingDetBoqLinePopltVO.getAllRowsInRange();
            for (Row popLine : popLines) {
                CostingDetailsBOQLinePopulateVORowImpl costingDetBoqLinePopLine =
                    (CostingDetailsBOQLinePopulateVORowImpl)popLine;
                costingDetBoqLinePopLine.setTransSelectFlag(markAs);
                if ("Y".equals(markAs)) {
                    selectedBoqsList.add(new Number(costingDetBoqLinePopLine.getLineId()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return selectedBoqsList;
    }

    public void refreshCostingDetailsHeaderVO() {
        getBOQCostingDetailsHeaderVO1().executeQuery();
    }

    public void refreshJobItemCostingsVO() {
        getBOQJobItemCostingVO1().executeQuery();
    }

    public String populateSelectedBoqLines(Set selectedBoqId) {
        String exeMessage = null;
        try {
            if (selectedBoqId != null) {
                Set<Number> selectedBoqIdSet = (Set<Number>)selectedBoqId;
                BOQJobItemCostingVOImpl jobCostingVO =
                    getBOQJobItemCostingVO1();
                for (Number boqLineId : selectedBoqIdSet) {
                    BOQJobItemCostingVORowImpl jobCostingRow =
                        (BOQJobItemCostingVORowImpl)jobCostingVO.createRow();
                    jobCostingRow.setBoqLineId(boqLineId);
                    jobCostingVO.insertRow(jobCostingRow);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exeMessage;
    }

    public void refreshBoqLinesPopulateVO() {
        if (getBOQCostingDetailsHeaderVO1().getCurrentRow() != null) {
            BOQCostingDetailsHeaderVORowImpl boqCostHdrRow =
                (BOQCostingDetailsHeaderVORowImpl)getBOQCostingDetailsHeaderVO1().getCurrentRow();
            CostingDetailsBOQLinePopulateVOImpl costingDetBoqLinePopltVO =
                getCostingDetailsBOQLinePopulateVO1();
            costingDetBoqLinePopltVO.setNamedWhereClauseParam("p_boq_hdr_id",
                                                              boqCostHdrRow.getBoqHeaderId());
            costingDetBoqLinePopltVO.setNamedWhereClauseParam("p_cost_det_Hdr_id",
                                                              boqCostHdrRow.getCostingDetailsHeaderId());
            costingDetBoqLinePopltVO.executeQuery();

        }

    }

    public Boolean makeAsCurrentCostingDetailsHdrForId(Number costingDetailHdrId) {
        if (costingDetailHdrId != null) {
            CommonUtilsAMImpl commAm = (CommonUtilsAMImpl)getCommonUtilsAM1();
            Boolean isCurrRowSetted =
                commAm.makeAsCurrentRow(getBOQCostingDetailsHeaderVO1(),
                                        costingDetailHdrId);
            if (isCurrRowSetted) {
                return true;
            }

        }
        return false;
    }

    public Number createCostingDetailsForSelectedProject() {
        Number costingDetailsId = null;

        try {
            if (getTransCostingDetailsHeaderVO1().getCurrentRow() != null) {
                TransCostingDetailsHeaderVORowImpl transRow =
                    (TransCostingDetailsHeaderVORowImpl)getTransCostingDetailsHeaderVO1().getCurrentRow();
                Number maxPrjItemAnyId =
                    getMaxPrjJobHdrIdForProject(transRow.getTransProjectId(),
                                                transRow.getTransBUId());
                Number boqHdrId =
                    getBOQHeaderIdForProject(transRow.getTransProjectId(),
                                             transRow.getTransBUId());

                BOQCostingDetailsHeaderVOImpl costingDetHdrVO =
                    getBOQCostingDetailsHeaderVO1();
                BOQCostingDetailsHeaderVORowImpl costingDetHdrRow =
                    (BOQCostingDetailsHeaderVORowImpl)costingDetHdrVO.createRow();
                costingDetHdrRow.setBusinessUnitId(new Number(transRow.getTransBUId()));
                costingDetHdrRow.setProjectId(new Number(transRow.getTransProjectId()));

                costingDetHdrRow.setProjectJobHeaderId(maxPrjItemAnyId);
                costingDetHdrRow.setBoqHeaderId(boqHdrId.bigDecimalValue());
                costingDetailsId =
                        costingDetHdrRow.getCostingDetailsHeaderId();
                costingDetHdrVO.insertRow(costingDetHdrRow);
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return costingDetailsId;
    }

    public Number getMaxPrjJobHdrIdForProject(BigDecimal prjId,
                                              BigDecimal buId) {
        Number maxPrjItemAnyId = null;
        try {
            MaxProjectJobHeaderIdForProjectSearchVOImpl maxPrjJobHdrIdSerVO =
                getMaxProjectJobHeaderIdForProjectSearchVO1();
            maxPrjJobHdrIdSerVO.setNamedWhereClauseParam("p_bu_id", buId);
            maxPrjJobHdrIdSerVO.setNamedWhereClauseParam("p_prj_id", prjId);
            maxPrjJobHdrIdSerVO.executeQuery();

            if (maxPrjJobHdrIdSerVO.first() != null) {
                MaxProjectJobHeaderIdForProjectSearchVORowImpl maxPrjJobHdrIdRow =
                    (MaxProjectJobHeaderIdForProjectSearchVORowImpl)maxPrjJobHdrIdSerVO.first();
                if (maxPrjJobHdrIdRow.getProjectJobHeaderId() != null) {
                    maxPrjItemAnyId =
                            new Number(maxPrjJobHdrIdRow.getProjectJobHeaderId());

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxPrjItemAnyId;
    }

    public Number getBOQHeaderIdForProject(BigDecimal prjId, BigDecimal buId) {
        Number boqHeaderId = null;
        try {
            BOQHeaderIdForProjectSearchVOImpl boqHdrIdSerVO =
                getBOQHeaderIdForProjectSearchVO1();
            boqHdrIdSerVO.setNamedWhereClauseParam("p_prj_id", prjId);
            boqHdrIdSerVO.setNamedWhereClauseParam("p_bu_id", buId);
            boqHdrIdSerVO.executeQuery();
            if (boqHdrIdSerVO.first() != null) {
                BOQHeaderIdForProjectSearchVORowImpl boqHdrIdSerRow =
                    (BOQHeaderIdForProjectSearchVORowImpl)boqHdrIdSerVO.first();
                boqHeaderId = new Number(boqHdrIdSerRow.getHeaderId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return boqHeaderId;
    }

    public Boolean checkWhetherCostingDetailsCreatedForSelectedPrj() {
        Boolean isExists = false;

        if (getTransCostingDetailsHeaderVO1().getCurrentRow() != null) {
            TransCostingDetailsHeaderVORowImpl transRow =
                (TransCostingDetailsHeaderVORowImpl)getTransCostingDetailsHeaderVO1().getCurrentRow();
            CostingDetailsCreatedForProjectSearchVOImpl costingDetSerVO =
                getCostingDetailsCreatedForProjectSearchVO1();
            costingDetSerVO.setNamedWhereClauseParam("p_bu_id",
                                                     transRow.getTransBUId());
            costingDetSerVO.setNamedWhereClauseParam("p_prj_id",
                                                     transRow.getTransProjectId());
            costingDetSerVO.executeQuery();

            if (costingDetSerVO.first() != null) {
                CostingDetailsCreatedForProjectSearchVORowImpl costingDetSerRow =
                    (CostingDetailsCreatedForProjectSearchVORowImpl)costingDetSerVO.first();
                if (costingDetSerRow.getRowCount().longValue() > 0) {
                    isExists = true;
                }
            }

        }
        return isExists;
    }

    public Boolean isJobItemAnalysisCreatedForProject() {
        Boolean isExist = false;
        if (getTransCostingDetailsHeaderVO1().getCurrentRow() != null) {
            TransCostingDetailsHeaderVORowImpl transRow =
                (TransCostingDetailsHeaderVORowImpl)getTransCostingDetailsHeaderVO1().getCurrentRow();
            ItemAnalysisExistsForProjectWithApprovedSearchVOImpl searchVO =
                getItemAnalysisExistsForProjectWithApprovedSearchVO1();
            searchVO.setNamedWhereClauseParam("p_bu_id",
                                              transRow.getTransBUId());
            searchVO.setNamedWhereClauseParam("p_prj_id",
                                              transRow.getTransProjectId());
            searchVO.executeQuery();
            searchVO.setRangeSize(-1);
            if (searchVO.first() != null) {
                ItemAnalysisExistsForProjectWithApprovedSearchVORowImpl searchRow =
                    (ItemAnalysisExistsForProjectWithApprovedSearchVORowImpl)searchVO.first();
                if (searchRow.getRowCount().doubleValue() > 0) {
                    isExist = true;
                }
            }
        }
        return isExist;
    }

    public Boolean isBOQHeaderCreatedForProject() {
        Boolean isExist = false;
        if (getTransCostingDetailsHeaderVO1().getCurrentRow() != null) {
            TransCostingDetailsHeaderVORowImpl transRow =
                (TransCostingDetailsHeaderVORowImpl)getTransCostingDetailsHeaderVO1().getCurrentRow();
            BOQHeaderCreatedForProjectSearchVOImpl searchVO =
                getBOQHeaderCreatedForProjectSearchVO1();
            searchVO.setNamedWhereClauseParam("p_bu_id",
                                              transRow.getTransBUId());
            searchVO.setNamedWhereClauseParam("p_prj_id",
                                              transRow.getTransProjectId());
            searchVO.executeQuery();
            searchVO.setRangeSize(-1);
            if (searchVO.first() != null) {
                BOQHeaderCreatedForProjectSearchVORowImpl searchRow =
                    (BOQHeaderCreatedForProjectSearchVORowImpl)searchVO.first();
                if (searchRow.getRowCount().doubleValue() > 0) {
                    isExist = true;
                }
            }
        }
        return isExist;
    }

    public void initTransCostingDetailsaHeaderVO() {
        getTransCostingDetailsHeaderVO1().insertRow(getTransCostingDetailsHeaderVO1().createRow());
    }

    /**
     * Container's getter for BOQCostingDetailsHeaderSearchVO1.
     * @return BOQCostingDetailsHeaderSearchVO1
     */
    public BOQCostingDetailsHeaderSearchVOImpl getBOQCostingDetailsHeaderSearchVO1() {
        return (BOQCostingDetailsHeaderSearchVOImpl)findViewObject("BOQCostingDetailsHeaderSearchVO1");
    }

    /**
     * Container's getter for TransCostingDetailsHeaderVO1.
     * @return TransCostingDetailsHeaderVO1
     */
    public TransCostingDetailsHeaderVOImpl getTransCostingDetailsHeaderVO1() {
        return (TransCostingDetailsHeaderVOImpl)findViewObject("TransCostingDetailsHeaderVO1");
    }

    public void refreshTransCostingDetailsVOWith(Map attributeMap) {
        if (attributeMap != null &&
            getTransCostingDetailsHeaderVO1().getCurrentRow() != null) {
            Map<String, Object> attrMap = (Map<String, Object>)attributeMap;
            TransCostingDetailsHeaderVORowImpl transTempRow =
                (TransCostingDetailsHeaderVORowImpl)getTransCostingDetailsHeaderVO1().getCurrentRow();
            for (Entry<String, Object> entry : attrMap.entrySet()) {
                transTempRow.setAttribute(entry.getKey(), entry.getValue());

            }
        }
    }

    /**
     * Container's getter for CostingDetailsCreatedForProjectSearchVO1.
     * @return CostingDetailsCreatedForProjectSearchVO1
     */
    public CostingDetailsCreatedForProjectSearchVOImpl getCostingDetailsCreatedForProjectSearchVO1() {
        return (CostingDetailsCreatedForProjectSearchVOImpl)findViewObject("CostingDetailsCreatedForProjectSearchVO1");
    }

    /**
     * Container's getter for SearchProjectExistInPrjJobHdrVO1.
     * @return SearchProjectExistInPrjJobHdrVO1
     */
    public SearchProjectExistInPrjJobHdrVOImpl getSearchProjectExistInPrjJobHdrVO1() {
        return (SearchProjectExistInPrjJobHdrVOImpl)findViewObject("SearchProjectExistInPrjJobHdrVO1");
    }

    /**
     * Container's getter for ItemAnalysisExistsForProjectWithApprovedSearchVO1.
     * @return ItemAnalysisExistsForProjectWithApprovedSearchVO1
     */
    public ItemAnalysisExistsForProjectWithApprovedSearchVOImpl getItemAnalysisExistsForProjectWithApprovedSearchVO1() {
        return (ItemAnalysisExistsForProjectWithApprovedSearchVOImpl)findViewObject("ItemAnalysisExistsForProjectWithApprovedSearchVO1");
    }

    /**
     * Container's getter for MaxProjectJobHeaderIdForProjectSearchVO1.
     * @return MaxProjectJobHeaderIdForProjectSearchVO1
     */
    public MaxProjectJobHeaderIdForProjectSearchVOImpl getMaxProjectJobHeaderIdForProjectSearchVO1() {
        return (MaxProjectJobHeaderIdForProjectSearchVOImpl)findViewObject("MaxProjectJobHeaderIdForProjectSearchVO1");
    }

    /**
     * Container's getter for BOQCostingDetailsHeaderVO1.
     * @return BOQCostingDetailsHeaderVO1
     */
    public BOQCostingDetailsHeaderVOImpl getBOQCostingDetailsHeaderVO1() {
        return (BOQCostingDetailsHeaderVOImpl)findViewObject("BOQCostingDetailsHeaderVO1");
    }

    /**
     * Container's getter for BOQHeaderIdForProjectSearchVO1.
     * @return BOQHeaderIdForProjectSearchVO1
     */
    public BOQHeaderIdForProjectSearchVOImpl getBOQHeaderIdForProjectSearchVO1() {
        return (BOQHeaderIdForProjectSearchVOImpl)findViewObject("BOQHeaderIdForProjectSearchVO1");
    }

    /**
     * Container's getter for BOQHeaderCreatedForProjectSearchVO1.
     * @return BOQHeaderCreatedForProjectSearchVO1
     */
    public BOQHeaderCreatedForProjectSearchVOImpl getBOQHeaderCreatedForProjectSearchVO1() {
        return (BOQHeaderCreatedForProjectSearchVOImpl)findViewObject("BOQHeaderCreatedForProjectSearchVO1");
    }

    /**
     * Container's getter for BOQJobItemCostingVO1.
     * @return BOQJobItemCostingVO1
     */
    public BOQJobItemCostingVOImpl getBOQJobItemCostingVO1() {
        return (BOQJobItemCostingVOImpl)findViewObject("BOQJobItemCostingVO1");
    }

    /**
     * Container's getter for BOQCostingDetailsHeaderToJobItemCostingVL1.
     * @return BOQCostingDetailsHeaderToJobItemCostingVL1
     */
    public ViewLinkImpl getBOQCostingDetailsHeaderToJobItemCostingVL1() {
        return (ViewLinkImpl)findViewLink("BOQCostingDetailsHeaderToJobItemCostingVL1");
    }

    /**
     * Container's getter for CommonUtilsAM1.
     * @return CommonUtilsAM1
     */
    public ApplicationModuleImpl getCommonUtilsAM1() {
        return (ApplicationModuleImpl)findApplicationModule("CommonUtilsAM1");
    }

    /**
     * Container's getter for CostingDetailsBOQLinePopulateVO1.
     * @return CostingDetailsBOQLinePopulateVO1
     */
    public CostingDetailsBOQLinePopulateVOImpl getCostingDetailsBOQLinePopulateVO1() {
        return (CostingDetailsBOQLinePopulateVOImpl)findViewObject("CostingDetailsBOQLinePopulateVO1");
    }

    /**
     * Container's getter for UnitRateAnalysisForProjectJobVO1.
     * @return UnitRateAnalysisForProjectJobVO1
     */
    public UnitRateAnalysisForProjectJobVOImpl getUnitRateAnalysisForProjectJobVO1() {
        return (UnitRateAnalysisForProjectJobVOImpl)findViewObject("UnitRateAnalysisForProjectJobVO1");
    }

    /**
     * Container's getter for BOQJobItemCostingToUnitRateAnalysisForProjectJobVL1.
     * @return BOQJobItemCostingToUnitRateAnalysisForProjectJobVL1
     */
    public ViewLinkImpl getBOQJobItemCostingToUnitRateAnalysisForProjectJobVL1() {
        return (ViewLinkImpl)findViewLink("BOQJobItemCostingToUnitRateAnalysisForProjectJobVL1");
    }

    /**
     * Container's getter for TasksWSVO1.
     * @return TasksWSVO1
     */
    public TasksWSVOImpl getTasksWSVO1() {
        return (TasksWSVOImpl)findViewObject("TasksWSVO1");
    }

    public String interfaceTasks() {
        String message = "";
        //        BOQCostingDetailsHeaderVORowImpl costDetHdrRow =
        //            (BOQCostingDetailsHeaderVORowImpl)getBOQCostingDetailsHeaderVO1().getCurrentRow();
        //        if (costDetHdrRow != null) {
        //            getTasksWSVO1().applyViewCriteria(null);
        //            getTasksWSVO1().setRangeSize(-1);
        //            getTasksWSVO1().executeQuery();
        //            getTasksWSVO1().setNamedWhereClauseParam("p_hdr_id",
        //                                                     costDetHdrRow.getCostingDetailsHeaderId().longValue());
        //            getTasksWSVO1().executeQuery();
        //            Row[] taskRows = getTasksWSVO1().getAllRowsInRange();
        //            UpdateFinancialTaskStructure taskStr =
        //                new UpdateFinancialTaskStructure();
        //            ObjectFactory objectFactory = new ObjectFactory();
        //            //            List<ProjectTask> tasks = new ArrayList<ProjectTask>();
        //
        //            int i = 0;
        //            for (Row row : taskRows) {
        //                TasksWSVORowImpl taskRow = (TasksWSVORowImpl)row;
        //                if (i == 0) {
        //                    ProjectParam projectParam = new ProjectParam();
        //                    projectParam.setProjectName(objectFactory.createProjectParamProjectName(taskRow.getProjectNumber()));
        //                    projectParam.setGenerateWBSDataFlag(objectFactory.createProjectParamGenerateWBSDataFlag(true));
        //                    projectParam.setProjectKey(objectFactory.createProjectParamProjectKey(taskRow.getProjectNumber()));
        //                    projectParam.setReplaceFlag(objectFactory.createProjectParamReplaceFlag(true));
        //                    taskStr.setProject(projectParam);
        //                }
        //                ProjectTask task = new ProjectTask();
        //                task.setProjectName(objectFactory.createProjectTaskProjectName(taskRow.getProjectNumber()));
        //                task.setTaskName(objectFactory.createProjectTaskTaskName(taskRow.getActivity()));
        //                task.setTaskNumber(objectFactory.createProjectTaskTaskNumber(taskRow.getDescription() !=
        //                                                                             null ?
        //                                                                             taskRow.getDescription() :
        //                                                                             taskRow.getActivity()));
        //
        //                task.setPmTaskSourceReference(objectFactory.createProjectTaskPmTaskSourceReference(taskRow.getDescription() !=
        //                                                                                                   null ?
        //                                                                                                   taskRow.getDescription() :
        //                                                                                                   taskRow.getActivity()));
        //                Date startDate=new Date(taskRow.getStartDate());
        //                startDate.addJulianDays(1, 0);
        //                Date endDate=new Date(taskRow.getEndDate());
        //                endDate.addJulianDays(1, 0);
        //                task.setDnDisplaySequence(objectFactory.createProjectTaskDnDisplaySequence(new Long(i)));
        //                task.setPlanningStartDate(objectFactory.createProjectTaskPlanningStartDate(getXmlCalender(new oracle.jbo.domain.Date(startDate))));
        //                task.setPlanningEndDate(objectFactory.createProjectTaskPlanningEndDate(getXmlCalender(new oracle.jbo.domain.Date(endDate))));
        //                task.setStartDate(objectFactory.createProjectTaskStartDate(getXmlCalender(new oracle.jbo.domain.Date(startDate))));
        //                task.setCompletionDate(objectFactory.createProjectTaskCompletionDate(getXmlCalender(new oracle.jbo.domain.Date(endDate))));
        //                task.setBillableFlag(objectFactory.createProjectTaskBillableFlag(true));
        //                task.setChargeableFlag(objectFactory.createProjectTaskChargeableFlag(true));
        //                taskStr.getTasks().add(task);
        //                i++;
        //            }
        //            ProjectTaskServiceCustom taskCustom =
        //                new ProjectTaskServiceCustom();
        //            message = taskCustom.updateTaskStructure(taskStr);
        //        }
        return message;
    }

    public XMLGregorianCalendar getXmlCalender(oracle.jbo.domain.Date date) {
        oracle.jbo.domain.Date oracleDate = date;
        XMLGregorianCalendar xmlDate = null;
        try {

            java.util.Date jDate = oracleDate.getValue();
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTimeInMillis(jDate.getTime());
            DatatypeFactory df = DatatypeFactory.newInstance();
            xmlDate = df.newXMLGregorianCalendar(gc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xmlDate;
    }
}
