package StepDefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		StepDefinition sd = new StepDefinition();
		
		if(StepDefinition.placeID1==null) {
		sd.add_place_payload("Travin", "Canada", "Chinese");
		sd.user_calls_with_htpp_request("AddPlaceAPI", "POST");
		sd.verify_place_id_created_with_maps_to("Travin", "getPlaceAPI");
		}
	}

}
