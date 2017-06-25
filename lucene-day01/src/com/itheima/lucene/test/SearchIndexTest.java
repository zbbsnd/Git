package com.itheima.lucene.test;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

public class SearchIndexTest {
	@Test
	public void testSearchIndex() throws Exception {
		// 1. 创建Query搜索对象
		// 创建分词器
		Analyzer analyzer = new StandardAnalyzer();
		// 创建搜索解析器.第一个参数是;默认是filed域,第二个是分词器;
		QueryParser parser = new QueryParser("desc", analyzer);
		// 创建搜索对象
		Query query = parser.parse("desc:java AND lucene");

		// 2. 创建Directory流对象,声明索引库位置
		Directory directory = FSDirectory.open(new File("D:\\temp"));
		// 3. 创建索引读取对象IndexReader
		IndexReader reader = DirectoryReader.open(directory);
		// 4. 创建索引搜索对象IndexSearcher
		IndexSearcher searcher = new IndexSearcher(reader);
		// 5. 使用索引搜索对象，执行搜索，返回结果集TopDocs
		TopDocs topDocs = searcher.search(query, 10);
		System.out.println("查询到的数据总条数是：" + topDocs.totalHits);
		// 获取查询结果集
		ScoreDoc[] docs = topDocs.scoreDocs;

		// 6. 解析结果集
		for (ScoreDoc scoreDoc : docs) {
			// 获取文档
			int docID = scoreDoc.doc;
			Document doc = searcher.doc(docID);

			System.out.println("=============================");
			System.out.println("docID:" + docID);
			System.out.println("bookId:" + doc.get("id"));
			System.out.println("name:" + doc.get("name"));
			System.out.println("price:" + doc.get("price"));
		}
	}
}
