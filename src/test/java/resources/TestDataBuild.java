package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.GoogleAPIBody;
import pojo.Location;

public class TestDataBuild {

	public GoogleAPIBody testData(String name, String address, String language) {
		GoogleAPIBody gb = new GoogleAPIBody();
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		gb.setLocation(loc);
		gb.setAccuracy(50);
		gb.setName(name);
		gb.setPhone_number("(+91) 983 893 3937");
		gb.setAddress(address);
		gb.setWebsite("http://google.com");
		gb.setLanguage(language);

		List<String> typ = new ArrayList<String>();
		typ.add("shoe park");
		typ.add("shop");
		gb.setTypes(typ);

		return gb;
	}
	
	public String deleteBody(String placeID) {
		return "{\r\n"
				+ "    \"place_id\":\""+placeID+"\"\r\n"
				+ "}\r\n"
				+ "";
	}

}
