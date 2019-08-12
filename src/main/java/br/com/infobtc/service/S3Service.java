package br.com.infobtc.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3Service {

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Value("${s3.bucket}")
	private String bucketName;

	@Autowired
	private AmazonS3 amazonS3;

	public URI uploadFile(MultipartFile multipartFile) {
		try {
			String fileName = multipartFile.getOriginalFilename();
			String contentType = multipartFile.getContentType();
			InputStream inputStream = multipartFile.getInputStream();
			
			return uploadFile(inputStream, fileName, contentType);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro de IO: " + e.getMessage());
		}

	}

	public URI uploadFile(InputStream inputStream, String fileName, String contentType) {
		try {
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(contentType);
			amazonS3.putObject(bucketName, fileName, inputStream, metadata);
			return amazonS3.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			LOG.info("URISyntaxException: " + e.getMessage());
			throw new RuntimeException("Erro ao converter URL para URI.");
		}
	}

}
