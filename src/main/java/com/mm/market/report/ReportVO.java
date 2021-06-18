package com.mm.market.report;

import com.mm.market.member.MemberVO;
import com.mm.market.product.ProductVO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReportVO {
    private String address;
    private ProductVO productNum;
    private String title;
    private String message;
}
