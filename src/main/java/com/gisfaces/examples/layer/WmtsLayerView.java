package com.gisfaces.examples.layer;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.layer.WMTSLayer;
import com.gisfaces.model.layer.WMTSSubLayer;
import com.gisfaces.model.map.MapType;

@Named
@SessionScoped
public class WmtsLayerView extends MapView implements Serializable
{
	private static final long serialVersionUID = 6203168805569363387L;

	@PostConstruct
	public void init()
	{
		super.init();

		// Build a map layer.
		WMTSLayer layer = new WMTSLayer();
		layer.setId("earthdata");
		layer.setTitle("Earthdata");
		layer.setCopyright("Earthdata by NASA");
		layer.setUrl("https://gibs.earthdata.nasa.gov/wmts/epsg4326/best");
		layer.setActiveLayer(new WMTSSubLayer("SRTM_Color_Index"));

		// Initialize the map view.
		this.getModel().setMapType(MapType.TWO_D);
		this.getModel().getLayers().add(layer);
	}
}
