package model.event;
/**
 * An interface for events.
 * @author Jonathan Lovelace
 *
 */
public interface IEvent {
	/**
	 * @return the length of time to allow between the previous event and this one
	 */
	int getBuffer();
	/**
	 * @return the duration of the event
	 */
	int getDuration();
	/**
	 * The date the event starts. This is calculated from the previous event's date and this event's buffer.
	 * @return the date this event starts.
	 */
	int getDate();
	
	/**
	 * @return the previous event. May not be null. If it's reference-equal to
	 *         the object this method was called on, we've reached the start of
	 *         the timeline.
	 */
	IEvent getPreviousEvent();
	/**
	 * @param event the new previous event
	 */
	void setPreviousEvent(final IEvent event);
}
