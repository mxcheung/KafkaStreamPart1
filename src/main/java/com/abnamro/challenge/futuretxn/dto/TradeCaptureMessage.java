package com.abnamro.challenge.futuretxn.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeCaptureMessage {

	private String key = "";
	
	private String meta = "";
	
	private String rawMessage = "";

}
