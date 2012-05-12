package model.event;

/**
 * A simple event---not one that is just a wrapper around a list of other
 * events, but just an event.
 * 
 * @author Jonathan Lovelace
 * 
 */
public class SimpleEvent implements IEvent {
	/**
	 * Constructor.
	 * @param desc the text description of the event
	 * @param buf how much buffer time to leave after the previous event
	 * @param dur this event's duration
	 * @param date the date of this event
	 */
	public SimpleEvent(final String desc, final int buf, final int dur, final int date) {
		this(desc, buf, dur, new RootEvent(date - buf));
	}
	/**
	 * Constructor.
	 * @param desc the text description of the event
	 * @param buf how much buffer time to leave after the previous event
	 * @param dur this event's duration
	 * @param prev The previous event
	 */
	public SimpleEvent(final String desc, final int buf, final int dur, final IEvent prev) {
		description = desc;
		buffer = buf;
		duration = dur;
		previous = prev;
	}
	/**
	 * The text description of the event.
	 */
	private final String description;
	/**
	 * How much buffer time to leave after the previous event before this one.
	 */
	private final int buffer;
	/**
	 * The duration of this event.
	 */
	private final int duration;
	/**
	 * The previous event.
	 */
	private IEvent previous;
	/**
	 * @return the text description of the event.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @return How much buffer time to leave after the previous event before this one.
	 */
	@Override
	public int getBuffer() {
		return buffer;
	}
	/**
	 * @return The duration of this event.
	 */
	@Override
	public int getDuration() {
		return duration;
	}
	/**
	 * @return the date of the event
	 */
	@Override
	public int getDate() {
		return previous.getDate() + previous.getDuration() + buffer;
	}
	/**
	 * Set the previous event.
	 * @param event the previous event
	 */
	@Override
	public void setPreviousEvent(IEvent event) {
		if (event == null) {
			throw new IllegalArgumentException("Null previous event");
		}
		previous = event;
	}
	/**
	 * @return the previous event
	 */
	@Override
	public IEvent getPreviousEvent() {
		return previous;
	}
}
