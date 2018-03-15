package communication;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

//Обработка входящих сообщений от приложений (1С например)
public class ResponseToDevices implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {

        Message in = exchange.getIn();
        String idTablet       = in.getHeader("idTablet",String.class);
        String checkSum       = in.getHeader("CheckSum",String.class);

        Message out = exchange.getOut();
        out.setHeader("IDDevice",idTablet);
        out.setBody(in.getBody());

    }
}
