package communication;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

//Обработка входящих сообщений от приложений (1С например)
public class ResponseToDevices implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {

        Message in = exchange.getIn();

        byte[] MessageDataByte = (byte[]) in.getBody();

        String XMLData = new String (MessageDataByte);
        String CheckSum = String.valueOf(in.getHeader("CheckSum"));
        String FinalData = CheckSum+"<!>"+XMLData;



        Message out = exchange.getOut();
        out.setHeader("IDDevice",in.getHeader("idTablet"));
        out.setHeader("CheckSum",in.getHeader("CheckSum"));

        out.setBody(FinalData);

    }
}
