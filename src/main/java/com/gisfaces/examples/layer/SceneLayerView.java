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

package com.gisfaces.examples.layer;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.color.Color;
import com.gisfaces.model.layer.PortalItem;
import com.gisfaces.model.layer.SceneLayer;
import com.gisfaces.model.map.Basemap;
import com.gisfaces.model.renderer.SimpleRenderer;
import com.gisfaces.model.symbol.ColorMaterial;
import com.gisfaces.model.symbol.FillSymbol3DLayer;
import com.gisfaces.model.symbol.MeshSymbol3D;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class SceneLayerView extends MapView implements Serializable {
	private static final long serialVersionUID = -573497845346019169L;

	@PostConstruct
	public void init() {
		super.init();

		// Build the symbol layer.
		FillSymbol3DLayer symbolLayer = new FillSymbol3DLayer();
		symbolLayer.setMaterial(new ColorMaterial(new Color(244, 247, 134, 0.5)));

		// Build the symbol.
		MeshSymbol3D symbol = new MeshSymbol3D();
		symbol.getSymbolLayers().add(symbolLayer);

		// Build the renderer.
		SimpleRenderer renderer = new SimpleRenderer();
		renderer.setSymbol(symbol);

		// Build a map layer.
		SceneLayer layer = new SceneLayer();
		layer.setId("nybuildings");
		layer.setTitle("NY Buildings");
		layer.setPopupEnabled(true);
		layer.setPortalItem(new PortalItem("2e0761b9a4274b8db52c4bf34356911e"));
		layer.setRenderer(renderer);

		// Initialize the map view.
		this.getModel().setBasemap(Basemap.DARKGRAY);
		this.getModel().getViewpoint().setLatitude(40.706222);
		this.getModel().getViewpoint().setLongitude(-74.011483);
		this.getModel().getViewpoint().setZoom(16);
		this.getModel().getLayers().add(layer);
	}
}
