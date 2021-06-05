package com.abnamro.challenge.futuretxn.kafka.deserializer;

import org.apache.kafka.common.serialization.Deserializer;

import com.abnamro.challenge.futuretxn.dto.TradeCaptureMessage;
import com.abnamro.challenge.futuretxn.messages.TradeCaptureProto;
import com.google.protobuf.InvalidProtocolBufferException;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public  class TradeCaptureMessageDeserializer implements Deserializer<TradeCaptureMessage> {

	@Override
	public TradeCaptureMessage deserialize(String topic, byte[] data) {
		// TODO Auto-generated method stub
	    try {
			TradeCaptureProto.TradeCaptureMessage proto =   TradeCaptureProto.TradeCaptureMessage.parseFrom(data);
			return TradeCaptureMessage
					.builder()
					.meta(proto.getMeta())
					.rawMessage(proto.getRawMessage())
					.build();
		} catch (InvalidProtocolBufferException e) {
		  log.error("Unable to parse proto message",e);
		}
	    
		return null;
	}



}
