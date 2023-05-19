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
import com.gisfaces.model.layer.StreamLayer;
import com.gisfaces.model.layer.StreamLayerPurgeOptions;
import com.gisfaces.model.map.Basemap;
import com.gisfaces.model.symbol.LabelClass;
import com.gisfaces.model.symbol.LabelExpressionInfo;
import com.gisfaces.model.symbol.TextSymbol;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class StreamLayerView extends MapView implements Serializable {
	private static final long serialVersionUID = -1732781736877121348L;

	@PostConstruct
	public void init() {
		super.init();

		// Build a symbol for the label.
		TextSymbol symbol = new TextSymbol();
		symbol.setColor(new Color("#FF8C00"));
		symbol.setHaloColor(new Color("#808080"));
		symbol.setHaloSize("1px");

		// Build a label for the specified fields.
		LabelClass label = new LabelClass();
		label.setLabelExpressionInfo(new LabelExpressionInfo("$feature.id"));
		label.setSymbol(symbol);

		// Build a map layer.
		StreamLayer layer = new StreamLayer();
		layer.setId("labus");
		layer.setTitle("LA Bus Stream Layer");
		layer.setPopupEnabled(true);
		layer.setPurgeOptions(new StreamLayerPurgeOptions(1000, 10));
		layer.setUrl("https://geoeventsample1.esri.com:6443/arcgis/rest/services/LABus/StreamServer");
		layer.setLabelsVisible(true);
		layer.setLabelingInfo(new LabelClass[] { label });

		// Initialize the map view.
		this.getModel().setBasemap(Basemap.GRAY_VECTOR);
		this.getModel().getViewpoint().setLatitude(34.095316);
		this.getModel().getViewpoint().setLongitude(-118.354697);
		this.getModel().getViewpoint().setZoom(10);
		this.getModel().getLayers().add(layer);
	}
}
