package edu.toronto.cs.openome.conversion.service;

import java.util.Map;
import java.util.Vector;

public interface IConfigurator {
	public void setModel(String name, Map<String, Integer> labels, Map<String, Integer> ranks);
	public String query(String name);
}
