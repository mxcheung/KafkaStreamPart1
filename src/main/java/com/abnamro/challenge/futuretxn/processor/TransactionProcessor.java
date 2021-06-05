package com.abnamro.challenge.futuretxn.processor;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

import com.abnamro.challenge.futuretxn.config.StreamBinding;
import com.abnamro.challenge.futuretxn.config.StreamConstants;
import com.abnamro.challenge.futuretxn.dto.TradeCaptureMessage;
import com.abnamro.challenge.futuretxn.mapper.InputValueMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableBinding(StreamBinding.class)
public class TransactionProcessor {

  @Autowired
  private InputValueMapper inputValueMapper;

  @StreamListener
  @SendTo(StreamConstants.INPUT_FORMATTED)
  public KStream<String, TradeCaptureMessage> process(@Input(StreamConstants.EVENT_INPUT)  final KStream<String, String> eventStream) {

    KStream<String, TradeCaptureMessage> stream =
        eventStream.mapValues(inputValueMapper)
        .map((key, value) -> new KeyValue<>(value.getKey(), value));

    
    return  stream;
  }

//  private String getCustomerProductStr(InputRecord value) {
//    return value.getClientInformation().getClientNumber() + "_" + value.getProductInformation().getProductGroupCode();
//  }
}
