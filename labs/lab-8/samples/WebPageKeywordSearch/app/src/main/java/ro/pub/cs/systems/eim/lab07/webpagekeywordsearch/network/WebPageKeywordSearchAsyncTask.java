package ro.pub.cs.systems.eim.lab07.webpagekeywordsearch.network;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import ro.pub.cs.systems.eim.lab07.webpagekeywordsearch.general.Constants;
import ro.pub.cs.systems.eim.lab07.webpagekeywordsearch.general.Utilities;

public class WebPageKeywordSearchAsyncTask extends AsyncTask<String, Void, String> {

    private TextView resultsTextView;

    public WebPageKeywordSearchAsyncTask(TextView resultsTextView) {
        this.resultsTextView = resultsTextView;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection httpURLConnection = null;
        StringBuilder result = new StringBuilder();
        String error = null;
        try {
            String webPageAddress = params[0];
            String keyword = params[1];
            if (webPageAddress == null || webPageAddress.isEmpty()) {
                error = "Web Page address cannot be empty";
            }
            if (keyword == null || keyword.isEmpty()) {
                error = "Keyword cannot be empty";
            }
            if (error != null) {
                return error;
            }
            URLConnection urlConnection = getUrlConnection(webPageAddress, result);
            if (urlConnection instanceof HttpURLConnection) {
                httpURLConnection = (HttpURLConnection)urlConnection;
                BufferedReader bufferedReader = Utilities.getReader(httpURLConnection);
                int currentLineNumber = 0, numberOfOccurrences = 0;
                String currentLineContent;
                while ((currentLineContent = bufferedReader.readLine()) != null) {
                    currentLineNumber++;
                    if (currentLineContent.contains(keyword)) {
                        result.append("line: ").append(currentLineNumber).append(" / ").append(currentLineContent).append("\n");
                        numberOfOccurrences++;
                    }
                }
                result.append("Number of occurrences: ").append(numberOfOccurrences).append("\n");
                return result.toString();
            }
        } catch (Exception exception) {
            Log.e(Constants.TAG, exception.getMessage());
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return null;
    }

    private static URLConnection getUrlConnection(String webPageAddress, StringBuilder result) throws IOException {
        URL url = new URL(webPageAddress);
        result.append("Protocol: ").append(url.getProtocol()).append("\n");
        result.append("Host: ").append(url.getHost()).append("\n");
        result.append("Port: ").append(url.getPort()).append("\n");
        result.append("File: ").append(url.getFile()).append("\n");
        result.append("Reference: ").append(url.getRef()).append("\n");
        result.append("==========\n");
        return url.openConnection();
    }

    @Override
    protected void onPostExecute(String result) {
        resultsTextView.setText(result);
    }
}
