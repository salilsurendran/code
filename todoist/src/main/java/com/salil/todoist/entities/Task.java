package com.salil.todoist.entities;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {
	private String content;
	private int indent;
	private long[] labels;
	private boolean checked;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIndent() {
		return indent;
	}

	public void setIndent(int indent) {
		this.indent = indent;
	}

	public long[] getLabels() {
		return labels;
	}

	public void setLabels(long[] labels) {
		this.labels = labels;
	}

	public boolean hasLabel(List<Long> searchLabelIds) {
		if (labels != null)
			for (long labelId : labels)
				if (searchLabelIds.contains(labelId))
					return true;
		return false;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
