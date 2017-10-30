package fileAccess;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;

import Util.FileFilters;
import businessDomain.OutputFromReader;
import conf.Configuration;
import dataProcessing.OutputFromReaderProcessor;

public class FileMapper {

	public static void processFilesInDirectory() {

		File inputDirectory = null;
		FileFilter datFileFilter = FileFilters.getCustomFileFilter();
		File[] datFilesInDirectory = null;
		ArrayList<File> datFilesToProcess = new ArrayList<File>();
		int timesCheckFilesLoopRan = 0;

		try {
			// create new file object
			inputDirectory = new File(Configuration.INPUT_DIRECTORY_LOCATION);
			WatchService watcher = FileSystems.getDefault().newWatchService();
			Path path = Paths.get(Configuration.INPUT_DIRECTORY_LOCATION);
			WatchKey key = path.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);
			// key is going to monitor only "create" events, overflow events can
			// occur and will be monitored regardless

			checkFilesLoop(inputDirectory, datFileFilter, datFilesInDirectory, datFilesToProcess,
					timesCheckFilesLoopRan, watcher, path, key);

		} catch (Exception e) {
			// if any error occurs
			e.printStackTrace();
		}

	}

	private static void checkFilesLoop(File inputDirectory, FileFilter datFileFilter, File[] datFilesInDirectory,
			ArrayList<File> datFilesToProcess, int timesCheckFilesLoopRan, WatchService watcher, Path path,
			WatchKey key) {
		do {

			if (inputDirectory.exists() && inputDirectory.isDirectory()) {

				datFilesInDirectory = inputDirectory.listFiles(datFileFilter);
				for (File file : datFilesInDirectory) {
					datFilesToProcess.add(file);
				}
				// IF FIRST TIME CHECKING DIRECTORY, PROCESS CURRENT FILES
				// FIRST, THEN WAIT FOR FURTHER EVENTS...
				if (timesCheckFilesLoopRan > 0) {
					try {
						key = watcher.take();
					} catch (InterruptedException x) {
						return;
					}
					checkForFileCreateEvents(key, path, datFileFilter, datFilesToProcess);

					// Reset the key to receive further events. If the key
					// is no longer valid,
					// the directory is inaccessible so exit the loop.
					boolean valid = key.reset();
					if (!valid) {
						break;
					}
				} else {
					readFilesThenWriteFiles(datFilesToProcess);
				}

			} else {
				System.out.println("Could not find directory or directory is invalid...");
			}
			timesCheckFilesLoopRan++;
		} while (true);
	}

	private static void checkForFileCreateEvents(WatchKey key, Path path, FileFilter datFileFilter,
			ArrayList<File> datFilesToProcess) {
		for (WatchEvent<?> event : key.pollEvents()) {
			WatchEvent.Kind<?> kind = event.kind();

			// This key is registered only
			// for ENTRY_CREATE events,
			// but an OVERFLOW event can
			// occur regardless if events
			// are lost or discarded.
			if (kind == StandardWatchEventKinds.OVERFLOW) {
				continue;
			}

			// The filename is the
			// context of the event.
			@SuppressWarnings("unchecked")
			WatchEvent<Path> ev = (WatchEvent<Path>) event;
			Path filename = ev.context();

			// Resolve the filename against the directory.
			// If the filename is "test" and the directory is
			// "foo",
			// the resolved name is "test/foo".

			Path child = path.resolve(filename);
			File fileThatGeneratedEvent = child.toFile();
			if (fileThatGeneratedEvent.exists() && !fileThatGeneratedEvent.isDirectory()) {
				checkFileThatGeneratedCreateEvent(fileThatGeneratedEvent, datFileFilter, datFilesToProcess);
			} else {
				System.out.println("Could not find file or file was invalid...");
				continue;
			}

		}

	}

	private static void checkFileThatGeneratedCreateEvent(File fileThatGeneratedEvent, FileFilter datFileFilter,
			ArrayList<File> datFilesToProcess) {

		if (datFileFilter.accept(fileThatGeneratedEvent)) {

			datFilesToProcess.add(fileThatGeneratedEvent);
			// add to file list to process

			readFilesThenWriteFiles(datFilesToProcess);

		}

	}

	private static void readFilesThenWriteFiles(ArrayList<File> datFilesToProcess) {
		for (File file : datFilesToProcess) {
			OutputFromReader outPutFromReader = null;
			outPutFromReader = readFiles(file);
			callWriteResultsToOutputFile(file, outPutFromReader);
		}
	}

	private static OutputFromReader readFiles(File file) {
		OutputFromReader outPutFromReader = null;

		if (file.canRead()) {
			outPutFromReader = callReadFileLines(file);
		} else {
			System.out.println("Could not read from file...");
		}

		return outPutFromReader;

	}

	private static void callWriteResultsToOutputFile(File file, OutputFromReader outPutFromReader) {
		FileWriter.writeResultsToOutputFile(file.getName(), OutputFromReaderProcessor.getNumberOfCustomersInFile(outPutFromReader),
				 OutputFromReaderProcessor.getNumberOfSalesmenInFile(outPutFromReader),  OutputFromReaderProcessor.getIDOfMostExpensiveSale(outPutFromReader),
				 OutputFromReaderProcessor.getWorstSalesmanEver(outPutFromReader));
	}

	private static OutputFromReader callReadFileLines(File file) {
		OutputFromReader outPutFromReader;
		outPutFromReader = FileReader.readFileLines(file);
		return outPutFromReader;
	}

}
