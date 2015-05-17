/*
 * Copyright 2009 Prime Technology.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.miselmesnjak.sbpft1.domain;

import java.io.Serializable;

public class Car implements Serializable {

	private static final long serialVersionUID = 3940450471250634474L;
	
	public String id;
	public int year;
	public String brand;
	public String color;
    public int price;
    public boolean sold;

    public Car(String model, int year, String manufacturer, String color) {
		this.id = model;
		this.year = year;
		this.brand = manufacturer;
		this.color = color;
	}
	
	public Car(String model, int year, String manufacturer, String color, int price, boolean sold) {
		this(model, year, manufacturer, color);
        this.price = price;
        this.sold = sold;
	}

	public String getId() {
		return id;
	}

	public void setId(String model) {
		this.id = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String manufacturer) {
		this.brand = manufacturer;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

     public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }
    
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		
		if(!(obj instanceof Car))
			return false;
		
		Car compare = (Car) obj;
	
		return compare.id.equals(this.id);
	}

	@Override
	public int hashCode() {
		int hash = 1;
		
	    return hash * 31 + id.hashCode();
	}

    @Override
    public String toString() {
        return "Car{" + "id=" + id + ", year=" + year + ", brand=" + brand + ", color=" + color + ", price=" + price + '}';
    }
}