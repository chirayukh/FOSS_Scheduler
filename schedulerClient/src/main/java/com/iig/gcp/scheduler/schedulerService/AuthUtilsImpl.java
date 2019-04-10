package com.iig.gcp.scheduler.schedulerService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.cloudkms.v1.CloudKMS;
import com.google.api.services.cloudkms.v1.CloudKMSScopes;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Component
public class AuthUtilsImpl implements AuthUtils {

	/*
	 * @see com.iig.gcp.scheduler.schedulerService.AuthUtils#getStorageService(com.google.auth.Credentials, java.lang.String)
	 */
	/**
	 * @return Storage
	 * @param credentials
	 * @param google_project
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@Override
	public Storage getStorageService(Credentials credentials,String google_project) throws FileNotFoundException, IOException {

		Storage storage = StorageOptions.newBuilder().setCredentials(credentials).setProjectId(google_project).build().getService();
		return storage;

	}

	/*
	 * @see com.iig.gcp.scheduler.schedulerService.AuthUtils#getCredentials(java.lang.String)
	 */
	/**
	 * @return Credentials
	 * @param jsonCredential
	 * @throws IOException
	 */
	@Override
	public Credentials getCredentials(String jsonCredential) throws IOException {

		InputStream in = IOUtils.toInputStream(jsonCredential, "UTF-8");
		Credentials credentials = GoogleCredentials.fromStream(in);
		return credentials;
	}

	/*
	 * @see com.iig.gcp.scheduler.schedulerService.AuthUtils#getKmsService(com.google.api.client.googleapis.auth.oauth2.GoogleCredential)
	 */
	/**
	 * @return CloudKMS
	 * @param credential
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@Override
	public CloudKMS getKmsService(GoogleCredential credential) throws FileNotFoundException, IOException {

		HttpTransport transport = new NetHttpTransport();
		JsonFactory jsonFactory = new JacksonFactory();

		if (credential.createScopedRequired()) {
			credential = credential.createScoped(CloudKMSScopes.all());
		}

		return new CloudKMS.Builder(transport, jsonFactory, credential).setApplicationName("CloudKMS CryptFile")
				.build();

	}

	/*
	 * @see com.iig.gcp.scheduler.schedulerService.AuthUtils#getGoogleCredential(java.lang.String)
	 */
	/**
	 * @return GoogleCredential
	 * @param jsonCredential
	 * @throws IOException
	 */
	@Override
	public GoogleCredential getGoogleCredential(String jsonCredential) throws IOException {

		InputStream in = IOUtils.toInputStream(jsonCredential, "UTF-8");
		GoogleCredential credential = GoogleCredential.fromStream(in);
		return credential;

	}

}
