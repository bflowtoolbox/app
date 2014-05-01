package org.infai.m3b.visio.emf.visioutil;

public class VisioShapeSheetSection {
	
	private String name;
	private short id;
	
	public VisioShapeSheetSection(String name, short id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public short getId() {
		return id;
	}
}
