package com.luxoft.logeek.entity;

import java.util.*;

public abstract class BaseAuditEntity {
	protected List<ModifiedColumn> modifiedColumns = new ArrayList<>();
	
	public void includeInAudit(String columnName, Object oldValue, Object newValue, AuditMode... modes) {
		Set<ModifiedColumn.Draft> drafts = new HashSet<>();
		
		for(AuditMode mode : modes) {
			switch (mode) {
				case OLD_DRAFT:
					drafts.add(ModifiedColumn.Draft.OLD_DRAFT);
					break;
				case NEW_DRAFT:
					drafts.add(ModifiedColumn.Draft.NEW_DRAFT);
			}
		}
		
		modifiedColumns.add(new ModifiedColumn(drafts));
	}
	
	public static class ModifiedColumn {
		private Set<Draft> drafts;

		public ModifiedColumn(Set<Draft> drafts) {
			this.drafts = drafts;
		}

		public enum Draft {
			OLD_DRAFT,
			NEW_DRAFT
		}
	}
	
	public enum AuditMode {
		OLD_DRAFT,
		NEW_DRAFT
	}
}
