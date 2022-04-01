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

package com.gisfaces.examples.edit;

import com.gisfaces.event.MapGraphicCreateEvent;
import com.gisfaces.event.MapGraphicDeleteEvent;
import com.gisfaces.event.MapGraphicUpdateEvent;
import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.layer.GraphicsLayer;
import com.gisfaces.model.map.MapDimension;
import com.gisfaces.utilities.JSFUtilities;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

@Named
@SessionScoped
public class GraphicsLayerEditView extends MapView implements Serializable {
	private static final long serialVersionUID = 875780238824377582L;

	@PostConstruct
	public void init() {
		super.init();

		// Build the graphics layer.
		GraphicsLayer layer = new GraphicsLayer();
		layer.setId("graphics");
		layer.setEditable(true);
		layer.setTitle("Graphics Layer Edit");

		// Initialize the map view.
		this.getModel().setMapDimension(MapDimension.TWO_D);
		this.getModel().getViewpoint().setLatitude(39.828175);
		this.getModel().getViewpoint().setLongitude(-98.5795);
		this.getModel().getViewpoint().setZoom(4);
		this.getModel().getLayers().add(layer);
	}

	public void doMapGraphicCreateListener(AjaxBehaviorEvent event) {
		MapGraphicCreateEvent e = (MapGraphicCreateEvent) event;

		String summary = "Map Graphic Created";
		String detail = String.format("Layer ID='%s', Graphic ID='%s', Geometry='%s'.", e.getLayerId(), e.getGraphicId(), e.getGeometry());

		System.out.println(String.format("%s: %s", summary, detail));
		JSFUtilities.addInfoMessage(summary, detail);
	}

	public void doMapGraphicUpdateListener(AjaxBehaviorEvent event) {
		MapGraphicUpdateEvent e = (MapGraphicUpdateEvent) event;

		String summary = "Map Graphic Updated";
		String detail = String.format("Layer ID='%s', Graphic ID='%s', Geometry='%s'.", e.getLayerId(), e.getGraphicId(), e.getGeometry());

		System.out.println(String.format("%s: %s", summary, detail));
		JSFUtilities.addInfoMessage(summary, detail);
	}

	public void doMapGraphicDeleteListener(AjaxBehaviorEvent event) {
		MapGraphicDeleteEvent e = (MapGraphicDeleteEvent) event;

		String summary = "Map Graphic Deleted";
		String detail = String.format("Layer ID='%s', Graphic ID='%s'.", e.getLayerId(), e.getGraphicId());

		System.out.println(String.format("%s: %s", summary, detail));
		JSFUtilities.addInfoMessage(summary, detail);
	}
}
