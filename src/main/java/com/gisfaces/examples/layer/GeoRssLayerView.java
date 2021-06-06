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
import com.gisfaces.model.layer.GeoRSSLayer;
import com.gisfaces.model.map.MapType;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class GeoRssLayerView extends MapView implements Serializable {
	private static final long serialVersionUID = 6680927620368200395L;

	@PostConstruct
	public void init() {
		super.init();

		// Build a map layer.
		GeoRSSLayer layer = new GeoRSSLayer();
		layer.setId("wyoming");
		layer.setTitle("Wyoming");
		layer.setUrl("https://arcgis.github.io/arcgis-samples-javascript/sample-data/layers-georss/sample-georss.xml");

		// Initialize the map view.
		this.getModel().setMapType(MapType.TWO_D);
		this.getModel().getViewpoint().setLatitude(43.138893);
		this.getModel().getViewpoint().setLongitude(-107.689363);
		this.getModel().getViewpoint().setZoom(6);
		this.getModel().getLayers().add(layer);
	}
}
