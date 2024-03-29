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
import com.gisfaces.model.layer.PointCloudLayer;
import com.gisfaces.model.renderer.FixedSizePointSizeAlgorithm;
import com.gisfaces.model.renderer.PointCloudRGBRenderer;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class PointCloudLayerView extends MapView implements Serializable {
	private static final long serialVersionUID = -3320330687619029441L;

	@PostConstruct
	public void init() {
		super.init();

		// Build the optional point cloud renderer.
		PointCloudRGBRenderer renderer = new PointCloudRGBRenderer();
		renderer.setField("RGB");
		renderer.setPointsPerInch(25);
		renderer.setPointSizeAlgorithm(new FixedSizePointSizeAlgorithm(false, 5));

		// Build a map layer.
		PointCloudLayer layer = new PointCloudLayer("barnegat",
				"https://tiles.arcgis.com/tiles/V6ZHFr6zdgNZuVG0/arcgis/rest/services/BARNEGAT_BAY_LiDAR_UTM/SceneServer");
		layer.setRenderer(renderer);

		// Initialize the map view.
		this.getModel().getViewpoint().setLatitude(39.772073);
		this.getModel().getViewpoint().setLongitude(-74.121410);
		this.getModel().getViewpoint().setZoom(12);
		this.getModel().getLayers().add(layer);
	}
}
