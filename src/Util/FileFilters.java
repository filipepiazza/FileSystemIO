package Util;

import java.io.File;
import java.io.FileFilter;

import conf.Configuration;

public class FileFilters {

	public static FileFilter getCustomFileFilter() {

		FileFilter datFileFilter = new FileFilter() {
			@Override
			public boolean accept(File file) {
				for (String extension : Configuration.ACCEPTED_INPUT_EXTENSIONS) {
					// added this "OR" to account for "copied" files in MacOS
					if ((file.getName().endsWith(extension) || file.getName().contains(extension))
							&& !file.isHidden()) {
						return true;
					}
				}
				return false;
			}
		};

		return datFileFilter;

	}
	
}
