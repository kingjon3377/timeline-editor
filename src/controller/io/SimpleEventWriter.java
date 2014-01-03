package controller.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import model.event.IEvent;
import model.event.RootEvent;
import model.event.SimpleEvent;

/**
 * A class to write events in the format the EventReader reads.
 * @author Jonathan Lovelace
 *
 */
public class SimpleEventWriter {
	/**
	 * Write an Event to a Writer.
	 * @param event the event to write
	 * @param writer the writer to write to
	 * @throws IOException on writing error
	 */
	public void writeEvent(final SimpleEvent event, final Writer writer) throws IOException {
		writer.append(event.getDescription());
		writer.append(" [");
		final int date = event.getDate(); // since it's O(N) in re the number of events
		writer.append(Integer.toString(date));
		if (event.getDuration() != 0) {
			writer.append(" - ");
			writer.append(Integer.toString(date + event.getDuration()));
		}
		writer.append("]\n");
	}
	/**
	 * Write a list of Events to a writer.
	 * @param events the events to write. We skip any that aren't SimpleEvents
	 * @param writer the writer to write to
	 * @throws IOException on I/O error writing
	 */
	public void writeEvents(final List<IEvent> events, final Writer writer) throws IOException {
		for (IEvent event : events) {
			if (event instanceof SimpleEvent) {
				writeEvent((SimpleEvent) event, writer);
			} else if (!(event instanceof RootEvent)) {
				throw new IllegalStateException("Unhandled event type");
			}
		}
	}
	/**
	 * Write a list of Events to file.
	 * @param events the events to write.
	 * @param file the name of the file to write to
	 * @throws IOException on I/O error opening or writing to the file
	 */
	public void writeEvents(final List<IEvent> events, final String file) throws IOException {
		try (final Writer writer = new FileWriter(file)) {
			writeEvents(events, writer);
		}
	}
}
