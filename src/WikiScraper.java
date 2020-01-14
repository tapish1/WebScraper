import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import javax.swing.JFrame;

public class WikiScraper {
	static ArrayList <String> bookTitles=new ArrayList<>();
	static ArrayList <String> prices=new ArrayList<>();
	static ArrayList <String> authors=new ArrayList<>();
	
	public static void main (String []args) throws IOException {
		CLayout obj=new CLayout();
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.setSize(700,450);
		obj.setVisible(true);
		
		
		String url="https://www.amazon.com/best-sellers-books-Amazon/zgbs/books";
		Document page= Jsoup.connect(url).userAgent("Jsoup scraper").get();
		String Selector=".aok-inline-block.zg-item";
		Elements bookElms=page.select(Selector);
		
		
		for(Element elms: bookElms) {
			bookTitles.add(elms.select(" > .a-link-normal").text());
			String author=elms.select("div.a-row .a-size-small").text();
			authors.add(author.substring(0,author.length()-10));
			prices.add(elms.select("div.a-row .p13n-sc-price").text());
			//Need .text because element object stores whole html tag but we only need text
		}
		
	
		
	}
	
	public String  findbook(String book_name) {
		
		int place=1;
		String output="Sorry "+book_name+" isn't a top 50 book";
		for(String names_books: bookTitles) {
			if(book_name.equals(names_books)){
				output= "Yes "+book_name+" is a top 50 book and is "+place+" out of 50";
				break;
			}
			
			}
		return output;	
	}
	
	public String findauthor(String name) {
		String author=name+ " is not a top 50 author";
		for(String author_names: authors) {
			if(name.contentEquals(author_names)) {
				author= name +" is a top 50 author";
			}
		}
		
		return author;
	}
	
	public String bookspriced (double price) {
		String output="";
		int counter=1;

		for(int i=0; i<prices.size(); i++) {
			if(prices.get(i).equals("")) {
				prices.remove(i);
			}
			double cost=Double.parseDouble(prices.get(i).substring(1));
			if(cost<= price) {
				output+=counter+". "+bookTitles.get(i)+" costs "+prices.get(i)+"\n";	
				counter++;

			}
		}
		
		return output;
	}
		

	}

	
