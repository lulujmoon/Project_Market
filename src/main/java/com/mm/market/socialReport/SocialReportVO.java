package com.mm.market.socialReport;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SocialReportVO {
	private Long socialNum;
    private String address;
    private String username;
    private String title;
    private String message;
}