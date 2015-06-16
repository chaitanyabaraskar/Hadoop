package com.avro.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import com.hascode.entity.Book;

public class FileSerializationExample {
	public static void main(final String[] args) throws IOException {
				File store = new File("book.avro");
		BufferedReader br;

		System.out
				.println("serializing books to temp file: " + store.getPath());
		DatumWriter<Book> bookDatumWriter = new SpecificDatumWriter<Book>(
				Book.class);
		DataFileWriter<Book> bookFileWriter = new DataFileWriter<Book>(
				bookDatumWriter);

		Book book1 = new Book();
		bookFileWriter.create(book1.getSchema(), store);

		try {
			br = new BufferedReader(new FileReader("abc.txt"));
			String fileline;
			while ((fileline = br.readLine()) != null) {
				Book bookobj = new Book();
				String[] linearr = fileline.split(",");
				bookobj.setId(Integer.parseInt(linearr[0]));
				bookobj.setName(linearr[1]);
				bookobj.setCategory(linearr[2]);
				bookFileWriter.append(bookobj);
			}
		} catch (Exception e) {

		} finally {
			bookFileWriter.close();
		}

		DatumReader<Book> bookDatumReader = new SpecificDatumReader<Book>(
				Book.class);
		DataFileReader<Book> bookFileReader = new DataFileReader<Book>(store,
				bookDatumReader);
		FileWriter fw = new FileWriter("def.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		
		
		while (bookFileReader.hasNext()) {
			Book b1 = bookFileReader.next();
			System.out.println("deserialized from file: " + b1);
			bw.write(b1.toString());
			
		}
		bw.close();
	}

}