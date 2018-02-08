package ru.cse;

import org.apache.camel.builder.RouteBuilder;

public class ExchangeBetweenDevices extends RouteBuilder{
    @Override
    public void configure() throws Exception {

////      ДЛЯ ТЕСТИРОВАНИЯ
//       from("timer:foo?period=10s")
//               .setBody().simple("12\t2018-02-01 13:04:00\t45.2\t23.2\t456\t1\tgo\tRussia\tMotorolaZX8").to("activemq:topic:GeoDataV2").to("log:Send to topic");


        from("activemq:topic:GeoDataV2").process(new SavingDataToDatabase()).to("sql:insert into Geodata values (:#RegData,:#lon,:#lat,CONVERT(BINARY(16),:#UIDTask),:#UIDTaskType,:#StateTask,:#Geography,:#Device2)").to("log:Delivery to SQL");

    }
}
