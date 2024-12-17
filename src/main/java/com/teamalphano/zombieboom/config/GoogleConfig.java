package com.teamalphano.zombieboom.config;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.AndroidPublisherScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.AccessToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Slf4j
@Configuration
public class GoogleConfig {

    public InputStream loadKeyFile() throws IOException {
        // 서버의 특정 경로 지정
        String filePath = "/home/ubuntu/project/back/key/zombieboom-key.json"; // 서버 상의 절대 경로

        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("키 파일을 찾을 수 없습니다. 경로를 확인하세요: ");
        }

        return new FileInputStream(file);
    }

    @Bean
    public GoogleCredentials googleCredentials() throws IOException {
        InputStream inputStream = loadKeyFile();
        if (inputStream == null) {
            throw new IOException("키 파일을 찾을 수 없습니다. 경로를 확인하세요");
        }

        // GoogleCredentials 생성 및 스코프 설정
        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(inputStream)
                .createScoped(Collections.singleton(AndroidPublisherScopes.ANDROIDPUBLISHER));

        // AccessToken 갱신
        AccessToken accessToken = googleCredentials.refreshAccessToken();
        // Fetch access token
        googleCredentials.refreshIfExpired();

        if (accessToken != null) {
            log.info("Access Token Not Exist ");
        }

        return googleCredentials;
    }

    @Bean
    public AndroidPublisher androidPublisher(GoogleCredentials credentials) throws GeneralSecurityException, IOException {
        return new AndroidPublisher.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                new HttpCredentialsAdapter(credentials)
        ).setApplicationName("com.teamalphano.zombieboom").build();
    }
}