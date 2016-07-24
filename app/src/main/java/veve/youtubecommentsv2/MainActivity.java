package veve.youtubecommentsv2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.auth.ui.ActivityHelper;
import com.firebase.ui.auth.ui.AppCompatBase;
import com.firebase.ui.auth.ui.ExtraConstants;
import com.firebase.ui.auth.ui.FlowParameters;
import com.firebase.ui.auth.ui.TaskFailureLogger;
import com.firebase.ui.auth.ui.account_link.SaveCredentialsActivity;
import com.firebase.ui.auth.ui.email.PasswordToggler;
import com.firebase.ui.auth.ui.email.RecoverPasswordActivity;
import com.firebase.ui.auth.ui.email.field_validators.EmailFieldValidator;
import com.firebase.ui.auth.ui.email.field_validators.RequiredFieldValidator;
import com.firebase.ui.auth.util.FirebaseAuthWrapperFactory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatBase implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private EmailFieldValidator mEmailValidator;
    private RequiredFieldValidator mPasswordValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(com.firebase.ui.auth.R.string.sign_in);
        setContentView(com.firebase.ui.auth.R.layout.sign_in_layout);

        String email = getIntent().getStringExtra(ExtraConstants.EXTRA_EMAIL);

        mEmailEditText = (EditText) findViewById(com.firebase.ui.auth.R.id.email);

        TypedValue visibleIcon = new TypedValue();
        TypedValue slightlyVisibleIcon = new TypedValue();

        getResources().getValue(com.firebase.ui.auth.R.dimen.visible_icon, visibleIcon, true);
        getResources().getValue(com.firebase.ui.auth.R.dimen.slightly_visible_icon, slightlyVisibleIcon, true);

        mPasswordEditText = (EditText) findViewById(com.firebase.ui.auth.R.id.password);
        ImageView mTogglePasswordImage = (ImageView) findViewById(com.firebase.ui.auth.R.id.toggle_visibility);

        mPasswordEditText.setOnFocusChangeListener(new ImageFocusTransparencyChanger(
                mTogglePasswordImage,
                visibleIcon.getFloat(),
                slightlyVisibleIcon.getFloat()));

        mTogglePasswordImage.setOnClickListener(new PasswordToggler(mPasswordEditText));

        mEmailValidator = new EmailFieldValidator((TextInputLayout) findViewById(com.firebase.ui.auth.R.id
                .email_layout));
        mPasswordValidator = new RequiredFieldValidator((TextInputLayout) findViewById(com.firebase.ui.auth.R.id
                .password_layout));
        Button signInButton = (Button) findViewById(com.firebase.ui.auth.R.id.button_done);
        TextView recoveryButton =  (TextView) findViewById(com.firebase.ui.auth.R.id.trouble_signing_in);

        if (email != null) {
            mEmailEditText.setText(email);
        }
        signInButton.setOnClickListener(this);
        recoveryButton.setOnClickListener(this);

    }

    @Override
    public void onBackPressed () {
        super.onBackPressed();
    }

    private void signIn(String email, final String password) {
        mActivityHelper.getFirebaseAuth()
                .signInWithEmailAndPassword(email, password)
                .addOnFailureListener(
                        new TaskFailureLogger(TAG, "Error signing in with email and password"))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mActivityHelper.dismissDialog();
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = task.getResult().getUser();
                            if (FirebaseAuthWrapperFactory.getFirebaseAuthWrapper(
                                    mActivityHelper.getAppName())
                                    .isPlayServicesAvailable(MainActivity.this)) {
                                Intent saveCredentialIntent =
                                        SaveCredentialsActivity.createIntent(
                                                MainActivity.this,
                                                mActivityHelper.getFlowParams(),
                                                firebaseUser.getDisplayName(),
                                                firebaseUser.getEmail(),
                                                password,
                                                null,
                                                null);
                                startActivity(saveCredentialIntent);
                                finish(RESULT_OK, new Intent());
                            }
                        } else {
                            TextInputLayout passwordInput =
                                    (TextInputLayout) findViewById(com.firebase.ui.auth.R.id.password_layout);
                            passwordInput.setError(
                                    getString(com.firebase.ui.auth.R.string.login_error));
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == com.firebase.ui.auth.R.id.button_done) {
            boolean emailValid = mEmailValidator.validate(mEmailEditText.getText());
            boolean passwordValid = mPasswordValidator.validate(mPasswordEditText.getText());
            if (!emailValid || !passwordValid) {
                return;
            } else {
                mActivityHelper.showLoadingDialog(com.firebase.ui.auth.R.string.progress_dialog_signing_in);
                signIn(mEmailEditText.getText().toString(), mPasswordEditText.getText().toString());
                return;
            }
        } else if (view.getId() == com.firebase.ui.auth.R.id.trouble_signing_in) {
            startActivity(RecoverPasswordActivity.createIntent(
                    this,
                    mActivityHelper.getFlowParams(),
                    mEmailEditText.getText().toString()));
            return;
        }
    }

    public static Intent createIntent(Context context, FlowParameters flowParams,
            String email) {
        return ActivityHelper.createBaseIntent(context, MainActivity.class, flowParams)
                .putExtra(ExtraConstants.EXTRA_EMAIL, email);
    }

}
