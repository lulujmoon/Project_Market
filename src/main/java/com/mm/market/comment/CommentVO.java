package com.mm.market.comment;

import java.sql.Timestamp;

import com.mm.market.member.MemberFileVO;
import com.mm.market.member.MemberVO;

import lombok.Data;

@Data
public class CommentVO {
	
	private Long commentNum;
	private Long socialNum;
	private String commentContent;
	private String username;
	private Timestamp commentDate;
	private Long ref;
	private Long step;
	private Long depth;

	private MemberVO writer;
	private MemberFileVO writerFile;
}
