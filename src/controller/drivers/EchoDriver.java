package controller.drivers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.io.EventReader;
import controller.io.SimpleEventWriter;

/**
 * A driver to read a list of events and write them back, to verify that all is
 * working correctly.
 *
 * @author Jonathan Lovelace
 *
 */
public class EchoDriver {
	/**
	 * Static-only class.
	 */
	private EchoDriver() {
		// Do nothing
	}
	/**
	 * Driver method.
	 *
	 * @param args
	 *            a list of filenames to read from and then write to. Only use
	 *            this on unmodified files under version control!
	 */
	public static void main(final String[] args) {
		if (args.length == 0) {
			System.err.println("Usage: EchoDriver file [file ...]");
			System.err.println("Only use on unmodified files under version control");
		} else {
			final EventReader reader = new EventReader();
			final SimpleEventWriter writer = new SimpleEventWriter();
			for (String arg : args) {
				if (arg == null) {
					continue;
				}
				try {
					writer.writeEvents(reader.readEvents(arg), arg);
				} catch (final IOException e) {
					Logger.getLogger(EchoDriver.class.getName()).log(
							Level.SEVERE, "I/O error reading " + arg, e);
				}
			}
		}
	}
}
