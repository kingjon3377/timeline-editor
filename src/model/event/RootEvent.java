package model.event;

import java.util.logging.Logger;

/**
 * The base event, with a constant date.
 * @author Jonathan Lovelace
 *
 */
public class RootEvent implements IEvent {
	/**
	 * The starting date for the timeline.
	 */
	private final int date;
	/**
	 * Constructor
	 * @param start the starting date for the timeline 
	 */
	public RootEvent(final int start) {
		date = start;
	}
	/**
	 * @return 0, as "buffer" is meaningless for this class.
	 */
	@Override
	public int getBuffer() {
		return 0;
	}
	/**
	 * @return 0, as "duration" is meaningless for this class.
	 */
	@Override
	public int getDuration() {
		return 0;
	}
	/**
	 * @return the starting date of the timeline.
	 */
	@Override
	public int getDate() {
		return date;
	}
	
	/**
	 * We do nothing here, as there *is* and *can be* no previous event before
	 * the timeline starts. In fact, we log this as a warning.
	 * 
	 * @param event
	 *            Ignored
	 */
	@Override
	public void setPreviousEvent(final IEvent event) {
		Logger.getLogger(RootEvent.class.getName()).warning("RootEvent#setPreviousEvent called");
	}
	/**
	 * @return this, as we have no previous event.
	 */
	@Override
	public IEvent getPreviousEvent() {
		return this;
	}
}
