package com.abnamro.challenge.futuretxn.config;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

import com.abnamro.challenge.futuretxn.messages.TradeCaptureProto.TradeCaptureMessage;

public interface StreamBinding {

  @Input(StreamConstants.EVENT_INPUT)
  KStream<String, String> inputStream();

  @Output(StreamConstants.INPUT_FORMATTED)
  KStream<String, TradeCaptureMessage> outputStream();
}
