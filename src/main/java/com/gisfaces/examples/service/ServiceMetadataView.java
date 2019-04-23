package com.gisfaces.examples.service;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.service.ServiceDirectoryMetadata;
import com.gisfaces.model.service.ServiceDirectoryMetadataBuilder;
import com.gisfaces.utilities.JSFUtilities;

@Named
@SessionScoped
public class ServiceMetadataView extends MapView implements Serializable
{
	private static final long serialVersionUID = 9135707557221898353L;

	private String url;
	private ServiceDirectoryMetadata serviceDirectory;

	@PostConstruct
	public void init()
	{
		this.url = "http://sampleserver1.arcgisonline.com/ArcGIS/rest/services/PublicSafety";
		this.serviceDirectory = null;
	}

	public void doSearchButtonActionListener(ActionEvent event)
	{
		try
		{
			this.serviceDirectory = null;

			ServiceDirectoryMetadataBuilder builder = new ServiceDirectoryMetadataBuilder();
			this.serviceDirectory = builder.build(this.url);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			JSFUtilities.addErrorMessage("An error occurred querying map serivce.", e.getMessage());
		}
	}

	public void doResetButtonActionListener(ActionEvent event)
	{
		this.init();
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public ServiceDirectoryMetadata getServiceDirectory()
	{
		return serviceDirectory;
	}

	public void setServiceDirectory(ServiceDirectoryMetadata serviceDirectory)
	{
		this.serviceDirectory = serviceDirectory;
	}
}
