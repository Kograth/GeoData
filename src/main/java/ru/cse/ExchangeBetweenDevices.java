package ru.cse;

import communication.ResponseToDevices;
import org.apache.camel.builder.RouteBuilder;

public class ExchangeBetweenDevices extends RouteBuilder{
    @Override
    public void configure() throws Exception {

////      ДЛЯ ТЕСТИРОВАНИЯ SQL БАЗЫ
////       from("timer:foo?period=10s")
////                .setBody(simple("2018-02-01 13:04:00\\t45.2\\t23.2\\t456\\t1\\tgo\\tRussia\\tMotorolaZX8"))
////                .to("mqtt:cheese?publishTopicName=GeoDataV2&userName=smx&password=smx")
////               .to("log:Data accepted");




        from("activemq:topic:GeoDataV2").process(new SavingDataToDatabase())
                .choice()
                .when(header("No data").isEqualTo("0")).to("log:No data to write DataBase. Lenght -- :#lenghtM").otherwise()
                .to("sql:insert into dbo.GeoData values (:#RegData,:#lon,:#lat,CONVERT(BINARY(16),:#UIDTask),:#UIDTaskType,:#StateTask,:#Geography,:#Device2,:#UIDTaskNumber)")
                .to("log:Delivery to SQL");



        from("amqp:queue:Devices.MessageFrom1C")
                .process(new ResponseToDevices())
                .toD("activemq:topic:${header.IDDevice}?consumer.retroactive=true").to("log:Read msg from 1C");

        from("activemq:topic:Devices.MessageFromTablet.OpenCloseShift").process(new CreateOpenCloseShift()).to("log:GetInfoAboutShift.");


        from("activemq:topic:AnswerPackageFromTablet")
                .process(new SavingDataTo1C())
                .to("amqp:queue:Devices.MessageTo1C");
//                .streamCaching()
//                .to("cxf:bean:UploadDataTo1C")
//                .to("log:Upload msg from tablet.");



    }


}
