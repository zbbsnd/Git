package com.itheima.lucene.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.itheima.lucene.dao.BookDao;
import com.itheima.lucene.dao.BookDaoImpl;
import com.itheima.lucene.pojo.Book;

public class CreateIndexTest {


	@Test
	public void testCreateIndex() throws Exception{
//		1.采集数据
		BookDao bookDao=new BookDaoImpl();
		List<Book> bookList = bookDao.queryBookList();
	
//		2.创建Document文档对象
		List<Document> documents=new ArrayList<>();
		for (Book book : bookList) {
			Document document=new Document();
			// Document文档中添加Field域
			// 图书Id
			// Store.YES:表示存储到文档域中
			document.add(new TextField("id", book.getId().toString(),Store.YES));
			document.add(new TextField("name", book.getName().toString(),Store.YES));
			document.add(new TextField("price", book.getPrice().toString(),Store.YES));
			document.add(new TextField("pic", book.getPic().toString(),Store.YES));
			document.add(new TextField("desc", book.getDesc().toString(),Store.YES));
			//把document放到list中
			documents.add(document);
		}
//		3.创建分析器（分词器）
//		Analyzer analyzer=new StandardAnalyzer();
		//为了支持中文分词器,故将默认的分词器改成ik Analyer;这样就可以分析中文词汇了;
		Analyzer analyzer=new IKAnalyzer();
//		4.创建IndexWriterConfig配置信息类
		IndexWriterConfig indexWriterConfig=new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
//		5.创建Directory对象，声明索引库存储位置
		Directory directory=FSDirectory.open(new File("D://temp"));
//		6.创建IndexWriter写入对象
		IndexWriter indexWriter=new IndexWriter(directory , indexWriterConfig);
//		7.把Document写入到索引库中
		for (Document document : documents) {
			indexWriter.addDocument(document);
		}
//		8.释放资源
		indexWriter.close();
	}
	 
}
