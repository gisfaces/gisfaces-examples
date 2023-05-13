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

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.layer.MapImageLayer;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ReferenceView extends MapView implements Serializable {
	private static final long serialVersionUID = -666851330961452424L;

	private MapImageLayer warnings;
	private MapImageLayer radar;
	private MapImageLayer graticule;

	public ReferenceView() {
		super();
	}

	@PostConstruct
	public void init() {
		super.init();

		// Build a map layer.
		warnings = new MapImageLayer("warnings", "https://idpgis.ncep.noaa.gov/arcgis/rest/services/NWS_Forecasts_Guidance_Warnings/watch_warn_adv/MapServer");
		warnings.setTitle("Weather Warnings");
		warnings.setOpacity(0.5d);
		warnings.setRefreshInterval(5.0d);

		// Build a map layer.
		radar = new MapImageLayer("radar", "https://nowcoast.noaa.gov/arcgis/rest/services/nowcoast/radar_meteo_imagery_nexrad_time/MapServer");
		radar.setTitle("Doppler Radar");
		radar.setOpacity(0.5d);
		radar.setRefreshInterval(5.0);

		// Build a map layer.
		graticule = new MapImageLayer("graticule", "https://gis.ngdc.noaa.gov/arcgis/rest/services/graticule/MapServer");
		graticule.setTitle("Graticule Lines");
		graticule.setOpacity(0.5d);

		// Initialize the map view.
		this.getModel().getLayers().add(warnings);
		this.getModel().getLayers().add(radar);
		this.getModel().getLayers().add(graticule);
	}

	public MapImageLayer getWarnings() {
		return warnings;
	}

	public void setWarnings(MapImageLayer warnings) {
		this.warnings = warnings;
	}

	public MapImageLayer getRadar() {
		return radar;
	}

	public void setRadar(MapImageLayer radar) {
		this.radar = radar;
	}

	public MapImageLayer getGraticule() {
		return graticule;
	}

	public void setGraticule(MapImageLayer graticule) {
		this.graticule = graticule;
	}
}
