import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
import java.lang.Object;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class FileLoader
{
static List<List<String>> FinalvalSet=new ArrayList<List<String>>();
static int totalarg;
public static void main(String[] args) {

String[] items=new String[10];
totalarg=args.length;
for(int arg=0;arg<args.length;arg++)
{
	items[arg]=args[arg];
}

try (BufferedReader br = new BufferedReader(new FileReader(args[0])))
        {

            String sCurrentLine;
          
            while ((sCurrentLine = br.readLine()) != null) {
		List<String> valSet = new ArrayList<String>();
		
                String[] information = sCurrentLine.split(",");
		String mixitems="";
		for(int total=0;total<information.length;total++)
		{
			if(total>1)
			{
				mixitems=mixitems+information[total].trim();
			}
			else
			{
				valSet.add(information[total].trim());
			}
		}
		valSet.add(mixitems);
              
		//System.out.println(valSet);	
		FinalvalSet.add(valSet);

            }

        } 
	catch (IOException e) {
            e.printStackTrace();
        } 
   // System.out.println(FinalvalSet);
search(items);
}
public static void search(String items[])
{

//System.out.println(FinalvalSet);
System.out.println("in"+FinalvalSet.size());
String checkitems="";
for(int srh=1;srh<totalarg;srh++)
{
	checkitems=checkitems+items[srh];
}
System.out.println(checkitems);
ArrayList<String> id=new ArrayList<String>();
ArrayList<String> price=new ArrayList<String>();
int gotitems=0;
for(int outter=0;outter<FinalvalSet.size();outter++)
{
		if(FinalvalSet.get(outter).contains(checkitems))
		{
			//System.out.println(FinalvalSet.get(outter).get(0)+"\n");
			//System.out.println(FinalvalSet.get(outter).get(1)+"\n");
			id.add(FinalvalSet.get(outter).get(0));
			price.add(FinalvalSet.get(outter).get(1));
			gotitems=1;
		}

}
if(gotitems==1)
{
double minprice=Double.parseDouble(price.get(0));
int idfound=0;
int minid=0;

Iterator<String> priceIterator = price.iterator();
	while (priceIterator.hasNext()) {
		double currentprice=Double.parseDouble(priceIterator.next());
		if(minprice>currentprice)
		{
			minprice=currentprice;
			minid=idfound;
		}
		idfound=idfound+1;
	
	}

System.out.print(id.get(minid)+", ");
System.out.println(minprice);
}
else
{
System.out.println("Items not found");
}
	
}
}

