package com.gisfaces.examples.template;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class TemplateView implements Serializable
{
	private static final long serialVersionUID = -98711666727217094L;

	private String copyright;
	private String version;

	@PostConstruct
	public void init()
	{
		this.copyright = this.loadCopyright();
		this.version = this.loadVersion();
	}

	public String loadCopyright()
	{
		StringBuffer sb = new StringBuffer();

		try
		{
			InputStream stream = this.getClass().getResourceAsStream("/META-INF/copyright.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(stream));

			String line;
			while ((line = br.readLine()) != null)
			{
				sb.append(line);
			}

			br.close();
			stream.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return sb.toString();
	}

	public String loadVersion()
	{
		String text = "";

		Properties props = this.loadProperties();
		String version = props.getProperty("version");
		if (version != null)
		{
			text = String.format("GISFaces Version %s", version);
		}

		return text;
	}

	public Properties loadProperties()
	{
		Properties props = new Properties();

		try
		{
			InputStream stream = this.getClass().getResourceAsStream("/META-INF/maven/com.gisfaces/gisfaces/pom.properties");

			props.load(stream);

			stream.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return props;
	}

	public String getCopyright()
	{
		return copyright;
	}

	public void setCopyright(String copyright)
	{
		this.copyright = copyright;
	}

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version) 
	{
		this.version = version;
	}
}
