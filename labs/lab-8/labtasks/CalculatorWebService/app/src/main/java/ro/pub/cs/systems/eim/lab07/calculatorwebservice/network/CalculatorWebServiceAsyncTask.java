package ro.pub.cs.systems.eim.lab08.calculatorwebservice.network;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import okhttp3.FormBody;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ro.pub.cs.systems.eim.lab08.calculatorwebservice.general.Constants;

public class CalculatorWebServiceAsyncTask extends AsyncTask<String, Void, String> {

    private TextView resultTextView;

    public CalculatorWebServiceAsyncTask(TextView resultTextView) {
        this.resultTextView = resultTextView;
    }

    @Override
    protected String doInBackground(String... params) {
        String operator1 = params[0];
        String operator2 = params[1];
        String operation = params[2];
        int method = Integer.parseInt(params[3]);

        // TODO exercise 4
        // signal missing values through error messages
        if (operator1.isEmpty() || operator2.isEmpty() || operation.isEmpty()) {
            Log.e(Constants.TAG, Constants.ERROR_MESSAGE_EMPTY);
        }

        // create an instance of a okhttp object
        OkHttpClient client = new OkHttpClient();

        // get method used for sending request from methodsSpinner

        // 1. GET
        // a) build the URL into a Get object (append the operators / operations to the Internet address)
        // b) create an instance of a HttpUrl.Builder object
        // c) execute the request, thus generating the result

        Request request = null;

        if (method == Constants.GET_OPERATION) {
            request = new Request.Builder().
                    url(Constants.GET_WEB_SERVICE_ADDRESS + "?operation=" +
                        operation + "&t1=" + operator1 + "&t2=" + operator2).
                    build();
        }

        // 2. POST
        // a) build the URL into a PostBody object
        // b) create an instance of a RequestBuilder object
        // c) execute the request, thus generating the result

        if (method == Constants.POST_OPERATION) {
            FormBody formBody = new FormBody.Builder()
                    .add("operation", operation)
                    .add("t1", operator1)
                    .add("t2", operator2)
                    .build();

            request = new Request.Builder()
                    .url(Constants.POST_WEB_SERVICE_ADDRESS)
                    .post(formBody)
                    .build();
        }

        try (Response response = client.newCall(request).execute()) {
            // Se verifică dacă cererea a avut succes (ex: cod de status 200 OK)
            if (response.isSuccessful() && response.body() != null) {
                // Se extrage conținutul răspunsului sub formă de text (String)
                String content = response.body().string();
                /* aici se poate procesa răspunsul obținut */
                Log.i(Constants.TAG, content);
            } else {
                // Se gestionează cazul de eroare (ex: 404 Not Found, 500 Server Error)
                Log.e(Constants.TAG, "Cererea nu a avut succes. Cod: " + response.code());
            }

            StringBuilder result = new StringBuilder();

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.body().byteStream()))) {
                int currentLineNumber = 0;
                String currentLineContent;

                while ((currentLineContent = bufferedReader.readLine()) != null) {
                    currentLineNumber++;
                    result.append(currentLineNumber).append(": ").append(currentLineContent).append("\n");
                }

                Log.i(Constants.TAG, result.toString());
                return result.toString();

            } catch (IOException exception) {
                if (Constants.DEBUG) {
                    exception.printStackTrace();
                }
                return "A apărut o eroare la citirea răspunsului: " + exception.getMessage();
            }

        } catch (IOException e) {
            if (Constants.DEBUG) {
                e.printStackTrace();
            }
            return "Cererea de rețea a eșuat: " + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        // display the result in resultTextView
        resultTextView.setText(result);
    }

}
