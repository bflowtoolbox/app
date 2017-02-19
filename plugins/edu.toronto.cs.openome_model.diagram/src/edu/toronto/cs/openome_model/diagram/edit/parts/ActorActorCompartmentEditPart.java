package edu.toronto.cs.openome_model.diagram.edit.parts;

import java.util.List;

import openome_model.figures.ContainerSVGFigure;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EcoreFactoryImpl;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ListCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableCompartmentEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.render.editparts.RenderedDiagramRootEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.impl.DrawerStyleImpl;
import org.eclipse.gmf.runtime.notation.impl.NotationFactoryImpl;

import edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart.ActorFigure;
import edu.toronto.cs.openome_model.diagram.edit.policies.DoubleClickNameEditPolicy;

/**
 * @generated
 */
public class ActorActorCompartmentEditPart extends CompartmentEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 7001;
	/**
	 * The stored width of the actor figure.
	 * This is used for restoring (expanding) the actor
	 * figure when it's collapsed.
	 * @generated NOT
	 */
	int storedWidth = 450;
	/**
	 * The stored width of the actor figure.
	 * This is used for restoring (expanding) the actor
	 * figure when it's collapsed.
	 * @generated NOT
	 */
	int storedHeight = 450;

	/**
	 * The minimum size of the actor compartment in order
	 * to keep it from moving the elements inside it
	 * while contracting - see ticket #171
	 */
	Dimension minimumContraction = new Dimension(0, 0);

	/**
	 * @generated
	 */
	public ActorActorCompartmentEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected boolean hasModelChildrenChanged(Notification evt) {
		return false;
	}

	/**
	 * If this compartment is closed, forcefully redirect all intentions' anchor points
	 * to point to the green actor figure instead
	 */
	public void forceRedirect() {
		boolean isCollapsed = ((Boolean) getStructuralFeatureValue(NotationPackage.eINSTANCE
				.getDrawerStyle_Collapsed())).booleanValue();

		if (isCollapsed) {

			EAttributeImpl feature = (EAttributeImpl) NotationPackage.eINSTANCE
					.getDrawerStyle_Collapsed();

			NotationFactoryImpl notifierFactory = new NotationFactoryImpl();
			DrawerStyleImpl notifier = (DrawerStyleImpl) notifierFactory
					.createDrawerStyle();
			notifier.setCollapsed(true);

			ENotificationImpl notification = new ENotificationImpl(notifier,
					Notification.SET, feature, null, null);

			//Notify the compartment that anchor points have not been redirected yet
			this.handleNotificationEvent(notification);
		}
	}

	/**
	 * @generated
	 */
	public String getCompartmentName() {
		return edu.toronto.cs.openome_model.diagram.part.Messages.ActorActorCompartmentEditPart_title;
	}

	/**
	 * 
	 * @return the minimum size that the compartment may contract to
	 */
	public Dimension getMinimumContraction() {
		return minimumContraction;
	}

	/**
	 * Refreshes the connections inside the shape compartment if the supplied
	 * event is for an element inserted or removed from the editpart.
	 * 
	 * @see #refreshConnections()
	 * @param event
	 *            a model server event.
	 * @generated NOT
	 */
	protected void handleNotificationEvent(Notification event) {
		// ensures that the scroll bars never show up
		this.getCompartmentFigure().getScrollPane().setVerticalScrollBarVisibility(ScrollPane.NEVER);
		this.getCompartmentFigure().getScrollPane().setHorizontalScrollBarVisibility(ScrollPane.NEVER);
		Object feature = event.getFeature();

		// Handling a resize event
		if (NotationPackage.eINSTANCE.getSize_Width().equals(feature)
				|| NotationPackage.eINSTANCE.getSize_Height().equals(feature)) {
			/*
			 * Before proceeding with the resize, 
			 * we should calculate how far the compartment can be shrunk
			 * 
			 * What we want to do is look into our compartment
			 * and calculate the size of all its element
			 * 
			 * We can then find the elements lying closest to the boundary
			 * and set a constraint that we cannot contract farther than
			 * those elements' coordinates
			 * */

			List children = this.getChildren();
			int maxx = ContainerSVGFigure.SIZE_OF_ACTOR_SYMBOL;
			int maxy = ContainerSVGFigure.SIZE_OF_ACTOR_SYMBOL;
			int padding = 30;
			Rectangle currentRect;
			//for (int j = 0; j < container.getDecompositions().size(); j++){}
			for (int i = 0; i < children.size(); i++) {
				EditPart ep = (EditPart) (children.get(i));

				// if it's a Goal intention:
				if (ep instanceof GoalEditPart) {
					currentRect = ((GoalEditPart) (ep)).getPrimaryShape()
							.getBounds();
					maxx = Math.max(currentRect.x + currentRect.width, maxx);
					maxy = Math.max(currentRect.y + currentRect.height, maxy);

				} else if (ep instanceof Goal2EditPart) {
					currentRect = ((Goal2EditPart) (ep)).getPrimaryShape()
							.getBounds();
					maxx = Math.max(currentRect.x + currentRect.width, maxx);
					maxy = Math.max(currentRect.y + currentRect.height, maxy);

				} else if (ep instanceof Goal3EditPart) {
					currentRect = ((Goal3EditPart) (ep)).getPrimaryShape()
							.getBounds();
					maxx = Math.max(currentRect.x + currentRect.width, maxx);
					maxy = Math.max(currentRect.y + currentRect.height, maxy);

				} else if (ep instanceof Goal4EditPart) {
					currentRect = ((Goal4EditPart) (ep)).getPrimaryShape()
							.getBounds();
					maxx = Math.max(currentRect.x + currentRect.width, maxx);
					maxy = Math.max(currentRect.y + currentRect.height, maxy);

				} else if (ep instanceof Goal5EditPart) {
					currentRect = ((GoalEditPart) (ep)).getPrimaryShape()
							.getBounds();
					maxx = Math.max(currentRect.x + currentRect.width, maxx);
					maxy = Math.max(currentRect.y + currentRect.height, maxy);

				}
				
				// if it's a Softgoal intention:
				if (ep instanceof SoftgoalEditPart) {
					currentRect = ((SoftgoalEditPart) (ep)).getPrimaryShape()
							.getBounds();
					maxx = Math.max(currentRect.x + currentRect.width, maxx);
					maxy = Math.max(currentRect.y + currentRect.height, maxy);

				} else if (ep instanceof Softgoal2EditPart) {
					currentRect = ((Softgoal2EditPart) (ep)).getPrimaryShape()
							.getBounds();
					maxx = Math.max(currentRect.x + currentRect.width, maxx);
					maxy = Math.max(currentRect.y + currentRect.height, maxy);

				} else if (ep instanceof Softgoal3EditPart) {
					currentRect = ((Softgoal3EditPart) (ep)).getPrimaryShape()
							.getBounds();
					maxx = Math.max(currentRect.x + currentRect.width, maxx);
					maxy = Math.max(currentRect.y + currentRect.height, maxy);

				} else if (ep instanceof Softgoal4EditPart) {
					currentRect = ((Softgoal4EditPart) (ep)).getPrimaryShape()
							.getBounds();
					maxx = Math.max(currentRect.x + currentRect.width, maxx);
					maxy = Math.max(currentRect.y + currentRect.height, maxy);

				} else if (ep instanceof Softgoal5EditPart) {
					currentRect = ((Softgoal5EditPart) (ep)).getPrimaryShape()
							.getBounds();
					maxx = Math.max(currentRect.x + currentRect.width, maxx);
					maxy = Math.max(currentRect.y + currentRect.height, maxy);

				}

				// if it's a Task intention:
				if (ep instanceof TaskEditPart) {
					currentRect = ((TaskEditPart) (ep)).getPrimaryShape()
							.getBounds();
					maxx = Math.max(currentRect.x + currentRect.width, maxx);
					maxy = Math.max(currentRect.y + currentRect.height, maxy);

				} else if (ep instanceof Task2EditPart) {
					currentRect = ((Task2EditPart) (ep)).getPrimaryShape()
							.getBounds();
					maxx = Math.max(currentRect.x + currentRect.width, maxx);
					maxy = Math.max(currentRect.y + currentRect.height, maxy);

				} else if (ep instanceof Task3EditPart) {
					currentRect = ((Task3EditPart) (ep)).getPrimaryShape()
							.getBounds();
					maxx = Math.max(currentRect.x + currentRect.width, maxx);
					maxy = Math.max(currentRect.y + currentRect.height, maxy);

				} else if (ep instanceof Task4EditPart) {
					currentRect = ((Task4EditPart) (ep)).getPrimaryShape()
							.getBounds();
					maxx = Math.max(currentRect.x + currentRect.width, maxx);
					maxy = Math.max(currentRect.y + currentRect.height, maxy);

				} else if (ep instanceof Task5EditPart) {
					currentRect = ((Task5EditPart) (ep)).getPrimaryShape()
							.getBounds();
					maxx = Math.max(currentRect.x + currentRect.width, maxx);
					maxy = Math.max(currentRect.y + currentRect.height, maxy);

				}

				// if it's a Resource intention
				if (ep instanceof ResourceEditPart) {
					currentRect = ((ResourceEditPart) (ep)).getPrimaryShape()
							.getBounds();
					maxx = Math.max(currentRect.x + currentRect.width, maxx);
					maxy = Math.max(currentRect.y + currentRect.height, maxy);

				} else if (ep instanceof Resource2EditPart) {
					currentRect = ((Resource2EditPart) (ep)).getPrimaryShape()
							.getBounds();
					maxx = Math.max(currentRect.x + currentRect.width, maxx);
					maxy = Math.max(currentRect.y + currentRect.height, maxy);

				} else if (ep instanceof Resource3EditPart) {
					currentRect = ((Resource3EditPart) (ep)).getPrimaryShape()
							.getBounds();
					maxx = Math.max(currentRect.x + currentRect.width, maxx);
					maxy = Math.max(currentRect.y + currentRect.height, maxy);

				} else if (ep instanceof Resource4EditPart) {
					currentRect = ((Resource4EditPart) (ep)).getPrimaryShape()
							.getBounds();
					maxx = Math.max(currentRect.x + currentRect.width, maxx);
					maxy = Math.max(currentRect.y + currentRect.height, maxy);

				} else if (ep instanceof Resource5EditPart) {
					currentRect = ((Resource5EditPart) (ep)).getPrimaryShape()
							.getBounds();
					maxx = Math.max(currentRect.x + currentRect.width, maxx);
					maxy = Math.max(currentRect.y + currentRect.height, maxy);

				}
			}

			// Calculate and set the shrinking bound
			minimumContraction = (new Dimension(getMapMode().DPtoLP(
					maxx + padding), getMapMode().DPtoLP(maxy + padding)));
			IGraphicalEditPart actorEdit = (IGraphicalEditPart) getParent();
			((ActorEditPart) actorEdit).getPrimaryShape()
					.setMinimumContraction(minimumContraction);

			////////////////////////////EXPERIMENTAL////////////////////////////////////////
			// take into account the zoom level
			RenderedDiagramRootEditPart renderRoot = (RenderedDiagramRootEditPart) ((ActorEditPart) actorEdit)
					.getParent().getParent();
			double zoom = renderRoot.getZoomManager().getZoom();
			if (zoom < 1.0) {
				Dimension zoomedContraction = minimumContraction
						.getScaled(zoom);
				((ActorEditPart) actorEdit).getPrimaryShape()
						.setMinimumContraction(zoomedContraction);
			}
			////////////////////////////////////////////////////////////////////////////////

			// We would like the new bound to not interfere with collapsing 
			// or resizing with no elements
			if (((Boolean) getStructuralFeatureValue(NotationPackage.eINSTANCE
					.getDrawerStyle_Collapsed())).booleanValue()
					|| children.size() == 0) {
				((ActorEditPart) actorEdit)
						.getPrimaryShape()
						.setMinimumContraction(
								new Dimension(
										ContainerSVGFigure.SIZE_OF_ACTOR_SYMBOL,
										ContainerSVGFigure.SIZE_OF_ACTOR_SYMBOL));
			}

			refreshConnections();
		} else if (NotationPackage.eINSTANCE.getDrawerStyle_Collapsed().equals(
				feature)) {
			boolean isCollapsed = ((Boolean) getStructuralFeatureValue(NotationPackage.eINSTANCE
					.getDrawerStyle_Collapsed())).booleanValue();
			// normally, we would call this method to hide the intentions
			// within the actor, but when you make the intentions not visible,
			// you also make the links/connects connected to the intention
			// not visible as well, which is what we don't want, so we'll
			// comment it out
			// super.handleNotificationEvent(event);

			int collapsedWidth = ContainerSVGFigure.SIZE_OF_ACTOR_SYMBOL;
			int collapsedHeight = ContainerSVGFigure.SIZE_OF_ACTOR_SYMBOL;

			int xLocation = this.getFigure().getBounds().x;
			int yLocation = this.getFigure().getBounds().y;

			if (isCollapsed) {
				// determine which type of intention it is, then redirect it's
				// anchor points to point to the actor instead
				List listOfChildren = this.getChildren();
				for (int i = 0; i < listOfChildren.size(); i++) {
					EditPart ep = (EditPart) (listOfChildren.get(i));

					// if it's a Goal intention:
					if (ep instanceof GoalEditPart) {
						((GoalEditPart) (ep)).setIsCollapsed(true);
					} else if (ep instanceof Goal2EditPart) {
						((Goal2EditPart) (ep)).setIsCollapsed(true);
					} else if (ep instanceof Goal3EditPart) {
						((Goal3EditPart) (ep)).setIsCollapsed(true);
					} else if (ep instanceof Goal4EditPart) {
						((Goal4EditPart) (ep)).setIsCollapsed(true);
					} else if (ep instanceof Goal5EditPart) {
						((Goal5EditPart) (ep)).setIsCollapsed(true);
					}

					// if it's a Softgoal intention:
					if (ep instanceof SoftgoalEditPart) {
						((SoftgoalEditPart) (ep)).setIsCollapsed(true);
					} else if (ep instanceof Softgoal2EditPart) {
						((Softgoal2EditPart) (ep)).setIsCollapsed(true);
					} else if (ep instanceof Softgoal3EditPart) {
						((Softgoal3EditPart) (ep)).setIsCollapsed(true);
					} else if (ep instanceof Softgoal4EditPart) {
						((Softgoal4EditPart) (ep)).setIsCollapsed(true);
					} else if (ep instanceof Softgoal5EditPart) {
						((Softgoal5EditPart) (ep)).setIsCollapsed(true);
					}

					// if it's a Task intention:
					if (ep instanceof TaskEditPart) {
						((TaskEditPart) (ep)).setIsCollapsed(true);
					} else if (ep instanceof Task2EditPart) {
						((Task2EditPart) (ep)).setIsCollapsed(true);
					} else if (ep instanceof Task3EditPart) {
						((Task3EditPart) (ep)).setIsCollapsed(true);
					} else if (ep instanceof Task4EditPart) {
						((Task4EditPart) (ep)).setIsCollapsed(true);
					} else if (ep instanceof Task5EditPart) {
						((Task5EditPart) (ep)).setIsCollapsed(true);
					}

					// if it's a Resource intention
					if (ep instanceof ResourceEditPart) {
						((ResourceEditPart) (ep)).setIsCollapsed(true);
					} else if (ep instanceof Resource2EditPart) {
						((Resource2EditPart) (ep)).setIsCollapsed(true);
					} else if (ep instanceof Resource3EditPart) {
						((Resource3EditPart) (ep)).setIsCollapsed(true);
					} else if (ep instanceof Resource4EditPart) {
						((Resource4EditPart) (ep)).setIsCollapsed(true);
					} else if (ep instanceof Resource5EditPart) {
						((Resource5EditPart) (ep)).setIsCollapsed(true);
					}
				}

				// store the original width and height of the figure
				// so we can use it in the future when restoring

				// the math.max is to store the width and height as 110 if the user
				// resizes the actor figure down to 100 pixels and then collapses..
				// when the user tries to expand the actor figure again, they won't
				// get the resize points if the figure is 100 pixels wide/tall
				storedWidth = Math.max(110, this.getFigure().getBounds().width);
				storedHeight = Math.max(110,
						this.getFigure().getBounds().height);

				Rectangle newBounds = new Rectangle(xLocation, yLocation,
						collapsedWidth, collapsedHeight);

				CompositeCommand compoudCommand = new CompositeCommand(
						"collapse");

				ICommand changeThisBoundsCommand = new SetBoundsCommand(this
						.getEditingDomain(), "collapse", this.getParent(),
						newBounds);

				compoudCommand.add(changeThisBoundsCommand);

				// make it so
				try {
					compoudCommand.execute(new NullProgressMonitor(), null);
				} catch (ExecutionException e) {
					e.printStackTrace();
				}

			} else {
				// if a diagram contains a collapsed actor, ensure that
				// we all of the contained intentions to be visible. Without
				// this call, the figure will expand but the intentions will not show
				super.handleNotificationEvent(event);
				// determine which type of intention it is, then redirect it's
				// anchor points from the actor symbol to point back at the intention
				
				List listOfChildren = this.getChildren();
				for (int i = 0; i < listOfChildren.size(); i++) {
					EditPart ep = (EditPart) (listOfChildren.get(i));
					// if it's a Goal intention:
					if (ep instanceof GoalEditPart) {
						((GoalEditPart) (ep)).setIsCollapsed(false);
					} else if (ep instanceof Goal2EditPart) {
						((Goal2EditPart) (ep)).setIsCollapsed(false);
					} else if (ep instanceof Goal3EditPart) {
						((Goal3EditPart) (ep)).setIsCollapsed(false);
					} else if (ep instanceof Goal4EditPart) {
						((Goal4EditPart) (ep)).setIsCollapsed(false);
					} else if (ep instanceof Goal5EditPart) {
						((Goal5EditPart) (ep)).setIsCollapsed(false);
					}

					// if it's a Softgoal intention:
					if (ep instanceof SoftgoalEditPart) {
						((SoftgoalEditPart) (ep)).setIsCollapsed(false);
					} else if (ep instanceof Softgoal2EditPart) {
						((Softgoal2EditPart) (ep)).setIsCollapsed(false);
					} else if (ep instanceof Softgoal3EditPart) {
						((Softgoal3EditPart) (ep)).setIsCollapsed(false);
					} else if (ep instanceof Softgoal4EditPart) {
						((Softgoal4EditPart) (ep)).setIsCollapsed(false);
					} else if (ep instanceof Softgoal5EditPart) {
						((Softgoal5EditPart) (ep)).setIsCollapsed(false);
					}

					// if it's a Task intention:
					if (ep instanceof TaskEditPart) {
						((TaskEditPart) (ep)).setIsCollapsed(false);
					} else if (ep instanceof Task2EditPart) {
						((Task2EditPart) (ep)).setIsCollapsed(false);
					} else if (ep instanceof Task3EditPart) {
						((Task3EditPart) (ep)).setIsCollapsed(false);
					} else if (ep instanceof Task4EditPart) {
						((Task4EditPart) (ep)).setIsCollapsed(false);
					} else if (ep instanceof Task5EditPart) {
						((Task5EditPart) (ep)).setIsCollapsed(false);
					}

					// if it's a Resource intention
					if (ep instanceof ResourceEditPart) {
						((ResourceEditPart) (ep)).setIsCollapsed(false);
					} else if (ep instanceof Resource2EditPart) {
						((Resource2EditPart) (ep)).setIsCollapsed(false);
					} else if (ep instanceof Resource3EditPart) {
						((Resource3EditPart) (ep)).setIsCollapsed(false);
					} else if (ep instanceof Resource4EditPart) {
						((Resource4EditPart) (ep)).setIsCollapsed(false);
					} else if (ep instanceof Resource5EditPart) {
						((Resource5EditPart) (ep)).setIsCollapsed(false);
					}
				}

				// restore the width and height of the actor
				Rectangle newBounds = new Rectangle(xLocation, yLocation,
						storedWidth, storedHeight);

				CompositeCommand compoudCommand = new CompositeCommand(
						"collapse");

				ICommand changeThisBoundsCommand = new SetBoundsCommand(this
						.getEditingDomain(), "collapse", this.getParent(),
						newBounds);

				compoudCommand.add(changeThisBoundsCommand);

				// make it so
				try {
					compoudCommand.execute(new NullProgressMonitor(), null);
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				
			}
		} else {
			super.handleNotificationEvent(event);
			refreshConnections();
		}

		if (NotificationUtil.isElementAddedToSlot(event)
				|| NotificationUtil.isElementRemovedFromSlot(event)) {
			refreshConnections();
		}
	}

	/**
	 * @generated NOT
	 */
	public IFigure createFigure() {
		ResizableCompartmentFigure result = (ResizableCompartmentFigure) super
				.createFigure();

		// removes the scroll bars from the actor figure.. (ticket #114)
		// unfortunately, for some unknown reason, the vertical scrollbar is still visible
		result.getScrollPane().setVerticalScrollBarVisibility(ScrollPane.NEVER);
		result.getScrollPane().setHorizontalScrollBarVisibility(
				ScrollPane.NEVER);

		// removes the annoying border line at the top of the compartment
		// this fix is for ticket #115
		result.setBorder(null);

		result.setTitleVisibility(false);
		return result;
	}

	/**
	 * @generated NOT
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE,
				new ResizableCompartmentEditPolicy());
		installEditPolicy(
				EditPolicyRoles.SEMANTIC_ROLE,
				new edu.toronto.cs.openome_model.diagram.edit.policies.ActorActorCompartmentItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.CREATION_ROLE,
				new CreationEditPolicy());
		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE,
				new DragDropEditPolicy());
	}

	/**
	 * @generated
	 */
	protected void setRatio(Double ratio) {
		if (getFigure().getParent().getLayoutManager() instanceof ConstrainedToolbarLayout) {
			super.setRatio(ratio);
		}
	}

}
