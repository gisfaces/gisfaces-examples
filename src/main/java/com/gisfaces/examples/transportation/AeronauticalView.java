/*
 * The MIT License
 *
 * Copyright (c) 2013-2023 Chris Duncan (cduncan@gisfaces.com)
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
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class AeronauticalView extends ReferenceView implements Serializable {
	private static final long serialVersionUID = -6728584306790478248L;

	private MapImageLayer airports;
	private MapImageLayer runways;

	public AeronauticalView() {
		super();
	}

	@PostConstruct
	public void init() {
		super.init();

		// Build a map layer.
		airports = new MapImageLayer("airports", "https://geo.dot.gov/server/rest/services/NTAD/Airports/MapServer");

		// Build a map layer.
		runways = new MapImageLayer("runways", "https://geo.dot.gov/server/rest/services/NTAD/Runways/MapServer");

		// Some services may require the included proxy to be enabled.
		this.getModel().getConfiguration().setProxyEnabled(true);

		// Initialize the map view.
		this.getModel().getLayers().add(airports);
		this.getModel().getLayers().add(runways);
	}

	public MapImageLayer getAirports() {
		return airports;
	}

	public void setAirports(MapImageLayer airports) {
		this.airports = airports;
	}

	public MapImageLayer getRunways() {
		return runways;
	}

	public void setRunways(MapImageLayer runways) {
		this.runways = runways;
	}
}
