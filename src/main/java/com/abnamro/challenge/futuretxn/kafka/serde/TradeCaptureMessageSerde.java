package com.abnamro.challenge.futuretxn.kafka.serde;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import com.abnamro.challenge.futuretxn.dto.TradeCaptureMessage;
import com.abnamro.challenge.futuretxn.kafka.deserializer.TradeCaptureMessageDeserializer;
import com.abnamro.challenge.futuretxn.kafka.serializer.TradeCaptureMessageSerializer;

public  class TradeCaptureMessageSerde implements Serde<TradeCaptureMessage> {

	
	private TradeCaptureMessageSerializer tradeCaptureMessageSerializer = new TradeCaptureMessageSerializer();

	private TradeCaptureMessageDeserializer tradeCaptureMessageDeserializer = new TradeCaptureMessageDeserializer();

	@Override
	public Serializer<TradeCaptureMessage> serializer() {
		return tradeCaptureMessageSerializer;
	}

	@Override
	public Deserializer<TradeCaptureMessage> deserializer() {
		return tradeCaptureMessageDeserializer;
	}


}
