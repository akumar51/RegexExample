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
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.util.FileManager;

public class Main {   
	public static void main (String args[])  {
		String inputFileName = "/Users/amitkumar/eclipse-workspace/RegexExample/src/Data/model2.ttl";
		Model model = ModelFactory.createDefaultModel();
//		String queryString = "PREFIX dct:<http://purl.org/dc/terms/>\" +\n" + 
//				"                   \"PREFIX dcat:<http://www.w3.org/ns/dcat# \" +\n" + 
//				"                   \"SELECT DISTINCT ?subject WHERE\" +\n" + 
//				"                   \"{?subject dcat:accessURL ?object .FILTER regex(str(?object), \\\"version\\\") .}";
//		Query query = QueryFactory.create(queryString);
//		
		
		String queryString = "PREFIX dct:   <http://purl.org/dc/terms/> "
				+ "PREFIX dcat:  <http://www.w3.org/ns/dcat#> " 
				+ "SELECT DISTINCT ?subject" 
				+ "WHERE {"
				+ "?subject dct:title ?object" 
				+ "}";
		
		Query my_query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(my_query, model);

		try {
			
			
//				model.read(new FileInputStream(inputFileName),null,"TTL");
//				model.write(System.out); 
			ResultSet results=qexec.execSelect();
			while(results.hasNext())
			{
				QuerySolution soln = results.nextSolution();
				Literal name = soln.getLiteral("a");
				System.out.println(name);
			}
		}
		finally {
			qexec.close();
		}
//		catch (FileNotFoundException e) {
//		    e.printStackTrace();
//		}
	}
}

