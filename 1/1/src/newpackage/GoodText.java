package newpackage;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class GoodText {

    public GoodText() {
    }


    public void setNameN(ArrayList NameN) {
        this.NameN = NameN;
    }
//去除数据中{ }空行和回车
    public static String[] deleteArrayNull(String string[]) {
        String strArr[] = string;

        // step1: 定义一个list列表，并循环赋值
        ArrayList<String> strList = new ArrayList<String>();
        for (int i = 0; i < strArr.length; i++) {
            strArr[i]=  strArr[i].replaceAll(" ", "");
            strArr[i]=  strArr[i].replaceAll("\t", "");
            strArr[i]=  strArr[i].replaceAll("(//.*)", "");
            strArr[i]=  strArr[i].replaceAll("\\{", "");
            strArr[i]=  strArr[i].replaceAll("\\}", "");
            strList.add(strArr[i]);
        }

        // step2: 删除list列表中所有的空值e

        while (strList.remove(null));
        while (strList.remove(""));



        // step3: 把list列表转换给一个新定义的中间数组，并赋值给它
        String strArrLast[] = strList.toArray(new String[strList.size()]);

        return strArrLast;
    }

//得到所有文件的地址，将他导入到一个新建的列表中
    public static List<File> getFiles(String path){
        File root = new File(path);
        List<File> files = new ArrayList<File>();
        if(!root.isDirectory()){
            files.add(root);
        }else{
            File[] subFiles = root.listFiles();
            for(File f : subFiles){
                files.addAll(getFiles(f.getAbsolutePath()));
            }
        }
        return files;
    }

    // public static void main(String args[]) {
//        readFile();
//
//
//    }

   public static ArrayList NameM = new ArrayList<>();
   public static ArrayList NameN = new ArrayList<>();
   public static ArrayList XueHaoM = new ArrayList<>();
   public static ArrayList XueHaoN = new ArrayList<>();
   public static ArrayList ChaChong = new ArrayList<>();
   public static ArrayList TextM = new ArrayList<>();
   public static ArrayList TextN = new ArrayList<>();
   public static ArrayList SameHangM = new ArrayList<>();
    public static ArrayList SameHangN = new ArrayList<>();
  // public static List<Integer []> SameHangMT = new ArrayList<>();
   // public static List<Integer []> SameHangNT = new ArrayList<>();
  

    /**
     * 读入java文件
     */
    public static void readFile(String path) throws SQLException {
        List<File> files = getFiles(path);
        String Files[] = new String[files.size()];

        int r = 0;
        for (File f : files) {
            Files[r] = f.getPath();
            r++;
        }

        for (int i = 0; i < files.size()-1; i++) {
            for (int j = i+1; j < files.size(); j++){
                String pathnameMain = Files[i];//主代码
                String pathnameDis = Files[j]; //查重代码
                try (FileReader readerMain = new FileReader(pathnameMain);
                     FileReader readerDis = new FileReader(pathnameDis);
                     BufferedReader brM = new BufferedReader(readerMain);
                     BufferedReader brD = new BufferedReader(readerDis)
                        // 建立一个对象，它把文件内容转成计算机能读懂的语言
                ) {
                    String messageD = "", messageM = "";
                    String lineM, lineD;
                    while ((lineM = brM.readLine()) != null) {
                        // 一次读入一行数据
                        messageM = messageM + lineM + "\n";
                    }
                    TextM.add(messageM);
                    while ((lineD = brD.readLine()) != null) {

                        messageD = messageD + lineD + "\n";
                    }
                    TextN.add(messageD);
                    brD.close();
                    brM.close();
//                System.out.println(messageM);
//                System.out.println(messageD);
                    //查重

                    String[] M, N,temp1,temp2;
                    int p=0,q=0;

                    double Qian = 0.0, Zong = 0.0;
                    temp1 = messageM.split("\n");//回车分割
                    M = deleteArrayNull(temp1);//去杂
                    //System.out.println(Arrays.toString(M));
                    temp2 = messageD.split("\n");//原代码生成
                    N = deleteArrayNull(temp2);
               
                   String m="",n="";
                    for (int k = 0; k < M.length; k++) {
                        for (int l = 0; l < N.length; l++) {
                            String Mi = M[k];
                            String Ni = N[l];
                            if (Mi.equals(Ni)) {
                                Qian++;
                                
                                    //记录重复行
                                for (int u = 0; u < M.length; u++) {
                                    if (temp1[u].contains(Mi)){
                                       
                                        m=m+u+",";
                                        break;
                                    }
                                }
                                 for (int u = 0; u < N.length; u++) {
                                    if (temp2[u].contains(Ni)){
                                       
                                        n=n+u+",";
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        Zong++;
                    }
                    SameHangM.add(m);
                    SameHangN.add(n);
                 //  System.out.println(m);
                //   System.out.println(n);

                        //获取文件名字中学号部分
                    File tempFileM =new File(Files[i].trim());
                    File tempFileN =new File(Files[j].trim());

                    String XuehaoM = tempFileM.getName();
                    XuehaoM = XuehaoM.substring(0,10);//取学号
                    String XuehaoN = tempFileN.getName();
                    XuehaoN = XuehaoN.substring(0,10);
                    XueHaoM.add(XuehaoM);

                    XueHaoN.add(XuehaoN);
                   System.out.println(XuehaoM+"与"+XuehaoN);
                    System.out.println((int)(100 * Qian / Zong) + "%");
                    ChaChong.add((int)(100 * Qian / Zong) + "%");

                    //System.out.println(m);
                    
  
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        //数据库学号匹配姓名
                    
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "123456");
            String sql = "SELECT * FROM userinfo WHERE userName = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            for (int ii = 0; ii < GoodText.XueHaoM.size(); ii++) {
             stmt.setString(1,(String)GoodText.XueHaoM.get(ii));
        
            // Execute the query
            ResultSet rs = stmt.executeQuery();
          
            // Process the results
            if (rs.next()) {
                
                String name = rs.getString("password");
               NameM.add(name);
            }
            }
            for (int jj = 0; jj < GoodText.XueHaoN.size(); jj++) {
             stmt.setString(1,(String)GoodText.XueHaoN.get(jj));
            // Execute the query
            ResultSet rs = stmt.executeQuery();
            // Process the results
            if (rs.next()) {
                String name = rs.getString("password");
               NameN.add(name);
            }}
            
       
                    //记录重复行
          //  for (Object object : NameN) {
	//		System.out.print(object);
	//	}

    }



}
