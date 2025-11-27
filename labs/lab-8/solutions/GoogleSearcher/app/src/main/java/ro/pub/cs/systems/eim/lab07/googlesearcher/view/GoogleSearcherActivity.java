package ro.pub.cs.systems.eim.lab07.googlesearcher.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ro.pub.cs.systems.eim.lab07.googlesearcher.R;
import ro.pub.cs.systems.eim.lab07.googlesearcher.general.Constants;
import ro.pub.cs.systems.eim.lab07.googlesearcher.network.GoogleSearcherAsyncTask;

public class GoogleSearcherActivity extends AppCompatActivity {

    private EditText keywordEditText;
    private WebView googleResultsWebView;

    private final SearchGoogleButtonClickListener searchGoogleButtonClickListener = new SearchGoogleButtonClickListener();
    private class SearchGoogleButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            StringBuilder keyword = new StringBuilder(keywordEditText.getText().toString());
            if (keyword.length() == 0) {
                Toast.makeText(getApplication(), Constants.EMPTY_KEYWORD_ERROR_MESSAGE, Toast.LENGTH_LONG).show();
            } else {
                String[] keywords = keyword.toString().split(" ");
                keyword = new StringBuilder(Constants.SEARCH_PREFIX + keywords[0]);
                for (int index = 1; index < keywords.length; index++) {
                    keyword.append("+").append(keywords[index]);
                }
                new GoogleSearcherAsyncTask(googleResultsWebView).execute(keyword.toString());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_searcher);

        keywordEditText = (EditText)findViewById(R.id.keyword_edit_text);
        googleResultsWebView = (WebView)findViewById(R.id.google_results_web_view);

        Button searchGoogleButton = (Button) findViewById(R.id.search_google_button);
        searchGoogleButton.setOnClickListener(searchGoogleButtonClickListener);
    }
}
