package ru.cse;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;


public class SavingDataToDatabase implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {

        Character Tabs = 0x09;

        Message In = exchange.getIn();

        String Str = (String) In.getBody();

        String[] ArrayParametr          = Str.split(String.valueOf(Tabs));

        Message Out = exchange.getOut();

        Out.setHeader("RegData",ArrayParametr[1]);
        Out.setHeader("lon",ArrayParametr[2]);
        Out.setHeader("lat",ArrayParametr[3]);
        Out.setHeader("UIDTask",ArrayParametr[4]);
        Out.setHeader("UIDTaskType",ArrayParametr[5]);
        Out.setHeader("StateTask",ArrayParametr[6]);
        Out.setHeader("Geography",ArrayParametr[7]);
        Out.setHeader("Device2",ArrayParametr[8]);


    }

}
