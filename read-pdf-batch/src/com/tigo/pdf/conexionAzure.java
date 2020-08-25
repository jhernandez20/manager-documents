package com.tigo.pdf;

public class conexionAzure {
	 private String storageConnectionString;
	  
	  private CloudStorageAccount storageAccount;
	  
	  private CloudBlobClient blobClient;
	  
	  private CloudBlobContainer container;
	  
	  private CloudBlockBlob blob;
	  
	  private String accountName;
	  
	  private String accountKey;
	  
	  public ConexionAzure(String accountName, String accountKey) throws InvalidKeyException, URISyntaxException {
	    this.accountName = accountName;
	    this.accountKey = accountKey;
	    this.storageConnectionString = "DefaultEndpointsProtocol=https;AccountName=" + this.accountName + ";AccountKey=" + this.accountKey;
	    this.storageAccount = CloudStorageAccount.parse(this.storageConnectionString);
	  }
	  
	  public CloudStorageAccount getStorageAccount() {
	    return this.storageAccount;
	  }
	  
	  public void setStorageAccount(CloudStorageAccount storageAccount) {
	    this.storageAccount = storageAccount;
	  }
	  
	  public CloudBlobClient getBlobClient() {
	    return this.blobClient;
	  }
	  
	  public void setBlobClient(CloudBlobClient blobClient) {
	    this.blobClient = blobClient;
	  }
	  
	  public CloudBlobContainer getContainer() {
	    return this.container;
	  }
	  
	  public void setContainer(CloudBlobContainer container) {
	    this.container = container;
	  }
	  
	  public CloudBlockBlob getBlob() {
	    return this.blob;
	  }
	  
	  public void setBlob(CloudBlockBlob blob) {
	    this.blob = blob;
	  }

}
