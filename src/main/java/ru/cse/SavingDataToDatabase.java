package ru.cse;

import org.apache.camel.Exchange;

import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.TypeConverter;


public class SavingDataToDatabase implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {

        Character Tabs = 0x09;


        Message In = exchange.getIn();

        String Str = In.getBody(String.class);

        String[] ArrayParametr          = Str.split(String.valueOf(Tabs));


        Message Out = exchange.getOut();

        Out.setHeader("RegData",ArrayParametr[0]);
        Out.setHeader("lon",ArrayParametr[1]);
        Out.setHeader("lat",ArrayParametr[2]);
        Out.setHeader("UIDTask",ArrayParametr[3]);
        Out.setHeader("UIDTaskType",ArrayParametr[4]);
        Out.setHeader("StateTask",ArrayParametr[5]);
        Out.setHeader("Geography",ArrayParametr[6]);
        Out.setHeader("Device2",ArrayParametr[7]);


    }

}
