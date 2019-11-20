import java.util.*;
import java.io.*;
import org.apache.jena.*;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.util.FileManager;

public class RdfMetaData {   
	public static void main (String args[]) throws FileNotFoundException  {
		String inputFileName = "/Users/amitkumar/eclipse-workspace/RegexExample/src/Data/model2.ttl";
		Model model = ModelFactory.createDefaultModel();
//		String queryString = "PREFIX dct:<http://purl.org/dc/terms/>\" +\n" + 
//				"                   \"PREFIX dcat:<http://www.w3.org/ns/dcat# \" +\n" + 
//				"                   \"SELECT DISTINCT ?subject WHERE\" +\n" + 
//				"                   \"{?subject dcat:accessURL ?object .FILTER regex(str(?object), \\\"version\\\") .}";
//		Query query = QueryFactory.create(queryString);
//		
//		
//		String queryString = "PREFIX dct:   <http://purl.org/dc/terms/> "
//				+ "PREFIX dcat:  <http://www.w3.org/ns/dcat#> " 
//				+ "SELECT ?name " 
//				+ "WHERE {"
//				+ " ?name ?ContactURL \"a\"" 
//				+ "}";
		String queryString = "PREFIX dct:   <http://purl.org/dc/terms/> "
				+ "PREFIX dcat:  <http://www.w3.org/ns/dcat#> " 
				+ "SELECT DISTINCT ?subject " 
				+ "WHERE {"
				+ " ?subject dct:title ?object" 
				+ "}";

		
//		
//		PrintStream meta_data = new PrintStream(
//				new FileOutputStream("/Users/amitkumar/Documents/Data/temp.rtf", true));
//		System.setOut(meta_data);
		
		Query my_query = QueryFactory.create(queryString);
		int count = 0;

		
//		model.read(new FileInputStream(inputFileName),null,"TTL");
//		model.write(System.out);
//		
		

		File my_dir = new File("/Users/amitkumar/eclipse-workspace/RegexExample/src/Data/");
		for (int i = 0; i < my_dir.listFiles().length; i++) {
			

			// In each iteration fill the model with data
			model.read("/Users/amitkumar/eclipse-workspace/RegexExample/src/Data/" + my_dir.listFiles()[i].getName());
			QueryExecution qexec = QueryExecutionFactory.create(my_query, model);	
			
			ResultSet results = qexec.execSelect();
			for (; results.hasNext();) {

				QuerySolution soln = results.nextSolution();
				RDFNode predicate = soln.get("subject");
				Scanner file_scanner = new Scanner(new File("/Users/amitkumar/Documents/Data/temp.rtf"));
				int duplicate = 0;
					
				while (file_scanner.hasNextLine()) {
					if (file_scanner.nextLine().contentEquals(predicate.toString())) {	
						duplicate = 1;
						break;
					}
				}
				if (duplicate == 0) {
					System.out.println(predicate);
					count++;
				}
				

		}
			qexec.close();
		}
		
		System.out.println("Total Metadata =" + count);

	}

}

