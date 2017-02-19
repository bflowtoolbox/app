package edu.toronto.cs.openome.evaluation.qualitativeinteractivereasoning;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;


/**
 * @author meatcar
 *
 */
public class AnalysisTimer {
	boolean print;
	private Timer reasoningTimer = new Timer();
	private Timer hjTimer = new Timer();
	private List<Long> hjTimes = new ArrayList<Long>();
	private int hjIntentions = 0;
	
	/**
	 * @param printTime set to true if timing statistics are to be printed
	 */
	public AnalysisTimer(boolean printTime) {
		this.print = printTime;
	}
	
	public void startReasoningTimer() {
		reasoningTimer.start();
	}
	
	public void stopReasoningTimer() {
		reasoningTimer.stop();
		if (print)
			print();
	}
	
	public void startHumanJudgmentTimer() {
		hjTimer.start();
	}
	
	public void stopHumanJudgmentTimer() {
		hjTimer.stop();
		hjTimes.add(hjTimer.getElapsedTimeSecs());
	}
	
	/**
	 * @param i the number of intention that a human judgment was done on.
	 */
	public void judgementDoneOnIntention() {
		hjIntentions++;
	}
	
	/**
	 * Display the results of the timing.
	 */
	public void print() {
		
		if(hjTimes.size() != 0) { 
		// whether to print to console or not
		boolean console = true;
		
		String message = "";
		
		message += "Tot Reasoning Time:\t" + String.valueOf(reasoningTimer.getElapsedTimeSecs()) + "s\n";
		message += "Num Human Judgements:\t" + String.valueOf(hjTimes.size()) + "\n";
		message += "Num Intentions:\t" + String.valueOf(hjIntentions) + "\n";
		
		long sum = 0;
		for (Long l : hjTimes) {
			sum += l;
		}
		message += "Avg Human Judgement:\t" + String.valueOf(sum/hjTimes.size()) + "s\n";
		
		long min = hjTimes.get(0);
		for (Long l : hjTimes) {
			min = Math.min(min, l);
		}
		message += "Min Human Judgement:\t" + String.valueOf(min) + "s\n";
		
		long max = hjTimes.get(0);
		for (Long l : hjTimes) {
			max = Math.max(max, l);
		}
		message += "Max Human Judgement:\t" + String.valueOf(max) + "s\n";
		
		if (console) 
			System.out.println(message);
		
		Shell shell = PlatformUI.getWorkbench().getDisplay().getShells()[0];
		MessageDialog.openInformation(shell, 
				"Interactive Qualitative Backward Reasoning", 
				message);
		}
	}
 	
	private class Timer {
		private long startTime = 0;
		private long stopTime = 0;
		private boolean running = false;
		
		public void start() {
			startTime = System.currentTimeMillis();
			running = true;
		}
		
		public void stop() {
			stopTime = System.currentTimeMillis();
			running = false;
		}
		
		public long getElapsedTimeSecs() {
			if (running) {
				return -1;
			} else {
				return (stopTime - startTime)/1000;
			}
		}
	}
}
