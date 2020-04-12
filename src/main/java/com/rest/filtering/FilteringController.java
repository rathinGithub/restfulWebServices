package com.rest.filtering;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public SomeBean someBean() {
		return new SomeBean("value1", "value2", "value3");
	}

	@GetMapping("/filter-dynamic")
	public MappingJacksonValue someDynamicBean() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("someDynamicFilter", filter);
		MappingJacksonValue jacksonValue = new MappingJacksonValue(someBean);
		jacksonValue.setFilters(filters);	
        return jacksonValue;
	}
}
