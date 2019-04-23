package com.gisfaces.examples.layer;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.layer.ImageryLayer;
import com.gisfaces.model.layer.ImageryLayerFormat;
import com.gisfaces.model.map.PopupTemplate;

@Named
@SessionScoped
public class ImageryLayerView extends MapView implements Serializable
{
	private static final long serialVersionUID = -5131266274437559811L;

	@PostConstruct
	public void init()
	{
		super.init();

		// Build a map layer.
		ImageryLayer layer = new ImageryLayer();
		layer.setId("nlcd");
		layer.setTitle("National Land Cover Database");
		layer.setFormat(ImageryLayerFormat.JPGPNG.toString());
		layer.setPopupEnabled(true);
		layer.setPopupTemplate(new PopupTemplate("{Raster.ClassName}", "{*}"));
		layer.setUrl("https://sampleserver6.arcgisonline.com/arcgis/rest/services/NLCDLandCover2001/ImageServer");

		// Initialize the map view.
		this.getModel().getLayers().add(layer);
	}
}
