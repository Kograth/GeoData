package ru.cse;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import ru.cse.APILk.Service1c.UploadTo1CFromDevices;
import org.apache.camel.component.cxf.common.message.CxfConstants;

public class SavingDataTo1C implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {

//        UploadTo1CFromDevices ws1c = new UploadTo1CFromDevices();
//        ws1c.setParameter("Fuck off we're close for dinner.");
//        ws1c.setCheckSum("560000");

        Message In = exchange.getIn();

        //String BodyText = (String) IN.getBody();
        String BodyText = In.getBody(String.class);
        String[] BodyTextMas = BodyText.split("<!>");

        Message Out = exchange.getOut();

        int a = BodyTextMas.length;
        if (a==3) {
            Out.setHeader("idTablet",BodyTextMas[0]);
            Out.setHeader("CheckSum",BodyTextMas[1]);
            Out.setBody(BodyTextMas[2]);
        }
        else {
            Out.setHeader("idTablet",BodyTextMas[0]);
            Out.setHeader("CheckSum",BodyTextMas[1]);
            Out.setHeader("DateReg",BodyTextMas[2]);
            Out.setHeader("Success",BodyTextMas[3]);
        };



//        Out.setBody(ws1c);
//        Out.setHeader( CxfConstants.OPERATION_NAME, "UploadTo1CFromDevices");
//        Out.setHeader(CxfConstants.OPERATION_NAMESPACE, "http://www.rs1.cse.ru/");

    }
}
