import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class CLayout extends JFrame {

	public JPanel contentPanel=new JPanel();
;	public JPanel home_panel=new JPanel();
	public JPanel search_book=new JPanel();
	public JPanel search_author=new JPanel();
	public JPanel search_price=new JPanel();
	public JPanel buttons=new JPanel();
	private JLabel header;
	private JButton find_book;
	private JButton find_author;
	private JButton find_price;
	private JButton home;
	private JButton home2;
	private JButton home3;
	public CardLayout cl=new CardLayout();
	public JTextField books_input;
	public JTextField books_output;
	public WikiScraper connect=new WikiScraper();
	public JTextField author_input;
	public JTextField author_output;
	public JTextField price_input;
	public JTextArea price_output;
	private thehandler handler=new thehandler();



public CLayout() {
	super("Web Scraper");
	 header=new JLabel("Web Scraper For Amazons Top 50 Books");
	 header.setFont(new Font("Open Sans", Font.BOLD, 24));

	 home=new JButton("Home");
	 home2=new JButton("Home");
	 home3=new JButton("Home");
	 find_book=new JButton("Find Book");
	 find_price=new JButton("Search by Price");
	 find_author=new JButton("Search by Author");
	 
	    find_book.setFont(new Font("Open Sans", Font.PLAIN, 18));
	    find_price.setFont(new Font("Open Sans", Font.PLAIN, 18));
	    find_author.setFont(new Font("Open Sans", Font.PLAIN, 18));

	 
	home_panel.setBackground(Color.GRAY);
	search_book.setBackground(Color.GRAY);
	search_author.setBackground(Color.GRAY);
	search_price.setBackground(Color.GRAY);
	buttons.setBackground(Color.GRAY);
	
	find_book.addActionListener(handler);
	find_price.addActionListener(handler);
	find_author.addActionListener(handler);
	home.addActionListener(handler);
	home2.addActionListener(handler);
	home3.addActionListener(handler);
	
	home_panel.setLayout(new BoxLayout(home_panel, BoxLayout.Y_AXIS));
	home_panel.add(Box.createRigidArea(new Dimension(0,150)));
	header.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	buttons.setPreferredSize( new Dimension(100, 100) );
	buttons.setLayout(new FlowLayout(FlowLayout.CENTER));

	
	find_book.setPreferredSize(new Dimension(200,25));
	find_price.setPreferredSize(new Dimension(200,25));
	find_author.setPreferredSize(new Dimension(200,25));
	home_panel.add(header);
	home_panel.add(Box.createRigidArea(new Dimension(0,15)));
	buttons.add(find_book);
	buttons.add(find_price);
	buttons.add(find_author);
	home_panel.add(buttons);
	search_book.add(home);
	search_price.add(home2);
	search_author.add(home3);
	books_input=new JTextField("Enter name of book youd like to find");
	search_book.add(books_input);
	books_output=new JTextField(50);
	search_book.add(books_output);
	author_input=new JTextField("Enter author youd like to find");
	author_output=new JTextField(50);
	search_author.add(author_input);
	search_author.add(author_output);
	price_input=new JTextField("Enter a max price");
	price_output=new JTextArea();
	search_price.add(price_input);
	search_price.add(price_output);
	
	
	contentPanel.setLayout(cl);
	contentPanel.add(home_panel, "1");
	contentPanel.add(search_book, "2");
	contentPanel.add(search_author, "3");
	contentPanel.add(search_price, "4");
	this.setContentPane(contentPanel); 
	cl.show(contentPanel, "1");
	

	
}
private class thehandler implements ActionListener {
	public void actionPerformed (ActionEvent event) {
		if(event.getSource()==find_book) {
			cl.show(contentPanel,"2");
			books_input.addActionListener(handler);
			}else if(event.getSource()==books_input) {
				String output=connect.findbook(event.getActionCommand());
				books_output.setText(output);
			}else if(event.getSource()==find_author) {
				cl.show(contentPanel,"3");
				author_input.addActionListener(handler);
			}else if(event.getSource()==author_input) {
				String output=connect.findauthor(event.getActionCommand());
				author_output.setText(output);
			}else if(event.getSource()==find_price) {
				cl.show(contentPanel,"4");
				price_input.addActionListener(handler);
			}else if(event.getSource()==price_input) {
				try {
				String output=connect.bookspriced(Double.parseDouble(event.getActionCommand()));
				price_output.append(output);
				}catch(NumberFormatException nfe) {
					price_output.setText("Please enter a number");
				}
			}else if(event.getSource()==home  || event.getSource()==home2 || event.getSource()==home3 ) {
				cl.show(contentPanel,"1");
				books_input.setText("Enter name of book youd like to find");
				author_input.setText("Enter author youd like to find");
				price_input.setText("Enter a max price");
				books_output.setText("");
				author_output.setText("");
				price_output.setText("");
			}
	}

	}
}
	


