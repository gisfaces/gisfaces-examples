/*
 * The MIT License
 *
 * Copyright (c) 2013-2021 Chris Duncan (cduncan@gisfaces.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.gisfaces.examples.transportation;

import com.gisfaces.model.layer.MapImageLayer;
import com.gisfaces.model.map.Basemap;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class MaritimeView extends ReferenceView implements Serializable {
	private static final long serialVersionUID = 2224675449393427902L;

	private MapImageLayer waterways;
	private MapImageLayer ports;
	private MapImageLayer locks;
	private MapImageLayer dams;

	@PostConstruct
	public void init() {
		super.init();

		// Build the map layers.
		waterways = new MapImageLayer("waterways", "https://maps.bts.dot.gov/services/rest/services/NTAD/Navigable_Waterway_Lines/MapServer");
		ports = new MapImageLayer("ports", "https://maps.bts.dot.gov/services/rest/services/NTAD/Ports/MapServer");
		locks = new MapImageLayer("locks", "https://maps.bts.dot.gov/services/rest/services/NTAD/Locks/MapServer");
		dams = new MapImageLayer("dams", "https://maps.bts.dot.gov/services/rest/services/NTAD/Dams/MapServer");

		// Set the map basemap.
		this.getModel().setBasemap(Basemap.OCEANS);

		// Some services may require the included proxy to be enabled.
		this.getModel().getConfiguration().setProxyEnabled(true);

		// Add the map layers.
		this.getModel().getLayers().add(waterways);
		this.getModel().getLayers().add(ports);
		this.getModel().getLayers().add(locks);
		this.getModel().getLayers().add(dams);
	}

	public MapImageLayer getWaterways() {
		return waterways;
	}

	public void setWaterways(MapImageLayer waterways) {
		this.waterways = waterways;
	}

	public MapImageLayer getPorts() {
		return ports;
	}

	public void setPorts(MapImageLayer ports) {
		this.ports = ports;
	}

	public MapImageLayer getLocks() {
		return locks;
	}

	public void setLocks(MapImageLayer locks) {
		this.locks = locks;
	}

	public MapImageLayer getDams() {
		return dams;
	}

	public void setDams(MapImageLayer dams) {
		this.dams = dams;
	}
}
