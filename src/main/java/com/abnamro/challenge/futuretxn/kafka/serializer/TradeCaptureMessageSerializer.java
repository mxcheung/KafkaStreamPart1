package com.abnamro.challenge.futuretxn.kafka.serializer;

import org.apache.kafka.common.serialization.Serializer;

import com.abnamro.challenge.futuretxn.dto.TradeCaptureMessage;
import com.abnamro.challenge.futuretxn.messages.TradeCaptureProto;

public  class TradeCaptureMessageSerializer implements Serializer<TradeCaptureMessage> {

	@Override
	public byte[] serialize(String topic, TradeCaptureMessage data) {
		// TODO Auto-generated method stub
		return TradeCaptureProto.TradeCaptureMessage.newBuilder()
				.setKey(data.getKey())
				.setRawMessage(data.getRawMessage())
				.build().toByteArray();
				
	}

}
