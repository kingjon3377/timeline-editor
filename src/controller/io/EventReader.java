package controller.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import model.event.IEvent;
import model.event.SimpleEvent;

import org.eclipse.jdt.annotation.Nullable;

/**
 * A class to read Events from file.
 * @author Jonathan Lovelace
 *
 */
public class EventReader {
	/**
	 * Read an Event from a line.
	 *
	 * @param line
	 *            the line to read
	 * @param previous
	 *            the previous event. If null, we'll use the int-parameter
	 *            version of the SimpleEvent's constructor, and pass in a buffer of 3.
	 * @return the Event contained in it.
	 */
	public IEvent readLine(final String line, @Nullable final IEvent previous) {
		final String[] first = line.split("\\[");
		if (first.length != 2 || !first[1].endsWith("]")) {
			throw new IllegalArgumentException("Line must end with bracketed dates");
		}
		final String firstField = first[0].trim();
		final String desc = firstField == null ? "" : firstField;
		final String dates = first[1].substring(0, first[1].length() - 1);
		// ESCA-JAVA0177:
		final int start;
		// ESCA-JAVA0177:
		final int duration;
		if (dates.contains("-")) {
			final String[] dateArray = dates.split("-");
			if (dateArray.length != 2) {
				throw new IllegalArgumentException("Couldn't parse date range");
			}
			final int dateOne = Integer.parseInt(dateArray[0].trim());
			final int dateTwo = Integer.parseInt(dateArray[1].trim());
			start = dateOne;
			duration = dateTwo - dateOne;
		} else {
			start = Integer.parseInt(dates.trim());
			duration = 0;
		}
		if (previous == null) {
			return new SimpleEvent(desc, 3, duration, start);
		} else {
			return new SimpleEvent(desc, start - previous.getDuration() - previous.getDate(), duration, previous);
		}
	}
	/**
	 * @param reader a Reader to read from
	 * @return a list of events it contains.
	 * @throws IOException on I/O error reading from file
	 */
	public List<IEvent> readEvents(final BufferedReader reader) throws IOException {
		IEvent previous = null;
		List<IEvent> retval = new LinkedList<>();
		String line;
		while ((line = reader.readLine()) != null) {
			final IEvent event = readLine(line, previous);
			retval.add(event);
			previous = event;
		}
		return retval;
	}
	/**
	 * @param file the name of a file
	 * @return the list of events in it
	 * @throws IOException on I/O error opening or reading the file
	 */
	public List<IEvent> readEvents(final String file) throws IOException {
		try (final BufferedReader reader = new BufferedReader(new FileReader(
				file))) {
			return readEvents(reader);
		}
	}
}
