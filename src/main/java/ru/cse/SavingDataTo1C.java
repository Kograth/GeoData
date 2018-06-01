package ru.cse;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import ru.cse.APILk.Service1c.UploadTo1CFromDevices;
import org.apache.camel.component.cxf.common.message.CxfConstants;

public class SavingDataTo1C implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {

        UploadTo1CFromDevices ws1c = new UploadTo1CFromDevices();
        ws1c.setParameter("Fuck off we're close for dinner.");
        ws1c.setCheckSum("560000");
        Message Out = exchange.getOut();

        Out.setBody(ws1c);
        Out.setHeader( CxfConstants.OPERATION_NAME, "UploadTo1CFromDevices");
        Out.setHeader(CxfConstants.OPERATION_NAMESPACE, "http://www.rs1.cse.ru");

    }
}
