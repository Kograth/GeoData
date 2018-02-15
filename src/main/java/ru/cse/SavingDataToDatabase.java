package ru.cse;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;


public class SavingDataToDatabase implements Processor {



    @Override
    public void process(Exchange exchange) throws Exception {

        Character Tabs = 0x09;

        Message In = exchange.getIn();

        String Str = In.getBody(String.class);
        String UIDTask,UIDTaskType,StateTask,Geography;

        String[] ArrayParametr          = Str.split(String.valueOf(Tabs));

        int LenghtMass = ArrayParametr.length;

        Message Out = exchange.getOut();


        if (LenghtMass==8) {

            UIDTask     = ArrayParametr[3];
            UIDTaskType = ArrayParametr[4];
            StateTask   = ArrayParametr[5];
            Geography   = ArrayParametr[6];

            if (UIDTask.length()==0) {
                UIDTask = null;
            };
            if (UIDTaskType.length()==0) {
                UIDTaskType = null;
            };
            if (StateTask.length()==0) {
                StateTask = null;
            };
            if (Geography.length()==0) {
                Geography = null;
            };

            Out.setHeader("RegData",ArrayParametr[0]);
            Out.setHeader("lon",ArrayParametr[1]);
            Out.setHeader("lat",ArrayParametr[2]);
            Out.setHeader("UIDTask",UIDTask);
            Out.setHeader("UIDTaskType",UIDTaskType);
            Out.setHeader("StateTask",StateTask);
            Out.setHeader("Geography",Geography);
            Out.setHeader("Device2",ArrayParametr[7]);
        }
        else {
            Out.setHeader("No data","0");
            Out.setHeader("lenghtM",LenghtMass);
        }


    }

}
