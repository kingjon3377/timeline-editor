package model.event;

import java.util.Set;

/**
 * An interface for events.
 * @author Jonathan Lovelace
 *
 */
public interface IEvent {
	/**
	 * @return the duration of the event
	 */
	int getDuration();
	/**
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
	/**
	 * @return the tags associated with the event
	 */
	Set<String> getTags();
}
