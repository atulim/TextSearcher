package First;

import java.io.IOException; 
import org.apache.lucene.document.Document; 
import org.apache.lucene.queryParser.ParseException; 
import org.apache.lucene.search.ScoreDoc; 
import org.apache.lucene.search.TopDocs; 
public class tester 
{ 
	String indexDir = "FILE Address"; String dataDir = "File Address"; 
	indexer indexer; 
	searcher searcher;
	public static void main(String[] args) 
	{ 
		tester tester; 
		try 
		{ 
			tester = new tester(); 
			tester.createIndex(); 
			tester.search("Mohan"); 
		} 
		catch (IOException e) 
		{ 
			e.printStackTrace(); 
		} 
		catch (ParseException e) 
		{ 
			e.printStackTrace(); 
		}
	} 
	private void createIndex() throws IOException
	{ 
		indexer = new indexer(indexDir); 
		int numIndexed; 
		long startTime = System.currentTimeMillis(); 
		numIndexed = indexer.createIndex(dataDir, new TextFilter()); 
		long endTime = System.currentTimeMillis(); 
		indexer.close(); 
		System.out.println(numIndexed+" File indexed, time taken: " +(endTime-startTime)+" ms"); 
	} 
	private void search(String searchQuery) throws IOException, ParseException
	{ 
		searcher = new searcher(indexDir); 
		long startTime = System.currentTimeMillis(); 
		TopDocs hits = searcher.search(searchQuery); 
		long endTime = System.currentTimeMillis(); 
		System.out.println(hits.totalHits + " documents found. Time :" + (endTime - startTime)); 
		for(ScoreDoc scoreDoc : hits.scoreDocs) 
		{ 
			Document doc = searcher.getDocument(scoreDoc); 
			System.out.println("File: " + doc.get(constants.FILE_PATH)); 
		} 
		searcher.close(); 
	} 
}