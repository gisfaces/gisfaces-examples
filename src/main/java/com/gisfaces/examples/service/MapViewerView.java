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

package com.gisfaces.examples.service;

import com.gisfaces.examples.transportation.ReferenceView;
import com.gisfaces.model.layer.Layer;
import com.gisfaces.model.layer.MapImageLayer;
import com.gisfaces.utilities.JSFUtilities;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

@Named
@SessionScoped
public class MapViewerView extends ReferenceView implements Serializable {
	private static final long serialVersionUID = 2590893270488839413L;

	@PostConstruct
	public void init() {
		super.init();

		// Build a map layer.
		MapImageLayer airports = new MapImageLayer("airports", "https://geo.dot.gov/server/rest/services/NTAD/Airports/MapServer");
		airports.setTitle("Airports");
		airports.setOpacity(1.0d);

		// Build a map layer.
		MapImageLayer runways = new MapImageLayer("runways", "https://geo.dot.gov/server/rest/services/NTAD/Runways/MapServer");
		runways.setTitle("Runways");
		runways.setOpacity(1.0d);

		// Some services may require the included proxy to be enabled.
		this.getModel().getConfiguration().setProxyEnabled(true);

		// Initialize the map view.
		this.getModel().getLayers().add(airports);
		this.getModel().getLayers().add(runways);
	}

	public void onRowEdit(RowEditEvent event) {
		// Get the edited layer.
		Layer layer = (Layer) event.getObject();

		// Display a message.
		String summary = "Layer Edit Complete";
		String detail = String.format("ID='%s', Title='%s', Visible='%s', Opacity='%s'", layer.getId(), layer.getTitle(), layer.getVisible(),
				layer.getOpacity());
		JSFUtilities.addInfoMessage(summary, detail);
	}

	public void doUpButtonAction(Layer layer) {
		// Get the map layer list.
		List<Layer> layers = this.getModel().getLayers();

		// Get the index of the selected layer.
		int index = layers.lastIndexOf(layer);

		// Validate the layer can be moved up.
		if (index >= 1) {
			// Move the layer up.
			layers.remove(index);
			layers.add(--index, layer);

			// Display a message.
			String summary = "Layer Reorder Complete";
			String detail = String.format("Layer ID='%s', Title='%s' moved to index '%d'.", layer.getId(), layer.getTitle(), index);
			JSFUtilities.addInfoMessage(summary, detail);
		}
	}

	public void doDownButtonAction(Layer layer) {
		// Get the map layer list.
		List<Layer> layers = this.getModel().getLayers();

		// Get the index of the selected layer.
		int index = layers.lastIndexOf(layer);

		// Validate the layer can be moved down.
		if (index < (layers.size() - 1)) {
			// Move the layer down.
			layers.remove(index);
			layers.add(++index, layer);

			// Display a message.
			String summary = "Layer Reorder Complete";
			String detail = String.format("Layer ID='%s', Title='%s' moved to index '%d'.", layer.getId(), layer.getTitle(), index);
			JSFUtilities.addInfoMessage(summary, detail);
		}
	}

	public void doAddButtonActionListener(ActionEvent event) {
		// Add an empty layer for editing.
		this.getModel().getLayers().add(new MapImageLayer());

		// Display a message.
		JSFUtilities.addInfoMessage("Layer Added", "Please edit the new layer details.");
	}

	public void doDeleteButtonAction(Layer layer) {
		// Remove the layer from the list.
		this.getModel().getLayers().remove(layer);

		// Display a message.
		String summary = "Layer Removed";
		String detail = String.format("ID='%s', Title='%s'", layer.getId(), layer.getTitle());
		JSFUtilities.addInfoMessage(summary, detail);
	}
}
