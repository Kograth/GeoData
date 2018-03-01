package communication;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

//Обработка входящих сообщений от приложений (1С например)
public class ResponseToDevices implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {

       Message in = exchange.getIn();


        Message out = exchange.getOut();
        out.setHeader("IDDevice","ID1-001-001-001-001");
        out.setBody("Something haping in here...");

    }
}
