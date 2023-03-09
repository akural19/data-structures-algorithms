import crashtable.CrashTable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) throws IOException {

		// The format is [["Key", "Value"]]
		List<String[]> list = new ArrayList<>();

		// Table path argument provided
		if (args.length > 0) {
			list = Files.lines(Path.of(args[0].trim()))
					.map(l -> l.split("\\s+=>\\s+"))
					.collect(Collectors.toList());
		}
		
		var table = new CrashTable();
		
		for (int ii = 0; ii < list.size(); ii++) {
			String[] array = list.get(ii);
			table.put(array[0], array[1]);
		}
		
		table.printTable();
		checkGetKeys(table);
	}
	
	private static void checkGetKeys(CrashTable table) {
		String[] stringArray = table.getKeys();
		System.out.println();
		for (int ii = 0; ii < stringArray.length; ii++) {
			System.out.printf("%d.%s\n", ii+1, stringArray[ii]);
		}
	}
}