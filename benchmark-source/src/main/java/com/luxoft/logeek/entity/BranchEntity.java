package com.luxoft.logeek.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor
public class BranchEntity {
	@Id
	private Long id;
	@Column
	private String extId;
	private boolean usItem;
	private boolean isUsItem;

	protected BranchEntity() {
	}

	public BranchEntity(String extId) {
		this.extId = extId;
	}

	public boolean isUsItem() {
		return usItem;
	}

	public void setUsItem(boolean usItem) {
		this.usItem = usItem;
	}

	public void setIsUsItem(boolean isUsItem) {
		this.isUsItem = isUsItem;
	}
}
