package com.tigo.pdf;

public class Exec {
	 private CloudStorageAccount storageAccount;
	  
	  private CloudBlobClient blobClient;
	  
	  private CloudBlobContainer container;
	  
	  public ClienteBlob(String nombreCuenta, String llave, String nombreContenedor) throws GestorBlobException {
	    try {
	      ConexionAzure conexionAzure = new ConexionAzure(nombreCuenta, llave);
	      this.storageAccount = conexionAzure.getStorageAccount();
	      this.blobClient = this.storageAccount.createCloudBlobClient();
	      this.container = this.blobClient.getContainerReference(nombreContenedor);
	      this.container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());
	    } catch (InvalidKeyException|URISyntaxException e) {
	      throw new GestorBlobException("Hubo un error al crear la conexion con azure", e);
	    } catch (StorageException e) {
	      throw new GestorBlobException("Hubo un error al crear la conexion con azure", e);
	    } 
	  }
	  
	  public String subirArchivo(String nombreArchivo, byte[] arrayArchivo) throws GestorBlobException {
	    String uri = "";
	    try {
	      CloudBlockBlob blob = this.container.getBlockBlobReference(nombreArchivo);
	      blob.uploadFromByteArray(arrayArchivo, 0, arrayArchivo.length);
	      uri = blob.getStorageUri().getPrimaryUri().toString();
	      return uri;
	    } catch (URISyntaxException ex) {
	      throw new GestorBlobException("Hubo un error al subir el archivo al blob ", ex);
	    } catch (StorageException ex) {
	      throw new GestorBlobException("Hubo un error al subir el archivo al blob ", ex);
	    } catch (IOException e) {
	      throw new GestorBlobException("Hubo un error al subir el archivo al blob ", e);
	    } 
	  }
	  
	  public String subirArchivo(String nombreArchivo, String contenidoArchivo) throws GestorBlobException {
	    String uri = "";
	    try {
	      CloudBlockBlob blob = this.container.getBlockBlobReference(nombreArchivo);
	      blob.uploadText(contenidoArchivo);
	      uri = blob.getStorageUri().getPrimaryUri().toString();
	      return uri;
	    } catch (URISyntaxException ex) {
	      throw new GestorBlobException("Hubo un error al subir el archivo " + nombreArchivo + " al blob ", ex);
	    } catch (StorageException ex) {
	      throw new GestorBlobException("Hubo un error al subir el archivo " + nombreArchivo + " al blob ", ex);
	    } catch (IOException e) {
	      throw new GestorBlobException("Hubo un error al subir el archivo " + nombreArchivo + " al blob ", e);
	    } 
	  }
	  
	  public boolean eliminarArchivo(String nombreArchivo) throws GestorBlobException {
	    try {
	      CloudBlockBlob blob = this.container.getBlockBlobReference(nombreArchivo);
	      return blob.deleteIfExists();
	    } catch (URISyntaxException e) {
	      throw new GestorBlobException("Hubo un error al eliminar el archivo " + nombreArchivo + " del blob ", e);
	    } catch (StorageException e) {
	      throw new GestorBlobException("Hubo un error al eliminar el archivo " + nombreArchivo + " del blob ", e);
	    } 
	  }
	  
	  public String descargarContenidoArchivo(String nombreArchivo) throws GestorBlobException {
	    try {
	      CloudBlockBlob blob = this.container.getBlockBlobReference(nombreArchivo);
	      return blob.downloadText();
	    } catch (URISyntaxException e) {
	      throw new GestorBlobException("Hubo un error al descargar el archivo " + nombreArchivo + " del blob ", e);
	    } catch (StorageException e) {
	      throw new GestorBlobException("Hubo un error al descargar el archivo " + nombreArchivo + " del blob ", e);
	    } catch (UnsupportedEncodingException e) {
	      throw new GestorBlobException("Hubo un error al descargar el archivo " + nombreArchivo + " del blob ", e);
	    } catch (IOException e) {
	      throw new GestorBlobException("Hubo un error al descargar el archivo " + nombreArchivo + " del blob ", e);
	    } 
	  }
	  
	  public byte[] descargarArchivo(String nombreArchivo) throws GestorBlobException {
	    InputStream input = null;
	    try {
	      CloudBlockBlob blob = this.container.getBlockBlobReference(nombreArchivo);
	      BlobInputStream blobInputStream = blob.openInputStream();
	      return IOUtils.toByteArray((InputStream)blobInputStream);
	    } catch (URISyntaxException e) {
	      throw new GestorBlobException("Hubo un error al descargar el archivo " + nombreArchivo + " del blob ", e);
	    } catch (StorageException e) {
	      throw new GestorBlobException("Hubo un error al descargar el archivo " + nombreArchivo + " del blob ", e);
	    } catch (UnsupportedEncodingException e) {
	      throw new GestorBlobException("Hubo un error al descargar el archivo " + nombreArchivo + " del blob ", e);
	    } catch (IOException e) {
	      throw new GestorBlobException("Hubo un error al descargar el archivo " + nombreArchivo + " del blob ", e);
	    } 
	  }
}
