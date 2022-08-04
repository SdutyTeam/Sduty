package com.d108.sduty.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Timeline {
	private Profile profile ;
	private Story story ;
	private int cntReply; 
	private List<Reply> replies;
	private boolean likes;
	private boolean scrap;
	private String jobHashtag;
	private List<String> subjectHashtag;
	
	
}
