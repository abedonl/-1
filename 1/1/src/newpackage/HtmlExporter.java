/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;
/**
 *
 * @author 86173
 */

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.file.StandardOpenOption;

import java.util.Arrays;


public class HtmlExporter {
public static boolean contains(final int[] arr, final int key) {
         Arrays.sort(arr);  
         return Arrays.binarySearch(arr, key) >= 0; 
    }

    public static void HtmlExporter(String PathString2) throws IOException {
     //   String inputPath = PathString1; // 输入文件夹路径
        int fileCount = 0;
       String outputPath = PathString2; // 输出文件夹路径
       
 
//               Files.walkFileTree(Paths.get(inputPath), new SimpleFileVisitor<Path>() {

        while (1==1) {            
            if (fileCount==GoodText.TextM.size())break;
          String[] tempM =((String)(GoodText.TextM.get(fileCount))).split("\n");
          String[] tempN =((String)(GoodText.TextN.get(fileCount))).split("\n");
   //     if (Files.isRegularFile(path) && path.toString().endsWith(".java")) {
          //  File inputFile = path.toFile();
            Path outputFile = Paths.get(outputPath,GoodText.NameM.get(fileCount).toString()+"与"+GoodText.NameN.get(fileCount).toString()+GoodText.ChaChong.get(fileCount)+fileCount + ".html");
            StringBuilder htmlBuilder = new StringBuilder();
           htmlBuilder.append("<!DOCTYPE html>\n");
           htmlBuilder.append("<html>\n");
           htmlBuilder.append("<head>\n");
           htmlBuilder.append("<meta charset=\"utf-8\">\n");
           htmlBuilder.append("<title>\n");
           htmlBuilder.append("查重代码分析");
           htmlBuilder.append("</title>\n");
           htmlBuilder.append("</head>\n");
           htmlBuilder.append("<style>\n");
           htmlBuilder.append(".container{width:80%;margin:0 auto;} ");
           htmlBuilder.append(".next{text-align:right;} ");
           htmlBuilder.append(".grid{display:flex;flex-direction:row;flex-wrap:wrap;align-items:stretch;padding:0;} ");
           htmlBuilder.append(".wrapper{box-sizing:border-box;width:45%;position:relative;display:inline-block;padding-left:1rem;padding-right:1rem;vertical-align:top;} ");
           htmlBuilder.append(".codeName{font-weight:bold;} ");
           htmlBuilder.append(".codeBlock{line-height:1.2rem;padding:7px;height:700px;overflow:auto;border:1px solid #bbb;border-radius:5px;margin:3px;min-height:300px;font-family:Consolas, Menlo, Monaco, \"Liberation Mono\", \"Courier New\", monospace;} ");
           htmlBuilder.append("footer,footer a {color:grey;font-size:small;text-decoration:none;text-align:center;margin:20px 0;} \n");
           htmlBuilder.append("a.source_code {text-decoration:none;} ");
           htmlBuilder.append("a.source_code:hover {text-decoration:underline;}");
           htmlBuilder.append("</style>\n");
           htmlBuilder.append("<body>\n");
           htmlBuilder.append("<div class=\"container\">\n");
           htmlBuilder.append("<h2>\n");
           htmlBuilder.append("</h2>\n");
           htmlBuilder.append("<h3>\n");
           htmlBuilder.append("查重率：") .append(GoodText.ChaChong.get(fileCount)) ;
          htmlBuilder.append("</h3>\n");
           htmlBuilder.append("<div class=\"wrapper\">\n");
           htmlBuilder.append("<p class=\"codeName\">\n");
           htmlBuilder.append((String)GoodText.NameM.get(fileCount));
           htmlBuilder.append("</p>\n");
           htmlBuilder.append("<div class=\"codeBlock\">\n");
           htmlBuilder.append("<pre style=\"overflow: unset;\">\n");
                   for(int i = 0; i < tempM.length; i++){
                       String[] n = ((String)(GoodText.SameHangM.get(fileCount))).split(",");
                              int[] nn=new int[n.length];
                  for (int ii = 0; ii < n.length; ii++) {
                nn[ii] = Integer.parseInt(n[ii]);
            }
                    
                           
                       
                       if(contains(nn,i)){
            htmlBuilder.append("<a href=\"#source_other1\" id=\"source_one1\" class=\"source_code\" ><font color=\"#AA2050\">\n");
            htmlBuilder.append(tempM[i]);htmlBuilder.append("\n");
            htmlBuilder.append(" </font></a>\n");
                         continue;
                       }
                       htmlBuilder.append(tempM[i]);     htmlBuilder.append("\n");
                   }
           htmlBuilder.append("</pre>\n");
           htmlBuilder.append("</div>\n");
           htmlBuilder.append("</div>\n");
           htmlBuilder.append("<div class=\"wrapper\">\n");
           htmlBuilder.append("<p class=\"codeName\"> <small>\n");
           htmlBuilder.append((String)GoodText.NameN.get(fileCount));
           htmlBuilder.append("<div class=\"codeBlock\">\n");
           htmlBuilder.append("<pre style=\"overflow: unset;\">\n");
            
           for(int i = 0; i < tempN.length; i++){
             String [] n = ((String)(GoodText.SameHangN.get(fileCount))).split(",");
               
                int[] nn=new int[n.length];
                  for (int ii = 0; ii < n.length; ii++) {
                nn[ii] = Integer.parseInt(n[ii]);
            }
                 
                       //打印红行
                       if(contains(nn,i)){
            htmlBuilder.append("<a href=\"#source_other1\" id=\"source_one1\" class=\"source_code\" ><font color=\"#AA2050\">\n");
            htmlBuilder.append(tempN[i]);   htmlBuilder.append("\n");
            htmlBuilder.append(" </font></a>\n");
            continue;
                       }
                       //打印白行
                        htmlBuilder.append(tempN[i]);   htmlBuilder.append("\n");         
                   }
           htmlBuilder.append("</pre>\n");
           htmlBuilder.append("</div>\n");
           htmlBuilder.append("</div>\n");
           htmlBuilder.append("</div>\n");
           htmlBuilder.append("</body>\n");
           htmlBuilder.append("</html>\n");
            Files.write(outputFile, htmlBuilder.toString().getBytes(), StandardOpenOption.CREATE_NEW);
        fileCount++;

    }
//});
    }
}
