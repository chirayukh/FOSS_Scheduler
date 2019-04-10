package com.iig.gcp.scheduler.schedulerService;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.cloudkms.v1.CloudKMS;
import com.google.auth.Credentials;
import com.google.cloud.storage.Storage;

public interface AuthUtils {

	public Storage getStorageService(Credentials credentials, String google_project)
			throws FileNotFoundException, IOException;

	public CloudKMS getKmsService(GoogleCredential credential) throws FileNotFoundException, IOException;

	public Credentials getCredentials(String jsonCredential) throws IOException;

	public GoogleCredential getGoogleCredential(String jsonCredential) throws IOException;

}
