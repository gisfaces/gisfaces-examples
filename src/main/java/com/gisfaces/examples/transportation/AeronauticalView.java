package com.gisfaces.examples.transportation;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.model.layer.MapImageLayer;

@Named
@SessionScoped
public class AeronauticalView extends WeatherView implements Serializable
{
	private static final long serialVersionUID = -6728584306790478248L;

	private MapImageLayer airports;
	private MapImageLayer runways;

	public AeronauticalView()
	{
		super();
	}

	@PostConstruct
	public void init()
	{
		super.init();

		// Build a map layer.
		airports = new MapImageLayer("airports", "https://maps.bts.dot.gov/services/rest/services/NTAD/Airports/MapServer");

		// Build a map layer.
		runways = new MapImageLayer("runways", "https://maps.bts.dot.gov/services/rest/services/NTAD/Runways/MapServer");

		// Some services may require the included proxy to be enabled.
		this.getModel().getConfiguration().setProxyEnabled(true);

		// Initialize the map view.
		this.getModel().getLayers().add(airports);
		this.getModel().getLayers().add(runways);
	}

	public MapImageLayer getAirports()
	{
		return airports;
	}

	public void setAirports(MapImageLayer airports)
	{
		this.airports = airports;
	}

	public MapImageLayer getRunways()
	{
		return runways;
	}

	public void setRunways(MapImageLayer runways)
	{
		this.runways = runways;
	}
}
