package com.dev.lima.cdc.cadastrolivro;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author israel
 *
 */

public interface Uploader {
	
	/**
	 * Toda classe que implementa serviço de armazenamento de arquivos
	 * deve implementar esta interface
	 * @implSpec UploadLocal, simula acesso aws amazon	 
	 * @param capaFile
	 * @return
	 */
	
	String fazerUpload(MultipartFile capaFile);
}
