package com.d108.sduty.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class InterestHashtag {
	@Id
	@Column(name="interest_hashtag_seq")
	private int seq;
	@Column(name="interest_hashtag_name")
	private String name;

	@ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER)
	private Set<Study> studies = new HashSet<Study>();
}
