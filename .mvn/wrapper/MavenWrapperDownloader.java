/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.net.*;
import java.io.*;
import java.nio.channels.*;
import java.util.Properties;

public class MavenWrapperDownloader {

	private static final Logger logger = Logger.getLogger(MavenWrapperDownloader.class);

	private static final String WRAPPER_VERSION = "3.1.0";

	/**
	 * Default URL to download the maven-wrapper.jar from, if no 'downloadUrl' is provided.
	 */
	private static final String DEFAULT_DOWNLOAD_URL =
			"https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/" + WRAPPER_VERSION
					+ "/maven-wrapper-" + WRAPPER_VERSION + ".jar";

	/**
	 * Path to the maven-wrapper.properties file, which might contain a downloadUrl property to use instead of the
	 * default one.
	 */
	private static final String MAVEN_WRAPPER_PROPERTIES_PATH = ".mvn/wrapper/maven-wrapper.properties";

	/**
	 * Path where the maven-wrapper.jar will be saved to.
	 */
	private static final String MAVEN_WRAPPER_JAR_PATH = ".mvn/wrapper/maven-wrapper.jar";

	/**
	 * Name of the property which should be used to override the default download url for the wrapper.
	 */
	private static final String PROPERTY_NAME_WRAPPER_URL = "wrapperUrl";

	public static void main(String... args) {
		logger.info("- Downloader started");
		File baseDirectory = new File(args[0]);
		logger.info("- Using base directory: " + baseDirectory.getAbsolutePath());

		// If the maven-wrapper.properties exists, read it and check if it contains a custom
		// wrapperUrl parameter.
		File mavenWrapperPropertyFile = new File(baseDirectory, MAVEN_WRAPPER_PROPERTIES_PATH);
		String url = DEFAULT_DOWNLOAD_URL;
		if (mavenWrapperPropertyFile.exists()) {
			try (FileInputStream mavenWrapperPropertyFileInputStream = new FileInputStream(mavenWrapperPropertyFile);) {
				Properties mavenWrapperProperties = new Properties();
				mavenWrapperProperties.load(mavenWrapperPropertyFileInputStream);
				url = mavenWrapperProperties.getProperty(PROPERTY_NAME_WRAPPER_URL, url);
			} catch (IOException e) {
				logger.error("- ERROR loading '" + MAVEN_WRAPPER_PROPERTIES_PATH + "'");
			}
		}
		logger.info("- Downloading from: " + url);

		File outputFile = new File(baseDirectory.getAbsolutePath(), MAVEN_WRAPPER_JAR_PATH);
		if (!outputFile.getParentFile().exists() && !outputFile.getParentFile().mkdirs()) {
			logger.error("- ERROR creating output directory '" + outputFile.getParentFile().getAbsolutePath() + "'");
		}
		logger.info("- Downloading to: " + outputFile.getAbsolutePath());
		try {
			downloadFileFromURL(url, outputFile);
			logger.info("Done");
			System.exit(0);
		} catch (Exception e) {
			logger.error("- Error downloading");
			e.printStackTrace();
			System.exit(1);
		}
	}

	private static void downloadFileFromURL(String urlString, File destination) {
		if (System.getenv("MVNW_USERNAME") != null && System.getenv("MVNW_PASSWORD") != null) {
			String username = System.getenv("MVNW_USERNAME");
			char[] password = System.getenv("MVNW_PASSWORD").toCharArray();
			Authenticator.setDefault(new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
		}
		URL website = new URL(urlString);
		try (ReadableByteChannel rbc = Channels.newChannel(website.openStream()); FileOutputStream fos = new FileOutputStream(destination)) {
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		}
	}

}
