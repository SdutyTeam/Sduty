package com.d108.sduty.service;

import com.d108.sduty.dto.Qna;

public interface SettingService {
	public int registQna(Qna qna);
	public Qna getQnaDetail(int qnaSeq);
	public Qna updateQna(int qnaSeq, Qna qna);
	public void deleteQna(int qnaSeq);
}
