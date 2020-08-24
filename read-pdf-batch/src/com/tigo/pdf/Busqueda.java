package com.tigo.pdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;

import com.computec.tigofiletransfer.AzureConnection.AzureConnection;
import com.computec.tigofiletransfer.DBConnection.DBConnection;
import com.computec.tigofiletransfer.Entities.AzureConnectionnEntity;
import com.computec.tigofiletransfer.Utilities.Utilities;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;

public class Busqueda {
	public static void main(String[] args) throws IOException, DocumentException {
		String sep = File.separator;
		// path C:\Users\jhernandez\Desktop\WorkspaceAltiuz\TIGO\batch-process
		Path path = Paths.get("C:\\Users\\jhernandez\\Desktop\\WorkspaceAltiuz\\TIGO\\generate-txt2.tar.gz");
		List<Persona> listaPersonas = new ArrayList<Persona>();
		Persona persona = new Persona("George", "940258481");
		listaPersonas.add(persona);
		Persona persona2 = new Persona("Leandrito", "940258482");
		listaPersonas.add(persona2);
		// System.out.println("La lista tiene : " + listaPersonas.size());
		// conectarFtp();
		conectarFtp();
		// crearPdf(listaPersonas);

		/*
		 * String info_archivo = leerArchivo(path.toString()); if (info_archivo != null)
		 * { System.out.println("texto archivo: " + info_archivo); }
		 */
		// borrarArchivo(path.toString());
		// descomprirArchivo(path.toString());
		// crearTar(path.toString());
		// crearArchivo(path);
		// addText(path.toString(),"esta es la primera linea en el fichero.\n");
		
		/*
		 * 
		 * public static void connectAzureStorage(DBConnection con) {
		
		AzureConnection connection = new AzureConnection();
		connection.conexionAzure(con);
		
		AzureConnectionnEntity data = new AzureConnectionnEntity();
		data = Utilities.getAzureConnectionProp(conn);
		
		System.out.println("Conexion Data!"+data);
	}
		 * 
		 * */
	}

	public static void crearArchivo(Path path) {

		try {
			Files.createDirectories(path.getParent());
			Files.createFile(path);
			System.out.println("archivo creado");

		} catch (Exception e) {
			System.out.println("error" + e.getMessage());
		}

	}

	public static void addText(String path, String to_apped) {

		try {
			PrintWriter writer = new PrintWriter(new FileWriter(path, true));
			writer.append(to_apped);
			writer.close();
			System.out.println("se escribio correctamente el archivo");
		} catch (Exception e) {
			System.out.println("error" + e.getMessage());
		}
	}

	public static String leerArchivo(String path) {

		try {
			FileReader reader = new FileReader(path);
			BufferedReader buffer = new BufferedReader(reader);
			String linea = buffer.readLine();
			String texto = linea;
			while (linea != null) {
				// System.out.println(linea);
				linea = buffer.readLine();
				if (linea != null) {
					texto = texto + linea + "\n";
				}
			}
			return texto;

		} catch (Exception e) {
			System.out.println("error" + e.getMessage());
		}
		return null;
	}

	public static void borrarArchivo(String path) {

		File file = new File(path);
		if (file.exists()) {
			if (file.delete()) {
				System.out.println("se elimino el fichero.");
			} else {
				System.out.println("error.");
			}

		} else {
			System.out.println("El Fichero no existe.");
		}
	}

	public static void descomprirArchivo(String path) {

		File archive = new File(path);
		File destination = new File("C:\\Users\\jhernandez\\Desktop\\WorkspaceAltiuz\\TIGO\\generate-txt2");

		Archiver archiver = ArchiverFactory.createArchiver("tar", "gz");
		try {
			archiver.extract(archive, destination);
			System.out.println("Archivo descomprimido.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("error " + e.getMessage());
		}
	}

	public static void crearTar(String path) throws IOException {
		String archiveName = "generate-txt2";
		File destination = new File("C:\\Users\\jhernandez\\Desktop\\WorkspaceAltiuz\\TIGO");
		File source = new File(path);

		Archiver archiver = ArchiverFactory.createArchiver("tar", "gz");
		File archive = archiver.create(archiveName, destination, source);
	}

	public static void crearPdf(List<Persona> lista) throws FileNotFoundException, DocumentException {

		Document documento = new Document();
		FileOutputStream ficheroPDF = new FileOutputStream("persona.pdf");
		PdfWriter.getInstance(documento, ficheroPDF);

		documento.open();
		Paragraph titulo = new Paragraph("PDF", FontFactory.getFont("arial"));
		documento.add(titulo);
		PdfPTable tabla = new PdfPTable(3);
		tabla.addCell("ID");
		tabla.addCell("nombre");
		tabla.addCell("telefono");

		for (int i = 0; i < lista.size(); i++) {
			tabla.addCell("" + i);
			tabla.addCell(lista.get(i).getNombre());
			tabla.addCell(lista.get(i).getTelefono());
		}

		documento.add(tabla);
		documento.close();
	}

	public static void conectarFtp() {

		FTPClient client = new FTPClient();
		String sFTP = "ftp.dlptest.com";
		String sUser = "dlpuser@dlptest.com";
		String sPassword = "eUj8GeW55SvYaswqUyDSm5v6N";

		try {
			client.connect(sFTP);
			boolean login = client.login(sUser, sPassword);
			// client.changeWorkingDirectory("\\george");
			System.out.println("Conectado." + login);
			// System.out.println("directorio" +client.printWorkingDirectory());
			// FTPFile[] files1 = client.listDirectories();

			FTPFile[] files1 = client.listFiles("/george");

			System.out.println("directorio" + files1);
			// InputStream inputStream = ftpClient.retrieveFileStream(remoteFile2);
			printFileDetails(files1, client);
			client.logout();
			client.disconnect();
		} catch (IOException e) {
			System.out.println("error " + e.getMessage());
		}
	}

	private static void printFileDetails(FTPFile[] files, FTPClient client) {
		DateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (FTPFile file : files) {
			String details = file.getName();
			if (file.isDirectory()) {
				details = "[" + details + "]";
			}
			details += "\t\t" + file.getSize();
			details += "\t\t" + dateFormater.format(file.getTimestamp().getTime());

			System.out.println(details);
		}
		// boolean status = client.retrieveFile(filename, os);
	}

	public static void conexionFtp2() {

		String[] filesEntry = { "example.txt", "example2.txt", "example3.txt", "example4.txt", "example10.txt" };
		String sFTP = "ftp.dlptest.com";
		String sUser = "dlpuser@dlptest.com";
		String sPassword = "eUj8GeW55SvYaswqUyDSm5v6N";
		// ArrayList<String> cars = new ArrayList<String>();

		FTPClient client = new FTPClient();
		try {
			client.connect(sFTP);
			client.login(sUser, sPassword);

			// Download file from FTP server.
			for (int i = 0; i < filesEntry.length; i++) {
				
				FileOutputStream os = new FileOutputStream("C:\\Users\\jhernandez\\Desktop\\WorkspaceAltiuz\\TIGO\\test"+filesEntry[i]);

				boolean status = client.retrieveFile(filesEntry[i], os);
				System.out.println("status = " + status);
				System.out.println("reply  = " + client.getReplyString());
				if (status == false) {
					// Buscar en Azure
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				client.logout();
				client.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
