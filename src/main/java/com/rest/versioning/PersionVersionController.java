package com.rest.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersionVersionController {
	
	//URI VERSIONING
	@GetMapping("/v1/person")
	PersonV1 personV1() {
		return new PersonV1("Rathin Maheswaran");
	}

	@GetMapping("/v2/person")
	PersonV2 personV2() {
		return new PersonV2(new Name("Rathin","Maheswaran"));
	}
	
	//REQUEST PARAMETER VERSIONING
	@GetMapping(value="person",params = "version=1")
	PersonV1 personParamV1() {
		return new PersonV1("Rathin Maheswaran");
	}
	
	@GetMapping(value="person",params = "version=2")
	PersonV2 personParamV2() {
		return new PersonV2(new Name("Rathin","Maheswaran"));
	}
	
	//HEADER VERSIONING
	@GetMapping(value="person/header",headers = "X_API_VERSION=1")
	PersonV1 personHeaderV1() {
		return new PersonV1("Rathin Maheswaran");
	}
	
	@GetMapping(value="person/header",headers = "X_API_VERSION=2")
	PersonV2 personHeaderV2() {
		return new PersonV2(new Name("Rathin","Maheswaran"));
	}
	
	//MIME TYPE OR ACCEPT HEADER VERSIONING
	@GetMapping(value="person/produces",produces = "application/vnd.company.app-v1+json")
	PersonV1 personProducesV1() {
		return new PersonV1("Rathin Maheswaran");
	}
	
	@GetMapping(value="person/produces",produces = "application/vnd.company.app-v2+json")
	PersonV2 personProducesV2() {
		return new PersonV2(new Name("Rathin","Maheswaran"));
	}
}
