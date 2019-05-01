package com.gisfaces.examples.component;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import com.gisfaces.event.MapGeoLocationEvent;
import com.gisfaces.utilities.JSFUtilities;

@Named
@SessionScoped
public class GeoLocationView implements Serializable
{
	private static final long serialVersionUID = -8866985012091642915L;

	private boolean watch;
	private boolean accuracy;
	private int timeout;
	private int maximumAge;
	private MapGeoLocationEvent event;

	@PostConstruct
	public void init()
	{
		this.watch = true;
		this.accuracy = true;
		this.timeout = 60000;
		this.maximumAge = 0;
		this.event = null;
	}

	public void doGeoLocationListener(AjaxBehaviorEvent event)
	{
		this.event = (MapGeoLocationEvent) event;

		if (this.event != null)
		{
			String summary = "Geo-location event.";
			String detail = String.format("Timestamp='%s', Latitude='%s', Longitude='%s', Heading='%s', Speed='%s', Altitude='%s'", this.event.getTimestampDate(), this.event.getLatitude(), this.event.getLongitude(), this.event.getHeading(), this.event.getSpeed(), this.event.getAltitude());
			JSFUtilities.addInfoMessage(summary, detail);
		}
	}

	public boolean isWatch()
	{
		return watch;
	}

	public void setWatch(boolean watch)
	{
		this.watch = watch;
	}

	public boolean isAccuracy()
	{
		return accuracy;
	}

	public void setAccuracy(boolean accuracy)
	{
		this.accuracy = accuracy;
	}

	public int getTimeout()
	{
		return timeout;
	}

	public void setTimeout(int timeout)
	{
		this.timeout = timeout;
	}

	public int getMaximumAge()
	{
		return maximumAge;
	}

	public void setMaximumAge(int maximumAge)
	{
		this.maximumAge = maximumAge;
	}

	public MapGeoLocationEvent getEvent()
	{
		return event;
	}

	public void setEvent(MapGeoLocationEvent event)
	{
		this.event = event;
	}
}
