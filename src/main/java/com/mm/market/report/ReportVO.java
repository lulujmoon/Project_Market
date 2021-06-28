package com.mm.market.report;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReportVO {
	private Long productNum;
    private String address;
    private String username;
    private String title;
    private String message;
}
