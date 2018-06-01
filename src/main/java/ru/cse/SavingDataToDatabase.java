package ru.cse;

import static javax.xml.bind.DatatypeConverter.parseHexBinary;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;


public class SavingDataToDatabase implements Processor {


    private String IdDevice,Longitude,Latitude,DataReg,IdServer,IdWebService;

    @Override
    public void process(Exchange exchange) throws Exception {


        Character Tabs = 0x09;

        Message In = exchange.getIn();

        String Str = In.getBody(String.class);
        String UIDTaskType,StateTask,Geography;
        byte[] UIDTask;

        String[] ArrayParametr          = Str.split(String.valueOf(Tabs));

        int LenghtMass = ArrayParametr.length;

        Message Out = exchange.getOut();


        if (LenghtMass>=8) {

            DataReg     = ArrayParametr[0];
            Longitude   = ArrayParametr[1];
            Latitude    = ArrayParametr[2];
            UIDTask     = ConvertGuid(ArrayParametr[3]);
            UIDTaskType = ArrayParametr[4];
            StateTask   = ArrayParametr[5];
            Geography   = ArrayParametr[6];
            IdDevice    = ArrayParametr[7];
            //IdServer    = ArrayParametr[8];
            //IdWebService= ArrayParametr[9];

            /*            if (UIDTask.length()==0) {
            UIDTask = null;
            };*/
            if (UIDTaskType.length()==0) {
                UIDTaskType = null;
            };
            if (StateTask.length()==0) {
                StateTask = null;
            };
            if (Geography.length()==0) {
                Geography = null;
            };

            Out.setHeader("RegData",DataReg);
            Out.setHeader("lon",Longitude);
            Out.setHeader("lat",Latitude);
            Out.setHeader("UIDTask",UIDTask);
            Out.setHeader("UIDTaskType",UIDTaskType);
            Out.setHeader("StateTask",StateTask);
            Out.setHeader("Geography",Geography);
            Out.setHeader("Device2",IdDevice);
            Out.setHeader("ParamIdServer",IdServer);
            Out.setHeader("ParamIdWebService",IdWebService);
        }
        else {
            Out.setHeader("No data","0");
            Out.setHeader("lenghtM",LenghtMass);
        }


    }

    private byte[] ConvertGuid(String froGuid) {
        byte[] ret = null;
        String[] parts = froGuid.split("-");
        if (parts.length>4) {
             ret = parseHexBinary(parts[3]+parts[4]+parts[2]+parts[1]+parts[0]);
        }
        return ret;
    }
            
    @Override
    public String toString() {
        String NewString = " Device->"+IdDevice+" Longitude->"+Longitude+" Latitude->"+Latitude;
        return super.toString()+NewString;
    }
}
