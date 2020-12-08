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

package com.gisfaces.examples.map;

import com.gisfaces.event.MapBasemapEvent;
import com.gisfaces.event.MapClickEvent;
import com.gisfaces.event.MapExtentEvent;
import com.gisfaces.event.MapGeoLocationEvent;
import com.gisfaces.event.MapSelectEvent;
import com.gisfaces.model.map.Basemap;
import com.gisfaces.model.map.MapModel;
import com.gisfaces.utilities.JSFUtilities;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@Named
@SessionScoped
public class MapView implements Serializable {
	private static final long serialVersionUID = 848357454974222535L;

	private MapModel model;

	@PostConstruct
	public void init() {
		this.model = new MapModel();
		this.model.setBasemap(Basemap.STREETS);
		this.model.getViewpoint().setLatitude(39.828175);
		this.model.getViewpoint().setLongitude(-98.5795);
		this.model.getViewpoint().setZoom(4);
	}

	public List<SelectItem> getBasemaps() {
		return Basemap.getSelectItems();
	}

	public void doMapBasemapListener(AjaxBehaviorEvent event) {
		MapBasemapEvent e = (MapBasemapEvent) event;

		String summary = "Map Basemap Event";
		String detail = String.format("Basemap title='%s'", e.getBasemapTitle());

		System.out.println(String.format("%s: %s", summary, detail));
		JSFUtilities.addInfoMessage(summary, detail);
	}

	public void doMapClickListener(AjaxBehaviorEvent event) {
		MapClickEvent e = (MapClickEvent) event;

		String summary = "Map Click Event";
		String detail = String.format("Latitude='%s', Longitude='%s'", e.getLatitude(), e.getLongitude());

		System.out.println(String.format("%s: %s", summary, detail));
		JSFUtilities.addInfoMessage(summary, detail);
	}

	public void doMapExtentListener(AjaxBehaviorEvent event) {
		MapExtentEvent e = (MapExtentEvent) event;

		String summary = "Map Extent Event";
		String detail = String.format("Latitude='%s', Longitude='%s', Zoom='%s', Scale='%s', WKID='%s', XMin='%s', YMin='%s', XMax='%s', YMax='%s'",
				e.getLatitude(), e.getLongitude(), e.getZoom(), e.getScale(), e.getExtent().getSpatialReference().getWkid(), e.getExtent().getXmin(),
				e.getExtent().getYmin(), e.getExtent().getXmax(), e.getExtent().getYmax());

		System.out.println(String.format("%s: %s", summary, detail));
		JSFUtilities.addInfoMessage(summary, detail);
	}

	public void doMapSelectListener(AjaxBehaviorEvent event) {
		MapSelectEvent e = (MapSelectEvent) event;

		String summary = "Map Select Event";
		String detail = String.format("Layer ID='%s', SubLayer ID='%s', Graphic ID='%s', Attributes='%s'", e.getLayerId(), e.getSubLayerId(), e.getGraphicId(),
				e.getAttributesJson());

		System.out.println(String.format("%s: %s", summary, detail));
		JSFUtilities.addInfoMessage(summary, detail);
	}

	public void doMapGeoLocationListener(AjaxBehaviorEvent event) {
		MapGeoLocationEvent e = (MapGeoLocationEvent) event;

		String summary = "Map Geo-Location Event";
		String detail = String.format(
				"Latitude='%s', Longitude='%s', Heading='%s', Speed='%s', Altitude='%s', Accuracy='%s', Altitude Accuracy='%s', Timestamp='%s'",
				e.getLatitude(), e.getLongitude(), e.getHeading(), e.getSpeed(), e.getAltitude(), e.getAccuracy(), e.getAltitudeAccuracy(),
				e.getTimestampDate());

		System.out.println(String.format("%s: %s", summary, detail));
		JSFUtilities.addInfoMessage(summary, detail);
	}

	public void resetActionListener(ActionEvent event) {
		System.out.println("Map view reset action listener fired.");
		this.init();
	}

	public MapModel getModel() {
		return model;
	}

	public void setModel(MapModel model) {
		this.model = model;
	}
}
