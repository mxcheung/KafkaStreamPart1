package com.abnamro.challenge.futuretxn.mapper;

import static com.abnamro.challenge.futuretxn.config.FieldConstants.ACCOUNT_NUMBER;
import static com.abnamro.challenge.futuretxn.config.FieldConstants.CLIENT_NUMBER;
import static com.abnamro.challenge.futuretxn.config.FieldConstants.CLIENT_TYPE;
import static com.abnamro.challenge.futuretxn.config.FieldConstants.EXPIRATION_DATE;
import static com.abnamro.challenge.futuretxn.config.FieldConstants.EXTERNAL_NUMBER;
import static com.abnamro.challenge.futuretxn.config.FieldConstants.PRODUCT_GROUP_CODE;
import static com.abnamro.challenge.futuretxn.config.FieldConstants.QUANTITY_LONG;
import static com.abnamro.challenge.futuretxn.config.FieldConstants.QUANTITY_SHORT;
import static com.abnamro.challenge.futuretxn.config.FieldConstants.SUBACCOUNT_NUMBER;

import org.apache.kafka.streams.kstream.ValueMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.abnamro.challenge.futuretxn.dto.TradeCaptureMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class InputValueMapper implements ValueMapper<String, TradeCaptureMessage> {

  public static final String DATE_FORMAT = "yyyymmdd";

  @Autowired
  FixedLengthTokenizer fixedLengthTokenizer;

  @Override
  public TradeCaptureMessage apply(final String value) {
//	  TradeCaptureProto proto = new TradeCaptureProto();
    final TradeCaptureMessage inputRecord = new TradeCaptureMessage();
//    if (value == null || value.length() != 176) {
//      log.error("The input value is invalid");
//      throw new IllegalArgumentException("The input data is missing or in an invalid format");
//    }

    log.debug("Input is -> {}", value);
    
    
    if( value != null) {
    	
    	FieldSet fieldSet = fixedLengthTokenizer.tokenize(value);
    	
    	String client_type = fieldSet.readString(CLIENT_TYPE);
    	int client_number = fieldSet.readInt(CLIENT_NUMBER);
    	int account_number = fieldSet.readInt(ACCOUNT_NUMBER);
    	int subaccount_number = fieldSet.readInt(SUBACCOUNT_NUMBER);
    	String key = client_type + "-" + client_number + "-" + account_number +  "-"  + subaccount_number;
    	
    	inputRecord.setKey(key);
    	inputRecord.setMeta("meta");
    	inputRecord.setRawMessage(value);
    	log.debug("Input is -> {}", inputRecord.toString());
    }

    return inputRecord;
  }

  @Bean
  public FixedLengthTokenizer fixedLengthTokenizer() {
    FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();

    tokenizer.setColumns(
        new Range(1,3),
        new Range(4,7),
        new Range(8,11),
        new Range(12,15),
        new Range(16,19),
        new Range(20,25),
        new Range(26,27),
        new Range(28,31),
        new Range(32,37),
        new Range(38,45),
        new Range(46,48),
        new Range(49,50),
        new Range(51,51),
        new Range(52,52),
        new Range(53,62),
        new Range(63,63),
        new Range(64,73),
        new Range(74,85),
        new Range(86,86),
        new Range(87,89),
        new Range(90,101),
        new Range(102,102),
        new Range(103,105),
        new Range(106,117),
        new Range(118,118),
        new Range(119,121),
        new Range(122,129),
        new Range(130,135),
        new Range(136,141),
        new Range(142,147),
        new Range(148,162),
        new Range(163,168),
        new Range(169,175),
        new Range(176,176)

    );

    tokenizer.setNames(
        "RECORD CODE", CLIENT_TYPE, CLIENT_NUMBER, ACCOUNT_NUMBER,
        SUBACCOUNT_NUMBER,  "OPPOSITE PARTY CODE", PRODUCT_GROUP_CODE, "EXCHANGE CODE", "SYMBOL",
        EXPIRATION_DATE, "CURRENCY CODE", "MOVEMENT CODE", "BUY SELL CODE",
        "QUANTITY LONG SIGN", QUANTITY_LONG,
        "QUANTITY SHORT SIGN", QUANTITY_SHORT, "EXCH/BROKER FEE / DEC", "EXCH/BROKER FEE D C",
        "EXCH/BROKER FEE CUR CODE", "CLEARING FEE / DEC", "CLEARING FEE D C", "CLEARING FEE CUR CODE",
        "COMMISSION", "COMMISSION D C", "COMMISSION CUR CODE", "TRANSACTION DATE",
        "FUTURE REFERENCE", "TICKET NUMBER", EXTERNAL_NUMBER, "TRANSACTION PRICE / DEC",
        "TRADER INITIALS", "OPPOSITE TRADER ID", "OPEN CLOSE CODE"

        );

    return tokenizer;
  }
}
