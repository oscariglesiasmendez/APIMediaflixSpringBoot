package com.castelao.mediaflix_v4.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;

import io.github.cdimascio.dotenv.Dotenv;

@Service
public class CloudinaryService {

	private static Logger LOG = LoggerFactory.getLogger(CloudinaryService.class);
	private static final String URL = "url";
    private static final String CLOUDINARY_URL = "cloudinary://383698159469413:XJIrxiU7HyBzJY74pBdd4q1bwF8@dulpvbj0d";

	
	private Cloudinary cloudinary;

	public CloudinaryService() {
		cloudinary = new Cloudinary(CLOUDINARY_URL);
		cloudinary.config.secure = true;
		System.out.println(cloudinary.config.cloudName);
	}

	/**
	 * Sube la imagen recibida como argumento en base64 al cloud
	 * 
	 * Devuelve la URL publica donde esta alojada
	 * 
	 * @param base64Data
	 * @return
	 * @throws IOException
	 */
	public String upload(String base64Data) throws IOException {
		Map<String, String> options = new HashMap<String, String>();
		options.put("folder", "mediaflix");

		@SuppressWarnings("rawtypes")
		Map upload = cloudinary.uploader().upload("data:image/jpg;base64," + base64Data, options);
		
		LOG.debug(upload.toString());
		String url = (String) upload.get(URL);
		return url;
	}
}
