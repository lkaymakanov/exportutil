package wizard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SvnUtil {
	/**
		 * Returns the list with modified, added, delete files between 2 revisions!!!
		 * @param repoPath
		 * @param from the Revision from which to take changes !
		 * @param to The  revision to which to take changes! HEAD is the final revision!!!
		 * @return
		 * @throws IOException
		 */
		public static List<String> getChangedFilesFromRevisionToRevision(String repoPath,  String from , String to) throws IOException{
			String dirPath = "\"" + repoPath + "\"";   //double  quote the repoPath
	    	String cmdLineStr =  "cd "+ dirPath+ "  && svn diff -r " + from +":" + to +" --summarize";
			ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", cmdLineStr);
	        builder.redirectErrorStream(true);
	        Process p = builder.start();
	        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line;
	        List<String> res = new ArrayList<String>();
	        while (true) {
	            line = r.readLine();
	            if (line == null) { break; }
	            System.out.println(line);
	            res.add(line);
	        }
	        return res;
	        
		}
		
		public  static void main(String []args) throws IOException{
			String repoPath = "D:\\dblak";
			 List<String> svnchg =  SvnUtil.getChangedFilesFromRevisionToRevision("D:\\dblak", "6428", "6538");
			 SvnUtil.saveToFile(repoPath, new File("6428-6538.sql"), svnchg);
			  
			 //SvnUtil.getChangedFilesFromRevisionToRevision("D:\\dblak", "6539", "6575");
			  
			 //SvnUtil.getChangedFilesFromRevisionToRevision("D:\\dblak", "6576", "6614");
			  
			 //SvnUtil.getChangedFilesFromRevisionToRevision("D:\\dblak", "6615", "HEAD");
			  
			  
		}
		
		private static void saveToFile(String reportPath, File foutputFile, List<String> svnChangedFiles) throws IOException{
			
			PrintWriter pr = new  PrintWriter(foutputFile);
			
			for(String s: svnChangedFiles){
				String fpath = reportPath + "\\" + s.substring(1, s.length()).trim();   //get the full file path & name
				
				//open the file
				BufferedReader br = new BufferedReader(new FileReader(new File(fpath)));
				
				//save to output file
				saveFile(pr, br);
				
				br.close();  //close reader
			}
			
			pr.close();  //close writer
			
		}
		
		
		private static void saveFile(PrintWriter wr, BufferedReader br) throws IOException{
			String line = br.readLine();
			while(line != null){
				wr.println(line);
				line = br.readLine();
			}
		}
}

