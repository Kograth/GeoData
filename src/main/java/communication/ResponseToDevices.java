package communication;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class ResponseToDevices implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {

        Message out = exchange.getOut();
        out.setHeader("foo","MyDeviceTest");
        out.setBody("I am train to work.");

    }
}
