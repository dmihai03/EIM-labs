package ro.pub.cs.systems.eim.lab10.siplinphone;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.linphone.core.Account;
import org.linphone.core.AccountParams;
import org.linphone.core.Address;
import org.linphone.core.AuthInfo;
import org.linphone.core.Core;
import org.linphone.core.Factory;
import org.linphone.core.TransportType;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Factory factory = Factory.instance();
        Core core = factory.createCore(null, null, this);

        TextView status = findViewById(R.id.registration_status);
        status.setText("linphone lib version: " + core.getVersion());

        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.register_layout).setVisibility(View.GONE);
                findViewById(R.id.call_layout).setVisibility(View.VISIBLE);

                if (login(core)) {

                }
            }
        });

        findViewById(R.id.unregister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.register_layout).setVisibility(View.VISIBLE);
                findViewById(R.id.call_layout).setVisibility(View.GONE);
            }
        });
    }

    private boolean login(Core core) {
        String username = ((EditText) findViewById(R.id.username)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();
        String domain = ((EditText) findViewById(R.id.domain)).getText().toString();

        TransportType transportType;
        int checkedId = ((RadioGroup) findViewById(R.id.transport)).getCheckedRadioButtonId();
        if (checkedId == R.id.udp) {
            transportType = TransportType.Udp;
        } else if (checkedId == R.id.tcp) {
            transportType = TransportType.Tcp;
        } else {
            transportType = TransportType.Tls;
        }

        AuthInfo authInfo = Factory.instance().createAuthInfo(username, null, password, null, null, domain, null);

        AccountParams params = core.createAccountParams();
        Address identity = Factory.instance().createAddress("sip:" + username + "@" + domain);
        if (identity == null) {
            Toast.makeText(this, "Identity not valid", Toast.LENGTH_LONG).show();
            return false;
        }
        params.setIdentityAddress(identity);

        Address address = Factory.instance().createAddress("sip:" + domain);
        if (address != null) {
            address.setTransport(transportType);
        }
        params.setServerAddress(address);
        params.setRegisterEnabled(true);

        Account account = core.createAccount(params);
        core.addAuthInfo(authInfo);
        core.addAccount(account);

        core.setDefaultAccount(account);
        core.addListener(coreListener);

        core.start();

        if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, 0);
            return false;
        }
        return true;
    }

}

