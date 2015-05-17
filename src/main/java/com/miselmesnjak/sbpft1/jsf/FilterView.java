package com.miselmesnjak.sbpft1.jsf;


import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.miselmesnjak.sbpft1.domain.Car;

@ManagedBean
@SessionScoped
public class FilterView implements Serializable {

	private static final long serialVersionUID = 4709942705561595897L;
	private static final Logger LOG = LoggerFactory.getLogger(FilterView.class);

	private List<Car> cars;
	private List<Car> filteredCars;

	private String fld = "test value";

	@ManagedProperty("#{carService}")
	private CarService service;

	@PostConstruct
	public void init() {
		LOG.debug("... FilterView init ...");
		cars = this.service.createCars(10);
	}

	@SuppressWarnings("unchecked")
	public boolean filterByPrice(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim();
		if (filterText == null || filterText.equals("")) {
			return true;
		}

		if (value == null) {
			return false;
		}

		return ((Comparable<Integer>) value).compareTo(Integer
				.valueOf(filterText)) > 0;
	}
	
    public List<String> getBrands() {
        return service.getBrands();
    }
     
    public List<String> getColors() {
        return service.getColors();
    }
    
	public List<Car> getCars() {
		return cars;
	}

	public List<Car> getFilteredCars() {
		return filteredCars;
	}

	public void setFilteredCars(List<Car> filteredCars) {
		this.filteredCars = filteredCars;
	}

	public String getFld() {
		LOG.debug("... getting fld ...");
		return fld;
	}

	public void setService(CarService service) {
		LOG.debug("... setting service ...");
		this.service = service;
	}
}
