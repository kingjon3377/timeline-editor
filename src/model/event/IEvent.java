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
}
