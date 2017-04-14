package edu.sfsu.cs.orange.ocr;

import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;


public class SelectBookActivity extends Activity {
    private String bookName;
    private ArrayList<Book> books = new ArrayList<>();
    private RecyclerView recyclerView;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_book);
        bookName = getIntent().getStringExtra("bookName");
        recyclerView = (RecyclerView) findViewById(R.id.recyclyerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        BooksAdapter adapter = new BooksAdapter(this, books);
        recyclerView.setAdapter(adapter);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            String urlUsed = "https://www.goodreads.com/search/index.xml?key=tW1VF0rIISlnvrB8t7pyOw&q=" + (URLEncoder.encode(bookName, "UTF-8"));
            String xmlUnparsed = run(urlUsed);
            (((TextView) findViewById(R.id.bookTitle))).setText(urlUsed);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xmlUnparsed));
            Document doc = db.parse(is);

            XPath xPath = XPathFactory.newInstance().newXPath();
//

            String[] bookNames = new String[20];
            String[] author = new String[20];
            Integer[] pulicationDay = new Integer[20];
            Integer[] pulicationMonth = new Integer[20];
            Integer[] pulicationYear = new Integer[20];
            Double[] averageRating = new Double[20];
            int length = 20;

            for(int i =0; i <= 20 - 1; i++) {
                bookNames[i] = "Not initialized";
                author[i] = "Not found";
                pulicationDay[i] = Integer.parseInt("-1");
                pulicationMonth[i] = Integer.parseInt("-1");
                pulicationYear[i] = Integer.parseInt("-1");
                averageRating[i] = 0.00;

            int add =0;
//
            {
                String expression = "/GoodreadsResponse/search/results/work/best_book/title";
                NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
                for (int n = 0; n < 20; n++) {
                    bookNames[n] = nodeList.item(i).getFirstChild().getNodeValue().toString();
                    add += 1;
                }
            }
//
//
            {
                String expression = "/GoodreadsResponse/search/results/work/best_book/author/name";
                NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
                for (int j = 0; j <20;j++) {
                    author[j] = nodeList.item(i).getFirstChild().getNodeValue().toString();
                    add+=1;
                }
            }
//
            {
                String expression = "/GoodreadsResponse/search/results/work/original_publication_day";
                NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
                for (int z = 0; z < 20; z++) {
                    if (nodeList.item(z).getFirstChild() != null) {
                        pulicationDay[z] = Integer.parseInt(nodeList.item(i).getFirstChild().getNodeValue());

                    } else {
                        pulicationDay[z] = -1;
                        add+=1;
                    }
                }
            }
            {
                String expression = "/GoodreadsResponse/search/results/work/original_publication_month";
                NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
                for (int b = 0; b < 20; b++) {
                    if (nodeList.item(b).getFirstChild() != null) {
                        pulicationMonth[b] = Integer.parseInt(nodeList.item(i).getFirstChild().getNodeValue());
                        add+=1;
                    } else {
                        pulicationMonth[b] = -1;
                        add+=1;
                    }
                }
            }
            {
                  String expression = "/GoodreadsResponse/search/results/work/original_publication_year";
                NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
                for (int a = 0; a < 20; a++) {
                    if (nodeList.item(a).getFirstChild() != null) {
                        pulicationYear[a] = Integer.parseInt(nodeList.item(i).getFirstChild().getNodeValue());
                        add+=1;
                    }else {
                        pulicationMonth[a] = -1;
                        add += 1;
                    }
                }
            }
//
            {
                String expression = "/GoodreadsResponse/search/results/work/average_rating";
//
              NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
                for (int q = 0; q < 20; q++) {
                    length = nodeList.getLength();
                    if (nodeList.item(q).getFirstChild() != null) {
                        averageRating[q] = Double.parseDouble(nodeList.item(i).getFirstChild().getNodeValue());
                        add +=1;
                    } else {
                        averageRating[q] = (double)1;
                        add +=1;
                    }
                }

                Log.d("LOL", "add = " + add);
                for(int c = 0; i <= 20; c++) {
                    books.add(new Book(bookNames[c], author[c], pulicationDay[c], pulicationMonth[c], pulicationYear[c], averageRating[c]));
//                books.add(new Book("", "", 1, 1, 1, 5));
                }
            }

        }} catch (Exception e){
                Log.e("MYAPP", "exception", e);
            Toast.makeText(this, "No information was found in regards to the book specified.", Toast.LENGTH_LONG).show();
        }
    }

    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BooksAdapter adapter = new BooksAdapter(this, books);
        recyclerView.setAdapter(adapter);
    }
}