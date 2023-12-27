package model.services;

import com.oracle.xmlns.oxp.service.publicreportservice.ArrayOfParamNameValue;
import com.oracle.xmlns.oxp.service.publicreportservice.ArrayOfString;
import com.oracle.xmlns.oxp.service.publicreportservice.ExternalReportWSSService;
import com.oracle.xmlns.oxp.service.publicreportservice.ExternalReportWSSService_Service;

import com.oracle.xmlns.oxp.service.publicreportservice.ParamNameValue;
import com.oracle.xmlns.oxp.service.publicreportservice.ReportRequest;

import com.oracle.xmlns.oxp.service.publicreportservice.ReportResponse;
import com.oracle.xmlns.oxp.service.publicreportservice.RunReport;
import com.oracle.xmlns.oxp.service.publicreportservice.RunReportResponse;

import com.sun.xml.ws.developer.WSBindingProvider;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceRef;

import oracle.adf.share.ADFContext;

import oracle.wsm.security.util.SecurityConstants;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


import weblogic.wsee.jws.jaxws.owsm.SecurityPoliciesFeature;
import weblogic.wsee.jws.jaxws.owsm.SecurityPolicyFeature;

public class ExternalReportCustom{
    @WebServiceRef
    private static ExternalReportWSSService_Service externalReportWSSService_Service;
    ExternalReportWSSService externalReportWSSService;
    Map sessMap = ADFContext.getCurrent().getSessionScope();

