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
public class TemplateView implements Serializable {
	private static final long serialVersionUID = -98711666727217094L;

	private String copyright;
	private String version;

	@PostConstruct
	public void init() {
		this.copyright = this.loadCopyright();
		this.version = this.loadVersion();
	}

	public String loadCopyright() {
		StringBuffer sb = new StringBuffer();

		try {
			InputStream stream = this.getClass().getResourceAsStream("/META-INF/copyright.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(stream));

			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			br.close();
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	public String loadVersion() {
		String text = "";

		Properties props = this.loadProperties();
		String version = props.getProperty("version");
		if (version != null) {
			text = String.format("GISFaces Version %s", version);
		}

		return text;
	}

	public Properties loadProperties() {
		Properties props = new Properties();

		try {
			InputStream stream = this.getClass().getResourceAsStream("/META-INF/maven/com.gisfaces/gisfaces/pom.properties");

			props.load(stream);

			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return props;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
