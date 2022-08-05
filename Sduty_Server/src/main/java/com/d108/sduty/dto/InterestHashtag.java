package com.d108.sduty.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="interest_hashtag")
public class InterestHashtag {
	@Id
	@Column(name = " interest_hashtag_seq")
	private int seq;
	@Column(name = " interest_hashtag_name")
	private String name;
}
