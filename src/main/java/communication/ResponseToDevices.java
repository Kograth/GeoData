package communication;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

//Обработка входящих сообщений от приложений (1С например)
public class ResponseToDevices implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {

        Message in = exchange.getIn();
        //new String idTablet;
//        idTablet = in.getHeader("idTablet");
//        new String checkSum       = in.getHeader("CheckSum");
        byte[] STR = (byte[]) in.getBody();
        String FinishData = new String(STR);
        Message out = exchange.getOut();
        out.setHeader("IDDevice",in.getHeader("idTablet"));
        out.setHeader("CheckSum",in.getHeader("CheckSum"));

        out.setBody(FinishData);

    }
}
