package com.dev.lima.cdc.cadastrolivro;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.dev.lima.cdc.exeception.ImageCapaDuplicateDropBoxException;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;

@Component
public class UploadDropBox implements Uploader {
	
	@Value("${token.dropbox}")
	private String accessToken;

	@Override
	public String fazerUpload(MultipartFile capaFile) {

		DbxClientV2 client = criarClienteDropBox();
		enviarArquivo(client, capaFile);
		String linkCompartilhavelCapa = gerarLinkCompartilhavel(client, capaFile);
		return linkCompartilhavelCapa;
	}

	private void enviarArquivo(DbxClientV2 client, MultipartFile capa) {

		try {
			if(!arquivoExiste(client, capa)) {				
				ByteArrayInputStream inputStream = new ByteArrayInputStream(capa.getBytes());
				client.files()
				.uploadBuilder("/img-livros/" + capa.getOriginalFilename()).uploadAndFinish(inputStream);
				inputStream.close();
			}else {
				throw new ImageCapaDuplicateDropBoxException("O nome deste arquivo já está armazenado no servidor do DropBox.");
			}

		} catch (DbxException | IOException e) {

			throw new RuntimeException(e);
		}
	}

	private boolean arquivoExiste(DbxClientV2 client, MultipartFile capa) {
		
		try {
			ListFolderResult result = client.files().listFolder("/img-livros");
			Optional<Metadata> possivelLivroRepetido = result.getEntries().stream()
					.filter(metadata -> metadata.getName()
							.equals(capa.getOriginalFilename())).findFirst();
			
			if(possivelLivroRepetido.isPresent()) {
				return true;
			}
			
			return false;
			
		} catch (DbxException e) {
			throw new RuntimeException(e);
		}
	}

	private String gerarLinkCompartilhavel(DbxClientV2 client, MultipartFile capa) {

		try {
			SharedLinkMetadata sharedLinkMetadata = client.sharing()
					.createSharedLinkWithSettings("/img-livros/"+capa.getOriginalFilename());
		
			String urlFormatada = formatarUrlOpenFileBrowser(sharedLinkMetadata.getUrl());
			return urlFormatada;
		} catch (DbxException ex) {
			throw new RuntimeException(ex);
		}
	}

	private DbxClientV2 criarClienteDropBox() {
		DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
		DbxClientV2 client = new DbxClientV2(config, accessToken);

		return client;
	}
	
	private String formatarUrlOpenFileBrowser(String url) {
		return url.replace("dl=0", "raw=1");
	}

}
