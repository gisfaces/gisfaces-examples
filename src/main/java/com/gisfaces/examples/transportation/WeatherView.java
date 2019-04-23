package com.gisfaces.examples.transportation;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.layer.MapImageLayer;

@Named
@SessionScoped
public class WeatherView extends MapView implements Serializable
{
	private static final long serialVersionUID = -666851330961452424L;

	private MapImageLayer warnings;
	private MapImageLayer radar;

	public WeatherView()
	{
		super();
	}

	@PostConstruct
	public void init()
	{
		super.init();

		// Build a map layer.
		warnings = new MapImageLayer("warnings", "https://idpgis.ncep.noaa.gov/arcgis/rest/services/NWS_Forecasts_Guidance_Warnings/watch_warn_adv/MapServer");
		warnings.setTitle("Weather Warnings");
		warnings.setOpacity(0.5d);
		warnings.setRefreshInterval(5.0d);

		// Build a map layer.
		radar = new MapImageLayer("radar", "https://nowcoast.noaa.gov/arcgis/rest/services/nowcoast/radar_meteo_imagery_nexrad_time/MapServer");
		radar.setTitle("Doppler Radar");
		radar.setOpacity(0.5d);
		radar.setRefreshInterval(5.0);

		// Initialize the map view.
		this.getModel().getLayers().add(warnings);
		this.getModel().getLayers().add(radar);
	}

	public MapImageLayer getWarnings()
	{
		return warnings;
	}

	public void setWarnings(MapImageLayer warnings)
	{
		this.warnings = warnings;
	}

	public MapImageLayer getRadar()
	{
		return radar;
	}

	public void setRadar(MapImageLayer radar)
	{
		this.radar = radar;
	}
}
