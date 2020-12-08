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

package com.gisfaces.examples.layer;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.layer.TileLayer;
import com.gisfaces.model.map.Basemap;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class TileLayerView extends MapView implements Serializable {
	private static final long serialVersionUID = -8628938523118785144L;

	@PostConstruct
	public void init() {
		super.init();

		// Build a map layer.
		TileLayer housing = new TileLayer();
		housing.setId("nyhousing");
		housing.setTitle("NY Housing");
		housing.setUrl("https://tiles.arcgis.com/tiles/nGt4QxSblgDfeJn9/arcgis/rest/services/New_York_Housing_Density/MapServer");
		housing.setMaxScale(0);

		// Build a map layer.
		TileLayer streets = new TileLayer();
		streets.setId("usstreets");
		streets.setTitle("US Streets");
		streets.setUrl("https://server.arcgisonline.com/ArcGIS/rest/services/Reference/World_Transportation/MapServer");

		// Initialize the map view.
		this.getModel().setBasemap(Basemap.LIGHTGRAY);
		this.getModel().getViewpoint().setLatitude(40.664875);
		this.getModel().getViewpoint().setLongitude(-73.988606);
		this.getModel().getViewpoint().setZoom(11);
		this.getModel().getLayers().add(housing);
		this.getModel().getLayers().add(streets);
	}
}
