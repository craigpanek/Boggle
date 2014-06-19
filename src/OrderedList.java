/**
 * This class can represent an ordered list of Strings (words).
 * Items are inserted one at a time, being placed in their proper position.
 * @author Craig Panek
 * Date: 6-19-2014
 */

import java.util.ArrayList;

public class OrderedList {

	private ArrayList<String> list = new ArrayList<String>();
	
	/**
	 * Insert a given word into it's proper ordered position in the list.
	 * @param item
	 */
	public void insert(String item) {
		boolean found = false;
		boolean passed = false;
		int location;
	
		for(location = 0; location < list.size() && !found && !passed; location++) {
			if(item.equals(list.get(location))) {
				found = true;
			} else if (item.compareTo(list.get(location)) < 0) {
				passed = true;
			}
		}
		if(passed)
			--location;
		if (!found ) {
			list.add(location, item);
		}
	}
	
	/**
	 * @param item
	 * @return true if item is in the list, false otherwise.
	 */
	public boolean find(String item) {
		for(int i = 0; i < list.size(); i++)
			if(list.get(i).equals(item))
				return true;
		return false;
	}
	
	/**
	 * @return String representing entire ordered wordlist.
	 */
	public String toString() {
		String outStr = "";
	
		for(int i = 0; i < list.size(); i++) {
			outStr = outStr + list.get(i) + "\n";
		}
	
		return outStr;
	}
}