package org.infai.m3b.visio.emf.visioutil;

import org.infai.m3b.visio.emf.visiostub.IVCell;
import org.infai.m3b.visio.emf.visiostub.IVShape;
import org.jawin.COMException;

public class VisioShapeSheetUtil {
	
	IVShape shape;
	
	public VisioShapeSheetUtil(IVShape shape) {
		this.shape = shape;
	}
	
	public void setShapeText(String text) {
		
		try {
			IVCell cell = this.shape.getCellsU("LockTextEdit");
			if(cell != null)
				if (cell.getResultIU()==0)
					this.shape.setText(text);
					
		} catch (COMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void setShapeLocationX(double value) {
		
		try {
			IVCell cell = shape.getCellsU("PinX");
			double lock = shape.getCellsU("LockMoveX").getResultIU();
			if(cell != null && lock == 0)
				cell.setResultIUForce(value);	
		} catch (COMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setShapeLocationY(double value) {
		try {
			IVCell cell = shape.getCellsU("PinY");
			double lock = shape.getCellsU("LockMoveY").getResultIU();
			if(cell != null && lock == 0)
				cell.setResultIUForce(value);
		} catch (COMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public void setShapeHeight(double value) {
		try {
			IVCell cell = shape.getCellsU("Height");
			double lock = shape.getCellsU("LockHeight").getResultIU();
			if(cell != null && lock == 0)
				cell.setResultIUForce(value);
		} catch (COMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

	public void setShapeWidth(double value) {
		try {
			IVCell cell = shape.getCellsU("Width");
			double lock = shape.getCellsU("LockWidth").getResultIU();
			if(cell != null && lock == 0)
				cell.setResultIUForce(value);
		} catch (COMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}


}