    public ExternalReportCustom() {

        super();

        externalReportWSSService_Service =
                new ExternalReportWSSService_Service();
        SecurityPoliciesFeature securityFeatures =
            new SecurityPoliciesFeature(new String[] { "oracle/wss_username_token_over_ssl_client_policy" });
        externalReportWSSService =
                externalReportWSSService_Service.getExternalReportWSSService(securityFeatures);
        // Add your code to call the desired methods.
        WSBindingProvider wsbp = (WSBindingProvider)externalReportWSSService;

        Map<String, Object> requestContext = wsbp.getRequestContext();

        String serviceEndpoint =
            "https://fa-evjm-test-saasfaprod1.fa.ocs.oraclecloud.com/xmlpserver/services/ExternalReportWSSService?WSDL";
        String serviceUsername = "IntegrationUser";
        String servicePassword = "Nals@AST@123";
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                           serviceEndpoint);
//                        requestContext.put(WSBindingProvider.USERNAME_PROPERTY,
//                                           serviceUsername);
//                        requestContext.put(WSBindingProvider.PASSWORD_PROPERTY,
//                                           servicePassword);
        requestContext.put(SecurityConstants.ClientConstants.WSS_CSF_KEY,
                           "IntegrationUser");

    }

  public List getProjectRevenueAmounts(Long projectId) throws Exception {
        List<Map> dataList = new ArrayList<Map>();

        //        if (!isInitialised()) {
        //
        //            throw new Exception("External Service not initialized");
        //        }
        if (projectId != null) {
            ReportRequest repreq = new ReportRequest();
            repreq.setReportAbsolutePath("/Custom/PAAS/Master Reports/Finance/Receivables/Project Revenue Amount Report.xdo");

            repreq.setSizeOfDataChunkDownload(-1);
            repreq.setFlattenXML(false);

            ArrayOfParamNameValue arr = new ArrayOfParamNameValue();
            ParamNameValue param = new ParamNameValue();
            param.setName("p_prj_id");
            ArrayOfString ars = new ArrayOfString();
            ars.getItem().add(projectId.toString());
            param.setValues(ars);
            param.setDataType("Number");
            arr.getItem().add(param);
            repreq.setParameterNameValues(arr);


            RunReport runrep = new RunReport();
            runrep.setReportRequest(repreq);
//            RunReportResponse represp = null;
            ReportResponse represp = null;           
            try {
                represp = externalReportWSSService.runReport(repreq, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            ReportResponse res = represp.getRunReportReturn();
//            byte[] baReport = represp.getReportBytes();
            byte[] baReport = represp.getReportBytes();
            String str = new String(baReport);

            //                try {
            //
            //                    FileOutputStream fio =
            //                        new FileOutputStream("D:/artrxntype.txt");
            //                    fio.write(baReport);
            //                    fio.close();
            //                } catch (Exception ex) {
            //                    ex.printStackTrace();
            //                }


            //        ArrayList<ARTransactionTypeAttributes> an =
            //            new ArrayList<ARTransactionTypeAttributes>();


            try {
                ByteArrayInputStream fis = new ByteArrayInputStream(baReport);
                DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(fis);
                NodeList n1 = document.getElementsByTagName("P_PRJ_ID");
                NodeList n2 = document.getElementsByTagName("COMPLETION_DATE");
                NodeList n3 = document.getElementsByTagName("REVENUE_AMT");


                for (int i = 0; i < n2.getLength(); i++) {
                    Map dataMap = new HashMap<String, Object>();


                    if (n2.item(i) != null &&
                        !("".equals(n2.item(i).getTextContent()))) {
                        dataMap.put("COMPLETION_DATE",
                                    n2.item(i).getTextContent());
                    }
                    if (n3.item(i) != null &&
                        !("".equals(n3.item(i).getTextContent()))) {

                        dataMap.put("REVENUE_AMT",
                                    n3.item(i).getTextContent());
                    }

                    dataList.add(dataMap);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return dataList;

    }

    public byte[] generatePDFReport(String reportPath, String format,
                                    ByteArrayOutputStream data) throws Exception {
        byte[] baReport = new byte[0];
        try {
            //            if (!isInitialised()) {
            //                throw new Exception("External Service not initialized");
            //            }
            ReportRequest repreq = new ReportRequest();
            //            repreq.setReportAbsolutePath("/Custom/PAAS/Work Done Details Report.xdo");
            repreq.setReportAbsolutePath(reportPath);
            repreq.setSizeOfDataChunkDownload(-1);

            repreq.setReportData(data.toByteArray());
            repreq.setAttributeFormat(format);
            repreq.setFlattenXML(false);
            RunReport runrep = new RunReport();
            runrep.setReportRequest(repreq);
            ReportResponse represp = null;
            represp = externalReportWSSService.runReport(repreq, null);
//            represp = externalReportWSSService.runReport(repreq, null);
//            RunReportResponse represp = null;
//            represp = externalReportWSSService.runReport(runrep);

//            ReportResponse res = represp.getRunReportReturn();
            baReport = represp.getReportBytes();


            //                    FileOutputStream fio = new FileOutputStream("D:/CollectionReportData.xml");
            //                    fio.write(baReport);
            //                    fio.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return baReport;
    }

    public byte[] findAndExecuteReportForData(String url,
                                              Map<String, Map<String, String>> map) throws Exception {

        byte[] baReport = null;
        //        if (!isInitialised()) {
        //            throw new Exception("External Service not initialized");
        //        }

        ReportRequest repreq = new ReportRequest();
        repreq.setReportAbsolutePath(url);
        repreq.setSizeOfDataChunkDownload(-1);
        repreq.setFlattenXML(false);
        ArrayOfParamNameValue arr = new ArrayOfParamNameValue();
        if (map != null && map.size() > 0) {
            for (Entry<String, Map<String, String>> entry : map.entrySet()) {

                ParamNameValue param = new ParamNameValue();
                param.setName(entry.getKey());
                Map<String, String> valueMap = entry.getValue();
                if (valueMap != null && valueMap.size() > 0) {
                    for (Entry<String, String> valueEntry :
                         valueMap.entrySet()) {
                        ArrayOfString ars = new ArrayOfString();
                        ars.getItem().add(valueEntry.getKey());
                        param.setValues(ars);
                        param.setDataType(valueEntry.getValue());
                        arr.getItem().add(param);
                    }
                }
            }
            repreq.setParameterNameValues(arr);
        }
        RunReport runrep = new RunReport();
        runrep.setReportRequest(repreq);
        ReportResponse represp = null;                   
//        RunReportResponse represp = null;
        try {

            represp = externalReportWSSService.runReport(repreq, null);
        } catch (Exception e) {
            System.out.println(e);
        }
        FileOutputStream fos = null;
//        ReportResponse res = represp.getRunReportReturn();
        baReport = represp.getReportBytes();
        try {

            FileOutputStream fio =
                new FileOutputStream("D:/ReportxmlData.xml");
            fio.write(baReport);
            fio.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return baReport;
    }

    public List<ARTransactionTypeAttributes> findARTransactionTypeReport(Long orgId) throws Exception {
        ArrayList<ARTransactionTypeAttributes> an =
            new ArrayList<ARTransactionTypeAttributes>();
        Map<String, String> keys =
            (Map<String, String>)sessMap.get("SWSARTrxnTypeKeys");
        if (keys == null || (!(keys.containsValue(orgId.toString())))) {

            ADFContext.getCurrent().getSessionScope().remove("SWSARTrxnTypeData");
            //            if (!isInitialised()) {
            //
            //                throw new Exception("External Service not initialized");
            //            }
            if (orgId != null) {
                ReportRequest repreq = new ReportRequest();
                repreq.setReportAbsolutePath("/Custom/PAAS/Master Reports/Finance/Receivables/AR Transaction Types Listing Report.xdo");

                repreq.setSizeOfDataChunkDownload(-1);
                repreq.setFlattenXML(false);

                ArrayOfParamNameValue arr = new ArrayOfParamNameValue();
                ParamNameValue param = new ParamNameValue();
                param.setName("P_ORG_ID");
                ArrayOfString ars = new ArrayOfString();
                ars.getItem().add(orgId.toString());
                param.setValues(ars);
                param.setDataType("Number");
                arr.getItem().add(param);
                repreq.setParameterNameValues(arr);


                RunReport runrep = new RunReport();
                runrep.setReportRequest(repreq);
                ReportResponse represp=null;
//                RunReportResponse represp = null;
                try {
                    represp = externalReportWSSService.runReport(repreq,null);
                } catch (Exception e) {
                    e.printStackTrace();
                }


//                ReportResponse res = represp.getRunReportReturn();
                byte[] baReport = represp.getReportBytes();


                //                try {
                //
                //                    FileOutputStream fio =
                //                        new FileOutputStream("D:/artrxntype.txt");
                //                    fio.write(baReport);
                //                    fio.close();
                //                } catch (Exception ex) {
                //                    ex.printStackTrace();
                //                }


                //        ArrayList<ARTransactionTypeAttributes> an =
                //            new ArrayList<ARTransactionTypeAttributes>();


                try {
                    ByteArrayInputStream fis =
                        new ByteArrayInputStream(baReport);
                    DocumentBuilderFactory factory =
                        DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document document = builder.parse(fis);
                    NodeList n1 = document.getElementsByTagName("TRX_TYPE_ID");
                    NodeList n2 = document.getElementsByTagName("TRX_NAME");
                    NodeList n3 = document.getElementsByTagName("TRX_DESC");
                    NodeList n4 = document.getElementsByTagName("CM_TRX_NAME");


                    for (int i = 0; i < n1.getLength(); i++) {
                        ARTransactionTypeAttributes arrxtypeattr =
                            new ARTransactionTypeAttributes();

                        if (n1.item(i) != null &&
                            !("".equals(n1.item(i).getTextContent()))) {
                            arrxtypeattr.setTrxnTypeId(Long.parseLong(n1.item(i).getTextContent()));
                        }
                        if (n2.item(i) != null &&
                            !("".equals(n2.item(i).getTextContent()))) {
                            arrxtypeattr.setTrxnTypeName(n2.item(i).getTextContent());
                        }
                        if (n3.item(i) != null &&
                            !("".equals(n3.item(i).getTextContent()))) {
                            arrxtypeattr.setDesc(n3.item(i).getTextContent());
                        }
                        if (n4.item(i) != null &&
                            !("".equals(n4.item(i).getTextContent()))) {
                            arrxtypeattr.setCmTrxName(n4.item(i).getTextContent());
                        }

                        an.add(arrxtypeattr);
                    }
                    Map<String, String> newKeys =
                        new HashMap<String, String>();
                    newKeys.put("OrgId", orgId.toString());
                    sessMap.put("SWSARTrxnTypeKeys", newKeys);
                    sessMap.put("SWSARTrxnTypeData", an);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return (List<ARTransactionTypeAttributes>)ADFContext.getCurrent().getSessionScope().get("SWSARTrxnTypeData");
    }

    public List<ARTransactionSourceAttributes> findARTransactionSourceReport(Long orgId) throws Exception {
        ArrayList<ARTransactionSourceAttributes> an =
            new ArrayList<ARTransactionSourceAttributes>();
        Map<String, String> keys =
            (Map<String, String>)sessMap.get("SWSARTrxnSourceKeys");
        if (keys == null || (!(keys.containsValue(orgId.toString())))) {
            ADFContext.getCurrent().getSessionScope().remove("SWSARTrxnSourceData");

            if (orgId != null) {
                ReportRequest repreq = new ReportRequest();
                repreq.setReportAbsolutePath("/Custom/PAAS/Master Reports/Finance/Receivables/AR Batch Source Name Listing Report.xdo");

                repreq.setSizeOfDataChunkDownload(-1);
                repreq.setFlattenXML(false);

                ArrayOfParamNameValue arr = new ArrayOfParamNameValue();
                ParamNameValue param = new ParamNameValue();
                param.setName("P_ORG_ID");
                ArrayOfString ars = new ArrayOfString();
                ars.getItem().add(orgId.toString());
                param.setValues(ars);
                param.setDataType("Number");
                arr.getItem().add(param);
                repreq.setParameterNameValues(arr);


                RunReport runrep = new RunReport();
                runrep.setReportRequest(repreq);
                ReportResponse represp = null;
                
                try {
                    represp = externalReportWSSService.runReport(repreq,null);
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                ReportResponse res = represp.getRunReportReturn();
                byte[] baReport = represp.getReportBytes();

                //                try {
                //
                //                    FileOutputStream fio =
                //                        new FileOutputStream("D:/trxsource.txt");
                //                    fio.write(baReport);
                //                    fio.close();
                //                } catch (Exception ex) {
                //                    ex.printStackTrace();
                //                }

                try {
                    ByteArrayInputStream fis =
                        new ByteArrayInputStream(baReport);
                    DocumentBuilderFactory factory =
                        DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document document = builder.parse(fis);

                    NodeList n1 = document.getElementsByTagName("BTCH_ID");
                    NodeList n2 = document.getElementsByTagName("BTCH_SRC_ID");
                    NodeList n3 = document.getElementsByTagName("BTCH_NAME");


                    for (int i = 0; i < n1.getLength(); i++) {
                        ARTransactionSourceAttributes artrxsourceeattr =
                            new ARTransactionSourceAttributes();

                        if (n1.item(i) != null &&
                            !("".equals(n1.item(i).getTextContent()))) {
                            artrxsourceeattr.setBatchId(Long.parseLong(n1.item(i).getTextContent()));
                        }
                        if (n2.item(i) != null &&
                            !("".equals(n2.item(i).getTextContent()))) {
                            artrxsourceeattr.setBatchSourceId(Long.parseLong(n2.item(i).getTextContent()));
                        }
                        if (n3.item(i) != null &&
                            !("".equals(n3.item(i).getTextContent()))) {
                            artrxsourceeattr.setBatchName(n3.item(i).getTextContent());
                        }

                        an.add(artrxsourceeattr);
                    }

                    Map<String, String> newKeys =
                        new HashMap<String, String>();
                    newKeys.put("OrgId", orgId.toString());
                    sessMap.put("SWSARTrxnSourceKeys", newKeys);
                    sessMap.put("SWSARTrxnSourceData", an);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return (List<ARTransactionSourceAttributes>)ADFContext.getCurrent().getSessionScope().get("SWSARTrxnSourceData");

    }

    public List<NaturalAccountsReport> findNaturalAccountsReport(Long orgId) throws Exception {

        //        if (!isInitialised()) {
        //            //initializeJwt();
        //            throw new Exception("External Service not initialized");
        //        }
        Map<String, String> keys =
            (Map<String, String>)ADFContext.getCurrent().getSessionScope().get("SSNARKeys");
        if (orgId != null &&
            (keys == null || (!(keys.containsValue(orgId.toString()))))) {
            ADFContext.getCurrent().getSessionScope().remove("SSNARData");

            ReportRequest repreq = new ReportRequest();
            repreq.setReportAbsolutePath("/Custom/Supply Chain Management/PAAS/Master Reports/FINANCE/Natural Accounts Listing Report.xdo");

            repreq.setSizeOfDataChunkDownload(-1);
            repreq.setFlattenXML(false);

            ArrayOfParamNameValue arr = new ArrayOfParamNameValue();
            ParamNameValue param = new ParamNameValue();
            param.setName("P_ORG_ID");
            ArrayOfString ars = new ArrayOfString();
            ars.getItem().add(orgId.toString());
            param.setValues(ars);
            param.setDataType("Number");
            arr.getItem().add(param);
            repreq.setParameterNameValues(arr);

            RunReport runrep = new RunReport();
            runrep.setReportRequest(repreq);
            RunReportResponse represp = null;
            try {
//                represp = externalReportWSSService.runReport(runrep);
            } catch (Exception e) {
                e.printStackTrace();
            }


            ReportResponse res = represp.getRunReportReturn();
            byte[] baReport = res.getReportBytes();

            //            try {
            //                FileOutputStream fio =
            //                    new FileOutputStream("D:/naturalAcc.txt");
            //                fio.write(baReport);
            //                fio.close();
            //            } catch (Exception ex) {
            //                ex.printStackTrace();
            //            }


            ArrayList<NaturalAccountsReport> an =
                new ArrayList<NaturalAccountsReport>();


            try {
                ByteArrayInputStream fis = new ByteArrayInputStream(baReport);
                DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(fis);

                NodeList n1 = document.getElementsByTagName("ACCT");
                NodeList n2 = document.getElementsByTagName("DES");


                for (int i = 0; i < n1.getLength(); i++) {
                    NaturalAccountsReport naattr = new NaturalAccountsReport();

                    if (n1.item(i) != null &&
                        !("".equals(n1.item(i).getTextContent()))) {
                        naattr.setAcct(n1.item(i).getTextContent());
                    }
                    if (n2.item(i) != null &&
                        !("".equals(n2.item(i).getTextContent()))) {
                        naattr.setDes(n2.item(i).getTextContent());
                    }

                    an.add(naattr);
                }

                Map<String, String> newKeys = new HashMap<String, String>();
                newKeys.put("OrgId", orgId.toString());
                ADFContext.getCurrent().getSessionScope().put("SSNARKeys",
                                                              newKeys);
                ADFContext.getCurrent().getSessionScope().put("SSNARData", an);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (List<NaturalAccountsReport>)ADFContext.getCurrent().getSessionScope().get("SSNARData");

    }

    public List<GLAccountsListing> findSupplierGLAccountsListReport(String AcctComb,
                                                                    Long OrgId) throws Exception {

        //        if (!isInitialised()) {
        //            // initializeJwt();
        //            //            throw new Exception("External Service not initialized");
        //        }
        Map<String, String> keys =
            (Map<String, String>)ADFContext.getCurrent().getSessionScope().get("SSGLAKeys");
        if (keys == null || (!(keys.containsValue(OrgId.toString()))) ||
            (!(keys.containsValue(AcctComb != null ? AcctComb.toString() :
                                  null)))) {
            ADFContext.getCurrent().getSessionScope().remove("SSGLAData");

            ReportRequest repreq = new ReportRequest();
            repreq.setReportAbsolutePath("/Custom/Supply Chain Management/PAAS/Master Reports/FINANCE/Payables/GL Accounts Listing Report.xdo");

            repreq.setSizeOfDataChunkDownload(-1);
            repreq.setFlattenXML(false);

            ArrayOfParamNameValue arr = new ArrayOfParamNameValue();
            //            ParamNameValue param = new ParamNameValue();
            //            param.setName("P_ACCT_COMB");
            //            ArrayOfString ars = new ArrayOfString();
            //            ars.getItem().add(AcctComb!=null?AcctComb.toString():null);
            //            param.setValues(ars);
            //            param.setDataType("String");
            //            arr.getItem().add(param);

            ParamNameValue param2 = new ParamNameValue();
            param2.setName("P_ORG_ID");
            ArrayOfString ars2 = new ArrayOfString();
            ars2.getItem().add(OrgId.toString());
            param2.setDataType("Number");
            param2.setValues(ars2);
            arr.getItem().add(param2);

            repreq.setParameterNameValues(arr);

            RunReport runrep = new RunReport();
            runrep.setReportRequest(repreq);
            RunReportResponse represp = null;
            try {
//                represp = externalReportWSSService.runReport(runrep);
            } catch (Exception e) {
                e.printStackTrace();
            }


            ReportResponse res = represp.getRunReportReturn();
            byte[] baReport = res.getReportBytes();


            //            try {
            //
            //                FileOutputStream fio = new FileOutputStream("D:/glacc.txt");
            //                fio.write(baReport);
            //                fio.close();
            //            } catch (Exception ex) {
            //                ex.printStackTrace();
            //            }

            ArrayList<GLAccountsListing> an =
                new ArrayList<GLAccountsListing>();

            try {
                ByteArrayInputStream fis = new ByteArrayInputStream(baReport);
                DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(fis);

                NodeList n1 = document.getElementsByTagName("GL_ACNT");
                NodeList n2 =
                    document.getElementsByTagName("GL_ACCT_DESCRIPTION");
                NodeList n3 =
                    document.getElementsByTagName("CODE_COMBINATION_ID");

                for (int i = 0; i < n1.getLength(); i++) {

                    GLAccountsListing acclist = new GLAccountsListing();
                    if (n1.item(i) != null &&
                        !("".equals(n1.item(i).getTextContent()))) {
                        acclist.setGlAcnt(n1.item(i).getTextContent());
                    }

                    if (n2.item(i) != null &&
                        !("".equals(n2.item(i).getTextContent()))) {
                        acclist.setGlAcctDescription((n2.item(i).getTextContent()));
                    }
                    if (n3.item(i) != null &&
                        !("".equals(n3.item(i).getTextContent()))) {
                        acclist.setCodeCombinationId(Long.parseLong(n3.item(i).getTextContent()));
                    }
                    an.add(acclist);
                }

                Map<String, String> newKeys = new HashMap<String, String>();
                newKeys.put("OrgId", OrgId.toString());
                newKeys.put("AccComb",
                            (AcctComb != null ? AcctComb.toString() : null));
                ADFContext.getCurrent().getSessionScope().put("SSGLAKeys",
                                                              newKeys);
                ADFContext.getCurrent().getSessionScope().put("SSGLAData", an);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return (List<GLAccountsListing>)ADFContext.getCurrent().getSessionScope().get("SSGLAData");
    }

    public List<GLAccountsListing> findGLAccountsListReport(String AcctComb,
                                                            Long OrgId) throws Exception {

        //        if (!isInitialised()) {
        //            // initializeJwt();
        //            //            throw new Exception("External Service not initialized");
        //        }
        Map<String, String> keys =
            (Map<String, String>)ADFContext.getCurrent().getSessionScope().get("SSGLAccKeys");
        if (keys == null || (!(keys.containsValue(OrgId.toString()))) ||
            (!(keys.containsValue(AcctComb != null ? AcctComb.toString() :
                                  null)))) {
            ADFContext.getCurrent().getSessionScope().remove("SSGLAccData");

            ReportRequest repreq = new ReportRequest();
            repreq.setReportAbsolutePath("/Custom/Financials/Payables/PAAS/GL Account Listing Report.xdo");

            repreq.setSizeOfDataChunkDownload(-1);
            repreq.setFlattenXML(false);
            ArrayOfParamNameValue arr = new ArrayOfParamNameValue();
            if (AcctComb != null) {
                ParamNameValue param = new ParamNameValue();
                param.setName("P_ACCT_COMB");
                ArrayOfString ars = new ArrayOfString();
                ars.getItem().add(AcctComb != null ? AcctComb.toString() :
                                  null);
                param.setValues(ars);
                param.setDataType("String");
                arr.getItem().add(param);
            }
            ParamNameValue param2 = new ParamNameValue();
            param2.setName("P_ORG_ID");
            ArrayOfString ars2 = new ArrayOfString();
            ars2.getItem().add(OrgId.toString());
            param2.setDataType("Number");
            param2.setValues(ars2);
            arr.getItem().add(param2);

            repreq.setParameterNameValues(arr);

            RunReport runrep = new RunReport();
            runrep.setReportRequest(repreq);
            RunReportResponse represp = null;
            try {
//                represp = externalReportWSSService.runReport(runrep);
            } catch (Exception e) {
                e.printStackTrace();
            }


            ReportResponse res = represp.getRunReportReturn();
            byte[] baReport = res.getReportBytes();


            //            try {
            //
            //                FileOutputStream fio = new FileOutputStream("D:/glacc.txt");
            //                fio.write(baReport);
            //                fio.close();
            //            } catch (Exception ex) {
            //                ex.printStackTrace();
            //            }

            ArrayList<GLAccountsListing> an =
                new ArrayList<GLAccountsListing>();

            try {
                ByteArrayInputStream fis = new ByteArrayInputStream(baReport);
                DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(fis);

                NodeList n1 = document.getElementsByTagName("GL_ACNT");
                NodeList n2 =
                    document.getElementsByTagName("GL_ACCT_DESCRIPTION");
                NodeList n3 =
                    document.getElementsByTagName("CODE_COMBINATION_ID");
                NodeList n4 = document.getElementsByTagName("SEGMENT1");
                NodeList n5 = document.getElementsByTagName("SEGMENT2");
                NodeList n6 = document.getElementsByTagName("SEGMENT3");
                NodeList n7 = document.getElementsByTagName("SEGMENT4");
                NodeList n8 = document.getElementsByTagName("SEGMENT5");
                NodeList n9 = document.getElementsByTagName("SEGMENT6");
                NodeList n10 = document.getElementsByTagName("SEGMENT7");
                NodeList n11 = document.getElementsByTagName("SEGMENT8");

                for (int i = 0; i < n1.getLength(); i++) {

                    GLAccountsListing acclist = new GLAccountsListing();
                    if (n1.item(i) != null &&
                        !("".equals(n1.item(i).getTextContent()))) {
                        acclist.setGlAcnt(n1.item(i).getTextContent());
                    }

                    if (n2.item(i) != null &&
                        !("".equals(n2.item(i).getTextContent()))) {
                        acclist.setGlAcctDescription((n2.item(i).getTextContent()));
                    }
                    if (n3.item(i) != null &&
                        !("".equals(n3.item(i).getTextContent()))) {
                        acclist.setCodeCombinationId(Long.parseLong(n3.item(i).getTextContent()));
                    }
                    if (n4.item(i) != null &&
                        !("".equals(n4.item(i).getTextContent()))) {
                        acclist.setSegment1(n4.item(i).getTextContent());
                    }

                    if (n5.item(i) != null &&
                        !("".equals(n5.item(i).getTextContent()))) {
                        acclist.setSegment2((n5.item(i).getTextContent()));
                    }
                    if (n6.item(i) != null &&
                        !("".equals(n6.item(i).getTextContent()))) {
                        acclist.setSegment3((n6.item(i).getTextContent()));
                    }
                    if (n7.item(i) != null &&
                        !("".equals(n7.item(i).getTextContent()))) {
                        acclist.setSegment4((n7.item(i).getTextContent()));
                    }
                    if (n8.item(i) != null &&
                        !("".equals(n8.item(i).getTextContent()))) {
                        acclist.setSegment5((n8.item(i).getTextContent()));
                    }
                    if (n9.item(i) != null &&
                        !("".equals(n9.item(i).getTextContent()))) {
                        acclist.setSegment6((n9.item(i).getTextContent()));
                    }
                    if (n10.item(i) != null &&
                        !("".equals(n10.item(i).getTextContent()))) {
                        acclist.setSegment7((n10.item(i).getTextContent()));
                    }
                    if (n11.item(i) != null &&
                        !("".equals(n11.item(i).getTextContent()))) {
                        acclist.setSegment8((n11.item(i).getTextContent()));
                    }

                    an.add(acclist);
                }

                Map<String, String> newKeys = new HashMap<String, String>();
                newKeys.put("OrgId", OrgId.toString());
                newKeys.put("AccComb",
                            (AcctComb != null ? AcctComb.toString() : null));
                ADFContext.getCurrent().getSessionScope().put("SSGLAccKeys",
                                                              newKeys);
                ADFContext.getCurrent().getSessionScope().put("SSGLAccData",
                                                              an);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return (List<GLAccountsListing>)ADFContext.getCurrent().getSessionScope().get("SSGLAData");
    }

}
