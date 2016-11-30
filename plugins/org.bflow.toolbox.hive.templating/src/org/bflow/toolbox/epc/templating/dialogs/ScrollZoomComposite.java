package org.bflow.toolbox.epc.templating.dialogs;

import java.io.ByteArrayInputStream;

import org.eclipse.draw2d.Cursors;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

/**
 * This composite offers the ability to draw a picture, move and zoom it with
 * the mouse(-wheel).
 * 
 * @author Christian Soward
 * 
 */
public class ScrollZoomComposite extends ScrolledComposite {

	private final int IMAGE_MOVE_MOUSE_BUTTON = 1; // 2 means mouse-wheel-click, 1 left mouse click
													// See more @
													// MouseEvent.button
	private final double ZOOMFACTOR_UP = 1.3; // Preview-picture measurements
												// will be multiplicated by
												// these values
	private final double ZOOMFACTOR_DOWN = 0.7;

	private TemplateWizardPage1 wizardPage;
	private Label lblPreview;

	private TemplateFileService importer;

	private boolean moveImage;
	private Point startPos;
	private Image originalImage;
	private Image smallestImage;

	/**
	 * Constructor for the class ScrollZoomComposite. The wizardPage is required
	 * i.a. to access the users template-selection and the importer.
	 * 
	 * @param wizardPage
	 *            The parent wizardPage, where this composite is nested.
	 * @param style
	 *            SWT-style-flags.
	 * @param widthHint
	 *            The desired width.
	 * @param heightHint
	 *            The desired height.
	 */
	public ScrollZoomComposite(TemplateWizardPage1 wizardPage, int style, int widthHint, int heightHint) {
		super(wizardPage.getPanelPreview(), style);

		this.wizardPage = wizardPage;
		this.importer = this.wizardPage.getImporter();

		moveImage = false;
		startPos = new Point(0, 0);
		new Point(0, 0);

		lblPreview = new Label(this, SWT.NONE);
		setContent(lblPreview);

		GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
		layoutData.widthHint = widthHint;
		layoutData.heightHint = heightHint;
		setLayoutData(layoutData);

		// Set or remove a flag, when the mouse-move-button is pressed/released
		lblPreview.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseUp(MouseEvent e) {
				deactiveImageMovingState();
			}

			@Override
			public void mouseDown(MouseEvent e) {
				activateImageMovingState(e);
			}
		});

		// Move the preview appropriate to the mouse-movement
		lblPreview.addMouseMoveListener(new MouseMoveListener() {

			@Override
			public void mouseMove(MouseEvent e) {
				moveImage(e);
			}
		});

		// Bypasses an SWT-problem/bug.
		lblPreview.addMouseTrackListener(new MouseTrackAdapter() {
			
			private Control focusControl;

			@Override
			public void mouseExit(MouseEvent e) {
				if (focusControl != null) {
					focusControl.setFocus();
				}
			}

			@Override
			public void mouseEnter(MouseEvent e) {
				focusControl = Display.getCurrent().getFocusControl();
				setFocus();
			}
		});

		// Required for zooming
		addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseScrolled(MouseEvent e) {
				performPreviewZoom(e);
			}
		});
	}

	/**
	 * This method will be called, when the user switches the current selected
	 * template within the combobox. If the template has been changed, the
	 * preview-image will be drawn. If it's to large, it automatically will be
	 * zoomed, to fit exactly within the choosed bounds.
	 * 
	 * !!!!!!!!!!!WARNING!!!!!!!!!!!! This method is just checking if the
	 * current selection-text of the combobox has been changed. Internal it is
	 * using the template-IDs. Those can be equal! Also there is currently no
	 * recognition for switching between the local/global modes and the proposed
	 * template/variant-fragment modes!!!!!!!!!!!WARNING!!!!!!!!!!!!
	 * 
	 * @param e
	 *            The selectionEvent of the combobox.
	 */
	public void updateDiagramPreview(SelectionEvent e) {
		String currentTemplateID = e.toString();
		String lastTemplateID = wizardPage.getLastTemplateID();
		if (!currentTemplateID.equals(lastTemplateID)) {
			try {
				wizardPage.getTemplateList().setEnabled(false); // While rendering, the user shouldn't
											// be able to switch the selected
											// template within the combobox
				wizardPage.setLastTemplateID(currentTemplateID);

				// delete old image
				if (lblPreview.getImage() != null) {
					lblPreview.getImage().dispose();
				}
				if (originalImage != null) {
					originalImage.dispose();
				}
				if (smallestImage != null) {
					smallestImage.dispose();
				}

				byte[] preview = importer.getImageByteArrayFromCurrentTemplate();
				originalImage = new Image(lblPreview.getDisplay(), new ByteArrayInputStream(preview));

				boolean zoomRequired = false;
				double factorX = 1;
				double factorY = 1;
				double factor = 1;
				if (originalImage.getImageData().width > getBounds().width) {
					zoomRequired = true;
					factorX = ((double) getBounds().width) / ((double) originalImage.getBounds().width);
				}
				if (originalImage.getImageData().height > getBounds().height) {
					zoomRequired = true;
					factorY = ((double) getBounds().height) / ((double) originalImage.getBounds().height);
				}
				if (zoomRequired) {
					if (factorX > factorY)
						factor = factorY;
					else
						factor = factorX;
					Image scaled = zoom(originalImage, (int) (originalImage.getBounds().width * factor),
							(int) (originalImage.getBounds().height * factor));
					smallestImage = scaled;
					lblPreview.setImage(scaled);
					lblPreview.setSize(lblPreview.computeSize(scaled.getImageData().width, scaled.getImageData().height));

				} else {
					smallestImage = new Image(lblPreview.getDisplay(), new ByteArrayInputStream(preview));
					lblPreview.setImage(originalImage);
					lblPreview.setSize(lblPreview.computeSize(originalImage.getImageData().width, originalImage.getImageData().height));
				}

			} catch (Exception e1) {
				lblPreview.setText(Messages.ScrollZoomComposite_0);
				lblPreview.setSize(300, 100); // Wirft man im Try Block oben
												// drüber Exceptions, dann
												// scheint der Catch Block nur
												// beim ersten mal so zu
												// funktionieren wie er soll.
			} finally {
				wizardPage.getTemplateList().setEnabled(true);
				wizardPage.getTemplateList().setFocus();
			}

		}
	}

	/**
	 * Moves the image, if the internal image-moving-state is activated.
	 * 
	 * @param e
	 *            The mouseEvent if the mouse is moved within the picture.
	 */
	private void moveImage(MouseEvent e) {
		if (moveImage) {
			int movex = startPos.x - e.x;
			int movey = startPos.y - e.y;
			Point targetOrigin = getOrigin();
			targetOrigin.x += movex;
			targetOrigin.y += movey;
			setOrigin(targetOrigin);
		}
	}

	/**
	 * Deactivates the internal image-moving-state.
	 */
	private void deactiveImageMovingState() {
		moveImage = false;
		lblPreview.setCursor(Cursors.ARROW);
	}

	/**
	 * Activates the internal image-moving-state.
	 * 
	 * @param e
	 *            The mouseEvent, when the user presses the defined button for
	 *            moving the picture.
	 */
	private void activateImageMovingState(MouseEvent e) {
		moveImage = e.button == IMAGE_MOVE_MOUSE_BUTTON;
		if (moveImage) {
			lblPreview.setCursor(Cursors.SIZEALL);
			startPos.x = e.x;
			startPos.y = e.y;
			getOrigin();
		}
	}

	/**
	 * This method will be called, when the user intends to scale the image by
	 * using the mouse wheel. The image may be scaled and finally redrawn. This
	 * method prevents useless zooming: (1) The scaled image would be smaller,
	 * then the smallest useful image. Useful means it fits exactly within the
	 * defined preview-bounds. That means there are no scrollbar's
	 * required/visible. (2) The scaled image would be larger then the original
	 * image. The original image offers the best quality and should also be
	 * saved with high-resolution when a template is exported. So there should
	 * be no need to scale a image larger than its' original.
	 * 
	 * @param e
	 *            The mouseEvent when the user scrolls.
	 */
	private void performPreviewZoom(MouseEvent e) {
		// the current absolute preview center
		double centerOldX = getOrigin().x + (getBounds().width / 2);
		double centerOldY = getOrigin().y + (getBounds().height / 2);
		// mouse wheel up/zoom in
		if (e.count > 0) {

			// new center after scaling
			double centerNewX = centerOldX * ZOOMFACTOR_UP;
			double centerNewY = centerOldY * ZOOMFACTOR_UP;

			// image measurements after scaling
			int newWidth = (int) (lblPreview.getImage().getBounds().width * ZOOMFACTOR_UP);
			int newHeight = (int) (lblPreview.getImage().getBounds().height * ZOOMFACTOR_UP);

			// new image would be larger/equal than the original image?
			if (newWidth >= originalImage.getBounds().width || newHeight >= originalImage.getBounds().height) {
				// the current image is neither the original image(the largest)
				// nor the smallest possible image?
				if (!lblPreview.getImage().equals(originalImage) && !lblPreview.getImage().equals(smallestImage)) {
					int widthOld = 0;
					int hightOld = 0;
					if (lblPreview.getImage() != null) {
						widthOld = lblPreview.getImage().getBounds().width;
						hightOld = lblPreview.getImage().getBounds().height;
						lblPreview.getImage().dispose();
					}
					lblPreview.setImage(originalImage);
					lblPreview.setSize(lblPreview.computeSize(originalImage.getImageData().width, originalImage.getImageData().height));
					// calculate image movement
					setOrigin((int) (((float) centerOldX / widthOld) * originalImage.getBounds().width - getBounds().width / 2),
							(int) (((float) centerOldY / hightOld) * originalImage.getBounds().height - getBounds().height / 2));
				}
				// the new image is smaller than the original image. Scale, move
				// and draw it
			} else {
				Image scaled = zoom(originalImage, newWidth, newHeight);
				if (lblPreview.getImage() != null && !lblPreview.getImage().equals(smallestImage)
						&& !lblPreview.getImage().equals(originalImage))
					lblPreview.getImage().dispose();
				lblPreview.setImage(scaled);
				lblPreview.setSize(lblPreview.computeSize(scaled.getImageData().width, scaled.getImageData().height));
				setOrigin((int) (centerNewX - getBounds().width / 2), ((int) centerNewY - getBounds().height / 2));
			}
			// mouse wheel down/zoom out
		} else {

			// new center
			double centerNewX = centerOldX * ZOOMFACTOR_DOWN;
			double centerNewY = centerOldY * ZOOMFACTOR_DOWN;

			// image measurements after scaling
			int newWidth = (int) (lblPreview.getImage().getBounds().width * ZOOMFACTOR_DOWN);
			int newHeight = (int) (lblPreview.getImage().getBounds().height * ZOOMFACTOR_DOWN);

			// new image would be smaller/equal than the smallest possible
			// image?
			if (newWidth <= smallestImage.getBounds().width || newHeight <= smallestImage.getBounds().height) {
				// the current image is neither the smallest possible image nor
				// the original image?
				if (!lblPreview.getImage().equals(smallestImage) && !lblPreview.getImage().equals(originalImage)) {
					if (lblPreview.getImage() != null)
						lblPreview.getImage().dispose();
					lblPreview.setImage(smallestImage);
					lblPreview.setSize(lblPreview.computeSize(smallestImage.getImageData().width, smallestImage.getImageData().height));
				}
				// the new image is larger than the original image. Scale, move
				// and draw it
			} else {
				Image scaled = zoom(originalImage, newWidth, newHeight);
				if (lblPreview.getImage() != null && !lblPreview.getImage().equals(smallestImage)
						&& !lblPreview.getImage().equals(originalImage))
					lblPreview.getImage().dispose();
				lblPreview.setImage(scaled);
				lblPreview.setSize(lblPreview.computeSize(scaled.getImageData().width, scaled.getImageData().height));
				setOrigin((int) (centerNewX - getBounds().width / 2), ((int) centerNewY - getBounds().height / 2));
			}
		}
	}

	/**
	 * Returns a scaled image from a given image. The scaled image can both be
	 * smaller or larger then the given image. It may uses antialiasing and
	 * interpolation.
	 * 
	 * @param original
	 *            The given image.
	 * @param width
	 *            The desired width of the scaled image.
	 * @param height
	 *            The desired height of the scaled image.
	 * @return The scaled image.
	 */
	private Image zoom(Image original, int width, int height) {
		Image scaled = new Image(lblPreview.getDisplay(), width, height);
		GC gc = new GC(scaled);
		gc.setAntialias(SWT.ON);
		gc.setInterpolation(SWT.HIGH);
		gc.drawImage(original, 0, 0, original.getBounds().width, original.getBounds().height, 0, 0, width, height);
		gc.dispose();
		return scaled;
	}

}
