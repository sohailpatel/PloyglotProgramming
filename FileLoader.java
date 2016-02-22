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
try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) //Reading CSV File
        {
            String sCurrentLine;       
            while ((sCurrentLine = br.readLine()) != null) 
		{
		List<String> valSet = new ArrayList<String>();
                String[] information = sCurrentLine.split(",");
		for(int total=0;total<information.length;total++)
		{
			valSet.add(information[total].trim()); //Adding Hotel id and Price in List
		}
		FinalvalSet.add(valSet);
            }
        } 
	catch (IOException e) 
	{
            e.printStackTrace();
        } 
   // System.out.println(FinalvalSet);
search(items);
}
public static void search(String items[])  //Search function
{
ArrayList<String> id=new ArrayList<String>();
ArrayList<String> price=new ArrayList<String>();
int gotitems=0;
int gotitems1=0;
ArrayList<String> id1=new ArrayList<String>();
ArrayList<String> price1=new ArrayList<String>();
ArrayList<String> added=new ArrayList<String>();
ArrayList<String> c1=new ArrayList<String>();
added.add("open");
int count1=0;
int count2=0;
try
{
	for(int outter=0;outter<FinalvalSet.size();outter++)	//Running all the data through for loop
	{
		int count=0;
		for(int srh=1;srh<totalarg;srh++) //Checking the products given through commnad line
		{	
			if(FinalvalSet.get(outter).contains(items[srh]))
			{
				count++; //Incrementing counter if product is found
				String p=FinalvalSet.get(outter).get(1);
				String ad=FinalvalSet.get(outter).get(0);
	
					if(id1.contains(ad))// Checking if product is available in combo or distributed manner
					{
						int i=id1.indexOf(ad);
						int c2=Integer.parseInt(c1.get(i));
						c2++;
						c1.set(i,String.valueOf(c2));
						double pri1=Double.parseDouble(FinalvalSet.get(outter).get(1));
						double pri2=Double.parseDouble(price1.get(i));
						double pri=pri1+pri2;
						price1.set(i,String.valueOf(pri));//System.out.println(" id1 "+id1+" "+price1);
						count1++;
						if(count1==(totalarg-1))
						{
							gotitems1=1;
						}
					}
					else
					{//System.out.println("error2 ");
						c1.add("1");	
						id1.add(ad);
						price1.add(p);
						added.add(ad);//System.out.println(" id2 "+id1+" "+price1);
						count1++;
					}
			}
			
			
		}
		if(count==(totalarg-1)) //If count is equal to no of items provided for search then the item is present in that hotel
		{
			id.add(FinalvalSet.get(outter).get(0)); //Adding hotel id
			price.add(FinalvalSet.get(outter).get(1)); //adding product price
			gotitems=1;
		}
		
	}
for(int rm=0;rm<c1.size();rm++)
{
	int chk=Integer.parseInt(c1.get(rm));
	if(chk!=(totalarg-1))
	{
		price1.remove(rm);
		id1.remove(rm);
	}
}
if(gotitems==1) //Checking if the product is available
	{
		minimum(id,price);// Finding minimum price from all the available hotels through iterator
		
	}
else
{
	if(gotitems1==1) //Checking if the product is available
		{
			minimum(id1,price1);// Finding minimum price from all the available hotels through iterator		
		}
	else 
	{
		System.out.println("Items not found... Sorry please try another meal");
	}
}
}
catch(Exception e)
	{
		System.out.println("[]");
	}
}

static void minimum(ArrayList<String> id,ArrayList<String> price)
{
	double minprice=Double.parseDouble(price.get(0));
		int idfound=0;
		int minid=0;

		Iterator<String> priceIterator = price.iterator();
		while (priceIterator.hasNext()) 
		{
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
}
