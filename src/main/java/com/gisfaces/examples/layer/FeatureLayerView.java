package com.gisfaces.examples.layer;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.color.Color;
import com.gisfaces.model.layer.FeatureLayer;
import com.gisfaces.model.symbol.LabelClass;
import com.gisfaces.model.symbol.LabelExpressionInfo;
import com.gisfaces.model.symbol.TextSymbol;

@Named
@SessionScoped
public class FeatureLayerView extends MapView implements Serializable
{
	private static final long serialVersionUID = -5505438571864601823L;

	@PostConstruct
	public void init()
	{
		super.init();

		// Build a symbol for the label.
		TextSymbol symbol = new TextSymbol();
		symbol.setColor(new Color("#008CFF"));
		symbol.setHaloColor(new Color("#000000"));
		symbol.setHaloSize("1px");

		// Build a label for the specified fields.
		LabelClass label = new LabelClass();
		label.setLabelExpressionInfo(new LabelExpressionInfo("$feature.Cmn_Name"));
		label.setSymbol(symbol);

		// Build a map layer.
		FeatureLayer layer = new FeatureLayer("trees", "https://services.arcgis.com/V6ZHFr6zdgNZuVG0/arcgis/rest/services/Landscape_Trees/FeatureServer/0");
		layer.setLabelsVisible(true);
		layer.setLabelingInfo(new LabelClass[] { label } );

		// Initialize the map view.
		this.getModel().getViewpoint().setLatitude(35.612362);
		this.getModel().getViewpoint().setLongitude(-82.442788);
		this.getModel().getViewpoint().setZoom(16);
		this.getModel().getLayers().add(layer);
	}
}
