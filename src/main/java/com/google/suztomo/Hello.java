package com.google.suztomo;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.iam.v1.Iam;
import com.google.api.services.iam.v1.model.ServiceAccount;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class Hello {
  public static void main(String[] arguments) throws GeneralSecurityException, IOException {
    String serviceAccountName = "projects/cloud-graalvm-support-ci/serviceAccounts/cloud-graalvm-support-ci@cloud-graalvm-support-ci.iam.gserviceaccount.com";
    GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
    HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);

    NetHttpTransport netHttpTransport = GoogleNetHttpTransport.newTrustedTransport();
    GsonFactory gsonFactory = new GsonFactory();
    Iam iam = new Iam(
        netHttpTransport, gsonFactory, requestInitializer
    );
    ServiceAccount serviceAccount = iam.projects().serviceAccounts().get(serviceAccountName).execute();
    System.out.println("serviceAccount : " + serviceAccount);
    System.out.println("serviceAccount.disabled? " + serviceAccount.getDisabled());
  }
}
